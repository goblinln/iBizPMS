# 服务接口-IBIZPRO_PROJECTDAILY
## 接口说明
项目日报

## 接口清单
### 新建项目日报
#### 访问路径
/ibizproprojectdailies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailydto | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO) | 项目日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO)：项目日报实体传输对象 |

### 批量新建项目日报
#### 访问路径
/ibizproprojectdailies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailydtos | List<[IbizproProjectDailyDTO](#IbizproProjectDailyDTO)> | 项目日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目日报
#### 访问路径
/ibizproprojectdailies/{ibizproprojectdaily_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdaily_id | String | 项目日报主键ID |
| 2 | ibizproprojectdailydto | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO) | 项目日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO)：项目日报实体传输对象 |

### 批量更新项目日报
#### 访问路径
/ibizproprojectdailies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailydtos | List<[IbizproProjectDailyDTO](#IbizproProjectDailyDTO)> | 项目日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目日报
#### 访问路径
/ibizproprojectdailies/{ibizproprojectdaily_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdaily_id | String | 项目日报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目日报
#### 访问路径
/ibizproprojectdailies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 项目日报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目日报
#### 访问路径
/ibizproprojectdailies/{ibizproprojectdaily_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdaily_id | String | 项目日报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO)：项目日报实体传输对象 |

### 检查项目日报
#### 访问路径
/ibizproprojectdailies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailydto | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO) | 项目日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存项目日报
#### 访问路径
/ibizproprojectdailies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailydto | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO) | 项目日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目日报
#### 访问路径
/ibizproprojectdailies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailydtos | List<[IbizproProjectDailyDTO](#IbizproProjectDailyDTO)> | 项目日报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 汇总项目日报
#### 访问路径
/ibizproprojectdailies/{ibizproprojectdaily_id}/sumprojectdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdaily_id | String | 项目日报主键ID |
| 2 | ibizproprojectdailydto | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO) | 项目日报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectDailyDTO](#IbizproProjectDailyDTO)：项目日报实体传输对象 |

### 获取数据集
#### 访问路径
/ibizproprojectdailies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProjectDailySearchContext](#IbizproProjectDailySearchContext) | 项目日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproProjectDailyDTO](#IbizproProjectDailyDTO)>：项目日报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproprojectdailies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProjectDailySearchContext](#IbizproProjectDailySearchContext) | 项目日报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproProjectDailyDTO](#IbizproProjectDailyDTO)>：项目日报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbizproProjectDailyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ibizproprojectdailyname | String | 允许 | 项目日报名称 |
| 2 | tasks | String | 允许 | 任务 |
| 3 | begin | Timestamp | 允许 | 开始日期<br>时间格式：yyyy-MM-dd |
| 4 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | end | Timestamp | 允许 | 结束日期<br>时间格式：yyyy-MM-dd |
| 6 | createman | String | 不可 | 建立人 |
| 7 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 8 | totalestimates | Double | 允许 | 总工时 |
| 9 | updateman | String | 不可 | 更新人 |
| 10 | pm | String | 允许 | 项目负责人 |
| 11 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 12 | ibizproprojectdailyid | String | 不可 | 项目日报标识 |
| 13 | projectname | String | 允许 | 项目名称 |
| 14 | project | Long | 允许 | 项目编号 |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproProjectDailySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibizpro_projectdailyname_like | String | 允许 | 条件字段：ibizpro_projectdailyname<br>条件组合方式：`%like%` |
| 2 | n_pm_eq | String | 允许 | 条件字段：pm<br>条件组合方式：`=` |
| 3 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 4 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 5 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
