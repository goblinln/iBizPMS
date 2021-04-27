# 服务接口-IBZ_FAVORITES
## 接口说明
收藏

## 接口清单
### 新建收藏
#### 访问路径
/ibzfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavoritesdto | [IbzFavoritesDTO](#IbzFavoritesDTO) | 收藏实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzFavoritesDTO](#IbzFavoritesDTO)：收藏实体传输对象 |

### 批量新建收藏
#### 访问路径
/ibzfavorites/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavoritesdtos | List<[IbzFavoritesDTO](#IbzFavoritesDTO)> | 收藏实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新收藏
#### 访问路径
/ibzfavorites/{ibzfavorites_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavorites_id | String | 收藏主键ID |
| 2 | ibzfavoritesdto | [IbzFavoritesDTO](#IbzFavoritesDTO) | 收藏实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzFavoritesDTO](#IbzFavoritesDTO)：收藏实体传输对象 |

### 批量更新收藏
#### 访问路径
/ibzfavorites/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavoritesdtos | List<[IbzFavoritesDTO](#IbzFavoritesDTO)> | 收藏实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除收藏
#### 访问路径
/ibzfavorites/{ibzfavorites_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavorites_id | String | 收藏主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除收藏
#### 访问路径
/ibzfavorites/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 收藏主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取收藏
#### 访问路径
/ibzfavorites/{ibzfavorites_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavorites_id | String | 收藏主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzFavoritesDTO](#IbzFavoritesDTO)：收藏实体传输对象 |

### 检查收藏
#### 访问路径
/ibzfavorites/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavoritesdto | [IbzFavoritesDTO](#IbzFavoritesDTO) | 收藏实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存收藏
#### 访问路径
/ibzfavorites/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavoritesdto | [IbzFavoritesDTO](#IbzFavoritesDTO) | 收藏实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存收藏
#### 访问路径
/ibzfavorites/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzfavoritesdtos | List<[IbzFavoritesDTO](#IbzFavoritesDTO)> | 收藏实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibzfavorites/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzFavoritesSearchContext](#IbzFavoritesSearchContext) | 收藏查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzFavoritesDTO](#IbzFavoritesDTO)>：收藏实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzfavorites/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzFavoritesSearchContext](#IbzFavoritesSearchContext) | 收藏查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzFavoritesDTO](#IbzFavoritesDTO)>：收藏实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzFavoritesDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | type | String | 允许 | 类型 |
| 2 | createman | String | 不可 | 建立人 |
| 3 | ibzfavoritesid | String | 不可 | 收藏标识 |
| 4 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | updateman | String | 不可 | 更新人 |
| 6 | objectid | Long | 允许 | 数据对象标识 |
| 7 | account | String | 允许 | 收藏用户 |
| 8 | ibzfavoritesname | String | 允许 | 收藏名称 |
| 9 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzFavoritesSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 2 | n_ibz_favoritesid_eq | String | 允许 | 条件字段：ibz_favoritesid<br>条件组合方式：`=` |
| 3 | n_objectid_eq | Long | 允许 | 条件字段：objectid<br>条件组合方式：`=` |
| 4 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 5 | n_ibz_favoritesname_like | String | 允许 | 条件字段：ibz_favoritesname<br>条件组合方式：`%like%` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
