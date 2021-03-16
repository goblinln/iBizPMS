# Bug(ZT_BUG)

  

## 关系
{% plantuml %}
Bug *-- Bug 
Bug *-- 测试用例 
Bug *-- 需求 
Bug *-- 需求 
Bug *-- 任务 
产品的分支和平台信息 *-- Bug 
Bug *-- Bug 
测试用例 *-- Bug 
entry *-- Bug 
模块 *-- Bug 
产品计划 *-- Bug 
产品 *-- Bug 
项目 *-- Bug 
repo *-- Bug 
需求 *-- Bug 
需求 *-- Bug 
任务 *-- Bug 
任务 *-- Bug 
测试版本 *-- Bug 
索引检索 <|-- Bug 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|严重程度|SEVERITY|NSCODELIST|&nbsp;|
|需求版本|STORYVERSION|INT|&nbsp;更新业务自动加1|
|版本名称|BUILDNAME|TEXT|&nbsp;|
|相关Bug|LINKBUG|TEXT|&nbsp;|
|激活日期|ACTIVATEDDATE|DATE|&nbsp;|
|过期天数|OVERDUEBUGS|INT|&nbsp;|
|创建版本|CREATEBUILD|INT|&nbsp;|
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|解决方案|RESOLUTION|SSCODELIST|&nbsp;|
|修改日期|LASTEDITEDDATE|DATETIME|&nbsp;|
|移动端图片|MOBIMAGE|TEXT|&nbsp;|
|result|RESULT|INT|&nbsp;没有任何处理的代码|
|关键词|KEYWORDS|TEXT|&nbsp;|
|是否收藏|ISFAVORITES|TEXT|&nbsp;|
|模块名称|MODULENAME1|TEXT|&nbsp;|
|由谁关闭|CLOSEDBY|SSCODELIST|&nbsp;|
|浏览器|BROWSER|SSCODELIST|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|重现步骤|STEPS|LONGTEXT|&nbsp;|
|v2|V2|TEXT|&nbsp;|
|是否确认|CONFIRMED|NSCODELIST|&nbsp;|
|联系人|MAILTOCONACT|TEXT|&nbsp;|
|由谁创建|OPENEDBY|SSCODELIST|&nbsp;|
|激活次数|ACTIVATEDCOUNT|INT|&nbsp;|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|关闭日期|CLOSEDDATE|DATETIME|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|指派日期|ASSIGNEDDATE|DATETIME|&nbsp;|
|截止日期|DEADLINE|DATE|&nbsp;|
|标题颜色|COLOR|SSCODELIST|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|解决日期|RESOLVEDDATE|DATETIME|&nbsp;|
|Bug类型|TYPE|SSCODELIST|&nbsp;|
|Bug状态|STATUS|SSCODELIST|&nbsp;|
|影响版本|OPENEDBUILD|SMCODELIST|&nbsp;与build外键，且存在默认主干版本（trunk），逗号分开|
|延期解决|DELAYRESOLVE|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|抄送给|MAILTOPK|SMCODELIST|&nbsp;|
|v1|V1|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|lines|LINES|TEXT|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|版本项目|BUILDPROJECT|SSCODELIST|&nbsp;|
|Bug编号|ID|ACID|&nbsp;|
|延期|DELAY|TEXT|&nbsp;|
|found|FOUND|TEXT|&nbsp;暂时还不知道做什么用的，没有任何处理的代码|
|解决者|RESOLVEDBY|SSCODELIST|&nbsp;|
|解决版本|RESOLVEDBUILD|SSCODELIST|&nbsp;与build外键，且存在默认主干版本（trunk），逗号分开|
|优先级|PRI|NSCODELIST|&nbsp;|
|操作系统|OS|SSCODELIST|&nbsp;|
|hardware|HARDWARE|TEXT|&nbsp;暂时还不知道做什么用的，没有任何处理的代码|
|最后修改者|LASTEDITEDBY|SSCODELIST|&nbsp;|
|Bug标题|TITLE|TEXT|&nbsp;|
|产品|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|平台/分支|BRANCHNAME|PICKUPTEXT|&nbsp;|
|相关任务|TASKNAME|PICKUPTEXT|&nbsp;|
|相关用例|CASENAME|PICKUPTEXT|&nbsp;|
|项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|相关需求|STORYNAME|PICKUPTEXT|&nbsp;|
|用例版本|CASEVERSION|PICKUPDATA|&nbsp;|
|代码类型|REPOTYPE|PICKUPDATA|&nbsp;|
|模块名称|MODULENAME|PICKUPTEXT|&nbsp;|
|转需求|TOSTORY|PICKUP|&nbsp;|
|应用|ENTRY|PICKUP|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|
|转任务|TOTASK|PICKUP|&nbsp;|
|所属计划|PLAN|PICKUP|&nbsp;|
|所属模块|MODULE|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|
|重复ID|DUPLICATEBUG|PICKUP|&nbsp;|
|代码|REPO|PICKUP|&nbsp;|
|相关需求|STORY|PICKUP|&nbsp;|
|相关用例|CASE|PICKUP|&nbsp;|
|所属项目|PROJECT|PICKUP|&nbsp;|
|相关任务|TASK|PICKUP|&nbsp;|
|测试单|TESTTASK|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|严重程度|默认规则|默认规则|
|需求版本|默认规则|默认规则|
|版本名称|默认规则|内容长度必须小于等于[100]|
|相关Bug|默认规则|内容长度必须小于等于[255]|
|激活日期|默认规则|默认规则|
|过期天数|默认规则|默认规则|
|创建版本|默认规则|默认规则|
|指派给|默认规则|内容长度必须小于等于[30]|
|解决方案|默认规则|内容长度必须小于等于[30]|
|修改日期|默认规则|默认规则|
|移动端图片|默认规则|内容长度必须小于等于[4000]|
|result|默认规则|默认规则|
|关键词|默认规则|内容长度必须小于等于[255]|
|是否收藏|默认规则|内容长度必须小于等于[200]|
|模块名称|默认规则|内容长度必须小于等于[200]|
|由谁关闭|默认规则|内容长度必须小于等于[200]|
|浏览器|默认规则|内容长度必须小于等于[30]|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|重现步骤|默认规则|内容长度必须小于等于[65535]|
|v2|默认规则|内容长度必须小于等于[40]|
|是否确认|默认规则|默认规则|
|联系人|默认规则|内容长度必须小于等于[100]|
|由谁创建|默认规则|内容长度必须小于等于[200]|
|激活次数|默认规则|默认规则|
|创建日期|默认规则|默认规则|
|关闭日期|默认规则|默认规则|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|指派日期|默认规则|默认规则|
|截止日期|默认规则|默认规则|
|标题颜色|默认规则|内容长度必须小于等于[7]|
|备注|默认规则|内容长度必须小于等于[1048576]|
|解决日期|默认规则|默认规则|
|Bug类型|默认规则|内容长度必须小于等于[30]|
|Bug状态|默认规则|内容长度必须小于等于[8]|
|影响版本|默认规则|内容长度必须小于等于[255]|
|延期解决|默认规则|内容长度必须小于等于[200]|
|附件|默认规则|内容长度必须小于等于[1000]|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|v1|默认规则|内容长度必须小于等于[40]|
|已删除|默认规则|内容长度必须小于等于[1]|
|lines|默认规则|内容长度必须小于等于[10]|
|子状态|默认规则|内容长度必须小于等于[30]|
|版本项目|默认规则|内容长度必须小于等于[200]|
|Bug编号|默认规则|默认规则|
|延期|默认规则|内容长度必须小于等于[200]|
|found|默认规则|内容长度必须小于等于[30]|
|解决者|默认规则|内容长度必须小于等于[200]|
|解决版本|默认规则|内容长度必须小于等于[255]|
|优先级|默认规则|默认规则|
|操作系统|默认规则|内容长度必须小于等于[30]|
|hardware|默认规则|内容长度必须小于等于[30]|
|最后修改者|默认规则|内容长度必须小于等于[200]|
|Bug标题|默认规则|内容长度必须小于等于[255]|
|产品|默认规则|内容长度必须小于等于[90]|
|平台/分支|默认规则|内容长度必须小于等于[255]|
|相关任务|默认规则|内容长度必须小于等于[255]|
|相关用例|默认规则|内容长度必须小于等于[255]|
|项目|默认规则|内容长度必须小于等于[90]|
|相关需求|默认规则|内容长度必须小于等于[255]|
|用例版本|默认规则|默认规则|
|代码类型|默认规则|内容长度必须小于等于[30]|
|模块名称|默认规则|内容长度必须小于等于[60]|
|转需求|默认规则|默认规则|
|应用|默认规则|默认规则|
|所属产品|默认规则|默认规则|
|转任务|默认规则|默认规则|
|所属计划|默认规则|默认规则|
|所属模块|默认规则|默认规则|
|平台/分支|默认规则|默认规则|
|重复ID|默认规则|默认规则|
|代码|默认规则|默认规则|
|相关需求|默认规则|默认规则|
|相关用例|默认规则|默认规则|
|所属项目|默认规则|默认规则|
|相关任务|默认规则|默认规则|
|测试单|默认规则|默认规则|

## 状态控制

|Bug状态||是否收藏||是否确认|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   || --------   | ------------|------------|------------|------------|
|Value||Value2||Value3| 允许| 确认<br>Remove<br>Update<br>关联Bug<br>关联Bug（解决Bug）<br>解除关联Bug<br>解除关联Bug<br>Get<br>指派<br>批量解除关联Bug<br>转需求<br>解决<br>版本解除关联Bug<br>版本批量解除关联Bug<br>关联Bug（遗留Bug）<br>Create<br>批量解除关联Bug<br> | 不允许 | SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_FAVOR_BUT<br>SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_ACTIVATE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__BUG_ACTIVATE_BUT<br>SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_FAVOR_BUT<br>SRFUR__BUG_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__BUG_NFAVOR_BUT<br>SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_ACTIVATE_BUT<br>SRFUR__PROP_LBUG_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_CONFIRM_BUT<br>SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_NFAVOR_BUT<br>SRFUR__BUG_ACTIVATE_BUT<br> |
|Value||Value2||Value3| 允许| 版本批量解除关联Bug<br>Get<br>关联Bug（解决Bug）<br>Create<br>转需求<br>批量解除关联Bug<br>关联Bug（遗留Bug）<br>解除关联Bug<br>版本解除关联Bug<br>Update<br>Remove<br>批量解除关联Bug<br>版本关联Bug<br>解除关联Bug<br>关联Bug<br>指派<br>激活<br> | 不允许 | SRFUR__BUG_RESOLVE_BUT<br>SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_TOSTORY_BUT<br>SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_FAVOR_BUT<br>SRFUR__BUG_ASSIGNTO_BUT<br>SRFUR__BUG_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__BUG_ASSIGNTO_BUT<br>SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_RESOLVE_BUT<br>SRFUR__BUG_FAVOR_BUT<br>SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_CONFIRM_BUT<br>SRFUR__BUG_TOSTORY_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_NFAVOR_BUT<br>SRFUR__BUG_TOSTORY_BUT<br>SRFUR__BUG_CONFIRM_BUT<br>SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_RESOLVE_BUT<br>SRFUR__BUG_ASSIGNTO_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__BUG_CLOSE_BUT<br>SRFUR__BUG_RESOLVE_BUT<br>SRFUR__BUG_ASSIGNTO_BUT<br>SRFUR__BUG_TOSTORY_BUT<br>SRFUR__BUG_NFAVOR_BUT<br>SRFUR__BUG_CONFIRM_BUT<br>SRFUR__PROP_LBUG_BUT<br> |
|Value||Value2||Value3| 允许| Get<br>Remove<br>关闭<br>Update<br>版本解除关联Bug<br>指派<br>转需求<br>Create<br>激活<br>批量解除关联Bug<br>关联Bug（解决Bug）<br>解除关联Bug<br>关联Bug<br>解除关联Bug<br>版本关联Bug<br>批量解除关联Bug<br>关联Bug（遗留Bug）<br>版本批量解除关联Bug<br> | 不允许 | SRFUR__BUG_FAVOR_BUT<br>SRFUR__BUG_CONFIRM_BUT<br>SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_RESOLVE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_FAVOR_BUT<br>SRFUR__BUG_RESOLVE_BUT<br>SRFUR__BUG_CONFIRM_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__PROP_LBUG_BUT<br>SRFUR__BUG_CONFIRM_BUT<br>SRFUR__BUG_NFAVOR_BUT<br>SRFUR__BUG_RESOLVE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__BUG_CONFIRM_BUT<br>SRFUR__BUG_RESOLVE_BUT<br>SRFUR__BUG_NFAVOR_BUT<br>SRFUR__PROP_LBUG_BUT<br> |

Bug状态

| 状态        |    状态值   |
| --------   |------------|
|激活|active|
|已解决|resolved|
|已关闭|closed|
是否收藏

是否确认

| 状态        |    状态值   |
| --------   |------------|
|是|1|
|否|0|

## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|激活|用户自定义|&nbsp;|
|指派|用户自定义|&nbsp;|
|批量解除关联Bug|用户自定义|&nbsp;|
|Bug收藏|实体处理逻辑|&nbsp;|
|取消收藏|实体处理逻辑|&nbsp;|
|版本批量解除关联Bug|用户自定义|&nbsp;|
|版本关联Bug|用户自定义|&nbsp;|
|版本解除关联Bug|用户自定义|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|关闭|用户自定义|&nbsp;|
|确认|用户自定义|&nbsp;|
|获取联系人|实体处理逻辑|&nbsp;|
|关联Bug|用户自定义|&nbsp;|
|批量解除关联Bug|用户自定义|&nbsp;|
|关联Bug（解决Bug）|用户自定义|&nbsp;|
|关联Bug（遗留Bug）|用户自定义|&nbsp;|
|移除关联Bug（遗留Bug）|用户自定义|&nbsp;|
|解除关联Bug|用户自定义|&nbsp;|
|解决|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|行为|用户自定义|&nbsp;|
|发送消息前置处理|用户自定义|&nbsp;|
|转需求|用户自定义|&nbsp;|
|解除关联Bug|用户自定义|&nbsp;|
|更新需求版本|实体处理逻辑|&nbsp;|

## 处理逻辑
* Bug收藏 (BugFavorites)
  
   当前用户收藏相关Bug信息

{% plantuml %}
hide footbox

Bug -> 收藏: 创建收藏信息
Bug -> Bug: 设置收藏参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|创建收藏信息 |
|1|开始 | 
|2|设置收藏参数 |
<center>Bug收藏</center>
* Bug取消收藏 (BugNFavorites)
  
   当前用户收藏相关Bug信息

{% plantuml %}
hide footbox

Bug -> Bug: 取消收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|取消收藏 |
<center>Bug取消收藏</center>
* 获取联系人 (GetCurUserConcat)
  
   

{% plantuml %}
hide footbox

Bug -> Bug: 获取参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取参数 |
<center>获取联系人</center>
* 获取模块的平台 (GetModuleBranch)
  
   

{% plantuml %}
hide footbox

Bug -> 模块: 获取模块详情
Bug -> Bug: 设置模块参数
模块 -> Bug: 设置bug平台值
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取模块详情 |
|1|开始 | 
|2|设置模块参数 |
|3|设置bug平台值 |
<center>获取模块的平台</center>
* 获取产品 (GetProducts)
  
   

{% plantuml %}
hide footbox

Bug -> Bug: 获取产品
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取产品 |
<center>获取产品</center>
* 更新需求版本 (UpdateStoryVersion)
  
   

{% plantuml %}
hide footbox

Bug -> Bug: 重置需求版本
Bug -> Bug: 获取需求版本
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|重置需求版本 |
|1|开始 | 
|2|获取需求版本 |
<center>更新需求版本</center>
* 发送通知 (sendNotice)
  
   

{% plantuml %}
hide footbox

{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
<center>发送通知</center>
* 发送待办任务 (sendTodoTask)
  
   

{% plantuml %}
hide footbox

{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
<center>发送待办任务</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|AssignedToMyBug|指派给我Bug([MYSQL5](../../appendix/query_MYSQL5.md#Bug_AssignedToMyBug))|否|&nbsp;指派给我Bug（移动端）|
|AssignedToMyBugPc|指派给我Bug（PC）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_AssignedToMyBugPc))|否|&nbsp;指派给我Bug（移动端）|
|BuildBugs|版本关联Bug（已解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildBugs))|否|&nbsp;|
|buildLinkResolvedBugs|版本可关联的已解决的Bugs集合([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildLinkResolvedBugs))|否|&nbsp;用于版本中【已解决的Bug】表格页面【添加bug】拓展功能。
查询Bug状态【不为已解决】的bug集合（当前项目），选择多项数据之后，批量设置其状态为【已解决】。|
|BuildOpenBugs|版本关联Bug（已解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildOpenBugs))|否|&nbsp;|
|BuildProduceBug|Build产生的Bug([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBug))|否|&nbsp;|
|BuildProduceBugModule|Build产生的Bug-模块分布([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugModule))|否|&nbsp;|
|BuildProduceBugModule_Project|Build产生的Bug-模块分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugModule_Project))|否|&nbsp;|
|BuildProduceBugOpenedBy|Build产生的Bug-创建者分布([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugOpenedBy))|否|&nbsp;|
|BuildProduceBugOpenedBy_Project|Build产生的Bug-创建者分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugOpenedBy_Project))|否|&nbsp;|
|BuildProduceBugRES|Build产生的Bug（已解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugRES))|否|&nbsp;|
|BuildProduceBugRESOLVEDBY|Build产生的Bug-解决者分布([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugRESOLVEDBY))|否|&nbsp;|
|BuildProduceBugRESOLVEDBY_Project|Build产生的Bug-解决者分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugRESOLVEDBY_Project))|否|&nbsp;|
|BuildProduceBugResolution_Project|Build产生的Bug-解决方案分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugResolution_Project))|否|&nbsp;|
|BuildProduceBugSeverity_Project|Build产生的Bug-严重程度分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugSeverity_Project))|否|&nbsp;|
|BuildProduceBugStatus_Project|Build产生的Bug-状态分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugStatus_Project))|否|&nbsp;|
|BuildProduceBugType_Project|Build产生的Bug-类型分布(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_BuildProduceBugType_Project))|否|&nbsp;|
|CurUserResolve|当前用户解决的Bug([MYSQL5](../../appendix/query_MYSQL5.md#Bug_CurUserResolve))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Bug_Default))|是|&nbsp;|
|ESBulk|ES批量的导入([MYSQL5](../../appendix/query_MYSQL5.md#Bug_ESBulk))|否|&nbsp;|
|MyAgentBug|我代理的Bug([MYSQL5](../../appendix/query_MYSQL5.md#Bug_MyAgentBug))|否|&nbsp;|
|MyCurOpenedBug|累计创建的Bug数([MYSQL5](../../appendix/query_MYSQL5.md#Bug_MyCurOpenedBug))|否|&nbsp;|
|MyFavorites|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#Bug_MyFavorites))|否|&nbsp;|
|NotCurPlanLinkBug|计划关联bug(去除已关联)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_NotCurPlanLinkBug))|否|&nbsp;|
|ReleaseBugs|发布关联Bug（已解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_ReleaseBugs))|否|&nbsp;|
|ReleaseLeftBugs|发布关联Bug（未解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_ReleaseLeftBugs))|否|&nbsp;|
|ReleaseLinkableLeftBug|发布可关联的bug（遗留）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_ReleaseLinkableLeftBug))|否|&nbsp;|
|ReleaseLinkableResolvedBug|发布可关联的bug（已解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_ReleaseLinkableResolvedBug))|否|&nbsp;|
|ReportBugs|发布关联Bug（未解决）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_ReportBugs))|否|&nbsp;|
|SelectBugByBuild|版本关联bug(遗留得bug)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_SelectBugByBuild))|否|&nbsp;|
|SelectBugsByProject|查询遗留得bug(项目)([MYSQL5](../../appendix/query_MYSQL5.md#Bug_SelectBugsByProject))|否|&nbsp;|
|TaskBug|任务相关bug([MYSQL5](../../appendix/query_MYSQL5.md#Bug_TaskBug))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Bug_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AssignedToMyBug|指派给我Bug|AssignedToMyBug|否|&nbsp;|
|AssignedToMyBugPc|指派给我Bug（PC）|AssignedToMyBugPc|否|&nbsp;|
|BugsByBuild|版本关联bug(遗留的)|SelectBugByBuild|否|&nbsp;|
|BuildBugs|版本关联Bug（已解决）|BuildBugs|否|&nbsp;|
|buildLinkResolvedBugs|版本可关联的已解决的Bugs集合|buildLinkResolvedBugs|否|&nbsp;|
|BuildOpenBugs|版本关联Bug（已解决）|BuildOpenBugs|否|&nbsp;|
|BuildProduceBug|Build产生的Bug|BuildProduceBug|否|&nbsp;|
|BuildProduceBugModule|Build产生的Bug|BuildProduceBugModule|否|&nbsp;|
|BuildProduceBugModule_Project|Build产生的Bug-创建者分布(项目)|BuildProduceBugModule_Project|否|&nbsp;|
|BuildProduceBugOpenedBy|Build产生的Bug-创建分类|BuildProduceBugOpenedBy|否|&nbsp;|
|BuildProduceBugOpenedBy_Project|Build产生的Bug-创建者分布(项目)|BuildProduceBugOpenedBy_Project|否|&nbsp;|
|BuildProduceBugRES|Build产生的Bug（已解决）|BuildProduceBugRES|否|&nbsp;|
|BuildProduceBugRESOLVEDBY|Build产生的Bug-解决者分布|BuildProduceBugRESOLVEDBY|否|&nbsp;|
|BuildProduceBugRESOLVEDBY_Project|Build产生的Bug-解决者分布(项目)|BuildProduceBugRESOLVEDBY_Project|否|&nbsp;|
|BuildProduceBugResolution_Project|Build产生的Bug-解决方案分布(项目)|BuildProduceBugResolution_Project|否|&nbsp;|
|BuildProduceBugSeverity_Project|Build产生的Bug-严重程度分布(项目)|BuildProduceBugSeverity_Project|否|&nbsp;|
|BuildProduceBugStatus_Project|Build产生的Bug-状态分布(项目)|BuildProduceBugStatus_Project|否|&nbsp;|
|BuildProduceBugType_Project|Build产生的Bug-类型分布(项目)|BuildProduceBugType_Project|否|&nbsp;|
|CurUserResolve|当前用户解决的Bug|CurUserResolve|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ESBulk|ES批量的导入|ESBulk|否|&nbsp;|
|MyAgentBug|我代理的Bug|MyAgentBug|否|&nbsp;|
|MyCurOpenedBug|累计创建的Bug数|MyCurOpenedBug|否|&nbsp;|
|MyFavorites|我的收藏|MyFavorites|否|&nbsp;|
|NotCurPlanLinkBug|计划关联bug（去除已关联）|NotCurPlanLinkBug|否|&nbsp;|
|ProjectBugs|遗留得Bug(项目)|SelectBugsByProject|否|&nbsp;|
|ReleaseBugs|发布关联Bug（已解决）|ReleaseBugs|否|&nbsp;|
|ReleaseLeftBugs|发布关联Bug（已解决）|ReleaseLeftBugs|否|&nbsp;|
|ReleaseLinkableLeftBug|发布可关联的bug（遗留）|ReleaseLinkableLeftBug|否|&nbsp;|
|ReleaseLinkableResolvedBug|发布可关联的bug（已解决）|ReleaseLinkableResolvedBug|否|&nbsp;|
|ReportBugs|发布关联Bug（未解决）|ReportBugs|否|&nbsp;|
|TaskBug|任务相关bug|TaskBug|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|严重程度(SEVERITY)|EQ|
|过期天数(OVERDUEBUGS)|LT|
|指派给(ASSIGNEDTO)|EQ|
|解决方案(RESOLUTION)|EQ|
|由谁关闭(CLOSEDBY)|EQ|
|浏览器(BROWSER)|EQ|
|是否确认(CONFIRMED)|EQ|
|由谁创建(OPENEDBY)|EQ|
|指派日期(ASSIGNEDDATE)|GTANDEQ|
|截止日期(DEADLINE)|GTANDEQ|
|截止日期(DEADLINE)|LT|
|截止日期(DEADLINE)|LTANDEQ|
|标题颜色(COLOR)|EQ|
|解决日期(RESOLVEDDATE)|LTANDEQ|
|Bug类型(TYPE)|EQ|
|Bug状态(STATUS)|EQ|
|Bug状态(STATUS)|NOTEQ|
|版本项目(BUILDPROJECT)|EQ|
|Bug编号(ID)|EQ|
|解决者(RESOLVEDBY)|EQ|
|解决版本(RESOLVEDBUILD)|EQ|
|优先级(PRI)|EQ|
|操作系统(OS)|EQ|
|最后修改者(LASTEDITEDBY)|EQ|
|Bug标题(TITLE)|LIKE|
|产品(PRODUCTNAME)|EQ|
|产品(PRODUCTNAME)|LIKE|
|平台/分支(BRANCHNAME)|EQ|
|平台/分支(BRANCHNAME)|LIKE|
|相关任务(TASKNAME)|EQ|
|相关任务(TASKNAME)|LIKE|
|项目(PROJECTNAME)|EQ|
|项目(PROJECTNAME)|LIKE|
|相关需求(STORYNAME)|EQ|
|相关需求(STORYNAME)|LIKE|
|模块名称(MODULENAME)|EQ|
|模块名称(MODULENAME)|LIKE|
|转需求(TOSTORY)|EQ|
|应用(ENTRY)|EQ|
|所属产品(PRODUCT)|EQ|
|转任务(TOTASK)|EQ|
|所属计划(PLAN)|EQ|
|所属模块(MODULE)|EQ|
|平台/分支(BRANCH)|EQ|
|重复ID(DUPLICATEBUG)|EQ|
|代码(REPO)|EQ|
|相关需求(STORY)|EQ|
|相关用例(CASE)|EQ|
|所属项目(PROJECT)|EQ|
|相关任务(TASK)|EQ|
|测试单(TESTTASK)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|ID|P|确认|Bug标题|Bug状态|由谁创建|创建日期|指派给|方案|修改日期|激活日期|是否收藏|产品|项目|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - | - | - |
