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
 * 服务DTO对象[IBIZProMessageDTO]
 */
@Data
@ApiModel("消息")
//@JsonFilter(value = "dtofieldfilter")
public class IBIZProMessageDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [SENDPROXYID]
     *
     */
    @JSONField(name = "sendproxyid")
    @JsonProperty("sendproxyid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("发送代理标识")
    private String sendproxyid;

    /**
     * 属性 [IBIZPRO_MESSAGENAME]
     *
     */
    @JSONField(name = "ibizpromessagename")
    @JsonProperty("ibizpromessagename")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息名称")
    private String ibizpromessagename;

    /**
     * 属性 [ISLINK]
     *
     */
    @JSONField(name = "islink")
    @JsonProperty("islink")
    @ApiModelProperty("是否是链接消息")
    private Integer islink;

    /**
     * 属性 [SUBJECT]
     *
     */
    @JSONField(name = "subject")
    @JsonProperty("subject")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("标题")
    private String subject;

    /**
     * 属性 [RETRYINTERVALTIME]
     *
     */
    @JSONField(name = "retryintervaltime")
    @JsonProperty("retryintervaltime")
    @ApiModelProperty("重发间隔时间")
    private Integer retryintervaltime;

    /**
     * 属性 [BCC]
     *
     */
    @JSONField(name = "bcc")
    @JsonProperty("bcc")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("密件抄送方")
    private String bcc;

    /**
     * 属性 [ISDONE]
     *
     */
    @JSONField(name = "isdone")
    @JsonProperty("isdone")
    @ApiModelProperty("是否完成")
    private Integer isdone;

    /**
     * 属性 [PARAM]
     *
     */
    @JSONField(name = "param")
    @JsonProperty("param")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("消息参数")
    private String param;

    /**
     * 属性 [IBIZPRO_MESSAGEID]
     *
     */
    @JSONField(name = "ibizpromessageid")
    @JsonProperty("ibizpromessageid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息标识")
    private String ibizpromessageid;

    /**
     * 属性 [RETRYTIMES]
     *
     */
    @JSONField(name = "retrytimes")
    @JsonProperty("retrytimes")
    @ApiModelProperty("重发次数")
    private Integer retrytimes;

    /**
     * 属性 [ISSYNC]
     *
     */
    @JSONField(name = "issync")
    @JsonProperty("issync")
    @ApiModelProperty("是否同步")
    private Integer issync;

    /**
     * 属性 [FROM]
     *
     */
    @JSONField(name = "from")
    @JsonProperty("from")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("发送方")
    private String from;

    /**
     * 属性 [CC]
     *
     */
    @JSONField(name = "cc")
    @JsonProperty("cc")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("抄送方")
    private String cc;

    /**
     * 属性 [SENDTIME]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "sendtime" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("sendtime")
    @ApiModelProperty("发送时间")
    private Timestamp sendtime;

    /**
     * 属性 [CONTENT]
     *
     */
    @JSONField(name = "content")
    @JsonProperty("content")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("内容")
    private String content;

    /**
     * 属性 [LINKURL]
     *
     */
    @JSONField(name = "linkurl")
    @JsonProperty("linkurl")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("链接地址")
    private String linkurl;

    /**
     * 属性 [ISREAD]
     *
     */
    @JSONField(name = "isread")
    @JsonProperty("isread")
    @ApiModelProperty("是否已读")
    private Integer isread;

    /**
     * 属性 [ISRETRY]
     *
     */
    @JSONField(name = "isretry")
    @JsonProperty("isretry")
    @ApiModelProperty("是否重发")
    private Integer isretry;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("消息类型")
    private String type;

    /**
     * 属性 [TO]
     *
     */
    @JSONField(name = "to")
    @JsonProperty("to")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("接收方")
    private String to;


    /**
     * 设置 [IBIZPRO_MESSAGENAME]
     */
    public void setIbizpromessagename(String  ibizpromessagename){
        this.ibizpromessagename = ibizpromessagename ;
        this.modify("ibizpro_messagename",ibizpromessagename);
    }

    /**
     * 设置 [ISLINK]
     */
    public void setIslink(Integer  islink){
        this.islink = islink ;
        this.modify("islink",islink);
    }

    /**
     * 设置 [SUBJECT]
     */
    public void setSubject(String  subject){
        this.subject = subject ;
        this.modify("subject",subject);
    }

    /**
     * 设置 [RETRYINTERVALTIME]
     */
    public void setRetryintervaltime(Integer  retryintervaltime){
        this.retryintervaltime = retryintervaltime ;
        this.modify("retryintervaltime",retryintervaltime);
    }

    /**
     * 设置 [BCC]
     */
    public void setBcc(String  bcc){
        this.bcc = bcc ;
        this.modify("bcc",bcc);
    }

    /**
     * 设置 [PARAM]
     */
    public void setParam(String  param){
        this.param = param ;
        this.modify("param",param);
    }

    /**
     * 设置 [RETRYTIMES]
     */
    public void setRetrytimes(Integer  retrytimes){
        this.retrytimes = retrytimes ;
        this.modify("retrytimes",retrytimes);
    }

    /**
     * 设置 [ISSYNC]
     */
    public void setIssync(Integer  issync){
        this.issync = issync ;
        this.modify("issync",issync);
    }

    /**
     * 设置 [FROM]
     */
    public void setFrom(String  from){
        this.from = from ;
        this.modify("from",from);
    }

    /**
     * 设置 [CC]
     */
    public void setCc(String  cc){
        this.cc = cc ;
        this.modify("cc",cc);
    }

    /**
     * 设置 [CONTENT]
     */
    public void setContent(String  content){
        this.content = content ;
        this.modify("content",content);
    }

    /**
     * 设置 [LINKURL]
     */
    public void setLinkurl(String  linkurl){
        this.linkurl = linkurl ;
        this.modify("linkurl",linkurl);
    }

    /**
     * 设置 [ISRETRY]
     */
    public void setIsretry(Integer  isretry){
        this.isretry = isretry ;
        this.modify("isretry",isretry);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [TO]
     */
    public void setTo(String  to){
        this.to = to ;
        this.modify("to",to);
    }


}


