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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;


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
@TableName(value = "zt_casestep",resultMap = "IbzLibCaseStepsResultMap")
public class IbzLibCaseSteps extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 版本
     */
    @TableField(exist = false)
    @JSONField(name = "version")
    @JsonProperty("version")
    private Integer version;
    /**
     * 用例编号
     */
    @DEField(name = "case")
    @TableField(value = "`case"`)
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    private Long ibizcase;
    /**
     * 预期
     */
    @TableField(value = "`expect"`)
    @JSONField(name = "expect")
    @JsonProperty("expect")
    private String expect;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    private String files;
    /**
     * 编号
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 实际情况
     */
    @TableField(exist = false)
    @JSONField(name = "reals")
    @JsonProperty("reals")
    private String reals;
    /**
     * 步骤
     */
    @TableField(value = "`desc"`)
    @JSONField(name = "desc")
    @JsonProperty("desc")
    private String desc;
    /**
     * 编号
     */
    @TableField(value = "`parent"`)
    @JSONField(name = "parent")
    @JsonProperty("parent")
    private Long parent;
    /**
     * 类型
     */
    @TableField(value = "`type"`)
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;

    /**
     * 用例
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.IbzCase ibzcase;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps ibzlibcasestepcs;


    /**
     * 用例库用例步骤
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps> ibzlibcasesteps;


    /**
     * 设置 [用例编号]
     */
    public void setIbizcase(Long ibizcase){
        this.ibizcase = ibizcase ;
        this.modify("case",ibizcase);
    }

    /**
     * 设置 [预期]
     */
    public void setExpect(String expect){
        this.expect = expect ;
        this.modify("expect",expect);
    }

    /**
     * 设置 [步骤]
     */
    public void setDesc(String desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [编号]
     */
    public void setParent(Long parent){
        this.parent = parent ;
        this.modify("parent",parent);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
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
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


