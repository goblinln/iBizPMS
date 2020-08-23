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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
/**
 * 关系型数据实体[IbzLibCaseSteps] 查询条件对象
 */
@Slf4j
@Data
public class IbzLibCaseStepsSearchContext extends QueryWrapperContext<IbzLibCaseSteps> {

	private Integer n_version_eq;//[版本]
	public void setN_version_eq(Integer n_version_eq) {
        this.n_version_eq = n_version_eq;
        if(!ObjectUtils.isEmpty(this.n_version_eq)){
            this.getSearchCond().eq("version", n_version_eq);
        }
    }
	private BigInteger n_case_eq;//[用例编号]
	public void setN_case_eq(BigInteger n_case_eq) {
        this.n_case_eq = n_case_eq;
        if(!ObjectUtils.isEmpty(this.n_case_eq)){
            this.getSearchCond().eq("`case`", n_case_eq);
        }
    }
	private String n_expect_like;//[预期]
	public void setN_expect_like(String n_expect_like) {
        this.n_expect_like = n_expect_like;
        if(!ObjectUtils.isEmpty(this.n_expect_like)){
            this.getSearchCond().like("expect", n_expect_like);
        }
    }
	private BigInteger n_parent_eq;//[编号]
	public void setN_parent_eq(BigInteger n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
        if(!ObjectUtils.isEmpty(this.n_parent_eq)){
            this.getSearchCond().eq("parent", n_parent_eq);
        }
    }
	private String n_type_eq;//[类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("type", n_type_eq);
        }
    }

    /**
	 * 启用快速搜索
	 */
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
            this.getSearchCond().and( wrapper ->
                     wrapper.like("expect", query)   
            );
		 }
	}
}



