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

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [系统数据库] 对象
 */
@Data
public class PSSystemDBCfg extends EntityClient implements Serializable {
    @Override
    public void modify(String field, Object val) {
        getExtensionparams().put("dirtyflagenable", true);
        super.modify(field, val);
    }

    /**
     * 表空间2名称
     */
    @JSONField(name = "tabspace2")
    @JsonProperty("tabspace2")
    private String tabspace2;

    /**
     * 资源状态
     */
    @JSONField(name = "resstate")
    @JsonProperty("resstate")
    private Integer resstate;

    /**
     * 空值排序
     */
    @JSONField(name = "nullvalorder")
    @JsonProperty("nullvalorder")
    private String nullvalorder;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    private String usertag3;

    /**
     * 默认表空间名称
     */
    @JSONField(name = "tabspace")
    @JsonProperty("tabspace")
    private String tabspace;

    /**
     * 默认数据源
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "defaultflag")
    @JsonProperty("defaultflag")
    private Integer defaultflag;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;

    /**
     * 无数据库模式
     */
    @JSONField(name = "nodbinstmode")
    @JsonProperty("nodbinstmode")
    private Integer nodbinstmode;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    private String usertag4;

    /**
     * 系统数据库名称
     */
    @JSONField(name = "pssystemdbcfgname")
    @JsonProperty("pssystemdbcfgname")
    private String pssystemdbcfgname;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    private String pssystemname;

    /**
     * 资源信息
     */
    @JSONField(name = "resinfo")
    @JsonProperty("resinfo")
    private String resinfo;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    private String usertag;

    /**
     * 自定义参数
     */
    @JSONField(name = "userparams")
    @JsonProperty("userparams")
    private String userparams;

    /**
     * 资源就绪时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "resreadytime" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("resreadytime")
    private Timestamp resreadytime;

    /**
     * 发布外键
     */
    @JSONField(name = "pubfkeyflag")
    @JsonProperty("pubfkeyflag")
    private Integer pubfkeyflag;

    /**
     * 系统数据库标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssystemdbcfgid")
    @JsonProperty("pssystemdbcfgid")
    private String pssystemdbcfgid;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    private String usertag2;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;

    /**
     * 发布模型注释
     */
    @JSONField(name = "pubcommentflag")
    @JsonProperty("pubcommentflag")
    private Integer pubcommentflag;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 表空间3名称
     */
    @JSONField(name = "tabspace3")
    @JsonProperty("tabspace3")
    private String tabspace3;

    /**
     * 支持Web管理
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "enablewebtool")
    @JsonProperty("enablewebtool")
    private Integer enablewebtool;

    /**
     * 发布数据库模型
     */
    @JSONField(name = "pubdbmodelflag")
    @JsonProperty("pubdbmodelflag")
    private Integer pubdbmodelflag;

    /**
     * 对象名称转换
     */
    @JSONField(name = "objnamecase")
    @JsonProperty("objnamecase")
    private String objnamecase;

    /**
     * 发布索引
     */
    @JSONField(name = "pubindexflag")
    @JsonProperty("pubindexflag")
    private Integer pubindexflag;

    /**
     * 附加模式名称
     */
    @JSONField(name = "appendschema")
    @JsonProperty("appendschema")
    private Integer appendschema;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    private String pssystemid;

    /**
     * 数据库模式名称
     */
    @JSONField(name = "dbschemaname")
    @JsonProperty("dbschemaname")
    private String dbschemaname;

    /**
     * 表空间4名称
     */
    @JSONField(name = "tabspace4")
    @JsonProperty("tabspace4")
    private String tabspace4;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    private String usercat;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 发布视图
     */
    @JSONField(name = "pubviewflag")
    @JsonProperty("pubviewflag")
    private Integer pubviewflag;





    /**
     * 设置 [表空间2名称]
     */
    public void setTabspace2(String tabspace2) {
        this.tabspace2 = tabspace2 ;
        this.modify("tabspace2", tabspace2);
    }

    /**
     * 设置 [资源状态]
     */
    public void setResstate(Integer resstate) {
        this.resstate = resstate ;
        this.modify("resstate", resstate);
    }

    /**
     * 设置 [空值排序]
     */
    public void setNullvalorder(String nullvalorder) {
        this.nullvalorder = nullvalorder ;
        this.modify("nullvalorder", nullvalorder);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3", usertag3);
    }

    /**
     * 设置 [默认表空间名称]
     */
    public void setTabspace(String tabspace) {
        this.tabspace = tabspace ;
        this.modify("tabspace", tabspace);
    }

    /**
     * 设置 [默认数据源]
     */
    public void setDefaultflag(Integer defaultflag) {
        this.defaultflag = defaultflag ;
        this.modify("defaultflag", defaultflag);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo", memo);
    }

    /**
     * 设置 [无数据库模式]
     */
    public void setNodbinstmode(Integer nodbinstmode) {
        this.nodbinstmode = nodbinstmode ;
        this.modify("nodbinstmode", nodbinstmode);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4", usertag4);
    }

    /**
     * 设置 [系统数据库名称]
     */
    public void setPssystemdbcfgname(String pssystemdbcfgname) {
        this.pssystemdbcfgname = pssystemdbcfgname ;
        this.modify("pssystemdbcfgname", pssystemdbcfgname);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname", pssystemname);
    }

    /**
     * 设置 [资源信息]
     */
    public void setResinfo(String resinfo) {
        this.resinfo = resinfo ;
        this.modify("resinfo", resinfo);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag", usertag);
    }

    /**
     * 设置 [自定义参数]
     */
    public void setUserparams(String userparams) {
        this.userparams = userparams ;
        this.modify("userparams", userparams);
    }

    /**
     * 设置 [资源就绪时间]
     */
    public void setResreadytime(Timestamp resreadytime) {
        this.resreadytime = resreadytime ;
        this.modify("resreadytime", resreadytime);
    }

    /**
     * 格式化日期 [资源就绪时间]
     */
    public String formatResreadytime() {
        if (this.resreadytime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(resreadytime);
    }
    /**
     * 设置 [发布外键]
     */
    public void setPubfkeyflag(Integer pubfkeyflag) {
        this.pubfkeyflag = pubfkeyflag ;
        this.modify("pubfkeyflag", pubfkeyflag);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2", usertag2);
    }

    /**
     * 设置 [发布模型注释]
     */
    public void setPubcommentflag(Integer pubcommentflag) {
        this.pubcommentflag = pubcommentflag ;
        this.modify("pubcommentflag", pubcommentflag);
    }

    /**
     * 设置 [表空间3名称]
     */
    public void setTabspace3(String tabspace3) {
        this.tabspace3 = tabspace3 ;
        this.modify("tabspace3", tabspace3);
    }

    /**
     * 设置 [支持Web管理]
     */
    public void setEnablewebtool(Integer enablewebtool) {
        this.enablewebtool = enablewebtool ;
        this.modify("enablewebtool", enablewebtool);
    }

    /**
     * 设置 [发布数据库模型]
     */
    public void setPubdbmodelflag(Integer pubdbmodelflag) {
        this.pubdbmodelflag = pubdbmodelflag ;
        this.modify("pubdbmodelflag", pubdbmodelflag);
    }

    /**
     * 设置 [对象名称转换]
     */
    public void setObjnamecase(String objnamecase) {
        this.objnamecase = objnamecase ;
        this.modify("objnamecase", objnamecase);
    }

    /**
     * 设置 [发布索引]
     */
    public void setPubindexflag(Integer pubindexflag) {
        this.pubindexflag = pubindexflag ;
        this.modify("pubindexflag", pubindexflag);
    }

    /**
     * 设置 [附加模式名称]
     */
    public void setAppendschema(Integer appendschema) {
        this.appendschema = appendschema ;
        this.modify("appendschema", appendschema);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid", pssystemid);
    }

    /**
     * 设置 [数据库模式名称]
     */
    public void setDbschemaname(String dbschemaname) {
        this.dbschemaname = dbschemaname ;
        this.modify("dbschemaname", dbschemaname);
    }

    /**
     * 设置 [表空间4名称]
     */
    public void setTabspace4(String tabspace4) {
        this.tabspace4 = tabspace4 ;
        this.modify("tabspace4", tabspace4);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat", usercat);
    }

    /**
     * 设置 [发布视图]
     */
    public void setPubviewflag(Integer pubviewflag) {
        this.pubviewflag = pubviewflag ;
        this.modify("pubviewflag", pubviewflag);
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
        this.reset("pssystemdbcfgid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


