# 服务接口-ZT_TODO
## 接口说明
待办

## 接口清单
### 新建待办
#### 访问路径
/todos

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 批量新建待办
#### 访问路径
/todos/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | tododtos | List<[TodoDTO](#TodoDTO)> | 待办实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取待办
#### 访问路径
/todos/{todo_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 删除待办
#### 访问路径
/todos/{todo_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除待办
#### 访问路径
/todos/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 待办主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新待办
#### 访问路径
/todos/{todo_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 批量更新待办
#### 访问路径
/todos/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | tododtos | List<[TodoDTO](#TodoDTO)> | 待办实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### Activate
#### 访问路径
/todos/{todo_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### AssignTo
#### 访问路径
/todos/{todo_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 检查待办
#### 访问路径
/todos/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### Close
#### 访问路径
/todos/{todo_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 定时创建周期
#### 访问路径
/todos/{todo_id}/createcycle

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### Finish
#### 访问路径
/todos/{todo_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 保存待办
#### 访问路径
/todos/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存待办
#### 访问路径
/todos/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | tododtos | List<[TodoDTO](#TodoDTO)> | 待办实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/todos/{todo_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 发送消息前置处理
#### 访问路径
/todos/{todo_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | todo_id | Long | 待办主键ID |
| 2 | tododto | [TodoDTO](#TodoDTO) | 待办实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TodoDTO](#TodoDTO)：待办实体传输对象 |

### 获取DEFAULT
#### 访问路径
/todos/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TodoDTO](#TodoDTO)>：待办实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/todos/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TodoDTO](#TodoDTO)>：待办实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的待办
#### 访问路径
/todos/fetchmytodo

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TodoDTO](#TodoDTO)>：待办实体传输对象列表 |

### 查询我的待办
#### 访问路径
/todos/searchmytodo

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TodoDTO](#TodoDTO)>：待办实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的待办
#### 访问路径
/todos/fetchmytodopc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TodoDTO](#TodoDTO)>：待办实体传输对象列表 |

### 查询我的待办
#### 访问路径
/todos/searchmytodopc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TodoDTO](#TodoDTO)>：待办实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取MyUpcoming
#### 访问路径
/todos/fetchmyupcoming

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TodoDTO](#TodoDTO)>：待办实体传输对象列表 |

### 查询MyUpcoming
#### 访问路径
/todos/searchmyupcoming

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TodoSearchContext](#TodoSearchContext) | 待办查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TodoDTO](#TodoDTO)>：待办实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TodoDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | id | Long | 不可 | 编号 |
| 2 | account | String | 允许 | 所有者 |
| 3 | config_day | Integer | 允许 | 间隔天数 |
| 4 | assignedtopk | String | 允许 | 指派给（选择） |
| 5 | date1 | String | 允许 | 日期 |
| 6 | config_type | String | 允许 | 周期类型 |
| 7 | closeddate | Timestamp | 允许 | 关闭时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 8 | closedby | String | 允许 | 由谁关闭 |
| 9 | type | String | 允许 | 类型 |
| 10 | end | Integer | 允许 | 结束 |
| 11 | desc | String | 允许 | 描述 |
| 12 | noticeusers | String | 允许 | 消息通知用户 |
| 13 | finishedby | String | 允许 | 由谁完成 |
| 14 | begin | Integer | 允许 | 开始 |
| 15 | idvalue | Long | 允许 | 关联编号 |
| 16 | assignedby | String | 允许 | 由谁指派 |
| 17 | config_month | String | 允许 | 周期设置月 |
| 18 | task | String | 允许 | 待办名称 |
| 19 | bug | String | 允许 | 待办名称 |
| 20 | finisheddate | Timestamp | 允许 | 完成时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 21 | cycle | Integer | 允许 | 周期 |
| 22 | date_disable | String | 允许 | 待定 |
| 23 | config_week | String | 允许 | 周期设置周几 |
| 24 | assignedto | String | 允许 | 指派给 |
| 25 | status | String | 允许 | 状态 |
| 26 | config_beforedays | Integer | 允许 | 提前 |
| 27 | name | String | 允许 | 待办名称 |
| 28 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd |
| 29 | config_end | Timestamp | 允许 | 过期时间<br>时间格式：yyyy-MM-dd |
| 30 | cost | Integer | 允许 | 费用 |
| 31 | pri | Integer | 允许 | 优先级 |
| 32 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 33 | story | String | 允许 | 待办名称 |
| 34 | ibizprivate | String | 允许 | 私人事务 |
| 35 | config | String | 允许 | config |
| 36 | consumed | Double | 允许 | 工时 |
| 37 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TodoSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 3 | n_config_type_eq | String | 允许 | 条件字段：config_type<br>条件组合方式：`=` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | n_type_noteq | String | 允许 | 条件字段：type<br>条件组合方式：`!=`或者`<>` |
| 6 | n_end_eq | Integer | 允许 | 条件字段：end<br>条件组合方式：`=` |
| 7 | n_begin_eq | Integer | 允许 | 条件字段：begin<br>条件组合方式：`=` |
| 8 | n_cycle_eq | Integer | 允许 | 条件字段：cycle<br>条件组合方式：`=` |
| 9 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 10 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 11 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 12 | n_date_eq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`=`<br>时间格式：yyyy-MM-dd |
| 13 | n_date_gtandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 14 | n_date_ltandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 15 | n_date_noteq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`!=`或者`<>`<br>时间格式：yyyy-MM-dd |
| 16 | n_private_eq | String | 允许 | 条件字段：private<br>条件组合方式：`=` |
| 17 | customcond | String | 允许 | 自定义查询条件 |
| 18 | customparams | String | 允许 | 自定义查询参数 |
| 19 | query | String | 允许 | 快速搜索 |
| 20 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 21 | page | int | 允许 | 当前页数<br>默认值0 |
| 22 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 23 | sort | String | 允许 | 排序 |
