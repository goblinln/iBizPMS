# 服务接口-PSSYSTEMDBCFG
## 接口说明
系统数据库

## 接口清单
### 新建系统数据库
#### 访问路径
/pssystemdbcfgs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfgdto | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO) | 系统数据库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO)：系统数据库实体传输对象 |

### 批量新建系统数据库
#### 访问路径
/pssystemdbcfgs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfgdtos | List<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)> | 系统数据库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统数据库
#### 访问路径
/pssystemdbcfgs/{pssystemdbcfg_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfg_id | String | 系统数据库主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO)：系统数据库实体传输对象 |

### 删除系统数据库
#### 访问路径
/pssystemdbcfgs/{pssystemdbcfg_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfg_id | String | 系统数据库主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统数据库
#### 访问路径
/pssystemdbcfgs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 系统数据库主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统数据库
#### 访问路径
/pssystemdbcfgs/{pssystemdbcfg_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfg_id | String | 系统数据库主键ID |
| 2 | pssystemdbcfgdto | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO) | 系统数据库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO)：系统数据库实体传输对象 |

### 批量更新系统数据库
#### 访问路径
/pssystemdbcfgs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfgdtos | List<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)> | 系统数据库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查系统数据库
#### 访问路径
/pssystemdbcfgs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfgdto | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO) | 系统数据库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存系统数据库
#### 访问路径
/pssystemdbcfgs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfgdto | [PSSystemDBCfgDTO](#PSSystemDBCfgDTO) | 系统数据库实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统数据库
#### 访问路径
/pssystemdbcfgs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssystemdbcfgdtos | List<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)> | 系统数据库实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取版本
#### 访问路径
/pssystemdbcfgs/fetchbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSystemDBCfgSearchContext](#PSSystemDBCfgSearchContext) | 系统数据库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)>：系统数据库实体传输对象列表 |

### 查询版本
#### 访问路径
/pssystemdbcfgs/searchbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSystemDBCfgSearchContext](#PSSystemDBCfgSearchContext) | 系统数据库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)>：系统数据库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/pssystemdbcfgs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSystemDBCfgSearchContext](#PSSystemDBCfgSearchContext) | 系统数据库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)>：系统数据库实体传输对象列表 |

### 查询数据集
#### 访问路径
/pssystemdbcfgs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSystemDBCfgSearchContext](#PSSystemDBCfgSearchContext) | 系统数据库查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PSSystemDBCfgDTO](#PSSystemDBCfgDTO)>：系统数据库实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### PSSystemDBCfgDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | tabspace2 | String | 允许 | 表空间2名称 |
| 2 | resstate | Integer | 允许 | 资源状态 |
| 3 | nullvalorder | String | 允许 | 空值排序 |
| 4 | usertag3 | String | 允许 | 用户标记3 |
| 5 | tabspace | String | 允许 | 默认表空间名称 |
| 6 | defaultflag | Integer | 允许 | 默认数据源 |
| 7 | memo | String | 允许 | 备注 |
| 8 | nodbinstmode | Integer | 允许 | 无数据库模式 |
| 9 | usertag4 | String | 允许 | 用户标记4 |
| 10 | pssystemdbcfgname | String | 允许 | 系统数据库名称 |
| 11 | pssystemname | String | 允许 | 系统 |
| 12 | resinfo | String | 允许 | 资源信息 |
| 13 | usertag | String | 允许 | 用户标记 |
| 14 | userparams | String | 允许 | 自定义参数 |
| 15 | resreadytime | Timestamp | 允许 | 资源就绪时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 16 | pubfkeyflag | Integer | 允许 | 发布外键 |
| 17 | pssystemdbcfgid | String | 不可 | 系统数据库标识 |
| 18 | usertag2 | String | 允许 | 用户标记2 |
| 19 | updateman | String | 不可 | 更新人 |
| 20 | pubcommentflag | Integer | 允许 | 发布模型注释 |
| 21 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 22 | tabspace3 | String | 允许 | 表空间3名称 |
| 23 | enablewebtool | Integer | 允许 | 支持Web管理 |
| 24 | pubdbmodelflag | Integer | 允许 | 发布数据库模型 |
| 25 | objnamecase | String | 允许 | 对象名称转换 |
| 26 | pubindexflag | Integer | 允许 | 发布索引 |
| 27 | appendschema | Integer | 允许 | 附加模式名称 |
| 28 | pssystemid | String | 允许 | 系统 |
| 29 | dbschemaname | String | 允许 | 数据库模式名称 |
| 30 | tabspace4 | String | 允许 | 表空间4名称 |
| 31 | usercat | String | 允许 | 用户分类 |
| 32 | createman | String | 不可 | 建立人 |
| 33 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 34 | pubviewflag | Integer | 允许 | 发布视图 |
| 35 | <动态属性> | Object | 允许 | 支持动态属性 |

