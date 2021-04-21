package com.hsb.mvpmsweb.config;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "creationDateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "lastUpdateDateTime", LocalDateTime.class, LocalDateTime.now());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "lastUpdateDateTime", LocalDateTime.class, LocalDateTime.now());
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
