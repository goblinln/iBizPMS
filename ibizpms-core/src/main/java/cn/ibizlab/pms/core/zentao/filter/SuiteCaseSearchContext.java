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
import cn.ibizlab.pms.core.zentao.domain.SuiteCase;
/**
 * 关系型数据实体[SuiteCase] 查询条件对象
 */
@Slf4j
@Data
public class SuiteCaseSearchContext extends QueryWrapperContext<SuiteCase> {

	private Long n_suite_eq;//[测试套件]
	public void setN_suite_eq(Long n_suite_eq) {
        this.n_suite_eq = n_suite_eq;
    }
	private Long n_case_eq;//[用例]
	public void setN_case_eq(Long n_case_eq) {
        this.n_case_eq = n_case_eq;
    }
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
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
                     wrapper.like("case", query)
            );
		 }
	}
}



