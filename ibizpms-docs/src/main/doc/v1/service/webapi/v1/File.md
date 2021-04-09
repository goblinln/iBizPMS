# 服务接口-ZT_FILE
## 接口说明
附件

## 接口清单
### 新建附件
#### 访问路径
/files

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | filedto | [FileDTO](#FileDTO) | 附件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [FileDTO](#FileDTO)：附件实体传输对象 |

### 批量新建附件
#### 访问路径
/files/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | filedtos | List<[FileDTO](#FileDTO)> | 附件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新附件
#### 访问路径
/files/{file_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | file_id | Long | 附件主键ID |
| 2 | filedto | [FileDTO](#FileDTO) | 附件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [FileDTO](#FileDTO)：附件实体传输对象 |

### 批量更新附件
#### 访问路径
/files/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | filedtos | List<[FileDTO](#FileDTO)> | 附件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除附件
#### 访问路径
/files/{file_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | file_id | Long | 附件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除附件
#### 访问路径
/files/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 附件主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取附件
#### 访问路径
/files/{file_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | file_id | Long | 附件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [FileDTO](#FileDTO)：附件实体传输对象 |

### 检查附件
#### 访问路径
/files/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | filedto | [FileDTO](#FileDTO) | 附件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存附件
#### 访问路径
/files/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | filedto | [FileDTO](#FileDTO) | 附件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存附件
#### 访问路径
/files/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | filedtos | List<[FileDTO](#FileDTO)> | 附件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存附件
#### 访问路径
/files/{file_id}/updateobjectidforpmsee

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | file_id | Long | 附件主键ID |
| 2 | filedto | [FileDTO](#FileDTO) | 附件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [FileDTO](#FileDTO)：附件实体传输对象 |

### 获取DEFAULT
#### 访问路径
/files/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[FileDTO](#FileDTO)>：附件实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/files/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[FileDTO](#FileDTO)>：附件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文件库查询
#### 访问路径
/files/fetchdoclibfile

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[FileDTO](#FileDTO)>：附件实体传输对象列表 |

### 查询文件库查询
#### 访问路径
/files/searchdoclibfile

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[FileDTO](#FileDTO)>：附件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文件库查询
#### 访问路径
/files/fetchproductdoclibfile

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[FileDTO](#FileDTO)>：附件实体传输对象列表 |

### 查询文件库查询
#### 访问路径
/files/searchproductdoclibfile

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[FileDTO](#FileDTO)>：附件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取动态(根据类型过滤)
#### 访问路径
/files/fetchtype

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[FileDTO](#FileDTO)>：附件实体传输对象列表 |

### 查询动态(根据类型过滤)
#### 访问路径
/files/searchtype

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[FileDTO](#FileDTO)>：附件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取查询附件
#### 访问路径
/files/fetchtypenotbysrfparentkey

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[FileDTO](#FileDTO)>：附件实体传输对象列表 |

### 查询查询附件
#### 访问路径
/files/searchtypenotbysrfparentkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [FileSearchContext](#FileSearchContext) | 附件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[FileDTO](#FileDTO)>：附件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### FileDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | pathname | String | 允许 | 路径 |
| 2 | objectid | Long | 允许 | 对象ID |
| 3 | strsize | String | 允许 | 显示大小 |
| 4 | doclibtype | String | 允许 | 文档类型 |
| 5 | deleted | String | 允许 | 已删除 |
| 6 | extension | String | 允许 | 文件类型 |
| 7 | objecttype | String | 允许 | 对象类型 |
| 8 | addedby | String | 允许 | 由谁添加 |
| 9 | title | String | 不可 | 标题 |
| 10 | addeddate | Timestamp | 允许 | 添加时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 11 | downloads | Integer | 允许 | 下载次数 |
| 12 | size | Integer | 允许 | 大小 |
| 13 | id | Long | 不可 | id |
| 14 | extra | String | 允许 | 备注 |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

#### FileSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_objectid_eq | Long | 允许 | 条件字段：objectid<br>条件组合方式：`=` |
| 2 | n_objecttype_eq | String | 允许 | 条件字段：objecttype<br>条件组合方式：`=` |
| 3 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 4 | n_extra_eq | String | 允许 | 条件字段：extra<br>条件组合方式：`=` |
| 5 | n_extra_noteq | String | 允许 | 条件字段：extra<br>条件组合方式：`!=`或者`<>` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
