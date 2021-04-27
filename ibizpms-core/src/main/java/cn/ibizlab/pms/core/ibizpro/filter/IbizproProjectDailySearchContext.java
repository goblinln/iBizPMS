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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
/**
 * 关系型数据实体[IbizproProjectDaily] 查询条件对象
 */
@Slf4j
@Data
public class IbizproProjectDailySearchContext extends QueryWrapperContext<IbizproProjectDaily> {

	private String n_ibizpro_projectdailyname_like;//[项目日报名称]
	public void setN_ibizpro_projectdailyname_like(String n_ibizpro_projectdailyname_like) {
        this.n_ibizpro_projectdailyname_like = n_ibizpro_projectdailyname_like;
        if(!ObjectUtils.isEmpty(this.n_ibizpro_projectdailyname_like)){
            this.getSearchCond().like("`ibizpro_projectdailyname`", n_ibizpro_projectdailyname_like);
        }
    }
	private String n_pm_eq;//[项目负责人]
	public void setN_pm_eq(String n_pm_eq) {
        this.n_pm_eq = n_pm_eq;
        if(!ObjectUtils.isEmpty(this.n_pm_eq)){
            this.getSearchCond().eq("`pm`", n_pm_eq);
        }
    }
	private String n_ibizpro_projectdailyid_eq;//[项目日报标识]
	public void setN_ibizpro_projectdailyid_eq(String n_ibizpro_projectdailyid_eq) {
        this.n_ibizpro_projectdailyid_eq = n_ibizpro_projectdailyid_eq;
        if(!ObjectUtils.isEmpty(this.n_ibizpro_projectdailyid_eq)){
            this.getSearchCond().eq("`ibizpro_projectdailyid`", n_ibizpro_projectdailyid_eq);
        }
    }
	private String n_projectname_eq;//[项目名称]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
        if(!ObjectUtils.isEmpty(this.n_projectname_eq)){
            this.getSearchCond().eq("`projectname`", n_projectname_eq);
        }
    }
	private String n_projectname_like;//[项目名称]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
        if(!ObjectUtils.isEmpty(this.n_projectname_like)){
            this.getSearchCond().like("`projectname`", n_projectname_like);
        }
    }
	private Long n_project_eq;//[项目编号]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
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
                     wrapper.like("`ibizpro_projectdailyname`", query)
            );
		 }
	}
}



