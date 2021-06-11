# 服务接口-ZT_MODULE
## 接口说明
模块

## 接口清单
### 新建模块
#### 访问路径
/modules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | moduledto | [ModuleDTO](#ModuleDTO) | 模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ModuleDTO](#ModuleDTO)：模块实体传输对象 |

### 批量新建模块
#### 访问路径
/modules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | moduledtos | List<[ModuleDTO](#ModuleDTO)> | 模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取模块
#### 访问路径
/modules/{module_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | module_id | Long | 模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ModuleDTO](#ModuleDTO)：模块实体传输对象 |

### 删除模块
#### 访问路径
/modules/{module_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | module_id | Long | 模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除模块
#### 访问路径
/modules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新模块
#### 访问路径
/modules/{module_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | module_id | Long | 模块主键ID |
| 2 | moduledto | [ModuleDTO](#ModuleDTO) | 模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ModuleDTO](#ModuleDTO)：模块实体传输对象 |

### 批量更新模块
#### 访问路径
/modules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | moduledtos | List<[ModuleDTO](#ModuleDTO)> | 模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查模块
#### 访问路径
/modules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | moduledto | [ModuleDTO](#ModuleDTO) | 模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/modules/{module_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | module_id | Long | 模块主键ID |
| 2 | moduledto | [ModuleDTO](#ModuleDTO) | 模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ModuleDTO](#ModuleDTO)：模块实体传输对象 |

### 保存模块
#### 访问路径
/modules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | moduledto | [ModuleDTO](#ModuleDTO) | 模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存模块
#### 访问路径
/modules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | moduledtos | List<[ModuleDTO](#ModuleDTO)> | 模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取BugModule
#### 访问路径
/modules/fetchbugmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询BugModule
#### 访问路径
/modules/searchbugmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/modules/fetchbugmodulecodelist

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询数据集
#### 访问路径
/modules/searchbugmodulecodelist

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/modules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/modules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文档目录
#### 访问路径
/modules/fetchdocmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询文档目录
#### 访问路径
/modules/searchdocmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品线
#### 访问路径
/modules/fetchline

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询产品线
#### 访问路径
/modules/searchline

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取需求模块
#### 访问路径
/modules/fetchstorymodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询需求模块
#### 访问路径
/modules/searchstorymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务模块
#### 访问路径
/modules/fetchtaskmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ModuleDTO](#ModuleDTO)>：模块实体传输对象列表 |

### 查询任务模块
#### 访问路径
/modules/searchtaskmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ModuleSearchContext](#ModuleSearchContext) | 模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ModuleDTO](#ModuleDTO)>：模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ModuleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | root | String | 允许 | 所属根 |
| 2 | grade | Integer | 允许 | 级别 |
| 3 | type | String | 允许 | 类型 |
| 4 | name | String | 不可 | 模块名称 |
| 5 | order | Integer | 允许 | 排序 |
| 6 | owner | String | 允许 | 负责人 |
| 7 | id | Long | 不可 | id |
| 8 | orderpk | String | 允许 | 数据选择排序 |
| 9 | collector | String | 允许 | 收藏者 |
| 10 | ibizshort | String | 允许 | 简称 |
| 11 | path | String | 允许 | 路径 |
| 12 | mdeptid | String | 允许 | 部门标识 |
| 13 | orgid | String | 允许 | 组织标识 |
| 14 | deleted | String | 允许 | 已删除 |
| 15 | parentname | String | 允许 | 上级模块 |
| 16 | branch | Long | 允许 | 平台/分支 |
| 17 | parent | Long | 允许 | 上级模块 |
| 18 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ModuleSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_root_eq | String | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 2 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 3 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 4 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 5 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 6 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 7 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 8 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
