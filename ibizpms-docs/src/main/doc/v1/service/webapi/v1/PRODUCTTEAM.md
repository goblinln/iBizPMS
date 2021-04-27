# 服务接口-IBZ_PRODUCTTEAM
## 接口说明
产品团队

## 接口清单
### 新建产品团队
#### 访问路径
/productteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 批量新建产品团队
#### 访问路径
/productteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteamdtos | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)> | 产品团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新产品团队
#### 访问路径
/productteams/{productteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteam_id | Long | 产品团队主键ID |
| 2 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 批量更新产品团队
#### 访问路径
/productteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteamdtos | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)> | 产品团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除产品团队
#### 访问路径
/productteams/{productteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteam_id | Long | 产品团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除产品团队
#### 访问路径
/productteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 产品团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取产品团队
#### 访问路径
/productteams/{productteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteam_id | Long | 产品团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 检查产品团队
#### 访问路径
/productteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### PmsEe团队管理过滤
#### 访问路径
/productteams/{productteam_id}/productteamguolv

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteam_id | Long | 产品团队主键ID |
| 2 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 保存产品团队
#### 访问路径
/productteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存产品团队
#### 访问路径
/productteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productteamdtos | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)> | 产品团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/productteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 查询数据集
#### 访问路径
/productteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品团队成员信息
#### 访问路径
/productteams/fetchproductteaminfo

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 查询产品团队成员信息
#### 访问路径
/productteams/searchproductteaminfo

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目立项
#### 访问路径
/productteams/fetchprojectapp

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 查询项目立项
#### 访问路径
/productteams/searchprojectapp

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目产品团队
#### 访问路径
/productteams/fetchprojectproductteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 查询项目产品团队
#### 访问路径
/productteams/searchprojectproductteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品团队管理
#### 访问路径
/productteams/fetchroweditdefaultproductteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 查询产品团队管理
#### 访问路径
/productteams/searchroweditdefaultproductteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立产品团队
#### 访问路径
/products/{product_id}/productteams

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 根据批量建立产品团队
#### 访问路径
/products/{product_id}/productteams/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteamdtos | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)> | 产品团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新产品团队
#### 访问路径
/products/{product_id}/productteams/{productteam_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteam_id | Long | 产品团队主键ID |
| 3 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 根据批量更新产品团队
#### 访问路径
/products/{product_id}/productteams/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteamdtos | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)> | 产品团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除产品团队
#### 访问路径
/products/{product_id}/productteams/{productteam_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteam_id | Long | 产品团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除产品团队
#### 访问路径
/products/{product_id}/productteams/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 产品团队主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取产品团队
#### 访问路径
/products/{product_id}/productteams/{productteam_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteam_id | Long | 产品团队主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 根据检查产品团队
#### 访问路径
/products/{product_id}/productteams/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### PmsEe团队管理过滤
#### 访问路径
/products/{product_id}/productteams/{productteam_id}/productteamguolv

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteam_id | Long | 产品团队主键ID |
| 3 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO)：产品团队实体传输对象 |

### 根据保存产品团队
#### 访问路径
/products/{product_id}/productteams/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteamdto | [PRODUCTTEAMDTO](#PRODUCTTEAMDTO) | 产品团队实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存产品团队
#### 访问路径
/products/{product_id}/productteams/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | productteamdtos | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)> | 产品团队实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取数据集
#### 访问路径
/products/{product_id}/productteams/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 根据查询数据集
#### 访问路径
/products/{product_id}/productteams/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取产品团队成员信息
#### 访问路径
/products/{product_id}/productteams/fetchproductteaminfo

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 根据查询产品团队成员信息
#### 访问路径
/products/{product_id}/productteams/searchproductteaminfo

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目立项
#### 访问路径
/products/{product_id}/productteams/fetchprojectapp

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 根据查询项目立项
#### 访问路径
/products/{product_id}/productteams/searchprojectapp

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目产品团队
#### 访问路径
/products/{product_id}/productteams/fetchprojectproductteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 根据查询项目产品团队
#### 访问路径
/products/{product_id}/productteams/searchprojectproductteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取产品团队管理
#### 访问路径
/products/{product_id}/productteams/fetchroweditdefaultproductteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象列表 |

### 根据查询产品团队管理
#### 访问路径
/products/{product_id}/productteams/searchroweditdefaultproductteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [PRODUCTTEAMSearchContext](#PRODUCTTEAMSearchContext) | 产品团队查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PRODUCTTEAMDTO](#PRODUCTTEAMDTO)>：产品团队实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### PRODUCTTEAMDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | teamstatus | String | 允许 | 成员状态 |
| 2 | account | String | 允许 | 用户 |
| 3 | join | Timestamp | 允许 | 加盟日<br>时间格式：yyyy-MM-dd |
| 4 | left | Double | 允许 | 预计剩余 |
| 5 | total | Integer | 允许 | 总计可用 |
| 6 | end | Timestamp | 允许 | 结束时间<br>时间格式：yyyy-MM-dd |
| 7 | username | String | 允许 | 用户 |
| 8 | estimate | Double | 允许 | 最初预计 |
| 9 | hours | Double | 允许 | 可用工时/天 |
| 10 | taskcnt | Integer | 允许 | 任务数 |
| 11 | type | String | 允许 | 团队类型 |
| 12 | consumed | Double | 允许 | 总计消耗 |
| 13 | days | Integer | 允许 | 可用工日 |
| 14 | id | Long | 不可 | 编号 |
| 15 | leadingcadre | String | 允许 | 当前负责人 |
| 16 | limited | String | 允许 | 受限用户 |
| 17 | order | Integer | 允许 | 排序 |
| 18 | role | String | 允许 | 角色 |
| 19 | root | Long | 允许 | 产品编号 |
| 20 | <动态属性> | Object | 允许 | 支持动态属性 |

#### PRODUCTTEAMSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_teamstatus_eq | String | 允许 | 条件字段：teamstatus<br>条件组合方式：`=` |
| 2 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 3 | n_account_like | String | 允许 | 条件字段：account<br>条件组合方式：`%like%` |
| 4 | n_join_gtandeq | Timestamp | 允许 | 条件字段：join<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 5 | n_end_ltandeq | Timestamp | 允许 | 条件字段：end<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 6 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 7 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 8 | n_leadingcadre_eq | String | 允许 | 条件字段：leadingcadre<br>条件组合方式：`=` |
| 9 | n_limited_eq | String | 允许 | 条件字段：limited<br>条件组合方式：`=` |
| 10 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 11 | n_root_in | Long | 允许 | 条件字段：root<br>条件组合方式：`in(...)` |
| 12 | customcond | String | 允许 | 自定义查询条件 |
| 13 | customparams | String | 允许 | 自定义查询参数 |
| 14 | query | String | 允许 | 快速搜索 |
| 15 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 16 | page | int | 允许 | 当前页数<br>默认值0 |
| 17 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 18 | sort | String | 允许 | 排序 |
