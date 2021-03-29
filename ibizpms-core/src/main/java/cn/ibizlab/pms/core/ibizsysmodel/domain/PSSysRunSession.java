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
 * ServiceApi [系统运行会话] 对象
 */
@Data
public class PSSysRunSession extends EntityClient implements Serializable {
    @Override
    public void modify(String field, Object val) {
        getExtensionparams().put("dirtyflagenable", true);
        super.modify(field, val);
    }

    /**
     * 运行参数4
     */
    @JSONField(name = "runparam4")
    @JsonProperty("runparam4")
    private String runparam4;

    /**
     * 系统运行会话名称
     */
    @JSONField(name = "pssysrunsessionname")
    @JsonProperty("pssysrunsessionname")
    private String pssysrunsessionname;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 系统运行会话标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssysrunsessionid")
    @JsonProperty("pssysrunsessionid")
    private String pssysrunsessionid;

    /**
     * 服务体系
     */
    @JSONField(name = "pssyssfpubid")
    @JsonProperty("pssyssfpubid")
    private String pssyssfpubid;

    /**
     * 打包模式
     */
    @JSONField(name = "packmode")
    @JsonProperty("packmode")
    private String packmode;

    /**
     * 运行数据库
     */
    @JSONField(name = "pssystemdbcfgname")
    @JsonProperty("pssystemdbcfgname")
    private String pssystemdbcfgname;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    private String pssystemid;

    /**
     * 运行参数5
     */
    @JSONField(name = "runparam5")
    @JsonProperty("runparam5")
    private Integer runparam5;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "starttime" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("starttime")
    private Timestamp starttime;

    /**
     * 运行数据库
     */
    @JSONField(name = "pssystemdbcfgid")
    @JsonProperty("pssystemdbcfgid")
    private String pssystemdbcfgid;

    /**
     * 运行模式
     */
    @JSONField(name = "runmode")
    @JsonProperty("runmode")
    private String runmode;

    /**
     * 模板出错时终止
     */
    @JSONField(name = "stopwhentemplerror")
    @JsonProperty("stopwhentemplerror")
    private Integer stopwhentemplerror;

    /**
     * 运行参数
     */
    @JSONField(name = "runparam")
    @JsonProperty("runparam")
    private String runparam;

    /**
     * 运行状态
     */
    @DEField(defaultValue = "10")
    @JSONField(name = "runstate")
    @JsonProperty("runstate")
    private Integer runstate;

    /**
     * 输出调试信息
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "debugmode")
    @JsonProperty("debugmode")
    private Integer debugmode;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "endtime" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("endtime")
    private Timestamp endtime;

    /**
     * 运行参数6
     */
    @JSONField(name = "runparam6")
    @JsonProperty("runparam6")
    private Integer runparam6;

    /**
     * 服务体系
     */
    @JSONField(name = "pssyssfpubname")
    @JsonProperty("pssyssfpubname")
    private String pssyssfpubname;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;

    /**
     * 修复数据结构
     */
    @JSONField(name = "fixdbmodel")
    @JsonProperty("fixdbmodel")
    private Integer fixdbmodel;

    /**
     * 重新构建
     */
    @JSONField(name = "rebuildmode")
    @JsonProperty("rebuildmode")
    private Integer rebuildmode;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    private String pssystemname;

    /**
     * 运行参数3
     */
    @JSONField(name = "runparam3")
    @JsonProperty("runparam3")
    private String runparam3;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;

    /**
     * 控制台标识
     */
    @JSONField(name = "psdsconsoleid")
    @JsonProperty("psdsconsoleid")
    private String psdsconsoleid;

    /**
     * 签入版本库
     */
    @JSONField(name = "enablevc")
    @JsonProperty("enablevc")
    private Integer enablevc;

    /**
     * 运行参数2
     */
    @JSONField(name = "runparam2")
    @JsonProperty("runparam2")
    private String runparam2;

    /**
     * 系统应用2
     */
    @JSONField(name = "pssysappname2")
    @JsonProperty("pssysappname2")
    private String pssysappname2;

    /**
     * 系统服务接口
     */
    @JSONField(name = "pssysserviceapiname")
    @JsonProperty("pssysserviceapiname")
    private String pssysserviceapiname;

    /**
     * 系统应用
     */
    @JSONField(name = "pssysappname")
    @JsonProperty("pssysappname")
    private String pssysappname;

    /**
     * 系统应用2
     */
    @JSONField(name = "pssysappid2")
    @JsonProperty("pssysappid2")
    private String pssysappid2;

    /**
     * 系统服务接口
     */
    @JSONField(name = "pssysserviceapiid")
    @JsonProperty("pssysserviceapiid")
    private String pssysserviceapiid;

    /**
     * 系统应用
     */
    @JSONField(name = "pssysappid")
    @JsonProperty("pssysappid")
    private String pssysappid;


    /**
     * 
     */
    @JSONField(name = "pssysapp")
    @JsonProperty("pssysapp")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp pssysapp;

    /**
     * 
     */
    @JSONField(name = "pssysapp2")
    @JsonProperty("pssysapp2")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp pssysapp2;

    /**
     * 
     */
    @JSONField(name = "pssysserviceapi")
    @JsonProperty("pssysserviceapi")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI pssysserviceapi;




    /**
     * 设置 [运行参数4]
     */
    public void setRunparam4(String runparam4) {
        this.runparam4 = runparam4 ;
        this.modify("runparam4", runparam4);
    }

    /**
     * 设置 [系统运行会话名称]
     */
    public void setPssysrunsessionname(String pssysrunsessionname) {
        this.pssysrunsessionname = pssysrunsessionname ;
        this.modify("pssysrunsessionname", pssysrunsessionname);
    }

    /**
     * 设置 [服务体系]
     */
    public void setPssyssfpubid(String pssyssfpubid) {
        this.pssyssfpubid = pssyssfpubid ;
        this.modify("pssyssfpubid", pssyssfpubid);
    }

    /**
     * 设置 [打包模式]
     */
    public void setPackmode(String packmode) {
        this.packmode = packmode ;
        this.modify("packmode", packmode);
    }

    /**
     * 设置 [运行数据库]
     */
    public void setPssystemdbcfgname(String pssystemdbcfgname) {
        this.pssystemdbcfgname = pssystemdbcfgname ;
        this.modify("pssystemdbcfgname", pssystemdbcfgname);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid", pssystemid);
    }

    /**
     * 设置 [运行参数5]
     */
    public void setRunparam5(Integer runparam5) {
        this.runparam5 = runparam5 ;
        this.modify("runparam5", runparam5);
    }

    /**
     * 设置 [开始时间]
     */
    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime ;
        this.modify("starttime", starttime);
    }

    /**
     * 格式化日期 [开始时间]
     */
    public String formatStarttime() {
        if (this.starttime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(starttime);
    }
    /**
     * 设置 [运行数据库]
     */
    public void setPssystemdbcfgid(String pssystemdbcfgid) {
        this.pssystemdbcfgid = pssystemdbcfgid ;
        this.modify("pssystemdbcfgid", pssystemdbcfgid);
    }

    /**
     * 设置 [运行模式]
     */
    public void setRunmode(String runmode) {
        this.runmode = runmode ;
        this.modify("runmode", runmode);
    }

    /**
     * 设置 [模板出错时终止]
     */
    public void setStopwhentemplerror(Integer stopwhentemplerror) {
        this.stopwhentemplerror = stopwhentemplerror ;
        this.modify("stopwhentemplerror", stopwhentemplerror);
    }

    /**
     * 设置 [运行参数]
     */
    public void setRunparam(String runparam) {
        this.runparam = runparam ;
        this.modify("runparam", runparam);
    }

    /**
     * 设置 [运行状态]
     */
    public void setRunstate(Integer runstate) {
        this.runstate = runstate ;
        this.modify("runstate", runstate);
    }

    /**
     * 设置 [输出调试信息]
     */
    public void setDebugmode(Integer debugmode) {
        this.debugmode = debugmode ;
        this.modify("debugmode", debugmode);
    }

    /**
     * 设置 [结束时间]
     */
    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime ;
        this.modify("endtime", endtime);
    }

    /**
     * 格式化日期 [结束时间]
     */
    public String formatEndtime() {
        if (this.endtime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(endtime);
    }
    /**
     * 设置 [运行参数6]
     */
    public void setRunparam6(Integer runparam6) {
        this.runparam6 = runparam6 ;
        this.modify("runparam6", runparam6);
    }

    /**
     * 设置 [服务体系]
     */
    public void setPssyssfpubname(String pssyssfpubname) {
        this.pssyssfpubname = pssyssfpubname ;
        this.modify("pssyssfpubname", pssyssfpubname);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo", memo);
    }

    /**
     * 设置 [修复数据结构]
     */
    public void setFixdbmodel(Integer fixdbmodel) {
        this.fixdbmodel = fixdbmodel ;
        this.modify("fixdbmodel", fixdbmodel);
    }

    /**
     * 设置 [重新构建]
     */
    public void setRebuildmode(Integer rebuildmode) {
        this.rebuildmode = rebuildmode ;
        this.modify("rebuildmode", rebuildmode);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname", pssystemname);
    }

    /**
     * 设置 [运行参数3]
     */
    public void setRunparam3(String runparam3) {
        this.runparam3 = runparam3 ;
        this.modify("runparam3", runparam3);
    }

    /**
     * 设置 [控制台标识]
     */
    public void setPsdsconsoleid(String psdsconsoleid) {
        this.psdsconsoleid = psdsconsoleid ;
        this.modify("psdsconsoleid", psdsconsoleid);
    }

    /**
     * 设置 [签入版本库]
     */
    public void setEnablevc(Integer enablevc) {
        this.enablevc = enablevc ;
        this.modify("enablevc", enablevc);
    }

    /**
     * 设置 [运行参数2]
     */
    public void setRunparam2(String runparam2) {
        this.runparam2 = runparam2 ;
        this.modify("runparam2", runparam2);
    }

    /**
     * 设置 [系统应用2]
     */
    public void setPssysappname2(String pssysappname2) {
        this.pssysappname2 = pssysappname2 ;
        this.modify("pssysappname2", pssysappname2);
    }

    /**
     * 设置 [系统服务接口]
     */
    public void setPssysserviceapiname(String pssysserviceapiname) {
        this.pssysserviceapiname = pssysserviceapiname ;
        this.modify("pssysserviceapiname", pssysserviceapiname);
    }

    /**
     * 设置 [系统应用]
     */
    public void setPssysappname(String pssysappname) {
        this.pssysappname = pssysappname ;
        this.modify("pssysappname", pssysappname);
    }

    /**
     * 设置 [系统应用2]
     */
    public void setPssysappid2(String pssysappid2) {
        this.pssysappid2 = pssysappid2 ;
        this.modify("pssysappid2", pssysappid2);
    }

    /**
     * 设置 [系统服务接口]
     */
    public void setPssysserviceapiid(String pssysserviceapiid) {
        this.pssysserviceapiid = pssysserviceapiid ;
        this.modify("pssysserviceapiid", pssysserviceapiid);
    }

    /**
     * 设置 [系统应用]
     */
    public void setPssysappid(String pssysappid) {
        this.pssysappid = pssysappid ;
        this.modify("pssysappid", pssysappid);
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
        this.reset("pssysrunsessionid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


