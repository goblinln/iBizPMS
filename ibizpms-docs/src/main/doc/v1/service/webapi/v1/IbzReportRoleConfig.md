# 服务接口-IBZ_REPORT_ROLE_CONFIG
## 接口说明
汇报角色配置

## 接口清单
### 新建汇报角色配置
#### 访问路径
/ibzreportroleconfigs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigdto | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO) | 汇报角色配置实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)：汇报角色配置实体传输对象 |

### 批量新建汇报角色配置
#### 访问路径
/ibzreportroleconfigs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigdtos | List<[IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)> | 汇报角色配置实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取汇报角色配置
#### 访问路径
/ibzreportroleconfigs/{ibzreportroleconfig_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfig_id | String | 汇报角色配置主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)：汇报角色配置实体传输对象 |

### 删除汇报角色配置
#### 访问路径
/ibzreportroleconfigs/{ibzreportroleconfig_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfig_id | String | 汇报角色配置主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除汇报角色配置
#### 访问路径
/ibzreportroleconfigs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 汇报角色配置主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新汇报角色配置
#### 访问路径
/ibzreportroleconfigs/{ibzreportroleconfig_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfig_id | String | 汇报角色配置主键ID |
| 2 | ibzreportroleconfigdto | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO) | 汇报角色配置实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)：汇报角色配置实体传输对象 |

### 批量更新汇报角色配置
#### 访问路径
/ibzreportroleconfigs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigdtos | List<[IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)> | 汇报角色配置实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查汇报角色配置
#### 访问路径
/ibzreportroleconfigs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigdto | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO) | 汇报角色配置实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存汇报角色配置
#### 访问路径
/ibzreportroleconfigs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigdto | [IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO) | 汇报角色配置实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存汇报角色配置
#### 访问路径
/ibzreportroleconfigs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigdtos | List<[IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)> | 汇报角色配置实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibzreportroleconfigs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportRoleConfigSearchContext](#IbzReportRoleConfigSearchContext) | 汇报角色配置查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)>：汇报角色配置实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzreportroleconfigs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzReportRoleConfigSearchContext](#IbzReportRoleConfigSearchContext) | 汇报角色配置查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzReportRoleConfigDTO](#IbzReportRoleConfigDTO)>：汇报角色配置实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzReportRoleConfigDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | ibzreportroleconfigname | String | 允许 | 汇报角色配置名称 |
| 2 | ibzreportroleconfigid | String | 不可 | 汇报角色配置标识 |
| 3 | report_role | String | 允许 | 角色 |
| 4 | type | String | 允许 | 类型 |
| 5 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | createman | String | 不可 | 建立人 |
| 8 | updateman | String | 不可 | 更新人 |
| 9 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzReportRoleConfigSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibz_report_role_configname_like | String | 允许 | 条件字段：ibz_report_role_configname<br>条件组合方式：`%like%` |
| 2 | n_ibz_report_role_configid_eq | String | 允许 | 条件字段：ibz_report_role_configid<br>条件组合方式：`=` |
| 3 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
