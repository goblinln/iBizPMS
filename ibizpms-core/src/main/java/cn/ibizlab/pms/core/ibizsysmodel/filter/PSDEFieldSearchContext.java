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
 * ServiceApi数据实体[PSDEField] 查询条件对象
 */
@Slf4j
@Data
public class PSDEFieldSearchContext extends SearchContextBase {
	private Integer n_viewcollevel_eq;//[视图列级别]

	private String n_dupcheckmode_eq;//[重复值检查]

	private String n_updateovmode_eq;//[更新旧值回填]

	private String n_dbvaluemode_eq;//[数据库更新值模式]

	private String n_statefield_eq;//[主状态属性]

	private String n_dvt_eq;//[默认值类型]

	private Integer n_extendmode_eq;//[扩展模式]

	private String n_biztag_eq;//[业务标记]

	private String n_unionkeyvalue_eq;//[联合键值]

	private String n_psdefieldname_like;//[实体属性名称]

	private String n_usercat_eq;//[用户分类]

	private Integer n_deftype_eq;//[属性类型]

	private String n_nullvalorder_eq;//[空值排序]

	private Integer n_pkey_eq;//[主键属性]

	private String n_predefinetype_eq;//[系统预置属性]

	private Integer n_lockflag_eq;//[模型锁模式]

	private String n_stringcase_eq;//[字符转换]

	private Integer n_dynamodelflag_eq;//[动态模型类型]

	private String n_dbvaluemode2_eq;//[数据库新建值模式]

	private String n_valuepsdefname_eq;//[值项属性]

	private String n_valuepsdefname_like;//[值项属性]

	private String n_dupchkpsdefname_eq;//[范围属性]

	private String n_dupchkpsdefname_like;//[范围属性]

	private String n_no2dupchkpsdefname_eq;//[范围属性2]

	private String n_no2dupchkpsdefname_like;//[范围属性2]

	private String n_derpsdefname_eq;//[关系属性]

	private String n_derpsdefname_like;//[关系属性]

	private String n_no3dupchkpsdefname_eq;//[范围属性3]

	private String n_no3dupchkpsdefname_like;//[范围属性3]

	private String n_psdename_eq;//[实体]

	private String n_psdename_like;//[实体]

	private String n_psdeid_eq;//[实体]

	private String n_no2dupchkpsdefid_eq;//[范围属性2]

	private String n_dupchkpsdefid_eq;//[范围属性]

	private String n_valuepsdefid_eq;//[值项属性]

	private String n_derpsdefid_eq;//[关系属性]

	private String n_no3dupchkpsdefid_eq;//[范围属性3]

}


