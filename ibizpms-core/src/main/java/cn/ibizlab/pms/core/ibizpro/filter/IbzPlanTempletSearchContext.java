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
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
/**
 * 关系型数据实体[IbzPlanTemplet] 查询条件对象
 */
@Slf4j
@Data
public class IbzPlanTempletSearchContext extends QueryWrapperContext<IbzPlanTemplet> {

                
	private String n_acl_eq;//[权限]
	public void setN_acl_eq(String n_acl_eq) {
        this.n_acl_eq = n_acl_eq;
    }
                
	private String n_createmanname_eq;//[创建人姓名]
	public void setN_createmanname_eq(String n_createmanname_eq) {
        this.n_createmanname_eq = n_createmanname_eq;
    }
                
	private String n_ibz_plantempletid_eq;//[产品计划模板标识]
	public void setN_ibz_plantempletid_eq(String n_ibz_plantempletid_eq) {
        this.n_ibz_plantempletid_eq = n_ibz_plantempletid_eq;
    }
                
	private String n_ibz_plantempletname_like;//[模板名称]
	public void setN_ibz_plantempletname_like(String n_ibz_plantempletname_like) {
        this.n_ibz_plantempletname_like = n_ibz_plantempletname_like;
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
                     wrapper.like("ibz_plantempletname", query)
            );
		 }
	}
}



