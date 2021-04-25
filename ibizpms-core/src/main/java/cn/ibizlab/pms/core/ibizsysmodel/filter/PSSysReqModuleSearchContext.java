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
 * ServiceApi数据实体[PSSysReqModule] 查询条件对象
 */
@Slf4j
@Data
public class PSSysReqModuleSearchContext extends SearchContextBase {
	private String n_pssysreqmodulename_like;//[系统需求模块名称]

	private String n_usercat_eq;//[用户分类]

	private String n_pssysreqmoduleid_eq;//[系统需求模块标识]

	private String n_psmodulename_eq;//[系统模块]

	private String n_psmodulename_like;//[系统模块]

	private String n_ppssysreqmodulename_eq;//[父需求模块]

	private String n_ppssysreqmodulename_like;//[父需求模块]

	private String n_ppssysreqmoduleid_eq;//[父需求模块]

	private String n_psmoduleid_eq;//[系统模块]

}


