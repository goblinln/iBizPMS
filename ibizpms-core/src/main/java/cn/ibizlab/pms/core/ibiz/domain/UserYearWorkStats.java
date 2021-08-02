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
 * 实体[用户年度工作内容统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_user", resultMap = "UserYearWorkStatsResultMap")
@ApiModel("用户年度工作内容统计")
public class UserYearWorkStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    @TableField(value = "`PASSWORD`")
    @JSONField(name = "password")
    @JsonProperty("password")
    @ApiModelProperty("密码")
    private String password;
    /**
     * 累计参与产品数
     */
    @TableField(exist = false)
    @JSONField(name = "yearproductcnt")
    @JsonProperty("yearproductcnt")
    @ApiModelProperty("累计参与产品数")
    private Integer yearproductcnt;
    /**
     * 累计创建Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yearbugcnt")
    @JsonProperty("yearbugcnt")
    @ApiModelProperty("累计创建Bug数")
    private Integer yearbugcnt;
    /**
     * 微信
     */
    @TableField(value = "`WEIXIN`")
    @JSONField(name = "weixin")
    @JsonProperty("weixin")
    @ApiModelProperty("微信")
    private String weixin;
    /**
     * 累计创建用例数
     */
    @TableField(exist = false)
    @JSONField(name = "yearcasecnt")
    @JsonProperty("yearcasecnt")
    @ApiModelProperty("累计创建用例数")
    private Integer yearcasecnt;
    /**
     * 账号
     */
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("账号")
    private String account;
    /**
     * QQ
     */
    @TableField(value = "`QQ`")
    @JSONField(name = "qq")
    @JsonProperty("qq")
    @ApiModelProperty("QQ")
    private String qq;
    /**
     * ranzhi
     */
    @TableField(value = "`RANZHI`")
    @JSONField(name = "ranzhi")
    @JsonProperty("ranzhi")
    @ApiModelProperty("ranzhi")
    private String ranzhi;
    /**
     * nickname
     */
    @TableField(value = "`NICKNAME`")
    @JSONField(name = "nickname")
    @JsonProperty("nickname")
    @ApiModelProperty("nickname")
    private String nickname;
    /**
     * avatar
     */
    @TableField(value = "`AVATAR`")
    @JSONField(name = "avatar")
    @JsonProperty("avatar")
    @ApiModelProperty("avatar")
    private String avatar;
    /**
     * 月完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "monthfinishtask")
    @JsonProperty("monthfinishtask")
    @ApiModelProperty("月完成任务数")
    private Integer monthfinishtask;
    /**
     * fails
     */
    @TableField(value = "`FAILS`")
    @JSONField(name = "fails")
    @JsonProperty("fails")
    @ApiModelProperty("fails")
    private Integer fails;
    /**
     * 入职日期
     */
    @TableField(value = "`JOIN`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "join", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("join")
    @ApiModelProperty("入职日期")
    private Timestamp join;
    /**
     * 累计创建需求数
     */
    @TableField(exist = false)
    @JSONField(name = "yearstorycnt")
    @JsonProperty("yearstorycnt")
    @ApiModelProperty("累计创建需求数")
    private Integer yearstorycnt;
    /**
     * 累计创建计划数
     */
    @TableField(exist = false)
    @JSONField(name = "yearplancnt")
    @JsonProperty("yearplancnt")
    @ApiModelProperty("累计创建计划数")
    private Integer yearplancnt;
    /**
     * 钉钉
     */
    @TableField(value = "`DINGDING`")
    @JSONField(name = "dingding")
    @JsonProperty("dingding")
    @ApiModelProperty("钉钉")
    private String dingding;
    /**
     * ip
     */
    @TableField(value = "`IP`")
    @JSONField(name = "ip")
    @JsonProperty("ip")
    @ApiModelProperty("ip")
    private String ip;
    /**
     * 累计动态数
     */
    @TableField(exist = false)
    @JSONField(name = "yearactioncnt")
    @JsonProperty("yearactioncnt")
    @ApiModelProperty("累计动态数")
    private Integer yearactioncnt;
    /**
     * 手机
     */
    @TableField(value = "`MOBILE`")
    @JSONField(name = "mobile")
    @JsonProperty("mobile")
    @ApiModelProperty("手机")
    private String mobile;
    /**
     * whatsapp
     */
    @TableField(value = "`WHATSAPP`")
    @JSONField(name = "whatsapp")
    @JsonProperty("whatsapp")
    @ApiModelProperty("whatsapp")
    private String whatsapp;
    /**
     * 用户编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("用户编号")
    private Long id;
    /**
     * 邮箱
     */
    @TableField(value = "`EMAIL`")
    @JSONField(name = "email")
    @JsonProperty("email")
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 累计工时数
     */
    @TableField(exist = false)
    @JSONField(name = "yearestimatecnt")
    @JsonProperty("yearestimatecnt")
    @ApiModelProperty("累计工时数")
    private Integer yearestimatecnt;
    /**
     * clientLang
     */
    @TableField(value = "`CLIENTLANG`")
    @JSONField(name = "clientlang")
    @JsonProperty("clientlang")
    @ApiModelProperty("clientLang")
    private String clientlang;
    /**
     * 月累计工时
     */
    @TableField(exist = false)
    @JSONField(name = "montestimate")
    @JsonProperty("montestimate")
    @ApiModelProperty("月累计工时")
    private Integer montestimate;
    /**
     * 真实用户名
     */
    @TableField(value = "`REALNAME`")
    @JSONField(name = "realname")
    @JsonProperty("realname")
    @ApiModelProperty("真实用户名")
    private String realname;
    /**
     * 源代码账户
     */
    @TableField(value = "`COMMITER`")
    @JSONField(name = "commiter")
    @JsonProperty("commiter")
    @ApiModelProperty("源代码账户")
    private String commiter;
    /**
     * slack
     */
    @TableField(value = "`SLACK`")
    @JSONField(name = "slack")
    @JsonProperty("slack")
    @ApiModelProperty("slack")
    private String slack;
    /**
     * 逻辑删除标志
     */
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("逻辑删除标志")
    private String deleted;
    /**
     * 最后登录
     */
    @TableField(value = "`LAST`")
    @JSONField(name = "last")
    @JsonProperty("last")
    @ApiModelProperty("最后登录")
    private Integer last;
    /**
     * 年度
     */
    @TableField(exist = false)
    @JSONField(name = "curyear")
    @JsonProperty("curyear")
    @ApiModelProperty("年度")
    private String curyear;
    /**
     * 判断角色
     */
    @TableField(exist = false)
    @JSONField(name = "judgerole")
    @JsonProperty("judgerole")
    @ApiModelProperty("判断角色")
    private String judgerole;
    /**
     * skype
     */
    @TableField(value = "`SKYPE`")
    @JSONField(name = "skype")
    @JsonProperty("skype")
    @ApiModelProperty("skype")
    private String skype;
    /**
     * birthday
     */
    @TableField(value = "`BIRTHDAY`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "birthday", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("birthday")
    @ApiModelProperty("birthday")
    private Timestamp birthday;
    /**
     * 电话
     */
    @TableField(value = "`PHONE`")
    @JSONField(name = "phone")
    @JsonProperty("phone")
    @ApiModelProperty("电话")
    private String phone;
    /**
     * 累计登录次数
     */
    @TableField(exist = false)
    @JSONField(name = "yearvisits")
    @JsonProperty("yearvisits")
    @ApiModelProperty("累计登录次数")
    private Integer yearvisits;
    /**
     * score
     */
    @TableField(value = "`SCORE`")
    @JSONField(name = "score")
    @JsonProperty("score")
    @ApiModelProperty("score")
    private Integer score;
    /**
     * 角色
     */
    @TableField(value = "`ROLE`")
    @JSONField(name = "role")
    @JsonProperty("role")
    @ApiModelProperty("角色")
    private String role;
    /**
     * clientStatus
     */
    @TableField(value = "`CLIENTSTATUS`")
    @JSONField(name = "clientstatus")
    @JsonProperty("clientstatus")
    @ApiModelProperty("clientStatus")
    private String clientstatus;
    /**
     * 部门编号
     */
    @DEField(dict = "RealDept")
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("部门编号")
    private String dept;
    /**
     * 标题
     */
    @TableField(exist = false)
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("标题")
    private String title;
    /**
     * 当前月
     */
    @TableField(exist = false)
    @JSONField(name = "curmonth")
    @JsonProperty("curmonth")
    @ApiModelProperty("当前月")
    private String curmonth;
    /**
     * 月解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "montresolvedbug")
    @JsonProperty("montresolvedbug")
    @ApiModelProperty("月解决Bug数")
    private Integer montresolvedbug;
    /**
     * 通讯地址
     */
    @TableField(value = "`ADDRESS`")
    @JSONField(name = "address")
    @JsonProperty("address")
    @ApiModelProperty("通讯地址")
    private String address;
    /**
     * scoreLevel
     */
    @TableField(value = "`SCORELEVEL`")
    @JSONField(name = "scorelevel")
    @JsonProperty("scorelevel")
    @ApiModelProperty("scoreLevel")
    private Integer scorelevel;
    /**
     * 累计登录次数
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`VISITS`")
    @JSONField(name = "visits")
    @JsonProperty("visits")
    @ApiModelProperty("累计登录次数")
    private Integer visits;
    /**
     * 累计日志数
     */
    @TableField(exist = false)
    @JSONField(name = "yearlogcnt")
    @JsonProperty("yearlogcnt")
    @ApiModelProperty("累计日志数")
    private Integer yearlogcnt;
    /**
     * locked
     */
    @TableField(value = "`LOCKED`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "locked", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("locked")
    @ApiModelProperty("locked")
    private Timestamp locked;
    /**
     * 性别
     */
    @TableField(value = "`GENDER`")
    @JSONField(name = "gender")
    @JsonProperty("gender")
    @ApiModelProperty("性别")
    private String gender;
    /**
     * zipcode
     */
    @TableField(value = "`ZIPCODE`")
    @JSONField(name = "zipcode")
    @JsonProperty("zipcode")
    @ApiModelProperty("zipcode")
    private String zipcode;



    /**
     * 设置 [密码]
     */
    public void setPassword(String password) {
        this.password = password;
        this.modify("password", password);
    }

    /**
     * 设置 [微信]
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
        this.modify("weixin", weixin);
    }

    /**
     * 设置 [账号]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [QQ]
     */
    public void setQq(String qq) {
        this.qq = qq;
        this.modify("qq", qq);
    }

    /**
     * 设置 [ranzhi]
     */
    public void setRanzhi(String ranzhi) {
        this.ranzhi = ranzhi;
        this.modify("ranzhi", ranzhi);
    }

    /**
     * 设置 [nickname]
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.modify("nickname", nickname);
    }

    /**
     * 设置 [avatar]
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
        this.modify("avatar", avatar);
    }

    /**
     * 设置 [fails]
     */
    public void setFails(Integer fails) {
        this.fails = fails;
        this.modify("fails", fails);
    }

    /**
     * 设置 [入职日期]
     */
    public void setJoin(Timestamp join) {
        this.join = join;
        this.modify("join", join);
    }

    /**
     * 格式化日期 [入职日期]
     */
    public String formatJoin() {
        if (this.join == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(join);
    }
    /**
     * 设置 [钉钉]
     */
    public void setDingding(String dingding) {
        this.dingding = dingding;
        this.modify("dingding", dingding);
    }

    /**
     * 设置 [ip]
     */
    public void setIp(String ip) {
        this.ip = ip;
        this.modify("ip", ip);
    }

    /**
     * 设置 [手机]
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
        this.modify("mobile", mobile);
    }

    /**
     * 设置 [whatsapp]
     */
    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
        this.modify("whatsapp", whatsapp);
    }

    /**
     * 设置 [邮箱]
     */
    public void setEmail(String email) {
        this.email = email;
        this.modify("email", email);
    }

    /**
     * 设置 [clientLang]
     */
    public void setClientlang(String clientlang) {
        this.clientlang = clientlang;
        this.modify("clientlang", clientlang);
    }

    /**
     * 设置 [真实用户名]
     */
    public void setRealname(String realname) {
        this.realname = realname;
        this.modify("realname", realname);
    }

    /**
     * 设置 [源代码账户]
     */
    public void setCommiter(String commiter) {
        this.commiter = commiter;
        this.modify("commiter", commiter);
    }

    /**
     * 设置 [slack]
     */
    public void setSlack(String slack) {
        this.slack = slack;
        this.modify("slack", slack);
    }

    /**
     * 设置 [逻辑删除标志]
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted;
        this.modify("deleted", deleted);
    }

    /**
     * 设置 [最后登录]
     */
    public void setLast(Integer last) {
        this.last = last;
        this.modify("last", last);
    }

    /**
     * 设置 [skype]
     */
    public void setSkype(String skype) {
        this.skype = skype;
        this.modify("skype", skype);
    }

    /**
     * 设置 [birthday]
     */
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
        this.modify("birthday", birthday);
    }

    /**
     * 格式化日期 [birthday]
     */
    public String formatBirthday() {
        if (this.birthday == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(birthday);
    }
    /**
     * 设置 [电话]
     */
    public void setPhone(String phone) {
        this.phone = phone;
        this.modify("phone", phone);
    }

    /**
     * 设置 [score]
     */
    public void setScore(Integer score) {
        this.score = score;
        this.modify("score", score);
    }

    /**
     * 设置 [角色]
     */
    public void setRole(String role) {
        this.role = role;
        this.modify("role", role);
    }

    /**
     * 设置 [clientStatus]
     */
    public void setClientstatus(String clientstatus) {
        this.clientstatus = clientstatus;
        this.modify("clientstatus", clientstatus);
    }

    /**
     * 设置 [部门编号]
     */
    public void setDept(String dept) {
        this.dept = dept;
        this.modify("dept", dept);
    }

    /**
     * 设置 [通讯地址]
     */
    public void setAddress(String address) {
        this.address = address;
        this.modify("address", address);
    }

    /**
     * 设置 [scoreLevel]
     */
    public void setScorelevel(Integer scorelevel) {
        this.scorelevel = scorelevel;
        this.modify("scorelevel", scorelevel);
    }

    /**
     * 设置 [累计登录次数]
     */
    public void setVisits(Integer visits) {
        this.visits = visits;
        this.modify("visits", visits);
    }

    /**
     * 设置 [locked]
     */
    public void setLocked(Timestamp locked) {
        this.locked = locked;
        this.modify("locked", locked);
    }

    /**
     * 格式化日期 [locked]
     */
    public String formatLocked() {
        if (this.locked == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(locked);
    }
    /**
     * 设置 [性别]
     */
    public void setGender(String gender) {
        this.gender = gender;
        this.modify("gender", gender);
    }

    /**
     * 设置 [zipcode]
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
        this.modify("zipcode", zipcode);
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


