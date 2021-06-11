# 服务接口-ZT_TESTTASK
## 接口说明
测试版本

## 接口清单
### 新建测试版本
#### 访问路径
/testtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 批量新建测试版本
#### 访问路径
/testtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试版本
#### 访问路径
/testtasks/{testtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 删除测试版本
#### 访问路径
/testtasks/{testtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试版本
#### 访问路径
/testtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试版本主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试版本
#### 访问路径
/testtasks/{testtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 批量更新测试版本
#### 访问路径
/testtasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/testtasks/{testtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 阻塞
#### 访问路径
/testtasks/{testtask_id}/block

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 检查测试版本
#### 访问路径
/testtasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/testtasks/{testtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 关联测试用例
#### 访问路径
/testtasks/{testtask_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 移动端测试版本计数器
#### 访问路径
/testtasks/{testtask_id}/mobtesttaskcounter

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 保存测试版本
#### 访问路径
/testtasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试版本
#### 访问路径
/testtasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 开始
#### 访问路径
/testtasks/{testtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 关联测试用例
#### 访问路径
/testtasks/{testtask_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testtask_id | Long | 测试版本主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 获取DEFAULT
#### 访问路径
/testtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/testtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的测试单
#### 访问路径
/testtasks/fetchmytesttaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象列表 |

### 查询我的测试单
#### 访问路径
/testtasks/searchmytesttaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试版本
#### 访问路径
/products/{product_id}/testtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据批量建立测试版本
#### 访问路径
/products/{product_id}/testtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试版本
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据删除测试版本
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试版本
#### 访问路径
/products/{product_id}/testtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 测试版本主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试版本
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据批量更新测试版本
#### 访问路径
/products/{product_id}/testtasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 阻塞
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/block

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据检查测试版本
#### 访问路径
/products/{product_id}/testtasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 关联测试用例
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 移动端测试版本计数器
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/mobtesttaskcounter

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据保存测试版本
#### 访问路径
/products/{product_id}/testtasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试版本
#### 访问路径
/products/{product_id}/testtasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 开始
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 关联测试用例
#### 访问路径
/products/{product_id}/testtasks/{testtask_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/testtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/testtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的测试单
#### 访问路径
/products/{product_id}/testtasks/fetchmytesttaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象列表 |

### 根据查询我的测试单
#### 访问路径
/products/{product_id}/testtasks/searchmytesttaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试版本
#### 访问路径
/projects/{project_id}/testtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据批量建立测试版本
#### 访问路径
/projects/{project_id}/testtasks/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试版本
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据删除测试版本
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试版本
#### 访问路径
/projects/{project_id}/testtasks/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 测试版本主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试版本
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据批量更新测试版本
#### 访问路径
/projects/{project_id}/testtasks/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 阻塞
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/block

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据检查测试版本
#### 访问路径
/projects/{project_id}/testtasks/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 关联测试用例
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 移动端测试版本计数器
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/mobtesttaskcounter

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据保存测试版本
#### 访问路径
/projects/{project_id}/testtasks/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试版本
#### 访问路径
/projects/{project_id}/testtasks/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtaskdtos | List<[TestTaskDTO](#TestTaskDTO)> | 测试版本实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 开始
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/start

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 关联测试用例
#### 访问路径
/projects/{project_id}/testtasks/{testtask_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testtask_id | Long | 测试版本主键ID |
| 3 | testtaskdto | [TestTaskDTO](#TestTaskDTO) | 测试版本实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestTaskDTO](#TestTaskDTO)：测试版本实体传输对象 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/testtasks/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/testtasks/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的测试单
#### 访问路径
/projects/{project_id}/testtasks/fetchmytesttaskpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象列表 |

### 根据查询我的测试单
#### 访问路径
/projects/{project_id}/testtasks/searchmytesttaskpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TestTaskSearchContext](#TestTaskSearchContext) | 测试版本查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestTaskDTO](#TestTaskDTO)>：测试版本实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TestTaskDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | end | Timestamp | 允许 | 结束日期<br>时间格式：yyyy-MM-dd |
| 2 | begin | Timestamp | 允许 | 开始日期<br>时间格式：yyyy-MM-dd |
| 3 | ownerpk | String | 允许 | 负责人（选择） |
| 4 | mailto | String | 允许 | 抄送给 |
| 5 | casecnt | Integer | 允许 | 用例数 |
| 6 | mailtopk | String | 允许 | 抄送给 |
| 7 | pri | Integer | 允许 | 优先级 |
| 8 | comment | String | 允许 | 备注 |
| 9 | substatus | String | 允许 | 子状态 |
| 10 | report | String | 允许 | report |
| 11 | desc | String | 允许 | 描述 |
| 12 | id | Long | 不可 | 编号 |
| 13 | status | String | 允许 | 当前状态 |
| 14 | mailtoconact | String | 允许 | 联系人 |
| 15 | owner | String | 允许 | 负责人 |
| 16 | deleted | String | 允许 | 已删除 |
| 17 | auto | String | 允许 | auto |
| 18 | name | String | 不可 | 名称 |
| 19 | buildname | String | 允许 | 版本 |
| 20 | productname | String | 允许 | 产品 |
| 21 | projecttname | String | 允许 | 项目 |
| 22 | product | Long | 允许 | 所属产品 |
| 23 | build | Long | 允许 | 版本 |
| 24 | project | Long | 允许 | 所属项目 |
| 25 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TestTaskSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 4 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 5 | n_owner_eq | String | 允许 | 条件字段：owner<br>条件组合方式：`=` |
| 6 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 7 | n_buildname_eq | String | 允许 | 条件字段：buildname<br>条件组合方式：`=` |
| 8 | n_buildname_like | String | 允许 | 条件字段：buildname<br>条件组合方式：`%like%` |
| 9 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 10 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 11 | n_projecttname_eq | String | 允许 | 条件字段：projecttname<br>条件组合方式：`=` |
| 12 | n_projecttname_like | String | 允许 | 条件字段：projecttname<br>条件组合方式：`%like%` |
| 13 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 14 | n_build_eq | Long | 允许 | 条件字段：build<br>条件组合方式：`=` |
| 15 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 16 | customcond | String | 允许 | 自定义查询条件 |
| 17 | customparams | String | 允许 | 自定义查询参数 |
| 18 | query | String | 允许 | 快速搜索 |
| 19 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 20 | page | int | 允许 | 当前页数<br>默认值0 |
| 21 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 22 | sort | String | 允许 | 排序 |
