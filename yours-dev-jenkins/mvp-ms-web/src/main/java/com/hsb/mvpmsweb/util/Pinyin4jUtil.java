package com.hsb.mvpmsweb.util;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 多音字暂时只返回了一个
 * @author user
 *
 */
public class Pinyin4jUtil {
	/**
	 * 获取中文汉字拼音 默认输出   
	 * @param chinese
	 * @return
	 */
    public static String getPinyin(String chinese) {  
        return getPinyinZh_CN(makeStringByStringSet(chinese)).split(",")[0];  
    }  
  
    /**
     * 拼音大写输出 
     * @param chinese
     * @return
     */
    public static String getPinyinToUpperCase(String chinese) {  
        return getPinyinZh_CN(makeStringByStringSet(chinese)).toUpperCase().split(",")[0];  
    }  
  
    /**
     * 拼音小写输出  
     * @param chinese
     * @return
     */
    public static String getPinyinToLowerCase(String chinese) {  
        return getPinyinZh_CN(makeStringByStringSet(chinese)).toLowerCase().split(",")[0];  
    }  
  
    /**
     * 首字母大写输出  
     * @param chinese
     * @return
     */
    public static String getPinyinFirstToUpperCase(String chinese) {  
        return getPinyin(chinese).split(",")[0];  
    }
    /**
             * 只输出大写首字母
     * @param chinese
     * @return
     */
   public static String getFirstToUpperCase(String chinese) {  
       return String.valueOf(getPinyin(chinese).split(",")[0].charAt(0));  
   } 
  
    /**
     * 拼音简拼输出 
     * @param chinese
     * @return
     */
    public static String getPinyinJianPin(String chinese) {  
        return getPinyinConvertJianPin(getPinyin(chinese)).split(",")[0];  
    }  
    
    /**
     * 拼音简拼输出[小写形式]
     * @param chinese
     * @return
     */
    public static String getPinyinJianPinLowerCase(String chinese) {  
        return getPinyinConvertJianPin(getPinyin(chinese)).toLowerCase().split(",")[0];  
    }
  
    /**
     * 字符集转换 
     * @param chinese
     * @return
     */
    public static Set<String> makeStringByStringSet(String chinese) {  
        char[] chars = chinese.toCharArray();  
        if (chinese != null && !chinese.trim().equalsIgnoreCase("")) {  
            char[] srcChar = chinese.toCharArray();
            //转为二维String数组
            String[][] temp = new String[chinese.length()][];  
            for (int i = 0; i < srcChar.length; i++) {  
                char c = srcChar[i];  
  
                // 是中文或者a-z或者A-Z转换拼音  
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {  
  
                    try {  
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(  
                                chars[i], getDefaultOutputFormat());  
  
                    } catch (BadHanyuPinyinOutputFormatCombination e) {  
                        e.printStackTrace();  
                    }  
                } else if (((int) c >= 65 && (int) c <= 90)  
                        || ((int) c >= 97 && (int) c <= 122)
                        || ((int) c >= 48 && (int) c <= 57)) { 
                	//48-57是数字的ASCLL码十进制范围，其余为大小写的ASCLL码范围
                    temp[i] = new String[] { String.valueOf(srcChar[i]) };  
                } else {  
                    temp[i] = new String[] { "#" };  
                }  
            }  
            String[] pingyinArray = Exchange(temp);  
            Set<String> zhongWenPinYin = new HashSet<String>();  
            for (int i = 0; i < pingyinArray.length; i++) {  
                zhongWenPinYin.add(pingyinArray[i]);  
            }  
            return zhongWenPinYin;  
        }  
        return null;  
    }  
  
    /**
     * Default Format 默认输出格式   
     * @return
     */
    public static HanyuPinyinOutputFormat getDefaultOutputFormat() {  
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写  
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字  
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示  
        return format;  
    }  
  
    public static String[] Exchange(String[][] strJaggedArray) {  
        String[][] temp = doExchange(strJaggedArray);  
        return temp[0];  
    }  
  
    private static String[][] doExchange(String[][] strJaggedArray) {  
        int len = strJaggedArray.length;  
        if (len >= 2) {  
            int len1 = strJaggedArray[0].length;  
            int len2 = strJaggedArray[1].length;  
            int newlen = len1 * len2;  
            String[] temp = new String[newlen];  
            int Index = 0;  
            for (int i = 0; i < len1; i++) {  
                for (int j = 0; j < len2; j++) {  
                    temp[Index] = capitalize(strJaggedArray[0][i])  
                            + capitalize(strJaggedArray[1][j]);  
                    Index++;  
                }  
            }  
            String[][] newArray = new String[len - 1][];  
            for (int i = 2; i < len; i++) {  
                newArray[i - 1] = strJaggedArray[i];  
            }  
            newArray[0] = temp;
            //递归将二维数组转为一维，并合并
            return doExchange(newArray);  
        } else {  
            return strJaggedArray;  
        }  
    }  
  
    /**
     * 首字母大写   
     * @param s
     * @return
     */
    public static String capitalize(String s) {  
        char ch[];  
        ch = s.toCharArray(); 
        if (ch!= null && ch.length > 0) {
	        if (ch[0] >= 'a' && ch[0] <= 'z') {  
	            ch[0] = (char) (ch[0] - 32);  
	        }  
        }
        String newString = new String(ch);  
        return newString;  
    }  
  
    /**
     * 字符串集合转换字符串(逗号分隔)  
     * @param stringSet
     * @return
     */
    public static String getPinyinZh_CN(Set<String> stringSet) {  
        StringBuilder str = new StringBuilder();  
        int i = 0;  
        for (String s : stringSet) {  
            if (i == stringSet.size() - 1) {  
                str.append(s);  
            } else {  
                str.append(s + ",");  
            }  
            i++;  
        }  
        return str.toString();  
    }  
  
    /**
     * 获取每个拼音的简称 
     * @param chinese
     * @return
     */
    public static String getPinyinConvertJianPin(String chinese) {  
        String[] strArray = chinese.split(",");  
        String strChar = "";  
        for (String str : strArray) {  
            char arr[] = str.toCharArray(); // 将字符串转化成char型数组  
            for (int i = 0; i < arr.length; i++) {
            	if ((arr[i] >= 65 && arr[i] < 91) ||
            		(arr[i]>=48 && arr[i]<=57))  { // 判断是否是大写字母  
                    strChar += arr[i] + "";  
                }  
            }  
            strChar += ",";  
        }  
        return strChar;  
    }  
    /*
    public static void main(String[] args) {  
        String str = "重庆 123 abc";  
        System.out.println("小写输出：" + getPinyinToLowerCase(str));  
        System.out.println("大写输出：" + getPinyinToUpperCase(str));  
        System.out.println("首字母大写输出：" + getPinyinFirstToUpperCase(str));  
        System.out.println("简拼大写输出：" + getPinyinJianPin(str));  
        System.out.println("简拼小写输出：" + getPinyinJianPinLowerCase(str));  
    }  
    */
    /**
     * HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
　　         * UPPERCASE：大写  (ZHONG)
　　         * LOWERCASE：小写  (zhong)
　　        * format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
　　        * WITHOUT_TONE：无音标  (zhong)
　　        * WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)
　　        * WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
　　        * format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
　　        * WITH_V：用v表示ü  (nv)
　　        * WITH_U_AND_COLON：用"u:"表示ü  (nu:)
　　        * WITH_U_UNICODE：直接用ü (nü)
　　        * format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
     */
}
