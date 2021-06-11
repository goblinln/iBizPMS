# 服务接口-IBZ_PRODUCTMODULE
## 接口说明
需求模块

## 接口清单
### 新建需求模块
#### 访问路径
/productmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 批量新建需求模块
#### 访问路径
/productmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmoduledtos | List<[ProductModuleDTO](#ProductModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取需求模块
#### 访问路径
/productmodules/{productmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmodule_id | Long | 需求模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 删除需求模块
#### 访问路径
/productmodules/{productmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmodule_id | Long | 需求模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除需求模块
#### 访问路径
/productmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 需求模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新需求模块
#### 访问路径
/productmodules/{productmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmodule_id | Long | 需求模块主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 批量更新需求模块
#### 访问路径
/productmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmoduledtos | List<[ProductModuleDTO](#ProductModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查需求模块
#### 访问路径
/productmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/productmodules/{productmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmodule_id | Long | 需求模块主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 删除模块
#### 访问路径
/productmodules/{productmodule_id}/removemodule

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmodule_id | Long | 需求模块主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 保存需求模块
#### 访问路径
/productmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存需求模块
#### 访问路径
/productmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmoduledtos | List<[ProductModuleDTO](#ProductModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 同步Ibz平台模块
#### 访问路径
/productmodules/{productmodule_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productmodule_id | Long | 需求模块主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 获取BYPATH
#### 访问路径
/productmodules/fetchbypath

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 查询BYPATH
#### 访问路径
/productmodules/searchbypath

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/productmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/productmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取父模块
#### 访问路径
/productmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 查询父模块
#### 访问路径
/productmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块
#### 访问路径
/productmodules/fetchroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 查询根模块
#### 访问路径
/productmodules/searchroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块_无分支
#### 访问路径
/productmodules/fetchroot_nobranch

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 查询根模块_无分支
#### 访问路径
/productmodules/searchroot_nobranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取StoryModule
#### 访问路径
/productmodules/fetchstorymodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 查询StoryModule
#### 访问路径
/productmodules/searchstorymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立需求模块
#### 访问路径
/products/{product_id}/productmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 根据批量建立需求模块
#### 访问路径
/products/{product_id}/productmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmoduledtos | List<[ProductModuleDTO](#ProductModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取需求模块
#### 访问路径
/products/{product_id}/productmodules/{productmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmodule_id | Long | 需求模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 根据删除需求模块
#### 访问路径
/products/{product_id}/productmodules/{productmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmodule_id | Long | 需求模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除需求模块
#### 访问路径
/products/{product_id}/productmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 需求模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新需求模块
#### 访问路径
/products/{product_id}/productmodules/{productmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmodule_id | Long | 需求模块主键ID |
| 3 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 根据批量更新需求模块
#### 访问路径
/products/{product_id}/productmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmoduledtos | List<[ProductModuleDTO](#ProductModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查需求模块
#### 访问路径
/products/{product_id}/productmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/products/{product_id}/productmodules/{productmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmodule_id | Long | 需求模块主键ID |
| 3 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 删除模块
#### 访问路径
/products/{product_id}/productmodules/{productmodule_id}/removemodule

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmodule_id | Long | 需求模块主键ID |
| 3 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 根据保存需求模块
#### 访问路径
/products/{product_id}/productmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存需求模块
#### 访问路径
/products/{product_id}/productmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmoduledtos | List<[ProductModuleDTO](#ProductModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 同步Ibz平台模块
#### 访问路径
/products/{product_id}/productmodules/{productmodule_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productmodule_id | Long | 需求模块主键ID |
| 3 | productmoduledto | [ProductModuleDTO](#ProductModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductModuleDTO](#ProductModuleDTO)：需求模块实体传输对象 |

### 根据获取BYPATH
#### 访问路径
/products/{product_id}/productmodules/fetchbypath

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 根据查询BYPATH
#### 访问路径
/products/{product_id}/productmodules/searchbypath

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取父模块
#### 访问路径
/products/{product_id}/productmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 根据查询父模块
#### 访问路径
/products/{product_id}/productmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块
#### 访问路径
/products/{product_id}/productmodules/fetchroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 根据查询根模块
#### 访问路径
/products/{product_id}/productmodules/searchroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块_无分支
#### 访问路径
/products/{product_id}/productmodules/fetchroot_nobranch

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 根据查询根模块_无分支
#### 访问路径
/products/{product_id}/productmodules/searchroot_nobranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取StoryModule
#### 访问路径
/products/{product_id}/productmodules/fetchstorymodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象列表 |

### 根据查询StoryModule
#### 访问路径
/products/{product_id}/productmodules/searchstorymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductModuleSearchContext](#ProductModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductModuleDTO](#ProductModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProductModuleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | path | String | 允许 | path |
| 2 | orderpk | String | 允许 | 数据选择排序 |
| 3 | deleted | String | 允许 | 逻辑删除标志 |
| 4 | name | String | 不可 | 名称 |
| 5 | branch | Integer | 允许 | branch |
| 6 | ibizshort | String | 允许 | 简称 |
| 7 | order | Integer | 允许 | 排序值 |
| 8 | grade | Integer | 允许 | grade |
| 9 | type | String | 允许 | 类型（story） |
| 10 | owner | String | 允许 | owner |
| 11 | isleaf | String | 允许 | 叶子模块 |
| 12 | id | Long | 不可 | id |
| 13 | collector | String | 允许 | collector |
| 14 | rootname | String | 允许 | 所属产品 |
| 15 | parentname | String | 允许 | 上级模块 |
| 16 | root | Long | 允许 | 产品 |
| 17 | parent | Long | 允许 | id |
| 18 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProductModuleSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 2 | n_branch_eq | Integer | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 3 | n_branch_noteq | Integer | 允许 | 条件字段：branch<br>条件组合方式：`!=`或者`<>` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 6 | n_rootname_eq | String | 允许 | 条件字段：rootname<br>条件组合方式：`=` |
| 7 | n_rootname_like | String | 允许 | 条件字段：rootname<br>条件组合方式：`%like%` |
| 8 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 9 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 10 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 11 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 12 | customcond | String | 允许 | 自定义查询条件 |
| 13 | customparams | String | 允许 | 自定义查询参数 |
| 14 | query | String | 允许 | 快速搜索 |
| 15 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 16 | page | int | 允许 | 当前页数<br>默认值0 |
| 17 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 18 | sort | String | 允许 | 排序 |
