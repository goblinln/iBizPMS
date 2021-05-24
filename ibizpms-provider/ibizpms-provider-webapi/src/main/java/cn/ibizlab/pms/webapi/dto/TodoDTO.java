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
 * 服务DTO对象[TodoDTO]
 */
@Data
@ApiModel("待办")
//@JsonFilter(value = "dtofieldfilter")
public class TodoDTO extends DTOBase implements Serializable {

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
     * 属性 [CONFIG_DAY]
     *
     */
    @JSONField(name = "config_day")
    @JsonProperty("config_day")
    @ApiModelProperty("间隔天数")
    private Integer configDay;

    /**
     * 属性 [ASSIGNEDTOPK]
     *
     */
    @JSONField(name = "assignedtopk")
    @JsonProperty("assignedtopk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("指派给（选择）")
    private String assignedtopk;

    /**
     * 属性 [DATE1]
     *
     */
    @JSONField(name = "date1")
    @JsonProperty("date1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("日期")
    private String date1;

    /**
     * 属性 [CONFIG_TYPE]
     *
     */
    @JSONField(name = "config_type")
    @JsonProperty("config_type")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("周期类型")
    private String configType;

    /**
     * 属性 [TODOSN]
     *
     */
    @JSONField(name = "todosn")
    @JsonProperty("todosn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("待办编号")
    private Long todosn;

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
     * 属性 [CLOSEDBY]
     *
     */
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁关闭")
    private String closedby;

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
     * 属性 [END]
     *
     */
    @JSONField(name = "end")
    @JsonProperty("end")
    @ApiModelProperty("结束")
    private Integer end;

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
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [NOTICEUSERS]
     *
     */
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;

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
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

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
     * 属性 [BEGIN]
     *
     */
    @JSONField(name = "begin")
    @JsonProperty("begin")
    @ApiModelProperty("开始")
    private Integer begin;

    /**
     * 属性 [IDVALUE]
     *
     */
    @JSONField(name = "idvalue")
    @JsonProperty("idvalue")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联编号")
    private Long idvalue;

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
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [CONFIG_MONTH]
     *
     */
    @JSONField(name = "config_month")
    @JsonProperty("config_month")
    @Size(min = 0, max = 4000, message = "内容长度必须小于等于[4000]")
    @ApiModelProperty("周期设置月")
    private String configMonth;

    /**
     * 属性 [TASK]
     *
     */
    @JSONField(name = "task")
    @JsonProperty("task")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("待办名称")
    private String task;

    /**
     * 属性 [BUG]
     *
     */
    @JSONField(name = "bug")
    @JsonProperty("bug")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("待办名称")
    private String bug;

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
     * 属性 [CYCLE]
     *
     */
    @JSONField(name = "cycle")
    @JsonProperty("cycle")
    @ApiModelProperty("周期")
    private Integer cycle;

    /**
     * 属性 [DATE_DISABLE]
     *
     */
    @JSONField(name = "date_disable")
    @JsonProperty("date_disable")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("待定")
    private String dateDisable;

    /**
     * 属性 [CONFIG_WEEK]
     *
     */
    @JSONField(name = "config_week")
    @JsonProperty("config_week")
    @Size(min = 0, max = 4000, message = "内容长度必须小于等于[4000]")
    @ApiModelProperty("周期设置周几")
    private String configWeek;

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
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 6, message = "内容长度必须小于等于[6]")
    @ApiModelProperty("状态")
    private String status;

    /**
     * 属性 [CONFIG_BEFOREDAYS]
     *
     */
    @JSONField(name = "config_beforedays")
    @JsonProperty("config_beforedays")
    @ApiModelProperty("提前")
    private Integer configBeforedays;

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
     * 属性 [ASSIGNEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "assigneddate" , format="yyyy-MM-dd")
    @JsonProperty("assigneddate")
    @ApiModelProperty("指派日期")
    private Timestamp assigneddate;

    /**
     * 属性 [CONFIG_END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "config_end" , format="yyyy-MM-dd")
    @JsonProperty("config_end")
    @ApiModelProperty("过期时间")
    private Timestamp configEnd;

    /**
     * 属性 [COST]
     *
     */
    @JSONField(name = "cost")
    @JsonProperty("cost")
    @ApiModelProperty("费用")
    private Integer cost;

    /**
     * 属性 [PRI]
     *
     */
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;

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
     * 属性 [STORY]
     *
     */
    @JSONField(name = "story")
    @JsonProperty("story")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("待办名称")
    private String story;

    /**
     * 属性 [PRIVATE]
     *
     */
    @JSONField(name = "ibizprivate")
    @JsonProperty("ibizprivate")
    @Size(min = 0, max = 4000, message = "内容长度必须小于等于[4000]")
    @ApiModelProperty("私人事务")
    private String ibizprivate;

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
     * 属性 [CONFIG]
     *
     */
    @JSONField(name = "config")
    @JsonProperty("config")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("config")
    private String config;


    /**
     * 设置 [TODOSN]
     */
    public void setTodosn(Long  todosn){
        this.todosn = todosn ;
        this.modify("todosn",todosn);
    }

    /**
     * 设置 [CLOSEDDATE]
     */
    public void setCloseddate(Timestamp  closeddate){
        this.closeddate = closeddate ;
        this.modify("closeddate",closeddate);
    }

    /**
     * 设置 [CLOSEDBY]
     */
    public void setClosedby(String  closedby){
        this.closedby = closedby ;
        this.modify("closedby",closedby);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [END]
     */
    public void setEnd(Integer  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [FINISHEDBY]
     */
    public void setFinishedby(String  finishedby){
        this.finishedby = finishedby ;
        this.modify("finishedby",finishedby);
    }

    /**
     * 设置 [BEGIN]
     */
    public void setBegin(Integer  begin){
        this.begin = begin ;
        this.modify("begin",begin);
    }

    /**
     * 设置 [IDVALUE]
     */
    public void setIdvalue(Long  idvalue){
        this.idvalue = idvalue ;
        this.modify("idvalue",idvalue);
    }

    /**
     * 设置 [ASSIGNEDBY]
     */
    public void setAssignedby(String  assignedby){
        this.assignedby = assignedby ;
        this.modify("assignedby",assignedby);
    }

    /**
     * 设置 [FINISHEDDATE]
     */
    public void setFinisheddate(Timestamp  finisheddate){
        this.finisheddate = finisheddate ;
        this.modify("finisheddate",finisheddate);
    }

    /**
     * 设置 [CYCLE]
     */
    public void setCycle(Integer  cycle){
        this.cycle = cycle ;
        this.modify("cycle",cycle);
    }

    /**
     * 设置 [ASSIGNEDTO]
     */
    public void setAssignedto(String  assignedto){
        this.assignedto = assignedto ;
        this.modify("assignedto",assignedto);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [ASSIGNEDDATE]
     */
    public void setAssigneddate(Timestamp  assigneddate){
        this.assigneddate = assigneddate ;
        this.modify("assigneddate",assigneddate);
    }

    /**
     * 设置 [COST]
     */
    public void setCost(Integer  cost){
        this.cost = cost ;
        this.modify("cost",cost);
    }

    /**
     * 设置 [PRI]
     */
    public void setPri(Integer  pri){
        this.pri = pri ;
        this.modify("pri",pri);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [PRIVATE]
     */
    public void setIbizprivate(String  ibizprivate){
        this.ibizprivate = ibizprivate ;
        this.modify("private",ibizprivate);
    }

    /**
     * 设置 [CONFIG]
     */
    public void setConfig(String  config){
        this.config = config ;
        this.modify("config",config);
    }


}


