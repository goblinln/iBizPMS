package cn.ibizlab.pms.core.report.filter;

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
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
/**
 * 关系型数据实体[IbzWeekly] 查询条件对象
 */
@Slf4j
@Data
public class IbzWeeklySearchContext extends QueryWrapperContext<IbzWeekly> {

                
	private String n_issubmit_eq;//[是否提交]
	public void setN_issubmit_eq(String n_issubmit_eq) {
        this.n_issubmit_eq = n_issubmit_eq;
    }
                
	private String n_ibz_weeklyname_like;//[周报名称]
	public void setN_ibz_weeklyname_like(String n_ibz_weeklyname_like) {
        this.n_ibz_weeklyname_like = n_ibz_weeklyname_like;
    }
                
	private Long n_ibz_weeklyid_eq;//[周报标识]
	public void setN_ibz_weeklyid_eq(Long n_ibz_weeklyid_eq) {
        this.n_ibz_weeklyid_eq = n_ibz_weeklyid_eq;
    }
                
	private String n_reportstatus_eq;//[状态]
	public void setN_reportstatus_eq(String n_reportstatus_eq) {
        this.n_reportstatus_eq = n_reportstatus_eq;
    }
                
	private String n_reportto_eq;//[汇报给]
	public void setN_reportto_eq(String n_reportto_eq) {
        this.n_reportto_eq = n_reportto_eq;
    }
                
	private String n_account_eq;//[用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
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
                     wrapper.like("ibz_weeklyname", query)
            );
		 }
	}
}



