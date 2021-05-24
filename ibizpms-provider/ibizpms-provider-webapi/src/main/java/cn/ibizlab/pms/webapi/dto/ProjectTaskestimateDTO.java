package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
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
 * 服务DTO对象[ProjectTaskestimateDTO]
 */
@Data
@ApiModel("项目工时统计")
//@JsonFilter(value = "dtofieldfilter")
public class ProjectTaskestimateDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [TWENTYFIVEEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentyfiveevaluationtime")
    @JsonProperty("twentyfiveevaluationtime")
    @ApiModelProperty("二十五号评估工时")
    private Double twentyfiveevaluationtime;

    /**
     * 属性 [SEVENTEENCONSUMED]
     *
     */
    @JSONField(name = "seventeenconsumed")
    @JsonProperty("seventeenconsumed")
    @ApiModelProperty("十七号工时")
    private Double seventeenconsumed;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目标识")
    private Long project;

    /**
     * 属性 [FIFTEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "fifteenevaluationtime")
    @JsonProperty("fifteenevaluationtime")
    @ApiModelProperty("十五号评估工时")
    private Double fifteenevaluationtime;

    /**
     * 属性 [OTHERCONSUMED]
     *
     */
    @JSONField(name = "otherconsumed")
    @JsonProperty("otherconsumed")
    @ApiModelProperty("其他项目工时")
    private Double otherconsumed;

    /**
     * 属性 [THIRTEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "thirteenevaluationcost")
    @JsonProperty("thirteenevaluationcost")
    @ApiModelProperty("十三号评估成本")
    private Double thirteenevaluationcost;

    /**
     * 属性 [THIRTYEVALUATIONCOST]
     *
     */
    @JSONField(name = "thirtyevaluationcost")
    @JsonProperty("thirtyevaluationcost")
    @ApiModelProperty("三十号评估成本")
    private Double thirtyevaluationcost;

    /**
     * 属性 [EVALUATIONCOST]
     *
     */
    @JSONField(name = "evaluationcost")
    @JsonProperty("evaluationcost")
    @ApiModelProperty("评估成本")
    private Double evaluationcost;

    /**
     * 属性 [TWENTYSIXEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentysixevaluationcost")
    @JsonProperty("twentysixevaluationcost")
    @ApiModelProperty("二十六号评估成本")
    private Double twentysixevaluationcost;

    /**
     * 属性 [TWOEVALUATIONTIME]
     *
     */
    @JSONField(name = "twoevaluationtime")
    @JsonProperty("twoevaluationtime")
    @ApiModelProperty("二号评估工时")
    private Double twoevaluationtime;

    /**
     * 属性 [THIRTYEVALUATIONTIME]
     *
     */
    @JSONField(name = "thirtyevaluationtime")
    @JsonProperty("thirtyevaluationtime")
    @ApiModelProperty("三十号评估工时")
    private Double thirtyevaluationtime;

    /**
     * 属性 [TWENTYSIXCONSUMED]
     *
     */
    @JSONField(name = "twentysixconsumed")
    @JsonProperty("twentysixconsumed")
    @ApiModelProperty("二十六号工时")
    private Double twentysixconsumed;

    /**
     * 属性 [NINETEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "nineteenevaluationtime")
    @JsonProperty("nineteenevaluationtime")
    @ApiModelProperty("十九号评估工时")
    private Double nineteenevaluationtime;

    /**
     * 属性 [TWENTYONECONSUMED]
     *
     */
    @JSONField(name = "twentyoneconsumed")
    @JsonProperty("twentyoneconsumed")
    @ApiModelProperty("二十一号工时")
    private Double twentyoneconsumed;

    /**
     * 属性 [EVALUATIONTIME]
     *
     */
    @JSONField(name = "evaluationtime")
    @JsonProperty("evaluationtime")
    @ApiModelProperty("评估工时")
    private Double evaluationtime;

    /**
     * 属性 [TWENTYFOUREVALUATIONCOST]
     *
     */
    @JSONField(name = "twentyfourevaluationcost")
    @JsonProperty("twentyfourevaluationcost")
    @ApiModelProperty("二十四号评估成本")
    private Double twentyfourevaluationcost;

    /**
     * 属性 [THREEEVALUATIONCOST]
     *
     */
    @JSONField(name = "threeevaluationcost")
    @JsonProperty("threeevaluationcost")
    @ApiModelProperty("三号评估成本")
    private Double threeevaluationcost;

    /**
     * 属性 [THIRTYONEEVALUATIONCOST]
     *
     */
    @JSONField(name = "thirtyoneevaluationcost")
    @JsonProperty("thirtyoneevaluationcost")
    @ApiModelProperty("三十一号评估成本")
    private Double thirtyoneevaluationcost;

    /**
     * 属性 [SIXEVALUATIONCOST]
     *
     */
    @JSONField(name = "sixevaluationcost")
    @JsonProperty("sixevaluationcost")
    @ApiModelProperty("六号评估成本")
    private Double sixevaluationcost;

    /**
     * 属性 [EIGHTEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "eighteenevaluationtime")
    @JsonProperty("eighteenevaluationtime")
    @ApiModelProperty("十八号评估工时")
    private Double eighteenevaluationtime;

    /**
     * 属性 [THIRTEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "thirteenevaluationtime")
    @JsonProperty("thirteenevaluationtime")
    @ApiModelProperty("十三号评估工时")
    private Double thirteenevaluationtime;

    /**
     * 属性 [TWENTYNINEEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentynineevaluationcost")
    @JsonProperty("twentynineevaluationcost")
    @ApiModelProperty("二十九号评估成本")
    private Double twentynineevaluationcost;

    /**
     * 属性 [TWENTYEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentyevaluationtime")
    @JsonProperty("twentyevaluationtime")
    @ApiModelProperty("二十号评估工时")
    private Double twentyevaluationtime;

    /**
     * 属性 [TWENTYTWOEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentytwoevaluationtime")
    @JsonProperty("twentytwoevaluationtime")
    @ApiModelProperty("二十二号评估工时")
    private Double twentytwoevaluationtime;

    /**
     * 属性 [ONEEVALUATIONTIME]
     *
     */
    @JSONField(name = "oneevaluationtime")
    @JsonProperty("oneevaluationtime")
    @ApiModelProperty("一号评估工时")
    private Double oneevaluationtime;

    /**
     * 属性 [ELEVENCONSUMED]
     *
     */
    @JSONField(name = "elevenconsumed")
    @JsonProperty("elevenconsumed")
    @ApiModelProperty("十一号工时")
    private Double elevenconsumed;

    /**
     * 属性 [FOURTEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "fourteenevaluationtime")
    @JsonProperty("fourteenevaluationtime")
    @ApiModelProperty("十四号评估工时")
    private Double fourteenevaluationtime;

    /**
     * 属性 [TWENTYTWOCONSUMED]
     *
     */
    @JSONField(name = "twentytwoconsumed")
    @JsonProperty("twentytwoconsumed")
    @ApiModelProperty("二十二号工时")
    private Double twentytwoconsumed;

    /**
     * 属性 [TWENTYONEEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentyoneevaluationcost")
    @JsonProperty("twentyoneevaluationcost")
    @ApiModelProperty("二十一号评估成本")
    private Double twentyoneevaluationcost;

    /**
     * 属性 [EIGHTCONSUMED]
     *
     */
    @JSONField(name = "eightconsumed")
    @JsonProperty("eightconsumed")
    @ApiModelProperty("八号工时")
    private Double eightconsumed;

    /**
     * 属性 [TWENTYEIGHTEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentyeightevaluationtime")
    @JsonProperty("twentyeightevaluationtime")
    @ApiModelProperty("二十八号评估工时")
    private Double twentyeightevaluationtime;

    /**
     * 属性 [THREECONSUMED]
     *
     */
    @JSONField(name = "threeconsumed")
    @JsonProperty("threeconsumed")
    @ApiModelProperty("三号工时")
    private Double threeconsumed;

    /**
     * 属性 [TWENTYTHREEEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentythreeevaluationtime")
    @JsonProperty("twentythreeevaluationtime")
    @ApiModelProperty("二十三号评估工时")
    private Double twentythreeevaluationtime;

    /**
     * 属性 [TENEVALUATIONTIME]
     *
     */
    @JSONField(name = "tenevaluationtime")
    @JsonProperty("tenevaluationtime")
    @ApiModelProperty("十号评估工时")
    private Double tenevaluationtime;

    /**
     * 属性 [SIXCONSUMED]
     *
     */
    @JSONField(name = "sixconsumed")
    @JsonProperty("sixconsumed")
    @ApiModelProperty("六号工时")
    private Double sixconsumed;

    /**
     * 属性 [TWENTYFIVECONSUMED]
     *
     */
    @JSONField(name = "twentyfiveconsumed")
    @JsonProperty("twentyfiveconsumed")
    @ApiModelProperty("二十五号工时")
    private Double twentyfiveconsumed;

    /**
     * 属性 [TWENTYEIGHTCONSUMED]
     *
     */
    @JSONField(name = "twentyeightconsumed")
    @JsonProperty("twentyeightconsumed")
    @ApiModelProperty("二十八号工时")
    private Double twentyeightconsumed;

    /**
     * 属性 [THIRTEENCONSUMED]
     *
     */
    @JSONField(name = "thirteenconsumed")
    @JsonProperty("thirteenconsumed")
    @ApiModelProperty("十三号工时")
    private Double thirteenconsumed;

    /**
     * 属性 [FOUREVALUATIONCOST]
     *
     */
    @JSONField(name = "fourevaluationcost")
    @JsonProperty("fourevaluationcost")
    @ApiModelProperty("四号评估成本")
    private Double fourevaluationcost;

    /**
     * 属性 [TWENTYTWOEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentytwoevaluationcost")
    @JsonProperty("twentytwoevaluationcost")
    @ApiModelProperty("二十二号评估成本")
    private Double twentytwoevaluationcost;

    /**
     * 属性 [MONTH]
     *
     */
    @JSONField(name = "month")
    @JsonProperty("month")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("月")
    private String month;

    /**
     * 属性 [TWELVEEVALUATIONTIME]
     *
     */
    @JSONField(name = "twelveevaluationtime")
    @JsonProperty("twelveevaluationtime")
    @ApiModelProperty("十二号评估工时")
    private Double twelveevaluationtime;

    /**
     * 属性 [TENEVALUATIONCOST]
     *
     */
    @JSONField(name = "tenevaluationcost")
    @JsonProperty("tenevaluationcost")
    @ApiModelProperty("十号评估成本")
    private Double tenevaluationcost;

    /**
     * 属性 [SEVENEVALUATIONCOST]
     *
     */
    @JSONField(name = "sevenevaluationcost")
    @JsonProperty("sevenevaluationcost")
    @ApiModelProperty("七号评估成本")
    private Double sevenevaluationcost;

    /**
     * 属性 [FIFTEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "fifteenevaluationcost")
    @JsonProperty("fifteenevaluationcost")
    @ApiModelProperty("十五号评估成本")
    private Double fifteenevaluationcost;

    /**
     * 属性 [NINEEVALUATIONCOST]
     *
     */
    @JSONField(name = "nineevaluationcost")
    @JsonProperty("nineevaluationcost")
    @ApiModelProperty("九号评估成本")
    private Double nineevaluationcost;

    /**
     * 属性 [THIRTYCONSUMED]
     *
     */
    @JSONField(name = "thirtyconsumed")
    @JsonProperty("thirtyconsumed")
    @ApiModelProperty("三十号工时")
    private Double thirtyconsumed;

    /**
     * 属性 [ELEVENEVALUATIONCOST]
     *
     */
    @JSONField(name = "elevenevaluationcost")
    @JsonProperty("elevenevaluationcost")
    @ApiModelProperty("十一号评估成本")
    private Double elevenevaluationcost;

    /**
     * 属性 [THREEEVALUATIONTIME]
     *
     */
    @JSONField(name = "threeevaluationtime")
    @JsonProperty("threeevaluationtime")
    @ApiModelProperty("三号评估工时")
    private Double threeevaluationtime;

    /**
     * 属性 [TWENTYNINEEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentynineevaluationtime")
    @JsonProperty("twentynineevaluationtime")
    @ApiModelProperty("二十九号评估工时")
    private Double twentynineevaluationtime;

    /**
     * 属性 [NINECONSUMED]
     *
     */
    @JSONField(name = "nineconsumed")
    @JsonProperty("nineconsumed")
    @ApiModelProperty("九号工时")
    private Double nineconsumed;

    /**
     * 属性 [ONEEVALUATIONCOST]
     *
     */
    @JSONField(name = "oneevaluationcost")
    @JsonProperty("oneevaluationcost")
    @ApiModelProperty("一号评估成本")
    private Double oneevaluationcost;

    /**
     * 属性 [TWENTYCONSUMED]
     *
     */
    @JSONField(name = "twentyconsumed")
    @JsonProperty("twentyconsumed")
    @ApiModelProperty("二十号工时")
    private Double twentyconsumed;

    /**
     * 属性 [FIVEEVALUATIONCOST]
     *
     */
    @JSONField(name = "fiveevaluationcost")
    @JsonProperty("fiveevaluationcost")
    @ApiModelProperty("五号评估成本")
    private Double fiveevaluationcost;

    /**
     * 属性 [TWENTYONEEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentyoneevaluationtime")
    @JsonProperty("twentyoneevaluationtime")
    @ApiModelProperty("二十一号评估工时")
    private Double twentyoneevaluationtime;

    /**
     * 属性 [FOURONSUMED]
     *
     */
    @JSONField(name = "fourconsumed")
    @JsonProperty("fourconsumed")
    @ApiModelProperty("四号工时")
    private Double fourconsumed;

    /**
     * 属性 [OTHEREVALUATIONTIME]
     *
     */
    @JSONField(name = "otherevaluationtime")
    @JsonProperty("otherevaluationtime")
    @ApiModelProperty("其他项目评估工时")
    private Double otherevaluationtime;

    /**
     * 属性 [TWENTYSEVENCONSUMED]
     *
     */
    @JSONField(name = "twentysevenconsumed")
    @JsonProperty("twentysevenconsumed")
    @ApiModelProperty("二十七号工时")
    private Double twentysevenconsumed;

    /**
     * 属性 [TWOCONSUMED]
     *
     */
    @JSONField(name = "twoconsumed")
    @JsonProperty("twoconsumed")
    @ApiModelProperty("二号工时")
    private Double twoconsumed;

    /**
     * 属性 [TWENTYFOUREVALUATIONTIME]
     *
     */
    @JSONField(name = "twentyfourevaluationtime")
    @JsonProperty("twentyfourevaluationtime")
    @ApiModelProperty("二十四号评估工时")
    private Double twentyfourevaluationtime;

    /**
     * 属性 [TWENTYSIXEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentysixevaluationtime")
    @JsonProperty("twentysixevaluationtime")
    @ApiModelProperty("二十六号评估工时")
    private Double twentysixevaluationtime;

    /**
     * 属性 [NINEEVALUATIONTIME]
     *
     */
    @JSONField(name = "nineevaluationtime")
    @JsonProperty("nineevaluationtime")
    @ApiModelProperty("九号评估工时")
    private Double nineevaluationtime;

    /**
     * 属性 [EIGHTEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "eighteenevaluationcost")
    @JsonProperty("eighteenevaluationcost")
    @ApiModelProperty("十八号评估成本")
    private Double eighteenevaluationcost;

    /**
     * 属性 [TWENTYTHREEEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentythreeevaluationcost")
    @JsonProperty("twentythreeevaluationcost")
    @ApiModelProperty("二十三号评估成本")
    private Double twentythreeevaluationcost;

    /**
     * 属性 [FOUREVALUATIONTIME]
     *
     */
    @JSONField(name = "fourevaluationtime")
    @JsonProperty("fourevaluationtime")
    @ApiModelProperty("四号评估工时")
    private Double fourevaluationtime;

    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目名称")
    private String projectname;

    /**
     * 属性 [EIGHTEVALUATIONCOST]
     *
     */
    @JSONField(name = "eightevaluationcost")
    @JsonProperty("eightevaluationcost")
    @ApiModelProperty("八号评估成本")
    private Double eightevaluationcost;

    /**
     * 属性 [EIGHTEVALUATIONTIME]
     *
     */
    @JSONField(name = "eightevaluationtime")
    @JsonProperty("eightevaluationtime")
    @ApiModelProperty("八号评估工时")
    private Double eightevaluationtime;

    /**
     * 属性 [TWENTYNINECONSUMED]
     *
     */
    @JSONField(name = "twentynineconsumed")
    @JsonProperty("twentynineconsumed")
    @ApiModelProperty("二十九号工时")
    private Double twentynineconsumed;

    /**
     * 属性 [CONSUMED]
     *
     */
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("工时")
    private Double consumed;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;

    /**
     * 属性 [SIXTEENCONSUMED]
     *
     */
    @JSONField(name = "sixteenconsumed")
    @JsonProperty("sixteenconsumed")
    @ApiModelProperty("十六号工时")
    private Double sixteenconsumed;

    /**
     * 属性 [THIRTYONEEVALUATIONTIME]
     *
     */
    @JSONField(name = "thirtyoneevaluationtime")
    @JsonProperty("thirtyoneevaluationtime")
    @ApiModelProperty("三十一号评估工时")
    private Double thirtyoneevaluationtime;

    /**
     * 属性 [SIXTEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "sixteenevaluationcost")
    @JsonProperty("sixteenevaluationcost")
    @ApiModelProperty("十六号评估成本")
    private Double sixteenevaluationcost;

    /**
     * 属性 [SIXTEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "sixteenevaluationtime")
    @JsonProperty("sixteenevaluationtime")
    @ApiModelProperty("十六号评估工时")
    private Double sixteenevaluationtime;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("用户")
    private String account;

    /**
     * 属性 [SEVENCONSUMED]
     *
     */
    @JSONField(name = "sevenconsumed")
    @JsonProperty("sevenconsumed")
    @ApiModelProperty("七号工时")
    private Double sevenconsumed;

    /**
     * 属性 [NINETEENCONSUMED]
     *
     */
    @JSONField(name = "nineteenconsumed")
    @JsonProperty("nineteenconsumed")
    @ApiModelProperty("十九号工时")
    private Double nineteenconsumed;

    /**
     * 属性 [YEAR]
     *
     */
    @JSONField(name = "year")
    @JsonProperty("year")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("年")
    private String year;

    /**
     * 属性 [SIXEVALUATIONTIME]
     *
     */
    @JSONField(name = "sixevaluationtime")
    @JsonProperty("sixevaluationtime")
    @ApiModelProperty("六号评估工时")
    private Double sixevaluationtime;

    /**
     * 属性 [TWENTYFIVEEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentyfiveevaluationcost")
    @JsonProperty("twentyfiveevaluationcost")
    @ApiModelProperty("二十五号评估成本")
    private Double twentyfiveevaluationcost;

    /**
     * 属性 [SEVENTEENEVALUATIONTIME]
     *
     */
    @JSONField(name = "seventeenevaluationtime")
    @JsonProperty("seventeenevaluationtime")
    @ApiModelProperty("十七号评估工时")
    private Double seventeenevaluationtime;

    /**
     * 属性 [TWENTYSEVENEVALUATIONTIME]
     *
     */
    @JSONField(name = "twentysevenevaluationtime")
    @JsonProperty("twentysevenevaluationtime")
    @ApiModelProperty("二十七号评估工时")
    private Double twentysevenevaluationtime;

    /**
     * 属性 [FOURTEENCONSUMED]
     *
     */
    @JSONField(name = "fourteenconsumed")
    @JsonProperty("fourteenconsumed")
    @ApiModelProperty("十四号工时")
    private Double fourteenconsumed;

    /**
     * 属性 [EIGHTEENCONSUMED]
     *
     */
    @JSONField(name = "eighteenconsumed")
    @JsonProperty("eighteenconsumed")
    @ApiModelProperty("十八号工时")
    private Double eighteenconsumed;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("主键")
    private String id;

    /**
     * 属性 [TWENTYFOURCONSUMED]
     *
     */
    @JSONField(name = "twentyfourconsumed")
    @JsonProperty("twentyfourconsumed")
    @ApiModelProperty("二十四号工时")
    private Double twentyfourconsumed;

    /**
     * 属性 [THIRTYONECONSUMED]
     *
     */
    @JSONField(name = "thirtyoneconsumed")
    @JsonProperty("thirtyoneconsumed")
    @ApiModelProperty("三十一号工时")
    private Double thirtyoneconsumed;

    /**
     * 属性 [TWELVEEVALUATIONCOST]
     *
     */
    @JSONField(name = "twelveevaluationcost")
    @JsonProperty("twelveevaluationcost")
    @ApiModelProperty("十二号评估成本")
    private Double twelveevaluationcost;

    /**
     * 属性 [SEVENTEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "seventeenevaluationcost")
    @JsonProperty("seventeenevaluationcost")
    @ApiModelProperty("十七号评估成本")
    private Double seventeenevaluationcost;

    /**
     * 属性 [TWENTYEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentyevaluationcost")
    @JsonProperty("twentyevaluationcost")
    @ApiModelProperty("二十号评估成本")
    private Double twentyevaluationcost;

    /**
     * 属性 [TENCONSUMED]
     *
     */
    @JSONField(name = "tenconsumed")
    @JsonProperty("tenconsumed")
    @ApiModelProperty("十号工时")
    private Double tenconsumed;

    /**
     * 属性 [ELEVENEVALUATIONTIME]
     *
     */
    @JSONField(name = "elevenevaluationtime")
    @JsonProperty("elevenevaluationtime")
    @ApiModelProperty("十一号评估工时")
    private Double elevenevaluationtime;

    /**
     * 属性 [FIVEEVALUATIONTIME]
     *
     */
    @JSONField(name = "fiveevaluationtime")
    @JsonProperty("fiveevaluationtime")
    @ApiModelProperty("五号评估工时")
    private Double fiveevaluationtime;

    /**
     * 属性 [FIFTEENCONSUMED]
     *
     */
    @JSONField(name = "fifteenconsumed")
    @JsonProperty("fifteenconsumed")
    @ApiModelProperty("十五号工时")
    private Double fifteenconsumed;

    /**
     * 属性 [TWENTYEIGHTEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentyeightevaluationcost")
    @JsonProperty("twentyeightevaluationcost")
    @ApiModelProperty("二十八号评估成本")
    private Double twentyeightevaluationcost;

    /**
     * 属性 [TWELVECONSUMED]
     *
     */
    @JSONField(name = "twelveconsumed")
    @JsonProperty("twelveconsumed")
    @ApiModelProperty("十二号工时")
    private Double twelveconsumed;

    /**
     * 属性 [FOURTEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "fourteenevaluationcost")
    @JsonProperty("fourteenevaluationcost")
    @ApiModelProperty("十四号评估成本")
    private Double fourteenevaluationcost;

    /**
     * 属性 [FIVECONSUMED]
     *
     */
    @JSONField(name = "fiveconsumed")
    @JsonProperty("fiveconsumed")
    @ApiModelProperty("五号工时")
    private Double fiveconsumed;

    /**
     * 属性 [TWENTYTHREECONSUMED]
     *
     */
    @JSONField(name = "twentythreeconsumed")
    @JsonProperty("twentythreeconsumed")
    @ApiModelProperty("二十三号工时")
    private Double twentythreeconsumed;

    /**
     * 属性 [SEVENEVALUATIONTIME]
     *
     */
    @JSONField(name = "sevenevaluationtime")
    @JsonProperty("sevenevaluationtime")
    @ApiModelProperty("七号评估工时")
    private Double sevenevaluationtime;

    /**
     * 属性 [TWENTYSEVENEVALUATIONCOST]
     *
     */
    @JSONField(name = "twentysevenevaluationcost")
    @JsonProperty("twentysevenevaluationcost")
    @ApiModelProperty("二十七号评估成本")
    private Double twentysevenevaluationcost;

    /**
     * 属性 [ONECONSUMED]
     *
     */
    @JSONField(name = "oneconsumed")
    @JsonProperty("oneconsumed")
    @ApiModelProperty("一号工时")
    private Double oneconsumed;

    /**
     * 属性 [TWOEVALUATIONCOST]
     *
     */
    @JSONField(name = "twoevaluationcost")
    @JsonProperty("twoevaluationcost")
    @ApiModelProperty("二号评估成本")
    private Double twoevaluationcost;

    /**
     * 属性 [NINETEENEVALUATIONCOST]
     *
     */
    @JSONField(name = "nineteenevaluationcost")
    @JsonProperty("nineteenevaluationcost")
    @ApiModelProperty("十九号评估成本")
    private Double nineteenevaluationcost;

    /**
     * 属性 [INPUTCOST]
     *
     */
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    @ApiModelProperty("投入成本")
    private Double inputcost;


    /**
     * 设置 [EVALUATIONCOST]
     */
    public void setEvaluationcost(Double  evaluationcost){
        this.evaluationcost = evaluationcost ;
        this.modify("evaluationcost",evaluationcost);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [INPUTCOST]
     */
    public void setInputcost(Double  inputcost){
        this.inputcost = inputcost ;
        this.modify("inputcost",inputcost);
    }


}


