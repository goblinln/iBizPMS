# 服务接口-SYS_UPDATE_LOG
## 接口说明
更新日志

## 接口清单
### 新建更新日志
#### 访问路径
/sysupdatelogs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelogdto | [SysUpdateLogDTO](#SysUpdateLogDTO) | 更新日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateLogDTO](#SysUpdateLogDTO)：更新日志实体传输对象 |

### 批量新建更新日志
#### 访问路径
/sysupdatelogs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelogdtos | List<[SysUpdateLogDTO](#SysUpdateLogDTO)> | 更新日志实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新更新日志
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatelogdto | [SysUpdateLogDTO](#SysUpdateLogDTO) | 更新日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateLogDTO](#SysUpdateLogDTO)：更新日志实体传输对象 |

### 批量更新更新日志
#### 访问路径
/sysupdatelogs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelogdtos | List<[SysUpdateLogDTO](#SysUpdateLogDTO)> | 更新日志实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除更新日志
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除更新日志
#### 访问路径
/sysupdatelogs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 更新日志主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取更新日志
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateLogDTO](#SysUpdateLogDTO)：更新日志实体传输对象 |

### 检查更新日志
#### 访问路径
/sysupdatelogs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelogdto | [SysUpdateLogDTO](#SysUpdateLogDTO) | 更新日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取最新更新信息
#### 访问路径
/sysupdatelogs/{sysupdatelog_id}/getlastupdateinfo

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelog_id | String | 更新日志主键ID |
| 2 | sysupdatelogdto | [SysUpdateLogDTO](#SysUpdateLogDTO) | 更新日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysUpdateLogDTO](#SysUpdateLogDTO)：更新日志实体传输对象 |

### 保存更新日志
#### 访问路径
/sysupdatelogs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelogdto | [SysUpdateLogDTO](#SysUpdateLogDTO) | 更新日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存更新日志
#### 访问路径
/sysupdatelogs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysupdatelogdtos | List<[SysUpdateLogDTO](#SysUpdateLogDTO)> | 更新日志实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/sysupdatelogs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUpdateLogSearchContext](#SysUpdateLogSearchContext) | 更新日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysUpdateLogDTO](#SysUpdateLogDTO)>：更新日志实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysupdatelogs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysUpdateLogSearchContext](#SysUpdateLogSearchContext) | 更新日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysUpdateLogDTO](#SysUpdateLogDTO)>：更新日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysUpdateLogDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 2 | updateman | String | 不可 | 更新人 |
| 3 | createman | String | 不可 | 建立人 |
| 4 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 5 | sysupdatelogid | String | 不可 | 系统更新日志标识 |
| 6 | updatebranch | String | 允许 | 更新平台 |
| 7 | updesc | String | 允许 | 更新说明 |
| 8 | sysupdatelogname | String | 允许 | 更新名称 |
| 9 | latestupdate | Integer | 允许 | 最新更新 |
| 10 | update | Timestamp | 允许 | 更新日期<br>时间格式：yyyy-MM-dd |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

#### SysUpdateLogSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_updatebranch_eq | String | 允许 | 条件字段：updatebranch<br>条件组合方式：`=` |
| 2 | n_sys_update_logname_like | String | 允许 | 条件字段：sys_update_logname<br>条件组合方式：`%like%` |
| 3 | n_latestupdate_eq | Integer | 允许 | 条件字段：latestupdate<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
