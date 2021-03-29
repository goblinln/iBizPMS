# 系统应用(PSSYSAPP)

  

## 关系
{% plantuml %}
系统应用 *-- 系统运行会话 
系统应用 *-- 系统运行会话 
系统服务接口 *-- 系统应用 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|启用统一认证|UACLOGIN|YESNO|&nbsp;|
|前台技术架构|PSPFID|TEXT|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|自动添加应用视图|AUTOADDAPPVIEW|YESNO|&nbsp;|
|启用本地服务|ENALOCALSERVICE|YESNO|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|只发布系统引用视图（废弃）|PUBSYSREFVIEWONLY|YESNO|&nbsp;|
|视图主菜单方向|MAINMENUSIDE|SSCODELIST|&nbsp;|
|系统应用标识|PSSYSAPPID|GUID|&nbsp;|
|代码目录|CODEFOLDER|TEXT|&nbsp;|
|表格适应屏宽|GRIDFORCEFIT|YESNO|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|表单项无权限显示模式|FINOPRIVDM|NSCODELIST|&nbsp;|
|自定义参数|USERPARAMS|TEXT|&nbsp;|
|移动端方向设置|ORIENTATIONMODE|SSCODELIST|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|删除模式|REMOVEFLAG|NSCODELIST|&nbsp;|
|启用故事板|ENABLESTORYBOARD|YESNO|&nbsp;|
|应用主题|PSSTUDIOTHEMENAME|TEXT|&nbsp;|
|支持动态系统|ENABLEDYNASYS|YESNO|&nbsp;|
|是否启用|VALIDFLAG|YESNO|&nbsp;|
|图标文件|ICONFILE|TEXT|&nbsp;|
|应用标记|APPTAG|TEXT|&nbsp;|
|默认应用|DEFAULTPUB|YESNO|&nbsp;|
|应用标记2|APPTAG2|TEXT|&nbsp;|
|服务代码名称|SERVICECODENAME|TEXT|&nbsp;|
|防止XSS攻击|PREVENTXSS|YESNO|&nbsp;|
|应用类型|PSAPPTYPENAME|TEXT|&nbsp;|
|应用CDN|PSPFCDNID|TEXT|&nbsp;|
|表格列启用链接|GRIDCOLENABLELINK|NSCODELIST|&nbsp;|
|应用主题|PSSTUDIOTHEMEID|TEXT|&nbsp;|
|转换12列至24列布局|ENABLEC12TOC24|YESNO|&nbsp;|
|应用编号|APPSN|TEXT|&nbsp;|
|应用标记4|APPTAG4|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|表格行激活模式|GRIDROWACTIVEMODE|NSCODELIST|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|代码名称|APPPKGNAME|TEXT|&nbsp;|
|中文名称|LOGICNAME|TEXT|&nbsp;|
|应用样式|PSPFSTYLEID|TEXT|&nbsp;|
|按钮无权限显示模式|BTNNOPRIVDM|NSCODELIST|&nbsp;|
|应用CDN|PSPFCDNNAME|TEXT|&nbsp;|
|只发布引用视图|PUBREFVIEWONLY|YESNO|&nbsp;|
|应用模式|APPMODE|SSCODELIST|&nbsp;|
|系统应用名称|PSSYSAPPNAME|TEXT|&nbsp;|
|应用类型|PSAPPTYPEID|TEXT|&nbsp;|
|应用目录|APPFOLDER|TEXT|&nbsp;|
|起始页图片文件|STARTPAGEFILE|TEXT|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|应用标记3|APPTAG3|TEXT|&nbsp;|
|应用样式参数|PFSTYLEPARAM|LONGTEXT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|内建界面式样|UISTYLE|SSCODELIST|&nbsp;|
|表格列无权限显示模式|GCNOPRIVDM|NSCODELIST|&nbsp;|
|输出表单项更新权限标记|FIUPDATEPRIVTAG|YESNO|&nbsp;|
|默认服务接口|PSSYSSERVICEAPINAME|PICKUPTEXT|&nbsp;|
|默认服务接口|PSSYSSERVICEAPIID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|启用统一认证|默认规则|默认规则|
|前台技术架构|默认规则|内容长度必须小于等于[100]|
|用户标记|默认规则|内容长度必须小于等于[200]|
|自动添加应用视图|默认规则|默认规则|
|启用本地服务|默认规则|默认规则|
|用户标记2|默认规则|内容长度必须小于等于[200]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|系统|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|只发布系统引用视图（废弃）|默认规则|默认规则|
|视图主菜单方向|默认规则|内容长度必须小于等于[20]|
|系统应用标识|默认规则|内容长度必须小于等于[100]|
|代码目录|默认规则|内容长度必须小于等于[60]|
|表格适应屏宽|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|表单项无权限显示模式|默认规则|默认规则|
|自定义参数|默认规则|内容长度必须小于等于[2000]|
|移动端方向设置|默认规则|内容长度必须小于等于[20]|
|备注|默认规则|内容长度必须小于等于[2000]|
|删除模式|默认规则|默认规则|
|启用故事板|默认规则|默认规则|
|应用主题|默认规则|内容长度必须小于等于[200]|
|支持动态系统|默认规则|默认规则|
|是否启用|默认规则|默认规则|
|图标文件|默认规则|内容长度必须小于等于[100]|
|应用标记|默认规则|内容长度必须小于等于[60]|
|默认应用|默认规则|默认规则|
|应用标记2|默认规则|内容长度必须小于等于[60]|
|服务代码名称|默认规则|内容长度必须小于等于[60]|
|防止XSS攻击|默认规则|默认规则|
|应用类型|默认规则|内容长度必须小于等于[200]|
|应用CDN|默认规则|内容长度必须小于等于[100]|
|表格列启用链接|默认规则|默认规则|
|应用主题|默认规则|内容长度必须小于等于[100]|
|转换12列至24列布局|默认规则|默认规则|
|应用编号|默认规则|内容长度必须小于等于[100]|
|应用标记4|默认规则|内容长度必须小于等于[60]|
|建立人|默认规则|内容长度必须小于等于[60]|
|表格行激活模式|默认规则|默认规则|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|代码名称|默认规则|内容长度必须小于等于[60]|
|中文名称|默认规则|内容长度必须小于等于[200]|
|应用样式|默认规则|内容长度必须小于等于[100]|
|按钮无权限显示模式|默认规则|默认规则|
|应用CDN|默认规则|内容长度必须小于等于[200]|
|只发布引用视图|默认规则|默认规则|
|应用模式|默认规则|内容长度必须小于等于[30]|
|系统应用名称|默认规则|内容长度必须小于等于[200]|
|应用类型|默认规则|内容长度必须小于等于[100]|
|应用目录|默认规则|内容长度必须小于等于[500]|
|起始页图片文件|默认规则|内容长度必须小于等于[100]|
|用户分类|默认规则|内容长度必须小于等于[10]|
|应用标记3|默认规则|内容长度必须小于等于[60]|
|应用样式参数|默认规则|内容长度必须小于等于[1048576]|
|系统|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|内建界面式样|默认规则|内容长度必须小于等于[30]|
|表格列无权限显示模式|默认规则|默认规则|
|输出表单项更新权限标记|默认规则|默认规则|
|默认服务接口|默认规则|内容长度必须小于等于[200]|
|默认服务接口|默认规则|内容长度必须小于等于[100]|

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
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|Build|版本([MYSQL5](../../appendix/query_MYSQL5.md#PSSysApp_Build))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSysApp_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSysApp_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|Build|版本|Build|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|视图主菜单方向(MAINMENUSIDE)|EQ|
|表单项无权限显示模式(FINOPRIVDM)|EQ|
|移动端方向设置(ORIENTATIONMODE)|EQ|
|删除模式(REMOVEFLAG)|EQ|
|表格列启用链接(GRIDCOLENABLELINK)|EQ|
|表格行激活模式(GRIDROWACTIVEMODE)|EQ|
|按钮无权限显示模式(BTNNOPRIVDM)|EQ|
|应用模式(APPMODE)|EQ|
|系统应用名称(PSSYSAPPNAME)|LIKE|
|用户分类(USERCAT)|EQ|
|内建界面式样(UISTYLE)|EQ|
|表格列无权限显示模式(GCNOPRIVDM)|EQ|
|默认服务接口(PSSYSSERVICEAPINAME)|EQ|
|默认服务接口(PSSYSSERVICEAPINAME)|LIKE|
|默认服务接口(PSSYSSERVICEAPIID)|EQ|

## 导入模式
无


## 导出模式
无
