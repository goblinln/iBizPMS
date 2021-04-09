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
 * ServiceApi数据实体[PSSysApp] 查询条件对象
 */
@Slf4j
@Data
public class PSSysAppSearchContext extends SearchContextBase {
	private String n_mainmenuside_eq;//[视图主菜单方向]

	private Integer n_finoprivdm_eq;//[表单项无权限显示模式]

	private String n_orientationmode_eq;//[移动端方向设置]

	private Integer n_removeflag_eq;//[删除模式]

	private Integer n_gridcolenablelink_eq;//[表格列启用链接]

	private Integer n_gridrowactivemode_eq;//[表格行激活模式]

	private Integer n_btnnoprivdm_eq;//[按钮无权限显示模式]

	private String n_appmode_eq;//[应用模式]

	private String n_pssysappname_like;//[系统应用名称]

	private String n_usercat_eq;//[用户分类]

	private String n_uistyle_eq;//[内建界面式样]

	private Integer n_gcnoprivdm_eq;//[表格列无权限显示模式]

	private String n_pssysserviceapiname_eq;//[默认服务接口]

	private String n_pssysserviceapiname_like;//[默认服务接口]

	private String n_pssysserviceapiid_eq;//[默认服务接口]

}


