package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;

/**
 * 实体[公司]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_company",resultMap = "CompanyResultMap")
public class Company extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * admins
     */
    @TableField(value = "admins")
    @JSONField(name = "admins")
    @JsonProperty("admins")
    private String admins;
    /**
     * 传真
     */
    @TableField(value = "fax")
    @JSONField(name = "fax")
    @JsonProperty("fax")
    private String fax;
    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0" , preType = DEPredefinedFieldType.LOGICVALID, logicval = "0" , logicdelval="1")
    @TableLogic(value= "0",delval="1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 官网
     */
    @TableField(value = "website")
    @JSONField(name = "website")
    @JsonProperty("website")
    private String website;
    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * 邮政编码
     */
    @TableField(value = "zipcode")
    @JSONField(name = "zipcode")
    @JsonProperty("zipcode")
    private String zipcode;
    /**
     * 通讯地址
     */
    @TableField(value = "address")
    @JSONField(name = "address")
    @JsonProperty("address")
    private String address;
    /**
     * 内网
     */
    @TableField(value = "backyard")
    @JSONField(name = "backyard")
    @JsonProperty("backyard")
    private String backyard;
    /**
     * 公司名称
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 匿名登陆
     */
    @TableField(value = "guest")
    @JSONField(name = "guest")
    @JsonProperty("guest")
    private String guest;
    /**
     * 联系电话
     */
    @TableField(value = "phone")
    @JSONField(name = "phone")
    @JsonProperty("phone")
    private String phone;



    /**
     * 设置 [admins]
     */
    public void setAdmins(String admins){
        this.admins = admins ;
        this.modify("admins",admins);
    }
    /**
     * 设置 [传真]
     */
    public void setFax(String fax){
        this.fax = fax ;
        this.modify("fax",fax);
    }
    /**
     * 设置 [官网]
     */
    public void setWebsite(String website){
        this.website = website ;
        this.modify("website",website);
    }
    /**
     * 设置 [邮政编码]
     */
    public void setZipcode(String zipcode){
        this.zipcode = zipcode ;
        this.modify("zipcode",zipcode);
    }
    /**
     * 设置 [通讯地址]
     */
    public void setAddress(String address){
        this.address = address ;
        this.modify("address",address);
    }
    /**
     * 设置 [内网]
     */
    public void setBackyard(String backyard){
        this.backyard = backyard ;
        this.modify("backyard",backyard);
    }
    /**
     * 设置 [公司名称]
     */
    public void setName(String name){
        this.name = name ;
        this.modify("name",name);
    }
    /**
     * 设置 [匿名登陆]
     */
    public void setGuest(String guest){
        this.guest = guest ;
        this.modify("guest",guest);
    }
    /**
     * 设置 [联系电话]
     */
    public void setPhone(String phone){
        this.phone = phone ;
        this.modify("phone",phone);
    }

}


