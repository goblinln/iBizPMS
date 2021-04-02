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
import cn.ibizlab.pms.core.zentao.domain.Build;
/**
 * 关系型数据实体[Build] 查询条件对象
 */
@Slf4j
@Data
public class BuildSearchContext extends QueryWrapperContext<Build> {

	private String n_name_like;//[名称编号]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSearchCond().like("`name`", n_name_like);
        }
    }
	private String n_backgroundid_eq;//[后台体系]
	public void setN_backgroundid_eq(String n_backgroundid_eq) {
        this.n_backgroundid_eq = n_backgroundid_eq;
        if(!ObjectUtils.isEmpty(this.n_backgroundid_eq)){
            this.getSearchCond().eq("`backgroundid`", n_backgroundid_eq);
        }
    }
	private String n_releasetype_eq;//[运行模式]
	public void setN_releasetype_eq(String n_releasetype_eq) {
        this.n_releasetype_eq = n_releasetype_eq;
        if(!ObjectUtils.isEmpty(this.n_releasetype_eq)){
            this.getSearchCond().eq("`releasetype`", n_releasetype_eq);
        }
    }
	private Integer n_rebuild_eq;//[重新构建]
	public void setN_rebuild_eq(Integer n_rebuild_eq) {
        this.n_rebuild_eq = n_rebuild_eq;
        if(!ObjectUtils.isEmpty(this.n_rebuild_eq)){
            this.getSearchCond().eq("`rebuild`", n_rebuild_eq);
        }
    }
	private String n_sqlid_eq;//[运行数据库]
	public void setN_sqlid_eq(String n_sqlid_eq) {
        this.n_sqlid_eq = n_sqlid_eq;
        if(!ObjectUtils.isEmpty(this.n_sqlid_eq)){
            this.getSearchCond().eq("`sqlid`", n_sqlid_eq);
        }
    }
	private String n_frontapplication_eq;//[系统应用]
	public void setN_frontapplication_eq(String n_frontapplication_eq) {
        this.n_frontapplication_eq = n_frontapplication_eq;
        if(!ObjectUtils.isEmpty(this.n_frontapplication_eq)){
            this.getSearchCond().eq("`frontapplication`", n_frontapplication_eq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_ltandeq;//[打包日期]
	public void setN_date_ltandeq(Timestamp n_date_ltandeq) {
        this.n_date_ltandeq = n_date_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_date_ltandeq)){
            this.getSearchCond().le("`date`", n_date_ltandeq);
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
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("`product`", n_product_eq);
        }
    }
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
        if(!ObjectUtils.isEmpty(this.n_branch_eq)){
            this.getSearchCond().eq("`branch`", n_branch_eq);
        }
    }
	private Long n_project_eq;//[所属项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
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



