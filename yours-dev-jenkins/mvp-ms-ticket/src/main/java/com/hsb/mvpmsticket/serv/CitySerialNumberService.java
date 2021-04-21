package com.hsb.mvpmsticket.serv;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsticket.exception.MvpMsTicketException;
import com.hsb.mvpmsticket.model.entity.CitySerialNumber;
import com.hsb.mvpmsticket.model.payload.CityListResponseListData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-08-07
 */
public interface CitySerialNumberService extends IService<CitySerialNumber> {

	CityListResponseListData findAll();

	void addCity(String city) throws MvpMsTicketException;

}
