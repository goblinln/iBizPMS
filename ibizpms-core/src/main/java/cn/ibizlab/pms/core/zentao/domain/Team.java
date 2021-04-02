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
 * 实体[群组]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_team", resultMap = "TeamResultMap")
@ApiModel("群组")
public class Team extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 最初预计
     */
    @DEField(defaultValue = "0")
    @TableField(value = "estimate")
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    @ApiModelProperty("最初预计")
    private Double estimate;
    /**
     * 总计消耗
     */
    @DEField(defaultValue = "0.00")
    @TableField(value = "consumed")
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("总计消耗")
    private Double consumed;
    /**
     * 可用工时/天
     */
    @DEField(defaultValue = "0.0")
    @TableField(value = "hours")
    @JSONField(name = "hours")
    @JsonProperty("hours")
    @ApiModelProperty("可用工时/天")
    private Double hours;
    /**
     * 角色
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "role")
    @JSONField(name = "role")
    @JsonProperty("role")
    @ApiModelProperty("角色")
    private String role;
    /**
     * 用户
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 团队类型
     */
    @DEField(defaultValue = "project")
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("团队类型")
    private String type;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 预计剩余
     */
    @DEField(defaultValue = "0.00")
    @TableField(value = "left")
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("预计剩余")
    private Double left;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "order")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 可用工日
     */
    @DEField(defaultValue = "0")
    @TableField(value = "days")
    @JSONField(name = "days")
    @JsonProperty("days")
    @ApiModelProperty("可用工日")
    private Integer days;
    /**
     * 加盟日
     */
    @TableField(value = "join")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "join", format = "yyyy-MM-dd")
    @JsonProperty("join")
    @ApiModelProperty("加盟日")
    private Timestamp join;
    /**
     * 受限用户
     */
    @DEField(defaultValue = "no")
    @TableField(value = "limited")
    @JSONField(name = "limited")
    @JsonProperty("limited")
    @ApiModelProperty("受限用户")
    private String limited;
    /**
     * 关联编号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "root")
    @JSONField(name = "root")
    @JsonProperty("root")
    @ApiModelProperty("关联编号")
    private Long root;



    /**
     * 设置 [最初预计]
     */
    public void setEstimate(Double estimate) {
        this.estimate = estimate;
        this.modify("estimate", estimate);
    }

    /**
     * 设置 [总计消耗]
     */
    public void setConsumed(Double consumed) {
        this.consumed = consumed;
        this.modify("consumed", consumed);
    }

    /**
     * 设置 [可用工时/天]
     */
    public void setHours(Double hours) {
        this.hours = hours;
        this.modify("hours", hours);
    }

    /**
     * 设置 [角色]
     */
    public void setRole(String role) {
        this.role = role;
        this.modify("role", role);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [团队类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [预计剩余]
     */
    public void setLeft(Double left) {
        this.left = left;
        this.modify("left", left);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [可用工日]
     */
    public void setDays(Integer days) {
        this.days = days;
        this.modify("days", days);
    }

    /**
     * 设置 [加盟日]
     */
    public void setJoin(Timestamp join) {
        this.join = join;
        this.modify("join", join);
    }

    /**
     * 格式化日期 [加盟日]
     */
    public String formatJoin() {
        if (this.join == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(join);
    }
    /**
     * 设置 [受限用户]
     */
    public void setLimited(String limited) {
        this.limited = limited;
        this.modify("limited", limited);
    }

    /**
     * 设置 [关联编号]
     */
    public void setRoot(Long root) {
        this.root = root;
        this.modify("root", root);
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


