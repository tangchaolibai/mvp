package com.hsb.mvpmsuser.config;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "creationDateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "lastUpdateDateTime", Date.class, new Date());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "lastUpdateDateTime", Date.class, new Date());
	}
	
	@Override
	public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<Object> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
	}

}
