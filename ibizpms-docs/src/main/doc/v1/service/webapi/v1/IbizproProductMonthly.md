# 服务接口-IBIZPRO_PRODUCTMONTHLY
## 接口说明
产品月报

## 接口清单
### 新建产品月报
#### 访问路径
/ibizproproductmonthlies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthlydto | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO) | 产品月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)：产品月报实体传输对象 |

### 批量新建产品月报
#### 访问路径
/ibizproproductmonthlies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthlydtos | List<[IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)> | 产品月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新产品月报
#### 访问路径
/ibizproproductmonthlies/{ibizproproductmonthly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthly_id | Long | 产品月报主键ID |
| 2 | ibizproproductmonthlydto | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO) | 产品月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)：产品月报实体传输对象 |

### 批量更新产品月报
#### 访问路径
/ibizproproductmonthlies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthlydtos | List<[IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)> | 产品月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除产品月报
#### 访问路径
/ibizproproductmonthlies/{ibizproproductmonthly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthly_id | Long | 产品月报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除产品月报
#### 访问路径
/ibizproproductmonthlies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 产品月报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取产品月报
#### 访问路径
/ibizproproductmonthlies/{ibizproproductmonthly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthly_id | Long | 产品月报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)：产品月报实体传输对象 |

### 检查产品月报
#### 访问路径
/ibizproproductmonthlies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthlydto | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO) | 产品月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 手动生成产品月报
#### 访问路径
/ibizproproductmonthlies/{ibizproproductmonthly_id}/manualcreatemonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthly_id | Long | 产品月报主键ID |
| 2 | ibizproproductmonthlydto | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO) | 产品月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)：产品月报实体传输对象 |

### 保存产品月报
#### 访问路径
/ibizproproductmonthlies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthlydto | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO) | 产品月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存产品月报
#### 访问路径
/ibizproproductmonthlies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthlydtos | List<[IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)> | 产品月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 汇总产品月报
#### 访问路径
/ibizproproductmonthlies/{ibizproproductmonthly_id}/statsproductmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproproductmonthly_id | Long | 产品月报主键ID |
| 2 | ibizproproductmonthlydto | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO) | 产品月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)：产品月报实体传输对象 |

### 获取数据集
#### 访问路径
/ibizproproductmonthlies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProductMonthlySearchContext](#IbizproProductMonthlySearchContext) | 产品月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)>：产品月报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproproductmonthlies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProductMonthlySearchContext](#IbizproProductMonthlySearchContext) | 产品月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproProductMonthlyDTO](#IbizproProductMonthlyDTO)>：产品月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbizproProductMonthlyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | tasks | String | 允许 | 任务 |
| 2 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 3 | updateman | String | 不可 | 更新人 |
| 4 | totalestimates | Double | 允许 | 总工时 |
| 5 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | yearmonth | String | 允许 | 年月 |
| 8 | ibizproproductmonthlyid | Long | 不可 | 产品月报标识 |
| 9 | ibizproproductmonthlyname | String | 允许 | 产品月报名称 |
| 10 | createman | String | 不可 | 建立人 |
| 11 | po | String | 允许 | 产品负责人 |
| 12 | productname | String | 允许 | 产品名称 |
| 13 | product | Long | 允许 | 产品编号 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproProductMonthlySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibizpro_productmonthlyid_eq | Long | 允许 | 条件字段：ibizpro_productmonthlyid<br>条件组合方式：`=` |
| 2 | n_ibizpro_productmonthlyname_like | String | 允许 | 条件字段：ibizpro_productmonthlyname<br>条件组合方式：`%like%` |
| 3 | n_po_eq | String | 允许 | 条件字段：po<br>条件组合方式：`=` |
| 4 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 5 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 6 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
