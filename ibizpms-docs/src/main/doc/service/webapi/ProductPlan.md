# 服务接口-ZT_PRODUCTPLAN
## 接口说明
产品计划

## 接口清单
### 新建产品计划
#### 访问路径
/productplans

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 批量新建产品计划
#### 访问路径
/productplans/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplandtos | List<[ProductPlanDTO](#ProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新产品计划
#### 访问路径
/productplans/{productplan_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 批量更新产品计划
#### 访问路径
/productplans/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplandtos | List<[ProductPlanDTO](#ProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除产品计划
#### 访问路径
/productplans/{productplan_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除产品计划
#### 访问路径
/productplans/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | ids | List<Long> | 产品计划主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取产品计划
#### 访问路径
/productplans/{productplan_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/productplans/{productplan_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 批量解除关联需求
#### 访问路径
/productplans/{productplan_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 检查产品计划
#### 访问路径
/productplans/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联Bug
#### 访问路径
/productplans/{productplan_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 关联需求
#### 访问路径
/productplans/{productplan_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 保存产品计划
#### 访问路径
/productplans/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存产品计划
#### 访问路径
/productplans/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplandtos | List<[ProductPlanDTO](#ProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 解除关联Bug
#### 访问路径
/productplans/{productplan_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 解除关联需求
#### 访问路径
/productplans/{productplan_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 获取CurProductPlan
#### 访问路径
/productplans/fetchcurproductplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 查询CurProductPlan
#### 访问路径
/productplans/searchcurproductplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/productplans/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/productplans/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取默认查询
#### 访问路径
/productplans/fetchdefaultparent

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 查询默认查询
#### 访问路径
/productplans/searchdefaultparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取计划（代码表）
#### 访问路径
/productplans/fetchplancodelist

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 查询计划（代码表）
#### 访问路径
/productplans/searchplancodelist

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目计划列表
#### 访问路径
/productplans/fetchprojectplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 查询项目计划列表
#### 访问路径
/productplans/searchprojectplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立产品计划
#### 访问路径
/products/{product_id}/productplans

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 根据批量建立产品计划
#### 访问路径
/products/{product_id}/productplans/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplandtos | List<[ProductPlanDTO](#ProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 根据批量更新产品计划
#### 访问路径
/products/{product_id}/productplans/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplandtos | List<[ProductPlanDTO](#ProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除产品计划
#### 访问路径
/products/{product_id}/productplans/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 产品计划主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 批量解除关联需求
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 根据检查产品计划
#### 访问路径
/products/{product_id}/productplans/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联Bug
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 关联需求
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 根据保存产品计划
#### 访问路径
/products/{product_id}/productplans/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存产品计划
#### 访问路径
/products/{product_id}/productplans/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplandtos | List<[ProductPlanDTO](#ProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 解除关联需求
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | productplandto | [ProductPlanDTO](#ProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProductPlanDTO](#ProductPlanDTO)：产品计划实体传输对象 |

### 根据获取CurProductPlan
#### 访问路径
/products/{product_id}/productplans/fetchcurproductplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询CurProductPlan
#### 访问路径
/products/{product_id}/productplans/searchcurproductplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productplans/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productplans/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取默认查询
#### 访问路径
/products/{product_id}/productplans/fetchdefaultparent

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询默认查询
#### 访问路径
/products/{product_id}/productplans/searchdefaultparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划（代码表）
#### 访问路径
/products/{product_id}/productplans/fetchplancodelist

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询计划（代码表）
#### 访问路径
/products/{product_id}/productplans/searchplancodelist

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目计划列表
#### 访问路径
/products/{product_id}/productplans/fetchprojectplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目计划列表
#### 访问路径
/products/{product_id}/productplans/searchprojectplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| -- | -- | -- | -- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| -- | -- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProductPlanDTO](#ProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProductPlanDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| -- | -- | -- | -- | -- |
| 1 | title | String | 不可 | 名称 |
| 2 | id | Long | 不可 | 编号 |
| 3 | begin | Timestamp | 允许 | 开始日期<br>时间格式：yyyy-MM-dd |
| 4 | desc | String | 允许 | 描述 |
| 5 | end | Timestamp | 允许 | 结束日期<br>时间格式：yyyy-MM-dd |
| 6 | deleted | String | 不可 | 已删除 |
| 7 | order | String | 允许 | 排序 |
| 8 | parentname | String | 允许 | 父计划名称 |
| 9 | branch | Long | 允许 | 平台/分支 |
| 10 | parent | Long | 允许 | 父计划 |
| 11 | product | Long | 允许 | 产品 |
| 12 | statuss | String | 允许 | 状态 |
| 13 | future | String | 允许 | 待定 |
| 14 | delta | String | 允许 | 周期 |
| 15 | oldtitle | String | 允许 | 上一次计划名称 |
| 16 | storycnt | Integer | 允许 | 需求数 |
| 17 | bugcnt | Integer | 允许 | bug数 |
| 18 | isexpired | String | 允许 | 是否过期 |
| 19 | estimatecnt | Integer | 允许 | 工时数 |
| 20 | beginstr | String | 允许 | 开始日期 |
| 21 | endstr | String | 允许 | 结束日期 |
| 22 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProductPlanSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| -- | -- | -- | -- | -- |
| 22 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 23 | n_begin_gtandeq | Timestamp | 允许 | 条件字段：begin<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 24 | n_end_ltandeq | Timestamp | 允许 | 条件字段：end<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 25 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 26 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 27 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 28 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 29 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 30 | n_future_eq | String | 允许 | 条件字段：future<br>条件组合方式：`=` |
| 31 | n_delta_eq | String | 允许 | 条件字段：delta<br>条件组合方式：`=` |
| 32 | n_isexpired_eq | String | 允许 | 条件字段：isexpired<br>条件组合方式：`=` |
| 33 | customcond | String | 允许 | 自定义查询条件 |
| 34 | customparams | String | 允许 | 自定义查询参数 |
| 35 | query | String | 允许 | 快速搜索 |
| 36 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 37 | page | int | 允许 | 当前页数<br>默认值0 |
| 38 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 39 | sort | String | 允许 | 排序 |
