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
import cn.ibizlab.pms.core.zentao.domain.TestRun;
/**
 * 关系型数据实体[TestRun] 查询条件对象
 */
@Slf4j
@Data
public class TestRunSearchContext extends QueryWrapperContext<TestRun> {

	private Long n_case_eq;//[测试用例]
	public void setN_case_eq(Long n_case_eq) {
        this.n_case_eq = n_case_eq;
    }
	private Long n_task_eq;//[测试单]
	public void setN_task_eq(Long n_task_eq) {
        this.n_task_eq = n_task_eq;
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
                     wrapper.like("t1.`ID`", query)
            );
		 }
	}
}



