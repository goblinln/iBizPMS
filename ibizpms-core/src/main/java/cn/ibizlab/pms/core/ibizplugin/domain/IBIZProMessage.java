package cn.ibizlab.pms.core.ibizplugin.domain;

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
 * ServiceApi [消息] 对象
 */
@Data
public class IBIZProMessage extends EntityClient implements Serializable {

    /**
     * 重发次数
     */
    @JSONField(name = "retrytimes")
    @JsonProperty("retrytimes")
    private Integer retrytimes;

    /**
     * 发送方
     */
    @JSONField(name = "from")
    @JsonProperty("from")
    private String from;

    /**
     * 抄送方
     */
    @JSONField(name = "cc")
    @JsonProperty("cc")
    private String cc;

    /**
     * 接收方
     */
    @JSONField(name = "to")
    @JsonProperty("to")
    private String to;

    /**
     * 内容
     */
    @JSONField(name = "content")
    @JsonProperty("content")
    private String content;

    /**
     * 密件抄送方
     */
    @JSONField(name = "bcc")
    @JsonProperty("bcc")
    private String bcc;

    /**
     * 消息参数
     */
    @JSONField(name = "param")
    @JsonProperty("param")
    private String param;

    /**
     * 消息名称
     */
    @DEField(name = "ibizpro_messagename")
    @JSONField(name = "ibizpro_messagename")
    @JsonProperty("ibizpro_messagename")
    private String ibizproMessagename;

    /**
     * 标题
     */
    @JSONField(name = "subject")
    @JsonProperty("subject")
    private String subject;

    /**
     * 重发间隔时间
     */
    @JSONField(name = "retryintervaltime")
    @JsonProperty("retryintervaltime")
    private Integer retryintervaltime;

    /**
     * 消息类型
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;

    /**
     * 是否同步
     */
    @JSONField(name = "issync")
    @JsonProperty("issync")
    private Integer issync;

    /**
     * 是否重发
     */
    @JSONField(name = "isretry")
    @JsonProperty("isretry")
    private Integer isretry;

    /**
     * 是否是链接消息
     */
    @JSONField(name = "islink")
    @JsonProperty("islink")
    private Integer islink;

    /**
     * 消息标识
     */
    @DEField(name = "ibizpro_messageid" , isKeyField=true)
    @JSONField(name = "ibizpro_messageid")
    @JsonProperty("ibizpro_messageid")
    private String ibizproMessageid;





    /**
     * 设置 [重发次数]
     */
    public void setRetrytimes(Integer retrytimes){
        this.retrytimes = retrytimes ;
        this.modify("retrytimes",retrytimes);
    }

    /**
     * 设置 [发送方]
     */
    public void setFrom(String from){
        this.from = from ;
        this.modify("from",from);
    }

    /**
     * 设置 [抄送方]
     */
    public void setCc(String cc){
        this.cc = cc ;
        this.modify("cc",cc);
    }

    /**
     * 设置 [接收方]
     */
    public void setTo(String to){
        this.to = to ;
        this.modify("to",to);
    }

    /**
     * 设置 [内容]
     */
    public void setContent(String content){
        this.content = content ;
        this.modify("content",content);
    }

    /**
     * 设置 [密件抄送方]
     */
    public void setBcc(String bcc){
        this.bcc = bcc ;
        this.modify("bcc",bcc);
    }

    /**
     * 设置 [消息参数]
     */
    public void setParam(String param){
        this.param = param ;
        this.modify("param",param);
    }

    /**
     * 设置 [消息名称]
     */
    public void setIbizproMessagename(String ibizproMessagename){
        this.ibizproMessagename = ibizproMessagename ;
        this.modify("ibizpro_messagename",ibizproMessagename);
    }

    /**
     * 设置 [标题]
     */
    public void setSubject(String subject){
        this.subject = subject ;
        this.modify("subject",subject);
    }

    /**
     * 设置 [重发间隔时间]
     */
    public void setRetryintervaltime(Integer retryintervaltime){
        this.retryintervaltime = retryintervaltime ;
        this.modify("retryintervaltime",retryintervaltime);
    }

    /**
     * 设置 [消息类型]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [是否同步]
     */
    public void setIssync(Integer issync){
        this.issync = issync ;
        this.modify("issync",issync);
    }

    /**
     * 设置 [是否重发]
     */
    public void setIsretry(Integer isretry){
        this.isretry = isretry ;
        this.modify("isretry",isretry);
    }

    /**
     * 设置 [是否是链接消息]
     */
    public void setIslink(Integer islink){
        this.islink = islink ;
        this.modify("islink",islink);
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
        this.reset("ibizpro_messageid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


