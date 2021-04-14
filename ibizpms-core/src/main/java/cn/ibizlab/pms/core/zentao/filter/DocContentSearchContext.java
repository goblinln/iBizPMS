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
import cn.ibizlab.pms.core.zentao.domain.DocContent;
/**
 * 关系型数据实体[DocContent] 查询条件对象
 */
@Slf4j
@Data
public class DocContentSearchContext extends QueryWrapperContext<DocContent> {

	private String n_title_like;//[文档标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
	private Long n_doc_eq;//[文档]
	public void setN_doc_eq(Long n_doc_eq) {
        this.n_doc_eq = n_doc_eq;
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
                     wrapper.like("title", query)
            );
		 }
	}
}



