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
 * 实体[需求描述]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_storyspec", resultMap = "StorySpecResultMap")
@ApiModel("需求描述")
public class StorySpec extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 需求描述	
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "spec")
    @JSONField(name = "spec")
    @JsonProperty("spec")
    @ApiModelProperty("需求描述	")
    private String spec;
    /**
     * 验收标准
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "verify")
    @JSONField(name = "verify")
    @JsonProperty("verify")
    @ApiModelProperty("验收标准")
    private String verify;
    /**
     * 虚拟主键
     */
    @DEField(isKeyField = true)
    @TableField(exist = false)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("虚拟主键")
    private String id;
    /**
     * 需求名称
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("需求名称")
    private String title;
    /**
     * 版本号
     */
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本号")
    private Integer version;
    /**
     * 需求
     */
    @TableField(value = "story")
    @JSONField(name = "story")
    @JsonProperty("story")
    @ApiModelProperty("需求")
    private Long story;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Story ztstory;



    /**
     * 设置 [需求描述	]
     */
    public void setSpec(String spec) {
        this.spec = spec;
        this.modify("spec", spec);
    }

    /**
     * 设置 [验收标准]
     */
    public void setVerify(String verify) {
        this.verify = verify;
        this.modify("verify", verify);
    }

    /**
     * 设置 [需求名称]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [版本号]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [需求]
     */
    public void setStory(Long story) {
        this.story = story;
        this.modify("story", story);
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


