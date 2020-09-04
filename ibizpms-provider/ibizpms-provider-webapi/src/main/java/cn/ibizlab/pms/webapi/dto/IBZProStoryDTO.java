package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;

/**
 * 服务DTO对象[IBZProStoryDTO]
 */
@Data
public class IBZProStoryDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    private BigInteger module;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    private BigInteger product;

    /**
     * 属性 [IBIZ_ID]
     *
     */
    @JSONField(name = "ibizid")
    @JsonProperty("ibizid")
    private String ibizid;

    /**
     * 属性 [SOURCE]
     *
     */
    @JSONField(name = "source")
    @JsonProperty("source")
    private String source;

    /**
     * 属性 [SOURCENOTE]
     *
     */
    @JSONField(name = "sourcenote")
    @JsonProperty("sourcenote")
    private String sourcenote;

    /**
     * 属性 [SOURCEID]
     *
     */
    @JSONField(name = "sourceid")
    @JsonProperty("sourceid")
    private String sourceid;

    /**
     * 属性 [SOURCENAME]
     *
     */
    @JSONField(name = "sourcename")
    @JsonProperty("sourcename")
    private String sourcename;

    /**
     * 属性 [SOURCEOBJECT]
     *
     */
    @JSONField(name = "sourceobject")
    @JsonProperty("sourceobject")
    private String sourceobject;

    /**
     * 属性 [PLAN]
     *
     */
    @JSONField(name = "plan")
    @JsonProperty("plan")
    private String plan;

    /**
     * 属性 [ESTIMATE]
     *
     */
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    private Double estimate;

    /**
     * 属性 [OPENEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "openeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    private Timestamp openeddate;

    /**
     * 属性 [CLOSEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "closeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    private Timestamp closeddate;

    /**
     * 属性 [LASTEDITEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "lastediteddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    private Timestamp lastediteddate;


    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [MODULE]
     */
    public void setModule(BigInteger  module){
        this.module = module ;
        this.modify("module",module);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(BigInteger  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [IBIZ_ID]
     */
    public void setIbizid(String  ibizid){
        this.ibizid = ibizid ;
        this.modify("ibiz_id",ibizid);
    }

    /**
     * 设置 [SOURCE]
     */
    public void setSource(String  source){
        this.source = source ;
        this.modify("source",source);
    }

    /**
     * 设置 [SOURCENOTE]
     */
    public void setSourcenote(String  sourcenote){
        this.sourcenote = sourcenote ;
        this.modify("sourcenote",sourcenote);
    }

    /**
     * 设置 [SOURCEID]
     */
    public void setSourceid(String  sourceid){
        this.sourceid = sourceid ;
        this.modify("sourceid",sourceid);
    }

    /**
     * 设置 [SOURCENAME]
     */
    public void setSourcename(String  sourcename){
        this.sourcename = sourcename ;
        this.modify("sourcename",sourcename);
    }

    /**
     * 设置 [SOURCEOBJECT]
     */
    public void setSourceobject(String  sourceobject){
        this.sourceobject = sourceobject ;
        this.modify("sourceobject",sourceobject);
    }

    /**
     * 设置 [PLAN]
     */
    public void setPlan(String  plan){
        this.plan = plan ;
        this.modify("plan",plan);
    }

    /**
     * 设置 [ESTIMATE]
     */
    public void setEstimate(Double  estimate){
        this.estimate = estimate ;
        this.modify("estimate",estimate);
    }

    /**
     * 设置 [OPENEDDATE]
     */
    public void setOpeneddate(Timestamp  openeddate){
        this.openeddate = openeddate ;
        this.modify("openeddate",openeddate);
    }

    /**
     * 设置 [CLOSEDDATE]
     */
    public void setCloseddate(Timestamp  closeddate){
        this.closeddate = closeddate ;
        this.modify("closeddate",closeddate);
    }

    /**
     * 设置 [LASTEDITEDDATE]
     */
    public void setLastediteddate(Timestamp  lastediteddate){
        this.lastediteddate = lastediteddate ;
        this.modify("lastediteddate",lastediteddate);
    }


}

