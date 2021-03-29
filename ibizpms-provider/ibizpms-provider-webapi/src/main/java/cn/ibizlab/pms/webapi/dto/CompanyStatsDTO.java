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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[CompanyStatsDTO]
 */
@Data
@ApiModel("公司动态汇总")
public class CompanyStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [LOGINCNT]
     *
     */
    @JSONField(name = "logincnt")
    @JsonProperty("logincnt")
    @ApiModelProperty("登录次数")
    private Integer logincnt;

    /**
     * 属性 [OPENEDSTORYCNT]
     *
     */
    @JSONField(name = "openedstorycnt")
    @JsonProperty("openedstorycnt")
    @ApiModelProperty("新增需求数")
    private Integer openedstorycnt;

    /**
     * 属性 [ZTDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "ztdate" , format="yyyy-MM-dd")
    @JsonProperty("ztdate")
    @ApiModelProperty("日期")
    private Timestamp ztdate;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日志日期")
    private Timestamp date;

    /**
     * 属性 [CLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    @ApiModelProperty("关闭需求数")
    private Integer closedstorycnt;

    /**
     * 属性 [OPENEDBUGCNT]
     *
     */
    @JSONField(name = "openedbugcnt")
    @JsonProperty("openedbugcnt")
    @ApiModelProperty("新增Bug数")
    private Integer openedbugcnt;

    /**
     * 属性 [LOGHOURS]
     *
     */
    @JSONField(name = "loghours")
    @JsonProperty("loghours")
    @ApiModelProperty("日志工时")
    private Integer loghours;

    /**
     * 属性 [DYNAMICCNT]
     *
     */
    @JSONField(name = "dynamiccnt")
    @JsonProperty("dynamiccnt")
    @ApiModelProperty("动态数")
    private Integer dynamiccnt;

    /**
     * 属性 [FINISHEDTASKCNT]
     *
     */
    @JSONField(name = "finishedtaskcnt")
    @JsonProperty("finishedtaskcnt")
    @ApiModelProperty("完成任务数")
    private Integer finishedtaskcnt;

    /**
     * 属性 [RESOLVEDBUGCNT]
     *
     */
    @JSONField(name = "resolvedbugcnt")
    @JsonProperty("resolvedbugcnt")
    @ApiModelProperty("解决Bug数")
    private Integer resolvedbugcnt;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [OPENEDTASKCNT]
     *
     */
    @JSONField(name = "openedtaskcnt")
    @JsonProperty("openedtaskcnt")
    @ApiModelProperty("新增任务数")
    private Integer openedtaskcnt;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("标识")
    private Long id;


    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [COMMENT]
     */
    public void setComment(String  comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }


}


