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
import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
/**
 * 关系型数据实体[ProjectStats] 查询条件对象
 */
@Slf4j
@Data
public class ProjectStatsSearchContext extends QueryWrapperContext<ProjectStats> {

                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_end_ltandeq;//[截止日期]
	public void setN_end_ltandeq(Timestamp n_end_ltandeq) {
        this.n_end_ltandeq = n_end_ltandeq;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_begin_gtandeq;//[开始时间]
	public void setN_begin_gtandeq(Timestamp n_begin_gtandeq) {
        this.n_begin_gtandeq = n_begin_gtandeq;
    }
                
	private String n_status_eq;//[状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
                
	private String n_status_noteq;//[状态]
	public void setN_status_noteq(String n_status_noteq) {
        this.n_status_noteq = n_status_noteq;
    }
                
	private String n_name_like;//[项目名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
                
	private Long n_id_eq;//[项目编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
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



