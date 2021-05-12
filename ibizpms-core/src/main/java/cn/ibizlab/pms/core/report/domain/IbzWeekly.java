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
 * 实体[周报]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_WEEKLY", resultMap = "IbzWeeklyResultMap")
@ApiModel("周报")
public class IbzWeekly extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否提交
     */
    @TableField(value = "`ISSUBMIT`")
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @ApiModelProperty("是否提交")
    private String issubmit;
    /**
     * 下周计划
     */
    @TableField(value = "`PLANNEXTWEEK`")
    @JSONField(name = "plannextweek")
    @JsonProperty("plannextweek")
    @ApiModelProperty("下周计划")
    private String plannextweek;
    /**
     * 周报名称
     */
    @DEField(name = "ibz_weeklyname")
    @TableField(value = "`IBZ_WEEKLYNAME`")
    @JSONField(name = "ibzweeklyname")
    @JsonProperty("ibzweeklyname")
    @ApiModelProperty("周报名称")
    private String ibzweeklyname;
    /**
     * 抄送给
     */
    @TableField(value = "`MAILTO`")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 汇报给(选择)
     */
    @TableField(exist = false)
    @JSONField(name = "reporttopk")
    @JsonProperty("reporttopk")
    @ApiModelProperty("汇报给(选择)")
    private String reporttopk;
    /**
     * 下周计划任务
     */
    @TableField(value = "`NEXTWEEKTASK`")
    @JSONField(name = "nextweektask")
    @JsonProperty("nextweektask")
    @ApiModelProperty("下周计划任务")
    private String nextweektask;
    /**
     * 周报标识
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`IBZ_WEEKLYID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "ibzweeklyid")
    @JsonProperty("ibzweeklyid")
    @ApiModelProperty("周报标识")
    private Long ibzweeklyid;
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
     * 抄送给(选择)
     */
    @TableField(exist = false)
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @ApiModelProperty("抄送给(选择)")
    private String mailtopk;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 状态
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`REPORTSTATUS`")
    @JSONField(name = "reportstatus")
    @JsonProperty("reportstatus")
    @ApiModelProperty("状态")
    private String reportstatus;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 本周工作
     */
    @TableField(value = "`WORKTHISWEEK`")
    @JSONField(name = "workthisweek")
    @JsonProperty("workthisweek")
    @ApiModelProperty("本周工作")
    private String workthisweek;
    /**
     * 汇报给
     */
    @TableField(value = "`REPORTTO`")
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    @ApiModelProperty("汇报给")
    private String reportto;
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
     * 用户
     */
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 本周完成任务
     */
    @TableField(value = "`THISWEEKTASK`")
    @JSONField(name = "thisweektask")
    @JsonProperty("thisweektask")
    @ApiModelProperty("本周完成任务")
    private String thisweektask;
    /**
     * 其他事项
     */
    @TableField(value = "`COMMENT`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("其他事项")
    private String comment;
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
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
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
     * 设置 [是否提交]
     */
    public void setIssubmit(String issubmit) {
        this.issubmit = issubmit;
        this.modify("issubmit", issubmit);
    }

    /**
     * 设置 [下周计划]
     */
    public void setPlannextweek(String plannextweek) {
        this.plannextweek = plannextweek;
        this.modify("plannextweek", plannextweek);
    }

    /**
     * 设置 [周报名称]
     */
    public void setIbzweeklyname(String ibzweeklyname) {
        this.ibzweeklyname = ibzweeklyname;
        this.modify("ibz_weeklyname", ibzweeklyname);
    }

    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [下周计划任务]
     */
    public void setNextweektask(String nextweektask) {
        this.nextweektask = nextweektask;
        this.modify("nextweektask", nextweektask);
    }

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
     * 设置 [状态]
     */
    public void setReportstatus(String reportstatus) {
        this.reportstatus = reportstatus;
        this.modify("reportstatus", reportstatus);
    }

    /**
     * 设置 [本周工作]
     */
    public void setWorkthisweek(String workthisweek) {
        this.workthisweek = workthisweek;
        this.modify("workthisweek", workthisweek);
    }

    /**
     * 设置 [汇报给]
     */
    public void setReportto(String reportto) {
        this.reportto = reportto;
        this.modify("reportto", reportto);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [本周完成任务]
     */
    public void setThisweektask(String thisweektask) {
        this.thisweektask = thisweektask;
        this.modify("thisweektask", thisweektask);
    }

    /**
     * 设置 [其他事项]
     */
    public void setComment(String comment) {
        this.comment = comment;
        this.modify("comment", comment);
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
        this.reset("ibz_weeklyid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


