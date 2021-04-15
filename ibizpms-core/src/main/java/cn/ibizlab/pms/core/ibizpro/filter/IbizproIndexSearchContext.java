package cn.ibizlab.pms.core.ibizpro.filter;

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
import org.elasticsearch.index.query.QueryBuilders;


import cn.ibizlab.pms.util.filter.QueryBuildContext;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import java.util.regex.Pattern;
import com.mongodb.BasicDBObject;

/**
 * NoSQL数据实体[IbizproIndex] 查询条件对象
 */
@Slf4j
@Data
public class IbizproIndexSearchContext extends QueryBuildContext {

	private String n_indexname_like;//[标题[需求、任务等]]
	public void setN_indexname_like(String n_indexname_like) {
        this.n_indexname_like = n_indexname_like;
        if(!ObjectUtils.isEmpty(this.n_indexname_like)){
            Pattern pattern = Pattern.compile("^.*" + n_indexname_like + ".*$", Pattern.CASE_INSENSITIVE);
            this.getSearchCond().and("indexname").regex(pattern);
            this.getEsCond().must(QueryBuilders.wildcardQuery("indexname", String.format("*%s*",n_indexname_like)));
        }
    }
	private String n_index_type_eq;//[类型]
	public void setN_index_type_eq(String n_index_type_eq) {
        this.n_index_type_eq = n_index_type_eq;
        if(!ObjectUtils.isEmpty(this.n_index_type_eq)){
            this.getSearchCond().and("index_type").is(n_index_type_eq);
            this.getEsCond().must(QueryBuilders.termQuery("index_type", n_index_type_eq));
        }
    }

    /**
	 * 启用快速搜索
	 */
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
            Pattern pattern = Pattern.compile("^.*" + query + ".*$", Pattern.CASE_INSENSITIVE);
            this.getSearchCond().or(new BasicDBObject("indexname",pattern));
            Pattern pattern = Pattern.compile("^.*" + query + ".*$", Pattern.CASE_INSENSITIVE);
            this.getSearchCond().or(new BasicDBObject("indexdesc",pattern));
		 }
	}
}


