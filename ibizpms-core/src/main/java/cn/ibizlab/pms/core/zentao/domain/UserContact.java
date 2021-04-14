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
 * 实体[用户联系方式]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_usercontact", resultMap = "UserContactResultMap")
@ApiModel("用户联系方式")
public class UserContact extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * userList
     */
    @TableField(value = "`userlist`")
    @JSONField(name = "userlist")
    @JsonProperty("userlist")
    @ApiModelProperty("userList")
    private String userlist;
    /**
     * 标题
     */
    @TableField(value = "`listname`")
    @JSONField(name = "listname")
    @JsonProperty("listname")
    @ApiModelProperty("标题")
    private String listname;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * account
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`account`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("account")
    private String account;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`updateby`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`org`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`dept`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;



    /**
     * 设置 [userList]
     */
    public void setUserlist(String userlist) {
        this.userlist = userlist;
        this.modify("userlist", userlist);
    }

    /**
     * 设置 [标题]
     */
    public void setListname(String listname) {
        this.listname = listname;
        this.modify("listname", listname);
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


