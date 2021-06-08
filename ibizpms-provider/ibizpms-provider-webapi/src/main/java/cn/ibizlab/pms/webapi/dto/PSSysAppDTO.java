package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[PSSysAppDTO]
 */
@Data
@ApiModel("系统应用")
@JsonFilter(value = "dtofieldfilter")
public class PSSysAppDTO extends DTOClient implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [UACLOGIN]
     *
     */
    @JSONField(name = "uaclogin")
    @JsonProperty("uaclogin")
    @ApiModelProperty("启用统一认证")
    private Integer uaclogin;
    /**
     * 属性 [PSPFID]
     *
     */
    @JSONField(name = "pspfid")
    @JsonProperty("pspfid")
    @NotBlank(message = "[前台技术架构]不允许为空!")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("前台技术架构")
    private String pspfid;
    /**
     * 属性 [USERTAG]
     *
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("用户标记")
    private String usertag;
    /**
     * 属性 [AUTOADDAPPVIEW]
     *
     */
    @JSONField(name = "autoaddappview")
    @JsonProperty("autoaddappview")
    @ApiModelProperty("自动添加应用视图")
    private Integer autoaddappview;
    /**
     * 属性 [ENALOCALSERVICE]
     *
     */
    @JSONField(name = "enalocalservice")
    @JsonProperty("enalocalservice")
    @ApiModelProperty("启用本地服务")
    private Integer enalocalservice;
    /**
     * 属性 [USERTAG2]
     *
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("用户标记2")
    private String usertag2;
    /**
     * 属性 [USERTAG4]
     *
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @Size(min = 0, max = 50, message = "内容长度必须小于等于[50]")
    @ApiModelProperty("用户标记4")
    private String usertag4;
    /**
     * 属性 [PSSYSTEMID]
     *
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统")
    private String pssystemid;
    /**
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 属性 [PUBSYSREFVIEWONLY]
     *
     */
    @JSONField(name = "pubsysrefviewonly")
    @JsonProperty("pubsysrefviewonly")
    @ApiModelProperty("只发布系统引用视图（废弃）")
    private Integer pubsysrefviewonly;
    /**
     * 属性 [MAINMENUSIDE]
     *
     */
    @JSONField(name = "mainmenuside")
    @JsonProperty("mainmenuside")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("视图主菜单方向")
    private String mainmenuside;
    /**
     * 属性 [PSSYSAPPID]
     *
     */
    @JSONField(name = "pssysappid")
    @JsonProperty("pssysappid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统应用标识")
    private String pssysappid;
    /**
     * 属性 [CODEFOLDER]
     *
     */
    @JSONField(name = "codefolder")
    @JsonProperty("codefolder")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("代码目录")
    private String codefolder;
    /**
     * 属性 [GRIDFORCEFIT]
     *
     */
    @JSONField(name = "gridforcefit")
    @JsonProperty("gridforcefit")
    @ApiModelProperty("表格适应屏宽")
    private Integer gridforcefit;
    /**
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 属性 [FINOPRIVDM]
     *
     */
    @JSONField(name = "finoprivdm")
    @JsonProperty("finoprivdm")
    @ApiModelProperty("表单项无权限显示模式")
    private Integer finoprivdm;
    /**
     * 属性 [USERPARAMS]
     *
     */
    @JSONField(name = "userparams")
    @JsonProperty("userparams")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("自定义参数")
    private String userparams;
    /**
     * 属性 [ORIENTATIONMODE]
     *
     */
    @JSONField(name = "orientationmode")
    @JsonProperty("orientationmode")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("移动端方向设置")
    private String orientationmode;
    /**
     * 属性 [MEMO]
     *
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("备注")
    private String memo;
    /**
     * 属性 [REMOVEFLAG]
     *
     */
    @JSONField(name = "removeflag")
    @JsonProperty("removeflag")
    @ApiModelProperty("删除模式")
    private Integer removeflag;
    /**
     * 属性 [ENABLESTORYBOARD]
     *
     */
    @JSONField(name = "enablestoryboard")
    @JsonProperty("enablestoryboard")
    @ApiModelProperty("启用故事板")
    private Integer enablestoryboard;
    /**
     * 属性 [PSSTUDIOTHEMENAME]
     *
     */
    @JSONField(name = "psstudiothemename")
    @JsonProperty("psstudiothemename")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("应用主题")
    private String psstudiothemename;
    /**
     * 属性 [ENABLEDYNASYS]
     *
     */
    @JSONField(name = "enabledynasys")
    @JsonProperty("enabledynasys")
    @ApiModelProperty("支持动态系统")
    private Integer enabledynasys;
    /**
     * 属性 [VALIDFLAG]
     *
     */
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    @ApiModelProperty("是否启用")
    private Integer validflag;
    /**
     * 属性 [ICONFILE]
     *
     */
    @JSONField(name = "iconfile")
    @JsonProperty("iconfile")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("图标文件")
    private String iconfile;
    /**
     * 属性 [APPTAG]
     *
     */
    @JSONField(name = "apptag")
    @JsonProperty("apptag")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("应用标记")
    private String apptag;
    /**
     * 属性 [DEFAULTPUB]
     *
     */
    @JSONField(name = "defaultpub")
    @JsonProperty("defaultpub")
    @ApiModelProperty("默认应用")
    private Integer defaultpub;
    /**
     * 属性 [APPTAG2]
     *
     */
    @JSONField(name = "apptag2")
    @JsonProperty("apptag2")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("应用标记2")
    private String apptag2;
    /**
     * 属性 [SERVICECODENAME]
     *
     */
    @JSONField(name = "servicecodename")
    @JsonProperty("servicecodename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("服务代码名称")
    private String servicecodename;
    /**
     * 属性 [PREVENTXSS]
     *
     */
    @JSONField(name = "preventxss")
    @JsonProperty("preventxss")
    @ApiModelProperty("防止XSS攻击")
    private Integer preventxss;
    /**
     * 属性 [PSAPPTYPENAME]
     *
     */
    @JSONField(name = "psapptypename")
    @JsonProperty("psapptypename")
    @NotBlank(message = "[应用类型]不允许为空!")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("应用类型")
    private String psapptypename;
    /**
     * 属性 [PSPFCDNID]
     *
     */
    @JSONField(name = "pspfcdnid")
    @JsonProperty("pspfcdnid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("应用CDN")
    private String pspfcdnid;
    /**
     * 属性 [GRIDCOLENABLELINK]
     *
     */
    @JSONField(name = "gridcolenablelink")
    @JsonProperty("gridcolenablelink")
    @ApiModelProperty("表格列启用链接")
    private Integer gridcolenablelink;
    /**
     * 属性 [PSSTUDIOTHEMEID]
     *
     */
    @JSONField(name = "psstudiothemeid")
    @JsonProperty("psstudiothemeid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("应用主题")
    private String psstudiothemeid;
    /**
     * 属性 [ENABLEC12TOC24]
     *
     */
    @JSONField(name = "enablec12toc24")
    @JsonProperty("enablec12toc24")
    @ApiModelProperty("转换12列至24列布局")
    private Integer enablec12toc24;
    /**
     * 属性 [APPSN]
     *
     */
    @JSONField(name = "appsn")
    @JsonProperty("appsn")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("应用编号")
    private String appsn;
    /**
     * 属性 [APPTAG4]
     *
     */
    @JSONField(name = "apptag4")
    @JsonProperty("apptag4")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("应用标记4")
    private String apptag4;
    /**
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 属性 [GRIDROWACTIVEMODE]
     *
     */
    @JSONField(name = "gridrowactivemode")
    @JsonProperty("gridrowactivemode")
    @ApiModelProperty("表格行激活模式")
    private Integer gridrowactivemode;
    /**
     * 属性 [USERTAG3]
     *
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @Size(min = 0, max = 50, message = "内容长度必须小于等于[50]")
    @ApiModelProperty("用户标记3")
    private String usertag3;
    /**
     * 属性 [APPPKGNAME]
     *
     */
    @JSONField(name = "apppkgname")
    @JsonProperty("apppkgname")
    @NotBlank(message = "[代码名称]不允许为空!")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("代码名称")
    private String apppkgname;
    /**
     * 属性 [LOGICNAME]
     *
     */
    @JSONField(name = "logicname")
    @JsonProperty("logicname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("中文名称")
    private String logicname;
    /**
     * 属性 [PSPFSTYLEID]
     *
     */
    @JSONField(name = "pspfstyleid")
    @JsonProperty("pspfstyleid")
    @NotBlank(message = "[应用样式]不允许为空!")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("应用样式")
    private String pspfstyleid;
    /**
     * 属性 [BTNNOPRIVDM]
     *
     */
    @JSONField(name = "btnnoprivdm")
    @JsonProperty("btnnoprivdm")
    @ApiModelProperty("按钮无权限显示模式")
    private Integer btnnoprivdm;
    /**
     * 属性 [PSPFCDNNAME]
     *
     */
    @JSONField(name = "pspfcdnname")
    @JsonProperty("pspfcdnname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("应用CDN")
    private String pspfcdnname;
    /**
     * 属性 [PUBREFVIEWONLY]
     *
     */
    @JSONField(name = "pubrefviewonly")
    @JsonProperty("pubrefviewonly")
    @ApiModelProperty("只发布引用视图")
    private Integer pubrefviewonly;
    /**
     * 属性 [APPMODE]
     *
     */
    @JSONField(name = "appmode")
    @JsonProperty("appmode")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("应用模式")
    private String appmode;
    /**
     * 属性 [PSSYSAPPNAME]
     *
     */
    @JSONField(name = "pssysappname")
    @JsonProperty("pssysappname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("系统应用名称")
    private String pssysappname;
    /**
     * 属性 [PSAPPTYPEID]
     *
     */
    @JSONField(name = "psapptypeid")
    @JsonProperty("psapptypeid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("应用类型")
    private String psapptypeid;
    /**
     * 属性 [APPFOLDER]
     *
     */
    @JSONField(name = "appfolder")
    @JsonProperty("appfolder")
    @Size(min = 0, max = 500, message = "内容长度必须小于等于[500]")
    @ApiModelProperty("应用目录")
    private String appfolder;
    /**
     * 属性 [STARTPAGEFILE]
     *
     */
    @JSONField(name = "startpagefile")
    @JsonProperty("startpagefile")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("起始页图片文件")
    private String startpagefile;
    /**
     * 属性 [USERCAT]
     *
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("用户分类")
    private String usercat;
    /**
     * 属性 [APPTAG3]
     *
     */
    @JSONField(name = "apptag3")
    @JsonProperty("apptag3")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("应用标记3")
    private String apptag3;
    /**
     * 属性 [PFSTYLEPARAM]
     *
     */
    @JSONField(name = "pfstyleparam")
    @JsonProperty("pfstyleparam")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("应用样式参数")
    private String pfstyleparam;
    /**
     * 属性 [PSSYSTEMNAME]
     *
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统")
    private String pssystemname;
    /**
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 属性 [UISTYLE]
     *
     */
    @JSONField(name = "uistyle")
    @JsonProperty("uistyle")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("内建界面式样")
    private String uistyle;
    /**
     * 属性 [GCNOPRIVDM]
     *
     */
    @JSONField(name = "gcnoprivdm")
    @JsonProperty("gcnoprivdm")
    @ApiModelProperty("表格列无权限显示模式")
    private Integer gcnoprivdm;
    /**
     * 属性 [FIUPDATEPRIVTAG]
     *
     */
    @JSONField(name = "fiupdateprivtag")
    @JsonProperty("fiupdateprivtag")
    @ApiModelProperty("输出表单项更新权限标记")
    private Integer fiupdateprivtag;
    /**
     * 属性 [PSSYSSERVICEAPINAME]
     *
     */
    @JSONField(name = "pssysserviceapiname")
    @JsonProperty("pssysserviceapiname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("默认服务接口")
    private String pssysserviceapiname;
    /**
     * 属性 [PSSYSSERVICEAPIID]
     *
     */
    @JSONField(name = "pssysserviceapiid")
    @JsonProperty("pssysserviceapiid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("默认服务接口")
    private String pssysserviceapiid;

    /**
     * 设置 [UACLOGIN]
     */
    public void setUaclogin(Integer  uaclogin){
        this.uaclogin = uaclogin ;
        this.modify("uaclogin",uaclogin);
    }

    /**
     * 设置 [PSPFID]
     */
    public void setPspfid(String  pspfid){
        this.pspfid = pspfid ;
        this.modify("pspfid",pspfid);
    }

    /**
     * 设置 [USERTAG]
     */
    public void setUsertag(String  usertag){
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [AUTOADDAPPVIEW]
     */
    public void setAutoaddappview(Integer  autoaddappview){
        this.autoaddappview = autoaddappview ;
        this.modify("autoaddappview",autoaddappview);
    }

    /**
     * 设置 [ENALOCALSERVICE]
     */
    public void setEnalocalservice(Integer  enalocalservice){
        this.enalocalservice = enalocalservice ;
        this.modify("enalocalservice",enalocalservice);
    }

    /**
     * 设置 [USERTAG2]
     */
    public void setUsertag2(String  usertag2){
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [USERTAG4]
     */
    public void setUsertag4(String  usertag4){
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [PSSYSTEMID]
     */
    public void setPssystemid(String  pssystemid){
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [PUBSYSREFVIEWONLY]
     */
    public void setPubsysrefviewonly(Integer  pubsysrefviewonly){
        this.pubsysrefviewonly = pubsysrefviewonly ;
        this.modify("pubsysrefviewonly",pubsysrefviewonly);
    }

    /**
     * 设置 [MAINMENUSIDE]
     */
    public void setMainmenuside(String  mainmenuside){
        this.mainmenuside = mainmenuside ;
        this.modify("mainmenuside",mainmenuside);
    }

    /**
     * 设置 [CODEFOLDER]
     */
    public void setCodefolder(String  codefolder){
        this.codefolder = codefolder ;
        this.modify("codefolder",codefolder);
    }

    /**
     * 设置 [GRIDFORCEFIT]
     */
    public void setGridforcefit(Integer  gridforcefit){
        this.gridforcefit = gridforcefit ;
        this.modify("gridforcefit",gridforcefit);
    }

    /**
     * 设置 [FINOPRIVDM]
     */
    public void setFinoprivdm(Integer  finoprivdm){
        this.finoprivdm = finoprivdm ;
        this.modify("finoprivdm",finoprivdm);
    }

    /**
     * 设置 [USERPARAMS]
     */
    public void setUserparams(String  userparams){
        this.userparams = userparams ;
        this.modify("userparams",userparams);
    }

    /**
     * 设置 [ORIENTATIONMODE]
     */
    public void setOrientationmode(String  orientationmode){
        this.orientationmode = orientationmode ;
        this.modify("orientationmode",orientationmode);
    }

    /**
     * 设置 [MEMO]
     */
    public void setMemo(String  memo){
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [REMOVEFLAG]
     */
    public void setRemoveflag(Integer  removeflag){
        this.removeflag = removeflag ;
        this.modify("removeflag",removeflag);
    }

    /**
     * 设置 [ENABLESTORYBOARD]
     */
    public void setEnablestoryboard(Integer  enablestoryboard){
        this.enablestoryboard = enablestoryboard ;
        this.modify("enablestoryboard",enablestoryboard);
    }

    /**
     * 设置 [PSSTUDIOTHEMENAME]
     */
    public void setPsstudiothemename(String  psstudiothemename){
        this.psstudiothemename = psstudiothemename ;
        this.modify("psstudiothemename",psstudiothemename);
    }

    /**
     * 设置 [ENABLEDYNASYS]
     */
    public void setEnabledynasys(Integer  enabledynasys){
        this.enabledynasys = enabledynasys ;
        this.modify("enabledynasys",enabledynasys);
    }

    /**
     * 设置 [VALIDFLAG]
     */
    public void setValidflag(Integer  validflag){
        this.validflag = validflag ;
        this.modify("validflag",validflag);
    }

    /**
     * 设置 [ICONFILE]
     */
    public void setIconfile(String  iconfile){
        this.iconfile = iconfile ;
        this.modify("iconfile",iconfile);
    }

    /**
     * 设置 [APPTAG]
     */
    public void setApptag(String  apptag){
        this.apptag = apptag ;
        this.modify("apptag",apptag);
    }

    /**
     * 设置 [DEFAULTPUB]
     */
    public void setDefaultpub(Integer  defaultpub){
        this.defaultpub = defaultpub ;
        this.modify("defaultpub",defaultpub);
    }

    /**
     * 设置 [APPTAG2]
     */
    public void setApptag2(String  apptag2){
        this.apptag2 = apptag2 ;
        this.modify("apptag2",apptag2);
    }

    /**
     * 设置 [SERVICECODENAME]
     */
    public void setServicecodename(String  servicecodename){
        this.servicecodename = servicecodename ;
        this.modify("servicecodename",servicecodename);
    }

    /**
     * 设置 [PREVENTXSS]
     */
    public void setPreventxss(Integer  preventxss){
        this.preventxss = preventxss ;
        this.modify("preventxss",preventxss);
    }

    /**
     * 设置 [PSAPPTYPENAME]
     */
    public void setPsapptypename(String  psapptypename){
        this.psapptypename = psapptypename ;
        this.modify("psapptypename",psapptypename);
    }

    /**
     * 设置 [PSPFCDNID]
     */
    public void setPspfcdnid(String  pspfcdnid){
        this.pspfcdnid = pspfcdnid ;
        this.modify("pspfcdnid",pspfcdnid);
    }

    /**
     * 设置 [GRIDCOLENABLELINK]
     */
    public void setGridcolenablelink(Integer  gridcolenablelink){
        this.gridcolenablelink = gridcolenablelink ;
        this.modify("gridcolenablelink",gridcolenablelink);
    }

    /**
     * 设置 [PSSTUDIOTHEMEID]
     */
    public void setPsstudiothemeid(String  psstudiothemeid){
        this.psstudiothemeid = psstudiothemeid ;
        this.modify("psstudiothemeid",psstudiothemeid);
    }

    /**
     * 设置 [ENABLEC12TOC24]
     */
    public void setEnablec12toc24(Integer  enablec12toc24){
        this.enablec12toc24 = enablec12toc24 ;
        this.modify("enablec12toc24",enablec12toc24);
    }

    /**
     * 设置 [APPSN]
     */
    public void setAppsn(String  appsn){
        this.appsn = appsn ;
        this.modify("appsn",appsn);
    }

    /**
     * 设置 [APPTAG4]
     */
    public void setApptag4(String  apptag4){
        this.apptag4 = apptag4 ;
        this.modify("apptag4",apptag4);
    }

    /**
     * 设置 [GRIDROWACTIVEMODE]
     */
    public void setGridrowactivemode(Integer  gridrowactivemode){
        this.gridrowactivemode = gridrowactivemode ;
        this.modify("gridrowactivemode",gridrowactivemode);
    }

    /**
     * 设置 [USERTAG3]
     */
    public void setUsertag3(String  usertag3){
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [APPPKGNAME]
     */
    public void setApppkgname(String  apppkgname){
        this.apppkgname = apppkgname ;
        this.modify("apppkgname",apppkgname);
    }

    /**
     * 设置 [LOGICNAME]
     */
    public void setLogicname(String  logicname){
        this.logicname = logicname ;
        this.modify("logicname",logicname);
    }

    /**
     * 设置 [PSPFSTYLEID]
     */
    public void setPspfstyleid(String  pspfstyleid){
        this.pspfstyleid = pspfstyleid ;
        this.modify("pspfstyleid",pspfstyleid);
    }

    /**
     * 设置 [BTNNOPRIVDM]
     */
    public void setBtnnoprivdm(Integer  btnnoprivdm){
        this.btnnoprivdm = btnnoprivdm ;
        this.modify("btnnoprivdm",btnnoprivdm);
    }

    /**
     * 设置 [PSPFCDNNAME]
     */
    public void setPspfcdnname(String  pspfcdnname){
        this.pspfcdnname = pspfcdnname ;
        this.modify("pspfcdnname",pspfcdnname);
    }

    /**
     * 设置 [PUBREFVIEWONLY]
     */
    public void setPubrefviewonly(Integer  pubrefviewonly){
        this.pubrefviewonly = pubrefviewonly ;
        this.modify("pubrefviewonly",pubrefviewonly);
    }

    /**
     * 设置 [APPMODE]
     */
    public void setAppmode(String  appmode){
        this.appmode = appmode ;
        this.modify("appmode",appmode);
    }

    /**
     * 设置 [PSSYSAPPNAME]
     */
    public void setPssysappname(String  pssysappname){
        this.pssysappname = pssysappname ;
        this.modify("pssysappname",pssysappname);
    }

    /**
     * 设置 [PSAPPTYPEID]
     */
    public void setPsapptypeid(String  psapptypeid){
        this.psapptypeid = psapptypeid ;
        this.modify("psapptypeid",psapptypeid);
    }

    /**
     * 设置 [APPFOLDER]
     */
    public void setAppfolder(String  appfolder){
        this.appfolder = appfolder ;
        this.modify("appfolder",appfolder);
    }

    /**
     * 设置 [STARTPAGEFILE]
     */
    public void setStartpagefile(String  startpagefile){
        this.startpagefile = startpagefile ;
        this.modify("startpagefile",startpagefile);
    }

    /**
     * 设置 [USERCAT]
     */
    public void setUsercat(String  usercat){
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [APPTAG3]
     */
    public void setApptag3(String  apptag3){
        this.apptag3 = apptag3 ;
        this.modify("apptag3",apptag3);
    }

    /**
     * 设置 [PFSTYLEPARAM]
     */
    public void setPfstyleparam(String  pfstyleparam){
        this.pfstyleparam = pfstyleparam ;
        this.modify("pfstyleparam",pfstyleparam);
    }

    /**
     * 设置 [PSSYSTEMNAME]
     */
    public void setPssystemname(String  pssystemname){
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [UISTYLE]
     */
    public void setUistyle(String  uistyle){
        this.uistyle = uistyle ;
        this.modify("uistyle",uistyle);
    }

    /**
     * 设置 [GCNOPRIVDM]
     */
    public void setGcnoprivdm(Integer  gcnoprivdm){
        this.gcnoprivdm = gcnoprivdm ;
        this.modify("gcnoprivdm",gcnoprivdm);
    }

    /**
     * 设置 [FIUPDATEPRIVTAG]
     */
    public void setFiupdateprivtag(Integer  fiupdateprivtag){
        this.fiupdateprivtag = fiupdateprivtag ;
        this.modify("fiupdateprivtag",fiupdateprivtag);
    }

    /**
     * 设置 [PSSYSSERVICEAPIID]
     */
    public void setPssysserviceapiid(String  pssysserviceapiid){
        this.pssysserviceapiid = pssysserviceapiid ;
        this.modify("pssysserviceapiid",pssysserviceapiid);
    }


}


