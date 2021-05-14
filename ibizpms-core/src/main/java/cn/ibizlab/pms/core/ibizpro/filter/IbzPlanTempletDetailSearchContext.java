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
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
/**
 * 关系型数据实体[IbzPlanTempletDetail] 查询条件对象
 */
@Slf4j
@Data
public class IbzPlanTempletDetailSearchContext extends QueryWrapperContext<IbzPlanTempletDetail> {

                
	private String n_type_eq;//[类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private String n_ibz_plantempletdetailid_eq;//[计划模板详情标识]
	public void setN_ibz_plantempletdetailid_eq(String n_ibz_plantempletdetailid_eq) {
        this.n_ibz_plantempletdetailid_eq = n_ibz_plantempletdetailid_eq;
    }
                
	private String n_ibz_plantempletdetailname_like;//[计划模板详情名称]
	public void setN_ibz_plantempletdetailname_like(String n_ibz_plantempletdetailname_like) {
        this.n_ibz_plantempletdetailname_like = n_ibz_plantempletdetailname_like;
    }
                
	private String n_plantempletid_eq;//[产品计划模板标识]
	public void setN_plantempletid_eq(String n_plantempletid_eq) {
        this.n_plantempletid_eq = n_plantempletid_eq;
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
                     wrapper.like("t1.`IBZ_PLANTEMPLETDETAILNAME`", query)
            );
		 }
	}
}



