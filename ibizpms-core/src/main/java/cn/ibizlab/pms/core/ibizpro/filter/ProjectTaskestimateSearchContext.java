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
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTaskestimate;
/**
 * 关系型数据实体[ProjectTaskestimate] 查询条件对象
 */
@Slf4j
@Data
public class ProjectTaskestimateSearchContext extends QueryWrapperContext<ProjectTaskestimate> {

	private String n_project_in;//[项目标识]
	public void setN_project_in(String n_project_in) {
        this.n_project_in = n_project_in;
    }
	private String n_month_eq;//[月]
	public void setN_month_eq(String n_month_eq) {
        this.n_month_eq = n_month_eq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_date_eq;//[日期]
	public void setN_date_eq(Timestamp n_date_eq) {
        this.n_date_eq = n_date_eq;
    }
	private String n_account_eq;//[用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
    }
	private String n_year_eq;//[年]
	public void setN_year_eq(String n_year_eq) {
        this.n_year_eq = n_year_eq;
    }
	private String n_id_eq;//[主键]
	public void setN_id_eq(String n_id_eq) {
        this.n_id_eq = n_id_eq;
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
                     wrapper.like("date", query)
            );
		 }
	}
}



