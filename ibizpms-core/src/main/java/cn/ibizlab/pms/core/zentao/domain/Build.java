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
 * 实体[版本]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_build", resultMap = "BuildResultMap")
@ApiModel("版本")
public class Build extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * Bug版本健值
     */
    @TableField(exist = false)
    @JSONField(name = "ids")
    @JsonProperty("ids")
    @ApiModelProperty("Bug版本健值")
    private String ids;
    /**
     * 名称编号
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("名称编号")
    private String name;
    /**
     * 后台体系
     */
    @TableField(exist = false)
    @JSONField(name = "backgroundid")
    @JsonProperty("backgroundid")
    @ApiModelProperty("后台体系")
    private String backgroundid;
    /**
     * 构建者
     */
    @DEField(defaultValueType = DEFieldDefaultValueType.OPERATORNAME)
    @TableField(value = "`BUILDER`")
    @JSONField(name = "builder")
    @JsonProperty("builder")
    @ApiModelProperty("构建者")
    private String builder;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`CREATEBY`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 运行模式
     */
    @TableField(exist = false)
    @JSONField(name = "releasetype")
    @JsonProperty("releasetype")
    @ApiModelProperty("运行模式")
    private String releasetype;
    /**
     * 构建者（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "builderpk")
    @JsonProperty("builderpk")
    @ApiModelProperty("构建者（选择）")
    private String builderpk;
    /**
     * 重新构建
     */
    @TableField(exist = false)
    @JSONField(name = "rebuild")
    @JsonProperty("rebuild")
    @ApiModelProperty("重新构建")
    private Integer rebuild;
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
     * 描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("描述")
    private String desc;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
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
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * 运行数据库
     */
    @TableField(exist = false)
    @JSONField(name = "sqlid")
    @JsonProperty("sqlid")
    @ApiModelProperty("运行数据库")
    private String sqlid;
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
     * 源代码地址
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SCMPATH`")
    @JSONField(name = "scmpath")
    @JsonProperty("scmpath")
    @ApiModelProperty("源代码地址")
    private String scmpath;
    /**
     * 下载地址
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`FILEPATH`")
    @JSONField(name = "filepath")
    @JsonProperty("filepath")
    @ApiModelProperty("下载地址")
    private String filepath;
    /**
     * 产生的bug
     */
    @TableField(exist = false)
    @JSONField(name = "createbugcnt")
    @JsonProperty("createbugcnt")
    @ApiModelProperty("产生的bug")
    private String createbugcnt;
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
     * 完成的需求
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`STORIES`")
    @JSONField(name = "stories")
    @JsonProperty("stories")
    @ApiModelProperty("完成的需求")
    private String stories;
    /**
     * 解决的Bug
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`BUGS`")
    @JSONField(name = "bugs")
    @JsonProperty("bugs")
    @ApiModelProperty("解决的Bug")
    private String bugs;
    /**
     * 系统应用
     */
    @TableField(exist = false)
    @JSONField(name = "frontapplication")
    @JsonProperty("frontapplication")
    @ApiModelProperty("系统应用")
    private String frontapplication;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 打包日期
     */
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("打包日期")
    private Timestamp date;
    /**
     * 产品名称
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品名称")
    private String productname;
    /**
     * 产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
    private Long product;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BRANCH`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("平台/分支")
    private Long branch;
    /**
     * 所属项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PROJECT`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("所属项目")
    private Long project;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Branch ztbranch;

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
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;



    /**
     * 设置 [名称编号]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [构建者]
     */
    public void setBuilder(String builder) {
        this.builder = builder;
        this.modify("builder", builder);
    }

    /**
     * 设置 [描述]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [源代码地址]
     */
    public void setScmpath(String scmpath) {
        this.scmpath = scmpath;
        this.modify("scmpath", scmpath);
    }

    /**
     * 设置 [下载地址]
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
        this.modify("filepath", filepath);
    }

    /**
     * 设置 [完成的需求]
     */
    public void setStories(String stories) {
        this.stories = stories;
        this.modify("stories", stories);
    }

    /**
     * 设置 [解决的Bug]
     */
    public void setBugs(String bugs) {
        this.bugs = bugs;
        this.modify("bugs", bugs);
    }

    /**
     * 设置 [打包日期]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [打包日期]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [所属项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
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


