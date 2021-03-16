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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[产品计划]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_productplan", resultMap = "ProductPlanResultMap")
public class ProductPlan extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总任务数
     */
    @TableField(exist = false)
    @JSONField(name = "taskscnt")
    @JsonProperty("taskscnt")
    private Integer taskscnt;
    /**
     * 工时数
     */
    @TableField(exist = false)
    @JSONField(name = "estimatecnt")
    @JsonProperty("estimatecnt")
    private Integer estimatecnt;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * 名称
     */
    @TableField(value = "`title`")
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 延迟任务数
     */
    @TableField(exist = false)
    @JSONField(name = "delaytaskscnt")
    @JsonProperty("delaytaskscnt")
    private Integer delaytaskscnt;
    /**
     * 上一次计划名称
     */
    @TableField(exist = false)
    @JSONField(name = "oldtitle")
    @JsonProperty("oldtitle")
    private String oldtitle;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 开始日期
     */
    @TableField(value = "`begin`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    private Timestamp begin;
    /**
     * 状态
     */
    @TableField(exist = false)
    @JSONField(name = "statuss")
    @JsonProperty("statuss")
    private String statuss;
    /**
     * 描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`desc`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    private String desc;
    /**
     * 结束日期
     */
    @TableField(value = "`end`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    private Timestamp end;
    /**
     * 延期
     */
    @TableField(exist = false)
    @JSONField(name = "delay")
    @JsonProperty("delay")
    private String delay;
    /**
     * 持续时间
     */
    @TableField(exist = false)
    @JSONField(name = "duration")
    @JsonProperty("duration")
    private String duration;
    /**
     * 开始日期
     */
    @TableField(exist = false)
    @JSONField(name = "beginstr")
    @JsonProperty("beginstr")
    private String beginstr;
    /**
     * 剩余工时
     */
    @TableField(exist = false)
    @JSONField(name = "leftestimate")
    @JsonProperty("leftestimate")
    private Double leftestimate;
    /**
     * 计划模板
     */
    @TableField(exist = false)
    @JSONField(name = "plantemplet")
    @JsonProperty("plantemplet")
    private String plantemplet;
    /**
     * 未完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "unfinishedtaskscnt")
    @JsonProperty("unfinishedtaskscnt")
    private Integer unfinishedtaskscnt;
    /**
     * 结束日期
     */
    @TableField(exist = false)
    @JSONField(name = "endstr")
    @JsonProperty("endstr")
    private String endstr;
    /**
     * 计划状态
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 是否过期
     */
    @TableField(exist = false)
    @JSONField(name = "isexpired")
    @JsonProperty("isexpired")
    private String isexpired;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 消耗工时
     */
    @TableField(exist = false)
    @JSONField(name = "consumedestimate")
    @JsonProperty("consumedestimate")
    private Double consumedestimate;
    /**
     * 排序
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`order`")
    @JSONField(name = "order")
    @JsonProperty("order")
    private String order;
    /**
     * 叶子节点
     */
    @TableField(exist = false)
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    private Integer isleaf;
    /**
     * 待定
     */
    @TableField(exist = false)
    @JSONField(name = "future")
    @JsonProperty("future")
    private String future;
    /**
     * 需求数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    private Integer storycnt;
    /**
     * 周期
     */
    @TableField(exist = false)
    @JSONField(name = "delta")
    @JsonProperty("delta")
    private String delta;
    /**
     * 完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "finishedtaskscnt")
    @JsonProperty("finishedtaskscnt")
    private Integer finishedtaskscnt;
    /**
     * bug数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    private Integer bugcnt;
    /**
     * 父计划名称
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    private String parentname;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`branch`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    private Long branch;
    /**
     * 父计划
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`parent`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    private Long parent;
    /**
     * 产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`product`")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Long product;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Branch ztbranch;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztproduct;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.ProductPlan ibizparent;



    /**
     * 设置 [名称]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [开始日期]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [开始日期]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(begin);
    }
    /**
     * 设置 [描述]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [结束日期]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [结束日期]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(end);
    }
    /**
     * 设置 [计划状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(String order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [父计划]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
    }

    /**
     * 设置 [产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
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


