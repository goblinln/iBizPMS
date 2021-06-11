# 服务接口-IBZ_PRODUCTLIFE
## 接口说明
产品生命周期

## 接口清单
### 获取GetRoadmap
#### 访问路径
/productlives/fetchgetroadmap

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductLifeSearchContext](#ProductLifeSearchContext) | 产品生命周期查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductLifeDTO](#ProductLifeDTO)>：产品生命周期实体传输对象列表 |

### 查询GetRoadmap
#### 访问路径
/productlives/searchgetroadmap

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductLifeSearchContext](#ProductLifeSearchContext) | 产品生命周期查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductLifeDTO](#ProductLifeDTO)>：产品生命周期实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品路线
#### 访问路径
/productlives/fetchgetroadmaps

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductLifeSearchContext](#ProductLifeSearchContext) | 产品生命周期查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductLifeDTO](#ProductLifeDTO)>：产品生命周期实体传输对象列表 |

### 查询获取产品路线
#### 访问路径
/productlives/searchgetroadmaps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductLifeSearchContext](#ProductLifeSearchContext) | 产品生命周期查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductLifeDTO](#ProductLifeDTO)>：产品生命周期实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取RoadMapYear
#### 访问路径
/productlives/fetchroadmapyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductLifeSearchContext](#ProductLifeSearchContext) | 产品生命周期查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductLifeDTO](#ProductLifeDTO)>：产品生命周期实体传输对象列表 |

### 查询RoadMapYear
#### 访问路径
/productlives/searchroadmapyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductLifeSearchContext](#ProductLifeSearchContext) | 产品生命周期查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductLifeDTO](#ProductLifeDTO)>：产品生命周期实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProductLifeDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | branch | String | 允许 | 平台/分支 |
| 2 | createman | String | 不可 | 建立人 |
| 3 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 4 | productlifename | String | 允许 | 产品生命周期名称 |
| 5 | product | Integer | 允许 | 产品 |
| 6 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | parent | String | 允许 | 父对象 |
| 8 | year | String | 允许 | 年 |
| 9 | updateman | String | 不可 | 更新人 |
| 10 | type | String | 允许 | 属性 |
| 11 | marker | Integer | 允许 | 里程碑 |
| 12 | begin | Timestamp | 允许 | 开始日期<br>时间格式：yyyy-MM-dd |
| 13 | productlifeid | String | 不可 | 产品生命周期标识 |
| 14 | end | Timestamp | 允许 | 结束日期<br>时间格式：yyyy-MM-dd |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProductLifeSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibz_productlifename_like | String | 允许 | 条件字段：ibz_productlifename<br>条件组合方式：`%like%` |
| 2 | n_year_eq | String | 允许 | 条件字段：year<br>条件组合方式：`=` |
| 3 | n_ibz_productlifeid_eq | String | 允许 | 条件字段：ibz_productlifeid<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
