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
import cn.ibizlab.pms.core.zentao.domain.TestReport;
/**
 * 关系型数据实体[TestReport] 查询条件对象
 */
@Slf4j
@Data
public class TestReportSearchContext extends QueryWrapperContext<TestReport> {

	private String n_title_like;//[标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
	private String n_objecttype_eq;//[对象类型]
	public void setN_objecttype_eq(String n_objecttype_eq) {
        this.n_objecttype_eq = n_objecttype_eq;
    }
	private String n_projectname_eq;//[所属项目]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
    }
	private String n_projectname_like;//[所属项目]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
    }
	private String n_productname_eq;//[所属产品]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
    }
	private String n_productname_like;//[所属产品]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
    }
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
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
                     wrapper.like("title", query)
            );
		 }
	}
}



