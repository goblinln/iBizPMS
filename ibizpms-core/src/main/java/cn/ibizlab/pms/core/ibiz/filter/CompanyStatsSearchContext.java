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
import cn.ibizlab.pms.core.ibiz.domain.CompanyStats;
/**
 * 关系型数据实体[CompanyStats] 查询条件对象
 */
@Slf4j
@Data
public class CompanyStatsSearchContext extends QueryWrapperContext<CompanyStats> {

                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_date_gtandeq;//[日志日期]
	public void setN_date_gtandeq(Timestamp n_date_gtandeq) {
        this.n_date_gtandeq = n_date_gtandeq;
    }
                
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_date_ltandeq;//[日志日期]
	public void setN_date_ltandeq(Timestamp n_date_ltandeq) {
        this.n_date_ltandeq = n_date_ltandeq;
    }
                
	private String n_comment_like;//[备注]
	public void setN_comment_like(String n_comment_like) {
        this.n_comment_like = n_comment_like;
    }
                
	private Long n_id_eq;//[标识]
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
                     wrapper.like("comment", query)
            );
		 }
	}
}



