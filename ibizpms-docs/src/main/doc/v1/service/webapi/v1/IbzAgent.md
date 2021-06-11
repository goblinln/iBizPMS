# 服务接口-IBZ_AGENT
## 接口说明
代理

## 接口清单
### 新建代理
#### 访问路径
/ibzagents

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagentdto | [IbzAgentDTO](#IbzAgentDTO) | 代理实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzAgentDTO](#IbzAgentDTO)：代理实体传输对象 |

### 批量新建代理
#### 访问路径
/ibzagents/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagentdtos | List<[IbzAgentDTO](#IbzAgentDTO)> | 代理实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取代理
#### 访问路径
/ibzagents/{ibzagent_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagent_id | Long | 代理主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzAgentDTO](#IbzAgentDTO)：代理实体传输对象 |

### 删除代理
#### 访问路径
/ibzagents/{ibzagent_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagent_id | Long | 代理主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除代理
#### 访问路径
/ibzagents/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 代理主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新代理
#### 访问路径
/ibzagents/{ibzagent_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagent_id | Long | 代理主键ID |
| 2 | ibzagentdto | [IbzAgentDTO](#IbzAgentDTO) | 代理实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzAgentDTO](#IbzAgentDTO)：代理实体传输对象 |

### 批量更新代理
#### 访问路径
/ibzagents/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagentdtos | List<[IbzAgentDTO](#IbzAgentDTO)> | 代理实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查代理
#### 访问路径
/ibzagents/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagentdto | [IbzAgentDTO](#IbzAgentDTO) | 代理实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存代理
#### 访问路径
/ibzagents/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagentdto | [IbzAgentDTO](#IbzAgentDTO) | 代理实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存代理
#### 访问路径
/ibzagents/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzagentdtos | List<[IbzAgentDTO](#IbzAgentDTO)> | 代理实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibzagents/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzAgentSearchContext](#IbzAgentSearchContext) | 代理查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzAgentDTO](#IbzAgentDTO)>：代理实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzagents/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzAgentSearchContext](#IbzAgentSearchContext) | 代理查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzAgentDTO](#IbzAgentDTO)>：代理实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzAgentDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | updateman | String | 不可 | 更新人 |
| 2 | createmanname | String | 允许 | 创建人姓名 |
| 3 | agentend | Timestamp | 允许 | 代理结束日期<br>时间格式：yyyy-MM-dd |
| 4 | ibzagentid | Long | 不可 | 代理标识 |
| 5 | createman | String | 不可 | 建立人 |
| 6 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | agentuser | String | 允许 | 代理用户 |
| 8 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 9 | agentbegin | Timestamp | 允许 | 代理开始日期<br>时间格式：yyyy-MM-dd |
| 10 | ibzagentname | String | 允许 | 代理名称 |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzAgentSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_createmanname_eq | String | 允许 | 条件字段：createmanname<br>条件组合方式：`=` |
| 2 | n_ibz_agentid_eq | Long | 允许 | 条件字段：ibz_agentid<br>条件组合方式：`=` |
| 3 | n_ibz_agentname_like | String | 允许 | 条件字段：ibz_agentname<br>条件组合方式：`%like%` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
