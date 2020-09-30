package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 * 服务DTO对象[IbzMyTerritoryDTO]
 */
@Data
public class IbzMyTerritoryDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [FAILS]
     *
     */
    @JSONField(name = "fails")
    @JsonProperty("fails")
    private Integer fails;

    /**
     * 属性 [ADDRESS]
     *
     */
    @JSONField(name = "address")
    @JsonProperty("address")
    @Size(min = 0, max = 120, message = "内容长度必须小于等于[120]")
    private String address;

    /**
     * 属性 [PASSWORD]
     *
     */
    @JSONField(name = "password")
    @JsonProperty("password")
    @Size(min = 0, max = 32, message = "内容长度必须小于等于[32]")
    private String password;

    /**
     * 属性 [WEIXIN]
     *
     */
    @JSONField(name = "weixin")
    @JsonProperty("weixin")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String weixin;

    /**
     * 属性 [DINGDING]
     *
     */
    @JSONField(name = "dingding")
    @JsonProperty("dingding")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String dingding;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String account;

    /**
     * 属性 [RANZHI]
     *
     */
    @JSONField(name = "ranzhi")
    @JsonProperty("ranzhi")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String ranzhi;

    /**
     * 属性 [SLACK]
     *
     */
    @JSONField(name = "slack")
    @JsonProperty("slack")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String slack;

    /**
     * 属性 [REALNAME]
     *
     */
    @JSONField(name = "realname")
    @JsonProperty("realname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String realname;

    /**
     * 属性 [LOCKED]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "locked" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("locked")
    private Timestamp locked;

    /**
     * 属性 [SCORELEVEL]
     *
     */
    @JSONField(name = "scorelevel")
    @JsonProperty("scorelevel")
    private Integer scorelevel;

    /**
     * 属性 [AVATAR]
     *
     */
    @JSONField(name = "avatar")
    @JsonProperty("avatar")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String avatar;

    /**
     * 属性 [ZIPCODE]
     *
     */
    @JSONField(name = "zipcode")
    @JsonProperty("zipcode")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    private String zipcode;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    private Integer dept;

    /**
     * 属性 [COMMITER]
     *
     */
    @JSONField(name = "commiter")
    @JsonProperty("commiter")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String commiter;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    private String deleted;

    /**
     * 属性 [LAST]
     *
     */
    @JSONField(name = "last")
    @JsonProperty("last")
    private Integer last;

    /**
     * 属性 [SKYPE]
     *
     */
    @JSONField(name = "skype")
    @JsonProperty("skype")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String skype;

    /**
     * 属性 [SCORE]
     *
     */
    @JSONField(name = "score")
    @JsonProperty("score")
    private Integer score;

    /**
     * 属性 [WHATSAPP]
     *
     */
    @JSONField(name = "whatsapp")
    @JsonProperty("whatsapp")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String whatsapp;

    /**
     * 属性 [VISITS]
     *
     */
    @JSONField(name = "visits")
    @JsonProperty("visits")
    private Integer visits;

    /**
     * 属性 [MOBILE]
     *
     */
    @JSONField(name = "mobile")
    @JsonProperty("mobile")
    @Size(min = 0, max = 11, message = "内容长度必须小于等于[11]")
    private String mobile;

    /**
     * 属性 [CLIENTLANG]
     *
     */
    @JSONField(name = "clientlang")
    @JsonProperty("clientlang")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    private String clientlang;

    /**
     * 属性 [JOIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "join" , format="yyyy-MM-dd")
    @JsonProperty("join")
    private Timestamp join;

    /**
     * 属性 [IP]
     *
     */
    @JSONField(name = "ip")
    @JsonProperty("ip")
    @Size(min = 0, max = 15, message = "内容长度必须小于等于[15]")
    private String ip;

    /**
     * 属性 [EMAIL]
     *
     */
    @JSONField(name = "email")
    @JsonProperty("email")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String email;

    /**
     * 属性 [NICKNAME]
     *
     */
    @JSONField(name = "nickname")
    @JsonProperty("nickname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String nickname;

    /**
     * 属性 [PHONE]
     *
     */
    @JSONField(name = "phone")
    @JsonProperty("phone")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    private String phone;

    /**
     * 属性 [BIRTHDAY]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "birthday" , format="yyyy-MM-dd")
    @JsonProperty("birthday")
    private Timestamp birthday;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 属性 [QQ]
     *
     */
    @JSONField(name = "qq")
    @JsonProperty("qq")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    private String qq;

    /**
     * 属性 [GENDER]
     *
     */
    @JSONField(name = "gender")
    @JsonProperty("gender")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String gender;

    /**
     * 属性 [ROLE]
     *
     */
    @JSONField(name = "role")
    @JsonProperty("role")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String role;

    /**
     * 属性 [CLIENTSTATUS]
     *
     */
    @JSONField(name = "clientstatus")
    @JsonProperty("clientstatus")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String clientstatus;

    /**
     * 属性 [MYTASKS]
     *
     */
    @JSONField(name = "mytasks")
    @JsonProperty("mytasks")
    private Integer mytasks;

    /**
     * 属性 [MYBUGS]
     *
     */
    @JSONField(name = "mybugs")
    @JsonProperty("mybugs")
    private Integer mybugs;

    /**
     * 属性 [MYEBUGS]
     *
     */
    @JSONField(name = "myebugs")
    @JsonProperty("myebugs")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String myebugs;

    /**
     * 属性 [MYSTORYS]
     *
     */
    @JSONField(name = "mystorys")
    @JsonProperty("mystorys")
    private Integer mystorys;

    /**
     * 属性 [PRODUCTS]
     *
     */
    @JSONField(name = "products")
    @JsonProperty("products")
    private Integer products;

    /**
     * 属性 [EPROJECTS]
     *
     */
    @JSONField(name = "eprojects")
    @JsonProperty("eprojects")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String eprojects;

    /**
     * 属性 [PROJECTS]
     *
     */
    @JSONField(name = "projects")
    @JsonProperty("projects")
    private Integer projects;

    /**
     * 属性 [MYETASKS]
     *
     */
    @JSONField(name = "myetasks")
    @JsonProperty("myetasks")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String myetasks;

    /**
     * 属性 [MYTODOCNT]
     *
     */
    @JSONField(name = "mytodocnt")
    @JsonProperty("mytodocnt")
    private Integer mytodocnt;

    /**
     * 属性 [MYFAVORITESTORYS]
     *
     */
    @JSONField(name = "myfavoritestorys")
    @JsonProperty("myfavoritestorys")
    private Integer myfavoritestorys;

    /**
     * 属性 [MYFAVORITEBUGS]
     *
     */
    @JSONField(name = "myfavoritebugs")
    @JsonProperty("myfavoritebugs")
    private Integer myfavoritebugs;

    /**
     * 属性 [MYFAVORITETASKS]
     *
     */
    @JSONField(name = "myfavoritetasks")
    @JsonProperty("myfavoritetasks")
    private Integer myfavoritetasks;

    /**
     * 属性 [MYFAVORITES]
     *
     */
    @JSONField(name = "myfavorites")
    @JsonProperty("myfavorites")
    private Integer myfavorites;

    /**
     * 属性 [MYTERRITORYCNT]
     *
     */
    @JSONField(name = "myterritorycnt")
    @JsonProperty("myterritorycnt")
    private Integer myterritorycnt;


    /**
     * 设置 [FAILS]
     */
    public void setFails(Integer  fails){
        this.fails = fails ;
        this.modify("fails",fails);
    }

    /**
     * 设置 [ADDRESS]
     */
    public void setAddress(String  address){
        this.address = address ;
        this.modify("address",address);
    }

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
     * 设置 [DINGDING]
     */
    public void setDingding(String  dingding){
        this.dingding = dingding ;
        this.modify("dingding",dingding);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [RANZHI]
     */
    public void setRanzhi(String  ranzhi){
        this.ranzhi = ranzhi ;
        this.modify("ranzhi",ranzhi);
    }

    /**
     * 设置 [SLACK]
     */
    public void setSlack(String  slack){
        this.slack = slack ;
        this.modify("slack",slack);
    }

    /**
     * 设置 [REALNAME]
     */
    public void setRealname(String  realname){
        this.realname = realname ;
        this.modify("realname",realname);
    }

    /**
     * 设置 [LOCKED]
     */
    public void setLocked(Timestamp  locked){
        this.locked = locked ;
        this.modify("locked",locked);
    }

    /**
     * 设置 [SCORELEVEL]
     */
    public void setScorelevel(Integer  scorelevel){
        this.scorelevel = scorelevel ;
        this.modify("scorelevel",scorelevel);
    }

    /**
     * 设置 [AVATAR]
     */
    public void setAvatar(String  avatar){
        this.avatar = avatar ;
        this.modify("avatar",avatar);
    }

    /**
     * 设置 [ZIPCODE]
     */
    public void setZipcode(String  zipcode){
        this.zipcode = zipcode ;
        this.modify("zipcode",zipcode);
    }

    /**
     * 设置 [DEPT]
     */
    public void setDept(Integer  dept){
        this.dept = dept ;
        this.modify("dept",dept);
    }

    /**
     * 设置 [COMMITER]
     */
    public void setCommiter(String  commiter){
        this.commiter = commiter ;
        this.modify("commiter",commiter);
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
     * 设置 [SCORE]
     */
    public void setScore(Integer  score){
        this.score = score ;
        this.modify("score",score);
    }

    /**
     * 设置 [WHATSAPP]
     */
    public void setWhatsapp(String  whatsapp){
        this.whatsapp = whatsapp ;
        this.modify("whatsapp",whatsapp);
    }

    /**
     * 设置 [VISITS]
     */
    public void setVisits(Integer  visits){
        this.visits = visits ;
        this.modify("visits",visits);
    }

    /**
     * 设置 [MOBILE]
     */
    public void setMobile(String  mobile){
        this.mobile = mobile ;
        this.modify("mobile",mobile);
    }

    /**
     * 设置 [CLIENTLANG]
     */
    public void setClientlang(String  clientlang){
        this.clientlang = clientlang ;
        this.modify("clientlang",clientlang);
    }

    /**
     * 设置 [JOIN]
     */
    public void setJoin(Timestamp  join){
        this.join = join ;
        this.modify("join",join);
    }

    /**
     * 设置 [IP]
     */
    public void setIp(String  ip){
        this.ip = ip ;
        this.modify("ip",ip);
    }

    /**
     * 设置 [EMAIL]
     */
    public void setEmail(String  email){
        this.email = email ;
        this.modify("email",email);
    }

    /**
     * 设置 [NICKNAME]
     */
    public void setNickname(String  nickname){
        this.nickname = nickname ;
        this.modify("nickname",nickname);
    }

    /**
     * 设置 [PHONE]
     */
    public void setPhone(String  phone){
        this.phone = phone ;
        this.modify("phone",phone);
    }

    /**
     * 设置 [BIRTHDAY]
     */
    public void setBirthday(Timestamp  birthday){
        this.birthday = birthday ;
        this.modify("birthday",birthday);
    }

    /**
     * 设置 [QQ]
     */
    public void setQq(String  qq){
        this.qq = qq ;
        this.modify("qq",qq);
    }

    /**
     * 设置 [GENDER]
     */
    public void setGender(String  gender){
        this.gender = gender ;
        this.modify("gender",gender);
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


}


