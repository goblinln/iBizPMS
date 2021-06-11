# 服务接口-PROJECTTODO
## 接口说明
项目其他活动

## 接口清单
### 新建项目其他活动
#### 访问路径
/projecttodos

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 批量新建项目其他活动
#### 访问路径
/projecttodos/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttododtos | List<[ProjectTodoDTO](#ProjectTodoDTO)> | 项目其他活动实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目其他活动
#### 访问路径
/projecttodos/{projecttodo_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 删除项目其他活动
#### 访问路径
/projecttodos/{projecttodo_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目其他活动
#### 访问路径
/projecttodos/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 项目其他活动主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目其他活动
#### 访问路径
/projecttodos/{projecttodo_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 批量更新项目其他活动
#### 访问路径
/projecttodos/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttododtos | List<[ProjectTodoDTO](#ProjectTodoDTO)> | 项目其他活动实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### Activate
#### 访问路径
/projecttodos/{projecttodo_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### AssignTo
#### 访问路径
/projecttodos/{projecttodo_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 检查项目其他活动
#### 访问路径
/projecttodos/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### Close
#### 访问路径
/projecttodos/{projecttodo_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 定时创建周期
#### 访问路径
/projecttodos/{projecttodo_id}/createcycle

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### Finish
#### 访问路径
/projecttodos/{projecttodo_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 保存项目其他活动
#### 访问路径
/projecttodos/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目其他活动
#### 访问路径
/projecttodos/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttododtos | List<[ProjectTodoDTO](#ProjectTodoDTO)> | 项目其他活动实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/projecttodos/{projecttodo_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projecttodos/{projecttodo_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttodo_id | Long | 项目其他活动主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 获取数据集
#### 访问路径
/projecttodos/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTodoSearchContext](#ProjectTodoSearchContext) | 项目其他活动查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTodoDTO](#ProjectTodoDTO)>：项目其他活动实体传输对象列表 |

### 查询数据集
#### 访问路径
/projecttodos/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTodoSearchContext](#ProjectTodoSearchContext) | 项目其他活动查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTodoDTO](#ProjectTodoDTO)>：项目其他活动实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 根据批量建立项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttododtos | List<[ProjectTodoDTO](#ProjectTodoDTO)> | 项目其他活动实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 根据删除项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 项目其他活动主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 根据批量更新项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttododtos | List<[ProjectTodoDTO](#ProjectTodoDTO)> | 项目其他活动实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### Activate
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### AssignTo
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 根据检查项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### Close
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 定时创建周期
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/createcycle

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### Finish
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/finish

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 根据保存项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存项目其他活动
#### 访问路径
/projects/{project_id}/projecttodos/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttododtos | List<[ProjectTodoDTO](#ProjectTodoDTO)> | 项目其他活动实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projects/{project_id}/projecttodos/{projecttodo_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projecttodo_id | Long | 项目其他活动主键ID |
| 3 | projecttododto | [ProjectTodoDTO](#ProjectTodoDTO) | 项目其他活动实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTodoDTO](#ProjectTodoDTO)：项目其他活动实体传输对象 |

### 根据获取数据集
#### 访问路径
/projects/{project_id}/projecttodos/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTodoSearchContext](#ProjectTodoSearchContext) | 项目其他活动查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTodoDTO](#ProjectTodoDTO)>：项目其他活动实体传输对象列表 |

### 根据查询数据集
#### 访问路径
/projects/{project_id}/projecttodos/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTodoSearchContext](#ProjectTodoSearchContext) | 项目其他活动查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTodoDTO](#ProjectTodoDTO)>：项目其他活动实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectTodoDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | id | Long | 不可 | 编号 |
| 2 | account | String | 允许 | 所有者 |
| 3 | ibizprivate | String | 允许 | 私人事务 |
| 4 | status | String | 允许 | 状态 |
| 5 | type | String | 允许 | 类型 |
| 6 | finisheddate | Timestamp | 允许 | 完成时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | pri | Integer | 允许 | 优先级 |
| 8 | config | String | 允许 | config |
| 9 | assignedby | String | 允许 | 由谁指派 |
| 10 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd |
| 11 | closeddate | Timestamp | 允许 | 关闭时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 12 | assignedto | String | 允许 | 指派给 |
| 13 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 14 | finishedby | String | 允许 | 由谁完成 |
| 15 | name | String | 允许 | 待办名称 |
| 16 | cost | Integer | 允许 | 费用 |
| 17 | closedby | String | 允许 | 由谁关闭 |
| 18 | desc | String | 允许 | 描述 |
| 19 | idvalue | Long | 允许 | 项目编号 |
| 20 | consumed | Double | 允许 | 工时 |
| 21 | files | String | 允许 | 附件 |
| 22 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectTodoSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 3 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 4 | n_idvalue_eq | Long | 允许 | 条件字段：idvalue<br>条件组合方式：`=` |
| 5 | customcond | String | 允许 | 自定义查询条件 |
| 6 | customparams | String | 允许 | 自定义查询参数 |
| 7 | query | String | 允许 | 快速搜索 |
| 8 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 9 | page | int | 允许 | 当前页数<br>默认值0 |
| 10 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 11 | sort | String | 允许 | 排序 |
