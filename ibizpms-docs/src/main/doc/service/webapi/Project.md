# 服务接口-ZT_PROJECT
## 接口说明
项目

## 接口清单
### 新建项目
#### 访问路径
/projects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 批量新建项目
#### 访问路径
/projects/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | projectdtos | List<[ProjectDTO](#ProjectDTO)> | 项目实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目
#### 访问路径
/projects/{project_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 批量更新项目
#### 访问路径
/projects/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | projectdtos | List<[ProjectDTO](#ProjectDTO)> | 项目实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目
#### 访问路径
/projects/{project_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目
#### 访问路径
/projects/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | ids | List<Long> | 项目主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目
#### 访问路径
/projects/{project_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 激活
#### 访问路径
/projects/{project_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 批量解除关联需求
#### 访问路径
/projects/{project_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 取消置顶
#### 访问路径
/projects/{project_id}/cancelprojecttop

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 检查项目
#### 访问路径
/projects/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 关联需求
#### 访问路径
/projects/{project_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 团队管理
#### 访问路径
/projects/{project_id}/managemembers

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 移动端项目计数器
#### 访问路径
/projects/{project_id}/mobprojectcount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 项目任务快速分组计数器
#### 访问路径
/projects/{project_id}/projecttaskqcnt

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 置顶
#### 访问路径
/projects/{project_id}/projecttop

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 延期
#### 访问路径
/projects/{project_id}/putoff

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 保存项目
#### 访问路径
/projects/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目
#### 访问路径
/projects/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | projectdtos | List<[ProjectDTO](#ProjectDTO)> | 项目实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 开始
#### 访问路径
/projects/{project_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 挂起
#### 访问路径
/projects/{project_id}/suspend

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 移除成员
#### 访问路径
/projects/{project_id}/unlinkmember

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 解除关联需求
#### 访问路径
/projects/{project_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 排序
#### 访问路径
/projects/{project_id}/updateorder

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 获取BugProject
#### 访问路径
/projects/fetchbugproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询BugProject
#### 访问路径
/projects/searchbugproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前项目
#### 访问路径
/projects/fetchcurproduct

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前项目
#### 访问路径
/projects/searchcurproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前用户项目
#### 访问路径
/projects/fetchcuruser

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前用户项目
#### 访问路径
/projects/searchcuruser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/projects/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/projects/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取参与项目(年度总结)
#### 访问路径
/projects/fetchinvolvedproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询参与项目(年度总结)
#### 访问路径
/projects/searchinvolvedproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取参与项目完成需求任务bug
#### 访问路径
/projects/fetchinvolvedproject_storytaskbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询参与项目完成需求任务bug
#### 访问路径
/projects/searchinvolvedproject_storytaskbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的项目
#### 访问路径
/projects/fetchmyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询我的项目
#### 访问路径
/projects/searchmyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队
#### 访问路径
/projects/fetchprojectteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询项目团队
#### 访问路径
/projects/searchprojectteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取需求影响项目
#### 访问路径
/projects/fetchstoryproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询需求影响项目
#### 访问路径
/projects/searchstoryproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| -- | -- | -- | -- | -- |
| 1 | openedversion | String | 允许 | 当前系统版本 |
| 2 | begin | Timestamp | 不可 | 开始时间<br>时间格式：yyyy-MM-dd |
| 3 | acl | String | 允许 | 访问控制 |
| 4 | deleted | String | 允许 | 已删除 |
| 5 | desc | String | 允许 | 项目描述 |
| 6 | pm | String | 允许 | 项目负责人 |
| 7 | id | Long | 不可 | 项目编号 |
| 8 | name | String | 不可 | 项目名称 |
| 9 | substatus | String | 允许 | 子状态 |
| 10 | order | Integer | 允许 | 项目排序 |
| 11 | rd | String | 允许 | 发布负责人 |
| 12 | whitelist | String | 允许 | 分组白名单 |
| 13 | pri | String | 允许 | 优先级 |
| 14 | end | Timestamp | 不可 | 结束日期<br>时间格式：yyyy-MM-dd |
| 15 | canceleddate | Timestamp | 允许 | 取消日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 16 | code | String | 允许 | 项目代号 |
| 17 | catid | Integer | 允许 | catID |
| 18 | statge | String | 允许 | statge |
| 19 | canceledby | String | 允许 | 由谁取消 |
| 20 | iscat | String | 允许 | isCat |
| 21 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 22 | closedby | String | 允许 | 由谁关闭 |
| 23 | type | String | 允许 | 项目类型 |
| 24 | po | String | 允许 | 产品负责人 |
| 25 | status | String | 允许 | 项目状态 |
| 26 | days | Integer | 允许 | 可用工作日 |
| 27 | team | String | 允许 | 团队名称 |
| 28 | closeddate | Timestamp | 允许 | 关闭日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 29 | openedby | String | 允许 | 由谁创建 |
| 30 | qd | String | 允许 | 测试负责人 |
| 31 | parentname | String | 允许 | parent |
| 32 | parent | Long | 允许 | 父项目 |
| 33 | taskcnt | Integer | 允许 | 任务总数 |
| 34 | bugcnt | Integer | 允许 | Bug总数 |
| 35 | storycnt | Integer | 允许 | 需求总数 |
| 36 | products | String | 允许 | 关联产品 |
| 37 | branchs | String | 允许 | 关联产品平台集合 |
| 38 | plans | String | 允许 | 关联计划 |
| 39 | srfarray | String | 允许 | 关联数据数组 |
| 40 | comment | String | 允许 | 备注 |
| 41 | period | String | 允许 | 时间段 |
| 42 | account | String | 允许 | 项目团队成员 |
| 43 | join | Timestamp | 允许 | 加盟日<br>时间格式：yyyy-MM-dd |
| 44 | hours | BigDecimal | 允许 | 可用工时/天 |
| 45 | role | String | 允许 | 角色 |
| 46 | totalconsumed | Double | 允许 | 任务消耗总工时 |
| 47 | totalwh | Integer | 允许 | 总工时 |
| 48 | totalleft | Double | 允许 | 任务预计剩余总工时 |
| 49 | totalestimate | Double | 允许 | 任务最初预计总工时 |
| 50 | totalhours | BigDecimal | 允许 | 可用工时 |
| 51 | mobimage | String | 允许 | 移动端图片 |
| 52 | accounts | String | 允许 | 项目团队相关成员 |
| 53 | order1 | Integer | 允许 | 项目排序 |
| 54 | istop | Integer | 允许 | 是否置顶 |
| 55 | dept | String | 允许 | 选择部门 |
| 56 | managemembers | String | 允许 | 复制团队 |
| 57 | buildcnt | Integer | 允许 | 版本总数 |
| 58 | teamcnt | Integer | 允许 | 团队成员总数 |
| 59 | alltaskcnt | Integer | 允许 | 所有任务数 |
| 60 | unclosetaskcnt | Integer | 允许 | 未关闭任务数 |
| 61 | asstomytaskcnt | Integer | 允许 | 指派给我任务数 |
| 62 | unstarttaskcnt | Integer | 允许 | 未开始任务数 |
| 63 | moretaskcnt | Integer | 允许 | 更多任务数 |
| 64 | ystarttaskcnt | Integer | 允许 | 进行中任务数 |
| 65 | uncompletetaskcnt | Integer | 允许 | 未完成任务数 |
| 66 | ycompletetaskcnt | Integer | 允许 | 已完成任务数 |
| 67 | mycompletetaskcnt | Integer | 允许 | 我完成任务数 |
| 68 | closetaskcnt | Integer | 允许 | 关闭任务数 |
| 69 | canceltaskcnt | Integer | 允许 | 取消任务数 |
| 70 | storychangecnt | Integer | 允许 | 需求变更数 |
| 71 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| -- | -- | -- | -- | -- |
| 71 | n_acl_eq | String | 允许 |  |
| 72 | n_pm_eq | String | 允许 |  |
| 73 | n_id_eq | Long | 允许 |  |
| 74 | n_id_noteq | Long | 允许 |  |
| 75 | n_name_like | String | 允许 |  |
| 76 | n_rd_eq | String | 允许 |  |
| 77 | n_pri_eq | String | 允许 |  |
| 78 | n_statge_eq | String | 允许 |  |
| 79 | n_iscat_eq | String | 允许 |  |
| 80 | n_type_eq | String | 允许 |  |
| 81 | n_po_eq | String | 允许 |  |
| 82 | n_status_eq | String | 允许 |  |
| 83 | n_status_noteq | String | 允许 |  |
| 84 | n_qd_eq | String | 允许 |  |
| 85 | n_parentname_eq | String | 允许 |  |
| 86 | n_parentname_like | String | 允许 |  |
| 87 | n_parent_eq | Long | 允许 |  |
| 88 | n_period_eq | String | 允许 |  |
| 89 | n_account_eq | String | 允许 |  |
| 90 | n_dept_eq | String | 允许 |  |
| 91 | n_managemembers_eq | String | 允许 |  |
| 92 | customcond | String | 允许 | 自定义查询条件 |
| 93 | customparams | String | 允许 | 自定义查询参数 |
| 94 | query | String | 允许 | 快速搜索 |
| 95 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 96 | page | int | 允许 | 当前页数<br>默认值0 |
| 97 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 98 | sort | String | 允许 | 排序 |
