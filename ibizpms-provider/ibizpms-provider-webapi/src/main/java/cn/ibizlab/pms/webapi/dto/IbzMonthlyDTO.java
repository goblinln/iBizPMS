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
 * 服务DTO对象[IbzMonthlyDTO]
 */
@Data
public class IbzMonthlyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [IBZ_MONTHLYID]
     *
     */
    @JSONField(name = "ibzmonthlyid")
    @JsonProperty("ibzmonthlyid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String ibzmonthlyid;

    /**
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String createman;

    /**
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String updateman;

    /**
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 属性 [IBZ_MONTHLYNAME]
     *
     */
    @JSONField(name = "ibzmonthlyname")
    @JsonProperty("ibzmonthlyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    private String ibzmonthlyname;

    /**
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;

    /**
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String account;

    /**
     * 属性 [WORKTHISMONTH]
     *
     */
    @JSONField(name = "workthismonth")
    @JsonProperty("workthismonth")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    private String workthismonth;

    /**
     * 属性 [PLANSNEXTMONTH]
     *
     */
    @JSONField(name = "plansnextmonth")
    @JsonProperty("plansnextmonth")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    private String plansnextmonth;

    /**
     * 属性 [REPORTTO]
     *
     */
    @JSONField(name = "reportto")
    @JsonProperty("reportto")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String reportto;

    /**
     * 属性 [MAILTO]
     *
     */
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    private String mailto;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    private String comment;

    /**
     * 属性 [THISMONTHTASK]
     *
     */
    @JSONField(name = "thismonthtask")
    @JsonProperty("thismonthtask")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    private String thismonthtask;

    /**
     * 属性 [NEXTMONTHPLANSTASK]
     *
     */
    @JSONField(name = "nextmonthplanstask")
    @JsonProperty("nextmonthplanstask")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    private String nextmonthplanstask;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String files;

    /**
     * 属性 [ISSUBMIT]
     *
     */
    @JSONField(name = "issubmit")
    @JsonProperty("issubmit")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String issubmit;


    /**
     * 设置 [IBZ_MONTHLYNAME]
     */
    public void setIbzmonthlyname(String  ibzmonthlyname){
        this.ibzmonthlyname = ibzmonthlyname ;
        this.modify("ibz_monthlyname",ibzmonthlyname);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [WORKTHISMONTH]
     */
    public void setWorkthismonth(String  workthismonth){
        this.workthismonth = workthismonth ;
        this.modify("workthismonth",workthismonth);
    }

    /**
     * 设置 [PLANSNEXTMONTH]
     */
    public void setPlansnextmonth(String  plansnextmonth){
        this.plansnextmonth = plansnextmonth ;
        this.modify("plansnextmonth",plansnextmonth);
    }

    /**
     * 设置 [REPORTTO]
     */
    public void setReportto(String  reportto){
        this.reportto = reportto ;
        this.modify("reportto",reportto);
    }

    /**
     * 设置 [MAILTO]
     */
    public void setMailto(String  mailto){
        this.mailto = mailto ;
        this.modify("mailto",mailto);
    }

    /**
     * 设置 [COMMENT]
     */
    public void setComment(String  comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }

    /**
     * 设置 [THISMONTHTASK]
     */
    public void setThismonthtask(String  thismonthtask){
        this.thismonthtask = thismonthtask ;
        this.modify("thismonthtask",thismonthtask);
    }

    /**
     * 设置 [NEXTMONTHPLANSTASK]
     */
    public void setNextmonthplanstask(String  nextmonthplanstask){
        this.nextmonthplanstask = nextmonthplanstask ;
        this.modify("nextmonthplanstask",nextmonthplanstask);
    }

    /**
     * 设置 [ISSUBMIT]
     */
    public void setIssubmit(String  issubmit){
        this.issubmit = issubmit ;
        this.modify("issubmit",issubmit);
    }


}


