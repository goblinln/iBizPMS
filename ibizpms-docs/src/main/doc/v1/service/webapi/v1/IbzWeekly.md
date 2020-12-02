# 服务接口-IBZ_WEEKLY
## 接口说明
周报

## 接口清单
### 新建周报
#### 访问路径
/ibzweeklies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 批量新建周报
#### 访问路径
/ibzweeklies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweeklydtos | List<[IbzWeeklyDTO](#IbzWeeklyDTO)> | 周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新周报
#### 访问路径
/ibzweeklies/{ibzweekly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |
| 2 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 批量更新周报
#### 访问路径
/ibzweeklies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweeklydtos | List<[IbzWeeklyDTO](#IbzWeeklyDTO)> | 周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除周报
#### 访问路径
/ibzweeklies/{ibzweekly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除周报
#### 访问路径
/ibzweeklies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 周报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取周报
#### 访问路径
/ibzweeklies/{ibzweekly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 检查周报
#### 访问路径
/ibzweeklies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 定时生成每周周报
#### 访问路径
/ibzweeklies/{ibzweekly_id}/createeveryweekreport

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |
| 2 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 已读
#### 访问路径
/ibzweeklies/{ibzweekly_id}/haveread

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |
| 2 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 定时推送每周周报
#### 访问路径
/ibzweeklies/{ibzweekly_id}/pushuserweekly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |
| 2 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 保存周报
#### 访问路径
/ibzweeklies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存周报
#### 访问路径
/ibzweeklies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweeklydtos | List<[IbzWeeklyDTO](#IbzWeeklyDTO)> | 周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 提交
#### 访问路径
/ibzweeklies/{ibzweekly_id}/submit

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzweekly_id | Long | 周报主键ID |
| 2 | ibzweeklydto | [IbzWeeklyDTO](#IbzWeeklyDTO) | 周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzWeeklyDTO](#IbzWeeklyDTO)：周报实体传输对象 |

### 获取数据集
#### 访问路径
/ibzweeklies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzWeeklySearchContext](#IbzWeeklySearchContext) | 周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzWeeklyDTO](#IbzWeeklyDTO)>：周报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzweeklies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzWeeklySearchContext](#IbzWeeklySearchContext) | 周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzWeeklyDTO](#IbzWeeklyDTO)>：周报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我收到的周报
#### 访问路径
/ibzweeklies/fetchmyweekly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzWeeklySearchContext](#IbzWeeklySearchContext) | 周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzWeeklyDTO](#IbzWeeklyDTO)>：周报实体传输对象列表 |

### 查询我收到的周报
#### 访问路径
/ibzweeklies/searchmyweekly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzWeeklySearchContext](#IbzWeeklySearchContext) | 周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzWeeklyDTO](#IbzWeeklyDTO)>：周报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzWeeklyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ibzweeklyname | String | 允许 | 周报名称 |
| 2 | ibzweeklyid | Long | 不可 | 周报标识 |
| 3 | createman | String | 不可 | 建立人 |
| 4 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | updateman | String | 不可 | 更新人 |
| 6 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | account | String | 允许 | 用户 |
| 8 | mailto | String | 允许 | 抄送给 |
| 9 | files | String | 允许 | 附件 |
| 10 | issubmit | String | 允许 | 是否提交 |
| 11 | reportto | String | 允许 | 汇报给 |
| 12 | comment | String | 允许 | 其他事项 |
| 13 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 14 | workthisweek | String | 允许 | 本周工作 |
| 15 | plannextweek | String | 允许 | 下周计划 |
| 16 | thisweektask | String | 允许 | 本周完成任务 |
| 17 | nextweektask | String | 允许 | 下周计划任务 |
| 18 | updatemanname | String | 不可 | 更新人名称 |
| 19 | createmanname | String | 不可 | 建立人名称 |
| 20 | reportstatus | String | 允许 | 状态 |
| 21 | submittime | Timestamp | 允许 | 提交时间<br>时间格式：HH:mm:ss |
| 22 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzWeeklySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibz_weeklyname_like | String | 允许 | 条件字段：ibz_weeklyname<br>条件组合方式：`%like%`<br>时间格式：HH:mm:ss |
| 2 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=`<br>时间格式：HH:mm:ss |
| 3 | n_issubmit_eq | String | 允许 | 条件字段：issubmit<br>条件组合方式：`=`<br>时间格式：HH:mm:ss |
| 4 | n_reportto_eq | String | 允许 | 条件字段：reportto<br>条件组合方式：`=`<br>时间格式：HH:mm:ss |
| 5 | n_reportstatus_eq | String | 允许 | 条件字段：reportstatus<br>条件组合方式：`=`<br>时间格式：HH:mm:ss |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
