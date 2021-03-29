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
 * 实体[任务工时统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_taskestimate", resultMap = "TaskEstimateStatsResultMap")
@ApiModel("任务工时统计")
public class TaskEstimateStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    @TableField(value = "`date`")
    @JSONField(name = "date")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private String date;
    /**
     * 消耗的工时
     */
    @TableField(exist = false)
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("消耗的工时")
    private Double consumed;
    /**
     * 用户
     */
    @TableField(value = "`account`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("用户")
    private String account;
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
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("项目名称")
    private String name;
    /**
     * 任务数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务数")
    private Integer taskcnt;



    /**
     * 设置 [日期]
     */
    public void setDate(String date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 设置 [用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
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


