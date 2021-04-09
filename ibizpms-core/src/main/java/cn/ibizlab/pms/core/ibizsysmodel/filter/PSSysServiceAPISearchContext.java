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
 * ServiceApi数据实体[PSSysServiceAPI] 查询条件对象
 */
@Slf4j
@Data
public class PSSysServiceAPISearchContext extends SearchContextBase {
	private Integer n_apimode_eq;//[接口模式]

	private String n_predefinedtype_eq;//[平台预定义类型]

	private Integer n_lockflag_eq;//[模型锁标志]

	private String n_defselectreqmethod_eq;//[默认查询请求方式]

	private String n_usercat_eq;//[用户分类]

	private String n_authmode_eq;//[认证模式]

	private String n_apitype_eq;//[接口类型]

	private String n_defdedatasetreqmethod_eq;//[默认结果集请求方式]

	private String n_servicetype_eq;//[服务类型]

	private String n_pssysserviceapiname_like;//[系统服务接口名称]

	private String n_defdeactionreqmethod_eq;//[默认实体行为请求方式]

	private String n_psmodulename_eq;//[系统模块]

	private String n_psmodulename_like;//[系统模块]

	private String n_psmoduleid_eq;//[系统模块]

}


