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
 * 服务DTO对象[PSSysSFPubDTO]
 */
@Data
@ApiModel("后台服务架构")
@JsonFilter(value = "dtofieldfilter")
public class PSSysSFPubDTO extends DTOClient implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [USERTAG]
     *
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 属性 [PSSYSTEMID]
     *
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统")
    private String pssystemid;

    /**
     * 属性 [PUBFOLDER]
     *
     */
    @JSONField(name = "pubfolder")
    @JsonProperty("pubfolder")
    @Size(min = 0, max = 500, message = "内容长度必须小于等于[500]")
    @ApiModelProperty("服务目录")
    private String pubfolder;

    /**
     * 属性 [PSSFSTYLEVERID]
     *
     */
    @JSONField(name = "pssfstyleverid")
    @JsonProperty("pssfstyleverid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("服务框架扩展")
    private String pssfstyleverid;

    /**
     * 属性 [USERTAG4]
     *
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @Size(min = 0, max = 50, message = "内容长度必须小于等于[50]")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 属性 [PUBTAG4]
     *
     */
    @JSONField(name = "pubtag4")
    @JsonProperty("pubtag4")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("发布标记4")
    private String pubtag4;

    /**
     * 属性 [PSSFSTYLEID]
     *
     */
    @JSONField(name = "pssfstyleid")
    @JsonProperty("pssfstyleid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("服务框架")
    private String pssfstyleid;

    /**
     * 属性 [REMOVEFLAG]
     *
     */
    @JSONField(name = "removeflag")
    @JsonProperty("removeflag")
    @ApiModelProperty("删除模式")
    private Integer removeflag;

    /**
     * 属性 [SUBSYSPKGFLAG]
     *
     */
    @JSONField(name = "subsyspkgflag")
    @JsonProperty("subsyspkgflag")
    @ApiModelProperty("引用系统组件")
    private Integer subsyspkgflag;

    /**
     * 属性 [DEFAULTPUB]
     *
     */
    @JSONField(name = "defaultpub")
    @JsonProperty("defaultpub")
    @ApiModelProperty("默认后台服务")
    private Integer defaultpub;

    /**
     * 属性 [DOCPSSFSTYLENAME]
     *
     */
    @JSONField(name = "docpssfstylename")
    @JsonProperty("docpssfstylename")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("文档模板样式")
    private String docpssfstylename;

    /**
     * 属性 [USERTAG3]
     *
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @Size(min = 0, max = 50, message = "内容长度必须小于等于[50]")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 属性 [PSSFSTYLEPARAMNAME]
     *
     */
    @JSONField(name = "pssfstyleparamname")
    @JsonProperty("pssfstyleparamname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("服务框架参数")
    private String pssfstyleparamname;

    /**
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 属性 [CODENAME]
     *
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("代码名称")
    private String codename;

    /**
     * 属性 [USERTAG2]
     *
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 属性 [PSSYSSFPUBNAME]
     *
     */
    @JSONField(name = "pssyssfpubname")
    @JsonProperty("pssyssfpubname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("后台服务架构名称")
    private String pssyssfpubname;

    /**
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 属性 [PUBTAG]
     *
     */
    @JSONField(name = "pubtag")
    @JsonProperty("pubtag")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("发布标记")
    private String pubtag;

    /**
     * 属性 [BASECLSPKGCODENAME]
     *
     */
    @JSONField(name = "baseclspkgcodename")
    @JsonProperty("baseclspkgcodename")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("基类代码包名")
    private String baseclspkgcodename;

    /**
     * 属性 [VERSTR]
     *
     */
    @JSONField(name = "verstr")
    @JsonProperty("verstr")
    @Size(min = 0, max = 50, message = "内容长度必须小于等于[50]")
    @ApiModelProperty("版本号")
    private String verstr;

    /**
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 属性 [PUBTAG3]
     *
     */
    @JSONField(name = "pubtag3")
    @JsonProperty("pubtag3")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("发布标记3")
    private String pubtag3;

    /**
     * 属性 [PSSFSTYLEPARAMID]
     *
     */
    @JSONField(name = "pssfstyleparamid")
    @JsonProperty("pssfstyleparamid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("服务框架参数")
    private String pssfstyleparamid;

    /**
     * 属性 [DOCPSSFSTYLEID]
     *
     */
    @JSONField(name = "docpssfstyleid")
    @JsonProperty("docpssfstyleid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("文档模板样式")
    private String docpssfstyleid;

    /**
     * 属性 [MEMO]
     *
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 属性 [PKGCODENAME]
     *
     */
    @JSONField(name = "pkgcodename")
    @JsonProperty("pkgcodename")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("代码包名")
    private String pkgcodename;

    /**
     * 属性 [PSSYSSFPUBID]
     *
     */
    @JSONField(name = "pssyssfpubid")
    @JsonProperty("pssyssfpubid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("后台服务架构标识")
    private String pssyssfpubid;

    /**
     * 属性 [PSSYSTEMNAME]
     *
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统")
    private String pssystemname;

    /**
     * 属性 [CONTENTTYPE]
     *
     */
    @JSONField(name = "contenttype")
    @JsonProperty("contenttype")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("发布内容类型")
    private String contenttype;

    /**
     * 属性 [USERCAT]
     *
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 属性 [PUBTAG2]
     *
     */
    @JSONField(name = "pubtag2")
    @JsonProperty("pubtag2")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("发布标记2")
    private String pubtag2;

    /**
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 属性 [STYLEPARAMS]
     *
     */
    @JSONField(name = "styleparams")
    @JsonProperty("styleparams")
    @Size(min = 0, max = 4000, message = "内容长度必须小于等于[4000]")
    @ApiModelProperty("服务框架参数")
    private String styleparams;

    /**
     * 属性 [PPSSYSSFPUBNAME]
     *
     */
    @JSONField(name = "ppssyssfpubname")
    @JsonProperty("ppssyssfpubname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("父后台服务体系")
    private String ppssyssfpubname;

    /**
     * 属性 [PPSSYSSFPUBID]
     *
     */
    @JSONField(name = "ppssyssfpubid")
    @JsonProperty("ppssyssfpubid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("父后台服务体系")
    private String ppssyssfpubid;


    /**
     * 设置 [USERTAG]
     */
    public void setUsertag(String  usertag){
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [PSSYSTEMID]
     */
    public void setPssystemid(String  pssystemid){
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [PUBFOLDER]
     */
    public void setPubfolder(String  pubfolder){
        this.pubfolder = pubfolder ;
        this.modify("pubfolder",pubfolder);
    }

    /**
     * 设置 [PSSFSTYLEVERID]
     */
    public void setPssfstyleverid(String  pssfstyleverid){
        this.pssfstyleverid = pssfstyleverid ;
        this.modify("pssfstyleverid",pssfstyleverid);
    }

    /**
     * 设置 [USERTAG4]
     */
    public void setUsertag4(String  usertag4){
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [PUBTAG4]
     */
    public void setPubtag4(String  pubtag4){
        this.pubtag4 = pubtag4 ;
        this.modify("pubtag4",pubtag4);
    }

    /**
     * 设置 [PSSFSTYLEID]
     */
    public void setPssfstyleid(String  pssfstyleid){
        this.pssfstyleid = pssfstyleid ;
        this.modify("pssfstyleid",pssfstyleid);
    }

    /**
     * 设置 [REMOVEFLAG]
     */
    public void setRemoveflag(Integer  removeflag){
        this.removeflag = removeflag ;
        this.modify("removeflag",removeflag);
    }

    /**
     * 设置 [SUBSYSPKGFLAG]
     */
    public void setSubsyspkgflag(Integer  subsyspkgflag){
        this.subsyspkgflag = subsyspkgflag ;
        this.modify("subsyspkgflag",subsyspkgflag);
    }

    /**
     * 设置 [DEFAULTPUB]
     */
    public void setDefaultpub(Integer  defaultpub){
        this.defaultpub = defaultpub ;
        this.modify("defaultpub",defaultpub);
    }

    /**
     * 设置 [DOCPSSFSTYLENAME]
     */
    public void setDocpssfstylename(String  docpssfstylename){
        this.docpssfstylename = docpssfstylename ;
        this.modify("docpssfstylename",docpssfstylename);
    }

    /**
     * 设置 [USERTAG3]
     */
    public void setUsertag3(String  usertag3){
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [PSSFSTYLEPARAMNAME]
     */
    public void setPssfstyleparamname(String  pssfstyleparamname){
        this.pssfstyleparamname = pssfstyleparamname ;
        this.modify("pssfstyleparamname",pssfstyleparamname);
    }

    /**
     * 设置 [CODENAME]
     */
    public void setCodename(String  codename){
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [USERTAG2]
     */
    public void setUsertag2(String  usertag2){
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [PSSYSSFPUBNAME]
     */
    public void setPssyssfpubname(String  pssyssfpubname){
        this.pssyssfpubname = pssyssfpubname ;
        this.modify("pssyssfpubname",pssyssfpubname);
    }

    /**
     * 设置 [PUBTAG]
     */
    public void setPubtag(String  pubtag){
        this.pubtag = pubtag ;
        this.modify("pubtag",pubtag);
    }

    /**
     * 设置 [BASECLSPKGCODENAME]
     */
    public void setBaseclspkgcodename(String  baseclspkgcodename){
        this.baseclspkgcodename = baseclspkgcodename ;
        this.modify("baseclspkgcodename",baseclspkgcodename);
    }

    /**
     * 设置 [VERSTR]
     */
    public void setVerstr(String  verstr){
        this.verstr = verstr ;
        this.modify("verstr",verstr);
    }

    /**
     * 设置 [PUBTAG3]
     */
    public void setPubtag3(String  pubtag3){
        this.pubtag3 = pubtag3 ;
        this.modify("pubtag3",pubtag3);
    }

    /**
     * 设置 [PSSFSTYLEPARAMID]
     */
    public void setPssfstyleparamid(String  pssfstyleparamid){
        this.pssfstyleparamid = pssfstyleparamid ;
        this.modify("pssfstyleparamid",pssfstyleparamid);
    }

    /**
     * 设置 [DOCPSSFSTYLEID]
     */
    public void setDocpssfstyleid(String  docpssfstyleid){
        this.docpssfstyleid = docpssfstyleid ;
        this.modify("docpssfstyleid",docpssfstyleid);
    }

    /**
     * 设置 [MEMO]
     */
    public void setMemo(String  memo){
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [PKGCODENAME]
     */
    public void setPkgcodename(String  pkgcodename){
        this.pkgcodename = pkgcodename ;
        this.modify("pkgcodename",pkgcodename);
    }

    /**
     * 设置 [PSSYSTEMNAME]
     */
    public void setPssystemname(String  pssystemname){
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [CONTENTTYPE]
     */
    public void setContenttype(String  contenttype){
        this.contenttype = contenttype ;
        this.modify("contenttype",contenttype);
    }

    /**
     * 设置 [USERCAT]
     */
    public void setUsercat(String  usercat){
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [PUBTAG2]
     */
    public void setPubtag2(String  pubtag2){
        this.pubtag2 = pubtag2 ;
        this.modify("pubtag2",pubtag2);
    }

    /**
     * 设置 [STYLEPARAMS]
     */
    public void setStyleparams(String  styleparams){
        this.styleparams = styleparams ;
        this.modify("styleparams",styleparams);
    }

    /**
     * 设置 [PPSSYSSFPUBID]
     */
    public void setPpssyssfpubid(String  ppssyssfpubid){
        this.ppssyssfpubid = ppssyssfpubid ;
        this.modify("ppssyssfpubid",ppssyssfpubid);
    }


}


