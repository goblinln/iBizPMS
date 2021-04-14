package cn.ibizlab.pms.core.ibizsysmodel.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.annotation.DEField;
import cn.ibizlab.pms.util.enums.DEPredefinedFieldType;
import cn.ibizlab.pms.util.enums.DEFieldDefaultValueType;
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.enums.DupCheck;
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [实体] 对象
 */
@Data
@ApiModel("实体")
public class PSDataEntity extends EntityClient implements Serializable {

    /**
     * 模型导入导出能力
     */
    @JSONField(name = "modelimpexpflag")
    @JsonProperty("modelimpexpflag")
    @ApiModelProperty("模型导入导出能力")
    private Integer modelimpexpflag;

    /**
     * 访问控制体系
     */
    @JSONField(name = "accctrlarch")
    @JsonProperty("accctrlarch")
    @ApiModelProperty("访问控制体系")
    private Integer accctrlarch;

    /**
     * 自定义参数
     */
    @JSONField(name = "userparams")
    @JsonProperty("userparams")
    @ApiModelProperty("自定义参数")
    private String userparams;

    /**
     * 全局禁止子系统导入
     */
    @JSONField(name = "delockflag")
    @JsonProperty("delockflag")
    @ApiModelProperty("全局禁止子系统导入")
    private Integer delockflag;

    /**
     * 模型锁模式
     */
    @JSONField(name = "lockflag")
    @JsonProperty("lockflag")
    @ApiModelProperty("模型锁模式")
    private Integer lockflag;

    /**
     * 级别3视图名称
     */
    @JSONField(name = "viewname3")
    @JsonProperty("viewname3")
    @ApiModelProperty("级别3视图名称")
    private String viewname3;

    /**
     * 实体标记2
     */
    @JSONField(name = "detag2")
    @JsonProperty("detag2")
    @ApiModelProperty("实体标记2")
    private String detag2;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 服务API模式
     */
    @JSONField(name = "serviceapiflag")
    @JsonProperty("serviceapiflag")
    @ApiModelProperty("服务API模式")
    private Integer serviceapiflag;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 启用数据版本
     */
    @JSONField(name = "enabledataver")
    @JsonProperty("enabledataver")
    @ApiModelProperty("启用数据版本")
    private Integer enabledataver;

    /**
     * 启用多表单
     */
    @JSONField(name = "enamultiform")
    @JsonProperty("enamultiform")
    @ApiModelProperty("启用多表单")
    private Integer enamultiform;

    /**
     * 级别2视图名称
     */
    @JSONField(name = "viewname2")
    @JsonProperty("viewname2")
    @ApiModelProperty("级别2视图名称")
    private String viewname2;

    /**
     * 默认测试单元
     */
    @JSONField(name = "testcaseflag")
    @JsonProperty("testcaseflag")
    @ApiModelProperty("默认测试单元")
    private Integer testcaseflag;

    /**
     * 实体编号
     */
    @JSONField(name = "desn")
    @JsonProperty("desn")
    @ApiModelProperty("实体编号")
    private String desn;

    /**
     * 数据导入导出能力
     */
    @JSONField(name = "dataimpexpflag")
    @JsonProperty("dataimpexpflag")
    @ApiModelProperty("数据导入导出能力")
    private Integer dataimpexpflag;

    /**
     * 最大缓存数据对象数
     */
    @JSONField(name = "maxentitycachecnt")
    @JsonProperty("maxentitycachecnt")
    @ApiModelProperty("最大缓存数据对象数")
    private Integer maxentitycachecnt;

    /**
     * 数据变更日志类型
     */
    @JSONField(name = "datachglogmode")
    @JsonProperty("datachglogmode")
    @ApiModelProperty("数据变更日志类型")
    private Integer datachglogmode;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 系统实体
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "systemflag")
    @JsonProperty("systemflag")
    @ApiModelProperty("系统实体")
    private Integer systemflag;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @ApiModelProperty("系统")
    private String pssystemid;

    /**
     * 启用操作者名称模型
     */
    @JSONField(name = "enableopnamemodel")
    @JsonProperty("enableopnamemodel")
    @ApiModelProperty("启用操作者名称模型")
    private Integer enableopnamemodel;

    /**
     * 扩展模式
     */
    @JSONField(name = "dynamicmode")
    @JsonProperty("dynamicmode")
    @ApiModelProperty("扩展模式")
    private Integer dynamicmode;

    /**
     * 支持多数据源
     */
    @JSONField(name = "enablemultids")
    @JsonProperty("enablemultids")
    @ApiModelProperty("支持多数据源")
    private Integer enablemultids;

    /**
     * 索引类型
     */
    @JSONField(name = "indexdetype")
    @JsonProperty("indexdetype")
    @ApiModelProperty("索引类型")
    private String indexdetype;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 是否启用
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    @ApiModelProperty("是否启用")
    private Integer validflag;

    /**
     * TODO
     */
    @JSONField(name = "todotask")
    @JsonProperty("todotask")
    @ApiModelProperty("TODO")
    private String todotask;

    /**
     * 支持移动端
     */
    @JSONField(name = "enablemob")
    @JsonProperty("enablemob")
    @ApiModelProperty("支持移动端")
    private Integer enablemob;

    /**
     * 支持获取结果集
     */
    @JSONField(name = "enablededataset")
    @JsonProperty("enablededataset")
    @ApiModelProperty("支持获取结果集")
    private Integer enablededataset;

    /**
     * 启用数据对象缓存
     */
    @JSONField(name = "enableentitycache")
    @JsonProperty("enableentitycache")
    @ApiModelProperty("启用数据对象缓存")
    private Integer enableentitycache;

    /**
     * 存储模式
     */
    @JSONField(name = "storagemode")
    @JsonProperty("storagemode")
    @ApiModelProperty("存储模式")
    private Integer storagemode;

    /**
     * 启用逻辑有效
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "logicvalid")
    @JsonProperty("logicvalid")
    @ApiModelProperty("启用逻辑有效")
    private Integer logicvalid;

    /**
     * 启用工作流模型
     */
    @JSONField(name = "enablewfmodel")
    @JsonProperty("enablewfmodel")
    @ApiModelProperty("启用工作流模型")
    private Integer enablewfmodel;

    /**
     * 实体类型
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "detype")
    @JsonProperty("detype")
    @ApiModelProperty("实体类型")
    private Integer detype;

    /**
     * SaaS构型
     */
    @JSONField(name = "saasmode")
    @JsonProperty("saasmode")
    @ApiModelProperty("SaaS构型")
    private Integer saasmode;

    /**
     * 实体名称
     */
    @JSONField(name = "psdataentityname")
    @JsonProperty("psdataentityname")
    @ApiModelProperty("实体名称")
    private String psdataentityname;

    /**
     * 视图名称
     */
    @JSONField(name = "viewname")
    @JsonProperty("viewname")
    @ApiModelProperty("视图名称")
    private String viewname;

    /**
     * 实体标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "psdataentityid")
    @JsonProperty("psdataentityid")
    @ApiModelProperty("实体标识")
    private String psdataentityid;

    /**
     * 预置业务功能模式
     */
    @JSONField(name = "biztag")
    @JsonProperty("biztag")
    @ApiModelProperty("预置业务功能模式")
    private String biztag;

    /**
     * 数据库表空间
     */
    @JSONField(name = "dbtabspace")
    @JsonProperty("dbtabspace")
    @ApiModelProperty("数据库表空间")
    private String dbtabspace;

    /**
     * 主键规则
     */
    @JSONField(name = "keyrule")
    @JsonProperty("keyrule")
    @ApiModelProperty("主键规则")
    private String keyrule;

    /**
     * 默认用户界面行为
     */
    @JSONField(name = "useraction")
    @JsonProperty("useraction")
    @ApiModelProperty("默认用户界面行为")
    private Integer useraction;

    /**
     * 子系统实体
     */
    @JSONField(name = "subsysde")
    @JsonProperty("subsysde")
    @ApiModelProperty("子系统实体")
    private Integer subsysde;

    /**
     * 服务代码名称
     */
    @JSONField(name = "servicecodename")
    @JsonProperty("servicecodename")
    @ApiModelProperty("服务代码名称")
    private String servicecodename;

    /**
     * 默认发布行为服务
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "svrpubmode")
    @JsonProperty("svrpubmode")
    @ApiModelProperty("默认发布行为服务")
    private Integer svrpubmode;

    /**
     * 无视图模式
     */
    @JSONField(name = "noviewmode")
    @JsonProperty("noviewmode")
    @ApiModelProperty("无视图模式")
    private Integer noviewmode;

    /**
     * 启用数据审计
     */
    @JSONField(name = "enableaudit")
    @JsonProperty("enableaudit")
    @ApiModelProperty("启用数据审计")
    private Integer enableaudit;

    /**
     * 默认数据源
     */
    @JSONField(name = "dslink")
    @JsonProperty("dslink")
    @ApiModelProperty("默认数据源")
    private String dslink;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @ApiModelProperty("系统")
    private String pssystemname;

    /**
     * 多视图级别
     */
    @JSONField(name = "viewlevel")
    @JsonProperty("viewlevel")
    @ApiModelProperty("多视图级别")
    private Integer viewlevel;

    /**
     * 虚拟实体
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "virtualflag")
    @JsonProperty("virtualflag")
    @ApiModelProperty("虚拟实体")
    private Integer virtualflag;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 启用临时数据
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "enatempdata")
    @JsonProperty("enatempdata")
    @ApiModelProperty("启用临时数据")
    private Integer enatempdata;

    /**
     * 逻辑无效值
     */
    @JSONField(name = "logicinvalidvalue")
    @JsonProperty("logicinvalidvalue")
    @ApiModelProperty("逻辑无效值")
    private String logicinvalidvalue;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 虚拟主键分隔符
     */
    @JSONField(name = "vkeyseparator")
    @JsonProperty("vkeyseparator")
    @ApiModelProperty("虚拟主键分隔符")
    private String vkeyseparator;

    /**
     * 逻辑有效值
     */
    @JSONField(name = "logicvalidvalue")
    @JsonProperty("logicvalidvalue")
    @ApiModelProperty("逻辑有效值")
    private String logicvalidvalue;

    /**
     * 删除模式
     */
    @JSONField(name = "removeflag")
    @JsonProperty("removeflag")
    @ApiModelProperty("删除模式")
    private Integer removeflag;

    /**
     * 实体标记
     */
    @JSONField(name = "detag")
    @JsonProperty("detag")
    @ApiModelProperty("实体标记")
    private String detag;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    @ApiModelProperty("代码名称")
    private String codename;

    /**
     * 启用DA日志
     */
    @JSONField(name = "enabledalog")
    @JsonProperty("enabledalog")
    @ApiModelProperty("启用DA日志")
    private Integer enabledalog;

    /**
     * 动态模型类型
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "dynamodelflag")
    @JsonProperty("dynamodelflag")
    @ApiModelProperty("动态模型类型")
    private Integer dynamodelflag;

    /**
     * 数据对象缓存时长
     */
    @JSONField(name = "entitycachetimeout")
    @JsonProperty("entitycachetimeout")
    @ApiModelProperty("数据对象缓存时长")
    private Integer entitycachetimeout;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 支持实体行为
     */
    @JSONField(name = "enabledeaction")
    @JsonProperty("enabledeaction")
    @ApiModelProperty("支持实体行为")
    private Integer enabledeaction;

    /**
     * 支持简单查询
     */
    @JSONField(name = "enableselect")
    @JsonProperty("enableselect")
    @ApiModelProperty("支持简单查询")
    private Integer enableselect;

    /**
     * 实体类别
     */
    @DEField(defaultValue = "DEFAULT")
    @JSONField(name = "decat")
    @JsonProperty("decat")
    @ApiModelProperty("实体类别")
    private String decat;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 启用组织模型
     */
    @JSONField(name = "enableorgmodel")
    @JsonProperty("enableorgmodel")
    @ApiModelProperty("启用组织模型")
    private Integer enableorgmodel;

    /**
     * 现有数据结构
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "existingmodel")
    @JsonProperty("existingmodel")
    @ApiModelProperty("现有数据结构")
    private Integer existingmodel;

    /**
     * 模型状态
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "modelstate")
    @JsonProperty("modelstate")
    @ApiModelProperty("模型状态")
    private Integer modelstate;

    /**
     * 显示颜色
     */
    @JSONField(name = "color")
    @JsonProperty("color")
    @ApiModelProperty("显示颜色")
    private String color;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 审计模式
     */
    @JSONField(name = "auditmode")
    @JsonProperty("auditmode")
    @ApiModelProperty("审计模式")
    private Integer auditmode;

    /**
     * 主表名称
     */
    @JSONField(name = "tablename")
    @JsonProperty("tablename")
    @ApiModelProperty("主表名称")
    private String tablename;

    /**
     * 用户表
     */
    @JSONField(name = "extablename")
    @JsonProperty("extablename")
    @ApiModelProperty("用户表")
    private String extablename;

    /**
     * 级别4视图名称
     */
    @JSONField(name = "viewname4")
    @JsonProperty("viewname4")
    @ApiModelProperty("级别4视图名称")
    private String viewname4;

    /**
     * 基类参数
     */
    @JSONField(name = "baseclsparams")
    @JsonProperty("baseclsparams")
    @ApiModelProperty("基类参数")
    private String baseclsparams;

    /**
     * 数据访问控制方式
     */
    @JSONField(name = "dataaccmode")
    @JsonProperty("dataaccmode")
    @ApiModelProperty("数据访问控制方式")
    private Integer dataaccmode;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 中文名称
     */
    @JSONField(name = "logicname")
    @JsonProperty("logicname")
    @ApiModelProperty("中文名称")
    private String logicname;

    /**
     * 子系统服务接口
     */
    @JSONField(name = "pssubsysserviceapiname")
    @JsonProperty("pssubsysserviceapiname")
    @ApiModelProperty("子系统服务接口")
    private String pssubsysserviceapiname;

    /**
     * 模块颜色
     */
    @JSONField(name = "modcolor")
    @JsonProperty("modcolor")
    @ApiModelProperty("模块颜色")
    private String modcolor;

    /**
     * 子系统接口实体
     */
    @JSONField(name = "pssubsyssadename")
    @JsonProperty("pssubsyssadename")
    @ApiModelProperty("子系统接口实体")
    private String pssubsyssadename;

    /**
     * 系统模块
     */
    @JSONField(name = "psmodulename")
    @JsonProperty("psmodulename")
    @ApiModelProperty("系统模块")
    private String psmodulename;

    /**
     * 子系统实体
     */
    @JSONField(name = "subsysmodule")
    @JsonProperty("subsysmodule")
    @ApiModelProperty("子系统实体")
    private Integer subsysmodule;

    /**
     * 系统设计需求
     */
    @JSONField(name = "pssysreqitemname")
    @JsonProperty("pssysreqitemname")
    @ApiModelProperty("系统设计需求")
    private String pssysreqitemname;

    /**
     * 系统设计需求
     */
    @JSONField(name = "pssysreqitemid")
    @JsonProperty("pssysreqitemid")
    @ApiModelProperty("系统设计需求")
    private String pssysreqitemid;

    /**
     * 子系统服务接口
     */
    @JSONField(name = "pssubsysserviceapiid")
    @JsonProperty("pssubsysserviceapiid")
    @ApiModelProperty("子系统服务接口")
    private String pssubsysserviceapiid;

    /**
     * 系统模块
     */
    @JSONField(name = "psmoduleid")
    @JsonProperty("psmoduleid")
    @ApiModelProperty("系统模块")
    private String psmoduleid;

    /**
     * 子系统接口实体
     */
    @JSONField(name = "pssubsyssadeid")
    @JsonProperty("pssubsyssadeid")
    @ApiModelProperty("子系统接口实体")
    private String pssubsyssadeid;


    /**
     * 
     */
    @JSONField(name = "psmodule")
    @JsonProperty("psmodule")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule psmodule;

    /**
     * 
     */
    @JSONField(name = "pssubsyssade")
    @JsonProperty("pssubsyssade")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysSADE pssubsyssade;

    /**
     * 
     */
    @JSONField(name = "pssubsysserviceapi")
    @JsonProperty("pssubsysserviceapi")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysServiceAPI pssubsysserviceapi;

    /**
     * 
     */
    @JSONField(name = "pssysreqitem")
    @JsonProperty("pssysreqitem")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqItem pssysreqitem;




    /**
     * 设置 [模型导入导出能力]
     */
    public void setModelimpexpflag(Integer modelimpexpflag) {
        this.modelimpexpflag = modelimpexpflag ;
        this.modify("modelimpexpflag",modelimpexpflag);
    }

    /**
     * 设置 [访问控制体系]
     */
    public void setAccctrlarch(Integer accctrlarch) {
        this.accctrlarch = accctrlarch ;
        this.modify("accctrlarch",accctrlarch);
    }

    /**
     * 设置 [自定义参数]
     */
    public void setUserparams(String userparams) {
        this.userparams = userparams ;
        this.modify("userparams",userparams);
    }

    /**
     * 设置 [全局禁止子系统导入]
     */
    public void setDelockflag(Integer delockflag) {
        this.delockflag = delockflag ;
        this.modify("delockflag",delockflag);
    }

    /**
     * 设置 [模型锁模式]
     */
    public void setLockflag(Integer lockflag) {
        this.lockflag = lockflag ;
        this.modify("lockflag",lockflag);
    }

    /**
     * 设置 [级别3视图名称]
     */
    public void setViewname3(String viewname3) {
        this.viewname3 = viewname3 ;
        this.modify("viewname3",viewname3);
    }

    /**
     * 设置 [实体标记2]
     */
    public void setDetag2(String detag2) {
        this.detag2 = detag2 ;
        this.modify("detag2",detag2);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [服务API模式]
     */
    public void setServiceapiflag(Integer serviceapiflag) {
        this.serviceapiflag = serviceapiflag ;
        this.modify("serviceapiflag",serviceapiflag);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [启用数据版本]
     */
    public void setEnabledataver(Integer enabledataver) {
        this.enabledataver = enabledataver ;
        this.modify("enabledataver",enabledataver);
    }

    /**
     * 设置 [启用多表单]
     */
    public void setEnamultiform(Integer enamultiform) {
        this.enamultiform = enamultiform ;
        this.modify("enamultiform",enamultiform);
    }

    /**
     * 设置 [级别2视图名称]
     */
    public void setViewname2(String viewname2) {
        this.viewname2 = viewname2 ;
        this.modify("viewname2",viewname2);
    }

    /**
     * 设置 [默认测试单元]
     */
    public void setTestcaseflag(Integer testcaseflag) {
        this.testcaseflag = testcaseflag ;
        this.modify("testcaseflag",testcaseflag);
    }

    /**
     * 设置 [实体编号]
     */
    public void setDesn(String desn) {
        this.desn = desn ;
        this.modify("desn",desn);
    }

    /**
     * 设置 [数据导入导出能力]
     */
    public void setDataimpexpflag(Integer dataimpexpflag) {
        this.dataimpexpflag = dataimpexpflag ;
        this.modify("dataimpexpflag",dataimpexpflag);
    }

    /**
     * 设置 [最大缓存数据对象数]
     */
    public void setMaxentitycachecnt(Integer maxentitycachecnt) {
        this.maxentitycachecnt = maxentitycachecnt ;
        this.modify("maxentitycachecnt",maxentitycachecnt);
    }

    /**
     * 设置 [数据变更日志类型]
     */
    public void setDatachglogmode(Integer datachglogmode) {
        this.datachglogmode = datachglogmode ;
        this.modify("datachglogmode",datachglogmode);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [系统实体]
     */
    public void setSystemflag(Integer systemflag) {
        this.systemflag = systemflag ;
        this.modify("systemflag",systemflag);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [启用操作者名称模型]
     */
    public void setEnableopnamemodel(Integer enableopnamemodel) {
        this.enableopnamemodel = enableopnamemodel ;
        this.modify("enableopnamemodel",enableopnamemodel);
    }

    /**
     * 设置 [扩展模式]
     */
    public void setDynamicmode(Integer dynamicmode) {
        this.dynamicmode = dynamicmode ;
        this.modify("dynamicmode",dynamicmode);
    }

    /**
     * 设置 [支持多数据源]
     */
    public void setEnablemultids(Integer enablemultids) {
        this.enablemultids = enablemultids ;
        this.modify("enablemultids",enablemultids);
    }

    /**
     * 设置 [索引类型]
     */
    public void setIndexdetype(String indexdetype) {
        this.indexdetype = indexdetype ;
        this.modify("indexdetype",indexdetype);
    }

    /**
     * 设置 [是否启用]
     */
    public void setValidflag(Integer validflag) {
        this.validflag = validflag ;
        this.modify("validflag",validflag);
    }

    /**
     * 设置 [TODO]
     */
    public void setTodotask(String todotask) {
        this.todotask = todotask ;
        this.modify("todotask",todotask);
    }

    /**
     * 设置 [支持移动端]
     */
    public void setEnablemob(Integer enablemob) {
        this.enablemob = enablemob ;
        this.modify("enablemob",enablemob);
    }

    /**
     * 设置 [支持获取结果集]
     */
    public void setEnablededataset(Integer enablededataset) {
        this.enablededataset = enablededataset ;
        this.modify("enablededataset",enablededataset);
    }

    /**
     * 设置 [启用数据对象缓存]
     */
    public void setEnableentitycache(Integer enableentitycache) {
        this.enableentitycache = enableentitycache ;
        this.modify("enableentitycache",enableentitycache);
    }

    /**
     * 设置 [存储模式]
     */
    public void setStoragemode(Integer storagemode) {
        this.storagemode = storagemode ;
        this.modify("storagemode",storagemode);
    }

    /**
     * 设置 [启用逻辑有效]
     */
    public void setLogicvalid(Integer logicvalid) {
        this.logicvalid = logicvalid ;
        this.modify("logicvalid",logicvalid);
    }

    /**
     * 设置 [启用工作流模型]
     */
    public void setEnablewfmodel(Integer enablewfmodel) {
        this.enablewfmodel = enablewfmodel ;
        this.modify("enablewfmodel",enablewfmodel);
    }

    /**
     * 设置 [实体类型]
     */
    public void setDetype(Integer detype) {
        this.detype = detype ;
        this.modify("detype",detype);
    }

    /**
     * 设置 [SaaS构型]
     */
    public void setSaasmode(Integer saasmode) {
        this.saasmode = saasmode ;
        this.modify("saasmode",saasmode);
    }

    /**
     * 设置 [实体名称]
     */
    public void setPsdataentityname(String psdataentityname) {
        this.psdataentityname = psdataentityname ;
        this.modify("psdataentityname",psdataentityname);
    }

    /**
     * 设置 [视图名称]
     */
    public void setViewname(String viewname) {
        this.viewname = viewname ;
        this.modify("viewname",viewname);
    }

    /**
     * 设置 [预置业务功能模式]
     */
    public void setBiztag(String biztag) {
        this.biztag = biztag ;
        this.modify("biztag",biztag);
    }

    /**
     * 设置 [数据库表空间]
     */
    public void setDbtabspace(String dbtabspace) {
        this.dbtabspace = dbtabspace ;
        this.modify("dbtabspace",dbtabspace);
    }

    /**
     * 设置 [主键规则]
     */
    public void setKeyrule(String keyrule) {
        this.keyrule = keyrule ;
        this.modify("keyrule",keyrule);
    }

    /**
     * 设置 [默认用户界面行为]
     */
    public void setUseraction(Integer useraction) {
        this.useraction = useraction ;
        this.modify("useraction",useraction);
    }

    /**
     * 设置 [子系统实体]
     */
    public void setSubsysde(Integer subsysde) {
        this.subsysde = subsysde ;
        this.modify("subsysde",subsysde);
    }

    /**
     * 设置 [服务代码名称]
     */
    public void setServicecodename(String servicecodename) {
        this.servicecodename = servicecodename ;
        this.modify("servicecodename",servicecodename);
    }

    /**
     * 设置 [默认发布行为服务]
     */
    public void setSvrpubmode(Integer svrpubmode) {
        this.svrpubmode = svrpubmode ;
        this.modify("svrpubmode",svrpubmode);
    }

    /**
     * 设置 [无视图模式]
     */
    public void setNoviewmode(Integer noviewmode) {
        this.noviewmode = noviewmode ;
        this.modify("noviewmode",noviewmode);
    }

    /**
     * 设置 [启用数据审计]
     */
    public void setEnableaudit(Integer enableaudit) {
        this.enableaudit = enableaudit ;
        this.modify("enableaudit",enableaudit);
    }

    /**
     * 设置 [默认数据源]
     */
    public void setDslink(String dslink) {
        this.dslink = dslink ;
        this.modify("dslink",dslink);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [多视图级别]
     */
    public void setViewlevel(Integer viewlevel) {
        this.viewlevel = viewlevel ;
        this.modify("viewlevel",viewlevel);
    }

    /**
     * 设置 [虚拟实体]
     */
    public void setVirtualflag(Integer virtualflag) {
        this.virtualflag = virtualflag ;
        this.modify("virtualflag",virtualflag);
    }

    /**
     * 设置 [启用临时数据]
     */
    public void setEnatempdata(Integer enatempdata) {
        this.enatempdata = enatempdata ;
        this.modify("enatempdata",enatempdata);
    }

    /**
     * 设置 [逻辑无效值]
     */
    public void setLogicinvalidvalue(String logicinvalidvalue) {
        this.logicinvalidvalue = logicinvalidvalue ;
        this.modify("logicinvalidvalue",logicinvalidvalue);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [虚拟主键分隔符]
     */
    public void setVkeyseparator(String vkeyseparator) {
        this.vkeyseparator = vkeyseparator ;
        this.modify("vkeyseparator",vkeyseparator);
    }

    /**
     * 设置 [逻辑有效值]
     */
    public void setLogicvalidvalue(String logicvalidvalue) {
        this.logicvalidvalue = logicvalidvalue ;
        this.modify("logicvalidvalue",logicvalidvalue);
    }

    /**
     * 设置 [删除模式]
     */
    public void setRemoveflag(Integer removeflag) {
        this.removeflag = removeflag ;
        this.modify("removeflag",removeflag);
    }

    /**
     * 设置 [实体标记]
     */
    public void setDetag(String detag) {
        this.detag = detag ;
        this.modify("detag",detag);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [启用DA日志]
     */
    public void setEnabledalog(Integer enabledalog) {
        this.enabledalog = enabledalog ;
        this.modify("enabledalog",enabledalog);
    }

    /**
     * 设置 [动态模型类型]
     */
    public void setDynamodelflag(Integer dynamodelflag) {
        this.dynamodelflag = dynamodelflag ;
        this.modify("dynamodelflag",dynamodelflag);
    }

    /**
     * 设置 [数据对象缓存时长]
     */
    public void setEntitycachetimeout(Integer entitycachetimeout) {
        this.entitycachetimeout = entitycachetimeout ;
        this.modify("entitycachetimeout",entitycachetimeout);
    }

    /**
     * 设置 [支持实体行为]
     */
    public void setEnabledeaction(Integer enabledeaction) {
        this.enabledeaction = enabledeaction ;
        this.modify("enabledeaction",enabledeaction);
    }

    /**
     * 设置 [支持简单查询]
     */
    public void setEnableselect(Integer enableselect) {
        this.enableselect = enableselect ;
        this.modify("enableselect",enableselect);
    }

    /**
     * 设置 [实体类别]
     */
    public void setDecat(String decat) {
        this.decat = decat ;
        this.modify("decat",decat);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [启用组织模型]
     */
    public void setEnableorgmodel(Integer enableorgmodel) {
        this.enableorgmodel = enableorgmodel ;
        this.modify("enableorgmodel",enableorgmodel);
    }

    /**
     * 设置 [现有数据结构]
     */
    public void setExistingmodel(Integer existingmodel) {
        this.existingmodel = existingmodel ;
        this.modify("existingmodel",existingmodel);
    }

    /**
     * 设置 [模型状态]
     */
    public void setModelstate(Integer modelstate) {
        this.modelstate = modelstate ;
        this.modify("modelstate",modelstate);
    }

    /**
     * 设置 [显示颜色]
     */
    public void setColor(String color) {
        this.color = color ;
        this.modify("color",color);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [审计模式]
     */
    public void setAuditmode(Integer auditmode) {
        this.auditmode = auditmode ;
        this.modify("auditmode",auditmode);
    }

    /**
     * 设置 [主表名称]
     */
    public void setTablename(String tablename) {
        this.tablename = tablename ;
        this.modify("tablename",tablename);
    }

    /**
     * 设置 [用户表]
     */
    public void setExtablename(String extablename) {
        this.extablename = extablename ;
        this.modify("extablename",extablename);
    }

    /**
     * 设置 [级别4视图名称]
     */
    public void setViewname4(String viewname4) {
        this.viewname4 = viewname4 ;
        this.modify("viewname4",viewname4);
    }

    /**
     * 设置 [基类参数]
     */
    public void setBaseclsparams(String baseclsparams) {
        this.baseclsparams = baseclsparams ;
        this.modify("baseclsparams",baseclsparams);
    }

    /**
     * 设置 [数据访问控制方式]
     */
    public void setDataaccmode(Integer dataaccmode) {
        this.dataaccmode = dataaccmode ;
        this.modify("dataaccmode",dataaccmode);
    }

    /**
     * 设置 [中文名称]
     */
    public void setLogicname(String logicname) {
        this.logicname = logicname ;
        this.modify("logicname",logicname);
    }

    /**
     * 设置 [系统设计需求]
     */
    public void setPssysreqitemid(String pssysreqitemid) {
        this.pssysreqitemid = pssysreqitemid ;
        this.modify("pssysreqitemid",pssysreqitemid);
    }

    /**
     * 设置 [子系统服务接口]
     */
    public void setPssubsysserviceapiid(String pssubsysserviceapiid) {
        this.pssubsysserviceapiid = pssubsysserviceapiid ;
        this.modify("pssubsysserviceapiid",pssubsysserviceapiid);
    }

    /**
     * 设置 [系统模块]
     */
    public void setPsmoduleid(String psmoduleid) {
        this.psmoduleid = psmoduleid ;
        this.modify("psmoduleid",psmoduleid);
    }

    /**
     * 设置 [子系统接口实体]
     */
    public void setPssubsyssadeid(String pssubsyssadeid) {
        this.pssubsyssadeid = pssubsyssadeid ;
        this.modify("pssubsyssadeid",pssubsyssadeid);
    }

    /**
     * 复制当前对象数据到目标对象(粘贴重置)
     * @param targetEntity 目标数据对象
     * @param bIncEmpty  是否包括空值
     * @param <T>
     * @return
     */
    @Override
    public <T> T copyTo(T targetEntity, boolean bIncEmpty) {
        this.reset("psdataentityid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


