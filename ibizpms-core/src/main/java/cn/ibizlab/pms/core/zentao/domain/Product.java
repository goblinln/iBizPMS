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
 * 实体[产品]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_product", resultMap = "ProductResultMap")
@ApiModel("产品")
public class Product extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组织标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "orgid")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织标识")
    private String orgid;
    /**
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 测试负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "qd")
    @JSONField(name = "qd")
    @JsonProperty("qd")
    @ApiModelProperty("测试负责人")
    private String qd;
    /**
     * 产品分类
     */
    @TableField(value = "productclass")
    @JSONField(name = "productclass")
    @JsonProperty("productclass")
    @ApiModelProperty("产品分类")
    private String productclass;
    /**
     * 未确认Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "unconfirmbugcnt")
    @JsonProperty("unconfirmbugcnt")
    @ApiModelProperty("未确认Bug数")
    private Integer unconfirmbugcnt;
    /**
     * 访问控制
     */
    @DEField(defaultValue = "open")
    @TableField(value = "acl")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("访问控制")
    private String acl;
    /**
     * 产品名称
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("产品名称")
    private String name;
    /**
     * 移动端图片
     */
    @TableField(exist = false)
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    @ApiModelProperty("移动端图片")
    private String mobimage;
    /**
     * 测试单数
     */
    @TableField(exist = false)
    @JSONField(name = "testtaskcnt")
    @JsonProperty("testtaskcnt")
    @ApiModelProperty("测试单数")
    private Integer testtaskcnt;
    /**
     * 套件数
     */
    @TableField(exist = false)
    @JSONField(name = "testsuitecnt")
    @JsonProperty("testsuitecnt")
    @ApiModelProperty("套件数")
    private Integer testsuitecnt;
    /**
     * 计划总数
     */
    @TableField(exist = false)
    @JSONField(name = "productplancnt")
    @JsonProperty("productplancnt")
    @ApiModelProperty("计划总数")
    private Integer productplancnt;
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
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 已关闭需求
     */
    @TableField(exist = false)
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    @ApiModelProperty("已关闭需求")
    private Integer closedstorycnt;
    /**
     * 相关Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "relatedbugcnt")
    @JsonProperty("relatedbugcnt")
    @ApiModelProperty("相关Bug数")
    private Integer relatedbugcnt;
    /**
     * 分组白名单
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "whitelist")
    @JSONField(name = "whitelist")
    @JsonProperty("whitelist")
    @ApiModelProperty("分组白名单")
    private String whitelist;
    /**
     * 部门标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "mdeptid")
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @ApiModelProperty("部门标识")
    private String mdeptid;
    /**
     * 发布总数
     */
    @TableField(exist = false)
    @JSONField(name = "releasecnt")
    @JsonProperty("releasecnt")
    @ApiModelProperty("发布总数")
    private Integer releasecnt;
    /**
     * 发布负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "rd")
    @JSONField(name = "rd")
    @JsonProperty("rd")
    @ApiModelProperty("发布负责人")
    private String rd;
    /**
     * 产品负责人（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "popk")
    @JsonProperty("popk")
    @ApiModelProperty("产品负责人（选择）")
    private String popk;
    /**
     * 未关闭Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "notclosedbugcnt")
    @JsonProperty("notclosedbugcnt")
    @ApiModelProperty("未关闭Bug数")
    private Integer notclosedbugcnt;
    /**
     * 支持产品汇报
     */
    @TableField(value = "supproreport")
    @JSONField(name = "supproreport")
    @JsonProperty("supproreport")
    @ApiModelProperty("支持产品汇报")
    private String supproreport;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "order")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 产品类型
     */
    @DEField(defaultValue = "normal")
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("产品类型")
    private String type;
    /**
     * 产品负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "po")
    @JSONField(name = "po")
    @JsonProperty("po")
    @ApiModelProperty("产品负责人")
    private String po;
    /**
     * 测试负责人（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "qdpk")
    @JsonProperty("qdpk")
    @ApiModelProperty("测试负责人（选择）")
    private String qdpk;
    /**
     * 产品描述	
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "desc")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("产品描述	")
    private String desc;
    /**
     * 状态
     */
    @DEField(defaultValue = "normal")
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("状态")
    private String status;
    /**
     * 已变更需求
     */
    @TableField(exist = false)
    @JSONField(name = "changedstorycnt")
    @JsonProperty("changedstorycnt")
    @ApiModelProperty("已变更需求")
    private Integer changedstorycnt;
    /**
     * 未解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    @ApiModelProperty("未解决Bug数")
    private Integer activebugcnt;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "createdby")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @ApiModelProperty("由谁创建")
    private String createdby;
    /**
     * 发布负责人（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "rdpk")
    @JsonProperty("rdpk")
    @ApiModelProperty("发布负责人（选择）")
    private String rdpk;
    /**
     * 当前系统版本
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "createdversion")
    @JSONField(name = "createdversion")
    @JsonProperty("createdversion")
    @ApiModelProperty("当前系统版本")
    private String createdversion;
    /**
     * 草稿需求
     */
    @TableField(exist = false)
    @JSONField(name = "draftstorycnt")
    @JsonProperty("draftstorycnt")
    @ApiModelProperty("草稿需求")
    private Integer draftstorycnt;
    /**
     * 文档数
     */
    @TableField(exist = false)
    @JSONField(name = "doccnt")
    @JsonProperty("doccnt")
    @ApiModelProperty("文档数")
    private Integer doccnt;
    /**
     * 用例数
     */
    @TableField(exist = false)
    @JSONField(name = "casecnt")
    @JsonProperty("casecnt")
    @ApiModelProperty("用例数")
    private Integer casecnt;
    /**
     * 关联项目数
     */
    @TableField(exist = false)
    @JSONField(name = "relatedprojects")
    @JsonProperty("relatedprojects")
    @ApiModelProperty("关联项目数")
    private Integer relatedprojects;
    /**
     * IBIZ标识
     */
    @DEField(name = "ibiz_id")
    @TableField(value = "ibiz_id")
    @JSONField(name = "ibiz_id")
    @JsonProperty("ibiz_id")
    @ApiModelProperty("IBIZ标识")
    private String ibizId;
    /**
     * 子状态
     */
    @TableField(value = "substatus")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @ApiModelProperty("子状态")
    private String substatus;
    /**
     * 产品代号
     */
    @TableField(value = "code")
    @JSONField(name = "code")
    @JsonProperty("code")
    @ApiModelProperty("产品代号")
    private String code;
    /**
     * 属性
     */
    @TableField(exist = false)
    @JSONField(name = "srfcount")
    @JsonProperty("srfcount")
    @ApiModelProperty("属性")
    private Integer srfcount;
    /**
     * 排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("排序")
    private Integer order1;
    /**
     * BUILD数
     */
    @TableField(exist = false)
    @JSONField(name = "buildcnt")
    @JsonProperty("buildcnt")
    @ApiModelProperty("BUILD数")
    private Integer buildcnt;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createddate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    @ApiModelProperty("创建日期")
    private Timestamp createddate;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 激活需求数
     */
    @TableField(exist = false)
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    @ApiModelProperty("激活需求数")
    private Integer activestorycnt;
    /**
     * 产品线
     */
    @TableField(exist = false)
    @JSONField(name = "linename")
    @JsonProperty("linename")
    @ApiModelProperty("产品线")
    private String linename;
    /**
     * 产品线
     */
    @DEField(defaultValue = "0")
    @TableField(value = "line")
    @JSONField(name = "line")
    @JsonProperty("line")
    @ApiModelProperty("产品线")
    private Long line;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "updateby")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 产品线
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Module moduleline;


    /**
     * 产品团队
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM> productteam;


    /**
     * 设置 [测试负责人]
     */
    public void setQd(String qd) {
        this.qd = qd;
        this.modify("qd", qd);
    }

    /**
     * 设置 [产品分类]
     */
    public void setProductclass(String productclass) {
        this.productclass = productclass;
        this.modify("productclass", productclass);
    }

    /**
     * 设置 [访问控制]
     */
    public void setAcl(String acl) {
        this.acl = acl;
        this.modify("acl", acl);
    }

    /**
     * 设置 [产品名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [分组白名单]
     */
    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
        this.modify("whitelist", whitelist);
    }

    /**
     * 设置 [发布负责人]
     */
    public void setRd(String rd) {
        this.rd = rd;
        this.modify("rd", rd);
    }

    /**
     * 设置 [支持产品汇报]
     */
    public void setSupproreport(String supproreport) {
        this.supproreport = supproreport;
        this.modify("supproreport", supproreport);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [产品类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [产品负责人]
     */
    public void setPo(String po) {
        this.po = po;
        this.modify("po", po);
    }

    /**
     * 设置 [产品描述	]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [当前系统版本]
     */
    public void setCreatedversion(String createdversion) {
        this.createdversion = createdversion;
        this.modify("createdversion", createdversion);
    }

    /**
     * 设置 [IBIZ标识]
     */
    public void setIbizId(String ibizId) {
        this.ibizId = ibizId;
        this.modify("ibiz_id", ibizId);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [产品代号]
     */
    public void setCode(String code) {
        this.code = code;
        this.modify("code", code);
    }

    /**
     * 设置 [产品线]
     */
    public void setLine(Long line) {
        this.line = line;
        this.modify("line", line);
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


