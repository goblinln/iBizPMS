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
 * 实体[block]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_block", resultMap = "BlockResultMap")
@ApiModel("block")
public class Block extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 高度
     */
    @DEField(defaultValue = "0")
    @TableField(value = "height")
    @JSONField(name = "height")
    @JsonProperty("height")
    @ApiModelProperty("高度")
    private Integer height;
    /**
     * params
     */
    @TableField(value = "params")
    @JSONField(name = "params")
    @JsonProperty("params")
    @ApiModelProperty("params")
    private String params;
    /**
     * 区块名称
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("区块名称")
    private String title;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "order")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 来源区块
     */
    @TableField(value = "block")
    @JSONField(name = "block")
    @JsonProperty("block")
    @ApiModelProperty("来源区块")
    private String block;
    /**
     * 所属用户
     */
    @TableField(value = "account")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("所属用户")
    private String account;
    /**
     * 位置
     */
    @DEField(defaultValue = "0")
    @TableField(value = "grid")
    @JSONField(name = "grid")
    @JsonProperty("grid")
    @ApiModelProperty("位置")
    private Integer grid;
    /**
     * 所属模块
     */
    @TableField(value = "module")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("所属模块")
    private String module;
    /**
     * 隐藏
     */
    @DEField(defaultValue = "0")
    @TableField(value = "hidden")
    @JSONField(name = "hidden")
    @JsonProperty("hidden")
    @ApiModelProperty("隐藏")
    private Integer hidden;
    /**
     * 来源模块
     */
    @TableField(value = "source")
    @JSONField(name = "source")
    @JsonProperty("source")
    @ApiModelProperty("来源模块")
    private String source;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;



    /**
     * 设置 [高度]
     */
    public void setHeight(Integer height) {
        this.height = height;
        this.modify("height", height);
    }

    /**
     * 设置 [params]
     */
    public void setParams(String params) {
        this.params = params;
        this.modify("params", params);
    }

    /**
     * 设置 [区块名称]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [来源区块]
     */
    public void setBlock(String block) {
        this.block = block;
        this.modify("block", block);
    }

    /**
     * 设置 [所属用户]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [位置]
     */
    public void setGrid(Integer grid) {
        this.grid = grid;
        this.modify("grid", grid);
    }

    /**
     * 设置 [所属模块]
     */
    public void setModule(String module) {
        this.module = module;
        this.modify("module", module);
    }

    /**
     * 设置 [隐藏]
     */
    public void setHidden(Integer hidden) {
        this.hidden = hidden;
        this.modify("hidden", hidden);
    }

    /**
     * 设置 [来源模块]
     */
    public void setSource(String source) {
        this.source = source;
        this.modify("source", source);
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


