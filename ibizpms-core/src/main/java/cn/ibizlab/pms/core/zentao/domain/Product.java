package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.annotation.DEField;
import cn.ibizlab.pms.util.enums.DEPredefinedFieldType;
import cn.ibizlab.pms.util.enums.DEFieldDefaultValueType;
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[产品]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_product",resultMap = "ProductResultMap")
public class Product extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 测试负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`qd`")
    @JSONField(name = "qd")
    @JsonProperty("qd")
    private String qd;
    /**
     * 访问控制
     */
    @DEField(defaultValue = "open")
    @TableField(value = "`acl`")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    private String acl;
    /**
     * 产品名称
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 编号
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0" , preType = DEPredefinedFieldType.LOGICVALID, logicval = "0" , logicdelval="1")
    @TableLogic(value= "0",delval="1")
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 分组白名单
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`whitelist`")
    @JSONField(name = "whitelist")
    @JsonProperty("whitelist")
    private String whitelist;
    /**
     * 发布负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`rd`")
    @JSONField(name = "rd")
    @JsonProperty("rd")
    private String rd;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`order`")
    @JSONField(name = "order")
    @JsonProperty("order")
    private Integer order;
    /**
     * 产品类型
     */
    @DEField(defaultValue = "normal")
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 产品负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`po`")
    @JSONField(name = "po")
    @JsonProperty("po")
    private String po;
    /**
     * 产品描述	
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`desc`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    private String desc;
    /**
     * 状态
     */
    @DEField(defaultValue = "normal")
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`createdby`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    private String createdby;
    /**
     * 当前系统版本
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`createdversion`")
    @JSONField(name = "createdversion")
    @JsonProperty("createdversion")
    private String createdversion;
    /**
     * 子状态
     */
    @TableField(value = "`substatus`")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    private String substatus;
    /**
     * 产品代号
     */
    @TableField(value = "`code`")
    @JSONField(name = "code")
    @JsonProperty("code")
    private String code;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createddate`" , fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    private Timestamp createddate;
    /**
     * 产品线
     */
    @TableField(exist = false)
    @JSONField(name = "linename")
    @JsonProperty("linename")
    private String linename;
    /**
     * 产品线
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`line`")
    @JSONField(name = "line")
    @JsonProperty("line")
    private Long line;
    /**
     * 未解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    private Integer activebugcnt;
    /**
     * 计划总数
     */
    @TableField(exist = false)
    @JSONField(name = "productplancnt")
    @JsonProperty("productplancnt")
    private Integer productplancnt;
    /**
     * 发布总数
     */
    @TableField(exist = false)
    @JSONField(name = "releasecnt")
    @JsonProperty("releasecnt")
    private Integer releasecnt;
    /**
     * 激活需求数
     */
    @TableField(exist = false)
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    private Integer activestorycnt;
    /**
     * 未确认Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "unconfirmbugcnt")
    @JsonProperty("unconfirmbugcnt")
    private Integer unconfirmbugcnt;
    /**
     * 未关闭Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "notclosedbugcnt")
    @JsonProperty("notclosedbugcnt")
    private Integer notclosedbugcnt;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * 相关Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "relatedbugcnt")
    @JsonProperty("relatedbugcnt")
    private Integer relatedbugcnt;
    /**
     * 已变更需求
     */
    @TableField(exist = false)
    @JSONField(name = "changedstorycnt")
    @JsonProperty("changedstorycnt")
    private Integer changedstorycnt;
    /**
     * 草稿需求
     */
    @TableField(exist = false)
    @JSONField(name = "draftstorycnt")
    @JsonProperty("draftstorycnt")
    private Integer draftstorycnt;
    /**
     * 已关闭需求
     */
    @TableField(exist = false)
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    private Integer closedstorycnt;
    /**
     * 关联项目数
     */
    @TableField(exist = false)
    @JSONField(name = "relatedprojects")
    @JsonProperty("relatedprojects")
    private Integer relatedprojects;
    /**
     * 文档数
     */
    @TableField(exist = false)
    @JSONField(name = "doccnt")
    @JsonProperty("doccnt")
    private Integer doccnt;
    /**
     * BUILD数
     */
    @TableField(exist = false)
    @JSONField(name = "buildcnt")
    @JsonProperty("buildcnt")
    private Integer buildcnt;
    /**
     * 用例数
     */
    @TableField(exist = false)
    @JSONField(name = "casecnt")
    @JsonProperty("casecnt")
    private Integer casecnt;
    /**
     * 属性
     */
    @TableField(exist = false)
    @JSONField(name = "srfcount")
    @JsonProperty("srfcount")
    private Integer srfcount;
    /**
     * 移动端图片
     */
    @TableField(exist = false)
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    private String mobimage;
    /**
     * 排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    private Integer order1;
    /**
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    private Integer istop;
    /**
     * 测试单数
     */
    @TableField(exist = false)
    @JSONField(name = "testtaskcnt")
    @JsonProperty("testtaskcnt")
    private Integer testtaskcnt;
    /**
     * 套件数
     */
    @TableField(exist = false)
    @JSONField(name = "testsuitecnt")
    @JsonProperty("testsuitecnt")
    private Integer testsuitecnt;

    /**
     * 产品线
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Module moduleline;



    /**
     * 设置 [测试负责人]
     */
    public void setQd(String qd){
        this.qd = qd ;
        this.modify("qd",qd);
    }

    /**
     * 设置 [访问控制]
     */
    public void setAcl(String acl){
        this.acl = acl ;
        this.modify("acl",acl);
    }

    /**
     * 设置 [产品名称]
     */
    public void setName(String name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [分组白名单]
     */
    public void setWhitelist(String whitelist){
        this.whitelist = whitelist ;
        this.modify("whitelist",whitelist);
    }

    /**
     * 设置 [发布负责人]
     */
    public void setRd(String rd){
        this.rd = rd ;
        this.modify("rd",rd);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [产品类型]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [产品负责人]
     */
    public void setPo(String po){
        this.po = po ;
        this.modify("po",po);
    }

    /**
     * 设置 [产品描述	]
     */
    public void setDesc(String desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [状态]
     */
    public void setStatus(String status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [当前系统版本]
     */
    public void setCreatedversion(String createdversion){
        this.createdversion = createdversion ;
        this.modify("createdversion",createdversion);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [产品代号]
     */
    public void setCode(String code){
        this.code = code ;
        this.modify("code",code);
    }

    /**
     * 设置 [产品线]
     */
    public void setLine(Long line){
        this.line = line ;
        this.modify("line",line);
    }


    @Override
    public Serializable getDefaultKey(boolean gen) {
       return IdWorker.getId();
    }
    /**
     * 复制当前对象数据到目标对象(粘贴重置)
     * @param targetEntity 目标数据对象
     * @param bIncEmpty  是否包括空值
     * @param <T>
     * @return
     */
    @Override
    public <T> T copyTo(T targetEntity, boolean bIncEmpty) {
        this.reset("id");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


