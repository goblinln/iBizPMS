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
 * 服务DTO对象[SysUpdateLogDTO]
 */
@Data
@ApiModel("更新日志")
@JsonFilter(value = "dtofieldfilter")
public class SysUpdateLogDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 属性 [SYS_UPDATE_LOGID]
     *
     */
    @JSONField(name = "sysupdatelogid")
    @JsonProperty("sysupdatelogid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统更新日志标识")
    private String sysupdatelogid;

    /**
     * 属性 [UPDATEBRANCH]
     *
     */
    @JSONField(name = "updatebranch")
    @JsonProperty("updatebranch")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新平台")
    private String updatebranch;

    /**
     * 属性 [UPDESC]
     *
     */
    @JSONField(name = "updesc")
    @JsonProperty("updesc")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("更新说明")
    private String updesc;

    /**
     * 属性 [SYS_UPDATE_LOGNAME]
     *
     */
    @JSONField(name = "sysupdatelogname")
    @JsonProperty("sysupdatelogname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("更新名称")
    private String sysupdatelogname;

    /**
     * 属性 [LATESTUPDATE]
     *
     */
    @JSONField(name = "latestupdate")
    @JsonProperty("latestupdate")
    @ApiModelProperty("最新更新")
    private Integer latestupdate;

    /**
     * 属性 [UPDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "update" , format="yyyy-MM-dd")
    @JsonProperty("update")
    @ApiModelProperty("更新日期")
    private Timestamp update;


    /**
     * 设置 [UPDATEBRANCH]
     */
    public void setUpdatebranch(String  updatebranch){
        this.updatebranch = updatebranch ;
        this.modify("updatebranch",updatebranch);
    }

    /**
     * 设置 [UPDESC]
     */
    public void setUpdesc(String  updesc){
        this.updesc = updesc ;
        this.modify("updesc",updesc);
    }

    /**
     * 设置 [SYS_UPDATE_LOGNAME]
     */
    public void setSysupdatelogname(String  sysupdatelogname){
        this.sysupdatelogname = sysupdatelogname ;
        this.modify("sys_update_logname",sysupdatelogname);
    }

    /**
     * 设置 [LATESTUPDATE]
     */
    public void setLatestupdate(Integer  latestupdate){
        this.latestupdate = latestupdate ;
        this.modify("latestupdate",latestupdate);
    }

    /**
     * 设置 [UPDATE]
     */
    public void setUpdate(Timestamp  update){
        this.update = update ;
        this.modify("update",update);
    }


}


