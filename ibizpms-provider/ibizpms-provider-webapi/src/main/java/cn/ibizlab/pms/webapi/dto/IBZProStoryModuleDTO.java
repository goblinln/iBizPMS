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
 * 服务DTO对象[IBZProStoryModuleDTO]
 */
@Data
@ApiModel("需求模块（iBizSys）")
@JsonFilter(value = "dtofieldfilter")
public class IBZProStoryModuleDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [GRADE]
     *
     */
    @JSONField(name = "grade")
    @JsonProperty("grade")
    @ApiModelProperty("级别")
    private Integer grade;

    /**
     * 属性 [COLLECTOR]
     *
     */
    @JSONField(name = "collector")
    @JsonProperty("collector")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("collector")
    private String collector;

    /**
     * 属性 [OWNER]
     *
     */
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("owner")
    private String owner;

    /**
     * 属性 [IBIZ_STORYTYPE]
     *
     */
    @JSONField(name = "ibiz_storytype")
    @JsonProperty("ibiz_storytype")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("需求模块类型")
    private String ibizStorytype;

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
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("类型")
    private String type;

    /**
     * 属性 [SHORT]
     *
     */
    @JSONField(name = "ibizshort")
    @JsonProperty("ibizshort")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("简称")
    private String ibizshort;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[名称]不允许为空!")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("名称")
    private String name;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [PATH]
     *
     */
    @JSONField(name = "path")
    @JsonProperty("path")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("路径")
    private String path;

    /**
     * 属性 [IBIZ_ID]
     *
     */
    @JSONField(name = "ibizid")
    @JsonProperty("ibizid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("IBIZ标识")
    private String ibizid;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品")
    private String productname;

    /**
     * 属性 [ROOT]
     *
     */
    @JSONField(name = "root")
    @JsonProperty("root")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long root;

    /**
     * 属性 [PARENT]
     *
     */
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long parent;

    /**
     * 属性 [MDEPTID]
     *
     */
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("部门标识")
    private String mdeptid;

    /**
     * 属性 [MDEPTNAME]
     *
     */
    @JSONField(name = "mdeptname")
    @JsonProperty("mdeptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String mdeptname;

    /**
     * 属性 [ORGID]
     *
     */
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("组织机构标识")
    private String orgid;

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
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;

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
     * 设置 [GRADE]
     */
    public void setGrade(Integer  grade){
        this.grade = grade ;
        this.modify("grade",grade);
    }

    /**
     * 设置 [COLLECTOR]
     */
    public void setCollector(String  collector){
        this.collector = collector ;
        this.modify("collector",collector);
    }

    /**
     * 设置 [OWNER]
     */
    public void setOwner(String  owner){
        this.owner = owner ;
        this.modify("owner",owner);
    }

    /**
     * 设置 [IBIZ_STORYTYPE]
     */
    public void setIbizStorytype(String  ibizStorytype){
        this.ibizStorytype = ibizStorytype ;
        this.modify("ibiz_storytype",ibizStorytype);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [SHORT]
     */
    public void setIbizshort(String  ibizshort){
        this.ibizshort = ibizshort ;
        this.modify("short",ibizshort);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [PATH]
     */
    public void setPath(String  path){
        this.path = path ;
        this.modify("path",path);
    }

    /**
     * 设置 [IBIZ_ID]
     */
    public void setIbizid(String  ibizid){
        this.ibizid = ibizid ;
        this.modify("ibiz_id",ibizid);
    }

    /**
     * 设置 [ROOT]
     */
    public void setRoot(Long  root){
        this.root = root ;
        this.modify("root",root);
    }

    /**
     * 设置 [PARENT]
     */
    public void setParent(Long  parent){
        this.parent = parent ;
        this.modify("parent",parent);
    }


}


