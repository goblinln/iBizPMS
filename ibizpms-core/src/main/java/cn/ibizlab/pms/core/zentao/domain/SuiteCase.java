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
 * 实体[套件用例]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_suitecase",resultMap = "SuiteCaseResultMap")
public class SuiteCase extends EntityMP implements Serializable {

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
     * 用例版本
     */
    @TableField(value = "`version`")
    @JSONField(name = "version")
    @JsonProperty("version")
    private Integer version;
    /**
     * 测试套件
     */
    @TableField(value = "`suite`")
    @JSONField(name = "suite")
    @JsonProperty("suite")
    private Long suite;
    /**
     * 用例
     */
    @DEField(name = "case")
    @TableField(value = "`case`")
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    private Long ibizcase;
    /**
     * 所属产品
     */
    @TableField(value = "`product`")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Long product;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Case ztcase;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztproduct;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.TestSuite zttestsuite;



    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version){
        this.version = version ;
        this.modify("version",version);
    }

    /**
     * 设置 [测试套件]
     */
    public void setSuite(Long suite){
        this.suite = suite ;
        this.modify("suite",suite);
    }

    /**
     * 设置 [用例]
     */
    public void setIbizcase(Long ibizcase){
        this.ibizcase = ibizcase ;
        this.modify("case",ibizcase);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product){
        this.product = product ;
        this.modify("product",product);
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


