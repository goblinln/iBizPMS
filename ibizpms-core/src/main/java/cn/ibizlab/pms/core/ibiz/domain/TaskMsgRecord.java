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
 * 实体[待办消息记录]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_TASKMSGRECORD", resultMap = "TaskMsgRecordResultMap")
@ApiModel("待办消息记录")
public class TaskMsgRecord extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方应用待办标识
     */
    @TableField(value = "`APPTASKID`")
    @JSONField(name = "apptaskid")
    @JsonProperty("apptaskid")
    @ApiModelProperty("第三方应用待办标识")
    private String apptaskid;
    /**
     * 数据标识
     */
    @TableField(value = "`DATAID`")
    @JSONField(name = "dataid")
    @JsonProperty("dataid")
    @ApiModelProperty("数据标识")
    private String dataid;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`UPDATEDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 标题
     */
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("标题")
    private String title;
    /**
     * 备注
     */
    @TableField(value = "`MEMO`")
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN, dict = "SysOperator")
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 待办人标识
     */
    @TableField(value = "`TASKUSERID`")
    @JSONField(name = "taskuserid")
    @JsonProperty("taskuserid")
    @ApiModelProperty("待办人标识")
    private String taskuserid;
    /**
     * 待办类型
     */
    @TableField(value = "`TASKTYPE`")
    @JSONField(name = "tasktype")
    @JsonProperty("tasktype")
    @ApiModelProperty("待办类型")
    private String tasktype;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN, dict = "SysOperator")
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 待办消息记录名称
     */
    @TableField(value = "`TASKMSGRECORDNAME`")
    @JSONField(name = "taskmsgrecordname")
    @JsonProperty("taskmsgrecordname")
    @ApiModelProperty("待办消息记录名称")
    private String taskmsgrecordname;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`CREATEDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 待办消息记录标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "`TASKMSGRECORDID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "taskmsgrecordid")
    @JsonProperty("taskmsgrecordid")
    @ApiModelProperty("待办消息记录标识")
    private String taskmsgrecordid;
    /**
     * 逻辑有效标志
     */
    @DEField(preType = DEPredefinedFieldType.LOGICVALID, logicval = "1", logicdelval = "0")
    @TableLogic(value = "1", delval = "0")
    @TableField(value = "`ENABLE`")
    @JSONField(name = "enable")
    @JsonProperty("enable")
    @ApiModelProperty("逻辑有效标志")
    private Integer enable;



    /**
     * 设置 [第三方应用待办标识]
     */
    public void setApptaskid(String apptaskid) {
        this.apptaskid = apptaskid;
        this.modify("apptaskid", apptaskid);
    }

    /**
     * 设置 [数据标识]
     */
    public void setDataid(String dataid) {
        this.dataid = dataid;
        this.modify("dataid", dataid);
    }

    /**
     * 设置 [标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo;
        this.modify("memo", memo);
    }

    /**
     * 设置 [待办人标识]
     */
    public void setTaskuserid(String taskuserid) {
        this.taskuserid = taskuserid;
        this.modify("taskuserid", taskuserid);
    }

    /**
     * 设置 [待办类型]
     */
    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
        this.modify("tasktype", tasktype);
    }

    /**
     * 设置 [待办消息记录名称]
     */
    public void setTaskmsgrecordname(String taskmsgrecordname) {
        this.taskmsgrecordname = taskmsgrecordname;
        this.modify("taskmsgrecordname", taskmsgrecordname);
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
        this.reset("taskmsgrecordid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


