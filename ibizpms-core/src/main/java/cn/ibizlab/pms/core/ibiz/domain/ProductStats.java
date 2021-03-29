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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[产品统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_product", resultMap = "ProductStatsResultMap")
public class ProductStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品代号
     */
    @TableField(value = "`code`")
    @JSONField(name = "code")
    @JsonProperty("code")
    private String code;
    /**
     * 产品编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 已完成的需求数
     */
    @TableField(exist = false)
    @JSONField(name = "finishedstorycnt")
    @JsonProperty("finishedstorycnt")
    private Integer finishedstorycnt;
    /**
     * 已发布需求数
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstorycnt")
    @JsonProperty("releasedstorycnt")
    private Integer releasedstorycnt;
    /**
     * 严重bug比
     */
    @TableField(exist = false)
    @JSONField(name = "importantbugpercent")
    @JsonProperty("importantbugpercent")
    private String importantbugpercent;
    /**
     * 未完成关联项目数
     */
    @TableField(exist = false)
    @JSONField(name = "undoneresprojectcnt")
    @JsonProperty("undoneresprojectcnt")
    private Integer undoneresprojectcnt;
    /**
     * 指派给我的Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "assigntomebugcnt")
    @JsonProperty("assigntomebugcnt")
    private Integer assigntomebugcnt;
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
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    private Integer istop;
    /**
     * 关联项目数
     */
    @TableField(exist = false)
    @JSONField(name = "resprojectcnt")
    @JsonProperty("resprojectcnt")
    private Integer resprojectcnt;
    /**
     * 所有Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    private Integer bugcnt;
    /**
     * 已消耗工时
     */
    @TableField(exist = false)
    @JSONField(name = "haveconsumed")
    @JsonProperty("haveconsumed")
    private Double haveconsumed;
    /**
     * 产品类型
     */
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 未开始需求数
     */
    @TableField(exist = false)
    @JSONField(name = "waitstorycnt")
    @JsonProperty("waitstorycnt")
    private Integer waitstorycnt;
    /**
     * 需求所提bug数
     */
    @TableField(exist = false)
    @JSONField(name = "bugstory")
    @JsonProperty("bugstory")
    private Integer bugstory;
    /**
     * 需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    private Integer storycnt;
    /**
     * 产品名称
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 重要的Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "importantbugcnt")
    @JsonProperty("importantbugcnt")
    private Integer importantbugcnt;
    /**
     * 昨天关闭Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayclosedbugcnt")
    @JsonProperty("yesterdayclosedbugcnt")
    private Integer yesterdayclosedbugcnt;
    /**
     * 状态
     */
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 昨天解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayresolvedbugcnt")
    @JsonProperty("yesterdayresolvedbugcnt")
    private Integer yesterdayresolvedbugcnt;
    /**
     * 开发中需求数
     */
    @TableField(exist = false)
    @JSONField(name = "developingstorycnt")
    @JsonProperty("developingstorycnt")
    private Integer developingstorycnt;
    /**
     * 激活需求数
     */
    @TableField(exist = false)
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    private Integer activestorycnt;
    /**
     * 未关闭Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "notclosedbugcnt")
    @JsonProperty("notclosedbugcnt")
    private Integer notclosedbugcnt;
    /**
     * 维护中发布数
     */
    @TableField(exist = false)
    @JSONField(name = "normalreleasecnt")
    @JsonProperty("normalreleasecnt")
    private Integer normalreleasecnt;
    /**
     * 产品排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    private Integer order1;
    /**
     * 当前项目
     */
    @TableField(exist = false)
    @JSONField(name = "currproject")
    @JsonProperty("currproject")
    private String currproject;
    /**
     * 测试中需求数
     */
    @TableField(exist = false)
    @JSONField(name = "testingstorycnt")
    @JsonProperty("testingstorycnt")
    private Integer testingstorycnt;
    /**
     * 计划总数
     */
    @TableField(exist = false)
    @JSONField(name = "productplancnt")
    @JsonProperty("productplancnt")
    private Integer productplancnt;
    /**
     * 发布总数
     */
    @TableField(exist = false)
    @JSONField(name = "releasecnt")
    @JsonProperty("releasecnt")
    private Integer releasecnt;
    /**
     * 昨天确认Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayconfirmbugcnt")
    @JsonProperty("yesterdayconfirmbugcnt")
    private Integer yesterdayconfirmbugcnt;
    /**
     * 已延期
     */
    @TableField(exist = false)
    @JSONField(name = "postponedprojectcnt")
    @JsonProperty("postponedprojectcnt")
    private Integer postponedprojectcnt;
    /**
     * 未过期计划数
     */
    @TableField(exist = false)
    @JSONField(name = "unendproductplancnt")
    @JsonProperty("unendproductplancnt")
    private Integer unendproductplancnt;
    /**
     * 解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "resolvedbugcnt")
    @JsonProperty("resolvedbugcnt")
    private Integer resolvedbugcnt;
    /**
     * 已计划需求数
     */
    @TableField(exist = false)
    @JSONField(name = "plannedstorycnt")
    @JsonProperty("plannedstorycnt")
    private Integer plannedstorycnt;
    /**
     * 未确认Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "unconfirmbugcnt")
    @JsonProperty("unconfirmbugcnt")
    private Integer unconfirmbugcnt;
    /**
     * 未解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    private Integer activebugcnt;



    /**
     * 设置 [产品代号]
     */
    public void setCode(String code) {
        this.code = code;
        this.modify("code", code);
    }

    /**
     * 设置 [产品类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [产品名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
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


