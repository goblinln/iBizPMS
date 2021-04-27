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
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
/**
 * 关系型数据实体[PRODUCTTEAM] 查询条件对象
 */
@Slf4j
@Data
public class PRODUCTTEAMSearchContext extends QueryWrapperContext<PRODUCTTEAM> {

	private String n_teamstatus_eq;//[成员状态]
	public void setN_teamstatus_eq(String n_teamstatus_eq) {
        this.n_teamstatus_eq = n_teamstatus_eq;
        if(!ObjectUtils.isEmpty(this.n_teamstatus_eq)){
            this.getSearchCond().eq("`teamstatus`", n_teamstatus_eq);
        }
    }
	private String n_account_eq;//[用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
        if(!ObjectUtils.isEmpty(this.n_account_eq)){
            this.getSearchCond().eq("`account`", n_account_eq);
        }
    }
	private String n_account_like;//[用户]
	public void setN_account_like(String n_account_like) {
        this.n_account_like = n_account_like;
        if(!ObjectUtils.isEmpty(this.n_account_like)){
            this.getSearchCond().like("`account`", n_account_like);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_join_gtandeq;//[加盟日]
	public void setN_join_gtandeq(Timestamp n_join_gtandeq) {
        this.n_join_gtandeq = n_join_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_join_gtandeq)){
            this.getSearchCond().ge("`join`", n_join_gtandeq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_end_ltandeq;//[结束时间]
	public void setN_end_ltandeq(Timestamp n_end_ltandeq) {
        this.n_end_ltandeq = n_end_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_end_ltandeq)){
            this.getSearchCond().le("`end`", n_end_ltandeq);
        }
    }
	private String n_type_eq;//[团队类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("`type`", n_type_eq);
        }
    }
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_leadingcadre_eq;//[当前负责人]
	public void setN_leadingcadre_eq(String n_leadingcadre_eq) {
        this.n_leadingcadre_eq = n_leadingcadre_eq;
        if(!ObjectUtils.isEmpty(this.n_leadingcadre_eq)){
            this.getSearchCond().eq("`leadingcadre`", n_leadingcadre_eq);
        }
    }
	private String n_limited_eq;//[受限用户]
	public void setN_limited_eq(String n_limited_eq) {
        this.n_limited_eq = n_limited_eq;
        if(!ObjectUtils.isEmpty(this.n_limited_eq)){
            this.getSearchCond().eq("`limited`", n_limited_eq);
        }
    }
	private Long n_root_eq;//[产品编号]
	public void setN_root_eq(Long n_root_eq) {
        this.n_root_eq = n_root_eq;
        if(!ObjectUtils.isEmpty(this.n_root_eq)){
            this.getSearchCond().eq("`root`", n_root_eq);
        }
    }
	private String n_root_in;//[产品编号]
	public void setN_root_in(String n_root_in) {
        this.n_root_in = n_root_in;
        if(!ObjectUtils.isEmpty(this.n_root_in)){
			this.getSearchCond().in("`root`",this.n_root_in.split(";"));
        }
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
                     wrapper.like("`account`", query)
            );
		 }
	}
}



