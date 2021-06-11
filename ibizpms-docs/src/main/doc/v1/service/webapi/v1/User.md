# 服务接口-ZT_USER
## 接口说明
用户

## 接口清单
### 新建用户
#### 访问路径
/users

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | userdto | [UserDTO](#UserDTO) | 用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserDTO](#UserDTO)：用户实体传输对象 |

### 批量新建用户
#### 访问路径
/users/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | userdtos | List<[UserDTO](#UserDTO)> | 用户实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用户
#### 访问路径
/users/{user_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | user_id | Long | 用户主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserDTO](#UserDTO)：用户实体传输对象 |

### 删除用户
#### 访问路径
/users/{user_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | user_id | Long | 用户主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用户
#### 访问路径
/users/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 用户主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用户
#### 访问路径
/users/{user_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | user_id | Long | 用户主键ID |
| 2 | userdto | [UserDTO](#UserDTO) | 用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserDTO](#UserDTO)：用户实体传输对象 |

### 批量更新用户
#### 访问路径
/users/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | userdtos | List<[UserDTO](#UserDTO)> | 用户实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查用户
#### 访问路径
/users/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | userdto | [UserDTO](#UserDTO) | 用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据代码账户查询用户信息
#### 访问路径
/users/{user_id}/getbycommiter

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | user_id | Long | 用户主键ID |
| 2 | userdto | [UserDTO](#UserDTO) | 用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserDTO](#UserDTO)：用户实体传输对象 |

### 保存用户
#### 访问路径
/users/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | userdto | [UserDTO](#UserDTO) | 用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用户
#### 访问路径
/users/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | userdtos | List<[UserDTO](#UserDTO)> | 用户实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 同步账号
#### 访问路径
/users/{user_id}/syncaccount

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | user_id | Long | 用户主键ID |
| 2 | userdto | [UserDTO](#UserDTO) | 用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserDTO](#UserDTO)：用户实体传输对象 |

### 获取Bug用户
#### 访问路径
/users/fetchbuguser

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询Bug用户
#### 访问路径
/users/searchbuguser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/users/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/users/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取根据源代码账户获取登录名
#### 访问路径
/users/fetchgetbycommiter

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询根据源代码账户获取登录名
#### 访问路径
/users/searchgetbycommiter

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队管理
#### 访问路径
/users/fetchprojectteamm

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询项目团队管理
#### 访问路径
/users/searchprojectteamm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队成员
#### 访问路径
/users/fetchprojectteamuser

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询项目团队成员
#### 访问路径
/users/searchprojectteamuser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队成员
#### 访问路径
/users/fetchprojectteamusertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询项目团队成员
#### 访问路径
/users/searchprojectteamusertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取TASKTEAM
#### 访问路径
/users/fetchtaskteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserDTO](#UserDTO)>：用户实体传输对象列表 |

### 查询TASKTEAM
#### 访问路径
/users/searchtaskteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserSearchContext](#UserSearchContext) | 用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserDTO](#UserDTO)>：用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### UserDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | password | String | 允许 | 密码 |
| 2 | address | String | 允许 | 通讯地址 |
| 3 | weixin | String | 允许 | 微信 |
| 4 | dingding | String | 允许 | 钉钉 |
| 5 | fails | Integer | 允许 | fails |
| 6 | slack | String | 允许 | slack |
| 7 | ranzhi | String | 允许 | ranzhi |
| 8 | account | String | 允许 | 账户 |
| 9 | locked | Timestamp | 允许 | locked<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | avatar | String | 允许 | avatar |
| 11 | scorelevel | Integer | 允许 | scoreLevel |
| 12 | realname | String | 允许 | 真实姓名 |
| 13 | zipcode | String | 允许 | zipcode |
| 14 | dept | Integer | 允许 | 所属部门 |
| 15 | commiter | String | 允许 | 源代码账户 |
| 16 | role | String | 允许 | 职位 |
| 17 | deleted | String | 允许 | 逻辑删除标志 |
| 18 | last | Integer | 允许 | 最后登录 |
| 19 | clientstatus | String | 允许 | clientStatus |
| 20 | skype | String | 允许 | skype |
| 21 | whatsapp | String | 允许 | whatsapp |
| 22 | score | Integer | 允许 | score |
| 23 | gender | String | 允许 | 性别 |
| 24 | mobile | String | 允许 | 手机 |
| 25 | clientlang | String | 允许 | clientLang |
| 26 | visits | Integer | 允许 | 访问次数 |
| 27 | join | Timestamp | 允许 | 入职日期<br>时间格式：yyyy-MM-dd |
| 28 | email | String | 允许 | 邮箱 |
| 29 | ip | String | 允许 | ip |
| 30 | birthday | Timestamp | 允许 | birthday<br>时间格式：yyyy-MM-dd |
| 31 | nickname | String | 允许 | nickname |
| 32 | phone | String | 允许 | 电话 |
| 33 | id | Long | 不可 | ID |
| 34 | qq | String | 允许 | QQ |
| 35 | <动态属性> | Object | 允许 | 支持动态属性 |

#### UserSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_account_in | String | 允许 | 条件字段：account<br>条件组合方式：`in(...)` |
| 2 | n_account_notin | String | 允许 | 条件字段：account<br>条件组合方式：`notin(...)` |
| 3 | n_realname_like | String | 允许 | 条件字段：realname<br>条件组合方式：`%like%` |
| 4 | n_dept_eq | Integer | 允许 | 条件字段：dept<br>条件组合方式：`=` |
| 5 | n_role_eq | String | 允许 | 条件字段：role<br>条件组合方式：`=` |
| 6 | n_clientstatus_eq | String | 允许 | 条件字段：clientstatus<br>条件组合方式：`=` |
| 7 | n_gender_eq | String | 允许 | 条件字段：gender<br>条件组合方式：`=` |
| 8 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
