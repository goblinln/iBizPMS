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
import cn.ibizlab.pms.core.zentao.domain.TestTask;
/**
 * 关系型数据实体[TestTask] 查询条件对象
 */
@Slf4j
@Data
public class TestTaskSearchContext extends QueryWrapperContext<TestTask> {

	private Integer n_pri_eq;//[优先级]
	public void setN_pri_eq(Integer n_pri_eq) {
        this.n_pri_eq = n_pri_eq;
    }
	private String n_status_eq;//[当前状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
	private String n_status_noteq;//[当前状态]
	public void setN_status_noteq(String n_status_noteq) {
        this.n_status_noteq = n_status_noteq;
    }
	private String n_owner_eq;//[负责人]
	public void setN_owner_eq(String n_owner_eq) {
        this.n_owner_eq = n_owner_eq;
    }
	private String n_name_like;//[名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
	private String n_buildname_eq;//[版本]
	public void setN_buildname_eq(String n_buildname_eq) {
        this.n_buildname_eq = n_buildname_eq;
    }
	private String n_buildname_like;//[版本]
	public void setN_buildname_like(String n_buildname_like) {
        this.n_buildname_like = n_buildname_like;
    }
	private String n_productname_eq;//[产品]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
    }
	private String n_productname_like;//[产品]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
    }
	private String n_projecttname_eq;//[项目]
	public void setN_projecttname_eq(String n_projecttname_eq) {
        this.n_projecttname_eq = n_projecttname_eq;
    }
	private String n_projecttname_like;//[项目]
	public void setN_projecttname_like(String n_projecttname_like) {
        this.n_projecttname_like = n_projecttname_like;
    }
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
	private Long n_build_eq;//[版本]
	public void setN_build_eq(Long n_build_eq) {
        this.n_build_eq = n_build_eq;
    }
	private Long n_project_eq;//[所属项目]
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



