# 服务接口-ZT_TASKTEAM
## 接口说明
任务团队

## 接口清单
### 根据建立任务团队
#### 访问路径
/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteam_id | Long | 任务团队主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteam_id | Long | 任务团队主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |
| 5 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |
| 5 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量建立任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据删除任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteam_id | Long | 任务团队主键ID |
| 5 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskTeamDTO](#TaskTeamDTO)：任务团队实体传输对象 |

### 根据批量更新任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdto | [TaskTeamDTO](#TaskTeamDTO) | 任务团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务团队
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | taskteamdtos | List<[TaskTeamDTO](#TaskTeamDTO)> | 任务团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskTeamSearchContext](#TaskTeamSearchContext) | 任务团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskTeamDTO](#TaskTeamDTO)>：任务团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TaskTeamDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | estimate | Double | 允许 | 最初预计 |
| 2 | username | String | 允许 | 用户 |
| 3 | join | Timestamp | 允许 | 加盟日<br>时间格式：yyyy-MM-dd |
| 4 | hours | Double | 允许 | 可用工时/天 |
| 5 | account | String | 允许 | 用户 |
| 6 | order | Integer | 允许 | 排序 |
| 7 | left | Double | 允许 | 预计剩余 |
| 8 | consumed | Double | 允许 | 总计消耗 |
| 9 | limited | String | 允许 | 受限用户 |
| 10 | role | String | 允许 | 角色 |
| 11 | id | Long | 不可 | 编号 |
| 12 | days | Integer | 允许 | 可用工日 |
| 13 | total | Integer | 允许 | 总计可用 |
| 14 | type | String | 允许 | 团队类型 |
| 15 | root | Long | 允许 | 编号 |
| 16 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TaskTeamSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_account_like | String | 允许 | 条件字段：account<br>条件组合方式：`%like%` |
| 2 | n_limited_eq | String | 允许 | 条件字段：limited<br>条件组合方式：`=` |
| 3 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
