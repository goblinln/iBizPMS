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
 * ServiceApi数据实体[PSSysSFPub] 查询条件对象
 */
@Slf4j
@Data
public class PSSysSFPubSearchContext extends SearchContextBase {
	private Integer n_removeflag_eq;//[删除模式]

	private String n_pssyssfpubname_like;//[后台服务架构名称]

	private String n_pssyssfpubid_eq;//[后台服务架构标识]

	private String n_contenttype_eq;//[发布内容类型]

	private String n_usercat_eq;//[用户分类]

	private String n_ppssyssfpubname_eq;//[父后台服务体系]

	private String n_ppssyssfpubname_like;//[父后台服务体系]

	private String n_ppssyssfpubid_eq;//[父后台服务体系]

}


