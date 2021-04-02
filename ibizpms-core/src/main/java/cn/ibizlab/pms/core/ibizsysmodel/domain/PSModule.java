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
 * ServiceApi [系统模块] 对象
 */
@Data
@ApiModel("系统模块")
public class PSModule extends EntityClient implements Serializable {

    /**
     * 代码包名
     */
    @JSONField(name = "pkgcodename")
    @JsonProperty("pkgcodename")
    @ApiModelProperty("代码包名")
    private String pkgcodename;

    /**
     * 子系统模块
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "subsysmodule")
    @JsonProperty("subsysmodule")
    @ApiModelProperty("子系统模块")
    private Integer subsysmodule;

    /**
     * 模块标记4
     */
    @JSONField(name = "modtag4")
    @JsonProperty("modtag4")
    @ApiModelProperty("模块标记4")
    private String modtag4;

    /**
     * 模块标记3
     */
    @JSONField(name = "modtag3")
    @JsonProperty("modtag3")
    @ApiModelProperty("模块标记3")
    private String modtag3;

    /**
     * 模型锁模式
     */
    @JSONField(name = "lockflag")
    @JsonProperty("lockflag")
    @ApiModelProperty("模型锁模式")
    private Integer lockflag;

    /**
     * 显示颜色
     */
    @JSONField(name = "color")
    @JsonProperty("color")
    @ApiModelProperty("显示颜色")
    private String color;

    /**
     * 模块编号
     */
    @JSONField(name = "modulesn")
    @JsonProperty("modulesn")
    @ApiModelProperty("模块编号")
    private String modulesn;

    /**
     * 系统模块名称
     */
    @JSONField(name = "psmodulename")
    @JsonProperty("psmodulename")
    @ApiModelProperty("系统模块名称")
    private String psmodulename;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @ApiModelProperty("系统")
    private String pssystemname;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 默认模块
     */
    @JSONField(name = "defaultflag")
    @JsonProperty("defaultflag")
    @ApiModelProperty("默认模块")
    private Integer defaultflag;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 类包参数
     */
    @JSONField(name = "clspkgparams")
    @JsonProperty("clspkgparams")
    @ApiModelProperty("类包参数")
    private String clspkgparams;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    @ApiModelProperty("代码名称")
    private String codename;

    /**
     * 自定义参数
     */
    @JSONField(name = "userparams")
    @JsonProperty("userparams")
    @ApiModelProperty("自定义参数")
    private String userparams;

    /**
     * 模块标记2
     */
    @JSONField(name = "modtag2")
    @JsonProperty("modtag2")
    @ApiModelProperty("模块标记2")
    private String modtag2;

    /**
     * 系统模块标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "psmoduleid")
    @JsonProperty("psmoduleid")
    @ApiModelProperty("系统模块标识")
    private String psmoduleid;

    /**
     * 模块标记
     */
    @JSONField(name = "modtag")
    @JsonProperty("modtag")
    @ApiModelProperty("模块标记")
    private String modtag;

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
     * 服务API模式
     */
    @JSONField(name = "serviceapiflag")
    @JsonProperty("serviceapiflag")
    @ApiModelProperty("服务API模式")
    private Integer serviceapiflag;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 排序值
     */
    @DEField(defaultValue = "1000", preType = DEPredefinedFieldType.ORDERVALUE)
    @JSONField(name = "ordervalue")
    @JsonProperty("ordervalue")
    @ApiModelProperty("排序值")
    private Integer ordervalue;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 无视图模式
     */
    @JSONField(name = "noviewmode")
    @JsonProperty("noviewmode")
    @ApiModelProperty("无视图模式")
    private Integer noviewmode;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @ApiModelProperty("系统")
    private String pssystemid;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 子系统类型
     */
    @JSONField(name = "sysreftype")
    @JsonProperty("sysreftype")
    @ApiModelProperty("子系统类型")
    private String sysreftype;

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
     * 设置 [代码包名]
     */
    public void setPkgcodename(String pkgcodename) {
        this.pkgcodename = pkgcodename ;
        this.modify("pkgcodename",pkgcodename);
    }

    /**
     * 设置 [子系统模块]
     */
    public void setSubsysmodule(Integer subsysmodule) {
        this.subsysmodule = subsysmodule ;
        this.modify("subsysmodule",subsysmodule);
    }

    /**
     * 设置 [模块标记4]
     */
    public void setModtag4(String modtag4) {
        this.modtag4 = modtag4 ;
        this.modify("modtag4",modtag4);
    }

    /**
     * 设置 [模块标记3]
     */
    public void setModtag3(String modtag3) {
        this.modtag3 = modtag3 ;
        this.modify("modtag3",modtag3);
    }

    /**
     * 设置 [模型锁模式]
     */
    public void setLockflag(Integer lockflag) {
        this.lockflag = lockflag ;
        this.modify("lockflag",lockflag);
    }

    /**
     * 设置 [显示颜色]
     */
    public void setColor(String color) {
        this.color = color ;
        this.modify("color",color);
    }

    /**
     * 设置 [模块编号]
     */
    public void setModulesn(String modulesn) {
        this.modulesn = modulesn ;
        this.modify("modulesn",modulesn);
    }

    /**
     * 设置 [系统模块名称]
     */
    public void setPsmodulename(String psmodulename) {
        this.psmodulename = psmodulename ;
        this.modify("psmodulename",psmodulename);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [默认模块]
     */
    public void setDefaultflag(Integer defaultflag) {
        this.defaultflag = defaultflag ;
        this.modify("defaultflag",defaultflag);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [类包参数]
     */
    public void setClspkgparams(String clspkgparams) {
        this.clspkgparams = clspkgparams ;
        this.modify("clspkgparams",clspkgparams);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [自定义参数]
     */
    public void setUserparams(String userparams) {
        this.userparams = userparams ;
        this.modify("userparams",userparams);
    }

    /**
     * 设置 [模块标记2]
     */
    public void setModtag2(String modtag2) {
        this.modtag2 = modtag2 ;
        this.modify("modtag2",modtag2);
    }

    /**
     * 设置 [模块标记]
     */
    public void setModtag(String modtag) {
        this.modtag = modtag ;
        this.modify("modtag",modtag);
    }

    /**
     * 设置 [服务API模式]
     */
    public void setServiceapiflag(Integer serviceapiflag) {
        this.serviceapiflag = serviceapiflag ;
        this.modify("serviceapiflag",serviceapiflag);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [无视图模式]
     */
    public void setNoviewmode(Integer noviewmode) {
        this.noviewmode = noviewmode ;
        this.modify("noviewmode",noviewmode);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [子系统类型]
     */
    public void setSysreftype(String sysreftype) {
        this.sysreftype = sysreftype ;
        this.modify("sysreftype",sysreftype);
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
        this.reset("psmoduleid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


