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
 * 实体[项目]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_project", resultMap = "ProjectResultMap")
@ApiModel("项目")
public class Project extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前系统版本
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`OPENEDVERSION`")
    @JSONField(name = "openedversion")
    @JsonProperty("openedversion")
    @ApiModelProperty("当前系统版本")
    private String openedversion;
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
     * 开始时间
     */
    @TableField(value = "`BEGIN`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始时间")
    private Timestamp begin;
    /**
     * 可用工时/天
     */
    @TableField(exist = false)
    @JSONField(name = "hours")
    @JsonProperty("hours")
    @ApiModelProperty("可用工时/天")
    private BigDecimal hours;
    /**
     * 项目立项信息
     */
    @TableField(value = "`PMSEEPROJECTINFO`")
    @JSONField(name = "pmseeprojectinfo")
    @JsonProperty("pmseeprojectinfo")
    @ApiModelProperty("项目立项信息")
    private String pmseeprojectinfo;
    /**
     * 访问控制
     */
    @DEField(defaultValue = "open")
    @TableField(value = "`ACL`")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("访问控制")
    private String acl;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 任务消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    @ApiModelProperty("任务消耗总工时")
    private Double totalconsumed;
    /**
     * 关联产品
     */
    @TableField(exist = false)
    @JSONField(name = "products")
    @JsonProperty("products")
    @ApiModelProperty("关联产品")
    private String products;
    /**
     * 已完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "ycompletetaskcnt")
    @JsonProperty("ycompletetaskcnt")
    @ApiModelProperty("已完成任务数")
    private Integer ycompletetaskcnt;
    /**
     * 关联计划
     */
    @TableField(exist = false)
    @JSONField(name = "plans")
    @JsonProperty("plans")
    @ApiModelProperty("关联计划")
    private String plans;
    /**
     * 项目描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("项目描述")
    private String desc;
    /**
     * 临时任务数
     */
    @TableField(exist = false)
    @JSONField(name = "temptaskcnt")
    @JsonProperty("temptaskcnt")
    @ApiModelProperty("临时任务数")
    private Integer temptaskcnt;
    /**
     * 文档数量
     */
    @TableField(exist = false)
    @JSONField(name = "doclibcnt")
    @JsonProperty("doclibcnt")
    @ApiModelProperty("文档数量")
    private Integer doclibcnt;
    /**
     * 我完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "mycompletetaskcnt")
    @JsonProperty("mycompletetaskcnt")
    @ApiModelProperty("我完成任务数")
    private Integer mycompletetaskcnt;
    /**
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;
    /**
     * 未完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "uncompletetaskcnt")
    @JsonProperty("uncompletetaskcnt")
    @ApiModelProperty("未完成任务数")
    private Integer uncompletetaskcnt;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`MDEPTNAME`")
    @JSONField(name = "mdeptname")
    @JsonProperty("mdeptname")
    @ApiModelProperty("归属部门名")
    private String mdeptname;
    /**
     * 团队成员总数
     */
    @TableField(exist = false)
    @JSONField(name = "teamcnt")
    @JsonProperty("teamcnt")
    @ApiModelProperty("团队成员总数")
    private Integer teamcnt;
    /**
     * 项目负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`PM`")
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @ApiModelProperty("项目负责人")
    private String pm;
    /**
     * 选择部门
     */
    @TableField(exist = false)
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("选择部门")
    private String dept;
    /**
     * 项目编号
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("项目编号")
    private Long id;
    /**
     * 项目名称
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("项目名称")
    private String name;
    /**
     * 子状态
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SUBSTATUS`")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @ApiModelProperty("子状态")
    private String substatus;
    /**
     * 角色
     */
    @TableField(exist = false)
    @JSONField(name = "role")
    @JsonProperty("role")
    @ApiModelProperty("角色")
    private String role;
    /**
     * 项目排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("项目排序")
    private Integer order;
    /**
     * Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug总数")
    private Integer bugcnt;
    /**
     * 发布负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`RD`")
    @JSONField(name = "rd")
    @JsonProperty("rd")
    @ApiModelProperty("发布负责人")
    private String rd;
    /**
     * 复制团队
     */
    @TableField(exist = false)
    @JSONField(name = "managemembers")
    @JsonProperty("managemembers")
    @ApiModelProperty("复制团队")
    private String managemembers;
    /**
     * 进行中任务数
     */
    @TableField(exist = false)
    @JSONField(name = "ystarttaskcnt")
    @JsonProperty("ystarttaskcnt")
    @ApiModelProperty("进行中任务数")
    private Integer ystarttaskcnt;
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
     * 分组白名单
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`WHITELIST`")
    @JSONField(name = "whitelist")
    @JsonProperty("whitelist")
    @ApiModelProperty("分组白名单")
    private String whitelist;
    /**
     * 移动端图片
     */
    @TableField(exist = false)
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    @ApiModelProperty("移动端图片")
    private String mobimage;
    /**
     * 总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalwh")
    @JsonProperty("totalwh")
    @ApiModelProperty("总工时")
    private Integer totalwh;
    /**
     * 可用工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalhours")
    @JsonProperty("totalhours")
    @ApiModelProperty("可用工时")
    private BigDecimal totalhours;
    /**
     * 优先级
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`PRI`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private String pri;
    /**
     * 结束日期
     */
    @TableField(value = "`END`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束日期")
    private Timestamp end;
    /**
     * 取消日期
     */
    @TableField(value = "`CANCELEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "canceleddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("canceleddate")
    @ApiModelProperty("取消日期")
    private Timestamp canceleddate;
    /**
     * 计划任务数
     */
    @TableField(exist = false)
    @JSONField(name = "plantaskcnt")
    @JsonProperty("plantaskcnt")
    @ApiModelProperty("计划任务数")
    private Integer plantaskcnt;
    /**
     * 加盟日
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "join", format = "yyyy-MM-dd")
    @JsonProperty("join")
    @ApiModelProperty("加盟日")
    private Timestamp join;
    /**
     * 任务最初预计总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    @ApiModelProperty("任务最初预计总工时")
    private Double totalestimate;
    /**
     * 部门标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`MDEPTID`")
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @ApiModelProperty("部门标识")
    private String mdeptid;
    /**
     * 任务预计剩余总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    @ApiModelProperty("任务预计剩余总工时")
    private Double totalleft;
    /**
     * 关联数据数组
     */
    @TableField(exist = false)
    @JSONField(name = "srfarray")
    @JsonProperty("srfarray")
    @ApiModelProperty("关联数据数组")
    private String srfarray;
    /**
     * 项目代号
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CODE`")
    @JSONField(name = "code")
    @JsonProperty("code")
    @ApiModelProperty("项目代号")
    private String code;
    /**
     * 时间段
     */
    @TableField(exist = false)
    @JSONField(name = "period")
    @JsonProperty("period")
    @ApiModelProperty("时间段")
    private String period;
    /**
     * 关闭任务数
     */
    @TableField(exist = false)
    @JSONField(name = "closetaskcnt")
    @JsonProperty("closetaskcnt")
    @ApiModelProperty("关闭任务数")
    private Integer closetaskcnt;
    /**
     * 关联产品平台集合
     */
    @TableField(exist = false)
    @JSONField(name = "branchs")
    @JsonProperty("branchs")
    @ApiModelProperty("关联产品平台集合")
    private String branchs;
    /**
     * catID
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`CATID`")
    @JSONField(name = "catid")
    @JsonProperty("catid")
    @ApiModelProperty("catID")
    private Integer catid;
    /**
     * 组织标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORGID`")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织标识")
    private String orgid;
    /**
     * 未开始任务数
     */
    @TableField(exist = false)
    @JSONField(name = "unstarttaskcnt")
    @JsonProperty("unstarttaskcnt")
    @ApiModelProperty("未开始任务数")
    private Integer unstarttaskcnt;
    /**
     * 项目团队相关成员
     */
    @TableField(exist = false)
    @JSONField(name = "accounts")
    @JsonProperty("accounts")
    @ApiModelProperty("项目团队相关成员")
    private String accounts;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * statge
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`STATGE`")
    @JSONField(name = "statge")
    @JsonProperty("statge")
    @ApiModelProperty("statge")
    private String statge;
    /**
     * 取消任务数
     */
    @TableField(exist = false)
    @JSONField(name = "canceltaskcnt")
    @JsonProperty("canceltaskcnt")
    @ApiModelProperty("取消任务数")
    private Integer canceltaskcnt;
    /**
     * 任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务总数")
    private Integer taskcnt;
    /**
     * 所有任务数
     */
    @TableField(exist = false)
    @JSONField(name = "alltaskcnt")
    @JsonProperty("alltaskcnt")
    @ApiModelProperty("所有任务数")
    private Integer alltaskcnt;
    /**
     * 支持项目汇报
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`SUPPROREPORT`")
    @JSONField(name = "supproreport")
    @JsonProperty("supproreport")
    @ApiModelProperty("支持项目汇报")
    private String supproreport;
    /**
     * 由谁取消
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CANCELEDBY`")
    @JSONField(name = "canceledby")
    @JsonProperty("canceledby")
    @ApiModelProperty("由谁取消")
    private String canceledby;
    /**
     * isCat
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ISCAT`")
    @JSONField(name = "iscat")
    @JsonProperty("iscat")
    @ApiModelProperty("isCat")
    private String iscat;
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
     * 未关闭任务数
     */
    @TableField(exist = false)
    @JSONField(name = "unclosetaskcnt")
    @JsonProperty("unclosetaskcnt")
    @ApiModelProperty("未关闭任务数")
    private Integer unclosetaskcnt;
    /**
     * 需求变更数
     */
    @TableField(exist = false)
    @JSONField(name = "storychangecnt")
    @JsonProperty("storychangecnt")
    @ApiModelProperty("需求变更数")
    private Integer storychangecnt;
    /**
     * 由谁关闭
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CLOSEDBY`")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @ApiModelProperty("由谁关闭")
    private String closedby;
    /**
     * 项目类型
     */
    @DEField(defaultValue = "sprint")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("项目类型")
    private String type;
    /**
     * 版本总数
     */
    @TableField(exist = false)
    @JSONField(name = "buildcnt")
    @JsonProperty("buildcnt")
    @ApiModelProperty("版本总数")
    private Integer buildcnt;
    /**
     * 项目团队成员
     */
    @TableField(exist = false)
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("项目团队成员")
    private String account;
    /**
     * 产品负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`PO`")
    @JSONField(name = "po")
    @JsonProperty("po")
    @ApiModelProperty("产品负责人")
    private String po;
    /**
     * 指派给我任务数
     */
    @TableField(exist = false)
    @JSONField(name = "asstomytaskcnt")
    @JsonProperty("asstomytaskcnt")
    @ApiModelProperty("指派给我任务数")
    private Integer asstomytaskcnt;
    /**
     * 项目排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("项目排序")
    private Integer order1;
    /**
     * 项目状态
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("项目状态")
    private String status;
    /**
     * 更多任务数
     */
    @TableField(exist = false)
    @JSONField(name = "moretaskcnt")
    @JsonProperty("moretaskcnt")
    @ApiModelProperty("更多任务数")
    private Integer moretaskcnt;
    /**
     * 可用工作日
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`DAYS`")
    @JSONField(name = "days")
    @JsonProperty("days")
    @ApiModelProperty("可用工作日")
    private Integer days;
    /**
     * 周期任务数
     */
    @TableField(exist = false)
    @JSONField(name = "cycletaskcnt")
    @JsonProperty("cycletaskcnt")
    @ApiModelProperty("周期任务数")
    private Integer cycletaskcnt;
    /**
     * 团队名称
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`TEAM`")
    @JSONField(name = "team")
    @JsonProperty("team")
    @ApiModelProperty("团队名称")
    private String team;
    /**
     * 关闭日期
     */
    @TableField(value = "`CLOSEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭日期")
    private Timestamp closeddate;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`OPENEDBY`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("由谁创建")
    private String openedby;
    /**
     * 需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求总数")
    private Integer storycnt;
    /**
     * 测试负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`QD`")
    @JSONField(name = "qd")
    @JsonProperty("qd")
    @ApiModelProperty("测试负责人")
    private String qd;
    /**
     * parent
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("parent")
    private String parentname;
    /**
     * 父项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("父项目")
    private Long parent;
    /**
     * 项目编号
     */
    @TableField(value = "`PROJECTSN`")
    @JSONField(name = "projectsn")
    @JsonProperty("projectsn")
    @ApiModelProperty("项目编号")
    private Long projectsn;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ibizparent;


    /**
     * 项目团队
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.ibiz.domain.ProjectTeam> projectteam;


    /**
     * 设置 [当前系统版本]
     */
    public void setOpenedversion(String openedversion) {
        this.openedversion = openedversion;
        this.modify("openedversion", openedversion);
    }

    /**
     * 设置 [开始时间]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [开始时间]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(begin);
    }
    /**
     * 设置 [项目立项信息]
     */
    public void setPmseeprojectinfo(String pmseeprojectinfo) {
        this.pmseeprojectinfo = pmseeprojectinfo;
        this.modify("pmseeprojectinfo", pmseeprojectinfo);
    }

    /**
     * 设置 [访问控制]
     */
    public void setAcl(String acl) {
        this.acl = acl;
        this.modify("acl", acl);
    }

    /**
     * 设置 [项目描述]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [项目负责人]
     */
    public void setPm(String pm) {
        this.pm = pm;
        this.modify("pm", pm);
    }

    /**
     * 设置 [项目名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [项目排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [发布负责人]
     */
    public void setRd(String rd) {
        this.rd = rd;
        this.modify("rd", rd);
    }

    /**
     * 设置 [分组白名单]
     */
    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
        this.modify("whitelist", whitelist);
    }

    /**
     * 设置 [优先级]
     */
    public void setPri(String pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [结束日期]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [结束日期]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(end);
    }
    /**
     * 设置 [取消日期]
     */
    public void setCanceleddate(Timestamp canceleddate) {
        this.canceleddate = canceleddate;
        this.modify("canceleddate", canceleddate);
    }

    /**
     * 格式化日期 [取消日期]
     */
    public String formatCanceleddate() {
        if (this.canceleddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(canceleddate);
    }
    /**
     * 设置 [项目代号]
     */
    public void setCode(String code) {
        this.code = code;
        this.modify("code", code);
    }

    /**
     * 设置 [catID]
     */
    public void setCatid(Integer catid) {
        this.catid = catid;
        this.modify("catid", catid);
    }

    /**
     * 设置 [statge]
     */
    public void setStatge(String statge) {
        this.statge = statge;
        this.modify("statge", statge);
    }

    /**
     * 设置 [支持项目汇报]
     */
    public void setSupproreport(String supproreport) {
        this.supproreport = supproreport;
        this.modify("supproreport", supproreport);
    }

    /**
     * 设置 [由谁取消]
     */
    public void setCanceledby(String canceledby) {
        this.canceledby = canceledby;
        this.modify("canceledby", canceledby);
    }

    /**
     * 设置 [isCat]
     */
    public void setIscat(String iscat) {
        this.iscat = iscat;
        this.modify("iscat", iscat);
    }

    /**
     * 设置 [由谁关闭]
     */
    public void setClosedby(String closedby) {
        this.closedby = closedby;
        this.modify("closedby", closedby);
    }

    /**
     * 设置 [项目类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [产品负责人]
     */
    public void setPo(String po) {
        this.po = po;
        this.modify("po", po);
    }

    /**
     * 设置 [项目状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [可用工作日]
     */
    public void setDays(Integer days) {
        this.days = days;
        this.modify("days", days);
    }

    /**
     * 设置 [团队名称]
     */
    public void setTeam(String team) {
        this.team = team;
        this.modify("team", team);
    }

    /**
     * 设置 [关闭日期]
     */
    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
        this.modify("closeddate", closeddate);
    }

    /**
     * 格式化日期 [关闭日期]
     */
    public String formatCloseddate() {
        if (this.closeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closeddate);
    }
    /**
     * 设置 [测试负责人]
     */
    public void setQd(String qd) {
        this.qd = qd;
        this.modify("qd", qd);
    }

    /**
     * 设置 [父项目]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
    }

    /**
     * 设置 [项目编号]
     */
    public void setProjectsn(Long projectsn) {
        this.projectsn = projectsn;
        this.modify("projectsn", projectsn);
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


