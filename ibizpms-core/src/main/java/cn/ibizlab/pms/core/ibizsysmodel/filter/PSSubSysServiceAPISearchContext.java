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
 * ServiceApi数据实体[PSSubSysServiceAPI] 查询条件对象
 */
@Slf4j
@Data
public class PSSubSysServiceAPISearchContext extends SearchContextBase {
	private String n_pssubsysserviceapiname_like;//[外部服务接口名称]

	private String n_pssysserviceapiname_eq;//[系统服务接口]

	private String n_pssysserviceapiname_like;//[系统服务接口]

	private String n_psmodulename_eq;//[系统模块]

	private String n_psmodulename_like;//[系统模块]

	private String n_psmoduleid_eq;//[系统模块]

	private String n_pssysserviceapiid_eq;//[系统服务接口]

}


