package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
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


import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;


/**
 * 实体[文档内容]
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "zt_doccontent",resultMap = "DocContentResultMap")
public class DocContent extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 附件
     */
    @TableField(value = "files")
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
    private BigInteger id;
    /**
     * 文档正文
     */
    @TableField(value = "content")
    @JSONField(name = "content")
    @JsonProperty("content")
    private String content;
    /**
     * 文档类型
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 文档标题
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 文档摘要
     */
    @TableField(value = "digest")
    @JSONField(name = "digest")
    @JsonProperty("digest")
    private String digest;
    /**
     * 版本号
     */
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    private Integer version;
    /**
     * 文档
     */
    @TableField(value = "doc")
    @JSONField(name = "doc")
    @JsonProperty("doc")
    private BigInteger doc;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Doc ztDoc;



    /**
     * 设置 [附件]
     */
    public void setFiles(String files){
        this.files = files ;
        this.modify("files",files);
    }
    /**
     * 设置 [文档正文]
     */
    public void setContent(String content){
        this.content = content ;
        this.modify("content",content);
    }
    /**
     * 设置 [文档类型]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
    }
    /**
     * 设置 [文档标题]
     */
    public void setTitle(String title){
        this.title = title ;
        this.modify("title",title);
    }
    /**
     * 设置 [文档摘要]
     */
    public void setDigest(String digest){
        this.digest = digest ;
        this.modify("digest",digest);
    }
    /**
     * 设置 [版本号]
     */
    public void setVersion(Integer version){
        this.version = version ;
        this.modify("version",version);
    }
    /**
     * 设置 [文档]
     */
    public void setDoc(BigInteger doc){
        this.doc = doc ;
        this.modify("doc",doc);
    }

}


