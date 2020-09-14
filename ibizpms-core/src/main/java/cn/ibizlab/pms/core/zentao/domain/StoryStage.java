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
 * 实体[需求阶段]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_storystage",resultMap = "StoryStageResultMap")
public class StoryStage extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 虚拟主键
     */
    @DEField(isKeyField=true)
    @TableField(exist = false)
    @JSONField(name = "id")
    @JsonProperty("id")
    private String id;
    /**
     * 设置阶段者
     */
    @TableField(value = "stagedby")
    @JSONField(name = "stagedby")
    @JsonProperty("stagedby")
    private String stagedby;
    /**
     * 所处阶段
     */
    @TableField(value = "stage")
    @JSONField(name = "stage")
    @JsonProperty("stage")
    private String stage;
    /**
     * 需求
     */
    @TableField(value = "story")
    @JSONField(name = "story")
    @JsonProperty("story")
    private Long story;
    /**
     * 平台/分支
     */
    @TableField(value = "branch")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    private Long branch;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Branch ztbranch;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Story ztstory;



    /**
     * 设置 [设置阶段者]
     */
    public void setStagedby(String stagedby){
        this.stagedby = stagedby ;
        this.modify("stagedby",stagedby);
    }

    /**
     * 设置 [所处阶段]
     */
    public void setStage(String stage){
        this.stage = stage ;
        this.modify("stage",stage);
    }

    /**
     * 设置 [需求]
     */
    public void setStory(Long story){
        this.story = story ;
        this.modify("story",story);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch){
        this.branch = branch ;
        this.modify("branch",branch);
    }


}


