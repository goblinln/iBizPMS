# 项目(ZT_PROJECT)

  

## 关系
{% plantuml %}
项目 *-- 项目日报 
项目 *-- 项目月报 
项目 *-- 项目周报 
项目 *-- Bug统计 
项目 *-- 员工负载表 
项目 *-- 任务模块 
项目 *-- 项目团队 
项目 *-- 项目其他活动 
项目 *-- 系统日志 
项目 *-- Bug 
项目 *-- 版本 
项目 *-- burn 
项目 *-- 文档库 
项目 *-- 文档 
项目 *-- 项目产品 
项目 *-- 项目中需要做的需求 
项目 *-- 任务 
项目 *-- 测试报告 
项目 *-- 测试版本 
项目 *-- 项目 
项目 *-- 项目 
索引检索 <|-- 项目 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|当前系统版本|OPENEDVERSION|TEXT|&nbsp;|
|开始时间|BEGIN|DATE|&nbsp;|
|可用工时/天|HOURS|DECIMAL|&nbsp;|
|项目立项信息|PMSEEPROJECTINFO|LONGTEXT|&nbsp;|
|访问控制|ACL|SSCODELIST|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|任务消耗总工时|TOTALCONSUMED|FLOAT|&nbsp;|
|关联产品|PRODUCTS|TEXT|&nbsp;|
|已完成任务数|YCOMPLETETASKCNT|INT|&nbsp;|
|关联计划|PLANS|TEXT|&nbsp;|
|项目描述|DESC|LONGTEXT|&nbsp;|
|临时任务数|TEMPTASKCNT|INT|&nbsp;|
|文档数量|DOCLIBCNT|INT|&nbsp;|
|我完成任务数|MYCOMPLETETASKCNT|INT|&nbsp;|
|是否置顶|ISTOP|INT|&nbsp;|
|未完成任务数|UNCOMPLETETASKCNT|INT|&nbsp;|
|团队成员总数|TEAMCNT|INT|&nbsp;|
|项目负责人|PM|SSCODELIST|&nbsp;|
|选择部门|DEPT|SSCODELIST|&nbsp;|
|项目编号|ID|ACID|&nbsp;|
|项目名称|NAME|TEXT|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|角色|ROLE|TEXT|&nbsp;|
|项目排序|ORDER|INT|&nbsp;|
|Bug总数|BUGCNT|INT|&nbsp;|
|发布负责人|RD|SSCODELIST|&nbsp;|
|复制团队|MANAGEMEMBERS|SSCODELIST|&nbsp;|
|进行中任务数|YSTARTASKCNT|INT|&nbsp;|
|分组白名单|WHITELIST|LONGTEXT|&nbsp;|
|移动端图片|MOBIMAGE|TEXT|&nbsp;|
|总工时|TOTALWH|INT|&nbsp;|
|项目团队成员|PROJECTTEAMS|ONE2MANYDATA|&nbsp;|
|可用工时|TOTALHOURS|DECIMAL|&nbsp;|
|优先级|PRI|SSCODELIST|&nbsp;|
|结束日期|END|DATE|&nbsp;|
|取消日期|CANCELEDDATE|DATETIME|&nbsp;|
|计划任务数|PLANTASKCNT|INT|&nbsp;|
|加盟日|JOIN|DATE|&nbsp;|
|任务最初预计总工时|TOTALESTIMATE|FLOAT|&nbsp;|
|部门标识|MDEPTID|TEXT|&nbsp;|
|任务预计剩余总工时|TOTALLEFT|FLOAT|&nbsp;|
|关联数据数组|SRFARRAY|TEXT|&nbsp;|
|项目代号|CODE|TEXT|&nbsp;|
|时间段|PERIOD|SSCODELIST|&nbsp;|
|关闭任务数|CLOSETASKCNT|INT|&nbsp;|
|关联产品平台集合|BRANCHS|TEXT|&nbsp;|
|catID|CATID|INT|&nbsp;|
|组织标识|ORGID|TEXT|&nbsp;|
|未开始任务数|UNSTARTTASKCNT|INT|&nbsp;|
|项目团队相关成员|ACCOUNTS|TEXT|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|statge|STATGE|SSCODELIST|&nbsp;|
|取消任务数|CANCELTASKCNT|INT|&nbsp;|
|任务总数|TASKCNT|INT|&nbsp;|
|所有任务数|ALLTASKCNT|INT|&nbsp;|
|支持项目汇报|SUPPROREPORT|SSCODELIST|&nbsp;|
|由谁取消|CANCELEDBY|TEXT|&nbsp;|
|isCat|ISCAT|SSCODELIST|&nbsp;暂时还不知道做什么用的，代码也没搜到相关含义以及处理（除了赋值默认值）|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|未关闭任务数|UNCLOSETASKCNT|INT|&nbsp;|
|需求变更数|STORYCHANGECNT|INT|&nbsp;|
|由谁关闭|CLOSEDBY|TEXT|&nbsp;|
|项目类型|TYPE|SSCODELIST|&nbsp;|
|版本总数|BUILDCNT|INT|&nbsp;|
|项目团队成员|ACCOUNT|SSCODELIST|&nbsp;|
|产品负责人|PO|SSCODELIST|&nbsp;|
|指派给我任务数|ASSTOMYTASKCNT|INT|&nbsp;|
|项目排序|ORDER1|INT|&nbsp;|
|项目状态|STATUS|SSCODELIST|&nbsp;|
|更多任务数|MORETASKCNT|INT|&nbsp;|
|可用工作日|DAYS|INT|&nbsp;|
|周期任务数|CYCLETASKCNT|INT|&nbsp;|
|团队名称|TEAM|TEXT|&nbsp;不是外键文本，仅仅是团队名称|
|关闭日期|CLOSEDDATE|DATETIME|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|由谁创建|OPENEDBY|TEXT|&nbsp;|
|需求总数|STORYCNT|INT|&nbsp;|
|测试负责人|QD|SSCODELIST|&nbsp;|
|parent|PARENTNAME|PICKUPTEXT|&nbsp;|
|父项目|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|当前系统版本|默认规则|内容长度必须小于等于[20]|
|开始时间|项目周期不能为空|『开始日期』不能为空。|
|开始时间|默认规则|默认规则|
|可用工时/天|默认规则|默认规则|
|项目立项信息|默认规则|内容长度必须小于等于[1048576]|
|访问控制|默认规则|内容长度必须小于等于[7]|
|已删除|默认规则|内容长度必须小于等于[1]|
|任务消耗总工时|默认规则|默认规则|
|关联产品|默认规则|内容长度必须小于等于[100]|
|已完成任务数|默认规则|默认规则|
|关联计划|默认规则|内容长度必须小于等于[100]|
|项目描述|默认规则|内容长度必须小于等于[65535]|
|临时任务数|默认规则|默认规则|
|文档数量|默认规则|默认规则|
|我完成任务数|默认规则|默认规则|
|是否置顶|默认规则|默认规则|
|未完成任务数|默认规则|默认规则|
|团队成员总数|默认规则|默认规则|
|项目负责人|默认规则|内容长度必须小于等于[30]|
|选择部门|默认规则|内容长度必须小于等于[200]|
|项目编号|默认规则|默认规则|
|项目名称|默认规则|内容长度必须小于等于[90]|
|子状态|默认规则|内容长度必须小于等于[30]|
|角色|默认规则|内容长度必须小于等于[100]|
|项目排序|默认规则|默认规则|
|Bug总数|默认规则|默认规则|
|发布负责人|默认规则|内容长度必须小于等于[30]|
|复制团队|默认规则|内容长度必须小于等于[200]|
|进行中任务数|默认规则|默认规则|
|分组白名单|默认规则|内容长度必须小于等于[65535]|
|移动端图片|默认规则|内容长度必须小于等于[4000]|
|总工时|默认规则|默认规则|
|项目团队成员|默认规则|内容长度必须小于等于[1048576]|
|可用工时|默认规则|默认规则|
|优先级|默认规则|内容长度必须小于等于[1]|
|结束日期|『截止日期』不能为空|『截止日期』不能为空。|
|结束日期|默认规则|默认规则|
|取消日期|默认规则|默认规则|
|计划任务数|默认规则|默认规则|
|加盟日|默认规则|默认规则|
|任务最初预计总工时|默认规则|默认规则|
|部门标识|默认规则|内容长度必须小于等于[100]|
|任务预计剩余总工时|默认规则|默认规则|
|关联数据数组|默认规则|内容长度必须小于等于[100]|
|项目代号|默认规则|内容长度必须小于等于[45]|
|时间段|默认规则|内容长度必须小于等于[200]|
|关闭任务数|默认规则|默认规则|
|关联产品平台集合|默认规则|内容长度必须小于等于[100]|
|catID|默认规则|默认规则|
|组织标识|默认规则|内容长度必须小于等于[100]|
|未开始任务数|默认规则|默认规则|
|项目团队相关成员|默认规则|内容长度必须小于等于[100]|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|statge|默认规则|内容长度必须小于等于[1]|
|取消任务数|默认规则|默认规则|
|任务总数|默认规则|默认规则|
|所有任务数|默认规则|默认规则|
|支持项目汇报|默认规则|内容长度必须小于等于[60]|
|由谁取消|默认规则|内容长度必须小于等于[30]|
|isCat|默认规则|内容长度必须小于等于[1]|
|创建日期|默认规则|默认规则|
|未关闭任务数|默认规则|默认规则|
|需求变更数|默认规则|默认规则|
|由谁关闭|默认规则|内容长度必须小于等于[30]|
|项目类型|默认规则|内容长度必须小于等于[20]|
|版本总数|默认规则|默认规则|
|项目团队成员|默认规则|内容长度必须小于等于[200]|
|产品负责人|默认规则|内容长度必须小于等于[30]|
|指派给我任务数|默认规则|默认规则|
|项目排序|默认规则|默认规则|
|项目状态|默认规则|内容长度必须小于等于[10]|
|更多任务数|默认规则|默认规则|
|可用工作日|默认规则|默认规则|
|周期任务数|默认规则|默认规则|
|团队名称|默认规则|内容长度必须小于等于[90]|
|关闭日期|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[1048576]|
|由谁创建|默认规则|内容长度必须小于等于[30]|
|需求总数|默认规则|默认规则|
|测试负责人|默认规则|内容长度必须小于等于[30]|
|parent|默认规则|内容长度必须小于等于[90]|
|父项目|默认规则|默认规则|

## 状态控制

|项目状态||是否置顶|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   | ------------|------------|------------|------------|
|Value||Value2| 允许| 激活<br>Update<br>Save<br>Get<br>Remove<br> | 不允许 | TOP<br>SRFUR__PROJ_START_BUT<br>SRFUR__PROJ_SUSPEND_BUT<br>SRFUR__PROJ_CLOSED_BUT<br>SRFUR__PROJ_DELAY_BUT<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__PROJ_SUSPEND_BUT<br>SRFUR__PROJ_START_BUT<br>SRFUR__PROJ_CLOSED_BUT<br>NOTOP<br>SRFUR__PROJ_DELAY_BUT<br> |
|Value||Value2| 允许| 挂起<br>延期<br>Update<br>Save<br>Get<br>关闭<br>Remove<br> | 不允许 | TOP<br>SRFUR__PROJ_START_BUT<br>SRFUR__PROJ_ACTIVATION_BUT<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__PROJ_ACTIVATION_BUT<br>NOTOP<br>SRFUR__PROJ_START_BUT<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__PROJ_SUSPEND_BUT<br>SRFUR__PROJ_START_BUT<br>TOP<br>SRFUR__PROJ_DELAY_BUT<br> |
|Value||Value2| 允许| 激活<br>Remove<br>Save<br>关闭<br>Get<br> | 不允许 | SRFUR__PROJ_START_BUT<br>NOTOP<br>SRFUR__PROJ_SUSPEND_BUT<br>SRFUR__PROJ_DELAY_BUT<br> |
|Value||Value2| 允许| 延期<br>Get<br>关闭<br>开始<br>Update<br>挂起<br>Save<br>Remove<br> | 不允许 | TOP<br>SRFUR__PROJ_ACTIVATION_BUT<br> |
|Value||Value2| 允许|  | 不允许 | NOTOP<br>SRFUR__PROJ_ACTIVATION_BUT<br> |

项目状态

| 状态        |    状态值   |
| --------   |------------|
|未开始|wait|
|进行中|doing|
|已挂起|suspended|
|已关闭|closed|
是否置顶


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
|激活|用户自定义|&nbsp;|
|批量解除关联需求|用户自定义|&nbsp;|
|取消置顶|实体处理逻辑|&nbsp;项目置顶操作|
|CheckKey|内置方法|&nbsp;|
|关闭|用户自定义|&nbsp;|
|关联需求|用户自定义|&nbsp;|
|团队管理|用户自定义|&nbsp;|
|移动端项目计数器|实体处理逻辑|&nbsp;|
|项目立项任务快速分组计数器|用户自定义|&nbsp;|
|项目立项待办任务快速分组计数器|用户自定义|&nbsp;|
|项目任务快速分组计数器|实体处理逻辑|&nbsp;|
|置顶|实体处理逻辑|&nbsp;项目置顶操作|
|延期|用户自定义|&nbsp;|
|退出|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|开始|用户自定义|&nbsp;|
|挂起|用户自定义|&nbsp;|
|移除成员|用户自定义|&nbsp;|
|解除关联需求|用户自定义|&nbsp;|
|根据结束日期更新可用工作日|用户自定义|&nbsp;|
|排序|用户自定义|&nbsp;|
|更新项目周期及可用工作日|用户自定义|&nbsp;|

## 处理逻辑
* 取消置顶 (CancelProjectTop)
  
   

{% plantuml %}
hide footbox

项目 -> 项目: 删除置顶
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|删除置顶 |
<center>取消置顶</center>
* 移动端项目计数器 (MobProjectCount)
  
   

{% plantuml %}
hide footbox

项目 -> 项目: 团队成员
项目 -> 项目: 获取Bug数
项目 -> 项目: 获取需求数
项目 -> 项目: 获取任务数
项目 -> 项目: 版本数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|团队成员 |
|2|获取Bug数 |
|2|开始 | 
|3|获取需求数 |
|4|获取任务数 |
|5|版本数 |
<center>移动端项目计数器</center>
* 项目立项全部任务快速分组计数器 (PmsEeProjectAllTaskCount)
  
   

{% plantuml %}
hide footbox

项目 -> 项目: 获取计划任务
项目 -> 项目: 获取周期任务
项目 -> 项目: 获取临时任务
项目 -> 项目: 获取所有任务
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取计划任务 |
|2|获取周期任务 |
|2|开始 | 
|3|获取临时任务 |
|4|获取所有任务 |
<center>项目立项全部任务快速分组计数器</center>
* 项目立项待办任务快速分组计数器 (PmsEeProjectTodoTaskCount)
  
   

{% plantuml %}
hide footbox

项目 -> 项目: 获取计划任务
项目 -> 项目: 获取所有任务
项目 -> 项目: 获取周期任务
项目 -> 项目: 获取临时任务
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取计划任务 |
|2|获取所有任务 |
|3|获取周期任务 |
|3|开始 | 
|4|获取临时任务 |
<center>项目立项待办任务快速分组计数器</center>
* 项目任务快速分组计数器 (ProjectTaskQCnt)
  
   项目任务快速分组计数器

{% plantuml %}
hide footbox

项目 -> 项目: 所有任务
项目 -> 项目: 未关闭的任务数
项目 -> 项目: 需求变更数
项目 -> 项目: 已取消任务
项目 -> 项目: 已完成的任务数
项目 -> 项目: 指派给我的任务数
项目 -> 项目: 未关闭任务数
项目 -> 项目: 未开始的任务数
项目 -> 项目: 已关闭的任务数
项目 -> 项目: 我完成的任务数
项目 -> 项目: 进行中的任务
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|所有任务 |
|2|未关闭的任务数 |
|3|需求变更数 |
|4|已取消任务 |
|5|已完成的任务数 |
|6|指派给我的任务数 |
|6|开始 | 
|7|未关闭任务数 |
|8|未开始的任务数 |
|9|已关闭的任务数 |
|10|我完成的任务数 |
|11|进行中的任务 |
<center>项目任务快速分组计数器</center>
* 置顶 (ProjectTop)
  
   

{% plantuml %}
hide footbox

项目 -> 置顶: Save
项目 -> 项目: 设置参数
项目 -> 项目: 获取置顶最大排序值
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|Save |
|2|设置参数 |
|2|开始 | 
|3|获取置顶最大排序值 |
<center>置顶</center>
* 获取项目产品计划 (getProjectProductPlan)
  
   

{% plantuml %}
hide footbox

项目 -> 项目: 获取SrfArray
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取SrfArray |
<center>获取项目产品计划</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|AllProject|所有项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_AllProject))|否|&nbsp;|
|BugSelectableProjectList|Bug表单中可选的项目列表([MYSQL5](../../appendix/query_MYSQL5.md#Project_BugSelectableProjectList))|否|&nbsp;Bug表单中可选的项目列表、与产品选择做关联，使用网页上下文传递参数（产品标识）。|
|CurPersonalProjects|当前人的所有项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_CurPersonalProjects))|否|&nbsp;|
|CurPlanProject|当前计划项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_CurPlanProject))|否|&nbsp;|
|CurProduct|当前项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_CurProduct))|否|&nbsp;|
|CurUser|当前用户项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_CurUser))|否|&nbsp;|
|CurUserSa|当前用户项目（企业版）([MYSQL5](../../appendix/query_MYSQL5.md#Project_CurUserSa))|否|&nbsp;|
|CurUserTodo|当前用户待办项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_CurUserTodo))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Project_Default))|是|&nbsp;|
|ESBulk|ES批量的导入([MYSQL5](../../appendix/query_MYSQL5.md#Project_ESBulk))|否|&nbsp;|
|InvolvedProject|参与项目(年度总结)([MYSQL5](../../appendix/query_MYSQL5.md#Project_InvolvedProject))|否|&nbsp;|
|InvolvedProjectStoryTaskBug|参与项目完成需求任务bug([MYSQL5](../../appendix/query_MYSQL5.md#Project_InvolvedProjectStoryTaskBug))|否|&nbsp;|
|MyManageProject|我管辖的项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_MyManageProject))|否|&nbsp;|
|MyProject|我的项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_MyProject))|否|&nbsp;|
|ProjectTeam|项目团队([MYSQL5](../../appendix/query_MYSQL5.md#Project_ProjectTeam))|否|&nbsp;|
|StoryProject|需求影响项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_StoryProject))|否|&nbsp;|
|UnDoneProject|未完成项目([MYSQL5](../../appendix/query_MYSQL5.md#Project_UnDoneProject))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Project_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AllProject|所有项目|AllProject|否|&nbsp;|
|BugProject|BugProject|BugSelectableProjectList|否|&nbsp;|
|CurPersonalProjects|当前人的所有项目|CurPersonalProjects|否|&nbsp;|
|CurPlanProject|当前计划项目|CurPlanProject|否|&nbsp;|
|CurProduct|当前项目|CurProduct|否|&nbsp;|
|CurUser|当前用户项目|CurUser|否|&nbsp;|
|CurUserSa|当前用户项目（企业版）|CurUserSa|否|&nbsp;|
|CurUserTodo|当前用户待办项目|CurUserTodo|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ESBulk|ES批量的导入|ESBulk|否|&nbsp;|
|InvolvedProject|参与项目(年度总结)|InvolvedProject|否|&nbsp;|
|InvolvedProject_StoryTaskBug|参与项目完成需求任务bug|InvolvedProjectStoryTaskBug|否|&nbsp;|
|MyManageProject|我管辖的项目|MyManageProject|否|&nbsp;|
|MyProject|我的项目|MyProject|否|&nbsp;|
|ProjectTeam|项目团队|ProjectTeam|否|&nbsp;|
|StoryProject|需求影响项目|StoryProject|否|&nbsp;|
|UnDoneProject|未完成项目|UnDoneProject|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|访问控制(ACL)|EQ|
|是否置顶(ISTOP)|EQ|
|项目负责人(PM)|EQ|
|选择部门(DEPT)|EQ|
|项目编号(ID)|EQ|
|项目编号(ID)|IN|
|项目编号(ID)|NOTEQ|
|项目名称(NAME)|EQ|
|项目名称(NAME)|LIKE|
|发布负责人(RD)|EQ|
|复制团队(MANAGEMEMBERS)|EQ|
|优先级(PRI)|EQ|
|时间段(PERIOD)|EQ|
|statge(STATGE)|EQ|
|支持项目汇报(SUPPROREPORT)|EQ|
|isCat(ISCAT)|EQ|
|项目类型(TYPE)|EQ|
|项目团队成员(ACCOUNT)|EQ|
|产品负责人(PO)|EQ|
|项目状态(STATUS)|EQ|
|项目状态(STATUS)|NOTEQ|
|测试负责人(QD)|EQ|
|parent(PARENTNAME)|EQ|
|parent(PARENTNAME)|LIKE|
|父项目(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
