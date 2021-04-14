package cn.ibizlab.pms.core.ibizplugin.filter;

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
 * ServiceApi数据实体[IBIZProMessage] 查询条件对象
 */
@Slf4j
@Data
public class IBIZProMessageSearchContext extends SearchContextBase {
	private String n_sendproxyid_eq;//[发送代理标识]

	private String n_ibizpro_messagename_like;//[消息名称]

	private Integer n_islink_eq;//[是否是链接消息]

	private String n_ibizpro_messageid_eq;//[消息标识]

	private Integer n_issync_eq;//[是否同步]

	private Integer n_isretry_eq;//[是否重发]

	private String n_type_eq;//[消息类型]

}


