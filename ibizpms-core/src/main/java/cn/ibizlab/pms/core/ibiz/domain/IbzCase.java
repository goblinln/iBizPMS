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
 * 实体[测试用例]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_case", resultMap = "IbzCaseResultMap")
@ApiModel("测试用例")
public class IbzCase extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 最后修改者
     */
    @DEField(dict = "UserRealName")
    @TableField(value = "`LASTEDITEDBY`")
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @ApiModelProperty("最后修改者")
    private String lasteditedby;
    /**
     * path
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private Integer path;
    /**
     * 用例编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("用例编号")
    private Long id;
    /**
     * scriptedBy
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SCRIPTEDBY`")
    @JSONField(name = "scriptedby")
    @JsonProperty("scriptedby")
    @ApiModelProperty("scriptedBy")
    private String scriptedby;
    /**
     * 用例类型
     */
    @DEField(defaultValue = "feature", dict = "Testcase__type")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("用例类型")
    private String type;
    /**
     * scriptStatus
     */
    @TableField(value = "`SCRIPTSTATUS`")
    @JSONField(name = "scriptstatus")
    @JsonProperty("scriptstatus")
    @ApiModelProperty("scriptStatus")
    private String scriptstatus;
    /**
     * 适用阶段
     */
    @DEField(defaultValue = "#EMPTY", dict = "Testcase__stage")
    @TableField(value = "`STAGE`")
    @JSONField(name = "stage")
    @JsonProperty("stage")
    @ApiModelProperty("适用阶段")
    private String stage;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`OPENEDDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建日期")
    private Timestamp openeddate;
    /**
     * 修改日期
     */
    @TableField(value = "`LASTEDITEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastediteddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("修改日期")
    private Timestamp lastediteddate;
    /**
     * auto
     */
    @DEField(defaultValue = "no")
    @TableField(value = "`AUTO`")
    @JSONField(name = "auto")
    @JsonProperty("auto")
    @ApiModelProperty("auto")
    private String auto;
    /**
     * 用例标题
     */
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("用例标题")
    private String title;
    /**
     * howRun
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`HOWRUN`")
    @JSONField(name = "howrun")
    @JsonProperty("howrun")
    @ApiModelProperty("howRun")
    private String howrun;
    /**
     * 优先级
     */
    @DEField(defaultValue = "3", dict = "Testcase__pri")
    @TableField(value = "`PRI`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private String pri;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 关键词
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`KEYWORDS`")
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @ApiModelProperty("关键词")
    private String keywords;
    /**
     * scriptLocation
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SCRIPTLOCATION`")
    @JSONField(name = "scriptlocation")
    @JsonProperty("scriptlocation")
    @ApiModelProperty("scriptLocation")
    private String scriptlocation;
    /**
     * 用例版本
     */
    @DEField(defaultValue = "0", dict = "CurCaseVersion")
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;
    /**
     * 状态
     */
    @DEField(defaultValue = "wait", dict = "Testcase__status")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("状态")
    private String status;
    /**
     * 前置条件
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`PRECONDITION`")
    @JSONField(name = "precondition")
    @JsonProperty("precondition")
    @ApiModelProperty("前置条件")
    private String precondition;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID)
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME, dict = "UserRealName")
    @TableField(value = "`OPENEDBY`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("由谁创建")
    private String openedby;
    /**
     * scriptedDate
     */
    @TableField(value = "`SCRIPTEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "scripteddate", format = "yyyy-MM-dd")
    @JsonProperty("scripteddate")
    @ApiModelProperty("scriptedDate")
    private Timestamp scripteddate;
    /**
     * 用例库
     */
    @TableField(exist = false)
    @JSONField(name = "libname")
    @JsonProperty("libname")
    @ApiModelProperty("用例库")
    private String libname;
    /**
     * 所属模块
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("所属模块")
    private String modulename;
    /**
     * id
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`MODULE`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("id")
    private Long module;
    /**
     * 编号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`LIB`")
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @ApiModelProperty("编号")
    private Long lib;

    /**
     * 模块
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.IbzLibModule libmodule;

    /**
     * 用例库
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.IbzLib caselib;


    /**
     * 用例库用例步骤
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps> ibzlibcasestepss;


    /**
     * 设置 [最后修改者]
     */
    public void setLasteditedby(String lasteditedby) {
        this.lasteditedby = lasteditedby;
        this.modify("lasteditedby", lasteditedby);
    }

    /**
     * 设置 [path]
     */
    public void setPath(Integer path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [scriptedBy]
     */
    public void setScriptedby(String scriptedby) {
        this.scriptedby = scriptedby;
        this.modify("scriptedby", scriptedby);
    }

    /**
     * 设置 [用例类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [scriptStatus]
     */
    public void setScriptstatus(String scriptstatus) {
        this.scriptstatus = scriptstatus;
        this.modify("scriptstatus", scriptstatus);
    }

    /**
     * 设置 [适用阶段]
     */
    public void setStage(String stage) {
        this.stage = stage;
        this.modify("stage", stage);
    }

    /**
     * 设置 [修改日期]
     */
    public void setLastediteddate(Timestamp lastediteddate) {
        this.lastediteddate = lastediteddate;
        this.modify("lastediteddate", lastediteddate);
    }

    /**
     * 格式化日期 [修改日期]
     */
    public String formatLastediteddate() {
        if (this.lastediteddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastediteddate);
    }
    /**
     * 设置 [auto]
     */
    public void setAuto(String auto) {
        this.auto = auto;
        this.modify("auto", auto);
    }

    /**
     * 设置 [用例标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [howRun]
     */
    public void setHowrun(String howrun) {
        this.howrun = howrun;
        this.modify("howrun", howrun);
    }

    /**
     * 设置 [优先级]
     */
    public void setPri(String pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [关键词]
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
        this.modify("keywords", keywords);
    }

    /**
     * 设置 [scriptLocation]
     */
    public void setScriptlocation(String scriptlocation) {
        this.scriptlocation = scriptlocation;
        this.modify("scriptlocation", scriptlocation);
    }

    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [前置条件]
     */
    public void setPrecondition(String precondition) {
        this.precondition = precondition;
        this.modify("precondition", precondition);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [scriptedDate]
     */
    public void setScripteddate(Timestamp scripteddate) {
        this.scripteddate = scripteddate;
        this.modify("scripteddate", scripteddate);
    }

    /**
     * 格式化日期 [scriptedDate]
     */
    public String formatScripteddate() {
        if (this.scripteddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(scripteddate);
    }
    /**
     * 设置 [id]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
    }

    /**
     * 设置 [编号]
     */
    public void setLib(Long lib) {
        this.lib = lib;
        this.modify("lib", lib);
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


