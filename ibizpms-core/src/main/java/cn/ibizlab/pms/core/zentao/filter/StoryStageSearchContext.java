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
import cn.ibizlab.pms.core.zentao.domain.StoryStage;
/**
 * 关系型数据实体[StoryStage] 查询条件对象
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryStageSearchContext extends QueryWrapperContext<StoryStage> {

	private BigInteger n_story_eq;//[需求]
	public void setN_story_eq(BigInteger n_story_eq) {
        this.n_story_eq = n_story_eq;
        if(!ObjectUtils.isEmpty(this.n_story_eq)){
            this.getSelectCond().eq("story", n_story_eq);
        }
    }
	private BigInteger n_branch_eq;//[平台/分支]
	public void setN_branch_eq(BigInteger n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
        if(!ObjectUtils.isEmpty(this.n_branch_eq)){
            this.getSelectCond().eq("branch", n_branch_eq);
        }
    }

    /**
	 * 启用快速搜索
	 */
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
		 }
	}
}




