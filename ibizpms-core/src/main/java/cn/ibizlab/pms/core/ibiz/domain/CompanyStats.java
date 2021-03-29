package cn.ibizlab.pms.core.ibiz.domain;

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
 * 实体[公司动态汇总]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_action", resultMap = "CompanyStatsResultMap")
public class CompanyStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录次数
     */
    @TableField(exist = false)
    @JSONField(name = "logincnt")
    @JsonProperty("logincnt")
    private Integer logincnt;
    /**
     * 新增需求数
     */
    @TableField(exist = false)
    @JSONField(name = "openedstorycnt")
    @JsonProperty("openedstorycnt")
    private Integer openedstorycnt;
    /**
     * 日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "ztdate", format = "yyyy-MM-dd")
    @JsonProperty("ztdate")
    private Timestamp ztdate;
    /**
     * 日志日期
     */
    @TableField(value = "`date`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 关闭需求数
     */
    @TableField(exist = false)
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    private Integer closedstorycnt;
    /**
     * 新增Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "openedbugcnt")
    @JsonProperty("openedbugcnt")
    private Integer openedbugcnt;
    /**
     * 日志工时
     */
    @TableField(exist = false)
    @JSONField(name = "loghours")
    @JsonProperty("loghours")
    private Integer loghours;
    /**
     * 动态数
     */
    @TableField(exist = false)
    @JSONField(name = "dynamiccnt")
    @JsonProperty("dynamiccnt")
    private Integer dynamiccnt;
    /**
     * 完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "finishedtaskcnt")
    @JsonProperty("finishedtaskcnt")
    private Integer finishedtaskcnt;
    /**
     * 解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "resolvedbugcnt")
    @JsonProperty("resolvedbugcnt")
    private Integer resolvedbugcnt;
    /**
     * 备注
     */
    @TableField(value = "`comment`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * 新增任务数
     */
    @TableField(exist = false)
    @JSONField(name = "openedtaskcnt")
    @JsonProperty("openedtaskcnt")
    private Integer openedtaskcnt;
    /**
     * 标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;



    /**
     * 设置 [日志日期]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [日志日期]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [备注]
     */
    public void setComment(String comment) {
        this.comment = comment;
        this.modify("comment", comment);
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
        this.reset("id");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


