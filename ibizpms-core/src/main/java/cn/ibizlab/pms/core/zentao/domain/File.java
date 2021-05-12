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
 * 实体[附件]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_file", resultMap = "FileResultMap")
@ApiModel("附件")
public class File extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路径
     */
    @TableField(value = "`PATHNAME`")
    @JSONField(name = "pathname")
    @JsonProperty("pathname")
    @ApiModelProperty("路径")
    private String pathname;
    /**
     * 对象ID
     */
    @TableField(value = "`OBJECTID`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    @ApiModelProperty("对象ID")
    private Long objectid;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORG`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 显示大小
     */
    @TableField(exist = false)
    @JSONField(name = "strsize")
    @JsonProperty("strsize")
    @ApiModelProperty("显示大小")
    private String strsize;
    /**
     * 文档类型
     */
    @TableField(exist = false)
    @JSONField(name = "doclibtype")
    @JsonProperty("doclibtype")
    @ApiModelProperty("文档类型")
    private String doclibtype;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 文件类型
     */
    @TableField(value = "`EXTENSION`")
    @JSONField(name = "extension")
    @JsonProperty("extension")
    @ApiModelProperty("文件类型")
    private String extension;
    /**
     * 对象类型
     */
    @TableField(value = "`OBJECTTYPE`")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @ApiModelProperty("对象类型")
    private String objecttype;
    /**
     * 由谁添加
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`ADDEDBY`")
    @JSONField(name = "addedby")
    @JsonProperty("addedby")
    @ApiModelProperty("由谁添加")
    private String addedby;
    /**
     * 标题
     */
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("标题")
    private String title;
    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 添加时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`ADDEDDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "addeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("addeddate")
    @ApiModelProperty("添加时间")
    private Timestamp addeddate;
    /**
     * 下载次数
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`DOWNLOADS`")
    @JSONField(name = "downloads")
    @JsonProperty("downloads")
    @ApiModelProperty("下载次数")
    private Integer downloads;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 大小
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`SIZE`")
    @JSONField(name = "size")
    @JsonProperty("size")
    @ApiModelProperty("大小")
    private Integer size;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * id
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 备注
     */
    @TableField(value = "`EXTRA`")
    @JSONField(name = "extra")
    @JsonProperty("extra")
    @ApiModelProperty("备注")
    private String extra;
    /**
     * 附件编号
     */
    @TableField(value = "`FILESN`")
    @JSONField(name = "filesn")
    @JsonProperty("filesn")
    @ApiModelProperty("附件编号")
    private Long filesn;



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

    /**
     * 设置 [附件编号]
     */
    public void setFilesn(Long filesn) {
        this.filesn = filesn;
        this.modify("filesn", filesn);
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


