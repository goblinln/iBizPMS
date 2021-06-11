# 服务接口-IBIZPROINDEX
## 接口说明
索引检索

## 接口清单
### 新建索引检索
#### 访问路径
/ibizproindices

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindexdto | [IbizproIndexDTO](#IbizproIndexDTO) | 索引检索实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproIndexDTO](#IbizproIndexDTO)：索引检索实体传输对象 |

### 批量新建索引检索
#### 访问路径
/ibizproindices/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindexdtos | List<[IbizproIndexDTO](#IbizproIndexDTO)> | 索引检索实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取索引检索
#### 访问路径
/ibizproindices/{ibizproindex_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindex_id | Long | 索引检索主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproIndexDTO](#IbizproIndexDTO)：索引检索实体传输对象 |

### 删除索引检索
#### 访问路径
/ibizproindices/{ibizproindex_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindex_id | Long | 索引检索主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除索引检索
#### 访问路径
/ibizproindices/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 索引检索主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新索引检索
#### 访问路径
/ibizproindices/{ibizproindex_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindex_id | Long | 索引检索主键ID |
| 2 | ibizproindexdto | [IbizproIndexDTO](#IbizproIndexDTO) | 索引检索实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproIndexDTO](#IbizproIndexDTO)：索引检索实体传输对象 |

### 批量更新索引检索
#### 访问路径
/ibizproindices/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindexdtos | List<[IbizproIndexDTO](#IbizproIndexDTO)> | 索引检索实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查索引检索
#### 访问路径
/ibizproindices/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindexdto | [IbizproIndexDTO](#IbizproIndexDTO) | 索引检索实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存索引检索
#### 访问路径
/ibizproindices/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindexdto | [IbizproIndexDTO](#IbizproIndexDTO) | 索引检索实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存索引检索
#### 访问路径
/ibizproindices/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindexdtos | List<[IbizproIndexDTO](#IbizproIndexDTO)> | 索引检索实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibizproindices/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproindices/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 全文搜索-查询{deds.getLogicName()}
#### 访问路径
/ibizproindices/fetchdefault/es

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象列表 |

### 获取全文检索
#### 访问路径
/ibizproindices/fetchesquery

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象列表 |

### 查询全文检索
#### 访问路径
/ibizproindices/searchesquery

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 全文搜索-查询{deds.getLogicName()}
#### 访问路径
/ibizproindices/fetchesquery/es

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象列表 |

### 获取数据集2
#### 访问路径
/ibizproindices/fetchindexder

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象列表 |

### 查询数据集2
#### 访问路径
/ibizproindices/searchindexder

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 全文搜索-查询{deds.getLogicName()}
#### 访问路径
/ibizproindices/fetchindexder/es

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproIndexSearchContext](#IbizproIndexSearchContext) | 索引检索查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproIndexDTO](#IbizproIndexDTO)>：索引检索实体传输对象列表 |

### 全文检索-获取索引检索
#### 访问路径
/ibizproindices/{ibizproindex_id}/es

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproindex_id | Long | 索引检索主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproIndexDTO](#IbizproIndexDTO)：索引检索实体传输对象 |

## 附录
### 数据类型说明
#### IbizproIndexDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | indexid | Long | 允许 | 主键 |
| 2 | acl | String | 允许 | 权限 |
| 3 | docid | String | 允许 | docid |
| 4 | indexname | String | 允许 | 标题[需求、任务等] |
| 5 | color | String | 允许 | 颜色 |
| 6 | deleted | String | 允许 | 逻辑标识 |
| 7 | acllist | String | 允许 | 权限列表 |
| 8 | indexdesc | String | 允许 | 内容[需求、任务等] |
| 9 | product | Long | 允许 | 产品 |
| 10 | indextype | String | 允许 | 类型 |
| 11 | mdeptid | String | 允许 | 部门标识 |
| 12 | project | Long | 允许 | 项目 |
| 13 | orgid | String | 允许 | 组织标识 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproIndexSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_indexid_eq | Long | 允许 | 条件字段：indexid<br>条件组合方式：`=` |
| 2 | n_indexname_like | String | 允许 | 条件字段：indexname<br>条件组合方式：`%like%` |
| 3 | n_index_type_eq | String | 允许 | 条件字段：index_type<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
