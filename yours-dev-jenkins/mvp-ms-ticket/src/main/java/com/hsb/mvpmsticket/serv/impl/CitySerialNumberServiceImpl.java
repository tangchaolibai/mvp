package com.hsb.mvpmsticket.serv.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsticket.exception.MvpMsTicketException;
import com.hsb.mvpmsticket.exception.MvpMsTicketExceptionCode;
import com.hsb.mvpmsticket.mapper.CitySerialNumberMapper;
import com.hsb.mvpmsticket.model.entity.CitySerialNumber;
import com.hsb.mvpmsticket.model.payload.CityListResponseListData;
import com.hsb.mvpmsticket.model.payload.CityResponseData;
import com.hsb.mvpmsticket.serv.CitySerialNumberService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-08-07
 */
@Service
public class CitySerialNumberServiceImpl extends ServiceImpl<CitySerialNumberMapper, CitySerialNumber> implements CitySerialNumberService {

	@Override
	public CityListResponseListData findAll() {
		List<CitySerialNumber> list =  list();
		List<CityResponseData> datas = list.stream().map(e -> toCityResponseData(e)).collect(Collectors.toList());
		CityListResponseListData data = new CityListResponseListData();
		data.setData(datas);
		return data;
	}
	
	private CityResponseData toCityResponseData (CitySerialNumber citySerialNumber) {
		CityResponseData data = new CityResponseData();
		data.setId(citySerialNumber.id());
		data.setCityName(citySerialNumber.cityName());
		return data;
	}

	@Override
	public void addCity(String city) throws MvpMsTicketException {
		QueryWrapper<CitySerialNumber> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("city_name", city);
		int num = count(queryWrapper);
		if(num == 1) {
			throw new MvpMsTicketException(MvpMsTicketExceptionCode.ERROR_001_01);
		}
		save(new CitySerialNumber().cityName(city));
	}

}
