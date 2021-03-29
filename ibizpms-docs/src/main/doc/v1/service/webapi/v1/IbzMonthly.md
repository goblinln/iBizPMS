# 服务接口-IBZ_MONTHLY
## 接口说明
月报

## 接口清单
### 新建月报
#### 访问路径
/ibzmonthlies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 批量新建月报
#### 访问路径
/ibzmonthlies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthlydtos | List<[IbzMonthlyDTO](#IbzMonthlyDTO)> | 月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新月报
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 批量更新月报
#### 访问路径
/ibzmonthlies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthlydtos | List<[IbzMonthlyDTO](#IbzMonthlyDTO)> | 月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除月报
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除月报
#### 访问路径
/ibzmonthlies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 月报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取月报
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 检查月报
#### 访问路径
/ibzmonthlies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 新建时获取信息
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}/creategetinfo

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 定时生成用户月报
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}/createusermonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 编辑时获取完成任务
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}/editgetcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 已读
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}/haveread

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 定时推送待阅提醒用户月报
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}/pushusermonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 保存月报
#### 访问路径
/ibzmonthlies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存月报
#### 访问路径
/ibzmonthlies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthlydtos | List<[IbzMonthlyDTO](#IbzMonthlyDTO)> | 月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 提交
#### 访问路径
/ibzmonthlies/{ibzmonthly_id}/submit

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzmonthly_id | Long | 月报主键ID |
| 2 | ibzmonthlydto | [IbzMonthlyDTO](#IbzMonthlyDTO) | 月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzMonthlyDTO](#IbzMonthlyDTO)：月报实体传输对象 |

### 获取数据集
#### 访问路径
/ibzmonthlies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzmonthlies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的月报
#### 访问路径
/ibzmonthlies/fetchmymonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询我的月报
#### 访问路径
/ibzmonthlies/searchmymonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的月报（移动端）
#### 访问路径
/ibzmonthlies/fetchmymonthlymob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询我的月报（移动端）
#### 访问路径
/ibzmonthlies/searchmymonthlymob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我收到的月报
#### 访问路径
/ibzmonthlies/fetchmyreceivedmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询我收到的月报
#### 访问路径
/ibzmonthlies/searchmyreceivedmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我提交的月报
#### 访问路径
/ibzmonthlies/fetchmysubmitmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询我提交的月报
#### 访问路径
/ibzmonthlies/searchmysubmitmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品月报
#### 访问路径
/ibzmonthlies/fetchproductmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询产品月报
#### 访问路径
/ibzmonthlies/searchproductmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目月报
#### 访问路径
/ibzmonthlies/fetchprojectmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象列表 |

### 查询项目月报
#### 访问路径
/ibzmonthlies/searchprojectmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzMonthlySearchContext](#IbzMonthlySearchContext) | 月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzMonthlyDTO](#IbzMonthlyDTO)>：月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzMonthlyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | createman | String | 不可 | 建立人 |
| 2 | reportstatus | String | 允许 | 状态 |
| 3 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 4 | plansnextmonth | String | 允许 | 下月计划 |
| 5 | reporttopk | String | 允许 | 汇报给（选择） |
| 6 | issubmit | String | 允许 | 是否提交 |
| 7 | mailtopk | String | 允许 | 抄送给（选择） |
| 8 | updatemanname | String | 不可 | 更新人名称 |
| 9 | updateman | String | 不可 | 更新人 |
| 10 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 11 | submittime | Timestamp | 允许 | 提交时间<br>时间格式：HH:mm:ss |
| 12 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 13 | createmanname | String | 不可 | 建立人名称 |
| 14 | files | String | 允许 | 附件 |
| 15 | ibzmonthlyid | Long | 不可 | 月报标识 |
| 16 | account | String | 允许 | 用户 |
| 17 | nextmonthplanstask | String | 允许 | 下月计划任务 |
| 18 | thismonthtask | String | 允许 | 本月完成任务 |
| 19 | workthismonth | String | 允许 | 本月工作 |
| 20 | reportto | String | 允许 | 汇报给 |
| 21 | ibzmonthlyname | String | 允许 | 月报名称 |
| 22 | comment | String | 允许 | 其他事项 |
| 23 | mailto | String | 允许 | 抄送给 |
| 24 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzMonthlySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_reportstatus_eq | String | 允许 | 条件字段：reportstatus<br>条件组合方式：`=` |
| 2 | n_issubmit_eq | String | 允许 | 条件字段：issubmit<br>条件组合方式：`=` |
| 3 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 4 | n_reportto_eq | String | 允许 | 条件字段：reportto<br>条件组合方式：`=` |
| 5 | n_ibz_monthlyname_like | String | 允许 | 条件字段：ibz_monthlyname<br>条件组合方式：`%like%` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
