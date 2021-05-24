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
 * 服务DTO对象[ProjectDTO]
 */
@Data
@ApiModel("项目")
//@JsonFilter(value = "dtofieldfilter")
public class ProjectDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [OPENEDVERSION]
     *
     */
    @JSONField(name = "openedversion")
    @JsonProperty("openedversion")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("当前系统版本")
    private String openedversion;

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
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @NotNull(message = "[开始时间]不允许为空!")
    @ApiModelProperty("开始时间")
    private Timestamp begin;

    /**
     * 属性 [HOURS]
     *
     */
    @JSONField(name = "hours")
    @JsonProperty("hours")
    @ApiModelProperty("可用工时/天")
    private BigDecimal hours;

    /**
     * 属性 [PMSEEPROJECTINFO]
     *
     */
    @JSONField(name = "pmseeprojectinfo")
    @JsonProperty("pmseeprojectinfo")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("项目立项信息")
    private String pmseeprojectinfo;

    /**
     * 属性 [ACL]
     *
     */
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    @ApiModelProperty("访问控制")
    private String acl;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [TOTALCONSUMED]
     *
     */
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    @ApiModelProperty("任务消耗总工时")
    private Double totalconsumed;

    /**
     * 属性 [PRODUCTS]
     *
     */
    @JSONField(name = "products")
    @JsonProperty("products")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("关联产品")
    private String products;

    /**
     * 属性 [YCOMPLETETASKCNT]
     *
     */
    @JSONField(name = "ycompletetaskcnt")
    @JsonProperty("ycompletetaskcnt")
    @ApiModelProperty("已完成任务数")
    private Integer ycompletetaskcnt;

    /**
     * 属性 [PLANS]
     *
     */
    @JSONField(name = "plans")
    @JsonProperty("plans")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("关联计划")
    private String plans;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("项目描述")
    private String desc;

    /**
     * 属性 [TEMPTASKCNT]
     *
     */
    @JSONField(name = "temptaskcnt")
    @JsonProperty("temptaskcnt")
    @ApiModelProperty("临时任务数")
    private Integer temptaskcnt;

    /**
     * 属性 [DOCLIBCNT]
     *
     */
    @JSONField(name = "doclibcnt")
    @JsonProperty("doclibcnt")
    @ApiModelProperty("文档数量")
    private Integer doclibcnt;

    /**
     * 属性 [MYCOMPLETETASKCNT]
     *
     */
    @JSONField(name = "mycompletetaskcnt")
    @JsonProperty("mycompletetaskcnt")
    @ApiModelProperty("我完成任务数")
    private Integer mycompletetaskcnt;

    /**
     * 属性 [ISTOP]
     *
     */
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;

    /**
     * 属性 [UNCOMPLETETASKCNT]
     *
     */
    @JSONField(name = "uncompletetaskcnt")
    @JsonProperty("uncompletetaskcnt")
    @ApiModelProperty("未完成任务数")
    private Integer uncompletetaskcnt;

    /**
     * 属性 [MDEPTNAME]
     *
     */
    @JSONField(name = "mdeptname")
    @JsonProperty("mdeptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String mdeptname;

    /**
     * 属性 [TEAMCNT]
     *
     */
    @JSONField(name = "teamcnt")
    @JsonProperty("teamcnt")
    @ApiModelProperty("团队成员总数")
    private Integer teamcnt;

    /**
     * 属性 [PM]
     *
     */
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("项目负责人")
    private String pm;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("选择部门")
    private String dept;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long id;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[项目名称]不允许为空!")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("项目名称")
    private String name;

    /**
     * 属性 [SUBSTATUS]
     *
     */
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("子状态")
    private String substatus;

    /**
     * 属性 [ROLE]
     *
     */
    @JSONField(name = "role")
    @JsonProperty("role")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("角色")
    private String role;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("项目排序")
    private Integer order;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug总数")
    private Integer bugcnt;

    /**
     * 属性 [RD]
     *
     */
    @JSONField(name = "rd")
    @JsonProperty("rd")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("发布负责人")
    private String rd;

    /**
     * 属性 [MANAGEMEMBERS]
     *
     */
    @JSONField(name = "managemembers")
    @JsonProperty("managemembers")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("复制团队")
    private String managemembers;

    /**
     * 属性 [YSTARTASKCNT]
     *
     */
    @JSONField(name = "ystarttaskcnt")
    @JsonProperty("ystarttaskcnt")
    @ApiModelProperty("进行中任务数")
    private Integer ystarttaskcnt;

    /**
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [WHITELIST]
     *
     */
    @JSONField(name = "whitelist")
    @JsonProperty("whitelist")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("分组白名单")
    private String whitelist;

    /**
     * 属性 [MOBIMAGE]
     *
     */
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    @Size(min = 0, max = 4000, message = "内容长度必须小于等于[4000]")
    @ApiModelProperty("移动端图片")
    private String mobimage;

    /**
     * 属性 [TOTALWH]
     *
     */
    @JSONField(name = "totalwh")
    @JsonProperty("totalwh")
    @ApiModelProperty("总工时")
    private Integer totalwh;

    /**
     * 属性 [TOTALHOURS]
     *
     */
    @JSONField(name = "totalhours")
    @JsonProperty("totalhours")
    @ApiModelProperty("可用工时")
    private BigDecimal totalhours;

    /**
     * 属性 [PRI]
     *
     */
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("优先级")
    private String pri;

    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @NotNull(message = "[结束日期]不允许为空!")
    @ApiModelProperty("结束日期")
    private Timestamp end;

    /**
     * 属性 [CANCELEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "canceleddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("canceleddate")
    @ApiModelProperty("取消日期")
    private Timestamp canceleddate;

    /**
     * 属性 [PLANTASKCNT]
     *
     */
    @JSONField(name = "plantaskcnt")
    @JsonProperty("plantaskcnt")
    @ApiModelProperty("计划任务数")
    private Integer plantaskcnt;

    /**
     * 属性 [JOIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd")
    @JsonProperty("join")
    @ApiModelProperty("加盟日")
    private Timestamp join;

    /**
     * 属性 [TOTALESTIMATE]
     *
     */
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    @ApiModelProperty("任务最初预计总工时")
    private Double totalestimate;

    /**
     * 属性 [MDEPTID]
     *
     */
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("部门标识")
    private String mdeptid;

    /**
     * 属性 [TOTALLEFT]
     *
     */
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    @ApiModelProperty("任务预计剩余总工时")
    private Double totalleft;

    /**
     * 属性 [SRFARRAY]
     *
     */
    @JSONField(name = "srfarray")
    @JsonProperty("srfarray")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("关联数据数组")
    private String srfarray;

    /**
     * 属性 [CODE]
     *
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    @Size(min = 0, max = 45, message = "内容长度必须小于等于[45]")
    @ApiModelProperty("项目代号")
    private String code;

    /**
     * 属性 [PERIOD]
     *
     */
    @JSONField(name = "period")
    @JsonProperty("period")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("时间段")
    private String period;

    /**
     * 属性 [CLOSETASKCNT]
     *
     */
    @JSONField(name = "closetaskcnt")
    @JsonProperty("closetaskcnt")
    @ApiModelProperty("关闭任务数")
    private Integer closetaskcnt;

    /**
     * 属性 [BRANCHS]
     *
     */
    @JSONField(name = "branchs")
    @JsonProperty("branchs")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("关联产品平台集合")
    private String branchs;

    /**
     * 属性 [CATID]
     *
     */
    @JSONField(name = "catid")
    @JsonProperty("catid")
    @ApiModelProperty("catID")
    private Integer catid;

    /**
     * 属性 [ORGID]
     *
     */
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("组织标识")
    private String orgid;

    /**
     * 属性 [UNSTARTTASKCNT]
     *
     */
    @JSONField(name = "unstarttaskcnt")
    @JsonProperty("unstarttaskcnt")
    @ApiModelProperty("未开始任务数")
    private Integer unstarttaskcnt;

    /**
     * 属性 [ACCOUNTS]
     *
     */
    @JSONField(name = "accounts")
    @JsonProperty("accounts")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目团队相关成员")
    private String accounts;

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
     * 属性 [STATGE]
     *
     */
    @JSONField(name = "statge")
    @JsonProperty("statge")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("statge")
    private String statge;

    /**
     * 属性 [CANCELTASKCNT]
     *
     */
    @JSONField(name = "canceltaskcnt")
    @JsonProperty("canceltaskcnt")
    @ApiModelProperty("取消任务数")
    private Integer canceltaskcnt;

    /**
     * 属性 [TASKCNT]
     *
     */
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务总数")
    private Integer taskcnt;

    /**
     * 属性 [ALLTASKCNT]
     *
     */
    @JSONField(name = "alltaskcnt")
    @JsonProperty("alltaskcnt")
    @ApiModelProperty("所有任务数")
    private Integer alltaskcnt;

    /**
     * 属性 [SUPPROREPORT]
     *
     */
    @JSONField(name = "supproreport")
    @JsonProperty("supproreport")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("支持项目汇报")
    private String supproreport;

    /**
     * 属性 [CANCELEDBY]
     *
     */
    @JSONField(name = "canceledby")
    @JsonProperty("canceledby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁取消")
    private String canceledby;

    /**
     * 属性 [ISCAT]
     *
     */
    @JSONField(name = "iscat")
    @JsonProperty("iscat")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("isCat")
    private String iscat;

    /**
     * 属性 [OPENEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "openeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建日期")
    private Timestamp openeddate;

    /**
     * 属性 [UNCLOSETASKCNT]
     *
     */
    @JSONField(name = "unclosetaskcnt")
    @JsonProperty("unclosetaskcnt")
    @ApiModelProperty("未关闭任务数")
    private Integer unclosetaskcnt;

    /**
     * 属性 [STORYCHANGECNT]
     *
     */
    @JSONField(name = "storychangecnt")
    @JsonProperty("storychangecnt")
    @ApiModelProperty("需求变更数")
    private Integer storychangecnt;

    /**
     * 属性 [CLOSEDBY]
     *
     */
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁关闭")
    private String closedby;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("项目类型")
    private String type;

    /**
     * 属性 [BUILDCNT]
     *
     */
    @JSONField(name = "buildcnt")
    @JsonProperty("buildcnt")
    @ApiModelProperty("版本总数")
    private Integer buildcnt;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("项目团队成员")
    private String account;

    /**
     * 属性 [PO]
     *
     */
    @JSONField(name = "po")
    @JsonProperty("po")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("产品负责人")
    private String po;

    /**
     * 属性 [ASSTOMYTASKCNT]
     *
     */
    @JSONField(name = "asstomytaskcnt")
    @JsonProperty("asstomytaskcnt")
    @ApiModelProperty("指派给我任务数")
    private Integer asstomytaskcnt;

    /**
     * 属性 [ORDER1]
     *
     */
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("项目排序")
    private Integer order1;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("项目状态")
    private String status;

    /**
     * 属性 [MORETASKCNT]
     *
     */
    @JSONField(name = "moretaskcnt")
    @JsonProperty("moretaskcnt")
    @ApiModelProperty("更多任务数")
    private Integer moretaskcnt;

    /**
     * 属性 [DAYS]
     *
     */
    @JSONField(name = "days")
    @JsonProperty("days")
    @ApiModelProperty("可用工作日")
    private Integer days;

    /**
     * 属性 [CYCLETASKCNT]
     *
     */
    @JSONField(name = "cycletaskcnt")
    @JsonProperty("cycletaskcnt")
    @ApiModelProperty("周期任务数")
    private Integer cycletaskcnt;

    /**
     * 属性 [TEAM]
     *
     */
    @JSONField(name = "team")
    @JsonProperty("team")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("团队名称")
    private String team;

    /**
     * 属性 [CLOSEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "closeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭日期")
    private Timestamp closeddate;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [OPENEDBY]
     *
     */
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁创建")
    private String openedby;

    /**
     * 属性 [STORYCNT]
     *
     */
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求总数")
    private Integer storycnt;

    /**
     * 属性 [QD]
     *
     */
    @JSONField(name = "qd")
    @JsonProperty("qd")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("测试负责人")
    private String qd;

    /**
     * 属性 [PARENTNAME]
     *
     */
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("parent")
    private String parentname;

    /**
     * 属性 [PARENT]
     *
     */
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("父项目")
    private Long parent;

    /**
     * 属性 [PROJECTSN]
     *
     */
    @JSONField(name = "projectsn")
    @JsonProperty("projectsn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long projectsn;


    /**
     * 设置 [OPENEDVERSION]
     */
    public void setOpenedversion(String  openedversion){
        this.openedversion = openedversion ;
        this.modify("openedversion",openedversion);
    }

    /**
     * 设置 [BEGIN]
     */
    public void setBegin(Timestamp  begin){
        this.begin = begin ;
        this.modify("begin",begin);
    }

    /**
     * 设置 [PMSEEPROJECTINFO]
     */
    public void setPmseeprojectinfo(String  pmseeprojectinfo){
        this.pmseeprojectinfo = pmseeprojectinfo ;
        this.modify("pmseeprojectinfo",pmseeprojectinfo);
    }

    /**
     * 设置 [ACL]
     */
    public void setAcl(String  acl){
        this.acl = acl ;
        this.modify("acl",acl);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [PM]
     */
    public void setPm(String  pm){
        this.pm = pm ;
        this.modify("pm",pm);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [SUBSTATUS]
     */
    public void setSubstatus(String  substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(Integer  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [RD]
     */
    public void setRd(String  rd){
        this.rd = rd ;
        this.modify("rd",rd);
    }

    /**
     * 设置 [WHITELIST]
     */
    public void setWhitelist(String  whitelist){
        this.whitelist = whitelist ;
        this.modify("whitelist",whitelist);
    }

    /**
     * 设置 [PRI]
     */
    public void setPri(String  pri){
        this.pri = pri ;
        this.modify("pri",pri);
    }

    /**
     * 设置 [END]
     */
    public void setEnd(Timestamp  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [CANCELEDDATE]
     */
    public void setCanceleddate(Timestamp  canceleddate){
        this.canceleddate = canceleddate ;
        this.modify("canceleddate",canceleddate);
    }

    /**
     * 设置 [CODE]
     */
    public void setCode(String  code){
        this.code = code ;
        this.modify("code",code);
    }

    /**
     * 设置 [CATID]
     */
    public void setCatid(Integer  catid){
        this.catid = catid ;
        this.modify("catid",catid);
    }

    /**
     * 设置 [STATGE]
     */
    public void setStatge(String  statge){
        this.statge = statge ;
        this.modify("statge",statge);
    }

    /**
     * 设置 [SUPPROREPORT]
     */
    public void setSupproreport(String  supproreport){
        this.supproreport = supproreport ;
        this.modify("supproreport",supproreport);
    }

    /**
     * 设置 [CANCELEDBY]
     */
    public void setCanceledby(String  canceledby){
        this.canceledby = canceledby ;
        this.modify("canceledby",canceledby);
    }

    /**
     * 设置 [ISCAT]
     */
    public void setIscat(String  iscat){
        this.iscat = iscat ;
        this.modify("iscat",iscat);
    }

    /**
     * 设置 [CLOSEDBY]
     */
    public void setClosedby(String  closedby){
        this.closedby = closedby ;
        this.modify("closedby",closedby);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [PO]
     */
    public void setPo(String  po){
        this.po = po ;
        this.modify("po",po);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [DAYS]
     */
    public void setDays(Integer  days){
        this.days = days ;
        this.modify("days",days);
    }

    /**
     * 设置 [TEAM]
     */
    public void setTeam(String  team){
        this.team = team ;
        this.modify("team",team);
    }

    /**
     * 设置 [CLOSEDDATE]
     */
    public void setCloseddate(Timestamp  closeddate){
        this.closeddate = closeddate ;
        this.modify("closeddate",closeddate);
    }

    /**
     * 设置 [QD]
     */
    public void setQd(String  qd){
        this.qd = qd ;
        this.modify("qd",qd);
    }

    /**
     * 设置 [PARENT]
     */
    public void setParent(Long  parent){
        this.parent = parent ;
        this.modify("parent",parent);
    }

    /**
     * 设置 [PROJECTSN]
     */
    public void setProjectsn(Long  projectsn){
        this.projectsn = projectsn ;
        this.modify("projectsn",projectsn);
    }


    /**
     *  [IBZ_PROJECTTEAMNEST]
     */
    @JsonProperty("ibzprojectteams")
    @JSONField(name = "ibzprojectteams")
	private List<IBZPROJECTTEAMDTO> projectteam ;

}


