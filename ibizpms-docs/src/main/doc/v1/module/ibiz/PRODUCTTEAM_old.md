# 产品团队(IBZ_PRODUCTTEAM)

  

## 关系
{% plantuml %}
产品 *-- 产品团队 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|成员状态|TEAMSTATUS|TEXT|&nbsp;|
|用户|ACCOUNT|TEXT|&nbsp;|
|加盟日|JOIN|DATE|&nbsp;|
|预计剩余|LEFT|FLOAT|&nbsp;|
|总计可用|TOTAL|INT|&nbsp;|
|结束时间|END|DATE|&nbsp;|
|用户|USERNAME|TEXT|&nbsp;|
|最初预计|ESTIMATE|FLOAT|&nbsp;|
|可用工时/天|HOURS|FLOAT|&nbsp;|
|任务数|TASKCNT|INT|&nbsp;|
|团队类型|TYPE|SSCODELIST|&nbsp;|
|总计消耗|CONSUMED|FLOAT|&nbsp;|
|可用工日|DAYS|INT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|当前负责人|LEADINGCADRE|SSCODELIST|&nbsp;|
|受限用户|LIMITED|SSCODELIST|&nbsp;|
|排序|ORDER|INT|&nbsp;task需要排序处理人顺序,project使用id排序|
|角色|ROLE|TEXT|&nbsp;自动带入用户职位，可修改|
|产品编号|ROOT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|成员状态|默认规则|内容长度必须小于等于[100]|
|用户|默认规则|内容长度必须小于等于[30]|
|加盟日|默认规则|默认规则|
|预计剩余|默认规则|默认规则|
|总计可用|默认规则|默认规则|
|结束时间|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[200]|
|最初预计|默认规则|默认规则|
|可用工时/天|默认规则|默认规则|
|任务数|默认规则|默认规则|
|团队类型|默认规则|内容长度必须小于等于[7]|
|总计消耗|默认规则|默认规则|
|可用工日|默认规则|默认规则|
|编号|默认规则|默认规则|
|当前负责人|默认规则|内容长度必须小于等于[60]|
|受限用户|默认规则|内容长度必须小于等于[8]|
|排序|默认规则|默认规则|
|角色|默认规则|内容长度必须小于等于[30]|
|产品编号|默认规则|默认规则|

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
|PmsEe团队管理过滤|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 获取项目的可用工日 (GetProjectDays)
  
   

{% plantuml %}
hide footbox

产品 -> 产品团队: 回填
产品团队 -> 产品: 获取产品详情
产品团队 -> 产品团队: 设置产品参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|回填 |
|2|获取产品详情 |
|2|开始 | 
|3|设置产品参数 |
<center>获取项目的可用工日</center>
* 获取成员角色 (GetUserRole)
  
   

{% plantuml %}
hide footbox

产品团队 -> 产品团队: 获取角色成员
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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PRODUCTTEAM_Default))|是|&nbsp;|
|ProductTeamInfo|产品团队成员信息([MYSQL5](../../appendix/query_MYSQL5.md#PRODUCTTEAM_ProductTeamInfo))|否|&nbsp;|
|ProjectApp|项目立项([MYSQL5](../../appendix/query_MYSQL5.md#PRODUCTTEAM_ProjectApp))|否|&nbsp;|
|ProjectProductTeam|项目产品团队([MYSQL5](../../appendix/query_MYSQL5.md#PRODUCTTEAM_ProjectProductTeam))|否|&nbsp;|
|RowEditDefaultProductTeam|产品团队管理([MYSQL5](../../appendix/query_MYSQL5.md#PRODUCTTEAM_RowEditDefaultProductTeam))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PRODUCTTEAM_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|ProductTeamInfo|产品团队成员信息|ProductTeamInfo|否|&nbsp;|
|ProjectApp|项目立项|ProjectApp|否|&nbsp;|
|ProjectProductTeam|项目产品团队|ProjectProductTeam|否|&nbsp;|
|RowEditDefaultProductTeam|产品团队管理|RowEditDefaultProductTeam|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|成员状态(TEAMSTATUS)|EQ|
|用户(ACCOUNT)|EQ|
|用户(ACCOUNT)|LIKE|
|加盟日(JOIN)|GTANDEQ|
|结束时间(END)|LTANDEQ|
|团队类型(TYPE)|EQ|
|当前负责人(LEADINGCADRE)|EQ|
|受限用户(LIMITED)|EQ|
|产品编号(ROOT)|EQ|
|产品编号(ROOT)|IN|

## 导入模式
无


## 导出模式
无
