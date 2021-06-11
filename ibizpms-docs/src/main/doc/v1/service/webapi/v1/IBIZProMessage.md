# 服务接口-IBIZPRO_MESSAGE
## 接口说明
消息

## 接口清单
### 新建消息
#### 访问路径
/ibizpromessages

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProMessageDTO](#IBIZProMessageDTO)：消息实体传输对象 |

### 批量新建消息
#### 访问路径
/ibizpromessages/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessagedtos | List<[IBIZProMessageDTO](#IBIZProMessageDTO)> | 消息实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取消息
#### 访问路径
/ibizpromessages/{ibizpromessage_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessage_id | String | 消息主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProMessageDTO](#IBIZProMessageDTO)：消息实体传输对象 |

### 删除消息
#### 访问路径
/ibizpromessages/{ibizpromessage_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessage_id | String | 消息主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除消息
#### 访问路径
/ibizpromessages/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 消息主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新消息
#### 访问路径
/ibizpromessages/{ibizpromessage_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessage_id | String | 消息主键ID |
| 2 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProMessageDTO](#IBIZProMessageDTO)：消息实体传输对象 |

### 批量更新消息
#### 访问路径
/ibizpromessages/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessagedtos | List<[IBIZProMessageDTO](#IBIZProMessageDTO)> | 消息实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查消息
#### 访问路径
/ibizpromessages/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 标记已完成
#### 访问路径
/ibizpromessages/{ibizpromessage_id}/markdone

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessage_id | String | 消息主键ID |
| 2 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProMessageDTO](#IBIZProMessageDTO)：消息实体传输对象 |

### 标记已读
#### 访问路径
/ibizpromessages/{ibizpromessage_id}/markread

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessage_id | String | 消息主键ID |
| 2 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProMessageDTO](#IBIZProMessageDTO)：消息实体传输对象 |

### 保存消息
#### 访问路径
/ibizpromessages/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存消息
#### 访问路径
/ibizpromessages/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessagedtos | List<[IBIZProMessageDTO](#IBIZProMessageDTO)> | 消息实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 发送消息
#### 访问路径
/ibizpromessages/{ibizpromessage_id}/send

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibizpromessage_id | String | 消息主键ID |
| 2 | ibizpromessagedto | [IBIZProMessageDTO](#IBIZProMessageDTO) | 消息实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IBIZProMessageDTO](#IBIZProMessageDTO)：消息实体传输对象 |

### 获取数据集
#### 访问路径
/ibizpromessages/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProMessageSearchContext](#IBIZProMessageSearchContext) | 消息查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IBIZProMessageDTO](#IBIZProMessageDTO)>：消息实体传输对象列表 |

### 查询数据集
#### 访问路径
/ibizpromessages/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProMessageSearchContext](#IBIZProMessageSearchContext) | 消息查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IBIZProMessageDTO](#IBIZProMessageDTO)>：消息实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户全部消息
#### 访问路径
/ibizpromessages/fetchuserallmessages

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProMessageSearchContext](#IBIZProMessageSearchContext) | 消息查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IBIZProMessageDTO](#IBIZProMessageDTO)>：消息实体传输对象列表 |

### 查询用户全部消息
#### 访问路径
/ibizpromessages/searchuserallmessages

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProMessageSearchContext](#IBIZProMessageSearchContext) | 消息查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IBIZProMessageDTO](#IBIZProMessageDTO)>：消息实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户未读信息
#### 访问路径
/ibizpromessages/fetchuserunreadmessages

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProMessageSearchContext](#IBIZProMessageSearchContext) | 消息查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IBIZProMessageDTO](#IBIZProMessageDTO)>：消息实体传输对象列表 |

### 查询用户未读信息
#### 访问路径
/ibizpromessages/searchuserunreadmessages

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IBIZProMessageSearchContext](#IBIZProMessageSearchContext) | 消息查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IBIZProMessageDTO](#IBIZProMessageDTO)>：消息实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IBIZProMessageDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | sendproxyid | String | 允许 | 发送代理标识 |
| 2 | ibizpromessagename | String | 允许 | 消息名称 |
| 3 | islink | Integer | 允许 | 是否是链接消息 |
| 4 | subject | String | 允许 | 标题 |
| 5 | retryintervaltime | Integer | 允许 | 重发间隔时间 |
| 6 | bcc | String | 允许 | 密件抄送方 |
| 7 | isdone | Integer | 允许 | 是否完成 |
| 8 | param | String | 允许 | 消息参数 |
| 9 | ibizpromessageid | String | 允许 | 消息标识 |
| 10 | retrytimes | Integer | 允许 | 重发次数 |
| 11 | issync | Integer | 允许 | 是否同步 |
| 12 | from | String | 允许 | 发送方 |
| 13 | cc | String | 允许 | 抄送方 |
| 14 | sendtime | Timestamp | 允许 | 发送时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 15 | content | String | 允许 | 内容 |
| 16 | linkurl | String | 允许 | 链接地址 |
| 17 | isread | Integer | 允许 | 是否已读 |
| 18 | isretry | Integer | 允许 | 是否重发 |
| 19 | type | String | 允许 | 消息类型 |
| 20 | to | String | 允许 | 接收方 |
| 21 | <动态属性> | Object | 允许 | 支持动态属性 |

