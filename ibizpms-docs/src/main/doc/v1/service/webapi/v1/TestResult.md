# 服务接口-ZT_TESTRESULT
## 接口说明
测试结果

## 接口清单
### 新建测试结果
#### 访问路径
/testresults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 批量新建测试结果
#### 访问路径
/testresults/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试结果
#### 访问路径
/testresults/{testresult_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresult_id | Long | 测试结果主键ID |
| 2 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 批量更新测试结果
#### 访问路径
/testresults/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除测试结果
#### 访问路径
/testresults/{testresult_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试结果
#### 访问路径
/testresults/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试结果主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试结果
#### 访问路径
/testresults/{testresult_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 检查测试结果
#### 访问路径
/testresults/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存测试结果
#### 访问路径
/testresults/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试结果
#### 访问路径
/testresults/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取CurTestRun
#### 访问路径
/testresults/fetchcurtestrun

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 查询CurTestRun
#### 访问路径
/testresults/searchcurtestrun

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/testresults/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/testresults/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试结果
#### 访问路径
/cases/{case_id}/testresults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量建立测试结果
#### 访问路径
/cases/{case_id}/testresults/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试结果
#### 访问路径
/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresult_id | Long | 测试结果主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量更新测试结果
#### 访问路径
/cases/{case_id}/testresults/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试结果
#### 访问路径
/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试结果
#### 访问路径
/cases/{case_id}/testresults/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | ids | List<Long> | 测试结果主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试结果
#### 访问路径
/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据检查测试结果
#### 访问路径
/cases/{case_id}/testresults/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试结果
#### 访问路径
/cases/{case_id}/testresults/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试结果
#### 访问路径
/cases/{case_id}/testresults/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取CurTestRun
#### 访问路径
/cases/{case_id}/testresults/fetchcurtestrun

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询CurTestRun
#### 访问路径
/cases/{case_id}/testresults/searchcurtestrun

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/cases/{case_id}/testresults/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/cases/{case_id}/testresults/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量建立测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresult_id | Long | 测试结果主键ID |
| 4 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量更新测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | ids | List<Long> | 测试结果主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据检查测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试结果
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取CurTestRun
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/fetchcurtestrun

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询CurTestRun
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/searchcurtestrun

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/cases/{case_id}/testresults/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量建立测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresult_id | Long | 测试结果主键ID |
| 4 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量更新测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | ids | List<Long> | 测试结果主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据检查测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取CurTestRun
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/fetchcurtestrun

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询CurTestRun
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/searchcurtestrun

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/cases/{case_id}/testresults/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量建立测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresult_id | Long | 测试结果主键ID |
| 5 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据批量更新测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | ids | List<Long> | 测试结果主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresult_id | Long | 测试结果主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestResultDTO](#TestResultDTO)：测试结果实体传输对象 |

### 根据检查测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresultdto | [TestResultDTO](#TestResultDTO) | 测试结果实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | testresultdtos | List<[TestResultDTO](#TestResultDTO)> | 测试结果实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取CurTestRun
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/fetchcurtestrun

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询CurTestRun
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/searchcurtestrun

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [TestResultSearchContext](#TestResultSearchContext) | 测试结果查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestResultDTO](#TestResultDTO)>：测试结果实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TestResultDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | lastrunner | String | 允许 | 最后执行人 |
| 2 | stepresults | String | 允许 | 步骤结果 |
| 3 | caseresult | String | 允许 | 测试结果 |
| 4 | xml | String | 允许 | 结果文件 |
| 5 | task | String | 允许 | 属性 |
| 6 | duration | Double | 允许 | 持续时间 |
| 7 | date | Timestamp | 允许 | 测试时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 8 | id | Long | 不可 | 编号 |
| 9 | version | Integer | 允许 | 用例版本 |
| 10 | story | Long | 允许 | 相关需求 |
| 11 | title | String | 允许 | 用例名称 |
| 12 | modulename | String | 允许 | 所属模块 |
| 13 | module | Long | 允许 | 所属模块 |
| 14 | precondition | String | 允许 | 前置条件 |
| 15 | product | Long | 允许 | 所属产品 |
| 16 | job | Long | 允许 | 构建任务 |
| 17 | ibizcase | Long | 允许 | 用例 |
| 18 | run | Long | 允许 | 测试执行 |
| 19 | compile | Long | 允许 | 代码编译 |
| 20 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TestResultSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 2 | n_product_like | Long | 允许 | 条件字段：product<br>条件组合方式：`%like%` |
| 3 | n_job_eq | Long | 允许 | 条件字段：job<br>条件组合方式：`=` |
| 4 | n_case_eq | Long | 允许 | 条件字段：case<br>条件组合方式：`=` |
| 5 | n_run_eq | Long | 允许 | 条件字段：run<br>条件组合方式：`=` |
| 6 | n_compile_eq | Long | 允许 | 条件字段：compile<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
