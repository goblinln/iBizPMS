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
 * ServiceApi数据实体[PSSubSysSADE] 查询条件对象
 */
@Slf4j
@Data
public class PSSubSysSADESearchContext extends SearchContextBase {
	private String n_syncmodelmode_eq;//[同步模型模式]

	private String n_pssubsyssadeid_eq;//[外部接口实体标识]

	private Integer n_majorflag_eq;//[接口模式]

	private String n_usercat_eq;//[用户分类]

	private String n_pssubsyssadename_like;//[外部接口实体名称]

	private String n_pssubsysserviceapiname_eq;//[子系统接口]

	private String n_pssubsysserviceapiname_like;//[子系统接口]

	private String n_pssubsysserviceapiid_eq;//[子系统接口]

}


