package cn.ibizlab.pms.core.ibiz.filter;

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
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
/**
 * 关系型数据实体[SysUpdateFeatures] 查询条件对象
 */
@Slf4j
@Data
public class SysUpdateFeaturesSearchContext extends QueryWrapperContext<SysUpdateFeatures> {

	private String n_sys_update_featuresname_like;//[系统更新功能名称]
	public void setN_sys_update_featuresname_like(String n_sys_update_featuresname_like) {
        this.n_sys_update_featuresname_like = n_sys_update_featuresname_like;
    }
	private String n_type_eq;//[更新类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
	private String n_sys_update_logname_eq;//[所属更新]
	public void setN_sys_update_logname_eq(String n_sys_update_logname_eq) {
        this.n_sys_update_logname_eq = n_sys_update_logname_eq;
    }
	private String n_sys_update_logname_like;//[所属更新]
	public void setN_sys_update_logname_like(String n_sys_update_logname_like) {
        this.n_sys_update_logname_like = n_sys_update_logname_like;
    }
	private String n_sys_update_logid_eq;//[系统更新日志标识]
	public void setN_sys_update_logid_eq(String n_sys_update_logid_eq) {
        this.n_sys_update_logid_eq = n_sys_update_logid_eq;
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
                     wrapper.like("sys_update_featuresname", query)
            );
		 }
	}
}



