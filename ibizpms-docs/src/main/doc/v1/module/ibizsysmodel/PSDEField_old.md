# 实体属性(PSDEFIELD)

  

## 关系
{% plantuml %}
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
实体属性 *-- 实体属性 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|自定义参数|USERPARAMS|TEXT|&nbsp;|
|支持链接属性回写|ENAWRITEBACK|YESNO|&nbsp;|
|视图列级别|VIEWCOLLEVEL|NSCODELIST|&nbsp;|
|服务代码名称|SERVICECODENAME|TEXT|&nbsp;|
|重复值检查|DUPCHECKMODE|SSCODELIST|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|更新旧值回填|UPDATEOVMODE|SSCODELIST|&nbsp;|
|单位宽度|UNITWIDTH|INT|&nbsp;|
|数据库更新值模式|DBVALUEMODE|SSCODELIST|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|默认测试值|TESTDATA|TEXT|&nbsp;|
|主状态属性|STATEFIELD|SSCODELIST|&nbsp;|
|逻辑属性参数|FORMULAFIELDS|TEXT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|单位|UNIT|TEXT|&nbsp;|
|多表单识别属性|MULTIFORMFIELD|YESNO|&nbsp;|
|索引类型属性|INDEXTYPE|YESNO|&nbsp;|
|检查值范围|DUPCHECKVALUES|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|默认用户行为|ENABLEUSERINPUT|NMCODELIST|&nbsp;|
|默认值类型|DVT|SSCODELIST|&nbsp;|
|检查递归|CHECKRECURSION|YESNO|&nbsp;|
|查询扩展选项|QUERYCS|SMCODELIST|&nbsp;|
|扩展模式|EXTENDMODE|NSCODELIST|&nbsp;|
|启用审计|ENABLEAUDIT|YESNO|&nbsp;|
|值格式化|VALUEFORMAT|TEXT|&nbsp;|
|排序值|ORDERVALUE|INT|&nbsp;|
|长度|LENGTH|INT|&nbsp;|
|数据导入标识|IMPORTTAG|TEXT|&nbsp;|
|数据导入识别|IMPORTKEY|YESNO|&nbsp;|
|业务标记|BIZTAG|SSCODELIST|&nbsp;|
|是否启用|VALIDFLAG|YESNO|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|数据类型|PSDATATYPENAME|TEXT|&nbsp;|
|表名称|TABLENAME|TEXT|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|主信息属性|MAJORFIELD|YESNO|&nbsp;|
|实体属性标识|PSDEFIELDID|GUID|&nbsp;|
|数据导入次序|IMPORTORDER|INT|&nbsp;|
|联合键值|UNIONKEYVALUE|SSCODELIST|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|实体属性名称|PSDEFIELDNAME|TEXT|&nbsp;|
|双字节字符|UNICODECHAR|YESNO|&nbsp;|
|物理化属性|PHYSICALFIELD|YESNO|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|代码名称|CODENAME|TEXT|&nbsp;|
|字符长度|STRLENGTH|INT|&nbsp;|
|属性类型|DEFTYPE|NSCODELIST|&nbsp;|
|属性默认值|DEFAULTVALUE|TEXT|&nbsp;|
|空值排序|NULLVALORDER|SSCODELIST|&nbsp;|
|外键属性|FKEY|YESNO|&nbsp;|
|逻辑字段格式|FORMULAFORMAT|TEXT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|主键属性|PKEY|NSCODELIST|&nbsp;|
|浮点精度|PRECISION2|INT|&nbsp;|
|系统预置属性|PREDEFINETYPE|SSCODELIST|&nbsp;|
|查询输出|QUERYCOLUMN|YESNO|&nbsp;|
|支持临时数据|ENABLETEMPDATA|YESNO|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|粘帖重置|PASTERESET|YESNO|&nbsp;|
|模型锁模式|LOCKFLAG|NSCODELIST|&nbsp;|
|字符转换|STRINGCASE|SSCODELIST|&nbsp;|
|启用快速搜索|ENABLEQS|YESNO|&nbsp;|
|启用列权限控制|ENABLECOLPRIV|YESNO|&nbsp;|
|中文名称|LOGICNAME|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|允许为空|ALLOWEMPTY|YESNO|&nbsp;|
|自定义导出范围|CUSTOMEXPORTSCOPE|YESNO|&nbsp;|
|动态模型类型|DYNAMODELFLAG|NSCODELIST|&nbsp;|
|导出范围|EXPORTSCOPE|NMCODELIST|&nbsp;|
|审计信息格式|AUDITINFOFORMAT|TEXT|&nbsp;|
|数据库新建值模式|DBVALUEMODE2|SSCODELIST|&nbsp;|
|数据类型|PSDATATYPEID|TEXT|&nbsp;|
|值项属性|VALUEPSDEFNAME|PICKUPTEXT|&nbsp;|
|范围属性|DUPCHKPSDEFNAME|PICKUPTEXT|&nbsp;|
|范围属性2|NO2DUPCHKPSDEFNAME|PICKUPTEXT|&nbsp;|
|关系属性|DERPSDEFNAME|PICKUPTEXT|&nbsp;|
|范围属性3|NO3DUPCHKPSDEFNAME|PICKUPTEXT|&nbsp;|
|实体|PSDENAME|PICKUPTEXT|&nbsp;|
|PSSUBSYSSADEID|PSSUBSYSSADEID|PICKUPDATA|&nbsp;|
|实体|PSDEID|PICKUP|&nbsp;|
|范围属性2|NO2DUPCHKPSDEFID|PICKUP|&nbsp;|
|范围属性|DUPCHKPSDEFID|PICKUP|&nbsp;|
|值项属性|VALUEPSDEFID|PICKUP|&nbsp;|
|关系属性|DERPSDEFID|PICKUP|&nbsp;|
|范围属性3|NO3DUPCHKPSDEFID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|自定义参数|默认规则|内容长度必须小于等于[2000]|
|支持链接属性回写|默认规则|默认规则|
|视图列级别|默认规则|默认规则|
|服务代码名称|默认规则|内容长度必须小于等于[50]|
|重复值检查|默认规则|内容长度必须小于等于[100]|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|更新旧值回填|默认规则|内容长度必须小于等于[20]|
|单位宽度|默认规则|默认规则|
|数据库更新值模式|默认规则|内容长度必须小于等于[30]|
|更新时间|默认规则|默认规则|
|默认测试值|默认规则|内容长度必须小于等于[100]|
|主状态属性|默认规则|内容长度必须小于等于[10]|
|逻辑属性参数|默认规则|内容长度必须小于等于[200]|
|系统|默认规则|内容长度必须小于等于[100]|
|单位|默认规则|内容长度必须小于等于[20]|
|多表单识别属性|默认规则|默认规则|
|索引类型属性|默认规则|默认规则|
|检查值范围|默认规则|内容长度必须小于等于[200]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|默认用户行为|默认规则|默认规则|
|默认值类型|默认规则|内容长度必须小于等于[50]|
|检查递归|默认规则|默认规则|
|查询扩展选项|默认规则|内容长度必须小于等于[60]|
|扩展模式|默认规则|默认规则|
|启用审计|默认规则|默认规则|
|值格式化|默认规则|内容长度必须小于等于[50]|
|排序值|默认规则|默认规则|
|长度|默认规则|默认规则|
|数据导入标识|默认规则|内容长度必须小于等于[30]|
|数据导入识别|默认规则|默认规则|
|业务标记|默认规则|内容长度必须小于等于[30]|
|是否启用|默认规则|默认规则|
|用户标记|默认规则|内容长度必须小于等于[100]|
|数据类型|默认规则|内容长度必须小于等于[200]|
|表名称|默认规则|内容长度必须小于等于[60]|
|用户标记2|默认规则|内容长度必须小于等于[100]|
|主信息属性|默认规则|默认规则|
|实体属性标识|默认规则|内容长度必须小于等于[100]|
|数据导入次序|默认规则|默认规则|
|联合键值|默认规则|内容长度必须小于等于[10]|
|更新人|默认规则|内容长度必须小于等于[60]|
|实体属性名称|默认规则|内容长度必须小于等于[200]|
|双字节字符|默认规则|默认规则|
|物理化属性|默认规则|默认规则|
|用户分类|默认规则|内容长度必须小于等于[10]|
|代码名称|默认规则|内容长度必须小于等于[60]|
|字符长度|默认规则|默认规则|
|属性类型|默认规则|默认规则|
|属性默认值|默认规则|内容长度必须小于等于[200]|
|空值排序|默认规则|内容长度必须小于等于[10]|
|外键属性|默认规则|默认规则|
|逻辑字段格式|默认规则|内容长度必须小于等于[500]|
|备注|默认规则|内容长度必须小于等于[2000]|
|主键属性|默认规则|默认规则|
|浮点精度|默认规则|默认规则|
|系统预置属性|默认规则|内容长度必须小于等于[100]|
|查询输出|默认规则|默认规则|
|支持临时数据|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|粘帖重置|默认规则|默认规则|
|模型锁模式|默认规则|默认规则|
|字符转换|默认规则|内容长度必须小于等于[100]|
|启用快速搜索|默认规则|默认规则|
|启用列权限控制|默认规则|默认规则|
|中文名称|默认规则|内容长度必须小于等于[60]|
|建立人|默认规则|内容长度必须小于等于[60]|
|系统|默认规则|内容长度必须小于等于[100]|
|允许为空|默认规则|默认规则|
|自定义导出范围|默认规则|默认规则|
|动态模型类型|默认规则|默认规则|
|导出范围|默认规则|默认规则|
|审计信息格式|默认规则|内容长度必须小于等于[200]|
|数据库新建值模式|默认规则|内容长度必须小于等于[30]|
|数据类型|默认规则|内容长度必须小于等于[100]|
|值项属性|默认规则|内容长度必须小于等于[200]|
|范围属性|默认规则|内容长度必须小于等于[200]|
|范围属性2|默认规则|内容长度必须小于等于[200]|
|关系属性|默认规则|内容长度必须小于等于[200]|
|范围属性3|默认规则|内容长度必须小于等于[200]|
|实体|默认规则|内容长度必须小于等于[200]|
|PSSUBSYSSADEID|默认规则|内容长度必须小于等于[100]|
|实体|默认规则|内容长度必须小于等于[100]|
|范围属性2|默认规则|内容长度必须小于等于[100]|
|范围属性|默认规则|内容长度必须小于等于[100]|
|值项属性|默认规则|内容长度必须小于等于[100]|
|关系属性|默认规则|内容长度必须小于等于[100]|
|范围属性3|默认规则|内容长度必须小于等于[100]|

## 状态控制

无


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSDEField_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSDEField_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|视图列级别(VIEWCOLLEVEL)|EQ|
|重复值检查(DUPCHECKMODE)|EQ|
|更新旧值回填(UPDATEOVMODE)|EQ|
|数据库更新值模式(DBVALUEMODE)|EQ|
|主状态属性(STATEFIELD)|EQ|
|默认值类型(DVT)|EQ|
|扩展模式(EXTENDMODE)|EQ|
|业务标记(BIZTAG)|EQ|
|联合键值(UNIONKEYVALUE)|EQ|
|实体属性名称(PSDEFIELDNAME)|LIKE|
|用户分类(USERCAT)|EQ|
|属性类型(DEFTYPE)|EQ|
|空值排序(NULLVALORDER)|EQ|
|主键属性(PKEY)|EQ|
|系统预置属性(PREDEFINETYPE)|EQ|
|模型锁模式(LOCKFLAG)|EQ|
|字符转换(STRINGCASE)|EQ|
|动态模型类型(DYNAMODELFLAG)|EQ|
|数据库新建值模式(DBVALUEMODE2)|EQ|
|值项属性(VALUEPSDEFNAME)|EQ|
|值项属性(VALUEPSDEFNAME)|LIKE|
|范围属性(DUPCHKPSDEFNAME)|EQ|
|范围属性(DUPCHKPSDEFNAME)|LIKE|
|范围属性2(NO2DUPCHKPSDEFNAME)|EQ|
|范围属性2(NO2DUPCHKPSDEFNAME)|LIKE|
|关系属性(DERPSDEFNAME)|EQ|
|关系属性(DERPSDEFNAME)|LIKE|
|范围属性3(NO3DUPCHKPSDEFNAME)|EQ|
|范围属性3(NO3DUPCHKPSDEFNAME)|LIKE|
|实体(PSDENAME)|EQ|
|实体(PSDENAME)|LIKE|
|实体(PSDEID)|EQ|
|范围属性2(NO2DUPCHKPSDEFID)|EQ|
|范围属性(DUPCHKPSDEFID)|EQ|
|值项属性(VALUEPSDEFID)|EQ|
|关系属性(DERPSDEFID)|EQ|
|范围属性3(NO3DUPCHKPSDEFID)|EQ|

## 导入模式
无


## 导出模式
无
