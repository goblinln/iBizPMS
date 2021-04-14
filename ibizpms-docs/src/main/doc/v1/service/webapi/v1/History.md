# 服务接口-ZT_HISTORY
## 接口说明
操作历史

## 接口清单
### 新建操作历史
#### 访问路径
/histories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [HistoryDTO](#HistoryDTO)：操作历史实体传输对象 |

### 批量新建操作历史
#### 访问路径
/histories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | historydtos | List<[HistoryDTO](#HistoryDTO)> | 操作历史实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新操作历史
#### 访问路径
/histories/{history_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | history_id | Long | 操作历史主键ID |
| 2 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [HistoryDTO](#HistoryDTO)：操作历史实体传输对象 |

### 批量更新操作历史
#### 访问路径
/histories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | historydtos | List<[HistoryDTO](#HistoryDTO)> | 操作历史实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除操作历史
#### 访问路径
/histories/{history_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | history_id | Long | 操作历史主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除操作历史
#### 访问路径
/histories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 操作历史主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取操作历史
#### 访问路径
/histories/{history_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | history_id | Long | 操作历史主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [HistoryDTO](#HistoryDTO)：操作历史实体传输对象 |

### 检查操作历史
#### 访问路径
/histories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存操作历史
#### 访问路径
/histories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存操作历史
#### 访问路径
/histories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | historydtos | List<[HistoryDTO](#HistoryDTO)> | 操作历史实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/histories/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [HistorySearchContext](#HistorySearchContext) | 操作历史查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[HistoryDTO](#HistoryDTO)>：操作历史实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/histories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [HistorySearchContext](#HistorySearchContext) | 操作历史查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[HistoryDTO](#HistoryDTO)>：操作历史实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立操作历史
#### 访问路径
/actions/{action_id}/histories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [HistoryDTO](#HistoryDTO)：操作历史实体传输对象 |

### 根据批量建立操作历史
#### 访问路径
/actions/{action_id}/histories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | historydtos | List<[HistoryDTO](#HistoryDTO)> | 操作历史实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新操作历史
#### 访问路径
/actions/{action_id}/histories/{history_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | history_id | Long | 操作历史主键ID |
| 3 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [HistoryDTO](#HistoryDTO)：操作历史实体传输对象 |

### 根据批量更新操作历史
#### 访问路径
/actions/{action_id}/histories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | historydtos | List<[HistoryDTO](#HistoryDTO)> | 操作历史实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除操作历史
#### 访问路径
/actions/{action_id}/histories/{history_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | history_id | Long | 操作历史主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除操作历史
#### 访问路径
/actions/{action_id}/histories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | ids | List<Long> | 操作历史主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取操作历史
#### 访问路径
/actions/{action_id}/histories/{history_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | history_id | Long | 操作历史主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [HistoryDTO](#HistoryDTO)：操作历史实体传输对象 |

### 根据检查操作历史
#### 访问路径
/actions/{action_id}/histories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存操作历史
#### 访问路径
/actions/{action_id}/histories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | historydto | [HistoryDTO](#HistoryDTO) | 操作历史实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存操作历史
#### 访问路径
/actions/{action_id}/histories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | historydtos | List<[HistoryDTO](#HistoryDTO)> | 操作历史实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/actions/{action_id}/histories/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | context | [HistorySearchContext](#HistorySearchContext) | 操作历史查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[HistoryDTO](#HistoryDTO)>：操作历史实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/actions/{action_id}/histories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | context | [HistorySearchContext](#HistorySearchContext) | 操作历史查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[HistoryDTO](#HistoryDTO)>：操作历史实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### HistoryDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | diff | String | 允许 | 不同 |
| 2 | field | String | 允许 | 字段 |
| 3 | ibiznew | String | 允许 | 新值 |
| 4 | old | String | 允许 | 旧值 |
| 5 | id | Long | 不可 | id |
| 6 | action | Long | 允许 | 关联日志 |
| 7 | createby | String | 允许 | 由谁创建 |
| 8 | updateby | String | 允许 | 由谁更新 |
| 9 | org | String | 允许 | 归属组织 |
| 10 | dept | String | 允许 | 归属部门 |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

#### HistorySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_field_leftlike | String | 允许 | 条件字段：field<br>条件组合方式：`%like` |
| 2 | n_action_eq | Long | 允许 | 条件字段：action<br>条件组合方式：`=` |
| 3 | customcond | String | 允许 | 自定义查询条件 |
| 4 | customparams | String | 允许 | 自定义查询参数 |
| 5 | query | String | 允许 | 快速搜索 |
| 6 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 7 | page | int | 允许 | 当前页数<br>默认值0 |
| 8 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 9 | sort | String | 允许 | 排序 |
