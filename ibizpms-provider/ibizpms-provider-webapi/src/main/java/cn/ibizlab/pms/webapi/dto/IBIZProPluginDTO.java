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
 * 服务DTO对象[IBIZProPluginDTO]
 */
@Data
@ApiModel("系统插件")
@JsonFilter(value = "dtofieldfilter")
public class IBIZProPluginDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [VERSION]
     *
     */
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本")
    private Integer version;
    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("类型")
    private String type;
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
     * 属性 [DOWNLOADCOUNT]
     *
     */
    @JSONField(name = "downloadcount")
    @JsonProperty("downloadcount")
    @ApiModelProperty("总下载量")
    private Integer downloadcount;
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
     * 属性 [DOWNLOADURL]
     *
     */
    @JSONField(name = "downloadurl")
    @JsonProperty("downloadurl")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("最新版本下载地址")
    private String downloadurl;
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
     * 属性 [TAG]
     *
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("标签")
    private String tag;
    /**
     * 属性 [SCORE]
     *
     */
    @JSONField(name = "score")
    @JsonProperty("score")
    @ApiModelProperty("总评分")
    private Integer score;
    /**
     * 属性 [IBIZPRO_PLUGINNAME]
     *
     */
    @JSONField(name = "ibizpropluginname")
    @JsonProperty("ibizpropluginname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("系统插件名称")
    private String ibizpropluginname;
    /**
     * 属性 [IBIZPRO_PLUGINID]
     *
     */
    @JSONField(name = "ibizpropluginid")
    @JsonProperty("ibizpropluginid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统插件标识")
    private String ibizpropluginid;
    /**
     * 属性 [KEYWORD]
     *
     */
    @JSONField(name = "keyword")
    @JsonProperty("keyword")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("关键字")
    private String keyword;
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
     * 属性 [COMMENTCOUNT]
     *
     */
    @JSONField(name = "commentcount")
    @JsonProperty("commentcount")
    @ApiModelProperty("总评论数")
    private Integer commentcount;

    /**
     * 设置 [VERSION]
     */
    public void setVersion(Integer  version){
        this.version = version ;
        this.modify("version",version);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [DOWNLOADURL]
     */
    public void setDownloadurl(String  downloadurl){
        this.downloadurl = downloadurl ;
        this.modify("downloadurl",downloadurl);
    }

    /**
     * 设置 [TAG]
     */
    public void setTag(String  tag){
        this.tag = tag ;
        this.modify("tag",tag);
    }

    /**
     * 设置 [IBIZPRO_PLUGINNAME]
     */
    public void setIbizpropluginname(String  ibizpropluginname){
        this.ibizpropluginname = ibizpropluginname ;
        this.modify("ibizpro_pluginname",ibizpropluginname);
    }

    /**
     * 设置 [KEYWORD]
     */
    public void setKeyword(String  keyword){
        this.keyword = keyword ;
        this.modify("keyword",keyword);
    }


}


