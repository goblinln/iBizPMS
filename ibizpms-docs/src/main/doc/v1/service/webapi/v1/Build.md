# 服务接口-ZT_BUILD
## 接口说明
版本

## 接口清单
### 新建版本
#### 访问路径
/builds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 批量新建版本
#### 访问路径
/builds/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新版本
#### 访问路径
/builds/{build_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | build_id | Long | 版本主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 批量更新版本
#### 访问路径
/builds/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除版本
#### 访问路径
/builds/{build_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | build_id | Long | 版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除版本
#### 访问路径
/builds/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 版本主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取版本
#### 访问路径
/builds/{build_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | build_id | Long | 版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 检查版本
#### 访问路径
/builds/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联需求
#### 访问路径
/builds/{build_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | build_id | Long | 版本主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 移动端项目版本计数器
#### 访问路径
/builds/{build_id}/mobprojectbuildcounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | build_id | Long | 版本主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 一键发布
#### 访问路径
/builds/{build_id}/oneclickrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | build_id | Long | 版本主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 保存版本
#### 访问路径
/builds/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存版本
#### 访问路径
/builds/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取Bug产品版本
#### 访问路径
/builds/fetchbugproductbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 查询Bug产品版本
#### 访问路径
/builds/searchbugproductbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品版本
#### 访问路径
/builds/fetchcurproduct

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 查询产品版本
#### 访问路径
/builds/searchcurproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/builds/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/builds/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试版本
#### 访问路径
/builds/fetchtestbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 查询测试版本
#### 访问路径
/builds/searchtestbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试轮次
#### 访问路径
/builds/fetchtestrounds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 查询测试轮次
#### 访问路径
/builds/searchtestrounds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取更新日志
#### 访问路径
/builds/fetchupdatelog

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 查询更新日志
#### 访问路径
/builds/searchupdatelog

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立版本
#### 访问路径
/products/{product_id}/builds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据批量建立版本
#### 访问路径
/products/{product_id}/builds/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新版本
#### 访问路径
/products/{product_id}/builds/{build_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据批量更新版本
#### 访问路径
/products/{product_id}/builds/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除版本
#### 访问路径
/products/{product_id}/builds/{build_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | build_id | Long | 版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除版本
#### 访问路径
/products/{product_id}/builds/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 版本主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取版本
#### 访问路径
/products/{product_id}/builds/{build_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | build_id | Long | 版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据检查版本
#### 访问路径
/products/{product_id}/builds/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联需求
#### 访问路径
/products/{product_id}/builds/{build_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 移动端项目版本计数器
#### 访问路径
/products/{product_id}/builds/{build_id}/mobprojectbuildcounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 一键发布
#### 访问路径
/products/{product_id}/builds/{build_id}/oneclickrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据保存版本
#### 访问路径
/products/{product_id}/builds/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存版本
#### 访问路径
/products/{product_id}/builds/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取Bug产品版本
#### 访问路径
/products/{product_id}/builds/fetchbugproductbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询Bug产品版本
#### 访问路径
/products/{product_id}/builds/searchbugproductbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取产品版本
#### 访问路径
/products/{product_id}/builds/fetchcurproduct

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询产品版本
#### 访问路径
/products/{product_id}/builds/searchcurproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/builds/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/builds/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试版本
#### 访问路径
/products/{product_id}/builds/fetchtestbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询测试版本
#### 访问路径
/products/{product_id}/builds/searchtestbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试轮次
#### 访问路径
/products/{product_id}/builds/fetchtestrounds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询测试轮次
#### 访问路径
/products/{product_id}/builds/searchtestrounds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取更新日志
#### 访问路径
/products/{product_id}/builds/fetchupdatelog

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询更新日志
#### 访问路径
/products/{product_id}/builds/searchupdatelog

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立版本
#### 访问路径
/projects/{project_id}/builds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据批量建立版本
#### 访问路径
/projects/{project_id}/builds/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新版本
#### 访问路径
/projects/{project_id}/builds/{build_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据批量更新版本
#### 访问路径
/projects/{project_id}/builds/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除版本
#### 访问路径
/projects/{project_id}/builds/{build_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | build_id | Long | 版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除版本
#### 访问路径
/projects/{project_id}/builds/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 版本主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取版本
#### 访问路径
/projects/{project_id}/builds/{build_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | build_id | Long | 版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据检查版本
#### 访问路径
/projects/{project_id}/builds/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联需求
#### 访问路径
/projects/{project_id}/builds/{build_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 移动端项目版本计数器
#### 访问路径
/projects/{project_id}/builds/{build_id}/mobprojectbuildcounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 一键发布
#### 访问路径
/projects/{project_id}/builds/{build_id}/oneclickrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | build_id | Long | 版本主键ID |
| 3 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BuildDTO](#BuildDTO)：版本实体传输对象 |

### 根据保存版本
#### 访问路径
/projects/{project_id}/builds/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | builddto | [BuildDTO](#BuildDTO) | 版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存版本
#### 访问路径
/projects/{project_id}/builds/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | builddtos | List<[BuildDTO](#BuildDTO)> | 版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取Bug产品版本
#### 访问路径
/projects/{project_id}/builds/fetchbugproductbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询Bug产品版本
#### 访问路径
/projects/{project_id}/builds/searchbugproductbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取产品版本
#### 访问路径
/projects/{project_id}/builds/fetchcurproduct

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询产品版本
#### 访问路径
/projects/{project_id}/builds/searchcurproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/builds/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/builds/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试版本
#### 访问路径
/projects/{project_id}/builds/fetchtestbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询测试版本
#### 访问路径
/projects/{project_id}/builds/searchtestbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试轮次
#### 访问路径
/projects/{project_id}/builds/fetchtestrounds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询测试轮次
#### 访问路径
/projects/{project_id}/builds/searchtestrounds

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取更新日志
#### 访问路径
/projects/{project_id}/builds/fetchupdatelog

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BuildDTO](#BuildDTO)>：版本实体传输对象列表 |

### 根据查询更新日志
#### 访问路径
/projects/{project_id}/builds/searchupdatelog

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BuildSearchContext](#BuildSearchContext) | 版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BuildDTO](#BuildDTO)>：版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### BuildDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ids | String | 允许 | Bug版本健值 |
| 2 | name | String | 不可 | 名称编号 |
| 3 | backgroundid | String | 允许 | 后台体系 |
| 4 | builder | String | 允许 | 构建者 |
| 5 | files | String | 允许 | 附件 |
| 6 | releasetype | String | 允许 | 运行模式 |
| 7 | builderpk | String | 允许 | 构建者（选择） |
| 8 | rebuild | Integer | 允许 | 重新构建 |
| 9 | desc | String | 允许 | 描述 |
| 10 | id | Long | 不可 | id |
| 11 | deleted | String | 允许 | 已删除 |
| 12 | sqlid | String | 允许 | 运行数据库 |
| 13 | scmpath | String | 允许 | 源代码地址 |
| 14 | filepath | String | 允许 | 下载地址 |
| 15 | createbugcnt | String | 允许 | 产生的bug |
| 16 | stories | String | 允许 | 完成的需求 |
| 17 | bugs | String | 允许 | 解决的Bug |
| 18 | frontapplication | String | 允许 | 系统应用 |
| 19 | noticeusers | String | 允许 | 消息通知用户 |
| 20 | date | Timestamp | 允许 | 打包日期<br>时间格式：yyyy-MM-dd |
| 21 | productname | String | 允许 | 产品名称 |
| 22 | product | Long | 允许 | 产品 |
| 23 | branch | Long | 允许 | 平台/分支 |
| 24 | project | Long | 允许 | 所属项目 |
| 25 | <动态属性> | Object | 允许 | 支持动态属性 |

#### BuildSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 2 | n_backgroundid_eq | String | 允许 | 条件字段：backgroundid<br>条件组合方式：`=` |
| 3 | n_releasetype_eq | String | 允许 | 条件字段：releasetype<br>条件组合方式：`=` |
| 4 | n_rebuild_eq | Integer | 允许 | 条件字段：rebuild<br>条件组合方式：`=` |
| 5 | n_sqlid_eq | String | 允许 | 条件字段：sqlid<br>条件组合方式：`=` |
| 6 | n_frontapplication_eq | String | 允许 | 条件字段：frontapplication<br>条件组合方式：`=` |
| 7 | n_date_ltandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 8 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 9 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 10 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 11 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 12 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 13 | customcond | String | 允许 | 自定义查询条件 |
| 14 | customparams | String | 允许 | 自定义查询参数 |
| 15 | query | String | 允许 | 快速搜索 |
| 16 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 17 | page | int | 允许 | 当前页数<br>默认值0 |
| 18 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 19 | sort | String | 允许 | 排序 |
