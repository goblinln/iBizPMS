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
 * 服务DTO对象[CaseStatsDTO]
 */
@Data
@ApiModel("测试用例统计")
//@JsonFilter(value = "dtofieldfilter")
public class CaseStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [PASSCASE]
     *
     */
    @JSONField(name = "passcase")
    @JsonProperty("passcase")
    @ApiModelProperty("通过用例数")
    private Integer passcase;

    /**
     * 属性 [BLOCKEDCASE]
     *
     */
    @JSONField(name = "blockedcase")
    @JsonProperty("blockedcase")
    @ApiModelProperty("阻塞用例数")
    private Integer blockedcase;

    /**
     * 属性 [TOTALRUNCASE]
     *
     */
    @JSONField(name = "totalruncase")
    @JsonProperty("totalruncase")
    @ApiModelProperty("总执行数")
    private Integer totalruncase;

    /**
     * 属性 [FAILCASE]
     *
     */
    @JSONField(name = "failcase")
    @JsonProperty("failcase")
    @ApiModelProperty("失败用例数")
    private Integer failcase;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("用例标题")
    private String title;

    /**
     * 属性 [TOTALCASE]
     *
     */
    @JSONField(name = "totalcase")
    @JsonProperty("totalcase")
    @ApiModelProperty("总用例数")
    private Integer totalcase;

    /**
     * 属性 [PASSRATE]
     *
     */
    @JSONField(name = "passrate")
    @JsonProperty("passrate")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("用例通过率")
    private String passrate;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用例编号")
    private Long id;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("模块名称")
    private String modulename;

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
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("模块")
    private Long module;


    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [MODULE]
     */
    public void setModule(Long  module){
        this.module = module ;
        this.modify("module",module);
    }


}


