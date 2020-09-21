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
 * 实体[发布]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_release",resultMap = "ReleaseResultMap")
public class Release extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 完成的需求
     */
    @TableField(value = "stories")
    @JSONField(name = "stories")
    @JsonProperty("stories")
    private String stories;
    /**
     * 里程碑
     */
    @DEField(defaultValue = "0")
    @TableField(value = "marker")
    @JSONField(name = "marker")
    @JsonProperty("marker")
    private Integer marker;
    /**
     * ID
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 遗留的Bug
     */
    @TableField(value = "leftbugs")
    @JSONField(name = "leftbugs")
    @JsonProperty("leftbugs")
    private String leftbugs;
    /**
     * 解决的Bug
     */
    @TableField(value = "bugs")
    @JSONField(name = "bugs")
    @JsonProperty("bugs")
    private String bugs;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0" , preType = DEPredefinedFieldType.LOGICVALID, logicval = "0" , logicdelval="1")
    @TableLogic(value= "0",delval="1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * 发布名称
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 发布日期
     */
    @TableField(value = "date")
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 状态
     */
    @DEField(defaultValue = "normal")
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 子状态
     */
    @TableField(value = "substatus")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    private String substatus;
    /**
     * 描述
     */
    @TableField(value = "desc")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    private String desc;
    /**
     * 版本
     */
    @TableField(exist = false)
    @JSONField(name = "buildname")
    @JsonProperty("buildname")
    private String buildname;
    /**
     * 产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Long product;
    /**
     * 版本
     */
    @TableField(value = "build")
    @JSONField(name = "build")
    @JsonProperty("build")
    private Long build;
    /**
     * 平台/分支
     */
    @TableField(value = "branch")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    private Long branch;
    /**
     * 产品名称
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    private String productname;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    private String files;
    /**
     * 后台体系
     */
    @TableField(exist = false)
    @JSONField(name = "backgroundid")
    @JsonProperty("backgroundid")
    private String backgroundid;
    /**
     * 运行数据库
     */
    @TableField(exist = false)
    @JSONField(name = "sqlid")
    @JsonProperty("sqlid")
    private String sqlid;
    /**
     * 系统应用
     */
    @TableField(exist = false)
    @JSONField(name = "frontapplication")
    @JsonProperty("frontapplication")
    private String frontapplication;
    /**
     * 重新构建
     */
    @TableField(exist = false)
    @JSONField(name = "rebuild")
    @JsonProperty("rebuild")
    private Integer rebuild;
    /**
     * 运行模式
     */
    @TableField(exist = false)
    @JSONField(name = "releasetype")
    @JsonProperty("releasetype")
    private String releasetype;
    /**
     * 构建者
     */
    @TableField(exist = false)
    @JSONField(name = "builder")
    @JsonProperty("builder")
    private String builder;
    /**
     * 打包日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "builddate" , format="yyyy-MM-dd")
    @JsonProperty("builddate")
    private Timestamp builddate;

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
    private cn.ibizlab.pms.core.zentao.domain.Build ztbuild;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztproduct;



    /**
     * 设置 [完成的需求]
     */
    public void setStories(String stories){
        this.stories = stories ;
        this.modify("stories",stories);
    }

    /**
     * 设置 [里程碑]
     */
    public void setMarker(Integer marker){
        this.marker = marker ;
        this.modify("marker",marker);
    }

    /**
     * 设置 [遗留的Bug]
     */
    public void setLeftbugs(String leftbugs){
        this.leftbugs = leftbugs ;
        this.modify("leftbugs",leftbugs);
    }

    /**
     * 设置 [解决的Bug]
     */
    public void setBugs(String bugs){
        this.bugs = bugs ;
        this.modify("bugs",bugs);
    }

    /**
     * 设置 [发布名称]
     */
    public void setName(String name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [发布日期]
     */
    public void setDate(Timestamp date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 格式化日期 [发布日期]
     */
    public String formatDate(){
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [状态]
     */
    public void setStatus(String status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [描述]
     */
    public void setDesc(String desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [产品]
     */
    public void setProduct(Long product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [版本]
     */
    public void setBuild(Long build){
        this.build = build ;
        this.modify("build",build);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch){
        this.branch = branch ;
        this.modify("branch",branch);
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
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


