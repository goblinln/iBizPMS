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
import cn.ibizlab.pms.core.zentao.domain.WebHook;
/**
 * 关系型数据实体[WebHook] 查询条件对象
 */
@Slf4j
@Data
public class WebHookSearchContext extends QueryWrapperContext<WebHook> {

	private String n_name_like;//[name]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
	private String n_sendtype_eq;//[sendType]
	public void setN_sendtype_eq(String n_sendtype_eq) {
        this.n_sendtype_eq = n_sendtype_eq;
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
                     wrapper.like("name", query)
            );
		 }
	}
}



