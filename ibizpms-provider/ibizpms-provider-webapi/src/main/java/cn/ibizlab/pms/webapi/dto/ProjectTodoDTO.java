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
 * 服务DTO对象[ProjectTodoDTO]
 */
@Data
@ApiModel("项目其他活动")
public class ProjectTodoDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("所有者")
    private String account;

    /**
     * 属性 [PRIVATE]
     *
     */
    @JSONField(name = "ibizprivate")
    @JsonProperty("ibizprivate")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("私人事务")
    private String ibizprivate;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 6, message = "内容长度必须小于等于[6]")
    @ApiModelProperty("状态")
    private String status;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("类型")
    private String type;

    /**
     * 属性 [FINISHEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "finisheddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("finisheddate")
    @ApiModelProperty("完成时间")
    private Timestamp finisheddate;

    /**
     * 属性 [PRI]
     *
     */
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;

    /**
     * 属性 [CONFIG]
     *
     */
    @JSONField(name = "config")
    @JsonProperty("config")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("config")
    private String config;

    /**
     * 属性 [ASSIGNEDBY]
     *
     */
    @JSONField(name = "assignedby")
    @JsonProperty("assignedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁指派")
    private String assignedby;

    /**
     * 属性 [ASSIGNEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "assigneddate" , format="yyyy-MM-dd")
    @JsonProperty("assigneddate")
    @ApiModelProperty("指派日期")
    private Timestamp assigneddate;

    /**
     * 属性 [CLOSEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "closeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭时间")
    private Timestamp closeddate;

    /**
     * 属性 [ASSIGNEDTO]
     *
     */
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("指派给")
    private String assignedto;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;

    /**
     * 属性 [FINISHEDBY]
     *
     */
    @JSONField(name = "finishedby")
    @JsonProperty("finishedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁完成")
    private String finishedby;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @Size(min = 0, max = 150, message = "内容长度必须小于等于[150]")
    @ApiModelProperty("待办名称")
    private String name;

    /**
     * 属性 [COST]
     *
     */
    @JSONField(name = "cost")
    @JsonProperty("cost")
    @ApiModelProperty("费用")
    private Integer cost;

    /**
     * 属性 [CLOSEDBY]
     *
     */
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁关闭")
    private String closedby;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("描述")
    private String desc;

    /**
     * 属性 [IDVALUE]
     *
     */
    @JSONField(name = "idvalue")
    @JsonProperty("idvalue")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long idvalue;

    /**
     * 属性 [CONSUMED]
     *
     */
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("工时")
    private Double consumed;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("附件")
    private String files;


    /**
     * 设置 [PRIVATE]
     */
    public void setIbizprivate(String  ibizprivate){
        this.ibizprivate = ibizprivate ;
        this.modify("private",ibizprivate);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [FINISHEDDATE]
     */
    public void setFinisheddate(Timestamp  finisheddate){
        this.finisheddate = finisheddate ;
        this.modify("finisheddate",finisheddate);
    }

    /**
     * 设置 [PRI]
     */
    public void setPri(Integer  pri){
        this.pri = pri ;
        this.modify("pri",pri);
    }

    /**
     * 设置 [CONFIG]
     */
    public void setConfig(String  config){
        this.config = config ;
        this.modify("config",config);
    }

    /**
     * 设置 [ASSIGNEDBY]
     */
    public void setAssignedby(String  assignedby){
        this.assignedby = assignedby ;
        this.modify("assignedby",assignedby);
    }

    /**
     * 设置 [ASSIGNEDDATE]
     */
    public void setAssigneddate(Timestamp  assigneddate){
        this.assigneddate = assigneddate ;
        this.modify("assigneddate",assigneddate);
    }

    /**
     * 设置 [CLOSEDDATE]
     */
    public void setCloseddate(Timestamp  closeddate){
        this.closeddate = closeddate ;
        this.modify("closeddate",closeddate);
    }

    /**
     * 设置 [ASSIGNEDTO]
     */
    public void setAssignedto(String  assignedto){
        this.assignedto = assignedto ;
        this.modify("assignedto",assignedto);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [FINISHEDBY]
     */
    public void setFinishedby(String  finishedby){
        this.finishedby = finishedby ;
        this.modify("finishedby",finishedby);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [COST]
     */
    public void setCost(Integer  cost){
        this.cost = cost ;
        this.modify("cost",cost);
    }

    /**
     * 设置 [CLOSEDBY]
     */
    public void setClosedby(String  closedby){
        this.closedby = closedby ;
        this.modify("closedby",closedby);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [IDVALUE]
     */
    public void setIdvalue(Long  idvalue){
        this.idvalue = idvalue ;
        this.modify("idvalue",idvalue);
    }

    /**
     * 设置 [CONSUMED]
     */
    public void setConsumed(Double  consumed){
        this.consumed = consumed ;
        this.modify("consumed",consumed);
    }


}


