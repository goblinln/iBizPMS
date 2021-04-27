# 服务接口-IBZ_TOP
## 接口说明
置顶

## 接口清单
### 新建置顶
#### 访问路径
/ibztops

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztopdto | [IbzTopDTO](#IbzTopDTO) | 置顶实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzTopDTO](#IbzTopDTO)：置顶实体传输对象 |

### 批量新建置顶
#### 访问路径
/ibztops/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztopdtos | List<[IbzTopDTO](#IbzTopDTO)> | 置顶实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新置顶
#### 访问路径
/ibztops/{ibztop_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztop_id | String | 置顶主键ID |
| 2 | ibztopdto | [IbzTopDTO](#IbzTopDTO) | 置顶实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzTopDTO](#IbzTopDTO)：置顶实体传输对象 |

### 批量更新置顶
#### 访问路径
/ibztops/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztopdtos | List<[IbzTopDTO](#IbzTopDTO)> | 置顶实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除置顶
#### 访问路径
/ibztops/{ibztop_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztop_id | String | 置顶主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除置顶
#### 访问路径
/ibztops/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 置顶主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取置顶
#### 访问路径
/ibztops/{ibztop_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztop_id | String | 置顶主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzTopDTO](#IbzTopDTO)：置顶实体传输对象 |

### 检查置顶
#### 访问路径
/ibztops/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztopdto | [IbzTopDTO](#IbzTopDTO) | 置顶实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存置顶
#### 访问路径
/ibztops/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztopdto | [IbzTopDTO](#IbzTopDTO) | 置顶实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存置顶
#### 访问路径
/ibztops/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibztopdtos | List<[IbzTopDTO](#IbzTopDTO)> | 置顶实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibztops/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzTopSearchContext](#IbzTopSearchContext) | 置顶查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzTopDTO](#IbzTopDTO)>：置顶实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibztops/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzTopSearchContext](#IbzTopSearchContext) | 置顶查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzTopDTO](#IbzTopDTO)>：置顶实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzTopDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 2 | updateman | String | 不可 | 更新人 |
| 3 | objectorder | Integer | 允许 | 排序 |
| 4 | ibztopid | String | 不可 | 标识 |
| 5 | objectid | Integer | 允许 | 数据对象标识 |
| 6 | ibztopname | String | 允许 | 置顶名称 |
| 7 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 8 | createman | String | 不可 | 建立人 |
| 9 | account | String | 允许 | 置顶用户 |
| 10 | type | String | 允许 | 类型 |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzTopSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibz_topid_eq | String | 允许 | 条件字段：ibz_topid<br>条件组合方式：`=` |
| 2 | n_ibz_topname_like | String | 允许 | 条件字段：ibz_topname<br>条件组合方式：`%like%` |
| 3 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | customcond | String | 允许 | 自定义查询条件 |
| 6 | customparams | String | 允许 | 自定义查询参数 |
| 7 | query | String | 允许 | 快速搜索 |
| 8 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 9 | page | int | 允许 | 当前页数<br>默认值0 |
| 10 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 11 | sort | String | 允许 | 排序 |
