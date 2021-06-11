# 服务接口-IBZ_CASESTATS
## 接口说明
测试用例统计

## 接口清单
### 新建测试用例统计
#### 访问路径
/casestats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestatsdto | [CaseStatsDTO](#CaseStatsDTO) | 测试用例统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStatsDTO](#CaseStatsDTO)：测试用例统计实体传输对象 |

### 批量新建测试用例统计
#### 访问路径
/casestats/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestatsdtos | List<[CaseStatsDTO](#CaseStatsDTO)> | 测试用例统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试用例统计
#### 访问路径
/casestats/{casestats_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestats_id | Long | 测试用例统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStatsDTO](#CaseStatsDTO)：测试用例统计实体传输对象 |

### 删除测试用例统计
#### 访问路径
/casestats/{casestats_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestats_id | Long | 测试用例统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试用例统计
#### 访问路径
/casestats/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试用例统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试用例统计
#### 访问路径
/casestats/{casestats_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestats_id | Long | 测试用例统计主键ID |
| 2 | casestatsdto | [CaseStatsDTO](#CaseStatsDTO) | 测试用例统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStatsDTO](#CaseStatsDTO)：测试用例统计实体传输对象 |

### 批量更新测试用例统计
#### 访问路径
/casestats/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestatsdtos | List<[CaseStatsDTO](#CaseStatsDTO)> | 测试用例统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查测试用例统计
#### 访问路径
/casestats/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestatsdto | [CaseStatsDTO](#CaseStatsDTO) | 测试用例统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存测试用例统计
#### 访问路径
/casestats/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestatsdto | [CaseStatsDTO](#CaseStatsDTO) | 测试用例统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试用例统计
#### 访问路径
/casestats/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestatsdtos | List<[CaseStatsDTO](#CaseStatsDTO)> | 测试用例统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/casestats/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStatsSearchContext](#CaseStatsSearchContext) | 测试用例统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStatsDTO](#CaseStatsDTO)>：测试用例统计实体传输对象列表 |

### 查询数据集
#### 访问路径
/casestats/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStatsSearchContext](#CaseStatsSearchContext) | 测试用例统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStatsDTO](#CaseStatsDTO)>：测试用例统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试用例统计
#### 访问路径
/casestats/fetchtestcasestats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStatsSearchContext](#CaseStatsSearchContext) | 测试用例统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStatsDTO](#CaseStatsDTO)>：测试用例统计实体传输对象列表 |

### 查询测试用例统计
#### 访问路径
/casestats/searchtestcasestats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStatsSearchContext](#CaseStatsSearchContext) | 测试用例统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStatsDTO](#CaseStatsDTO)>：测试用例统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### CaseStatsDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | passcase | Integer | 允许 | 通过用例数 |
| 2 | blockedcase | Integer | 允许 | 阻塞用例数 |
| 3 | totalruncase | Integer | 允许 | 总执行数 |
| 4 | failcase | Integer | 允许 | 失败用例数 |
| 5 | title | String | 允许 | 用例标题 |
| 6 | totalcase | Integer | 允许 | 总用例数 |
| 7 | passrate | String | 允许 | 用例通过率 |
| 8 | id | Long | 允许 | 用例编号 |
| 9 | modulename | String | 允许 | 模块名称 |
| 10 | product | Long | 允许 | 产品 |
| 11 | module | Long | 允许 | 模块 |
| 12 | <动态属性> | Object | 允许 | 支持动态属性 |

#### CaseStatsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 4 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 5 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 6 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
