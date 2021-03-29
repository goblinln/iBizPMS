package cn.ibizlab.pms.core.ibiz.domain;

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
 * 实体[更新日志]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_SYS_UPDATE_LOG", resultMap = "SysUpdateLogResultMap")
public class SysUpdateLog extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 系统更新日志标识
     */
    @DEField(name = "sys_update_logid", isKeyField = true)
    @TableId(value = "sys_update_logid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "sysupdatelogid")
    @JsonProperty("sysupdatelogid")
    private String sysupdatelogid;
    /**
     * 更新平台
     */
    @DEField(defaultValue = "MOB")
    @TableField(value = "`updatebranch`")
    @JSONField(name = "updatebranch")
    @JsonProperty("updatebranch")
    private String updatebranch;
    /**
     * 更新说明
     */
    @TableField(value = "`updesc`")
    @JSONField(name = "updesc")
    @JsonProperty("updesc")
    private String updesc;
    /**
     * 更新名称
     */
    @DEField(name = "sys_update_logname")
    @TableField(value = "`sys_update_logname`")
    @JSONField(name = "sysupdatelogname")
    @JsonProperty("sysupdatelogname")
    private String sysupdatelogname;
    /**
     * 最新更新
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`latestupdate`")
    @JSONField(name = "latestupdate")
    @JsonProperty("latestupdate")
    private Integer latestupdate;
    /**
     * 更新日期
     */
    @TableField(value = "`update`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "update", format = "yyyy-MM-dd")
    @JsonProperty("update")
    private Timestamp update;



    /**
     * 设置 [更新平台]
     */
    public void setUpdatebranch(String updatebranch) {
        this.updatebranch = updatebranch;
        this.modify("updatebranch", updatebranch);
    }

    /**
     * 设置 [更新说明]
     */
    public void setUpdesc(String updesc) {
        this.updesc = updesc;
        this.modify("updesc", updesc);
    }

    /**
     * 设置 [更新名称]
     */
    public void setSysupdatelogname(String sysupdatelogname) {
        this.sysupdatelogname = sysupdatelogname;
        this.modify("sys_update_logname", sysupdatelogname);
    }

    /**
     * 设置 [最新更新]
     */
    public void setLatestupdate(Integer latestupdate) {
        this.latestupdate = latestupdate;
        this.modify("latestupdate", latestupdate);
    }

    /**
     * 设置 [更新日期]
     */
    public void setUpdate(Timestamp update) {
        this.update = update;
        this.modify("update", update);
    }

    /**
     * 格式化日期 [更新日期]
     */
    public String formatUpdate() {
        if (this.update == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(update);
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
        this.reset("sys_update_logid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


