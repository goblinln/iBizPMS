# 服务接口-IBZ_COMPANYSTATS
## 接口说明
公司动态汇总

## 接口清单
### 新建公司动态汇总
#### 访问路径
/companystats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystatsdto | [CompanyStatsDTO](#CompanyStatsDTO) | 公司动态汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CompanyStatsDTO](#CompanyStatsDTO)：公司动态汇总实体传输对象 |

### 批量新建公司动态汇总
#### 访问路径
/companystats/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystatsdtos | List<[CompanyStatsDTO](#CompanyStatsDTO)> | 公司动态汇总实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取公司动态汇总
#### 访问路径
/companystats/{companystats_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystats_id | Long | 公司动态汇总主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CompanyStatsDTO](#CompanyStatsDTO)：公司动态汇总实体传输对象 |

### 删除公司动态汇总
#### 访问路径
/companystats/{companystats_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystats_id | Long | 公司动态汇总主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除公司动态汇总
#### 访问路径
/companystats/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 公司动态汇总主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新公司动态汇总
#### 访问路径
/companystats/{companystats_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystats_id | Long | 公司动态汇总主键ID |
| 2 | companystatsdto | [CompanyStatsDTO](#CompanyStatsDTO) | 公司动态汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CompanyStatsDTO](#CompanyStatsDTO)：公司动态汇总实体传输对象 |

### 批量更新公司动态汇总
#### 访问路径
/companystats/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystatsdtos | List<[CompanyStatsDTO](#CompanyStatsDTO)> | 公司动态汇总实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查公司动态汇总
#### 访问路径
/companystats/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystatsdto | [CompanyStatsDTO](#CompanyStatsDTO) | 公司动态汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存公司动态汇总
#### 访问路径
/companystats/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystatsdto | [CompanyStatsDTO](#CompanyStatsDTO) | 公司动态汇总实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存公司动态汇总
#### 访问路径
/companystats/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companystatsdtos | List<[CompanyStatsDTO](#CompanyStatsDTO)> | 公司动态汇总实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取公司动态统计
#### 访问路径
/companystats/fetchcompanydynamicstats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CompanyStatsSearchContext](#CompanyStatsSearchContext) | 公司动态汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CompanyStatsDTO](#CompanyStatsDTO)>：公司动态汇总实体传输对象列表 |

### 查询公司动态统计
#### 访问路径
/companystats/searchcompanydynamicstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CompanyStatsSearchContext](#CompanyStatsSearchContext) | 公司动态汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CompanyStatsDTO](#CompanyStatsDTO)>：公司动态汇总实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/companystats/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CompanyStatsSearchContext](#CompanyStatsSearchContext) | 公司动态汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CompanyStatsDTO](#CompanyStatsDTO)>：公司动态汇总实体传输对象列表 |

### 查询数据集
#### 访问路径
/companystats/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CompanyStatsSearchContext](#CompanyStatsSearchContext) | 公司动态汇总查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CompanyStatsDTO](#CompanyStatsDTO)>：公司动态汇总实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### CompanyStatsDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | logincnt | Integer | 允许 | 登录次数 |
| 2 | openedstorycnt | Integer | 允许 | 新增需求数 |
| 3 | ztdate | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 4 | date | Timestamp | 允许 | 日志日期<br>时间格式：yyyy-MM-dd |
| 5 | closedstorycnt | Integer | 允许 | 关闭需求数 |
| 6 | openedbugcnt | Integer | 允许 | 新增Bug数 |
| 7 | loghours | Integer | 允许 | 日志工时 |
| 8 | dynamiccnt | Integer | 允许 | 动态数 |
| 9 | finishedtaskcnt | Integer | 允许 | 完成任务数 |
| 10 | resolvedbugcnt | Integer | 允许 | 解决Bug数 |
| 11 | comment | String | 允许 | 备注 |
| 12 | openedtaskcnt | Integer | 允许 | 新增任务数 |
| 13 | id | Long | 允许 | 标识 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### CompanyStatsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_date_gtandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 2 | n_date_ltandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 3 | n_comment_like | String | 允许 | 条件字段：comment<br>条件组合方式：`%like%` |
| 4 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 5 | customcond | String | 允许 | 自定义查询条件 |
| 6 | customparams | String | 允许 | 自定义查询参数 |
| 7 | query | String | 允许 | 快速搜索 |
| 8 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 9 | page | int | 允许 | 当前页数<br>默认值0 |
| 10 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 11 | sort | String | 允许 | 排序 |
