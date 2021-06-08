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
 * 服务DTO对象[IbzReportDTO]
 */
@Data
@ApiModel("汇报汇总")
@JsonFilter(value = "dtofieldfilter")
public class IbzReportDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [MAILTO]
     *
     */
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 属性 [IBZ_DAILYID]
     *
     */
    @JSONField(name = "ibzdailyid")
    @JsonProperty("ibzdailyid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("汇报标识")
    private Long ibzdailyid;
    /**
     * 属性 [REPORTLYCNT]
     *
     */
    @JSONField(name = "reportlycnt")
    @JsonProperty("reportlycnt")
    @ApiModelProperty("未读汇报数")
    private Integer reportlycnt;
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
     * 属性 [WORKTODAY]
     *
     */
    @JSONField(name = "worktoday")
    @JsonProperty("worktoday")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("工作")
    private String worktoday;
    /**
     * 属性 [MONTHLYCNT]
     *
     */
    @JSONField(name = "monthlycnt")
    @JsonProperty("monthlycnt")
    @ApiModelProperty("未读月报数")
    private Integer monthlycnt;
    /**
     * 属性 [DAILYCNT]
     *
     */
    @JSONField(name = "dailycnt")
    @JsonProperty("dailycnt")
    @ApiModelProperty("未读日报数")
    private Integer dailycnt;
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
     * 属性 [TODAYTASK]
     *
     */
    @JSONField(name = "todaytask")
    @JsonProperty("todaytask")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("完成任务")
    private String todaytask;
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
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("类型")
    private String type;
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
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 属性 [IBZ_DAILYNAME]
     *
     */
    @JSONField(name = "ibzdailyname")
    @JsonProperty("ibzdailyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("汇报名称")
    private String ibzdailyname;
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
     * 属性 [SUBMITTIME]
     *
     */
    @JSONField(name = "submittime")
    @JsonProperty("submittime")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("提交时间")
    private String submittime;
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
     * 属性 [CREATEMANNAME]
     *
     */
    @JSONField(name = "createmanname")
    @JsonProperty("createmanname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人名称")
    private String createmanname;
    /**
     * 属性 [PLANSTOMORROW]
     *
     */
    @JSONField(name = "planstomorrow")
    @JsonProperty("planstomorrow")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("计划")
    private String planstomorrow;
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
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
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
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("其他事项")
    private String comment;
    /**
     * 属性 [TOMORROWPLANSTASK]
     *
     */
    @JSONField(name = "tomorrowplanstask")
    @JsonProperty("tomorrowplanstask")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("计划任务")
    private String tomorrowplanstask;

    /**
     * 设置 [MAILTO]
     */
    public void setMailto(String  mailto){
        this.mailto = mailto ;
        this.modify("mailto",mailto);
    }

    /**
     * 设置 [WORKTODAY]
     */
    public void setWorktoday(String  worktoday){
        this.worktoday = worktoday ;
        this.modify("worktoday",worktoday);
    }

    /**
     * 设置 [TODAYTASK]
     */
    public void setTodaytask(String  todaytask){
        this.todaytask = todaytask ;
        this.modify("todaytask",todaytask);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [IBZ_DAILYNAME]
     */
    public void setIbzdailyname(String  ibzdailyname){
        this.ibzdailyname = ibzdailyname ;
        this.modify("ibz_dailyname",ibzdailyname);
    }

    /**
     * 设置 [REPORTTO]
     */
    public void setReportto(String  reportto){
        this.reportto = reportto ;
        this.modify("reportto",reportto);
    }

    /**
     * 设置 [PLANSTOMORROW]
     */
    public void setPlanstomorrow(String  planstomorrow){
        this.planstomorrow = planstomorrow ;
        this.modify("planstomorrow",planstomorrow);
    }

    /**
     * 设置 [REPORTSTATUS]
     */
    public void setReportstatus(String  reportstatus){
        this.reportstatus = reportstatus ;
        this.modify("reportstatus",reportstatus);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [ISSUBMIT]
     */
    public void setIssubmit(String  issubmit){
        this.issubmit = issubmit ;
        this.modify("issubmit",issubmit);
    }

    /**
     * 设置 [COMMENT]
     */
    public void setComment(String  comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }

    /**
     * 设置 [TOMORROWPLANSTASK]
     */
    public void setTomorrowplanstask(String  tomorrowplanstask){
        this.tomorrowplanstask = tomorrowplanstask ;
        this.modify("tomorrowplanstask",tomorrowplanstask);
    }


}


