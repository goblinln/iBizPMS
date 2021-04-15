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
import cn.ibizlab.pms.core.zentao.domain.UserTpl;
/**
 * 关系型数据实体[UserTpl] 查询条件对象
 */
@Slf4j
@Data
public class UserTplSearchContext extends QueryWrapperContext<UserTpl> {

	private String n_title_like;//[模板标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
	private String n_type_eq;//[type]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
	private String n_public_eq;//[公开]
	public void setN_public_eq(String n_public_eq) {
        this.n_public_eq = n_public_eq;
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
            );
		 }
	}
}



