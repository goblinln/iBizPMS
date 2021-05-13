package cn.ibizlab.pms.core.ibiz.domain;

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
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.enums.DupCheck;
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[实体]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_user", resultMap = "IbiLoginResultMap")
@ApiModel("实体")
public class IbiLogin extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 真实姓名
     */
    @TableField(value = "`REALNAME`")
    @JSONField(name = "realname")
    @JsonProperty("realname")
    @ApiModelProperty("真实姓名")
    private String realname;
    /**
     * ID
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("ID")
    private Long id;
    /**
     * 账户
     */
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("账户")
    private String account;
    /**
     * token
     */
    @TableField(exist = false)
    @JSONField(name = "token")
    @JsonProperty("token")
    @ApiModelProperty("token")
    private String token;
    /**
     * 源代码账户
     */
    @TableField(value = "`COMMITER`")
    @JSONField(name = "commiter")
    @JsonProperty("commiter")
    @ApiModelProperty("源代码账户")
    private String commiter;



    /**
     * 设置 [真实姓名]
     */
    public void setRealname(String realname) {
        this.realname = realname;
        this.modify("realname", realname);
    }

    /**
     * 设置 [账户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [源代码账户]
     */
    public void setCommiter(String commiter) {
        this.commiter = commiter;
        this.modify("commiter", commiter);
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
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


