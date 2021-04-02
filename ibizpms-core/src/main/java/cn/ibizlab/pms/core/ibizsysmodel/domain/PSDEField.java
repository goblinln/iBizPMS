package cn.ibizlab.pms.core.ibizsysmodel.domain;

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

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [实体属性] 对象
 */
@Data
@ApiModel("实体属性")
public class PSDEField extends EntityClient implements Serializable {
    @Override
    public void modify(String field, Object val) {
        getExtensionparams().put("dirtyflagenable", true);
        super.modify(field, val);
    }

    /**
     * 自定义参数
     */
    @JSONField(name = "userparams")
    @JsonProperty("userparams")
    @ApiModelProperty("自定义参数")
    private String userparams;

    /**
     * 支持链接属性回写
     */
    @JSONField(name = "enawriteback")
    @JsonProperty("enawriteback")
    @ApiModelProperty("支持链接属性回写")
    private Integer enawriteback;

    /**
     * 视图列级别
     */
    @JSONField(name = "viewcollevel")
    @JsonProperty("viewcollevel")
    @ApiModelProperty("视图列级别")
    private Integer viewcollevel;

    /**
     * 服务代码名称
     */
    @JSONField(name = "servicecodename")
    @JsonProperty("servicecodename")
    @ApiModelProperty("服务代码名称")
    private String servicecodename;

    /**
     * 重复值检查
     */
    @JSONField(name = "dupcheckmode")
    @JsonProperty("dupcheckmode")
    @ApiModelProperty("重复值检查")
    private String dupcheckmode;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 更新旧值回填
     */
    @JSONField(name = "updateovmode")
    @JsonProperty("updateovmode")
    @ApiModelProperty("更新旧值回填")
    private String updateovmode;

    /**
     * 单位宽度
     */
    @JSONField(name = "unitwidth")
    @JsonProperty("unitwidth")
    @ApiModelProperty("单位宽度")
    private Integer unitwidth;

    /**
     * 数据库更新值模式
     */
    @JSONField(name = "dbvaluemode")
    @JsonProperty("dbvaluemode")
    @ApiModelProperty("数据库更新值模式")
    private String dbvaluemode;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 默认测试值
     */
    @JSONField(name = "testdata")
    @JsonProperty("testdata")
    @ApiModelProperty("默认测试值")
    private String testdata;

    /**
     * 主状态属性
     */
    @JSONField(name = "statefield")
    @JsonProperty("statefield")
    @ApiModelProperty("主状态属性")
    private String statefield;

    /**
     * 逻辑属性参数
     */
    @JSONField(name = "formulafields")
    @JsonProperty("formulafields")
    @ApiModelProperty("逻辑属性参数")
    private String formulafields;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @ApiModelProperty("系统")
    private String pssystemname;

    /**
     * 单位
     */
    @JSONField(name = "unit")
    @JsonProperty("unit")
    @ApiModelProperty("单位")
    private String unit;

    /**
     * 多表单识别属性
     */
    @JSONField(name = "multiformfield")
    @JsonProperty("multiformfield")
    @ApiModelProperty("多表单识别属性")
    private Integer multiformfield;

    /**
     * 索引类型属性
     */
    @JSONField(name = "indextype")
    @JsonProperty("indextype")
    @ApiModelProperty("索引类型属性")
    private Integer indextype;

    /**
     * 检查值范围
     */
    @JSONField(name = "dupcheckvalues")
    @JsonProperty("dupcheckvalues")
    @ApiModelProperty("检查值范围")
    private String dupcheckvalues;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 默认用户行为
     */
    @DEField(defaultValue = "3")
    @JSONField(name = "enableuserinput")
    @JsonProperty("enableuserinput")
    @ApiModelProperty("默认用户行为")
    private Integer enableuserinput;

    /**
     * 默认值类型
     */
    @DEField(name = "dvt")
    @JSONField(name = "defaultvaluetype")
    @JsonProperty("defaultvaluetype")
    @ApiModelProperty("默认值类型")
    private String defaultvaluetype;

    /**
     * 检查递归
     */
    @JSONField(name = "checkrecursion")
    @JsonProperty("checkrecursion")
    @ApiModelProperty("检查递归")
    private Integer checkrecursion;

    /**
     * 查询扩展选项
     */
    @JSONField(name = "querycs")
    @JsonProperty("querycs")
    @ApiModelProperty("查询扩展选项")
    private String querycs;

    /**
     * 扩展模式
     */
    @JSONField(name = "extendmode")
    @JsonProperty("extendmode")
    @ApiModelProperty("扩展模式")
    private Integer extendmode;

    /**
     * 启用审计
     */
    @JSONField(name = "enableaudit")
    @JsonProperty("enableaudit")
    @ApiModelProperty("启用审计")
    private Integer enableaudit;

    /**
     * 值格式化
     */
    @JSONField(name = "valueformat")
    @JsonProperty("valueformat")
    @ApiModelProperty("值格式化")
    private String valueformat;

    /**
     * 排序值
     */
    @DEField(preType = DEPredefinedFieldType.ORDERVALUE)
    @JSONField(name = "ordervalue")
    @JsonProperty("ordervalue")
    @ApiModelProperty("排序值")
    private Integer ordervalue;

    /**
     * 长度
     */
    @JSONField(name = "length")
    @JsonProperty("length")
    @ApiModelProperty("长度")
    private Integer length;

    /**
     * 数据导入标识
     */
    @JSONField(name = "importtag")
    @JsonProperty("importtag")
    @ApiModelProperty("数据导入标识")
    private String importtag;

    /**
     * 数据导入识别
     */
    @JSONField(name = "importkey")
    @JsonProperty("importkey")
    @ApiModelProperty("数据导入识别")
    private Integer importkey;

    /**
     * 业务标记
     */
    @JSONField(name = "biztag")
    @JsonProperty("biztag")
    @ApiModelProperty("业务标记")
    private String biztag;

    /**
     * 是否启用
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    @ApiModelProperty("是否启用")
    private Integer validflag;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 数据类型
     */
    @JSONField(name = "psdatatypename")
    @JsonProperty("psdatatypename")
    @ApiModelProperty("数据类型")
    private String psdatatypename;

    /**
     * 表名称
     */
    @JSONField(name = "tablename")
    @JsonProperty("tablename")
    @ApiModelProperty("表名称")
    private String tablename;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 主信息属性
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "majorfield")
    @JsonProperty("majorfield")
    @ApiModelProperty("主信息属性")
    private Integer majorfield;

    /**
     * 实体属性标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "psdefieldid")
    @JsonProperty("psdefieldid")
    @ApiModelProperty("实体属性标识")
    private String psdefieldid;

    /**
     * 数据导入次序
     */
    @JSONField(name = "importorder")
    @JsonProperty("importorder")
    @ApiModelProperty("数据导入次序")
    private Integer importorder;

    /**
     * 联合键值
     */
    @JSONField(name = "unionkeyvalue")
    @JsonProperty("unionkeyvalue")
    @ApiModelProperty("联合键值")
    private String unionkeyvalue;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 实体属性名称
     */
    @JSONField(name = "psdefieldname")
    @JsonProperty("psdefieldname")
    @ApiModelProperty("实体属性名称")
    private String psdefieldname;

    /**
     * 双字节字符
     */
    @JSONField(name = "unicodechar")
    @JsonProperty("unicodechar")
    @ApiModelProperty("双字节字符")
    private Integer unicodechar;

    /**
     * 物理化属性
     */
    @JSONField(name = "physicalfield")
    @JsonProperty("physicalfield")
    @ApiModelProperty("物理化属性")
    private Integer physicalfield;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    @ApiModelProperty("代码名称")
    private String codename;

    /**
     * 字符长度
     */
    @JSONField(name = "strlength")
    @JsonProperty("strlength")
    @ApiModelProperty("字符长度")
    private Integer strlength;

    /**
     * 属性类型
     */
    @JSONField(name = "deftype")
    @JsonProperty("deftype")
    @ApiModelProperty("属性类型")
    private Integer deftype;

    /**
     * 属性默认值
     */
    @JSONField(name = "defaultvalue")
    @JsonProperty("defaultvalue")
    @ApiModelProperty("属性默认值")
    private String defaultvalue;

    /**
     * 空值排序
     */
    @JSONField(name = "nullvalorder")
    @JsonProperty("nullvalorder")
    @ApiModelProperty("空值排序")
    private String nullvalorder;

    /**
     * 外键属性
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "fkey")
    @JsonProperty("fkey")
    @ApiModelProperty("外键属性")
    private Integer fkey;

    /**
     * 逻辑字段格式
     */
    @JSONField(name = "formulaformat")
    @JsonProperty("formulaformat")
    @ApiModelProperty("逻辑字段格式")
    private String formulaformat;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 主键属性
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "pkey")
    @JsonProperty("pkey")
    @ApiModelProperty("主键属性")
    private Integer pkey;

    /**
     * 浮点精度
     */
    @JSONField(name = "precision2")
    @JsonProperty("precision2")
    @ApiModelProperty("浮点精度")
    private Integer precision2;

    /**
     * 系统预置属性
     */
    @JSONField(name = "predefinetype")
    @JsonProperty("predefinetype")
    @ApiModelProperty("系统预置属性")
    private String predefinetype;

    /**
     * 查询输出
     */
    @JSONField(name = "querycolumn")
    @JsonProperty("querycolumn")
    @ApiModelProperty("查询输出")
    private Integer querycolumn;

    /**
     * 支持临时数据
     */
    @JSONField(name = "enabletempdata")
    @JsonProperty("enabletempdata")
    @ApiModelProperty("支持临时数据")
    private Integer enabletempdata;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 粘帖重置
     */
    @JSONField(name = "pastereset")
    @JsonProperty("pastereset")
    @ApiModelProperty("粘帖重置")
    private Integer pastereset;

    /**
     * 模型锁模式
     */
    @JSONField(name = "lockflag")
    @JsonProperty("lockflag")
    @ApiModelProperty("模型锁模式")
    private Integer lockflag;

    /**
     * 字符转换
     */
    @JSONField(name = "stringcase")
    @JsonProperty("stringcase")
    @ApiModelProperty("字符转换")
    private String stringcase;

    /**
     * 启用快速搜索
     */
    @JSONField(name = "enableqs")
    @JsonProperty("enableqs")
    @ApiModelProperty("启用快速搜索")
    private Integer enableqs;

    /**
     * 启用列权限控制
     */
    @JSONField(name = "enablecolpriv")
    @JsonProperty("enablecolpriv")
    @ApiModelProperty("启用列权限控制")
    private Integer enablecolpriv;

    /**
     * 中文名称
     */
    @JSONField(name = "logicname")
    @JsonProperty("logicname")
    @ApiModelProperty("中文名称")
    private String logicname;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @ApiModelProperty("系统")
    private String pssystemid;

    /**
     * 允许为空
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "allowempty")
    @JsonProperty("allowempty")
    @ApiModelProperty("允许为空")
    private Integer allowempty;

    /**
     * 自定义导出范围
     */
    @JSONField(name = "customexportscope")
    @JsonProperty("customexportscope")
    @ApiModelProperty("自定义导出范围")
    private Integer customexportscope;

    /**
     * 动态模型类型
     */
    @JSONField(name = "dynamodelflag")
    @JsonProperty("dynamodelflag")
    @ApiModelProperty("动态模型类型")
    private Integer dynamodelflag;

    /**
     * 导出范围
     */
    @JSONField(name = "exportscope")
    @JsonProperty("exportscope")
    @ApiModelProperty("导出范围")
    private Integer exportscope;

    /**
     * 审计信息格式
     */
    @JSONField(name = "auditinfoformat")
    @JsonProperty("auditinfoformat")
    @ApiModelProperty("审计信息格式")
    private String auditinfoformat;

    /**
     * 数据库新建值模式
     */
    @JSONField(name = "dbvaluemode2")
    @JsonProperty("dbvaluemode2")
    @ApiModelProperty("数据库新建值模式")
    private String dbvaluemode2;

    /**
     * 数据类型
     */
    @JSONField(name = "psdatatypeid")
    @JsonProperty("psdatatypeid")
    @ApiModelProperty("数据类型")
    private String psdatatypeid;

    /**
     * 值项属性
     */
    @JSONField(name = "valuepsdefname")
    @JsonProperty("valuepsdefname")
    @ApiModelProperty("值项属性")
    private String valuepsdefname;

    /**
     * 范围属性
     */
    @DEField(name = "dupchkpsdefname")
    @JSONField(name = "dupcheckpsdefname")
    @JsonProperty("dupcheckpsdefname")
    @ApiModelProperty("范围属性")
    private String dupcheckpsdefname;

    /**
     * 范围属性2
     */
    @JSONField(name = "no2dupchkpsdefname")
    @JsonProperty("no2dupchkpsdefname")
    @ApiModelProperty("范围属性2")
    private String no2dupchkpsdefname;

    /**
     * 关系属性
     */
    @JSONField(name = "derpsdefname")
    @JsonProperty("derpsdefname")
    @ApiModelProperty("关系属性")
    private String derpsdefname;

    /**
     * 范围属性3
     */
    @JSONField(name = "no3dupchkpsdefname")
    @JsonProperty("no3dupchkpsdefname")
    @ApiModelProperty("范围属性3")
    private String no3dupchkpsdefname;

    /**
     * 实体
     */
    @JSONField(name = "psdename")
    @JsonProperty("psdename")
    @ApiModelProperty("实体")
    private String psdename;

    /**
     * PSSUBSYSSADEID
     */
    @JSONField(name = "pssubsyssadeid")
    @JsonProperty("pssubsyssadeid")
    @ApiModelProperty("PSSUBSYSSADEID")
    private String pssubsyssadeid;

    /**
     * 实体
     */
    @JSONField(name = "psdeid")
    @JsonProperty("psdeid")
    @ApiModelProperty("实体")
    private String psdeid;

    /**
     * 范围属性2
     */
    @JSONField(name = "no2dupchkpsdefid")
    @JsonProperty("no2dupchkpsdefid")
    @ApiModelProperty("范围属性2")
    private String no2dupchkpsdefid;

    /**
     * 范围属性
     */
    @DEField(name = "dupchkpsdefid")
    @JSONField(name = "dupcheckpsdefid")
    @JsonProperty("dupcheckpsdefid")
    @ApiModelProperty("范围属性")
    private String dupcheckpsdefid;

    /**
     * 值项属性
     */
    @JSONField(name = "valuepsdefid")
    @JsonProperty("valuepsdefid")
    @ApiModelProperty("值项属性")
    private String valuepsdefid;

    /**
     * 关系属性
     */
    @JSONField(name = "derpsdefid")
    @JsonProperty("derpsdefid")
    @ApiModelProperty("关系属性")
    private String derpsdefid;

    /**
     * 范围属性3
     */
    @JSONField(name = "no3dupchkpsdefid")
    @JsonProperty("no3dupchkpsdefid")
    @ApiModelProperty("范围属性3")
    private String no3dupchkpsdefid;


    /**
     * 
     */
    @JSONField(name = "psde")
    @JsonProperty("psde")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSDataEntity psde;

    /**
     * 
     */
    @JSONField(name = "derpsdef")
    @JsonProperty("derpsdef")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField derpsdef;

    /**
     * 
     */
    @JSONField(name = "dupchkpsdef")
    @JsonProperty("dupchkpsdef")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField dupchkpsdef;

    /**
     * 
     */
    @JSONField(name = "no2dupchkpsdef")
    @JsonProperty("no2dupchkpsdef")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField no2dupchkpsdef;

    /**
     * 
     */
    @JSONField(name = "no3dupchkpsdef")
    @JsonProperty("no3dupchkpsdef")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField no3dupchkpsdef;

    /**
     * 
     */
    @JSONField(name = "valuepsdef")
    @JsonProperty("valuepsdef")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField valuepsdef;




    /**
     * 设置 [自定义参数]
     */
    public void setUserparams(String userparams) {
        this.userparams = userparams ;
        this.modify("userparams",userparams);
    }

    /**
     * 设置 [支持链接属性回写]
     */
    public void setEnawriteback(Integer enawriteback) {
        this.enawriteback = enawriteback ;
        this.modify("enawriteback",enawriteback);
    }

    /**
     * 设置 [视图列级别]
     */
    public void setViewcollevel(Integer viewcollevel) {
        this.viewcollevel = viewcollevel ;
        this.modify("viewcollevel",viewcollevel);
    }

    /**
     * 设置 [服务代码名称]
     */
    public void setServicecodename(String servicecodename) {
        this.servicecodename = servicecodename ;
        this.modify("servicecodename",servicecodename);
    }

    /**
     * 设置 [重复值检查]
     */
    public void setDupcheckmode(String dupcheckmode) {
        this.dupcheckmode = dupcheckmode ;
        this.modify("dupcheckmode",dupcheckmode);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [更新旧值回填]
     */
    public void setUpdateovmode(String updateovmode) {
        this.updateovmode = updateovmode ;
        this.modify("updateovmode",updateovmode);
    }

    /**
     * 设置 [单位宽度]
     */
    public void setUnitwidth(Integer unitwidth) {
        this.unitwidth = unitwidth ;
        this.modify("unitwidth",unitwidth);
    }

    /**
     * 设置 [数据库更新值模式]
     */
    public void setDbvaluemode(String dbvaluemode) {
        this.dbvaluemode = dbvaluemode ;
        this.modify("dbvaluemode",dbvaluemode);
    }

    /**
     * 设置 [默认测试值]
     */
    public void setTestdata(String testdata) {
        this.testdata = testdata ;
        this.modify("testdata",testdata);
    }

    /**
     * 设置 [主状态属性]
     */
    public void setStatefield(String statefield) {
        this.statefield = statefield ;
        this.modify("statefield",statefield);
    }

    /**
     * 设置 [逻辑属性参数]
     */
    public void setFormulafields(String formulafields) {
        this.formulafields = formulafields ;
        this.modify("formulafields",formulafields);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [单位]
     */
    public void setUnit(String unit) {
        this.unit = unit ;
        this.modify("unit",unit);
    }

    /**
     * 设置 [多表单识别属性]
     */
    public void setMultiformfield(Integer multiformfield) {
        this.multiformfield = multiformfield ;
        this.modify("multiformfield",multiformfield);
    }

    /**
     * 设置 [索引类型属性]
     */
    public void setIndextype(Integer indextype) {
        this.indextype = indextype ;
        this.modify("indextype",indextype);
    }

    /**
     * 设置 [检查值范围]
     */
    public void setDupcheckvalues(String dupcheckvalues) {
        this.dupcheckvalues = dupcheckvalues ;
        this.modify("dupcheckvalues",dupcheckvalues);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [默认用户行为]
     */
    public void setEnableuserinput(Integer enableuserinput) {
        this.enableuserinput = enableuserinput ;
        this.modify("enableuserinput",enableuserinput);
    }

    /**
     * 设置 [默认值类型]
     */
    public void setDefaultvaluetype(String defaultvaluetype) {
        this.defaultvaluetype = defaultvaluetype ;
        this.modify("dvt",defaultvaluetype);
    }

    /**
     * 设置 [检查递归]
     */
    public void setCheckrecursion(Integer checkrecursion) {
        this.checkrecursion = checkrecursion ;
        this.modify("checkrecursion",checkrecursion);
    }

    /**
     * 设置 [查询扩展选项]
     */
    public void setQuerycs(String querycs) {
        this.querycs = querycs ;
        this.modify("querycs",querycs);
    }

    /**
     * 设置 [扩展模式]
     */
    public void setExtendmode(Integer extendmode) {
        this.extendmode = extendmode ;
        this.modify("extendmode",extendmode);
    }

    /**
     * 设置 [启用审计]
     */
    public void setEnableaudit(Integer enableaudit) {
        this.enableaudit = enableaudit ;
        this.modify("enableaudit",enableaudit);
    }

    /**
     * 设置 [值格式化]
     */
    public void setValueformat(String valueformat) {
        this.valueformat = valueformat ;
        this.modify("valueformat",valueformat);
    }

    /**
     * 设置 [长度]
     */
    public void setLength(Integer length) {
        this.length = length ;
        this.modify("length",length);
    }

    /**
     * 设置 [数据导入标识]
     */
    public void setImporttag(String importtag) {
        this.importtag = importtag ;
        this.modify("importtag",importtag);
    }

    /**
     * 设置 [数据导入识别]
     */
    public void setImportkey(Integer importkey) {
        this.importkey = importkey ;
        this.modify("importkey",importkey);
    }

    /**
     * 设置 [业务标记]
     */
    public void setBiztag(String biztag) {
        this.biztag = biztag ;
        this.modify("biztag",biztag);
    }

    /**
     * 设置 [是否启用]
     */
    public void setValidflag(Integer validflag) {
        this.validflag = validflag ;
        this.modify("validflag",validflag);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [数据类型]
     */
    public void setPsdatatypename(String psdatatypename) {
        this.psdatatypename = psdatatypename ;
        this.modify("psdatatypename",psdatatypename);
    }

    /**
     * 设置 [表名称]
     */
    public void setTablename(String tablename) {
        this.tablename = tablename ;
        this.modify("tablename",tablename);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [主信息属性]
     */
    public void setMajorfield(Integer majorfield) {
        this.majorfield = majorfield ;
        this.modify("majorfield",majorfield);
    }

    /**
     * 设置 [数据导入次序]
     */
    public void setImportorder(Integer importorder) {
        this.importorder = importorder ;
        this.modify("importorder",importorder);
    }

    /**
     * 设置 [联合键值]
     */
    public void setUnionkeyvalue(String unionkeyvalue) {
        this.unionkeyvalue = unionkeyvalue ;
        this.modify("unionkeyvalue",unionkeyvalue);
    }

    /**
     * 设置 [实体属性名称]
     */
    public void setPsdefieldname(String psdefieldname) {
        this.psdefieldname = psdefieldname ;
        this.modify("psdefieldname",psdefieldname);
    }

    /**
     * 设置 [双字节字符]
     */
    public void setUnicodechar(Integer unicodechar) {
        this.unicodechar = unicodechar ;
        this.modify("unicodechar",unicodechar);
    }

    /**
     * 设置 [物理化属性]
     */
    public void setPhysicalfield(Integer physicalfield) {
        this.physicalfield = physicalfield ;
        this.modify("physicalfield",physicalfield);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [字符长度]
     */
    public void setStrlength(Integer strlength) {
        this.strlength = strlength ;
        this.modify("strlength",strlength);
    }

    /**
     * 设置 [属性类型]
     */
    public void setDeftype(Integer deftype) {
        this.deftype = deftype ;
        this.modify("deftype",deftype);
    }

    /**
     * 设置 [属性默认值]
     */
    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue ;
        this.modify("defaultvalue",defaultvalue);
    }

    /**
     * 设置 [空值排序]
     */
    public void setNullvalorder(String nullvalorder) {
        this.nullvalorder = nullvalorder ;
        this.modify("nullvalorder",nullvalorder);
    }

    /**
     * 设置 [外键属性]
     */
    public void setFkey(Integer fkey) {
        this.fkey = fkey ;
        this.modify("fkey",fkey);
    }

    /**
     * 设置 [逻辑字段格式]
     */
    public void setFormulaformat(String formulaformat) {
        this.formulaformat = formulaformat ;
        this.modify("formulaformat",formulaformat);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [主键属性]
     */
    public void setPkey(Integer pkey) {
        this.pkey = pkey ;
        this.modify("pkey",pkey);
    }

    /**
     * 设置 [浮点精度]
     */
    public void setPrecision2(Integer precision2) {
        this.precision2 = precision2 ;
        this.modify("precision2",precision2);
    }

    /**
     * 设置 [系统预置属性]
     */
    public void setPredefinetype(String predefinetype) {
        this.predefinetype = predefinetype ;
        this.modify("predefinetype",predefinetype);
    }

    /**
     * 设置 [查询输出]
     */
    public void setQuerycolumn(Integer querycolumn) {
        this.querycolumn = querycolumn ;
        this.modify("querycolumn",querycolumn);
    }

    /**
     * 设置 [支持临时数据]
     */
    public void setEnabletempdata(Integer enabletempdata) {
        this.enabletempdata = enabletempdata ;
        this.modify("enabletempdata",enabletempdata);
    }

    /**
     * 设置 [粘帖重置]
     */
    public void setPastereset(Integer pastereset) {
        this.pastereset = pastereset ;
        this.modify("pastereset",pastereset);
    }

    /**
     * 设置 [模型锁模式]
     */
    public void setLockflag(Integer lockflag) {
        this.lockflag = lockflag ;
        this.modify("lockflag",lockflag);
    }

    /**
     * 设置 [字符转换]
     */
    public void setStringcase(String stringcase) {
        this.stringcase = stringcase ;
        this.modify("stringcase",stringcase);
    }

    /**
     * 设置 [启用快速搜索]
     */
    public void setEnableqs(Integer enableqs) {
        this.enableqs = enableqs ;
        this.modify("enableqs",enableqs);
    }

    /**
     * 设置 [启用列权限控制]
     */
    public void setEnablecolpriv(Integer enablecolpriv) {
        this.enablecolpriv = enablecolpriv ;
        this.modify("enablecolpriv",enablecolpriv);
    }

    /**
     * 设置 [中文名称]
     */
    public void setLogicname(String logicname) {
        this.logicname = logicname ;
        this.modify("logicname",logicname);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [允许为空]
     */
    public void setAllowempty(Integer allowempty) {
        this.allowempty = allowempty ;
        this.modify("allowempty",allowempty);
    }

    /**
     * 设置 [自定义导出范围]
     */
    public void setCustomexportscope(Integer customexportscope) {
        this.customexportscope = customexportscope ;
        this.modify("customexportscope",customexportscope);
    }

    /**
     * 设置 [动态模型类型]
     */
    public void setDynamodelflag(Integer dynamodelflag) {
        this.dynamodelflag = dynamodelflag ;
        this.modify("dynamodelflag",dynamodelflag);
    }

    /**
     * 设置 [导出范围]
     */
    public void setExportscope(Integer exportscope) {
        this.exportscope = exportscope ;
        this.modify("exportscope",exportscope);
    }

    /**
     * 设置 [审计信息格式]
     */
    public void setAuditinfoformat(String auditinfoformat) {
        this.auditinfoformat = auditinfoformat ;
        this.modify("auditinfoformat",auditinfoformat);
    }

    /**
     * 设置 [数据库新建值模式]
     */
    public void setDbvaluemode2(String dbvaluemode2) {
        this.dbvaluemode2 = dbvaluemode2 ;
        this.modify("dbvaluemode2",dbvaluemode2);
    }

    /**
     * 设置 [数据类型]
     */
    public void setPsdatatypeid(String psdatatypeid) {
        this.psdatatypeid = psdatatypeid ;
        this.modify("psdatatypeid",psdatatypeid);
    }

    /**
     * 设置 [值项属性]
     */
    public void setValuepsdefname(String valuepsdefname) {
        this.valuepsdefname = valuepsdefname ;
        this.modify("valuepsdefname",valuepsdefname);
    }

    /**
     * 设置 [范围属性]
     */
    public void setDupcheckpsdefname(String dupcheckpsdefname) {
        this.dupcheckpsdefname = dupcheckpsdefname ;
        this.modify("dupchkpsdefname",dupcheckpsdefname);
    }

    /**
     * 设置 [范围属性2]
     */
    public void setNo2dupchkpsdefname(String no2dupchkpsdefname) {
        this.no2dupchkpsdefname = no2dupchkpsdefname ;
        this.modify("no2dupchkpsdefname",no2dupchkpsdefname);
    }

    /**
     * 设置 [关系属性]
     */
    public void setDerpsdefname(String derpsdefname) {
        this.derpsdefname = derpsdefname ;
        this.modify("derpsdefname",derpsdefname);
    }

    /**
     * 设置 [范围属性3]
     */
    public void setNo3dupchkpsdefname(String no3dupchkpsdefname) {
        this.no3dupchkpsdefname = no3dupchkpsdefname ;
        this.modify("no3dupchkpsdefname",no3dupchkpsdefname);
    }

    /**
     * 设置 [实体]
     */
    public void setPsdename(String psdename) {
        this.psdename = psdename ;
        this.modify("psdename",psdename);
    }

    /**
     * 设置 [实体]
     */
    public void setPsdeid(String psdeid) {
        this.psdeid = psdeid ;
        this.modify("psdeid",psdeid);
    }

    /**
     * 设置 [范围属性2]
     */
    public void setNo2dupchkpsdefid(String no2dupchkpsdefid) {
        this.no2dupchkpsdefid = no2dupchkpsdefid ;
        this.modify("no2dupchkpsdefid",no2dupchkpsdefid);
    }

    /**
     * 设置 [范围属性]
     */
    public void setDupcheckpsdefid(String dupcheckpsdefid) {
        this.dupcheckpsdefid = dupcheckpsdefid ;
        this.modify("dupchkpsdefid",dupcheckpsdefid);
    }

    /**
     * 设置 [值项属性]
     */
    public void setValuepsdefid(String valuepsdefid) {
        this.valuepsdefid = valuepsdefid ;
        this.modify("valuepsdefid",valuepsdefid);
    }

    /**
     * 设置 [关系属性]
     */
    public void setDerpsdefid(String derpsdefid) {
        this.derpsdefid = derpsdefid ;
        this.modify("derpsdefid",derpsdefid);
    }

    /**
     * 设置 [范围属性3]
     */
    public void setNo3dupchkpsdefid(String no3dupchkpsdefid) {
        this.no3dupchkpsdefid = no3dupchkpsdefid ;
        this.modify("no3dupchkpsdefid",no3dupchkpsdefid);
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
        this.reset("psdefieldid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


