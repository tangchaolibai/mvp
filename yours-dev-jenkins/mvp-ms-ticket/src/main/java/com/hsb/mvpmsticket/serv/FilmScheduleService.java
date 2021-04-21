package com.hsb.mvpmsticket.serv;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsticket.exception.MvpMsTicketException;
import com.hsb.mvpmsticket.model.entity.FilmSchedule;
import com.hsb.mvpmsticket.model.payload.BuyTicketsResponseData;

public interface FilmScheduleService extends IService<FilmSchedule> {

	BuyTicketsResponseData buy(Integer scheduleId, Integer count) throws MvpMsTicketException;

	void loadInitData();

}
