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
 * 服务DTO对象[ProductPlanDTO]
 */
@Data
@ApiModel("产品计划")
public class ProductPlanDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [TASKSCNT]
     *
     */
    @JSONField(name = "taskscnt")
    @JsonProperty("taskscnt")
    @ApiModelProperty("总任务数")
    private Integer taskscnt;

    /**
     * 属性 [ESTIMATECNT]
     *
     */
    @JSONField(name = "estimatecnt")
    @JsonProperty("estimatecnt")
    @ApiModelProperty("工时数")
    private Integer estimatecnt;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @NotBlank(message = "[名称]不允许为空!")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("名称")
    private String title;

    /**
     * 属性 [DELAYTASKSCNT]
     *
     */
    @JSONField(name = "delaytaskscnt")
    @JsonProperty("delaytaskscnt")
    @ApiModelProperty("延迟任务数")
    private Integer delaytaskscnt;

    /**
     * 属性 [OLDTITLE]
     *
     */
    @JSONField(name = "oldtitle")
    @JsonProperty("oldtitle")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("上一次计划名称")
    private String oldtitle;

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
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始日期")
    private Timestamp begin;

    /**
     * 属性 [STATUSS]
     *
     */
    @JSONField(name = "statuss")
    @JsonProperty("statuss")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("状态")
    private String statuss;

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
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束日期")
    private Timestamp end;

    /**
     * 属性 [DELAY]
     *
     */
    @JSONField(name = "delay")
    @JsonProperty("delay")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("延期")
    private String delay;

    /**
     * 属性 [DURATION]
     *
     */
    @JSONField(name = "duration")
    @JsonProperty("duration")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("持续时间")
    private String duration;

    /**
     * 属性 [BEGINSTR]
     *
     */
    @JSONField(name = "beginstr")
    @JsonProperty("beginstr")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("开始日期")
    private String beginstr;

    /**
     * 属性 [LEFTESTIMATE]
     *
     */
    @JSONField(name = "leftestimate")
    @JsonProperty("leftestimate")
    @ApiModelProperty("剩余工时")
    private Double leftestimate;

    /**
     * 属性 [PLANTEMPLET]
     *
     */
    @JSONField(name = "plantemplet")
    @JsonProperty("plantemplet")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("计划模板")
    private String plantemplet;

    /**
     * 属性 [UNFINISHEDTASKSCNT]
     *
     */
    @JSONField(name = "unfinishedtaskscnt")
    @JsonProperty("unfinishedtaskscnt")
    @ApiModelProperty("未完成任务数")
    private Integer unfinishedtaskscnt;

    /**
     * 属性 [ENDSTR]
     *
     */
    @JSONField(name = "endstr")
    @JsonProperty("endstr")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("结束日期")
    private String endstr;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("计划状态")
    private String status;

    /**
     * 属性 [ISEXPIRED]
     *
     */
    @JSONField(name = "isexpired")
    @JsonProperty("isexpired")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("是否过期")
    private String isexpired;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [CONSUMEDESTIMATE]
     *
     */
    @JSONField(name = "consumedestimate")
    @JsonProperty("consumedestimate")
    @ApiModelProperty("消耗工时")
    private Double consumedestimate;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("排序")
    private String order;

    /**
     * 属性 [ISLEAF]
     *
     */
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @ApiModelProperty("叶子节点")
    private Integer isleaf;

    /**
     * 属性 [FUTURE]
     *
     */
    @JSONField(name = "future")
    @JsonProperty("future")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("待定")
    private String future;

    /**
     * 属性 [STORYCNT]
     *
     */
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求数")
    private Integer storycnt;

    /**
     * 属性 [DELTA]
     *
     */
    @JSONField(name = "delta")
    @JsonProperty("delta")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("周期")
    private String delta;

    /**
     * 属性 [FINISHEDTASKSCNT]
     *
     */
    @JSONField(name = "finishedtaskscnt")
    @JsonProperty("finishedtaskscnt")
    @ApiModelProperty("完成任务数")
    private Integer finishedtaskscnt;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("bug数")
    private Integer bugcnt;

    /**
     * 属性 [PARENTNAME]
     *
     */
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("父计划名称")
    private String parentname;

    /**
     * 属性 [BRANCH]
     *
     */
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("平台/分支")
    private Long branch;

    /**
     * 属性 [PARENT]
     *
     */
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("父计划")
    private Long parent;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品")
    private Long product;


    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [BEGIN]
     */
    public void setBegin(Timestamp  begin){
        this.begin = begin ;
        this.modify("begin",begin);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [END]
     */
    public void setEnd(Timestamp  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(String  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [BRANCH]
     */
    public void setBranch(Long  branch){
        this.branch = branch ;
        this.modify("branch",branch);
    }

    /**
     * 设置 [PARENT]
     */
    public void setParent(Long  parent){
        this.parent = parent ;
        this.modify("parent",parent);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }


}


