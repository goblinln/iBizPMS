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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[ProductStatsDTO]
 */
@Data
@ApiModel("产品统计")
public class ProductStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [CODE]
     *
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    @Size(min = 0, max = 45, message = "内容长度必须小于等于[45]")
    @ApiModelProperty("产品代号")
    private String code;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品编号")
    private Long id;

    /**
     * 属性 [FINISHEDSTORYCNT]
     *
     */
    @JSONField(name = "finishedstorycnt")
    @JsonProperty("finishedstorycnt")
    @ApiModelProperty("已完成的需求数")
    private Integer finishedstorycnt;

    /**
     * 属性 [RELEASEDSTORYCNT]
     *
     */
    @JSONField(name = "releasedstorycnt")
    @JsonProperty("releasedstorycnt")
    @ApiModelProperty("已发布需求数")
    private Integer releasedstorycnt;

    /**
     * 属性 [IMPORTANTBUGPERCENT]
     *
     */
    @JSONField(name = "importantbugpercent")
    @JsonProperty("importantbugpercent")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("严重bug比")
    private String importantbugpercent;

    /**
     * 属性 [UNDONERESPROJECTCNT]
     *
     */
    @JSONField(name = "undoneresprojectcnt")
    @JsonProperty("undoneresprojectcnt")
    @ApiModelProperty("未完成关联项目数")
    private Integer undoneresprojectcnt;

    /**
     * 属性 [ASSIGNTOMEBUGCNT]
     *
     */
    @JSONField(name = "assigntomebugcnt")
    @JsonProperty("assigntomebugcnt")
    @ApiModelProperty("指派给我的Bug数")
    private Integer assigntomebugcnt;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [ISTOP]
     *
     */
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;

    /**
     * 属性 [RESPROJECTCNT]
     *
     */
    @JSONField(name = "resprojectcnt")
    @JsonProperty("resprojectcnt")
    @ApiModelProperty("关联项目数")
    private Integer resprojectcnt;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("所有Bug数")
    private Integer bugcnt;

    /**
     * 属性 [HAVECONSUMED]
     *
     */
    @JSONField(name = "haveconsumed")
    @JsonProperty("haveconsumed")
    @ApiModelProperty("已消耗工时")
    private Double haveconsumed;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("产品类型")
    private String type;

    /**
     * 属性 [WAITSTORYCNT]
     *
     */
    @JSONField(name = "waitstorycnt")
    @JsonProperty("waitstorycnt")
    @ApiModelProperty("未开始需求数")
    private Integer waitstorycnt;

    /**
     * 属性 [BUGSTORY]
     *
     */
    @JSONField(name = "bugstory")
    @JsonProperty("bugstory")
    @ApiModelProperty("需求所提bug数")
    private Integer bugstory;

    /**
     * 属性 [STORYCNT]
     *
     */
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求总数")
    private Integer storycnt;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[产品名称]不允许为空!")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 属性 [IMPORTANTBUGCNT]
     *
     */
    @JSONField(name = "importantbugcnt")
    @JsonProperty("importantbugcnt")
    @ApiModelProperty("重要的Bug数")
    private Integer importantbugcnt;

    /**
     * 属性 [YESTERDAYCLOSEDBUGCNT]
     *
     */
    @JSONField(name = "yesterdayclosedbugcnt")
    @JsonProperty("yesterdayclosedbugcnt")
    @ApiModelProperty("昨天关闭Bug数")
    private Integer yesterdayclosedbugcnt;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("状态")
    private String status;

    /**
     * 属性 [YESTERDAYRESOLVEDBUGCNT]
     *
     */
    @JSONField(name = "yesterdayresolvedbugcnt")
    @JsonProperty("yesterdayresolvedbugcnt")
    @ApiModelProperty("昨天解决Bug数")
    private Integer yesterdayresolvedbugcnt;

    /**
     * 属性 [DEVELOPINGSTORYCNT]
     *
     */
    @JSONField(name = "developingstorycnt")
    @JsonProperty("developingstorycnt")
    @ApiModelProperty("开发中需求数")
    private Integer developingstorycnt;

    /**
     * 属性 [ACTIVESTORYCNT]
     *
     */
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    @ApiModelProperty("激活需求数")
    private Integer activestorycnt;

    /**
     * 属性 [NOTCLOSEDBUGCNT]
     *
     */
    @JSONField(name = "notclosedbugcnt")
    @JsonProperty("notclosedbugcnt")
    @ApiModelProperty("未关闭Bug数")
    private Integer notclosedbugcnt;

    /**
     * 属性 [NORMALRELEASECNT]
     *
     */
    @JSONField(name = "normalreleasecnt")
    @JsonProperty("normalreleasecnt")
    @ApiModelProperty("维护中发布数")
    private Integer normalreleasecnt;

    /**
     * 属性 [ORDER1]
     *
     */
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("产品排序")
    private Integer order1;

    /**
     * 属性 [CURRPROJECT]
     *
     */
    @JSONField(name = "currproject")
    @JsonProperty("currproject")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("当前项目")
    private String currproject;

    /**
     * 属性 [TESTINGSTORYCNT]
     *
     */
    @JSONField(name = "testingstorycnt")
    @JsonProperty("testingstorycnt")
    @ApiModelProperty("测试中需求数")
    private Integer testingstorycnt;

    /**
     * 属性 [PRODUCTPLANCNT]
     *
     */
    @JSONField(name = "productplancnt")
    @JsonProperty("productplancnt")
    @ApiModelProperty("计划总数")
    private Integer productplancnt;

    /**
     * 属性 [RELEASECNT]
     *
     */
    @JSONField(name = "releasecnt")
    @JsonProperty("releasecnt")
    @ApiModelProperty("发布总数")
    private Integer releasecnt;

    /**
     * 属性 [YESTERDAYCONFIRMBUGCNT]
     *
     */
    @JSONField(name = "yesterdayconfirmbugcnt")
    @JsonProperty("yesterdayconfirmbugcnt")
    @ApiModelProperty("昨天确认Bug数")
    private Integer yesterdayconfirmbugcnt;

    /**
     * 属性 [POSTPONEDPROJECTCNT]
     *
     */
    @JSONField(name = "postponedprojectcnt")
    @JsonProperty("postponedprojectcnt")
    @ApiModelProperty("已延期")
    private Integer postponedprojectcnt;

    /**
     * 属性 [UNENDPRODUCTPLANCNT]
     *
     */
    @JSONField(name = "unendproductplancnt")
    @JsonProperty("unendproductplancnt")
    @ApiModelProperty("未过期计划数")
    private Integer unendproductplancnt;

    /**
     * 属性 [RESOLVEDBUGCNT]
     *
     */
    @JSONField(name = "resolvedbugcnt")
    @JsonProperty("resolvedbugcnt")
    @ApiModelProperty("解决Bug数")
    private Integer resolvedbugcnt;

    /**
     * 属性 [PLANNEDSTORYCNT]
     *
     */
    @JSONField(name = "plannedstorycnt")
    @JsonProperty("plannedstorycnt")
    @ApiModelProperty("已计划需求数")
    private Integer plannedstorycnt;

    /**
     * 属性 [UNCONFIRMBUGCNT]
     *
     */
    @JSONField(name = "unconfirmbugcnt")
    @JsonProperty("unconfirmbugcnt")
    @ApiModelProperty("未确认Bug数")
    private Integer unconfirmbugcnt;

    /**
     * 属性 [ACTIVEBUGCNT]
     *
     */
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    @ApiModelProperty("未解决Bug数")
    private Integer activebugcnt;


    /**
     * 设置 [CODE]
     */
    public void setCode(String  code){
        this.code = code ;
        this.modify("code",code);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }


}


