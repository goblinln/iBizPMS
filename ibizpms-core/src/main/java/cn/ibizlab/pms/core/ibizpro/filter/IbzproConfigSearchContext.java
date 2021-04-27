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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
/**
 * 关系型数据实体[IbzproConfig] 查询条件对象
 */
@Slf4j
@Data
public class IbzproConfigSearchContext extends QueryWrapperContext<IbzproConfig> {

	private String n_ibzpro_configname_like;//[系统配置表名称]
	public void setN_ibzpro_configname_like(String n_ibzpro_configname_like) {
        this.n_ibzpro_configname_like = n_ibzpro_configname_like;
        if(!ObjectUtils.isEmpty(this.n_ibzpro_configname_like)){
            this.getSearchCond().like("`ibzpro_configname`", n_ibzpro_configname_like);
        }
    }
	private String n_ibzpro_configid_eq;//[系统配置表标识]
	public void setN_ibzpro_configid_eq(String n_ibzpro_configid_eq) {
        this.n_ibzpro_configid_eq = n_ibzpro_configid_eq;
        if(!ObjectUtils.isEmpty(this.n_ibzpro_configid_eq)){
            this.getSearchCond().eq("`ibzpro_configid`", n_ibzpro_configid_eq);
        }
    }
	private String n_type_eq;//[类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("`type`", n_type_eq);
        }
    }
	private String n_managementstatus_eq;//[管理现状]
	public void setN_managementstatus_eq(String n_managementstatus_eq) {
        this.n_managementstatus_eq = n_managementstatus_eq;
        if(!ObjectUtils.isEmpty(this.n_managementstatus_eq)){
            this.getSearchCond().eq("`managementstatus`", n_managementstatus_eq);
        }
    }
	private String n_vaild_eq;//[是否启用]
	public void setN_vaild_eq(String n_vaild_eq) {
        this.n_vaild_eq = n_vaild_eq;
        if(!ObjectUtils.isEmpty(this.n_vaild_eq)){
            this.getSearchCond().eq("`vaild`", n_vaild_eq);
        }
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
                     wrapper.like("`ibzpro_configname`", query)
            );
		 }
	}
}



