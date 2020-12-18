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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[测试用例统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_case", resultMap = "IBZ_CASESTATSResultMap")
public class IBZ_CASESTATS extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用例编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 用例标题
     */
    @TableField(value = "`title`")
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 模块
     */
    @TableField(value = "`module`")
    @JSONField(name = "module")
    @JsonProperty("module")
    private Long module;
    /**
     * 模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    private String modulename;
    /**
     * 总用例数
     */
    @TableField(exist = false)
    @JSONField(name = "totalcase")
    @JsonProperty("totalcase")
    private Integer totalcase;
    /**
     * 通过用例数
     */
    @TableField(exist = false)
    @JSONField(name = "passcase")
    @JsonProperty("passcase")
    private Integer passcase;
    /**
     * 失败用例数
     */
    @TableField(exist = false)
    @JSONField(name = "failcase")
    @JsonProperty("failcase")
    private Integer failcase;
    /**
     * 阻塞用例数
     */
    @TableField(exist = false)
    @JSONField(name = "blockedcase")
    @JsonProperty("blockedcase")
    private Integer blockedcase;
    /**
     * 总执行数
     */
    @TableField(exist = false)
    @JSONField(name = "totalruncase")
    @JsonProperty("totalruncase")
    private Integer totalruncase;
    /**
     * 用例通过率
     */
    @TableField(exist = false)
    @JSONField(name = "passrate")
    @JsonProperty("passrate")
    private String passrate;

    /**
     * 模块
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Module ztmodule;



    /**
     * 设置 [用例标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [模块]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
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


