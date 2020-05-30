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


import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;


/**
 * 实体[cron]
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "zt_cron",resultMap = "CronResultMap")
public class Cron extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.UUID)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * status
     */
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * buildin
     */
    @DEField(defaultValue = "0")
    @TableField(value = "buildin")
    @JSONField(name = "buildin")
    @JsonProperty("buildin")
    private Integer buildin;
    /**
     * m
     */
    @TableField(value = "m")
    @JSONField(name = "m")
    @JsonProperty("m")
    private String m;
    /**
     * lastTime
     */
    @TableField(value = "lasttime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "lasttime" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lasttime")
    private Timestamp lasttime;
    /**
     * command
     */
    @TableField(value = "command")
    @JSONField(name = "command")
    @JsonProperty("command")
    private String command;
    /**
     * dom
     */
    @TableField(value = "dom")
    @JSONField(name = "dom")
    @JsonProperty("dom")
    private String dom;
    /**
     * mon
     */
    @TableField(value = "mon")
    @JSONField(name = "mon")
    @JsonProperty("mon")
    private String mon;
    /**
     * remark
     */
    @TableField(value = "remark")
    @JSONField(name = "remark")
    @JsonProperty("remark")
    private String remark;
    /**
     * h
     */
    @TableField(value = "h")
    @JSONField(name = "h")
    @JsonProperty("h")
    private String h;
    /**
     * type
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * dow
     */
    @TableField(value = "dow")
    @JSONField(name = "dow")
    @JsonProperty("dow")
    private String dow;



    /**
     * 设置 [status]
     */
    public void setStatus(String status){
        this.status = status ;
        this.modify("status",status);
    }
    /**
     * 设置 [buildin]
     */
    public void setBuildin(Integer buildin){
        this.buildin = buildin ;
        this.modify("buildin",buildin);
    }
    /**
     * 设置 [m]
     */
    public void setM(String m){
        this.m = m ;
        this.modify("m",m);
    }
    /**
     * 设置 [lastTime]
     */
    public void setLasttime(Timestamp lasttime){
        this.lasttime = lasttime ;
        this.modify("lasttime",lasttime);
    }
    /**
     * 设置 [command]
     */
    public void setCommand(String command){
        this.command = command ;
        this.modify("command",command);
    }
    /**
     * 设置 [dom]
     */
    public void setDom(String dom){
        this.dom = dom ;
        this.modify("dom",dom);
    }
    /**
     * 设置 [mon]
     */
    public void setMon(String mon){
        this.mon = mon ;
        this.modify("mon",mon);
    }
    /**
     * 设置 [remark]
     */
    public void setRemark(String remark){
        this.remark = remark ;
        this.modify("remark",remark);
    }
    /**
     * 设置 [h]
     */
    public void setH(String h){
        this.h = h ;
        this.modify("h",h);
    }
    /**
     * 设置 [type]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
    }
    /**
     * 设置 [dow]
     */
    public void setDow(String dow){
        this.dow = dow ;
        this.modify("dow",dow);
    }

}


