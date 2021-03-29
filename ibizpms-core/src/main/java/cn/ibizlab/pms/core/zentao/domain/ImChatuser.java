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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[ImChatuser]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_im_chatuser", resultMap = "ImChatuserResultMap")
public class ImChatuser extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * order
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`order`")
    @JSONField(name = "order")
    @JsonProperty("order")
    private Integer order;
    /**
     * freeze
     */
    @TableField(value = "`freeze`")
    @JSONField(name = "freeze")
    @JsonProperty("freeze")
    private String freeze;
    /**
     * star
     */
    @TableField(value = "`star`")
    @JSONField(name = "star")
    @JsonProperty("star")
    private String star;
    /**
     * hide
     */
    @TableField(value = "`hide`")
    @JSONField(name = "hide")
    @JsonProperty("hide")
    private String hide;
    /**
     * join
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`join`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "join", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("join")
    private Timestamp join;
    /**
     * user
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`user`")
    @JSONField(name = "user")
    @JsonProperty("user")
    private Integer user;
    /**
     * mute
     */
    @TableField(value = "`mute`")
    @JSONField(name = "mute")
    @JsonProperty("mute")
    private String mute;
    /**
     * cgid
     */
    @TableField(value = "`cgid`")
    @JSONField(name = "cgid")
    @JsonProperty("cgid")
    private String cgid;
    /**
     * quit
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`quit`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "quit", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("quit")
    private Timestamp quit;
    /**
     * category
     */
    @TableField(value = "`category`")
    @JSONField(name = "category")
    @JsonProperty("category")
    private String category;



    /**
     * 设置 [order]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [freeze]
     */
    public void setFreeze(String freeze) {
        this.freeze = freeze;
        this.modify("freeze", freeze);
    }

    /**
     * 设置 [star]
     */
    public void setStar(String star) {
        this.star = star;
        this.modify("star", star);
    }

    /**
     * 设置 [hide]
     */
    public void setHide(String hide) {
        this.hide = hide;
        this.modify("hide", hide);
    }

    /**
     * 设置 [join]
     */
    public void setJoin(Timestamp join) {
        this.join = join;
        this.modify("join", join);
    }

    /**
     * 格式化日期 [join]
     */
    public String formatJoin() {
        if (this.join == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(join);
    }
    /**
     * 设置 [user]
     */
    public void setUser(Integer user) {
        this.user = user;
        this.modify("user", user);
    }

    /**
     * 设置 [mute]
     */
    public void setMute(String mute) {
        this.mute = mute;
        this.modify("mute", mute);
    }

    /**
     * 设置 [cgid]
     */
    public void setCgid(String cgid) {
        this.cgid = cgid;
        this.modify("cgid", cgid);
    }

    /**
     * 设置 [quit]
     */
    public void setQuit(Timestamp quit) {
        this.quit = quit;
        this.modify("quit", quit);
    }

    /**
     * 格式化日期 [quit]
     */
    public String formatQuit() {
        if (this.quit == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(quit);
    }
    /**
     * 设置 [category]
     */
    public void setCategory(String category) {
        this.category = category;
        this.modify("category", category);
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


