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

### 获取任务
#### 访问路径
/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除任务
#### 访问路径
/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除任务
#### 访问路径
/subtasks/batch

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

### 更新任务
#### 访问路径
/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 批量更新任务
#### 访问路径
/subtasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtaskdtos | List<[SubTaskDTO](#SubTaskDTO)> | 任务实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查任务
#### 访问路径
/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 保存任务
#### 访问路径
/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存任务
#### 访问路径
/subtasks/savebatch

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

### 行为
#### 访问路径
/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | subtask_id | Long | 任务主键ID |
| 2 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取指派给我任务
#### 访问路径
/subtasks/fetchassignedtomytask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询指派给我任务
#### 访问路径
/subtasks/searchassignedtomytask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我任务（EE）
#### 访问路径
/subtasks/fetchassignedtomytaskee

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询指派给我任务（EE）
#### 访问路径
/subtasks/searchassignedtomytaskee

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我任务（PC）
#### 访问路径
/subtasks/fetchassignedtomytaskpc

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询指派给我任务（PC）
#### 访问路径
/subtasks/searchassignedtomytaskpc

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Bug相关任务
#### 访问路径
/subtasks/fetchbugtask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询Bug相关任务
#### 访问路径
/subtasks/searchbugtask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询
#### 访问路径
/subtasks/fetchbymodule

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询通过模块查询
#### 访问路径
/subtasks/searchbymodule

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询（聚微）
#### 访问路径
/subtasks/fetchbymoduleee

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询通过模块查询（聚微）
#### 访问路径
/subtasks/searchbymoduleee

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询（子任务）
#### 访问路径
/subtasks/fetchchilddefault

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询数据查询（子任务）
#### 访问路径
/subtasks/searchchilddefault

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询（子任务）（更多）
#### 访问路径
/subtasks/fetchchilddefaultmore

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询数据查询（子任务）（更多）
#### 访问路径
/subtasks/searchchilddefaultmore

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取子任务
#### 访问路径
/subtasks/fetchchildtask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询子任务
#### 访问路径
/subtasks/searchchildtask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取子任务（树）
#### 访问路径
/subtasks/fetchchildtasktree

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询子任务（树）
#### 访问路径
/subtasks/searchchildtasktree

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户年度完成任务
#### 访问路径
/subtasks/fetchcurfinishtask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询用户年度完成任务
#### 访问路径
/subtasks/searchcurfinishtask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/subtasks/fetchdefault

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/subtasks/searchdefault

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DefaultRow
#### 访问路径
/subtasks/fetchdefaultrow

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询DefaultRow
#### 访问路径
/subtasks/searchdefaultrow

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/subtasks/fetchesbulk

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/subtasks/searchesbulk

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我代理的任务
#### 访问路径
/subtasks/fetchmyagenttask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我代理的任务
#### 访问路径
/subtasks/searchmyagenttask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我相关的任务
#### 访问路径
/subtasks/fetchmyalltask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我相关的任务
#### 访问路径
/subtasks/searchmyalltask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我相关的任务（聚微）
#### 访问路径
/subtasks/fetchmyalltaskee

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我相关的任务（聚微）
#### 访问路径
/subtasks/searchmyalltaskee

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（汇报）
#### 访问路径
/subtasks/fetchmycompletetask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（汇报）
#### 访问路径
/subtasks/searchmycompletetask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（移动端日报）
#### 访问路径
/subtasks/fetchmycompletetaskmobdaily

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（移动端日报）
#### 访问路径
/subtasks/searchmycompletetaskmobdaily

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（移动端月报）
#### 访问路径
/subtasks/fetchmycompletetaskmobmonthly

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（移动端月报）
#### 访问路径
/subtasks/searchmycompletetaskmobmonthly

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（月报展示）
#### 访问路径
/subtasks/fetchmycompletetaskmonthlyzs

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（月报展示）
#### 访问路径
/subtasks/searchmycompletetaskmonthlyzs

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我完成的任务（汇报）
#### 访问路径
/subtasks/fetchmycompletetaskzs

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我完成的任务（汇报）
#### 访问路径
/subtasks/searchmycompletetaskzs

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/subtasks/fetchmyfavorites

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/subtasks/searchmyfavorites

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我计划参与的任务（移动端月报）
#### 访问路径
/subtasks/fetchmyplanstaskmobmonthly

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我计划参与的任务（移动端月报）
#### 访问路径
/subtasks/searchmyplanstaskmobmonthly

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的任务
#### 访问路径
/subtasks/fetchmyprojecttask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我的任务
#### 访问路径
/subtasks/searchmyprojecttask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我计划参与的任务（汇报）
#### 访问路径
/subtasks/fetchmytomorrowplantask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我计划参与的任务（汇报）
#### 访问路径
/subtasks/searchmytomorrowplantask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我计划参与的任务（汇报）
#### 访问路径
/subtasks/fetchmytomorrowplantaskmobdaily

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我计划参与的任务（汇报）
#### 访问路径
/subtasks/searchmytomorrowplantaskmobdaily

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取移动端下周计划参与(汇报)
#### 访问路径
/subtasks/fetchnextweekcompletetaskmobzs

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询移动端下周计划参与(汇报)
#### 访问路径
/subtasks/searchnextweekcompletetaskmobzs

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周完成的任务(汇报)
#### 访问路径
/subtasks/fetchnextweekcompletetaskzs

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询本周完成的任务(汇报)
#### 访问路径
/subtasks/searchnextweekcompletetaskzs

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取下周计划完成任务(汇报)
#### 访问路径
/subtasks/fetchnextweekplancompletetask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询下周计划完成任务(汇报)
#### 访问路径
/subtasks/searchnextweekplancompletetask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取人员任务
#### 访问路径
/subtasks/fetchpersonnaltasks

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询人员任务
#### 访问路径
/subtasks/searchpersonnaltasks

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取相关任务（计划）
#### 访问路径
/subtasks/fetchplantask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询相关任务（计划）
#### 访问路径
/subtasks/searchplantask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务（项目立项）
#### 访问路径
/subtasks/fetchprojectapptask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询项目任务（项目立项）
#### 访问路径
/subtasks/searchprojectapptask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目成员任务
#### 访问路径
/subtasks/fetchprojectmembertask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询项目成员任务
#### 访问路径
/subtasks/searchprojectmembertask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务
#### 访问路径
/subtasks/fetchprojecttask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询项目任务
#### 访问路径
/subtasks/searchprojecttask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务（聚微）
#### 访问路径
/subtasks/fetchprojecttaskee

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询项目任务（聚微）
#### 访问路径
/subtasks/searchprojecttaskee

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根任务
#### 访问路径
/subtasks/fetchroottask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询根任务
#### 访问路径
/subtasks/searchroottask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取关联计划（当前项目未关联）
#### 访问路径
/subtasks/fetchtasklinkplan

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询关联计划（当前项目未关联）
#### 访问路径
/subtasks/searchtasklinkplan

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我本月完成的任务（下拉列表框）
#### 访问路径
/subtasks/fetchthismonthcompletetaskchoice

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询我本月完成的任务（下拉列表框）
#### 访问路径
/subtasks/searchthismonthcompletetaskchoice

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周完成的任务(汇报)
#### 访问路径
/subtasks/fetchthisweekcompletetask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询本周完成的任务(汇报)
#### 访问路径
/subtasks/searchthisweekcompletetask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周已完成任务(下拉框选择)
#### 访问路径
/subtasks/fetchthisweekcompletetaskchoice

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询本周已完成任务(下拉框选择)
#### 访问路径
/subtasks/searchthisweekcompletetaskchoice

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取移动端本周已完成任务(汇报)
#### 访问路径
/subtasks/fetchthisweekcompletetaskmobzs

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询移动端本周已完成任务(汇报)
#### 访问路径
/subtasks/searchthisweekcompletetaskmobzs

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取本周完成的任务(汇报)
#### 访问路径
/subtasks/fetchthisweekcompletetaskzs

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询本周完成的任务(汇报)
#### 访问路径
/subtasks/searchthisweekcompletetaskzs

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取todo列表查询
#### 访问路径
/subtasks/fetchtodolisttask

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
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 查询todo列表查询
#### 访问路径
/subtasks/searchtodolisttask

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
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务类型分组
#### 访问路径
/subtasks/fetchtypegroup

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
/subtasks/searchtypegroup

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
/subtasks/fetchtypegroupplan

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
/subtasks/searchtypegroupplan

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

### 根据获取任务
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | subtask_id | Long | 任务主键ID |
| 3 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | task_id | Long | 任务主键ID |
| 2 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/stories/{story_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | subtask_id | Long | 任务主键ID |
| 4 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/projects/{project_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | task_id | Long | 任务主键ID |
| 3 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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

### 根据获取任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据删除任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | ids | List<Long> | 任务主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据批量更新任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/batch

#### 请求方法
PUT

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

### 激活
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 指派/转交
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 取消
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据检查任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/checkkey

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 需求变更确认
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 创建周期任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/createcycletasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 删除工时
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 编辑工时
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 完成
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取下一个团队成员(完成)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（激活）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员剩余工时（开始或继续）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 获取团队成员
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 关联计划
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/linkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 其他更新
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 暂停
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/pause

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 工时录入
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续后剩余工时为0
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 剩余工时为0时不设为已完成
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始后剩余工时为0
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 继续
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/restart

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据保存任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/save

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
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/savebatch

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

### 行为
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 开始
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 检查多人任务操作权限
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 任务收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 更新需求版本
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | subtask_id | Long | 任务主键ID |
| 5 | subtaskdto | [SubTaskDTO](#SubTaskDTO) | 任务实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubTaskDTO](#SubTaskDTO)：任务实体传输对象 |

### 根据获取指派给我任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchassignedtomytask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchassignedtomytask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（EE）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（EE）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchassignedtomytaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我任务（PC）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询指派给我任务（PC）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchbugtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询Bug相关任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchbugtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchbymoduleee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询通过模块查询（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchbymoduleee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchilddefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchilddefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchilddefaultmore

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询数据查询（子任务）（更多）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchilddefaultmore

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchildtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchildtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取子任务（树）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchildtasktree

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询子任务（树）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchildtasktree

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取用户年度完成任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchcurfinishtask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询用户年度完成任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchcurfinishtask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DefaultRow
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchdefaultrow

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询DefaultRow
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchdefaultrow

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyagenttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我代理的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyagenttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyalltask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyalltaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我相关的任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyalltaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端日报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskmonthlyzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（月报展示）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskmonthlyzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我完成的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyplanstaskmobmonthly

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（移动端月报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyplanstaskmobmonthly

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmyprojecttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我的任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmyprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantaskmobdaily

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我计划参与的任务（汇报）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchmytomorrowplantaskmobdaily

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端下周计划参与(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchnextweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchnextweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchnextweekplancompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询下周计划完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchnextweekplancompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取人员任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchpersonnaltasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询人员任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchpersonnaltasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关任务（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询相关任务（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchplantask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（项目立项）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（项目立项）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojectapptask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojectmembertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目成员任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojectmembertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojecttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchprojecttaskee

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询项目任务（聚微）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchprojecttaskee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchroottask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询根任务
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchroottask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtasklinkplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询关联计划（当前项目未关联）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtasklinkplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthismonthcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询我本月完成的任务（下拉列表框）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthismonthcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskchoice

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周已完成任务(下拉框选择)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskchoice

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskmobzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询移动端本周已完成任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskmobzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchthisweekcompletetaskzs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询本周完成的任务(汇报)
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchthisweekcompletetaskzs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取todo列表查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtodolisttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象列表 |

### 根据查询todo列表查询
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtodolisttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubTaskDTO](#SubTaskDTO)>：任务实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtypegroup

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtypegroup

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务类型分组（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchtypegroupplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<HashMap>：结果集Map列表 |

### 根据查询任务类型分组（计划）
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchtypegroupplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | projectmodule_id | Long | 任务模块主键ID |/r/n| 3 | task_id | Long | 任务主键ID |
| 4 | context | [TaskSearchContext](#TaskSearchContext) | 任务查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<HashMap>：结果集Map分页对象<br>分页对象为`org.springframework.data.domain.Page` |

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
| 12 | n_assigneddate_gtandeq | Timestamp | 允许 | 条件字段：assigneddate<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 13 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 14 | n_pri_in | Integer | 允许 | 条件字段：pri<br>条件组合方式：`in(...)` |
| 15 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 16 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 17 | n_status_in | String | 允许 | 条件字段：status<br>条件组合方式：`in(...)` |
| 18 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 19 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 20 | n_closeddate_ltandeq | Timestamp | 允许 | 条件字段：closeddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 21 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 22 | n_assignedto_eq | String | 允许 | 条件字段：assignedto<br>条件组合方式：`=` |
| 23 | n_assignedto_in | String | 允许 | 条件字段：assignedto<br>条件组合方式：`in(...)` |
| 24 | n_eststarted_gtandeq | Timestamp | 允许 | 条件字段：eststarted<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 25 | n_eststarted_ltandeq | Timestamp | 允许 | 条件字段：eststarted<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 26 | n_cycle_eq | Integer | 允许 | 条件字段：cycle<br>条件组合方式：`=` |
| 27 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 28 | n_tasktype_eq | String | 允许 | 条件字段：tasktype<br>条件组合方式：`=` |
| 29 | n_finisheddate_ltandeq | Timestamp | 允许 | 条件字段：finisheddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 30 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 31 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 32 | n_storyname_eq | String | 允许 | 条件字段：storyname<br>条件组合方式：`=` |
| 33 | n_storyname_like | String | 允许 | 条件字段：storyname<br>条件组合方式：`%like%` |
| 34 | n_path_like | String | 允许 | 条件字段：path<br>条件组合方式：`%like%` |
| 35 | n_planname_eq | String | 允许 | 条件字段：planname<br>条件组合方式：`=` |
| 36 | n_planname_like | String | 允许 | 条件字段：planname<br>条件组合方式：`%like%` |
| 37 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 38 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 39 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 40 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 41 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 42 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 43 | n_project_in | Long | 允许 | 条件字段：project<br>条件组合方式：`in(...)` |
| 44 | n_plan_eq | Long | 允许 | 条件字段：plan<br>条件组合方式：`=` |
| 45 | n_plan_noteq | Long | 允许 | 条件字段：plan<br>条件组合方式：`!=`或者`<>` |
| 46 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 47 | n_story_eq | Long | 允许 | 条件字段：story<br>条件组合方式：`=` |
| 48 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 49 | n_parent_gtandeq | Long | 允许 | 条件字段：parent<br>条件组合方式：`>=` |
| 50 | n_frombug_eq | Long | 允许 | 条件字段：frombug<br>条件组合方式：`=` |
| 51 | customcond | String | 允许 | 自定义查询条件 |
| 52 | customparams | String | 允许 | 自定义查询参数 |
| 53 | query | String | 允许 | 快速搜索 |
| 54 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 55 | page | int | 允许 | 当前页数<br>默认值0 |
| 56 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 57 | sort | String | 允许 | 排序 |
