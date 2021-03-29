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
 * 实体[附件]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_file", resultMap = "FileResultMap")
public class File extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路径
     */
    @TableField(value = "`pathname`")
    @JSONField(name = "pathname")
    @JsonProperty("pathname")
    private String pathname;
    /**
     * 对象ID
     */
    @TableField(value = "`objectid`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    private Long objectid;
    /**
     * 显示大小
     */
    @TableField(exist = false)
    @JSONField(name = "strsize")
    @JsonProperty("strsize")
    private String strsize;
    /**
     * 文档类型
     */
    @TableField(exist = false)
    @JSONField(name = "doclibtype")
    @JsonProperty("doclibtype")
    private String doclibtype;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 文件类型
     */
    @TableField(value = "`extension`")
    @JSONField(name = "extension")
    @JsonProperty("extension")
    private String extension;
    /**
     * 对象类型
     */
    @TableField(value = "`objecttype`")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    private String objecttype;
    /**
     * 由谁添加
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`addedby`")
    @JSONField(name = "addedby")
    @JsonProperty("addedby")
    private String addedby;
    /**
     * 标题
     */
    @TableField(value = "`title`")
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 添加时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`addeddate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "addeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("addeddate")
    private Timestamp addeddate;
    /**
     * 下载次数
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`downloads`")
    @JSONField(name = "downloads")
    @JsonProperty("downloads")
    private Integer downloads;
    /**
     * 大小
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`size`")
    @JSONField(name = "size")
    @JsonProperty("size")
    private Integer size;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 备注
     */
    @TableField(value = "`extra`")
    @JSONField(name = "extra")
    @JsonProperty("extra")
    private String extra;



    /**
     * 设置 [路径]
     */
    public void setPathname(String pathname) {
        this.pathname = pathname;
        this.modify("pathname", pathname);
    }

    /**
     * 设置 [对象ID]
     */
    public void setObjectid(Long objectid) {
        this.objectid = objectid;
        this.modify("objectid", objectid);
    }

    /**
     * 设置 [文件类型]
     */
    public void setExtension(String extension) {
        this.extension = extension;
        this.modify("extension", extension);
    }

    /**
     * 设置 [对象类型]
     */
    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
        this.modify("objecttype", objecttype);
    }

    /**
     * 设置 [标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [下载次数]
     */
    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
        this.modify("downloads", downloads);
    }

    /**
     * 设置 [大小]
     */
    public void setSize(Integer size) {
        this.size = size;
        this.modify("size", size);
    }

    /**
     * 设置 [备注]
     */
    public void setExtra(String extra) {
        this.extra = extra;
        this.modify("extra", extra);
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


