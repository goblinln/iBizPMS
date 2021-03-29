# 服务接口-IBZ_TASKSTATS
## 接口说明
任务统计

## 接口清单
### 新建任务统计
#### 访问路径
/taskstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstatsdto | [TaskStatsDTO](#TaskStatsDTO) | 任务统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskStatsDTO](#TaskStatsDTO)：任务统计实体传输对象 |

### 批量新建任务统计
#### 访问路径
/taskstats/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstatsdtos | List<[TaskStatsDTO](#TaskStatsDTO)> | 任务统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新任务统计
#### 访问路径
/taskstats/{taskstats_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstats_id | Long | 任务统计主键ID |
| 2 | taskstatsdto | [TaskStatsDTO](#TaskStatsDTO) | 任务统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskStatsDTO](#TaskStatsDTO)：任务统计实体传输对象 |

### 批量更新任务统计
#### 访问路径
/taskstats/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstatsdtos | List<[TaskStatsDTO](#TaskStatsDTO)> | 任务统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除任务统计
#### 访问路径
/taskstats/{taskstats_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstats_id | Long | 任务统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除任务统计
#### 访问路径
/taskstats/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 任务统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取任务统计
#### 访问路径
/taskstats/{taskstats_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstats_id | Long | 任务统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskStatsDTO](#TaskStatsDTO)：任务统计实体传输对象 |

### 检查任务统计
#### 访问路径
/taskstats/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstatsdto | [TaskStatsDTO](#TaskStatsDTO) | 任务统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存任务统计
#### 访问路径
/taskstats/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstatsdto | [TaskStatsDTO](#TaskStatsDTO) | 任务统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存任务统计
#### 访问路径
/taskstats/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskstatsdtos | List<[TaskStatsDTO](#TaskStatsDTO)> | 任务统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/taskstats/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskStatsSearchContext](#TaskStatsSearchContext) | 任务统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskStatsDTO](#TaskStatsDTO)>：任务统计实体传输对象列表 |

### 查询数据集
#### 访问路径
/taskstats/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskStatsSearchContext](#TaskStatsSearchContext) | 任务统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskStatsDTO](#TaskStatsDTO)>：任务统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务完成汇总表
#### 访问路径
/taskstats/fetchtaskfinishhuizong

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskStatsSearchContext](#TaskStatsSearchContext) | 任务统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskStatsDTO](#TaskStatsDTO)>：任务统计实体传输对象列表 |

### 查询任务完成汇总表
#### 访问路径
/taskstats/searchtaskfinishhuizong

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskStatsSearchContext](#TaskStatsSearchContext) | 任务统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskStatsDTO](#TaskStatsDTO)>：任务统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户完成任务统计
#### 访问路径
/taskstats/fetchuserfinishtasksum

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskStatsSearchContext](#TaskStatsSearchContext) | 任务统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskStatsDTO](#TaskStatsDTO)>：任务统计实体传输对象列表 |

### 查询用户完成任务统计
#### 访问路径
/taskstats/searchuserfinishtasksum

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskStatsSearchContext](#TaskStatsSearchContext) | 任务统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskStatsDTO](#TaskStatsDTO)>：任务统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TaskStatsDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | taskdeadline | Timestamp | 允许 | 任务截至日期<br>时间格式：yyyy-MM-dd |
| 2 | taskid | Integer | 允许 | 任务编号 |
| 3 | taskrealstart | Timestamp | 允许 | 任务实际开始时间<br>时间格式：yyyy-MM-dd |
| 4 | taskefficient | String | 允许 | 效率 |
| 5 | taskestimate | Integer | 允许 | 任务预计消耗 |
| 6 | end | Timestamp | 允许 | 结束<br>时间格式：yyyy-MM-dd |
| 7 | userconsumed | Integer | 允许 | 用户总消耗 |
| 8 | totalleft | Integer | 允许 | 剩余总工时 |
| 9 | projectconsumed | Integer | 允许 | 项目总消耗 |
| 10 | begin | Timestamp | 允许 | 属性<br>时间格式：yyyy-MM-dd |
| 11 | totalconsumed | Integer | 允许 | 消耗总工时 |
| 12 | totalestimate | Integer | 允许 | 预计总工时 |
| 13 | taskeststarted | Timestamp | 允许 | 任务预计开始日期<br>时间格式：yyyy-MM-dd |
| 14 | id | Long | 允许 | 编号 |
| 15 | dept | String | 允许 | 部门 |
| 16 | name | String | 允许 | 名称 |
| 17 | projectname | String | 允许 | 项目名称 |
| 18 | project | String | 允许 | 项目 |
| 19 | taskfinisheddate | Timestamp | 允许 | 任务实际完成日期<br>时间格式：yyyy-MM-dd |
| 20 | taskname | String | 允许 | 任务名称 |
| 21 | taskcnt | Integer | 允许 | 总任务数 |
| 22 | taskpri | Integer | 允许 | 任务优先级 |
| 23 | taskdelay | String | 允许 | 任务延期 |
| 24 | finishedby | String | 允许 | 完成者 |
| 25 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TaskStatsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_end_ltandeq | Timestamp | 允许 | 条件字段：end<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 2 | n_begin_gtandeq | Timestamp | 允许 | 条件字段：begin<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 3 | n_dept_eq | String | 允许 | 条件字段：dept<br>条件组合方式：`=` |
| 4 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 5 | n_project_eq | String | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 6 | n_finishedby_eq | String | 允许 | 条件字段：finishedby<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
