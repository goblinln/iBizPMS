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
 * 实体[用例步骤]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_casestep", resultMap = "CaseStepResultMap")
@ApiModel("用例步骤")
public class CaseStep extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用例步骤编号
     */
    @TableField(exist = false)
    @JSONField(name = "casestepid")
    @JsonProperty("casestepid")
    @ApiModelProperty("用例步骤编号")
    private Long casestepid;
    /**
     * 实际情况
     */
    @TableField(exist = false)
    @JSONField(name = "reals")
    @JsonProperty("reals")
    @ApiModelProperty("实际情况")
    private String reals;
    /**
     * 测试结果
     */
    @TableField(exist = false)
    @JSONField(name = "steps")
    @JsonProperty("steps")
    @ApiModelProperty("测试结果")
    private String steps;
    /**
     * 用例步骤类型
     */
    @DEField(defaultValue = "step")
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("用例步骤类型")
    private String type;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 步骤
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "desc")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("步骤")
    private String desc;
    /**
     * 预期
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "expect")
    @JSONField(name = "expect")
    @JsonProperty("expect")
    @ApiModelProperty("预期")
    private String expect;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 执行编号
     */
    @TableField(exist = false)
    @JSONField(name = "runid")
    @JsonProperty("runid")
    @ApiModelProperty("执行编号")
    private Integer runid;
    /**
     * 用例版本
     */
    @DEField(defaultValue = "0")
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;
    /**
     * 用例
     */
    @DEField(defaultValue = "0")
    @TableField(value = "case")
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    @ApiModelProperty("用例")
    private Long ibizcase;
    /**
     * 分组用例步骤的组编号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "parent")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("分组用例步骤的组编号")
    private Long parent;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "createby")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "updateby")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "org")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "dept")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Case ztcase;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.CaseStep ibizparent;


    /**
     * 用例步骤
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.zentao.domain.CaseStep> casestep;


    /**
     * 设置 [用例步骤类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [步骤]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [预期]
     */
    public void setExpect(String expect) {
        this.expect = expect;
        this.modify("expect", expect);
    }

    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [用例]
     */
    public void setIbizcase(Long ibizcase) {
        this.ibizcase = ibizcase;
        this.modify("case", ibizcase);
    }

    /**
     * 设置 [分组用例步骤的组编号]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
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


