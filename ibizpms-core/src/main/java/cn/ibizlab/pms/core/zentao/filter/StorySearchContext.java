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
import cn.ibizlab.pms.core.zentao.domain.Story;
/**
 * 关系型数据实体[Story] 查询条件对象
 */
@Slf4j
@Data
public class StorySearchContext extends QueryWrapperContext<Story> {

                
	private String n_assignedto_eq;//[指派给]
	public void setN_assignedto_eq(String n_assignedto_eq) {
        this.n_assignedto_eq = n_assignedto_eq;
    }
                
	private String n_plan_eq;//[所属计划]
	public void setN_plan_eq(String n_plan_eq) {
        this.n_plan_eq = n_plan_eq;
    }
                
	private String n_plan_noteq;//[所属计划]
	public void setN_plan_noteq(String n_plan_noteq) {
        this.n_plan_noteq = n_plan_noteq;
    }
                
	private String n_storypoints_eq;//[故事点]
	public void setN_storypoints_eq(String n_storypoints_eq) {
        this.n_storypoints_eq = n_storypoints_eq;
    }
                
	private Integer n_pri_eq;//[优先级]
	public void setN_pri_eq(Integer n_pri_eq) {
        this.n_pri_eq = n_pri_eq;
    }
                
	private Integer n_pri_gtandeq;//[优先级]
	public void setN_pri_gtandeq(Integer n_pri_gtandeq) {
        this.n_pri_gtandeq = n_pri_gtandeq;
    }
                
	private Integer n_pri_ltandeq;//[优先级]
	public void setN_pri_ltandeq(Integer n_pri_ltandeq) {
        this.n_pri_ltandeq = n_pri_ltandeq;
    }
                
	private String n_assessresult_eq;//[评审结果]
	public void setN_assessresult_eq(String n_assessresult_eq) {
        this.n_assessresult_eq = n_assessresult_eq;
    }
                
	private String n_status_eq;//[当前状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
                
	private String n_status_noteq;//[当前状态]
	public void setN_status_noteq(String n_status_noteq) {
        this.n_status_noteq = n_status_noteq;
    }
                
	private String n_title_like;//[需求名称]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
                
	private String n_reviewedby_eq;//[由谁评审]
	public void setN_reviewedby_eq(String n_reviewedby_eq) {
        this.n_reviewedby_eq = n_reviewedby_eq;
    }
                
	private String n_stagedby_eq;//[设置阶段者]
	public void setN_stagedby_eq(String n_stagedby_eq) {
        this.n_stagedby_eq = n_stagedby_eq;
    }
                
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
    }
                
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private Long n_id_noteq;//[编号]
	public void setN_id_noteq(Long n_id_noteq) {
        this.n_id_noteq = n_id_noteq;
    }
                
	private String n_source_eq;//[需求来源]
	public void setN_source_eq(String n_source_eq) {
        this.n_source_eq = n_source_eq;
    }
                
	private String n_ischild_eq;//[是否可以细分]
	public void setN_ischild_eq(String n_ischild_eq) {
        this.n_ischild_eq = n_ischild_eq;
    }
                
	private String n_closedreason_eq;//[关闭原因]
	public void setN_closedreason_eq(String n_closedreason_eq) {
        this.n_closedreason_eq = n_closedreason_eq;
    }
                
	private String n_color_eq;//[标题颜色]
	public void setN_color_eq(String n_color_eq) {
        this.n_color_eq = n_color_eq;
    }
                
	private String n_isfavorites_eq;//[是否收藏]
	public void setN_isfavorites_eq(String n_isfavorites_eq) {
        this.n_isfavorites_eq = n_isfavorites_eq;
    }
                
	private String n_keywords_like;//[关键词]
	public void setN_keywords_like(String n_keywords_like) {
        this.n_keywords_like = n_keywords_like;
    }
                
	private String n_lasteditedby_eq;//[最后修改]
	public void setN_lasteditedby_eq(String n_lasteditedby_eq) {
        this.n_lasteditedby_eq = n_lasteditedby_eq;
    }
                
	private String n_stage_eq;//[所处阶段]
	public void setN_stage_eq(String n_stage_eq) {
        this.n_stage_eq = n_stage_eq;
    }
                
	private String n_stage_noteq;//[所处阶段]
	public void setN_stage_noteq(String n_stage_noteq) {
        this.n_stage_noteq = n_stage_noteq;
    }
                
	private String n_closedby_eq;//[由谁关闭]
	public void setN_closedby_eq(String n_closedby_eq) {
        this.n_closedby_eq = n_closedby_eq;
    }
                
	private String n_result_eq;//[评审结果]
	public void setN_result_eq(String n_result_eq) {
        this.n_result_eq = n_result_eq;
    }
                
	private String n_type_eq;//[需求类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private Integer n_preversion_eq;//[之前的版本]
	public void setN_preversion_eq(Integer n_preversion_eq) {
        this.n_preversion_eq = n_preversion_eq;
    }
                
	private String n_path_like;//[模块路径]
	public void setN_path_like(String n_path_like) {
        this.n_path_like = n_path_like;
    }
                
	private String n_parentname_eq;//[父需求名称]
	public void setN_parentname_eq(String n_parentname_eq) {
        this.n_parentname_eq = n_parentname_eq;
    }
                
	private String n_parentname_like;//[父需求名称]
	public void setN_parentname_like(String n_parentname_like) {
        this.n_parentname_like = n_parentname_like;
    }
                
	private String n_modulename_eq;//[所属模块名称]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
    }
                
	private String n_modulename_like;//[所属模块名称]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
    }
                
	private String n_prodoctname_eq;//[产品名称]
	public void setN_prodoctname_eq(String n_prodoctname_eq) {
        this.n_prodoctname_eq = n_prodoctname_eq;
    }
                
	private String n_prodoctname_like;//[产品名称]
	public void setN_prodoctname_like(String n_prodoctname_like) {
        this.n_prodoctname_like = n_prodoctname_like;
    }
                
	private String n_branchname_eq;//[平台/分支]
	public void setN_branchname_eq(String n_branchname_eq) {
        this.n_branchname_eq = n_branchname_eq;
    }
                
	private String n_branchname_like;//[平台/分支]
	public void setN_branchname_like(String n_branchname_like) {
        this.n_branchname_like = n_branchname_like;
    }
                
	private Long n_frombug_eq;//[来源Bug]
	public void setN_frombug_eq(Long n_frombug_eq) {
        this.n_frombug_eq = n_frombug_eq;
    }
                
	private Long n_parent_eq;//[父需求]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
    }
                
	private Long n_parent_gtandeq;//[父需求]
	public void setN_parent_gtandeq(Long n_parent_gtandeq) {
        this.n_parent_gtandeq = n_parent_gtandeq;
    }
                
	private Long n_module_eq;//[所属模块]
	public void setN_module_eq(Long n_module_eq) {
        this.n_module_eq = n_module_eq;
    }
                
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
                
	private Long n_duplicatestory_eq;//[重复需求ID]
	public void setN_duplicatestory_eq(Long n_duplicatestory_eq) {
        this.n_duplicatestory_eq = n_duplicatestory_eq;
    }
                
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
    }
                
	private Long n_tobug_eq;//[转Bug]
	public void setN_tobug_eq(Long n_tobug_eq) {
        this.n_tobug_eq = n_tobug_eq;
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
                     wrapper.like("t1.`TITLE`", query)
                        .or().like("storysn", query)
            );
		 }
	}
}



