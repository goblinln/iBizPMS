# 服务接口-IBIZPRO_PROJECTMONTHLY
## 接口说明
项目月报

## 接口清单
### 新建项目月报
#### 访问路径
/ibizproprojectmonthlies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlydto | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO) | 项目月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)：项目月报实体传输对象 |

### 批量新建项目月报
#### 访问路径
/ibizproprojectmonthlies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlydtos | List<[IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)> | 项目月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目月报
#### 访问路径
/ibizproprojectmonthlies/{ibizproprojectmonthly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthly_id | String | 项目月报主键ID |
| 2 | ibizproprojectmonthlydto | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO) | 项目月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)：项目月报实体传输对象 |

### 批量更新项目月报
#### 访问路径
/ibizproprojectmonthlies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlydtos | List<[IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)> | 项目月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目月报
#### 访问路径
/ibizproprojectmonthlies/{ibizproprojectmonthly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthly_id | String | 项目月报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目月报
#### 访问路径
/ibizproprojectmonthlies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 项目月报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目月报
#### 访问路径
/ibizproprojectmonthlies/{ibizproprojectmonthly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthly_id | String | 项目月报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)：项目月报实体传输对象 |

### 检查项目月报
#### 访问路径
/ibizproprojectmonthlies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlydto | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO) | 项目月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 手动生成项目月报
#### 访问路径
/ibizproprojectmonthlies/{ibizproprojectmonthly_id}/manualcreatemonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthly_id | String | 项目月报主键ID |
| 2 | ibizproprojectmonthlydto | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO) | 项目月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)：项目月报实体传输对象 |

### 保存项目月报
#### 访问路径
/ibizproprojectmonthlies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlydto | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO) | 项目月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目月报
#### 访问路径
/ibizproprojectmonthlies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlydtos | List<[IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)> | 项目月报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 汇总项目月报
#### 访问路径
/ibizproprojectmonthlies/{ibizproprojectmonthly_id}/sumprojectmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthly_id | String | 项目月报主键ID |
| 2 | ibizproprojectmonthlydto | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO) | 项目月报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)：项目月报实体传输对象 |

### 获取数据集
#### 访问路径
/ibizproprojectmonthlies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProjectMonthlySearchContext](#IbizproProjectMonthlySearchContext) | 项目月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)>：项目月报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproprojectmonthlies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProjectMonthlySearchContext](#IbizproProjectMonthlySearchContext) | 项目月报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproProjectMonthlyDTO](#IbizproProjectMonthlyDTO)>：项目月报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbizproProjectMonthlyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ibizproprojectmonthlyname | String | 允许 | 项目月报名称 |
| 2 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 3 | yearmonth | String | 允许 | 年月 |
| 4 | totalestimates | Double | 允许 | 总工时 |
| 5 | updateman | String | 不可 | 更新人 |
| 6 | tasks | String | 允许 | 任务 |
| 7 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 8 | ibizproprojectmonthlyid | String | 不可 | 项目月报标识 |
| 9 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | createman | String | 不可 | 建立人 |
| 11 | pm | String | 允许 | 项目负责人 |
| 12 | projectname | String | 允许 | 项目名称 |
| 13 | project | Long | 允许 | 项目编号 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproProjectMonthlySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibizpro_projectmonthlyname_like | String | 允许 | 条件字段：ibizpro_projectmonthlyname<br>条件组合方式：`%like%` |
| 2 | n_ibizpro_projectmonthlyid_eq | String | 允许 | 条件字段：ibizpro_projectmonthlyid<br>条件组合方式：`=` |
| 3 | n_pm_eq | String | 允许 | 条件字段：pm<br>条件组合方式：`=` |
| 4 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 5 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 6 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
