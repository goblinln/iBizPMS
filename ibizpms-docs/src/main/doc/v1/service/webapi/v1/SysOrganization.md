# 服务接口-SYS_ORG
## 接口说明
单位

## 接口清单
### 新建单位
#### 访问路径
/sysorganizations

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganizationdto | [SysOrganizationDTO](#SysOrganizationDTO) | 单位实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysOrganizationDTO](#SysOrganizationDTO)：单位实体传输对象 |

### 批量新建单位
#### 访问路径
/sysorganizations/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganizationdtos | List<[SysOrganizationDTO](#SysOrganizationDTO)> | 单位实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新单位
#### 访问路径
/sysorganizations/{sysorganization_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganization_id | String | 单位主键ID |
| 2 | sysorganizationdto | [SysOrganizationDTO](#SysOrganizationDTO) | 单位实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysOrganizationDTO](#SysOrganizationDTO)：单位实体传输对象 |

### 批量更新单位
#### 访问路径
/sysorganizations/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganizationdtos | List<[SysOrganizationDTO](#SysOrganizationDTO)> | 单位实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除单位
#### 访问路径
/sysorganizations/{sysorganization_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganization_id | String | 单位主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除单位
#### 访问路径
/sysorganizations/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 单位主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取单位
#### 访问路径
/sysorganizations/{sysorganization_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganization_id | String | 单位主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysOrganizationDTO](#SysOrganizationDTO)：单位实体传输对象 |

### 检查单位
#### 访问路径
/sysorganizations/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganizationdto | [SysOrganizationDTO](#SysOrganizationDTO) | 单位实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存单位
#### 访问路径
/sysorganizations/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganizationdto | [SysOrganizationDTO](#SysOrganizationDTO) | 单位实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存单位
#### 访问路径
/sysorganizations/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysorganizationdtos | List<[SysOrganizationDTO](#SysOrganizationDTO)> | 单位实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/sysorganizations/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysOrganizationSearchContext](#SysOrganizationSearchContext) | 单位查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysOrganizationDTO](#SysOrganizationDTO)>：单位实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysorganizations/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysOrganizationSearchContext](#SysOrganizationSearchContext) | 单位查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysOrganizationDTO](#SysOrganizationDTO)>：单位实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysOrganizationDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | orgid | String | 允许 | 单位标识 |
| 2 | orgcode | String | 允许 | 单位代码 |
| 3 | orgname | String | 不可 | 名称 |
| 4 | parentorgid | String | 允许 | 上级单位 |
| 5 | shortname | String | 允许 | 单位简称 |
| 6 | orglevel | Integer | 允许 | 单位级别 |
| 7 | showorder | Integer | 允许 | 排序 |
| 8 | parentorgname | String | 允许 | 上级单位 |
| 9 | domains | String | 允许 | 区属 |
| 10 | enable | Integer | 允许 | 逻辑有效 |
| 11 | createdate | Timestamp | 允许 | 创建时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 12 | updatedate | Timestamp | 允许 | 最后修改时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 13 | <动态属性> | Object | 允许 | 支持动态属性 |

