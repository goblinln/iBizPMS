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
 * 实体[extension]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_extension", resultMap = "ExtensionResultMap")
@ApiModel("extension")
public class Extension extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * dirs
     */
    @TableField(value = "dirs")
    @JSONField(name = "dirs")
    @JsonProperty("dirs")
    @ApiModelProperty("dirs")
    private String dirs;
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
     * status
     */
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("status")
    private String status;
    /**
     * code
     */
    @TableField(value = "code")
    @JSONField(name = "code")
    @JsonProperty("code")
    @ApiModelProperty("code")
    private String code;
    /**
     * depends
     */
    @TableField(value = "depends")
    @JSONField(name = "depends")
    @JsonProperty("depends")
    @ApiModelProperty("depends")
    private String depends;
    /**
     * type
     */
    @DEField(defaultValue = "extension")
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("type")
    private String type;
    /**
     * files
     */
    @TableField(value = "files")
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("files")
    private String files;
    /**
     * zentaoCompatible
     */
    @TableField(value = "zentaocompatible")
    @JSONField(name = "zentaocompatible")
    @JsonProperty("zentaocompatible")
    @ApiModelProperty("zentaoCompatible")
    private String zentaocompatible;
    /**
     * license
     */
    @TableField(value = "license")
    @JSONField(name = "license")
    @JsonProperty("license")
    @ApiModelProperty("license")
    private String license;
    /**
     * name
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("name")
    private String name;
    /**
     * installedTime
     */
    @TableField(value = "installedtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "installedtime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("installedtime")
    @ApiModelProperty("installedTime")
    private Timestamp installedtime;
    /**
     * site
     */
    @TableField(value = "site")
    @JSONField(name = "site")
    @JsonProperty("site")
    @ApiModelProperty("site")
    private String site;
    /**
     * author
     */
    @TableField(value = "author")
    @JSONField(name = "author")
    @JsonProperty("author")
    @ApiModelProperty("author")
    private String author;
    /**
     * desc
     */
    @TableField(value = "desc")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
    /**
     * version
     */
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("version")
    private String version;



    /**
     * 设置 [dirs]
     */
    public void setDirs(String dirs) {
        this.dirs = dirs;
        this.modify("dirs", dirs);
    }

    /**
     * 设置 [status]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [code]
     */
    public void setCode(String code) {
        this.code = code;
        this.modify("code", code);
    }

    /**
     * 设置 [depends]
     */
    public void setDepends(String depends) {
        this.depends = depends;
        this.modify("depends", depends);
    }

    /**
     * 设置 [type]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [files]
     */
    public void setFiles(String files) {
        this.files = files;
        this.modify("files", files);
    }

    /**
     * 设置 [zentaoCompatible]
     */
    public void setZentaocompatible(String zentaocompatible) {
        this.zentaocompatible = zentaocompatible;
        this.modify("zentaocompatible", zentaocompatible);
    }

    /**
     * 设置 [license]
     */
    public void setLicense(String license) {
        this.license = license;
        this.modify("license", license);
    }

    /**
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [installedTime]
     */
    public void setInstalledtime(Timestamp installedtime) {
        this.installedtime = installedtime;
        this.modify("installedtime", installedtime);
    }

    /**
     * 格式化日期 [installedTime]
     */
    public String formatInstalledtime() {
        if (this.installedtime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(installedtime);
    }
    /**
     * 设置 [site]
     */
    public void setSite(String site) {
        this.site = site;
        this.modify("site", site);
    }

    /**
     * 设置 [author]
     */
    public void setAuthor(String author) {
        this.author = author;
        this.modify("author", author);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [version]
     */
    public void setVersion(String version) {
        this.version = version;
        this.modify("version", version);
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


