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
import cn.ibizlab.pms.core.report.domain.IbzDaily;
/**
 * 关系型数据实体[IbzDaily] 查询条件对象
 */
@Slf4j
@Data
public class IbzDailySearchContext extends QueryWrapperContext<IbzDaily> {

	private String n_reportto_eq;//[汇报给]
	public void setN_reportto_eq(String n_reportto_eq) {
        this.n_reportto_eq = n_reportto_eq;
    }
	private String n_issubmit_eq;//[是否提交]
	public void setN_issubmit_eq(String n_issubmit_eq) {
        this.n_issubmit_eq = n_issubmit_eq;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_eq;//[日期]
	public void setN_date_eq(Timestamp n_date_eq) {
        this.n_date_eq = n_date_eq;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_gtandeq;//[日期]
	public void setN_date_gtandeq(Timestamp n_date_gtandeq) {
        this.n_date_gtandeq = n_date_gtandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_ltandeq;//[日期]
	public void setN_date_ltandeq(Timestamp n_date_ltandeq) {
        this.n_date_ltandeq = n_date_ltandeq;
    }
	private String n_reportstatus_eq;//[状态]
	public void setN_reportstatus_eq(String n_reportstatus_eq) {
        this.n_reportstatus_eq = n_reportstatus_eq;
    }
	private String n_account_eq;//[用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
    }
	private String n_ibz_dailyname_like;//[日报名称]
	public void setN_ibz_dailyname_like(String n_ibz_dailyname_like) {
        this.n_ibz_dailyname_like = n_ibz_dailyname_like;
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
                     wrapper.like("t1.`IBZ_DAILYNAME`", query)
            );
		 }
	}
}



