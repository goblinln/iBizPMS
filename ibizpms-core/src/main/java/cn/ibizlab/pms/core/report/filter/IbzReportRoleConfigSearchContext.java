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
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
/**
 * 关系型数据实体[IbzReportRoleConfig] 查询条件对象
 */
@Slf4j
@Data
public class IbzReportRoleConfigSearchContext extends QueryWrapperContext<IbzReportRoleConfig> {

	private String n_ibz_report_role_configname_like;//[汇报角色配置名称]
	public void setN_ibz_report_role_configname_like(String n_ibz_report_role_configname_like) {
        this.n_ibz_report_role_configname_like = n_ibz_report_role_configname_like;
    }
	private String n_type_eq;//[类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
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
                     wrapper.like("t1.`IBZ_REPORT_ROLE_CONFIGNAME`", query)
            );
		 }
	}
}



