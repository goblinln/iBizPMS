# 任务团队(IBZ_TASKTEAM)

  任务团队

## 关系
{% plantuml %}
任务 *-- 任务团队 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|最初预计|ESTIMATE|FLOAT|&nbsp;|
|用户|USERNAME|TEXT|&nbsp;|
|加盟日|JOIN|DATE|&nbsp;|
|可用工时/天|HOURS|FLOAT|&nbsp;|
|用户|ACCOUNT|TEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;task需要排序处理人顺序,project使用id排序|
|预计剩余|LEFT|FLOAT|&nbsp;|
|总计消耗|CONSUMED|FLOAT|&nbsp;|
|受限用户|LIMITED|SSCODELIST|&nbsp;|
|角色|ROLE|TEXT|&nbsp;自动带入用户职位，可修改|
|编号|ID|ACID|&nbsp;|
|可用工日|DAYS|INT|&nbsp;|
|总计可用|TOTAL|INT|&nbsp;|
|团队类型|TYPE|SSCODELIST|&nbsp;|
|编号|ROOT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|最初预计|预计消耗大于等于0|预计大于等于0|
|最初预计|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[200]|
|加盟日|默认规则|默认规则|
|可用工时/天|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[30]|
|排序|默认规则|默认规则|
|预计剩余|预计剩余大于等于0|预计剩余大于等于0|
|预计剩余|默认规则|默认规则|
|总计消耗|总计消耗大于等于0|总计消耗大于等于0|
|总计消耗|默认规则|默认规则|
|受限用户|默认规则|内容长度必须小于等于[8]|
|角色|默认规则|内容长度必须小于等于[30]|
|编号|默认规则|默认规则|
|可用工日|默认规则|默认规则|
|总计可用|默认规则|默认规则|
|团队类型|默认规则|内容长度必须小于等于[7]|
|编号|默认规则|默认规则|

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
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#TaskTeam_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TaskTeam_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|用户(ACCOUNT)|LIKE|
|受限用户(LIMITED)|EQ|
|编号(ID)|EQ|
|团队类型(TYPE)|EQ|
|编号(ROOT)|EQ|

## 导入模式
无


## 导出模式
无
