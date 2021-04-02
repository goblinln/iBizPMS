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
 * 实体[任务预计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_taskestimate", resultMap = "TaskEstimateResultMap")
@ApiModel("任务预计")
public class TaskEstimate extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 月（显示）
     */
    @TableField(value = "monthname")
    @JSONField(name = "monthname")
    @JsonProperty("monthname")
    @ApiModelProperty("月（显示）")
    private String monthname;
    /**
     * 年
     */
    @TableField(exist = false)
    @JSONField(name = "year")
    @JsonProperty("year")
    @ApiModelProperty("年")
    private String year;
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
     * 预计剩余
     */
    @DEField(defaultValue = "0")
    @TableField(value = "left")
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("预计剩余")
    private Double left;
    /**
     * 总计消耗
     */
    @DEField(defaultValue = "0")
    @TableField(value = "consumed")
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("总计消耗")
    private Double consumed;
    /**
     * 评估成本
     */
    @TableField(value = "evaluationcost")
    @JSONField(name = "evaluationcost")
    @JsonProperty("evaluationcost")
    @ApiModelProperty("评估成本")
    private Double evaluationcost;
    /**
     * 月（排序）
     */
    @TableField(exist = false)
    @JSONField(name = "monthorder")
    @JsonProperty("monthorder")
    @ApiModelProperty("月（排序）")
    private Integer monthorder;
    /**
     * 附件
     */
    @TableField(value = "files")
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
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
     * 评估状态
     */
    @DEField(defaultValue = "no")
    @TableField(value = "evaluationstatus")
    @JSONField(name = "evaluationstatus")
    @JsonProperty("evaluationstatus")
    @ApiModelProperty("评估状态")
    private String evaluationstatus;
    /**
     * 年（显示）
     */
    @TableField(exist = false)
    @JSONField(name = "yearname")
    @JsonProperty("yearname")
    @ApiModelProperty("年（显示）")
    private String yearname;
    /**
     * 日期
     */
    @TableField(value = "date")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
    /**
     * 评估工时
     */
    @TableField(value = "evaluationtime")
    @JSONField(name = "evaluationtime")
    @JsonProperty("evaluationtime")
    @ApiModelProperty("评估工时")
    private Double evaluationtime;
    /**
     * 投入成本
     */
    @TableField(value = "inputcost")
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    @ApiModelProperty("投入成本")
    private Double inputcost;
    /**
     * 日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "dates", format = "yyyy-MM-dd")
    @JsonProperty("dates")
    @ApiModelProperty("日期")
    private Timestamp dates;
    /**
     * 月
     */
    @TableField(exist = false)
    @JSONField(name = "month")
    @JsonProperty("month")
    @ApiModelProperty("月")
    private String month;
    /**
     * work
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "work")
    @JSONField(name = "work")
    @JsonProperty("work")
    @ApiModelProperty("work")
    private String work;
    /**
     * 评估说明
     */
    @TableField(value = "evaluationdesc")
    @JSONField(name = "evaluationdesc")
    @JsonProperty("evaluationdesc")
    @ApiModelProperty("评估说明")
    private String evaluationdesc;
    /**
     * 任务种别
     */
    @TableField(exist = false)
    @JSONField(name = "taskspecies")
    @JsonProperty("taskspecies")
    @ApiModelProperty("任务种别")
    private String taskspecies;
    /**
     * 任务名称
     */
    @TableField(exist = false)
    @JSONField(name = "taskname")
    @JsonProperty("taskname")
    @ApiModelProperty("任务名称")
    private String taskname;
    /**
     * 所属项目
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("所属项目")
    private String projectname;
    /**
     * 任务类型
     */
    @TableField(exist = false)
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("任务类型")
    private String type;
    /**
     * 任务删除标识
     */
    @TableField(exist = false)
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("任务删除标识")
    private String deleted;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private Long project;
    /**
     * 任务
     */
    @DEField(defaultValue = "0")
    @TableField(value = "task")
    @JSONField(name = "task")
    @JsonProperty("task")
    @ApiModelProperty("任务")
    private Long task;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Task zttask;



    /**
     * 设置 [月（显示）]
     */
    public void setMonthname(String monthname) {
        this.monthname = monthname;
        this.modify("monthname", monthname);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [预计剩余]
     */
    public void setLeft(Double left) {
        this.left = left;
        this.modify("left", left);
    }

    /**
     * 设置 [总计消耗]
     */
    public void setConsumed(Double consumed) {
        this.consumed = consumed;
        this.modify("consumed", consumed);
    }

    /**
     * 设置 [评估成本]
     */
    public void setEvaluationcost(Double evaluationcost) {
        this.evaluationcost = evaluationcost;
        this.modify("evaluationcost", evaluationcost);
    }

    /**
     * 设置 [附件]
     */
    public void setFiles(String files) {
        this.files = files;
        this.modify("files", files);
    }

    /**
     * 设置 [评估状态]
     */
    public void setEvaluationstatus(String evaluationstatus) {
        this.evaluationstatus = evaluationstatus;
        this.modify("evaluationstatus", evaluationstatus);
    }

    /**
     * 设置 [日期]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [日期]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [评估工时]
     */
    public void setEvaluationtime(Double evaluationtime) {
        this.evaluationtime = evaluationtime;
        this.modify("evaluationtime", evaluationtime);
    }

    /**
     * 设置 [投入成本]
     */
    public void setInputcost(Double inputcost) {
        this.inputcost = inputcost;
        this.modify("inputcost", inputcost);
    }

    /**
     * 设置 [work]
     */
    public void setWork(String work) {
        this.work = work;
        this.modify("work", work);
    }

    /**
     * 设置 [评估说明]
     */
    public void setEvaluationdesc(String evaluationdesc) {
        this.evaluationdesc = evaluationdesc;
        this.modify("evaluationdesc", evaluationdesc);
    }

    /**
     * 设置 [任务]
     */
    public void setTask(Long task) {
        this.task = task;
        this.modify("task", task);
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


