# 服务接口-IBZ_PROJECTTEAM
## 接口说明
项目团队

## 接口清单
### 获取成员角色
#### 访问路径
/projectteams/{projectteam_id}/getuserrole

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteam_id | Long | 项目团队主键ID |
| 2 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 新建项目团队
#### 访问路径
/projectteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 批量新建项目团队
#### 访问路径
/projectteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteamdtos | List<[ProjectTeamDTO](#ProjectTeamDTO)> | 项目团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目团队
#### 访问路径
/projectteams/{projectteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteam_id | Long | 项目团队主键ID |
| 2 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 批量更新项目团队
#### 访问路径
/projectteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteamdtos | List<[ProjectTeamDTO](#ProjectTeamDTO)> | 项目团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目团队
#### 访问路径
/projectteams/{projectteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteam_id | Long | 项目团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目团队
#### 访问路径
/projectteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 项目团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目团队
#### 访问路径
/projectteams/{projectteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteam_id | Long | 项目团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 检查项目团队
#### 访问路径
/projectteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存项目团队
#### 访问路径
/projectteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目团队
#### 访问路径
/projectteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectteamdtos | List<[ProjectTeamDTO](#ProjectTeamDTO)> | 项目团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/projectteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/projectteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目成员（项目经理）
#### 访问路径
/projectteams/fetchprojectteampm

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 查询项目成员（项目经理）
#### 访问路径
/projectteams/searchprojectteampm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取行编辑查询
#### 访问路径
/projectteams/fetchroweditdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 查询行编辑查询
#### 访问路径
/projectteams/searchroweditdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询
#### 访问路径
/projectteams/fetchtaskcntestimateconsumedleft

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 查询数据查询
#### 访问路径
/projectteams/searchtaskcntestimateconsumedleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取成员角色
#### 访问路径
/projects/{project_id}/projectteams/{projectteam_id}/getuserrole

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteam_id | Long | 项目团队主键ID |
| 3 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 根据建立项目团队
#### 访问路径
/projects/{project_id}/projectteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 根据批量建立项目团队
#### 访问路径
/projects/{project_id}/projectteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteamdtos | List<[ProjectTeamDTO](#ProjectTeamDTO)> | 项目团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新项目团队
#### 访问路径
/projects/{project_id}/projectteams/{projectteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteam_id | Long | 项目团队主键ID |
| 3 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 根据批量更新项目团队
#### 访问路径
/projects/{project_id}/projectteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteamdtos | List<[ProjectTeamDTO](#ProjectTeamDTO)> | 项目团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除项目团队
#### 访问路径
/projects/{project_id}/projectteams/{projectteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteam_id | Long | 项目团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除项目团队
#### 访问路径
/projects/{project_id}/projectteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 项目团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取项目团队
#### 访问路径
/projects/{project_id}/projectteams/{projectteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteam_id | Long | 项目团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTeamDTO](#ProjectTeamDTO)：项目团队实体传输对象 |

### 根据检查项目团队
#### 访问路径
/projects/{project_id}/projectteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存项目团队
#### 访问路径
/projects/{project_id}/projectteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteamdto | [ProjectTeamDTO](#ProjectTeamDTO) | 项目团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存项目团队
#### 访问路径
/projects/{project_id}/projectteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectteamdtos | List<[ProjectTeamDTO](#ProjectTeamDTO)> | 项目团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目成员（项目经理）
#### 访问路径
/projects/{project_id}/projectteams/fetchprojectteampm

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 根据查询项目成员（项目经理）
#### 访问路径
/projects/{project_id}/projectteams/searchprojectteampm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取行编辑查询
#### 访问路径
/projects/{project_id}/projectteams/fetchroweditdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 根据查询行编辑查询
#### 访问路径
/projects/{project_id}/projectteams/searchroweditdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/projects/{project_id}/projectteams/fetchtaskcntestimateconsumedleft

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/projects/{project_id}/projectteams/searchtaskcntestimateconsumedleft

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectTeamSearchContext](#ProjectTeamSearchContext) | 项目团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTeamDTO](#ProjectTeamDTO)>：项目团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectTeamDTO
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

#### ProjectTeamSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_limited_eq | String | 允许 | 条件字段：limited<br>条件组合方式：`=` |
| 2 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 3 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 5 | n_account_like | String | 允许 | 条件字段：account<br>条件组合方式：`%like%` |
| 6 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 7 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 8 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
