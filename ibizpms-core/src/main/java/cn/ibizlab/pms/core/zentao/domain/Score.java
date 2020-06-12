package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
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
 * 实体[score]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_score",resultMap = "ScoreResultMap")
public class Score extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * method
     */
    @TableField(value = "method")
    @JSONField(name = "method")
    @JsonProperty("method")
    private String method;
    /**
     * score
     */
    @DEField(defaultValue = "0")
    @TableField(value = "score")
    @JSONField(name = "score")
    @JsonProperty("score")
    private Integer score;
    /**
     * time
     */
    @TableField(value = "time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "time" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("time")
    private Timestamp time;
    /**
     * account
     */
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * before
     */
    @DEField(defaultValue = "0")
    @TableField(value = "before")
    @JSONField(name = "before")
    @JsonProperty("before")
    private Integer before;
    /**
     * desc
     */
    @TableField(value = "desc")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    private String desc;
    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * after
     */
    @DEField(defaultValue = "0")
    @TableField(value = "after")
    @JSONField(name = "after")
    @JsonProperty("after")
    private Integer after;
    /**
     * module
     */
    @TableField(value = "module")
    @JSONField(name = "module")
    @JsonProperty("module")
    private String module;



    /**
     * 设置 [method]
     */
    public void setMethod(String method){
        this.method = method ;
        this.modify("method",method);
    }
    /**
     * 设置 [score]
     */
    public void setScore(Integer score){
        this.score = score ;
        this.modify("score",score);
    }
    /**
     * 设置 [time]
     */
    public void setTime(Timestamp time){
        this.time = time ;
        this.modify("time",time);
    }
    /**
     * 设置 [account]
     */
    public void setAccount(String account){
        this.account = account ;
        this.modify("account",account);
    }
    /**
     * 设置 [before]
     */
    public void setBefore(Integer before){
        this.before = before ;
        this.modify("before",before);
    }
    /**
     * 设置 [desc]
     */
    public void setDesc(String desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }
    /**
     * 设置 [after]
     */
    public void setAfter(Integer after){
        this.after = after ;
        this.modify("after",after);
    }
    /**
     * 设置 [module]
     */
    public void setModule(String module){
        this.module = module ;
        this.modify("module",module);
    }

}


