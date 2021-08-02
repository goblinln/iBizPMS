# 服务接口-IBZ_SUBTASK
## 接口说明
任务

## 接口清单
### 新建任务
#### 访问路径
/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 批量新建任务
#### 访问路径
/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据建立任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

## 附录
### 数据类型说明
#### SubTaskDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | canceledby | String | 允许 | 由谁取消 |
| 2 | configtype | String | 允许 | 周期类型 |
| 3 | left | Double | 允许 | 预计剩余 |
| 4 | isfavorites | String | 允许 | 是否收藏 |
| 5 | configend | Timestamp | 允许 | 过期日期<br>时间格式：yyyy-MM-dd |
| 6 | hasdetail | String | 允许 | 是否填写描述 |
| 7 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 8 | assign | String | 允许 | 是否指派 |
| 9 | color | String | 允许 | 标题颜色 |
| 10 | id | Long | 不可 | 编号 |
| 11 | finishedby | String | 允许 | 由谁完成 |
| 12 | mytotaltime | Double | 允许 | 我的总消耗 |
| 13 | mailtopk | String | 允许 | 抄送给 |
| 14 | finishedlist | String | 允许 | 完成者列表 |
| 15 | modulename1 | String | 允许 | 所属模块 |
| 16 | isleaf | String | 允许 | 是否子任务 |
| 17 | realstarted | Timestamp | 允许 | 实际开始<br>时间格式：yyyy-MM-dd |
| 18 | status1 | String | 允许 | 任务状态 |
| 19 | replycount | Integer | 允许 | 回复数量 |
| 20 | configbegin | Timestamp | 允许 | 开始日期<br>时间格式：yyyy-MM-dd |
| 21 | updatedate | Timestamp | 允许 | 最后的更新日期<br>时间格式：yyyy-MM-dd |
| 22 | noticeusers | String | 允许 | 消息通知用户 |
| 23 | closedby | String | 允许 | 由谁关闭 |
| 24 | currentconsumed | Double | 允许 | 本次消耗 |
| 25 | files | String | 允许 | 附件 |
| 26 | substatus | String | 允许 | 子状态 |
| 27 | closedreason | String | 允许 | 关闭原因 |
| 28 | taskspecies | String | 允许 | 任务种别 |
| 29 | lastediteddate | Timestamp | 允许 | 最后修改日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 30 | configday | Integer | 允许 | 间隔天数 |
| 31 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 32 | pri | Integer | 允许 | 优先级 |
| 33 | lasteditedby | String | 允许 | 最后修改 |
| 34 | idvalue | Long | 允许 | 关联编号 |
| 35 | status | String | 允许 | 任务状态 |
| 36 | multiple | String | 允许 | 多人任务 |
| 37 | name | String | 不可 | 任务名称 |
| 38 | closeddate | Timestamp | 允许 | 关闭时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 39 | inputcost | Double | 允许 | 投入成本 |
| 40 | totaltime | Double | 允许 | 总计耗时 |
| 41 | type | String | 允许 | 任务类型 |
| 42 | assignedto | String | 允许 | 指派给 |
| 43 | delay | String | 允许 | 延期 |
| 44 | desc | String | 允许 | 任务描述 |
| 45 | eststarted | Timestamp | 允许 | 预计开始<br>时间格式：yyyy-MM-dd |
| 46 | deadline | Timestamp | 允许 | 截止日期<br>时间格式：yyyy-MM-dd |
| 47 | statusorder | Long | 允许 | 排序 |
| 48 | mailtoconact | String | 允许 | 联系人 |
| 49 | deleted | String | 允许 | 已删除 |
| 50 | cycle | Integer | 允许 | 周期 |
| 51 | mailto | String | 允许 | 抄送给 |
| 52 | consumed | Double | 允许 | 总计消耗 |
| 53 | estimate | Double | 允许 | 最初预计 |
| 54 | openedby | String | 允许 | 由谁创建 |
| 55 | isfinished | String | 允许 | 是否完成 |
| 56 | canceleddate | Timestamp | 允许 | 取消时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 57 | configmonth | String | 允许 | 周期设置月 |
| 58 | comment | String | 允许 | 备注 |
| 59 | duration | String | 允许 | 持续时间 |
| 60 | assignedtozj | String | 允许 | 转交给 |
| 61 | usernames | String | 允许 | 团队用户 |
| 62 | myconsumed | Double | 允许 | 之前消耗 |
| 63 | configweek | String | 允许 | 周期设置周几 |
| 64 | tasktype | String | 允许 | 任务类型 |
| 65 | allmodules | String | 允许 | 所有模块 |
| 66 | configbeforedays | Integer | 允许 | 提前天数 |
| 67 | finisheddate | Timestamp | 允许 | 实际完成<br>时间格式：yyyy-MM-dd |
| 68 | progressrate | String | 允许 | 进度 |
| 69 | modulename | String | 允许 | 所属模块 |
| 70 | storyname | String | 允许 | 相关需求 |
| 71 | path | String | 允许 | 模块路径 |
| 72 | planname | String | 允许 | 所属计划 |
| 73 | projectname | String | 允许 | 所属项目 |
| 74 | product | Long | 允许 | 产品 |
| 75 | storyversion | Integer | 允许 | 需求版本 |
| 76 | productname | String | 允许 | 产品 |
| 77 | parentname | String | 允许 | 父任务 |
| 78 | project | Long | 允许 | 所属项目 |
| 79 | plan | Long | 允许 | 编号 |
| 80 | module | Long | 允许 | 模块 |
| 81 | story | Long | 允许 | 相关需求 |
| 82 | parent | Long | 允许 | 父任务 |
| 83 | frombug | Long | 允许 | 来源Bug |
| 84 | ordernum | Integer | 允许 | 排序 |
| 85 | assignedtopk | String | 允许 | 指派给 |
| 86 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TaskSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_canceledby_eq | String | 允许 | 条件字段：canceledby<br>条件组合方式：`=` |
| 2 | n_config_type_eq | String | 允许 | 条件字段：config_type<br>条件组合方式：`=` |
| 3 | n_isfavorites_eq | String | 允许 | 条件字段：isfavorites<br>条件组合方式：`=` |
| 4 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 5 | n_id_noteq | Long | 允许 | 条件字段：id<br>条件组合方式：`!=`或者`<>` |
| 6 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 7 | n_finishedby_eq | String | 允许 | 条件字段：finishedby<br>条件组合方式：`=` |
| 8 | n_status1_eq | String | 允许 | 条件字段：status1<br>条件组合方式：`=` |
| 9 | n_closedby_eq | String | 允许 | 条件字段：closedby<br>条件组合方式：`=` |
| 10 | n_closedreason_eq | String | 允许 | 条件字段：closedreason<br>条件组合方式：`=` |
| 11 | n_taskspecies_eq | String | 允许 | 条件字段：taskspecies<br>条件组合方式：`=` |
| 12 | n_taskspecies_in | String | 允许 | 条件字段：taskspecies<br>条件组合方式：`in(...)` |
| 13 | n_assigneddate_gtandeq | Timestamp | 允许 | 条件字段：assigneddate<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 14 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 15 | n_pri_in | Integer | 允许 | 条件字段：pri<br>条件组合方式：`in(...)` |
| 16 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 17 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 18 | n_status_in | String | 允许 | 条件字段：status<br>条件组合方式：`in(...)` |
| 19 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 20 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 21 | n_closeddate_ltandeq | Timestamp | 允许 | 条件字段：closeddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 22 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 23 | n_assignedto_eq | String | 允许 | 条件字段：assignedto<br>条件组合方式：`=` |
| 24 | n_assignedto_in | String | 允许 | 条件字段：assignedto<br>条件组合方式：`in(...)` |
| 25 | n_eststarted_gtandeq | Timestamp | 允许 | 条件字段：eststarted<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 26 | n_eststarted_ltandeq | Timestamp | 允许 | 条件字段：eststarted<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 27 | n_cycle_eq | Integer | 允许 | 条件字段：cycle<br>条件组合方式：`=` |
| 28 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 29 | n_tasktype_eq | String | 允许 | 条件字段：tasktype<br>条件组合方式：`=` |
| 30 | n_finisheddate_ltandeq | Timestamp | 允许 | 条件字段：finisheddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 31 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 32 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 33 | n_storyname_eq | String | 允许 | 条件字段：storyname<br>条件组合方式：`=` |
| 34 | n_storyname_like | String | 允许 | 条件字段：storyname<br>条件组合方式：`%like%` |
| 35 | n_path_like | String | 允许 | 条件字段：path<br>条件组合方式：`%like%` |
| 36 | n_planname_eq | String | 允许 | 条件字段：planname<br>条件组合方式：`=` |
| 37 | n_planname_like | String | 允许 | 条件字段：planname<br>条件组合方式：`%like%` |
| 38 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 39 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 40 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 41 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 42 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 43 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 44 | n_project_in | Long | 允许 | 条件字段：project<br>条件组合方式：`in(...)` |
| 45 | n_plan_eq | Long | 允许 | 条件字段：plan<br>条件组合方式：`=` |
| 46 | n_plan_noteq | Long | 允许 | 条件字段：plan<br>条件组合方式：`!=`或者`<>` |
| 47 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 48 | n_story_eq | Long | 允许 | 条件字段：story<br>条件组合方式：`=` |
| 49 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 50 | n_parent_gtandeq | Long | 允许 | 条件字段：parent<br>条件组合方式：`>=` |
| 51 | n_frombug_eq | Long | 允许 | 条件字段：frombug<br>条件组合方式：`=` |
| 52 | customcond | String | 允许 | 自定义查询条件 |
| 53 | customparams | String | 允许 | 自定义查询参数 |
| 54 | query | String | 允许 | 快速搜索 |
| 55 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 56 | page | int | 允许 | 当前页数<br>默认值0 |
| 57 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 58 | sort | String | 允许 | 排序 |
