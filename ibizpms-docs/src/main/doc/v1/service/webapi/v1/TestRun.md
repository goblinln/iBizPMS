# 服务接口-ZT_TESTRUN
## 接口说明
测试运行

## 接口清单
### 新建测试运行
#### 访问路径
/testruns

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 批量新建测试运行
#### 访问路径
/testruns/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试运行
#### 访问路径
/testruns/{testrun_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrun_id | Long | 测试运行主键ID |
| 2 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 批量更新测试运行
#### 访问路径
/testruns/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除测试运行
#### 访问路径
/testruns/{testrun_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试运行
#### 访问路径
/testruns/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试运行主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试运行
#### 访问路径
/testruns/{testrun_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 检查测试运行
#### 访问路径
/testruns/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存测试运行
#### 访问路径
/testruns/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试运行
#### 访问路径
/testruns/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/testruns/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/testruns/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据批量建立测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrun_id | Long | 测试运行主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据批量更新测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | ids | List<Long> | 测试运行主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据检查测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试运行
#### 访问路径
/testtasks/{testtask_id}/testruns/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/testtasks/{testtask_id}/testruns/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/testtasks/{testtask_id}/testruns/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据批量建立测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrun_id | Long | 测试运行主键ID |
| 4 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据批量更新测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | ids | List<Long> | 测试运行主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据检查测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试运行
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/testruns/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据批量建立测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrun_id | Long | 测试运行主键ID |
| 4 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据批量更新测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | ids | List<Long> | 测试运行主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrun_id | Long | 测试运行主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestRunDTO](#TestRunDTO)：测试运行实体传输对象 |

### 根据检查测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundto | [TestRunDTO](#TestRunDTO) | 测试运行实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试运行
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testrundtos | List<[TestRunDTO](#TestRunDTO)> | 测试运行实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/testruns/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |/r/n| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | context | [TestRunSearchContext](#TestRunSearchContext) | 测试运行查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestRunDTO](#TestRunDTO)>：测试运行实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TestRunDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | lastrunresult | String | 允许 | 结果 |
| 2 | lastrundate | Timestamp | 允许 | 最后执行时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 3 | assignedto | String | 允许 | 指派给 |
| 4 | lastrunner | String | 允许 | 最后执行人 |
| 5 | status | String | 允许 | 当前状态 |
| 6 | id | Long | 不可 | 编号 |
| 7 | version | Integer | 允许 | 用例版本 |
| 8 | ibizcase | Long | 允许 | 测试用例 |
| 9 | task | Long | 允许 | 测试单 |
| 10 | createby | String | 允许 | 由谁创建 |
| 11 | updateby | String | 允许 | 由谁更新 |
| 12 | org | String | 允许 | 归属组织 |
| 13 | dept | String | 允许 | 归属部门 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TestRunSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_case_eq | Long | 允许 | 条件字段：case<br>条件组合方式：`=` |
| 2 | n_task_eq | Long | 允许 | 条件字段：task<br>条件组合方式：`=` |
| 3 | customcond | String | 允许 | 自定义查询条件 |
| 4 | customparams | String | 允许 | 自定义查询参数 |
| 5 | query | String | 允许 | 快速搜索 |
| 6 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 7 | page | int | 允许 | 当前页数<br>默认值0 |
| 8 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 9 | sort | String | 允许 | 排序 |
