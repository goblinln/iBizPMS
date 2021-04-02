package cn.ibizlab.pms.core.report.domain;

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
 * 实体[汇报角色配置]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_REPORT_ROLE_CONFIG", resultMap = "IbzReportRoleConfigResultMap")
@ApiModel("汇报角色配置")
public class IbzReportRoleConfig extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 汇报角色配置名称
     */
    @DEField(name = "ibz_report_role_configname")
    @TableField(value = "ibz_report_role_configname")
    @JSONField(name = "ibzreportroleconfigname")
    @JsonProperty("ibzreportroleconfigname")
    @ApiModelProperty("汇报角色配置名称")
    private String ibzreportroleconfigname;
    /**
     * 汇报角色配置标识
     */
    @DEField(name = "ibz_report_role_configid", isKeyField = true)
    @TableId(value = "ibz_report_role_configid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibzreportroleconfigid")
    @JsonProperty("ibzreportroleconfigid")
    @ApiModelProperty("汇报角色配置标识")
    private String ibzreportroleconfigid;
    /**
     * 角色
     */
    @DEField(name = "report_role")
    @TableField(value = "report_role")
    @JSONField(name = "report_role")
    @JsonProperty("report_role")
    @ApiModelProperty("角色")
    private String reportRole;
    /**
     * 类型
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createdate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "updatedate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "createman", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "updateman")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;



    /**
     * 设置 [汇报角色配置名称]
     */
    public void setIbzreportroleconfigname(String ibzreportroleconfigname) {
        this.ibzreportroleconfigname = ibzreportroleconfigname;
        this.modify("ibz_report_role_configname", ibzreportroleconfigname);
    }

    /**
     * 设置 [角色]
     */
    public void setReportRole(String reportRole) {
        this.reportRole = reportRole;
        this.modify("report_role", reportRole);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
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
        this.reset("ibz_report_role_configid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


