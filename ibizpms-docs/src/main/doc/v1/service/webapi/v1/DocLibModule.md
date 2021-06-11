# 服务接口-IBZ_DOCLIBMODULE
## 接口说明
文档库分类

## 接口清单
### 新建文档库分类
#### 访问路径
/doclibmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 批量新建文档库分类
#### 访问路径
/doclibmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmoduledtos | List<[DocLibModuleDTO](#DocLibModuleDTO)> | 文档库分类实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取文档库分类
#### 访问路径
/doclibmodules/{doclibmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 删除文档库分类
#### 访问路径
/doclibmodules/{doclibmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除文档库分类
#### 访问路径
/doclibmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 文档库分类主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新文档库分类
#### 访问路径
/doclibmodules/{doclibmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |
| 2 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 批量更新文档库分类
#### 访问路径
/doclibmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmoduledtos | List<[DocLibModuleDTO](#DocLibModuleDTO)> | 文档库分类实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查文档库分类
#### 访问路径
/doclibmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 收藏
#### 访问路径
/doclibmodules/{doclibmodule_id}/collect

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |
| 2 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 取消收藏
#### 访问路径
/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |
| 2 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 收藏
#### 访问路径
/doclibmodules/{doclibmodule_id}/doclibmodulefavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |
| 2 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 重建模块路径
#### 访问路径
/doclibmodules/{doclibmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |
| 2 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 保存文档库分类
#### 访问路径
/doclibmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存文档库分类
#### 访问路径
/doclibmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmoduledtos | List<[DocLibModuleDTO](#DocLibModuleDTO)> | 文档库分类实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 取消收藏
#### 访问路径
/doclibmodules/{doclibmodule_id}/uncollect

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | doclibmodule_id | Long | 文档库分类主键ID |
| 2 | doclibmoduledto | [DocLibModuleDTO](#DocLibModuleDTO) | 文档库分类实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [DocLibModuleDTO](#DocLibModuleDTO)：文档库分类实体传输对象 |

### 获取自定义文档库的模块
#### 访问路径
/doclibmodules/fetchalldoclibmodule_custom

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询自定义文档库的模块
#### 访问路径
/doclibmodules/searchalldoclibmodule_custom

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所有文档库模块
#### 访问路径
/doclibmodules/fetchalldoclibmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询所有文档库模块
#### 访问路径
/doclibmodules/searchalldoclibmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取子模块目录
#### 访问路径
/doclibmodules/fetchchildmodulebyparent

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询子模块目录
#### 访问路径
/doclibmodules/searchchildmodulebyparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取文档库分类子模块
#### 访问路径
/doclibmodules/fetchchildmodulebyrealparent

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询文档库分类子模块
#### 访问路径
/doclibmodules/searchchildmodulebyrealparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/doclibmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询数据集
#### 访问路径
/doclibmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/doclibmodules/fetchmyfavourites

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/doclibmodules/searchmyfavourites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取父集合
#### 访问路径
/doclibmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询父集合
#### 访问路径
/doclibmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所有根模块目录
#### 访问路径
/doclibmodules/fetchrootmodulemulu

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询所有根模块目录
#### 访问路径
/doclibmodules/searchrootmodulemulu

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块目录
#### 访问路径
/doclibmodules/fetchrootmodulemulubyroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询根模块目录
#### 访问路径
/doclibmodules/searchrootmodulemulubyroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块目录动态
#### 访问路径
/doclibmodules/fetchrootmodulemulubysrfparentkey

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象列表 |

### 查询根模块目录动态
#### 访问路径
/doclibmodules/searchrootmodulemulubysrfparentkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [DocLibModuleSearchContext](#DocLibModuleSearchContext) | 文档库分类查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[DocLibModuleDTO](#DocLibModuleDTO)>：文档库分类实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### DocLibModuleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | isfavourites | String | 允许 | 是否已收藏 |
| 2 | grade | Integer | 允许 | grade |
| 3 | path | String | 允许 | path |
| 4 | order | Integer | 允许 | 排序值 |
| 5 | name | String | 不可 | 名称 |
| 6 | docqtype | String | 允许 | 查询类型 |
| 7 | owner | String | 允许 | owner |
| 8 | branch | Integer | 允许 | branch |
| 9 | isleaf | String | 允许 | 叶子模块 |
| 10 | type | String | 允许 | 类型 |
| 11 | ibizshort | String | 允许 | 简称 |
| 12 | doccnt | Integer | 允许 | 文档数 |
| 13 | collector | String | 允许 | collector |
| 14 | id | Long | 不可 | id |
| 15 | deleted | String | 允许 | 逻辑删除标志 |
| 16 | modulename | String | 允许 | 上级模块 |
| 17 | doclibname | String | 允许 | 所属文档库 |
| 18 | parent | Long | 允许 | id |
| 19 | root | Long | 允许 | 编号 |
| 20 | <动态属性> | Object | 允许 | 支持动态属性 |

#### DocLibModuleSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_isfavourites_eq | String | 允许 | 条件字段：isfavourites<br>条件组合方式：`=` |
| 2 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 3 | n_docqtype_eq | String | 允许 | 条件字段：docqtype<br>条件组合方式：`=` |
| 4 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 5 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 6 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 7 | n_doclibname_eq | String | 允许 | 条件字段：doclibname<br>条件组合方式：`=` |
| 8 | n_doclibname_like | String | 允许 | 条件字段：doclibname<br>条件组合方式：`%like%` |
| 9 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 10 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 11 | customcond | String | 允许 | 自定义查询条件 |
| 12 | customparams | String | 允许 | 自定义查询参数 |
| 13 | query | String | 允许 | 快速搜索 |
| 14 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 15 | page | int | 允许 | 当前页数<br>默认值0 |
| 16 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 17 | sort | String | 允许 | 排序 |
