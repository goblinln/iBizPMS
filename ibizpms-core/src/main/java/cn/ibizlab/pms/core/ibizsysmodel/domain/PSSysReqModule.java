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
 * ServiceApi [系统需求模块] 对象
 */
@Data
@ApiModel("系统需求模块")
public class PSSysReqModule extends EntityClient implements Serializable {

    /**
     * 模块标记2
     */
    @JSONField(name = "moduletag2")
    @JsonProperty("moduletag2")
    @ApiModelProperty("模块标记2")
    private String moduletag2;

    /**
     * 系统需求模块名称
     */
    @JSONField(name = "pssysreqmodulename")
    @JsonProperty("pssysreqmodulename")
    @ApiModelProperty("系统需求模块名称")
    private String pssysreqmodulename;

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
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 模块标记4
     */
    @JSONField(name = "moduletag4")
    @JsonProperty("moduletag4")
    @ApiModelProperty("模块标记4")
    private String moduletag4;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 模块标记3
     */
    @JSONField(name = "moduletag3")
    @JsonProperty("moduletag3")
    @ApiModelProperty("模块标记3")
    private String moduletag3;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 模块标记
     */
    @JSONField(name = "moduletag")
    @JsonProperty("moduletag")
    @ApiModelProperty("模块标记")
    private String moduletag;

    /**
     * 模块编号
     */
    @JSONField(name = "modulesn")
    @JsonProperty("modulesn")
    @ApiModelProperty("模块编号")
    private String modulesn;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @ApiModelProperty("系统")
    private String pssystemid;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 排序值
     */
    @DEField(preType = DEPredefinedFieldType.ORDERVALUE)
    @JSONField(name = "ordervalue")
    @JsonProperty("ordervalue")
    @ApiModelProperty("排序值")
    private Integer ordervalue;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @ApiModelProperty("系统")
    private String pssystemname;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 系统需求模块标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssysreqmoduleid")
    @JsonProperty("pssysreqmoduleid")
    @ApiModelProperty("系统需求模块标识")
    private String pssysreqmoduleid;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 内容
     */
    @JSONField(name = "content")
    @JsonProperty("content")
    @ApiModelProperty("内容")
    private String content;

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
     * 代码标识
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    @ApiModelProperty("代码标识")
    private String codename;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 系统模块
     */
    @JSONField(name = "psmodulename")
    @JsonProperty("psmodulename")
    @ApiModelProperty("系统模块")
    private String psmodulename;

    /**
     * 父需求模块
     */
    @JSONField(name = "ppssysreqmodulename")
    @JsonProperty("ppssysreqmodulename")
    @ApiModelProperty("父需求模块")
    private String ppssysreqmodulename;

    /**
     * 父需求模块
     */
    @JSONField(name = "ppssysreqmoduleid")
    @JsonProperty("ppssysreqmoduleid")
    @ApiModelProperty("父需求模块")
    private String ppssysreqmoduleid;

    /**
     * 系统模块
     */
    @JSONField(name = "psmoduleid")
    @JsonProperty("psmoduleid")
    @ApiModelProperty("系统模块")
    private String psmoduleid;


    /**
     * 
     */
    @JSONField(name = "psmodule")
    @JsonProperty("psmodule")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule psmodule;

    /**
     * 
     */
    @JSONField(name = "ppsysreqmodule")
    @JsonProperty("ppsysreqmodule")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqModule ppsysreqmodule;




    /**
     * 设置 [模块标记2]
     */
    public void setModuletag2(String moduletag2) {
        this.moduletag2 = moduletag2 ;
        this.modify("moduletag2",moduletag2);
    }

    /**
     * 设置 [系统需求模块名称]
     */
    public void setPssysreqmodulename(String pssysreqmodulename) {
        this.pssysreqmodulename = pssysreqmodulename ;
        this.modify("pssysreqmodulename",pssysreqmodulename);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [模块标记4]
     */
    public void setModuletag4(String moduletag4) {
        this.moduletag4 = moduletag4 ;
        this.modify("moduletag4",moduletag4);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [模块标记3]
     */
    public void setModuletag3(String moduletag3) {
        this.moduletag3 = moduletag3 ;
        this.modify("moduletag3",moduletag3);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [模块标记]
     */
    public void setModuletag(String moduletag) {
        this.moduletag = moduletag ;
        this.modify("moduletag",moduletag);
    }

    /**
     * 设置 [模块编号]
     */
    public void setModulesn(String modulesn) {
        this.modulesn = modulesn ;
        this.modify("modulesn",modulesn);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [内容]
     */
    public void setContent(String content) {
        this.content = content ;
        this.modify("content",content);
    }

    /**
     * 设置 [代码标识]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [父需求模块]
     */
    public void setPpssysreqmoduleid(String ppssysreqmoduleid) {
        this.ppssysreqmoduleid = ppssysreqmoduleid ;
        this.modify("ppssysreqmoduleid",ppssysreqmoduleid);
    }

    /**
     * 设置 [系统模块]
     */
    public void setPsmoduleid(String psmoduleid) {
        this.psmoduleid = psmoduleid ;
        this.modify("psmoduleid",psmoduleid);
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
        this.reset("pssysreqmoduleid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


