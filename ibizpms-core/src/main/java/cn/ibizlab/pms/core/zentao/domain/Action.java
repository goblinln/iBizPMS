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
 * 实体[系统日志]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_action", resultMap = "ActionResultMap")
@ApiModel("系统日志")
public class Action extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 本月
     */
    @TableField(exist = false)
    @JSONField(name = "thismonth")
    @JsonProperty("thismonth")
    @ApiModelProperty("本月")
    private String thismonth;
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
     * 昨天
     */
    @TableField(exist = false)
    @JSONField(name = "yesterday")
    @JsonProperty("yesterday")
    @ApiModelProperty("昨天")
    private String yesterday;
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
     * 附加值
     */
    @TableField(value = "`EXTRA`")
    @JSONField(name = "extra")
    @JsonProperty("extra")
    @ApiModelProperty("附加值")
    private String extra;
    /**
     * 文件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("文件")
    private String files;
    /**
     * 系统日志编号
     */
    @TableField(value = "`ACTIONSN`")
    @JSONField(name = "actionsn")
    @JsonProperty("actionsn")
    @ApiModelProperty("系统日志编号")
    private Long actionsn;
    /**
     * 上月
     */
    @TableField(exist = false)
    @JSONField(name = "lastmonth")
    @JsonProperty("lastmonth")
    @ApiModelProperty("上月")
    private String lastmonth;
    /**
     * 当前用户
     */
    @TableField(exist = false)
    @JSONField(name = "isactorss")
    @JsonProperty("isactorss")
    @ApiModelProperty("当前用户")
    private Long isactorss;
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
     * 本周
     */
    @TableField(exist = false)
    @JSONField(name = "thisweek")
    @JsonProperty("thisweek")
    @ApiModelProperty("本周")
    private String thisweek;
    /**
     * 今天
     */
    @TableField(exist = false)
    @JSONField(name = "today")
    @JsonProperty("today")
    @ApiModelProperty("今天")
    private String today;
    /**
     * 显示日期
     */
    @TableField(exist = false)
    @JSONField(name = "date1")
    @JsonProperty("date1")
    @ApiModelProperty("显示日期")
    private String date1;
    /**
     * 对象类型
     */
    @DEField(preType = DEPredefinedFieldType.PARENTTYPE)
    @TableField(value = "`OBJECTTYPE`")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @ApiModelProperty("对象类型")
    private String objecttype;
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
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 备注
     */
    @TableField(value = "`COMMENT`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
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
     * 已读
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`READ`")
    @JSONField(name = "read")
    @JsonProperty("read")
    @ApiModelProperty("已读")
    private String read;
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
     * 动作
     */
    @TableField(value = "`ACTION`")
    @JSONField(name = "action")
    @JsonProperty("action")
    @ApiModelProperty("动作")
    private String action;
    /**
     * 日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`DATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
    /**
     * 产品
     */
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
    private String product;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "lastcomment")
    @JsonProperty("lastcomment")
    @ApiModelProperty("备注")
    private String lastcomment;
    /**
     * 前端键值
     */
    @TableField(exist = false)
    @JSONField(name = "srfkey")
    @JsonProperty("srfkey")
    @ApiModelProperty("前端键值")
    private Long srfkey;
    /**
     * 操作方式
     */
    @TableField(exist = false)
    @JSONField(name = "actionmanner")
    @JsonProperty("actionmanner")
    @ApiModelProperty("操作方式")
    private String actionmanner;
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
     * 上周
     */
    @TableField(exist = false)
    @JSONField(name = "lastweek")
    @JsonProperty("lastweek")
    @ApiModelProperty("上周")
    private String lastweek;
    /**
     * 对象ID
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.PARENTID)
    @TableField(value = "`OBJECTID`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    @ApiModelProperty("对象ID")
    private Long objectid;
    /**
     * 操作者
     */
    @TableField(value = "`ACTOR`")
    @JSONField(name = "actor")
    @JsonProperty("actor")
    @ApiModelProperty("操作者")
    private String actor;
    /**
     * 项目
     */
    @TableField(value = "`PROJECT`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private Long project;

    /**
     * 项目相关操作
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ztProject;


    /**
     * 操作历史
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.zentao.domain.History> historys;


    /**
     * 设置 [附加值]
     */
    public void setExtra(String extra) {
        this.extra = extra;
        this.modify("extra", extra);
    }

    /**
     * 设置 [系统日志编号]
     */
    public void setActionsn(Long actionsn) {
        this.actionsn = actionsn;
        this.modify("actionsn", actionsn);
    }

    /**
     * 设置 [备注]
     */
    public void setComment(String comment) {
        this.comment = comment;
        this.modify("comment", comment);
    }

    /**
     * 设置 [已读]
     */
    public void setRead(String read) {
        this.read = read;
        this.modify("read", read);
    }

    /**
     * 设置 [动作]
     */
    public void setAction(String action) {
        this.action = action;
        this.modify("action", action);
    }

    /**
     * 设置 [产品]
     */
    public void setProduct(String product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [操作者]
     */
    public void setActor(String actor) {
        this.actor = actor;
        this.modify("actor", actor);
    }

    /**
     * 设置 [项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
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


