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
 * 服务DTO对象[GroupDTO]
 */
@Data
@ApiModel("群组")
@JsonFilter(value = "dtofieldfilter")
public class GroupDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ACL]
     *
     */
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("acl")
    private String acl;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("分组描述")
    private String desc;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[分组名称]不允许为空!")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("分组名称")
    private String name;

    /**
     * 属性 [ROLE]
     *
     */
    @JSONField(name = "role")
    @JsonProperty("role")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("role")
    private String role;

    /**
     * 属性 [GROUPSN]
     *
     */
    @JSONField(name = "groupsn")
    @JsonProperty("groupsn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("群组编号")
    private Long groupsn;


    /**
     * 设置 [ACL]
     */
    public void setAcl(String  acl){
        this.acl = acl ;
        this.modify("acl",acl);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [ROLE]
     */
    public void setRole(String  role){
        this.role = role ;
        this.modify("role",role);
    }

    /**
     * 设置 [GROUPSN]
     */
    public void setGroupsn(Long  groupsn){
        this.groupsn = groupsn ;
        this.modify("groupsn",groupsn);
    }


}


