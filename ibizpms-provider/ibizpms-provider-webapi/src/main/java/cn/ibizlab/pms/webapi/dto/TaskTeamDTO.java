package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;

/**
 * 服务DTO对象[TaskTeamDTO]
 */
@Data
public class TaskTeamDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ROLE]
     *
     */
    @JSONField(name = "role")
    @JsonProperty("role")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String role;

    /**
     * 属性 [ROOT]
     *
     */
    @JSONField(name = "root")
    @JsonProperty("root")
    private Long root;

    /**
     * 属性 [LIMITED]
     *
     */
    @JSONField(name = "limited")
    @JsonProperty("limited")
    @Size(min = 0, max = 8, message = "内容长度必须小于等于[8]")
    private String limited;

    /**
     * 属性 [TOTAL]
     *
     */
    @JSONField(name = "total")
    @JsonProperty("total")
    private Integer total;

    /**
     * 属性 [USERNAME]
     *
     */
    @JSONField(name = "username")
    @JsonProperty("username")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    private String username;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    private Integer order;

    /**
     * 属性 [DAYS]
     *
     */
    @JSONField(name = "days")
    @JsonProperty("days")
    private Integer days;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    private String type;

    /**
     * 属性 [ESTIMATE]
     *
     */
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    private BigDecimal estimate;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String account;

    /**
     * 属性 [CONSUMED]
     *
     */
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    private BigDecimal consumed;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;

    /**
     * 属性 [JOIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd")
    @JsonProperty("join")
    private Timestamp join;

    /**
     * 属性 [HOURS]
     *
     */
    @JSONField(name = "hours")
    @JsonProperty("hours")
    private Double hours;

    /**
     * 属性 [LEFT]
     *
     */
    @JSONField(name = "left")
    @JsonProperty("left")
    private BigDecimal left;


    /**
     * 设置 [ROLE]
     */
    public void setRole(String  role){
        this.role = role ;
        this.modify("role",role);
    }

    /**
     * 设置 [ROOT]
     */
    public void setRoot(Long  root){
        this.root = root ;
        this.modify("root",root);
    }

    /**
     * 设置 [LIMITED]
     */
    public void setLimited(String  limited){
        this.limited = limited ;
        this.modify("limited",limited);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(Integer  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [DAYS]
     */
    public void setDays(Integer  days){
        this.days = days ;
        this.modify("days",days);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [ESTIMATE]
     */
    public void setEstimate(BigDecimal  estimate){
        this.estimate = estimate ;
        this.modify("estimate",estimate);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [CONSUMED]
     */
    public void setConsumed(BigDecimal  consumed){
        this.consumed = consumed ;
        this.modify("consumed",consumed);
    }

    /**
     * 设置 [JOIN]
     */
    public void setJoin(Timestamp  join){
        this.join = join ;
        this.modify("join",join);
    }

    /**
     * 设置 [HOURS]
     */
    public void setHours(Double  hours){
        this.hours = hours ;
        this.modify("hours",hours);
    }

    /**
     * 设置 [LEFT]
     */
    public void setLeft(BigDecimal  left){
        this.left = left ;
        this.modify("left",left);
    }


}


