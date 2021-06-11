# 服务接口-ZT_DOCCONTENT
## 接口说明
文档内容

## 接口清单
### 新建文档内容
#### 访问路径
/doccontents

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocContentDTO](#DocContentDTO)：文档内容实体传输对象 |

### 批量新建文档内容
#### 访问路径
/doccontents/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontentdtos | List<[DocContentDTO](#DocContentDTO)> | 文档内容实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取文档内容
#### 访问路径
/doccontents/{doccontent_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontent_id | Long | 文档内容主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocContentDTO](#DocContentDTO)：文档内容实体传输对象 |

### 删除文档内容
#### 访问路径
/doccontents/{doccontent_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontent_id | Long | 文档内容主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除文档内容
#### 访问路径
/doccontents/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 文档内容主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新文档内容
#### 访问路径
/doccontents/{doccontent_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontent_id | Long | 文档内容主键ID |
| 2 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocContentDTO](#DocContentDTO)：文档内容实体传输对象 |

### 批量更新文档内容
#### 访问路径
/doccontents/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontentdtos | List<[DocContentDTO](#DocContentDTO)> | 文档内容实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查文档内容
#### 访问路径
/doccontents/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存文档内容
#### 访问路径
/doccontents/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存文档内容
#### 访问路径
/doccontents/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doccontentdtos | List<[DocContentDTO](#DocContentDTO)> | 文档内容实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取当前版本
#### 访问路径
/doccontents/fetchcurversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象列表 |

### 查询当前版本
#### 访问路径
/doccontents/searchcurversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/doccontents/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/doccontents/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立文档内容
#### 访问路径
/docs/{doc_id}/doccontents

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocContentDTO](#DocContentDTO)：文档内容实体传输对象 |

### 根据批量建立文档内容
#### 访问路径
/docs/{doc_id}/doccontents/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontentdtos | List<[DocContentDTO](#DocContentDTO)> | 文档内容实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取文档内容
#### 访问路径
/docs/{doc_id}/doccontents/{doccontent_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontent_id | Long | 文档内容主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocContentDTO](#DocContentDTO)：文档内容实体传输对象 |

### 根据删除文档内容
#### 访问路径
/docs/{doc_id}/doccontents/{doccontent_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontent_id | Long | 文档内容主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除文档内容
#### 访问路径
/docs/{doc_id}/doccontents/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | ids | List<Long> | 文档内容主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新文档内容
#### 访问路径
/docs/{doc_id}/doccontents/{doccontent_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontent_id | Long | 文档内容主键ID |
| 3 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocContentDTO](#DocContentDTO)：文档内容实体传输对象 |

### 根据批量更新文档内容
#### 访问路径
/docs/{doc_id}/doccontents/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontentdtos | List<[DocContentDTO](#DocContentDTO)> | 文档内容实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查文档内容
#### 访问路径
/docs/{doc_id}/doccontents/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存文档内容
#### 访问路径
/docs/{doc_id}/doccontents/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontentdto | [DocContentDTO](#DocContentDTO) | 文档内容实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存文档内容
#### 访问路径
/docs/{doc_id}/doccontents/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | doccontentdtos | List<[DocContentDTO](#DocContentDTO)> | 文档内容实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取当前版本
#### 访问路径
/docs/{doc_id}/doccontents/fetchcurversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象列表 |

### 根据查询当前版本
#### 访问路径
/docs/{doc_id}/doccontents/searchcurversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/docs/{doc_id}/doccontents/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/docs/{doc_id}/doccontents/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | context | [DocContentSearchContext](#DocContentSearchContext) | 文档内容查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocContentDTO](#DocContentDTO)>：文档内容实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### DocContentDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | files | String | 允许 | 附件 |
| 2 | id | Long | 不可 | 编号 |
| 3 | content | String | 允许 | 文档正文 |
| 4 | type | String | 允许 | 文档类型 |
| 5 | title | String | 不可 | 文档标题 |
| 6 | version | Integer | 允许 | 版本号 |
| 7 | digest | String | 允许 | 文档摘要 |
| 8 | doc | Long | 允许 | 文档 |
| 9 | <动态属性> | Object | 允许 | 支持动态属性 |

#### DocContentSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 3 | n_doc_eq | Long | 允许 | 条件字段：doc<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
