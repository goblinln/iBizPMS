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
 * 实体[项目相关成员]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_project", resultMap = "IbzProjectMemberResultMap")
@ApiModel("项目相关成员")
public class IbzProjectMember extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 团队成员（二）
     */
    @TableField(exist = false)
    @JSONField(name = "secondmember")
    @JsonProperty("secondmember")
    @ApiModelProperty("团队成员（二）")
    private String secondmember;
    /**
     * 发布负责人
     */
    @TableField(exist = false)
    @JSONField(name = "rd")
    @JsonProperty("rd")
    @ApiModelProperty("发布负责人")
    private String rd;
    /**
     * 测试负责人
     */
    @TableField(exist = false)
    @JSONField(name = "qd")
    @JsonProperty("qd")
    @ApiModelProperty("测试负责人")
    private String qd;
    /**
     * 全部成员
     */
    @TableField(exist = false)
    @JSONField(name = "teamembers")
    @JsonProperty("teamembers")
    @ApiModelProperty("全部成员")
    private String teamembers;
    /**
     * 团队成员（三）
     */
    @TableField(exist = false)
    @JSONField(name = "thirdmember")
    @JsonProperty("thirdmember")
    @ApiModelProperty("团队成员（三）")
    private String thirdmember;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 团队成员（一）
     */
    @TableField(exist = false)
    @JSONField(name = "fristmember")
    @JsonProperty("fristmember")
    @ApiModelProperty("团队成员（一）")
    private String fristmember;
    /**
     * 产品负责人
     */
    @TableField(exist = false)
    @JSONField(name = "po")
    @JsonProperty("po")
    @ApiModelProperty("产品负责人")
    private String po;
    /**
     * 团队成员（四）
     */
    @TableField(exist = false)
    @JSONField(name = "fourthmember")
    @JsonProperty("fourthmember")
    @ApiModelProperty("团队成员（四）")
    private String fourthmember;
    /**
     * 项目负责人
     */
    @TableField(exist = false)
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @ApiModelProperty("项目负责人")
    private String pm;
    /**
     * 团队成员（五）
     */
    @TableField(exist = false)
    @JSONField(name = "fifthmember")
    @JsonProperty("fifthmember")
    @ApiModelProperty("团队成员（五）")
    private String fifthmember;
    /**
     * 团队成员（六）
     */
    @TableField(exist = false)
    @JSONField(name = "sixthmember")
    @JsonProperty("sixthmember")
    @ApiModelProperty("团队成员（六）")
    private String sixthmember;
    /**
     * 项目名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("项目名称")
    private String name;



    /**
     * 设置 [项目名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
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


