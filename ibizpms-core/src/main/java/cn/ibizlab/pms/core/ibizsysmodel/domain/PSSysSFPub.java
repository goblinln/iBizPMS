package cn.ibizlab.pms.core.ibizsysmodel.domain;

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

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [后台服务架构] 对象
 */
@Data
public class PSSysSFPub extends EntityClient implements Serializable {

    /**
     * 后台服务架构名称
     */
    @JSONField(name = "pssyssfpubname")
    @JsonProperty("pssyssfpubname")
    private String pssyssfpubname;

    /**
     * 后台服务架构标识
     */
    @DEField(isKeyField=true)
    @JSONField(name = "pssyssfpubid")
    @JsonProperty("pssyssfpubid")
    private String pssyssfpubid;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    private String codename;

    /**
     * 基类代码包名
     */
    @JSONField(name = "baseclspkgcodename")
    @JsonProperty("baseclspkgcodename")
    private String baseclspkgcodename;

    /**
     * 发布内容类型
     */
    @JSONField(name = "contenttype")
    @JsonProperty("contenttype")
    private String contenttype;

    /**
     * 默认后台服务
     */
    @JSONField(name = "defaultpub")
    @JsonProperty("defaultpub")
    private Integer defaultpub;

    /**
     * 文档模板样式
     */
    @JSONField(name = "docpssfstyleid")
    @JsonProperty("docpssfstyleid")
    private String docpssfstyleid;

    /**
     * 文档模板样式
     */
    @JSONField(name = "docpssfstylename")
    @JsonProperty("docpssfstylename")
    private String docpssfstylename;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;

    /**
     * 服务框架
     */
    @JSONField(name = "pssfstyleid")
    @JsonProperty("pssfstyleid")
    private String pssfstyleid;

    /**
     * 服务框架参数
     */
    @JSONField(name = "pssfstyleparamid")
    @JsonProperty("pssfstyleparamid")
    private String pssfstyleparamid;

    /**
     * 代码包名
     */
    @JSONField(name = "pkgcodename")
    @JsonProperty("pkgcodename")
    private String pkgcodename;

    /**
     * 服务框架扩展
     */
    @JSONField(name = "pssfstyleverid")
    @JsonProperty("pssfstyleverid")
    private String pssfstyleverid;

    /**
     * 服务框架参数
     */
    @JSONField(name = "pssfstyleparamname")
    @JsonProperty("pssfstyleparamname")
    private String pssfstyleparamname;

    /**
     * 服务目录
     */
    @JSONField(name = "pubfolder")
    @JsonProperty("pubfolder")
    private String pubfolder;

    /**
     * 发布标记
     */
    @JSONField(name = "pubtag")
    @JsonProperty("pubtag")
    private String pubtag;

    /**
     * 发布标记3
     */
    @JSONField(name = "pubtag3")
    @JsonProperty("pubtag3")
    private String pubtag3;

    /**
     * 发布标记2
     */
    @JSONField(name = "pubtag2")
    @JsonProperty("pubtag2")
    private String pubtag2;

    /**
     * 发布标记4
     */
    @JSONField(name = "pubtag4")
    @JsonProperty("pubtag4")
    private String pubtag4;

    /**
     * 删除模式
     */
    @JSONField(name = "removeflag")
    @JsonProperty("removeflag")
    private Integer removeflag;

    /**
     * 服务框架参数
     */
    @JSONField(name = "styleparams")
    @JsonProperty("styleparams")
    private String styleparams;

    /**
     * 引用系统组件
     */
    @JSONField(name = "subsyspkgflag")
    @JsonProperty("subsyspkgflag")
    private Integer subsyspkgflag;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    private String usercat;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    private String usertag;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    private String usertag2;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    private String usertag4;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    private String usertag3;

    /**
     * 版本号
     */
    @JSONField(name = "verstr")
    @JsonProperty("verstr")
    private String verstr;

    /**
     * 父后台服务体系
     */
    @JSONField(name = "ppssyssfpubid")
    @JsonProperty("ppssyssfpubid")
    private String ppssyssfpubid;

    /**
     * 父后台服务体系
     */
    @JSONField(name = "ppssyssfpubname")
    @JsonProperty("ppssyssfpubname")
    private String ppssyssfpubname;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    private String pssystemid;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    private String pssystemname;


    /**
     * 
     */
    @JSONField(name = "ppssyssfpub")
    @JsonProperty("ppssyssfpub")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysSFPub ppssyssfpub;




    /**
     * 设置 [后台服务架构名称]
     */
    public void setPssyssfpubname(String pssyssfpubname){
        this.pssyssfpubname = pssyssfpubname ;
        this.modify("pssyssfpubname",pssyssfpubname);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename){
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [基类代码包名]
     */
    public void setBaseclspkgcodename(String baseclspkgcodename){
        this.baseclspkgcodename = baseclspkgcodename ;
        this.modify("baseclspkgcodename",baseclspkgcodename);
    }

    /**
     * 设置 [发布内容类型]
     */
    public void setContenttype(String contenttype){
        this.contenttype = contenttype ;
        this.modify("contenttype",contenttype);
    }

    /**
     * 设置 [默认后台服务]
     */
    public void setDefaultpub(Integer defaultpub){
        this.defaultpub = defaultpub ;
        this.modify("defaultpub",defaultpub);
    }

    /**
     * 设置 [文档模板样式]
     */
    public void setDocpssfstyleid(String docpssfstyleid){
        this.docpssfstyleid = docpssfstyleid ;
        this.modify("docpssfstyleid",docpssfstyleid);
    }

    /**
     * 设置 [文档模板样式]
     */
    public void setDocpssfstylename(String docpssfstylename){
        this.docpssfstylename = docpssfstylename ;
        this.modify("docpssfstylename",docpssfstylename);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo){
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [服务框架]
     */
    public void setPssfstyleid(String pssfstyleid){
        this.pssfstyleid = pssfstyleid ;
        this.modify("pssfstyleid",pssfstyleid);
    }

    /**
     * 设置 [服务框架参数]
     */
    public void setPssfstyleparamid(String pssfstyleparamid){
        this.pssfstyleparamid = pssfstyleparamid ;
        this.modify("pssfstyleparamid",pssfstyleparamid);
    }

    /**
     * 设置 [代码包名]
     */
    public void setPkgcodename(String pkgcodename){
        this.pkgcodename = pkgcodename ;
        this.modify("pkgcodename",pkgcodename);
    }

    /**
     * 设置 [服务框架扩展]
     */
    public void setPssfstyleverid(String pssfstyleverid){
        this.pssfstyleverid = pssfstyleverid ;
        this.modify("pssfstyleverid",pssfstyleverid);
    }

    /**
     * 设置 [服务框架参数]
     */
    public void setPssfstyleparamname(String pssfstyleparamname){
        this.pssfstyleparamname = pssfstyleparamname ;
        this.modify("pssfstyleparamname",pssfstyleparamname);
    }

    /**
     * 设置 [服务目录]
     */
    public void setPubfolder(String pubfolder){
        this.pubfolder = pubfolder ;
        this.modify("pubfolder",pubfolder);
    }

    /**
     * 设置 [发布标记]
     */
    public void setPubtag(String pubtag){
        this.pubtag = pubtag ;
        this.modify("pubtag",pubtag);
    }

    /**
     * 设置 [发布标记3]
     */
    public void setPubtag3(String pubtag3){
        this.pubtag3 = pubtag3 ;
        this.modify("pubtag3",pubtag3);
    }

    /**
     * 设置 [发布标记2]
     */
    public void setPubtag2(String pubtag2){
        this.pubtag2 = pubtag2 ;
        this.modify("pubtag2",pubtag2);
    }

    /**
     * 设置 [发布标记4]
     */
    public void setPubtag4(String pubtag4){
        this.pubtag4 = pubtag4 ;
        this.modify("pubtag4",pubtag4);
    }

    /**
     * 设置 [删除模式]
     */
    public void setRemoveflag(Integer removeflag){
        this.removeflag = removeflag ;
        this.modify("removeflag",removeflag);
    }

    /**
     * 设置 [服务框架参数]
     */
    public void setStyleparams(String styleparams){
        this.styleparams = styleparams ;
        this.modify("styleparams",styleparams);
    }

    /**
     * 设置 [引用系统组件]
     */
    public void setSubsyspkgflag(Integer subsyspkgflag){
        this.subsyspkgflag = subsyspkgflag ;
        this.modify("subsyspkgflag",subsyspkgflag);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat){
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag){
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2){
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4){
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3){
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [版本号]
     */
    public void setVerstr(String verstr){
        this.verstr = verstr ;
        this.modify("verstr",verstr);
    }

    /**
     * 设置 [父后台服务体系]
     */
    public void setPpssyssfpubid(String ppssyssfpubid){
        this.ppssyssfpubid = ppssyssfpubid ;
        this.modify("ppssyssfpubid",ppssyssfpubid);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid){
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname){
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }


}


