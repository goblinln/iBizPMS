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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[项目统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_project", resultMap = "ProjectStatsResultMap")
@ApiModel("项目统计")
public class ProjectStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标准规范
     */
    @TableField(exist = false)
    @JSONField(name = "standard")
    @JsonProperty("standard")
    @ApiModelProperty("标准规范")
    private Integer standard;
    /**
     * 工时类型
     */
    @TableField(exist = false)
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("工时类型")
    private String type;
    /**
     * 空需求
     */
    @TableField(exist = false)
    @JSONField(name = "emptystory")
    @JsonProperty("emptystory")
    @ApiModelProperty("空需求")
    private Integer emptystory;
    /**
     * 草稿需求
     */
    @TableField(exist = false)
    @JSONField(name = "draftstory")
    @JsonProperty("draftstory")
    @ApiModelProperty("草稿需求")
    private Integer draftstory;
    /**
     * 截止日期
     */
    @TableField(value = "`end`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("截止日期")
    private Timestamp end;
    /**
     * 已发布阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstagestorycnt")
    @JsonProperty("releasedstagestorycnt")
    @ApiModelProperty("已发布阶段需求数")
    private Integer releasedstagestorycnt;
    /**
     * 重要Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "importantbugcnt")
    @JsonProperty("importantbugcnt")
    @ApiModelProperty("重要Bug数")
    private Integer importantbugcnt;
    /**
     * 已取消任务数
     */
    @TableField(exist = false)
    @JSONField(name = "canceltaskcnt")
    @JsonProperty("canceltaskcnt")
    @ApiModelProperty("已取消任务数")
    private Integer canceltaskcnt;
    /**
     * 开始时间
     */
    @TableField(value = "`begin`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始时间")
    private Timestamp begin;
    /**
     * 状态
     */
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("状态")
    private String status;
    /**
     * 严重Bug比率
     */
    @TableField(exist = false)
    @JSONField(name = "seriousbugproportion")
    @JsonProperty("seriousbugproportion")
    @ApiModelProperty("严重Bug比率")
    private String seriousbugproportion;
    /**
     * 进行中任务数
     */
    @TableField(exist = false)
    @JSONField(name = "doingtaskcnt")
    @JsonProperty("doingtaskcnt")
    @ApiModelProperty("进行中任务数")
    private Integer doingtaskcnt;
    /**
     * 未完成任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "undonetaskcnt")
    @JsonProperty("undonetaskcnt")
    @ApiModelProperty("未完成任务总数")
    private Integer undonetaskcnt;
    /**
     * 服务类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "servetaskcnt")
    @JsonProperty("servetaskcnt")
    @ApiModelProperty("服务类型任务")
    private Integer servetaskcnt;
    /**
     * 已完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "donetaskcnt")
    @JsonProperty("donetaskcnt")
    @ApiModelProperty("已完成任务数")
    private Integer donetaskcnt;
    /**
     * 总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalwh")
    @JsonProperty("totalwh")
    @ApiModelProperty("总工时")
    private Integer totalwh;
    /**
     * 未关闭需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "unclosedstorycnt")
    @JsonProperty("unclosedstorycnt")
    @ApiModelProperty("未关闭需求总数")
    private Integer unclosedstorycnt;
    /**
     * 其他类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "misctaskcnt")
    @JsonProperty("misctaskcnt")
    @ApiModelProperty("其他类型任务")
    private Integer misctaskcnt;
    /**
     * 剩余需求数
     */
    @TableField(exist = false)
    @JSONField(name = "leftstorycnt")
    @JsonProperty("leftstorycnt")
    @ApiModelProperty("剩余需求数")
    private Integer leftstorycnt;
    /**
     * 性能问题
     */
    @TableField(exist = false)
    @JSONField(name = "performance")
    @JsonProperty("performance")
    @ApiModelProperty("性能问题")
    private Integer performance;
    /**
     * 空阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "emptystagestorycnt")
    @JsonProperty("emptystagestorycnt")
    @ApiModelProperty("空阶段需求数")
    private Integer emptystagestorycnt;
    /**
     * 未开始阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "waitstagestorycnt")
    @JsonProperty("waitstagestorycnt")
    @ApiModelProperty("未开始阶段需求数")
    private Integer waitstagestorycnt;
    /**
     * 研发中阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "developingstagestorycnt")
    @JsonProperty("developingstagestorycnt")
    @ApiModelProperty("研发中阶段需求数")
    private Integer developingstagestorycnt;
    /**
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    @ApiModelProperty("是否置顶")
    private Integer istop;
    /**
     * 完成需求数
     */
    @TableField(exist = false)
    @JSONField(name = "completestorycnt")
    @JsonProperty("completestorycnt")
    @ApiModelProperty("完成需求数")
    private Integer completestorycnt;
    /**
     * 测试类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "testtaskcnt")
    @JsonProperty("testtaskcnt")
    @ApiModelProperty("测试类型任务")
    private Integer testtaskcnt;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    @ApiModelProperty("需求总数")
    private Integer storycnt;
    /**
     * 已立项阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "projectedstagestorycnt")
    @JsonProperty("projectedstagestorycnt")
    @ApiModelProperty("已立项阶段需求数")
    private Integer projectedstagestorycnt;
    /**
     * 讨论类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "discusstaskcnt")
    @JsonProperty("discusstaskcnt")
    @ApiModelProperty("讨论类型任务")
    private Integer discusstaskcnt;
    /**
     * 完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "completetaskcnt")
    @JsonProperty("completetaskcnt")
    @ApiModelProperty("完成任务数")
    private Integer completetaskcnt;
    /**
     * 已计划阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "plannedstagestorycnt")
    @JsonProperty("plannedstagestorycnt")
    @ApiModelProperty("已计划阶段需求数")
    private Integer plannedstagestorycnt;
    /**
     * 测试完毕阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "testedstagestorycnt")
    @JsonProperty("testedstagestorycnt")
    @ApiModelProperty("测试完毕阶段需求数")
    private Integer testedstagestorycnt;
    /**
     * 项目名称
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("项目名称")
    private String name;
    /**
     * 任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务总数")
    private Integer taskcnt;
    /**
     * 任务预计剩余总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    @ApiModelProperty("任务预计剩余总工时")
    private Double totalleft;
    /**
     * 工期
     */
    @TableField(exist = false)
    @JSONField(name = "timescale")
    @JsonProperty("timescale")
    @ApiModelProperty("工期")
    private String timescale;
    /**
     * 激活需求
     */
    @TableField(exist = false)
    @JSONField(name = "activestory")
    @JsonProperty("activestory")
    @ApiModelProperty("激活需求")
    private Integer activestory;
    /**
     * 项目排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    @ApiModelProperty("项目排序")
    private Integer order1;
    /**
     * 未解决Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    @ApiModelProperty("未解决Bug总数")
    private Integer activebugcnt;
    /**
     * 设计缺陷
     */
    @TableField(exist = false)
    @JSONField(name = "designdefect")
    @JsonProperty("designdefect")
    @ApiModelProperty("设计缺陷")
    private Integer designdefect;
    /**
     * 未开始任务数
     */
    @TableField(exist = false)
    @JSONField(name = "waittaskcnt")
    @JsonProperty("waittaskcnt")
    @ApiModelProperty("未开始任务数")
    private Integer waittaskcnt;
    /**
     * 已暂停任务数
     */
    @TableField(exist = false)
    @JSONField(name = "pausetaskcnt")
    @JsonProperty("pausetaskcnt")
    @ApiModelProperty("已暂停任务数")
    private Integer pausetaskcnt;
    /**
     * 已关闭阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "closedstagestorycnt")
    @JsonProperty("closedstagestorycnt")
    @ApiModelProperty("已关闭阶段需求数")
    private Integer closedstagestorycnt;
    /**
     * 任务最初预计总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    @ApiModelProperty("任务最初预计总工时")
    private Double totalestimate;
    /**
     * 已验收阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "verifiedstagestorycnt")
    @JsonProperty("verifiedstagestorycnt")
    @ApiModelProperty("已验收阶段需求数")
    private Integer verifiedstagestorycnt;
    /**
     * 安装部署
     */
    @TableField(exist = false)
    @JSONField(name = "install")
    @JsonProperty("install")
    @ApiModelProperty("安装部署")
    private Integer install;
    /**
     * 项目编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("项目编号")
    private Long id;
    /**
     * 设计类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "designtaskcnt")
    @JsonProperty("designtaskcnt")
    @ApiModelProperty("设计类型任务")
    private Integer designtaskcnt;
    /**
     * 安全相关
     */
    @TableField(exist = false)
    @JSONField(name = "security")
    @JsonProperty("security")
    @ApiModelProperty("安全相关")
    private Integer security;
    /**
     * 其他
     */
    @TableField(exist = false)
    @JSONField(name = "others")
    @JsonProperty("others")
    @ApiModelProperty("其他")
    private Integer others;
    /**
     * Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug总数")
    private Integer bugcnt;
    /**
     * 未关闭Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "unclosedbugcnt")
    @JsonProperty("unclosedbugcnt")
    @ApiModelProperty("未关闭Bug总数")
    private Integer unclosedbugcnt;
    /**
     * 昨日完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayctaskcnt")
    @JsonProperty("yesterdayctaskcnt")
    @ApiModelProperty("昨日完成任务数")
    private Integer yesterdayctaskcnt;
    /**
     * 工时
     */
    @TableField(exist = false)
    @JSONField(name = "time")
    @JsonProperty("time")
    @ApiModelProperty("工时")
    private Double time;
    /**
     * 测试脚本
     */
    @TableField(exist = false)
    @JSONField(name = "automation")
    @JsonProperty("automation")
    @ApiModelProperty("测试脚本")
    private Integer automation;
    /**
     * 未确认Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "unconfirmedbugcnt")
    @JsonProperty("unconfirmedbugcnt")
    @ApiModelProperty("未确认Bug总数")
    private Integer unconfirmedbugcnt;
    /**
     * 研发完毕阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "developedstagestorycnt")
    @JsonProperty("developedstagestorycnt")
    @ApiModelProperty("研发完毕阶段需求数")
    private Integer developedstagestorycnt;
    /**
     * 测试中阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "testingstagestorycnt")
    @JsonProperty("testingstagestorycnt")
    @ApiModelProperty("测试中阶段需求数")
    private Integer testingstagestorycnt;
    /**
     * 已结束任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "finishtaskcnt")
    @JsonProperty("finishtaskcnt")
    @ApiModelProperty("已结束任务总数")
    private Integer finishtaskcnt;
    /**
     * 配置相关
     */
    @TableField(exist = false)
    @JSONField(name = "config")
    @JsonProperty("config")
    @ApiModelProperty("配置相关")
    private Integer config;
    /**
     * 任务消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    @ApiModelProperty("任务消耗总工时")
    private Double totalconsumed;
    /**
     * 事务类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "affairtaskcnt")
    @JsonProperty("affairtaskcnt")
    @ApiModelProperty("事务类型任务")
    private Integer affairtaskcnt;
    /**
     * 项目消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "projecttotalconsumed")
    @JsonProperty("projecttotalconsumed")
    @ApiModelProperty("项目消耗总工时")
    private Double projecttotalconsumed;
    /**
     * 界面类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "uitaskcnt")
    @JsonProperty("uitaskcnt")
    @ApiModelProperty("界面类型任务")
    private Integer uitaskcnt;
    /**
     * Bug/完成需求
     */
    @TableField(exist = false)
    @JSONField(name = "bugstory")
    @JsonProperty("bugstory")
    @ApiModelProperty("Bug/完成需求")
    private Integer bugstory;
    /**
     * 已关闭需求
     */
    @TableField(exist = false)
    @JSONField(name = "closedstory")
    @JsonProperty("closedstory")
    @ApiModelProperty("已关闭需求")
    private Integer closedstory;
    /**
     * Bug/完成任务
     */
    @TableField(exist = false)
    @JSONField(name = "bugtask")
    @JsonProperty("bugtask")
    @ApiModelProperty("Bug/完成任务")
    private Integer bugtask;
    /**
     * 已发布需求数
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstorycnt")
    @JsonProperty("releasedstorycnt")
    @ApiModelProperty("已发布需求数")
    private Integer releasedstorycnt;
    /**
     * 研究类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "studytaskcnt")
    @JsonProperty("studytaskcnt")
    @ApiModelProperty("研究类型任务")
    private Integer studytaskcnt;
    /**
     * 已关闭任务数
     */
    @TableField(exist = false)
    @JSONField(name = "closedtaskcnt")
    @JsonProperty("closedtaskcnt")
    @ApiModelProperty("已关闭任务数")
    private Integer closedtaskcnt;
    /**
     * 代码错误
     */
    @TableField(exist = false)
    @JSONField(name = "codeerror")
    @JsonProperty("codeerror")
    @ApiModelProperty("代码错误")
    private Integer codeerror;
    /**
     * 关闭需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    @ApiModelProperty("关闭需求总数")
    private Integer closedstorycnt;
    /**
     * 已解决Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "finishbugcnt")
    @JsonProperty("finishbugcnt")
    @ApiModelProperty("已解决Bug总数")
    private Integer finishbugcnt;
    /**
     * 已变更需求
     */
    @TableField(exist = false)
    @JSONField(name = "changedstory")
    @JsonProperty("changedstory")
    @ApiModelProperty("已变更需求")
    private Integer changedstory;
    /**
     * 开发类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "develtaskcnt")
    @JsonProperty("develtaskcnt")
    @ApiModelProperty("开发类型任务")
    private Integer develtaskcnt;
    /**
     * 人数
     */
    @TableField(exist = false)
    @JSONField(name = "membercnt")
    @JsonProperty("membercnt")
    @ApiModelProperty("人数")
    private Integer membercnt;
    /**
     * 进度
     */
    @TableField(exist = false)
    @JSONField(name = "progress")
    @JsonProperty("progress")
    @ApiModelProperty("进度")
    private String progress;
    /**
     * 昨天解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayrbugcnt")
    @JsonProperty("yesterdayrbugcnt")
    @ApiModelProperty("昨天解决Bug数")
    private Integer yesterdayrbugcnt;



    /**
     * 设置 [截止日期]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [截止日期]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(end);
    }
    /**
     * 设置 [开始时间]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [开始时间]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(begin);
    }
    /**
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [项目名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
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


