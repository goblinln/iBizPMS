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
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
/**
 * 关系型数据实体[TaskTeam] 查询条件对象
 */
@Slf4j
@Data
public class TaskTeamSearchContext extends QueryWrapperContext<TaskTeam> {

	private String n_account_like;//[用户]
	public void setN_account_like(String n_account_like) {
        this.n_account_like = n_account_like;
    }
	private String n_limited_eq;//[受限用户]
	public void setN_limited_eq(String n_limited_eq) {
        this.n_limited_eq = n_limited_eq;
    }
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private String n_type_eq;//[团队类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
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
                     wrapper.like("t1.`ACCOUNT`", query)
            );
		 }
	}
}



