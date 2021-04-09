# 服务接口-IBZ_PROJECTMODULE
## 接口说明
任务模块

## 接口清单
### 新建任务模块
#### 访问路径
/projectmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 批量新建任务模块
#### 访问路径
/projectmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmoduledtos | List<[ProjectModuleDTO](#ProjectModuleDTO)> | 任务模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新任务模块
#### 访问路径
/projectmodules/{projectmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 批量更新任务模块
#### 访问路径
/projectmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmoduledtos | List<[ProjectModuleDTO](#ProjectModuleDTO)> | 任务模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除任务模块
#### 访问路径
/projectmodules/{projectmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除任务模块
#### 访问路径
/projectmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 任务模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取任务模块
#### 访问路径
/projectmodules/{projectmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 检查任务模块
#### 访问路径
/projectmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/projectmodules/{projectmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 删除模块
#### 访问路径
/projectmodules/{projectmodule_id}/removemodule

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmodule_id | Long | 任务模块主键ID |
| 2 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 保存任务模块
#### 访问路径
/projectmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存任务模块
#### 访问路径
/projectmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectmoduledtos | List<[ProjectModuleDTO](#ProjectModuleDTO)> | 任务模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取BYPATH
#### 访问路径
/projectmodules/fetchbypath

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询BYPATH
#### 访问路径
/projectmodules/searchbypath

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/projectmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/projectmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取父模块
#### 访问路径
/projectmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询父模块
#### 访问路径
/projectmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块
#### 访问路径
/projectmodules/fetchroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询根模块
#### 访问路径
/projectmodules/searchroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块_无分支
#### 访问路径
/projectmodules/fetchroot_nobranch

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询根模块_无分支
#### 访问路径
/projectmodules/searchroot_nobranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块
#### 访问路径
/projectmodules/fetchroot_task

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询根模块
#### 访问路径
/projectmodules/searchroot_task

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务模块
#### 访问路径
/projectmodules/fetchtaskmodules

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 查询任务模块
#### 访问路径
/projectmodules/searchtaskmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立任务模块
#### 访问路径
/projects/{project_id}/projectmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 根据批量建立任务模块
#### 访问路径
/projects/{project_id}/projectmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmoduledtos | List<[ProjectModuleDTO](#ProjectModuleDTO)> | 任务模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新任务模块
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 根据批量更新任务模块
#### 访问路径
/projects/{project_id}/projectmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmoduledtos | List<[ProjectModuleDTO](#ProjectModuleDTO)> | 任务模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除任务模块
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmodule_id | Long | 任务模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除任务模块
#### 访问路径
/projects/{project_id}/projectmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 任务模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取任务模块
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmodule_id | Long | 任务模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 根据检查任务模块
#### 访问路径
/projects/{project_id}/projectmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 删除模块
#### 访问路径
/projects/{project_id}/projectmodules/{projectmodule_id}/removemodule

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmodule_id | Long | 任务模块主键ID |
| 3 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectModuleDTO](#ProjectModuleDTO)：任务模块实体传输对象 |

### 根据保存任务模块
#### 访问路径
/projects/{project_id}/projectmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmoduledto | [ProjectModuleDTO](#ProjectModuleDTO) | 任务模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存任务模块
#### 访问路径
/projects/{project_id}/projectmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectmoduledtos | List<[ProjectModuleDTO](#ProjectModuleDTO)> | 任务模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取BYPATH
#### 访问路径
/projects/{project_id}/projectmodules/fetchbypath

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询BYPATH
#### 访问路径
/projects/{project_id}/projectmodules/searchbypath

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取父模块
#### 访问路径
/projects/{project_id}/projectmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询父模块
#### 访问路径
/projects/{project_id}/projectmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块
#### 访问路径
/projects/{project_id}/projectmodules/fetchroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询根模块
#### 访问路径
/projects/{project_id}/projectmodules/searchroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块_无分支
#### 访问路径
/projects/{project_id}/projectmodules/fetchroot_nobranch

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询根模块_无分支
#### 访问路径
/projects/{project_id}/projectmodules/searchroot_nobranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块
#### 访问路径
/projects/{project_id}/projectmodules/fetchroot_task

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询根模块
#### 访问路径
/projects/{project_id}/projectmodules/searchroot_task

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务模块
#### 访问路径
/projects/{project_id}/projectmodules/fetchtaskmodules

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象列表 |

### 根据查询任务模块
#### 访问路径
/projects/{project_id}/projectmodules/searchtaskmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectModuleSearchContext](#ProjectModuleSearchContext) | 任务模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectModuleDTO](#ProjectModuleDTO)>：任务模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectModuleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ibizshort | String | 允许 | 简称 |
| 2 | isleaf | String | 允许 | 叶子模块 |
| 3 | type | String | 允许 | 类型（task） |
| 4 | orderpk | String | 允许 | 数据选择排序 |
| 5 | name | String | 不可 | 名称 |
| 6 | order | Integer | 允许 | 排序值 |
| 7 | collector | String | 允许 | collector |
| 8 | grade | Integer | 允许 | grade |
| 9 | branch | Integer | 允许 | branch |
| 10 | path | String | 允许 | path |
| 11 | id | Long | 不可 | id |
| 12 | owner | String | 允许 | owner |
| 13 | deleted | String | 允许 | 逻辑删除标志 |
| 14 | rootname | String | 允许 | 所属项目 |
| 15 | parentname | String | 允许 | 上级模块 |
| 16 | root | Long | 允许 | 项目 |
| 17 | parent | Long | 允许 | id |
| 18 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectModuleSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 2 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 3 | n_rootname_eq | String | 允许 | 条件字段：rootname<br>条件组合方式：`=` |
| 4 | n_rootname_like | String | 允许 | 条件字段：rootname<br>条件组合方式：`%like%` |
| 5 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 6 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 7 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 8 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
