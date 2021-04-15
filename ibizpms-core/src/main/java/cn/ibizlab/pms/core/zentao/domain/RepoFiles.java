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
 * 实体[repofiles]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_repofiles", resultMap = "RepoFilesResultMap")
@ApiModel("repofiles")
public class RepoFiles extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * path
     */
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private String path;
    /**
     * type
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("type")
    private String type;
    /**
     * revision
     */
    @TableField(value = "`REVISION`")
    @JSONField(name = "revision")
    @JsonProperty("revision")
    @ApiModelProperty("revision")
    private Integer revision;
    /**
     * action
     */
    @TableField(value = "`ACTION`")
    @JSONField(name = "action")
    @JsonProperty("action")
    @ApiModelProperty("action")
    private String action;
    /**
     * repo
     */
    @TableField(value = "`REPO`")
    @JSONField(name = "repo")
    @JsonProperty("repo")
    @ApiModelProperty("repo")
    private Integer repo;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * parent
     */
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("parent")
    private Long parent;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.RepoFiles ibizparent;



    /**
     * 设置 [path]
     */
    public void setPath(String path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [type]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [revision]
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
        this.modify("revision", revision);
    }

    /**
     * 设置 [action]
     */
    public void setAction(String action) {
        this.action = action;
        this.modify("action", action);
    }

    /**
     * 设置 [repo]
     */
    public void setRepo(Integer repo) {
        this.repo = repo;
        this.modify("repo", repo);
    }

    /**
     * 设置 [parent]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
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


