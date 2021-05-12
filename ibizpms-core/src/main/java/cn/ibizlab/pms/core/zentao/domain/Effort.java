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
 * 实体[effort]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_effort", resultMap = "EffortResultMap")
@ApiModel("effort")
public class Effort extends EntityMP implements Serializable {

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
     * idvalue
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`IDVALUE`")
    @JSONField(name = "idvalue")
    @JsonProperty("idvalue")
    @ApiModelProperty("idvalue")
    private Integer idvalue;
    /**
     * status
     */
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("status")
    private String status;
    /**
     * end
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`END`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("end")
    @ApiModelProperty("end")
    private Timestamp end;
    /**
     * date
     */
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("date")
    private Timestamp date;
    /**
     * name
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("name")
    private String name;
    /**
     * desc
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
    /**
     * todo
     */
    @TableField(value = "`TODO`")
    @JSONField(name = "todo")
    @JsonProperty("todo")
    @ApiModelProperty("todo")
    private String todo;
    /**
     * type
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("type")
    private String type;
    /**
     * id
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * begin
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`BEGIN`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("begin")
    @ApiModelProperty("begin")
    private Timestamp begin;



    /**
     * 设置 [user]
     */
    public void setUser(String user) {
        this.user = user;
        this.modify("user", user);
    }

    /**
     * 设置 [idvalue]
     */
    public void setIdvalue(Integer idvalue) {
        this.idvalue = idvalue;
        this.modify("idvalue", idvalue);
    }

    /**
     * 设置 [status]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [end]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [end]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(end);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [todo]
     */
    public void setTodo(String todo) {
        this.todo = todo;
        this.modify("todo", todo);
    }

    /**
     * 设置 [type]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [begin]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [begin]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(begin);
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


