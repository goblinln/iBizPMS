package cn.ibizlab.pms.core.report.domain;

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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[日报]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_DAILY", resultMap = "IbzDailyResultMap")
@ApiModel("日报")
public class IbzDaily extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`UPDATEDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 提交时间
     */
    @TableField(value = "`SUBMITTIME`")
    @JsonFormat(pattern = "HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "submittime", format = "HH:mm:ss")
    @JsonProperty("submittime")
    @ApiModelProperty("提交时间")
    private Timestamp submittime;
    /**
     * 完成任务
     */
    @DEField(dict = "MyCompleteTask")
    @TableField(value = "`TODAYTASK`")
    @JSONField(name = "todaytask")
    @JsonProperty("todaytask")
    @ApiModelProperty("完成任务")
    private String todaytask;
    /**
     * 今日工作
     */
    @TableField(value = "`WORKTODAY`")
    @JSONField(name = "worktoday")
    @JsonProperty("worktoday")
    @ApiModelProperty("今日工作")
    private String worktoday;
    /**
     * 明日计划任务
     */
    @DEField(dict = "MyPlanTask")
    @TableField(value = "`TOMORROWPLANSTASK`")
    @JSONField(name = "tomorrowplanstask")
    @JsonProperty("tomorrowplanstask")
    @ApiModelProperty("明日计划任务")
    private String tomorrowplanstask;
    /**
     * 汇报给
     */
    @DEField(dict = "UserRealName_Gird")
    @TableField(value = "`REPORTTO`")
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    @ApiModelProperty("汇报给")
    private String reportto;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN, dict = "SysOperator")
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`CREATEDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 是否提交
     */
    @DEField(defaultValue = "0", dict = "YesNo")
    @TableField(value = "`ISSUBMIT`")
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @ApiModelProperty("是否提交")
    private String issubmit;
    /**
     * 日期
     */
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 更新人名称
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEMANNAME`")
    @JSONField(name = "updatemanname")
    @JsonProperty("updatemanname")
    @ApiModelProperty("更新人名称")
    private String updatemanname;
    /**
     * 抄送给（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @ApiModelProperty("抄送给（选择）")
    private String mailtopk;
    /**
     * 状态
     */
    @DEField(defaultValue = "0", dict = "ReportStatus")
    @TableField(value = "`REPORTSTATUS`")
    @JSONField(name = "reportstatus")
    @JsonProperty("reportstatus")
    @ApiModelProperty("状态")
    private String reportstatus;
    /**
     * 其他事项
     */
    @TableField(value = "`COMMENT`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("其他事项")
    private String comment;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN, dict = "SysOperator")
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 明日计划
     */
    @TableField(value = "`PLANSTOMORROW`")
    @JSONField(name = "planstomorrow")
    @JsonProperty("planstomorrow")
    @ApiModelProperty("明日计划")
    private String planstomorrow;
    /**
     * 抄送给
     */
    @DEField(dict = "UserRealName_Gird")
    @TableField(value = "`MAILTO`")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 日报标识
     */
    @DEField(name = "ibz_dailyid", isKeyField = true)
    @TableId(value = "`IBZ_DAILYID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "ibzdailyid")
    @JsonProperty("ibzdailyid")
    @ApiModelProperty("日报标识")
    private Long ibzdailyid;
    /**
     * 用户
     */
    @DEField(dict = "UserRealName")
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 汇报给（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "reporttopk")
    @JsonProperty("reporttopk")
    @ApiModelProperty("汇报给（选择）")
    private String reporttopk;
    /**
     * 建立人名称
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`CREATEMANNAME`")
    @JSONField(name = "createmanname")
    @JsonProperty("createmanname")
    @ApiModelProperty("建立人名称")
    private String createmanname;
    /**
     * 日报名称
     */
    @DEField(name = "ibz_dailyname")
    @TableField(value = "`IBZ_DAILYNAME`")
    @JSONField(name = "ibzdailyname")
    @JsonProperty("ibzdailyname")
    @ApiModelProperty("日报名称")
    private String ibzdailyname;



    /**
     * 设置 [提交时间]
     */
    public void setSubmittime(Timestamp submittime) {
        this.submittime = submittime;
        this.modify("submittime", submittime);
    }

    /**
     * 格式化日期 [提交时间]
     */
    public String formatSubmittime() {
        if (this.submittime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(submittime);
    }
    /**
     * 设置 [完成任务]
     */
    public void setTodaytask(String todaytask) {
        this.todaytask = todaytask;
        this.modify("todaytask", todaytask);
    }

    /**
     * 设置 [今日工作]
     */
    public void setWorktoday(String worktoday) {
        this.worktoday = worktoday;
        this.modify("worktoday", worktoday);
    }

    /**
     * 设置 [明日计划任务]
     */
    public void setTomorrowplanstask(String tomorrowplanstask) {
        this.tomorrowplanstask = tomorrowplanstask;
        this.modify("tomorrowplanstask", tomorrowplanstask);
    }

    /**
     * 设置 [汇报给]
     */
    public void setReportto(String reportto) {
        this.reportto = reportto;
        this.modify("reportto", reportto);
    }

    /**
     * 设置 [是否提交]
     */
    public void setIssubmit(String issubmit) {
        this.issubmit = issubmit;
        this.modify("issubmit", issubmit);
    }

    /**
     * 设置 [日期]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [日期]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [状态]
     */
    public void setReportstatus(String reportstatus) {
        this.reportstatus = reportstatus;
        this.modify("reportstatus", reportstatus);
    }

    /**
     * 设置 [其他事项]
     */
    public void setComment(String comment) {
        this.comment = comment;
        this.modify("comment", comment);
    }

    /**
     * 设置 [明日计划]
     */
    public void setPlanstomorrow(String planstomorrow) {
        this.planstomorrow = planstomorrow;
        this.modify("planstomorrow", planstomorrow);
    }

    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [日报名称]
     */
    public void setIbzdailyname(String ibzdailyname) {
        this.ibzdailyname = ibzdailyname;
        this.modify("ibz_dailyname", ibzdailyname);
    }


    @Override
    public Serializable getDefaultKey(boolean gen) {
        return IdWorker.getId();
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
        this.reset("ibz_dailyid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


