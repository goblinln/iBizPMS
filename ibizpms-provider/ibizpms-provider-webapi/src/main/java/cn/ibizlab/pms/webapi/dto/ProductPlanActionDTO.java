package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[ProductPlanActionDTO]
 */
@Data
@ApiModel("产品计划日志")
//@JsonFilter(value = "dtofieldfilter")
public class ProductPlanActionDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [THISMONTH]
     *
     */
    @JSONField(name = "thismonth")
    @JsonProperty("thismonth")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("本月")
    private String thismonth;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [YESTERDAY]
     *
     */
    @JSONField(name = "yesterday")
    @JsonProperty("yesterday")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("昨天")
    private String yesterday;

    /**
     * 属性 [EXTRA]
     *
     */
    @JSONField(name = "extra")
    @JsonProperty("extra")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("附加值")
    private String extra;

    /**
     * 属性 [ACTIONSN]
     *
     */
    @JSONField(name = "actionsn")
    @JsonProperty("actionsn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统日志编号")
    private Long actionsn;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("文件")
    private String files;

    /**
     * 属性 [ISACTORSS]
     *
     */
    @JSONField(name = "isactorss")
    @JsonProperty("isactorss")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("当前用户")
    private Long isactorss;

    /**
     * 属性 [LASTMONTH]
     *
     */
    @JSONField(name = "lastmonth")
    @JsonProperty("lastmonth")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("上月")
    private String lastmonth;

    /**
     * 属性 [THISWEEK]
     *
     */
    @JSONField(name = "thisweek")
    @JsonProperty("thisweek")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("本周")
    private String thisweek;

    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;

    /**
     * 属性 [DATE1]
     *
     */
    @JSONField(name = "date1")
    @JsonProperty("date1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("显示日期")
    private String date1;

    /**
     * 属性 [TODAY]
     *
     */
    @JSONField(name = "today")
    @JsonProperty("today")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("今天")
    private String today;

    /**
     * 属性 [OBJECTTYPE]
     *
     */
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("对象类型")
    private String objecttype;

    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [NOTICEUSERS]
     *
     */
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;

    /**
     * 属性 [READ]
     *
     */
    @JSONField(name = "read")
    @JsonProperty("read")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已读")
    private String read;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;

    /**
     * 属性 [ACTION]
     *
     */
    @JSONField(name = "action")
    @JsonProperty("action")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("动作")
    private String action;

    /**
     * 属性 [LASTCOMMENT]
     *
     */
    @JSONField(name = "lastcomment")
    @JsonProperty("lastcomment")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("备注")
    private String lastcomment;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("产品")
    private String product;

    /**
     * 属性 [ACTIONMANNER]
     *
     */
    @JSONField(name = "actionmanner")
    @JsonProperty("actionmanner")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("操作方式")
    private String actionmanner;

    /**
     * 属性 [SRFKEY]
     *
     */
    @JSONField(name = "srfkey")
    @JsonProperty("srfkey")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("前端键值")
    private Long srfkey;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [LASTWEEK]
     *
     */
    @JSONField(name = "lastweek")
    @JsonProperty("lastweek")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("上周")
    private String lastweek;

    /**
     * 属性 [ACTOR]
     *
     */
    @JSONField(name = "actor")
    @JsonProperty("actor")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("操作者")
    private String actor;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属项目")
    private Long project;

    /**
     * 属性 [OBJECTID]
     *
     */
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long objectid;


    /**
     * 设置 [EXTRA]
     */
    public void setExtra(String  extra){
        this.extra = extra ;
        this.modify("extra",extra);
    }

    /**
     * 设置 [ACTIONSN]
     */
    public void setActionsn(Long  actionsn){
        this.actionsn = actionsn ;
        this.modify("actionsn",actionsn);
    }

    /**
     * 设置 [OBJECTTYPE]
     */
    public void setObjecttype(String  objecttype){
        this.objecttype = objecttype ;
        this.modify("objecttype",objecttype);
    }

    /**
     * 设置 [COMMENT]
     */
    public void setComment(String  comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }

    /**
     * 设置 [READ]
     */
    public void setRead(String  read){
        this.read = read ;
        this.modify("read",read);
    }

    /**
     * 设置 [ACTION]
     */
    public void setAction(String  action){
        this.action = action ;
        this.modify("action",action);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(String  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [ACTOR]
     */
    public void setActor(String  actor){
        this.actor = actor ;
        this.modify("actor",actor);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }

    /**
     * 设置 [OBJECTID]
     */
    public void setObjectid(Long  objectid){
        this.objectid = objectid ;
        this.modify("objectid",objectid);
    }


}


