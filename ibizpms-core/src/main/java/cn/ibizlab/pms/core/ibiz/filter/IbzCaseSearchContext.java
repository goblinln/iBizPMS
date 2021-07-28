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
import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
/**
 * 关系型数据实体[IbzCase] 查询条件对象
 */
@Slf4j
@Data
public class IbzCaseSearchContext extends QueryWrapperContext<IbzCase> {

                
	private String n_lasteditedby_eq;//[最后修改者]
	public void setN_lasteditedby_eq(String n_lasteditedby_eq) {
        this.n_lasteditedby_eq = n_lasteditedby_eq;
    }
                
	private Long n_id_eq;//[用例编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_type_eq;//[用例类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private String n_title_like;//[用例标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
                
	private String n_pri_eq;//[优先级]
	public void setN_pri_eq(String n_pri_eq) {
        this.n_pri_eq = n_pri_eq;
    }
                
	private String n_status_eq;//[状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
                
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
    }
                
	private String n_libname_eq;//[用例库]
	public void setN_libname_eq(String n_libname_eq) {
        this.n_libname_eq = n_libname_eq;
    }
                
	private String n_libname_like;//[用例库]
	public void setN_libname_like(String n_libname_like) {
        this.n_libname_like = n_libname_like;
    }
                
	private String n_modulename_eq;//[所属模块]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
    }
                
	private String n_modulename_like;//[所属模块]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
    }
                
	private Long n_module_eq;//[id]
	public void setN_module_eq(Long n_module_eq) {
        this.n_module_eq = n_module_eq;
    }
                
	private Long n_lib_eq;//[编号]
	public void setN_lib_eq(Long n_lib_eq) {
        this.n_lib_eq = n_lib_eq;
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



