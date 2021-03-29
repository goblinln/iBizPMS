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
 * [文档] 对象
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文档")
public class IBzDoc extends EntityBase implements Serializable {

    /**
     * 文档标识
     */
    @DEField(name = "ibz_docid", isKeyField = true)
    @JSONField(name = "iBzDocId")
    @JsonProperty("iBzDocId")
    @ApiModelProperty("文档标识")
    private String ibzdocid;

    /**
     * 由谁添加
     */
    @JSONField(name = "addedBy")
    @JsonProperty("addedBy")
    @ApiModelProperty("由谁添加")
    private String addedby;

    /**
     * 由谁更新
     */
    @JSONField(name = "editedBy")
    @JsonProperty("editedBy")
    @ApiModelProperty("由谁更新")
    private String editedby;

    /**
     * 添加时间
     */
    @JSONField(name = "addedDate")
    @JsonProperty("addedDate")
    @ApiModelProperty("添加时间")
    private String addeddate;

    /**
     * 大小
     */
    @JSONField(name = "size")
    @JsonProperty("size")
    @ApiModelProperty("大小")
    private String size;

    /**
     * 所属文档库
     */
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @ApiModelProperty("所属文档库")
    private String lib;

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
     * 文档名称
     */
    @DEField(name = "ibz_docname")
    @JSONField(name = "iBzDocName")
    @JsonProperty("iBzDocName")
    @ApiModelProperty("文档名称")
    private String ibzdocname;

    /**
     * 排序
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private String order;

    /**
     * 是否已收藏
     */
    @JSONField(name = "isCollect")
    @JsonProperty("isCollect")
    @ApiModelProperty("是否已收藏")
    private Integer iscollect;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createMan")
    @JsonProperty("createMan")
    @ApiModelProperty("建立人")
    private String createman;

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
     * 更新时间
     */
    @JSONField(name = "editedDate")
    @JsonProperty("editedDate")
    @ApiModelProperty("更新时间")
    private String editeddate;

    /**
     * 对象类型
     */
    @JSONField(name = "objectType")
    @JsonProperty("objectType")
    @ApiModelProperty("对象类型")
    private String objecttype;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateMan")
    @JsonProperty("updateMan")
    @ApiModelProperty("更新人")
    private String updateman;




}


