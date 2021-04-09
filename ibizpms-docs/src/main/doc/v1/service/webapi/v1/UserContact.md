# 服务接口-ZT_USERCONTACT
## 接口说明
用户联系方式

## 接口清单
### 新建用户联系方式
#### 访问路径
/usercontacts

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontactdto | [UserContactDTO](#UserContactDTO) | 用户联系方式实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserContactDTO](#UserContactDTO)：用户联系方式实体传输对象 |

### 批量新建用户联系方式
#### 访问路径
/usercontacts/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontactdtos | List<[UserContactDTO](#UserContactDTO)> | 用户联系方式实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用户联系方式
#### 访问路径
/usercontacts/{usercontact_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontact_id | Long | 用户联系方式主键ID |
| 2 | usercontactdto | [UserContactDTO](#UserContactDTO) | 用户联系方式实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserContactDTO](#UserContactDTO)：用户联系方式实体传输对象 |

### 批量更新用户联系方式
#### 访问路径
/usercontacts/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontactdtos | List<[UserContactDTO](#UserContactDTO)> | 用户联系方式实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除用户联系方式
#### 访问路径
/usercontacts/{usercontact_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontact_id | Long | 用户联系方式主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用户联系方式
#### 访问路径
/usercontacts/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 用户联系方式主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用户联系方式
#### 访问路径
/usercontacts/{usercontact_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontact_id | Long | 用户联系方式主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserContactDTO](#UserContactDTO)：用户联系方式实体传输对象 |

### 检查用户联系方式
#### 访问路径
/usercontacts/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontactdto | [UserContactDTO](#UserContactDTO) | 用户联系方式实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存用户联系方式
#### 访问路径
/usercontacts/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontactdto | [UserContactDTO](#UserContactDTO) | 用户联系方式实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用户联系方式
#### 访问路径
/usercontacts/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | usercontactdtos | List<[UserContactDTO](#UserContactDTO)> | 用户联系方式实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取抄送联系人
#### 访问路径
/usercontacts/fetchcurusercontact

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserContactSearchContext](#UserContactSearchContext) | 用户联系方式查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserContactDTO](#UserContactDTO)>：用户联系方式实体传输对象列表 |

### 查询抄送联系人
#### 访问路径
/usercontacts/searchcurusercontact

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserContactSearchContext](#UserContactSearchContext) | 用户联系方式查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserContactDTO](#UserContactDTO)>：用户联系方式实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/usercontacts/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserContactSearchContext](#UserContactSearchContext) | 用户联系方式查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserContactDTO](#UserContactDTO)>：用户联系方式实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/usercontacts/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserContactSearchContext](#UserContactSearchContext) | 用户联系方式查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserContactDTO](#UserContactDTO)>：用户联系方式实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的联系人
#### 访问路径
/usercontacts/fetchmyusercontact

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserContactSearchContext](#UserContactSearchContext) | 用户联系方式查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserContactDTO](#UserContactDTO)>：用户联系方式实体传输对象列表 |

### 查询我的联系人
#### 访问路径
/usercontacts/searchmyusercontact

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserContactSearchContext](#UserContactSearchContext) | 用户联系方式查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserContactDTO](#UserContactDTO)>：用户联系方式实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### UserContactDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | userlist | String | 允许 | userList |
| 2 | listname | String | 允许 | 标题 |
| 3 | id | Long | 不可 | id |
| 4 | account | String | 允许 | account |
| 5 | <动态属性> | Object | 允许 | 支持动态属性 |

#### UserContactSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | customcond | String | 允许 | 自定义查询条件 |
| 2 | customparams | String | 允许 | 自定义查询参数 |
| 3 | query | String | 允许 | 快速搜索 |
| 4 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 5 | page | int | 允许 | 当前页数<br>默认值0 |
| 6 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 7 | sort | String | 允许 | 排序 |
