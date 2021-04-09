# 服务接口-SYS_DEPT
## 接口说明
部门

## 接口清单
### 新建部门
#### 访问路径
/sysdepartments

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartmentdto | [SysDepartmentDTO](#SysDepartmentDTO) | 部门实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysDepartmentDTO](#SysDepartmentDTO)：部门实体传输对象 |

### 批量新建部门
#### 访问路径
/sysdepartments/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartmentdtos | List<[SysDepartmentDTO](#SysDepartmentDTO)> | 部门实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新部门
#### 访问路径
/sysdepartments/{sysdepartment_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartment_id | String | 部门主键ID |
| 2 | sysdepartmentdto | [SysDepartmentDTO](#SysDepartmentDTO) | 部门实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysDepartmentDTO](#SysDepartmentDTO)：部门实体传输对象 |

### 批量更新部门
#### 访问路径
/sysdepartments/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartmentdtos | List<[SysDepartmentDTO](#SysDepartmentDTO)> | 部门实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除部门
#### 访问路径
/sysdepartments/{sysdepartment_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartment_id | String | 部门主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除部门
#### 访问路径
/sysdepartments/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 部门主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取部门
#### 访问路径
/sysdepartments/{sysdepartment_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartment_id | String | 部门主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysDepartmentDTO](#SysDepartmentDTO)：部门实体传输对象 |

### 检查部门
#### 访问路径
/sysdepartments/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartmentdto | [SysDepartmentDTO](#SysDepartmentDTO) | 部门实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存部门
#### 访问路径
/sysdepartments/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartmentdto | [SysDepartmentDTO](#SysDepartmentDTO) | 部门实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存部门
#### 访问路径
/sysdepartments/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysdepartmentdtos | List<[SysDepartmentDTO](#SysDepartmentDTO)> | 部门实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/sysdepartments/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysDepartmentSearchContext](#SysDepartmentSearchContext) | 部门查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysDepartmentDTO](#SysDepartmentDTO)>：部门实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysdepartments/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysDepartmentSearchContext](#SysDepartmentSearchContext) | 部门查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysDepartmentDTO](#SysDepartmentDTO)>：部门实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysDepartmentDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | deptid | String | 允许 | 部门标识 |
| 2 | deptcode | String | 不可 | 部门代码 |
| 3 | deptname | String | 不可 | 部门名称 |
| 4 | orgid | String | 允许 | 单位 |
| 5 | parentdeptid | String | 允许 | 上级部门 |
| 6 | shortname | String | 允许 | 部门简称 |
| 7 | deptlevel | Integer | 允许 | 部门级别 |
| 8 | domains | String | 允许 | 区属 |
| 9 | showorder | Integer | 允许 | 排序 |
| 10 | bcode | String | 允许 | 业务编码 |
| 11 | leaderid | String | 允许 | 分管领导标识 |
| 12 | leadername | String | 允许 | 分管领导 |
| 13 | orgname | String | 允许 | 单位 |
| 14 | parentdeptname | String | 允许 | 上级部门 |
| 15 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 16 | enable | Integer | 不可 | 逻辑有效标志 |
| 17 | updateman | String | 不可 | 更新人 |
| 18 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 19 | createman | String | 不可 | 建立人 |
| 20 | <动态属性> | Object | 允许 | 支持动态属性 |

