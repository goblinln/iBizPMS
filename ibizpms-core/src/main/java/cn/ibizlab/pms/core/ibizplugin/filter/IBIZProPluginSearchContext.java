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
 * ServiceApi数据实体[IBIZProPlugin] 查询条件对象
 */
@Slf4j
@Data
public class IBIZProPluginSearchContext extends SearchContextBase {
	private String n_type_eq;//[类型]

	private String n_ibizpro_pluginname_like;//[系统插件名称]

}


