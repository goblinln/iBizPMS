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

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [实体属性] 对象
 */
@Data
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
    private String userparams;

    /**
     * 支持链接属性回写
     */
    @JSONField(name = "enawriteback")
    @JsonProperty("enawriteback")
    private Integer enawriteback;

    /**
     * 视图列级别
     */
    @JSONField(name = "viewcollevel")
    @JsonProperty("viewcollevel")
    private Integer viewcollevel;

    /**
     * 服务代码名称
     */
    @JSONField(name = "servicecodename")
    @JsonProperty("servicecodename")
    private String servicecodename;

    /**
     * 重复值检查
     */
    @JSONField(name = "dupcheckmode")
    @JsonProperty("dupcheckmode")
    private String dupcheckmode;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    private String usertag3;

    /**
     * 更新旧值回填
     */
    @JSONField(name = "updateovmode")
    @JsonProperty("updateovmode")
    private String updateovmode;

    /**
     * 单位宽度
     */
    @JSONField(name = "unitwidth")
    @JsonProperty("unitwidth")
    private Integer unitwidth;

    /**
     * 数据库更新值模式
     */
    @JSONField(name = "dbvaluemode")
    @JsonProperty("dbvaluemode")
    private String dbvaluemode;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 默认测试值
     */
    @JSONField(name = "testdata")
    @JsonProperty("testdata")
    private String testdata;

    /**
     * 主状态属性
     */
    @JSONField(name = "statefield")
    @JsonProperty("statefield")
    private String statefield;

    /**
     * 逻辑属性参数
     */
    @JSONField(name = "formulafields")
    @JsonProperty("formulafields")
    private String formulafields;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    private String pssystemname;

    /**
     * 单位
     */
    @JSONField(name = "unit")
    @JsonProperty("unit")
    private String unit;

    /**
     * 多表单识别属性
     */
    @JSONField(name = "multiformfield")
    @JsonProperty("multiformfield")
    private Integer multiformfield;

    /**
     * 索引类型属性
     */
    @JSONField(name = "indextype")
    @JsonProperty("indextype")
    private Integer indextype;

    /**
     * 检查值范围
     */
    @JSONField(name = "dupcheckvalues")
    @JsonProperty("dupcheckvalues")
    private String dupcheckvalues;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    private String usertag4;

    /**
     * 默认用户行为
     */
    @DEField(defaultValue = "3")
    @JSONField(name = "enableuserinput")
    @JsonProperty("enableuserinput")
    private Integer enableuserinput;

    /**
     * 默认值类型
     */
    @DEField(name = "dvt")
    @JSONField(name = "defaultvaluetype")
    @JsonProperty("defaultvaluetype")
    private String defaultvaluetype;

    /**
     * 检查递归
     */
    @JSONField(name = "checkrecursion")
    @JsonProperty("checkrecursion")
    private Integer checkrecursion;

    /**
     * 查询扩展选项
     */
    @JSONField(name = "querycs")
    @JsonProperty("querycs")
    private String querycs;

    /**
     * 扩展模式
     */
    @JSONField(name = "extendmode")
    @JsonProperty("extendmode")
    private Integer extendmode;

    /**
     * 启用审计
     */
    @JSONField(name = "enableaudit")
    @JsonProperty("enableaudit")
    private Integer enableaudit;

    /**
     * 值格式化
     */
    @JSONField(name = "valueformat")
    @JsonProperty("valueformat")
    private String valueformat;

    /**
     * 排序值
     */
    @DEField(preType = DEPredefinedFieldType.ORDERVALUE)
    @JSONField(name = "ordervalue")
    @JsonProperty("ordervalue")
    private Integer ordervalue;

    /**
     * 长度
     */
    @JSONField(name = "length")
    @JsonProperty("length")
    private Integer length;

    /**
     * 数据导入标识
     */
    @JSONField(name = "importtag")
    @JsonProperty("importtag")
    private String importtag;

    /**
     * 数据导入识别
     */
    @JSONField(name = "importkey")
    @JsonProperty("importkey")
    private Integer importkey;

    /**
     * 业务标记
     */
    @JSONField(name = "biztag")
    @JsonProperty("biztag")
    private String biztag;

    /**
     * 是否启用
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    private Integer validflag;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    private String usertag;

    /**
     * 数据类型
     */
    @JSONField(name = "psdatatypename")
    @JsonProperty("psdatatypename")
    private String psdatatypename;

    /**
     * 表名称
     */
    @JSONField(name = "tablename")
    @JsonProperty("tablename")
    private String tablename;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    private String usertag2;

    /**
     * 主信息属性
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "majorfield")
    @JsonProperty("majorfield")
    private Integer majorfield;

    /**
     * 实体属性标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "psdefieldid")
    @JsonProperty("psdefieldid")
    private String psdefieldid;

    /**
     * 数据导入次序
     */
    @JSONField(name = "importorder")
    @JsonProperty("importorder")
    private Integer importorder;

    /**
     * 联合键值
     */
    @JSONField(name = "unionkeyvalue")
    @JsonProperty("unionkeyvalue")
    private String unionkeyvalue;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;

    /**
     * 实体属性名称
     */
    @JSONField(name = "psdefieldname")
    @JsonProperty("psdefieldname")
    private String psdefieldname;

    /**
     * 双字节字符
     */
    @JSONField(name = "unicodechar")
    @JsonProperty("unicodechar")
    private Integer unicodechar;

    /**
     * 物理化属性
     */
    @JSONField(name = "physicalfield")
    @JsonProperty("physicalfield")
    private Integer physicalfield;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    private String usercat;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    private String codename;

    /**
     * 字符长度
     */
    @JSONField(name = "strlength")
    @JsonProperty("strlength")
    private Integer strlength;

    /**
     * 属性类型
     */
    @JSONField(name = "deftype")
    @JsonProperty("deftype")
    private Integer deftype;

    /**
     * 属性默认值
     */
    @JSONField(name = "defaultvalue")
    @JsonProperty("defaultvalue")
    private String defaultvalue;

    /**
     * 空值排序
     */
    @JSONField(name = "nullvalorder")
    @JsonProperty("nullvalorder")
    private String nullvalorder;

    /**
     * 外键属性
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "fkey")
    @JsonProperty("fkey")
    private Integer fkey;

    /**
     * 逻辑字段格式
     */
    @JSONField(name = "formulaformat")
    @JsonProperty("formulaformat")
    private String formulaformat;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;

    /**
     * 主键属性
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "pkey")
    @JsonProperty("pkey")
    private Integer pkey;

    /**
     * 浮点精度
     */
    @JSONField(name = "precision2")
    @JsonProperty("precision2")
    private Integer precision2;

    /**
     * 系统预置属性
     */
    @JSONField(name = "predefinetype")
    @JsonProperty("predefinetype")
    private String predefinetype;

    /**
     * 查询输出
     */
    @JSONField(name = "querycolumn")
    @JsonProperty("querycolumn")
    private Integer querycolumn;

    /**
     * 支持临时数据
     */
    @JSONField(name = "enabletempdata")
    @JsonProperty("enabletempdata")
    private Integer enabletempdata;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 粘帖重置
     */
    @JSONField(name = "pastereset")
    @JsonProperty("pastereset")
    private Integer pastereset;

    /**
     * 模型锁模式
     */
    @JSONField(name = "lockflag")
    @JsonProperty("lockflag")
    private Integer lockflag;

    /**
     * 字符转换
     */
    @JSONField(name = "stringcase")
    @JsonProperty("stringcase")
    private String stringcase;

    /**
     * 启用快速搜索
     */
    @JSONField(name = "enableqs")
    @JsonProperty("enableqs")
    private Integer enableqs;

    /**
     * 启用列权限控制
     */
    @JSONField(name = "enablecolpriv")
    @JsonProperty("enablecolpriv")
    private Integer enablecolpriv;

    /**
     * 中文名称
     */
    @JSONField(name = "logicname")
    @JsonProperty("logicname")
    private String logicname;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    private String pssystemid;

    /**
     * 允许为空
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "allowempty")
    @JsonProperty("allowempty")
    private Integer allowempty;

    /**
     * 自定义导出范围
     */
    @JSONField(name = "customexportscope")
    @JsonProperty("customexportscope")
    private Integer customexportscope;

    /**
     * 动态模型类型
     */
    @JSONField(name = "dynamodelflag")
    @JsonProperty("dynamodelflag")
    private Integer dynamodelflag;

    /**
     * 导出范围
     */
    @JSONField(name = "exportscope")
    @JsonProperty("exportscope")
    private Integer exportscope;

    /**
     * 审计信息格式
     */
    @JSONField(name = "auditinfoformat")
    @JsonProperty("auditinfoformat")
    private String auditinfoformat;

    /**
     * 数据库新建值模式
     */
    @JSONField(name = "dbvaluemode2")
    @JsonProperty("dbvaluemode2")
    private String dbvaluemode2;

    /**
     * 数据类型
     */
    @JSONField(name = "psdatatypeid")
    @JsonProperty("psdatatypeid")
    private String psdatatypeid;

    /**
     * 值项属性
     */
    @JSONField(name = "valuepsdefname")
    @JsonProperty("valuepsdefname")
    private String valuepsdefname;

    /**
     * 范围属性
     */
    @DEField(name = "dupchkpsdefname")
    @JSONField(name = "dupcheckpsdefname")
    @JsonProperty("dupcheckpsdefname")
    private String dupcheckpsdefname;

    /**
     * 范围属性2
     */
    @JSONField(name = "no2dupchkpsdefname")
    @JsonProperty("no2dupchkpsdefname")
    private String no2dupchkpsdefname;

    /**
     * 关系属性
     */
    @JSONField(name = "derpsdefname")
    @JsonProperty("derpsdefname")
    private String derpsdefname;

    /**
     * 范围属性3
     */
    @JSONField(name = "no3dupchkpsdefname")
    @JsonProperty("no3dupchkpsdefname")
    private String no3dupchkpsdefname;

    /**
     * 实体
     */
    @JSONField(name = "psdename")
    @JsonProperty("psdename")
    private String psdename;

    /**
     * PSSUBSYSSADEID
     */
    @JSONField(name = "pssubsyssadeid")
    @JsonProperty("pssubsyssadeid")
    private String pssubsyssadeid;

    /**
     * 实体
     */
    @JSONField(name = "psdeid")
    @JsonProperty("psdeid")
    private String psdeid;

    /**
     * 范围属性2
     */
    @JSONField(name = "no2dupchkpsdefid")
    @JsonProperty("no2dupchkpsdefid")
    private String no2dupchkpsdefid;

    /**
     * 范围属性
     */
    @DEField(name = "dupchkpsdefid")
    @JSONField(name = "dupcheckpsdefid")
    @JsonProperty("dupcheckpsdefid")
    private String dupcheckpsdefid;

    /**
     * 值项属性
     */
    @JSONField(name = "valuepsdefid")
    @JsonProperty("valuepsdefid")
    private String valuepsdefid;

    /**
     * 关系属性
     */
    @JSONField(name = "derpsdefid")
    @JsonProperty("derpsdefid")
    private String derpsdefid;

    /**
     * 范围属性3
     */
    @JSONField(name = "no3dupchkpsdefid")
    @JsonProperty("no3dupchkpsdefid")
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
        this.modify("userparams", userparams);
    }

    /**
     * 设置 [支持链接属性回写]
     */
    public void setEnawriteback(Integer enawriteback) {
        this.enawriteback = enawriteback ;
        this.modify("enawriteback", enawriteback);
    }

    /**
     * 设置 [视图列级别]
     */
    public void setViewcollevel(Integer viewcollevel) {
        this.viewcollevel = viewcollevel ;
        this.modify("viewcollevel", viewcollevel);
    }

    /**
     * 设置 [服务代码名称]
     */
    public void setServicecodename(String servicecodename) {
        this.servicecodename = servicecodename ;
        this.modify("servicecodename", servicecodename);
    }

    /**
     * 设置 [重复值检查]
     */
    public void setDupcheckmode(String dupcheckmode) {
        this.dupcheckmode = dupcheckmode ;
        this.modify("dupcheckmode", dupcheckmode);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3", usertag3);
    }

    /**
     * 设置 [更新旧值回填]
     */
    public void setUpdateovmode(String updateovmode) {
        this.updateovmode = updateovmode ;
        this.modify("updateovmode", updateovmode);
    }

    /**
     * 设置 [单位宽度]
     */
    public void setUnitwidth(Integer unitwidth) {
        this.unitwidth = unitwidth ;
        this.modify("unitwidth", unitwidth);
    }

    /**
     * 设置 [数据库更新值模式]
     */
    public void setDbvaluemode(String dbvaluemode) {
        this.dbvaluemode = dbvaluemode ;
        this.modify("dbvaluemode", dbvaluemode);
    }

    /**
     * 设置 [默认测试值]
     */
    public void setTestdata(String testdata) {
        this.testdata = testdata ;
        this.modify("testdata", testdata);
    }

    /**
     * 设置 [主状态属性]
     */
    public void setStatefield(String statefield) {
        this.statefield = statefield ;
        this.modify("statefield", statefield);
    }

    /**
     * 设置 [逻辑属性参数]
     */
    public void setFormulafields(String formulafields) {
        this.formulafields = formulafields ;
        this.modify("formulafields", formulafields);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname", pssystemname);
    }

    /**
     * 设置 [单位]
     */
    public void setUnit(String unit) {
        this.unit = unit ;
        this.modify("unit", unit);
    }

    /**
     * 设置 [多表单识别属性]
     */
    public void setMultiformfield(Integer multiformfield) {
        this.multiformfield = multiformfield ;
        this.modify("multiformfield", multiformfield);
    }

    /**
     * 设置 [索引类型属性]
     */
    public void setIndextype(Integer indextype) {
        this.indextype = indextype ;
        this.modify("indextype", indextype);
    }

    /**
     * 设置 [检查值范围]
     */
    public void setDupcheckvalues(String dupcheckvalues) {
        this.dupcheckvalues = dupcheckvalues ;
        this.modify("dupcheckvalues", dupcheckvalues);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4", usertag4);
    }

    /**
     * 设置 [默认用户行为]
     */
    public void setEnableuserinput(Integer enableuserinput) {
        this.enableuserinput = enableuserinput ;
        this.modify("enableuserinput", enableuserinput);
    }

    /**
     * 设置 [默认值类型]
     */
    public void setDefaultvaluetype(String defaultvaluetype) {
        this.defaultvaluetype = defaultvaluetype ;
        this.modify("dvt", defaultvaluetype);
    }

    /**
     * 设置 [检查递归]
     */
    public void setCheckrecursion(Integer checkrecursion) {
        this.checkrecursion = checkrecursion ;
        this.modify("checkrecursion", checkrecursion);
    }

    /**
     * 设置 [查询扩展选项]
     */
    public void setQuerycs(String querycs) {
        this.querycs = querycs ;
        this.modify("querycs", querycs);
    }

    /**
     * 设置 [扩展模式]
     */
    public void setExtendmode(Integer extendmode) {
        this.extendmode = extendmode ;
        this.modify("extendmode", extendmode);
    }

    /**
     * 设置 [启用审计]
     */
    public void setEnableaudit(Integer enableaudit) {
        this.enableaudit = enableaudit ;
        this.modify("enableaudit", enableaudit);
    }

    /**
     * 设置 [值格式化]
     */
    public void setValueformat(String valueformat) {
        this.valueformat = valueformat ;
        this.modify("valueformat", valueformat);
    }

    /**
     * 设置 [长度]
     */
    public void setLength(Integer length) {
        this.length = length ;
        this.modify("length", length);
    }

    /**
     * 设置 [数据导入标识]
     */
    public void setImporttag(String importtag) {
        this.importtag = importtag ;
        this.modify("importtag", importtag);
    }

    /**
     * 设置 [数据导入识别]
     */
    public void setImportkey(Integer importkey) {
        this.importkey = importkey ;
        this.modify("importkey", importkey);
    }

    /**
     * 设置 [业务标记]
     */
    public void setBiztag(String biztag) {
        this.biztag = biztag ;
        this.modify("biztag", biztag);
    }

    /**
     * 设置 [是否启用]
     */
    public void setValidflag(Integer validflag) {
        this.validflag = validflag ;
        this.modify("validflag", validflag);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag", usertag);
    }

    /**
     * 设置 [数据类型]
     */
    public void setPsdatatypename(String psdatatypename) {
        this.psdatatypename = psdatatypename ;
        this.modify("psdatatypename", psdatatypename);
    }

    /**
     * 设置 [表名称]
     */
    public void setTablename(String tablename) {
        this.tablename = tablename ;
        this.modify("tablename", tablename);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2", usertag2);
    }

    /**
     * 设置 [主信息属性]
     */
    public void setMajorfield(Integer majorfield) {
        this.majorfield = majorfield ;
        this.modify("majorfield", majorfield);
    }

    /**
     * 设置 [数据导入次序]
     */
    public void setImportorder(Integer importorder) {
        this.importorder = importorder ;
        this.modify("importorder", importorder);
    }

    /**
     * 设置 [联合键值]
     */
    public void setUnionkeyvalue(String unionkeyvalue) {
        this.unionkeyvalue = unionkeyvalue ;
        this.modify("unionkeyvalue", unionkeyvalue);
    }

    /**
     * 设置 [实体属性名称]
     */
    public void setPsdefieldname(String psdefieldname) {
        this.psdefieldname = psdefieldname ;
        this.modify("psdefieldname", psdefieldname);
    }

    /**
     * 设置 [双字节字符]
     */
    public void setUnicodechar(Integer unicodechar) {
        this.unicodechar = unicodechar ;
        this.modify("unicodechar", unicodechar);
    }

    /**
     * 设置 [物理化属性]
     */
    public void setPhysicalfield(Integer physicalfield) {
        this.physicalfield = physicalfield ;
        this.modify("physicalfield", physicalfield);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat", usercat);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename", codename);
    }

    /**
     * 设置 [字符长度]
     */
    public void setStrlength(Integer strlength) {
        this.strlength = strlength ;
        this.modify("strlength", strlength);
    }

    /**
     * 设置 [属性类型]
     */
    public void setDeftype(Integer deftype) {
        this.deftype = deftype ;
        this.modify("deftype", deftype);
    }

    /**
     * 设置 [属性默认值]
     */
    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue ;
        this.modify("defaultvalue", defaultvalue);
    }

    /**
     * 设置 [空值排序]
     */
    public void setNullvalorder(String nullvalorder) {
        this.nullvalorder = nullvalorder ;
        this.modify("nullvalorder", nullvalorder);
    }

    /**
     * 设置 [外键属性]
     */
    public void setFkey(Integer fkey) {
        this.fkey = fkey ;
        this.modify("fkey", fkey);
    }

    /**
     * 设置 [逻辑字段格式]
     */
    public void setFormulaformat(String formulaformat) {
        this.formulaformat = formulaformat ;
        this.modify("formulaformat", formulaformat);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo", memo);
    }

    /**
     * 设置 [主键属性]
     */
    public void setPkey(Integer pkey) {
        this.pkey = pkey ;
        this.modify("pkey", pkey);
    }

    /**
     * 设置 [浮点精度]
     */
    public void setPrecision2(Integer precision2) {
        this.precision2 = precision2 ;
        this.modify("precision2", precision2);
    }

    /**
     * 设置 [系统预置属性]
     */
    public void setPredefinetype(String predefinetype) {
        this.predefinetype = predefinetype ;
        this.modify("predefinetype", predefinetype);
    }

    /**
     * 设置 [查询输出]
     */
    public void setQuerycolumn(Integer querycolumn) {
        this.querycolumn = querycolumn ;
        this.modify("querycolumn", querycolumn);
    }

    /**
     * 设置 [支持临时数据]
     */
    public void setEnabletempdata(Integer enabletempdata) {
        this.enabletempdata = enabletempdata ;
        this.modify("enabletempdata", enabletempdata);
    }

    /**
     * 设置 [粘帖重置]
     */
    public void setPastereset(Integer pastereset) {
        this.pastereset = pastereset ;
        this.modify("pastereset", pastereset);
    }

    /**
     * 设置 [模型锁模式]
     */
    public void setLockflag(Integer lockflag) {
        this.lockflag = lockflag ;
        this.modify("lockflag", lockflag);
    }

    /**
     * 设置 [字符转换]
     */
    public void setStringcase(String stringcase) {
        this.stringcase = stringcase ;
        this.modify("stringcase", stringcase);
    }

    /**
     * 设置 [启用快速搜索]
     */
    public void setEnableqs(Integer enableqs) {
        this.enableqs = enableqs ;
        this.modify("enableqs", enableqs);
    }

    /**
     * 设置 [启用列权限控制]
     */
    public void setEnablecolpriv(Integer enablecolpriv) {
        this.enablecolpriv = enablecolpriv ;
        this.modify("enablecolpriv", enablecolpriv);
    }

    /**
     * 设置 [中文名称]
     */
    public void setLogicname(String logicname) {
        this.logicname = logicname ;
        this.modify("logicname", logicname);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid", pssystemid);
    }

    /**
     * 设置 [允许为空]
     */
    public void setAllowempty(Integer allowempty) {
        this.allowempty = allowempty ;
        this.modify("allowempty", allowempty);
    }

    /**
     * 设置 [自定义导出范围]
     */
    public void setCustomexportscope(Integer customexportscope) {
        this.customexportscope = customexportscope ;
        this.modify("customexportscope", customexportscope);
    }

    /**
     * 设置 [动态模型类型]
     */
    public void setDynamodelflag(Integer dynamodelflag) {
        this.dynamodelflag = dynamodelflag ;
        this.modify("dynamodelflag", dynamodelflag);
    }

    /**
     * 设置 [导出范围]
     */
    public void setExportscope(Integer exportscope) {
        this.exportscope = exportscope ;
        this.modify("exportscope", exportscope);
    }

    /**
     * 设置 [审计信息格式]
     */
    public void setAuditinfoformat(String auditinfoformat) {
        this.auditinfoformat = auditinfoformat ;
        this.modify("auditinfoformat", auditinfoformat);
    }

    /**
     * 设置 [数据库新建值模式]
     */
    public void setDbvaluemode2(String dbvaluemode2) {
        this.dbvaluemode2 = dbvaluemode2 ;
        this.modify("dbvaluemode2", dbvaluemode2);
    }

    /**
     * 设置 [数据类型]
     */
    public void setPsdatatypeid(String psdatatypeid) {
        this.psdatatypeid = psdatatypeid ;
        this.modify("psdatatypeid", psdatatypeid);
    }

    /**
     * 设置 [值项属性]
     */
    public void setValuepsdefname(String valuepsdefname) {
        this.valuepsdefname = valuepsdefname ;
        this.modify("valuepsdefname", valuepsdefname);
    }

    /**
     * 设置 [范围属性]
     */
    public void setDupcheckpsdefname(String dupcheckpsdefname) {
        this.dupcheckpsdefname = dupcheckpsdefname ;
        this.modify("dupchkpsdefname", dupcheckpsdefname);
    }

    /**
     * 设置 [范围属性2]
     */
    public void setNo2dupchkpsdefname(String no2dupchkpsdefname) {
        this.no2dupchkpsdefname = no2dupchkpsdefname ;
        this.modify("no2dupchkpsdefname", no2dupchkpsdefname);
    }

    /**
     * 设置 [关系属性]
     */
    public void setDerpsdefname(String derpsdefname) {
        this.derpsdefname = derpsdefname ;
        this.modify("derpsdefname", derpsdefname);
    }

    /**
     * 设置 [范围属性3]
     */
    public void setNo3dupchkpsdefname(String no3dupchkpsdefname) {
        this.no3dupchkpsdefname = no3dupchkpsdefname ;
        this.modify("no3dupchkpsdefname", no3dupchkpsdefname);
    }

    /**
     * 设置 [实体]
     */
    public void setPsdename(String psdename) {
        this.psdename = psdename ;
        this.modify("psdename", psdename);
    }

    /**
     * 设置 [实体]
     */
    public void setPsdeid(String psdeid) {
        this.psdeid = psdeid ;
        this.modify("psdeid", psdeid);
    }

    /**
     * 设置 [范围属性2]
     */
    public void setNo2dupchkpsdefid(String no2dupchkpsdefid) {
        this.no2dupchkpsdefid = no2dupchkpsdefid ;
        this.modify("no2dupchkpsdefid", no2dupchkpsdefid);
    }

    /**
     * 设置 [范围属性]
     */
    public void setDupcheckpsdefid(String dupcheckpsdefid) {
        this.dupcheckpsdefid = dupcheckpsdefid ;
        this.modify("dupchkpsdefid", dupcheckpsdefid);
    }

    /**
     * 设置 [值项属性]
     */
    public void setValuepsdefid(String valuepsdefid) {
        this.valuepsdefid = valuepsdefid ;
        this.modify("valuepsdefid", valuepsdefid);
    }

    /**
     * 设置 [关系属性]
     */
    public void setDerpsdefid(String derpsdefid) {
        this.derpsdefid = derpsdefid ;
        this.modify("derpsdefid", derpsdefid);
    }

    /**
     * 设置 [范围属性3]
     */
    public void setNo3dupchkpsdefid(String no3dupchkpsdefid) {
        this.no3dupchkpsdefid = no3dupchkpsdefid ;
        this.modify("no3dupchkpsdefid", no3dupchkpsdefid);
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
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


