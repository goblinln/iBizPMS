package cn.ibizlab.pms.core.ibizplugin.domain;

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

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [系统插件] 对象
 */
@Data
public class IBIZProPlugin extends EntityClient implements Serializable {

    /**
     * 版本
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "version")
    @JsonProperty("version")
    private Integer version;

    /**
     * 类型
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;

    /**
     * 总下载量
     */
    @JSONField(name = "downloadcount")
    @JsonProperty("downloadcount")
    private Integer downloadcount;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 最新版本下载地址
     */
    @JSONField(name = "downloadurl")
    @JsonProperty("downloadurl")
    private String downloadurl;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 标签
     */
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;

    /**
     * 总评分
     */
    @JSONField(name = "score")
    @JsonProperty("score")
    private Integer score;

    /**
     * 系统插件名称
     */
    @DEField(name = "ibizpro_pluginname")
    @JSONField(name = "ibizpropluginname")
    @JsonProperty("ibizpropluginname")
    private String ibizpropluginname;

    /**
     * 系统插件标识
     */
    @DEField(name = "ibizpro_pluginid" , isKeyField = true)
    @JSONField(name = "ibizpropluginid")
    @JsonProperty("ibizpropluginid")
    private String ibizpropluginid;

    /**
     * 关键字
     */
    @JSONField(name = "keyword")
    @JsonProperty("keyword")
    private String keyword;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;

    /**
     * 总评论数
     */
    @JSONField(name = "commentcount")
    @JsonProperty("commentcount")
    private Integer commentcount;





    /**
     * 设置 [版本]
     */
    public void setVersion(Integer version) {
        this.version = version ;
        this.modify("version", version);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type ;
        this.modify("type", type);
    }

    /**
     * 设置 [最新版本下载地址]
     */
    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl ;
        this.modify("downloadurl", downloadurl);
    }

    /**
     * 设置 [标签]
     */
    public void setTag(String tag) {
        this.tag = tag ;
        this.modify("tag", tag);
    }

    /**
     * 设置 [系统插件名称]
     */
    public void setIbizpropluginname(String ibizpropluginname) {
        this.ibizpropluginname = ibizpropluginname ;
        this.modify("ibizpro_pluginname", ibizpropluginname);
    }

    /**
     * 设置 [关键字]
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword ;
        this.modify("keyword", keyword);
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
        this.reset("ibizpro_pluginid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


