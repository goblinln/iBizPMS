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
 * 实体[待办]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_todo", resultMap = "TodoResultMap")
@ApiModel("待办")
public class Todo extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 所有者
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("所有者")
    private String account;
    /**
     * 间隔天数
     */
    @TableField(exist = false)
    @JSONField(name = "config_day")
    @JsonProperty("config_day")
    @ApiModelProperty("间隔天数")
    private Integer configDay;
    /**
     * 指派给（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "assignedtopk")
    @JsonProperty("assignedtopk")
    @ApiModelProperty("指派给（选择）")
    private String assignedtopk;
    /**
     * 日期
     */
    @TableField(exist = false)
    @JSONField(name = "date1")
    @JsonProperty("date1")
    @ApiModelProperty("日期")
    private String date1;
    /**
     * 周期类型
     */
    @TableField(exist = false)
    @JSONField(name = "config_type")
    @JsonProperty("config_type")
    @ApiModelProperty("周期类型")
    private String configType;
    /**
     * 关闭时间
     */
    @TableField(value = "closeddate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭时间")
    private Timestamp closeddate;
    /**
     * 由谁关闭
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "closedby")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @ApiModelProperty("由谁关闭")
    private String closedby;
    /**
     * 类型
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 结束
     */
    @DEField(defaultValue = "1800")
    @TableField(value = "end")
    @JSONField(name = "end")
    @JsonProperty("end")
    @ApiModelProperty("结束")
    private Integer end;
    /**
     * 描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "desc")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("描述")
    private String desc;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 由谁完成
     */
    @TableField(value = "finishedby")
    @JSONField(name = "finishedby")
    @JsonProperty("finishedby")
    @ApiModelProperty("由谁完成")
    private String finishedby;
    /**
     * 开始
     */
    @DEField(defaultValue = "600")
    @TableField(value = "begin")
    @JSONField(name = "begin")
    @JsonProperty("begin")
    @ApiModelProperty("开始")
    private Integer begin;
    /**
     * 关联编号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "idvalue")
    @JSONField(name = "idvalue")
    @JsonProperty("idvalue")
    @ApiModelProperty("关联编号")
    private Long idvalue;
    /**
     * 由谁指派
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "assignedby")
    @JSONField(name = "assignedby")
    @JsonProperty("assignedby")
    @ApiModelProperty("由谁指派")
    private String assignedby;
    /**
     * 周期设置月
     */
    @TableField(exist = false)
    @JSONField(name = "config_month")
    @JsonProperty("config_month")
    @ApiModelProperty("周期设置月")
    private String configMonth;
    /**
     * 待办名称
     */
    @TableField(exist = false)
    @JSONField(name = "task")
    @JsonProperty("task")
    @ApiModelProperty("待办名称")
    private String task;
    /**
     * 待办名称
     */
    @TableField(exist = false)
    @JSONField(name = "bug")
    @JsonProperty("bug")
    @ApiModelProperty("待办名称")
    private String bug;
    /**
     * 完成时间
     */
    @TableField(value = "finisheddate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "finisheddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("finisheddate")
    @ApiModelProperty("完成时间")
    private Timestamp finisheddate;
    /**
     * 周期
     */
    @DEField(defaultValue = "0")
    @TableField(value = "cycle")
    @JSONField(name = "cycle")
    @JsonProperty("cycle")
    @ApiModelProperty("周期")
    private Integer cycle;
    /**
     * 待定
     */
    @TableField(exist = false)
    @JSONField(name = "date_disable")
    @JsonProperty("date_disable")
    @ApiModelProperty("待定")
    private String dateDisable;
    /**
     * 周期设置周几
     */
    @TableField(exist = false)
    @JSONField(name = "config_week")
    @JsonProperty("config_week")
    @ApiModelProperty("周期设置周几")
    private String configWeek;
    /**
     * 指派给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "assignedto")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 状态
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("状态")
    private String status;
    /**
     * 提前
     */
    @TableField(exist = false)
    @JSONField(name = "config_beforedays")
    @JsonProperty("config_beforedays")
    @ApiModelProperty("提前")
    private Integer configBeforedays;
    /**
     * 待办名称
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("待办名称")
    private String name;
    /**
     * 指派日期
     */
    @TableField(value = "assigneddate")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "assigneddate", format = "yyyy-MM-dd")
    @JsonProperty("assigneddate")
    @ApiModelProperty("指派日期")
    private Timestamp assigneddate;
    /**
     * 过期时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "config_end", format = "yyyy-MM-dd")
    @JsonProperty("config_end")
    @ApiModelProperty("过期时间")
    private Timestamp configEnd;
    /**
     * 费用
     */
    @TableField(value = "cost")
    @JSONField(name = "cost")
    @JsonProperty("cost")
    @ApiModelProperty("费用")
    private Integer cost;
    /**
     * 优先级
     */
    @DEField(defaultValue = "0")
    @TableField(value = "pri")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;
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
     * 待办名称
     */
    @TableField(exist = false)
    @JSONField(name = "story")
    @JsonProperty("story")
    @ApiModelProperty("待办名称")
    private String story;
    /**
     * 私人事务
     */
    @DEField(defaultValue = "0")
    @TableField(value = "private")
    @JSONField(name = "ibizprivate")
    @JsonProperty("ibizprivate")
    @ApiModelProperty("私人事务")
    private String ibizprivate;
    /**
     * config
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "config")
    @JSONField(name = "config")
    @JsonProperty("config")
    @ApiModelProperty("config")
    private String config;



    /**
     * 设置 [关闭时间]
     */
    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
        this.modify("closeddate", closeddate);
    }

    /**
     * 格式化日期 [关闭时间]
     */
    public String formatCloseddate() {
        if (this.closeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closeddate);
    }
    /**
     * 设置 [由谁关闭]
     */
    public void setClosedby(String closedby) {
        this.closedby = closedby;
        this.modify("closedby", closedby);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [结束]
     */
    public void setEnd(Integer end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 设置 [描述]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [由谁完成]
     */
    public void setFinishedby(String finishedby) {
        this.finishedby = finishedby;
        this.modify("finishedby", finishedby);
    }

    /**
     * 设置 [开始]
     */
    public void setBegin(Integer begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 设置 [关联编号]
     */
    public void setIdvalue(Long idvalue) {
        this.idvalue = idvalue;
        this.modify("idvalue", idvalue);
    }

    /**
     * 设置 [由谁指派]
     */
    public void setAssignedby(String assignedby) {
        this.assignedby = assignedby;
        this.modify("assignedby", assignedby);
    }

    /**
     * 设置 [完成时间]
     */
    public void setFinisheddate(Timestamp finisheddate) {
        this.finisheddate = finisheddate;
        this.modify("finisheddate", finisheddate);
    }

    /**
     * 格式化日期 [完成时间]
     */
    public String formatFinisheddate() {
        if (this.finisheddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(finisheddate);
    }
    /**
     * 设置 [周期]
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
        this.modify("cycle", cycle);
    }

    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [待办名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [指派日期]
     */
    public void setAssigneddate(Timestamp assigneddate) {
        this.assigneddate = assigneddate;
        this.modify("assigneddate", assigneddate);
    }

    /**
     * 格式化日期 [指派日期]
     */
    public String formatAssigneddate() {
        if (this.assigneddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(assigneddate);
    }
    /**
     * 设置 [费用]
     */
    public void setCost(Integer cost) {
        this.cost = cost;
        this.modify("cost", cost);
    }

    /**
     * 设置 [优先级]
     */
    public void setPri(Integer pri) {
        this.pri = pri;
        this.modify("pri", pri);
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
     * 设置 [私人事务]
     */
    public void setIbizprivate(String ibizprivate) {
        this.ibizprivate = ibizprivate;
        this.modify("private", ibizprivate);
    }

    /**
     * 设置 [config]
     */
    public void setConfig(String config) {
        this.config = config;
        this.modify("config", config);
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


