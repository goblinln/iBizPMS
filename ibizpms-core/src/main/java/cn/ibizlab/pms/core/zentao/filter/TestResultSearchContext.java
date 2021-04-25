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
import cn.ibizlab.pms.core.zentao.domain.TestResult;
/**
 * 关系型数据实体[TestResult] 查询条件对象
 */
@Slf4j
@Data
public class TestResultSearchContext extends QueryWrapperContext<TestResult> {

	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
	private Long n_product_like;//[所属产品]
	public void setN_product_like(Long n_product_like) {
        this.n_product_like = n_product_like;
    }
	private Long n_job_eq;//[构建任务]
	public void setN_job_eq(Long n_job_eq) {
        this.n_job_eq = n_job_eq;
    }
	private Long n_case_eq;//[用例]
	public void setN_case_eq(Long n_case_eq) {
        this.n_case_eq = n_case_eq;
    }
	private Long n_run_eq;//[测试执行]
	public void setN_run_eq(Long n_run_eq) {
        this.n_run_eq = n_run_eq;
    }
	private Long n_compile_eq;//[代码编译]
	public void setN_compile_eq(Long n_compile_eq) {
        this.n_compile_eq = n_compile_eq;
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
                     wrapper.like("title", query)
            );
		 }
	}
}



