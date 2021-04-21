package com.hsb.mvpmsweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsb.mvpmsweb.model.domain.CountriesAndRegions;

public interface CountriesAndRegionsMapper extends BaseMapper<CountriesAndRegions> {

	@Select("SELECT entity_id,name_en_us,name_zh_cn,name_zh_hk,area_code,active,prohibit FROM COUNTRIES_REGIONS WHERE active = 1 ORDER BY name_en_us ASC")
	public List<CountriesAndRegions> getAreaCodeListByActive();
	
}
