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
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 需求描述	
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SPEC`")
    @JSONField(name = "spec")
    @JsonProperty("spec")
    @ApiModelProperty("需求描述	")
    private String spec;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`CREATEBY`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 验收标准
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`VERIFY`")
    @JSONField(name = "verify")
    @JsonProperty("verify")
    @ApiModelProperty("验收标准")
    private String verify;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORG`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
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
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("需求名称")
    private String title;
    /**
     * 版本号
     */
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本号")
    private Integer version;
    /**
     * 需求
     */
    @TableField(value = "`STORY`")
    @JSONField(name = "story")
    @JsonProperty("story")
    @ApiModelProperty("需求")
    private Long story;
    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;

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


