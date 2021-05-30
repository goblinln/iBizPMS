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
 * 实体[操作历史]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_history", resultMap = "HistoryResultMap")
@ApiModel("操作历史")
public class History extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 不同
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`DIFF`")
    @JSONField(name = "diff")
    @JsonProperty("diff")
    @ApiModelProperty("不同")
    private String diff;
    /**
     * 字段
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`FIELD`")
    @JSONField(name = "field")
    @JsonProperty("field")
    @ApiModelProperty("字段")
    private String field;
    /**
     * 新值
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`NEW`")
    @JSONField(name = "ibiznew")
    @JsonProperty("ibiznew")
    @ApiModelProperty("新值")
    private String ibiznew;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`CREATEBY`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORG`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 旧值
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`OLD`")
    @JSONField(name = "old")
    @JsonProperty("old")
    @ApiModelProperty("旧值")
    private String old;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 关联日志
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ACTION`")
    @JSONField(name = "action")
    @JsonProperty("action")
    @ApiModelProperty("关联日志")
    private Long action;
    /**
     * 操作历史编号
     */
    @TableField(value = "`HISTORYSN`")
    @JSONField(name = "historysn")
    @JsonProperty("historysn")
    @ApiModelProperty("操作历史编号")
    private Long historysn;

    /**
     * 关联日志
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Action ztAction;



    /**
     * 设置 [不同]
     */
    public void setDiff(String diff) {
        this.diff = diff;
        this.modify("diff", diff);
    }

    /**
     * 设置 [字段]
     */
    public void setField(String field) {
        this.field = field;
        this.modify("field", field);
    }

    /**
     * 设置 [新值]
     */
    public void setIbiznew(String ibiznew) {
        this.ibiznew = ibiznew;
        this.modify("new", ibiznew);
    }

    /**
     * 设置 [旧值]
     */
    public void setOld(String old) {
        this.old = old;
        this.modify("old", old);
    }

    /**
     * 设置 [关联日志]
     */
    public void setAction(Long action) {
        this.action = action;
        this.modify("action", action);
    }

    /**
     * 设置 [操作历史编号]
     */
    public void setHistorysn(Long historysn) {
        this.historysn = historysn;
        this.modify("historysn", historysn);
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


