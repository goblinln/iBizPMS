package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[ProductSumDTO]
 */
@Data
@ApiModel("产品汇总表")
//@JsonFilter(value = "dtofieldfilter")
public class ProductSumDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [DESIGNDEFECT]
     *
     */
    @JSONField(name = "designdefect")
    @JsonProperty("designdefect")
    @ApiModelProperty("设计缺陷")
    private Integer designdefect;

    /**
     * 属性 [CHANGEDSTORYCNT]
     *
     */
    @JSONField(name = "changedstorycnt")
    @JsonProperty("changedstorycnt")
    @ApiModelProperty("已变更")
    private Integer changedstorycnt;

    /**
     * 属性 [DEVELOPINGSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "developingstagestoryhours")
    @JsonProperty("developingstagestoryhours")
    @ApiModelProperty("研发中阶段需求工时")
    private Integer developingstagestoryhours;

    /**
     * 属性 [STORYCNT]
     *
     */
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("总计")
    private Integer storycnt;

    /**
     * 属性 [OTHERS]
     *
     */
    @JSONField(name = "others")
    @JsonProperty("others")
    @ApiModelProperty("其他")
    private Integer others;

    /**
     * 属性 [CONFIG]
     *
     */
    @JSONField(name = "config")
    @JsonProperty("config")
    @ApiModelProperty("配置相关")
    private Integer config;

    /**
     * 属性 [CODEERROR]
     *
     */
    @JSONField(name = "codeerror")
    @JsonProperty("codeerror")
    @ApiModelProperty("代码错误")
    private Integer codeerror;

    /**
     * 属性 [BUGSUM]
     *
     */
    @JSONField(name = "bugsum")
    @JsonProperty("bugsum")
    @ApiModelProperty("bug总计")
    private Integer bugsum;

    /**
     * 属性 [PERFORMANCE]
     *
     */
    @JSONField(name = "performance")
    @JsonProperty("performance")
    @ApiModelProperty("性能问题")
    private Integer performance;

    /**
     * 属性 [END]
     *
     */
    @JSONField(name = "end")
    @JsonProperty("end")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("结束日期")
    private String end;

    /**
     * 属性 [CLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    @ApiModelProperty("已关闭")
    private Integer closedstorycnt;

    /**
     * 属性 [CLOSEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "closedstagestoryhours")
    @JsonProperty("closedstagestoryhours")
    @ApiModelProperty("已关闭阶段需求工时")
    private Integer closedstagestoryhours;

    /**
     * 属性 [TESTEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "testedstagestoryhours")
    @JsonProperty("testedstagestoryhours")
    @ApiModelProperty("测试完毕阶段需求工时")
    private Integer testedstagestoryhours;

    /**
     * 属性 [WAITSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "waitstagestoryhours")
    @JsonProperty("waitstagestoryhours")
    @ApiModelProperty("未开始阶段需求工时")
    private Integer waitstagestoryhours;

    /**
     * 属性 [PO]
     *
     */
    @JSONField(name = "po")
    @JsonProperty("po")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("产品负责人")
    private String po;

    /**
     * 属性 [TESTINGSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "testingstagestoryhours")
    @JsonProperty("testingstagestoryhours")
    @ApiModelProperty("测试中阶段需求工时")
    private Integer testingstagestoryhours;

    /**
     * 属性 [PROJECTEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "projectedstagestoryhours")
    @JsonProperty("projectedstagestoryhours")
    @ApiModelProperty("已立项阶段需求工时")
    private Integer projectedstagestoryhours;

    /**
     * 属性 [PROJECTEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "projectedstagestorycnt")
    @JsonProperty("projectedstagestorycnt")
    @ApiModelProperty("已立项阶段需求数量")
    private Integer projectedstagestorycnt;

    /**
     * 属性 [VERIFIEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "verifiedstagestorycnt")
    @JsonProperty("verifiedstagestorycnt")
    @ApiModelProperty("已验收阶段需求数量")
    private Integer verifiedstagestorycnt;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键标识")
    private Long id;

    /**
     * 属性 [TOTALHOURS]
     *
     */
    @JSONField(name = "totalhours")
    @JsonProperty("totalhours")
    @ApiModelProperty("总工时")
    private Integer totalhours;

    /**
     * 属性 [PLANEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "planedstagestorycnt")
    @JsonProperty("planedstagestorycnt")
    @ApiModelProperty("已计划阶段需求数量")
    private Integer planedstagestorycnt;

    /**
     * 属性 [DEVELOPINGSTAGESTORYCNT]
     *
     */
    @JSONField(name = "developingstagestorycnt")
    @JsonProperty("developingstagestorycnt")
    @ApiModelProperty("研发中阶段需求数量")
    private Integer developingstagestorycnt;

    /**
     * 属性 [ACTIVESTORYCNT]
     *
     */
    @JSONField(name = "activestorycnt")
    @JsonProperty("activestorycnt")
    @ApiModelProperty("激活")
    private Integer activestorycnt;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug数")
    private Integer bugcnt;

    /**
     * 属性 [WAITSTAGESTORYCNT]
     *
     */
    @JSONField(name = "waitstagestorycnt")
    @JsonProperty("waitstagestorycnt")
    @ApiModelProperty("未开始阶段需求数量")
    private Integer waitstagestorycnt;

    /**
     * 属性 [VERIFIEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "verifiedstagestoryhours")
    @JsonProperty("verifiedstagestoryhours")
    @ApiModelProperty("已验收阶段需求工时")
    private Integer verifiedstagestoryhours;

    /**
     * 属性 [WAITSTORYCNT]
     *
     */
    @JSONField(name = "waitstorycnt")
    @JsonProperty("waitstorycnt")
    @ApiModelProperty("草稿")
    private Integer waitstorycnt;

    /**
     * 属性 [TESTINGSTAGESTORYCNT]
     *
     */
    @JSONField(name = "testingstagestorycnt")
    @JsonProperty("testingstagestorycnt")
    @ApiModelProperty("测试中阶段需求数量")
    private Integer testingstagestorycnt;

    /**
     * 属性 [BEGIN]
     *
     */
    @JSONField(name = "begin")
    @JsonProperty("begin")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("开始日期")
    private String begin;

    /**
     * 属性 [PLAN]
     *
     */
    @JSONField(name = "plan")
    @JsonProperty("plan")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("计划")
    private Long plan;

    /**
     * 属性 [RELEASEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "releasedstagestoryhours")
    @JsonProperty("releasedstagestoryhours")
    @ApiModelProperty("已发布阶段需求工时")
    private Integer releasedstagestoryhours;

    /**
     * 属性 [DEVELOPEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "developedstagestoryhours")
    @JsonProperty("developedstagestoryhours")
    @ApiModelProperty("研发完毕阶段需求工时")
    private Integer developedstagestoryhours;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 属性 [DEVELOPEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "developedstagestorycnt")
    @JsonProperty("developedstagestorycnt")
    @ApiModelProperty("研发完毕阶段需求数量")
    private Integer developedstagestorycnt;

    /**
     * 属性 [AUTOMATION]
     *
     */
    @JSONField(name = "automation")
    @JsonProperty("automation")
    @ApiModelProperty("测试脚本")
    private Integer automation;

    /**
     * 属性 [PLANEDSTAGESTORYHOURS]
     *
     */
    @JSONField(name = "planedstagestoryhours")
    @JsonProperty("planedstagestoryhours")
    @ApiModelProperty("已计划阶段需求工时")
    private Integer planedstagestoryhours;

    /**
     * 属性 [INSTALL]
     *
     */
    @JSONField(name = "install")
    @JsonProperty("install")
    @ApiModelProperty("安装部署")
    private Integer install;

    /**
     * 属性 [SECURITY]
     *
     */
    @JSONField(name = "security")
    @JsonProperty("security")
    @ApiModelProperty("安全相关")
    private Integer security;

    /**
     * 属性 [RELEASEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "releasedstagestorycnt")
    @JsonProperty("releasedstagestorycnt")
    @ApiModelProperty("已发布阶段需求数量")
    private Integer releasedstagestorycnt;

    /**
     * 属性 [TESTEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "testedstagestorycnt")
    @JsonProperty("testedstagestorycnt")
    @ApiModelProperty("测试完毕阶段需求数量")
    private Integer testedstagestorycnt;

    /**
     * 属性 [STANDARD]
     *
     */
    @JSONField(name = "standard")
    @JsonProperty("standard")
    @ApiModelProperty("标准规范")
    private Integer standard;

    /**
     * 属性 [CLOSEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "closedstagestorycnt")
    @JsonProperty("closedstagestorycnt")
    @ApiModelProperty("已关闭阶段需求数量")
    private Integer closedstagestorycnt;


    /**
     * 设置 [PO]
     */
    public void setPo(String  po){
        this.po = po ;
        this.modify("po",po);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }


}


