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
import cn.ibizlab.pms.core.zentao.domain.File;
/**
 * 关系型数据实体[File] 查询条件对象
 */
@Slf4j
@Data
public class FileSearchContext extends QueryWrapperContext<File> {

	private Long n_objectid_eq;//[对象ID]
	public void setN_objectid_eq(Long n_objectid_eq) {
        this.n_objectid_eq = n_objectid_eq;
    }
	private String n_objecttype_eq;//[对象类型]
	public void setN_objecttype_eq(String n_objecttype_eq) {
        this.n_objecttype_eq = n_objecttype_eq;
    }
	private String n_title_like;//[标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
	private String n_extra_eq;//[备注]
	public void setN_extra_eq(String n_extra_eq) {
        this.n_extra_eq = n_extra_eq;
    }
	private String n_extra_noteq;//[备注]
	public void setN_extra_noteq(String n_extra_noteq) {
        this.n_extra_noteq = n_extra_noteq;
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



