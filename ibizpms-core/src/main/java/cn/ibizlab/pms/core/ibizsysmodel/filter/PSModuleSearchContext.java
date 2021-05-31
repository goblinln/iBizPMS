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
 * ServiceApi数据实体[PSModule] 查询条件对象
 */
@Slf4j
@Data
public class PSModuleSearchContext extends SearchContextBase {
	private Integer n_lockflag_eq;//[模型锁模式]

	private String n_psmodulename_like;//[系统模块名称]

	private String n_usercat_eq;//[用户分类]

	private String n_psmoduleid_eq;//[系统模块标识]

	private Integer n_serviceapiflag_eq;//[服务API模式]

	private String n_sysreftype_eq;//[子系统类型]

}


