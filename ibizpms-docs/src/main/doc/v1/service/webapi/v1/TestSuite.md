# 服务接口-ZT_TESTSUITE
## 接口说明
测试套件

## 接口清单
### 新建测试套件
#### 访问路径
/testsuites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 批量新建测试套件
#### 访问路径
/testsuites/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuitedtos | List<[TestSuiteDTO](#TestSuiteDTO)> | 测试套件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试套件
#### 访问路径
/testsuites/{testsuite_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuite_id | Long | 测试套件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 删除测试套件
#### 访问路径
/testsuites/{testsuite_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuite_id | Long | 测试套件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试套件
#### 访问路径
/testsuites/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试套件主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试套件
#### 访问路径
/testsuites/{testsuite_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuite_id | Long | 测试套件主键ID |
| 2 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 批量更新测试套件
#### 访问路径
/testsuites/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuitedtos | List<[TestSuiteDTO](#TestSuiteDTO)> | 测试套件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查测试套件
#### 访问路径
/testsuites/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 移动端测试套件计数器
#### 访问路径
/testsuites/{testsuite_id}/mobtestsuitecount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuite_id | Long | 测试套件主键ID |
| 2 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 保存测试套件
#### 访问路径
/testsuites/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试套件
#### 访问路径
/testsuites/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testsuitedtos | List<[TestSuiteDTO](#TestSuiteDTO)> | 测试套件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/testsuites/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/testsuites/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取公开套件
#### 访问路径
/testsuites/fetchpublictestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象列表 |

### 查询公开套件
#### 访问路径
/testsuites/searchpublictestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试套件
#### 访问路径
/products/{product_id}/testsuites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 根据批量建立测试套件
#### 访问路径
/products/{product_id}/testsuites/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuitedtos | List<[TestSuiteDTO](#TestSuiteDTO)> | 测试套件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试套件
#### 访问路径
/products/{product_id}/testsuites/{testsuite_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuite_id | Long | 测试套件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 根据删除测试套件
#### 访问路径
/products/{product_id}/testsuites/{testsuite_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuite_id | Long | 测试套件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试套件
#### 访问路径
/products/{product_id}/testsuites/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 测试套件主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试套件
#### 访问路径
/products/{product_id}/testsuites/{testsuite_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuite_id | Long | 测试套件主键ID |
| 3 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 根据批量更新测试套件
#### 访问路径
/products/{product_id}/testsuites/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuitedtos | List<[TestSuiteDTO](#TestSuiteDTO)> | 测试套件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据检查测试套件
#### 访问路径
/products/{product_id}/testsuites/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 移动端测试套件计数器
#### 访问路径
/products/{product_id}/testsuites/{testsuite_id}/mobtestsuitecount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuite_id | Long | 测试套件主键ID |
| 3 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestSuiteDTO](#TestSuiteDTO)：测试套件实体传输对象 |

### 根据保存测试套件
#### 访问路径
/products/{product_id}/testsuites/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuitedto | [TestSuiteDTO](#TestSuiteDTO) | 测试套件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试套件
#### 访问路径
/products/{product_id}/testsuites/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testsuitedtos | List<[TestSuiteDTO](#TestSuiteDTO)> | 测试套件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/testsuites/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/testsuites/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取公开套件
#### 访问路径
/products/{product_id}/testsuites/fetchpublictestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象列表 |

### 根据查询公开套件
#### 访问路径
/products/{product_id}/testsuites/searchpublictestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestSuiteSearchContext](#TestSuiteSearchContext) | 测试套件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestSuiteDTO](#TestSuiteDTO)>：测试套件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TestSuiteDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | name | String | 不可 | 名称 |
| 2 | addeddate | Timestamp | 允许 | 创建时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 3 | id | Long | 不可 | 编号 |
| 4 | deleted | String | 允许 | 已删除 |
| 5 | lasteditedby | String | 允许 | 最后编辑人 |
| 6 | type | String | 允许 | 类型 |
| 7 | lastediteddate | Timestamp | 允许 | 最后编辑时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 8 | addedby | String | 允许 | 由谁创建 |
| 9 | casecnt | Integer | 允许 | 用例数 |
| 10 | desc | String | 允许 | 描述 |
| 11 | product | Long | 允许 | 所属产品 |
| 12 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TestSuiteSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 4 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 5 | customcond | String | 允许 | 自定义查询条件 |
| 6 | customparams | String | 允许 | 自定义查询参数 |
| 7 | query | String | 允许 | 快速搜索 |
| 8 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 9 | page | int | 允许 | 当前页数<br>默认值0 |
| 10 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 11 | sort | String | 允许 | 排序 |
