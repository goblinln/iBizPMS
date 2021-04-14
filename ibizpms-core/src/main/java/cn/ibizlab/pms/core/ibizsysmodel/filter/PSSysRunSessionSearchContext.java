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
 * ServiceApi数据实体[PSSysRunSession] 查询条件对象
 */
@Slf4j
@Data
public class PSSysRunSessionSearchContext extends SearchContextBase {
	private String n_pssysrunsessionname_like;//[系统运行会话名称]

	private String n_packmode_eq;//[打包模式]

	private String n_runmode_eq;//[运行模式]

	private Integer n_runstate_eq;//[运行状态]

	private Integer n_rebuildmode_eq;//[重新构建]

	private String n_pssysappname2_eq;//[系统应用2]

	private String n_pssysappname2_like;//[系统应用2]

	private String n_pssysserviceapiname_eq;//[系统服务接口]

	private String n_pssysserviceapiname_like;//[系统服务接口]

	private String n_pssysappname_eq;//[系统应用]

	private String n_pssysappname_like;//[系统应用]

	private String n_pssysappid2_eq;//[系统应用2]

	private String n_pssysserviceapiid_eq;//[系统服务接口]

	private String n_pssysappid_eq;//[系统应用]

}


