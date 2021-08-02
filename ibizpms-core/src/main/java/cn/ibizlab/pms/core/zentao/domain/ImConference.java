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
 * 实体[ImConference]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_im_conference", resultMap = "ImConferenceResultMap")
@ApiModel("ImConference")
public class ImConference extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * status
     */
    @DEField(dict = "Im_conference__status")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("status")
    private String status;
    /**
     * cgid
     */
    @TableField(value = "`CGID`")
    @JSONField(name = "cgid")
    @JsonProperty("cgid")
    @ApiModelProperty("cgid")
    private String cgid;
    /**
     * openedBy
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`OPENEDBY`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("openedBy")
    private Integer openedby;
    /**
     * openedDate
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`OPENEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("openedDate")
    private Timestamp openeddate;
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
     * rid
     */
    @TableField(value = "`RID`")
    @JSONField(name = "rid")
    @JsonProperty("rid")
    @ApiModelProperty("rid")
    private String rid;
    /**
     * participants
     */
    @TableField(value = "`PARTICIPANTS`")
    @JSONField(name = "participants")
    @JsonProperty("participants")
    @ApiModelProperty("participants")
    private String participants;



    /**
     * 设置 [status]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [cgid]
     */
    public void setCgid(String cgid) {
        this.cgid = cgid;
        this.modify("cgid", cgid);
    }

    /**
     * 设置 [openedBy]
     */
    public void setOpenedby(Integer openedby) {
        this.openedby = openedby;
        this.modify("openedby", openedby);
    }

    /**
     * 设置 [openedDate]
     */
    public void setOpeneddate(Timestamp openeddate) {
        this.openeddate = openeddate;
        this.modify("openeddate", openeddate);
    }

    /**
     * 格式化日期 [openedDate]
     */
    public String formatOpeneddate() {
        if (this.openeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(openeddate);
    }
    /**
     * 设置 [rid]
     */
    public void setRid(String rid) {
        this.rid = rid;
        this.modify("rid", rid);
    }

    /**
     * 设置 [participants]
     */
    public void setParticipants(String participants) {
        this.participants = participants;
        this.modify("participants", participants);
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


