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
import cn.ibizlab.pms.core.ibiz.domain.EmpLoyeeload;
/**
 * 关系型数据实体[EmpLoyeeload] 查询条件对象
 */
@Slf4j
@Data
public class EmpLoyeeloadSearchContext extends QueryWrapperContext<EmpLoyeeload> {

	private String n_name_like;//[任务名]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
	private String n_dept_eq;//[部门]
	public void setN_dept_eq(String n_dept_eq) {
        this.n_dept_eq = n_dept_eq;
    }
	private Long n_id_eq;//[主键]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private Integer n_workday_eq;//[工作日天数]
	public void setN_workday_eq(Integer n_workday_eq) {
        this.n_workday_eq = n_workday_eq;
    }
	private String n_assign_eq;//[是否指派]
	public void setN_assign_eq(String n_assign_eq) {
        this.n_assign_eq = n_assign_eq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_begin_ltandeq;//[属性]
	public void setN_begin_ltandeq(Timestamp n_begin_ltandeq) {
        this.n_begin_ltandeq = n_begin_ltandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_end_gtandeq;//[结束]
	public void setN_end_gtandeq(Timestamp n_end_gtandeq) {
        this.n_end_gtandeq = n_end_gtandeq;
    }
	private String n_projectname_eq;//[项目]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
    }
	private String n_projectname_like;//[项目]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
    }
	private Long n_project_eq;//[项目编号]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
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
                     wrapper.like("t1.`NAME`", query)
            );
		 }
	}
}



