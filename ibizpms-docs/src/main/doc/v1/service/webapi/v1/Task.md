# 服务接口-ZT_TASK
## 接口说明
任务

## 接口清单
### 新建任务
#### 访问路径
/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 批量新建任务
#### 访问路径
/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新任务
#### 访问路径
/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 批量更新任务
#### 访问路径
/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除任务
#### 访问路径
/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除任务
#### 访问路径
/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取任务
#### 访问路径
/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查任务
#### 访问路径
/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 保存任务
#### 访问路径
/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存任务
#### 访问路径
/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取指派给我任务
#### 访问路径
/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询指派给我任务
#### 访问路径
/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我任务（EE）
#### 访问路径
/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询指派给我任务（EE）
#### 访问路径
/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我任务（PC）
#### 访问路径
/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询指派给我任务（PC）
#### 访问路径
/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Bug相关任务
#### 访问路径
/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询Bug相关任务
#### 访问路径
/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询
#### 访问路径
/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询通过模块查询
#### 访问路径
/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询（聚微）
#### 访问路径
/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询通过模块查询（聚微）
#### 访问路径
/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询（子任务）
#### 访问路径
/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询数据查询（子任务）
#### 访问路径
/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询（子任务）（更多）
#### 访问路径
/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询数据查询（子任务）（更多）
#### 访问路径
/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取子任务
#### 访问路径
/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询子任务
#### 访问路径
/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取子任务（树）
#### 访问路径
/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询子任务（树）
#### 访问路径
/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户年度完成任务
#### 访问路径
/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询用户年度完成任务
#### 访问路径
/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DefaultRow
#### 访问路径
/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询DefaultRow
#### 访问路径
/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我代理的任务
#### 访问路径
/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我代理的任务
#### 访问路径
/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我相关的任务
#### 访问路径
/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我相关的任务
#### 访问路径
/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我相关的任务（聚微）
#### 访问路径
/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我相关的任务（聚微）
#### 访问路径
/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（汇报）
#### 访问路径
/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（汇报）
#### 访问路径
/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（移动端日报）
#### 访问路径
/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（移动端日报）
#### 访问路径
/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（移动端月报）
#### 访问路径
/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（移动端月报）
#### 访问路径
/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（月报展示）
#### 访问路径
/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（月报展示）
#### 访问路径
/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（汇报）
#### 访问路径
/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（汇报）
#### 访问路径
/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我计划参与的任务（移动端月报）
#### 访问路径
/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我计划参与的任务（移动端月报）
#### 访问路径
/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我计划参与的任务（汇报）
#### 访问路径
/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我计划参与的任务（汇报）
#### 访问路径
/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我计划参与的任务（汇报）
#### 访问路径
/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我计划参与的任务（汇报）
#### 访问路径
/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取移动端下周计划参与(汇报)
#### 访问路径
/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询移动端下周计划参与(汇报)
#### 访问路径
/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周完成的任务(汇报)
#### 访问路径
/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询本周完成的任务(汇报)
#### 访问路径
/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取下周计划完成任务(汇报)
#### 访问路径
/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询下周计划完成任务(汇报)
#### 访问路径
/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取相关任务（计划）
#### 访问路径
/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询相关任务（计划）
#### 访问路径
/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务（项目立项）
#### 访问路径
/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询项目任务（项目立项）
#### 访问路径
/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务
#### 访问路径
/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询项目任务
#### 访问路径
/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务（聚微）
#### 访问路径
/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询项目任务（聚微）
#### 访问路径
/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根任务
#### 访问路径
/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询根任务
#### 访问路径
/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取关联计划（当前项目未关联）
#### 访问路径
/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询关联计划（当前项目未关联）
#### 访问路径
/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我本月完成的任务（下拉列表框）
#### 访问路径
/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询我本月完成的任务（下拉列表框）
#### 访问路径
/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周完成的任务(汇报)
#### 访问路径
/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询本周完成的任务(汇报)
#### 访问路径
/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周已完成任务(下拉框选择)
#### 访问路径
/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询本周已完成任务(下拉框选择)
#### 访问路径
/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取移动端本周已完成任务(汇报)
#### 访问路径
/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询移动端本周已完成任务(汇报)
#### 访问路径
/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周完成的任务(汇报)
#### 访问路径
/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询本周完成的任务(汇报)
#### 访问路径
/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取todo列表查询
#### 访问路径
/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 查询todo列表查询
#### 访问路径
/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务类型分组
#### 访问路径
/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 查询任务类型分组
#### 访问路径
/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务类型分组（计划）
#### 访问路径
/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 查询任务类型分组（计划）
#### 访问路径
/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/productplans/{productplan_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/productplans/{productplan_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/productplans/{productplan_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/productplans/{productplan_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/productplans/{productplan_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/productplans/{productplan_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/productplans/{productplan_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/productplans/{productplan_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/productplans/{productplan_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/productplans/{productplan_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/productplans/{productplan_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/productplans/{productplan_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/productplans/{productplan_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/productplans/{productplan_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/productplans/{productplan_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/productplans/{productplan_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/productplans/{productplan_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/productplans/{productplan_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/productplans/{productplan_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/productplans/{productplan_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/productplans/{productplan_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/productplans/{productplan_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/productplans/{productplan_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/productplans/{productplan_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/productplans/{productplan_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/productplans/{productplan_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/productplans/{productplan_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/productplans/{productplan_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/productplans/{productplan_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/stories/{story_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/stories/{story_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/stories/{story_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/stories/{story_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/stories/{story_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/stories/{story_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/stories/{story_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/stories/{story_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/stories/{story_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/stories/{story_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/stories/{story_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/stories/{story_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/stories/{story_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/stories/{story_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/stories/{story_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/stories/{story_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/stories/{story_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/stories/{story_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/stories/{story_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/stories/{story_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/stories/{story_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/stories/{story_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/stories/{story_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/stories/{story_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/stories/{story_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/stories/{story_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/stories/{story_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/stories/{story_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/stories/{story_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/stories/{story_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/stories/{story_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/stories/{story_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/stories/{story_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/stories/{story_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/stories/{story_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/stories/{story_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/stories/{story_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/stories/{story_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/stories/{story_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/stories/{story_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/stories/{story_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/stories/{story_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/stories/{story_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/stories/{story_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/stories/{story_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/stories/{story_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/stories/{story_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/stories/{story_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/stories/{story_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/stories/{story_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/stories/{story_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/stories/{story_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/stories/{story_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/stories/{story_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/stories/{story_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/stories/{story_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/stories/{story_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/stories/{story_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/stories/{story_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/stories/{story_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/stories/{story_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/stories/{story_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/stories/{story_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/stories/{story_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/stories/{story_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/stories/{story_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/stories/{story_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/stories/{story_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/stories/{story_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/stories/{story_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/stories/{story_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/stories/{story_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/stories/{story_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/stories/{story_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/stories/{story_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/stories/{story_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/stories/{story_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/stories/{story_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/stories/{story_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/projects/{project_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/projects/{project_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/projects/{project_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/projects/{project_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/projects/{project_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/projects/{project_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/projects/{project_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/projects/{project_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/projects/{project_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/projects/{project_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/projects/{project_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/projects/{project_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/projects/{project_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/projects/{project_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/projects/{project_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/projects/{project_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/projects/{project_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/projects/{project_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/projects/{project_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/projects/{project_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/projects/{project_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/projects/{project_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projects/{project_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/projects/{project_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/projects/{project_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/projects/{project_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | task_id | Long | 任务主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/projects/{project_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/projects/{project_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/projects/{project_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/projects/{project_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/projects/{project_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/projects/{project_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/projects/{project_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/projects/{project_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/projects/{project_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/projects/{project_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/projects/{project_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/projects/{project_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/projects/{project_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/projects/{project_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/projects/{project_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/projects/{project_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/projects/{project_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/projects/{project_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/projects/{project_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/projects/{project_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projects/{project_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projects/{project_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/projects/{project_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/projects/{project_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/projects/{project_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/projects/{project_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projects/{project_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projects/{project_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/projects/{project_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/projects/{project_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/projects/{project_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/projects/{project_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/projects/{project_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/projects/{project_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/projects/{project_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/projects/{project_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/projects/{project_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/projects/{project_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/projects/{project_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/projects/{project_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/projects/{project_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/projects/{project_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量建立任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 激活
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | taskdtos | List<[TaskDTO](#TaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | task_id | Long | 任务主键ID |
| 4 | taskdto | [TaskDTO](#TaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TaskDTO](#TaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TaskDTO](#TaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TaskDTO](#TaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TaskDTO
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
| 47 | statusorder | Integer | 允许 | 排序 |
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
| 85 | ibztaskestimates | List<[IBZTaskEstimateDTO}](#IBZTaskEstimateDTO})> | 允许 | 任务预计 |
| 86 | ibztaskteams | List<[IBZTaskTeamDTO}](#IBZTaskTeamDTO})> | 允许 | 任务团队 |
| 87 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IBZTaskEstimateDTO
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
| 26 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IBZTaskTeamDTO
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

#### TaskSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_canceledby_eq | String | 允许 | 条件字段：canceledby<br>条件组合方式：`=` |
| 2 | n_config_type_eq | String | 允许 | 条件字段：config_type<br>条件组合方式：`=` |
| 3 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 4 | n_id_noteq | Long | 允许 | 条件字段：id<br>条件组合方式：`!=`或者`<>` |
| 5 | n_finishedby_eq | String | 允许 | 条件字段：finishedby<br>条件组合方式：`=` |
| 6 | n_status1_eq | String | 允许 | 条件字段：status1<br>条件组合方式：`=` |
| 7 | n_closedby_eq | String | 允许 | 条件字段：closedby<br>条件组合方式：`=` |
| 8 | n_closedreason_eq | String | 允许 | 条件字段：closedreason<br>条件组合方式：`=` |
| 9 | n_taskspecies_eq | String | 允许 | 条件字段：taskspecies<br>条件组合方式：`=` |
| 10 | n_assigneddate_gtandeq | Timestamp | 允许 | 条件字段：assigneddate<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 11 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 12 | n_pri_in | Integer | 允许 | 条件字段：pri<br>条件组合方式：`in(...)` |
| 13 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 14 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 15 | n_status_in | String | 允许 | 条件字段：status<br>条件组合方式：`in(...)` |
| 16 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 17 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 18 | n_closeddate_ltandeq | Timestamp | 允许 | 条件字段：closeddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 19 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 20 | n_assignedto_eq | String | 允许 | 条件字段：assignedto<br>条件组合方式：`=` |
| 21 | n_assignedto_in | String | 允许 | 条件字段：assignedto<br>条件组合方式：`in(...)` |
| 22 | n_eststarted_gtandeq | Timestamp | 允许 | 条件字段：eststarted<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 23 | n_eststarted_ltandeq | Timestamp | 允许 | 条件字段：eststarted<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 24 | n_cycle_eq | Integer | 允许 | 条件字段：cycle<br>条件组合方式：`=` |
| 25 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 26 | n_tasktype_eq | String | 允许 | 条件字段：tasktype<br>条件组合方式：`=` |
| 27 | n_finisheddate_ltandeq | Timestamp | 允许 | 条件字段：finisheddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 28 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 29 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 30 | n_storyname_eq | String | 允许 | 条件字段：storyname<br>条件组合方式：`=` |
| 31 | n_storyname_like | String | 允许 | 条件字段：storyname<br>条件组合方式：`%like%` |
| 32 | n_path_like | String | 允许 | 条件字段：path<br>条件组合方式：`%like%` |
| 33 | n_planname_eq | String | 允许 | 条件字段：planname<br>条件组合方式：`=` |
| 34 | n_planname_like | String | 允许 | 条件字段：planname<br>条件组合方式：`%like%` |
| 35 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 36 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 37 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 38 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 39 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 40 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 41 | n_project_in | Long | 允许 | 条件字段：project<br>条件组合方式：`in(...)` |
| 42 | n_plan_eq | Long | 允许 | 条件字段：plan<br>条件组合方式：`=` |
| 43 | n_plan_noteq | Long | 允许 | 条件字段：plan<br>条件组合方式：`!=`或者`<>` |
| 44 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 45 | n_story_eq | Long | 允许 | 条件字段：story<br>条件组合方式：`=` |
| 46 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 47 | n_parent_gtandeq | Long | 允许 | 条件字段：parent<br>条件组合方式：`>=` |
| 48 | n_frombug_eq | Long | 允许 | 条件字段：frombug<br>条件组合方式：`=` |
| 49 | customcond | String | 允许 | 自定义查询条件 |
| 50 | customparams | String | 允许 | 自定义查询参数 |
| 51 | query | String | 允许 | 快速搜索 |
| 52 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 53 | page | int | 允许 | 当前页数<br>默认值0 |
| 54 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 55 | sort | String | 允许 | 排序 |
