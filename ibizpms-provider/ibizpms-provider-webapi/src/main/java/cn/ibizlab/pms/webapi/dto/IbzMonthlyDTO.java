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
 * 服务DTO对象[IbzMonthlyDTO]
 */
@Data
@ApiModel("月报")
@JsonFilter(value = "dtofieldfilter")
public class IbzMonthlyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 属性 [PLANSNEXTMONTH]
     *
     */
    @JSONField(name = "plansnextmonth")
    @JsonProperty("plansnextmonth")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("下月计划")
    private String plansnextmonth;
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
     * 属性 [ISSUBMIT]
     *
     */
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("是否提交")
    private String issubmit;
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
     * 属性 [UPDATEMANNAME]
     *
     */
    @JSONField(name = "updatemanname")
    @JsonProperty("updatemanname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人名称")
    private String updatemanname;
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
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
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
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
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
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 属性 [IBZ_MONTHLYID]
     *
     */
    @JSONField(name = "ibzmonthlyid")
    @JsonProperty("ibzmonthlyid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("月报标识")
    private Long ibzmonthlyid;
    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 属性 [NEXTMONTHPLANSTASK]
     *
     */
    @JSONField(name = "nextmonthplanstask")
    @JsonProperty("nextmonthplanstask")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("下月计划任务")
    private String nextmonthplanstask;
    /**
     * 属性 [THISMONTHTASK]
     *
     */
    @JSONField(name = "thismonthtask")
    @JsonProperty("thismonthtask")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("本月完成任务")
    private String thismonthtask;
    /**
     * 属性 [WORKTHISMONTH]
     *
     */
    @JSONField(name = "workthismonth")
    @JsonProperty("workthismonth")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("本月工作")
    private String workthismonth;
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
     * 属性 [IBZ_MONTHLYNAME]
     *
     */
    @JSONField(name = "ibzmonthlyname")
    @JsonProperty("ibzmonthlyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("月报名称")
    private String ibzmonthlyname;
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
     * 属性 [MAILTO]
     *
     */
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("抄送给")
    private String mailto;

    /**
     * 设置 [REPORTSTATUS]
     */
    public void setReportstatus(String  reportstatus){
        this.reportstatus = reportstatus ;
        this.modify("reportstatus",reportstatus);
    }

    /**
     * 设置 [PLANSNEXTMONTH]
     */
    public void setPlansnextmonth(String  plansnextmonth){
        this.plansnextmonth = plansnextmonth ;
        this.modify("plansnextmonth",plansnextmonth);
    }

    /**
     * 设置 [ISSUBMIT]
     */
    public void setIssubmit(String  issubmit){
        this.issubmit = issubmit ;
        this.modify("issubmit",issubmit);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [SUBMITTIME]
     */
    public void setSubmittime(Timestamp  submittime){
        this.submittime = submittime ;
        this.modify("submittime",submittime);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [NEXTMONTHPLANSTASK]
     */
    public void setNextmonthplanstask(String  nextmonthplanstask){
        this.nextmonthplanstask = nextmonthplanstask ;
        this.modify("nextmonthplanstask",nextmonthplanstask);
    }

    /**
     * 设置 [THISMONTHTASK]
     */
    public void setThismonthtask(String  thismonthtask){
        this.thismonthtask = thismonthtask ;
        this.modify("thismonthtask",thismonthtask);
    }

    /**
     * 设置 [WORKTHISMONTH]
     */
    public void setWorkthismonth(String  workthismonth){
        this.workthismonth = workthismonth ;
        this.modify("workthismonth",workthismonth);
    }

    /**
     * 设置 [REPORTTO]
     */
    public void setReportto(String  reportto){
        this.reportto = reportto ;
        this.modify("reportto",reportto);
    }

    /**
     * 设置 [IBZ_MONTHLYNAME]
     */
    public void setIbzmonthlyname(String  ibzmonthlyname){
        this.ibzmonthlyname = ibzmonthlyname ;
        this.modify("ibz_monthlyname",ibzmonthlyname);
    }

    /**
     * 设置 [COMMENT]
     */
    public void setComment(String  comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }

    /**
     * 设置 [MAILTO]
     */
    public void setMailto(String  mailto){
        this.mailto = mailto ;
        this.modify("mailto",mailto);
    }


}


