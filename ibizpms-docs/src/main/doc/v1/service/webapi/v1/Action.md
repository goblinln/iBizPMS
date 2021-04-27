# 服务接口-ZT_ACTION
## 接口说明
系统日志

## 接口清单
### 新建系统日志
#### 访问路径
/actions

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ActionDTO](#ActionDTO)：系统日志实体传输对象 |

### 批量新建系统日志
#### 访问路径
/actions/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | actiondtos | List<[ActionDTO](#ActionDTO)> | 系统日志实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统日志
#### 访问路径
/actions/{action_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ActionDTO](#ActionDTO)：系统日志实体传输对象 |

### 批量更新系统日志
#### 访问路径
/actions/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | actiondtos | List<[ActionDTO](#ActionDTO)> | 系统日志实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除系统日志
#### 访问路径
/actions/{action_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统日志
#### 访问路径
/actions/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 系统日志主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统日志
#### 访问路径
/actions/{action_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ActionDTO](#ActionDTO)：系统日志实体传输对象 |

### 检查系统日志
#### 访问路径
/actions/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 添加备注
#### 访问路径
/actions/{action_id}/comment

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ActionDTO](#ActionDTO)：系统日志实体传输对象 |

### 编辑备注信息
#### 访问路径
/actions/{action_id}/editcomment

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ActionDTO](#ActionDTO)：系统日志实体传输对象 |

### Pms企业专用
#### 访问路径
/actions/{action_id}/managepmsee

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | action_id | Long | 系统日志主键ID |
| 2 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ActionDTO](#ActionDTO)：系统日志实体传输对象 |

### 保存系统日志
#### 访问路径
/actions/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | actiondto | [ActionDTO](#ActionDTO) | 系统日志实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统日志
#### 访问路径
/actions/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | actiondtos | List<[ActionDTO](#ActionDTO)> | 系统日志实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/actions/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/actions/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取MobType
#### 访问路径
/actions/fetchmobtype

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询MobType
#### 访问路径
/actions/searchmobtype

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目动态(我的)
#### 访问路径
/actions/fetchmytrends

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询项目动态(我的)
#### 访问路径
/actions/searchmytrends

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ProductTrends
#### 访问路径
/actions/fetchproducttrends

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询ProductTrends
#### 访问路径
/actions/searchproducttrends

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目动态(项目相关所有)
#### 访问路径
/actions/fetchprojecttrends

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询项目动态(项目相关所有)
#### 访问路径
/actions/searchprojecttrends

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取查询用户使用年
#### 访问路径
/actions/fetchqueryuseryear

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询查询用户使用年
#### 访问路径
/actions/searchqueryuseryear

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Type
#### 访问路径
/actions/fetchtype

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ActionDTO](#ActionDTO)>：系统日志实体传输对象列表 |

### 查询Type
#### 访问路径
/actions/searchtype

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ActionSearchContext](#ActionSearchContext) | 系统日志查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ActionDTO](#ActionDTO)>：系统日志实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ActionDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | thismonth | String | 允许 | 本月 |
| 2 | yesterday | String | 允许 | 昨天 |
| 3 | extra | String | 允许 | 附加值 |
| 4 | files | String | 允许 | 文件 |
| 5 | lastmonth | String | 允许 | 上月 |
| 6 | isactorss | Long | 允许 | 当前用户 |
| 7 | thisweek | String | 允许 | 本周 |
| 8 | today | String | 允许 | 今天 |
| 9 | date1 | String | 允许 | 显示日期 |
| 10 | objecttype | String | 允许 | 对象类型 |
| 11 | id | Long | 不可 | id |
| 12 | noticeusers | String | 允许 | 消息通知用户 |
| 13 | comment | String | 允许 | 备注 |
| 14 | read | String | 允许 | 已读 |
| 15 | action | String | 允许 | 动作 |
| 16 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 17 | product | String | 允许 | 产品 |
| 18 | lastcomment | String | 允许 | 备注 |
| 19 | srfkey | Long | 允许 | 前端键值 |
| 20 | actionmanner | String | 允许 | 操作方式 |
| 21 | lastweek | String | 允许 | 上周 |
| 22 | objectid | Long | 允许 | 对象ID |
| 23 | actor | String | 允许 | 操作者 |
| 24 | project | Long | 允许 | 项目 |
| 25 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ActionSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_thismonth_eq | String | 允许 | 条件字段：thismonth<br>条件组合方式：`=` |
| 2 | n_yesterday_eq | String | 允许 | 条件字段：yesterday<br>条件组合方式：`=` |
| 3 | n_lastmonth_eq | String | 允许 | 条件字段：lastmonth<br>条件组合方式：`=` |
| 4 | n_thisweek_eq | String | 允许 | 条件字段：thisweek<br>条件组合方式：`=` |
| 5 | n_today_eq | String | 允许 | 条件字段：today<br>条件组合方式：`=` |
| 6 | n_objecttype_eq | String | 允许 | 条件字段：objecttype<br>条件组合方式：`=` |
| 7 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 8 | n_comment_like | String | 允许 | 条件字段：comment<br>条件组合方式：`%like%` |
| 9 | n_read_eq | String | 允许 | 条件字段：read<br>条件组合方式：`=` |
| 10 | n_action_eq | String | 允许 | 条件字段：action<br>条件组合方式：`=` |
| 11 | n_actionmanner_eq | String | 允许 | 条件字段：actionmanner<br>条件组合方式：`=` |
| 12 | n_lastweek_eq | String | 允许 | 条件字段：lastweek<br>条件组合方式：`=` |
| 13 | n_objectid_eq | Long | 允许 | 条件字段：objectid<br>条件组合方式：`=` |
| 14 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 15 | customcond | String | 允许 | 自定义查询条件 |
| 16 | customparams | String | 允许 | 自定义查询参数 |
| 17 | query | String | 允许 | 快速搜索 |
| 18 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 19 | page | int | 允许 | 当前页数<br>默认值0 |
| 20 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 21 | sort | String | 允许 | 排序 |
