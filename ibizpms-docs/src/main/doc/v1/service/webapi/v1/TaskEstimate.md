# 服务接口-ZT_TASKESTIMATE
## 接口说明
任务预计

## 接口清单
### 新建任务预计
#### 访问路径
/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 批量新建任务预计
#### 访问路径
/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取任务预计
#### 访问路径
/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 删除任务预计
#### 访问路径
/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除任务预计
#### 访问路径
/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新任务预计
#### 访问路径
/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimate_id | Long | 任务预计主键ID |
| 2 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 批量更新任务预计
#### 访问路径
/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查任务预计
#### 访问路径
/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimate_id | Long | 任务预计主键ID |
| 2 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 保存任务预计
#### 访问路径
/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存任务预计
#### 访问路径
/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取日志日
#### 访问路径
/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询日志日
#### 访问路径
/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取日志日（项目）
#### 访问路径
/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询日志日（项目）
#### 访问路径
/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取日志月
#### 访问路径
/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询日志月
#### 访问路径
/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取日志年
#### 访问路径
/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询日志年
#### 访问路径
/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所有人员
#### 访问路径
/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询所有人员
#### 访问路径
/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所有项目
#### 访问路径
/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询所有项目
#### 访问路径
/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT1
#### 访问路径
/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询DEFAULT1
#### 访问路径
/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我管辖的所有人员
#### 访问路径
/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询我管辖的所有人员
#### 访问路径
/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取日志月（项目）
#### 访问路径
/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询日志月（项目）
#### 访问路径
/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取日志年（项目）
#### 访问路径
/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询日志年（项目）
#### 访问路径
/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目日志
#### 访问路径
/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询项目日志
#### 访问路径
/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取人员参与的所有项目
#### 访问路径
/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 查询人员参与的所有项目
#### 访问路径
/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimate_id | Long | 任务预计主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimate_id | Long | 任务预计主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimate_id | Long | 任务预计主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |
| 5 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |
| 5 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |
| 5 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |
| 5 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量建立任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据删除任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务预计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |
| 5 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据批量更新任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目经理评估
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimate_id | Long | 任务预计主键ID |
| 5 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskEstimateDTO](#TaskEstimateDTO)：任务预计实体传输对象 |

### 根据保存任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedto | [TaskEstimateDTO](#TaskEstimateDTO) | 任务预计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务预计
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskestimatedtos | List<[TaskEstimateDTO](#TaskEstimateDTO)> | 任务预计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取日志日
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactiondate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactiondate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志日（项目）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactiondatebyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志日（项目）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactiondatebyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有人员
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchallaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有人员
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchallaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所有项目
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchallprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询所有项目
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchallprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefaults

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefaults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我管辖的所有人员
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchmyaccounts

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询我管辖的所有人员
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchmyaccounts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志月（项目）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志月（项目）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取日志年（项目）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询日志年（项目）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionyear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目日志
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询项目日志
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员参与的所有项目
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchsomeonejoinallpros

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象列表 |

### 根据查询人员参与的所有项目
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchsomeonejoinallpros

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskEstimateSearchContext](#TaskEstimateSearchContext) | 任务预计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskEstimateDTO](#TaskEstimateDTO)>：任务预计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TaskEstimateDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | monthname | String | 允许 | 月（显示） |
| 2 | year | String | 允许 | 年 |
| 3 | account | String | 允许 | 用户 |
| 4 | left | Double | 允许 | 预计剩余 |
| 5 | consumed | Double | 允许 | 总计消耗 |
| 6 | evaluationcost | Double | 允许 | 评估成本 |
| 7 | monthorder | Integer | 允许 | 月（排序） |
| 8 | files | String | 允许 | 附件 |
| 9 | id | Long | 不可 | 编号 |
| 10 | evaluationstatus | String | 允许 | 评估状态 |
| 11 | yearname | String | 允许 | 年（显示） |
| 12 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 13 | evaluationtime | Double | 允许 | 评估工时 |
| 14 | inputcost | Double | 允许 | 投入成本 |
| 15 | dates | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 16 | month | String | 允许 | 月 |
| 17 | work | String | 允许 | work |
| 18 | evaluationdesc | String | 允许 | 评估说明 |
| 19 | taskspecies | String | 允许 | 任务种别 |
| 20 | taskname | String | 允许 | 任务名称 |
| 21 | projectname | String | 允许 | 所属项目 |
| 22 | type | String | 允许 | 任务类型 |
| 23 | deleted | String | 允许 | 任务删除标识 |
| 24 | project | Long | 允许 | 项目 |
| 25 | task | Long | 允许 | 任务 |
| 26 | day | String | 允许 | 天 |
| 27 | dayname | String | 允许 | 天（显示） |
| 28 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TaskEstimateSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_year_eq | String | 允许 | 条件字段：year<br>条件组合方式：`=` |
| 2 | n_id_like | Long | 允许 | 条件字段：id<br>条件组合方式：`%like%` |
| 3 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | n_month_eq | String | 允许 | 条件字段：month<br>条件组合方式：`=` |
| 5 | n_taskname_eq | String | 允许 | 条件字段：taskname<br>条件组合方式：`=` |
| 6 | n_taskname_like | String | 允许 | 条件字段：taskname<br>条件组合方式：`%like%` |
| 7 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 8 | n_task_eq | Long | 允许 | 条件字段：task<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
