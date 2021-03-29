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
import cn.ibizlab.pms.core.zentao.domain.History;
/**
 * 关系型数据实体[History] 查询条件对象
 */
@Slf4j
@Data
public class HistorySearchContext extends QueryWrapperContext<History> {

	private String n_field_leftlike;//[字段]
	public void setN_field_leftlike(String n_field_leftlike) {
        this.n_field_leftlike = n_field_leftlike;
        if(!ObjectUtils.isEmpty(this.n_field_leftlike)){
            this.getSearchCond().likeRight("`field`", n_field_leftlike);
        }
    }
	private Long n_action_eq;//[关联日志]
	public void setN_action_eq(Long n_action_eq) {
        this.n_action_eq = n_action_eq;
        if(!ObjectUtils.isEmpty(this.n_action_eq)){
            this.getSearchCond().eq("`action`", n_action_eq);
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
                     wrapper.like("`diff`", query)
            );
		 }
	}
}



