package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;


import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;


/**
 * 实体[relation]
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "zt_relation",resultMap = "RelationResultMap")
public class Relation extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * BID
     */
    @TableField(value = "bid")
    @JSONField(name = "bid")
    @JsonProperty("bid")
    private Integer bid;
    /**
     * AVersion
     */
    @TableField(value = "aversion")
    @JSONField(name = "aversion")
    @JsonProperty("aversion")
    private String aversion;
    /**
     * BType
     */
    @TableField(value = "btype")
    @JSONField(name = "btype")
    @JsonProperty("btype")
    private String btype;
    /**
     * project
     */
    @TableField(value = "project")
    @JSONField(name = "project")
    @JsonProperty("project")
    private Integer project;
    /**
     * extra
     */
    @TableField(value = "extra")
    @JSONField(name = "extra")
    @JsonProperty("extra")
    private String extra;
    /**
     * relation
     */
    @TableField(value = "relation")
    @JSONField(name = "relation")
    @JsonProperty("relation")
    private String relation;
    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.UUID)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * program
     */
    @TableField(value = "program")
    @JSONField(name = "program")
    @JsonProperty("program")
    private Integer program;
    /**
     * BVersion
     */
    @TableField(value = "bversion")
    @JSONField(name = "bversion")
    @JsonProperty("bversion")
    private String bversion;
    /**
     * AType
     */
    @TableField(value = "atype")
    @JSONField(name = "atype")
    @JsonProperty("atype")
    private String atype;
    /**
     * product
     */
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Integer product;
    /**
     * AID
     */
    @TableField(value = "aid")
    @JSONField(name = "aid")
    @JsonProperty("aid")
    private Integer aid;



    /**
     * 设置 [BID]
     */
    public void setBid(Integer bid){
        this.bid = bid ;
        this.modify("bid",bid);
    }
    /**
     * 设置 [AVersion]
     */
    public void setAversion(String aversion){
        this.aversion = aversion ;
        this.modify("aversion",aversion);
    }
    /**
     * 设置 [BType]
     */
    public void setBtype(String btype){
        this.btype = btype ;
        this.modify("btype",btype);
    }
    /**
     * 设置 [project]
     */
    public void setProject(Integer project){
        this.project = project ;
        this.modify("project",project);
    }
    /**
     * 设置 [extra]
     */
    public void setExtra(String extra){
        this.extra = extra ;
        this.modify("extra",extra);
    }
    /**
     * 设置 [relation]
     */
    public void setRelation(String relation){
        this.relation = relation ;
        this.modify("relation",relation);
    }
    /**
     * 设置 [program]
     */
    public void setProgram(Integer program){
        this.program = program ;
        this.modify("program",program);
    }
    /**
     * 设置 [BVersion]
     */
    public void setBversion(String bversion){
        this.bversion = bversion ;
        this.modify("bversion",bversion);
    }
    /**
     * 设置 [AType]
     */
    public void setAtype(String atype){
        this.atype = atype ;
        this.modify("atype",atype);
    }
    /**
     * 设置 [product]
     */
    public void setProduct(Integer product){
        this.product = product ;
        this.modify("product",product);
    }
    /**
     * 设置 [AID]
     */
    public void setAid(Integer aid){
        this.aid = aid ;
        this.modify("aid",aid);
    }

}


