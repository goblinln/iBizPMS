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
 * 实体[relation]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_relation", resultMap = "RelationResultMap")
@ApiModel("relation")
public class Relation extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * BID
     */
    @TableField(value = "bid")
    @JSONField(name = "bid")
    @JsonProperty("bid")
    @ApiModelProperty("BID")
    private Integer bid;
    /**
     * AVersion
     */
    @TableField(value = "aversion")
    @JSONField(name = "aversion")
    @JsonProperty("aversion")
    @ApiModelProperty("AVersion")
    private String aversion;
    /**
     * BType
     */
    @TableField(value = "btype")
    @JSONField(name = "btype")
    @JsonProperty("btype")
    @ApiModelProperty("BType")
    private String btype;
    /**
     * project
     */
    @TableField(value = "project")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("project")
    private Integer project;
    /**
     * extra
     */
    @TableField(value = "extra")
    @JSONField(name = "extra")
    @JsonProperty("extra")
    @ApiModelProperty("extra")
    private String extra;
    /**
     * relation
     */
    @TableField(value = "relation")
    @JSONField(name = "relation")
    @JsonProperty("relation")
    @ApiModelProperty("relation")
    private String relation;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * program
     */
    @TableField(value = "program")
    @JSONField(name = "program")
    @JsonProperty("program")
    @ApiModelProperty("program")
    private Integer program;
    /**
     * BVersion
     */
    @TableField(value = "bversion")
    @JSONField(name = "bversion")
    @JsonProperty("bversion")
    @ApiModelProperty("BVersion")
    private String bversion;
    /**
     * AType
     */
    @TableField(value = "atype")
    @JSONField(name = "atype")
    @JsonProperty("atype")
    @ApiModelProperty("AType")
    private String atype;
    /**
     * product
     */
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("product")
    private Integer product;
    /**
     * AID
     */
    @TableField(value = "aid")
    @JSONField(name = "aid")
    @JsonProperty("aid")
    @ApiModelProperty("AID")
    private Integer aid;



    /**
     * 设置 [BID]
     */
    public void setBid(Integer bid) {
        this.bid = bid;
        this.modify("bid", bid);
    }

    /**
     * 设置 [AVersion]
     */
    public void setAversion(String aversion) {
        this.aversion = aversion;
        this.modify("aversion", aversion);
    }

    /**
     * 设置 [BType]
     */
    public void setBtype(String btype) {
        this.btype = btype;
        this.modify("btype", btype);
    }

    /**
     * 设置 [project]
     */
    public void setProject(Integer project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [extra]
     */
    public void setExtra(String extra) {
        this.extra = extra;
        this.modify("extra", extra);
    }

    /**
     * 设置 [relation]
     */
    public void setRelation(String relation) {
        this.relation = relation;
        this.modify("relation", relation);
    }

    /**
     * 设置 [program]
     */
    public void setProgram(Integer program) {
        this.program = program;
        this.modify("program", program);
    }

    /**
     * 设置 [BVersion]
     */
    public void setBversion(String bversion) {
        this.bversion = bversion;
        this.modify("bversion", bversion);
    }

    /**
     * 设置 [AType]
     */
    public void setAtype(String atype) {
        this.atype = atype;
        this.modify("atype", atype);
    }

    /**
     * 设置 [product]
     */
    public void setProduct(Integer product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [AID]
     */
    public void setAid(Integer aid) {
        this.aid = aid;
        this.modify("aid", aid);
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


