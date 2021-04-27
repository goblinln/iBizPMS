# 服务接口-ZT_COMPANY
## 接口说明
公司

## 接口清单
### 新建公司
#### 访问路径
/companies

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companydto | [CompanyDTO](#CompanyDTO) | 公司实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CompanyDTO](#CompanyDTO)：公司实体传输对象 |

### 批量新建公司
#### 访问路径
/companies/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companydtos | List<[CompanyDTO](#CompanyDTO)> | 公司实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新公司
#### 访问路径
/companies/{company_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | company_id | Long | 公司主键ID |
| 2 | companydto | [CompanyDTO](#CompanyDTO) | 公司实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CompanyDTO](#CompanyDTO)：公司实体传输对象 |

### 批量更新公司
#### 访问路径
/companies/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companydtos | List<[CompanyDTO](#CompanyDTO)> | 公司实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除公司
#### 访问路径
/companies/{company_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | company_id | Long | 公司主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除公司
#### 访问路径
/companies/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 公司主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取公司
#### 访问路径
/companies/{company_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | company_id | Long | 公司主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CompanyDTO](#CompanyDTO)：公司实体传输对象 |

### 检查公司
#### 访问路径
/companies/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companydto | [CompanyDTO](#CompanyDTO) | 公司实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存公司
#### 访问路径
/companies/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companydto | [CompanyDTO](#CompanyDTO) | 公司实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存公司
#### 访问路径
/companies/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | companydtos | List<[CompanyDTO](#CompanyDTO)> | 公司实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/companies/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CompanySearchContext](#CompanySearchContext) | 公司查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CompanyDTO](#CompanyDTO)>：公司实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/companies/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CompanySearchContext](#CompanySearchContext) | 公司查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CompanyDTO](#CompanyDTO)>：公司实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### CompanyDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | admins | String | 允许 | admins |
| 2 | fax | String | 允许 | 传真 |
| 3 | deleted | String | 允许 | 逻辑删除标志 |
| 4 | website | String | 允许 | 官网 |
| 5 | id | Long | 不可 | id |
| 6 | zipcode | String | 允许 | 邮政编码 |
| 7 | address | String | 允许 | 通讯地址 |
| 8 | backyard | String | 允许 | 内网 |
| 9 | name | String | 不可 | 公司名称 |
| 10 | guest | String | 允许 | 匿名登陆 |
| 11 | phone | String | 允许 | 联系电话 |
| 12 | <动态属性> | Object | 允许 | 支持动态属性 |

#### CompanySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 3 | n_guest_eq | String | 允许 | 条件字段：guest<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
