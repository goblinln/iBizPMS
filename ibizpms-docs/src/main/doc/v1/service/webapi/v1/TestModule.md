# 服务接口-IBZ_TESTMODULE
## 接口说明
测试模块

## 接口清单
### 新建测试模块
#### 访问路径
/testmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 批量新建测试模块
#### 访问路径
/testmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmoduledtos | List<[TestModuleDTO](#TestModuleDTO)> | 测试模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试模块
#### 访问路径
/testmodules/{testmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmodule_id | Long | 测试模块主键ID |
| 2 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 批量更新测试模块
#### 访问路径
/testmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmoduledtos | List<[TestModuleDTO](#TestModuleDTO)> | 测试模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除测试模块
#### 访问路径
/testmodules/{testmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmodule_id | Long | 测试模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试模块
#### 访问路径
/testmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试模块
#### 访问路径
/testmodules/{testmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmodule_id | Long | 测试模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 检查测试模块
#### 访问路径
/testmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/testmodules/{testmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmodule_id | Long | 测试模块主键ID |
| 2 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 删除模块
#### 访问路径
/testmodules/{testmodule_id}/removemodule

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmodule_id | Long | 测试模块主键ID |
| 2 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 保存测试模块
#### 访问路径
/testmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试模块
#### 访问路径
/testmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testmoduledtos | List<[TestModuleDTO](#TestModuleDTO)> | 测试模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取BYPATH
#### 访问路径
/testmodules/fetchbypath

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 查询BYPATH
#### 访问路径
/testmodules/searchbypath

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/testmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/testmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取父模块
#### 访问路径
/testmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 查询父模块
#### 访问路径
/testmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块
#### 访问路径
/testmodules/fetchroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 查询根模块
#### 访问路径
/testmodules/searchroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根模块_无分支
#### 访问路径
/testmodules/fetchroot_nobranch

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 查询根模块_无分支
#### 访问路径
/testmodules/searchroot_nobranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取TestModule
#### 访问路径
/testmodules/fetchtestmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 查询TestModule
#### 访问路径
/testmodules/searchtestmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试模块
#### 访问路径
/products/{product_id}/testmodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 根据批量建立测试模块
#### 访问路径
/products/{product_id}/testmodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmoduledtos | List<[TestModuleDTO](#TestModuleDTO)> | 测试模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试模块
#### 访问路径
/products/{product_id}/testmodules/{testmodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmodule_id | Long | 测试模块主键ID |
| 3 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 根据批量更新测试模块
#### 访问路径
/products/{product_id}/testmodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmoduledtos | List<[TestModuleDTO](#TestModuleDTO)> | 测试模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试模块
#### 访问路径
/products/{product_id}/testmodules/{testmodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmodule_id | Long | 测试模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试模块
#### 访问路径
/products/{product_id}/testmodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 测试模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试模块
#### 访问路径
/products/{product_id}/testmodules/{testmodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmodule_id | Long | 测试模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 根据检查测试模块
#### 访问路径
/products/{product_id}/testmodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 重建模块路径
#### 访问路径
/products/{product_id}/testmodules/{testmodule_id}/fix

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmodule_id | Long | 测试模块主键ID |
| 3 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 删除模块
#### 访问路径
/products/{product_id}/testmodules/{testmodule_id}/removemodule

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmodule_id | Long | 测试模块主键ID |
| 3 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestModuleDTO](#TestModuleDTO)：测试模块实体传输对象 |

### 根据保存测试模块
#### 访问路径
/products/{product_id}/testmodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmoduledto | [TestModuleDTO](#TestModuleDTO) | 测试模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试模块
#### 访问路径
/products/{product_id}/testmodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testmoduledtos | List<[TestModuleDTO](#TestModuleDTO)> | 测试模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取BYPATH
#### 访问路径
/products/{product_id}/testmodules/fetchbypath

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 根据查询BYPATH
#### 访问路径
/products/{product_id}/testmodules/searchbypath

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/testmodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/testmodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取父模块
#### 访问路径
/products/{product_id}/testmodules/fetchparentmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 根据查询父模块
#### 访问路径
/products/{product_id}/testmodules/searchparentmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块
#### 访问路径
/products/{product_id}/testmodules/fetchroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 根据查询根模块
#### 访问路径
/products/{product_id}/testmodules/searchroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取根模块_无分支
#### 访问路径
/products/{product_id}/testmodules/fetchroot_nobranch

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 根据查询根模块_无分支
#### 访问路径
/products/{product_id}/testmodules/searchroot_nobranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取TestModule
#### 访问路径
/products/{product_id}/testmodules/fetchtestmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象列表 |

### 根据查询TestModule
#### 访问路径
/products/{product_id}/testmodules/searchtestmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestModuleSearchContext](#TestModuleSearchContext) | 测试模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestModuleDTO](#TestModuleDTO)>：测试模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TestModuleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | type | String | 允许 | 类型（story） |
| 2 | path | String | 允许 | path |
| 3 | owner | String | 允许 | owner |
| 4 | order | Integer | 允许 | 排序值 |
| 5 | deleted | String | 允许 | 逻辑删除标志 |
| 6 | branch | Integer | 允许 | branch |
| 7 | collector | String | 允许 | collector |
| 8 | id | Long | 不可 | id |
| 9 | isleaf | String | 允许 | 叶子模块 |
| 10 | name | String | 不可 | 名称 |
| 11 | ibizshort | String | 允许 | 简称 |
| 12 | grade | Integer | 允许 | grade |
| 13 | parentname | String | 允许 | 上级模块 |
| 14 | rootname | String | 允许 | 测试 |
| 15 | root | Long | 允许 | 编号 |
| 16 | parent | Long | 允许 | id |
| 17 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TestModuleSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_branch_eq | Integer | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 2 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 3 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 4 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 5 | n_rootname_eq | String | 允许 | 条件字段：rootname<br>条件组合方式：`=` |
| 6 | n_rootname_like | String | 允许 | 条件字段：rootname<br>条件组合方式：`%like%` |
| 7 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 8 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
