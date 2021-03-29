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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[我的地盘]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_user", resultMap = "IbzMyTerritoryResultMap")
public class IbzMyTerritory extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 我的过期bug数
     */
    @TableField(exist = false)
    @JSONField(name = "myebugs")
    @JsonProperty("myebugs")
    private String myebugs;
    /**
     * nickname
     */
    @TableField(value = "`nickname`")
    @JSONField(name = "nickname")
    @JsonProperty("nickname")
    private String nickname;
    /**
     * fails
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`fails`")
    @JSONField(name = "fails")
    @JsonProperty("fails")
    private Integer fails;
    /**
     * 访问次数
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`visits`")
    @JSONField(name = "visits")
    @JsonProperty("visits")
    private Integer visits;
    /**
     * 电话
     */
    @TableField(value = "`phone`")
    @JSONField(name = "phone")
    @JsonProperty("phone")
    private String phone;
    /**
     * 我的待办数
     */
    @TableField(exist = false)
    @JSONField(name = "mytodocnt")
    @JsonProperty("mytodocnt")
    private Integer mytodocnt;
    /**
     * 我的地盘
     */
    @TableField(exist = false)
    @JSONField(name = "myterritorycnt")
    @JsonProperty("myterritorycnt")
    private Integer myterritorycnt;
    /**
     * 我的bugs
     */
    @TableField(exist = false)
    @JSONField(name = "mybugs")
    @JsonProperty("mybugs")
    private Integer mybugs;
    /**
     * 职位
     */
    @TableField(value = "`role`")
    @JSONField(name = "role")
    @JsonProperty("role")
    private String role;
    /**
     * 真实姓名
     */
    @TableField(value = "`realname`")
    @JSONField(name = "realname")
    @JsonProperty("realname")
    private String realname;
    /**
     * clientStatus
     */
    @TableField(value = "`clientstatus`")
    @JSONField(name = "clientstatus")
    @JsonProperty("clientstatus")
    private String clientstatus;
    /**
     * 最后登录
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`last`")
    @JSONField(name = "last")
    @JsonProperty("last")
    private Integer last;
    /**
     * zipcode
     */
    @TableField(value = "`zipcode`")
    @JSONField(name = "zipcode")
    @JsonProperty("zipcode")
    private String zipcode;
    /**
     * skype
     */
    @TableField(value = "`skype`")
    @JSONField(name = "skype")
    @JsonProperty("skype")
    private String skype;
    /**
     * 我收藏的bugs
     */
    @TableField(exist = false)
    @JSONField(name = "myfavoritebugs")
    @JsonProperty("myfavoritebugs")
    private Integer myfavoritebugs;
    /**
     * 入职日期
     */
    @DEField(defaultValue = "0000-00-00")
    @TableField(value = "`join`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "join", format = "yyyy-MM-dd")
    @JsonProperty("join")
    private Timestamp join;
    /**
     * score
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`score`")
    @JSONField(name = "score")
    @JsonProperty("score")
    private Integer score;
    /**
     * 所属部门
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`dept`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    private Integer dept;
    /**
     * 账户
     */
    @TableField(value = "`account`")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * 我的收藏
     */
    @TableField(exist = false)
    @JSONField(name = "myfavorites")
    @JsonProperty("myfavorites")
    private Integer myfavorites;
    /**
     * 我的需求数
     */
    @TableField(exist = false)
    @JSONField(name = "mystorys")
    @JsonProperty("mystorys")
    private Integer mystorys;
    /**
     * 源代码账户
     */
    @TableField(value = "`commiter`")
    @JSONField(name = "commiter")
    @JsonProperty("commiter")
    private String commiter;
    /**
     * 手机
     */
    @TableField(value = "`mobile`")
    @JSONField(name = "mobile")
    @JsonProperty("mobile")
    private String mobile;
    /**
     * locked
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`locked`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "locked", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("locked")
    private Timestamp locked;
    /**
     * 通讯地址
     */
    @TableField(value = "`address`")
    @JSONField(name = "address")
    @JsonProperty("address")
    private String address;
    /**
     * 我的任务
     */
    @TableField(exist = false)
    @JSONField(name = "mytasks")
    @JsonProperty("mytasks")
    private Integer mytasks;
    /**
     * scoreLevel
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`scorelevel`")
    @JSONField(name = "scorelevel")
    @JsonProperty("scorelevel")
    private Integer scorelevel;
    /**
     * 密码
     */
    @TableField(value = "`password`")
    @JSONField(name = "password")
    @JsonProperty("password")
    private String password;
    /**
     * ranzhi
     */
    @TableField(value = "`ranzhi`")
    @JSONField(name = "ranzhi")
    @JsonProperty("ranzhi")
    private String ranzhi;
    /**
     * 未关闭项目数
     */
    @TableField(exist = false)
    @JSONField(name = "projects")
    @JsonProperty("projects")
    private Integer projects;
    /**
     * slack
     */
    @TableField(value = "`slack`")
    @JSONField(name = "slack")
    @JsonProperty("slack")
    private String slack;
    /**
     * 未关闭产品数
     */
    @TableField(exist = false)
    @JSONField(name = "products")
    @JsonProperty("products")
    private Integer products;
    /**
     * 微信
     */
    @TableField(value = "`weixin`")
    @JSONField(name = "weixin")
    @JsonProperty("weixin")
    private String weixin;
    /**
     * 我的过期任务数
     */
    @TableField(exist = false)
    @JSONField(name = "myetasks")
    @JsonProperty("myetasks")
    private String myetasks;
    /**
     * 过期项目数
     */
    @TableField(exist = false)
    @JSONField(name = "eprojects")
    @JsonProperty("eprojects")
    private String eprojects;
    /**
     * whatsapp
     */
    @TableField(value = "`whatsapp`")
    @JSONField(name = "whatsapp")
    @JsonProperty("whatsapp")
    private String whatsapp;
    /**
     * QQ
     */
    @TableField(value = "`qq`")
    @JSONField(name = "qq")
    @JsonProperty("qq")
    private String qq;
    /**
     * 男女
     */
    @TableField(value = "`gender`")
    @JSONField(name = "gender")
    @JsonProperty("gender")
    private String gender;
    /**
     * clientLang
     */
    @DEField(defaultValue = "zh-cn")
    @TableField(value = "`clientlang`")
    @JSONField(name = "clientlang")
    @JsonProperty("clientlang")
    private String clientlang;
    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID)
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 我收藏的任务
     */
    @TableField(exist = false)
    @JSONField(name = "myfavoritetasks")
    @JsonProperty("myfavoritetasks")
    private Integer myfavoritetasks;
    /**
     * birthday
     */
    @DEField(defaultValue = "0000-00-00")
    @TableField(value = "`birthday`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "birthday", format = "yyyy-MM-dd")
    @JsonProperty("birthday")
    private Timestamp birthday;
    /**
     * ip
     */
    @TableField(value = "`ip`")
    @JSONField(name = "ip")
    @JsonProperty("ip")
    private String ip;
    /**
     * 我收藏的需求数
     */
    @TableField(exist = false)
    @JSONField(name = "myfavoritestorys")
    @JsonProperty("myfavoritestorys")
    private Integer myfavoritestorys;
    /**
     * 邮箱
     */
    @TableField(value = "`email`")
    @JSONField(name = "email")
    @JsonProperty("email")
    private String email;
    /**
     * 钉钉
     */
    @TableField(value = "`dingding`")
    @JSONField(name = "dingding")
    @JsonProperty("dingding")
    private String dingding;
    /**
     * avatar
     */
    @TableField(value = "`avatar`")
    @JSONField(name = "avatar")
    @JsonProperty("avatar")
    private String avatar;
    /**
     * ID
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;



    /**
     * 设置 [nickname]
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.modify("nickname", nickname);
    }

    /**
     * 设置 [fails]
     */
    public void setFails(Integer fails) {
        this.fails = fails;
        this.modify("fails", fails);
    }

    /**
     * 设置 [访问次数]
     */
    public void setVisits(Integer visits) {
        this.visits = visits;
        this.modify("visits", visits);
    }

    /**
     * 设置 [电话]
     */
    public void setPhone(String phone) {
        this.phone = phone;
        this.modify("phone", phone);
    }

    /**
     * 设置 [职位]
     */
    public void setRole(String role) {
        this.role = role;
        this.modify("role", role);
    }

    /**
     * 设置 [真实姓名]
     */
    public void setRealname(String realname) {
        this.realname = realname;
        this.modify("realname", realname);
    }

    /**
     * 设置 [clientStatus]
     */
    public void setClientstatus(String clientstatus) {
        this.clientstatus = clientstatus;
        this.modify("clientstatus", clientstatus);
    }

    /**
     * 设置 [最后登录]
     */
    public void setLast(Integer last) {
        this.last = last;
        this.modify("last", last);
    }

    /**
     * 设置 [zipcode]
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
        this.modify("zipcode", zipcode);
    }

    /**
     * 设置 [skype]
     */
    public void setSkype(String skype) {
        this.skype = skype;
        this.modify("skype", skype);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(join);
    }
    /**
     * 设置 [score]
     */
    public void setScore(Integer score) {
        this.score = score;
        this.modify("score", score);
    }

    /**
     * 设置 [所属部门]
     */
    public void setDept(Integer dept) {
        this.dept = dept;
        this.modify("dept", dept);
    }

    /**
     * 设置 [账户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [源代码账户]
     */
    public void setCommiter(String commiter) {
        this.commiter = commiter;
        this.modify("commiter", commiter);
    }

    /**
     * 设置 [手机]
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
        this.modify("mobile", mobile);
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
     * 设置 [密码]
     */
    public void setPassword(String password) {
        this.password = password;
        this.modify("password", password);
    }

    /**
     * 设置 [ranzhi]
     */
    public void setRanzhi(String ranzhi) {
        this.ranzhi = ranzhi;
        this.modify("ranzhi", ranzhi);
    }

    /**
     * 设置 [slack]
     */
    public void setSlack(String slack) {
        this.slack = slack;
        this.modify("slack", slack);
    }

    /**
     * 设置 [微信]
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
        this.modify("weixin", weixin);
    }

    /**
     * 设置 [whatsapp]
     */
    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
        this.modify("whatsapp", whatsapp);
    }

    /**
     * 设置 [QQ]
     */
    public void setQq(String qq) {
        this.qq = qq;
        this.modify("qq", qq);
    }

    /**
     * 设置 [男女]
     */
    public void setGender(String gender) {
        this.gender = gender;
        this.modify("gender", gender);
    }

    /**
     * 设置 [clientLang]
     */
    public void setClientlang(String clientlang) {
        this.clientlang = clientlang;
        this.modify("clientlang", clientlang);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(birthday);
    }
    /**
     * 设置 [ip]
     */
    public void setIp(String ip) {
        this.ip = ip;
        this.modify("ip", ip);
    }

    /**
     * 设置 [邮箱]
     */
    public void setEmail(String email) {
        this.email = email;
        this.modify("email", email);
    }

    /**
     * 设置 [钉钉]
     */
    public void setDingding(String dingding) {
        this.dingding = dingding;
        this.modify("dingding", dingding);
    }

    /**
     * 设置 [avatar]
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
        this.modify("avatar", avatar);
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


