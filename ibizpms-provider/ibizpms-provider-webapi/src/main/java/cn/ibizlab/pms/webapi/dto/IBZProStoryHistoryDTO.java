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
 * 服务DTO对象[IBZProStoryHistoryDTO]
 */
@Data
@ApiModel("需求操作历史")
@JsonFilter(value = "dtofieldfilter")
public class IBZProStoryHistoryDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [CREATEBY]
     *
     */
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁创建")
    private String createby;

    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [DIFF]
     *
     */
    @JSONField(name = "diff")
    @JsonProperty("diff")
    @Size(min = 0, max = 16777215, message = "内容长度必须小于等于[16777215]")
    @ApiModelProperty("不同")
    private String diff;

    /**
     * 属性 [HISTORYSN]
     *
     */
    @JSONField(name = "historysn")
    @JsonProperty("historysn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("操作历史编号")
    private Long historysn;

    /**
     * 属性 [FIELD]
     *
     */
    @JSONField(name = "field")
    @JsonProperty("field")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("字段")
    private String field;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    /**
     * 属性 [NEW]
     *
     */
    @JSONField(name = "ibiznew")
    @JsonProperty("ibiznew")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("新值")
    private String ibiznew;

    /**
     * 属性 [OLD]
     *
     */
    @JSONField(name = "old")
    @JsonProperty("old")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("旧值")
    private String old;

    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [ACTION]
     *
     */
    @JSONField(name = "action")
    @JsonProperty("action")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long action;


    /**
     * 设置 [DIFF]
     */
    public void setDiff(String  diff){
        this.diff = diff ;
        this.modify("diff",diff);
    }

    /**
     * 设置 [HISTORYSN]
     */
    public void setHistorysn(Long  historysn){
        this.historysn = historysn ;
        this.modify("historysn",historysn);
    }

    /**
     * 设置 [FIELD]
     */
    public void setField(String  field){
        this.field = field ;
        this.modify("field",field);
    }

    /**
     * 设置 [NEW]
     */
    public void setIbiznew(String  ibiznew){
        this.ibiznew = ibiznew ;
        this.modify("new",ibiznew);
    }

    /**
     * 设置 [OLD]
     */
    public void setOld(String  old){
        this.old = old ;
        this.modify("old",old);
    }

    /**
     * 设置 [ACTION]
     */
    public void setAction(Long  action){
        this.action = action ;
        this.modify("action",action);
    }


}


