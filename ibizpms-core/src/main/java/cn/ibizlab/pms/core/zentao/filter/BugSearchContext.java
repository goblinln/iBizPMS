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
import cn.ibizlab.pms.core.zentao.domain.Bug;
/**
 * 关系型数据实体[Bug] 查询条件对象
 */
@Slf4j
@Data
public class BugSearchContext extends QueryWrapperContext<Bug> {

                
	private Integer n_severity_eq;//[严重程度]
	public void setN_severity_eq(Integer n_severity_eq) {
        this.n_severity_eq = n_severity_eq;
    }
                
	private Integer n_overduebugs_lt;//[过期天数]
	public void setN_overduebugs_lt(Integer n_overduebugs_lt) {
        this.n_overduebugs_lt = n_overduebugs_lt;
    }
                
	private String n_assignedto_eq;//[指派给]
	public void setN_assignedto_eq(String n_assignedto_eq) {
        this.n_assignedto_eq = n_assignedto_eq;
    }
                
	private String n_resolution_eq;//[解决方案]
	public void setN_resolution_eq(String n_resolution_eq) {
        this.n_resolution_eq = n_resolution_eq;
    }
                
	private String n_isfavorites_eq;//[是否收藏]
	public void setN_isfavorites_eq(String n_isfavorites_eq) {
        this.n_isfavorites_eq = n_isfavorites_eq;
    }
                
	private String n_closedby_eq;//[由谁关闭]
	public void setN_closedby_eq(String n_closedby_eq) {
        this.n_closedby_eq = n_closedby_eq;
    }
                
	private String n_browser_eq;//[浏览器]
	public void setN_browser_eq(String n_browser_eq) {
        this.n_browser_eq = n_browser_eq;
    }
                
	private Integer n_confirmed_eq;//[是否确认]
	public void setN_confirmed_eq(Integer n_confirmed_eq) {
        this.n_confirmed_eq = n_confirmed_eq;
    }
                
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_assigneddate_gtandeq;//[指派日期]
	public void setN_assigneddate_gtandeq(Timestamp n_assigneddate_gtandeq) {
        this.n_assigneddate_gtandeq = n_assigneddate_gtandeq;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_deadline_gtandeq;//[截止日期]
	public void setN_deadline_gtandeq(Timestamp n_deadline_gtandeq) {
        this.n_deadline_gtandeq = n_deadline_gtandeq;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_deadline_lt;//[截止日期]
	public void setN_deadline_lt(Timestamp n_deadline_lt) {
        this.n_deadline_lt = n_deadline_lt;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_deadline_ltandeq;//[截止日期]
	public void setN_deadline_ltandeq(Timestamp n_deadline_ltandeq) {
        this.n_deadline_ltandeq = n_deadline_ltandeq;
    }
                
	private String n_color_eq;//[标题颜色]
	public void setN_color_eq(String n_color_eq) {
        this.n_color_eq = n_color_eq;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_resolveddate_ltandeq;//[解决日期]
	public void setN_resolveddate_ltandeq(Timestamp n_resolveddate_ltandeq) {
        this.n_resolveddate_ltandeq = n_resolveddate_ltandeq;
    }
                
	private String n_type_eq;//[Bug类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private String n_status_eq;//[Bug状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
                
	private String n_status_noteq;//[Bug状态]
	public void setN_status_noteq(String n_status_noteq) {
        this.n_status_noteq = n_status_noteq;
    }
                
	private Long n_bugsn_eq;//[BUG编号]
	public void setN_bugsn_eq(Long n_bugsn_eq) {
        this.n_bugsn_eq = n_bugsn_eq;
    }
                
	private String n_buildproject_eq;//[版本项目]
	public void setN_buildproject_eq(String n_buildproject_eq) {
        this.n_buildproject_eq = n_buildproject_eq;
    }
                
	private Long n_id_eq;//[Bug编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_resolvedby_eq;//[解决者]
	public void setN_resolvedby_eq(String n_resolvedby_eq) {
        this.n_resolvedby_eq = n_resolvedby_eq;
    }
                
	private String n_resolvedbuild_eq;//[解决版本]
	public void setN_resolvedbuild_eq(String n_resolvedbuild_eq) {
        this.n_resolvedbuild_eq = n_resolvedbuild_eq;
    }
                
	private Integer n_pri_eq;//[优先级]
	public void setN_pri_eq(Integer n_pri_eq) {
        this.n_pri_eq = n_pri_eq;
    }
                
	private String n_os_eq;//[操作系统]
	public void setN_os_eq(String n_os_eq) {
        this.n_os_eq = n_os_eq;
    }
                
	private String n_lasteditedby_eq;//[最后修改者]
	public void setN_lasteditedby_eq(String n_lasteditedby_eq) {
        this.n_lasteditedby_eq = n_lasteditedby_eq;
    }
                
	private String n_title_like;//[Bug标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
                
	private String n_productname_eq;//[产品]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
    }
                
	private String n_productname_like;//[产品]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
    }
                
	private String n_branchname_eq;//[平台/分支]
	public void setN_branchname_eq(String n_branchname_eq) {
        this.n_branchname_eq = n_branchname_eq;
    }
                
	private String n_branchname_like;//[平台/分支]
	public void setN_branchname_like(String n_branchname_like) {
        this.n_branchname_like = n_branchname_like;
    }
                
	private String n_taskname_eq;//[相关任务]
	public void setN_taskname_eq(String n_taskname_eq) {
        this.n_taskname_eq = n_taskname_eq;
    }
                
	private String n_taskname_like;//[相关任务]
	public void setN_taskname_like(String n_taskname_like) {
        this.n_taskname_like = n_taskname_like;
    }
                
	private String n_projectname_eq;//[项目]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
    }
                
	private String n_projectname_like;//[项目]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
    }
                
	private String n_storyname_eq;//[相关需求]
	public void setN_storyname_eq(String n_storyname_eq) {
        this.n_storyname_eq = n_storyname_eq;
    }
                
	private String n_storyname_like;//[相关需求]
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
                
	private Long n_tostory_eq;//[转需求]
	public void setN_tostory_eq(Long n_tostory_eq) {
        this.n_tostory_eq = n_tostory_eq;
    }
                
	private Long n_entry_eq;//[应用]
	public void setN_entry_eq(Long n_entry_eq) {
        this.n_entry_eq = n_entry_eq;
    }
                
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
                
	private Long n_totask_eq;//[转任务]
	public void setN_totask_eq(Long n_totask_eq) {
        this.n_totask_eq = n_totask_eq;
    }
                
	private Long n_plan_eq;//[所属计划]
	public void setN_plan_eq(Long n_plan_eq) {
        this.n_plan_eq = n_plan_eq;
    }
                
	private Long n_module_eq;//[所属模块]
	public void setN_module_eq(Long n_module_eq) {
        this.n_module_eq = n_module_eq;
    }
                
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
    }
                
	private Long n_duplicatebug_eq;//[重复ID]
	public void setN_duplicatebug_eq(Long n_duplicatebug_eq) {
        this.n_duplicatebug_eq = n_duplicatebug_eq;
    }
                
	private Long n_repo_eq;//[代码]
	public void setN_repo_eq(Long n_repo_eq) {
        this.n_repo_eq = n_repo_eq;
    }
                
	private Long n_story_eq;//[相关需求]
	public void setN_story_eq(Long n_story_eq) {
        this.n_story_eq = n_story_eq;
    }
                
	private Long n_case_eq;//[相关用例]
	public void setN_case_eq(Long n_case_eq) {
        this.n_case_eq = n_case_eq;
    }
                
	private Long n_project_eq;//[所属项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
    }
                
	private Long n_task_eq;//[相关任务]
	public void setN_task_eq(Long n_task_eq) {
        this.n_task_eq = n_task_eq;
    }
                
	private Long n_testtask_eq;//[测试单]
	public void setN_testtask_eq(Long n_testtask_eq) {
        this.n_testtask_eq = n_testtask_eq;
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
                     wrapper.like("t1.`ID`", query)
                        .or().like("t1.`TITLE`", query)
            );
		 }
	}
}



