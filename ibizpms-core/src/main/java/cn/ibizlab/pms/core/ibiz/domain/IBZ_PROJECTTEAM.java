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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;

/**
 * 实体[项目团队]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_team",resultMap = "IBZ_PROJECTTEAMResultMap")
public class IBZ_PROJECTTEAM extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 加盟日
     */
    @DEField(defaultValue = "0000-00-00")
    @TableField(value = "join")
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd")
    @JsonProperty("join")
    private Timestamp join;
    /**
     * 用户
     */
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * 可用工时/天
     */
    @DEField(defaultValue = "0.0")
    @TableField(value = "hours")
    @JSONField(name = "hours")
    @JsonProperty("hours")
    private Double hours;
    /**
     * 预计剩余
     */
    @DEField(defaultValue = "0.00")
    @TableField(value = "left")
    @JSONField(name = "left")
    @JsonProperty("left")
    private Double left;
    /**
     * 可用工日
     */
    @TableField(value = "days")
    @JSONField(name = "days")
    @JsonProperty("days")
    private Integer days;
    /**
     * 编号
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * 总计消耗
     */
    @DEField(defaultValue = "0.00")
    @TableField(value = "consumed")
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    private Double consumed;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "order")
    @JSONField(name = "order")
    @JsonProperty("order")
    private Integer order;
    /**
     * 最初预计
     */
    @DEField(defaultValue = "0.00")
    @TableField(value = "estimate")
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    private Double estimate;
    /**
     * 受限用户
     */
    @DEField(defaultValue = "no")
    @TableField(value = "limited")
    @JSONField(name = "limited")
    @JsonProperty("limited")
    private String limited;
    /**
     * 角色
     */
    @TableField(value = "role")
    @JSONField(name = "role")
    @JsonProperty("role")
    private String role;
    /**
     * 团队类型
     */
    @DEField(defaultValue = "project")
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 总计可用
     */
    @TableField(exist = false)
    @JSONField(name = "total")
    @JsonProperty("total")
    private Integer total;
    /**
     * 项目编号
     */
    @TableField(value = "root")
    @JSONField(name = "root")
    @JsonProperty("root")
    private BigInteger root;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project projectteam;



    /**
     * 设置 [加盟日]
     */
    public void setJoin(Timestamp join){
        this.join = join ;
        this.modify("join",join);
    }

    /**
     * 格式化日期 [加盟日]
     */
    public String formatJoin(){
        if (this.join == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(join);
    }
    /**
     * 设置 [用户]
     */
    public void setAccount(String account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [可用工时/天]
     */
    public void setHours(Double hours){
        this.hours = hours ;
        this.modify("hours",hours);
    }

    /**
     * 设置 [预计剩余]
     */
    public void setLeft(Double left){
        this.left = left ;
        this.modify("left",left);
    }

    /**
     * 设置 [可用工日]
     */
    public void setDays(Integer days){
        this.days = days ;
        this.modify("days",days);
    }

    /**
     * 设置 [总计消耗]
     */
    public void setConsumed(Double consumed){
        this.consumed = consumed ;
        this.modify("consumed",consumed);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [最初预计]
     */
    public void setEstimate(Double estimate){
        this.estimate = estimate ;
        this.modify("estimate",estimate);
    }

    /**
     * 设置 [受限用户]
     */
    public void setLimited(String limited){
        this.limited = limited ;
        this.modify("limited",limited);
    }

    /**
     * 设置 [角色]
     */
    public void setRole(String role){
        this.role = role ;
        this.modify("role",role);
    }

    /**
     * 设置 [团队类型]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [项目编号]
     */
    public void setRoot(BigInteger root){
        this.root = root ;
        this.modify("root",root);
    }


}


