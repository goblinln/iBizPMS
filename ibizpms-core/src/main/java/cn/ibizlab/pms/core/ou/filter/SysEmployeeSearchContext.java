package cn.ibizlab.pms.core.ou.filter;

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
 * ServiceApi数据实体[SysEmployee] 查询条件对象
 */
@Slf4j
@Data
public class SysEmployeeSearchContext extends SearchContextBase {
	private String n_userid_eq;//[用户标识]

	private String n_username_eq;//[用户全局名]

	private String n_username_in;//[用户全局名]

	private String n_username_like;//[用户全局名]

	private String n_username_notin;//[用户全局名]

	private String n_personname_like;//[姓名]

	private String n_mdeptid_eq;//[主部门]

	private String n_mdeptname_eq;//[主部门名称]

	private String n_mdeptname_like;//[主部门名称]

	private String n_orgid_eq;//[单位]

	private String n_orgname_eq;//[单位名称]

	private String n_orgname_like;//[单位名称]

	private String n_sex_eq;//[性别]

}


