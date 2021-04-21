package com.hsb.mvpmsweb.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsweb.api.exception.MvpTicketException;
import com.hsb.mvpmsweb.api.exception.MvpTicketExceptionCode;
import com.hsb.mvpmsweb.mapper.PerformenceMapper;
import com.hsb.mvpmsweb.mapper.ProductMapper;
import com.hsb.mvpmsweb.model.domain.Performence;
import com.hsb.mvpmsweb.model.domain.Product;
import com.hsb.mvpmsweb.model.payload.ticket.ProductDetailResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.ProductResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.PutOnSaleResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.request.PutOnSaleRequest;
import com.hsb.mvpmsweb.service.ProductService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
	
	@Autowired
	private PerformenceMapper performenceMapper;
	
	@Value("${mvp.file.path}")
	private String uploadFilePath;
	
	@Value("${user.dir}")
	private String userdir;

	@Override
	public PutOnSaleResponseData putOnSale(@Valid PutOnSaleRequest putOnSaleRequest/*, @PictureType MultipartFile posterPic*/) throws MvpTicketException {
		Performence performence = new Performence();
		performence.setCastMember(putOnSaleRequest.getCastMember()).setDate(putOnSaleRequest.getDate()).setEndTime(putOnSaleRequest.getEndTime())
			.setLocation(putOnSaleRequest.getLocation()).setName(putOnSaleRequest.getPerformenceName()).setPrice(putOnSaleRequest.getPrice())
			.setStartTime(putOnSaleRequest.getStartTime()).setTicketLeft(putOnSaleRequest.getTicketsAmount());
		performenceMapper.insert(performence);
		Product product = new Product();
		product.setCurrency(putOnSaleRequest.getCurrency()).setName(putOnSaleRequest.getProductName()).setPerformenceId(performence.getEntityId())
			.setUserId(putOnSaleRequest.getUserId());
		save(product);
		PutOnSaleResponseData data = new PutOnSaleResponseData();
		data.setEntityId(product.getEntityId());
		return data;
	}

	@Override
	public void uploadPic(@Valid int productId, MultipartFile multipartFile) throws MvpTicketException {
		Product product = getById(productId);
		File file = new File(userdir.replace("\\", "/"));
		String savePath = file.getParent().replace("\\", "/") + uploadFilePath + "/" + product.getUserId() + "/products/";
		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdirs();
		try {
			String filename = String.valueOf(productId) + '_' + multipartFile.getOriginalFilename();
			multipartFile.transferTo(new File(savePath, filename));
			product.setPhotoPath("/useruploadFile" + "/" + product.getUserId() + "/products/" + filename);
		} catch (IOException e) {
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_01);
		}
		saveOrUpdate(product);
	}

	@Override
	public List<ProductResponseData> getProductListByUserId(@Valid int userId) {
		QueryWrapper<Product> queryWrappper = new QueryWrapper<Product>().eq("user_id", userId);
		List<Product> list = list(queryWrappper);
		return list.stream().sorted(Comparator.comparing(Product::getCreationDateTime)).map(this::toResponsData).collect(Collectors.toList());
	}

	private ProductResponseData toResponsData(Product p) {
		return new ProductResponseData()
				.setEntityId(p.getEntityId())
				.setName(p.getName())
				.setPhotoPath(p.getPhotoPath())
				.setCurrency(p.getCurrency());
	}

	@Override
	public ProductDetailResponseData getProductDetailById(@Valid int productId) throws MvpTicketException {
		Product product = getById(productId);
		if(product == null) throw new MvpTicketException(MvpTicketExceptionCode.ERROR_002_01);
		Performence performance = performenceMapper.selectById(product.getPerformenceId());
		if(performance == null) throw new MvpTicketException(MvpTicketExceptionCode.ERROR_002_02);
		return new ProductDetailResponseData()
				.setCastMember(performance.getCastMember())
				.setConcertName(performance.getName())
				.setDate(performance.getDate())
				.setEndTime(performance.getEndTime())
				.setEntityId(productId)
				.setLocation(performance.getLocation())
				.setPrice(performance.getPrice())
				.setStartTime(performance.getStartTime())
				.setCurrency(product.getCurrency());
	}

}
