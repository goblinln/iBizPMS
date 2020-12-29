# 服务接口-IBIZPRO_PRODUCTDAILY
## 接口说明
产品日报

## 接口清单
### 新建产品日报
#### 访问路径
/ibizproproductdailies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailydto | [IbizproProductDailyDTO](#IbizproProductDailyDTO) | 产品日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductDailyDTO](#IbizproProductDailyDTO)：产品日报实体传输对象 |

### 批量新建产品日报
#### 访问路径
/ibizproproductdailies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailydtos | List<[IbizproProductDailyDTO](#IbizproProductDailyDTO)> | 产品日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新产品日报
#### 访问路径
/ibizproproductdailies/{ibizproproductdaily_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdaily_id | Long | 产品日报主键ID |
| 2 | ibizproproductdailydto | [IbizproProductDailyDTO](#IbizproProductDailyDTO) | 产品日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductDailyDTO](#IbizproProductDailyDTO)：产品日报实体传输对象 |

### 批量更新产品日报
#### 访问路径
/ibizproproductdailies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailydtos | List<[IbizproProductDailyDTO](#IbizproProductDailyDTO)> | 产品日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除产品日报
#### 访问路径
/ibizproproductdailies/{ibizproproductdaily_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdaily_id | Long | 产品日报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除产品日报
#### 访问路径
/ibizproproductdailies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 产品日报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取产品日报
#### 访问路径
/ibizproproductdailies/{ibizproproductdaily_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdaily_id | Long | 产品日报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductDailyDTO](#IbizproProductDailyDTO)：产品日报实体传输对象 |

### 检查产品日报
#### 访问路径
/ibizproproductdailies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailydto | [IbizproProductDailyDTO](#IbizproProductDailyDTO) | 产品日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存产品日报
#### 访问路径
/ibizproproductdailies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailydto | [IbizproProductDailyDTO](#IbizproProductDailyDTO) | 产品日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存产品日报
#### 访问路径
/ibizproproductdailies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailydtos | List<[IbizproProductDailyDTO](#IbizproProductDailyDTO)> | 产品日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibizproproductdailies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProductDailySearchContext](#IbizproProductDailySearchContext) | 产品日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproProductDailyDTO](#IbizproProductDailyDTO)>：产品日报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproproductdailies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProductDailySearchContext](#IbizproProductDailySearchContext) | 产品日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproProductDailyDTO](#IbizproProductDailyDTO)>：产品日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbizproProductDailyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ibizproproductdailyname | String | 允许 | 产品日报名称 |
| 2 | ibizproproductdailyid | Long | 不可 | 产品日报标识 |
| 3 | createman | String | 不可 | 建立人 |
| 4 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | updateman | String | 不可 | 更新人 |
| 7 | po | String | 允许 | 产品负责人 |
| 8 | product | Long | 允许 | 产品 |
| 9 | productname | String | 允许 | 产品名称 |
| 10 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 11 | tasks | String | 允许 | 任务 |
| 12 | totalestimates | Double | 允许 | 总工时 |
| 13 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproProductDailySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibizpro_productdailyname_like | String | 允许 | 条件字段：ibizpro_productdailyname<br>条件组合方式：`%like%` |
| 2 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 3 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 4 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 5 | customcond | String | 允许 | 自定义查询条件 |
| 6 | customparams | String | 允许 | 自定义查询参数 |
| 7 | query | String | 允许 | 快速搜索 |
| 8 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 9 | page | int | 允许 | 当前页数<br>默认值0 |
| 10 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 11 | sort | String | 允许 | 排序 |
