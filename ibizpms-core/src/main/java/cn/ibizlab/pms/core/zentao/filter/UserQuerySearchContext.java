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
import cn.ibizlab.pms.core.zentao.domain.UserQuery;
/**
 * 关系型数据实体[UserQuery] 查询条件对象
 */
@Slf4j
@Data
public class UserQuerySearchContext extends QueryWrapperContext<UserQuery> {

	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_shortcut_eq;//[shortcut]
	public void setN_shortcut_eq(String n_shortcut_eq) {
        this.n_shortcut_eq = n_shortcut_eq;
        if(!ObjectUtils.isEmpty(this.n_shortcut_eq)){
            this.getSearchCond().eq("`shortcut`", n_shortcut_eq);
        }
    }
	private String n_title_like;//[title]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
        if(!ObjectUtils.isEmpty(this.n_title_like)){
            this.getSearchCond().like("`title`", n_title_like);
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
                     wrapper.like("`title`", query)
            );
		 }
	}
}



