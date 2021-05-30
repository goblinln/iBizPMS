package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[PRODUCTTEAMDTO]
 */
@Data
@ApiModel("产品团队")
@JsonFilter(value = "dtofieldfilter")
public class PRODUCTTEAMDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [TEAMSTATUS]
     *
     */
    @JSONField(name = "teamstatus")
    @JsonProperty("teamstatus")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("成员状态")
    private String teamstatus;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("用户")
    private String account;

    /**
     * 属性 [JOIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd")
    @JsonProperty("join")
    @ApiModelProperty("加盟日")
    private Timestamp join;

    /**
     * 属性 [LEFT]
     *
     */
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("预计剩余")
    private Double left;

    /**
     * 属性 [TOTAL]
     *
     */
    @JSONField(name = "total")
    @JsonProperty("total")
    @ApiModelProperty("总计可用")
    private Integer total;

    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束时间")
    private Timestamp end;

    /**
     * 属性 [USERNAME]
     *
     */
    @JSONField(name = "username")
    @JsonProperty("username")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("用户")
    private String username;

    /**
     * 属性 [ESTIMATE]
     *
     */
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    @ApiModelProperty("最初预计")
    private Double estimate;

    /**
     * 属性 [HOURS]
     *
     */
    @JSONField(name = "hours")
    @JsonProperty("hours")
    @ApiModelProperty("可用工时/天")
    private Double hours;

    /**
     * 属性 [TASKCNT]
     *
     */
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务数")
    private Integer taskcnt;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    @ApiModelProperty("团队类型")
    private String type;

    /**
     * 属性 [CONSUMED]
     *
     */
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("总计消耗")
    private Double consumed;

    /**
     * 属性 [DAYS]
     *
     */
    @JSONField(name = "days")
    @JsonProperty("days")
    @ApiModelProperty("可用工日")
    private Integer days;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 属性 [LEADINGCADRE]
     *
     */
    @JSONField(name = "leadingcadre")
    @JsonProperty("leadingcadre")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("当前负责人")
    private String leadingcadre;

    /**
     * 属性 [LIMITED]
     *
     */
    @JSONField(name = "limited")
    @JsonProperty("limited")
    @Size(min = 0, max = 8, message = "内容长度必须小于等于[8]")
    @ApiModelProperty("受限用户")
    private String limited;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;

    /**
     * 属性 [ROLE]
     *
     */
    @JSONField(name = "role")
    @JsonProperty("role")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("角色")
    private String role;

    /**
     * 属性 [ROOT]
     *
     */
    @JSONField(name = "root")
    @JsonProperty("root")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品编号")
    private Long root;

    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [CREATEBY]
     *
     */
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁创建")
    private String createby;

    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;


    /**
     * 设置 [TEAMSTATUS]
     */
    public void setTeamstatus(String  teamstatus){
        this.teamstatus = teamstatus ;
        this.modify("teamstatus",teamstatus);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [JOIN]
     */
    public void setJoin(Timestamp  join){
        this.join = join ;
        this.modify("join",join);
    }

    /**
     * 设置 [LEFT]
     */
    public void setLeft(Double  left){
        this.left = left ;
        this.modify("left",left);
    }

    /**
     * 设置 [END]
     */
    public void setEnd(Timestamp  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [ESTIMATE]
     */
    public void setEstimate(Double  estimate){
        this.estimate = estimate ;
        this.modify("estimate",estimate);
    }

    /**
     * 设置 [HOURS]
     */
    public void setHours(Double  hours){
        this.hours = hours ;
        this.modify("hours",hours);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [CONSUMED]
     */
    public void setConsumed(Double  consumed){
        this.consumed = consumed ;
        this.modify("consumed",consumed);
    }

    /**
     * 设置 [DAYS]
     */
    public void setDays(Integer  days){
        this.days = days ;
        this.modify("days",days);
    }

    /**
     * 设置 [LEADINGCADRE]
     */
    public void setLeadingcadre(String  leadingcadre){
        this.leadingcadre = leadingcadre ;
        this.modify("leadingcadre",leadingcadre);
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


}


