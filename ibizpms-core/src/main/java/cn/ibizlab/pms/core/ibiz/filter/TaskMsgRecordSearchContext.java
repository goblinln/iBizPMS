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
import cn.ibizlab.pms.core.ibiz.domain.TaskMsgRecord;
/**
 * 关系型数据实体[TaskMsgRecord] 查询条件对象
 */
@Slf4j
@Data
public class TaskMsgRecordSearchContext extends QueryWrapperContext<TaskMsgRecord> {

	private String n_taskmsgrecordname_like;//[待办消息记录名称]
	public void setN_taskmsgrecordname_like(String n_taskmsgrecordname_like) {
        this.n_taskmsgrecordname_like = n_taskmsgrecordname_like;
        if(!ObjectUtils.isEmpty(this.n_taskmsgrecordname_like)){
            this.getSearchCond().like("`taskmsgrecordname`", n_taskmsgrecordname_like);
        }
    }
	private String n_taskmsgrecordid_eq;//[待办消息记录标识]
	public void setN_taskmsgrecordid_eq(String n_taskmsgrecordid_eq) {
        this.n_taskmsgrecordid_eq = n_taskmsgrecordid_eq;
        if(!ObjectUtils.isEmpty(this.n_taskmsgrecordid_eq)){
            this.getSearchCond().eq("`taskmsgrecordid`", n_taskmsgrecordid_eq);
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
                     wrapper.like("`taskmsgrecordname`", query)
            );
		 }
	}
}



