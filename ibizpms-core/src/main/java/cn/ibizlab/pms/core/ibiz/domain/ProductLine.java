package cn.ibizlab.pms.core.ibiz.domain;

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
 * 实体[产品线（废弃）]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_module", resultMap = "ProductLineResultMap")
@ApiModel("产品线（废弃）")
public class ProductLine extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品线名称
     */
    @DEField(name = "ibz_productlinename")
    @TableField(value = "`IBZ_PRODUCTLINENAME`")
    @JSONField(name = "productlinename")
    @JsonProperty("productlinename")
    @ApiModelProperty("产品线名称")
    private String productlinename;
    /**
     * 产品线标识
     */
    @DEField(name = "ibz_productlineid", isKeyField = true)
    @TableId(value = "`IBZ_PRODUCTLINEID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "productlineid")
    @JsonProperty("productlineid")
    @ApiModelProperty("产品线标识")
    private String productlineid;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`UPDATEDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`CREATEDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;



    /**
     * 设置 [产品线名称]
     */
    public void setProductlinename(String productlinename) {
        this.productlinename = productlinename;
        this.modify("ibz_productlinename", productlinename);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
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
        this.reset("ibz_productlineid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


