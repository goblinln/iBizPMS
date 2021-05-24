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
 * 实体[汇报日志]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_action", resultMap = "IbzProReportlyActionResultMap")
@ApiModel("汇报日志")
public class IbzProReportlyAction extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动作
     */
    @TableField(value = "`ACTION`")
    @JSONField(name = "action")
    @JsonProperty("action")
    @ApiModelProperty("动作")
    private String action;
    /**
     * 操作方式
     */
    @TableField(exist = false)
    @JSONField(name = "actionmanner")
    @JsonProperty("actionmanner")
    @ApiModelProperty("操作方式")
    private String actionmanner;
    /**
     * 操作者
     */
    @TableField(value = "`ACTOR`")
    @JSONField(name = "actor")
    @JsonProperty("actor")
    @ApiModelProperty("操作者")
    private String actor;
    /**
     * 系统日志编号
     */
    @TableField(value = "`ACTIONSN`")
    @JSONField(name = "actionsn")
    @JsonProperty("actionsn")
    @ApiModelProperty("系统日志编号")
    private Long actionsn;
    /**
     * 备注
     */
    @TableField(value = "`COMMENT`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
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
     * 显示日期
     */
    @TableField(exist = false)
    @JSONField(name = "date1")
    @JsonProperty("date1")
    @ApiModelProperty("显示日期")
    private String date1;
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
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 文件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("文件")
    private String files;
    /**
     * 附加值
     */
    @TableField(value = "`EXTRA`")
    @JSONField(name = "extra")
    @JsonProperty("extra")
    @ApiModelProperty("附加值")
    private String extra;
    /**
     * 当前用户
     */
    @TableField(exist = false)
    @JSONField(name = "isactorss")
    @JsonProperty("isactorss")
    @ApiModelProperty("当前用户")
    private Long isactorss;
    /**
     * id
     */
    @DEField(isKeyField = true, dupCheck = DupCheck.ALL)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "lastcomment")
    @JsonProperty("lastcomment")
    @ApiModelProperty("备注")
    private String lastcomment;
    /**
     * 上月
     */
    @TableField(exist = false)
    @JSONField(name = "lastmonth")
    @JsonProperty("lastmonth")
    @ApiModelProperty("上月")
    private String lastmonth;
    /**
     * 上周
     */
    @TableField(exist = false)
    @JSONField(name = "lastweek")
    @JsonProperty("lastweek")
    @ApiModelProperty("上周")
    private String lastweek;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 对象类型
     */
    @TableField(value = "`OBJECTTYPE`")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @ApiModelProperty("对象类型")
    private String objecttype;
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
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 产品
     */
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
    private String product;
    /**
     * 本月
     */
    @TableField(exist = false)
    @JSONField(name = "thismonth")
    @JsonProperty("thismonth")
    @ApiModelProperty("本月")
    private String thismonth;
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
     * 昨天
     */
    @TableField(exist = false)
    @JSONField(name = "yesterday")
    @JsonProperty("yesterday")
    @ApiModelProperty("昨天")
    private String yesterday;
    /**
     * 项目
     */
    @TableField(value = "`PROJECT`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private Long project;
    /**
     * 汇报标识
     */
    @TableField(value = "`OBJECTID`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    @ApiModelProperty("汇报标识")
    private Long objectid;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.report.domain.IbzReportly ibzproreportly;



    /**
     * 设置 [动作]
     */
    public void setAction(String action) {
        this.action = action;
        this.modify("action", action);
    }

    /**
     * 设置 [操作者]
     */
    public void setActor(String actor) {
        this.actor = actor;
        this.modify("actor", actor);
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
     * 设置 [附加值]
     */
    public void setExtra(String extra) {
        this.extra = extra;
        this.modify("extra", extra);
    }

    /**
     * 设置 [对象类型]
     */
    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
        this.modify("objecttype", objecttype);
    }

    /**
     * 设置 [产品]
     */
    public void setProduct(String product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [已读]
     */
    public void setRead(String read) {
        this.read = read;
        this.modify("read", read);
    }

    /**
     * 设置 [项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [汇报标识]
     */
    public void setObjectid(Long objectid) {
        this.objectid = objectid;
        this.modify("objectid", objectid);
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


