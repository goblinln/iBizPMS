# 需求(ZT_STORY)

  

## 关系
{% plantuml %}
需求 *-- Bug 
需求 *-- Bug 
需求 *-- 测试用例 
需求 *-- 项目中需要做的需求 
需求 *-- 需求描述 
需求 *-- 需求阶段 
需求 *-- 需求 
需求 *-- 任务 
需求 *-- 需求 
需求模块 *-- 需求 
产品的分支和平台信息 *-- 需求 
Bug *-- 需求 
Bug *-- 需求 
产品 *-- 需求 
需求 *-- 需求 
需求 *-- 需求 
索引检索 <|-- 需求 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|所属模块名称|MODULENAME1|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|细分需求|CHILDSTORIES|SMCODELIST|&nbsp;|
|IBIZ标识|IBIZ_ID|TEXT|&nbsp;|
|所属计划|PLAN|LONGTEXT|&nbsp;|
|版本号|VERSION|INT|&nbsp;每次更新内容，会自动加1（只有真正更新数据才会更新，例如打开编辑/变更需求，没有进行任何修改点击保存，是不会更新该笔数据）|
|指派日期|ASSIGNEDDATE|DATETIME|&nbsp;|
|故事点|STORYPOINTS|SSCODELIST|&nbsp;|
|来源对象名称|SOURCENAME|TEXT|&nbsp;|
|需求提供时间|STORYPROVIDEDATE|DATE|&nbsp;|
|是否子需求|ISLEAF|TEXT|&nbsp;|
|优先级|PRI|NSCODELIST|&nbsp;|
|来源对象标识|SOURCEID|TEXT|&nbsp;|
|相关需求|LINKSTORIES|SMCODELIST|&nbsp;|
|评审结果|ASSESSRESULT|SSCODELIST|&nbsp;|
|当前状态|STATUS|SSCODELIST|&nbsp;|
|抄送给|MAILTOPK|SMCODELIST|&nbsp;|
|预计工时|ESTIMATE|FLOAT|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|评审时间|REVIEWEDDATE|DATE|&nbsp;|
|需求名称|TITLE|TEXT|&nbsp;|
|联系人|MAILTOCONACT|TEXT|&nbsp;|
|来源备注|SOURCENOTE|TEXT|&nbsp;|
|版本号|VERSIONC|TEXT|&nbsp;|
|由谁评审|REVIEWEDBY|SMCODELIST|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|设置阶段者|STAGEDBY|SSCODELIST|&nbsp;|
|由谁创建|OPENEDBY|SSCODELIST|&nbsp;|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|编号|ID|ACID|&nbsp;|
|来源对象|IBIZ_SOURCEOBJECT|TEXT|&nbsp;|
|需求来源|SOURCE|SSCODELIST|&nbsp;|
|需求最晚完成时间|STORYLATESTFINISHEDDATE|DATE|&nbsp;|
|不需要评审|NEEDNOTREVIEW|SMCODELIST|&nbsp;|
|是否可以细分|ISCHILD|TEXT|&nbsp;|
|关闭原因|CLOSEDREASON|SSCODELIST|&nbsp;|
|标题颜色|COLOR|SSCODELIST|&nbsp;|
|orgid|ORGID|TEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|是否收藏|ISFAVORITES|TEXT|&nbsp;|
|来源对象|SOURCEOBJECT|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|关键词|KEYWORDS|TEXT|&nbsp;|
|最后修改|LASTEDITEDBY|SSCODELIST|&nbsp;|
|所处阶段|STAGE|SSCODELIST|&nbsp;|
|项目|PROJECT|BIGINT|&nbsp;|
|关闭日期	|CLOSEDDATE|DATETIME|&nbsp;|
|需求描述|SPEC|HTMLTEXT|&nbsp;|
|来源对象名称|IBIZ_SOURCENAME|TEXT|&nbsp;|
|指派给（选择）|ASSIGNEDTOPK|TEXT|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|acllist|ACLLIST|TEXT|&nbsp;|
|验收标准|VERIFY|LONGTEXT|&nbsp;|
|由谁关闭|CLOSEDBY|SSCODELIST|&nbsp;|
|acl|ACL|TEXT|&nbsp;|
|评审结果|RESULT|SSCODELIST|&nbsp;|
|需求类型|TYPE|SSCODELIST|&nbsp;|
|最后修改日期|LASTEDITEDDATE|DATETIME|&nbsp;|
|来源对象标识|IBIZ_SOURCEID|TEXT|&nbsp;|
|之前的版本|PREVERSION|INT|&nbsp;|
|需求提供人|STORYPROVIDER|TEXT|&nbsp;|
|MDEPTID|MDEPTID|TEXT|&nbsp;|
|模块路径|PATH|PICKUPDATA|&nbsp;|
|父需求名称|PARENTNAME|PICKUPTEXT|&nbsp;|
|所属模块名称|MODULENAME|PICKUPTEXT|&nbsp;|
|产品名称|PRODOCTNAME|PICKUPTEXT|&nbsp;|
|平台/分支|BRANCHNAME|PICKUPTEXT|&nbsp;|
|来源Bug|FROMBUG|PICKUP|&nbsp;|
|父需求|PARENT|PICKUP|&nbsp;|
|所属模块|MODULE|PICKUP|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|
|重复需求ID|DUPLICATESTORY|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|
|转Bug|TOBUG|PICKUP|&nbsp;暂时没找到使用地方（页面操作了从需求转Bug也没有更新该字段）|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|指派给|默认规则|内容长度必须小于等于[30]|
|所属模块名称|默认规则|内容长度必须小于等于[200]|
|附件|默认规则|内容长度必须小于等于[1000]|
|细分需求|默认规则|内容长度必须小于等于[255]|
|IBIZ标识|默认规则|内容长度必须小于等于[100]|
|所属计划|默认规则|内容长度必须小于等于[1048576]|
|版本号|默认规则|默认规则|
|指派日期|默认规则|默认规则|
|故事点|默认规则|内容长度必须小于等于[60]|
|来源对象名称|默认规则|内容长度必须小于等于[100]|
|需求提供时间|默认规则|默认规则|
|是否子需求|默认规则|内容长度必须小于等于[200]|
|优先级|默认规则|默认规则|
|来源对象标识|默认规则|内容长度必须小于等于[100]|
|相关需求|默认规则|内容长度必须小于等于[255]|
|评审结果|默认规则|内容长度必须小于等于[200]|
|当前状态|默认规则|内容长度必须小于等于[200]|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|预计工时|默认规则|默认规则|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|评审时间|默认规则|默认规则|
|需求名称|默认规则|内容长度必须小于等于[255]|
|联系人|默认规则|内容长度必须小于等于[100]|
|来源备注|默认规则|内容长度必须小于等于[255]|
|版本号|默认规则|内容长度必须小于等于[200]|
|由谁评审|默认规则|内容长度必须小于等于[255]|
|子状态|默认规则|内容长度必须小于等于[30]|
|设置阶段者|默认规则|内容长度必须小于等于[200]|
|由谁创建|默认规则|内容长度必须小于等于[200]|
|创建日期|默认规则|默认规则|
|编号|默认规则|默认规则|
|来源对象|默认规则|内容长度必须小于等于[100]|
|需求来源|默认规则|内容长度必须小于等于[20]|
|需求最晚完成时间|默认规则|默认规则|
|不需要评审|默认规则|内容长度必须小于等于[4000]|
|是否可以细分|默认规则|内容长度必须小于等于[200]|
|关闭原因|默认规则|内容长度必须小于等于[30]|
|标题颜色|默认规则|内容长度必须小于等于[7]|
|orgid|默认规则|内容长度必须小于等于[100]|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|是否收藏|默认规则|内容长度必须小于等于[200]|
|来源对象|默认规则|内容长度必须小于等于[100]|
|已删除|默认规则|内容长度必须小于等于[1]|
|关键词|默认规则|内容长度必须小于等于[255]|
|最后修改|默认规则|内容长度必须小于等于[200]|
|所处阶段|默认规则|内容长度必须小于等于[200]|
|项目|默认规则|默认规则|
|关闭日期	|默认规则|默认规则|
|需求描述|默认规则|内容长度必须小于等于[1048576]|
|来源对象名称|默认规则|内容长度必须小于等于[100]|
|指派给（选择）|默认规则|内容长度必须小于等于[200]|
|备注|默认规则|内容长度必须小于等于[1048576]|
|acllist|默认规则|内容长度必须小于等于[100]|
|验收标准|默认规则|内容长度必须小于等于[1048576]|
|由谁关闭|默认规则|内容长度必须小于等于[200]|
|acl|默认规则|内容长度必须小于等于[100]|
|评审结果|默认规则|内容长度必须小于等于[200]|
|需求类型|默认规则|内容长度必须小于等于[30]|
|最后修改日期|默认规则|默认规则|
|来源对象标识|默认规则|内容长度必须小于等于[100]|
|之前的版本|默认规则|默认规则|
|需求提供人|默认规则|内容长度必须小于等于[100]|
|MDEPTID|默认规则|内容长度必须小于等于[100]|
|模块路径|默认规则|内容长度必须小于等于[255]|
|父需求名称|默认规则|内容长度必须小于等于[255]|
|所属模块名称|默认规则|内容长度必须小于等于[60]|
|产品名称|默认规则|内容长度必须小于等于[90]|
|平台/分支|默认规则|内容长度必须小于等于[255]|
|来源Bug|默认规则|默认规则|
|父需求|默认规则|默认规则|
|所属模块|默认规则|默认规则|
|所属产品|默认规则|默认规则|
|重复需求ID|默认规则|默认规则|
|平台/分支|默认规则|默认规则|
|转Bug|默认规则|默认规则|

## 状态控制

|当前状态||是否收藏||是否可以细分|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   || --------   | ------------|------------|------------|------------|
|Value||Value2||Value3| 允许| 关闭<br>Save<br>项目批量解除关联需求<br>版本解除关联需求<br>评审<br>发布批量解除关联需求<br>计划批量解除关联需求<br>Update<br>变更<br>Remove<br>项目解除关联需求<br>项目关联需求-按计划关联<br>项目关联需求<br>计划关联需求<br>Get<br>指派<br>发布解除关联需求<br>版本批量解除关联需求<br>项目关联需求<br>发布关联需求<br>计划解除关联需求<br> | 不允许 | SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_CCASE_BUT<br>SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_CLOSED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_CCASE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许| 计划关联需求<br>Update<br>版本批量解除关联需求<br>关闭<br>项目关联需求<br>指派<br>Remove<br>项目关联需求-按计划关联<br>项目关联需求<br>发布关联需求<br>Save<br>评审<br>版本解除关联需求<br>计划解除关联需求<br>变更<br>计划批量解除关联需求<br>发布批量解除关联需求<br>项目批量解除关联需求<br>Get<br>发布解除关联需求<br>项目解除关联需求<br> | 不允许 | SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_ACTIVE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_CCASE_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_CCASE_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_CLOSED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许| 发布批量解除关联需求<br>Update<br>项目批量解除关联需求<br>计划解除关联需求<br>发布解除关联需求<br>指派<br>项目关联需求-按计划关联<br>Remove<br>Save<br>项目关联需求<br>计划批量解除关联需求<br>项目关联需求<br>版本解除关联需求<br>发布关联需求<br>Get<br>激活<br>计划关联需求<br>项目解除关联需求<br>版本批量解除关联需求<br> | 不允许 | SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_CLOSED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_CCASE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_CCASE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许| 计划批量解除关联需求<br>发布解除关联需求<br>Remove<br>版本解除关联需求<br>Get<br>项目关联需求<br>变更<br>项目解除关联需求<br>关闭<br>评审<br>版本批量解除关联需求<br>项目关联需求-按计划关联<br>发布批量解除关联需求<br>指派<br>计划解除关联需求<br>项目批量解除关联需求<br>项目关联需求<br>发布关联需求<br>Save<br>计划关联需求<br>Update<br>激活<br> | 不允许 | SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_CCASE_BUT<br>SRFUR__STORY_ASS_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_CHANGED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_FAVOR_BUT<br>SRFUR__STORY_ACTIVE_BUT<br> |
|Value||Value2||Value3| 允许| 评审<br>Get<br>版本解除关联需求<br>变更<br>计划关联需求<br>Remove<br>激活<br>发布解除关联需求<br>项目关联需求<br>项目解除关联需求<br>项目关联需求<br>项目解除关联需求<br>Save<br>指派<br>计划解除关联需求<br>发布关联需求<br>关闭<br>版本解除关联需求<br>Update<br> | 不允许 | SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_XQXF_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_DELETE_BUT<br>SRFUR__STORY_CHANGED_BUT<br>SRFUR__STORY_CCASE_BUT<br>SRFUR__STORY_XQXF_BUT<br>SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_REVIEW_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_CLOSED_BUT<br>SRFUR__STORY_ASS_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__STORY_ACTIVE_BUT<br>SRFUR__STORY_NFAVOR_BUT<br>SRFUR__STORY_XQXF_BUT<br> |

当前状态

| 状态        |    状态值   |
| --------   |------------|
|草稿|draft|
|激活|active|
|已关闭|closed|
|已变更|changed|
是否收藏

是否可以细分


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|激活|用户自定义|&nbsp;|
|全部推送|用户自定义|&nbsp;|
|指派|用户自定义|&nbsp;需求指派|
|批量指派|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|批量变更平台/分支|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|批量变更模块|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|批量关联计划|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|批量变更阶段|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|批量关闭|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|批量评审|用户自定义|&nbsp;×，模板暂不支持批处理（参数）...|
|计划批量解除关联需求|用户自定义|&nbsp;|
|bug转需求|用户自定义|&nbsp;|
|版本批量解除关联需求|用户自定义|&nbsp;|
|项目关联需求|用户自定义|&nbsp;|
|版本解除关联需求|用户自定义|&nbsp;|
|版本解除关联需求|实体处理逻辑|&nbsp;|
|变更|用户自定义|&nbsp;需求变更|
|CheckKey|内置方法|&nbsp;|
|关闭|用户自定义|&nbsp;需求关闭|
|生成任务|用户自定义|&nbsp;|
|获取需求描述|用户自定义|&nbsp;|
|获取需求描述|实体处理逻辑|&nbsp;|
|任务相关需求草稿方法|用户自定义|&nbsp;|
|获取联系人|实体处理逻辑|&nbsp;|
|项目关联需求-按计划关联|用户自定义|&nbsp;|
|计划关联需求|用户自定义|&nbsp;|
|项目批量解除关联需求|用户自定义|&nbsp;|
|项目关联需求|用户自定义|&nbsp;|
|项目解除关联需求|用户自定义|&nbsp;|
|项目解除关联需求|实体处理逻辑|&nbsp;|
|推送|用户自定义|&nbsp;|
|发布批量解除关联需求|用户自定义|&nbsp;|
|发布关联需求|用户自定义|&nbsp;|
|发布解除关联需求|用户自定义|&nbsp;|
|重置由谁评审|用户自定义|&nbsp;|
|评审|用户自定义|&nbsp;需求评审|
|Save|内置方法|&nbsp;|
|行为|用户自定义|&nbsp;|
|发送消息前置处理|用户自定义|&nbsp;|
|需求收藏|实体处理逻辑|&nbsp;|
|取消收藏|实体处理逻辑|&nbsp;|
|同步Ibz平台实体|用户自定义|&nbsp;|
|转需求|用户自定义|&nbsp;|
|计划解除关联需求|用户自定义|&nbsp;|

## 处理逻辑
* 获取联系人 (GetCurUserConcat)
  
   

{% plantuml %}
hide footbox

需求 -> 需求: 获取参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取参数 |
<center>获取联系人</center>
* GetStorySpecs (GetStorySpecs)
  
   

{% plantuml %}
hide footbox

需求 -> 需求: 获取数据
需求 -> 需求: 参数
需求 -> 需求: 处理
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取数据 |
|2|参数 |
|3|处理 |
<center>GetStorySpecs</center>
* 需求收藏 (StoryFavorites)
  
   当前用户收藏相关需求信息

{% plantuml %}
hide footbox

需求 -> 收藏: 创建收藏信息
需求 -> 需求: 设置收藏参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|创建收藏信息 |
|1|开始 | 
|2|设置收藏参数 |
<center>需求收藏</center>
* 需求取消收藏 (StoryNFavorites)
  
   当前用户收藏相关需求信息

{% plantuml %}
hide footbox

需求 -> 需求: 取消收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|取消收藏 |
<center>需求取消收藏</center>
* buildUnlinkStorys (buildUnlinkStorys)
  
   

{% plantuml %}
hide footbox

需求 -> 需求: 参数
需求 -> 需求: 处理
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|参数 |
|2|处理 |
<center>buildUnlinkStorys</center>
* projectUnlinkStorys (projectUnlinkStorys)
  
   

{% plantuml %}
hide footbox

需求 -> 需求: 准备参数
需求 -> 需求: 处理
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|处理 |
<center>projectUnlinkStorys</center>
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
|AssignedToMyStory|指派给我的需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_AssignedToMyStory))|否|&nbsp;指派给我的需求（移动端使用）|
|AssignedToMyStoryCalendar|指派给我的需求（日历）([MYSQL5](../../appendix/query_MYSQL5.md#Story_AssignedToMyStoryCalendar))|否|&nbsp;指派给我的需求（移动端使用）|
|BugStory|Bug相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_BugStory))|否|&nbsp;|
|buildLinkCompletedStories|版本关联已完成的需求（选择数据源）([MYSQL5](../../appendix/query_MYSQL5.md#Story_BuildLinkCompletedStories))|否|&nbsp;版本主数据导航页面、【完成需求】分页。
【添加需求】功能中，作为多项数据选择界面的数据源。|
|BuildLinkableStories|版本可关联的需求（产品内）([MYSQL5](../../appendix/query_MYSQL5.md#Story_BuildLinkableStories))|否|&nbsp;根据【版本id】，获取当前【产品】中可选【需求列表】。
【需求】数据必须是【激活状态】。
用于【版本】界面行为【关联需求】。|
|BuildStories|获取版本相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_BuildStories))|否|&nbsp;获取指定版本中相关需求|
|ByModule|通过模块查询([MYSQL5](../../appendix/query_MYSQL5.md#Story_ByModule))|否|&nbsp;|
|CaseStory|相关用例需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_CaseStory))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Story_Default))|是|&nbsp;|
|ESBulk|ES批量的导入([MYSQL5](../../appendix/query_MYSQL5.md#Story_ESBulk))|否|&nbsp;|
|GetProductStories|获取产品需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_GetProductStories))|否|&nbsp;获取指定产品（含分支）中当前用户的相关需求|
|MyAgentStory|我代理的需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_MyAgentStory))|否|&nbsp;|
|MyCurOpenedStory|所创建需求数和对应的优先级及状态([MYSQL5](../../appendix/query_MYSQL5.md#Story_MyCurOpenedStory))|否|&nbsp;|
|MyFavorites|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#Story_MyFavorites))|否|&nbsp;|
|NotCurPlanLinkStory|计划关联需求(去除已关联)([MYSQL5](../../appendix/query_MYSQL5.md#Story_NotCurPlanLinkStory))|否|&nbsp;|
|ParentDefault|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#Story_ParentDefault))|否|&nbsp;|
|ParentDefaultQ|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#Story_ParentDefaultQ))|否|&nbsp;|
|projectLinkStory|项目关联需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_ProjectLinkStory))|否|&nbsp;|
|ProjectStories|项目相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_ProjectStories))|否|&nbsp;|
|ReleaseLinkableStories|版本可关联的完成的需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_ReleaseLinkableStories))|否|&nbsp;|
|ReleaseStories|获取产品发布相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_ReleaseStories))|否|&nbsp;获取指定产品发布中相关需求|
|ReportStories|报告完成需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_ReportStories))|否|&nbsp;|
|StoryChild|获取产品发布相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_StoryChild))|否|&nbsp;获取指定产品发布中相关需求|
|StoryRelated|获取产品发布相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_StoryRelated))|否|&nbsp;获取指定产品发布中相关需求|
|SubStory|需求细分([MYSQL5](../../appendix/query_MYSQL5.md#Story_SubStory))|否|&nbsp;|
|TaskRelatedStory|任务相关需求([MYSQL5](../../appendix/query_MYSQL5.md#Story_TaskRelatedStory))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Story_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AssignedToMyStory|指派给我的需求|AssignedToMyStory|否|&nbsp;|
|AssignedToMyStoryCalendar|指派给我的需求（日历）|AssignedToMyStoryCalendar|否|&nbsp;|
|BugStory|Bug相关需求|BugStory|否|&nbsp;|
|buildLinkCompletedStories|版本关联已完成的需求（选择数据源）|buildLinkCompletedStories|否|&nbsp;|
|BuildLinkableStories|版本可关联的需求（产品内）|BuildLinkableStories|否|&nbsp;|
|BuildStories|获取版本相关需求|BuildStories|否|&nbsp;|
|ByModule|通过模块查询|ByModule|否|&nbsp;|
|CaseStory|相关用例需求|CaseStory|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ESBulk|ES批量的导入|ESBulk|否|&nbsp;|
|GetProductStories|获取产品需求|GetProductStories|否|&nbsp;|
|MyAgentStory|我代理的需求|MyAgentStory|否|&nbsp;|
|MyCurOpenedStory|所创建需求数和对应的优先级及状态|MyCurOpenedStory|否|&nbsp;|
|MyFavorites|我的收藏|MyFavorites|否|&nbsp;|
|NotCurPlanLinkStory|计划关联需求(去除已关联)|NotCurPlanLinkStory|否|&nbsp;|
|ParentDefault|数据查询|ParentDefault|否|&nbsp;|
|ParentDefaultQ|数据查询|ParentDefaultQ|否|&nbsp;|
|projectLinkStory|项目关联需求|projectLinkStory|否|&nbsp;|
|ProjectStories|项目相关需求|ProjectStories|否|&nbsp;|
|ReleaseLinkableStories|版本可关联的完成的需求|ReleaseLinkableStories|否|&nbsp;|
|ReleaseStories|获取产品发布相关需求|ReleaseStories|否|&nbsp;|
|ReportStories|通过模块查询|ReportStories|否|&nbsp;|
|StoryChild|获取产品发布相关需求|StoryChild|否|&nbsp;|
|StoryRelated|获取产品发布相关需求|StoryRelated|否|&nbsp;|
|SubStory|需求细分|SubStory|否|&nbsp;|
|TaskRelatedStory|任务相关需求|TaskRelatedStory|否|&nbsp;|
|VIEW|默认（全部数据）|VIEW|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|指派给(ASSIGNEDTO)|EQ|
|所属计划(PLAN)|EQ|
|所属计划(PLAN)|NOTEQ|
|故事点(STORYPOINTS)|EQ|
|优先级(PRI)|EQ|
|优先级(PRI)|GTANDEQ|
|优先级(PRI)|LTANDEQ|
|评审结果(ASSESSRESULT)|EQ|
|当前状态(STATUS)|EQ|
|当前状态(STATUS)|NOTEQ|
|需求名称(TITLE)|LIKE|
|由谁评审(REVIEWEDBY)|EQ|
|设置阶段者(STAGEDBY)|EQ|
|由谁创建(OPENEDBY)|EQ|
|编号(ID)|EQ|
|编号(ID)|NOTEQ|
|需求来源(SOURCE)|EQ|
|是否可以细分(ISCHILD)|EQ|
|关闭原因(CLOSEDREASON)|EQ|
|标题颜色(COLOR)|EQ|
|是否收藏(ISFAVORITES)|EQ|
|关键词(KEYWORDS)|LIKE|
|最后修改(LASTEDITEDBY)|EQ|
|所处阶段(STAGE)|EQ|
|所处阶段(STAGE)|NOTEQ|
|由谁关闭(CLOSEDBY)|EQ|
|评审结果(RESULT)|EQ|
|需求类型(TYPE)|EQ|
|之前的版本(PREVERSION)|EQ|
|模块路径(PATH)|LIKE|
|父需求名称(PARENTNAME)|EQ|
|父需求名称(PARENTNAME)|LIKE|
|所属模块名称(MODULENAME)|EQ|
|所属模块名称(MODULENAME)|LIKE|
|产品名称(PRODOCTNAME)|EQ|
|产品名称(PRODOCTNAME)|LIKE|
|平台/分支(BRANCHNAME)|EQ|
|平台/分支(BRANCHNAME)|LIKE|
|来源Bug(FROMBUG)|EQ|
|父需求(PARENT)|EQ|
|父需求(PARENT)|GTANDEQ|
|所属模块(MODULE)|EQ|
|所属产品(PRODUCT)|EQ|
|重复需求ID(DUPLICATESTORY)|EQ|
|平台/分支(BRANCH)|EQ|
|转Bug(TOBUG)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|ID|P|需求名称|计划|创建|指派|预计|状态|阶段|所属模块名称|所属模块|是否收藏|是否可以细分|标题颜色|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - | - | - |
