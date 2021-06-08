package cn.ibizlab.pms.standardapi.dto;

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
 * 服务DTO对象[WeeklyDTO]
 */
@Data
@ApiModel("个人周报")
@JsonFilter(value = "dtofieldfilter")
public class WeeklyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ISSUBMIT]
     *
     */
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("是否提交")
    private String issubmit;
    /**
     * 属性 [PLANNEXTWEEK]
     *
     */
    @JSONField(name = "plannextweek")
    @JsonProperty("plannextweek")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("下周计划")
    private String plannextweek;
    /**
     * 属性 [IBZ_WEEKLYNAME]
     *
     */
    @JSONField(name = "ibzweeklyname")
    @JsonProperty("ibzweeklyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("周报名称")
    private String ibzweeklyname;
    /**
     * 属性 [MAILTO]
     *
     */
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 属性 [REPORTTOPK]
     *
     */
    @JSONField(name = "reporttopk")
    @JsonProperty("reporttopk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("汇报给(选择)")
    private String reporttopk;
    /**
     * 属性 [NEXTWEEKTASK]
     *
     */
    @JSONField(name = "nextweektask")
    @JsonProperty("nextweektask")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("下周计划任务")
    private String nextweektask;
    /**
     * 属性 [IBZ_WEEKLYID]
     *
     */
    @JSONField(name = "ibzweeklyid")
    @JsonProperty("ibzweeklyid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("周报标识")
    private Long ibzweeklyid;
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
     * 属性 [MAILTOPK]
     *
     */
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("抄送给(选择)")
    private String mailtopk;
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
     * 属性 [REPORTSTATUS]
     *
     */
    @JSONField(name = "reportstatus")
    @JsonProperty("reportstatus")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("状态")
    private String reportstatus;
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
     * 属性 [WORKTHISWEEK]
     *
     */
    @JSONField(name = "workthisweek")
    @JsonProperty("workthisweek")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("本周工作")
    private String workthisweek;
    /**
     * 属性 [REPORTTO]
     *
     */
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("汇报给")
    private String reportto;
    /**
     * 属性 [UPDATEMANNAME]
     *
     */
    @JSONField(name = "updatemanname")
    @JsonProperty("updatemanname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人名称")
    private String updatemanname;
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
     * 属性 [THISWEEKTASK]
     *
     */
    @JSONField(name = "thisweektask")
    @JsonProperty("thisweektask")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("本周完成任务")
    private String thisweektask;
    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("其他事项")
    private String comment;
    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
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
     * 属性 [CREATEMANNAME]
     *
     */
    @JSONField(name = "createmanname")
    @JsonProperty("createmanname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人名称")
    private String createmanname;
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
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 设置 [ISSUBMIT]
     */
    public void setIssubmit(String  issubmit){
        this.issubmit = issubmit ;
        this.modify("issubmit",issubmit);
    }

    /**
     * 设置 [PLANNEXTWEEK]
     */
    public void setPlannextweek(String  plannextweek){
        this.plannextweek = plannextweek ;
        this.modify("plannextweek",plannextweek);
    }

    /**
     * 设置 [IBZ_WEEKLYNAME]
     */
    public void setIbzweeklyname(String  ibzweeklyname){
        this.ibzweeklyname = ibzweeklyname ;
        this.modify("ibz_weeklyname",ibzweeklyname);
    }

    /**
     * 设置 [MAILTO]
     */
    public void setMailto(String  mailto){
        this.mailto = mailto ;
        this.modify("mailto",mailto);
    }

    /**
     * 设置 [NEXTWEEKTASK]
     */
    public void setNextweektask(String  nextweektask){
        this.nextweektask = nextweektask ;
        this.modify("nextweektask",nextweektask);
    }

    /**
     * 设置 [SUBMITTIME]
     */
    public void setSubmittime(Timestamp  submittime){
        this.submittime = submittime ;
        this.modify("submittime",submittime);
    }

    /**
     * 设置 [REPORTSTATUS]
     */
    public void setReportstatus(String  reportstatus){
        this.reportstatus = reportstatus ;
        this.modify("reportstatus",reportstatus);
    }

    /**
     * 设置 [WORKTHISWEEK]
     */
    public void setWorkthisweek(String  workthisweek){
        this.workthisweek = workthisweek ;
        this.modify("workthisweek",workthisweek);
    }

    /**
     * 设置 [REPORTTO]
     */
    public void setReportto(String  reportto){
        this.reportto = reportto ;
        this.modify("reportto",reportto);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [THISWEEKTASK]
     */
    public void setThisweektask(String  thisweektask){
        this.thisweektask = thisweektask ;
        this.modify("thisweektask",thisweektask);
    }

    /**
     * 设置 [COMMENT]
     */
    public void setComment(String  comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }


}


