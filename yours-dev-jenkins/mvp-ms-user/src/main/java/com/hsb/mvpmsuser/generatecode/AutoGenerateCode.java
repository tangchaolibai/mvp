package com.hsb.mvpmsuser.generatecode;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoGenerateCode {
	
	public static void main( String[] args ) {
		AutoGenerator mpg = new AutoGenerator();
    	GlobalConfig gc = new GlobalConfig();
    	gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
    	gc.setOpen(false);
    	gc.setFileOverride(false);
    	gc.setIdType(IdType.AUTO);
    	gc.setDateType(DateType.ONLY_DATE);
    	mpg.setGlobalConfig(gc);
    	// 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://12.1.1.30:3306/mvptest?autoReconnect=yes&useUnicode=yes&characterEncoding=UTF-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hsb.mvpmsuser.user");
//        pc.setModuleName("user");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
//        pc.setXml("/src/main/java/mapper");
        pc.setController("api");
        mpg.setPackageInfo(pc);
        
        //更改生成xml文件的路径
        InjectionConfig cfg = new InjectionConfig() {
          @Override
          public void initMap() {
              // to do nothing
          }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return System.getProperty("user.dir") + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        
        // Step5：策略配置（数据库表配置）
        StrategyConfig strategy = new StrategyConfig();
        // 指定表名（可以同时操作多个表，使用 , 隔开）（需要修改）
        strategy.setInclude("USER_INFO");
        // 配置数据表与实体类名之间映射的策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 配置数据表的字段与实体类的属性名之间映射的策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 配置 lombok 模式
        strategy.setEntityLombokModel(true);
        // 配置 rest 风格的控制器（@RestController）
        strategy.setRestControllerStyle(true);
        // 配置驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 配置表前缀，生成实体时去除表前缀
        // 此处的表名为 test_mybatis_plus_user，模块名为 test_mybatis_plus，去除前缀后剩下为 user。
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        
        TemplateConfig templateConfig = new TemplateConfig();
        //控制 不生成 controller
//        templateConfig.setController("");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
     // Step6：执行代码生成操作
        mpg.execute();
	}
	
}
