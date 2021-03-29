# 产品周报(IBIZPRO_PRODUCTWEEKLY)

  

## 关系
{% plantuml %}
产品 *-- 产品周报 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|产品负责人|PO|TEXT|&nbsp;|
|产品周报标识|IBIZPRO_PRODUCTWEEKLYID|ACID|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|任务|TASKS|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|产品周报名称|IBIZPRO_PRODUCTDAILYNAME|TEXT|&nbsp;|
|开始统计|BEGINDATESTATS|TEXT|&nbsp;|
|总工时|TOTALESTIMATES|FLOAT|&nbsp;|
|结束统计|ENDDATESTATS|TEXT|&nbsp;|
|产品名称|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|编号|PRODUCT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|产品负责人|默认规则|内容长度必须小于等于[100]|
|产品周报标识|默认规则|默认规则|
|更新时间|默认规则|默认规则|
|任务|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|
|日期|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|产品周报名称|默认规则|内容长度必须小于等于[200]|
|开始统计|默认规则|内容长度必须小于等于[100]|
|总工时|默认规则|默认规则|
|结束统计|默认规则|内容长度必须小于等于[100]|
|产品名称|默认规则|内容长度必须小于等于[90]|
|编号|默认规则|默认规则|

## 状态控制

无

产品周报标识


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
|统计产品周报|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbizproProductWeekly_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbizproProductWeekly_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|产品负责人(PO)|EQ|
|产品周报名称(IBIZPRO_PRODUCTDAILYNAME)|LIKE|
|产品名称(PRODUCTNAME)|EQ|
|产品名称(PRODUCTNAME)|LIKE|
|编号(PRODUCT)|EQ|

## 导入模式
无


## 导出模式
无
