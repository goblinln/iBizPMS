package cn.ibizlab.pms.core.zentao.domain;

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
 * 实体[score]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_score", resultMap = "ScoreResultMap")
@ApiModel("score")
public class Score extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * method
     */
    @TableField(value = "`METHOD`")
    @JSONField(name = "method")
    @JsonProperty("method")
    @ApiModelProperty("method")
    private String method;
    /**
     * score
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`SCORE`")
    @JSONField(name = "score")
    @JsonProperty("score")
    @ApiModelProperty("score")
    private Integer score;
    /**
     * time
     */
    @TableField(value = "`TIME`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "time", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("time")
    @ApiModelProperty("time")
    private Timestamp time;
    /**
     * account
     */
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("account")
    private String account;
    /**
     * before
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BEFORE`")
    @JSONField(name = "before")
    @JsonProperty("before")
    @ApiModelProperty("before")
    private Integer before;
    /**
     * desc
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * after
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`AFTER`")
    @JSONField(name = "after")
    @JsonProperty("after")
    @ApiModelProperty("after")
    private Integer after;
    /**
     * module
     */
    @TableField(value = "`MODULE`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("module")
    private String module;



    /**
     * 设置 [method]
     */
    public void setMethod(String method) {
        this.method = method;
        this.modify("method", method);
    }

    /**
     * 设置 [score]
     */
    public void setScore(Integer score) {
        this.score = score;
        this.modify("score", score);
    }

    /**
     * 设置 [time]
     */
    public void setTime(Timestamp time) {
        this.time = time;
        this.modify("time", time);
    }

    /**
     * 格式化日期 [time]
     */
    public String formatTime() {
        if (this.time == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
    /**
     * 设置 [account]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [before]
     */
    public void setBefore(Integer before) {
        this.before = before;
        this.modify("before", before);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [after]
     */
    public void setAfter(Integer after) {
        this.after = after;
        this.modify("after", after);
    }

    /**
     * 设置 [module]
     */
    public void setModule(String module) {
        this.module = module;
        this.modify("module", module);
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


