# 任务(ZT_TASK)

  

## 关系
{% plantuml %}
任务 *-- 任务团队 
任务 *-- Bug 
任务 *-- Bug 
任务 *-- burn 
任务 *-- 任务预计 
任务 *-- 任务 
任务模块 *-- 任务 
Bug *-- 任务 
项目 *-- 任务 
需求 *-- 任务 
任务 *-- 任务 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|由谁取消|CANCELEDBY|SSCODELIST|&nbsp;|
|预计剩余|LEFT|FLOAT|&nbsp;|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|标题颜色|COLOR|SSCODELIST|&nbsp;|
|编号|ID|ACID|&nbsp;|
|由谁完成|FINISHEDBY|SSCODELIST|&nbsp;|
|完成者列表|FINISHEDLIST|LONGTEXT|&nbsp;|
|实际开始|REALSTARTED|DATE|&nbsp;|
|由谁关闭|CLOSEDBY|SSCODELIST|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|关闭原因|CLOSEDREASON|SSCODELIST|&nbsp;|
|最后修改日期|LASTEDITEDDATE|DATETIME|&nbsp;|
|指派日期|ASSIGNEDDATE|DATETIME|&nbsp;|
|优先级|PRI|NSCODELIST|&nbsp;此处默认值从0修改为3|
|最后修改|LASTEDITEDBY|SSCODELIST|&nbsp;|
|任务状态|STATUS|SSCODELIST|&nbsp;|
|任务名称|NAME|TEXT|&nbsp;|
|关闭时间|CLOSEDDATE|DATETIME|&nbsp;|
|任务类型|TYPE|SSCODELIST|&nbsp;|
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|任务描述|DESC|LONGTEXT|&nbsp;|
|预计开始|ESTSTARTED|DATE|&nbsp;|
|截止日期|DEADLINE|DATE|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|总计消耗|CONSUMED|FLOAT|&nbsp;|
|最初预计|ESTIMATE|FLOAT|&nbsp;|
|由谁创建|OPENEDBY|SSCODELIST|&nbsp;|
|取消时间|CANCELEDDATE|DATETIME|&nbsp;|
|实际完成|FINISHEDDATE|DATE|&nbsp;|
|所属模块|MODULENAME|PICKUPTEXT|&nbsp;|
|相关需求|STORYNAME|PICKUPTEXT|&nbsp;|
|所属项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|产品|PRODUCT|PICKUPDATA|&nbsp;|
|需求版本|STORYVERSION|PICKUPDATA|&nbsp;半物理字段，且通过确认需求变更操作进行变动|
|产品|PRODUCTNAME|PICKUPDATA|&nbsp;|
|父任务|PARENTNAME|PICKUPTEXT|&nbsp;|
|所属项目|PROJECT|PICKUP|&nbsp;|
|相关需求|STORY|PICKUP|&nbsp;|
|父任务|PARENT|PICKUP|&nbsp;|
|来源Bug|FROMBUG|PICKUP|&nbsp;|
|持续时间|DURATION|TEXT|&nbsp;|
|id|MODULE|PICKUP|&nbsp;|
|模块路径|PATH|PICKUPDATA|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|本次消耗|CURRENTCONSUMED|FLOAT|&nbsp;|
|总计耗时|TOTALTIME|FLOAT|&nbsp;完成界面，临时界面属性|
|是否子任务|ISLEAF|TEXT|&nbsp;|
|所有模块|ALLMODULES|TEXT|&nbsp;|
|多人任务|MULTIPLE|TEXT|&nbsp;|
|项目团队成员|TASKTEAMS|ONE2MANYDATA|&nbsp;|
|所属模块|MODULENAME1|TEXT|&nbsp;|
|工时|IBZTASKESTIMATES|ONE2MANYDATA|&nbsp;|
|是否收藏|ISFAVORITES|TEXT|&nbsp;|
|任务状态|STATUS1|SSCODELIST|&nbsp;|
|任务类型|TASKTYPE|SSCODELIST|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|团队用户|USERNAMES|TEXT|&nbsp;|
|是否完成|ISFINISHED|TEXT|&nbsp;|
|回复数量|REPLYCOUNT|INT|&nbsp;|
|是否填写描述|HASDETAIL|TEXT|&nbsp;|
|最后的更新日期|UPDATEDATE|DATE|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|进度|PROGRESSRATE|TEXT|&nbsp;|
|延期|DELAY|TEXT|&nbsp;|
|属性|MAILTOPK|SMCODELIST|&nbsp;|
|联系人|MAILTOCONACT|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|由谁取消|默认规则|内容长度必须小于等于[30]|
|预计剩余|预计剩余大于0|预计剩余大于0|
|预计剩余|预计剩余大于等于0|预计剩余大于等于0|
|预计剩余|默认规则|默认规则|
|创建日期|默认规则|默认规则|
|标题颜色|默认规则|内容长度必须小于等于[7]|
|编号|默认规则|默认规则|
|由谁完成|默认规则|内容长度必须小于等于[30]|
|完成者列表|默认规则|内容长度必须小于等于[65535]|
|实际开始|默认规则|默认规则|
|由谁关闭|默认规则|内容长度必须小于等于[30]|
|子状态|默认规则|内容长度必须小于等于[30]|
|关闭原因|默认规则|内容长度必须小于等于[30]|
|最后修改日期|默认规则|默认规则|
|指派日期|默认规则|默认规则|
|优先级|默认规则|默认规则|
|最后修改|默认规则|内容长度必须小于等于[30]|
|任务状态|默认规则|内容长度必须小于等于[6]|
|任务名称|任务名称不大于10|任务名称不大于10|
|任务名称|默认规则|内容长度必须小于等于[255]|
|关闭时间|默认规则|默认规则|
|任务类型|默认规则|内容长度必须小于等于[20]|
|指派给|默认规则|内容长度必须小于等于[30]|
|任务描述|默认规则|内容长度必须小于等于[65535]|
|预计开始|默认规则|默认规则|
|截止日期|截至日期必须大于等于预计开始|截至日期必须大于等于预计开始|
|截止日期|默认规则|默认规则|
|已删除|默认规则|内容长度必须小于等于[1]|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|总计消耗|总计消耗大于等于0|总计消耗大于等于0|
|总计消耗|总计消耗大于0|总消耗大于0|
|总计消耗|默认规则|默认规则|
|最初预计|预计消耗大于等于0|预计消耗大于等于0|
|最初预计|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[30]|
|取消时间|默认规则|默认规则|
|实际完成|默认规则|默认规则|
|所属模块|默认规则|内容长度必须小于等于[60]|
|相关需求|默认规则|内容长度必须小于等于[255]|
|所属项目|默认规则|内容长度必须小于等于[90]|
|产品|默认规则|默认规则|
|需求版本|默认规则|默认规则|
|产品|默认规则|内容长度必须小于等于[90]|
|父任务|默认规则|内容长度必须小于等于[255]|
|所属项目|默认规则|默认规则|
|相关需求|默认规则|默认规则|
|父任务|默认规则|默认规则|
|来源Bug|默认规则|默认规则|
|持续时间|默认规则|内容长度必须小于等于[200]|
|id|默认规则|默认规则|
|模块路径|默认规则|内容长度必须小于等于[255]|
|备注|默认规则|内容长度必须小于等于[1048576]|
|本次消耗|本次消耗必须大于0|本次消耗必须大于0|
|本次消耗|默认规则|默认规则|
|总计耗时|默认规则|默认规则|
|是否子任务|默认规则|内容长度必须小于等于[200]|
|所有模块|默认规则|内容长度必须小于等于[100]|
|多人任务|默认规则|内容长度必须小于等于[200]|
|项目团队成员|默认规则|内容长度必须小于等于[1048576]|
|所属模块|默认规则|内容长度必须小于等于[200]|
|工时|默认规则|内容长度必须小于等于[1048576]|
|是否收藏|默认规则|内容长度必须小于等于[200]|
|任务状态|默认规则|内容长度必须小于等于[200]|
|任务类型|默认规则|内容长度必须小于等于[200]|
|附件|默认规则|内容长度必须小于等于[1000]|
|团队用户|默认规则|内容长度必须小于等于[100]|
|是否完成|默认规则|内容长度必须小于等于[100]|
|回复数量|默认规则|默认规则|
|是否填写描述|默认规则|内容长度必须小于等于[100]|
|最后的更新日期|默认规则|默认规则|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|进度|默认规则|内容长度必须小于等于[200]|
|延期|默认规则|内容长度必须小于等于[200]|
|属性|默认规则|内容长度必须小于等于[200]|
|联系人|默认规则|内容长度必须小于等于[100]|

## 状态控制

|任务状态||是否收藏||任务类型|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   || --------   | ------------|------------|------------|------------|
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_PAUSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许| GetDraft<br>Create<br>计算总耗时<br>删除工时<br>编辑工时<br>工时录入<br>关闭<br>Save<br>UpdateTemp<br>GetDraftTempMajor<br>GetTemp<br>RemoveTemp<br>Remove<br>继续<br>Update<br>激活<br>Get<br>指派/转交<br> | 不允许 | SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_ASSIGN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PROCEED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_CANCEL_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CANCEL_BUT<br> |
|Value||Value2||Value3| 允许| 激活<br>编辑工时<br>删除工时<br>工时录入<br>指派/转交<br>GetTemp<br>Remove<br>Get<br>GetDraftTempMajor<br>Update<br>RemoveTemp<br>GetDraft<br>计算总耗时<br>Create<br>继续<br>Save<br>UpdateTemp<br> | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PROCEED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_START_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_FAVOR_BUT<br> |
|Value||Value2||Value3| 允许| Get<br>暂停<br>Update<br>指派/转交<br>Save<br>UpdateTemp<br>计算总耗时<br>GetTemp<br>继续<br>Create<br>工时录入<br>关闭<br>RemoveTemp<br>取消<br>删除工时<br>GetDraftTempMajor<br>完成<br>Remove<br>GetDraft<br> | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PROCEED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_START_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_ASSIGN_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__STORY_FJTASK_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许| RemoveTemp<br>指派/转交<br>删除工时<br>激活<br>Get<br>GetDraft<br>继续<br>GetTemp<br>Save<br>Remove<br>Update<br>编辑工时<br>工时录入<br>Create<br>关闭<br>GetDraftTempMajor<br>UpdateTemp<br>计算总耗时<br> | 不允许 | SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_PAUSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_START_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PAUSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_PAUSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br> |
|Value||Value2||Value3| 允许| GetTemp<br>编辑工时<br>工时录入<br>GetDraft<br>Create<br>关闭<br>Get<br>GetDraftTempMajor<br>继续<br>取消<br>完成<br>Remove<br>RemoveTemp<br>Save<br>UpdateTemp<br>Update<br>计算总耗时<br>删除工时<br>指派/转交<br> | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_START_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_START_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_FORWARD_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_COMPLETE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_COMPLETE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_NFAVOR_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CANCEL_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FORWARD_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_PAUSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_CONSUM_BUT<br> |
|Value||Value2||Value3| 允许| 工时录入<br>UpdateTemp<br>RemoveTemp<br>Update<br>GetDraftTempMajor<br>编辑工时<br>计算总耗时<br>Remove<br>Create<br>激活<br>继续<br>GetDraft<br>Save<br>取消<br>开始<br>关闭<br>删除工时<br>Get<br>GetTemp<br>指派/转交<br> | 不允许 | SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_FAVOR_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_DELETE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_ASSIGN_BUT<br>SRFUR__TASK_CONSUM_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_COMPLETE_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__STORY_FJTASK_BUT<br>SRFUR__TASK_START_BUT<br>SRFUR__TASK_ACTIVATION_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_CLOSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br> |
|Value||Value2||Value3| 允许|  | 不允许 | SRFUR__TASK_ACTIVATION_BUT<br>SRFUR__TASK_SUBTASKS_BUT<br>SRFUR__TASK_XQCHANGE_BUT<br>SRFUR__TASK_PAUSE_BUT<br>SRFUR__TASK_PROCEED_BUT<br>SRFUR__TASK_NFAVOR_BUT<br>SRFUR__TASK_FORWARD_BUT<br>SRFUR__TASK_CLOSE_BUT<br> |

任务状态

| 状态        |    状态值   |
| --------   |------------|
|未开始|wait|
|进行中|doing|
|已完成|done|
|已暂停|pause|
|已取消|cancel|
|已关闭|closed|
|需求变更|storychange|
是否收藏

任务类型


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
|激活|用户自定义|&nbsp;激活完成、取消、关闭的任务|
|指派/转交|用户自定义|&nbsp;单人任务指派任务 & 多人任务时转交任务|
|计算总耗时|用户自定义|&nbsp;之前消耗：consumed + 本次消耗：currentConsumed = 总耗时：totalTime|
|取消|用户自定义|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|关闭|用户自定义|&nbsp;|
|需求变更确认|用户自定义|&nbsp;|
|删除工时|用户自定义|&nbsp;|
|编辑工时|用户自定义|&nbsp;|
|完成|用户自定义|&nbsp;|
|获取下一个团队成员(完成)|用户自定义|&nbsp;|
|获取联系人|实体处理逻辑|&nbsp;|
|获取团队成员|实体处理逻辑|&nbsp;|
|其他更新|用户自定义|&nbsp;|
|暂停|用户自定义|&nbsp;|
|工时录入|用户自定义|&nbsp;|
|继续|用户自定义|&nbsp;重启挂起的任务|
|Save|内置方法|&nbsp;|
|行为|用户自定义|&nbsp;|
|发送消息前置处理|用户自定义|&nbsp;|
|开始|用户自定义|&nbsp;|
|任务收藏|实体处理逻辑|&nbsp;FAVORITES|
|检查多人任务操作权限|用户自定义|&nbsp;|
|任务收藏|实体处理逻辑|&nbsp;FAVORITES|
|更新需求版本|实体处理逻辑|&nbsp;|

## 处理逻辑
* 获取联系人 (GetUserConcat)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
<center>获取联系人</center>
* 重置工时统计值 (ResetTaskestimate)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 重置工时统计
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|重置工时统计 |
<center>重置工时统计值</center>
* 任务取消收藏 (TaskCancleFavorites)
  
   当前用户收藏相关任务信息

{% plantuml %}
hide footbox

任务 -> 任务: 取消收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|取消收藏 |
<center>任务取消收藏</center>
* 任务收藏 (TaskFavorites)
  
   当前用户收藏相关任务信息

{% plantuml %}
hide footbox

任务 -> 任务: 设置收藏参数
任务 -> 收藏: 创建收藏信息
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|设置收藏参数 |
|2|创建收藏信息 |
<center>任务收藏</center>
* 更新需求版本 (UpdateStoryVersion)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 重置需求版本
任务 -> 任务: 获取需求版本
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|重置需求版本 |
|1|开始 | 
|2|获取需求版本 |
<center>更新需求版本</center>
* 获取团队成员 (getUsernames)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 任务团队
任务 -> 任务: 项目团队
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|任务团队 |
|1|开始 | 
|2|项目团队 |
<center>获取团队成员</center>
* 获取团队成员（草稿） (getUsernamesDraft)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 项目团队
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|项目团队 |
<center>获取团队成员（草稿）</center>
* 行为[Update]主状态拒绝逻辑 (Update__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[Update]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[Update]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[Update]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[Update]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[Update]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[Update]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[Update]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[Update]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[Update]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[Update]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[Update]操作 |
|5|状态[已取消_未收藏_子任务]拒绝[Update]操作 |
|6|状态[已取消_已收藏_多人任务]拒绝[Update]操作 |
|7|状态[已取消_已收藏_父任务]拒绝[Update]操作 |
|8|状态[已取消_已收藏_常规任务]拒绝[Update]操作 |
|9|状态[已取消_已收藏_子任务]拒绝[Update]操作 |
|10|状态[已关闭_未收藏_多人任务]拒绝[Update]操作 |
|11|状态[已关闭_未收藏_父任务]拒绝[Update]操作 |
|12|状态[已关闭_未收藏_子任务]拒绝[Update]操作 |
|13|状态[已关闭_已收藏_多人任务]拒绝[Update]操作 |
|14|状态[已关闭_已收藏_父任务]拒绝[Update]操作 |
|15|状态[已关闭_已收藏_常规任务]拒绝[Update]操作 |
|16|状态[已关闭_已收藏_子任务]拒绝[Update]操作 |
|17|状态[进行中_未收藏_多人任务]拒绝[Update]操作 |
|18|状态[进行中_未收藏_父任务]拒绝[Update]操作 |
|19|状态[进行中_未收藏_子任务]拒绝[Update]操作 |
|20|状态[进行中_已收藏_多人任务]拒绝[Update]操作 |
|21|状态[进行中_已收藏_父任务]拒绝[Update]操作 |
|22|状态[进行中_已收藏_常规任务]拒绝[Update]操作 |
|23|状态[进行中_已收藏_子任务]拒绝[Update]操作 |
|24|状态[已完成_未收藏_多人任务]拒绝[Update]操作 |
|25|状态[已完成_未收藏_父任务]拒绝[Update]操作 |
|26|状态[已完成_未收藏_子任务]拒绝[Update]操作 |
|27|状态[已完成_已收藏_多人任务]拒绝[Update]操作 |
|28|状态[已完成_已收藏_父任务]拒绝[Update]操作 |
|29|状态[已完成_已收藏_常规任务]拒绝[Update]操作 |
|30|状态[已完成_已收藏_子任务]拒绝[Update]操作 |
|31|状态[已暂停_未收藏_多人任务]拒绝[Update]操作 |
|32|状态[已暂停_未收藏_父任务]拒绝[Update]操作 |
|33|状态[已暂停_未收藏_子任务]拒绝[Update]操作 |
|34|状态[已暂停_已收藏_多人任务]拒绝[Update]操作 |
|35|状态[已暂停_已收藏_父任务]拒绝[Update]操作 |
|36|状态[已暂停_已收藏_常规任务]拒绝[Update]操作 |
|37|状态[已暂停_已收藏_子任务]拒绝[Update]操作 |
|38|状态[需求变更_未收藏_多人任务]拒绝[Update]操作 |
|39|状态[需求变更_未收藏_父任务]拒绝[Update]操作 |
|40|状态[需求变更_未收藏_常规任务]拒绝[Update]操作 |
|41|状态[需求变更_未收藏_子任务]拒绝[Update]操作 |
|42|状态[需求变更_已收藏_多人任务]拒绝[Update]操作 |
|43|状态[需求变更_已收藏_父任务]拒绝[Update]操作 |
|44|状态[需求变更_已收藏_常规任务]拒绝[Update]操作 |
|45|状态[需求变更_已收藏_子任务]拒绝[Update]操作 |
|46|状态[未进行_未收藏_多人任务]拒绝[Update]操作 |
|47|状态[未进行_未收藏_父任务]拒绝[Update]操作 |
|48|状态[未进行_未收藏_子任务]拒绝[Update]操作 |
|49|状态[未进行_已收藏_多人任务]拒绝[Update]操作 |
|50|状态[未进行_已收藏_父任务]拒绝[Update]操作 |
|51|状态[未进行_已收藏_常规任务]拒绝[Update]操作 |
|52|状态[未进行_已收藏_子任务]拒绝[Update]操作 |
<center>行为[Update]主状态拒绝逻辑</center>
* 行为[UpdateTemp]主状态拒绝逻辑 (UpdateTemp__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[UpdateTemp]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[UpdateTemp]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[UpdateTemp]操作 |
|5|状态[已取消_未收藏_子任务]拒绝[UpdateTemp]操作 |
|6|状态[已取消_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|7|状态[已取消_已收藏_父任务]拒绝[UpdateTemp]操作 |
|8|状态[已取消_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|9|状态[已取消_已收藏_子任务]拒绝[UpdateTemp]操作 |
|10|状态[已关闭_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|11|状态[已关闭_未收藏_父任务]拒绝[UpdateTemp]操作 |
|12|状态[已关闭_未收藏_子任务]拒绝[UpdateTemp]操作 |
|13|状态[已关闭_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|14|状态[已关闭_已收藏_父任务]拒绝[UpdateTemp]操作 |
|15|状态[已关闭_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|16|状态[已关闭_已收藏_子任务]拒绝[UpdateTemp]操作 |
|17|状态[进行中_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|18|状态[进行中_未收藏_父任务]拒绝[UpdateTemp]操作 |
|19|状态[进行中_未收藏_子任务]拒绝[UpdateTemp]操作 |
|20|状态[进行中_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|21|状态[进行中_已收藏_父任务]拒绝[UpdateTemp]操作 |
|22|状态[进行中_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|23|状态[进行中_已收藏_子任务]拒绝[UpdateTemp]操作 |
|24|状态[已完成_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|25|状态[已完成_未收藏_父任务]拒绝[UpdateTemp]操作 |
|26|状态[已完成_未收藏_子任务]拒绝[UpdateTemp]操作 |
|27|状态[已完成_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|28|状态[已完成_已收藏_父任务]拒绝[UpdateTemp]操作 |
|29|状态[已完成_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|30|状态[已完成_已收藏_子任务]拒绝[UpdateTemp]操作 |
|31|状态[已暂停_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|32|状态[已暂停_未收藏_父任务]拒绝[UpdateTemp]操作 |
|33|状态[已暂停_未收藏_子任务]拒绝[UpdateTemp]操作 |
|34|状态[已暂停_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|35|状态[已暂停_已收藏_父任务]拒绝[UpdateTemp]操作 |
|36|状态[已暂停_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|37|状态[已暂停_已收藏_子任务]拒绝[UpdateTemp]操作 |
|38|状态[需求变更_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|39|状态[需求变更_未收藏_父任务]拒绝[UpdateTemp]操作 |
|40|状态[需求变更_未收藏_常规任务]拒绝[UpdateTemp]操作 |
|41|状态[需求变更_未收藏_子任务]拒绝[UpdateTemp]操作 |
|42|状态[需求变更_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|43|状态[需求变更_已收藏_父任务]拒绝[UpdateTemp]操作 |
|44|状态[需求变更_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|45|状态[需求变更_已收藏_子任务]拒绝[UpdateTemp]操作 |
|46|状态[未进行_未收藏_多人任务]拒绝[UpdateTemp]操作 |
|47|状态[未进行_未收藏_父任务]拒绝[UpdateTemp]操作 |
|48|状态[未进行_未收藏_子任务]拒绝[UpdateTemp]操作 |
|49|状态[未进行_已收藏_多人任务]拒绝[UpdateTemp]操作 |
|50|状态[未进行_已收藏_父任务]拒绝[UpdateTemp]操作 |
|51|状态[未进行_已收藏_常规任务]拒绝[UpdateTemp]操作 |
|52|状态[未进行_已收藏_子任务]拒绝[UpdateTemp]操作 |
<center>行为[UpdateTemp]主状态拒绝逻辑</center>
* 行为[UpdateTempMajor]主状态拒绝逻辑 (UpdateTempMajor__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_未收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[UpdateTempMajor]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[UpdateTempMajor]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|5|状态[已取消_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|6|状态[已取消_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|7|状态[已取消_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|8|状态[已取消_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|9|状态[已取消_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|10|状态[已取消_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
|11|状态[已关闭_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|12|状态[已关闭_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|13|状态[已关闭_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|14|状态[已关闭_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|15|状态[已关闭_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|16|状态[已关闭_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|17|状态[已关闭_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|18|状态[已关闭_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
|19|状态[进行中_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|20|状态[进行中_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|21|状态[进行中_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|22|状态[进行中_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|23|状态[进行中_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|24|状态[进行中_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|25|状态[进行中_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|26|状态[进行中_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
|27|状态[已完成_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|28|状态[已完成_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|29|状态[已完成_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|30|状态[已完成_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|31|状态[已完成_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|32|状态[已完成_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|33|状态[已完成_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|34|状态[已完成_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
|35|状态[已暂停_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|36|状态[已暂停_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|37|状态[已暂停_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|38|状态[已暂停_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|39|状态[已暂停_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|40|状态[已暂停_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|41|状态[已暂停_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|42|状态[已暂停_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
|43|状态[需求变更_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|44|状态[需求变更_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|45|状态[需求变更_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|46|状态[需求变更_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|47|状态[需求变更_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|48|状态[需求变更_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|49|状态[需求变更_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|50|状态[需求变更_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
|51|状态[未进行_未收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|52|状态[未进行_未收藏_父任务]拒绝[UpdateTempMajor]操作 |
|53|状态[未进行_未收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|54|状态[未进行_未收藏_子任务]拒绝[UpdateTempMajor]操作 |
|55|状态[未进行_已收藏_多人任务]拒绝[UpdateTempMajor]操作 |
|56|状态[未进行_已收藏_父任务]拒绝[UpdateTempMajor]操作 |
|57|状态[未进行_已收藏_常规任务]拒绝[UpdateTempMajor]操作 |
|58|状态[未进行_已收藏_子任务]拒绝[UpdateTempMajor]操作 |
<center>行为[UpdateTempMajor]主状态拒绝逻辑</center>
* 行为[Remove]主状态拒绝逻辑 (Remove__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[Remove]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[Remove]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[Remove]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[Remove]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[Remove]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[Remove]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[Remove]操作 |
|5|状态[已取消_未收藏_子任务]拒绝[Remove]操作 |
|6|状态[已取消_已收藏_多人任务]拒绝[Remove]操作 |
|7|状态[已取消_已收藏_父任务]拒绝[Remove]操作 |
|8|状态[已取消_已收藏_常规任务]拒绝[Remove]操作 |
|9|状态[已取消_已收藏_子任务]拒绝[Remove]操作 |
|10|状态[已关闭_未收藏_多人任务]拒绝[Remove]操作 |
|11|状态[已关闭_未收藏_父任务]拒绝[Remove]操作 |
|12|状态[已关闭_未收藏_子任务]拒绝[Remove]操作 |
|13|状态[已关闭_已收藏_多人任务]拒绝[Remove]操作 |
|14|状态[已关闭_已收藏_父任务]拒绝[Remove]操作 |
|15|状态[已关闭_已收藏_常规任务]拒绝[Remove]操作 |
|16|状态[已关闭_已收藏_子任务]拒绝[Remove]操作 |
|17|状态[进行中_未收藏_多人任务]拒绝[Remove]操作 |
|18|状态[进行中_未收藏_父任务]拒绝[Remove]操作 |
|19|状态[进行中_未收藏_子任务]拒绝[Remove]操作 |
|20|状态[进行中_已收藏_多人任务]拒绝[Remove]操作 |
|21|状态[进行中_已收藏_父任务]拒绝[Remove]操作 |
|22|状态[进行中_已收藏_常规任务]拒绝[Remove]操作 |
|23|状态[进行中_已收藏_子任务]拒绝[Remove]操作 |
|24|状态[已完成_未收藏_多人任务]拒绝[Remove]操作 |
|25|状态[已完成_未收藏_父任务]拒绝[Remove]操作 |
|26|状态[已完成_未收藏_子任务]拒绝[Remove]操作 |
|27|状态[已完成_已收藏_多人任务]拒绝[Remove]操作 |
|28|状态[已完成_已收藏_父任务]拒绝[Remove]操作 |
|29|状态[已完成_已收藏_常规任务]拒绝[Remove]操作 |
|30|状态[已完成_已收藏_子任务]拒绝[Remove]操作 |
|31|状态[已暂停_未收藏_多人任务]拒绝[Remove]操作 |
|32|状态[已暂停_未收藏_父任务]拒绝[Remove]操作 |
|33|状态[已暂停_未收藏_子任务]拒绝[Remove]操作 |
|34|状态[已暂停_已收藏_多人任务]拒绝[Remove]操作 |
|35|状态[已暂停_已收藏_父任务]拒绝[Remove]操作 |
|36|状态[已暂停_已收藏_常规任务]拒绝[Remove]操作 |
|37|状态[已暂停_已收藏_子任务]拒绝[Remove]操作 |
|38|状态[需求变更_未收藏_多人任务]拒绝[Remove]操作 |
|39|状态[需求变更_未收藏_父任务]拒绝[Remove]操作 |
|40|状态[需求变更_未收藏_常规任务]拒绝[Remove]操作 |
|41|状态[需求变更_未收藏_子任务]拒绝[Remove]操作 |
|42|状态[需求变更_已收藏_多人任务]拒绝[Remove]操作 |
|43|状态[需求变更_已收藏_父任务]拒绝[Remove]操作 |
|44|状态[需求变更_已收藏_常规任务]拒绝[Remove]操作 |
|45|状态[需求变更_已收藏_子任务]拒绝[Remove]操作 |
|46|状态[未进行_未收藏_多人任务]拒绝[Remove]操作 |
|47|状态[未进行_未收藏_父任务]拒绝[Remove]操作 |
|48|状态[未进行_未收藏_子任务]拒绝[Remove]操作 |
|49|状态[未进行_已收藏_多人任务]拒绝[Remove]操作 |
|50|状态[未进行_已收藏_父任务]拒绝[Remove]操作 |
|51|状态[未进行_已收藏_常规任务]拒绝[Remove]操作 |
|52|状态[未进行_已收藏_子任务]拒绝[Remove]操作 |
<center>行为[Remove]主状态拒绝逻辑</center>
* 行为[RemoveTemp]主状态拒绝逻辑 (RemoveTemp__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[RemoveTemp]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[RemoveTemp]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[RemoveTemp]操作 |
|5|状态[已取消_未收藏_子任务]拒绝[RemoveTemp]操作 |
|6|状态[已取消_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|7|状态[已取消_已收藏_父任务]拒绝[RemoveTemp]操作 |
|8|状态[已取消_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|9|状态[已取消_已收藏_子任务]拒绝[RemoveTemp]操作 |
|10|状态[已关闭_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|11|状态[已关闭_未收藏_父任务]拒绝[RemoveTemp]操作 |
|12|状态[已关闭_未收藏_子任务]拒绝[RemoveTemp]操作 |
|13|状态[已关闭_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|14|状态[已关闭_已收藏_父任务]拒绝[RemoveTemp]操作 |
|15|状态[已关闭_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|16|状态[已关闭_已收藏_子任务]拒绝[RemoveTemp]操作 |
|17|状态[进行中_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|18|状态[进行中_未收藏_父任务]拒绝[RemoveTemp]操作 |
|19|状态[进行中_未收藏_子任务]拒绝[RemoveTemp]操作 |
|20|状态[进行中_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|21|状态[进行中_已收藏_父任务]拒绝[RemoveTemp]操作 |
|22|状态[进行中_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|23|状态[进行中_已收藏_子任务]拒绝[RemoveTemp]操作 |
|24|状态[已完成_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|25|状态[已完成_未收藏_父任务]拒绝[RemoveTemp]操作 |
|26|状态[已完成_未收藏_子任务]拒绝[RemoveTemp]操作 |
|27|状态[已完成_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|28|状态[已完成_已收藏_父任务]拒绝[RemoveTemp]操作 |
|29|状态[已完成_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|30|状态[已完成_已收藏_子任务]拒绝[RemoveTemp]操作 |
|31|状态[已暂停_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|32|状态[已暂停_未收藏_父任务]拒绝[RemoveTemp]操作 |
|33|状态[已暂停_未收藏_子任务]拒绝[RemoveTemp]操作 |
|34|状态[已暂停_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|35|状态[已暂停_已收藏_父任务]拒绝[RemoveTemp]操作 |
|36|状态[已暂停_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|37|状态[已暂停_已收藏_子任务]拒绝[RemoveTemp]操作 |
|38|状态[需求变更_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|39|状态[需求变更_未收藏_父任务]拒绝[RemoveTemp]操作 |
|40|状态[需求变更_未收藏_常规任务]拒绝[RemoveTemp]操作 |
|41|状态[需求变更_未收藏_子任务]拒绝[RemoveTemp]操作 |
|42|状态[需求变更_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|43|状态[需求变更_已收藏_父任务]拒绝[RemoveTemp]操作 |
|44|状态[需求变更_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|45|状态[需求变更_已收藏_子任务]拒绝[RemoveTemp]操作 |
|46|状态[未进行_未收藏_多人任务]拒绝[RemoveTemp]操作 |
|47|状态[未进行_未收藏_父任务]拒绝[RemoveTemp]操作 |
|48|状态[未进行_未收藏_子任务]拒绝[RemoveTemp]操作 |
|49|状态[未进行_已收藏_多人任务]拒绝[RemoveTemp]操作 |
|50|状态[未进行_已收藏_父任务]拒绝[RemoveTemp]操作 |
|51|状态[未进行_已收藏_常规任务]拒绝[RemoveTemp]操作 |
|52|状态[未进行_已收藏_子任务]拒绝[RemoveTemp]操作 |
<center>行为[RemoveTemp]主状态拒绝逻辑</center>
* 行为[RemoveTempMajor]主状态拒绝逻辑 (RemoveTempMajor__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_未收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[RemoveTempMajor]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[RemoveTempMajor]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|5|状态[已取消_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|6|状态[已取消_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|7|状态[已取消_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|8|状态[已取消_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|9|状态[已取消_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|10|状态[已取消_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
|11|状态[已关闭_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|12|状态[已关闭_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|13|状态[已关闭_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|14|状态[已关闭_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|15|状态[已关闭_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|16|状态[已关闭_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|17|状态[已关闭_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|18|状态[已关闭_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
|19|状态[进行中_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|20|状态[进行中_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|21|状态[进行中_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|22|状态[进行中_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|23|状态[进行中_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|24|状态[进行中_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|25|状态[进行中_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|26|状态[进行中_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
|27|状态[已完成_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|28|状态[已完成_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|29|状态[已完成_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|30|状态[已完成_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|31|状态[已完成_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|32|状态[已完成_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|33|状态[已完成_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|34|状态[已完成_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
|35|状态[已暂停_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|36|状态[已暂停_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|37|状态[已暂停_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|38|状态[已暂停_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|39|状态[已暂停_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|40|状态[已暂停_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|41|状态[已暂停_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|42|状态[已暂停_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
|43|状态[需求变更_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|44|状态[需求变更_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|45|状态[需求变更_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|46|状态[需求变更_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|47|状态[需求变更_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|48|状态[需求变更_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|49|状态[需求变更_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|50|状态[需求变更_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
|51|状态[未进行_未收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|52|状态[未进行_未收藏_父任务]拒绝[RemoveTempMajor]操作 |
|53|状态[未进行_未收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|54|状态[未进行_未收藏_子任务]拒绝[RemoveTempMajor]操作 |
|55|状态[未进行_已收藏_多人任务]拒绝[RemoveTempMajor]操作 |
|56|状态[未进行_已收藏_父任务]拒绝[RemoveTempMajor]操作 |
|57|状态[未进行_已收藏_常规任务]拒绝[RemoveTempMajor]操作 |
|58|状态[未进行_已收藏_子任务]拒绝[RemoveTempMajor]操作 |
<center>行为[RemoveTempMajor]主状态拒绝逻辑</center>
* 行为[updateStoryVersion]主状态拒绝逻辑 (UpdateStoryVersion__MSDeny)
  
   

{% plantuml %}
hide footbox

任务 -> 任务: 准备临时参数
任务 -> 任务: 获取数据
任务 -> : 状态[已取消_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已取消_已收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已关闭_已收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[进行中_已收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已完成_已收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[已暂停_已收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[需求变更_已收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_未收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_未收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_未收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_未收藏_子任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_已收藏_多人任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_已收藏_父任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_已收藏_常规任务]拒绝[更新需求版本]操作
任务 -> : 状态[未进行_已收藏_子任务]拒绝[更新需求版本]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[已取消_未收藏_多人任务]拒绝[更新需求版本]操作 |
|4|状态[已取消_未收藏_父任务]拒绝[更新需求版本]操作 |
|5|状态[已取消_未收藏_常规任务]拒绝[更新需求版本]操作 |
|6|状态[已取消_未收藏_子任务]拒绝[更新需求版本]操作 |
|7|状态[已取消_已收藏_多人任务]拒绝[更新需求版本]操作 |
|8|状态[已取消_已收藏_父任务]拒绝[更新需求版本]操作 |
|9|状态[已取消_已收藏_常规任务]拒绝[更新需求版本]操作 |
|10|状态[已取消_已收藏_子任务]拒绝[更新需求版本]操作 |
|11|状态[已关闭_未收藏_多人任务]拒绝[更新需求版本]操作 |
|12|状态[已关闭_未收藏_父任务]拒绝[更新需求版本]操作 |
|13|状态[已关闭_未收藏_常规任务]拒绝[更新需求版本]操作 |
|14|状态[已关闭_未收藏_子任务]拒绝[更新需求版本]操作 |
|15|状态[已关闭_已收藏_多人任务]拒绝[更新需求版本]操作 |
|16|状态[已关闭_已收藏_父任务]拒绝[更新需求版本]操作 |
|17|状态[已关闭_已收藏_常规任务]拒绝[更新需求版本]操作 |
|18|状态[已关闭_已收藏_子任务]拒绝[更新需求版本]操作 |
|19|状态[进行中_未收藏_多人任务]拒绝[更新需求版本]操作 |
|20|状态[进行中_未收藏_父任务]拒绝[更新需求版本]操作 |
|21|状态[进行中_未收藏_常规任务]拒绝[更新需求版本]操作 |
|22|状态[进行中_未收藏_子任务]拒绝[更新需求版本]操作 |
|23|状态[进行中_已收藏_多人任务]拒绝[更新需求版本]操作 |
|24|状态[进行中_已收藏_父任务]拒绝[更新需求版本]操作 |
|25|状态[进行中_已收藏_常规任务]拒绝[更新需求版本]操作 |
|26|状态[进行中_已收藏_子任务]拒绝[更新需求版本]操作 |
|27|状态[已完成_未收藏_多人任务]拒绝[更新需求版本]操作 |
|28|状态[已完成_未收藏_父任务]拒绝[更新需求版本]操作 |
|29|状态[已完成_未收藏_常规任务]拒绝[更新需求版本]操作 |
|30|状态[已完成_未收藏_子任务]拒绝[更新需求版本]操作 |
|31|状态[已完成_已收藏_多人任务]拒绝[更新需求版本]操作 |
|32|状态[已完成_已收藏_父任务]拒绝[更新需求版本]操作 |
|33|状态[已完成_已收藏_常规任务]拒绝[更新需求版本]操作 |
|34|状态[已完成_已收藏_子任务]拒绝[更新需求版本]操作 |
|35|状态[已暂停_未收藏_多人任务]拒绝[更新需求版本]操作 |
|36|状态[已暂停_未收藏_父任务]拒绝[更新需求版本]操作 |
|37|状态[已暂停_未收藏_常规任务]拒绝[更新需求版本]操作 |
|38|状态[已暂停_未收藏_子任务]拒绝[更新需求版本]操作 |
|39|状态[已暂停_已收藏_多人任务]拒绝[更新需求版本]操作 |
|40|状态[已暂停_已收藏_父任务]拒绝[更新需求版本]操作 |
|41|状态[已暂停_已收藏_常规任务]拒绝[更新需求版本]操作 |
|42|状态[已暂停_已收藏_子任务]拒绝[更新需求版本]操作 |
|43|状态[需求变更_未收藏_多人任务]拒绝[更新需求版本]操作 |
|44|状态[需求变更_未收藏_父任务]拒绝[更新需求版本]操作 |
|45|状态[需求变更_未收藏_常规任务]拒绝[更新需求版本]操作 |
|46|状态[需求变更_未收藏_子任务]拒绝[更新需求版本]操作 |
|47|状态[需求变更_已收藏_多人任务]拒绝[更新需求版本]操作 |
|48|状态[需求变更_已收藏_父任务]拒绝[更新需求版本]操作 |
|49|状态[需求变更_已收藏_常规任务]拒绝[更新需求版本]操作 |
|50|状态[需求变更_已收藏_子任务]拒绝[更新需求版本]操作 |
|51|状态[未进行_未收藏_多人任务]拒绝[更新需求版本]操作 |
|52|状态[未进行_未收藏_父任务]拒绝[更新需求版本]操作 |
|53|状态[未进行_未收藏_常规任务]拒绝[更新需求版本]操作 |
|54|状态[未进行_未收藏_子任务]拒绝[更新需求版本]操作 |
|55|状态[未进行_已收藏_多人任务]拒绝[更新需求版本]操作 |
|56|状态[未进行_已收藏_父任务]拒绝[更新需求版本]操作 |
|57|状态[未进行_已收藏_常规任务]拒绝[更新需求版本]操作 |
|58|状态[未进行_已收藏_子任务]拒绝[更新需求版本]操作 |
<center>行为[updateStoryVersion]主状态拒绝逻辑</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|AssignedToMyTask|指派给我任务([MYSQL5](../../appendix/query_MYSQL5.md#Task_AssignedToMyTask))|否|&nbsp;指派给我任务（移动端）|
|AssignedToMyTaskPc|指派给我任务（PC）([MYSQL5](../../appendix/query_MYSQL5.md#Task_AssignedToMyTaskPc))|否|&nbsp;指派给我任务（移动端）|
|BugTask|Bug相关任务([MYSQL5](../../appendix/query_MYSQL5.md#Task_BugTask))|否|&nbsp;|
|ByModule|通过模块查询([MYSQL5](../../appendix/query_MYSQL5.md#Task_ByModule))|否|&nbsp;|
|ChildTask|子任务([MYSQL5](../../appendix/query_MYSQL5.md#Task_ChildTask))|否|&nbsp;|
|ChildTaskTree|子任务（树）([MYSQL5](../../appendix/query_MYSQL5.md#Task_ChildTaskTree))|否|&nbsp;|
|CurFinishTask|用户年度完成任务([MYSQL5](../../appendix/query_MYSQL5.md#Task_CurFinishTask))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Task_Default))|否|&nbsp;|
|DefaultRow|DefaultRow([MYSQL5](../../appendix/query_MYSQL5.md#Task_DefaultRow))|否|&nbsp;|
|MyFavorites|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#Task_MyFavorites))|否|&nbsp;|
|ProjectTask|项目任务([MYSQL5](../../appendix/query_MYSQL5.md#Task_ProjectTask))|否|&nbsp;|
|RootTask|根任务([MYSQL5](../../appendix/query_MYSQL5.md#Task_RootTask))|否|&nbsp;|
|TodoListTask|todo任务列表查询([MYSQL5](../../appendix/query_MYSQL5.md#Task_TodoListTask))|否|&nbsp;|
|TypeGroup|任务类型分组([MYSQL5](../../appendix/query_MYSQL5.md#Task_TypeGroup))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Task_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AssignedToMyTask|指派给我任务|AssignedToMyTask|否|&nbsp;|
|AssignedToMyTaskPc|指派给我任务（PC）|AssignedToMyTaskPc|否|&nbsp;|
|BugTask|Bug相关任务|BugTask|否|&nbsp;|
|ByModule|通过模块查询|ByModule|否|&nbsp;|
|ChildTask|子任务|ChildTask|否|&nbsp;|
|ChildTaskTree|子任务（树）|ChildTaskTree|否|&nbsp;|
|CurFinishTask|用户年度完成任务|CurFinishTask|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|DefaultRow|DefaultRow|DefaultRow|否|&nbsp;|
|MyFavorites|我的收藏|MyFavorites|否|&nbsp;|
|ProjectTask|项目任务|ProjectTask|否|&nbsp;|
|RootTask|根任务|RootTask|否|&nbsp;|
|TodoListTask|todo列表查询|TodoListTask|否|&nbsp;|
|TypeGroup|任务类型分组|TypeGroup|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|由谁取消(CANCELEDBY)|EQ|
|标题颜色(COLOR)|EQ|
|编号(ID)|NOTEQ|
|由谁完成(FINISHEDBY)|EQ|
|由谁关闭(CLOSEDBY)|EQ|
|关闭原因(CLOSEDREASON)|EQ|
|指派日期(ASSIGNEDDATE)|GTANDEQ|
|优先级(PRI)|EQ|
|优先级(PRI)|IN|
|最后修改(LASTEDITEDBY)|EQ|
|任务状态(STATUS)|EQ|
|任务状态(STATUS)|IN|
|任务状态(STATUS)|NOTEQ|
|任务名称(NAME)|LIKE|
|关闭时间(CLOSEDDATE)|LTANDEQ|
|任务类型(TYPE)|EQ|
|指派给(ASSIGNEDTO)|EQ|
|指派给(ASSIGNEDTO)|IN|
|预计开始(ESTSTARTED)|GTANDEQ|
|预计开始(ESTSTARTED)|LTANDEQ|
|由谁创建(OPENEDBY)|EQ|
|实际完成(FINISHEDDATE)|LTANDEQ|
|所属模块(MODULENAME)|EQ|
|所属模块(MODULENAME)|LIKE|
|相关需求(STORYNAME)|EQ|
|相关需求(STORYNAME)|LIKE|
|所属项目(PROJECTNAME)|EQ|
|所属项目(PROJECTNAME)|LIKE|
|产品(PRODUCT)|EQ|
|父任务(PARENTNAME)|EQ|
|父任务(PARENTNAME)|LIKE|
|所属项目(PROJECT)|EQ|
|相关需求(STORY)|EQ|
|父任务(PARENT)|EQ|
|父任务(PARENT)|GTANDEQ|
|来源Bug(FROMBUG)|EQ|
|id(MODULE)|EQ|
|模块路径(PATH)|LIKE|
|任务状态(STATUS1)|EQ|
|任务类型(TASKTYPE)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|ID|P|任务名称|任务状态|任务状态|指派给|完成者|预计|消耗|剩余|截止日期|是否收藏|任务类型|产品|所属项目|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - | - | - | - |
