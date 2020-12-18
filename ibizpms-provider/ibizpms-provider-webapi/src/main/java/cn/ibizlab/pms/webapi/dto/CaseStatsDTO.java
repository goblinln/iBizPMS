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
 * 服务DTO对象[CaseStatsDTO]
 */
@Data
public class CaseStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String title;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long module;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String modulename;

    /**
     * 属性 [TOTALCASE]
     *
     */
    @JSONField(name = "totalcase")
    @JsonProperty("totalcase")
    private Integer totalcase;

    /**
     * 属性 [PASSCASE]
     *
     */
    @JSONField(name = "passcase")
    @JsonProperty("passcase")
    private Integer passcase;

    /**
     * 属性 [FAILCASE]
     *
     */
    @JSONField(name = "failcase")
    @JsonProperty("failcase")
    private Integer failcase;

    /**
     * 属性 [BLOCKEDCASE]
     *
     */
    @JSONField(name = "blockedcase")
    @JsonProperty("blockedcase")
    private Integer blockedcase;

    /**
     * 属性 [TOTALRUNCASE]
     *
     */
    @JSONField(name = "totalruncase")
    @JsonProperty("totalruncase")
    private Integer totalruncase;

    /**
     * 属性 [PASSRATE]
     *
     */
    @JSONField(name = "passrate")
    @JsonProperty("passrate")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String passrate;


    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [MODULE]
     */
    public void setModule(Long  module){
        this.module = module ;
        this.modify("module",module);
    }


}


