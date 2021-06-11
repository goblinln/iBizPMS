# 服务接口-IBZ_LIB
## 接口说明
用例库

## 接口清单
### 新建用例库
#### 访问路径
/ibzlibs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlibdto | [IbzLibDTO](#IbzLibDTO) | 用例库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibDTO](#IbzLibDTO)：用例库实体传输对象 |

### 批量新建用例库
#### 访问路径
/ibzlibs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlibdtos | List<[IbzLibDTO](#IbzLibDTO)> | 用例库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用例库
#### 访问路径
/ibzlibs/{ibzlib_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibDTO](#IbzLibDTO)：用例库实体传输对象 |

### 删除用例库
#### 访问路径
/ibzlibs/{ibzlib_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用例库
#### 访问路径
/ibzlibs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 用例库主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用例库
#### 访问路径
/ibzlibs/{ibzlib_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzlibdto | [IbzLibDTO](#IbzLibDTO) | 用例库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibDTO](#IbzLibDTO)：用例库实体传输对象 |

### 批量更新用例库
#### 访问路径
/ibzlibs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlibdtos | List<[IbzLibDTO](#IbzLibDTO)> | 用例库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查用例库
#### 访问路径
/ibzlibs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlibdto | [IbzLibDTO](#IbzLibDTO) | 用例库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存用例库
#### 访问路径
/ibzlibs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlibdto | [IbzLibDTO](#IbzLibDTO) | 用例库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用例库
#### 访问路径
/ibzlibs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlibdtos | List<[IbzLibDTO](#IbzLibDTO)> | 用例库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/ibzlibs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzLibSearchContext](#IbzLibSearchContext) | 用例库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzLibDTO](#IbzLibDTO)>：用例库实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/ibzlibs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzLibSearchContext](#IbzLibSearchContext) | 用例库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzLibDTO](#IbzLibDTO)>：用例库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzLibDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | desc | String | 允许 | 描述 |
| 2 | product | Long | 允许 | 产品 |
| 3 | addeddate | Timestamp | 允许 | 创建时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 4 | id | Long | 不可 | 编号 |
| 5 | lastediteddate | Timestamp | 允许 | 最后编辑时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | addedby | String | 允许 | 由谁创建 |
| 7 | deleted | String | 允许 | 已删除 |
| 8 | name | String | 不可 | 名称 |
| 9 | type | String | 允许 | 类型 |
| 10 | lasteditedby | String | 允许 | 最后编辑人 |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzLibSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_addedby_eq | String | 允许 | 条件字段：addedby<br>条件组合方式：`=` |
| 3 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
