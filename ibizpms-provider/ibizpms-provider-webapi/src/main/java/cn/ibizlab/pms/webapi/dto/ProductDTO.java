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
 * 服务DTO对象[ProductDTO]
 */
@Data
@ApiModel("产品")
//@JsonFilter(value = "dtofieldfilter")
public class ProductDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ORGID]
     *
     */
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("组织标识")
    private String orgid;

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
     * 属性 [ISTOP]
     *
     */
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [QD]
     *
     */
    @JSONField(name = "qd")
    @JsonProperty("qd")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("测试负责人")
    private String qd;

    /**
     * 属性 [PRODUCTCLASS]
     *
     */
    @JSONField(name = "productclass")
    @JsonProperty("productclass")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("产品分类")
    private String productclass;

    /**
     * 属性 [UNCONFIRMBUGCNT]
     *
     */
    @JSONField(name = "unconfirmbugcnt")
    @JsonProperty("unconfirmbugcnt")
    @ApiModelProperty("未确认Bug数")
    private Integer unconfirmbugcnt;

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
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[产品名称]不允许为空!")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 属性 [MOBIMAGE]
     *
     */
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    @Size(min = 0, max = 4000, message = "内容长度必须小于等于[4000]")
    @ApiModelProperty("移动端图片")
    private String mobimage;

    /**
     * 属性 [TESTTASKCNT]
     *
     */
    @JSONField(name = "testtaskcnt")
    @JsonProperty("testtaskcnt")
    @ApiModelProperty("测试单数")
    private Integer testtaskcnt;

    /**
     * 属性 [TESTSUITECNT]
     *
     */
    @JSONField(name = "testsuitecnt")
    @JsonProperty("testsuitecnt")
    @ApiModelProperty("套件数")
    private Integer testsuitecnt;

    /**
     * 属性 [PRODUCTPLANCNT]
     *
     */
    @JSONField(name = "productplancnt")
    @JsonProperty("productplancnt")
    @ApiModelProperty("计划总数")
    private Integer productplancnt;

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
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [CLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    @ApiModelProperty("已关闭需求")
    private Integer closedstorycnt;

    /**
     * 属性 [RELATEDBUGCNT]
     *
     */
    @JSONField(name = "relatedbugcnt")
    @JsonProperty("relatedbugcnt")
    @ApiModelProperty("相关Bug数")
    private Integer relatedbugcnt;

    /**
     * 属性 [WHITELIST]
     *
     */
    @JSONField(name = "whitelist")
    @JsonProperty("whitelist")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("分组白名单")
    private String whitelist;

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
     * 属性 [RELEASECNT]
     *
     */
    @JSONField(name = "releasecnt")
    @JsonProperty("releasecnt")
    @ApiModelProperty("发布总数")
    private Integer releasecnt;

    /**
     * 属性 [RD]
     *
     */
    @JSONField(name = "rd")
    @JsonProperty("rd")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("发布负责人")
    private String rd;

    /**
     * 属性 [POPK]
     *
     */
    @JSONField(name = "popk")
    @JsonProperty("popk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("产品负责人（选择）")
    private String popk;

    /**
     * 属性 [NOTCLOSEDBUGCNT]
     *
     */
    @JSONField(name = "notclosedbugcnt")
    @JsonProperty("notclosedbugcnt")
    @ApiModelProperty("未关闭Bug数")
    private Integer notclosedbugcnt;

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
     * 属性 [SUPPROREPORT]
     *
     */
    @JSONField(name = "supproreport")
    @JsonProperty("supproreport")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("支持产品汇报")
    private String supproreport;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("产品类型")
    private String type;

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
     * 属性 [PO]
     *
     */
    @JSONField(name = "po")
    @JsonProperty("po")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("产品负责人")
    private String po;

    /**
     * 属性 [QDPK]
     *
     */
    @JSONField(name = "qdpk")
    @JsonProperty("qdpk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("测试负责人（选择）")
    private String qdpk;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("产品描述	")
    private String desc;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("状态")
    private String status;

    /**
     * 属性 [CHANGEDSTORYCNT]
     *
     */
    @JSONField(name = "changedstorycnt")
    @JsonProperty("changedstorycnt")
    @ApiModelProperty("已变更需求")
    private Integer changedstorycnt;

    /**
     * 属性 [ACTIVEBUGCNT]
     *
     */
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    @ApiModelProperty("未解决Bug数")
    private Integer activebugcnt;

    /**
     * 属性 [CREATEDBY]
     *
     */
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁创建")
    private String createdby;

    /**
     * 属性 [RDPK]
     *
     */
    @JSONField(name = "rdpk")
    @JsonProperty("rdpk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("发布负责人（选择）")
    private String rdpk;

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
     * 属性 [CREATEDVERSION]
     *
     */
    @JSONField(name = "createdversion")
    @JsonProperty("createdversion")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("当前系统版本")
    private String createdversion;

    /**
     * 属性 [DRAFTSTORYCNT]
     *
     */
    @JSONField(name = "draftstorycnt")
    @JsonProperty("draftstorycnt")
    @ApiModelProperty("草稿需求")
    private Integer draftstorycnt;

    /**
     * 属性 [DOCCNT]
     *
     */
    @JSONField(name = "doccnt")
    @JsonProperty("doccnt")
    @ApiModelProperty("文档数")
    private Integer doccnt;

    /**
     * 属性 [CASECNT]
     *
     */
    @JSONField(name = "casecnt")
    @JsonProperty("casecnt")
    @ApiModelProperty("用例数")
    private Integer casecnt;

    /**
     * 属性 [RELATEDPROJECTS]
     *
     */
    @JSONField(name = "relatedprojects")
    @JsonProperty("relatedprojects")
    @ApiModelProperty("关联项目数")
    private Integer relatedprojects;

    /**
     * 属性 [IBIZ_ID]
     *
     */
    @JSONField(name = "ibiz_id")
    @JsonProperty("ibiz_id")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("IBIZ标识")
    private String ibizId;

    /**
     * 属性 [SUBSTATUS]
     *
     */
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("子状态")
    private String substatus;

    /**
     * 属性 [CODE]
     *
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    @Size(min = 0, max = 45, message = "内容长度必须小于等于[45]")
    @ApiModelProperty("产品代号")
    private String code;

    /**
     * 属性 [SRFCOUNT]
     *
     */
    @JSONField(name = "srfcount")
    @JsonProperty("srfcount")
    @ApiModelProperty("属性")
    private Integer srfcount;

    /**
     * 属性 [ORDER1]
     *
     */
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("排序")
    private Integer order1;

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
     * 属性 [BUILDCNT]
     *
     */
    @JSONField(name = "buildcnt")
    @JsonProperty("buildcnt")
    @ApiModelProperty("BUILD数")
    private Integer buildcnt;

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
     * 属性 [CREATEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    @ApiModelProperty("创建日期")
    private Timestamp createddate;

    /**
     * 属性 [NOTICEUSERS]
     *
     */
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;

    /**
     * 属性 [ACTIVESTORYCNT]
     *
     */
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    @ApiModelProperty("激活需求数")
    private Integer activestorycnt;

    /**
     * 属性 [PRODUCTSN]
     *
     */
    @JSONField(name = "productsn")
    @JsonProperty("productsn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品编号")
    private Long productsn;

    /**
     * 属性 [LINE]
     *
     */
    @JSONField(name = "line")
    @JsonProperty("line")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long line;

    /**
     * 属性 [LINENAME]
     *
     */
    @JSONField(name = "linename")
    @JsonProperty("linename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("产品线")
    private String linename;


    /**
     * 设置 [QD]
     */
    public void setQd(String  qd){
        this.qd = qd ;
        this.modify("qd",qd);
    }

    /**
     * 设置 [PRODUCTCLASS]
     */
    public void setProductclass(String  productclass){
        this.productclass = productclass ;
        this.modify("productclass",productclass);
    }

    /**
     * 设置 [ACL]
     */
    public void setAcl(String  acl){
        this.acl = acl ;
        this.modify("acl",acl);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [WHITELIST]
     */
    public void setWhitelist(String  whitelist){
        this.whitelist = whitelist ;
        this.modify("whitelist",whitelist);
    }

    /**
     * 设置 [RD]
     */
    public void setRd(String  rd){
        this.rd = rd ;
        this.modify("rd",rd);
    }

    /**
     * 设置 [SUPPROREPORT]
     */
    public void setSupproreport(String  supproreport){
        this.supproreport = supproreport ;
        this.modify("supproreport",supproreport);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(Integer  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [PO]
     */
    public void setPo(String  po){
        this.po = po ;
        this.modify("po",po);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [CREATEDVERSION]
     */
    public void setCreatedversion(String  createdversion){
        this.createdversion = createdversion ;
        this.modify("createdversion",createdversion);
    }

    /**
     * 设置 [IBIZ_ID]
     */
    public void setIbizId(String  ibizId){
        this.ibizId = ibizId ;
        this.modify("ibiz_id",ibizId);
    }

    /**
     * 设置 [SUBSTATUS]
     */
    public void setSubstatus(String  substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [CODE]
     */
    public void setCode(String  code){
        this.code = code ;
        this.modify("code",code);
    }

    /**
     * 设置 [PRODUCTSN]
     */
    public void setProductsn(Long  productsn){
        this.productsn = productsn ;
        this.modify("productsn",productsn);
    }

    /**
     * 设置 [LINE]
     */
    public void setLine(Long  line){
        this.line = line ;
        this.modify("line",line);
    }


}


