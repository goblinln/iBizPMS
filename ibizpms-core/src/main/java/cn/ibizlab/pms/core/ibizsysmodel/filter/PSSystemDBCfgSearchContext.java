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
 * ServiceApi数据实体[PSSystemDBCfg] 查询条件对象
 */
@Slf4j
@Data
public class PSSystemDBCfgSearchContext extends SearchContextBase {
	private Integer n_resstate_eq;//[资源状态]

	private String n_nullvalorder_eq;//[空值排序]

	private String n_pssystemdbcfgname_like;//[系统数据库名称]

	private String n_pssystemdbcfgid_eq;//[系统数据库标识]

	private String n_objnamecase_eq;//[对象名称转换]

	private String n_usercat_eq;//[用户分类]

}


