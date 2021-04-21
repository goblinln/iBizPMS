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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[ImMessage]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_im_message", resultMap = "ImMessageResultMap")
@ApiModel("ImMessage")
public class ImMessage extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user
     */
    @TableField(value = "`USER`")
    @JSONField(name = "user")
    @JsonProperty("user")
    @ApiModelProperty("user")
    private String user;
    /**
     * gid
     */
    @TableField(value = "`GID`")
    @JSONField(name = "gid")
    @JsonProperty("gid")
    @ApiModelProperty("gid")
    private String gid;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * date
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    @ApiModelProperty("date")
    private Timestamp date;
    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("逻辑删除标志")
    private String deleted;
    /**
     * data
     */
    @TableField(value = "`DATA`")
    @JSONField(name = "data")
    @JsonProperty("data")
    @ApiModelProperty("data")
    private String data;
    /**
     * type
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("type")
    private String type;
    /**
     * content
     */
    @TableField(value = "`CONTENT`")
    @JSONField(name = "content")
    @JsonProperty("content")
    @ApiModelProperty("content")
    private String content;
    /**
     * cgid
     */
    @TableField(value = "`CGID`")
    @JSONField(name = "cgid")
    @JsonProperty("cgid")
    @ApiModelProperty("cgid")
    private String cgid;
    /**
     * order
     */
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("order")
    private Long order;
    /**
     * contentType
     */
    @TableField(value = "`CONTENTTYPE`")
    @JSONField(name = "contenttype")
    @JsonProperty("contenttype")
    @ApiModelProperty("contentType")
    private String contenttype;



    /**
     * 设置 [user]
     */
    public void setUser(String user) {
        this.user = user;
        this.modify("user", user);
    }

    /**
     * 设置 [gid]
     */
    public void setGid(String gid) {
        this.gid = gid;
        this.modify("gid", gid);
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
     * 设置 [data]
     */
    public void setData(String data) {
        this.data = data;
        this.modify("data", data);
    }

    /**
     * 设置 [type]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [content]
     */
    public void setContent(String content) {
        this.content = content;
        this.modify("content", content);
    }

    /**
     * 设置 [cgid]
     */
    public void setCgid(String cgid) {
        this.cgid = cgid;
        this.modify("cgid", cgid);
    }

    /**
     * 设置 [order]
     */
    public void setOrder(Long order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [contentType]
     */
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
        this.modify("contenttype", contenttype);
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


