# 服务接口-IBZPRO_CONFIG
## 接口说明
系统配置表

## 接口清单
### 新建系统配置表
#### 访问路径
/ibzproconfigs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfigdto | [IbzproConfigDTO](#IbzproConfigDTO) | 系统配置表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproConfigDTO](#IbzproConfigDTO)：系统配置表实体传输对象 |

### 批量新建系统配置表
#### 访问路径
/ibzproconfigs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfigdtos | List<[IbzproConfigDTO](#IbzproConfigDTO)> | 系统配置表实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统配置表
#### 访问路径
/ibzproconfigs/{ibzproconfig_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfig_id | String | 系统配置表主键ID |
| 2 | ibzproconfigdto | [IbzproConfigDTO](#IbzproConfigDTO) | 系统配置表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproConfigDTO](#IbzproConfigDTO)：系统配置表实体传输对象 |

### 批量更新系统配置表
#### 访问路径
/ibzproconfigs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfigdtos | List<[IbzproConfigDTO](#IbzproConfigDTO)> | 系统配置表实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除系统配置表
#### 访问路径
/ibzproconfigs/{ibzproconfig_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfig_id | String | 系统配置表主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统配置表
#### 访问路径
/ibzproconfigs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 系统配置表主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统配置表
#### 访问路径
/ibzproconfigs/{ibzproconfig_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfig_id | String | 系统配置表主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproConfigDTO](#IbzproConfigDTO)：系统配置表实体传输对象 |

### 检查系统配置表
#### 访问路径
/ibzproconfigs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfigdto | [IbzproConfigDTO](#IbzproConfigDTO) | 系统配置表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统配置
#### 访问路径
/ibzproconfigs/{ibzproconfig_id}/getsystemconfig

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfig_id | String | 系统配置表主键ID |
| 2 | ibzproconfigdto | [IbzproConfigDTO](#IbzproConfigDTO) | 系统配置表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzproConfigDTO](#IbzproConfigDTO)：系统配置表实体传输对象 |

### 保存系统配置表
#### 访问路径
/ibzproconfigs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfigdto | [IbzproConfigDTO](#IbzproConfigDTO) | 系统配置表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统配置表
#### 访问路径
/ibzproconfigs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzproconfigdtos | List<[IbzproConfigDTO](#IbzproConfigDTO)> | 系统配置表实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibzproconfigs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproConfigSearchContext](#IbzproConfigSearchContext) | 系统配置表查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzproConfigDTO](#IbzproConfigDTO)>：系统配置表实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzproconfigs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzproConfigSearchContext](#IbzproConfigSearchContext) | 系统配置表查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzproConfigDTO](#IbzproConfigDTO)>：系统配置表实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzproConfigDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | updateman | String | 不可 | 更新人 |
| 2 | ibzproconfigname | String | 允许 | 系统配置表名称 |
| 3 | memo | String | 允许 | 描述 |
| 4 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | ibzproconfigid | String | 不可 | 系统配置表标识 |
| 7 | scope | String | 允许 | 范围 |
| 8 | type | String | 允许 | 类型 |
| 9 | managementstatus | String | 允许 | 管理现状 |
| 10 | vaild | String | 允许 | 是否启用 |
| 11 | createman | String | 不可 | 建立人 |
| 12 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzproConfigSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibzpro_configname_like | String | 允许 | 条件字段：ibzpro_configname<br>条件组合方式：`%like%` |
| 2 | n_ibzpro_configid_eq | String | 允许 | 条件字段：ibzpro_configid<br>条件组合方式：`=` |
| 3 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 4 | n_managementstatus_eq | String | 允许 | 条件字段：managementstatus<br>条件组合方式：`=` |
| 5 | n_vaild_eq | String | 允许 | 条件字段：vaild<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
