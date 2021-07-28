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
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
/**
 * 关系型数据实体[DocLibModule] 查询条件对象
 */
@Slf4j
@Data
public class DocLibModuleSearchContext extends QueryWrapperContext<DocLibModule> {

                
	private String n_isfavourites_eq;//[是否已收藏]
	public void setN_isfavourites_eq(String n_isfavourites_eq) {
        this.n_isfavourites_eq = n_isfavourites_eq;
    }
                
	private String n_name_like;//[名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
                
	private String n_docqtype_eq;//[查询类型]
	public void setN_docqtype_eq(String n_docqtype_eq) {
        this.n_docqtype_eq = n_docqtype_eq;
    }
                
	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_modulename_eq;//[上级模块]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
    }
                
	private String n_modulename_like;//[上级模块]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
    }
                
	private String n_doclibname_eq;//[所属文档库]
	public void setN_doclibname_eq(String n_doclibname_eq) {
        this.n_doclibname_eq = n_doclibname_eq;
    }
                
	private String n_doclibname_like;//[所属文档库]
	public void setN_doclibname_like(String n_doclibname_like) {
        this.n_doclibname_like = n_doclibname_like;
    }
                
	private Long n_parent_eq;//[id]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
    }
                
	private Long n_parent_ltandeq;//[id]
	public void setN_parent_ltandeq(Long n_parent_ltandeq) {
        this.n_parent_ltandeq = n_parent_ltandeq;
    }
                
	private Long n_root_eq;//[编号]
	public void setN_root_eq(Long n_root_eq) {
        this.n_root_eq = n_root_eq;
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



