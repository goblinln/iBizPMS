
# 实体-月报(IBZ_MONTHLY)
## 实体说明
月报

## 所属模块
[汇报模块](../report)

## 实体关系
### 1:N
无
### N:1
无


## 实体属性
| 序号 | 属性 | 属性名 | 数据类型 | 主键 | 外键 | 允许为空 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 1 | [月报标识](#属性-月报标识（IBZ_MONTHLYID）) | IBZ_MONTHLYID | 自增标识，整数类型，用户不可见 | 是 | 否 | 否 |
| 2 | [建立人](#属性-建立人（CREATEMAN）) | CREATEMAN | 文本，可指定长度 | 否 | 否 | 否 |
| 3 | [更新人](#属性-更新人（UPDATEMAN）) | UPDATEMAN | 文本，可指定长度 | 否 | 否 | 否 |
| 4 | [建立时间](#属性-建立时间（CREATEDATE）) | CREATEDATE | 日期时间型 | 否 | 否 | 否 |
| 5 | [月报名称](#属性-月报名称（IBZ_MONTHLYNAME）) | IBZ_MONTHLYNAME | 文本，可指定长度 | 否 | 否 | 是 |
| 6 | [更新时间](#属性-更新时间（UPDATEDATE）) | UPDATEDATE | 日期时间型 | 否 | 否 | 否 |
| 7 | [日期](#属性-日期（DATE）) | DATE | 日期型 | 否 | 否 | 是 |
| 8 | [用户](#属性-用户（ACCOUNT）) | ACCOUNT | 单项选择(文本值) | 否 | 否 | 是 |
| 9 | [本月工作](#属性-本月工作（WORKTHISMONTH）) | WORKTHISMONTH | HTML文本，没有长度限制 | 否 | 否 | 是 |
| 10 | [下月计划](#属性-下月计划（PLANSNEXTMONTH）) | PLANSNEXTMONTH | HTML文本，没有长度限制 | 否 | 否 | 是 |
| 11 | [汇报给](#属性-汇报给（REPORTTO）) | REPORTTO | 单项选择(文本值) | 否 | 否 | 是 |
| 12 | [抄送给](#属性-抄送给（MAILTO）) | MAILTO | 多项选择(文本值) | 否 | 否 | 是 |
| 13 | [其他事项](#属性-其他事项（COMMENT）) | COMMENT | HTML文本，没有长度限制 | 否 | 否 | 是 |
| 14 | [本月完成任务](#属性-本月完成任务（THISMONTHTASK）) | THISMONTHTASK | 多项选择(文本值) | 否 | 否 | 是 |
| 15 | [下月计划任务](#属性-下月计划任务（NEXTMONTHPLANSTASK）) | NEXTMONTHPLANSTASK | 多项选择(文本值) | 否 | 否 | 是 |
| 16 | [附件](#属性-附件（FILES）) | FILES | 文本，可指定长度 | 否 | 否 | 是 |
| 17 | [是否提交](#属性-是否提交（ISSUBMIT）) | ISSUBMIT | 单项选择(文本值) | 否 | 否 | 是 |
| 18 | [更新人名称](#属性-更新人名称（UPDATEMANNAME）) | UPDATEMANNAME | 文本，可指定长度 | 否 | 否 | 否 |
| 19 | [状态](#属性-状态（REPORTSTATUS）) | REPORTSTATUS | 单项选择(文本值) | 否 | 否 | 是 |
| 20 | [建立人名称](#属性-建立人名称（CREATEMANNAME）) | CREATEMANNAME | 文本，可指定长度 | 否 | 否 | 否 |
| 21 | [提交时间](#属性-提交时间（SUBMITTIME）) | SUBMITTIME | 时间型 | 否 | 否 | 是 |

### 属性-月报标识（IBZ_MONTHLYID）
#### 属性说明
月报标识

- 是否是主键
是

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
自增标识，整数类型，用户不可见

- Java类型
Long

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-建立人（CREATEMAN）
#### 属性说明
建立人

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
文本，可指定长度

- Java类型
String

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
参照数据字典【[云系统操作者（SysOperator）](../../codelist/SysOperator)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-更新人（UPDATEMAN）
#### 属性说明
更新人

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
文本，可指定长度

- Java类型
String

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
参照数据字典【[云系统操作者（SysOperator）](../../codelist/SysOperator)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-建立时间（CREATEDATE）
#### 属性说明
建立时间

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
日期时间型

- Java类型
Timestamp

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
无

- 数据格式
yyyy-MM-dd HH:mm:ss

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-月报名称（IBZ_MONTHLYNAME）
#### 属性说明
月报名称

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
文本，可指定长度

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
是

- 搜索条件
| 序号 | 组合方式 |
| ---- | ---- |
| 1 | `%like%` |

#### 关系属性
无

### 属性-更新时间（UPDATEDATE）
#### 属性说明
更新时间

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
日期时间型

- Java类型
Timestamp

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
无

- 数据格式
yyyy-MM-dd HH:mm:ss

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-日期（DATE）
#### 属性说明
日期

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
日期型

- Java类型
Timestamp

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
yyyy-MM-dd

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-用户（ACCOUNT）
#### 属性说明
用户

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
单项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
参照数据字典【[用户真实名称（动态）（UserRealName）](../../codelist/UserRealName)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
| 序号 | 组合方式 |
| ---- | ---- |
| 1 | `=` |

#### 关系属性
无

### 属性-本月工作（WORKTHISMONTH）
#### 属性说明
本月工作

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
HTML文本，没有长度限制

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-下月计划（PLANSNEXTMONTH）
#### 属性说明
下月计划

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
HTML文本，没有长度限制

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-汇报给（REPORTTO）
#### 属性说明
汇报给

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
单项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
参照数据字典【[用户真实名称（动态）（UserRealName）](../../codelist/UserRealName)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
| 序号 | 组合方式 |
| ---- | ---- |
| 1 | `=` |

#### 关系属性
无

### 属性-抄送给（MAILTO）
#### 属性说明
抄送给

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
多项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
参照数据字典【[用户真实名称（动态）（UserRealName）](../../codelist/UserRealName)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-其他事项（COMMENT）
#### 属性说明
其他事项

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
HTML文本，没有长度限制

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-本月完成任务（THISMONTHTASK）
#### 属性说明
本月完成任务

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
多项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
参照数据字典【[我完成的任务（汇报）（MyCompleteTask）](../../codelist/MyCompleteTask)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-下月计划任务（NEXTMONTHPLANSTASK）
#### 属性说明
下月计划任务

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
多项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
参照数据字典【[我计划参与的任务（汇报）（MyPlanTask）](../../codelist/MyPlanTask)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-附件（FILES）
#### 属性说明
附件

- 是否是主键
否

- 属性类型
应用界面字段[无存储]

- 数据类型
文本，可指定长度

- Java类型
String

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-是否提交（ISSUBMIT）
#### 属性说明
是否提交

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
单项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
| 项目 | 说明 |
| ---- | ---- |
| 类型 |  |
| 值 | 0 |

- 取值范围/公式
参照数据字典【[是否（YesNo）](../../codelist/YesNo)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
| 序号 | 组合方式 |
| ---- | ---- |
| 1 | `=` |

#### 关系属性
无

### 属性-更新人名称（UPDATEMANNAME）
#### 属性说明
更新人名称

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
文本，可指定长度

- Java类型
String

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-状态（REPORTSTATUS）
#### 属性说明
状态

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
单项选择(文本值)

- Java类型
String

- 是否允许为空
是

- 默认值
| 项目 | 说明 |
| ---- | ---- |
| 类型 |  |
| 值 | 0 |

- 取值范围/公式
参照数据字典【[汇报状态（ReportStatus）](../../codelist/ReportStatus)】

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
| 序号 | 组合方式 |
| ---- | ---- |
| 1 | `=` |

#### 关系属性
无

### 属性-建立人名称（CREATEMANNAME）
#### 属性说明
建立人名称

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
文本，可指定长度

- Java类型
String

- 是否允许为空
否

- 默认值
无

- 取值范围/公式
无

- 数据格式
无

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无

### 属性-提交时间（SUBMITTIME）
#### 属性说明
提交时间

- 是否是主键
否

- 属性类型
物理字段[来自当前实体物理表字段]

- 数据类型
时间型

- Java类型
Timestamp

- 是否允许为空
是

- 默认值
无

- 取值范围/公式
无

- 数据格式
HH:mm:ss

- 是否支持快速搜索
否

- 搜索条件
无

#### 关系属性
无


## 业务状态
| 序号 | 状态名称 | [是否提交](#属性-是否提交（ISSUBMIT）)<br>（ISSUBMIT） | 默认 |
| ---- | ---- | ---- | ---- |
| 1 | [未提交](#业务状态-未提交（nsubmit）) | 0 |  |  | 否 |
| 2 | [已提交](#业务状态-已提交（submit）) | 1 |  |  | 否 |
### 业务状态-未提交（nsubmit）
#### 状态说明
未提交

- 是否是默认状态
否

- 状态值
| 属性名 | 状态值 |
| ---- | ---- |
| [是否提交](#属性-是否提交（ISSUBMIT）)<br>（ISSUBMIT） | 0 |



- 流程相关状态
无

#### 实体行为控制
允许模式：允许


#### 操作权限控制
允许模式：拒绝
拒绝提示信息：无
### 业务状态-已提交（submit）
#### 状态说明
已提交

- 是否是默认状态
否

- 状态值
| 属性名 | 状态值 |
| ---- | ---- |
| [是否提交](#属性-是否提交（ISSUBMIT）)<br>（ISSUBMIT） | 1 |



- 流程相关状态
无

#### 实体行为控制
允许模式：允许


#### 操作权限控制
允许模式：拒绝
拒绝提示信息：无
| 序号 | 操作权限 |
| ---- | ---- |
| 1 | [月报提交](#操作权限-月报提交（SRFUR__MONTHLY_SUBMIT_BUT）)<br>（SRFUR__MONTHLY_SUBMIT_BUT） |

## 实体行为
| 序号 | 行为 | 行为名 | 行为类型 | 行为持有者 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | [Create](#实体行为-Create（Create）) | Create | 内置方法 | 后台及前台 |
| 2 | [Update](#实体行为-Update（Update）) | Update | 内置方法 | 后台及前台 |
| 3 | [Remove](#实体行为-Remove（Remove）) | Remove | 内置方法 | 后台及前台 |
| 4 | [Get](#实体行为-Get（Get）) | Get | 内置方法 | 后台及前台 |
| 5 | [GetDraft](#实体行为-GetDraft（GetDraft）) | GetDraft | 内置方法 | 后台及前台 |
| 6 | [CheckKey](#实体行为-CheckKey（CheckKey）) | CheckKey | 内置方法 | 后台及前台 |
| 7 | [定时生成用户月报](#实体行为-定时生成用户月报（CreateUserMonthly）) | CreateUserMonthly | 用户自定义 | 后台及前台 |
| 8 | [已读](#实体行为-已读（HaveRead）) | HaveRead | 用户自定义 | 后台及前台 |
| 9 | [定时推送待阅提醒用户月报](#实体行为-定时推送待阅提醒用户月报（PushUserMonthly）) | PushUserMonthly | 用户自定义 | 后台及前台 |
| 10 | [Save](#实体行为-Save（Save）) | Save | 内置方法 | 后台及前台 |
| 11 | [提交](#实体行为-提交（Submit）) | Submit | 用户自定义 | 后台及前台 |

### 实体行为-Create（Create）
#### 说明
Create

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-Update（Update）
#### 说明
Update

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-Remove（Remove）
#### 说明
Remove

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-Get（Get）
#### 说明
Get

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-GetDraft（GetDraft）
#### 说明
GetDraft

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-CheckKey（CheckKey）
#### 说明
CheckKey

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-定时生成用户月报（CreateUserMonthly）
#### 说明
定时生成用户月报

- 行为类型
用户自定义

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-已读（HaveRead）
#### 说明
已读

- 行为类型
用户自定义

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-定时推送待阅提醒用户月报（PushUserMonthly）
#### 说明
定时推送待阅提醒用户月报

- 行为类型
用户自定义

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-Save（Save）
#### 说明
Save

- 行为类型
内置方法

- 行为持有者
后台及前台

#### 逻辑附加
无
### 实体行为-提交（Submit）
#### 说明
提交

- 行为类型
用户自定义

- 行为持有者
后台及前台

#### 逻辑附加
无

## 逻辑处理
无

## 实体搜索
### 快速搜索项
| 序号 | 属性 |
| ---- | ---- |
| 1 | [月报名称（IBZ_MONTHLYNAME）](#属性-月报名称（IBZ_MONTHLYNAME）) |

### 搜索条件
| 序号 | 属性 | 组合方式 |
| ---- | ---- | ---- |
| 1 | [月报名称（IBZ_MONTHLYNAME）](#属性-月报名称（IBZ_MONTHLYNAME）) | `%like%` |
| 2 | [用户（ACCOUNT）](#属性-用户（ACCOUNT）) | `=` |
| 3 | [汇报给（REPORTTO）](#属性-汇报给（REPORTTO）) | `=` |
| 4 | [是否提交（ISSUBMIT）](#属性-是否提交（ISSUBMIT）) | `=` |
| 5 | [状态（REPORTSTATUS）](#属性-状态（REPORTSTATUS）) | `=` |

## 数据查询
| 序号 | 查询 | 查询名 | 默认 |
| ---- | ---- | ---- | ---- |
| 1 | [数据查询](#数据查询-数据查询（Default）) | Default | 否 |
| 2 | [我的月报](#数据查询-我的月报（MyMonthly）) | MyMonthly | 否 |
| 3 | [我的月报（移动端）](#数据查询-我的月报（移动端）（MyNotSubmit）) | MyNotSubmit | 否 |
| 4 | [我收到的月报](#数据查询-我收到的月报（MyReceivedMonthly）) | MyReceivedMonthly | 否 |
| 5 | [我提交的月报](#数据查询-我提交的月报（MySubmitMonthly）) | MySubmitMonthly | 否 |
| 6 | [默认（全部数据）](#数据查询-默认（全部数据）（View）) | View | 否 |

### 数据查询-数据查询（Default）
#### 说明
数据查询

- 默认查询
否

- 查询权限使用
否

#### SQL
- MYSQL5
```SQL
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_MONTHLYID`,
t1.`IBZ_MONTHLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`NEXTMONTHPLANSTASK`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.`SUBMITTIME`,
t1.`THISMONTHTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`
FROM `T_IBZ_MONTHLY` t1 

```
### 数据查询-我的月报（MyMonthly）
#### 说明
我的月报

- 默认查询
否

- 查询权限使用
否

#### SQL
- MYSQL5
```SQL
SELECT
	t1.`ACCOUNT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`CREATEMANNAME`,
	t1.`DATE`,
	t1.`IBZ_MONTHLYID`,
	t1.`IBZ_MONTHLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`THISMONTHTASK`,
	t1.`NEXTMONTHPLANSTASK`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN`,
	t1.`UPDATEMANNAME` 
FROM
	`t_ibz_monthly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_MONTHLYID 
	AND t11.objectType = 'monthly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
```
### 数据查询-我的月报（移动端）（MyNotSubmit）
#### 说明
我的月报（移动端）

- 默认查询
否

- 查询权限使用
否

#### SQL
- MYSQL5
```SQL
SELECT
t1.`ACCOUNT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
DATE_FORMAT(t1.DATE,'%Y-%m-%d') as `DATE`,
t1.`IBZ_DAILYID` AS IBZ_DAILYID,
t1.`IBZ_DAILYNAME` AS IBZ_DAILYNAME,
t1.`ISSUBMIT`,
t1.`MAILTO`,
'1' as `REPORTSTATUS`,
t1.`REPORTTO`,
t1.SUBMITTIME as `SUBMITTIME`,
t1.`THISMONTHTASK` AS THISMONTHTASK,
t1.`NEXTMONTHPLANSTASK` AS NEXTMONTHPLANSTASK,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
CONCAT_WS('','本月工作：',case when t1.WORKTHISMONTH is null then '无' else t1.WORKTHISMONTH end)  as WORKTHISMONTH,
t1.`COMMENT`,
CONCAT_WS('','下月计划：',case when t1.PLANSNEXTMONTH is null then '无' else t1.PLANSNEXTMONTH end) as PLANSNEXTMONTH,
'monthly' as type 
FROM `T_IBZ_MONTHLY` t1
```
### 数据查询-我收到的月报（MyReceivedMonthly）
#### 说明
我收到的月报

- 默认查询
否

- 查询权限使用
否

#### SQL
- MYSQL5
```SQL
SELECT
	t1.`ACCOUNT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`CREATEMANNAME`,
	t1.`DATE`,
	t1.`IBZ_MONTHLYID`,
	t1.`IBZ_MONTHLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`THISMONTHTASK`,
	t1.`NEXTMONTHPLANSTASK`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN`,
	t1.`UPDATEMANNAME` 
FROM
	`t_ibz_monthly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_MONTHLYID 
	AND t11.objectType = 'monthly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
```
### 数据查询-我提交的月报（MySubmitMonthly）
#### 说明
我提交的月报

- 默认查询
否

- 查询权限使用
否

#### SQL
- MYSQL5
```SQL
SELECT
	t1.`ACCOUNT`,
	t1.`CREATEDATE`,
	t1.`CREATEMAN`,
	t1.`CREATEMANNAME`,
	t1.`DATE`,
	t1.`IBZ_MONTHLYID`,
	t1.`IBZ_MONTHLYNAME`,
	t1.`ISSUBMIT`,
	t1.`MAILTO`,
	( CASE WHEN t11.id IS NOT NULL THEN '1' ELSE '0' END ) AS `REPORTSTATUS`,
	t1.`REPORTTO`,
	t1.`THISMONTHTASK`,
	t1.`NEXTMONTHPLANSTASK`,
	t1.`UPDATEDATE`,
	t1.`UPDATEMAN`,
	t1.`UPDATEMANNAME` 
FROM
	`t_ibz_monthly` t1
	LEFT JOIN zt_action t11 ON t11.objectID = t1.IBZ_MONTHLYID 
	AND t11.objectType = 'monthly' 
	AND t11.action = 'read' 
	AND t11.actor = #{srf.sessioncontext.srfloginname}
```
### 数据查询-默认（全部数据）（View）
#### 说明
默认（全部数据）

- 默认查询
否

- 查询权限使用
否

#### SQL
- MYSQL5
```SQL
SELECT
t1.`ACCOUNT`,
t1.`COMMENT`,
t1.`CREATEDATE`,
t1.`CREATEMAN`,
t1.`CREATEMANNAME`,
t1.`DATE`,
t1.`IBZ_MONTHLYID`,
t1.`IBZ_MONTHLYNAME`,
t1.`ISSUBMIT`,
t1.`MAILTO`,
t1.`NEXTMONTHPLANSTASK`,
t1.`PLANSNEXTMONTH`,
t1.`REPORTSTATUS`,
t1.`REPORTTO`,
t1.`SUBMITTIME`,
t1.`THISMONTHTASK`,
t1.`UPDATEDATE`,
t1.`UPDATEMAN`,
t1.`UPDATEMANNAME`,
t1.`WORKTHISMONTH`
FROM `T_IBZ_MONTHLY` t1 

```

## 数据集合
| 序号 | 集合 | 集合名 | 默认 |
| ---- | ---- | ---- | ---- |
| 1 | [数据集](#数据集合-数据集（Default）) | Default | 是 |
| 2 | [我的月报](#数据集合-我的月报（MyMonthly）) | MyMonthly | 否 |
| 3 | [我的月报（移动端）](#数据集合-我的月报（移动端）（MyNotSubmit）) | MyNotSubmit | 否 |
| 4 | [我收到的月报](#数据集合-我收到的月报（MyReceivedMonthly）) | MyReceivedMonthly | 否 |
| 5 | [我提交的月报](#数据集合-我提交的月报（MySubmitMonthly）) | MySubmitMonthly | 否 |

### 数据集合-数据集（Default）
#### 说明
数据集

- 默认集合
是

- 行为持有者
后台及前台

#### 关联的数据查询
| 序号 | 数据查询 |
| ---- | ---- |
| 1 | [数据查询（Default）](#数据查询-数据查询（Default）) |
### 数据集合-我的月报（MyMonthly）
#### 说明
我的月报

- 默认集合
否

- 行为持有者
后台及前台

#### 关联的数据查询
| 序号 | 数据查询 |
| ---- | ---- |
| 1 | [我的月报（MyMonthly）](#数据查询-我的月报（MyMonthly）) |
### 数据集合-我的月报（移动端）（MyNotSubmit）
#### 说明
我的月报（移动端）

- 默认集合
否

- 行为持有者
后台及前台

#### 关联的数据查询
| 序号 | 数据查询 |
| ---- | ---- |
| 1 | [我的月报（移动端）（MyNotSubmit）](#数据查询-我的月报（移动端）（MyNotSubmit）) |
### 数据集合-我收到的月报（MyReceivedMonthly）
#### 说明
我收到的月报

- 默认集合
否

- 行为持有者
后台及前台

#### 关联的数据查询
| 序号 | 数据查询 |
| ---- | ---- |
| 1 | [我收到的月报（MyReceivedMonthly）](#数据查询-我收到的月报（MyReceivedMonthly）) |
### 数据集合-我提交的月报（MySubmitMonthly）
#### 说明
我提交的月报

- 默认集合
否

- 行为持有者
后台及前台

#### 关联的数据查询
| 序号 | 数据查询 |
| ---- | ---- |
| 1 | [我提交的月报（MySubmitMonthly）](#数据查询-我提交的月报（MySubmitMonthly）) |

## 数据导入
无

## 数据导出
| 序号 | 数据导出 | 数据导出名 | 最大导出数量 |
| ---- | ---- | ---- | ---- |
| 1 | [数据导出](#数据导出-数据导出（DataExport）) | DataExport | 1,000 |

### 数据导出-数据导出（DataExport）
#### 说明
数据导出

- 最大导出数
1,000

- 行为持有者
后台及前台

| 序号 | 导出标题 | 属性 | 默认值 |
| ---- | ---- | ---- | ---- |
| 1 | 月报标识 | [月报标识（IBZ_MONTHLYID）](#属性-月报标识（IBZ_MONTHLYID）) |  |
| 2 | 月报名称 | [月报名称（IBZ_MONTHLYNAME）](#属性-月报名称（IBZ_MONTHLYNAME）) |  |
| 3 | 用户 | [用户（ACCOUNT）](#属性-用户（ACCOUNT）) |  |
| 4 | 日期 | [日期（DATE）](#属性-日期（DATE）) |  |
| 5 | 汇报给 | [汇报给（REPORTTO）](#属性-汇报给（REPORTTO）) |  |
| 6 | 是否提交 | [是否提交（ISSUBMIT）](#属性-是否提交（ISSUBMIT）) |  |

