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
 * 实体[项目中需要做的需求]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_projectstory", resultMap = "ProjectStoryResultMap")
@ApiModel("项目中需要做的需求")
public class ProjectStory extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 虚拟主键
     */
    @DEField(isKeyField = true)
    @TableField(exist = false)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("虚拟主键")
    private String id;
    /**
     * 排序
     */
    @TableField(value = "`order`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 需求版本
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`version`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("需求版本")
    private Integer version;
    /**
     * 需求
     */
    @TableField(value = "`story`")
    @JSONField(name = "story")
    @JsonProperty("story")
    @ApiModelProperty("需求")
    private Long story;
    /**
     * 项目
     */
    @TableField(value = "`project`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private Long project;
    /**
     * 所属产品
     */
    @TableField(value = "`product`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("所属产品")
    private Long product;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`createby`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`updateby`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`org`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`dept`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;

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
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Story ztstory;



    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [需求版本]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [需求]
     */
    public void setStory(Long story) {
        this.story = story;
        this.modify("story", story);
    }

    /**
     * 设置 [项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
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


