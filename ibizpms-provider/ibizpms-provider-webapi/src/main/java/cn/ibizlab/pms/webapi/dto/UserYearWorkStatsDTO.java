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
 * 服务DTO对象[UserYearWorkStatsDTO]
 */
@Data
@ApiModel("用户年度工作内容统计")
//@JsonFilter(value = "dtofieldfilter")
public class UserYearWorkStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [PASSWORD]
     *
     */
    @JSONField(name = "password")
    @JsonProperty("password")
    @Size(min = 0, max = 32, message = "内容长度必须小于等于[32]")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 属性 [YEARPRODUCTCNT]
     *
     */
    @JSONField(name = "yearproductcnt")
    @JsonProperty("yearproductcnt")
    @ApiModelProperty("累计参与产品数")
    private Integer yearproductcnt;

    /**
     * 属性 [YEARBUGCNT]
     *
     */
    @JSONField(name = "yearbugcnt")
    @JsonProperty("yearbugcnt")
    @ApiModelProperty("累计创建Bug数")
    private Integer yearbugcnt;

    /**
     * 属性 [WEIXIN]
     *
     */
    @JSONField(name = "weixin")
    @JsonProperty("weixin")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("微信")
    private String weixin;

    /**
     * 属性 [YEARCASECNT]
     *
     */
    @JSONField(name = "yearcasecnt")
    @JsonProperty("yearcasecnt")
    @ApiModelProperty("累计创建用例数")
    private Integer yearcasecnt;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("账号")
    private String account;

    /**
     * 属性 [QQ]
     *
     */
    @JSONField(name = "qq")
    @JsonProperty("qq")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("QQ")
    private String qq;

    /**
     * 属性 [RANZHI]
     *
     */
    @JSONField(name = "ranzhi")
    @JsonProperty("ranzhi")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("ranzhi")
    private String ranzhi;

    /**
     * 属性 [NICKNAME]
     *
     */
    @JSONField(name = "nickname")
    @JsonProperty("nickname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("nickname")
    private String nickname;

    /**
     * 属性 [AVATAR]
     *
     */
    @JSONField(name = "avatar")
    @JsonProperty("avatar")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("avatar")
    private String avatar;

    /**
     * 属性 [MONTHFINISHTASK]
     *
     */
    @JSONField(name = "monthfinishtask")
    @JsonProperty("monthfinishtask")
    @ApiModelProperty("月完成任务数")
    private Integer monthfinishtask;

    /**
     * 属性 [FAILS]
     *
     */
    @JSONField(name = "fails")
    @JsonProperty("fails")
    @ApiModelProperty("fails")
    private Integer fails;

    /**
     * 属性 [JOIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("join")
    @ApiModelProperty("入职日期")
    private Timestamp join;

    /**
     * 属性 [YEARSTORYCNT]
     *
     */
    @JSONField(name = "yearstorycnt")
    @JsonProperty("yearstorycnt")
    @ApiModelProperty("累计创建需求数")
    private Integer yearstorycnt;

    /**
     * 属性 [YEARPLANCNT]
     *
     */
    @JSONField(name = "yearplancnt")
    @JsonProperty("yearplancnt")
    @ApiModelProperty("累计创建计划数")
    private Integer yearplancnt;

    /**
     * 属性 [DINGDING]
     *
     */
    @JSONField(name = "dingding")
    @JsonProperty("dingding")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("钉钉")
    private String dingding;

    /**
     * 属性 [IP]
     *
     */
    @JSONField(name = "ip")
    @JsonProperty("ip")
    @Size(min = 0, max = 15, message = "内容长度必须小于等于[15]")
    @ApiModelProperty("ip")
    private String ip;

    /**
     * 属性 [YEARACTIONCNT]
     *
     */
    @JSONField(name = "yearactioncnt")
    @JsonProperty("yearactioncnt")
    @ApiModelProperty("累计动态数")
    private Integer yearactioncnt;

    /**
     * 属性 [MOBILE]
     *
     */
    @JSONField(name = "mobile")
    @JsonProperty("mobile")
    @Size(min = 0, max = 11, message = "内容长度必须小于等于[11]")
    @ApiModelProperty("手机")
    private String mobile;

    /**
     * 属性 [WHATSAPP]
     *
     */
    @JSONField(name = "whatsapp")
    @JsonProperty("whatsapp")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("whatsapp")
    private String whatsapp;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用户编号")
    private Long id;

    /**
     * 属性 [EMAIL]
     *
     */
    @JSONField(name = "email")
    @JsonProperty("email")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 属性 [YEARESTIMATECNT]
     *
     */
    @JSONField(name = "yearestimatecnt")
    @JsonProperty("yearestimatecnt")
    @ApiModelProperty("累计工时数")
    private Integer yearestimatecnt;

    /**
     * 属性 [CLIENTLANG]
     *
     */
    @JSONField(name = "clientlang")
    @JsonProperty("clientlang")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("clientLang")
    private String clientlang;

    /**
     * 属性 [MONTESTIMATE]
     *
     */
    @JSONField(name = "montestimate")
    @JsonProperty("montestimate")
    @ApiModelProperty("月累计工时")
    private Integer montestimate;

    /**
     * 属性 [REALNAME]
     *
     */
    @JSONField(name = "realname")
    @JsonProperty("realname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("真实用户名")
    private String realname;

    /**
     * 属性 [COMMITER]
     *
     */
    @JSONField(name = "commiter")
    @JsonProperty("commiter")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("源代码账户")
    private String commiter;

    /**
     * 属性 [SLACK]
     *
     */
    @JSONField(name = "slack")
    @JsonProperty("slack")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("slack")
    private String slack;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("逻辑删除标志")
    private String deleted;

    /**
     * 属性 [LAST]
     *
     */
    @JSONField(name = "last")
    @JsonProperty("last")
    @ApiModelProperty("最后登录")
    private Integer last;

    /**
     * 属性 [CURYEAR]
     *
     */
    @JSONField(name = "curyear")
    @JsonProperty("curyear")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("年度")
    private String curyear;

    /**
     * 属性 [JUDGEROLE]
     *
     */
    @JSONField(name = "judgerole")
    @JsonProperty("judgerole")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("判断角色")
    private String judgerole;

    /**
     * 属性 [SKYPE]
     *
     */
    @JSONField(name = "skype")
    @JsonProperty("skype")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("skype")
    private String skype;

    /**
     * 属性 [BIRTHDAY]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "birthday" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("birthday")
    @ApiModelProperty("birthday")
    private Timestamp birthday;

    /**
     * 属性 [PHONE]
     *
     */
    @JSONField(name = "phone")
    @JsonProperty("phone")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("电话")
    private String phone;

    /**
     * 属性 [YEARVISITS]
     *
     */
    @JSONField(name = "yearvisits")
    @JsonProperty("yearvisits")
    @ApiModelProperty("累计登录次数")
    private Integer yearvisits;

    /**
     * 属性 [SCORE]
     *
     */
    @JSONField(name = "score")
    @JsonProperty("score")
    @ApiModelProperty("score")
    private Integer score;

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
     * 属性 [CLIENTSTATUS]
     *
     */
    @JSONField(name = "clientstatus")
    @JsonProperty("clientstatus")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    @ApiModelProperty("clientStatus")
    private String clientstatus;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("部门编号")
    private String dept;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("标题")
    private String title;

    /**
     * 属性 [CURMONTH]
     *
     */
    @JSONField(name = "curmonth")
    @JsonProperty("curmonth")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("当前月")
    private String curmonth;

    /**
     * 属性 [MONTRESOLVEDBUG]
     *
     */
    @JSONField(name = "montresolvedbug")
    @JsonProperty("montresolvedbug")
    @ApiModelProperty("月解决Bug数")
    private Integer montresolvedbug;

    /**
     * 属性 [ADDRESS]
     *
     */
    @JSONField(name = "address")
    @JsonProperty("address")
    @Size(min = 0, max = 120, message = "内容长度必须小于等于[120]")
    @ApiModelProperty("通讯地址")
    private String address;

    /**
     * 属性 [SCORELEVEL]
     *
     */
    @JSONField(name = "scorelevel")
    @JsonProperty("scorelevel")
    @ApiModelProperty("scoreLevel")
    private Integer scorelevel;

    /**
     * 属性 [VISITS]
     *
     */
    @JSONField(name = "visits")
    @JsonProperty("visits")
    @ApiModelProperty("累计登录次数")
    private Integer visits;

    /**
     * 属性 [YEARLOGCNT]
     *
     */
    @JSONField(name = "yearlogcnt")
    @JsonProperty("yearlogcnt")
    @ApiModelProperty("累计日志数")
    private Integer yearlogcnt;

    /**
     * 属性 [LOCKED]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "locked" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("locked")
    @ApiModelProperty("locked")
    private Timestamp locked;

    /**
     * 属性 [GENDER]
     *
     */
    @JSONField(name = "gender")
    @JsonProperty("gender")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 属性 [ZIPCODE]
     *
     */
    @JSONField(name = "zipcode")
    @JsonProperty("zipcode")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("zipcode")
    private String zipcode;


    /**
     * 设置 [PASSWORD]
     */
    public void setPassword(String  password){
        this.password = password ;
        this.modify("password",password);
    }

    /**
     * 设置 [WEIXIN]
     */
    public void setWeixin(String  weixin){
        this.weixin = weixin ;
        this.modify("weixin",weixin);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [QQ]
     */
    public void setQq(String  qq){
        this.qq = qq ;
        this.modify("qq",qq);
    }

    /**
     * 设置 [RANZHI]
     */
    public void setRanzhi(String  ranzhi){
        this.ranzhi = ranzhi ;
        this.modify("ranzhi",ranzhi);
    }

    /**
     * 设置 [NICKNAME]
     */
    public void setNickname(String  nickname){
        this.nickname = nickname ;
        this.modify("nickname",nickname);
    }

    /**
     * 设置 [AVATAR]
     */
    public void setAvatar(String  avatar){
        this.avatar = avatar ;
        this.modify("avatar",avatar);
    }

    /**
     * 设置 [FAILS]
     */
    public void setFails(Integer  fails){
        this.fails = fails ;
        this.modify("fails",fails);
    }

    /**
     * 设置 [JOIN]
     */
    public void setJoin(Timestamp  join){
        this.join = join ;
        this.modify("join",join);
    }

    /**
     * 设置 [DINGDING]
     */
    public void setDingding(String  dingding){
        this.dingding = dingding ;
        this.modify("dingding",dingding);
    }

    /**
     * 设置 [IP]
     */
    public void setIp(String  ip){
        this.ip = ip ;
        this.modify("ip",ip);
    }

    /**
     * 设置 [MOBILE]
     */
    public void setMobile(String  mobile){
        this.mobile = mobile ;
        this.modify("mobile",mobile);
    }

    /**
     * 设置 [WHATSAPP]
     */
    public void setWhatsapp(String  whatsapp){
        this.whatsapp = whatsapp ;
        this.modify("whatsapp",whatsapp);
    }

    /**
     * 设置 [EMAIL]
     */
    public void setEmail(String  email){
        this.email = email ;
        this.modify("email",email);
    }

    /**
     * 设置 [CLIENTLANG]
     */
    public void setClientlang(String  clientlang){
        this.clientlang = clientlang ;
        this.modify("clientlang",clientlang);
    }

    /**
     * 设置 [REALNAME]
     */
    public void setRealname(String  realname){
        this.realname = realname ;
        this.modify("realname",realname);
    }

    /**
     * 设置 [COMMITER]
     */
    public void setCommiter(String  commiter){
        this.commiter = commiter ;
        this.modify("commiter",commiter);
    }

    /**
     * 设置 [SLACK]
     */
    public void setSlack(String  slack){
        this.slack = slack ;
        this.modify("slack",slack);
    }

    /**
     * 设置 [DELETED]
     */
    public void setDeleted(String  deleted){
        this.deleted = deleted ;
        this.modify("deleted",deleted);
    }

    /**
     * 设置 [LAST]
     */
    public void setLast(Integer  last){
        this.last = last ;
        this.modify("last",last);
    }

    /**
     * 设置 [SKYPE]
     */
    public void setSkype(String  skype){
        this.skype = skype ;
        this.modify("skype",skype);
    }

    /**
     * 设置 [BIRTHDAY]
     */
    public void setBirthday(Timestamp  birthday){
        this.birthday = birthday ;
        this.modify("birthday",birthday);
    }

    /**
     * 设置 [PHONE]
     */
    public void setPhone(String  phone){
        this.phone = phone ;
        this.modify("phone",phone);
    }

    /**
     * 设置 [SCORE]
     */
    public void setScore(Integer  score){
        this.score = score ;
        this.modify("score",score);
    }

    /**
     * 设置 [ROLE]
     */
    public void setRole(String  role){
        this.role = role ;
        this.modify("role",role);
    }

    /**
     * 设置 [CLIENTSTATUS]
     */
    public void setClientstatus(String  clientstatus){
        this.clientstatus = clientstatus ;
        this.modify("clientstatus",clientstatus);
    }

    /**
     * 设置 [DEPT]
     */
    public void setDept(String  dept){
        this.dept = dept ;
        this.modify("dept",dept);
    }

    /**
     * 设置 [ADDRESS]
     */
    public void setAddress(String  address){
        this.address = address ;
        this.modify("address",address);
    }

    /**
     * 设置 [SCORELEVEL]
     */
    public void setScorelevel(Integer  scorelevel){
        this.scorelevel = scorelevel ;
        this.modify("scorelevel",scorelevel);
    }

    /**
     * 设置 [VISITS]
     */
    public void setVisits(Integer  visits){
        this.visits = visits ;
        this.modify("visits",visits);
    }

    /**
     * 设置 [LOCKED]
     */
    public void setLocked(Timestamp  locked){
        this.locked = locked ;
        this.modify("locked",locked);
    }

    /**
     * 设置 [GENDER]
     */
    public void setGender(String  gender){
        this.gender = gender ;
        this.modify("gender",gender);
    }

    /**
     * 设置 [ZIPCODE]
     */
    public void setZipcode(String  zipcode){
        this.zipcode = zipcode ;
        this.modify("zipcode",zipcode);
    }


}


