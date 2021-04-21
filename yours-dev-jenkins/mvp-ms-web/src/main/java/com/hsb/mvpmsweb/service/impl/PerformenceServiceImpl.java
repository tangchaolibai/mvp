package com.hsb.mvpmsweb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsweb.mapper.PerformenceMapper;
import com.hsb.mvpmsweb.model.domain.Performence;
import com.hsb.mvpmsweb.service.PerformenceService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PerformenceServiceImpl extends ServiceImpl<PerformenceMapper, Performence> implements PerformenceService {
	
}
