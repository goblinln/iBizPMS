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
 * 实体[im_message]
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "zt_im_message",resultMap = "Im_messageResultMap")
public class Im_message extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user
     */
    @TableField(value = "user")
    @JSONField(name = "user")
    @JsonProperty("user")
    private String user;
    /**
     * gid
     */
    @TableField(value = "gid")
    @JSONField(name = "gid")
    @JsonProperty("gid")
    private String gid;
    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * date
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0" , preType = DEPredefinedFieldType.LOGICVALID, logicval = "0" , logicdelval="1")
    @TableLogic(value= "0",delval="1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * data
     */
    @TableField(value = "data")
    @JSONField(name = "data")
    @JsonProperty("data")
    private String data;
    /**
     * type
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * content
     */
    @TableField(value = "content")
    @JSONField(name = "content")
    @JsonProperty("content")
    private String content;
    /**
     * cgid
     */
    @TableField(value = "cgid")
    @JSONField(name = "cgid")
    @JsonProperty("cgid")
    private String cgid;
    /**
     * order
     */
    @TableField(value = "order")
    @JSONField(name = "order")
    @JsonProperty("order")
    private BigInteger order;
    /**
     * contentType
     */
    @TableField(value = "contenttype")
    @JSONField(name = "contenttype")
    @JsonProperty("contenttype")
    private String contenttype;



    /**
     * 设置 [user]
     */
    public void setUser(String user){
        this.user = user ;
        this.modify("user",user);
    }
    /**
     * 设置 [gid]
     */
    public void setGid(String gid){
        this.gid = gid ;
        this.modify("gid",gid);
    }
    /**
     * 设置 [date]
     */
    public void setDate(Timestamp date){
        this.date = date ;
        this.modify("date",date);
    }
    /**
     * 设置 [data]
     */
    public void setData(String data){
        this.data = data ;
        this.modify("data",data);
    }
    /**
     * 设置 [type]
     */
    public void setType(String type){
        this.type = type ;
        this.modify("type",type);
    }
    /**
     * 设置 [content]
     */
    public void setContent(String content){
        this.content = content ;
        this.modify("content",content);
    }
    /**
     * 设置 [cgid]
     */
    public void setCgid(String cgid){
        this.cgid = cgid ;
        this.modify("cgid",cgid);
    }
    /**
     * 设置 [order]
     */
    public void setOrder(BigInteger order){
        this.order = order ;
        this.modify("order",order);
    }
    /**
     * 设置 [contentType]
     */
    public void setContenttype(String contenttype){
        this.contenttype = contenttype ;
        this.modify("contenttype",contenttype);
    }

}


