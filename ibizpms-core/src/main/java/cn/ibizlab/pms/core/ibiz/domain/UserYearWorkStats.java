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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;


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
@TableName(value = "zt_user",resultMap = "UserYearWorkStatsResultMap")
public class UserYearWorkStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 真实用户名
     */
    @TableField(value = "`realname`")
    @JSONField(name = "realname")
    @JsonProperty("realname")
    private String realname;
    /**
     * 账号
     */
    @TableField(value = "`account`")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * 角色
     */
    @TableField(value = "`role`")
    @JSONField(name = "role")
    @JsonProperty("role")
    private String role;
    /**
     * 部门编号
     */
    @TableField(value = "`dept`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    private String dept;
    /**
     * 累计登录次数
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`visits`")
    @JSONField(name = "visits")
    @JsonProperty("visits")
    private Integer visits;
    /**
     * 累计创建计划数
     */
    @TableField(exist = false)
    @JSONField(name = "yearplancnt")
    @JsonProperty("yearplancnt")
    private Integer yearplancnt;
    /**
     * 累计参与产品数
     */
    @TableField(exist = false)
    @JSONField(name = "yearproductcnt")
    @JsonProperty("yearproductcnt")
    private Integer yearproductcnt;
    /**
     * 累计创建需求数
     */
    @TableField(exist = false)
    @JSONField(name = "yearstorycnt")
    @JsonProperty("yearstorycnt")
    private Integer yearstorycnt;
    /**
     * 累计动态数
     */
    @TableField(exist = false)
    @JSONField(name = "yearactioncnt")
    @JsonProperty("yearactioncnt")
    private Integer yearactioncnt;
    /**
     * 累计创建Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yearbugcnt")
    @JsonProperty("yearbugcnt")
    private Integer yearbugcnt;
    /**
     * 累计创建用例数
     */
    @TableField(exist = false)
    @JSONField(name = "yearcasecnt")
    @JsonProperty("yearcasecnt")
    private Integer yearcasecnt;
    /**
     * 累计日志数
     */
    @TableField(exist = false)
    @JSONField(name = "yearlogcnt")
    @JsonProperty("yearlogcnt")
    private Integer yearlogcnt;
    /**
     * 累计工时数
     */
    @TableField(exist = false)
    @JSONField(name = "yearestimatecnt")
    @JsonProperty("yearestimatecnt")
    private Integer yearestimatecnt;
    /**
     * 判断角色
     */
    @TableField(exist = false)
    @JSONField(name = "judgerole")
    @JsonProperty("judgerole")
    private String judgerole;
    /**
     * 累计登录次数
     */
    @TableField(exist = false)
    @JSONField(name = "yearvisits")
    @JsonProperty("yearvisits")
    private Integer yearvisits;
    /**
     * 年度
     */
    @TableField(exist = false)
    @JSONField(name = "curyear")
    @JsonProperty("curyear")
    private String curyear;
    /**
     * 标题
     */
    @TableField(exist = false)
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 月完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "monthfinishtask")
    @JsonProperty("monthfinishtask")
    private Integer monthfinishtask;
    /**
     * 月累计工时
     */
    @TableField(exist = false)
    @JSONField(name = "montestimate")
    @JsonProperty("montestimate")
    private Integer montestimate;
    /**
     * 月解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "montresolvedbug")
    @JsonProperty("montresolvedbug")
    private Integer montresolvedbug;
    /**
     * 当前月
     */
    @TableField(exist = false)
    @JSONField(name = "curmonth")
    @JsonProperty("curmonth")
    private String curmonth;
    /**
     * slack
     */
    @TableField(value = "`slack`")
    @JSONField(name = "slack")
    @JsonProperty("slack")
    private String slack;
    /**
     * skype
     */
    @TableField(value = "`skype`")
    @JSONField(name = "skype")
    @JsonProperty("skype")
    private String skype;
    /**
     * score
     */
    @TableField(value = "`score`")
    @JSONField(name = "score")
    @JsonProperty("score")
    private Integer score;
    /**
     * 微信
     */
    @TableField(value = "`weixin`")
    @JSONField(name = "weixin")
    @JsonProperty("weixin")
    private String weixin;
    /**
     * 入职日期
     */
    @TableField(value = "`join`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("join")
    private Timestamp join;
    /**
     * 电话
     */
    @TableField(value = "`phone`")
    @JSONField(name = "phone")
    @JsonProperty("phone")
    private String phone;
    /**
     * fails
     */
    @TableField(value = "`fails`")
    @JSONField(name = "fails")
    @JsonProperty("fails")
    private Integer fails;
    /**
     * 邮箱
     */
    @TableField(value = "`email`")
    @JSONField(name = "email")
    @JsonProperty("email")
    private String email;
    /**
     * clientLang
     */
    @TableField(value = "`clientlang`")
    @JSONField(name = "clientlang")
    @JsonProperty("clientlang")
    private String clientlang;
    /**
     * 逻辑删除标志
     */
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * ranzhi
     */
    @TableField(value = "`ranzhi`")
    @JSONField(name = "ranzhi")
    @JsonProperty("ranzhi")
    private String ranzhi;
    /**
     * avatar
     */
    @TableField(value = "`avatar`")
    @JSONField(name = "avatar")
    @JsonProperty("avatar")
    private String avatar;
    /**
     * 源代码账户
     */
    @TableField(value = "`commiter`")
    @JSONField(name = "commiter")
    @JsonProperty("commiter")
    private String commiter;
    /**
     * 性别
     */
    @TableField(value = "`gender`")
    @JSONField(name = "gender")
    @JsonProperty("gender")
    private String gender;
    /**
     * QQ
     */
    @TableField(value = "`qq`")
    @JSONField(name = "qq")
    @JsonProperty("qq")
    private String qq;
    /**
     * birthday
     */
    @TableField(value = "`birthday`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "birthday" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("birthday")
    private Timestamp birthday;
    /**
     * locked
     */
    @TableField(value = "`locked`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "locked" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("locked")
    private Timestamp locked;
    /**
     * 钉钉
     */
    @TableField(value = "`dingding`")
    @JSONField(name = "dingding")
    @JsonProperty("dingding")
    private String dingding;
    /**
     * ip
     */
    @TableField(value = "`ip`")
    @JSONField(name = "ip")
    @JsonProperty("ip")
    private String ip;
    /**
     * whatsapp
     */
    @TableField(value = "`whatsapp`")
    @JSONField(name = "whatsapp")
    @JsonProperty("whatsapp")
    private String whatsapp;
    /**
     * zipcode
     */
    @TableField(value = "`zipcode`")
    @JSONField(name = "zipcode")
    @JsonProperty("zipcode")
    private String zipcode;
    /**
     * 密码
     */
    @TableField(value = "`password`")
    @JSONField(name = "password")
    @JsonProperty("password")
    private String password;
    /**
     * nickname
     */
    @TableField(value = "`nickname`")
    @JSONField(name = "nickname")
    @JsonProperty("nickname")
    private String nickname;
    /**
     * clientStatus
     */
    @TableField(value = "`clientstatus`")
    @JSONField(name = "clientstatus")
    @JsonProperty("clientstatus")
    private String clientstatus;
    /**
     * scoreLevel
     */
    @TableField(value = "`scorelevel`")
    @JSONField(name = "scorelevel")
    @JsonProperty("scorelevel")
    private Integer scorelevel;
    /**
     * 手机
     */
    @TableField(value = "`mobile`")
    @JSONField(name = "mobile")
    @JsonProperty("mobile")
    private String mobile;
    /**
     * 最后登录
     */
    @TableField(value = "`last`")
    @JSONField(name = "last")
    @JsonProperty("last")
    private Integer last;
    /**
     * 通讯地址
     */
    @TableField(value = "`address`")
    @JSONField(name = "address")
    @JsonProperty("address")
    private String address;



    /**
     * 设置 [真实用户名]
     */
    public void setRealname(String realname){
        this.realname = realname ;
        this.modify("realname",realname);
    }

    /**
     * 设置 [账号]
     */
    public void setAccount(String account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [角色]
     */
    public void setRole(String role){
        this.role = role ;
        this.modify("role",role);
    }

    /**
     * 设置 [部门编号]
     */
    public void setDept(String dept){
        this.dept = dept ;
        this.modify("dept",dept);
    }

    /**
     * 设置 [累计登录次数]
     */
    public void setVisits(Integer visits){
        this.visits = visits ;
        this.modify("visits",visits);
    }

    /**
     * 设置 [slack]
     */
    public void setSlack(String slack){
        this.slack = slack ;
        this.modify("slack",slack);
    }

    /**
     * 设置 [skype]
     */
    public void setSkype(String skype){
        this.skype = skype ;
        this.modify("skype",skype);
    }

    /**
     * 设置 [score]
     */
    public void setScore(Integer score){
        this.score = score ;
        this.modify("score",score);
    }

    /**
     * 设置 [微信]
     */
    public void setWeixin(String weixin){
        this.weixin = weixin ;
        this.modify("weixin",weixin);
    }

    /**
     * 设置 [入职日期]
     */
    public void setJoin(Timestamp join){
        this.join = join ;
        this.modify("join",join);
    }

    /**
     * 格式化日期 [入职日期]
     */
    public String formatJoin(){
        if (this.join == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(join);
    }
    /**
     * 设置 [电话]
     */
    public void setPhone(String phone){
        this.phone = phone ;
        this.modify("phone",phone);
    }

    /**
     * 设置 [fails]
     */
    public void setFails(Integer fails){
        this.fails = fails ;
        this.modify("fails",fails);
    }

    /**
     * 设置 [邮箱]
     */
    public void setEmail(String email){
        this.email = email ;
        this.modify("email",email);
    }

    /**
     * 设置 [clientLang]
     */
    public void setClientlang(String clientlang){
        this.clientlang = clientlang ;
        this.modify("clientlang",clientlang);
    }

    /**
     * 设置 [逻辑删除标志]
     */
    public void setDeleted(String deleted){
        this.deleted = deleted ;
        this.modify("deleted",deleted);
    }

    /**
     * 设置 [ranzhi]
     */
    public void setRanzhi(String ranzhi){
        this.ranzhi = ranzhi ;
        this.modify("ranzhi",ranzhi);
    }

    /**
     * 设置 [avatar]
     */
    public void setAvatar(String avatar){
        this.avatar = avatar ;
        this.modify("avatar",avatar);
    }

    /**
     * 设置 [源代码账户]
     */
    public void setCommiter(String commiter){
        this.commiter = commiter ;
        this.modify("commiter",commiter);
    }

    /**
     * 设置 [性别]
     */
    public void setGender(String gender){
        this.gender = gender ;
        this.modify("gender",gender);
    }

    /**
     * 设置 [QQ]
     */
    public void setQq(String qq){
        this.qq = qq ;
        this.modify("qq",qq);
    }

    /**
     * 设置 [birthday]
     */
    public void setBirthday(Timestamp birthday){
        this.birthday = birthday ;
        this.modify("birthday",birthday);
    }

    /**
     * 格式化日期 [birthday]
     */
    public String formatBirthday(){
        if (this.birthday == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(birthday);
    }
    /**
     * 设置 [locked]
     */
    public void setLocked(Timestamp locked){
        this.locked = locked ;
        this.modify("locked",locked);
    }

    /**
     * 格式化日期 [locked]
     */
    public String formatLocked(){
        if (this.locked == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(locked);
    }
    /**
     * 设置 [钉钉]
     */
    public void setDingding(String dingding){
        this.dingding = dingding ;
        this.modify("dingding",dingding);
    }

    /**
     * 设置 [ip]
     */
    public void setIp(String ip){
        this.ip = ip ;
        this.modify("ip",ip);
    }

    /**
     * 设置 [whatsapp]
     */
    public void setWhatsapp(String whatsapp){
        this.whatsapp = whatsapp ;
        this.modify("whatsapp",whatsapp);
    }

    /**
     * 设置 [zipcode]
     */
    public void setZipcode(String zipcode){
        this.zipcode = zipcode ;
        this.modify("zipcode",zipcode);
    }

    /**
     * 设置 [密码]
     */
    public void setPassword(String password){
        this.password = password ;
        this.modify("password",password);
    }

    /**
     * 设置 [nickname]
     */
    public void setNickname(String nickname){
        this.nickname = nickname ;
        this.modify("nickname",nickname);
    }

    /**
     * 设置 [clientStatus]
     */
    public void setClientstatus(String clientstatus){
        this.clientstatus = clientstatus ;
        this.modify("clientstatus",clientstatus);
    }

    /**
     * 设置 [scoreLevel]
     */
    public void setScorelevel(Integer scorelevel){
        this.scorelevel = scorelevel ;
        this.modify("scorelevel",scorelevel);
    }

    /**
     * 设置 [手机]
     */
    public void setMobile(String mobile){
        this.mobile = mobile ;
        this.modify("mobile",mobile);
    }

    /**
     * 设置 [最后登录]
     */
    public void setLast(Integer last){
        this.last = last ;
        this.modify("last",last);
    }

    /**
     * 设置 [通讯地址]
     */
    public void setAddress(String address){
        this.address = address ;
        this.modify("address",address);
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
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


