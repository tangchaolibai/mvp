package com.hsb.mvpmsweb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.util.comparator.Comparators;

import com.alibaba.fastjson.JSONObject;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.constant.BaseConstants;
import com.hsb.mvpmsweb.constant.SwiftPassConstants;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

public class WebUtils {

	private WebUtils() {}

	public static String genMD5(String str) {
		return SecureUtil.md5(str).toUpperCase();
	}

	public static String genSHA256(String str) {
		return SecureUtil.sha256(str).toUpperCase();
	}

	public static String genRandomStr(int length) {
		return RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER, length).toLowerCase();
	}

	public static String beanToJson(Object object) {
		return JSONObject.toJSONString(object);
	}

	public static Object jsonToBean(String jsonStr, Class<?> cls) {
		return JSONObject.parseObject(jsonStr, cls.getClass());
	}
	
	public static JSONObject objectToJson(Object object) {
		return JSONObject.parseObject(object.toString());
	}

	// forms: "key=value&..." (key = @XmlElement(name))
	public static <T> String genOriStrByXmlBean(T t) throws MvpWebException {
		StringBuilder sb = new StringBuilder();
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> strList = new ArrayList<>();
		try {
			for (Field field : fields) {
				if(field.get(t) != null && !StrUtil.equals(field.getName(), SwiftPassConstants.API_SIGN))
					strList.add(field.getAnnotation(XmlElement.class).name() + "=" + field.get(t));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new MvpWebException(null, e.getMessage());
		} 

		strList = strList.stream().filter(s -> !s.startsWith("/")).sorted(Comparators.comparable()).collect(Collectors.toList());
		strList.forEach(s -> sb.append(s + "&"));
		return sb.toString();
	}
	
	public static String genOriStrByJsonObject(JSONObject object) throws MvpWebException {
		StringBuilder sb = new StringBuilder();
		List<String> strList = new ArrayList<>();
		for(String key : object.keySet()) {
			if(object.get(key) != null && !StrUtil.equals(key, SwiftPassConstants.API_SIGN)) {
				strList.add(key + "=" + object.get(key));
			}
		}
		
		strList = strList.stream().filter(s -> !s.startsWith("/")).sorted(Comparators.comparable()).collect(Collectors.toList());
		strList.forEach(s -> sb.append(s + "&"));
		return sb.toString();
	}

	public static String beanToXml(Object obj, Class<?> load) throws MvpWebException {
		String xmlStr = null;
		try {
			JAXBContext context = JAXBContext.newInstance(load);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			xmlStr = writer.toString();
		} catch (JAXBException e) {
			throw new MvpWebException(null, e.getMessage());
		}
		return xmlStr;
	}

	public static Object xmlToBean(String str, Class<?> load) throws MvpWebException {
		Object object = null;
		try {
			JAXBContext context = JAXBContext.newInstance(load);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			object = unmarshaller.unmarshal(new StringReader(str));
		} catch (JAXBException e) {
			throw new MvpWebException(null, e.getMessage());
		}
		return object;
	}

	public static void genXml(Document doc, String FilePath) throws MvpWebException {
		OutputFormat format = new OutputFormat(" ", true, BaseConstants.ENCODING_UTF8);
		format.setNewlines(true); 
		format.setIndent(true); 
		format.setIndent("    "); 
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(FilePath)), format);
			xmlWriter.write(doc);
			xmlWriter.flush();
			xmlWriter.close();
		} catch (IOException e) {
			throw new MvpWebException(null, e.getMessage());
		} 
	}

	public static JSONObject xmlToJson(String xmlStr) throws MvpWebException {
		JSONObject json = new JSONObject();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException | ClassCastException e) {
			throw new MvpWebException(null, e.getMessage());
		}
		Element root = doc.getRootElement();
		json.put(root.getName(), iterateElement(root));
		return json;
	}

	@SuppressWarnings("unchecked")
	private static JSONObject iterateElement(Element element) throws ClassCastException {
		List<Element> node = element.elements();
		JSONObject obj = new JSONObject();
		List<Object> list = null;
		for (Element child : node) {
			list = new LinkedList<>();
			String text = child.getTextTrim();
			if (StrUtil.isBlank(text)) {
				if (child.elements().size() == 0) {
					continue;
				}
				if (obj.containsKey(child.getName())) {
					list = (List<Object>) obj.get(child.getName());
				}
				list.add(iterateElement(child));
				obj.put(child.getName(), list);
			} else {
				if (obj.containsKey(child.getName())) {
					Object value = obj.get(child.getName());
					try {
						list = (List<Object>) value;
					} catch (ClassCastException e) {
						list.add(value);
					}
				}
				if (child.elements().size() == 0) { 
					obj.put(child.getName(), text);
				} else {
					list.add(text);
					obj.put(child.getName(), list);
				}
			}
		}
		return obj;
	}
	
	public static String getJsonObjectValue(JSONObject object, String key) {
		return (object.get(key) == null) ? null : object.get(key).toString();
	}
	
	public static String getLocalDateTimeAgo(LocalDateTime time) {
		String result = "ago";
		Duration duration = LocalDateTimeUtil.between(time, LocalDateTime.now());
		
		if(duration.toDays() >= BaseConstants.DAYS_OF_YEAR * 2) {
			result = duration.toDays() / BaseConstants.DAYS_OF_YEAR + " years " + result;
		}else if(duration.toDays() >= BaseConstants.DAYS_OF_YEAR) {
			result = duration.toDays() / BaseConstants.DAYS_OF_YEAR + " year " + result;
		}else if(duration.toDays() >= BaseConstants.DAYS_OF_MONTH * 2) {
			result = duration.toDays() / BaseConstants.DAYS_OF_MONTH + " months " + result;
		}else if(duration.toDays() >= BaseConstants.DAYS_OF_MONTH) {
			result = duration.toDays() / BaseConstants.DAYS_OF_MONTH + " month " + result;
		}else if(duration.toDays() > 1L) {
			result = duration.toDays() + " days " + result;
		}else if(duration.toDays() > 0L) {
			result = duration.toDays() + " day " + result;
		}else if(duration.toHours() > 1L) {
			result = duration.toHours() + " hours " + result;
		}else if(duration.toHours() > 0L) {
			result = duration.toHours() + " hour " + result;
		}else if(duration.toMinutes() > 1L) {
			result = duration.toMinutes() + " mins " + result;
		}else {
			result = "1 min " + result;
		}
		
		return result;
	}

}
