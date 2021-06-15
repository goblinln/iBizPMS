# 项目团队(IBZ_PROJECTTEAM)

  

## 关系
{% plantuml %}
项目 *-- 项目团队 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|角色|ROLE|TEXT|&nbsp;自动带入用户职位，可修改|
|受限用户|LIMITED|SSCODELIST|&nbsp;|
|总计可用|TOTAL|INT|&nbsp;|
|用户|USERNAME|TEXT|&nbsp;|
|可用工日|DAYS|INT|&nbsp;|
|退场时间|EXITDATE|DATE|&nbsp;|
|团队类型|TYPE|SSCODELIST|&nbsp;|
|排序|ORDER|INT|&nbsp;task需要排序处理人顺序,project使用id排序|
|编号|ID|ACID|&nbsp;|
|总计消耗|CONSUMED|FLOAT|&nbsp;|
|用户|ACCOUNT|TEXT|&nbsp;|
|最初预计|ESTIMATE|FLOAT|&nbsp;|
|加盟日|JOIN|DATE|&nbsp;|
|可用工时/天|HOURS|FLOAT|&nbsp;|
|任务数|TASKCNT|INT|&nbsp;|
|预计剩余|LEFT|FLOAT|&nbsp;|
|项目经理|PM|PICKUPDATA|&nbsp;|
|所属项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|项目编号|ROOT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|角色|默认规则|内容长度必须小于等于[30]|
|受限用户|默认规则|内容长度必须小于等于[8]|
|总计可用|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[200]|
|可用工日|默认规则|默认规则|
|退场时间|默认规则|默认规则|
|团队类型|默认规则|内容长度必须小于等于[7]|
|排序|默认规则|默认规则|
|编号|默认规则|默认规则|
|总计消耗|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[30]|
|最初预计|默认规则|默认规则|
|加盟日|默认规则|默认规则|
|可用工时/天|可用工时/天必须小于等于24|数值必须大于等于[0.0]且小于等于[24.0]|
|可用工时/天|默认规则|默认规则|
|任务数|默认规则|默认规则|
|预计剩余|默认规则|默认规则|
|项目经理|默认规则|内容长度必须小于等于[30]|
|所属项目|默认规则|内容长度必须小于等于[90]|
|项目编号|默认规则|默认规则|

## 状态控制

无


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
|CheckKey|内置方法|&nbsp;|
|获取成员角色|用户自定义|&nbsp;根据成员获取成员的角色信息|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 获取项目的可用工日 (GetProjectDays)
  
   

{% plantuml %}
hide footbox

项目团队 -> 项目团队: 设置项目参数
项目 -> 项目团队: 回填
项目团队 -> 项目: 获取项目详情
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|设置项目参数 |
|2|回填 |
|3|获取项目详情 |
<center>获取项目的可用工日</center>
* 获取成员角色 (GetUserRole)
  
   

{% plantuml %}
hide footbox

项目团队 -> 项目团队: 获取角色成员
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取角色成员 |
<center>获取成员角色</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTeam_Default))|是|&nbsp;|
|ProjectAllMembers|项目所有成员([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTeam_ProjectAllMembers))|否|&nbsp;|
|ProjectTeamPm|项目成员（项目经理）([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTeam_ProjectTeamPm))|否|&nbsp;|
|RowEditDefault|行编辑查询([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTeam_RowEditDefault))|否|&nbsp;|
|TaskCntEstimateConsumedLeft|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTeam_TaskCntEstimateConsumedLeft))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTeam_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ProjectTeamPm|项目成员（项目经理）|ProjectTeamPm|否|&nbsp;|
|RowEditDefault|行编辑查询|RowEditDefault|否|&nbsp;|
|TaskCntEstimateConsumedLeft|数据查询|TaskCntEstimateConsumedLeft|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|受限用户(LIMITED)|EQ|
|团队类型(TYPE)|EQ|
|编号(ID)|EQ|
|用户(ACCOUNT)|EQ|
|用户(ACCOUNT)|LIKE|
|所属项目(PROJECTNAME)|EQ|
|所属项目(PROJECTNAME)|LIKE|
|项目编号(ROOT)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|用户|角色|加盟日|可用工日|可用工时/天|总计可用|受限用户|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - |
