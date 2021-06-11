# 服务接口-IBIZPRO_PROJECTUSERTASK
## 接口说明
项目汇报用户任务

## 接口清单
### 新建项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertaskdto | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO) | 项目汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)：项目汇报用户任务实体传输对象 |

### 批量新建项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertaskdtos | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)> | 项目汇报用户任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/{ibzproprojectusertask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertask_id | Long | 项目汇报用户任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)：项目汇报用户任务实体传输对象 |

### 删除项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/{ibzproprojectusertask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertask_id | Long | 项目汇报用户任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 项目汇报用户任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/{ibzproprojectusertask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertask_id | Long | 项目汇报用户任务主键ID |
| 2 | ibzproprojectusertaskdto | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO) | 项目汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)：项目汇报用户任务实体传输对象 |

### 批量更新项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertaskdtos | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)> | 项目汇报用户任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertaskdto | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO) | 项目汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertaskdto | [IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO) | 项目汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目汇报用户任务
#### 访问路径
/ibzproprojectusertasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproprojectusertaskdtos | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)> | 项目汇报用户任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibzproprojectusertasks/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzproprojectusertasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目日报任务
#### 访问路径
/ibzproprojectusertasks/fetchprojectdailytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象列表 |

### 查询项目日报任务
#### 访问路径
/ibzproprojectusertasks/searchprojectdailytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目日报任务
#### 访问路径
/ibzproprojectusertasks/fetchprojectmonthlytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象列表 |

### 查询项目日报任务
#### 访问路径
/ibzproprojectusertasks/searchprojectmonthlytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目周报任务
#### 访问路径
/ibzproprojectusertasks/fetchprojectweeklytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象列表 |

### 查询项目周报任务
#### 访问路径
/ibzproprojectusertasks/searchprojectweeklytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProjectUserTaskSearchContext](#IbzproProjectUserTaskSearchContext) | 项目汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProjectUserTaskDTO](#IbzproProjectUserTaskDTO)>：项目汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzproProjectUserTaskDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | work | String | 允许 | work |
| 2 | tasktype | String | 允许 | 任务类型 |
| 3 | account | String | 允许 | 用户 |
| 4 | consumed | Double | 允许 | 总计消耗 |
| 5 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 6 | delaydays | String | 允许 | 延期天数 |
| 7 | id | Long | 不可 | 编号 |
| 8 | progressrate | String | 允许 | 进度 |
| 9 | eststarted | Timestamp | 允许 | 预计开始<br>时间格式：yyyy-MM-dd |
| 10 | deadline | Timestamp | 允许 | 截止日期<br>时间格式：yyyy-MM-dd |
| 11 | task | Long | 允许 | 任务 |
| 12 | left | Double | 允许 | 预计剩余 |
| 13 | taskname | String | 允许 | 任务名称 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzproProjectUserTaskSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_tasktype_eq | String | 允许 | 条件字段：tasktype<br>条件组合方式：`=` |
| 2 | n_id_like | Long | 允许 | 条件字段：id<br>条件组合方式：`%like%` |
| 3 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
