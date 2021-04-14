# 服务接口-ZT_DOCLIB
## 接口说明
文档库

## 接口清单
### 新建文档库
#### 访问路径
/doclibs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibdto | [DocLibDTO](#DocLibDTO) | 文档库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibDTO](#DocLibDTO)：文档库实体传输对象 |

### 批量新建文档库
#### 访问路径
/doclibs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibdtos | List<[DocLibDTO](#DocLibDTO)> | 文档库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新文档库
#### 访问路径
/doclibs/{doclib_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclib_id | Long | 文档库主键ID |
| 2 | doclibdto | [DocLibDTO](#DocLibDTO) | 文档库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibDTO](#DocLibDTO)：文档库实体传输对象 |

### 批量更新文档库
#### 访问路径
/doclibs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibdtos | List<[DocLibDTO](#DocLibDTO)> | 文档库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除文档库
#### 访问路径
/doclibs/{doclib_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclib_id | Long | 文档库主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除文档库
#### 访问路径
/doclibs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 文档库主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取文档库
#### 访问路径
/doclibs/{doclib_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclib_id | Long | 文档库主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibDTO](#DocLibDTO)：文档库实体传输对象 |

### 检查文档库
#### 访问路径
/doclibs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibdto | [DocLibDTO](#DocLibDTO) | 文档库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 收藏
#### 访问路径
/doclibs/{doclib_id}/collect

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclib_id | Long | 文档库主键ID |
| 2 | doclibdto | [DocLibDTO](#DocLibDTO) | 文档库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibDTO](#DocLibDTO)：文档库实体传输对象 |

### 保存文档库
#### 访问路径
/doclibs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibdto | [DocLibDTO](#DocLibDTO) | 文档库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存文档库
#### 访问路径
/doclibs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibdtos | List<[DocLibDTO](#DocLibDTO)> | 文档库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 取消收藏
#### 访问路径
/doclibs/{doclib_id}/uncollect

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclib_id | Long | 文档库主键ID |
| 2 | doclibdto | [DocLibDTO](#DocLibDTO) | 文档库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibDTO](#DocLibDTO)：文档库实体传输对象 |

### 获取自定义文档库
#### 访问路径
/doclibs/fetchbycustom

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询自定义文档库
#### 访问路径
/doclibs/searchbycustom

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品文档库
#### 访问路径
/doclibs/fetchbyproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询产品文档库
#### 访问路径
/doclibs/searchbyproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品文档库
#### 访问路径
/doclibs/fetchbyproductnotfiles

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询产品文档库
#### 访问路径
/doclibs/searchbyproductnotfiles

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目文件库
#### 访问路径
/doclibs/fetchbyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询项目文件库
#### 访问路径
/doclibs/searchbyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目文件库
#### 访问路径
/doclibs/fetchbyprojectnotfiles

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询项目文件库
#### 访问路径
/doclibs/searchbyprojectnotfiles

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所属文档库
#### 访问路径
/doclibs/fetchcurdoclib

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询所属文档库
#### 访问路径
/doclibs/searchcurdoclib

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/doclibs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/doclibs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/doclibs/fetchmyfavourites

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/doclibs/searchmyfavourites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根目录
#### 访问路径
/doclibs/fetchrootmodulemulu

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象列表 |

### 查询根目录
#### 访问路径
/doclibs/searchrootmodulemulu

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibSearchContext](#DocLibSearchContext) | 文档库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibDTO](#DocLibDTO)>：文档库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### DocLibDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | type | String | 允许 | 文档类型 |
| 2 | doclibtype | String | 允许 | 文件库类型 |
| 3 | isfavourites | String | 允许 | 是否收藏 |
| 4 | collector | String | 允许 | 收藏者 |
| 5 | mdeptid | String | 允许 | 部门标识 |
| 6 | orgid | String | 允许 | 组织标识 |
| 7 | acl | String | 允许 | 权限 |
| 8 | root | String | 允许 | Root |
| 9 | deleted | String | 允许 | 已删除 |
| 10 | groups | String | 允许 | 分组 |
| 11 | doccnt | Integer | 允许 | 文档数量 |
| 12 | id | Long | 不可 | 编号 |
| 13 | users | String | 允许 | 用户 |
| 14 | main | String | 允许 | 是否是主库 |
| 15 | name | String | 不可 | 文档库名称 |
| 16 | modulecnt | Integer | 允许 | 文件夹数 |
| 17 | openeddate | Timestamp | 允许 | 创建时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 18 | order | Integer | 允许 | 排序 |
| 19 | productname | String | 允许 | 所属产品 |
| 20 | projectname | String | 允许 | 所属项目 |
| 21 | project | Long | 允许 | 项目库 |
| 22 | product | Long | 允许 | 产品库 |
| 23 | createby | String | 允许 | 由谁创建 |
| 24 | updateby | String | 允许 | 由谁更新 |
| 25 | <动态属性> | Object | 允许 | 支持动态属性 |

#### DocLibSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 2 | n_doclibtype_eq | String | 允许 | 条件字段：doclibtype<br>条件组合方式：`=` |
| 3 | n_acl_eq | String | 允许 | 条件字段：acl<br>条件组合方式：`=` |
| 4 | n_main_eq | String | 允许 | 条件字段：main<br>条件组合方式：`=` |
| 5 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 6 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 7 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 8 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 9 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 10 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 11 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 12 | customcond | String | 允许 | 自定义查询条件 |
| 13 | customparams | String | 允许 | 自定义查询参数 |
| 14 | query | String | 允许 | 快速搜索 |
| 15 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 16 | page | int | 允许 | 当前页数<br>默认值0 |
| 17 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 18 | sort | String | 允许 | 排序 |
