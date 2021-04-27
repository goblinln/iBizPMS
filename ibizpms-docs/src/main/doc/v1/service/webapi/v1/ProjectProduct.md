# 服务接口-ZT_PROJECTPRODUCT
## 接口说明
项目产品

## 接口清单
### 新建项目产品
#### 访问路径
/projectproducts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 批量新建项目产品
#### 访问路径
/projectproducts/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目产品
#### 访问路径
/projectproducts/{projectproduct_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproduct_id | String | 项目产品主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 批量更新项目产品
#### 访问路径
/projectproducts/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目产品
#### 访问路径
/projectproducts/{projectproduct_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproduct_id | String | 项目产品主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目产品
#### 访问路径
/projectproducts/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 项目产品主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目产品
#### 访问路径
/projectproducts/{projectproduct_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproduct_id | String | 项目产品主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 检查项目产品
#### 访问路径
/projectproducts/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存项目产品
#### 访问路径
/projectproducts/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目产品
#### 访问路径
/projectproducts/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/projectproducts/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/projectproducts/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取关联计划
#### 访问路径
/projectproducts/fetchrelationplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象列表 |

### 查询关联计划
#### 访问路径
/projectproducts/searchrelationplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立项目产品
#### 访问路径
/products/{product_id}/projectproducts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 根据批量建立项目产品
#### 访问路径
/products/{product_id}/projectproducts/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新项目产品
#### 访问路径
/products/{product_id}/projectproducts/{projectproduct_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproduct_id | String | 项目产品主键ID |
| 3 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 根据批量更新项目产品
#### 访问路径
/products/{product_id}/projectproducts/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除项目产品
#### 访问路径
/products/{product_id}/projectproducts/{projectproduct_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproduct_id | String | 项目产品主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除项目产品
#### 访问路径
/products/{product_id}/projectproducts/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<String> | 项目产品主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取项目产品
#### 访问路径
/products/{product_id}/projectproducts/{projectproduct_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproduct_id | String | 项目产品主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 根据检查项目产品
#### 访问路径
/products/{product_id}/projectproducts/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存项目产品
#### 访问路径
/products/{product_id}/projectproducts/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存项目产品
#### 访问路径
/products/{product_id}/projectproducts/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/projectproducts/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/projectproducts/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划
#### 访问路径
/products/{product_id}/projectproducts/fetchrelationplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象列表 |

### 根据查询关联计划
#### 访问路径
/products/{product_id}/projectproducts/searchrelationplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立项目产品
#### 访问路径
/projects/{project_id}/projectproducts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 根据批量建立项目产品
#### 访问路径
/projects/{project_id}/projectproducts/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新项目产品
#### 访问路径
/projects/{project_id}/projectproducts/{projectproduct_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproduct_id | String | 项目产品主键ID |
| 3 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 根据批量更新项目产品
#### 访问路径
/projects/{project_id}/projectproducts/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除项目产品
#### 访问路径
/projects/{project_id}/projectproducts/{projectproduct_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproduct_id | String | 项目产品主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除项目产品
#### 访问路径
/projects/{project_id}/projectproducts/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<String> | 项目产品主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取项目产品
#### 访问路径
/projects/{project_id}/projectproducts/{projectproduct_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproduct_id | String | 项目产品主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectProductDTO](#ProjectProductDTO)：项目产品实体传输对象 |

### 根据检查项目产品
#### 访问路径
/projects/{project_id}/projectproducts/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存项目产品
#### 访问路径
/projects/{project_id}/projectproducts/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproductdto | [ProjectProductDTO](#ProjectProductDTO) | 项目产品实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存项目产品
#### 访问路径
/projects/{project_id}/projectproducts/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | projectproductdtos | List<[ProjectProductDTO](#ProjectProductDTO)> | 项目产品实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/projectproducts/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/projectproducts/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取关联计划
#### 访问路径
/projects/{project_id}/projectproducts/fetchrelationplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象列表 |

### 根据查询关联计划
#### 访问路径
/projects/{project_id}/projectproducts/searchrelationplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [ProjectProductSearchContext](#ProjectProductSearchContext) | 项目产品查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectProductDTO](#ProjectProductDTO)>：项目产品实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectProductDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | id | String | 不可 | 虚拟主键 |
| 2 | productname | String | 允许 | 产品 |
| 3 | projectname | String | 允许 | 项目 |
| 4 | planname | String | 允许 | 计划名称 |
| 5 | product | Long | 不可 | 产品 |
| 6 | plan | Long | 允许 | 产品计划 |
| 7 | branch | Long | 允许 | 平台/分支 |
| 8 | project | Long | 不可 | 项目 |
| 9 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectProductSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | String | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 3 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 4 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 5 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 6 | n_planname_eq | String | 允许 | 条件字段：planname<br>条件组合方式：`=` |
| 7 | n_planname_like | String | 允许 | 条件字段：planname<br>条件组合方式：`%like%` |
| 8 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 9 | n_plan_eq | Long | 允许 | 条件字段：plan<br>条件组合方式：`=` |
| 10 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 11 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 12 | customcond | String | 允许 | 自定义查询条件 |
| 13 | customparams | String | 允许 | 自定义查询参数 |
| 14 | query | String | 允许 | 快速搜索 |
| 15 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 16 | page | int | 允许 | 当前页数<br>默认值0 |
| 17 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 18 | sort | String | 允许 | 排序 |
