# 服务接口-IBIZPRO_PLUGIN
## 接口说明
系统插件

## 接口清单
### 新建系统插件
#### 访问路径
/ibizproplugins

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugindto | [IBIZProPluginDTO](#IBIZProPluginDTO) | 系统插件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProPluginDTO](#IBIZProPluginDTO)：系统插件实体传输对象 |

### 批量新建系统插件
#### 访问路径
/ibizproplugins/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugindtos | List<[IBIZProPluginDTO](#IBIZProPluginDTO)> | 系统插件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统插件
#### 访问路径
/ibizproplugins/{ibizproplugin_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugin_id | String | 系统插件主键ID |
| 2 | ibizproplugindto | [IBIZProPluginDTO](#IBIZProPluginDTO) | 系统插件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProPluginDTO](#IBIZProPluginDTO)：系统插件实体传输对象 |

### 批量更新系统插件
#### 访问路径
/ibizproplugins/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugindtos | List<[IBIZProPluginDTO](#IBIZProPluginDTO)> | 系统插件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除系统插件
#### 访问路径
/ibizproplugins/{ibizproplugin_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugin_id | String | 系统插件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统插件
#### 访问路径
/ibizproplugins/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 系统插件主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统插件
#### 访问路径
/ibizproplugins/{ibizproplugin_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugin_id | String | 系统插件主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProPluginDTO](#IBIZProPluginDTO)：系统插件实体传输对象 |

### 检查系统插件
#### 访问路径
/ibizproplugins/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugindto | [IBIZProPluginDTO](#IBIZProPluginDTO) | 系统插件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存系统插件
#### 访问路径
/ibizproplugins/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugindto | [IBIZProPluginDTO](#IBIZProPluginDTO) | 系统插件实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统插件
#### 访问路径
/ibizproplugins/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizproplugindtos | List<[IBIZProPluginDTO](#IBIZProPluginDTO)> | 系统插件实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/ibizproplugins/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProPluginSearchContext](#IBIZProPluginSearchContext) | 系统插件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IBIZProPluginDTO](#IBIZProPluginDTO)>：系统插件实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizproplugins/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProPluginSearchContext](#IBIZProPluginSearchContext) | 系统插件查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IBIZProPluginDTO](#IBIZProPluginDTO)>：系统插件实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IBIZProPluginDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | version | Integer | 允许 | 版本 |
| 2 | type | String | 允许 | 类型 |
| 3 | createman | String | 不可 | 建立人 |
| 4 | downloadcount | Integer | 允许 | 总下载量 |
| 5 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | downloadurl | String | 允许 | 最新版本下载地址 |
| 7 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 8 | tag | String | 允许 | 标签 |
| 9 | score | Integer | 允许 | 总评分 |
| 10 | ibizpropluginname | String | 允许 | 系统插件名称 |
| 11 | ibizpropluginid | String | 不可 | 系统插件标识 |
| 12 | keyword | String | 允许 | 关键字 |
| 13 | updateman | String | 不可 | 更新人 |
| 14 | commentcount | Integer | 允许 | 总评论数 |
| 15 | <动态属性> | Object | 允许 | 支持动态属性 |

