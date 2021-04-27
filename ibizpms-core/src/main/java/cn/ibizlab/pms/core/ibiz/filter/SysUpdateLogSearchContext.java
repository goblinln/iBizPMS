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
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
/**
 * 关系型数据实体[SysUpdateLog] 查询条件对象
 */
@Slf4j
@Data
public class SysUpdateLogSearchContext extends QueryWrapperContext<SysUpdateLog> {

	private String n_sys_update_logid_eq;//[系统更新日志标识]
	public void setN_sys_update_logid_eq(String n_sys_update_logid_eq) {
        this.n_sys_update_logid_eq = n_sys_update_logid_eq;
        if(!ObjectUtils.isEmpty(this.n_sys_update_logid_eq)){
            this.getSearchCond().eq("`sys_update_logid`", n_sys_update_logid_eq);
        }
    }
	private String n_updatebranch_eq;//[更新平台]
	public void setN_updatebranch_eq(String n_updatebranch_eq) {
        this.n_updatebranch_eq = n_updatebranch_eq;
        if(!ObjectUtils.isEmpty(this.n_updatebranch_eq)){
            this.getSearchCond().eq("`updatebranch`", n_updatebranch_eq);
        }
    }
	private String n_sys_update_logname_like;//[更新名称]
	public void setN_sys_update_logname_like(String n_sys_update_logname_like) {
        this.n_sys_update_logname_like = n_sys_update_logname_like;
        if(!ObjectUtils.isEmpty(this.n_sys_update_logname_like)){
            this.getSearchCond().like("`sys_update_logname`", n_sys_update_logname_like);
        }
    }
	private Integer n_latestupdate_eq;//[最新更新]
	public void setN_latestupdate_eq(Integer n_latestupdate_eq) {
        this.n_latestupdate_eq = n_latestupdate_eq;
        if(!ObjectUtils.isEmpty(this.n_latestupdate_eq)){
            this.getSearchCond().eq("`latestupdate`", n_latestupdate_eq);
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
                     wrapper.like("`sys_update_logname`", query)
            );
		 }
	}
}



