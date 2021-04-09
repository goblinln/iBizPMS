# 服务接口-IBIZPRO_PROJECTWEEKLY
## 接口说明
项目周报

## 接口清单
### 新建项目周报
#### 访问路径
/ibizproprojectweeklies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweeklydto | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO) | 项目周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)：项目周报实体传输对象 |

### 批量新建项目周报
#### 访问路径
/ibizproprojectweeklies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweeklydtos | List<[IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)> | 项目周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目周报
#### 访问路径
/ibizproprojectweeklies/{ibizproprojectweekly_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweekly_id | String | 项目周报主键ID |
| 2 | ibizproprojectweeklydto | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO) | 项目周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)：项目周报实体传输对象 |

### 批量更新项目周报
#### 访问路径
/ibizproprojectweeklies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweeklydtos | List<[IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)> | 项目周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目周报
#### 访问路径
/ibizproprojectweeklies/{ibizproprojectweekly_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweekly_id | String | 项目周报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目周报
#### 访问路径
/ibizproprojectweeklies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 项目周报主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目周报
#### 访问路径
/ibizproprojectweeklies/{ibizproprojectweekly_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweekly_id | String | 项目周报主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)：项目周报实体传输对象 |

### 检查项目周报
#### 访问路径
/ibizproprojectweeklies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweeklydto | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO) | 项目周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 定时推送项目周报
#### 访问路径
/ibizproprojectweeklies/{ibizproprojectweekly_id}/pushsumprojectweekly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweekly_id | String | 项目周报主键ID |
| 2 | ibizproprojectweeklydto | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO) | 项目周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)：项目周报实体传输对象 |

### 保存项目周报
#### 访问路径
/ibizproprojectweeklies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweeklydto | [IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO) | 项目周报实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目周报
#### 访问路径
/ibizproprojectweeklies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproprojectweeklydtos | List<[IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)> | 项目周报实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibizproprojectweeklies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProjectWeeklySearchContext](#IbizproProjectWeeklySearchContext) | 项目周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)>：项目周报实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproprojectweeklies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbizproProjectWeeklySearchContext](#IbizproProjectWeeklySearchContext) | 项目周报查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbizproProjectWeeklyDTO](#IbizproProjectWeeklyDTO)>：项目周报实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbizproProjectWeeklyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 2 | updateman | String | 不可 | 更新人 |
| 3 | year | String | 允许 | 年 |
| 4 | enddatestats | String | 允许 | 结束统计 |
| 5 | createman | String | 不可 | 建立人 |
| 6 | tasks | String | 允许 | 任务 |
| 7 | projectweeklyname | String | 允许 | 项目周报名称 |
| 8 | week | String | 允许 | 周 |
| 9 | projectweeklyid | String | 不可 | 项目周报标识 |
| 10 | pm | String | 允许 | 项目负责人 |
| 11 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 12 | totalestimates | Double | 允许 | 总工时 |
| 13 | begindatestats | String | 允许 | 开始统计 |
| 14 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 15 | month | String | 允许 | 月 |
| 16 | projectname | String | 允许 | 项目名称 |
| 17 | project | Long | 允许 | 项目编号 |
| 18 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbizproProjectWeeklySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibzpro_projectweeklyname_like | String | 允许 | 条件字段：ibzpro_projectweeklyname<br>条件组合方式：`%like%` |
| 2 | n_pm_eq | String | 允许 | 条件字段：pm<br>条件组合方式：`=` |
| 3 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
