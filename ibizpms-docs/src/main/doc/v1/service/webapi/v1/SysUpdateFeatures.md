# 服务接口-SYS_UPDATE_FEATURES
## 接口说明
系统更新功能

## 接口清单
### 新建系统更新功能
#### 访问路径
/sysupdatefeatures

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)：系统更新功能实体传输对象 |

### 批量新建系统更新功能
#### 访问路径
/sysupdatefeatures/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesdtos | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)> | 系统更新功能实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统更新功能
#### 访问路径
/sysupdatefeatures/{sysupdatefeatures_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeatures_id | String | 系统更新功能主键ID |
| 2 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)：系统更新功能实体传输对象 |

### 批量更新系统更新功能
#### 访问路径
/sysupdatefeatures/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesdtos | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)> | 系统更新功能实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除系统更新功能
#### 访问路径
/sysupdatefeatures/{sysupdatefeatures_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeatures_id | String | 系统更新功能主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统更新功能
#### 访问路径
/sysupdatefeatures/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 系统更新功能主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统更新功能
#### 访问路径
/sysupdatefeatures/{sysupdatefeatures_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeatures_id | String | 系统更新功能主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)：系统更新功能实体传输对象 |

### 检查系统更新功能
#### 访问路径
/sysupdatefeatures/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存系统更新功能
#### 访问路径
/sysupdatefeatures/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统更新功能
#### 访问路径
/sysupdatefeatures/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesdtos | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)> | 系统更新功能实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/sysupdatefeatures/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUpdateFeaturesSearchContext](#SysUpdateFeaturesSearchContext) | 系统更新功能查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)>：系统更新功能实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysupdatefeatures/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUpdateFeaturesSearchContext](#SysUpdateFeaturesSearchContext) | 系统更新功能查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)>：系统更新功能实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)：系统更新功能实体传输对象 |

### 根据批量建立系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeaturesdtos | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)> | 系统更新功能实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeatures_id | String | 系统更新功能主键ID |
| 3 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)：系统更新功能实体传输对象 |

### 根据批量更新系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeaturesdtos | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)> | 系统更新功能实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeatures_id | String | 系统更新功能主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | ids | List<String> | 系统更新功能主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeatures_id | String | 系统更新功能主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)：系统更新功能实体传输对象 |

### 根据检查系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeaturesdto | [SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO) | 系统更新功能实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存系统更新功能
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatefeaturesdtos | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)> | 系统更新功能实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取数据集
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | context | [SysUpdateFeaturesSearchContext](#SysUpdateFeaturesSearchContext) | 系统更新功能查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)>：系统更新功能实体传输对象列表 |

### 根据查询数据集
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | context | [SysUpdateFeaturesSearchContext](#SysUpdateFeaturesSearchContext) | 系统更新功能查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysUpdateFeaturesDTO](#SysUpdateFeaturesDTO)>：系统更新功能实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysUpdateFeaturesDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | sysupdatefeaturesname | String | 允许 | 系统更新功能名称 |
| 2 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 3 | createman | String | 不可 | 建立人 |
| 4 | updateman | String | 不可 | 更新人 |
| 5 | type | String | 允许 | 更新类型 |
| 6 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | upfeatures | String | 允许 | 更新功能 |
| 8 | sysupdatefeaturesid | String | 不可 | 系统更新功能标识 |
| 9 | displayorder | Integer | 允许 | 展示顺序 |
| 10 | featuresdesc | String | 允许 | 功能描述 |
| 11 | sysupdatelogname | String | 允许 | 所属更新 |
| 12 | sysupdatelogid | String | 允许 | 系统更新日志标识 |
| 13 | <动态属性> | Object | 允许 | 支持动态属性 |

#### SysUpdateFeaturesSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_sys_update_featuresname_like | String | 允许 | 条件字段：sys_update_featuresname<br>条件组合方式：`%like%` |
| 2 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 3 | n_sys_update_featuresid_eq | String | 允许 | 条件字段：sys_update_featuresid<br>条件组合方式：`=` |
| 4 | n_sys_update_logname_eq | String | 允许 | 条件字段：sys_update_logname<br>条件组合方式：`=` |
| 5 | n_sys_update_logname_like | String | 允许 | 条件字段：sys_update_logname<br>条件组合方式：`%like%` |
| 6 | n_sys_update_logid_eq | String | 允许 | 条件字段：sys_update_logid<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
