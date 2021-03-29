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
 * 实体[产品汇总表]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_product", resultMap = "ProductSumResultMap")
public class ProductSum extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设计缺陷
     */
    @TableField(exist = false)
    @JSONField(name = "designdefect")
    @JsonProperty("designdefect")
    private Integer designdefect;
    /**
     * 已变更
     */
    @TableField(exist = false)
    @JSONField(name = "changedstorycnt")
    @JsonProperty("changedstorycnt")
    private Integer changedstorycnt;
    /**
     * 研发中阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "developingstagestoryhours")
    @JsonProperty("developingstagestoryhours")
    private Integer developingstagestoryhours;
    /**
     * 总计
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    private Integer storycnt;
    /**
     * 其他
     */
    @TableField(exist = false)
    @JSONField(name = "others")
    @JsonProperty("others")
    private Integer others;
    /**
     * 配置相关
     */
    @TableField(exist = false)
    @JSONField(name = "config")
    @JsonProperty("config")
    private Integer config;
    /**
     * 代码错误
     */
    @TableField(exist = false)
    @JSONField(name = "codeerror")
    @JsonProperty("codeerror")
    private Integer codeerror;
    /**
     * bug总计
     */
    @TableField(exist = false)
    @JSONField(name = "bugsum")
    @JsonProperty("bugsum")
    private Integer bugsum;
    /**
     * 性能问题
     */
    @TableField(exist = false)
    @JSONField(name = "performance")
    @JsonProperty("performance")
    private Integer performance;
    /**
     * 结束日期
     */
    @TableField(exist = false)
    @JSONField(name = "end")
    @JsonProperty("end")
    private String end;
    /**
     * 已关闭
     */
    @TableField(exist = false)
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    private Integer closedstorycnt;
    /**
     * 已关闭阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "closedstagestoryhours")
    @JsonProperty("closedstagestoryhours")
    private Integer closedstagestoryhours;
    /**
     * 测试完毕阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "testedstagestoryhours")
    @JsonProperty("testedstagestoryhours")
    private Integer testedstagestoryhours;
    /**
     * 未开始阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "waitstagestoryhours")
    @JsonProperty("waitstagestoryhours")
    private Integer waitstagestoryhours;
    /**
     * 产品负责人
     */
    @TableField(value = "`po`")
    @JSONField(name = "po")
    @JsonProperty("po")
    private String po;
    /**
     * 测试中阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "testingstagestoryhours")
    @JsonProperty("testingstagestoryhours")
    private Integer testingstagestoryhours;
    /**
     * 已立项阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "projectedstagestoryhours")
    @JsonProperty("projectedstagestoryhours")
    private Integer projectedstagestoryhours;
    /**
     * 已立项阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "projectedstagestorycnt")
    @JsonProperty("projectedstagestorycnt")
    private Integer projectedstagestorycnt;
    /**
     * 已验收阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "verifiedstagestorycnt")
    @JsonProperty("verifiedstagestorycnt")
    private Integer verifiedstagestorycnt;
    /**
     * 主键标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalhours")
    @JsonProperty("totalhours")
    private Integer totalhours;
    /**
     * 已计划阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "planedstagestorycnt")
    @JsonProperty("planedstagestorycnt")
    private Integer planedstagestorycnt;
    /**
     * 研发中阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "developingstagestorycnt")
    @JsonProperty("developingstagestorycnt")
    private Integer developingstagestorycnt;
    /**
     * 激活
     */
    @TableField(exist = false)
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    private Integer activestorycnt;
    /**
     * Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    private Integer bugcnt;
    /**
     * 未开始阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "waitstagestorycnt")
    @JsonProperty("waitstagestorycnt")
    private Integer waitstagestorycnt;
    /**
     * 已验收阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "verifiedstagestoryhours")
    @JsonProperty("verifiedstagestoryhours")
    private Integer verifiedstagestoryhours;
    /**
     * 草稿
     */
    @TableField(exist = false)
    @JSONField(name = "waitstorycnt")
    @JsonProperty("waitstorycnt")
    private Integer waitstorycnt;
    /**
     * 测试中阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "testingstagestorycnt")
    @JsonProperty("testingstagestorycnt")
    private Integer testingstagestorycnt;
    /**
     * 开始日期
     */
    @TableField(exist = false)
    @JSONField(name = "begin")
    @JsonProperty("begin")
    private String begin;
    /**
     * 计划
     */
    @TableField(exist = false)
    @JSONField(name = "plan")
    @JsonProperty("plan")
    private Long plan;
    /**
     * 已发布阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstagestoryhours")
    @JsonProperty("releasedstagestoryhours")
    private Integer releasedstagestoryhours;
    /**
     * 研发完毕阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "developedstagestoryhours")
    @JsonProperty("developedstagestoryhours")
    private Integer developedstagestoryhours;
    /**
     * 产品名称
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 研发完毕阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "developedstagestorycnt")
    @JsonProperty("developedstagestorycnt")
    private Integer developedstagestorycnt;
    /**
     * 测试脚本
     */
    @TableField(exist = false)
    @JSONField(name = "automation")
    @JsonProperty("automation")
    private Integer automation;
    /**
     * 已计划阶段需求工时
     */
    @TableField(exist = false)
    @JSONField(name = "planedstagestoryhours")
    @JsonProperty("planedstagestoryhours")
    private Integer planedstagestoryhours;
    /**
     * 安装部署
     */
    @TableField(exist = false)
    @JSONField(name = "install")
    @JsonProperty("install")
    private Integer install;
    /**
     * 安全相关
     */
    @TableField(exist = false)
    @JSONField(name = "security")
    @JsonProperty("security")
    private Integer security;
    /**
     * 已发布阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstagestorycnt")
    @JsonProperty("releasedstagestorycnt")
    private Integer releasedstagestorycnt;
    /**
     * 测试完毕阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "testedstagestorycnt")
    @JsonProperty("testedstagestorycnt")
    private Integer testedstagestorycnt;
    /**
     * 标准规范
     */
    @TableField(exist = false)
    @JSONField(name = "standard")
    @JsonProperty("standard")
    private Integer standard;
    /**
     * 已关闭阶段需求数量
     */
    @TableField(exist = false)
    @JSONField(name = "closedstagestorycnt")
    @JsonProperty("closedstagestorycnt")
    private Integer closedstagestorycnt;



    /**
     * 设置 [产品负责人]
     */
    public void setPo(String po) {
        this.po = po;
        this.modify("po", po);
    }

    /**
     * 设置 [产品名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
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


