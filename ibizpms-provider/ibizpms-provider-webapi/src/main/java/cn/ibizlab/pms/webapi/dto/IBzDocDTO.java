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
 * 服务DTO对象[IBzDocDTO]
 */
@Data
@ApiModel("文档")
//@JsonFilter(value = "dtofieldfilter")
public class IBzDocDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [IBZ_DOCID]
     *
     */
    @JSONField(name = "ibzdocid")
    @JsonProperty("ibzdocid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("文档标识")
    private String ibzdocid;

    /**
     * 属性 [ADDEDBY]
     *
     */
    @JSONField(name = "addedby")
    @JsonProperty("addedby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁添加")
    private String addedby;

    /**
     * 属性 [EDITEDBY]
     *
     */
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String editedby;

    /**
     * 属性 [ADDEDDATE]
     *
     */
    @JSONField(name = "addeddate")
    @JsonProperty("addeddate")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("添加时间")
    private String addeddate;

    /**
     * 属性 [SIZE]
     *
     */
    @JSONField(name = "size")
    @JsonProperty("size")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("大小")
    private String size;

    /**
     * 属性 [LIB]
     *
     */
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("所属文档库")
    private String lib;

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
     * 属性 [IBZ_DOCNAME]
     *
     */
    @JSONField(name = "ibzdocname")
    @JsonProperty("ibzdocname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("文档名称")
    private String ibzdocname;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("排序")
    private String order;

    /**
     * 属性 [ISCOLLECT]
     *
     */
    @JSONField(name = "iscollect")
    @JsonProperty("iscollect")
    @ApiModelProperty("是否已收藏")
    private Integer iscollect;

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
     * 属性 [EDITEDDATE]
     *
     */
    @JSONField(name = "editeddate")
    @JsonProperty("editeddate")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("更新时间")
    private String editeddate;

    /**
     * 属性 [OBJECTTYPE]
     *
     */
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("对象类型")
    private String objecttype;

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
     * 设置 [ADDEDBY]
     */
    public void setAddedby(String  addedby){
        this.addedby = addedby ;
        this.modify("addedby",addedby);
    }

    /**
     * 设置 [EDITEDBY]
     */
    public void setEditedby(String  editedby){
        this.editedby = editedby ;
        this.modify("editedby",editedby);
    }

    /**
     * 设置 [ADDEDDATE]
     */
    public void setAddeddate(String  addeddate){
        this.addeddate = addeddate ;
        this.modify("addeddate",addeddate);
    }

    /**
     * 设置 [SIZE]
     */
    public void setSize(String  size){
        this.size = size ;
        this.modify("size",size);
    }

    /**
     * 设置 [LIB]
     */
    public void setLib(String  lib){
        this.lib = lib ;
        this.modify("lib",lib);
    }

    /**
     * 设置 [IBZ_DOCNAME]
     */
    public void setIbzdocname(String  ibzdocname){
        this.ibzdocname = ibzdocname ;
        this.modify("ibz_docname",ibzdocname);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(String  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [ISCOLLECT]
     */
    public void setIscollect(Integer  iscollect){
        this.iscollect = iscollect ;
        this.modify("iscollect",iscollect);
    }

    /**
     * 设置 [EDITEDDATE]
     */
    public void setEditeddate(String  editeddate){
        this.editeddate = editeddate ;
        this.modify("editeddate",editeddate);
    }

    /**
     * 设置 [OBJECTTYPE]
     */
    public void setObjecttype(String  objecttype){
        this.objecttype = objecttype ;
        this.modify("objecttype",objecttype);
    }


}


