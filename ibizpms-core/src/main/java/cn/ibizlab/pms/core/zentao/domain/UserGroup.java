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


import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;


/**
 * 实体[用户组]
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "zt_usergroup",resultMap = "UserGroupResultMap")
public class UserGroup extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * account
     */
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    private String account;
    /**
     * group
     */
    @DEField(defaultValue = "0")
    @TableField(value = "group")
    @JSONField(name = "group")
    @JsonProperty("group")
    private Integer group;
    /**
     * 虚拟主键
     */
    @TableId(value= "id",type=IdType.UUID)
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;



    /**
     * 设置 [account]
     */
    public void setAccount(String account){
        this.account = account ;
        this.modify("account",account);
    }
    /**
     * 设置 [group]
     */
    public void setGroup(Integer group){
        this.group = group ;
        this.modify("group",group);
    }

}


