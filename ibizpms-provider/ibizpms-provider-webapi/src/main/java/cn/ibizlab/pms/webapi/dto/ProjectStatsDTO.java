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
 * 服务DTO对象[ProjectStatsDTO]
 */
@Data
@ApiModel("项目统计")
//@JsonFilter(value = "dtofieldfilter")
public class ProjectStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [STANDARD]
     *
     */
    @JSONField(name = "standard")
    @JsonProperty("standard")
    @ApiModelProperty("标准规范")
    private Integer standard;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("工时类型")
    private String type;

    /**
     * 属性 [EMPTYSTORY]
     *
     */
    @JSONField(name = "emptystory")
    @JsonProperty("emptystory")
    @ApiModelProperty("空需求")
    private Integer emptystory;

    /**
     * 属性 [DRAFTSTORY]
     *
     */
    @JSONField(name = "draftstory")
    @JsonProperty("draftstory")
    @ApiModelProperty("草稿需求")
    private Integer draftstory;

    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("截止日期")
    private Timestamp end;

    /**
     * 属性 [RELEASEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "releasedstagestorycnt")
    @JsonProperty("releasedstagestorycnt")
    @ApiModelProperty("已发布阶段需求数")
    private Integer releasedstagestorycnt;

    /**
     * 属性 [IMPORTANTBUGCNT]
     *
     */
    @JSONField(name = "importantbugcnt")
    @JsonProperty("importantbugcnt")
    @ApiModelProperty("重要Bug数")
    private Integer importantbugcnt;

    /**
     * 属性 [CANCELTASKCNT]
     *
     */
    @JSONField(name = "canceltaskcnt")
    @JsonProperty("canceltaskcnt")
    @ApiModelProperty("已取消任务数")
    private Integer canceltaskcnt;

    /**
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始时间")
    private Timestamp begin;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("状态")
    private String status;

    /**
     * 属性 [SERIOUSBUGPROPORTION]
     *
     */
    @JSONField(name = "seriousbugproportion")
    @JsonProperty("seriousbugproportion")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("严重Bug比率")
    private String seriousbugproportion;

    /**
     * 属性 [DOINGTASKCNT]
     *
     */
    @JSONField(name = "doingtaskcnt")
    @JsonProperty("doingtaskcnt")
    @ApiModelProperty("进行中任务数")
    private Integer doingtaskcnt;

    /**
     * 属性 [UNDONETASKCNT]
     *
     */
    @JSONField(name = "undonetaskcnt")
    @JsonProperty("undonetaskcnt")
    @ApiModelProperty("未完成任务总数")
    private Integer undonetaskcnt;

    /**
     * 属性 [SERVETASKCNT]
     *
     */
    @JSONField(name = "servetaskcnt")
    @JsonProperty("servetaskcnt")
    @ApiModelProperty("服务类型任务")
    private Integer servetaskcnt;

    /**
     * 属性 [DONETASKCNT]
     *
     */
    @JSONField(name = "donetaskcnt")
    @JsonProperty("donetaskcnt")
    @ApiModelProperty("已完成任务数")
    private Integer donetaskcnt;

    /**
     * 属性 [TOTALWH]
     *
     */
    @JSONField(name = "totalwh")
    @JsonProperty("totalwh")
    @ApiModelProperty("总工时")
    private Integer totalwh;

    /**
     * 属性 [UNCLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "unclosedstorycnt")
    @JsonProperty("unclosedstorycnt")
    @ApiModelProperty("未关闭需求总数")
    private Integer unclosedstorycnt;

    /**
     * 属性 [MISCTASKCNT]
     *
     */
    @JSONField(name = "misctaskcnt")
    @JsonProperty("misctaskcnt")
    @ApiModelProperty("其他类型任务")
    private Integer misctaskcnt;

    /**
     * 属性 [LEFTSTORYCNT]
     *
     */
    @JSONField(name = "leftstorycnt")
    @JsonProperty("leftstorycnt")
    @ApiModelProperty("剩余需求数")
    private Integer leftstorycnt;

    /**
     * 属性 [PERFORMANCE]
     *
     */
    @JSONField(name = "performance")
    @JsonProperty("performance")
    @ApiModelProperty("性能问题")
    private Integer performance;

    /**
     * 属性 [EMPTYSTAGESTORYCNT]
     *
     */
    @JSONField(name = "emptystagestorycnt")
    @JsonProperty("emptystagestorycnt")
    @ApiModelProperty("空阶段需求数")
    private Integer emptystagestorycnt;

    /**
     * 属性 [WAITSTAGESTORYCNT]
     *
     */
    @JSONField(name = "waitstagestorycnt")
    @JsonProperty("waitstagestorycnt")
    @ApiModelProperty("未开始阶段需求数")
    private Integer waitstagestorycnt;

    /**
     * 属性 [DEVELOPINGSTAGESTORYCNT]
     *
     */
    @JSONField(name = "developingstagestorycnt")
    @JsonProperty("developingstagestorycnt")
    @ApiModelProperty("研发中阶段需求数")
    private Integer developingstagestorycnt;

    /**
     * 属性 [ISTOP]
     *
     */
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;

    /**
     * 属性 [COMPLETESTORYCNT]
     *
     */
    @JSONField(name = "completestorycnt")
    @JsonProperty("completestorycnt")
    @ApiModelProperty("完成需求数")
    private Integer completestorycnt;

    /**
     * 属性 [TESTTASKCNT]
     *
     */
    @JSONField(name = "testtaskcnt")
    @JsonProperty("testtaskcnt")
    @ApiModelProperty("测试类型任务")
    private Integer testtaskcnt;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [STORYCNT]
     *
     */
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求总数")
    private Integer storycnt;

    /**
     * 属性 [PROJECTEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "projectedstagestorycnt")
    @JsonProperty("projectedstagestorycnt")
    @ApiModelProperty("已立项阶段需求数")
    private Integer projectedstagestorycnt;

    /**
     * 属性 [DISCUSSTASKCNT]
     *
     */
    @JSONField(name = "discusstaskcnt")
    @JsonProperty("discusstaskcnt")
    @ApiModelProperty("讨论类型任务")
    private Integer discusstaskcnt;

    /**
     * 属性 [COMPLETETASKCNT]
     *
     */
    @JSONField(name = "completetaskcnt")
    @JsonProperty("completetaskcnt")
    @ApiModelProperty("完成任务数")
    private Integer completetaskcnt;

    /**
     * 属性 [PLANNEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "plannedstagestorycnt")
    @JsonProperty("plannedstagestorycnt")
    @ApiModelProperty("已计划阶段需求数")
    private Integer plannedstagestorycnt;

    /**
     * 属性 [TESTEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "testedstagestorycnt")
    @JsonProperty("testedstagestorycnt")
    @ApiModelProperty("测试完毕阶段需求数")
    private Integer testedstagestorycnt;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[项目名称]不允许为空!")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("项目名称")
    private String name;

    /**
     * 属性 [TASKCNT]
     *
     */
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务总数")
    private Integer taskcnt;

    /**
     * 属性 [TOTALLEFT]
     *
     */
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    @ApiModelProperty("任务预计剩余总工时")
    private Double totalleft;

    /**
     * 属性 [TIMESCALE]
     *
     */
    @JSONField(name = "timescale")
    @JsonProperty("timescale")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("工期")
    private String timescale;

    /**
     * 属性 [ACTIVESTORY]
     *
     */
    @JSONField(name = "activestory")
    @JsonProperty("activestory")
    @ApiModelProperty("激活需求")
    private Integer activestory;

    /**
     * 属性 [ORDER1]
     *
     */
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("项目排序")
    private Integer order1;

    /**
     * 属性 [ACTIVEBUGCNT]
     *
     */
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    @ApiModelProperty("未解决Bug总数")
    private Integer activebugcnt;

    /**
     * 属性 [DESIGNDEFECT]
     *
     */
    @JSONField(name = "designdefect")
    @JsonProperty("designdefect")
    @ApiModelProperty("设计缺陷")
    private Integer designdefect;

    /**
     * 属性 [WAITTASKCNT]
     *
     */
    @JSONField(name = "waittaskcnt")
    @JsonProperty("waittaskcnt")
    @ApiModelProperty("未开始任务数")
    private Integer waittaskcnt;

    /**
     * 属性 [PAUSETASKCNT]
     *
     */
    @JSONField(name = "pausetaskcnt")
    @JsonProperty("pausetaskcnt")
    @ApiModelProperty("已暂停任务数")
    private Integer pausetaskcnt;

    /**
     * 属性 [CLOSEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "closedstagestorycnt")
    @JsonProperty("closedstagestorycnt")
    @ApiModelProperty("已关闭阶段需求数")
    private Integer closedstagestorycnt;

    /**
     * 属性 [TOTALESTIMATE]
     *
     */
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    @ApiModelProperty("任务最初预计总工时")
    private Double totalestimate;

    /**
     * 属性 [VERIFIEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "verifiedstagestorycnt")
    @JsonProperty("verifiedstagestorycnt")
    @ApiModelProperty("已验收阶段需求数")
    private Integer verifiedstagestorycnt;

    /**
     * 属性 [INSTALL]
     *
     */
    @JSONField(name = "install")
    @JsonProperty("install")
    @ApiModelProperty("安装部署")
    private Integer install;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long id;

    /**
     * 属性 [DESIGNTASKCNT]
     *
     */
    @JSONField(name = "designtaskcnt")
    @JsonProperty("designtaskcnt")
    @ApiModelProperty("设计类型任务")
    private Integer designtaskcnt;

    /**
     * 属性 [SECURITY]
     *
     */
    @JSONField(name = "security")
    @JsonProperty("security")
    @ApiModelProperty("安全相关")
    private Integer security;

    /**
     * 属性 [OTHERS]
     *
     */
    @JSONField(name = "others")
    @JsonProperty("others")
    @ApiModelProperty("其他")
    private Integer others;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug总数")
    private Integer bugcnt;

    /**
     * 属性 [UNCLOSEDBUGCNT]
     *
     */
    @JSONField(name = "unclosedbugcnt")
    @JsonProperty("unclosedbugcnt")
    @ApiModelProperty("未关闭Bug总数")
    private Integer unclosedbugcnt;

    /**
     * 属性 [YESTERDAYCTASKCNT]
     *
     */
    @JSONField(name = "yesterdayctaskcnt")
    @JsonProperty("yesterdayctaskcnt")
    @ApiModelProperty("昨日完成任务数")
    private Integer yesterdayctaskcnt;

    /**
     * 属性 [TIME]
     *
     */
    @JSONField(name = "time")
    @JsonProperty("time")
    @ApiModelProperty("工时")
    private Double time;

    /**
     * 属性 [AUTOMATION]
     *
     */
    @JSONField(name = "automation")
    @JsonProperty("automation")
    @ApiModelProperty("测试脚本")
    private Integer automation;

    /**
     * 属性 [UNCONFIRMEDBUGCNT]
     *
     */
    @JSONField(name = "unconfirmedbugcnt")
    @JsonProperty("unconfirmedbugcnt")
    @ApiModelProperty("未确认Bug总数")
    private Integer unconfirmedbugcnt;

    /**
     * 属性 [DEVELOPEDSTAGESTORYCNT]
     *
     */
    @JSONField(name = "developedstagestorycnt")
    @JsonProperty("developedstagestorycnt")
    @ApiModelProperty("研发完毕阶段需求数")
    private Integer developedstagestorycnt;

    /**
     * 属性 [TESTINGSTAGESTORYCNT]
     *
     */
    @JSONField(name = "testingstagestorycnt")
    @JsonProperty("testingstagestorycnt")
    @ApiModelProperty("测试中阶段需求数")
    private Integer testingstagestorycnt;

    /**
     * 属性 [FINISHTASKCNT]
     *
     */
    @JSONField(name = "finishtaskcnt")
    @JsonProperty("finishtaskcnt")
    @ApiModelProperty("已结束任务总数")
    private Integer finishtaskcnt;

    /**
     * 属性 [CONFIG]
     *
     */
    @JSONField(name = "config")
    @JsonProperty("config")
    @ApiModelProperty("配置相关")
    private Integer config;

    /**
     * 属性 [TOTALCONSUMED]
     *
     */
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    @ApiModelProperty("任务消耗总工时")
    private Double totalconsumed;

    /**
     * 属性 [AFFAIRTASKCNT]
     *
     */
    @JSONField(name = "affairtaskcnt")
    @JsonProperty("affairtaskcnt")
    @ApiModelProperty("事务类型任务")
    private Integer affairtaskcnt;

    /**
     * 属性 [PROJECTTOTALCONSUMED]
     *
     */
    @JSONField(name = "projecttotalconsumed")
    @JsonProperty("projecttotalconsumed")
    @ApiModelProperty("项目消耗总工时")
    private Double projecttotalconsumed;

    /**
     * 属性 [UITASKCNT]
     *
     */
    @JSONField(name = "uitaskcnt")
    @JsonProperty("uitaskcnt")
    @ApiModelProperty("界面类型任务")
    private Integer uitaskcnt;

    /**
     * 属性 [BUGSTORY]
     *
     */
    @JSONField(name = "bugstory")
    @JsonProperty("bugstory")
    @ApiModelProperty("Bug/完成需求")
    private Integer bugstory;

    /**
     * 属性 [CLOSEDSTORY]
     *
     */
    @JSONField(name = "closedstory")
    @JsonProperty("closedstory")
    @ApiModelProperty("已关闭需求")
    private Integer closedstory;

    /**
     * 属性 [BUGTASK]
     *
     */
    @JSONField(name = "bugtask")
    @JsonProperty("bugtask")
    @ApiModelProperty("Bug/完成任务")
    private Integer bugtask;

    /**
     * 属性 [RELEASEDSTORYCNT]
     *
     */
    @JSONField(name = "releasedstorycnt")
    @JsonProperty("releasedstorycnt")
    @ApiModelProperty("已发布需求数")
    private Integer releasedstorycnt;

    /**
     * 属性 [STUDYTASKCNT]
     *
     */
    @JSONField(name = "studytaskcnt")
    @JsonProperty("studytaskcnt")
    @ApiModelProperty("研究类型任务")
    private Integer studytaskcnt;

    /**
     * 属性 [CLOSEDTASKCNT]
     *
     */
    @JSONField(name = "closedtaskcnt")
    @JsonProperty("closedtaskcnt")
    @ApiModelProperty("已关闭任务数")
    private Integer closedtaskcnt;

    /**
     * 属性 [CODEERROR]
     *
     */
    @JSONField(name = "codeerror")
    @JsonProperty("codeerror")
    @ApiModelProperty("代码错误")
    private Integer codeerror;

    /**
     * 属性 [CLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    @ApiModelProperty("关闭需求总数")
    private Integer closedstorycnt;

    /**
     * 属性 [FINISHBUGCNT]
     *
     */
    @JSONField(name = "finishbugcnt")
    @JsonProperty("finishbugcnt")
    @ApiModelProperty("已解决Bug总数")
    private Integer finishbugcnt;

    /**
     * 属性 [CHANGEDSTORY]
     *
     */
    @JSONField(name = "changedstory")
    @JsonProperty("changedstory")
    @ApiModelProperty("已变更需求")
    private Integer changedstory;

    /**
     * 属性 [DEVELTASKCNT]
     *
     */
    @JSONField(name = "develtaskcnt")
    @JsonProperty("develtaskcnt")
    @ApiModelProperty("开发类型任务")
    private Integer develtaskcnt;

    /**
     * 属性 [MEMBERCNT]
     *
     */
    @JSONField(name = "membercnt")
    @JsonProperty("membercnt")
    @ApiModelProperty("人数")
    private Integer membercnt;

    /**
     * 属性 [PROGRESS]
     *
     */
    @JSONField(name = "progress")
    @JsonProperty("progress")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("进度")
    private String progress;

    /**
     * 属性 [YESTERDAYRBUGCNT]
     *
     */
    @JSONField(name = "yesterdayrbugcnt")
    @JsonProperty("yesterdayrbugcnt")
    @ApiModelProperty("昨天解决Bug数")
    private Integer yesterdayrbugcnt;


    /**
     * 设置 [END]
     */
    public void setEnd(Timestamp  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [BEGIN]
     */
    public void setBegin(Timestamp  begin){
        this.begin = begin ;
        this.modify("begin",begin);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }


}


