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
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
/**
 * 关系型数据实体[ProjectTeam] 查询条件对象
 */
@Slf4j
@Data
public class ProjectTeamSearchContext extends QueryWrapperContext<ProjectTeam> {

	private String n_limited_eq;//[受限用户]
	public void setN_limited_eq(String n_limited_eq) {
        this.n_limited_eq = n_limited_eq;
        if(!ObjectUtils.isEmpty(this.n_limited_eq)){
            this.getSearchCond().eq("`limited`", n_limited_eq);
        }
    }
	private String n_type_eq;//[团队类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("`type`", n_type_eq);
        }
    }
	private String n_account_eq;//[用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
        if(!ObjectUtils.isEmpty(this.n_account_eq)){
            this.getSearchCond().eq("`account`", n_account_eq);
        }
    }
	private String n_account_like;//[用户]
	public void setN_account_like(String n_account_like) {
        this.n_account_like = n_account_like;
        if(!ObjectUtils.isEmpty(this.n_account_like)){
            this.getSearchCond().like("`account`", n_account_like);
        }
    }
	private String n_projectname_eq;//[所属项目]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
        if(!ObjectUtils.isEmpty(this.n_projectname_eq)){
            this.getSearchCond().eq("`projectname`", n_projectname_eq);
        }
    }
	private String n_projectname_like;//[所属项目]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
        if(!ObjectUtils.isEmpty(this.n_projectname_like)){
            this.getSearchCond().like("`projectname`", n_projectname_like);
        }
    }
	private Long n_root_eq;//[项目编号]
	public void setN_root_eq(Long n_root_eq) {
        this.n_root_eq = n_root_eq;
        if(!ObjectUtils.isEmpty(this.n_root_eq)){
            this.getSearchCond().eq("`root`", n_root_eq);
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
                     wrapper.like("`account`", query)
            );
		 }
	}
}



