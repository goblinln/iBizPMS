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
public class IbzWeekly extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 周报名称
     */
    @DEField(name = "ibz_weeklyname")
    @TableField(value = "`ibz_weeklyname`")
    @JSONField(name = "ibzweeklyname")
    @JsonProperty("ibzweeklyname")
    private String ibzweeklyname;
    /**
     * 周报标识
     */
    @DEField(name = "ibz_weeklyid", isKeyField = true)
    @TableId(value = "ibz_weeklyid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibzweeklyid")
    @JsonProperty("ibzweeklyid")
    private String ibzweeklyid;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 用户
     */
    @TableField(value = "`account`")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * 抄送给
     */
    @TableField(value = "`mailto`")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    private String mailto;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    private String files;
    /**
     * 是否提交
     */
    @TableField(value = "`issubmit`")
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    private String issubmit;
    /**
     * 汇报给
     */
    @TableField(value = "`reportto`")
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    private String reportto;
    /**
     * 其他事项
     */
    @TableField(value = "`comment`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * 日期
     */
    @TableField(value = "`date`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 本周工作
     */
    @TableField(value = "`workthisweek`")
    @JSONField(name = "workthisweek")
    @JsonProperty("workthisweek")
    private String workthisweek;



    /**
     * 设置 [周报名称]
     */
    public void setIbzweeklyname(String ibzweeklyname) {
        this.ibzweeklyname = ibzweeklyname;
        this.modify("ibz_weeklyname", ibzweeklyname);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [是否提交]
     */
    public void setIssubmit(String issubmit) {
        this.issubmit = issubmit;
        this.modify("issubmit", issubmit);
    }

    /**
     * 设置 [汇报给]
     */
    public void setReportto(String reportto) {
        this.reportto = reportto;
        this.modify("reportto", reportto);
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
    /**
     * 设置 [本周工作]
     */
    public void setWorkthisweek(String workthisweek) {
        this.workthisweek = workthisweek;
        this.modify("workthisweek", workthisweek);
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


