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
 * ServiceApi数据实体[PSSysReqItem] 查询条件对象
 */
@Slf4j
@Data
public class PSSysReqItemSearchContext extends SearchContextBase {
	private String n_itemtype_eq;//[项类型]

	private String n_pssysreqitemid_eq;//[系统需求项标识]

	private String n_usercat_eq;//[用户分类]

	private String n_pssysreqitemname_like;//[系统需求项名称]

	private String n_pssysreqmodulename_eq;//[需求模块]

	private String n_pssysreqmodulename_like;//[需求模块]

	private String n_ppssysreqitemname_eq;//[父需求项]

	private String n_ppssysreqitemname_like;//[父需求项]

	private String n_pssysreqmoduleid_eq;//[需求模块]

	private String n_ppssysreqitemid_eq;//[父需求项]

}


