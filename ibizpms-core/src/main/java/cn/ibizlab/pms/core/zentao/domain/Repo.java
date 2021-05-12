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
 * 实体[repo]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_repo", resultMap = "RepoResultMap")
@ApiModel("repo")
public class Repo extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * SCM
     */
    @TableField(value = "`SCM`")
    @JSONField(name = "scm")
    @JsonProperty("scm")
    @ApiModelProperty("SCM")
    private String scm;
    /**
     * prefix
     */
    @TableField(value = "`PREFIX`")
    @JSONField(name = "prefix")
    @JsonProperty("prefix")
    @ApiModelProperty("prefix")
    private String prefix;
    /**
     * password
     */
    @TableField(value = "`PASSWORD`")
    @JSONField(name = "password")
    @JsonProperty("password")
    @ApiModelProperty("password")
    private String password;
    /**
     * id
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * encoding
     */
    @TableField(value = "`ENCODING`")
    @JSONField(name = "encoding")
    @JsonProperty("encoding")
    @ApiModelProperty("encoding")
    private String encoding;
    /**
     * commits
     */
    @TableField(value = "`COMMITS`")
    @JSONField(name = "commits")
    @JsonProperty("commits")
    @ApiModelProperty("commits")
    private Integer commits;
    /**
     * synced
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`SYNCED`")
    @JSONField(name = "synced")
    @JsonProperty("synced")
    @ApiModelProperty("synced")
    private Integer synced;
    /**
     * lastSync
     */
    @TableField(value = "`LASTSYNC`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastsync", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastsync")
    @ApiModelProperty("lastSync")
    private Timestamp lastsync;
    /**
     * client
     */
    @TableField(value = "`CLIENT`")
    @JSONField(name = "client")
    @JsonProperty("client")
    @ApiModelProperty("client")
    private String client;
    /**
     * acl
     */
    @TableField(value = "`ACL`")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("acl")
    private String acl;
    /**
     * encrypt
     */
    @DEField(defaultValue = "plain")
    @TableField(value = "`ENCRYPT`")
    @JSONField(name = "encrypt")
    @JsonProperty("encrypt")
    @ApiModelProperty("encrypt")
    private String encrypt;
    /**
     * account
     */
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("account")
    private String account;
    /**
     * desc
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
    /**
     * name
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("name")
    private String name;
    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("逻辑删除标志")
    private String deleted;
    /**
     * path
     */
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private String path;



    /**
     * 设置 [SCM]
     */
    public void setScm(String scm) {
        this.scm = scm;
        this.modify("scm", scm);
    }

    /**
     * 设置 [prefix]
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.modify("prefix", prefix);
    }

    /**
     * 设置 [password]
     */
    public void setPassword(String password) {
        this.password = password;
        this.modify("password", password);
    }

    /**
     * 设置 [encoding]
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
        this.modify("encoding", encoding);
    }

    /**
     * 设置 [commits]
     */
    public void setCommits(Integer commits) {
        this.commits = commits;
        this.modify("commits", commits);
    }

    /**
     * 设置 [synced]
     */
    public void setSynced(Integer synced) {
        this.synced = synced;
        this.modify("synced", synced);
    }

    /**
     * 设置 [lastSync]
     */
    public void setLastsync(Timestamp lastsync) {
        this.lastsync = lastsync;
        this.modify("lastsync", lastsync);
    }

    /**
     * 格式化日期 [lastSync]
     */
    public String formatLastsync() {
        if (this.lastsync == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastsync);
    }
    /**
     * 设置 [client]
     */
    public void setClient(String client) {
        this.client = client;
        this.modify("client", client);
    }

    /**
     * 设置 [acl]
     */
    public void setAcl(String acl) {
        this.acl = acl;
        this.modify("acl", acl);
    }

    /**
     * 设置 [encrypt]
     */
    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
        this.modify("encrypt", encrypt);
    }

    /**
     * 设置 [account]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [path]
     */
    public void setPath(String path) {
        this.path = path;
        this.modify("path", path);
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


