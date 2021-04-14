package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * 服务DTO对象[PSSystemDBCfgDTO]
 */
@Data
@ApiModel("系统数据库")
public class PSSystemDBCfgDTO extends DTOClient implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [TABSPACE2]
     *
     */
    @JSONField(name = "tabspace2")
    @JsonProperty("tabspace2")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("表空间2名称")
    private String tabspace2;

    /**
     * 属性 [RESSTATE]
     *
     */
    @JSONField(name = "resstate")
    @JsonProperty("resstate")
    @ApiModelProperty("资源状态")
    private Integer resstate;

    /**
     * 属性 [NULLVALORDER]
     *
     */
    @JSONField(name = "nullvalorder")
    @JsonProperty("nullvalorder")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("空值排序")
    private String nullvalorder;

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
     * 属性 [TABSPACE]
     *
     */
    @JSONField(name = "tabspace")
    @JsonProperty("tabspace")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("默认表空间名称")
    private String tabspace;

    /**
     * 属性 [DEFAULTFLAG]
     *
     */
    @JSONField(name = "defaultflag")
    @JsonProperty("defaultflag")
    @ApiModelProperty("默认数据源")
    private Integer defaultflag;

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
     * 属性 [NODBINSTMODE]
     *
     */
    @JSONField(name = "nodbinstmode")
    @JsonProperty("nodbinstmode")
    @ApiModelProperty("无数据库模式")
    private Integer nodbinstmode;

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
     * 属性 [PSSYSTEMDBCFGNAME]
     *
     */
    @JSONField(name = "pssystemdbcfgname")
    @JsonProperty("pssystemdbcfgname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("系统数据库名称")
    private String pssystemdbcfgname;

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
     * 属性 [RESINFO]
     *
     */
    @JSONField(name = "resinfo")
    @JsonProperty("resinfo")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("资源信息")
    private String resinfo;

    /**
     * 属性 [USERTAG]
     *
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("用户标记")
    private String usertag;

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
     * 属性 [RESREADYTIME]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "resreadytime" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("resreadytime")
    @ApiModelProperty("资源就绪时间")
    private Timestamp resreadytime;

    /**
     * 属性 [PUBFKEYFLAG]
     *
     */
    @JSONField(name = "pubfkeyflag")
    @JsonProperty("pubfkeyflag")
    @ApiModelProperty("发布外键")
    private Integer pubfkeyflag;

    /**
     * 属性 [PSSYSTEMDBCFGID]
     *
     */
    @JSONField(name = "pssystemdbcfgid")
    @JsonProperty("pssystemdbcfgid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统数据库标识")
    private String pssystemdbcfgid;

    /**
     * 属性 [USERTAG2]
     *
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("用户标记2")
    private String usertag2;

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
     * 属性 [PUBCOMMENTFLAG]
     *
     */
    @JSONField(name = "pubcommentflag")
    @JsonProperty("pubcommentflag")
    @ApiModelProperty("发布模型注释")
    private Integer pubcommentflag;

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
     * 属性 [TABSPACE3]
     *
     */
    @JSONField(name = "tabspace3")
    @JsonProperty("tabspace3")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("表空间3名称")
    private String tabspace3;

    /**
     * 属性 [ENABLEWEBTOOL]
     *
     */
    @JSONField(name = "enablewebtool")
    @JsonProperty("enablewebtool")
    @ApiModelProperty("支持Web管理")
    private Integer enablewebtool;

    /**
     * 属性 [PUBDBMODELFLAG]
     *
     */
    @JSONField(name = "pubdbmodelflag")
    @JsonProperty("pubdbmodelflag")
    @ApiModelProperty("发布数据库模型")
    private Integer pubdbmodelflag;

    /**
     * 属性 [OBJNAMECASE]
     *
     */
    @JSONField(name = "objnamecase")
    @JsonProperty("objnamecase")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("对象名称转换")
    private String objnamecase;

    /**
     * 属性 [PUBINDEXFLAG]
     *
     */
    @JSONField(name = "pubindexflag")
    @JsonProperty("pubindexflag")
    @ApiModelProperty("发布索引")
    private Integer pubindexflag;

    /**
     * 属性 [APPENDSCHEMA]
     *
     */
    @JSONField(name = "appendschema")
    @JsonProperty("appendschema")
    @ApiModelProperty("附加模式名称")
    private Integer appendschema;

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
     * 属性 [DBSCHEMANAME]
     *
     */
    @JSONField(name = "dbschemaname")
    @JsonProperty("dbschemaname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("数据库模式名称")
    private String dbschemaname;

    /**
     * 属性 [TABSPACE4]
     *
     */
    @JSONField(name = "tabspace4")
    @JsonProperty("tabspace4")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("表空间4名称")
    private String tabspace4;

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
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;

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
     * 属性 [PUBVIEWFLAG]
     *
     */
    @JSONField(name = "pubviewflag")
    @JsonProperty("pubviewflag")
    @ApiModelProperty("发布视图")
    private Integer pubviewflag;


    /**
     * 设置 [TABSPACE2]
     */
    public void setTabspace2(String  tabspace2){
        this.tabspace2 = tabspace2 ;
        this.modify("tabspace2",tabspace2);
    }

    /**
     * 设置 [RESSTATE]
     */
    public void setResstate(Integer  resstate){
        this.resstate = resstate ;
        this.modify("resstate",resstate);
    }

    /**
     * 设置 [NULLVALORDER]
     */
    public void setNullvalorder(String  nullvalorder){
        this.nullvalorder = nullvalorder ;
        this.modify("nullvalorder",nullvalorder);
    }

    /**
     * 设置 [USERTAG3]
     */
    public void setUsertag3(String  usertag3){
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [TABSPACE]
     */
    public void setTabspace(String  tabspace){
        this.tabspace = tabspace ;
        this.modify("tabspace",tabspace);
    }

    /**
     * 设置 [DEFAULTFLAG]
     */
    public void setDefaultflag(Integer  defaultflag){
        this.defaultflag = defaultflag ;
        this.modify("defaultflag",defaultflag);
    }

    /**
     * 设置 [MEMO]
     */
    public void setMemo(String  memo){
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [NODBINSTMODE]
     */
    public void setNodbinstmode(Integer  nodbinstmode){
        this.nodbinstmode = nodbinstmode ;
        this.modify("nodbinstmode",nodbinstmode);
    }

    /**
     * 设置 [USERTAG4]
     */
    public void setUsertag4(String  usertag4){
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [PSSYSTEMDBCFGNAME]
     */
    public void setPssystemdbcfgname(String  pssystemdbcfgname){
        this.pssystemdbcfgname = pssystemdbcfgname ;
        this.modify("pssystemdbcfgname",pssystemdbcfgname);
    }

    /**
     * 设置 [PSSYSTEMNAME]
     */
    public void setPssystemname(String  pssystemname){
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [RESINFO]
     */
    public void setResinfo(String  resinfo){
        this.resinfo = resinfo ;
        this.modify("resinfo",resinfo);
    }

    /**
     * 设置 [USERTAG]
     */
    public void setUsertag(String  usertag){
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [USERPARAMS]
     */
    public void setUserparams(String  userparams){
        this.userparams = userparams ;
        this.modify("userparams",userparams);
    }

    /**
     * 设置 [RESREADYTIME]
     */
    public void setResreadytime(Timestamp  resreadytime){
        this.resreadytime = resreadytime ;
        this.modify("resreadytime",resreadytime);
    }

    /**
     * 设置 [PUBFKEYFLAG]
     */
    public void setPubfkeyflag(Integer  pubfkeyflag){
        this.pubfkeyflag = pubfkeyflag ;
        this.modify("pubfkeyflag",pubfkeyflag);
    }

    /**
     * 设置 [USERTAG2]
     */
    public void setUsertag2(String  usertag2){
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [PUBCOMMENTFLAG]
     */
    public void setPubcommentflag(Integer  pubcommentflag){
        this.pubcommentflag = pubcommentflag ;
        this.modify("pubcommentflag",pubcommentflag);
    }

    /**
     * 设置 [TABSPACE3]
     */
    public void setTabspace3(String  tabspace3){
        this.tabspace3 = tabspace3 ;
        this.modify("tabspace3",tabspace3);
    }

    /**
     * 设置 [ENABLEWEBTOOL]
     */
    public void setEnablewebtool(Integer  enablewebtool){
        this.enablewebtool = enablewebtool ;
        this.modify("enablewebtool",enablewebtool);
    }

    /**
     * 设置 [PUBDBMODELFLAG]
     */
    public void setPubdbmodelflag(Integer  pubdbmodelflag){
        this.pubdbmodelflag = pubdbmodelflag ;
        this.modify("pubdbmodelflag",pubdbmodelflag);
    }

    /**
     * 设置 [OBJNAMECASE]
     */
    public void setObjnamecase(String  objnamecase){
        this.objnamecase = objnamecase ;
        this.modify("objnamecase",objnamecase);
    }

    /**
     * 设置 [PUBINDEXFLAG]
     */
    public void setPubindexflag(Integer  pubindexflag){
        this.pubindexflag = pubindexflag ;
        this.modify("pubindexflag",pubindexflag);
    }

    /**
     * 设置 [APPENDSCHEMA]
     */
    public void setAppendschema(Integer  appendschema){
        this.appendschema = appendschema ;
        this.modify("appendschema",appendschema);
    }

    /**
     * 设置 [PSSYSTEMID]
     */
    public void setPssystemid(String  pssystemid){
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [DBSCHEMANAME]
     */
    public void setDbschemaname(String  dbschemaname){
        this.dbschemaname = dbschemaname ;
        this.modify("dbschemaname",dbschemaname);
    }

    /**
     * 设置 [TABSPACE4]
     */
    public void setTabspace4(String  tabspace4){
        this.tabspace4 = tabspace4 ;
        this.modify("tabspace4",tabspace4);
    }

    /**
     * 设置 [USERCAT]
     */
    public void setUsercat(String  usercat){
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [PUBVIEWFLAG]
     */
    public void setPubviewflag(Integer  pubviewflag){
        this.pubviewflag = pubviewflag ;
        this.modify("pubviewflag",pubviewflag);
    }


}


