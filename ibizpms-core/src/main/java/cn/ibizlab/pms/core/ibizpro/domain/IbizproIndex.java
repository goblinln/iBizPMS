package cn.ibizlab.pms.core.ibizpro.domain;

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
 * 实体[索引检索]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "", resultMap = "IbizproIndexResultMap")
@ApiModel("索引检索")
public class IbizproIndex extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @DEField(isKeyField = true)
    @TableField(exist = false)
    @JSONField(name = "indexid")
    @JsonProperty("indexid")
    @ApiModelProperty("主键")
    private Long indexid;
    /**
     * 权限
     */
    @TableField(exist = false)
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("权限")
    private String acl;
    /**
     * docid
     */
    @TableField(exist = false)
    @JSONField(name = "docid")
    @JsonProperty("docid")
    @ApiModelProperty("docid")
    private String docid;
    /**
     * 标题[需求、任务等]
     */
    @TableField(exist = false)
    @JSONField(name = "indexname")
    @JsonProperty("indexname")
    @ApiModelProperty("标题[需求、任务等]")
    private String indexname;
    /**
     * 颜色
     */
    @TableField(exist = false)
    @JSONField(name = "color")
    @JsonProperty("color")
    @ApiModelProperty("颜色")
    private String color;
    /**
     * 逻辑标识
     */
    @TableField(exist = false)
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("逻辑标识")
    private String deleted;
    /**
     * 权限列表
     */
    @TableField(exist = false)
    @JSONField(name = "acllist")
    @JsonProperty("acllist")
    @ApiModelProperty("权限列表")
    private String acllist;
    /**
     * 内容[需求、任务等]
     */
    @TableField(exist = false)
    @JSONField(name = "indexdesc")
    @JsonProperty("indexdesc")
    @ApiModelProperty("内容[需求、任务等]")
    private String indexdesc;
    /**
     * 产品
     */
    @TableField(exist = false)
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
    private Long product;
    /**
     * 类型
     */
    @TableField(exist = false)
    @JSONField(name = "indextype")
    @JsonProperty("indextype")
    @ApiModelProperty("类型")
    private String indextype;
    /**
     * 部门标识
     */
    @TableField(exist = false)
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @ApiModelProperty("部门标识")
    private String mdeptid;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private Long project;
    /**
     * 组织标识
     */
    @TableField(exist = false)
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织标识")
    private String orgid;




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
        this.reset("indexid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


