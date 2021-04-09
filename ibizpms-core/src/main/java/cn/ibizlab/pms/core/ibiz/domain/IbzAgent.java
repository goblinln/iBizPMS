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
 * 实体[代理]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_AGENT", resultMap = "IbzAgentResultMap")
@ApiModel("代理")
public class IbzAgent extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 创建人姓名
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`createmanname`")
    @JSONField(name = "createmanname")
    @JsonProperty("createmanname")
    @ApiModelProperty("创建人姓名")
    private String createmanname;
    /**
     * 代理结束日期
     */
    @TableField(value = "`agentend`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "agentend", format = "yyyy-MM-dd")
    @JsonProperty("agentend")
    @ApiModelProperty("代理结束日期")
    private Timestamp agentend;
    /**
     * 代理标识
     */
    @DEField(name = "ibz_agentid", isKeyField = true)
    @TableId(value = "ibz_agentid", type = IdType.AUTO)
    @JSONField(name = "ibzagentid")
    @JsonProperty("ibzagentid")
    @ApiModelProperty("代理标识")
    private Long ibzagentid;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 代理用户
     */
    @TableField(value = "`agentuser`")
    @JSONField(name = "agentuser")
    @JsonProperty("agentuser")
    @ApiModelProperty("代理用户")
    private String agentuser;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 代理开始日期
     */
    @TableField(value = "`agentbegin`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "agentbegin", format = "yyyy-MM-dd")
    @JsonProperty("agentbegin")
    @ApiModelProperty("代理开始日期")
    private Timestamp agentbegin;
    /**
     * 代理名称
     */
    @DEField(name = "ibz_agentname")
    @TableField(value = "`ibz_agentname`")
    @JSONField(name = "ibzagentname")
    @JsonProperty("ibzagentname")
    @ApiModelProperty("代理名称")
    private String ibzagentname;



    /**
     * 设置 [代理结束日期]
     */
    public void setAgentend(Timestamp agentend) {
        this.agentend = agentend;
        this.modify("agentend", agentend);
    }

    /**
     * 格式化日期 [代理结束日期]
     */
    public String formatAgentend() {
        if (this.agentend == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(agentend);
    }
    /**
     * 设置 [代理用户]
     */
    public void setAgentuser(String agentuser) {
        this.agentuser = agentuser;
        this.modify("agentuser", agentuser);
    }

    /**
     * 设置 [代理开始日期]
     */
    public void setAgentbegin(Timestamp agentbegin) {
        this.agentbegin = agentbegin;
        this.modify("agentbegin", agentbegin);
    }

    /**
     * 格式化日期 [代理开始日期]
     */
    public String formatAgentbegin() {
        if (this.agentbegin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(agentbegin);
    }
    /**
     * 设置 [代理名称]
     */
    public void setIbzagentname(String ibzagentname) {
        this.ibzagentname = ibzagentname;
        this.modify("ibz_agentname", ibzagentname);
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
        this.reset("ibz_agentid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


