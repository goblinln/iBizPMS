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
 * 实体[置顶]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_TOP", resultMap = "IbzTopResultMap")
public class IbzTop extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标识
     */
    @DEField(name = "ibz_topid", isKeyField = true)
    @TableId(value = "ibz_topid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibztopid")
    @JsonProperty("ibztopid")
    private String ibztopid;
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
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 置顶名称
     */
    @DEField(name = "ibz_topname")
    @TableField(value = "`ibz_topname`")
    @JSONField(name = "ibztopname")
    @JsonProperty("ibztopname")
    private String ibztopname;
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
     * 类型
     */
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 数据对象标识
     */
    @TableField(value = "`objectid`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    private Integer objectid;
    /**
     * 排序
     */
    @TableField(value = "`objectorder`")
    @JSONField(name = "objectorder")
    @JsonProperty("objectorder")
    private Integer objectorder;
    /**
     * 置顶用户
     */
    @TableField(value = "`account`")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;



    /**
     * 设置 [置顶名称]
     */
    public void setIbztopname(String ibztopname) {
        this.ibztopname = ibztopname;
        this.modify("ibz_topname", ibztopname);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [数据对象标识]
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
        this.modify("objectid", objectid);
    }

    /**
     * 设置 [排序]
     */
    public void setObjectorder(Integer objectorder) {
        this.objectorder = objectorder;
        this.modify("objectorder", objectorder);
    }

    /**
     * 设置 [置顶用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
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
        this.reset("ibz_topid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


