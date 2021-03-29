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
public class Project extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前系统版本
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`openedversion`")
    @JSONField(name = "openedversion")
    @JsonProperty("openedversion")
    private String openedversion;
    /**
     * 开始时间
     */
    @TableField(value = "`begin`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    private Timestamp begin;
    /**
     * 可用工时/天
     */
    @TableField(exist = false)
    @JSONField(name = "hours")
    @JsonProperty("hours")
    private BigDecimal hours;
    /**
     * 项目立项信息
     */
    @TableField(value = "`pmseeprojectinfo`")
    @JSONField(name = "pmseeprojectinfo")
    @JsonProperty("pmseeprojectinfo")
    private String pmseeprojectinfo;
    /**
     * 访问控制
     */
    @DEField(defaultValue = "open")
    @TableField(value = "`acl`")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    private String acl;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 任务消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    private Double totalconsumed;
    /**
     * 关联产品
     */
    @TableField(exist = false)
    @JSONField(name = "products")
    @JsonProperty("products")
    private String products;
    /**
     * 已完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "ycompletetaskcnt")
    @JsonProperty("ycompletetaskcnt")
    private Integer ycompletetaskcnt;
    /**
     * 关联计划
     */
    @TableField(exist = false)
    @JSONField(name = "plans")
    @JsonProperty("plans")
    private String plans;
    /**
     * 项目描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`desc`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    private String desc;
    /**
     * 临时任务数
     */
    @TableField(exist = false)
    @JSONField(name = "temptaskcnt")
    @JsonProperty("temptaskcnt")
    private Integer temptaskcnt;
    /**
     * 文档数量
     */
    @TableField(exist = false)
    @JSONField(name = "doclibcnt")
    @JsonProperty("doclibcnt")
    private Integer doclibcnt;
    /**
     * 我完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "mycompletetaskcnt")
    @JsonProperty("mycompletetaskcnt")
    private Integer mycompletetaskcnt;
    /**
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    private Integer istop;
    /**
     * 未完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "uncompletetaskcnt")
    @JsonProperty("uncompletetaskcnt")
    private Integer uncompletetaskcnt;
    /**
     * 团队成员总数
     */
    @TableField(exist = false)
    @JSONField(name = "teamcnt")
    @JsonProperty("teamcnt")
    private Integer teamcnt;
    /**
     * 项目负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`pm`")
    @JSONField(name = "pm")
    @JsonProperty("pm")
    private String pm;
    /**
     * 选择部门
     */
    @TableField(exist = false)
    @JSONField(name = "dept")
    @JsonProperty("dept")
    private String dept;
    /**
     * 项目编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 项目名称
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 子状态
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`substatus`")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    private String substatus;
    /**
     * 角色
     */
    @TableField(exist = false)
    @JSONField(name = "role")
    @JsonProperty("role")
    private String role;
    /**
     * 项目排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`order`")
    @JSONField(name = "order")
    @JsonProperty("order")
    private Integer order;
    /**
     * Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    private Integer bugcnt;
    /**
     * 发布负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`rd`")
    @JSONField(name = "rd")
    @JsonProperty("rd")
    private String rd;
    /**
     * 复制团队
     */
    @TableField(exist = false)
    @JSONField(name = "managemembers")
    @JsonProperty("managemembers")
    private String managemembers;
    /**
     * 进行中任务数
     */
    @TableField(exist = false)
    @JSONField(name = "ystarttaskcnt")
    @JsonProperty("ystarttaskcnt")
    private Integer ystarttaskcnt;
    /**
     * 分组白名单
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`whitelist`")
    @JSONField(name = "whitelist")
    @JsonProperty("whitelist")
    private String whitelist;
    /**
     * 移动端图片
     */
    @TableField(exist = false)
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    private String mobimage;
    /**
     * 总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalwh")
    @JsonProperty("totalwh")
    private Integer totalwh;
    /**
     * 可用工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalhours")
    @JsonProperty("totalhours")
    private BigDecimal totalhours;
    /**
     * 优先级
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`pri`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    private String pri;
    /**
     * 结束日期
     */
    @TableField(value = "`end`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    private Timestamp end;
    /**
     * 取消日期
     */
    @TableField(value = "`canceleddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "canceleddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("canceleddate")
    private Timestamp canceleddate;
    /**
     * 计划任务数
     */
    @TableField(exist = false)
    @JSONField(name = "plantaskcnt")
    @JsonProperty("plantaskcnt")
    private Integer plantaskcnt;
    /**
     * 加盟日
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "join", format = "yyyy-MM-dd")
    @JsonProperty("join")
    private Timestamp join;
    /**
     * 任务最初预计总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    private Double totalestimate;
    /**
     * 部门标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`mdeptid`")
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    private String mdeptid;
    /**
     * 任务预计剩余总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    private Double totalleft;
    /**
     * 关联数据数组
     */
    @TableField(exist = false)
    @JSONField(name = "srfarray")
    @JsonProperty("srfarray")
    private String srfarray;
    /**
     * 项目代号
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`code`")
    @JSONField(name = "code")
    @JsonProperty("code")
    private String code;
    /**
     * 时间段
     */
    @TableField(exist = false)
    @JSONField(name = "period")
    @JsonProperty("period")
    private String period;
    /**
     * 关闭任务数
     */
    @TableField(exist = false)
    @JSONField(name = "closetaskcnt")
    @JsonProperty("closetaskcnt")
    private Integer closetaskcnt;
    /**
     * 关联产品平台集合
     */
    @TableField(exist = false)
    @JSONField(name = "branchs")
    @JsonProperty("branchs")
    private String branchs;
    /**
     * catID
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`catid`")
    @JSONField(name = "catid")
    @JsonProperty("catid")
    private Integer catid;
    /**
     * 组织标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`orgid`")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    private String orgid;
    /**
     * 未开始任务数
     */
    @TableField(exist = false)
    @JSONField(name = "unstarttaskcnt")
    @JsonProperty("unstarttaskcnt")
    private Integer unstarttaskcnt;
    /**
     * 项目团队相关成员
     */
    @TableField(exist = false)
    @JSONField(name = "accounts")
    @JsonProperty("accounts")
    private String accounts;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    private String noticeusers;
    /**
     * statge
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`statge`")
    @JSONField(name = "statge")
    @JsonProperty("statge")
    private String statge;
    /**
     * 取消任务数
     */
    @TableField(exist = false)
    @JSONField(name = "canceltaskcnt")
    @JsonProperty("canceltaskcnt")
    private Integer canceltaskcnt;
    /**
     * 任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    private Integer taskcnt;
    /**
     * 所有任务数
     */
    @TableField(exist = false)
    @JSONField(name = "alltaskcnt")
    @JsonProperty("alltaskcnt")
    private Integer alltaskcnt;
    /**
     * 支持项目汇报
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`supproreport`")
    @JSONField(name = "supproreport")
    @JsonProperty("supproreport")
    private String supproreport;
    /**
     * 由谁取消
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`canceledby`")
    @JSONField(name = "canceledby")
    @JsonProperty("canceledby")
    private String canceledby;
    /**
     * isCat
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`iscat`")
    @JSONField(name = "iscat")
    @JsonProperty("iscat")
    private String iscat;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`openeddate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    private Timestamp openeddate;
    /**
     * 未关闭任务数
     */
    @TableField(exist = false)
    @JSONField(name = "unclosetaskcnt")
    @JsonProperty("unclosetaskcnt")
    private Integer unclosetaskcnt;
    /**
     * 需求变更数
     */
    @TableField(exist = false)
    @JSONField(name = "storychangecnt")
    @JsonProperty("storychangecnt")
    private Integer storychangecnt;
    /**
     * 由谁关闭
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`closedby`")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    private String closedby;
    /**
     * 项目类型
     */
    @DEField(defaultValue = "sprint")
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 版本总数
     */
    @TableField(exist = false)
    @JSONField(name = "buildcnt")
    @JsonProperty("buildcnt")
    private Integer buildcnt;
    /**
     * 项目团队成员
     */
    @TableField(exist = false)
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * 产品负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`po`")
    @JSONField(name = "po")
    @JsonProperty("po")
    private String po;
    /**
     * 指派给我任务数
     */
    @TableField(exist = false)
    @JSONField(name = "asstomytaskcnt")
    @JsonProperty("asstomytaskcnt")
    private Integer asstomytaskcnt;
    /**
     * 项目排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    private Integer order1;
    /**
     * 项目状态
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 更多任务数
     */
    @TableField(exist = false)
    @JSONField(name = "moretaskcnt")
    @JsonProperty("moretaskcnt")
    private Integer moretaskcnt;
    /**
     * 可用工作日
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`days`")
    @JSONField(name = "days")
    @JsonProperty("days")
    private Integer days;
    /**
     * 周期任务数
     */
    @TableField(exist = false)
    @JSONField(name = "cycletaskcnt")
    @JsonProperty("cycletaskcnt")
    private Integer cycletaskcnt;
    /**
     * 团队名称
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`team`")
    @JSONField(name = "team")
    @JsonProperty("team")
    private String team;
    /**
     * 关闭日期
     */
    @TableField(value = "`closeddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    private Timestamp closeddate;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`openedby`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    private String openedby;
    /**
     * 需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    private Integer storycnt;
    /**
     * 测试负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`qd`")
    @JSONField(name = "qd")
    @JsonProperty("qd")
    private String qd;
    /**
     * parent
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    private String parentname;
    /**
     * 父项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`parent`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    private Long parent;

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


