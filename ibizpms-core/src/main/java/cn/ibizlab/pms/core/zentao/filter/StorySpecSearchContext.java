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
import cn.ibizlab.pms.core.zentao.domain.StorySpec;
/**
 * 关系型数据实体[StorySpec] 查询条件对象
 */
@Slf4j
@Data
public class StorySpecSearchContext extends QueryWrapperContext<StorySpec> {

	private String n_title_like;//[需求名称]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
	private String n_id_eq;//[主键]
	public void setN_id_eq(String n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private Integer n_version_eq;//[版本号]
	public void setN_version_eq(Integer n_version_eq) {
        this.n_version_eq = n_version_eq;
    }
	private Integer n_version_noteq;//[版本号]
	public void setN_version_noteq(Integer n_version_noteq) {
        this.n_version_noteq = n_version_noteq;
    }
	private Long n_story_eq;//[需求]
	public void setN_story_eq(Long n_story_eq) {
        this.n_story_eq = n_story_eq;
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



