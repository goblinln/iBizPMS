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
 * 实体[用例库用例步骤]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "ZT_CASESTEP", resultMap = "IbzLibCaseStepResultMap")
@ApiModel("用例库用例步骤")
public class IbzLibCaseStep extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预期
     */
    @TableField(value = "`EXPECT`")
    @JSONField(name = "expect")
    @JsonProperty("expect")
    @ApiModelProperty("预期")
    private String expect;
    /**
     * 类型
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
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
     * 步骤
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("步骤")
    private String desc;
    /**
     * 实际情况
     */
    @TableField(exist = false)
    @JSONField(name = "reals")
    @JsonProperty("reals")
    @ApiModelProperty("实际情况")
    private String reals;



    /**
     * 设置 [预期]
     */
    public void setExpect(String expect) {
        this.expect = expect;
        this.modify("expect", expect);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [步骤]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
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


