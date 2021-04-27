# 服务接口-IBZ_DAILY
## 接口说明
日报

## 接口清单
### 新建日报
#### 访问路径
/ibzdailies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 批量新建日报
#### 访问路径
/ibzdailies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdailydtos | List<[IbzDailyDTO](#IbzDailyDTO)> | 日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新日报
#### 访问路径
/ibzdailies/{ibzdaily_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 批量更新日报
#### 访问路径
/ibzdailies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdailydtos | List<[IbzDailyDTO](#IbzDailyDTO)> | 日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除日报
#### 访问路径
/ibzdailies/{ibzdaily_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除日报
#### 访问路径
/ibzdailies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 日报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取日报
#### 访问路径
/ibzdailies/{ibzdaily_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 检查日报
#### 访问路径
/ibzdailies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 定时生成用户日报
#### 访问路径
/ibzdailies/{ibzdaily_id}/createuserdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 获取前一天日报计划参与任务（编辑）
#### 访问路径
/ibzdailies/{ibzdaily_id}/getyeaterdaydailyplanstaskedit

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 获取前一天日报计划参与任务（新建）
#### 访问路径
/ibzdailies/{ibzdaily_id}/getyesterdaydailyplanstask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 已读
#### 访问路径
/ibzdailies/{ibzdaily_id}/haveread

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 关联完成任务
#### 访问路径
/ibzdailies/{ibzdaily_id}/linkcompletetask

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 定时推送待阅提醒用户日报
#### 访问路径
/ibzdailies/{ibzdaily_id}/pushuserdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 保存日报
#### 访问路径
/ibzdailies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存日报
#### 访问路径
/ibzdailies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdailydtos | List<[IbzDailyDTO](#IbzDailyDTO)> | 日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 提交
#### 访问路径
/ibzdailies/{ibzdaily_id}/submit

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzdaily_id | Long | 日报主键ID |
| 2 | ibzdailydto | [IbzDailyDTO](#IbzDailyDTO) | 日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzDailyDTO](#IbzDailyDTO)：日报实体传输对象 |

### 获取数据集
#### 访问路径
/ibzdailies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzdailies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的日报（已提交和未提交）
#### 访问路径
/ibzdailies/fetchmyalldaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询我的日报（已提交和未提交）
#### 访问路径
/ibzdailies/searchmyalldaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我收到的日报
#### 访问路径
/ibzdailies/fetchmydaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询我收到的日报
#### 访问路径
/ibzdailies/searchmydaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的日报
#### 访问路径
/ibzdailies/fetchmynotsubmit

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询我的日报
#### 访问路径
/ibzdailies/searchmynotsubmit

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我提交的日报
#### 访问路径
/ibzdailies/fetchmysubmitdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询我提交的日报
#### 访问路径
/ibzdailies/searchmysubmitdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品日报
#### 访问路径
/ibzdailies/fetchproductdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询产品日报
#### 访问路径
/ibzdailies/searchproductdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目日报
#### 访问路径
/ibzdailies/fetchprojectdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象列表 |

### 查询项目日报
#### 访问路径
/ibzdailies/searchprojectdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzDailySearchContext](#IbzDailySearchContext) | 日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzDailyDTO](#IbzDailyDTO)>：日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzDailyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 2 | submittime | Timestamp | 允许 | 提交时间<br>时间格式：HH:mm:ss |
| 3 | todaytask | String | 允许 | 完成任务 |
| 4 | worktoday | String | 允许 | 今日工作 |
| 5 | tomorrowplanstask | String | 允许 | 明日计划任务 |
| 6 | reportto | String | 允许 | 汇报给 |
| 7 | createman | String | 不可 | 建立人 |
| 8 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 9 | issubmit | String | 允许 | 是否提交 |
| 10 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 11 | files | String | 允许 | 附件 |
| 12 | updatemanname | String | 不可 | 更新人名称 |
| 13 | mailtopk | String | 允许 | 抄送给（选择） |
| 14 | reportstatus | String | 允许 | 状态 |
| 15 | comment | String | 允许 | 其他事项 |
| 16 | updateman | String | 不可 | 更新人 |
| 17 | planstomorrow | String | 允许 | 明日计划 |
| 18 | mailto | String | 允许 | 抄送给 |
| 19 | ibzdailyid | Long | 不可 | 日报标识 |
| 20 | account | String | 允许 | 用户 |
| 21 | reporttopk | String | 允许 | 汇报给（选择） |
| 22 | createmanname | String | 不可 | 建立人名称 |
| 23 | ibzdailyname | String | 允许 | 日报名称 |
| 24 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzDailySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_reportto_eq | String | 允许 | 条件字段：reportto<br>条件组合方式：`=` |
| 2 | n_issubmit_eq | String | 允许 | 条件字段：issubmit<br>条件组合方式：`=` |
| 3 | n_date_eq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`=`<br>时间格式：yyyy-MM-dd |
| 4 | n_date_gtandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 5 | n_date_ltandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 6 | n_reportstatus_eq | String | 允许 | 条件字段：reportstatus<br>条件组合方式：`=` |
| 7 | n_ibz_dailyid_eq | Long | 允许 | 条件字段：ibz_dailyid<br>条件组合方式：`=` |
| 8 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 9 | n_ibz_dailyname_like | String | 允许 | 条件字段：ibz_dailyname<br>条件组合方式：`%like%` |
| 10 | customcond | String | 允许 | 自定义查询条件 |
| 11 | customparams | String | 允许 | 自定义查询参数 |
| 12 | query | String | 允许 | 快速搜索 |
| 13 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 14 | page | int | 允许 | 当前页数<br>默认值0 |
| 15 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 16 | sort | String | 允许 | 排序 |
