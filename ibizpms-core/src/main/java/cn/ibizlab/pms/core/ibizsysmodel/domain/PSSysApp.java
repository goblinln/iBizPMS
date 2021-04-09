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
 * ServiceApi [系统应用] 对象
 */
@Data
@ApiModel("系统应用")
public class PSSysApp extends EntityClient implements Serializable {
    @Override
    public void modify(String field, Object val) {
        getExtensionparams().put("dirtyflagenable", true);
        super.modify(field, val);
    }

    /**
     * 启用统一认证
     */
    @JSONField(name = "uaclogin")
    @JsonProperty("uaclogin")
    @ApiModelProperty("启用统一认证")
    private Integer uaclogin;

    /**
     * 前台技术架构
     */
    @JSONField(name = "pspfid")
    @JsonProperty("pspfid")
    @ApiModelProperty("前台技术架构")
    private String pspfid;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 自动添加应用视图
     */
    @JSONField(name = "autoaddappview")
    @JsonProperty("autoaddappview")
    @ApiModelProperty("自动添加应用视图")
    private Integer autoaddappview;

    /**
     * 启用本地服务
     */
    @JSONField(name = "enalocalservice")
    @JsonProperty("enalocalservice")
    @ApiModelProperty("启用本地服务")
    private Integer enalocalservice;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @ApiModelProperty("系统")
    private String pssystemid;

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
     * 只发布系统引用视图（废弃）
     */
    @JSONField(name = "pubsysrefviewonly")
    @JsonProperty("pubsysrefviewonly")
    @ApiModelProperty("只发布系统引用视图（废弃）")
    private Integer pubsysrefviewonly;

    /**
     * 视图主菜单方向
     */
    @JSONField(name = "mainmenuside")
    @JsonProperty("mainmenuside")
    @ApiModelProperty("视图主菜单方向")
    private String mainmenuside;

    /**
     * 系统应用标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssysappid")
    @JsonProperty("pssysappid")
    @ApiModelProperty("系统应用标识")
    private String pssysappid;

    /**
     * 代码目录
     */
    @JSONField(name = "codefolder")
    @JsonProperty("codefolder")
    @ApiModelProperty("代码目录")
    private String codefolder;

    /**
     * 表格适应屏宽
     */
    @JSONField(name = "gridforcefit")
    @JsonProperty("gridforcefit")
    @ApiModelProperty("表格适应屏宽")
    private Integer gridforcefit;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 表单项无权限显示模式
     */
    @JSONField(name = "finoprivdm")
    @JsonProperty("finoprivdm")
    @ApiModelProperty("表单项无权限显示模式")
    private Integer finoprivdm;

    /**
     * 自定义参数
     */
    @JSONField(name = "userparams")
    @JsonProperty("userparams")
    @ApiModelProperty("自定义参数")
    private String userparams;

    /**
     * 移动端方向设置
     */
    @JSONField(name = "orientationmode")
    @JsonProperty("orientationmode")
    @ApiModelProperty("移动端方向设置")
    private String orientationmode;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 删除模式
     */
    @JSONField(name = "removeflag")
    @JsonProperty("removeflag")
    @ApiModelProperty("删除模式")
    private Integer removeflag;

    /**
     * 启用故事板
     */
    @JSONField(name = "enablestoryboard")
    @JsonProperty("enablestoryboard")
    @ApiModelProperty("启用故事板")
    private Integer enablestoryboard;

    /**
     * 应用主题
     */
    @JSONField(name = "psstudiothemename")
    @JsonProperty("psstudiothemename")
    @ApiModelProperty("应用主题")
    private String psstudiothemename;

    /**
     * 支持动态系统
     */
    @JSONField(name = "enabledynasys")
    @JsonProperty("enabledynasys")
    @ApiModelProperty("支持动态系统")
    private Integer enabledynasys;

    /**
     * 是否启用
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    @ApiModelProperty("是否启用")
    private Integer validflag;

    /**
     * 图标文件
     */
    @JSONField(name = "iconfile")
    @JsonProperty("iconfile")
    @ApiModelProperty("图标文件")
    private String iconfile;

    /**
     * 应用标记
     */
    @JSONField(name = "apptag")
    @JsonProperty("apptag")
    @ApiModelProperty("应用标记")
    private String apptag;

    /**
     * 默认应用
     */
    @JSONField(name = "defaultpub")
    @JsonProperty("defaultpub")
    @ApiModelProperty("默认应用")
    private Integer defaultpub;

    /**
     * 应用标记2
     */
    @JSONField(name = "apptag2")
    @JsonProperty("apptag2")
    @ApiModelProperty("应用标记2")
    private String apptag2;

    /**
     * 服务代码名称
     */
    @JSONField(name = "servicecodename")
    @JsonProperty("servicecodename")
    @ApiModelProperty("服务代码名称")
    private String servicecodename;

    /**
     * 防止XSS攻击
     */
    @JSONField(name = "preventxss")
    @JsonProperty("preventxss")
    @ApiModelProperty("防止XSS攻击")
    private Integer preventxss;

    /**
     * 应用类型
     */
    @JSONField(name = "psapptypename")
    @JsonProperty("psapptypename")
    @ApiModelProperty("应用类型")
    private String psapptypename;

    /**
     * 应用CDN
     */
    @JSONField(name = "pspfcdnid")
    @JsonProperty("pspfcdnid")
    @ApiModelProperty("应用CDN")
    private String pspfcdnid;

    /**
     * 表格列启用链接
     */
    @JSONField(name = "gridcolenablelink")
    @JsonProperty("gridcolenablelink")
    @ApiModelProperty("表格列启用链接")
    private Integer gridcolenablelink;

    /**
     * 应用主题
     */
    @JSONField(name = "psstudiothemeid")
    @JsonProperty("psstudiothemeid")
    @ApiModelProperty("应用主题")
    private String psstudiothemeid;

    /**
     * 转换12列至24列布局
     */
    @JSONField(name = "enablec12toc24")
    @JsonProperty("enablec12toc24")
    @ApiModelProperty("转换12列至24列布局")
    private Integer enablec12toc24;

    /**
     * 应用编号
     */
    @JSONField(name = "appsn")
    @JsonProperty("appsn")
    @ApiModelProperty("应用编号")
    private String appsn;

    /**
     * 应用标记4
     */
    @JSONField(name = "apptag4")
    @JsonProperty("apptag4")
    @ApiModelProperty("应用标记4")
    private String apptag4;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 表格行激活模式
     */
    @JSONField(name = "gridrowactivemode")
    @JsonProperty("gridrowactivemode")
    @ApiModelProperty("表格行激活模式")
    private Integer gridrowactivemode;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 代码名称
     */
    @JSONField(name = "apppkgname")
    @JsonProperty("apppkgname")
    @ApiModelProperty("代码名称")
    private String apppkgname;

    /**
     * 中文名称
     */
    @JSONField(name = "logicname")
    @JsonProperty("logicname")
    @ApiModelProperty("中文名称")
    private String logicname;

    /**
     * 应用样式
     */
    @JSONField(name = "pspfstyleid")
    @JsonProperty("pspfstyleid")
    @ApiModelProperty("应用样式")
    private String pspfstyleid;

    /**
     * 按钮无权限显示模式
     */
    @JSONField(name = "btnnoprivdm")
    @JsonProperty("btnnoprivdm")
    @ApiModelProperty("按钮无权限显示模式")
    private Integer btnnoprivdm;

    /**
     * 应用CDN
     */
    @JSONField(name = "pspfcdnname")
    @JsonProperty("pspfcdnname")
    @ApiModelProperty("应用CDN")
    private String pspfcdnname;

    /**
     * 只发布引用视图
     */
    @JSONField(name = "pubrefviewonly")
    @JsonProperty("pubrefviewonly")
    @ApiModelProperty("只发布引用视图")
    private Integer pubrefviewonly;

    /**
     * 应用模式
     */
    @JSONField(name = "appmode")
    @JsonProperty("appmode")
    @ApiModelProperty("应用模式")
    private String appmode;

    /**
     * 系统应用名称
     */
    @JSONField(name = "pssysappname")
    @JsonProperty("pssysappname")
    @ApiModelProperty("系统应用名称")
    private String pssysappname;

    /**
     * 应用类型
     */
    @JSONField(name = "psapptypeid")
    @JsonProperty("psapptypeid")
    @ApiModelProperty("应用类型")
    private String psapptypeid;

    /**
     * 应用目录
     */
    @JSONField(name = "appfolder")
    @JsonProperty("appfolder")
    @ApiModelProperty("应用目录")
    private String appfolder;

    /**
     * 起始页图片文件
     */
    @JSONField(name = "startpagefile")
    @JsonProperty("startpagefile")
    @ApiModelProperty("起始页图片文件")
    private String startpagefile;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 应用标记3
     */
    @JSONField(name = "apptag3")
    @JsonProperty("apptag3")
    @ApiModelProperty("应用标记3")
    private String apptag3;

    /**
     * 应用样式参数
     */
    @JSONField(name = "pfstyleparam")
    @JsonProperty("pfstyleparam")
    @ApiModelProperty("应用样式参数")
    private String pfstyleparam;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @ApiModelProperty("系统")
    private String pssystemname;

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
     * 内建界面式样
     */
    @JSONField(name = "uistyle")
    @JsonProperty("uistyle")
    @ApiModelProperty("内建界面式样")
    private String uistyle;

    /**
     * 表格列无权限显示模式
     */
    @JSONField(name = "gcnoprivdm")
    @JsonProperty("gcnoprivdm")
    @ApiModelProperty("表格列无权限显示模式")
    private Integer gcnoprivdm;

    /**
     * 输出表单项更新权限标记
     */
    @JSONField(name = "fiupdateprivtag")
    @JsonProperty("fiupdateprivtag")
    @ApiModelProperty("输出表单项更新权限标记")
    private Integer fiupdateprivtag;

    /**
     * 默认服务接口
     */
    @JSONField(name = "pssysserviceapiname")
    @JsonProperty("pssysserviceapiname")
    @ApiModelProperty("默认服务接口")
    private String pssysserviceapiname;

    /**
     * 默认服务接口
     */
    @JSONField(name = "pssysserviceapiid")
    @JsonProperty("pssysserviceapiid")
    @ApiModelProperty("默认服务接口")
    private String pssysserviceapiid;


    /**
     * 
     */
    @JSONField(name = "pssysserviceapi")
    @JsonProperty("pssysserviceapi")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI pssysserviceapi;




    /**
     * 设置 [启用统一认证]
     */
    public void setUaclogin(Integer uaclogin) {
        this.uaclogin = uaclogin ;
        this.modify("uaclogin",uaclogin);
    }

    /**
     * 设置 [前台技术架构]
     */
    public void setPspfid(String pspfid) {
        this.pspfid = pspfid ;
        this.modify("pspfid",pspfid);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [自动添加应用视图]
     */
    public void setAutoaddappview(Integer autoaddappview) {
        this.autoaddappview = autoaddappview ;
        this.modify("autoaddappview",autoaddappview);
    }

    /**
     * 设置 [启用本地服务]
     */
    public void setEnalocalservice(Integer enalocalservice) {
        this.enalocalservice = enalocalservice ;
        this.modify("enalocalservice",enalocalservice);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [只发布系统引用视图（废弃）]
     */
    public void setPubsysrefviewonly(Integer pubsysrefviewonly) {
        this.pubsysrefviewonly = pubsysrefviewonly ;
        this.modify("pubsysrefviewonly",pubsysrefviewonly);
    }

    /**
     * 设置 [视图主菜单方向]
     */
    public void setMainmenuside(String mainmenuside) {
        this.mainmenuside = mainmenuside ;
        this.modify("mainmenuside",mainmenuside);
    }

    /**
     * 设置 [代码目录]
     */
    public void setCodefolder(String codefolder) {
        this.codefolder = codefolder ;
        this.modify("codefolder",codefolder);
    }

    /**
     * 设置 [表格适应屏宽]
     */
    public void setGridforcefit(Integer gridforcefit) {
        this.gridforcefit = gridforcefit ;
        this.modify("gridforcefit",gridforcefit);
    }

    /**
     * 设置 [表单项无权限显示模式]
     */
    public void setFinoprivdm(Integer finoprivdm) {
        this.finoprivdm = finoprivdm ;
        this.modify("finoprivdm",finoprivdm);
    }

    /**
     * 设置 [自定义参数]
     */
    public void setUserparams(String userparams) {
        this.userparams = userparams ;
        this.modify("userparams",userparams);
    }

    /**
     * 设置 [移动端方向设置]
     */
    public void setOrientationmode(String orientationmode) {
        this.orientationmode = orientationmode ;
        this.modify("orientationmode",orientationmode);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [删除模式]
     */
    public void setRemoveflag(Integer removeflag) {
        this.removeflag = removeflag ;
        this.modify("removeflag",removeflag);
    }

    /**
     * 设置 [启用故事板]
     */
    public void setEnablestoryboard(Integer enablestoryboard) {
        this.enablestoryboard = enablestoryboard ;
        this.modify("enablestoryboard",enablestoryboard);
    }

    /**
     * 设置 [应用主题]
     */
    public void setPsstudiothemename(String psstudiothemename) {
        this.psstudiothemename = psstudiothemename ;
        this.modify("psstudiothemename",psstudiothemename);
    }

    /**
     * 设置 [支持动态系统]
     */
    public void setEnabledynasys(Integer enabledynasys) {
        this.enabledynasys = enabledynasys ;
        this.modify("enabledynasys",enabledynasys);
    }

    /**
     * 设置 [是否启用]
     */
    public void setValidflag(Integer validflag) {
        this.validflag = validflag ;
        this.modify("validflag",validflag);
    }

    /**
     * 设置 [图标文件]
     */
    public void setIconfile(String iconfile) {
        this.iconfile = iconfile ;
        this.modify("iconfile",iconfile);
    }

    /**
     * 设置 [应用标记]
     */
    public void setApptag(String apptag) {
        this.apptag = apptag ;
        this.modify("apptag",apptag);
    }

    /**
     * 设置 [默认应用]
     */
    public void setDefaultpub(Integer defaultpub) {
        this.defaultpub = defaultpub ;
        this.modify("defaultpub",defaultpub);
    }

    /**
     * 设置 [应用标记2]
     */
    public void setApptag2(String apptag2) {
        this.apptag2 = apptag2 ;
        this.modify("apptag2",apptag2);
    }

    /**
     * 设置 [服务代码名称]
     */
    public void setServicecodename(String servicecodename) {
        this.servicecodename = servicecodename ;
        this.modify("servicecodename",servicecodename);
    }

    /**
     * 设置 [防止XSS攻击]
     */
    public void setPreventxss(Integer preventxss) {
        this.preventxss = preventxss ;
        this.modify("preventxss",preventxss);
    }

    /**
     * 设置 [应用类型]
     */
    public void setPsapptypename(String psapptypename) {
        this.psapptypename = psapptypename ;
        this.modify("psapptypename",psapptypename);
    }

    /**
     * 设置 [应用CDN]
     */
    public void setPspfcdnid(String pspfcdnid) {
        this.pspfcdnid = pspfcdnid ;
        this.modify("pspfcdnid",pspfcdnid);
    }

    /**
     * 设置 [表格列启用链接]
     */
    public void setGridcolenablelink(Integer gridcolenablelink) {
        this.gridcolenablelink = gridcolenablelink ;
        this.modify("gridcolenablelink",gridcolenablelink);
    }

    /**
     * 设置 [应用主题]
     */
    public void setPsstudiothemeid(String psstudiothemeid) {
        this.psstudiothemeid = psstudiothemeid ;
        this.modify("psstudiothemeid",psstudiothemeid);
    }

    /**
     * 设置 [转换12列至24列布局]
     */
    public void setEnablec12toc24(Integer enablec12toc24) {
        this.enablec12toc24 = enablec12toc24 ;
        this.modify("enablec12toc24",enablec12toc24);
    }

    /**
     * 设置 [应用编号]
     */
    public void setAppsn(String appsn) {
        this.appsn = appsn ;
        this.modify("appsn",appsn);
    }

    /**
     * 设置 [应用标记4]
     */
    public void setApptag4(String apptag4) {
        this.apptag4 = apptag4 ;
        this.modify("apptag4",apptag4);
    }

    /**
     * 设置 [表格行激活模式]
     */
    public void setGridrowactivemode(Integer gridrowactivemode) {
        this.gridrowactivemode = gridrowactivemode ;
        this.modify("gridrowactivemode",gridrowactivemode);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [代码名称]
     */
    public void setApppkgname(String apppkgname) {
        this.apppkgname = apppkgname ;
        this.modify("apppkgname",apppkgname);
    }

    /**
     * 设置 [中文名称]
     */
    public void setLogicname(String logicname) {
        this.logicname = logicname ;
        this.modify("logicname",logicname);
    }

    /**
     * 设置 [应用样式]
     */
    public void setPspfstyleid(String pspfstyleid) {
        this.pspfstyleid = pspfstyleid ;
        this.modify("pspfstyleid",pspfstyleid);
    }

    /**
     * 设置 [按钮无权限显示模式]
     */
    public void setBtnnoprivdm(Integer btnnoprivdm) {
        this.btnnoprivdm = btnnoprivdm ;
        this.modify("btnnoprivdm",btnnoprivdm);
    }

    /**
     * 设置 [应用CDN]
     */
    public void setPspfcdnname(String pspfcdnname) {
        this.pspfcdnname = pspfcdnname ;
        this.modify("pspfcdnname",pspfcdnname);
    }

    /**
     * 设置 [只发布引用视图]
     */
    public void setPubrefviewonly(Integer pubrefviewonly) {
        this.pubrefviewonly = pubrefviewonly ;
        this.modify("pubrefviewonly",pubrefviewonly);
    }

    /**
     * 设置 [应用模式]
     */
    public void setAppmode(String appmode) {
        this.appmode = appmode ;
        this.modify("appmode",appmode);
    }

    /**
     * 设置 [系统应用名称]
     */
    public void setPssysappname(String pssysappname) {
        this.pssysappname = pssysappname ;
        this.modify("pssysappname",pssysappname);
    }

    /**
     * 设置 [应用类型]
     */
    public void setPsapptypeid(String psapptypeid) {
        this.psapptypeid = psapptypeid ;
        this.modify("psapptypeid",psapptypeid);
    }

    /**
     * 设置 [应用目录]
     */
    public void setAppfolder(String appfolder) {
        this.appfolder = appfolder ;
        this.modify("appfolder",appfolder);
    }

    /**
     * 设置 [起始页图片文件]
     */
    public void setStartpagefile(String startpagefile) {
        this.startpagefile = startpagefile ;
        this.modify("startpagefile",startpagefile);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [应用标记3]
     */
    public void setApptag3(String apptag3) {
        this.apptag3 = apptag3 ;
        this.modify("apptag3",apptag3);
    }

    /**
     * 设置 [应用样式参数]
     */
    public void setPfstyleparam(String pfstyleparam) {
        this.pfstyleparam = pfstyleparam ;
        this.modify("pfstyleparam",pfstyleparam);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [内建界面式样]
     */
    public void setUistyle(String uistyle) {
        this.uistyle = uistyle ;
        this.modify("uistyle",uistyle);
    }

    /**
     * 设置 [表格列无权限显示模式]
     */
    public void setGcnoprivdm(Integer gcnoprivdm) {
        this.gcnoprivdm = gcnoprivdm ;
        this.modify("gcnoprivdm",gcnoprivdm);
    }

    /**
     * 设置 [输出表单项更新权限标记]
     */
    public void setFiupdateprivtag(Integer fiupdateprivtag) {
        this.fiupdateprivtag = fiupdateprivtag ;
        this.modify("fiupdateprivtag",fiupdateprivtag);
    }

    /**
     * 设置 [默认服务接口]
     */
    public void setPssysserviceapiid(String pssysserviceapiid) {
        this.pssysserviceapiid = pssysserviceapiid ;
        this.modify("pssysserviceapiid",pssysserviceapiid);
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
        this.reset("pssysappid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


