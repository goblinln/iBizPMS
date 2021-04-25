package cn.ibizlab.pms.core.ibizsysmodel.filter;

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


import cn.ibizlab.pms.util.filter.SearchContextBase;

/**
 * ServiceApi数据实体[PSDataEntity] 查询条件对象
 */
@Slf4j
@Data
public class PSDataEntitySearchContext extends SearchContextBase {
	private Integer n_modelimpexpflag_eq;//[模型导入导出能力]

	private Integer n_accctrlarch_eq;//[访问控制体系]

	private Integer n_lockflag_eq;//[模型锁模式]

	private Integer n_serviceapiflag_eq;//[服务API模式]

	private Integer n_dataimpexpflag_eq;//[数据导入导出能力]

	private Integer n_datachglogmode_eq;//[数据变更日志类型]

	private Integer n_dynamicmode_eq;//[扩展模式]

	private String n_indexdetype_eq;//[索引类型]

	private Integer n_storagemode_eq;//[存储模式]

	private Integer n_detype_eq;//[实体类型]

	private Integer n_saasmode_eq;//[SaaS构型]

	private String n_psdataentityname_like;//[实体名称]

	private String n_psdataentityid_eq;//[实体标识]

	private String n_biztag_eq;//[预置业务功能模式]

	private String n_dbtabspace_eq;//[数据库表空间]

	private String n_dslink_eq;//[默认数据源]

	private Integer n_viewlevel_eq;//[多视图级别]

	private Integer n_virtualflag_eq;//[虚拟实体]

	private Integer n_enatempdata_eq;//[启用临时数据]

	private String n_usercat_eq;//[用户分类]

	private Integer n_removeflag_eq;//[删除模式]

	private Integer n_dynamodelflag_eq;//[动态模型类型]

	private String n_decat_eq;//[实体类别]

	private Integer n_auditmode_eq;//[审计模式]

	private Integer n_dataaccmode_eq;//[数据访问控制方式]

	private String n_pssubsysserviceapiname_eq;//[子系统服务接口]

	private String n_pssubsysserviceapiname_like;//[子系统服务接口]

	private String n_pssubsyssadename_eq;//[子系统接口实体]

	private String n_pssubsyssadename_like;//[子系统接口实体]

	private String n_psmodulename_eq;//[系统模块]

	private String n_psmodulename_like;//[系统模块]

	private String n_pssysreqitemname_eq;//[系统设计需求]

	private String n_pssysreqitemname_like;//[系统设计需求]

	private String n_pssysreqitemid_eq;//[系统设计需求]

	private String n_pssubsysserviceapiid_eq;//[子系统服务接口]

	private String n_psmoduleid_eq;//[系统模块]

	private String n_pssubsyssadeid_eq;//[子系统接口实体]

}


