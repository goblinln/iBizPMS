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

/**
 * 实体[repobranch]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_repobranch",resultMap = "RepoBranchResultMap")
public class RepoBranch extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * revision
     */
    @TableField(value = "revision")
    @JSONField(name = "revision")
    @JsonProperty("revision")
    private Integer revision;
    /**
     * 虚拟主键
     */
    @TableId(value= "id",type=IdType.ASSIGN_UUID)
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;
    /**
     * repo
     */
    @TableField(value = "repo")
    @JSONField(name = "repo")
    @JsonProperty("repo")
    private Integer repo;
    /**
     * branch
     */
    @TableField(value = "branch")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    private String branch;



    /**
     * 设置 [revision]
     */
    public void setRevision(Integer revision){
        this.revision = revision ;
        this.modify("revision",revision);
    }

    /**
     * 设置 [repo]
     */
    public void setRepo(Integer repo){
        this.repo = repo ;
        this.modify("repo",repo);
    }

    /**
     * 设置 [branch]
     */
    public void setBranch(String branch){
        this.branch = branch ;
        this.modify("branch",branch);
    }


}


