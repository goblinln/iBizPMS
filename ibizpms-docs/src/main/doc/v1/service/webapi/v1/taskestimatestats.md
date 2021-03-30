# 服务接口-TASKESTIMATESTATS
## 接口说明
任务工时统计

## 接口清单
### 新建任务工时统计
#### 访问路径
/taskestimatestats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestatsdto | [taskestimatestatsDTO](#taskestimatestatsDTO) | 任务工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [taskestimatestatsDTO](#taskestimatestatsDTO)：任务工时统计实体传输对象 |

### 批量新建任务工时统计
#### 访问路径
/taskestimatestats/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestatsdtos | List<[taskestimatestatsDTO](#taskestimatestatsDTO)> | 任务工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新任务工时统计
#### 访问路径
/taskestimatestats/{taskestimatestats_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestats_id | Long | 任务工时统计主键ID |
| 2 | taskestimatestatsdto | [taskestimatestatsDTO](#taskestimatestatsDTO) | 任务工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [taskestimatestatsDTO](#taskestimatestatsDTO)：任务工时统计实体传输对象 |

### 批量更新任务工时统计
#### 访问路径
/taskestimatestats/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestatsdtos | List<[taskestimatestatsDTO](#taskestimatestatsDTO)> | 任务工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除任务工时统计
#### 访问路径
/taskestimatestats/{taskestimatestats_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestats_id | Long | 任务工时统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除任务工时统计
#### 访问路径
/taskestimatestats/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 任务工时统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取任务工时统计
#### 访问路径
/taskestimatestats/{taskestimatestats_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestats_id | Long | 任务工时统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [taskestimatestatsDTO](#taskestimatestatsDTO)：任务工时统计实体传输对象 |

### 检查任务工时统计
#### 访问路径
/taskestimatestats/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestatsdto | [taskestimatestatsDTO](#taskestimatestatsDTO) | 任务工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存任务工时统计
#### 访问路径
/taskestimatestats/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestatsdto | [taskestimatestatsDTO](#taskestimatestatsDTO) | 任务工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存任务工时统计
#### 访问路径
/taskestimatestats/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatestatsdtos | List<[taskestimatestatsDTO](#taskestimatestatsDTO)> | 任务工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取日志月
#### 访问路径
/taskestimatestats/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateStatsSearchContext](#TaskEstimateStatsSearchContext) | 任务工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[taskestimatestatsDTO](#taskestimatestatsDTO)>：任务工时统计实体传输对象列表 |

### 查询日志月
#### 访问路径
/taskestimatestats/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateStatsSearchContext](#TaskEstimateStatsSearchContext) | 任务工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[taskestimatestatsDTO](#taskestimatestatsDTO)>：任务工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取日志年
#### 访问路径
/taskestimatestats/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateStatsSearchContext](#TaskEstimateStatsSearchContext) | 任务工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[taskestimatestatsDTO](#taskestimatestatsDTO)>：任务工时统计实体传输对象列表 |

### 查询日志年
#### 访问路径
/taskestimatestats/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateStatsSearchContext](#TaskEstimateStatsSearchContext) | 任务工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[taskestimatestatsDTO](#taskestimatestatsDTO)>：任务工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/taskestimatestats/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateStatsSearchContext](#TaskEstimateStatsSearchContext) | 任务工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[taskestimatestatsDTO](#taskestimatestatsDTO)>：任务工时统计实体传输对象列表 |

### 查询数据集
#### 访问路径
/taskestimatestats/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateStatsSearchContext](#TaskEstimateStatsSearchContext) | 任务工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[taskestimatestatsDTO](#taskestimatestatsDTO)>：任务工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### taskestimatestatsDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | date | String | 允许 | 日期 |
| 2 | year | String | 允许 | 年 |
| 3 | consumed | Double | 允许 | 消耗的工时 |
| 4 | evaluationstatus | String | 允许 | 评估状态 |
| 5 | account | String | 允许 | 用户 |
| 6 | id | Long | 允许 | 编号 |
| 7 | monthname | String | 允许 | 月（显示） |
| 8 | name | String | 允许 | 项目名称 |
| 9 | yearname | String | 允许 | 年（显示） |
| 10 | evaluationtime | Double | 允许 | 评估工时 |
| 11 | evaluationcost | Double | 允许 | 评估成本 |
| 12 | inputcost | Double | 允许 | 投入成本 |
| 13 | month | String | 允许 | 月 |
| 14 | taskcnt | Integer | 允许 | 任务数 |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TaskEstimateStatsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | customcond | String | 允许 | 自定义查询条件 |
| 2 | customparams | String | 允许 | 自定义查询参数 |
| 3 | query | String | 允许 | 快速搜索 |
| 4 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 5 | page | int | 允许 | 当前页数<br>默认值0 |
| 6 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 7 | sort | String | 允许 | 排序 |
