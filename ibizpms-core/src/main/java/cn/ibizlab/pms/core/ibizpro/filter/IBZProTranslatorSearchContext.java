package cn.ibizlab.pms.core.ibizpro.filter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;


import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProTranslator;
/**
 * 关系型数据实体[IBZProTranslator] 查询条件对象
 */
@Slf4j
@Data
public class IBZProTranslatorSearchContext extends QueryWrapperContext<IBZProTranslator> {

                
	private String n_ibzpro_translatorname_like;//[业务值转换名称]
	public void setN_ibzpro_translatorname_like(String n_ibzpro_translatorname_like) {
        this.n_ibzpro_translatorname_like = n_ibzpro_translatorname_like;
    }
                
	private String n_ibzpro_translatorid_eq;//[业务值转换标识]
	public void setN_ibzpro_translatorid_eq(String n_ibzpro_translatorid_eq) {
        this.n_ibzpro_translatorid_eq = n_ibzpro_translatorid_eq;
    }

    /**
	 * 启用快速搜索
	 */
    @Override
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
            this.getSearchCond().and( wrapper ->
                     wrapper.like("ibzpro_translatorname", query)
            );
		 }
	}
}



