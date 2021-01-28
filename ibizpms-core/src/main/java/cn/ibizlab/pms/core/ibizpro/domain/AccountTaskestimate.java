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
public class AccountTaskestimate extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @DEField(isKeyField = true)
    @TableField(exist = false)
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;
    /**
     * 项目标识
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    private Long project;
    /**
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    private String projectname;
    /**
     * 年
     */
    @TableField(exist = false)
    @JSONField(name = "year")
    @JsonProperty("year")
    private String year;
    /**
     * 月
     */
    @TableField(exist = false)
    @JSONField(name = "month")
    @JsonProperty("month")
    private String month;
    /**
     * 用户
     */
    @TableField(exist = false)
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * 日期
     */
    @TableField(value = "`date`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 工时
     */
    @TableField(exist = false)
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    private Double consumed;
    /**
     * 评估成本
     */
    @TableField(value = "`evaluationcost`")
    @JSONField(name = "evaluationcost")
    @JsonProperty("evaluationcost")
    private Double evaluationcost;
    /**
     * 投入成本
     */
    @TableField(value = "`inputcost`")
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    private Double inputcost;
    /**
     * 评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "evaluationtime")
    @JsonProperty("evaluationtime")
    private Double evaluationtime;
    /**
     * 三十一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyoneevaluationtime")
    @JsonProperty("thirtyoneevaluationtime")
    private Double thirtyoneevaluationtime;
    /**
     * 三十号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyevaluationtime")
    @JsonProperty("thirtyevaluationtime")
    private Double thirtyevaluationtime;
    /**
     * 二十八号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyeightevaluationtime")
    @JsonProperty("twentyeightevaluationtime")
    private Double twentyeightevaluationtime;
    /**
     * 二十九号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentynineevaluationtime")
    @JsonProperty("twentynineevaluationtime")
    private Double twentynineevaluationtime;
    /**
     * 二十七号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysevenevaluationtime")
    @JsonProperty("twentysevenevaluationtime")
    private Double twentysevenevaluationtime;
    /**
     * 二十六号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysixevaluationtime")
    @JsonProperty("twentysixevaluationtime")
    private Double twentysixevaluationtime;
    /**
     * 二十五号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfiveevaluationtime")
    @JsonProperty("twentyfiveevaluationtime")
    private Double twentyfiveevaluationtime;
    /**
     * 二十四号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfourevaluationtime")
    @JsonProperty("twentyfourevaluationtime")
    private Double twentyfourevaluationtime;
    /**
     * 二十三号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentythreeevaluationtime")
    @JsonProperty("twentythreeevaluationtime")
    private Double twentythreeevaluationtime;
    /**
     * 二十二号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentytwoevaluationtime")
    @JsonProperty("twentytwoevaluationtime")
    private Double twentytwoevaluationtime;
    /**
     * 十九号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineteenevaluationtime")
    @JsonProperty("nineteenevaluationtime")
    private Double nineteenevaluationtime;
    /**
     * 二十一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyoneevaluationtime")
    @JsonProperty("twentyoneevaluationtime")
    private Double twentyoneevaluationtime;
    /**
     * 二十号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyevaluationtime")
    @JsonProperty("twentyevaluationtime")
    private Double twentyevaluationtime;
    /**
     * 十七号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "seventeenevaluationtime")
    @JsonProperty("seventeenevaluationtime")
    private Double seventeenevaluationtime;
    /**
     * 十八号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "eighteenevaluationtime")
    @JsonProperty("eighteenevaluationtime")
    private Double eighteenevaluationtime;
    /**
     * 十六号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixteenevaluationtime")
    @JsonProperty("sixteenevaluationtime")
    private Double sixteenevaluationtime;
    /**
     * 十五号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fifteenevaluationtime")
    @JsonProperty("fifteenevaluationtime")
    private Double fifteenevaluationtime;
    /**
     * 十四号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourteenevaluationtime")
    @JsonProperty("fourteenevaluationtime")
    private Double fourteenevaluationtime;
    /**
     * 十三号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirteenevaluationtime")
    @JsonProperty("thirteenevaluationtime")
    private Double thirteenevaluationtime;
    /**
     * 十一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "elevenevaluationtime")
    @JsonProperty("elevenevaluationtime")
    private Double elevenevaluationtime;
    /**
     * 九号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineevaluationtime")
    @JsonProperty("nineevaluationtime")
    private Double nineevaluationtime;
    /**
     * 十二号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twelveevaluationtime")
    @JsonProperty("twelveevaluationtime")
    private Double twelveevaluationtime;
    /**
     * 七号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "sevenevaluationtime")
    @JsonProperty("sevenevaluationtime")
    private Double sevenevaluationtime;
    /**
     * 十号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "tenevaluationtime")
    @JsonProperty("tenevaluationtime")
    private Double tenevaluationtime;
    /**
     * 六号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixevaluationtime")
    @JsonProperty("sixevaluationtime")
    private Double sixevaluationtime;
    /**
     * 八号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "eightevaluationtime")
    @JsonProperty("eightevaluationtime")
    private Double eightevaluationtime;
    /**
     * 五号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fiveevaluationtime")
    @JsonProperty("fiveevaluationtime")
    private Double fiveevaluationtime;
    /**
     * 一号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "oneevaluationtime")
    @JsonProperty("oneevaluationtime")
    private Double oneevaluationtime;
    /**
     * 三号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "threeevaluationtime")
    @JsonProperty("threeevaluationtime")
    private Double threeevaluationtime;
    /**
     * 四号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourevaluationtime")
    @JsonProperty("fourevaluationtime")
    private Double fourevaluationtime;
    /**
     * 二号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twoconsumed")
    @JsonProperty("twoconsumed")
    private Double twoconsumed;
    /**
     * 二号评估工时
     */
    @TableField(exist = false)
    @JSONField(name = "twoevaluationtime")
    @JsonProperty("twoevaluationtime")
    private Double twoevaluationtime;
    /**
     * 一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "oneconsumed")
    @JsonProperty("oneconsumed")
    private Double oneconsumed;
    /**
     * 四号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourconsumed")
    @JsonProperty("fourconsumed")
    private Double fourconsumed;
    /**
     * 五号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fiveconsumed")
    @JsonProperty("fiveconsumed")
    private Double fiveconsumed;
    /**
     * 三号工时
     */
    @TableField(exist = false)
    @JSONField(name = "threeconsumed")
    @JsonProperty("threeconsumed")
    private Double threeconsumed;
    /**
     * 七号工时
     */
    @TableField(exist = false)
    @JSONField(name = "sevenconsumed")
    @JsonProperty("sevenconsumed")
    private Double sevenconsumed;
    /**
     * 九号工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineconsumed")
    @JsonProperty("nineconsumed")
    private Double nineconsumed;
    /**
     * 六号工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixconsumed")
    @JsonProperty("sixconsumed")
    private Double sixconsumed;
    /**
     * 十号工时
     */
    @TableField(exist = false)
    @JSONField(name = "tenconsumed")
    @JsonProperty("tenconsumed")
    private Double tenconsumed;
    /**
     * 八号工时
     */
    @TableField(exist = false)
    @JSONField(name = "eightconsumed")
    @JsonProperty("eightconsumed")
    private Double eightconsumed;
    /**
     * 十三号工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirteenconsumed")
    @JsonProperty("thirteenconsumed")
    private Double thirteenconsumed;
    /**
     * 十一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "elevenconsumed")
    @JsonProperty("elevenconsumed")
    private Double elevenconsumed;
    /**
     * 十四号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fourteenconsumed")
    @JsonProperty("fourteenconsumed")
    private Double fourteenconsumed;
    /**
     * 十二号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twelveconsumed")
    @JsonProperty("twelveconsumed")
    private Double twelveconsumed;
    /**
     * 十七号工时
     */
    @TableField(exist = false)
    @JSONField(name = "seventeenconsumed")
    @JsonProperty("seventeenconsumed")
    private Double seventeenconsumed;
    /**
     * 十五号工时
     */
    @TableField(exist = false)
    @JSONField(name = "fifteenconsumed")
    @JsonProperty("fifteenconsumed")
    private Double fifteenconsumed;
    /**
     * 十八号工时
     */
    @TableField(exist = false)
    @JSONField(name = "eighteenconsumed")
    @JsonProperty("eighteenconsumed")
    private Double eighteenconsumed;
    /**
     * 十六号工时
     */
    @TableField(exist = false)
    @JSONField(name = "sixteenconsumed")
    @JsonProperty("sixteenconsumed")
    private Double sixteenconsumed;
    /**
     * 二十号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyconsumed")
    @JsonProperty("twentyconsumed")
    private Double twentyconsumed;
    /**
     * 二十二号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentytwoconsumed")
    @JsonProperty("twentytwoconsumed")
    private Double twentytwoconsumed;
    /**
     * 十九号工时
     */
    @TableField(exist = false)
    @JSONField(name = "nineteenconsumed")
    @JsonProperty("nineteenconsumed")
    private Double nineteenconsumed;
    /**
     * 二十一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyoneconsumed")
    @JsonProperty("twentyoneconsumed")
    private Double twentyoneconsumed;
    /**
     * 二十三号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentythreeconsumed")
    @JsonProperty("twentythreeconsumed")
    private Double twentythreeconsumed;
    /**
     * 二十四号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfourconsumed")
    @JsonProperty("twentyfourconsumed")
    private Double twentyfourconsumed;
    /**
     * 二十六号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysixconsumed")
    @JsonProperty("twentysixconsumed")
    private Double twentysixconsumed;
    /**
     * 二十九号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentynineconsumed")
    @JsonProperty("twentynineconsumed")
    private Double twentynineconsumed;
    /**
     * 二十七号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentysevenconsumed")
    @JsonProperty("twentysevenconsumed")
    private Double twentysevenconsumed;
    /**
     * 二十五号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfiveconsumed")
    @JsonProperty("twentyfiveconsumed")
    private Double twentyfiveconsumed;
    /**
     * 二十八号工时
     */
    @TableField(exist = false)
    @JSONField(name = "twentyeightconsumed")
    @JsonProperty("twentyeightconsumed")
    private Double twentyeightconsumed;
    /**
     * 三十一号工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyoneconsumed")
    @JsonProperty("thirtyoneconsumed")
    private Double thirtyoneconsumed;
    /**
     * 三十号工时
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyconsumed")
    @JsonProperty("thirtyconsumed")
    private Double thirtyconsumed;
    /**
     * 三十号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyevaluationcost")
    @JsonProperty("thirtyevaluationcost")
    private Double thirtyevaluationcost;
    /**
     * 三十一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "thirtyoneevaluationcost")
    @JsonProperty("thirtyoneevaluationcost")
    private Double thirtyoneevaluationcost;
    /**
     * 二十七号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentysevenevaluationcost")
    @JsonProperty("twentysevenevaluationcost")
    private Double twentysevenevaluationcost;
    /**
     * 二十九号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentynineevaluationcost")
    @JsonProperty("twentynineevaluationcost")
    private Double twentynineevaluationcost;
    /**
     * 二十八号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyeightevaluationcost")
    @JsonProperty("twentyeightevaluationcost")
    private Double twentyeightevaluationcost;
    /**
     * 二十六号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentysixevaluationcost")
    @JsonProperty("twentysixevaluationcost")
    private Double twentysixevaluationcost;
    /**
     * 二十三号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentythreeevaluationcost")
    @JsonProperty("twentythreeevaluationcost")
    private Double twentythreeevaluationcost;
    /**
     * 二十四号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfourevaluationcost")
    @JsonProperty("twentyfourevaluationcost")
    private Double twentyfourevaluationcost;
    /**
     * 二十二号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentytwoevaluationcost")
    @JsonProperty("twentytwoevaluationcost")
    private Double twentytwoevaluationcost;
    /**
     * 二十五号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyfiveevaluationcost")
    @JsonProperty("twentyfiveevaluationcost")
    private Double twentyfiveevaluationcost;
    /**
     * 十九号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "nineteenevaluationcost")
    @JsonProperty("nineteenevaluationcost")
    private Double nineteenevaluationcost;
    /**
     * 二十一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyoneevaluationcost")
    @JsonProperty("twentyoneevaluationcost")
    private Double twentyoneevaluationcost;
    /**
     * 二十号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twentyevaluationcost")
    @JsonProperty("twentyevaluationcost")
    private Double twentyevaluationcost;
    /**
     * 十八号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "eighteenevaluationcost")
    @JsonProperty("eighteenevaluationcost")
    private Double eighteenevaluationcost;
    /**
     * 十七号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "seventeenevaluationcost")
    @JsonProperty("seventeenevaluationcost")
    private Double seventeenevaluationcost;
    /**
     * 十五号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fifteenevaluationcost")
    @JsonProperty("fifteenevaluationcost")
    private Double fifteenevaluationcost;
    /**
     * 十六号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "sixteenevaluationcost")
    @JsonProperty("sixteenevaluationcost")
    private Double sixteenevaluationcost;
    /**
     * 十四号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fourteenevaluationcost")
    @JsonProperty("fourteenevaluationcost")
    private Double fourteenevaluationcost;
    /**
     * 十三号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "thirteenevaluationcost")
    @JsonProperty("thirteenevaluationcost")
    private Double thirteenevaluationcost;
    /**
     * 十号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "tenevaluationcost")
    @JsonProperty("tenevaluationcost")
    private Double tenevaluationcost;
    /**
     * 十二号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twelveevaluationcost")
    @JsonProperty("twelveevaluationcost")
    private Double twelveevaluationcost;
    /**
     * 十一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "elevenevaluationcost")
    @JsonProperty("elevenevaluationcost")
    private Double elevenevaluationcost;
    /**
     * 九号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "nineevaluationcost")
    @JsonProperty("nineevaluationcost")
    private Double nineevaluationcost;
    /**
     * 六号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "sixevaluationcost")
    @JsonProperty("sixevaluationcost")
    private Double sixevaluationcost;
    /**
     * 七号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "sevenevaluationcost")
    @JsonProperty("sevenevaluationcost")
    private Double sevenevaluationcost;
    /**
     * 五号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fiveevaluationcost")
    @JsonProperty("fiveevaluationcost")
    private Double fiveevaluationcost;
    /**
     * 八号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "eightevaluationcost")
    @JsonProperty("eightevaluationcost")
    private Double eightevaluationcost;
    /**
     * 四号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "fourevaluationcost")
    @JsonProperty("fourevaluationcost")
    private Double fourevaluationcost;
    /**
     * 三号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "threeevaluationcost")
    @JsonProperty("threeevaluationcost")
    private Double threeevaluationcost;
    /**
     * 二号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "twoevaluationcost")
    @JsonProperty("twoevaluationcost")
    private Double twoevaluationcost;
    /**
     * 一号评估成本
     */
    @TableField(exist = false)
    @JSONField(name = "oneevaluationcost")
    @JsonProperty("oneevaluationcost")
    private Double oneevaluationcost;



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


