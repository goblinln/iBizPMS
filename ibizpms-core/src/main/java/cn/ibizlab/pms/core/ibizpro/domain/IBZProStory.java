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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;

/**
 * 实体[需求]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZPRO_STORY",resultMap = "IBZProStoryResultMap")
public class IBZProStory extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createdate" , fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 需求名称
     */
    @DEField(name = "ibzpro_storyname")
    @TableField(value = "ibzpro_storyname")
    @JSONField(name = "ibzprostoryname")
    @JsonProperty("ibzprostoryname")
    private String ibzprostoryname;
    /**
     * 需求标识
     */
    @DEField(name = "ibzpro_storyid" , isKeyField=true)
    @TableId(value= "ibzpro_storyid",type=IdType.ASSIGN_UUID)
    @JSONField(name = "ibzprostoryid")
    @JsonProperty("ibzprostoryid")
    private String ibzprostoryid;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "createman" , fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "updateman")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "updatedate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 产品（开发系统）标识
     */
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    private String product;
    /**
     * 需求模块标识
     */
    @TableField(value = "storymodule")
    @JSONField(name = "storymodule")
    @JsonProperty("storymodule")
    private String storymodule;
    /**
     * 编号
     */
    @TableField(value = "pmsstory")
    @JSONField(name = "pmsstory")
    @JsonProperty("pmsstory")
    private BigInteger pmsstory;
    /**
     * 需求
     */
    @TableField(exist = false)
    @JSONField(name = "pmsstoryname")
    @JsonProperty("pmsstoryname")
    private String pmsstoryname;
    /**
     * 产品
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    private String productname;
    /**
     * 需求模块
     */
    @TableField(exist = false)
    @JSONField(name = "storymodulename")
    @JsonProperty("storymodulename")
    private String storymodulename;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct ibzproProduct;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule ibzproStorymodule;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Story ztStory;



    /**
     * 设置 [需求名称]
     */
    public void setIbzprostoryname(String ibzprostoryname){
        this.ibzprostoryname = ibzprostoryname ;
        this.modify("ibzpro_storyname",ibzprostoryname);
    }

    /**
     * 设置 [产品（开发系统）标识]
     */
    public void setProduct(String product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [需求模块标识]
     */
    public void setStorymodule(String storymodule){
        this.storymodule = storymodule ;
        this.modify("storymodule",storymodule);
    }

    /**
     * 设置 [编号]
     */
    public void setPmsstory(BigInteger pmsstory){
        this.pmsstory = pmsstory ;
        this.modify("pmsstory",pmsstory);
    }


}


