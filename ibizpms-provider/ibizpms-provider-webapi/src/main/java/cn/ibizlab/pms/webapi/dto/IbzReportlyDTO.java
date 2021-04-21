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
 * 服务DTO对象[IbzReportlyDTO]
 */
@Data
@ApiModel("汇报")
public class IbzReportlyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("附件")
    private String files;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("用户")
    private String account;

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
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 属性 [MAILTO]
     *
     */
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("抄送给")
    private String mailto;

    /**
     * 属性 [IBZ_REPORTLYID]
     *
     */
    @JSONField(name = "ibzreportlyid")
    @JsonProperty("ibzreportlyid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("汇报标识")
    private Long ibzreportlyid;

    /**
     * 属性 [IBZ_REPORTLYNAME]
     *
     */
    @JSONField(name = "ibzreportlyname")
    @JsonProperty("ibzreportlyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("汇报名称")
    private String ibzreportlyname;

    /**
     * 属性 [REPORTSTATUS]
     *
     */
    @JSONField(name = "reportstatus")
    @JsonProperty("reportstatus")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("状态")
    private String reportstatus;

    /**
     * 属性 [SUBMITTIME]
     *
     */
    @JsonFormat(pattern="HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "submittime" , format="HH:mm:ss")
    @JsonProperty("submittime")
    @ApiModelProperty("提交时间")
    private Timestamp submittime;

    /**
     * 属性 [REPORTTOPK]
     *
     */
    @JSONField(name = "reporttopk")
    @JsonProperty("reporttopk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("汇报给（选择）")
    private String reporttopk;

    /**
     * 属性 [REPORTTO]
     *
     */
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("汇报给")
    private String reportto;

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
     * 属性 [ISSUBMIT]
     *
     */
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("是否提交")
    private String issubmit;

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
     * 属性 [CONTENT]
     *
     */
    @JSONField(name = "content")
    @JsonProperty("content")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("工作内容")
    private String content;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("汇报日期")
    private Timestamp date;

    /**
     * 属性 [MAILTOPK]
     *
     */
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("抄送给（选择）")
    private String mailtopk;


    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [MAILTO]
     */
    public void setMailto(String  mailto){
        this.mailto = mailto ;
        this.modify("mailto",mailto);
    }

    /**
     * 设置 [IBZ_REPORTLYNAME]
     */
    public void setIbzreportlyname(String  ibzreportlyname){
        this.ibzreportlyname = ibzreportlyname ;
        this.modify("ibz_reportlyname",ibzreportlyname);
    }

    /**
     * 设置 [REPORTSTATUS]
     */
    public void setReportstatus(String  reportstatus){
        this.reportstatus = reportstatus ;
        this.modify("reportstatus",reportstatus);
    }

    /**
     * 设置 [SUBMITTIME]
     */
    public void setSubmittime(Timestamp  submittime){
        this.submittime = submittime ;
        this.modify("submittime",submittime);
    }

    /**
     * 设置 [REPORTTO]
     */
    public void setReportto(String  reportto){
        this.reportto = reportto ;
        this.modify("reportto",reportto);
    }

    /**
     * 设置 [ISSUBMIT]
     */
    public void setIssubmit(String  issubmit){
        this.issubmit = issubmit ;
        this.modify("issubmit",issubmit);
    }

    /**
     * 设置 [CONTENT]
     */
    public void setContent(String  content){
        this.content = content ;
        this.modify("content",content);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }


}


