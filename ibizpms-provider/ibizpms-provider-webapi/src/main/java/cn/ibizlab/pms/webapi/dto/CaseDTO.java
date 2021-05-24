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
 * 服务DTO对象[CaseDTO]
 */
@Data
@ApiModel("测试用例")
//@JsonFilter(value = "dtofieldfilter")
public class CaseDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [LASTEDITEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "lastediteddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("修改日期")
    private Timestamp lastediteddate;

    /**
     * 属性 [SCRIPTEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "scripteddate" , format="yyyy-MM-dd")
    @JsonProperty("scripteddate")
    @ApiModelProperty("scriptedDate")
    private Timestamp scripteddate;

    /**
     * 属性 [COLOR]
     *
     */
    @JSONField(name = "color")
    @JsonProperty("color")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    @ApiModelProperty("标题颜色")
    private String color;

    /**
     * 属性 [PATH]
     *
     */
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private Integer path;

    /**
     * 属性 [OPENEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "openeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建日期")
    private Timestamp openeddate;

    /**
     * 属性 [LASTRUNRESULT]
     *
     */
    @JSONField(name = "lastrunresult")
    @JsonProperty("lastrunresult")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("结果")
    private String lastrunresult;

    /**
     * 属性 [MODULENAME1]
     *
     */
    @JSONField(name = "modulename1")
    @JsonProperty("modulename1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("模块名称")
    private String modulename1;

    /**
     * 属性 [LINKCASE]
     *
     */
    @JSONField(name = "linkcase")
    @JsonProperty("linkcase")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("相关用例")
    private String linkcase;

    /**
     * 属性 [TASK]
     *
     */
    @JSONField(name = "task")
    @JsonProperty("task")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("属性")
    private String task;

    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;

    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;

    /**
     * 属性 [HOWRUN]
     *
     */
    @JSONField(name = "howrun")
    @JsonProperty("howrun")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("howRun")
    private String howrun;

    /**
     * 属性 [RESULTCNT]
     *
     */
    @JSONField(name = "resultcnt")
    @JsonProperty("resultcnt")
    @ApiModelProperty("测试结果数")
    private Integer resultcnt;

    /**
     * 属性 [NOTICEUSERS]
     *
     */
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;

    /**
     * 属性 [FROMCASEVERSION]
     *
     */
    @JSONField(name = "fromcaseversion")
    @JsonProperty("fromcaseversion")
    @ApiModelProperty("来源用例版本")
    private Integer fromcaseversion;

    /**
     * 属性 [VERSION]
     *
     */
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;

    /**
     * 属性 [SCRIPTEDBY]
     *
     */
    @JSONField(name = "scriptedby")
    @JsonProperty("scriptedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("scriptedBy")
    private String scriptedby;

    /**
     * 属性 [OPENEDBY]
     *
     */
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁创建")
    private String openedby;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("用例类型")
    private String type;

    /**
     * 属性 [RESULTFALICNT]
     *
     */
    @JSONField(name = "resultfalicnt")
    @JsonProperty("resultfalicnt")
    @ApiModelProperty("测试失败数")
    private Integer resultfalicnt;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("用例状态")
    private String status;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [AUTO]
     *
     */
    @JSONField(name = "auto")
    @JsonProperty("auto")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("auto")
    private String auto;

    /**
     * 属性 [ISFAVORITES]
     *
     */
    @JSONField(name = "isfavorites")
    @JsonProperty("isfavorites")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("是否收藏")
    private String isfavorites;

    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [FREQUENCY]
     *
     */
    @JSONField(name = "frequency")
    @JsonProperty("frequency")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("frequency")
    private String frequency;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @NotBlank(message = "[用例标题]不允许为空!")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("用例标题")
    private String title;

    /**
     * 属性 [LASTEDITEDBY]
     *
     */
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("最后修改者")
    private String lasteditedby;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [REVIEWEDBY]
     *
     */
    @JSONField(name = "reviewedby")
    @JsonProperty("reviewedby")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("由谁评审")
    private String reviewedby;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 1000, message = "内容长度必须小于等于[1000]")
    @ApiModelProperty("附件")
    private String files;

    /**
     * 属性 [TOBUGCNT]
     *
     */
    @JSONField(name = "tobugcnt")
    @JsonProperty("tobugcnt")
    @ApiModelProperty("转bug数")
    private Integer tobugcnt;

    /**
     * 属性 [ASSIGNEDTO]
     *
     */
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("指派给")
    private String assignedto;

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
     * 属性 [STORYVERSION]
     *
     */
    @JSONField(name = "storyversion")
    @JsonProperty("storyversion")
    @ApiModelProperty("需求版本")
    private Integer storyversion;

    /**
     * 属性 [REVIEWEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "revieweddate" , format="yyyy-MM-dd")
    @JsonProperty("revieweddate")
    @ApiModelProperty("评审时间")
    private Timestamp revieweddate;

    /**
     * 属性 [PRI]
     *
     */
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;

    /**
     * 属性 [STAGE]
     *
     */
    @JSONField(name = "stage")
    @JsonProperty("stage")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("适用阶段")
    private String stage;

    /**
     * 属性 [SCRIPTLOCATION]
     *
     */
    @JSONField(name = "scriptlocation")
    @JsonProperty("scriptlocation")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("scriptLocation")
    private String scriptlocation;

    /**
     * 属性 [STATUS1]
     *
     */
    @JSONField(name = "status1")
    @JsonProperty("status1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("用例状态")
    private String status1;

    /**
     * 属性 [LASTRUNDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "lastrundate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastrundate")
    @ApiModelProperty("执行时间")
    private Timestamp lastrundate;

    /**
     * 属性 [KEYWORDS]
     *
     */
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("关键词")
    private String keywords;

    /**
     * 属性 [SCRIPTSTATUS]
     *
     */
    @JSONField(name = "scriptstatus")
    @JsonProperty("scriptstatus")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("scriptStatus")
    private String scriptstatus;

    /**
     * 属性 [FRAME]
     *
     */
    @JSONField(name = "frame")
    @JsonProperty("frame")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("工具/框架")
    private String frame;

    /**
     * 属性 [LASTRUNRESULT1]
     *
     */
    @JSONField(name = "lastrunresult1")
    @JsonProperty("lastrunresult1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("测试用例结果")
    private String lastrunresult1;

    /**
     * 属性 [STEPCNT]
     *
     */
    @JSONField(name = "stepcnt")
    @JsonProperty("stepcnt")
    @ApiModelProperty("用例步骤数")
    private Integer stepcnt;

    /**
     * 属性 [SUBSTATUS]
     *
     */
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("子状态")
    private String substatus;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用例编号")
    private Long id;

    /**
     * 属性 [PRECONDITION]
     *
     */
    @JSONField(name = "precondition")
    @JsonProperty("precondition")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("前置条件")
    private String precondition;

    /**
     * 属性 [LASTRUNNER]
     *
     */
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("执行人")
    private String lastrunner;

    /**
     * 属性 [LIBNAME]
     *
     */
    @JSONField(name = "libname")
    @JsonProperty("libname")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("用例库")
    private String libname;

    /**
     * 属性 [STORYNAME]
     *
     */
    @JSONField(name = "storyname")
    @JsonProperty("storyname")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("需求名称")
    private String storyname;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("模块名称")
    private String modulename;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品名称")
    private String productname;

    /**
     * 属性 [FROMCASEID]
     *
     */
    @JSONField(name = "fromcaseid")
    @JsonProperty("fromcaseid")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("来源用例")
    private Long fromcaseid;

    /**
     * 属性 [BRANCH]
     *
     */
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("平台/分支")
    private Long branch;

    /**
     * 属性 [FROMBUG]
     *
     */
    @JSONField(name = "frombug")
    @JsonProperty("frombug")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("来源Bug")
    private Long frombug;

    /**
     * 属性 [STORY]
     *
     */
    @JSONField(name = "story")
    @JsonProperty("story")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("相关需求")
    private Long story;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属产品")
    private Long product;

    /**
     * 属性 [LIB]
     *
     */
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属库")
    private Long lib;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属模块")
    private Long module;

    /**
     * 属性 [CASESN]
     *
     */
    @JSONField(name = "casesn")
    @JsonProperty("casesn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("测试用例编号")
    private Long casesn;


    /**
     * 设置 [SCRIPTEDDATE]
     */
    public void setScripteddate(Timestamp  scripteddate){
        this.scripteddate = scripteddate ;
        this.modify("scripteddate",scripteddate);
    }

    /**
     * 设置 [COLOR]
     */
    public void setColor(String  color){
        this.color = color ;
        this.modify("color",color);
    }

    /**
     * 设置 [PATH]
     */
    public void setPath(Integer  path){
        this.path = path ;
        this.modify("path",path);
    }

    /**
     * 设置 [LASTRUNRESULT]
     */
    public void setLastrunresult(String  lastrunresult){
        this.lastrunresult = lastrunresult ;
        this.modify("lastrunresult",lastrunresult);
    }

    /**
     * 设置 [LINKCASE]
     */
    public void setLinkcase(String  linkcase){
        this.linkcase = linkcase ;
        this.modify("linkcase",linkcase);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(Integer  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [HOWRUN]
     */
    public void setHowrun(String  howrun){
        this.howrun = howrun ;
        this.modify("howrun",howrun);
    }

    /**
     * 设置 [FROMCASEVERSION]
     */
    public void setFromcaseversion(Integer  fromcaseversion){
        this.fromcaseversion = fromcaseversion ;
        this.modify("fromcaseversion",fromcaseversion);
    }

    /**
     * 设置 [VERSION]
     */
    public void setVersion(Integer  version){
        this.version = version ;
        this.modify("version",version);
    }

    /**
     * 设置 [SCRIPTEDBY]
     */
    public void setScriptedby(String  scriptedby){
        this.scriptedby = scriptedby ;
        this.modify("scriptedby",scriptedby);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [AUTO]
     */
    public void setAuto(String  auto){
        this.auto = auto ;
        this.modify("auto",auto);
    }

    /**
     * 设置 [FREQUENCY]
     */
    public void setFrequency(String  frequency){
        this.frequency = frequency ;
        this.modify("frequency",frequency);
    }

    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [REVIEWEDBY]
     */
    public void setReviewedby(String  reviewedby){
        this.reviewedby = reviewedby ;
        this.modify("reviewedby",reviewedby);
    }

    /**
     * 设置 [STORYVERSION]
     */
    public void setStoryversion(Integer  storyversion){
        this.storyversion = storyversion ;
        this.modify("storyversion",storyversion);
    }

    /**
     * 设置 [REVIEWEDDATE]
     */
    public void setRevieweddate(Timestamp  revieweddate){
        this.revieweddate = revieweddate ;
        this.modify("revieweddate",revieweddate);
    }

    /**
     * 设置 [PRI]
     */
    public void setPri(Integer  pri){
        this.pri = pri ;
        this.modify("pri",pri);
    }

    /**
     * 设置 [STAGE]
     */
    public void setStage(String  stage){
        this.stage = stage ;
        this.modify("stage",stage);
    }

    /**
     * 设置 [SCRIPTLOCATION]
     */
    public void setScriptlocation(String  scriptlocation){
        this.scriptlocation = scriptlocation ;
        this.modify("scriptlocation",scriptlocation);
    }

    /**
     * 设置 [LASTRUNDATE]
     */
    public void setLastrundate(Timestamp  lastrundate){
        this.lastrundate = lastrundate ;
        this.modify("lastrundate",lastrundate);
    }

    /**
     * 设置 [KEYWORDS]
     */
    public void setKeywords(String  keywords){
        this.keywords = keywords ;
        this.modify("keywords",keywords);
    }

    /**
     * 设置 [SCRIPTSTATUS]
     */
    public void setScriptstatus(String  scriptstatus){
        this.scriptstatus = scriptstatus ;
        this.modify("scriptstatus",scriptstatus);
    }

    /**
     * 设置 [FRAME]
     */
    public void setFrame(String  frame){
        this.frame = frame ;
        this.modify("frame",frame);
    }

    /**
     * 设置 [SUBSTATUS]
     */
    public void setSubstatus(String  substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [PRECONDITION]
     */
    public void setPrecondition(String  precondition){
        this.precondition = precondition ;
        this.modify("precondition",precondition);
    }

    /**
     * 设置 [LASTRUNNER]
     */
    public void setLastrunner(String  lastrunner){
        this.lastrunner = lastrunner ;
        this.modify("lastrunner",lastrunner);
    }

    /**
     * 设置 [FROMCASEID]
     */
    public void setFromcaseid(Long  fromcaseid){
        this.fromcaseid = fromcaseid ;
        this.modify("fromcaseid",fromcaseid);
    }

    /**
     * 设置 [BRANCH]
     */
    public void setBranch(Long  branch){
        this.branch = branch ;
        this.modify("branch",branch);
    }

    /**
     * 设置 [FROMBUG]
     */
    public void setFrombug(Long  frombug){
        this.frombug = frombug ;
        this.modify("frombug",frombug);
    }

    /**
     * 设置 [STORY]
     */
    public void setStory(Long  story){
        this.story = story ;
        this.modify("story",story);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [LIB]
     */
    public void setLib(Long  lib){
        this.lib = lib ;
        this.modify("lib",lib);
    }

    /**
     * 设置 [MODULE]
     */
    public void setModule(Long  module){
        this.module = module ;
        this.modify("module",module);
    }

    /**
     * 设置 [CASESN]
     */
    public void setCasesn(Long  casesn){
        this.casesn = casesn ;
        this.modify("casesn",casesn);
    }


    /**
     *  [IBZ_CASESTEP]
     */
    @JsonProperty("ibzcasesteps")
    @JSONField(name = "ibzcasesteps")
	private List<IBZCaseStepDTO> casesteps ;

}


