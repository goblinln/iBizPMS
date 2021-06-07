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
 * 实体[动态搜索栏]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_DYNAFILTER", resultMap = "DynaFilterResultMap")
@ApiModel("动态搜索栏")
public class DynaFilter extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态搜索栏标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "`DYNAFILTERID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "dynafilterid")
    @JsonProperty("dynafilterid")
    @ApiModelProperty("动态搜索栏标识")
    private String dynafilterid;
    /**
     * 组织部门标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPTID`")
    @JSONField(name = "deptid")
    @JsonProperty("deptid")
    @ApiModelProperty("组织部门标识")
    private String deptid;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 表单名称
     */
    @TableField(value = "`FORMNAME`")
    @JSONField(name = "formname")
    @JsonProperty("formname")
    @ApiModelProperty("表单名称")
    private String formname;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`UPDATEDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 组织机构标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORGID`")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织机构标识")
    private String orgid;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`CREATEDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 实体名称
     */
    @TableField(value = "`DENAME`")
    @JSONField(name = "dename")
    @JsonProperty("dename")
    @ApiModelProperty("实体名称")
    private String dename;
    /**
     * 动态搜索栏名称
     */
    @TableField(value = "`DYNAFILTERNAME`")
    @JSONField(name = "dynafiltername")
    @JsonProperty("dynafiltername")
    @ApiModelProperty("动态搜索栏名称")
    private String dynafiltername;
    /**
     * 数据
     */
    @TableField(value = "`DATA`")
    @JSONField(name = "data")
    @JsonProperty("data")
    @ApiModelProperty("数据")
    private String data;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;



    /**
     * 设置 [表单名称]
     */
    public void setFormname(String formname) {
        this.formname = formname;
        this.modify("formname", formname);
    }

    /**
     * 设置 [实体名称]
     */
    public void setDename(String dename) {
        this.dename = dename;
        this.modify("dename", dename);
    }

    /**
     * 设置 [动态搜索栏名称]
     */
    public void setDynafiltername(String dynafiltername) {
        this.dynafiltername = dynafiltername;
        this.modify("dynafiltername", dynafiltername);
    }

    /**
     * 设置 [数据]
     */
    public void setData(String data) {
        this.data = data;
        this.modify("data", data);
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
        this.reset("dynafilterid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


