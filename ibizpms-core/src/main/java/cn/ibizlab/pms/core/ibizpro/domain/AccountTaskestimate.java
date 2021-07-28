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
 * 实体[用户工时统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_taskestimate", resultMap = "AccountTaskestimateResultMap")
@ApiModel("用户工时统计")
public class AccountTaskestimate extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("项目名称")
    private String projectname;
    /**
     * 七号工时
     */
    @TableField(exist = false)
    @JSONField(name = "sevenconsumed")
    @JsonProperty("sevenconsumed")
    @ApiModelProperty("七号工时")
    private Double sevenconsumed;
    /**
     * 七号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "sevenevaluationtime")
    @JsonProperty("sevenevaluationtime")
    @ApiModelProperty("七号评估工时")
    private Double sevenevaluationtime;
    /**
     * 十四号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourteenevaluationtime")
    @JsonProperty("fourteenevaluationtime")
    @ApiModelProperty("十四号评估工时")
    private Double fourteenevaluationtime;
    /**
     * 十号工时
     */
    @TableField(exist = false)
    @JSONField(name = "tenconsumed")
    @JsonProperty("tenconsumed")
    @ApiModelProperty("十号工时")
    private Double tenconsumed;
    /**
     * 九号工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineconsumed")
    @JsonProperty("nineconsumed")
    @ApiModelProperty("九号工时")
    private Double nineconsumed;
    /**
     * 二十号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyconsumed")
    @JsonProperty("twentyconsumed")
    @ApiModelProperty("二十号工时")
    private Double twentyconsumed;
    /**
     * 六号工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixconsumed")
    @JsonProperty("sixconsumed")
    @ApiModelProperty("六号工时")
    private Double sixconsumed;
    /**
     * 三十号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyevaluationtime")
    @JsonProperty("thirtyevaluationtime")
    @ApiModelProperty("三十号评估工时")
    private Double thirtyevaluationtime;
    /**
     * 十三号工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirteenconsumed")
    @JsonProperty("thirteenconsumed")
    @ApiModelProperty("十三号工时")
    private Double thirteenconsumed;
    /**
     * 八号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "eightevaluationcost")
    @JsonProperty("eightevaluationcost")
    @ApiModelProperty("八号评估成本")
    private Double eightevaluationcost;
    /**
     * 十九号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineteenevaluationtime")
    @JsonProperty("nineteenevaluationtime")
    @ApiModelProperty("十九号评估工时")
    private Double nineteenevaluationtime;
    /**
     * 二十九号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentynineevaluationtime")
    @JsonProperty("twentynineevaluationtime")
    @ApiModelProperty("二十九号评估工时")
    private Double twentynineevaluationtime;
    /**
     * 十七号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "seventeenevaluationtime")
    @JsonProperty("seventeenevaluationtime")
    @ApiModelProperty("十七号评估工时")
    private Double seventeenevaluationtime;
    /**
     * 十二号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twelveevaluationcost")
    @JsonProperty("twelveevaluationcost")
    @ApiModelProperty("十二号评估成本")
    private Double twelveevaluationcost;
    /**
     * 二十九号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentynineevaluationcost")
    @JsonProperty("twentynineevaluationcost")
    @ApiModelProperty("二十九号评估成本")
    private Double twentynineevaluationcost;
    /**
     * 三号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "threeevaluationcost")
    @JsonProperty("threeevaluationcost")
    @ApiModelProperty("三号评估成本")
    private Double threeevaluationcost;
    /**
     * 二十六号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysixevaluationtime")
    @JsonProperty("twentysixevaluationtime")
    @ApiModelProperty("二十六号评估工时")
    private Double twentysixevaluationtime;
    /**
     * 十四号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fourteenevaluationcost")
    @JsonProperty("fourteenevaluationcost")
    @ApiModelProperty("十四号评估成本")
    private Double fourteenevaluationcost;
    /**
     * 十八号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "eighteenevaluationcost")
    @JsonProperty("eighteenevaluationcost")
    @ApiModelProperty("十八号评估成本")
    private Double eighteenevaluationcost;
    /**
     * 一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "oneevaluationtime")
    @JsonProperty("oneevaluationtime")
    @ApiModelProperty("一号评估工时")
    private Double oneevaluationtime;
    /**
     * 月
     */
    @TableField(exist = false)
    @JSONField(name = "month")
    @JsonProperty("month")
    @ApiModelProperty("月")
    private String month;
    /**
     * 日期
     */
    @TableField(value = "date")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
    /**
     * 二十九号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentynineconsumed")
    @JsonProperty("twentynineconsumed")
    @ApiModelProperty("二十九号工时")
    private Double twentynineconsumed;
    /**
     * 评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "evaluationtime")
    @JsonProperty("evaluationtime")
    @ApiModelProperty("评估工时")
    private Double evaluationtime;
    /**
     * 评估成本
     */
    @TableField(value = "evaluationcost")
    @JSONField(name = "evaluationcost")
    @JsonProperty("evaluationcost")
    @ApiModelProperty("评估成本")
    private Double evaluationcost;
    /**
     * 十一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "elevenevaluationtime")
    @JsonProperty("elevenevaluationtime")
    @ApiModelProperty("十一号评估工时")
    private Double elevenevaluationtime;
    /**
     * 十号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "tenevaluationtime")
    @JsonProperty("tenevaluationtime")
    @ApiModelProperty("十号评估工时")
    private Double tenevaluationtime;
    /**
     * 三十一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyoneevaluationcost")
    @JsonProperty("thirtyoneevaluationcost")
    @ApiModelProperty("三十一号评估成本")
    private Double thirtyoneevaluationcost;
    /**
     * 四号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourevaluationtime")
    @JsonProperty("fourevaluationtime")
    @ApiModelProperty("四号评估工时")
    private Double fourevaluationtime;
    /**
     * 二十二号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentytwoconsumed")
    @JsonProperty("twentytwoconsumed")
    @ApiModelProperty("二十二号工时")
    private Double twentytwoconsumed;
    /**
     * 二十一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyoneconsumed")
    @JsonProperty("twentyoneconsumed")
    @ApiModelProperty("二十一号工时")
    private Double twentyoneconsumed;
    /**
     * 十三号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirteenevaluationtime")
    @JsonProperty("thirteenevaluationtime")
    @ApiModelProperty("十三号评估工时")
    private Double thirteenevaluationtime;
    /**
     * 十七号工时
     */
    @TableField(exist = false)
    @JSONField(name = "seventeenconsumed")
    @JsonProperty("seventeenconsumed")
    @ApiModelProperty("十七号工时")
    private Double seventeenconsumed;
    /**
     * 九号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineevaluationtime")
    @JsonProperty("nineevaluationtime")
    @ApiModelProperty("九号评估工时")
    private Double nineevaluationtime;
    /**
     * 二号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twoconsumed")
    @JsonProperty("twoconsumed")
    @ApiModelProperty("二号工时")
    private Double twoconsumed;
    /**
     * 二十七号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysevenconsumed")
    @JsonProperty("twentysevenconsumed")
    @ApiModelProperty("二十七号工时")
    private Double twentysevenconsumed;
    /**
     * 投入成本
     */
    @TableField(value = "inputcost")
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    @ApiModelProperty("投入成本")
    private Double inputcost;
    /**
     * 十四号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourteenconsumed")
    @JsonProperty("fourteenconsumed")
    @ApiModelProperty("十四号工时")
    private Double fourteenconsumed;
    /**
     * 二十八号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyeightconsumed")
    @JsonProperty("twentyeightconsumed")
    @ApiModelProperty("二十八号工时")
    private Double twentyeightconsumed;
    /**
     * 工时
     */
    @TableField(exist = false)
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("工时")
    private Double consumed;
    /**
     * 十三号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "thirteenevaluationcost")
    @JsonProperty("thirteenevaluationcost")
    @ApiModelProperty("十三号评估成本")
    private Double thirteenevaluationcost;
    /**
     * 十一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "elevenconsumed")
    @JsonProperty("elevenconsumed")
    @ApiModelProperty("十一号工时")
    private Double elevenconsumed;
    /**
     * 二十一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyoneevaluationtime")
    @JsonProperty("twentyoneevaluationtime")
    @ApiModelProperty("二十一号评估工时")
    private Double twentyoneevaluationtime;
    /**
     * 十二号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twelveconsumed")
    @JsonProperty("twelveconsumed")
    @ApiModelProperty("十二号工时")
    private Double twelveconsumed;
    /**
     * 三十一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyoneconsumed")
    @JsonProperty("thirtyoneconsumed")
    @ApiModelProperty("三十一号工时")
    private Double thirtyoneconsumed;
    /**
     * 五号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fiveconsumed")
    @JsonProperty("fiveconsumed")
    @ApiModelProperty("五号工时")
    private Double fiveconsumed;
    /**
     * 二十四号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfourconsumed")
    @JsonProperty("twentyfourconsumed")
    @ApiModelProperty("二十四号工时")
    private Double twentyfourconsumed;
    /**
     * 二十八号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyeightevaluationcost")
    @JsonProperty("twentyeightevaluationcost")
    @ApiModelProperty("二十八号评估成本")
    private Double twentyeightevaluationcost;
    /**
     * 二十三号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentythreeevaluationtime")
    @JsonProperty("twentythreeevaluationtime")
    @ApiModelProperty("二十三号评估工时")
    private Double twentythreeevaluationtime;
    /**
     * 十九号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "nineteenevaluationcost")
    @JsonProperty("nineteenevaluationcost")
    @ApiModelProperty("十九号评估成本")
    private Double nineteenevaluationcost;
    /**
     * 三十号工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyconsumed")
    @JsonProperty("thirtyconsumed")
    @ApiModelProperty("三十号工时")
    private Double thirtyconsumed;
    /**
     * 二十一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyoneevaluationcost")
    @JsonProperty("twentyoneevaluationcost")
    @ApiModelProperty("二十一号评估成本")
    private Double twentyoneevaluationcost;
    /**
     * 二十号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyevaluationtime")
    @JsonProperty("twentyevaluationtime")
    @ApiModelProperty("二十号评估工时")
    private Double twentyevaluationtime;
    /**
     * 六号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "sixevaluationcost")
    @JsonProperty("sixevaluationcost")
    @ApiModelProperty("六号评估成本")
    private Double sixevaluationcost;
    /**
     * 十九号工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineteenconsumed")
    @JsonProperty("nineteenconsumed")
    @ApiModelProperty("十九号工时")
    private Double nineteenconsumed;
    /**
     * 八号工时
     */
    @TableField(exist = false)
    @JSONField(name = "eightconsumed")
    @JsonProperty("eightconsumed")
    @ApiModelProperty("八号工时")
    private Double eightconsumed;
    /**
     * 十七号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "seventeenevaluationcost")
    @JsonProperty("seventeenevaluationcost")
    @ApiModelProperty("十七号评估成本")
    private Double seventeenevaluationcost;
    /**
     * 十二号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twelveevaluationtime")
    @JsonProperty("twelveevaluationtime")
    @ApiModelProperty("十二号评估工时")
    private Double twelveevaluationtime;
    /**
     * 二十二号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentytwoevaluationcost")
    @JsonProperty("twentytwoevaluationcost")
    @ApiModelProperty("二十二号评估成本")
    private Double twentytwoevaluationcost;
    /**
     * 二十八号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyeightevaluationtime")
    @JsonProperty("twentyeightevaluationtime")
    @ApiModelProperty("二十八号评估工时")
    private Double twentyeightevaluationtime;
    /**
     * 十五号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fifteenconsumed")
    @JsonProperty("fifteenconsumed")
    @ApiModelProperty("十五号工时")
    private Double fifteenconsumed;
    /**
     * 三号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "threeevaluationtime")
    @JsonProperty("threeevaluationtime")
    @ApiModelProperty("三号评估工时")
    private Double threeevaluationtime;
    /**
     * 十八号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "eighteenevaluationtime")
    @JsonProperty("eighteenevaluationtime")
    @ApiModelProperty("十八号评估工时")
    private Double eighteenevaluationtime;
    /**
     * 三十号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyevaluationcost")
    @JsonProperty("thirtyevaluationcost")
    @ApiModelProperty("三十号评估成本")
    private Double thirtyevaluationcost;
    /**
     * 六号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixevaluationtime")
    @JsonProperty("sixevaluationtime")
    @ApiModelProperty("六号评估工时")
    private Double sixevaluationtime;
    /**
     * 主键
     */
    @DEField(isKeyField = true)
    @TableField(exist = false)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("主键")
    private String id;
    /**
     * 二十三号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentythreeevaluationcost")
    @JsonProperty("twentythreeevaluationcost")
    @ApiModelProperty("二十三号评估成本")
    private Double twentythreeevaluationcost;
    /**
     * 二十六号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentysixevaluationcost")
    @JsonProperty("twentysixevaluationcost")
    @ApiModelProperty("二十六号评估成本")
    private Double twentysixevaluationcost;
    /**
     * 二十七号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysevenevaluationtime")
    @JsonProperty("twentysevenevaluationtime")
    @ApiModelProperty("二十七号评估工时")
    private Double twentysevenevaluationtime;
    /**
     * 一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "oneconsumed")
    @JsonProperty("oneconsumed")
    @ApiModelProperty("一号工时")
    private Double oneconsumed;
    /**
     * 二十二号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentytwoevaluationtime")
    @JsonProperty("twentytwoevaluationtime")
    @ApiModelProperty("二十二号评估工时")
    private Double twentytwoevaluationtime;
    /**
     * 八号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "eightevaluationtime")
    @JsonProperty("eightevaluationtime")
    @ApiModelProperty("八号评估工时")
    private Double eightevaluationtime;
    /**
     * 二十五号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfiveevaluationcost")
    @JsonProperty("twentyfiveevaluationcost")
    @ApiModelProperty("二十五号评估成本")
    private Double twentyfiveevaluationcost;
    /**
     * 七号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "sevenevaluationcost")
    @JsonProperty("sevenevaluationcost")
    @ApiModelProperty("七号评估成本")
    private Double sevenevaluationcost;
    /**
     * 十六号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixteenevaluationtime")
    @JsonProperty("sixteenevaluationtime")
    @ApiModelProperty("十六号评估工时")
    private Double sixteenevaluationtime;
    /**
     * 十八号工时
     */
    @TableField(exist = false)
    @JSONField(name = "eighteenconsumed")
    @JsonProperty("eighteenconsumed")
    @ApiModelProperty("十八号工时")
    private Double eighteenconsumed;
    /**
     * 五号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fiveevaluationcost")
    @JsonProperty("fiveevaluationcost")
    @ApiModelProperty("五号评估成本")
    private Double fiveevaluationcost;
    /**
     * 二号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twoevaluationcost")
    @JsonProperty("twoevaluationcost")
    @ApiModelProperty("二号评估成本")
    private Double twoevaluationcost;
    /**
     * 十一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "elevenevaluationcost")
    @JsonProperty("elevenevaluationcost")
    @ApiModelProperty("十一号评估成本")
    private Double elevenevaluationcost;
    /**
     * 二号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twoevaluationtime")
    @JsonProperty("twoevaluationtime")
    @ApiModelProperty("二号评估工时")
    private Double twoevaluationtime;
    /**
     * 十五号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fifteenevaluationcost")
    @JsonProperty("fifteenevaluationcost")
    @ApiModelProperty("十五号评估成本")
    private Double fifteenevaluationcost;
    /**
     * 二十号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyevaluationcost")
    @JsonProperty("twentyevaluationcost")
    @ApiModelProperty("二十号评估成本")
    private Double twentyevaluationcost;
    /**
     * 二十三号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentythreeconsumed")
    @JsonProperty("twentythreeconsumed")
    @ApiModelProperty("二十三号工时")
    private Double twentythreeconsumed;
    /**
     * 三十一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyoneevaluationtime")
    @JsonProperty("thirtyoneevaluationtime")
    @ApiModelProperty("三十一号评估工时")
    private Double thirtyoneevaluationtime;
    /**
     * 九号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "nineevaluationcost")
    @JsonProperty("nineevaluationcost")
    @ApiModelProperty("九号评估成本")
    private Double nineevaluationcost;
    /**
     * 二十七号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentysevenevaluationcost")
    @JsonProperty("twentysevenevaluationcost")
    @ApiModelProperty("二十七号评估成本")
    private Double twentysevenevaluationcost;
    /**
     * 二十五号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfiveevaluationtime")
    @JsonProperty("twentyfiveevaluationtime")
    @ApiModelProperty("二十五号评估工时")
    private Double twentyfiveevaluationtime;
    /**
     * 二十五号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfiveconsumed")
    @JsonProperty("twentyfiveconsumed")
    @ApiModelProperty("二十五号工时")
    private Double twentyfiveconsumed;
    /**
     * 年
     */
    @TableField(exist = false)
    @JSONField(name = "year")
    @JsonProperty("year")
    @ApiModelProperty("年")
    private String year;
    /**
     * 二十四号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfourevaluationtime")
    @JsonProperty("twentyfourevaluationtime")
    @ApiModelProperty("二十四号评估工时")
    private Double twentyfourevaluationtime;
    /**
     * 二十四号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfourevaluationcost")
    @JsonProperty("twentyfourevaluationcost")
    @ApiModelProperty("二十四号评估成本")
    private Double twentyfourevaluationcost;
    /**
     * 项目标识
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目标识")
    private Long project;
    /**
     * 十五号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fifteenevaluationtime")
    @JsonProperty("fifteenevaluationtime")
    @ApiModelProperty("十五号评估工时")
    private Double fifteenevaluationtime;
    /**
     * 十号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "tenevaluationcost")
    @JsonProperty("tenevaluationcost")
    @ApiModelProperty("十号评估成本")
    private Double tenevaluationcost;
    /**
     * 三号工时
     */
    @TableField(exist = false)
    @JSONField(name = "threeconsumed")
    @JsonProperty("threeconsumed")
    @ApiModelProperty("三号工时")
    private Double threeconsumed;
    /**
     * 用户
     */
    @TableField(exist = false)
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 二十六号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysixconsumed")
    @JsonProperty("twentysixconsumed")
    @ApiModelProperty("二十六号工时")
    private Double twentysixconsumed;
    /**
     * 十六号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "sixteenevaluationcost")
    @JsonProperty("sixteenevaluationcost")
    @ApiModelProperty("十六号评估成本")
    private Double sixteenevaluationcost;
    /**
     * 一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "oneevaluationcost")
    @JsonProperty("oneevaluationcost")
    @ApiModelProperty("一号评估成本")
    private Double oneevaluationcost;
    /**
     * 四号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fourevaluationcost")
    @JsonProperty("fourevaluationcost")
    @ApiModelProperty("四号评估成本")
    private Double fourevaluationcost;
    /**
     * 五号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fiveevaluationtime")
    @JsonProperty("fiveevaluationtime")
    @ApiModelProperty("五号评估工时")
    private Double fiveevaluationtime;
    /**
     * 四号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourconsumed")
    @JsonProperty("fourconsumed")
    @ApiModelProperty("四号工时")
    private Double fourconsumed;
    /**
     * 十六号工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixteenconsumed")
    @JsonProperty("sixteenconsumed")
    @ApiModelProperty("十六号工时")
    private Double sixteenconsumed;



    /**
     * 设置 [日期]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [日期]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [评估成本]
     */
    public void setEvaluationcost(Double evaluationcost) {
        this.evaluationcost = evaluationcost;
        this.modify("evaluationcost", evaluationcost);
    }

    /**
     * 设置 [投入成本]
     */
    public void setInputcost(Double inputcost) {
        this.inputcost = inputcost;
        this.modify("inputcost", inputcost);
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


