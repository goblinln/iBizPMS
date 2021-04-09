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
import cn.ibizlab.pms.core.zentao.domain.Task;
/**
 * 关系型数据实体[Task] 查询条件对象
 */
@Slf4j
@Data
public class TaskSearchContext extends QueryWrapperContext<Task> {

	private String n_canceledby_eq;//[由谁取消]
	public void setN_canceledby_eq(String n_canceledby_eq) {
        this.n_canceledby_eq = n_canceledby_eq;
        if(!ObjectUtils.isEmpty(this.n_canceledby_eq)){
            this.getSearchCond().eq("`canceledby`", n_canceledby_eq);
        }
    }
	private String n_config_type_eq;//[周期类型]
	public void setN_config_type_eq(String n_config_type_eq) {
        this.n_config_type_eq = n_config_type_eq;
        if(!ObjectUtils.isEmpty(this.n_config_type_eq)){
            this.getSearchCond().eq("`config_type`", n_config_type_eq);
        }
    }
	private String n_color_eq;//[标题颜色]
	public void setN_color_eq(String n_color_eq) {
        this.n_color_eq = n_color_eq;
        if(!ObjectUtils.isEmpty(this.n_color_eq)){
            this.getSearchCond().eq("`color`", n_color_eq);
        }
    }
	private Long n_id_noteq;//[编号]
	public void setN_id_noteq(Long n_id_noteq) {
        this.n_id_noteq = n_id_noteq;
        if(!ObjectUtils.isEmpty(this.n_id_noteq)){
            this.getSearchCond().ne("`id`", n_id_noteq);
        }
    }
	private String n_finishedby_eq;//[由谁完成]
	public void setN_finishedby_eq(String n_finishedby_eq) {
        this.n_finishedby_eq = n_finishedby_eq;
        if(!ObjectUtils.isEmpty(this.n_finishedby_eq)){
            this.getSearchCond().eq("`finishedby`", n_finishedby_eq);
        }
    }
	private String n_status1_eq;//[任务状态]
	public void setN_status1_eq(String n_status1_eq) {
        this.n_status1_eq = n_status1_eq;
        if(!ObjectUtils.isEmpty(this.n_status1_eq)){
            this.getSearchCond().eq("`status1`", n_status1_eq);
        }
    }
	private String n_closedby_eq;//[由谁关闭]
	public void setN_closedby_eq(String n_closedby_eq) {
        this.n_closedby_eq = n_closedby_eq;
        if(!ObjectUtils.isEmpty(this.n_closedby_eq)){
            this.getSearchCond().eq("`closedby`", n_closedby_eq);
        }
    }
	private String n_closedreason_eq;//[关闭原因]
	public void setN_closedreason_eq(String n_closedreason_eq) {
        this.n_closedreason_eq = n_closedreason_eq;
        if(!ObjectUtils.isEmpty(this.n_closedreason_eq)){
            this.getSearchCond().eq("`closedreason`", n_closedreason_eq);
        }
    }
	private String n_taskspecies_eq;//[任务种别]
	public void setN_taskspecies_eq(String n_taskspecies_eq) {
        this.n_taskspecies_eq = n_taskspecies_eq;
        if(!ObjectUtils.isEmpty(this.n_taskspecies_eq)){
            this.getSearchCond().eq("`taskspecies`", n_taskspecies_eq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_assigneddate_gtandeq;//[指派日期]
	public void setN_assigneddate_gtandeq(Timestamp n_assigneddate_gtandeq) {
        this.n_assigneddate_gtandeq = n_assigneddate_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_assigneddate_gtandeq)){
            this.getSearchCond().ge("`assigneddate`", n_assigneddate_gtandeq);
        }
    }
	private Integer n_pri_eq;//[优先级]
	public void setN_pri_eq(Integer n_pri_eq) {
        this.n_pri_eq = n_pri_eq;
        if(!ObjectUtils.isEmpty(this.n_pri_eq)){
            this.getSearchCond().eq("`pri`", n_pri_eq);
        }
    }
	private String n_pri_in;//[优先级]
	public void setN_pri_in(String n_pri_in) {
        this.n_pri_in = n_pri_in;
        if(!ObjectUtils.isEmpty(this.n_pri_in)){
			this.getSearchCond().in("`pri`",this.n_pri_in.split(";"));
        }
    }
	private String n_lasteditedby_eq;//[最后修改]
	public void setN_lasteditedby_eq(String n_lasteditedby_eq) {
        this.n_lasteditedby_eq = n_lasteditedby_eq;
        if(!ObjectUtils.isEmpty(this.n_lasteditedby_eq)){
            this.getSearchCond().eq("`lasteditedby`", n_lasteditedby_eq);
        }
    }
	private String n_status_eq;//[任务状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
        if(!ObjectUtils.isEmpty(this.n_status_eq)){
            this.getSearchCond().eq("`status`", n_status_eq);
        }
    }
	private String n_status_in;//[任务状态]
	public void setN_status_in(String n_status_in) {
        this.n_status_in = n_status_in;
        if(!ObjectUtils.isEmpty(this.n_status_in)){
			this.getSearchCond().in("`status`",this.n_status_in.split(";"));
        }
    }
	private String n_status_noteq;//[任务状态]
	public void setN_status_noteq(String n_status_noteq) {
        this.n_status_noteq = n_status_noteq;
        if(!ObjectUtils.isEmpty(this.n_status_noteq)){
            this.getSearchCond().ne("`status`", n_status_noteq);
        }
    }
	private String n_name_like;//[任务名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSearchCond().like("`name`", n_name_like);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_closeddate_ltandeq;//[关闭时间]
	public void setN_closeddate_ltandeq(Timestamp n_closeddate_ltandeq) {
        this.n_closeddate_ltandeq = n_closeddate_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_closeddate_ltandeq)){
            this.getSearchCond().le("`closeddate`", n_closeddate_ltandeq);
        }
    }
	private String n_type_eq;//[任务类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("`type`", n_type_eq);
        }
    }
	private String n_assignedto_eq;//[指派给]
	public void setN_assignedto_eq(String n_assignedto_eq) {
        this.n_assignedto_eq = n_assignedto_eq;
        if(!ObjectUtils.isEmpty(this.n_assignedto_eq)){
            this.getSearchCond().eq("`assignedto`", n_assignedto_eq);
        }
    }
	private String n_assignedto_in;//[指派给]
	public void setN_assignedto_in(String n_assignedto_in) {
        this.n_assignedto_in = n_assignedto_in;
        if(!ObjectUtils.isEmpty(this.n_assignedto_in)){
			this.getSearchCond().in("`assignedto`",this.n_assignedto_in.split(";"));
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_eststarted_gtandeq;//[预计开始]
	public void setN_eststarted_gtandeq(Timestamp n_eststarted_gtandeq) {
        this.n_eststarted_gtandeq = n_eststarted_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_eststarted_gtandeq)){
            this.getSearchCond().ge("`eststarted`", n_eststarted_gtandeq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_eststarted_ltandeq;//[预计开始]
	public void setN_eststarted_ltandeq(Timestamp n_eststarted_ltandeq) {
        this.n_eststarted_ltandeq = n_eststarted_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_eststarted_ltandeq)){
            this.getSearchCond().le("`eststarted`", n_eststarted_ltandeq);
        }
    }
	private Integer n_cycle_eq;//[周期]
	public void setN_cycle_eq(Integer n_cycle_eq) {
        this.n_cycle_eq = n_cycle_eq;
        if(!ObjectUtils.isEmpty(this.n_cycle_eq)){
            this.getSearchCond().eq("`cycle`", n_cycle_eq);
        }
    }
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
        if(!ObjectUtils.isEmpty(this.n_openedby_eq)){
            this.getSearchCond().eq("`openedby`", n_openedby_eq);
        }
    }
	private String n_tasktype_eq;//[任务类型]
	public void setN_tasktype_eq(String n_tasktype_eq) {
        this.n_tasktype_eq = n_tasktype_eq;
        if(!ObjectUtils.isEmpty(this.n_tasktype_eq)){
            this.getSearchCond().eq("`tasktype`", n_tasktype_eq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_finisheddate_ltandeq;//[实际完成]
	public void setN_finisheddate_ltandeq(Timestamp n_finisheddate_ltandeq) {
        this.n_finisheddate_ltandeq = n_finisheddate_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_finisheddate_ltandeq)){
            this.getSearchCond().le("`finisheddate`", n_finisheddate_ltandeq);
        }
    }
	private String n_modulename_eq;//[所属模块]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
        if(!ObjectUtils.isEmpty(this.n_modulename_eq)){
            this.getSearchCond().eq("`modulename`", n_modulename_eq);
        }
    }
	private String n_modulename_like;//[所属模块]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
        if(!ObjectUtils.isEmpty(this.n_modulename_like)){
            this.getSearchCond().like("`modulename`", n_modulename_like);
        }
    }
	private String n_storyname_eq;//[相关需求]
	public void setN_storyname_eq(String n_storyname_eq) {
        this.n_storyname_eq = n_storyname_eq;
        if(!ObjectUtils.isEmpty(this.n_storyname_eq)){
            this.getSearchCond().eq("`storyname`", n_storyname_eq);
        }
    }
	private String n_storyname_like;//[相关需求]
	public void setN_storyname_like(String n_storyname_like) {
        this.n_storyname_like = n_storyname_like;
        if(!ObjectUtils.isEmpty(this.n_storyname_like)){
            this.getSearchCond().like("`storyname`", n_storyname_like);
        }
    }
	private String n_path_like;//[模块路径]
	public void setN_path_like(String n_path_like) {
        this.n_path_like = n_path_like;
        if(!ObjectUtils.isEmpty(this.n_path_like)){
            this.getSearchCond().like("`path`", n_path_like);
        }
    }
	private String n_planname_eq;//[所属计划]
	public void setN_planname_eq(String n_planname_eq) {
        this.n_planname_eq = n_planname_eq;
        if(!ObjectUtils.isEmpty(this.n_planname_eq)){
            this.getSearchCond().eq("`planname`", n_planname_eq);
        }
    }
	private String n_planname_like;//[所属计划]
	public void setN_planname_like(String n_planname_like) {
        this.n_planname_like = n_planname_like;
        if(!ObjectUtils.isEmpty(this.n_planname_like)){
            this.getSearchCond().like("`planname`", n_planname_like);
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
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("`product`", n_product_eq);
        }
    }
	private String n_parentname_eq;//[父任务]
	public void setN_parentname_eq(String n_parentname_eq) {
        this.n_parentname_eq = n_parentname_eq;
        if(!ObjectUtils.isEmpty(this.n_parentname_eq)){
            this.getSearchCond().eq("`parentname`", n_parentname_eq);
        }
    }
	private String n_parentname_like;//[父任务]
	public void setN_parentname_like(String n_parentname_like) {
        this.n_parentname_like = n_parentname_like;
        if(!ObjectUtils.isEmpty(this.n_parentname_like)){
            this.getSearchCond().like("`parentname`", n_parentname_like);
        }
    }
	private Long n_project_eq;//[所属项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
        }
    }
	private String n_project_in;//[所属项目]
	public void setN_project_in(String n_project_in) {
        this.n_project_in = n_project_in;
        if(!ObjectUtils.isEmpty(this.n_project_in)){
			this.getSearchCond().in("`project`",this.n_project_in.split(";"));
        }
    }
	private Long n_plan_eq;//[编号]
	public void setN_plan_eq(Long n_plan_eq) {
        this.n_plan_eq = n_plan_eq;
        if(!ObjectUtils.isEmpty(this.n_plan_eq)){
            this.getSearchCond().eq("`plan`", n_plan_eq);
        }
    }
	private Long n_plan_noteq;//[编号]
	public void setN_plan_noteq(Long n_plan_noteq) {
        this.n_plan_noteq = n_plan_noteq;
        if(!ObjectUtils.isEmpty(this.n_plan_noteq)){
            this.getSearchCond().ne("`plan`", n_plan_noteq);
        }
    }
	private Long n_module_eq;//[模块]
	public void setN_module_eq(Long n_module_eq) {
        this.n_module_eq = n_module_eq;
        if(!ObjectUtils.isEmpty(this.n_module_eq)){
            this.getSearchCond().eq("`module`", n_module_eq);
        }
    }
	private Long n_story_eq;//[相关需求]
	public void setN_story_eq(Long n_story_eq) {
        this.n_story_eq = n_story_eq;
        if(!ObjectUtils.isEmpty(this.n_story_eq)){
            this.getSearchCond().eq("`story`", n_story_eq);
        }
    }
	private Long n_parent_eq;//[父任务]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
        if(!ObjectUtils.isEmpty(this.n_parent_eq)){
            this.getSearchCond().eq("`parent`", n_parent_eq);
        }
    }
	private Long n_parent_gtandeq;//[父任务]
	public void setN_parent_gtandeq(Long n_parent_gtandeq) {
        this.n_parent_gtandeq = n_parent_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_parent_gtandeq)){
            this.getSearchCond().ge("`parent`", n_parent_gtandeq);
        }
    }
	private Long n_frombug_eq;//[来源Bug]
	public void setN_frombug_eq(Long n_frombug_eq) {
        this.n_frombug_eq = n_frombug_eq;
        if(!ObjectUtils.isEmpty(this.n_frombug_eq)){
            this.getSearchCond().eq("`frombug`", n_frombug_eq);
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
                     wrapper.like("`id`", query)
                        .or().like("`name`", query)
            );
		 }
	}
}



