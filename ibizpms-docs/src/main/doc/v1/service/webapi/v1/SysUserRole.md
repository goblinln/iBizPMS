# 服务接口-SYS_USER_ROLE
## 接口说明
用户角色关系

## 接口清单
### 新建用户角色关系
#### 访问路径
/sysuserroles

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserroledto | [SysUserRoleDTO](#SysUserRoleDTO) | 用户角色关系实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserRoleDTO](#SysUserRoleDTO)：用户角色关系实体传输对象 |

### 批量新建用户角色关系
#### 访问路径
/sysuserroles/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserroledtos | List<[SysUserRoleDTO](#SysUserRoleDTO)> | 用户角色关系实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用户角色关系
#### 访问路径
/sysuserroles/{sysuserrole_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserrole_id | String | 用户角色关系主键ID |
| 2 | sysuserroledto | [SysUserRoleDTO](#SysUserRoleDTO) | 用户角色关系实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserRoleDTO](#SysUserRoleDTO)：用户角色关系实体传输对象 |

### 批量更新用户角色关系
#### 访问路径
/sysuserroles/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserroledtos | List<[SysUserRoleDTO](#SysUserRoleDTO)> | 用户角色关系实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除用户角色关系
#### 访问路径
/sysuserroles/{sysuserrole_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserrole_id | String | 用户角色关系主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用户角色关系
#### 访问路径
/sysuserroles/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 用户角色关系主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用户角色关系
#### 访问路径
/sysuserroles/{sysuserrole_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserrole_id | String | 用户角色关系主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUserRoleDTO](#SysUserRoleDTO)：用户角色关系实体传输对象 |

### 检查用户角色关系
#### 访问路径
/sysuserroles/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserroledto | [SysUserRoleDTO](#SysUserRoleDTO) | 用户角色关系实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存用户角色关系
#### 访问路径
/sysuserroles/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserroledto | [SysUserRoleDTO](#SysUserRoleDTO) | 用户角色关系实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用户角色关系
#### 访问路径
/sysuserroles/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysuserroledtos | List<[SysUserRoleDTO](#SysUserRoleDTO)> | 用户角色关系实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/sysuserroles/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUserRoleSearchContext](#SysUserRoleSearchContext) | 用户角色关系查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysUserRoleDTO](#SysUserRoleDTO)>：用户角色关系实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysuserroles/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUserRoleSearchContext](#SysUserRoleSearchContext) | 用户角色关系查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysUserRoleDTO](#SysUserRoleDTO)>：用户角色关系实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysUserRoleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | userroleid | String | 不可 | 用户角色关系标识 |
| 2 | roleid | String | 允许 | 角色标识 |
| 3 | rolename | String | 允许 | 角色名称 |
| 4 | userid | String | 允许 | 用户标识 |
| 5 | personname | String | 允许 | 用户名称 |
| 6 | loginname | String | 允许 | 登录名 |
| 7 | orgname | String | 允许 | 单位 |
| 8 | mdeptname | String | 允许 | 主部门 |
| 9 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

