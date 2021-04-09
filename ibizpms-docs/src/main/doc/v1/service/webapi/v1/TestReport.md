# 服务接口-ZT_TESTREPORT
## 接口说明
测试报告

## 接口清单
### 根据测试单获取相应信息
#### 访问路径
/testreports/{testreport_id}/getinfotesttask

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 新建测试报告
#### 访问路径
/testreports

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 批量新建测试报告
#### 访问路径
/testreports/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试报告
#### 访问路径
/testreports/{testreport_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 批量更新测试报告
#### 访问路径
/testreports/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除测试报告
#### 访问路径
/testreports/{testreport_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试报告
#### 访问路径
/testreports/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试报告主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试报告
#### 访问路径
/testreports/{testreport_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 检查测试报告
#### 访问路径
/testreports/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据起始时间获取概况信息
#### 访问路径
/testreports/{testreport_id}/getinfotaskovbytime

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告概况信息（项目报告）
#### 访问路径
/testreports/{testreport_id}/getinfotesttaskovproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）
#### 访问路径
/testreports/{testreport_id}/getinfotesttaskproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）（单测试）
#### 访问路径
/testreports/{testreport_id}/getinfotesttaskr

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）（测试）
#### 访问路径
/testreports/{testreport_id}/getinfotesttasks

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告获取基本信息
#### 访问路径
/testreports/{testreport_id}/gettestreportbasicinfo

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告获取基本信息（项目报告）
#### 访问路径
/testreports/{testreport_id}/gettestreportproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreport_id | Long | 测试报告主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 保存测试报告
#### 访问路径
/testreports/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试报告
#### 访问路径
/testreports/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/testreports/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestReportSearchContext](#TestReportSearchContext) | 测试报告查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestReportDTO](#TestReportDTO)>：测试报告实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/testreports/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [TestReportSearchContext](#TestReportSearchContext) | 测试报告查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestReportDTO](#TestReportDTO)>：测试报告实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据测试单获取相应信息
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/getinfotesttask

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据建立测试报告
#### 访问路径
/products/{product_id}/testreports

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据批量建立测试报告
#### 访问路径
/products/{product_id}/testreports/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试报告
#### 访问路径
/products/{product_id}/testreports/{testreport_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据批量更新测试报告
#### 访问路径
/products/{product_id}/testreports/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试报告
#### 访问路径
/products/{product_id}/testreports/{testreport_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试报告
#### 访问路径
/products/{product_id}/testreports/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 测试报告主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试报告
#### 访问路径
/products/{product_id}/testreports/{testreport_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据检查测试报告
#### 访问路径
/products/{product_id}/testreports/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据起始时间获取概况信息
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/getinfotaskovbytime

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告概况信息（项目报告）
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/getinfotesttaskovproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/getinfotesttaskproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）（单测试）
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/getinfotesttaskr

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）（测试）
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/getinfotesttasks

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告获取基本信息
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/gettestreportbasicinfo

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告获取基本信息（项目报告）
#### 访问路径
/products/{product_id}/testreports/{testreport_id}/gettestreportproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据保存测试报告
#### 访问路径
/products/{product_id}/testreports/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试报告
#### 访问路径
/products/{product_id}/testreports/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/testreports/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestReportSearchContext](#TestReportSearchContext) | 测试报告查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestReportDTO](#TestReportDTO)>：测试报告实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/testreports/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [TestReportSearchContext](#TestReportSearchContext) | 测试报告查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestReportDTO](#TestReportDTO)>：测试报告实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据测试单获取相应信息
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/getinfotesttask

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据建立测试报告
#### 访问路径
/projects/{project_id}/testreports

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据批量建立测试报告
#### 访问路径
/projects/{project_id}/testreports/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试报告
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据批量更新测试报告
#### 访问路径
/projects/{project_id}/testreports/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试报告
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试报告
#### 访问路径
/projects/{project_id}/testreports/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | 测试报告主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试报告
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据检查测试报告
#### 访问路径
/projects/{project_id}/testreports/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据起始时间获取概况信息
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/getinfotaskovbytime

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告概况信息（项目报告）
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/getinfotesttaskovproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/getinfotesttaskproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）（单测试）
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/getinfotesttaskr

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试单获取相应信息（项目报告）（测试）
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/getinfotesttasks

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告获取基本信息
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/gettestreportbasicinfo

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据测试报告获取基本信息（项目报告）
#### 访问路径
/projects/{project_id}/testreports/{testreport_id}/gettestreportproject

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreport_id | Long | 测试报告主键ID |
| 3 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [TestReportDTO](#TestReportDTO)：测试报告实体传输对象 |

### 根据保存测试报告
#### 访问路径
/projects/{project_id}/testreports/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreportdto | [TestReportDTO](#TestReportDTO) | 测试报告实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试报告
#### 访问路径
/projects/{project_id}/testreports/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | testreportdtos | List<[TestReportDTO](#TestReportDTO)> | 测试报告实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/testreports/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TestReportSearchContext](#TestReportSearchContext) | 测试报告查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[TestReportDTO](#TestReportDTO)>：测试报告实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/testreports/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [TestReportSearchContext](#TestReportSearchContext) | 测试报告查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[TestReportDTO](#TestReportDTO)>：测试报告实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### TestReportDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | productcnt | Integer | 允许 | 项目报告产品数 |
| 2 | overviews | String | 允许 | 概况 |
| 3 | objectid | Integer | 允许 | 所属对象 |
| 4 | cases | String | 允许 | 用例 |
| 5 | members | String | 允许 | 参与人员 |
| 6 | bugs | String | 允许 | 测试的Bug |
| 7 | createdby | String | 允许 | 由谁创建 |
| 8 | stories | String | 允许 | 测试的需求 |
| 9 | files | String | 允许 | 附件 |
| 10 | comment | String | 允许 | 备注 |
| 11 | tasks | String | 允许 | 测试单 |
| 12 | title | String | 不可 | 标题 |
| 13 | begin | Timestamp | 不可 | 开始时间<br>时间格式：yyyy-MM-dd |
| 14 | objecttype | String | 允许 | 对象类型 |
| 15 | end | Timestamp | 不可 | 结束时间<br>时间格式：yyyy-MM-dd |
| 16 | builds | String | 允许 | 版本信息 |
| 17 | createddate | Timestamp | 允许 | 创建时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 18 | report | String | 允许 | 总结 |
| 19 | id | Long | 不可 | 编号 |
| 20 | owner | String | 允许 | 负责人 |
| 21 | deleted | String | 允许 | 已删除 |
| 22 | projectname | String | 允许 | 所属项目 |
| 23 | productname | String | 允许 | 所属产品 |
| 24 | product | Long | 允许 | 所属产品 |
| 25 | project | Long | 允许 | 所属项目 |
| 26 | <动态属性> | Object | 允许 | 支持动态属性 |

#### TestReportSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 2 | n_objecttype_eq | String | 允许 | 条件字段：objecttype<br>条件组合方式：`=` |
| 3 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 4 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 5 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 6 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 7 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 8 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
