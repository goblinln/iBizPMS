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
 * 实体[汇报汇总]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "t_ibz_daily", resultMap = "IbzReportResultMap")
@ApiModel("汇报汇总")
public class IbzReport extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 更新人名称
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "updatemanname")
    @JSONField(name = "updatemanname")
    @JsonProperty("updatemanname")
    @ApiModelProperty("更新人名称")
    private String updatemanname;
    /**
     * 抄送给
     */
    @TableField(value = "mailto")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 汇报标识
     */
    @DEField(name = "ibz_dailyid", isKeyField = true)
    @TableId(value = "ibz_dailyid", type = IdType.ASSIGN_ID)
    @JSONField(name = "ibzdailyid")
    @JsonProperty("ibzdailyid")
    @ApiModelProperty("汇报标识")
    private Long ibzdailyid;
    /**
     * 未读汇报数
     */
    @TableField(exist = false)
    @JSONField(name = "reportlycnt")
    @JsonProperty("reportlycnt")
    @ApiModelProperty("未读汇报数")
    private Integer reportlycnt;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "updatedate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 工作
     */
    @TableField(value = "worktoday")
    @JSONField(name = "worktoday")
    @JsonProperty("worktoday")
    @ApiModelProperty("工作")
    private String worktoday;
    /**
     * 未读月报数
     */
    @TableField(exist = false)
    @JSONField(name = "monthlycnt")
    @JsonProperty("monthlycnt")
    @ApiModelProperty("未读月报数")
    private Integer monthlycnt;
    /**
     * 未读日报数
     */
    @TableField(exist = false)
    @JSONField(name = "dailycnt")
    @JsonProperty("dailycnt")
    @ApiModelProperty("未读日报数")
    private Integer dailycnt;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createdate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 完成任务
     */
    @TableField(value = "todaytask")
    @JSONField(name = "todaytask")
    @JsonProperty("todaytask")
    @ApiModelProperty("完成任务")
    private String todaytask;
    /**
     * 用户
     */
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 类型
     */
    @TableField(exist = false)
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "createman", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "updateman")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 汇报名称
     */
    @DEField(name = "ibz_dailyname")
    @TableField(value = "ibz_dailyname")
    @JSONField(name = "ibzdailyname")
    @JsonProperty("ibzdailyname")
    @ApiModelProperty("汇报名称")
    private String ibzdailyname;
    /**
     * 汇报给
     */
    @TableField(value = "reportto")
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    @ApiModelProperty("汇报给")
    private String reportto;
    /**
     * 提交时间
     */
    @TableField(exist = false)
    @JSONField(name = "submittime")
    @JsonProperty("submittime")
    @ApiModelProperty("提交时间")
    private String submittime;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 建立人名称
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "createmanname")
    @JSONField(name = "createmanname")
    @JsonProperty("createmanname")
    @ApiModelProperty("建立人名称")
    private String createmanname;
    /**
     * 计划
     */
    @TableField(value = "planstomorrow")
    @JSONField(name = "planstomorrow")
    @JsonProperty("planstomorrow")
    @ApiModelProperty("计划")
    private String planstomorrow;
    /**
     * 状态
     */
    @DEField(defaultValue = "0")
    @TableField(value = "reportstatus")
    @JSONField(name = "reportstatus")
    @JsonProperty("reportstatus")
    @ApiModelProperty("状态")
    private String reportstatus;
    /**
     * 日期
     */
    @TableField(value = "date")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
    /**
     * 是否提交
     */
    @DEField(defaultValue = "0")
    @TableField(value = "issubmit")
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @ApiModelProperty("是否提交")
    private String issubmit;
    /**
     * 其他事项
     */
    @TableField(value = "comment")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("其他事项")
    private String comment;
    /**
     * 计划任务
     */
    @TableField(value = "tomorrowplanstask")
    @JSONField(name = "tomorrowplanstask")
    @JsonProperty("tomorrowplanstask")
    @ApiModelProperty("计划任务")
    private String tomorrowplanstask;



    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [工作]
     */
    public void setWorktoday(String worktoday) {
        this.worktoday = worktoday;
        this.modify("worktoday", worktoday);
    }

    /**
     * 设置 [完成任务]
     */
    public void setTodaytask(String todaytask) {
        this.todaytask = todaytask;
        this.modify("todaytask", todaytask);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [汇报名称]
     */
    public void setIbzdailyname(String ibzdailyname) {
        this.ibzdailyname = ibzdailyname;
        this.modify("ibz_dailyname", ibzdailyname);
    }

    /**
     * 设置 [汇报给]
     */
    public void setReportto(String reportto) {
        this.reportto = reportto;
        this.modify("reportto", reportto);
    }

    /**
     * 设置 [计划]
     */
    public void setPlanstomorrow(String planstomorrow) {
        this.planstomorrow = planstomorrow;
        this.modify("planstomorrow", planstomorrow);
    }

    /**
     * 设置 [状态]
     */
    public void setReportstatus(String reportstatus) {
        this.reportstatus = reportstatus;
        this.modify("reportstatus", reportstatus);
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
     * 设置 [是否提交]
     */
    public void setIssubmit(String issubmit) {
        this.issubmit = issubmit;
        this.modify("issubmit", issubmit);
    }

    /**
     * 设置 [其他事项]
     */
    public void setComment(String comment) {
        this.comment = comment;
        this.modify("comment", comment);
    }

    /**
     * 设置 [计划任务]
     */
    public void setTomorrowplanstask(String tomorrowplanstask) {
        this.tomorrowplanstask = tomorrowplanstask;
        this.modify("tomorrowplanstask", tomorrowplanstask);
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


