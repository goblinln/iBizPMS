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


/**
 * [产品线] 对象
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("产品线")
public class ProductLine extends EntityBase implements Serializable {

    /**
     * 产品线名称
     */
    @DEField(name = "ibz_productlinename")
    @JSONField(name = "productLineName")
    @JsonProperty("productLineName")
    @ApiModelProperty("产品线名称")
    private String productlinename;

    /**
     * 产品线标识
     */
    @DEField(name = "ibz_productlineid", isKeyField = true)
    @JSONField(name = "productLineId")
    @JsonProperty("productLineId")
    @ApiModelProperty("产品线标识")
    private String productlineid;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateMan")
    @JsonProperty("updateMan")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updateDate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updateDate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createDate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createDate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createMan")
    @JsonProperty("createMan")
    @ApiModelProperty("建立人")
    private String createman;




}


