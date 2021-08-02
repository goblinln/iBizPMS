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
 * 实体[产品计划]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_productplan", resultMap = "ProductPlanResultMap")
@ApiModel("产品计划")
public class ProductPlan extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总任务数
     */
    @TableField(exist = false)
    @JSONField(name = "taskscnt")
    @JsonProperty("taskscnt")
    @ApiModelProperty("总任务数")
    private Integer taskscnt;
    /**
     * 工时数
     */
    @TableField(exist = false)
    @JSONField(name = "estimatecnt")
    @JsonProperty("estimatecnt")
    @ApiModelProperty("工时数")
    private Integer estimatecnt;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 名称
     */
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("名称")
    private String title;
    /**
     * 延迟任务数
     */
    @TableField(exist = false)
    @JSONField(name = "delaytaskscnt")
    @JsonProperty("delaytaskscnt")
    @ApiModelProperty("延迟任务数")
    private Integer delaytaskscnt;
    /**
     * 上一次计划名称
     */
    @TableField(exist = false)
    @JSONField(name = "oldtitle")
    @JsonProperty("oldtitle")
    @ApiModelProperty("上一次计划名称")
    private String oldtitle;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORG`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 开始日期
     */
    @TableField(value = "`BEGIN`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始日期")
    private Timestamp begin;
    /**
     * 状态
     */
    @TableField(exist = false)
    @JSONField(name = "statuss")
    @JsonProperty("statuss")
    @ApiModelProperty("状态")
    private String statuss;
    /**
     * 描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("描述")
    private String desc;
    /**
     * 结束日期
     */
    @TableField(value = "`END`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束日期")
    private Timestamp end;
    /**
     * 延期
     */
    @TableField(exist = false)
    @JSONField(name = "delay")
    @JsonProperty("delay")
    @ApiModelProperty("延期")
    private String delay;
    /**
     * 持续时间
     */
    @TableField(exist = false)
    @JSONField(name = "duration")
    @JsonProperty("duration")
    @ApiModelProperty("持续时间")
    private String duration;
    /**
     * 开始日期
     */
    @TableField(exist = false)
    @JSONField(name = "beginstr")
    @JsonProperty("beginstr")
    @ApiModelProperty("开始日期")
    private String beginstr;
    /**
     * 剩余工时
     */
    @TableField(exist = false)
    @JSONField(name = "leftestimate")
    @JsonProperty("leftestimate")
    @ApiModelProperty("剩余工时")
    private Double leftestimate;
    /**
     * 计划模板
     */
    @TableField(exist = false)
    @JSONField(name = "plantemplet")
    @JsonProperty("plantemplet")
    @ApiModelProperty("计划模板")
    private String plantemplet;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * 未完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "unfinishedtaskscnt")
    @JsonProperty("unfinishedtaskscnt")
    @ApiModelProperty("未完成任务数")
    private Integer unfinishedtaskscnt;
    /**
     * 结束日期
     */
    @TableField(exist = false)
    @JSONField(name = "endstr")
    @JsonProperty("endstr")
    @ApiModelProperty("结束日期")
    private String endstr;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME, dict = "UserRealName")
    @TableField(value = "`CREATEBY`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 计划状态
     */
    @DEField(defaultValue = "wait", dict = "Task__status")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("计划状态")
    private String status;
    /**
     * 是否过期
     */
    @TableField(exist = false)
    @JSONField(name = "isexpired")
    @JsonProperty("isexpired")
    @ApiModelProperty("是否过期")
    private String isexpired;
    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME, dict = "UserRealName")
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 消耗工时
     */
    @TableField(exist = false)
    @JSONField(name = "consumedestimate")
    @JsonProperty("consumedestimate")
    @ApiModelProperty("消耗工时")
    private Double consumedestimate;
    /**
     * 排序
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private String order;
    /**
     * 叶子节点
     */
    @TableField(exist = false)
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @ApiModelProperty("叶子节点")
    private Integer isleaf;
    /**
     * 待定
     */
    @TableField(exist = false)
    @JSONField(name = "future")
    @JsonProperty("future")
    @ApiModelProperty("待定")
    private String future;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 需求数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求数")
    private Integer storycnt;
    /**
     * 周期
     */
    @TableField(exist = false)
    @JSONField(name = "delta")
    @JsonProperty("delta")
    @ApiModelProperty("周期")
    private String delta;
    /**
     * 产品计划编号
     */
    @TableField(value = "`PRODUCTPLANSN`")
    @JSONField(name = "productplansn")
    @JsonProperty("productplansn")
    @ApiModelProperty("产品计划编号")
    private Long productplansn;
    /**
     * 完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "finishedtaskscnt")
    @JsonProperty("finishedtaskscnt")
    @ApiModelProperty("完成任务数")
    private Integer finishedtaskscnt;
    /**
     * bug数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("bug数")
    private Integer bugcnt;
    /**
     * 父计划名称
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("父计划名称")
    private String parentname;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BRANCH`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("平台/分支")
    private Long branch;
    /**
     * 父计划
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("父计划")
    private Long parent;
    /**
     * 产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
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
     * 设置 [产品计划编号]
     */
    public void setProductplansn(Long productplansn) {
        this.productplansn = productplansn;
        this.modify("productplansn", productplansn);
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


