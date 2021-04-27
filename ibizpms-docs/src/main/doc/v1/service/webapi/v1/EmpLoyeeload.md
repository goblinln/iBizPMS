# 服务接口-员工负载表
## 接口说明
员工负载表

## 接口清单
### 新建员工负载表
#### 访问路径
/employeeloads

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeloaddto | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO) | 员工负载表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO)：员工负载表实体传输对象 |

### 批量新建员工负载表
#### 访问路径
/employeeloads/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeloaddtos | List<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)> | 员工负载表实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新员工负载表
#### 访问路径
/employeeloads/{employeeload_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeload_id | Long | 员工负载表主键ID |
| 2 | employeeloaddto | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO) | 员工负载表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO)：员工负载表实体传输对象 |

### 批量更新员工负载表
#### 访问路径
/employeeloads/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeloaddtos | List<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)> | 员工负载表实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除员工负载表
#### 访问路径
/employeeloads/{employeeload_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeload_id | Long | 员工负载表主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除员工负载表
#### 访问路径
/employeeloads/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 员工负载表主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取员工负载表
#### 访问路径
/employeeloads/{employeeload_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeload_id | Long | 员工负载表主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO)：员工负载表实体传输对象 |

### 检查员工负载表
#### 访问路径
/employeeloads/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeloaddto | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO) | 员工负载表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存员工负载表
#### 访问路径
/employeeloads/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeloaddto | [EmpLoyeeloadDTO](#EmpLoyeeloadDTO) | 员工负载表实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存员工负载表
#### 访问路径
/employeeloads/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | employeeloaddtos | List<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)> | 员工负载表实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/employeeloads/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [EmpLoyeeloadSearchContext](#EmpLoyeeloadSearchContext) | 员工负载表查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)>：员工负载表实体传输对象列表 |

### 查询数据集
#### 访问路径
/employeeloads/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [EmpLoyeeloadSearchContext](#EmpLoyeeloadSearchContext) | 员工负载表查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)>：员工负载表实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取员工负载表
#### 访问路径
/employeeloads/fetchgetwoerkload

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [EmpLoyeeloadSearchContext](#EmpLoyeeloadSearchContext) | 员工负载表查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)>：员工负载表实体传输对象列表 |

### 查询获取员工负载表
#### 访问路径
/employeeloads/searchgetwoerkload

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [EmpLoyeeloadSearchContext](#EmpLoyeeloadSearchContext) | 员工负载表查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[EmpLoyeeloadDTO](#EmpLoyeeloadDTO)>：员工负载表实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### EmpLoyeeloadDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | name | String | 允许 | 任务名 |
| 2 | taskcnt | Integer | 允许 | 任务数 |
| 3 | dept | String | 允许 | 部门 |
| 4 | id | Long | 允许 | 主键 |
| 5 | workday | Integer | 允许 | 工作日天数 |
| 6 | totaltaskcnt | Integer | 允许 | 总任务数 |
| 7 | assignedto | String | 允许 | 指派给 |
| 8 | left | Integer | 允许 | 剩余工时 |
| 9 | assign | String | 允许 | 是否指派 |
| 10 | begin | Timestamp | 允许 | 属性<br>时间格式：yyyy-MM-dd |
| 11 | totalleft | Integer | 允许 | 总工时 |
| 12 | workload | String | 允许 | 工作负载 |
| 13 | end | Timestamp | 允许 | 结束<br>时间格式：yyyy-MM-dd |
| 14 | projectname | String | 允许 | 项目 |
| 15 | project | Long | 允许 | 项目编号 |
| 16 | <动态属性> | Object | 允许 | 支持动态属性 |

#### EmpLoyeeloadSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 2 | n_dept_eq | String | 允许 | 条件字段：dept<br>条件组合方式：`=` |
| 3 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | n_workday_eq | Integer | 允许 | 条件字段：workday<br>条件组合方式：`=` |
| 5 | n_assign_eq | String | 允许 | 条件字段：assign<br>条件组合方式：`=` |
| 6 | n_begin_ltandeq | Timestamp | 允许 | 条件字段：begin<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 7 | n_end_gtandeq | Timestamp | 允许 | 条件字段：end<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 8 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 9 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 10 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 11 | customcond | String | 允许 | 自定义查询条件 |
| 12 | customparams | String | 允许 | 自定义查询参数 |
| 13 | query | String | 允许 | 快速搜索 |
| 14 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 15 | page | int | 允许 | 当前页数<br>默认值0 |
| 16 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 17 | sort | String | 允许 | 排序 |
