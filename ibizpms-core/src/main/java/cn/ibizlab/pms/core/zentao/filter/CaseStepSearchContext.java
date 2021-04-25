package cn.ibizlab.pms.core.zentao.filter;

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
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
/**
 * 关系型数据实体[CaseStep] 查询条件对象
 */
@Slf4j
@Data
public class CaseStepSearchContext extends QueryWrapperContext<CaseStep> {

	private String n_steps_eq;//[测试结果]
	public void setN_steps_eq(String n_steps_eq) {
        this.n_steps_eq = n_steps_eq;
    }
	private String n_type_eq;//[用例步骤类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private String n_expect_like;//[预期]
	public void setN_expect_like(String n_expect_like) {
        this.n_expect_like = n_expect_like;
    }
	private Integer n_version_eq;//[用例版本]
	public void setN_version_eq(Integer n_version_eq) {
        this.n_version_eq = n_version_eq;
    }
	private Long n_case_eq;//[用例]
	public void setN_case_eq(Long n_case_eq) {
        this.n_case_eq = n_case_eq;
    }
	private Long n_parent_eq;//[分组用例步骤的组编号]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
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
                     wrapper.like("t1.`EXPECT`", query)
            );
		 }
	}
}



