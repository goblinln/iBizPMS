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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[产品生命周期]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_PRODUCTLIFE", resultMap = "ProductLifeResultMap")
public class ProductLife extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台/分支
     */
    @TableField(value = "`branch`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    private String branch;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 产品生命周期名称
     */
    @DEField(name = "ibz_productlifename")
    @TableField(value = "`ibz_productlifename`")
    @JSONField(name = "productlifename")
    @JsonProperty("productlifename")
    private String productlifename;
    /**
     * 产品
     */
    @TableField(value = "`product`")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Integer product;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 父对象
     */
    @TableField(value = "`parent`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    private String parent;
    /**
     * 年
     */
    @TableField(value = "`year`")
    @JSONField(name = "year")
    @JsonProperty("year")
    private String year;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 属性
     */
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 里程碑
     */
    @TableField(value = "`marker`")
    @JSONField(name = "marker")
    @JsonProperty("marker")
    private Integer marker;
    /**
     * 开始日期
     */
    @TableField(value = "`begin`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    private Timestamp begin;
    /**
     * 产品生命周期标识
     */
    @DEField(name = "ibz_productlifeid", isKeyField = true)
    @TableId(value = "ibz_productlifeid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "productlifeid")
    @JsonProperty("productlifeid")
    private String productlifeid;
    /**
     * 结束日期
     */
    @TableField(value = "`end`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    private Timestamp end;



    /**
     * 设置 [平台/分支]
     */
    public void setBranch(String branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [产品生命周期名称]
     */
    public void setProductlifename(String productlifename) {
        this.productlifename = productlifename;
        this.modify("ibz_productlifename", productlifename);
    }

    /**
     * 设置 [产品]
     */
    public void setProduct(Integer product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [父对象]
     */
    public void setParent(String parent) {
        this.parent = parent;
        this.modify("parent", parent);
    }

    /**
     * 设置 [年]
     */
    public void setYear(String year) {
        this.year = year;
        this.modify("year", year);
    }

    /**
     * 设置 [属性]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [里程碑]
     */
    public void setMarker(Integer marker) {
        this.marker = marker;
        this.modify("marker", marker);
    }

    /**
     * 设置 [开始日期]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [开始日期]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(begin);
    }
    /**
     * 设置 [结束日期]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [结束日期]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(end);
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
        this.reset("ibz_productlifeid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


