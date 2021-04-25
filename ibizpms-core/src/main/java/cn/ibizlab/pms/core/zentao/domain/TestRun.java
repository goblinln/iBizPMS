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
 * 实体[测试运行]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_testrun", resultMap = "TestRunResultMap")
@ApiModel("测试运行")
public class TestRun extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结果
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`LASTRUNRESULT`")
    @JSONField(name = "lastrunresult")
    @JsonProperty("lastrunresult")
    @ApiModelProperty("结果")
    private String lastrunresult;
    /**
     * 最后执行时间
     */
    @TableField(value = "`LASTRUNDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastrundate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastrundate")
    @ApiModelProperty("最后执行时间")
    private Timestamp lastrundate;
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
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 指派给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`ASSIGNEDTO`")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
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
     * 最后执行人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`LASTRUNNER`")
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    @ApiModelProperty("最后执行人")
    private String lastrunner;
    /**
     * 当前状态
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("当前状态")
    private String status;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
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
     * 用例版本
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;
    /**
     * 测试用例
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`CASE`")
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    @ApiModelProperty("测试用例")
    private Long ibizcase;
    /**
     * 测试单
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`TASK`")
    @JSONField(name = "task")
    @JsonProperty("task")
    @ApiModelProperty("测试单")
    private Long task;
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
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;

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
    private cn.ibizlab.pms.core.zentao.domain.TestTask zttesttask;


    /**
     * 测试结果
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.zentao.domain.TestResult> testresults;


    /**
     * 设置 [结果]
     */
    public void setLastrunresult(String lastrunresult) {
        this.lastrunresult = lastrunresult;
        this.modify("lastrunresult", lastrunresult);
    }

    /**
     * 设置 [最后执行时间]
     */
    public void setLastrundate(Timestamp lastrundate) {
        this.lastrundate = lastrundate;
        this.modify("lastrundate", lastrundate);
    }

    /**
     * 格式化日期 [最后执行时间]
     */
    public String formatLastrundate() {
        if (this.lastrundate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastrundate);
    }
    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [最后执行人]
     */
    public void setLastrunner(String lastrunner) {
        this.lastrunner = lastrunner;
        this.modify("lastrunner", lastrunner);
    }

    /**
     * 设置 [当前状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [测试用例]
     */
    public void setIbizcase(Long ibizcase) {
        this.ibizcase = ibizcase;
        this.modify("case", ibizcase);
    }

    /**
     * 设置 [测试单]
     */
    public void setTask(Long task) {
        this.task = task;
        this.modify("task", task);
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


