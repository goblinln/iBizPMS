# 测试用例(ZT_CASE)

  

## 关系
{% plantuml %}
测试用例 *-- Bug 
测试用例 *-- 用例步骤 
测试用例 *-- 测试用例 
测试用例 *-- 套件用例 
测试用例 *-- 测试结果 
测试用例 *-- 测试运行 
产品的分支和平台信息 *-- 测试用例 
Bug *-- 测试用例 
测试用例 *-- 测试用例 
模块 *-- 测试用例 
产品 *-- 测试用例 
需求 *-- 测试用例 
测试套件 *-- 测试用例 
索引检索 <|-- 测试用例 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|修改日期|LASTEDITEDDATE|DATETIME|&nbsp;|
|scriptedDate|SCRIPTEDDATE|DATE|&nbsp;应该是预留后期使用的字段|
|标题颜色|COLOR|SSCODELIST|&nbsp;|
|path|PATH|INT|&nbsp;|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|结果|LASTRUNRESULT|SSCODELIST|&nbsp;|
|模块名称|MODULENAME1|TEXT|&nbsp;|
|相关用例|LINKCASE|TEXT|&nbsp;|
|用例步骤集合|CASESTEPS|ONE2MANYDATA|&nbsp;|
|属性|TASK|TEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|howRun|HOWRUN|TEXT|&nbsp;应该是预留后期使用的字段|
|测试结果数|RESULTCNT|INT|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|用例版本|VERSION|INT|&nbsp;|
|scriptedBy|SCRIPTEDBY|TEXT|&nbsp;应该是预留后期使用的字段|
|由谁创建|OPENEDBY|TEXT|&nbsp;|
|用例类型|TYPE|SSCODELIST|&nbsp;|
|测试失败数|RESULTFALICNT|INT|&nbsp;|
|用例状态|STATUS|SSCODELIST|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|auto|AUTO|TEXT|&nbsp;推测应该是自动测试的flag，代码中值unit、func、no|
|是否收藏|ISFAVORITES|TEXT|&nbsp;|
|frequency|FREQUENCY|SSCODELIST|&nbsp;|
|用例标题|TITLE|TEXT|&nbsp;|
|最后修改者|LASTEDITEDBY|TEXT|&nbsp;|
|由谁评审|REVIEWEDBY|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|转bug数|TOBUGCNT|INT|&nbsp;|
|指派给|ASSIGNEDTO|SSCODELIST|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|评审时间|REVIEWEDDATE|DATE|&nbsp;|
|优先级|PRI|NSCODELIST|&nbsp;|
|适用阶段|STAGE|SMCODELIST|&nbsp;|
|scriptLocation|SCRIPTLOCATION|TEXT|&nbsp;应该是预留后期使用的字段|
|用例状态|STATUS1|SSCODELIST|&nbsp;|
|执行时间|LASTRUNDATE|DATETIME|&nbsp;|
|关键词|KEYWORDS|TEXT|&nbsp;|
|scriptStatus|SCRIPTSTATUS|TEXT|&nbsp;应该是预留后期使用的字段|
|工具/框架|FRAME|SSCODELIST|&nbsp;|
|测试用例结果|LASTRUNRESULT1|SSCODELIST|&nbsp;|
|用例步骤数|STEPCNT|INT|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|用例编号|ID|ACID|&nbsp;|
|前置条件|PRECONDITION|LONGTEXT|&nbsp;|
|执行人|LASTRUNNER|TEXT|&nbsp;|
|用例库|LIBNAME|PICKUPTEXT|&nbsp;|
|来源用例版本|FROMCASEVERSION|PICKUPDATA|&nbsp;|
|需求名称|STORYNAME|PICKUPTEXT|&nbsp;|
|模块名称|MODULENAME|PICKUPTEXT|&nbsp;|
|需求版本|STORYVERSION|PICKUPDATA|&nbsp;|
|产品名称|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|来源用例|FROMCASEID|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|
|来源Bug|FROMBUG|PICKUP|&nbsp;|
|相关需求|STORY|PICKUP|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|
|所属库|LIB|PICKUP|&nbsp;|
|所属模块|MODULE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|修改日期|默认规则|默认规则|
|scriptedDate|默认规则|默认规则|
|标题颜色|默认规则|内容长度必须小于等于[7]|
|path|默认规则|默认规则|
|创建日期|默认规则|默认规则|
|结果|默认规则|内容长度必须小于等于[30]|
|模块名称|默认规则|内容长度必须小于等于[200]|
|相关用例|默认规则|内容长度必须小于等于[255]|
|用例步骤集合|默认规则|内容长度必须小于等于[1048576]|
|属性|默认规则|内容长度必须小于等于[100]|
|排序|默认规则|默认规则|
|howRun|默认规则|内容长度必须小于等于[30]|
|测试结果数|默认规则|默认规则|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|用例版本|默认规则|默认规则|
|scriptedBy|默认规则|内容长度必须小于等于[30]|
|由谁创建|默认规则|内容长度必须小于等于[30]|
|用例类型|默认规则|内容长度必须小于等于[30]|
|测试失败数|默认规则|默认规则|
|用例状态|默认规则|内容长度必须小于等于[30]|
|备注|默认规则|内容长度必须小于等于[1048576]|
|auto|默认规则|内容长度必须小于等于[10]|
|是否收藏|默认规则|内容长度必须小于等于[200]|
|frequency|默认规则|内容长度必须小于等于[1]|
|用例标题|默认规则|内容长度必须小于等于[255]|
|最后修改者|默认规则|内容长度必须小于等于[30]|
|由谁评审|默认规则|内容长度必须小于等于[255]|
|附件|默认规则|内容长度必须小于等于[1000]|
|转bug数|默认规则|默认规则|
|指派给|默认规则|内容长度必须小于等于[200]|
|已删除|默认规则|内容长度必须小于等于[1]|
|评审时间|默认规则|默认规则|
|优先级|默认规则|默认规则|
|适用阶段|默认规则|内容长度必须小于等于[255]|
|scriptLocation|默认规则|内容长度必须小于等于[255]|
|用例状态|默认规则|内容长度必须小于等于[200]|
|执行时间|默认规则|默认规则|
|关键词|默认规则|内容长度必须小于等于[255]|
|scriptStatus|默认规则|内容长度必须小于等于[30]|
|工具/框架|默认规则|内容长度必须小于等于[10]|
|测试用例结果|默认规则|内容长度必须小于等于[200]|
|用例步骤数|默认规则|默认规则|
|子状态|默认规则|内容长度必须小于等于[30]|
|用例编号|默认规则|默认规则|
|前置条件|默认规则|内容长度必须小于等于[65535]|
|执行人|默认规则|内容长度必须小于等于[30]|
|用例库|默认规则|内容长度必须小于等于[255]|
|来源用例版本|默认规则|默认规则|
|需求名称|默认规则|内容长度必须小于等于[255]|
|模块名称|默认规则|内容长度必须小于等于[60]|
|需求版本|默认规则|默认规则|
|产品名称|默认规则|内容长度必须小于等于[90]|
|来源用例|默认规则|默认规则|
|平台/分支|默认规则|默认规则|
|来源Bug|默认规则|默认规则|
|相关需求|默认规则|默认规则|
|所属产品|默认规则|默认规则|
|所属库|默认规则|默认规则|
|所属模块|默认规则|默认规则|

## 状态控制

|用例状态||测试用例结果||是否收藏|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   || --------   | ------------|------------|------------|------------|
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_DELETE_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_RUN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_DELETE_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_CASERUN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_RESULT_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASERUN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_DELETE_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_EDIT_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_DELETE_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_RESULT_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_EDIT_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_RUN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_RUN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_EDIT_BUT<br>SRFUR__CASE_RUN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_UNLINK_BUT<br>SRFUR__CASE_CASERESULT_BUT<br>SRFUR__CASE_RESULT_BUT<br>SRFUR__CASE_CASERUN_BUT<br>SRFUR__CASE_CASETOBUG_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_RUN_BUT<br>SRFUR__CASE_EDIT_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_FAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CASECOF_BUT<br>SRFUR__CASE_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__CASE_TOBUG_BUT<br>SRFUR__CASE_CONFIRM_BUT<br>SRFUR__CASE_NFAVOR_BUT<br>SRFUR__CASE_CASECOF_BUT<br> |

用例状态

| 状态        |    状态值   |
| --------   |------------|
|未开始|wait|
|正常|normal|
|被阻塞|blocked|
|研究中|investigate|
|已完成|done|
|需求变更|storychange|
|原用例更新|casechange|
测试用例结果

| 状态        |    状态值   |
| --------   |------------|
|忽略|n/a|
|通过|pass|
|失败|fail|
|阻塞|blocked|
是否收藏


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|CreateTemp|内置方法|&nbsp;|
|CreateTempMajor|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|UpdateTemp|内置方法|&nbsp;|
|UpdateTempMajor|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|RemoveTemp|内置方法|&nbsp;|
|RemoveTempMajor|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetTemp|内置方法|&nbsp;|
|GetTempMajor|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|GetDraftTemp|内置方法|&nbsp;|
|GetDraftTempMajor|内置方法|&nbsp;|
|建用例|用户自定义|&nbsp;|
|行为|实体处理逻辑|&nbsp;用例收藏|
|CaseNFavorite|实体处理逻辑|&nbsp;用例取消收藏|
|CheckKey|内置方法|&nbsp;|
|确认用例变更|用户自定义|&nbsp;|
|确认需求变更|用户自定义|&nbsp;|
|根据测试单获取或者状态|用户自定义|&nbsp;|
|获取测试单执行结果|用户自定义|&nbsp;|
|测试单关联测试用例|用户自定义|&nbsp;|
|移动端关联需求|用户自定义|&nbsp;|
|执行测试|用户自定义|&nbsp;|
|runCases|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|
|执行测试|用户自定义|&nbsp;|
|testRunCases|实体处理逻辑|&nbsp;|
|套件关联|用户自定义|&nbsp;|
|移除用例|用户自定义|&nbsp;|
|unlinkCases|实体处理逻辑|&nbsp;|
|移除用例|用户自定义|&nbsp;|
|unlinkSuiteCases|实体处理逻辑|&nbsp;|

## 处理逻辑
* 用例取消收藏 (CaseNFavorite)
  
   

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 用例取消收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|用例取消收藏 |
<center>用例取消收藏</center>
* 根据用例标识和版本获取用例步骤 (GetCaseStepByIdVersion)
  
   

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 获取用例步骤
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取用例步骤 |
<center>根据用例标识和版本获取用例步骤</center>
* 用例收藏 (caseFavorite)
  
   hua-测试收藏功能

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 准备参数
测试用例 -> 收藏: Create
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|Create |
<center>用例收藏</center>
* runCases (runCases)
  
   

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 准备参数
测试用例 -> 测试用例: 执行测试
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|执行测试 |
<center>runCases</center>
* testRunCases (testRunCases)
  
   

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 获取数据
测试用例 -> 测试用例: 执行测试
测试用例 -> 测试用例: 重置ID
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取数据 |
|2|执行测试 |
|3|重置ID |
<center>testRunCases</center>
* unlinkCases (unlinkCases)
  
   

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 获取数据
测试用例 -> 测试用例: 重置ID
测试用例 -> 测试用例: 处理移除接口
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取数据 |
|2|重置ID |
|3|处理移除接口 |
<center>unlinkCases</center>
* unlinkSuiteCases (unlinkSuiteCases)
  
   

{% plantuml %}
hide footbox

测试用例 -> 测试用例: 准备参数
测试用例 -> 测试用例: 移除用例
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|移除用例 |
<center>unlinkSuiteCases</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BatchNew|批量新建用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_BatchNew))|否|&nbsp;|
|CurOpenedCase|累计创建的用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_CurOpenedCase))|否|&nbsp;|
|CurSuite|套件关联用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_CurSuite))|否|&nbsp;|
|CurTestTask|测试单关联用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_CurTestTask))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Case_Default))|是|&nbsp;|
|ESBulk|ES批量的导入([MYSQL5](../../appendix/query_MYSQL5.md#Case_ESBulk))|否|&nbsp;|
|ModuleRePortCase|测试报告关联-按模块([MYSQL5](../../appendix/query_MYSQL5.md#Case_ModuleRePortCase))|否|&nbsp;|
|ModuleRePortCaseEntry|测试报告关联-按模块-条目([MYSQL5](../../appendix/query_MYSQL5.md#Case_ModuleRePortCaseEntry))|否|&nbsp;|
|ModuleRePortCase_Project|项目报告关联-按模块([MYSQL5](../../appendix/query_MYSQL5.md#Case_ModuleRePortCase_Project))|否|&nbsp;|
|MyFavorite|用例我得收藏([MYSQL5](../../appendix/query_MYSQL5.md#Case_MyFavorite))|否|&nbsp;|
|NotCurTestSuite|套件关联用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_NotCurTestSuite))|否|&nbsp;|
|NotCurTestTask|测试单关联用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_NotCurTestTask))|否|&nbsp;|
|NotCurTestTaskProject|测试单关联用例（项目关联）([MYSQL5](../../appendix/query_MYSQL5.md#Case_NotCurTestTaskProject))|否|&nbsp;|
|RePortCase|测试报告关联用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_RePortCase))|否|&nbsp;|
|RePortCaseEntry|测试报告关联用例-条目([MYSQL5](../../appendix/query_MYSQL5.md#Case_RePortCaseEntry))|否|&nbsp;|
|RePortCase_Project|项目报告关联用例-关联用例([MYSQL5](../../appendix/query_MYSQL5.md#Case_RePortCase_Project))|否|&nbsp;|
|RunERRePortCase|测试报告关联-执行人([MYSQL5](../../appendix/query_MYSQL5.md#Case_RunERRePortCase))|否|&nbsp;|
|RunERRePortCaseEntry|测试报告关联-执行人-条目([MYSQL5](../../appendix/query_MYSQL5.md#Case_RunERRePortCaseEntry))|否|&nbsp;|
|RunERRePortCase_Project|项目报告关联-执行人([MYSQL5](../../appendix/query_MYSQL5.md#Case_RunERRePortCase_Project))|否|&nbsp;|
|RunRePortCase|测试报告关联--执行结果([MYSQL5](../../appendix/query_MYSQL5.md#Case_RunRePortCase))|否|&nbsp;|
|RunRePortCaseEntry|测试报告关联--执行结果条目([MYSQL5](../../appendix/query_MYSQL5.md#Case_RunRePortCaseEntry))|否|&nbsp;|
|RunRePortCase_Project|项目报告关联-执行结果([MYSQL5](../../appendix/query_MYSQL5.md#Case_RunRePortCase_Project))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Case_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BatchNew|批量新建用例|BatchNew|否|&nbsp;|
|CurOpenedCase|累计创建的用例|CurOpenedCase|否|&nbsp;|
|CurSuite|套件关联用例|CurSuite|否|&nbsp;|
|CurTestTask|测试单关联用例|CurTestTask|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ESBulk|ES批量的导入|ESBulk|否|&nbsp;|
|ModuleRePortCase|测试报告关联用例|ModuleRePortCase|否|&nbsp;|
|ModuleRePortCaseEntry|测试报告关联-按模块-条目|ModuleRePortCaseEntry|否|&nbsp;|
|ModuleRePortCase_Project|项目报告关联-按模块|ModuleRePortCase_Project|否|&nbsp;|
|MyFavorite|我的收藏|MyFavorite|否|&nbsp;|
|NotCurTestSuite|套件关联用例|NotCurTestSuite|否|&nbsp;|
|NotCurTestTask|测试单关联用例|NotCurTestTask|否|&nbsp;|
|NotCurTestTaskProject|测试单关联用例（项目关联）|NotCurTestTaskProject|否|&nbsp;|
|RePortCase|测试报告关联用例|RePortCase|否|&nbsp;|
|RePortCaseEntry|测试报告关联用例-条目|RePortCaseEntry|否|&nbsp;|
|RePortCase_Project|项目报告关联用例-关联用例|RePortCase_Project|否|&nbsp;|
|RunERRePortCase|测试报告关联-执行人|RunERRePortCase|否|&nbsp;|
|RunERRePortCaseEntry|测试报告关联-执行人-条目|RunERRePortCaseEntry|否|&nbsp;|
|RunERRePortCase_Project|项目报告关联-执行人|RunERRePortCase_Project|否|&nbsp;|
|RunRePortCase|测试报告关联用例|RunRePortCase|否|&nbsp;|
|RunRePortCaseEntry|测试报告关联--执行结果条目|RunRePortCaseEntry|否|&nbsp;|
|RunRePortCase_Project|项目报告关联-执行结果|RunRePortCase_Project|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|标题颜色(COLOR)|EQ|
|结果(LASTRUNRESULT)|EQ|
|由谁创建(OPENEDBY)|EQ|
|用例类型(TYPE)|EQ|
|用例状态(STATUS)|EQ|
|是否收藏(ISFAVORITES)|EQ|
|frequency(FREQUENCY)|EQ|
|用例标题(TITLE)|LIKE|
|指派给(ASSIGNEDTO)|EQ|
|优先级(PRI)|EQ|
|用例状态(STATUS1)|EQ|
|工具/框架(FRAME)|EQ|
|测试用例结果(LASTRUNRESULT1)|EQ|
|用例编号(ID)|EQ|
|执行人(LASTRUNNER)|EQ|
|用例库(LIBNAME)|EQ|
|用例库(LIBNAME)|LIKE|
|需求名称(STORYNAME)|EQ|
|需求名称(STORYNAME)|LIKE|
|模块名称(MODULENAME)|EQ|
|模块名称(MODULENAME)|LIKE|
|产品名称(PRODUCTNAME)|EQ|
|产品名称(PRODUCTNAME)|LIKE|
|来源用例(FROMCASEID)|EQ|
|平台/分支(BRANCH)|EQ|
|来源Bug(FROMBUG)|EQ|
|相关需求(STORY)|EQ|
|所属产品(PRODUCT)|EQ|
|所属库(LIB)|EQ|
|所属模块(MODULE)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|id|P|用例标题|用例类型|创建|执行人|执行时间|结果|状态|状态|B|R|S|测试用例结果|是否收藏|标题颜色|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - |
