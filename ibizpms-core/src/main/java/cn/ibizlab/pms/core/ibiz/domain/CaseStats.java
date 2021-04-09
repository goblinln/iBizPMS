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
 * 实体[测试用例统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_case", resultMap = "CaseStatsResultMap")
@ApiModel("测试用例统计")
public class CaseStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通过用例数
     */
    @TableField(exist = false)
    @JSONField(name = "passcase")
    @JsonProperty("passcase")
    @ApiModelProperty("通过用例数")
    private Integer passcase;
    /**
     * 阻塞用例数
     */
    @TableField(exist = false)
    @JSONField(name = "blockedcase")
    @JsonProperty("blockedcase")
    @ApiModelProperty("阻塞用例数")
    private Integer blockedcase;
    /**
     * 总执行数
     */
    @TableField(exist = false)
    @JSONField(name = "totalruncase")
    @JsonProperty("totalruncase")
    @ApiModelProperty("总执行数")
    private Integer totalruncase;
    /**
     * 失败用例数
     */
    @TableField(exist = false)
    @JSONField(name = "failcase")
    @JsonProperty("failcase")
    @ApiModelProperty("失败用例数")
    private Integer failcase;
    /**
     * 用例标题
     */
    @TableField(value = "`title`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("用例标题")
    private String title;
    /**
     * 总用例数
     */
    @TableField(exist = false)
    @JSONField(name = "totalcase")
    @JsonProperty("totalcase")
    @ApiModelProperty("总用例数")
    private Integer totalcase;
    /**
     * 用例通过率
     */
    @TableField(exist = false)
    @JSONField(name = "passrate")
    @JsonProperty("passrate")
    @ApiModelProperty("用例通过率")
    private String passrate;
    /**
     * 用例编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("用例编号")
    private Long id;
    /**
     * 模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("模块名称")
    private String modulename;
    /**
     * 产品
     */
    @TableField(value = "`product`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
    private Long product;
    /**
     * 模块
     */
    @TableField(value = "`module`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("模块")
    private Long module;

    /**
     * 模块
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Module ztmodule;

    /**
     * 产品
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztproduct;



    /**
     * 设置 [用例标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [模块]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
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


