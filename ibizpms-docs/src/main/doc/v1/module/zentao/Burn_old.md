# burn(ZT_BURN)

  

## 关系
{% plantuml %}
项目 *-- burn 
任务 *-- burn 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|周末|ISWEEKEND|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;定时任务，每天一个有task的project对应一个图，也可手动刷新|
|虚拟主键|ID|TEXT|&nbsp;|
|总计消耗|CONSUMED|PICKUPDATA|&nbsp;|
|预计剩余|LEFT|PICKUPDATA|&nbsp;|
|最初预计|ESTIMATE|PICKUPDATA|&nbsp;|
|所属项目|PROJECT|PICKUP|&nbsp;|
|任务|TASK|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|周末|默认规则|内容长度必须小于等于[200]|
|日期|默认规则|默认规则|
|虚拟主键|默认规则|内容长度必须小于等于[200]|
|总计消耗|默认规则|默认规则|
|预计剩余|默认规则|默认规则|
|最初预计|默认规则|默认规则|
|所属项目|默认规则|默认规则|
|任务|默认规则|默认规则|

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
|更新燃尽图|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Burn_Default))|是|&nbsp;|
|ESTIMATEANDLEFT|燃尽图预计（含周末）([MYSQL5](../../appendix/query_MYSQL5.md#Burn_ESTIMATEANDLEFT))|否|&nbsp;燃尽图预计（含周末）|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Burn_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ESTIMATEANDLEFT|燃尽图预计（含周末）|ESTIMATEANDLEFT|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|周末(ISWEEKEND)|EQ|
|周末(ISWEEKEND)|IN|
|所属项目(PROJECT)|EQ|
|任务(TASK)|EQ|

## 导入模式
无


## 导出模式
无
