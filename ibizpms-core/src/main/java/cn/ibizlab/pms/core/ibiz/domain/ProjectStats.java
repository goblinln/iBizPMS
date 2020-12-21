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
 * 实体[项目统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_project", resultMap = "ProjectStatsResultMap")
public class ProjectStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    private Integer storycnt;
    /**
     * 任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    private Integer taskcnt;
    /**
     * 任务最初预计总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    private Double totalestimate;
    /**
     * 任务消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    private Double totalconsumed;
    /**
     * 任务预计剩余总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    private Double totalleft;
    /**
     * 未完成任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "undonetaskcnt")
    @JsonProperty("undonetaskcnt")
    private Integer undonetaskcnt;
    /**
     * 关闭需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    private Integer closedstorycnt;
    /**
     * Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    private Integer bugcnt;
    /**
     * 未解决Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    private Integer activebugcnt;
    /**
     * 未关闭需求总数
     */
    @TableField(exist = false)
    @JSONField(name = "unclosedstorycnt")
    @JsonProperty("unclosedstorycnt")
    private Integer unclosedstorycnt;
    /**
     * 已结束任务总数
     */
    @TableField(exist = false)
    @JSONField(name = "finishtaskcnt")
    @JsonProperty("finishtaskcnt")
    private Integer finishtaskcnt;
    /**
     * 已解决Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "finishbugcnt")
    @JsonProperty("finishbugcnt")
    private Integer finishbugcnt;
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
     * 工时
     */
    @TableField(exist = false)
    @JSONField(name = "time")
    @JsonProperty("time")
    private Double time;
    /**
     * 工时类型
     */
    @TableField(exist = false)
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * 项目名称
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 未确认Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "unconfirmedbugcnt")
    @JsonProperty("unconfirmedbugcnt")
    private Integer unconfirmedbugcnt;
    /**
     * 未关闭Bug总数
     */
    @TableField(exist = false)
    @JSONField(name = "unclosedbugcnt")
    @JsonProperty("unclosedbugcnt")
    private Integer unclosedbugcnt;
    /**
     * 总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalwh")
    @JsonProperty("totalwh")
    private Integer totalwh;
    /**
     * 已发布需求数
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstorycnt")
    @JsonProperty("releasedstorycnt")
    private Integer releasedstorycnt;
    /**
     * 昨日完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayctaskcnt")
    @JsonProperty("yesterdayctaskcnt")
    private Integer yesterdayctaskcnt;
    /**
     * 昨天解决Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "yesterdayrbugcnt")
    @JsonProperty("yesterdayrbugcnt")
    private Integer yesterdayrbugcnt;
    /**
     * 截止日期
     */
    @TableField(value = "`end`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    private Timestamp end;
    /**
     * 状态
     */
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 项目排序
     */
    @TableField(exist = false)
    @JSONField(name = "order1")
    @JsonProperty("order1")
    private Integer order1;
    /**
     * 是否置顶
     */
    @TableField(exist = false)
    @JSONField(name = "istop")
    @JsonProperty("istop")
    private Integer istop;
    /**
     * 已关闭任务数
     */
    @TableField(exist = false)
    @JSONField(name = "closedtaskcnt")
    @JsonProperty("closedtaskcnt")
    private Integer closedtaskcnt;
    /**
     * 已取消任务数
     */
    @TableField(exist = false)
    @JSONField(name = "canceltaskcnt")
    @JsonProperty("canceltaskcnt")
    private Integer canceltaskcnt;
    /**
     * 已暂停任务数
     */
    @TableField(exist = false)
    @JSONField(name = "pausetaskcnt")
    @JsonProperty("pausetaskcnt")
    private Integer pausetaskcnt;
    /**
     * 未开始任务数
     */
    @TableField(exist = false)
    @JSONField(name = "waittaskcnt")
    @JsonProperty("waittaskcnt")
    private Integer waittaskcnt;
    /**
     * 进行中任务数
     */
    @TableField(exist = false)
    @JSONField(name = "doingtaskcnt")
    @JsonProperty("doingtaskcnt")
    private Integer doingtaskcnt;
    /**
     * 已完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "donetaskcnt")
    @JsonProperty("donetaskcnt")
    private Integer donetaskcnt;
    /**
     * 设计类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "designtaskcnt")
    @JsonProperty("designtaskcnt")
    private Integer designtaskcnt;
    /**
     * 讨论类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "discusstaskcnt")
    @JsonProperty("discusstaskcnt")
    private Integer discusstaskcnt;
    /**
     * 研究类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "studytaskcnt")
    @JsonProperty("studytaskcnt")
    private Integer studytaskcnt;
    /**
     * 界面类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "uitaskcnt")
    @JsonProperty("uitaskcnt")
    private Integer uitaskcnt;
    /**
     * 测试类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "testtaskcnt")
    @JsonProperty("testtaskcnt")
    private Integer testtaskcnt;
    /**
     * 服务类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "servetaskcnt")
    @JsonProperty("servetaskcnt")
    private Integer servetaskcnt;
    /**
     * 开发类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "develtaskcnt")
    @JsonProperty("develtaskcnt")
    private Integer develtaskcnt;
    /**
     * 其他类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "misctaskcnt")
    @JsonProperty("misctaskcnt")
    private Integer misctaskcnt;
    /**
     * 事务类型任务
     */
    @TableField(exist = false)
    @JSONField(name = "affairtaskcnt")
    @JsonProperty("affairtaskcnt")
    private Integer affairtaskcnt;
    /**
     * 完成需求数
     */
    @TableField(exist = false)
    @JSONField(name = "completestorycnt")
    @JsonProperty("completestorycnt")
    private Integer completestorycnt;
    /**
     * 完成任务数
     */
    @TableField(exist = false)
    @JSONField(name = "completetaskcnt")
    @JsonProperty("completetaskcnt")
    private Integer completetaskcnt;
    /**
     * Bug/完成需求
     */
    @TableField(exist = false)
    @JSONField(name = "bugstory")
    @JsonProperty("bugstory")
    private Integer bugstory;
    /**
     * Bug/完成任务
     */
    @TableField(exist = false)
    @JSONField(name = "bugtask")
    @JsonProperty("bugtask")
    private Integer bugtask;
    /**
     * 重要Bug数
     */
    @TableField(exist = false)
    @JSONField(name = "importantbugcnt")
    @JsonProperty("importantbugcnt")
    private Integer importantbugcnt;
    /**
     * 严重Bug比率
     */
    @TableField(exist = false)
    @JSONField(name = "seriousbugproportion")
    @JsonProperty("seriousbugproportion")
    private String seriousbugproportion;
    /**
     * 代码错误
     */
    @TableField(exist = false)
    @JSONField(name = "codeerror")
    @JsonProperty("codeerror")
    private Integer codeerror;
    /**
     * 配置相关
     */
    @TableField(exist = false)
    @JSONField(name = "config")
    @JsonProperty("config")
    private Integer config;
    /**
     * 安装部署
     */
    @TableField(exist = false)
    @JSONField(name = "install")
    @JsonProperty("install")
    private Integer install;
    /**
     * 安全相关
     */
    @TableField(exist = false)
    @JSONField(name = "security")
    @JsonProperty("security")
    private Integer security;
    /**
     * 性能问题
     */
    @TableField(exist = false)
    @JSONField(name = "performance")
    @JsonProperty("performance")
    private Integer performance;
    /**
     * 标准规范
     */
    @TableField(exist = false)
    @JSONField(name = "standard")
    @JsonProperty("standard")
    private Integer standard;
    /**
     * 测试脚本
     */
    @TableField(exist = false)
    @JSONField(name = "automation")
    @JsonProperty("automation")
    private Integer automation;
    /**
     * 人数
     */
    @TableField(exist = false)
    @JSONField(name = "membercnt")
    @JsonProperty("membercnt")
    private Integer membercnt;
    /**
     * 设计缺陷
     */
    @TableField(exist = false)
    @JSONField(name = "designdefect")
    @JsonProperty("designdefect")
    private Integer designdefect;
    /**
     * 其他
     */
    @TableField(exist = false)
    @JSONField(name = "others")
    @JsonProperty("others")
    private Integer others;
    /**
     * 项目消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "projecttotalconsumed")
    @JsonProperty("projecttotalconsumed")
    private Double projecttotalconsumed;
    /**
     * 工期
     */
    @TableField(exist = false)
    @JSONField(name = "timescale")
    @JsonProperty("timescale")
    private String timescale;
    /**
     * 剩余需求数
     */
    @TableField(exist = false)
    @JSONField(name = "leftstorycnt")
    @JsonProperty("leftstorycnt")
    private Integer leftstorycnt;
    /**
     * 空需求
     */
    @TableField(exist = false)
    @JSONField(name = "emptystory")
    @JsonProperty("emptystory")
    private Integer emptystory;
    /**
     * 草稿需求
     */
    @TableField(exist = false)
    @JSONField(name = "draftstory")
    @JsonProperty("draftstory")
    private Integer draftstory;
    /**
     * 激活需求
     */
    @TableField(exist = false)
    @JSONField(name = "activestory")
    @JsonProperty("activestory")
    private Integer activestory;
    /**
     * 已关闭需求
     */
    @TableField(exist = false)
    @JSONField(name = "closedstory")
    @JsonProperty("closedstory")
    private Integer closedstory;
    /**
     * 已变更需求
     */
    @TableField(exist = false)
    @JSONField(name = "changedstory")
    @JsonProperty("changedstory")
    private Integer changedstory;
    /**
     * 空阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "emptystagestorycnt")
    @JsonProperty("emptystagestorycnt")
    private Integer emptystagestorycnt;
    /**
     * 未开始阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "waitstagestorycnt")
    @JsonProperty("waitstagestorycnt")
    private Integer waitstagestorycnt;
    /**
     * 已计划阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "plannedstagestorycnt")
    @JsonProperty("plannedstagestorycnt")
    private Integer plannedstagestorycnt;
    /**
     * 已立项阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "projectedstagestorycnt")
    @JsonProperty("projectedstagestorycnt")
    private Integer projectedstagestorycnt;
    /**
     * 研发中阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "developingstagestorycnt")
    @JsonProperty("developingstagestorycnt")
    private Integer developingstagestorycnt;
    /**
     * 研发完毕阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "developedstagestorycnt")
    @JsonProperty("developedstagestorycnt")
    private Integer developedstagestorycnt;
    /**
     * 测试中阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "testingstagestorycnt")
    @JsonProperty("testingstagestorycnt")
    private Integer testingstagestorycnt;
    /**
     * 测试完毕阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "testedstagestorycnt")
    @JsonProperty("testedstagestorycnt")
    private Integer testedstagestorycnt;
    /**
     * 已验收阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "verifiedstagestorycnt")
    @JsonProperty("verifiedstagestorycnt")
    private Integer verifiedstagestorycnt;
    /**
     * 已发布阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "releasedstagestorycnt")
    @JsonProperty("releasedstagestorycnt")
    private Integer releasedstagestorycnt;
    /**
     * 已关闭阶段需求数
     */
    @TableField(exist = false)
    @JSONField(name = "closedstagestorycnt")
    @JsonProperty("closedstagestorycnt")
    private Integer closedstagestorycnt;



    /**
     * 设置 [项目名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

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
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
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


