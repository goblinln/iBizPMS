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
 * 服务DTO对象[IbzAgentDTO]
 */
@Data
@ApiModel("代理")
@JsonFilter(value = "dtofieldfilter")
public class IbzAgentDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [CREATEMANNAME]
     *
     */
    @JSONField(name = "createmanname")
    @JsonProperty("createmanname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("创建人姓名")
    private String createmanname;

    /**
     * 属性 [AGENTEND]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "agentend" , format="yyyy-MM-dd")
    @JsonProperty("agentend")
    @ApiModelProperty("代理结束日期")
    private Timestamp agentend;

    /**
     * 属性 [IBZ_AGENTID]
     *
     */
    @JSONField(name = "ibzagentid")
    @JsonProperty("ibzagentid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("代理标识")
    private Long ibzagentid;

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
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 属性 [AGENTUSER]
     *
     */
    @JSONField(name = "agentuser")
    @JsonProperty("agentuser")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("代理用户")
    private String agentuser;

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
     * 属性 [AGENTBEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "agentbegin" , format="yyyy-MM-dd")
    @JsonProperty("agentbegin")
    @ApiModelProperty("代理开始日期")
    private Timestamp agentbegin;

    /**
     * 属性 [IBZ_AGENTNAME]
     *
     */
    @JSONField(name = "ibzagentname")
    @JsonProperty("ibzagentname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("代理名称")
    private String ibzagentname;


    /**
     * 设置 [AGENTEND]
     */
    public void setAgentend(Timestamp  agentend){
        this.agentend = agentend ;
        this.modify("agentend",agentend);
    }

    /**
     * 设置 [AGENTUSER]
     */
    public void setAgentuser(String  agentuser){
        this.agentuser = agentuser ;
        this.modify("agentuser",agentuser);
    }

    /**
     * 设置 [AGENTBEGIN]
     */
    public void setAgentbegin(Timestamp  agentbegin){
        this.agentbegin = agentbegin ;
        this.modify("agentbegin",agentbegin);
    }

    /**
     * 设置 [IBZ_AGENTNAME]
     */
    public void setIbzagentname(String  ibzagentname){
        this.ibzagentname = ibzagentname ;
        this.modify("ibz_agentname",ibzagentname);
    }


}


