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
 * 服务DTO对象[IbizproProductDailyDTO]
 */
@Data
@ApiModel("产品日报")
@JsonFilter(value = "dtofieldfilter")
public class IbizproProductDailyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束日期")
    private Timestamp end;
    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
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
     * 属性 [IBIZPRO_PRODUCTDAILYNAME]
     *
     */
    @JSONField(name = "ibizproproductdailyname")
    @JsonProperty("ibizproproductdailyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("产品日报名称")
    private String ibizproproductdailyname;
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
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 属性 [TASKS]
     *
     */
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("任务")
    private String tasks;
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
     * 属性 [IBIZPRO_PRODUCTDAILYID]
     *
     */
    @JSONField(name = "ibizproproductdailyid")
    @JsonProperty("ibizproproductdailyid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品日报标识")
    private Long ibizproproductdailyid;
    /**
     * 属性 [TOTALESTIMATES]
     *
     */
    @JSONField(name = "totalestimates")
    @JsonProperty("totalestimates")
    @ApiModelProperty("总工时")
    private Double totalestimates;
    /**
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始日期")
    private Timestamp begin;
    /**
     * 属性 [PO]
     *
     */
    @JSONField(name = "po")
    @JsonProperty("po")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("产品负责人")
    private String po;
    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品名称")
    private String productname;
    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品")
    private Long product;

    /**
     * 设置 [END]
     */
    public void setEnd(Timestamp  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [IBIZPRO_PRODUCTDAILYNAME]
     */
    public void setIbizproproductdailyname(String  ibizproproductdailyname){
        this.ibizproproductdailyname = ibizproproductdailyname ;
        this.modify("ibizpro_productdailyname",ibizproproductdailyname);
    }

    /**
     * 设置 [TASKS]
     */
    public void setTasks(String  tasks){
        this.tasks = tasks ;
        this.modify("tasks",tasks);
    }

    /**
     * 设置 [TOTALESTIMATES]
     */
    public void setTotalestimates(Double  totalestimates){
        this.totalestimates = totalestimates ;
        this.modify("totalestimates",totalestimates);
    }

    /**
     * 设置 [BEGIN]
     */
    public void setBegin(Timestamp  begin){
        this.begin = begin ;
        this.modify("begin",begin);
    }

    /**
     * 设置 [PO]
     */
    public void setPo(String  po){
        this.po = po ;
        this.modify("po",po);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }


}


