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
| ---- | ---- | ---- | ---- |
| 1 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 批量新建项目
#### 访问路径
/projects/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectdtos | List<[ProjectDTO](#ProjectDTO)> | 项目实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目
#### 访问路径
/projects/{project_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 删除项目
#### 访问路径
/projects/{project_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目
#### 访问路径
/projects/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 项目主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目
#### 访问路径
/projects/{project_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 批量更新项目
#### 访问路径
/projects/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectdtos | List<[ProjectDTO](#ProjectDTO)> | 项目实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/projects/{project_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 批量解除关联需求
#### 访问路径
/projects/{project_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 取消置顶
#### 访问路径
/projects/{project_id}/cancelprojecttop

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 检查项目
#### 访问路径
/projects/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 关联需求
#### 访问路径
/projects/{project_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 团队管理
#### 访问路径
/projects/{project_id}/managemembers

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 移动端项目计数器
#### 访问路径
/projects/{project_id}/mobprojectcount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 项目立项任务快速分组计数器
#### 访问路径
/projects/{project_id}/pmseeprojectalltaskcount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 项目立项待办任务快速分组计数器
#### 访问路径
/projects/{project_id}/pmseeprojecttodotaskcount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 项目任务快速分组计数器
#### 访问路径
/projects/{project_id}/projecttaskqcnt

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 置顶
#### 访问路径
/projects/{project_id}/projecttop

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 延期
#### 访问路径
/projects/{project_id}/putoff

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 保存项目
#### 访问路径
/projects/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目
#### 访问路径
/projects/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectdtos | List<[ProjectDTO](#ProjectDTO)> | 项目实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 开始
#### 访问路径
/projects/{project_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 挂起
#### 访问路径
/projects/{project_id}/suspend

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 移除成员
#### 访问路径
/projects/{project_id}/unlinkmember

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 解除关联需求
#### 访问路径
/projects/{project_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 排序
#### 访问路径
/projects/{project_id}/updateorder

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectdto | [ProjectDTO](#ProjectDTO) | 项目实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectDTO](#ProjectDTO)：项目实体传输对象 |

### 获取所有项目
#### 访问路径
/projects/fetchallproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询所有项目
#### 访问路径
/projects/searchallproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取BugProject
#### 访问路径
/projects/fetchbugproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询BugProject
#### 访问路径
/projects/searchbugproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前人的所有项目
#### 访问路径
/projects/fetchcurpersonalprojects

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前人的所有项目
#### 访问路径
/projects/searchcurpersonalprojects

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前计划项目
#### 访问路径
/projects/fetchcurplanproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前计划项目
#### 访问路径
/projects/searchcurplanproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前项目
#### 访问路径
/projects/fetchcurproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前项目
#### 访问路径
/projects/searchcurproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前用户项目
#### 访问路径
/projects/fetchcuruser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前用户项目
#### 访问路径
/projects/searchcuruser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前用户项目（企业版）
#### 访问路径
/projects/fetchcurusersa

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前用户项目（企业版）
#### 访问路径
/projects/searchcurusersa

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前用户待办项目
#### 访问路径
/projects/fetchcurusertodo

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询当前用户待办项目
#### 访问路径
/projects/searchcurusertodo

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/projects/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/projects/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/projects/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/projects/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取参与项目(年度总结)
#### 访问路径
/projects/fetchinvolvedproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询参与项目(年度总结)
#### 访问路径
/projects/searchinvolvedproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取参与项目完成需求任务bug
#### 访问路径
/projects/fetchinvolvedproject_storytaskbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询参与项目完成需求任务bug
#### 访问路径
/projects/searchinvolvedproject_storytaskbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的项目
#### 访问路径
/projects/fetchmyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询我的项目
#### 访问路径
/projects/searchmyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队
#### 访问路径
/projects/fetchprojectteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询项目团队
#### 访问路径
/projects/searchprojectteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取需求影响项目
#### 访问路径
/projects/fetchstoryproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询需求影响项目
#### 访问路径
/projects/searchstoryproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取未完成项目
#### 访问路径
/projects/fetchundoneproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectDTO](#ProjectDTO)>：项目实体传输对象列表 |

### 查询未完成项目
#### 访问路径
/projects/searchundoneproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectSearchContext](#ProjectSearchContext) | 项目查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectDTO](#ProjectDTO)>：项目实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | openedversion | String | 允许 | 当前系统版本 |
| 2 | begin | Timestamp | 不可 | 开始时间<br>时间格式：yyyy-MM-dd |
| 3 | hours | BigDecimal | 允许 | 可用工时/天 |
| 4 | pmseeprojectinfo | String | 允许 | 项目立项信息 |
| 5 | acl | String | 允许 | 访问控制 |
| 6 | deleted | String | 允许 | 已删除 |
| 7 | totalconsumed | Double | 允许 | 任务消耗总工时 |
| 8 | products | String | 允许 | 关联产品 |
| 9 | ycompletetaskcnt | Integer | 允许 | 已完成任务数 |
| 10 | plans | String | 允许 | 关联计划 |
| 11 | desc | String | 允许 | 项目描述 |
| 12 | temptaskcnt | Integer | 允许 | 临时任务数 |
| 13 | doclibcnt | Integer | 允许 | 文档数量 |
| 14 | mycompletetaskcnt | Integer | 允许 | 我完成任务数 |
| 15 | istop | Integer | 允许 | 是否置顶 |
| 16 | uncompletetaskcnt | Integer | 允许 | 未完成任务数 |
| 17 | teamcnt | Integer | 允许 | 团队成员总数 |
| 18 | pm | String | 允许 | 项目负责人 |
| 19 | dept | String | 允许 | 选择部门 |
| 20 | id | Long | 不可 | 项目编号 |
| 21 | name | String | 不可 | 项目名称 |
| 22 | substatus | String | 允许 | 子状态 |
| 23 | role | String | 允许 | 角色 |
| 24 | order | Integer | 允许 | 项目排序 |
| 25 | bugcnt | Integer | 允许 | Bug总数 |
| 26 | rd | String | 允许 | 发布负责人 |
| 27 | managemembers | String | 允许 | 复制团队 |
| 28 | ystarttaskcnt | Integer | 允许 | 进行中任务数 |
| 29 | whitelist | String | 允许 | 分组白名单 |
| 30 | mobimage | String | 允许 | 移动端图片 |
| 31 | totalwh | Integer | 允许 | 总工时 |
| 32 | totalhours | BigDecimal | 允许 | 可用工时 |
| 33 | pri | String | 允许 | 优先级 |
| 34 | end | Timestamp | 不可 | 结束日期<br>时间格式：yyyy-MM-dd |
| 35 | canceleddate | Timestamp | 允许 | 取消日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 36 | plantaskcnt | Integer | 允许 | 计划任务数 |
| 37 | join | Timestamp | 允许 | 加盟日<br>时间格式：yyyy-MM-dd |
| 38 | totalestimate | Double | 允许 | 任务最初预计总工时 |
| 39 | mdeptid | String | 允许 | 部门标识 |
| 40 | totalleft | Double | 允许 | 任务预计剩余总工时 |
| 41 | srfarray | String | 允许 | 关联数据数组 |
| 42 | code | String | 允许 | 项目代号 |
| 43 | period | String | 允许 | 时间段 |
| 44 | closetaskcnt | Integer | 允许 | 关闭任务数 |
| 45 | branchs | String | 允许 | 关联产品平台集合 |
| 46 | catid | Integer | 允许 | catID |
| 47 | orgid | String | 允许 | 组织标识 |
| 48 | unstarttaskcnt | Integer | 允许 | 未开始任务数 |
| 49 | accounts | String | 允许 | 项目团队相关成员 |
| 50 | noticeusers | String | 允许 | 消息通知用户 |
| 51 | statge | String | 允许 | statge |
| 52 | canceltaskcnt | Integer | 允许 | 取消任务数 |
| 53 | taskcnt | Integer | 允许 | 任务总数 |
| 54 | alltaskcnt | Integer | 允许 | 所有任务数 |
| 55 | supproreport | String | 允许 | 支持项目汇报 |
| 56 | canceledby | String | 允许 | 由谁取消 |
| 57 | iscat | String | 允许 | isCat |
| 58 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 59 | unclosetaskcnt | Integer | 允许 | 未关闭任务数 |
| 60 | storychangecnt | Integer | 允许 | 需求变更数 |
| 61 | closedby | String | 允许 | 由谁关闭 |
| 62 | type | String | 允许 | 项目类型 |
| 63 | buildcnt | Integer | 允许 | 版本总数 |
| 64 | account | String | 允许 | 项目团队成员 |
| 65 | po | String | 允许 | 产品负责人 |
| 66 | asstomytaskcnt | Integer | 允许 | 指派给我任务数 |
| 67 | order1 | Integer | 允许 | 项目排序 |
| 68 | status | String | 允许 | 项目状态 |
| 69 | moretaskcnt | Integer | 允许 | 更多任务数 |
| 70 | days | Integer | 允许 | 可用工作日 |
| 71 | cycletaskcnt | Integer | 允许 | 周期任务数 |
| 72 | team | String | 允许 | 团队名称 |
| 73 | closeddate | Timestamp | 允许 | 关闭日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 74 | comment | String | 允许 | 备注 |
| 75 | openedby | String | 允许 | 由谁创建 |
| 76 | storycnt | Integer | 允许 | 需求总数 |
| 77 | qd | String | 允许 | 测试负责人 |
| 78 | parentname | String | 允许 | parent |
| 79 | parent | Long | 允许 | 父项目 |
| 80 | ibzprojectteams | List<[IBZPROJECTTEAMDTO}](#IBZPROJECTTEAMDTO})> | 允许 | 项目团队 |
| 81 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IBZPROJECTTEAMDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | role | String | 允许 | 角色 |
| 2 | limited | String | 允许 | 受限用户 |
| 3 | total | Integer | 允许 | 总计可用 |
| 4 | username | String | 允许 | 用户 |
| 5 | days | Integer | 允许 | 可用工日 |
| 6 | exitdate | Timestamp | 允许 | 退场时间<br>时间格式：yyyy-MM-dd |
| 7 | type | String | 允许 | 团队类型 |
| 8 | order | Integer | 允许 | 排序 |
| 9 | id | Long | 不可 | 编号 |
| 10 | consumed | Double | 允许 | 总计消耗 |
| 11 | account | String | 允许 | 用户 |
| 12 | estimate | Double | 允许 | 最初预计 |
| 13 | join | Timestamp | 允许 | 加盟日<br>时间格式：yyyy-MM-dd |
| 14 | hours | Double | 允许 | 可用工时/天 |
| 15 | taskcnt | Integer | 允许 | 任务数 |
| 16 | left | Double | 允许 | 预计剩余 |
| 17 | pm | String | 允许 | 项目经理 |
| 18 | projectname | String | 允许 | 所属项目 |
| 19 | root | Long | 允许 | 项目编号 |
| 20 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_acl_eq | String | 允许 | 条件字段：acl<br>条件组合方式：`=` |
| 2 | n_istop_eq | Integer | 允许 | 条件字段：istop<br>条件组合方式：`=` |
| 3 | n_pm_eq | String | 允许 | 条件字段：pm<br>条件组合方式：`=` |
| 4 | n_dept_eq | String | 允许 | 条件字段：dept<br>条件组合方式：`=` |
| 5 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 6 | n_id_in | Long | 允许 | 条件字段：id<br>条件组合方式：`in(...)` |
| 7 | n_id_noteq | Long | 允许 | 条件字段：id<br>条件组合方式：`!=`或者`<>` |
| 8 | n_name_eq | String | 允许 | 条件字段：name<br>条件组合方式：`=` |
| 9 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 10 | n_rd_eq | String | 允许 | 条件字段：rd<br>条件组合方式：`=` |
| 11 | n_managemembers_eq | String | 允许 | 条件字段：managemembers<br>条件组合方式：`=` |
| 12 | n_pri_eq | String | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 13 | n_period_eq | String | 允许 | 条件字段：period<br>条件组合方式：`=` |
| 14 | n_statge_eq | String | 允许 | 条件字段：statge<br>条件组合方式：`=` |
| 15 | n_supproreport_eq | String | 允许 | 条件字段：supproreport<br>条件组合方式：`=` |
| 16 | n_iscat_eq | String | 允许 | 条件字段：iscat<br>条件组合方式：`=` |
| 17 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 18 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 19 | n_po_eq | String | 允许 | 条件字段：po<br>条件组合方式：`=` |
| 20 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 21 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 22 | n_qd_eq | String | 允许 | 条件字段：qd<br>条件组合方式：`=` |
| 23 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 24 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 25 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 26 | customcond | String | 允许 | 自定义查询条件 |
| 27 | customparams | String | 允许 | 自定义查询参数 |
| 28 | query | String | 允许 | 快速搜索 |
| 29 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 30 | page | int | 允许 | 当前页数<br>默认值0 |
| 31 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 32 | sort | String | 允许 | 排序 |
