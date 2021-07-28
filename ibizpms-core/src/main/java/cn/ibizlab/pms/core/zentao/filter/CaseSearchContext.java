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
import cn.ibizlab.pms.core.zentao.domain.Case;
/**
 * 关系型数据实体[Case] 查询条件对象
 */
@Slf4j
@Data
public class CaseSearchContext extends QueryWrapperContext<Case> {

                
	private String n_color_eq;//[标题颜色]
	public void setN_color_eq(String n_color_eq) {
        this.n_color_eq = n_color_eq;
    }
                
	private String n_lastrunresult_eq;//[结果]
	public void setN_lastrunresult_eq(String n_lastrunresult_eq) {
        this.n_lastrunresult_eq = n_lastrunresult_eq;
    }
                
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
    }
                
	private String n_type_eq;//[用例类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private String n_status_eq;//[用例状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
                
	private String n_isfavorites_eq;//[是否收藏]
	public void setN_isfavorites_eq(String n_isfavorites_eq) {
        this.n_isfavorites_eq = n_isfavorites_eq;
    }
                
	private String n_frequency_eq;//[frequency]
	public void setN_frequency_eq(String n_frequency_eq) {
        this.n_frequency_eq = n_frequency_eq;
    }
                
	private String n_title_like;//[用例标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
                
	private String n_assignedto_eq;//[指派给]
	public void setN_assignedto_eq(String n_assignedto_eq) {
        this.n_assignedto_eq = n_assignedto_eq;
    }
                
	private Integer n_pri_eq;//[优先级]
	public void setN_pri_eq(Integer n_pri_eq) {
        this.n_pri_eq = n_pri_eq;
    }
                
	private String n_status1_eq;//[用例状态]
	public void setN_status1_eq(String n_status1_eq) {
        this.n_status1_eq = n_status1_eq;
    }
                
	private String n_frame_eq;//[工具/框架]
	public void setN_frame_eq(String n_frame_eq) {
        this.n_frame_eq = n_frame_eq;
    }
                
	private String n_lastrunresult1_eq;//[测试用例结果]
	public void setN_lastrunresult1_eq(String n_lastrunresult1_eq) {
        this.n_lastrunresult1_eq = n_lastrunresult1_eq;
    }
                
	private String n_id_isnull;//[用例编号]
	public void setN_id_isnull(String n_id_isnull) {
        this.n_id_isnull = n_id_isnull;
    }
                
	private Long n_id_eq;//[用例编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_lastrunner_eq;//[执行人]
	public void setN_lastrunner_eq(String n_lastrunner_eq) {
        this.n_lastrunner_eq = n_lastrunner_eq;
    }
                
	private String n_libname_eq;//[用例库]
	public void setN_libname_eq(String n_libname_eq) {
        this.n_libname_eq = n_libname_eq;
    }
                
	private String n_libname_like;//[用例库]
	public void setN_libname_like(String n_libname_like) {
        this.n_libname_like = n_libname_like;
    }
                
	private String n_storyname_eq;//[需求名称]
	public void setN_storyname_eq(String n_storyname_eq) {
        this.n_storyname_eq = n_storyname_eq;
    }
                
	private String n_storyname_like;//[需求名称]
	public void setN_storyname_like(String n_storyname_like) {
        this.n_storyname_like = n_storyname_like;
    }
                
	private String n_modulename_eq;//[模块名称]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
    }
                
	private String n_modulename_like;//[模块名称]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
    }
                
	private String n_productname_eq;//[产品名称]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
    }
                
	private String n_productname_like;//[产品名称]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
    }
                
	private Long n_fromcaseid_eq;//[来源用例]
	public void setN_fromcaseid_eq(Long n_fromcaseid_eq) {
        this.n_fromcaseid_eq = n_fromcaseid_eq;
    }
                
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
    }
                
	private Long n_frombug_eq;//[来源Bug]
	public void setN_frombug_eq(Long n_frombug_eq) {
        this.n_frombug_eq = n_frombug_eq;
    }
                
	private Long n_story_eq;//[相关需求]
	public void setN_story_eq(Long n_story_eq) {
        this.n_story_eq = n_story_eq;
    }
                
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
                
	private Long n_product_gtandeq;//[所属产品]
	public void setN_product_gtandeq(Long n_product_gtandeq) {
        this.n_product_gtandeq = n_product_gtandeq;
    }
                
	private String n_product_isnotnull;//[所属产品]
	public void setN_product_isnotnull(String n_product_isnotnull) {
        this.n_product_isnotnull = n_product_isnotnull;
    }
                
	private Long n_product_noteq;//[所属产品]
	public void setN_product_noteq(Long n_product_noteq) {
        this.n_product_noteq = n_product_noteq;
    }
                
	private Long n_lib_eq;//[所属库]
	public void setN_lib_eq(Long n_lib_eq) {
        this.n_lib_eq = n_lib_eq;
    }
                
	private Long n_module_eq;//[所属模块]
	public void setN_module_eq(Long n_module_eq) {
        this.n_module_eq = n_module_eq;
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



