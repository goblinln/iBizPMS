# 服务接口-IBZ_REPORTLY
## 接口说明
汇报

## 接口清单
### 新建汇报
#### 访问路径
/ibzreportlies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportlydto | [IbzReportlyDTO](#IbzReportlyDTO) | 汇报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportlyDTO](#IbzReportlyDTO)：汇报实体传输对象 |

### 批量新建汇报
#### 访问路径
/ibzreportlies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportlydtos | List<[IbzReportlyDTO](#IbzReportlyDTO)> | 汇报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新汇报
#### 访问路径
/ibzreportlies/{ibzreportly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportly_id | Long | 汇报主键ID |
| 2 | ibzreportlydto | [IbzReportlyDTO](#IbzReportlyDTO) | 汇报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportlyDTO](#IbzReportlyDTO)：汇报实体传输对象 |

### 批量更新汇报
#### 访问路径
/ibzreportlies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportlydtos | List<[IbzReportlyDTO](#IbzReportlyDTO)> | 汇报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除汇报
#### 访问路径
/ibzreportlies/{ibzreportly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportly_id | Long | 汇报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除汇报
#### 访问路径
/ibzreportlies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 汇报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取汇报
#### 访问路径
/ibzreportlies/{ibzreportly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportly_id | Long | 汇报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportlyDTO](#IbzReportlyDTO)：汇报实体传输对象 |

### 检查汇报
#### 访问路径
/ibzreportlies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportlydto | [IbzReportlyDTO](#IbzReportlyDTO) | 汇报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 已读
#### 访问路径
/ibzreportlies/{ibzreportly_id}/haveread

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportly_id | Long | 汇报主键ID |
| 2 | ibzreportlydto | [IbzReportlyDTO](#IbzReportlyDTO) | 汇报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportlyDTO](#IbzReportlyDTO)：汇报实体传输对象 |

### 保存汇报
#### 访问路径
/ibzreportlies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportlydto | [IbzReportlyDTO](#IbzReportlyDTO) | 汇报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存汇报
#### 访问路径
/ibzreportlies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportlydtos | List<[IbzReportlyDTO](#IbzReportlyDTO)> | 汇报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 提交
#### 访问路径
/ibzreportlies/{ibzreportly_id}/submit

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportly_id | Long | 汇报主键ID |
| 2 | ibzreportlydto | [IbzReportlyDTO](#IbzReportlyDTO) | 汇报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportlyDTO](#IbzReportlyDTO)：汇报实体传输对象 |

### 获取数据集
#### 访问路径
/ibzreportlies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzreportlies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我所有的汇报
#### 访问路径
/ibzreportlies/fetchmyallreportly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象列表 |

### 查询我所有的汇报
#### 访问路径
/ibzreportlies/searchmyallreportly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我收到的汇报
#### 访问路径
/ibzreportlies/fetchmyreceived

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象列表 |

### 查询我收到的汇报
#### 访问路径
/ibzreportlies/searchmyreceived

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的未提交汇报
#### 访问路径
/ibzreportlies/fetchmyreportlymob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象列表 |

### 查询我的未提交汇报
#### 访问路径
/ibzreportlies/searchmyreportlymob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportlySearchContext](#IbzReportlySearchContext) | 汇报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportlyDTO](#IbzReportlyDTO)>：汇报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzReportlyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | files | String | 允许 | 附件 |
| 2 | account | String | 允许 | 用户 |
| 3 | updateman | String | 不可 | 更新人 |
| 4 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | mailto | String | 允许 | 抄送给 |
| 6 | ibzreportlyid | Long | 不可 | 汇报标识 |
| 7 | ibzreportlyname | String | 允许 | 汇报名称 |
| 8 | reportstatus | String | 允许 | 状态 |
| 9 | submittime | Timestamp | 允许 | 提交时间<br>时间格式：HH:mm:ss |
| 10 | reporttopk | String | 允许 | 汇报给（选择） |
| 11 | reportto | String | 允许 | 汇报给 |
| 12 | createman | String | 不可 | 建立人 |
| 13 | issubmit | String | 允许 | 是否提交 |
| 14 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 15 | content | String | 允许 | 工作内容 |
| 16 | date | Timestamp | 允许 | 汇报日期<br>时间格式：yyyy-MM-dd |
| 17 | mailtopk | String | 允许 | 抄送给（选择） |
| 18 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzReportlySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 2 | n_ibz_reportlyname_like | String | 允许 | 条件字段：ibz_reportlyname<br>条件组合方式：`%like%` |
| 3 | n_issubmit_eq | String | 允许 | 条件字段：issubmit<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
