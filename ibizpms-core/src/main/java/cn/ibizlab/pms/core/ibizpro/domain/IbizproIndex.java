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


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import cn.ibizlab.pms.util.domain.EntityMongo;

/**
 * 大数据 [索引检索] 对象
 */
@Data
@Document(collection = "ibizproindex")
@ApiModel("索引检索")
public class IbizproIndex extends EntityMongo implements Serializable {


    /**
     * 主键
     */
    @Id()
    @JSONField(name = "indexid")
    @JsonProperty("indexid")
    @ApiModelProperty("主键")
    private Long indexid;


    /**
     * 权限
     */
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @Field(name = "acl")
    @ApiModelProperty("权限")
    private String acl;


    /**
     * docid
     */
    @JSONField(name = "docid")
    @JsonProperty("docid")
    @Field(name = "docid")
    @ApiModelProperty("docid")
    private String docid;


    /**
     * 标题[需求、任务等]
     */
    @JSONField(name = "indexname")
    @JsonProperty("indexname")
    @Field(name = "indexname")
    @ApiModelProperty("标题[需求、任务等]")
    private String indexname;


    /**
     * 颜色
     */
    @JSONField(name = "color")
    @JsonProperty("color")
    @Field(name = "color")
    @ApiModelProperty("颜色")
    private String color;


    /**
     * 逻辑标识
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Field(name = "deleted")
    @ApiModelProperty("逻辑标识")
    private String deleted;


    /**
     * 权限列表
     */
    @JSONField(name = "acllist")
    @JsonProperty("acllist")
    @Field(name = "acllist")
    @ApiModelProperty("权限列表")
    private String acllist;


    /**
     * 内容[需求、任务等]
     */
    @JSONField(name = "indexdesc")
    @JsonProperty("indexdesc")
    @Field(name = "indexdesc")
    @ApiModelProperty("内容[需求、任务等]")
    private String indexdesc;


    /**
     * 产品
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @Field(name = "product")
    @ApiModelProperty("产品")
    private Long product;


    /**
     * 类型
     */
    @JSONField(name = "indextype")
    @JsonProperty("indextype")
    @Field(name = "index_type")
    @ApiModelProperty("类型")
    private String indextype;


    /**
     * 部门标识
     */
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @Field(name = "mdeptid")
    @ApiModelProperty("部门标识")
    private String mdeptid;


    /**
     * 项目
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @Field(name = "project")
    @ApiModelProperty("项目")
    private Long project;


    /**
     * 组织标识
     */
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @Field(name = "orgid")
    @ApiModelProperty("组织标识")
    private String orgid;





}


