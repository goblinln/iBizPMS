package cn.ibizlab.pms.core.zentao.domain;

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
 * 实体[log]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_log", resultMap = "LogResultMap")
public class Log extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    @TableField(value = "`url`")
    @JSONField(name = "url")
    @JsonProperty("url")
    private String url;
    /**
     * action
     */
    @TableField(value = "`action`")
    @JSONField(name = "action")
    @JsonProperty("action")
    private Integer action;
    /**
     * contentType
     */
    @TableField(value = "`contenttype`")
    @JSONField(name = "contenttype")
    @JsonProperty("contenttype")
    private String contenttype;
    /**
     * objectID
     */
    @TableField(value = "`objectid`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    private Integer objectid;
    /**
     * date
     */
    @TableField(value = "`date`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * result
     */
    @TableField(value = "`result`")
    @JSONField(name = "result")
    @JsonProperty("result")
    private String result;
    /**
     * objectType
     */
    @TableField(value = "`objecttype`")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    private String objecttype;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * data
     */
    @TableField(value = "`data`")
    @JSONField(name = "data")
    @JsonProperty("data")
    private String data;



    /**
     * 设置 [url]
     */
    public void setUrl(String url) {
        this.url = url;
        this.modify("url", url);
    }

    /**
     * 设置 [action]
     */
    public void setAction(Integer action) {
        this.action = action;
        this.modify("action", action);
    }

    /**
     * 设置 [contentType]
     */
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
        this.modify("contenttype", contenttype);
    }

    /**
     * 设置 [objectID]
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
        this.modify("objectid", objectid);
    }

    /**
     * 设置 [date]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [date]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * 设置 [result]
     */
    public void setResult(String result) {
        this.result = result;
        this.modify("result", result);
    }

    /**
     * 设置 [objectType]
     */
    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
        this.modify("objecttype", objecttype);
    }

    /**
     * 设置 [data]
     */
    public void setData(String data) {
        this.data = data;
        this.modify("data", data);
    }


    @Override
    public Serializable getDefaultKey(boolean gen) {
        return IdWorker.getId();
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


