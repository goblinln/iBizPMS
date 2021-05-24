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
 * 服务DTO对象[IbzProjectMemberDTO]
 */
@Data
@ApiModel("项目相关成员")
//@JsonFilter(value = "dtofieldfilter")
public class IbzProjectMemberDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [SECONDMEMBER]
     *
     */
    @JSONField(name = "secondmember")
    @JsonProperty("secondmember")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("团队成员（二）")
    private String secondmember;

    /**
     * 属性 [RD]
     *
     */
    @JSONField(name = "rd")
    @JsonProperty("rd")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("发布负责人")
    private String rd;

    /**
     * 属性 [QD]
     *
     */
    @JSONField(name = "qd")
    @JsonProperty("qd")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("测试负责人")
    private String qd;

    /**
     * 属性 [TEAMEMBERS]
     *
     */
    @JSONField(name = "teamembers")
    @JsonProperty("teamembers")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("全部成员")
    private String teamembers;

    /**
     * 属性 [THIRDMEMBER]
     *
     */
    @JSONField(name = "thirdmember")
    @JsonProperty("thirdmember")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("团队成员（三）")
    private String thirdmember;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 属性 [FRISTMEMBER]
     *
     */
    @JSONField(name = "fristmember")
    @JsonProperty("fristmember")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("团队成员（一）")
    private String fristmember;

    /**
     * 属性 [PO]
     *
     */
    @JSONField(name = "po")
    @JsonProperty("po")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("产品负责人")
    private String po;

    /**
     * 属性 [FOURTHMEMBER]
     *
     */
    @JSONField(name = "fourthmember")
    @JsonProperty("fourthmember")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("团队成员（四）")
    private String fourthmember;

    /**
     * 属性 [PM]
     *
     */
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("项目负责人")
    private String pm;

    /**
     * 属性 [FIFTHMEMBER]
     *
     */
    @JSONField(name = "fifthmember")
    @JsonProperty("fifthmember")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("团队成员（五）")
    private String fifthmember;

    /**
     * 属性 [SIXTHMEMBER]
     *
     */
    @JSONField(name = "sixthmember")
    @JsonProperty("sixthmember")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("团队成员（六）")
    private String sixthmember;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目名称")
    private String name;

    /**
     * 属性 [OPENEDBY]
     *
     */
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁创建")
    private String openedby;

    /**
     * 属性 [ACL]
     *
     */
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    @ApiModelProperty("访问控制")
    private String acl;


    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [ACL]
     */
    public void setAcl(String  acl){
        this.acl = acl ;
        this.modify("acl",acl);
    }


}


