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
import cn.ibizlab.pms.core.zentao.domain.Action;
/**
 * 关系型数据实体[Action] 查询条件对象
 */
@Slf4j
@Data
public class ActionSearchContext extends QueryWrapperContext<Action> {

	private String n_thismonth_eq;//[本月]
	public void setN_thismonth_eq(String n_thismonth_eq) {
        this.n_thismonth_eq = n_thismonth_eq;
        if(!ObjectUtils.isEmpty(this.n_thismonth_eq)){
            this.getSearchCond().eq("`thismonth`", n_thismonth_eq);
        }
    }
	private String n_yesterday_eq;//[昨天]
	public void setN_yesterday_eq(String n_yesterday_eq) {
        this.n_yesterday_eq = n_yesterday_eq;
        if(!ObjectUtils.isEmpty(this.n_yesterday_eq)){
            this.getSearchCond().eq("`yesterday`", n_yesterday_eq);
        }
    }
	private String n_lastmonth_eq;//[上月]
	public void setN_lastmonth_eq(String n_lastmonth_eq) {
        this.n_lastmonth_eq = n_lastmonth_eq;
        if(!ObjectUtils.isEmpty(this.n_lastmonth_eq)){
            this.getSearchCond().eq("`lastmonth`", n_lastmonth_eq);
        }
    }
	private String n_thisweek_eq;//[本周]
	public void setN_thisweek_eq(String n_thisweek_eq) {
        this.n_thisweek_eq = n_thisweek_eq;
        if(!ObjectUtils.isEmpty(this.n_thisweek_eq)){
            this.getSearchCond().eq("`thisweek`", n_thisweek_eq);
        }
    }
	private String n_today_eq;//[今天]
	public void setN_today_eq(String n_today_eq) {
        this.n_today_eq = n_today_eq;
        if(!ObjectUtils.isEmpty(this.n_today_eq)){
            this.getSearchCond().eq("`today`", n_today_eq);
        }
    }
	private String n_objecttype_eq;//[对象类型]
	public void setN_objecttype_eq(String n_objecttype_eq) {
        this.n_objecttype_eq = n_objecttype_eq;
        if(!ObjectUtils.isEmpty(this.n_objecttype_eq)){
            this.getSearchCond().eq("`objecttype`", n_objecttype_eq);
        }
    }
	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_comment_like;//[备注]
	public void setN_comment_like(String n_comment_like) {
        this.n_comment_like = n_comment_like;
        if(!ObjectUtils.isEmpty(this.n_comment_like)){
            this.getSearchCond().like("`comment`", n_comment_like);
        }
    }
	private String n_read_eq;//[已读]
	public void setN_read_eq(String n_read_eq) {
        this.n_read_eq = n_read_eq;
        if(!ObjectUtils.isEmpty(this.n_read_eq)){
            this.getSearchCond().eq("`read`", n_read_eq);
        }
    }
	private String n_action_eq;//[动作]
	public void setN_action_eq(String n_action_eq) {
        this.n_action_eq = n_action_eq;
        if(!ObjectUtils.isEmpty(this.n_action_eq)){
            this.getSearchCond().eq("`action`", n_action_eq);
        }
    }
	private String n_actionmanner_eq;//[操作方式]
	public void setN_actionmanner_eq(String n_actionmanner_eq) {
        this.n_actionmanner_eq = n_actionmanner_eq;
        if(!ObjectUtils.isEmpty(this.n_actionmanner_eq)){
            this.getSearchCond().eq("`actionmanner`", n_actionmanner_eq);
        }
    }
	private String n_lastweek_eq;//[上周]
	public void setN_lastweek_eq(String n_lastweek_eq) {
        this.n_lastweek_eq = n_lastweek_eq;
        if(!ObjectUtils.isEmpty(this.n_lastweek_eq)){
            this.getSearchCond().eq("`lastweek`", n_lastweek_eq);
        }
    }
	private Long n_objectid_eq;//[对象ID]
	public void setN_objectid_eq(Long n_objectid_eq) {
        this.n_objectid_eq = n_objectid_eq;
        if(!ObjectUtils.isEmpty(this.n_objectid_eq)){
            this.getSearchCond().eq("`objectid`", n_objectid_eq);
        }
    }
	private Long n_project_eq;//[项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
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
                     wrapper.like("`actor`", query)
            );
		 }
	}
}



