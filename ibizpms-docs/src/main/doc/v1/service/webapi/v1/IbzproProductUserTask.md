# 服务接口-IBIZPRO_PRODUCTUSERTASK
## 接口说明
产品汇报用户任务

## 接口清单
### 新建产品汇报用户任务
#### 访问路径
/ibzproproductusertasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertaskdto | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO) | 产品汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)：产品汇报用户任务实体传输对象 |

### 批量新建产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertaskdtos | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)> | 产品汇报用户任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/{ibzproproductusertask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertask_id | Long | 产品汇报用户任务主键ID |
| 2 | ibzproproductusertaskdto | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO) | 产品汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)：产品汇报用户任务实体传输对象 |

### 批量更新产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertaskdtos | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)> | 产品汇报用户任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/{ibzproproductusertask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertask_id | Long | 产品汇报用户任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 产品汇报用户任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/{ibzproproductusertask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertask_id | Long | 产品汇报用户任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)：产品汇报用户任务实体传输对象 |

### 检查产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertaskdto | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO) | 产品汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertaskdto | [IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO) | 产品汇报用户任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存产品汇报用户任务
#### 访问路径
/ibzproproductusertasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproproductusertaskdtos | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)> | 产品汇报用户任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibzproproductusertasks/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzproproductusertasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品日报用户任务统计
#### 访问路径
/ibzproproductusertasks/fetchproductdailyusertaskstats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象列表 |

### 查询产品日报用户任务统计
#### 访问路径
/ibzproproductusertasks/searchproductdailyusertaskstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品月报用户任务统计
#### 访问路径
/ibzproproductusertasks/fetchproductmonthlyusertaskstats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象列表 |

### 查询产品月报用户任务统计
#### 访问路径
/ibzproproductusertasks/searchproductmonthlyusertaskstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品周报用户任务统计
#### 访问路径
/ibzproproductusertasks/fetchproductweeklyusertaskstats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象列表 |

### 查询产品周报用户任务统计
#### 访问路径
/ibzproproductusertasks/searchproductweeklyusertaskstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproProductUserTaskSearchContext](#IbzproProductUserTaskSearchContext) | 产品汇报用户任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproProductUserTaskDTO](#IbzproProductUserTaskDTO)>：产品汇报用户任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzproProductUserTaskDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | tasktype | String | 允许 | 任务类型 |
| 2 | account | String | 允许 | 用户 |
| 3 | consumed | Double | 允许 | 总计消耗 |
| 4 | id | Long | 不可 | 编号 |
| 5 | taskname | String | 允许 | 任务名称 |
| 6 | progressrate | String | 允许 | 进度 |
| 7 | eststarted | Timestamp | 允许 | 预计开始<br>时间格式：yyyy-MM-dd |
| 8 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 9 | delaydays | String | 允许 | 延期天数 |
| 10 | task | Long | 允许 | 任务 |
| 11 | left | Double | 允许 | 预计剩余 |
| 12 | deadline | Timestamp | 允许 | 截止日期<br>时间格式：yyyy-MM-dd |
| 13 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzproProductUserTaskSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_tasktype_eq | String | 允许 | 条件字段：tasktype<br>条件组合方式：`=` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | customcond | String | 允许 | 自定义查询条件 |
| 4 | customparams | String | 允许 | 自定义查询参数 |
| 5 | query | String | 允许 | 快速搜索 |
| 6 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 7 | page | int | 允许 | 当前页数<br>默认值0 |
| 8 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 9 | sort | String | 允许 | 排序 |
