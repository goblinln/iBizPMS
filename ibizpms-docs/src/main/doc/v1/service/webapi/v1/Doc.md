# 服务接口-ZT_DOC
## 接口说明
文档

## 接口清单
### 新建文档
#### 访问路径
/docs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 批量新建文档
#### 访问路径
/docs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | docdtos | List<[DocDTO](#DocDTO)> | 文档实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取文档
#### 访问路径
/docs/{doc_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 删除文档
#### 访问路径
/docs/{doc_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除文档
#### 访问路径
/docs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 文档主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新文档
#### 访问路径
/docs/{doc_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 批量更新文档
#### 访问路径
/docs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | docdtos | List<[DocDTO](#DocDTO)> | 文档实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据版本更新正文信息
#### 访问路径
/docs/{doc_id}/byversionupdatecontext

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 检查文档
#### 访问路径
/docs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 收藏
#### 访问路径
/docs/{doc_id}/collect

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 行为
#### 访问路径
/docs/{doc_id}/getdocstatus

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 仅收藏文档
#### 访问路径
/docs/{doc_id}/onlycollectdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 仅取消收藏文档
#### 访问路径
/docs/{doc_id}/onlyuncollectdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 保存文档
#### 访问路径
/docs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存文档
#### 访问路径
/docs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | docdtos | List<[DocDTO](#DocDTO)> | 文档实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 取消收藏
#### 访问路径
/docs/{doc_id}/uncollect

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doc_id | Long | 文档主键ID |
| 2 | docdto | [DocDTO](#DocDTO) | 文档实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocDTO](#DocDTO)：文档实体传输对象 |

### 获取文档库文档（子库）
#### 访问路径
/docs/fetchchilddoclibdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询文档库文档（子库）
#### 访问路径
/docs/searchchilddoclibdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/docs/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/docs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文档库文档
#### 访问路径
/docs/fetchdoclibanddoc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询文档库文档
#### 访问路径
/docs/searchdoclibanddoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文档库文档
#### 访问路径
/docs/fetchdoclibdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询文档库文档
#### 访问路径
/docs/searchdoclibdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文档库分类文档
#### 访问路径
/docs/fetchdocmoduledoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询文档库分类文档
#### 访问路径
/docs/searchdocmoduledoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文档统计
#### 访问路径
/docs/fetchdocstatus

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询文档统计
#### 访问路径
/docs/searchdocstatus

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文件夹文档（子目录）
#### 访问路径
/docs/fetchmoduledocchild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询文件夹文档（子目录）
#### 访问路径
/docs/searchmoduledocchild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/docs/fetchmyfavourite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/docs/searchmyfavourite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/docs/fetchmyfavouritesonlydoc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/docs/searchmyfavouritesonlydoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取子目录文档
#### 访问路径
/docs/fetchnotrootdoc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询子目录文档
#### 访问路径
/docs/searchnotrootdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根目录文档
#### 访问路径
/docs/fetchrootdoc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocDTO](#DocDTO)>：文档实体传输对象列表 |

### 查询根目录文档
#### 访问路径
/docs/searchrootdoc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocSearchContext](#DocSearchContext) | 文档查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocDTO](#DocDTO)>：文档实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### DocDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | groups | String | 允许 | 分组 |
| 2 | content | String | 允许 | 文档正文 |
| 3 | docqtype | String | 允许 | 文档查询类型 |
| 4 | editeddate | Timestamp | 允许 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | views | Integer | 允许 | views |
| 6 | version | Integer | 允许 | 版本号 |
| 7 | editedby | String | 允许 | 由谁更新 |
| 8 | id | Long | 不可 | 文档编号 |
| 9 | title | String | 不可 | 文档标题 |
| 10 | deleted | String | 允许 | 已删除 |
| 11 | recentupdatecnt | Integer | 允许 | 最近更新数量 |
| 12 | type | String | 允许 | 文档类型 |
| 13 | alldoccnt | Integer | 允许 | 所有文档数量 |
| 14 | addeddate | Timestamp | 允许 | 添加时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 15 | acl | String | 允许 | 权限 |
| 16 | mydoccnt | Integer | 允许 | 我的文档数量 |
| 17 | url | String | 允许 | 文档链接 |
| 18 | doccnt | Integer | 允许 | 文档数 |
| 19 | myfavouritecnt | Integer | 允许 | 我的收藏数量 |
| 20 | users | String | 允许 | 用户 |
| 21 | recentaddcnt | Integer | 允许 | 最近添加数量 |
| 22 | keywords | String | 允许 | 关键字 |
| 23 | isfavourites | String | 允许 | 是否收藏 |
| 24 | collector | String | 允许 | 收藏者 |
| 25 | addedby | String | 允许 | 由谁添加 |
| 26 | todayupdatecnt | Integer | 允许 | 今日更新数量 |
| 27 | files | String | 允许 | 附件 |
| 28 | productname | String | 允许 | 所属产品 |
| 29 | projectname | String | 允许 | 所属项目 |
| 30 | libname | String | 允许 | 所属文档库 |
| 31 | modulename | String | 允许 | 模块分类 |
| 32 | lib | Long | 允许 | 所属文档库 |
| 33 | project | Long | 允许 | 所属项目 |
| 34 | product | Long | 允许 | 所属产品 |
| 35 | module | Long | 允许 | 所属分类 |
| 36 | <动态属性> | Object | 允许 | 支持动态属性 |

#### DocSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_docqtype_eq | String | 允许 | 条件字段：docqtype<br>条件组合方式：`=` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | n_acl_eq | String | 允许 | 条件字段：acl<br>条件组合方式：`=` |
| 6 | n_isfavourites_eq | String | 允许 | 条件字段：isfavourites<br>条件组合方式：`=` |
| 7 | n_collector_like | String | 允许 | 条件字段：collector<br>条件组合方式：`%like%` |
| 8 | n_addedby_eq | String | 允许 | 条件字段：addedby<br>条件组合方式：`=` |
| 9 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 10 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 11 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 12 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 13 | n_libname_eq | String | 允许 | 条件字段：libname<br>条件组合方式：`=` |
| 14 | n_libname_like | String | 允许 | 条件字段：libname<br>条件组合方式：`%like%` |
| 15 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 16 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 17 | n_lib_eq | Long | 允许 | 条件字段：lib<br>条件组合方式：`=` |
| 18 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 19 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 20 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 21 | customcond | String | 允许 | 自定义查询条件 |
| 22 | customparams | String | 允许 | 自定义查询参数 |
| 23 | query | String | 允许 | 快速搜索 |
| 24 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 25 | page | int | 允许 | 当前页数<br>默认值0 |
| 26 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 27 | sort | String | 允许 | 排序 |
