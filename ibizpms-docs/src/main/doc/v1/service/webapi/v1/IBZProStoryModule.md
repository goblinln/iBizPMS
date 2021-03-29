# 服务接口-IBZPRO_STORYMODULE
## 接口说明
需求模块

## 接口清单
### 新建需求模块
#### 访问路径
/ibzprostorymodules

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymoduledto | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO)：需求模块实体传输对象 |

### 批量新建需求模块
#### 访问路径
/ibzprostorymodules/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymoduledtos | List<[IBZProStoryModuleDTO](#IBZProStoryModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新需求模块
#### 访问路径
/ibzprostorymodules/{ibzprostorymodule_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymodule_id | Long | 需求模块主键ID |
| 2 | ibzprostorymoduledto | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO)：需求模块实体传输对象 |

### 批量更新需求模块
#### 访问路径
/ibzprostorymodules/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymoduledtos | List<[IBZProStoryModuleDTO](#IBZProStoryModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除需求模块
#### 访问路径
/ibzprostorymodules/{ibzprostorymodule_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymodule_id | Long | 需求模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除需求模块
#### 访问路径
/ibzprostorymodules/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 需求模块主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取需求模块
#### 访问路径
/ibzprostorymodules/{ibzprostorymodule_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymodule_id | Long | 需求模块主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO)：需求模块实体传输对象 |

### 检查需求模块
#### 访问路径
/ibzprostorymodules/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymoduledto | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存需求模块
#### 访问路径
/ibzprostorymodules/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymoduledto | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存需求模块
#### 访问路径
/ibzprostorymodules/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymoduledtos | List<[IBZProStoryModuleDTO](#IBZProStoryModuleDTO)> | 需求模块实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 同步Ibz平台模块
#### 访问路径
/ibzprostorymodules/{ibzprostorymodule_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprostorymodule_id | Long | 需求模块主键ID |
| 2 | ibzprostorymoduledto | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO) | 需求模块实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBZProStoryModuleDTO](#IBZProStoryModuleDTO)：需求模块实体传输对象 |

### 获取数据集
#### 访问路径
/ibzprostorymodules/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBZProStoryModuleSearchContext](#IBZProStoryModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IBZProStoryModuleDTO](#IBZProStoryModuleDTO)>：需求模块实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibzprostorymodules/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBZProStoryModuleSearchContext](#IBZProStoryModuleSearchContext) | 需求模块查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IBZProStoryModuleDTO](#IBZProStoryModuleDTO)>：需求模块实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IBZProStoryModuleDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | grade | Integer | 允许 | 级别 |
| 2 | collector | String | 允许 | collector |
| 3 | owner | String | 允许 | owner |
| 4 | ibiz_storytype | String | 允许 | 需求模块类型 |
| 5 | id | Long | 不可 | id |
| 6 | type | String | 允许 | 类型 |
| 7 | ibizshort | String | 允许 | 简称 |
| 8 | name | String | 不可 | 名称 |
| 9 | deleted | String | 允许 | 已删除 |
| 10 | path | String | 允许 | 路径 |
| 11 | ibizid | String | 允许 | IBIZ标识 |
| 12 | productname | String | 允许 | 产品 |
| 13 | root | Long | 允许 | 编号 |
| 14 | parent | Long | 允许 | id |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IBZProStoryModuleSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_ibiz_storytype_eq | String | 允许 | 条件字段：ibiz_storytype<br>条件组合方式：`=` |
| 2 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 3 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 4 | n_ibiz_id_eq | String | 允许 | 条件字段：ibiz_id<br>条件组合方式：`=` |
| 5 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 6 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 7 | n_root_eq | Long | 允许 | 条件字段：root<br>条件组合方式：`=` |
| 8 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 9 | customcond | String | 允许 | 自定义查询条件 |
| 10 | customparams | String | 允许 | 自定义查询参数 |
| 11 | query | String | 允许 | 快速搜索 |
| 12 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 13 | page | int | 允许 | 当前页数<br>默认值0 |
| 14 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 15 | sort | String | 允许 | 排序 |
