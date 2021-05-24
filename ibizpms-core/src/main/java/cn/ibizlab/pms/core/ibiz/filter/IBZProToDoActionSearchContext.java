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
import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoAction;
/**
 * 关系型数据实体[IBZProToDoAction] 查询条件对象
 */
@Slf4j
@Data
public class IBZProToDoActionSearchContext extends QueryWrapperContext<IBZProToDoAction> {

                
	private String n_actionmanner_eq;//[操作方式]
	public void setN_actionmanner_eq(String n_actionmanner_eq) {
        this.n_actionmanner_eq = n_actionmanner_eq;
    }
                
	private String n_action_eq;//[动作]
	public void setN_action_eq(String n_action_eq) {
        this.n_action_eq = n_action_eq;
    }
                
	private String n_comment_like;//[备注]
	public void setN_comment_like(String n_comment_like) {
        this.n_comment_like = n_comment_like;
    }
                
	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_objecttype_eq;//[对象类型]
	public void setN_objecttype_eq(String n_objecttype_eq) {
        this.n_objecttype_eq = n_objecttype_eq;
    }
                
	private String n_read_eq;//[已读]
	public void setN_read_eq(String n_read_eq) {
        this.n_read_eq = n_read_eq;
    }
                
	private Long n_objectid_eq;//[编号]
	public void setN_objectid_eq(Long n_objectid_eq) {
        this.n_objectid_eq = n_objectid_eq;
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
                     wrapper.like("t1.`ACTOR`", query)
            );
		 }
	}
}



