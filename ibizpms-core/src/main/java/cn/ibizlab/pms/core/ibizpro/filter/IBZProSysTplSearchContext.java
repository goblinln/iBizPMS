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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProSysTpl;
/**
 * 关系型数据实体[IBZProSysTpl] 查询条件对象
 */
@Slf4j
@Data
public class IBZProSysTplSearchContext extends QueryWrapperContext<IBZProSysTpl> {

                
	private String n_ibzpro_systplid_eq;//[系统模板标识]
	public void setN_ibzpro_systplid_eq(String n_ibzpro_systplid_eq) {
        this.n_ibzpro_systplid_eq = n_ibzpro_systplid_eq;
    }
                
	private String n_public_eq;//[是否公开]
	public void setN_public_eq(String n_public_eq) {
        this.n_public_eq = n_public_eq;
    }
                
	private String n_ibzpro_systplname_like;//[系统模板名称]
	public void setN_ibzpro_systplname_like(String n_ibzpro_systplname_like) {
        this.n_ibzpro_systplname_like = n_ibzpro_systplname_like;
    }
                
	private String n_tpltype_eq;//[IBIZ模板类型]
	public void setN_tpltype_eq(String n_tpltype_eq) {
        this.n_tpltype_eq = n_tpltype_eq;
    }
                
	private String n_ibiz_sourceobject_eq;//[来源对象]
	public void setN_ibiz_sourceobject_eq(String n_ibiz_sourceobject_eq) {
        this.n_ibiz_sourceobject_eq = n_ibiz_sourceobject_eq;
    }
                
	private Long n_file_eq;//[id]
	public void setN_file_eq(Long n_file_eq) {
        this.n_file_eq = n_file_eq;
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
                     wrapper.like("ibzpro_systplname", query)
            );
		 }
	}
}



