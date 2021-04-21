package com.hsb.mvpmsweb.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsweb.api.exception.MvpTicketException;
import com.hsb.mvpmsweb.model.domain.Product;
import com.hsb.mvpmsweb.model.payload.ticket.ProductDetailResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.ProductResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.PutOnSaleResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.request.PutOnSaleRequest;

public interface ProductService extends IService<Product> {

	List<ProductResponseData> getProductListByUserId(@Valid int userId);

	ProductDetailResponseData getProductDetailById(@Valid int productId) throws MvpTicketException;

	PutOnSaleResponseData putOnSale(@Valid PutOnSaleRequest putOnSaleRequest) throws MvpTicketException;

	void uploadPic(@Valid int productId, MultipartFile multipartFile) throws MvpTicketException;

}
