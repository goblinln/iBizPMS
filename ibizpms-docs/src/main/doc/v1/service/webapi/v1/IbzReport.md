# 服务接口-IBZ_REPORT
## 接口说明
汇报汇总

## 接口清单
### 新建汇报汇总
#### 访问路径
/ibzreports

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportdto | [IbzReportDTO](#IbzReportDTO) | 汇报汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportDTO](#IbzReportDTO)：汇报汇总实体传输对象 |

### 批量新建汇报汇总
#### 访问路径
/ibzreports/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportdtos | List<[IbzReportDTO](#IbzReportDTO)> | 汇报汇总实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新汇报汇总
#### 访问路径
/ibzreports/{ibzreport_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreport_id | Long | 汇报汇总主键ID |
| 2 | ibzreportdto | [IbzReportDTO](#IbzReportDTO) | 汇报汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportDTO](#IbzReportDTO)：汇报汇总实体传输对象 |

### 批量更新汇报汇总
#### 访问路径
/ibzreports/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportdtos | List<[IbzReportDTO](#IbzReportDTO)> | 汇报汇总实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除汇报汇总
#### 访问路径
/ibzreports/{ibzreport_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreport_id | Long | 汇报汇总主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除汇报汇总
#### 访问路径
/ibzreports/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 汇报汇总主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取汇报汇总
#### 访问路径
/ibzreports/{ibzreport_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreport_id | Long | 汇报汇总主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportDTO](#IbzReportDTO)：汇报汇总实体传输对象 |

### 检查汇报汇总
#### 访问路径
/ibzreports/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportdto | [IbzReportDTO](#IbzReportDTO) | 汇报汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 我未提交的（计数器）
#### 访问路径
/ibzreports/{ibzreport_id}/myreportinotsubmit

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreport_id | Long | 汇报汇总主键ID |
| 2 | ibzreportdto | [IbzReportDTO](#IbzReportDTO) | 汇报汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportDTO](#IbzReportDTO)：汇报汇总实体传输对象 |

### 我收到的汇报（计数器）
#### 访问路径
/ibzreports/{ibzreport_id}/reportireceived

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreport_id | Long | 汇报汇总主键ID |
| 2 | ibzreportdto | [IbzReportDTO](#IbzReportDTO) | 汇报汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportDTO](#IbzReportDTO)：汇报汇总实体传输对象 |

### 保存汇报汇总
#### 访问路径
/ibzreports/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportdto | [IbzReportDTO](#IbzReportDTO) | 汇报汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存汇报汇总
#### 访问路径
/ibzreports/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportdtos | List<[IbzReportDTO](#IbzReportDTO)> | 汇报汇总实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取汇报汇总
#### 访问路径
/ibzreports/fetchallreport

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportSearchContext](#IbzReportSearchContext) | 汇报汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportDTO](#IbzReportDTO)>：汇报汇总实体传输对象列表 |

### 查询汇报汇总
#### 访问路径
/ibzreports/searchallreport

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportSearchContext](#IbzReportSearchContext) | 汇报汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportDTO](#IbzReportDTO)>：汇报汇总实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/ibzreports/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportSearchContext](#IbzReportSearchContext) | 汇报汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportDTO](#IbzReportDTO)>：汇报汇总实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzreports/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportSearchContext](#IbzReportSearchContext) | 汇报汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportDTO](#IbzReportDTO)>：汇报汇总实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取汇报汇总（我收到的）
#### 访问路径
/ibzreports/fetchmyreallreport

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportSearchContext](#IbzReportSearchContext) | 汇报汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportDTO](#IbzReportDTO)>：汇报汇总实体传输对象列表 |

### 查询汇报汇总（我收到的）
#### 访问路径
/ibzreports/searchmyreallreport

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportSearchContext](#IbzReportSearchContext) | 汇报汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportDTO](#IbzReportDTO)>：汇报汇总实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzReportDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | updatemanname | String | 不可 | 更新人名称 |
| 2 | mailto | String | 允许 | 抄送给 |
| 3 | ibzdailyid | Long | 不可 | 汇报标识 |
| 4 | reportlycnt | Integer | 允许 | 未读汇报数 |
| 5 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | worktoday | String | 允许 | 工作 |
| 7 | monthlycnt | Integer | 允许 | 未读月报数 |
| 8 | dailycnt | Integer | 允许 | 未读日报数 |
| 9 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | todaytask | String | 允许 | 完成任务 |
| 11 | account | String | 允许 | 用户 |
| 12 | type | String | 允许 | 类型 |
| 13 | createman | String | 不可 | 建立人 |
| 14 | updateman | String | 不可 | 更新人 |
| 15 | ibzdailyname | String | 允许 | 汇报名称 |
| 16 | reportto | String | 允许 | 汇报给 |
| 17 | submittime | String | 允许 | 提交时间 |
| 18 | files | String | 允许 | 附件 |
| 19 | createmanname | String | 不可 | 建立人名称 |
| 20 | planstomorrow | String | 允许 | 计划 |
| 21 | reportstatus | String | 允许 | 状态 |
| 22 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 23 | issubmit | String | 允许 | 是否提交 |
| 24 | comment | String | 允许 | 其他事项 |
| 25 | tomorrowplanstask | String | 允许 | 计划任务 |
| 26 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzReportSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibz_dailyid_eq | Long | 允许 | 条件字段：ibz_dailyid<br>条件组合方式：`=` |
| 2 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 3 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 4 | n_ibz_dailyname_like | String | 允许 | 条件字段：ibz_dailyname<br>条件组合方式：`%like%` |
| 5 | n_reportto_eq | String | 允许 | 条件字段：reportto<br>条件组合方式：`=` |
| 6 | n_reportstatus_eq | String | 允许 | 条件字段：reportstatus<br>条件组合方式：`=` |
| 7 | n_issubmit_eq | String | 允许 | 条件字段：issubmit<br>条件组合方式：`=` |
| 8 | customcond | String | 允许 | 自定义查询条件 |
| 9 | customparams | String | 允许 | 自定义查询参数 |
| 10 | query | String | 允许 | 快速搜索 |
| 11 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 12 | page | int | 允许 | 当前页数<br>默认值0 |
| 13 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 14 | sort | String | 允许 | 排序 |
