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
import cn.ibizlab.pms.core.zentao.domain.Release;
/**
 * 关系型数据实体[Release] 查询条件对象
 */
@Slf4j
@Data
public class ReleaseSearchContext extends QueryWrapperContext<Release> {

	private String n_marker_eq;//[里程碑]
	public void setN_marker_eq(String n_marker_eq) {
        this.n_marker_eq = n_marker_eq;
        if(!ObjectUtils.isEmpty(this.n_marker_eq)){
            this.getSearchCond().eq("`marker`", n_marker_eq);
        }
    }
	private Long n_id_eq;//[ID]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private Integer n_rebuild_eq;//[重新构建]
	public void setN_rebuild_eq(Integer n_rebuild_eq) {
        this.n_rebuild_eq = n_rebuild_eq;
        if(!ObjectUtils.isEmpty(this.n_rebuild_eq)){
            this.getSearchCond().eq("`rebuild`", n_rebuild_eq);
        }
    }
	private String n_name_like;//[发布名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSearchCond().like("`name`", n_name_like);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_date_ltandeq;//[发布日期]
	public void setN_date_ltandeq(Timestamp n_date_ltandeq) {
        this.n_date_ltandeq = n_date_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_date_ltandeq)){
            this.getSearchCond().le("`date`", n_date_ltandeq);
        }
    }
	private String n_status_eq;//[状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
        if(!ObjectUtils.isEmpty(this.n_status_eq)){
            this.getSearchCond().eq("`status`", n_status_eq);
        }
    }
	private String n_releasetype_eq;//[运行模式]
	public void setN_releasetype_eq(String n_releasetype_eq) {
        this.n_releasetype_eq = n_releasetype_eq;
        if(!ObjectUtils.isEmpty(this.n_releasetype_eq)){
            this.getSearchCond().eq("`releasetype`", n_releasetype_eq);
        }
    }
	private String n_productname_eq;//[产品名称]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
        if(!ObjectUtils.isEmpty(this.n_productname_eq)){
            this.getSearchCond().eq("`productname`", n_productname_eq);
        }
    }
	private String n_productname_like;//[产品名称]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
        if(!ObjectUtils.isEmpty(this.n_productname_like)){
            this.getSearchCond().like("`productname`", n_productname_like);
        }
    }
	private String n_buildname_eq;//[版本]
	public void setN_buildname_eq(String n_buildname_eq) {
        this.n_buildname_eq = n_buildname_eq;
        if(!ObjectUtils.isEmpty(this.n_buildname_eq)){
            this.getSearchCond().eq("`buildname`", n_buildname_eq);
        }
    }
	private String n_buildname_like;//[版本]
	public void setN_buildname_like(String n_buildname_like) {
        this.n_buildname_like = n_buildname_like;
        if(!ObjectUtils.isEmpty(this.n_buildname_like)){
            this.getSearchCond().like("`buildname`", n_buildname_like);
        }
    }
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("`product`", n_product_eq);
        }
    }
	private Long n_build_eq;//[版本]
	public void setN_build_eq(Long n_build_eq) {
        this.n_build_eq = n_build_eq;
        if(!ObjectUtils.isEmpty(this.n_build_eq)){
            this.getSearchCond().eq("`build`", n_build_eq);
        }
    }
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
        if(!ObjectUtils.isEmpty(this.n_branch_eq)){
            this.getSearchCond().eq("`branch`", n_branch_eq);
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
                     wrapper.like("`name`", query)
            );
		 }
	}
}



