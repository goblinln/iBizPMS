# 服务接口-PRoductWeekly
## 接口说明
产品周报

## 接口清单
### 新建产品周报
#### 访问路径
/ibizproproductweeklies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweeklydto | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO) | 产品周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)：产品周报实体传输对象 |

### 批量新建产品周报
#### 访问路径
/ibizproproductweeklies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweeklydtos | List<[IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)> | 产品周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新产品周报
#### 访问路径
/ibizproproductweeklies/{ibizproproductweekly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweekly_id | Long | 产品周报主键ID |
| 2 | ibizproproductweeklydto | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO) | 产品周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)：产品周报实体传输对象 |

### 批量更新产品周报
#### 访问路径
/ibizproproductweeklies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweeklydtos | List<[IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)> | 产品周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除产品周报
#### 访问路径
/ibizproproductweeklies/{ibizproproductweekly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweekly_id | Long | 产品周报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除产品周报
#### 访问路径
/ibizproproductweeklies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 产品周报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取产品周报
#### 访问路径
/ibizproproductweeklies/{ibizproproductweekly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweekly_id | Long | 产品周报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)：产品周报实体传输对象 |

### 检查产品周报
#### 访问路径
/ibizproproductweeklies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweeklydto | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO) | 产品周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存产品周报
#### 访问路径
/ibizproproductweeklies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweeklydto | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO) | 产品周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存产品周报
#### 访问路径
/ibizproproductweeklies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweeklydtos | List<[IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)> | 产品周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 统计产品周报
#### 访问路径
/ibizproproductweeklies/{ibizproproductweekly_id}/sumproductweekly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductweekly_id | Long | 产品周报主键ID |
| 2 | ibizproproductweeklydto | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO) | 产品周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)：产品周报实体传输对象 |

### 获取数据集
#### 访问路径
/ibizproproductweeklies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProductWeeklySearchContext](#IbizproProductWeeklySearchContext) | 产品周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)>：产品周报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproproductweeklies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProductWeeklySearchContext](#IbizproProductWeeklySearchContext) | 产品周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproProductWeeklyDTO](#IbizproProductWeeklyDTO)>：产品周报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbizproProductWeeklyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | po | String | 允许 | 产品负责人 |
| 2 | ibizpro_productweeklyid | Long | 不可 | 产品周报标识 |
| 3 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 4 | tasks | String | 允许 | 任务 |
| 5 | updateman | String | 不可 | 更新人 |
| 6 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 7 | createman | String | 不可 | 建立人 |
| 8 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 9 | ibizproproductweeklyname | String | 允许 | 产品周报名称 |
| 10 | begindatestats | String | 允许 | 开始统计 |
| 11 | totalestimates | Double | 允许 | 总工时 |
| 12 | enddatestats | String | 允许 | 结束统计 |
| 13 | productname | String | 允许 | 产品名称 |
| 14 | product | Long | 允许 | 编号 |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproProductWeeklySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_po_eq | String | 允许 | 条件字段：po<br>条件组合方式：`=` |
| 2 | n_ibizpro_productdailyname_like | String | 允许 | 条件字段：ibizpro_productdailyname<br>条件组合方式：`%like%` |
| 3 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 4 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 5 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
