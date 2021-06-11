# 服务接口-IBZPRO_STORY
## 接口说明
需求

## 接口清单
### 新建需求
#### 访问路径
/ibzprostories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorydto | [IBZProStoryDTO](#IBZProStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryDTO](#IBZProStoryDTO)：需求实体传输对象 |

### 批量新建需求
#### 访问路径
/ibzprostories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorydtos | List<[IBZProStoryDTO](#IBZProStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取需求
#### 访问路径
/ibzprostories/{ibzprostory_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryDTO](#IBZProStoryDTO)：需求实体传输对象 |

### 删除需求
#### 访问路径
/ibzprostories/{ibzprostory_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除需求
#### 访问路径
/ibzprostories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 需求主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新需求
#### 访问路径
/ibzprostories/{ibzprostory_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostory_id | Long | 需求主键ID |
| 2 | ibzprostorydto | [IBZProStoryDTO](#IBZProStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryDTO](#IBZProStoryDTO)：需求实体传输对象 |

### 批量更新需求
#### 访问路径
/ibzprostories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorydtos | List<[IBZProStoryDTO](#IBZProStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查需求
#### 访问路径
/ibzprostories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorydto | [IBZProStoryDTO](#IBZProStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存需求
#### 访问路径
/ibzprostories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorydto | [IBZProStoryDTO](#IBZProStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存需求
#### 访问路径
/ibzprostories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorydtos | List<[IBZProStoryDTO](#IBZProStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 同步Ibz平台需求
#### 访问路径
/ibzprostories/{ibzprostory_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostory_id | Long | 需求主键ID |
| 2 | ibzprostorydto | [IBZProStoryDTO](#IBZProStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryDTO](#IBZProStoryDTO)：需求实体传输对象 |

### 获取数据集
#### 访问路径
/ibzprostories/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBZProStorySearchContext](#IBZProStorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IBZProStoryDTO](#IBZProStoryDTO)>：需求实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzprostories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBZProStorySearchContext](#IBZProStorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IBZProStoryDTO](#IBZProStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IBZProStoryDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | version | Integer | 允许 | 版本号 |
| 2 | sourcenote | String | 允许 | 来源备注 |
| 3 | mailto | String | 允许 | 抄送给 |
| 4 | stagedby | String | 允许 | 设置阶段者 |
| 5 | deleted | String | 允许 | 已删除 |
| 6 | ibiz_sourcename | String | 允许 | 来源对象名称 |
| 7 | pri | Integer | 允许 | 优先级 |
| 8 | stage | String | 允许 | 需求阶段 |
| 9 | closedby | String | 允许 | 由谁关闭 |
| 10 | spec | String | 允许 | 需求描述 |
| 11 | closeddate | Timestamp | 允许 | 关闭日期	<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 12 | linkstories | String | 允许 | 相关需求 |
| 13 | type | String | 允许 | 需求类型 |
| 14 | duplicatestory | Integer | 允许 | 重复需求 |
| 15 | branch | Integer | 允许 | 平台 |
| 16 | keywords | String | 允许 | 关键词 |
| 17 | childstories | String | 允许 | 需求细分 |
| 18 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 19 | tobug | Integer | 允许 | 转Bug |
| 20 | comment | String | 允许 | 备注 |
| 21 | id | Long | 不可 | 编号 |
| 22 | closedreason | String | 允许 | 关闭原因 |
| 23 | project | String | 允许 | 项目 |
| 24 | frombug | Integer | 允许 | 来源Bug |
| 25 | lasteditedby | String | 允许 | 最后修改者 |
| 26 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 27 | substatus | String | 允许 | 子状态 |
| 28 | ibiz_sourceid | String | 允许 | 来源对象标识 |
| 29 | title | String | 不可 | 需求名称 |
| 30 | lastediteddate | Timestamp | 允许 | 最后修改日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 31 | ibiz_sourceobject | String | 允许 | 来源对象 |
| 32 | ibizid | String | 允许 | IBIZ标识 |
| 33 | verify | String | 允许 | 验收标准 |
| 34 | reviewedby | String | 允许 | 由谁评审 |
| 35 | assignedto | String | 允许 | 指派给 |
| 36 | source | String | 允许 | 需求来源 |
| 37 | estimate | Double | 允许 | 预计工时 |
| 38 | openedby | String | 允许 | 由谁创建 |
| 39 | revieweddate | Timestamp | 允许 | 评审时间<br>时间格式：yyyy-MM-dd |
| 40 | color | String | 允许 | 颜色 |
| 41 | status | String | 允许 | 状态 |
| 42 | product | Long | 允许 | 编号 |
| 43 | module | Long | 允许 | id |
| 44 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IBZProStorySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_sourcenote_eq | String | 允许 | 条件字段：sourcenote<br>条件组合方式：`=` |
| 2 | n_stagedby_eq | String | 允许 | 条件字段：stagedby<br>条件组合方式：`=` |
| 3 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 4 | n_stage_eq | String | 允许 | 条件字段：stage<br>条件组合方式：`=` |
| 5 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 6 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 7 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 8 | n_ibiz_sourceobject_eq | String | 允许 | 条件字段：ibiz_sourceobject<br>条件组合方式：`=` |
| 9 | n_ibiz_id_eq | String | 允许 | 条件字段：ibiz_id<br>条件组合方式：`=` |
| 10 | n_source_eq | String | 允许 | 条件字段：source<br>条件组合方式：`=` |
| 11 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 12 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 13 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 14 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 15 | customcond | String | 允许 | 自定义查询条件 |
| 16 | customparams | String | 允许 | 自定义查询参数 |
| 17 | query | String | 允许 | 快速搜索 |
| 18 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 19 | page | int | 允许 | 当前页数<br>默认值0 |
| 20 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 21 | sort | String | 允许 | 排序 |
