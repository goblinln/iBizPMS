# 服务接口-SYS_USER
## 接口说明
系统用户

## 接口清单
### 新建系统用户
#### 访问路径
/sysusers

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserdto | [SysUserDTO](#SysUserDTO) | 系统用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserDTO](#SysUserDTO)：系统用户实体传输对象 |

### 批量新建系统用户
#### 访问路径
/sysusers/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserdtos | List<[SysUserDTO](#SysUserDTO)> | 系统用户实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统用户
#### 访问路径
/sysusers/{sysuser_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuser_id | String | 系统用户主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserDTO](#SysUserDTO)：系统用户实体传输对象 |

### 删除系统用户
#### 访问路径
/sysusers/{sysuser_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuser_id | String | 系统用户主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统用户
#### 访问路径
/sysusers/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 系统用户主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统用户
#### 访问路径
/sysusers/{sysuser_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuser_id | String | 系统用户主键ID |
| 2 | sysuserdto | [SysUserDTO](#SysUserDTO) | 系统用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserDTO](#SysUserDTO)：系统用户实体传输对象 |

### 批量更新系统用户
#### 访问路径
/sysusers/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserdtos | List<[SysUserDTO](#SysUserDTO)> | 系统用户实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 修改密码
#### 访问路径
/sysusers/{sysuser_id}/changepwd

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuser_id | String | 系统用户主键ID |
| 2 | sysuserdto | [SysUserDTO](#SysUserDTO) | 系统用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserDTO](#SysUserDTO)：系统用户实体传输对象 |

### 检查系统用户
#### 访问路径
/sysusers/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserdto | [SysUserDTO](#SysUserDTO) | 系统用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存系统用户
#### 访问路径
/sysusers/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserdto | [SysUserDTO](#SysUserDTO) | 系统用户实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统用户
#### 访问路径
/sysusers/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserdtos | List<[SysUserDTO](#SysUserDTO)> | 系统用户实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/sysusers/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUserSearchContext](#SysUserSearchContext) | 系统用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysUserDTO](#SysUserDTO)>：系统用户实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysusers/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUserSearchContext](#SysUserSearchContext) | 系统用户查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysUserDTO](#SysUserDTO)>：系统用户实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysUserDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | userid | String | 允许 | 用户标识 |
| 2 | username | String | 允许 | 用户全局名 |
| 3 | personname | String | 允许 | 用户姓名 |
| 4 | usercode | String | 不可 | 用户工号 |
| 5 | password | String | 允许 | 密码 |
| 6 | loginname | String | 不可 | 登录名 |
| 7 | domains | String | 允许 | 区属 |
| 8 | mdeptid | String | 允许 | 主部门 |
| 9 | mdeptcode | String | 允许 | 主部门代码 |
| 10 | mdeptname | String | 允许 | 主部门名称 |
| 11 | bcode | String | 允许 | 业务编码 |
| 12 | postid | String | 允许 | 岗位标识 |
| 13 | postcode | String | 允许 | 岗位代码 |
| 14 | postname | String | 允许 | 岗位名称 |
| 15 | orgid | String | 允许 | 单位 |
| 16 | orgcode | String | 允许 | 单位代码 |
| 17 | orgname | String | 允许 | 单位名称 |
| 18 | nickname | String | 允许 | 昵称别名 |
| 19 | sex | String | 允许 | 性别 |
| 20 | birthday | Timestamp | 允许 | 出生日期<br>时间格式：yyyy-MM-dd |
| 21 | certcode | String | 允许 | 证件号码 |
| 22 | phone | String | 允许 | 联系方式 |
| 23 | email | String | 允许 | 邮件 |
| 24 | avatar | String | 允许 | 社交账号 |
| 25 | addr | String | 允许 | 地址 |
| 26 | usericon | String | 允许 | 照片 |
| 27 | theme | String | 允许 | 样式 |
| 28 | lang | String | 允许 | 语言 |
| 29 | fontsize | String | 允许 | 字号 |
| 30 | memo | String | 允许 | 备注 |
| 31 | reserver | String | 允许 | 保留 |
| 32 | superuser | Integer | 允许 | 超级管理员 |
| 33 | originalpassword | String | 允许 | 原密码 |
| 34 | newpassword | String | 允许 | 新密码 |
| 35 | repeatpassword | String | 允许 | 重复密码 |
| 36 | <动态属性> | Object | 允许 | 支持动态属性 |

