# 服务接口-PSSYSSFPUB
## 接口说明
后台服务架构

## 接口清单
### 新建后台服务架构
#### 访问路径
/pssyssfpubs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpubdto | [PSSysSFPubDTO](#PSSysSFPubDTO) | 后台服务架构实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSysSFPubDTO](#PSSysSFPubDTO)：后台服务架构实体传输对象 |

### 批量新建后台服务架构
#### 访问路径
/pssyssfpubs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpubdtos | List<[PSSysSFPubDTO](#PSSysSFPubDTO)> | 后台服务架构实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新后台服务架构
#### 访问路径
/pssyssfpubs/{pssyssfpub_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpub_id | String | 后台服务架构主键ID |
| 2 | pssyssfpubdto | [PSSysSFPubDTO](#PSSysSFPubDTO) | 后台服务架构实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSysSFPubDTO](#PSSysSFPubDTO)：后台服务架构实体传输对象 |

### 批量更新后台服务架构
#### 访问路径
/pssyssfpubs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpubdtos | List<[PSSysSFPubDTO](#PSSysSFPubDTO)> | 后台服务架构实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除后台服务架构
#### 访问路径
/pssyssfpubs/{pssyssfpub_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpub_id | String | 后台服务架构主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除后台服务架构
#### 访问路径
/pssyssfpubs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 后台服务架构主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取后台服务架构
#### 访问路径
/pssyssfpubs/{pssyssfpub_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpub_id | String | 后台服务架构主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSysSFPubDTO](#PSSysSFPubDTO)：后台服务架构实体传输对象 |

### 检查后台服务架构
#### 访问路径
/pssyssfpubs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpubdto | [PSSysSFPubDTO](#PSSysSFPubDTO) | 后台服务架构实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存后台服务架构
#### 访问路径
/pssyssfpubs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpubdto | [PSSysSFPubDTO](#PSSysSFPubDTO) | 后台服务架构实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存后台服务架构
#### 访问路径
/pssyssfpubs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssyssfpubdtos | List<[PSSysSFPubDTO](#PSSysSFPubDTO)> | 后台服务架构实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取版本
#### 访问路径
/pssyssfpubs/fetchbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysSFPubSearchContext](#PSSysSFPubSearchContext) | 后台服务架构查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PSSysSFPubDTO](#PSSysSFPubDTO)>：后台服务架构实体传输对象列表 |

### 查询版本
#### 访问路径
/pssyssfpubs/searchbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysSFPubSearchContext](#PSSysSFPubSearchContext) | 后台服务架构查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PSSysSFPubDTO](#PSSysSFPubDTO)>：后台服务架构实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/pssyssfpubs/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysSFPubSearchContext](#PSSysSFPubSearchContext) | 后台服务架构查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PSSysSFPubDTO](#PSSysSFPubDTO)>：后台服务架构实体传输对象列表 |

### 查询数据集
#### 访问路径
/pssyssfpubs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysSFPubSearchContext](#PSSysSFPubSearchContext) | 后台服务架构查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PSSysSFPubDTO](#PSSysSFPubDTO)>：后台服务架构实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### PSSysSFPubDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | usertag | String | 允许 | 用户标记 |
| 2 | pssystemid | String | 允许 | 系统 |
| 3 | pubfolder | String | 允许 | 服务目录 |
| 4 | pssfstyleverid | String | 允许 | 服务框架扩展 |
| 5 | usertag4 | String | 允许 | 用户标记4 |
| 6 | pubtag4 | String | 允许 | 发布标记4 |
| 7 | pssfstyleid | String | 允许 | 服务框架 |
| 8 | removeflag | Integer | 允许 | 删除模式 |
| 9 | subsyspkgflag | Integer | 允许 | 引用系统组件 |
| 10 | defaultpub | Integer | 允许 | 默认后台服务 |
| 11 | docpssfstylename | String | 允许 | 文档模板样式 |
| 12 | usertag3 | String | 允许 | 用户标记3 |
| 13 | pssfstyleparamname | String | 允许 | 服务框架参数 |
| 14 | createman | String | 不可 | 建立人 |
| 15 | codename | String | 允许 | 代码名称 |
| 16 | usertag2 | String | 允许 | 用户标记2 |
| 17 | pssyssfpubname | String | 允许 | 后台服务架构名称 |
| 18 | updateman | String | 不可 | 更新人 |
| 19 | pubtag | String | 允许 | 发布标记 |
| 20 | baseclspkgcodename | String | 允许 | 基类代码包名 |
| 21 | verstr | String | 允许 | 版本号 |
| 22 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 23 | pubtag3 | String | 允许 | 发布标记3 |
| 24 | pssfstyleparamid | String | 允许 | 服务框架参数 |
| 25 | docpssfstyleid | String | 允许 | 文档模板样式 |
| 26 | memo | String | 允许 | 备注 |
| 27 | pkgcodename | String | 允许 | 代码包名 |
| 28 | pssyssfpubid | String | 不可 | 后台服务架构标识 |
| 29 | pssystemname | String | 允许 | 系统 |
| 30 | contenttype | String | 允许 | 发布内容类型 |
| 31 | usercat | String | 允许 | 用户分类 |
| 32 | pubtag2 | String | 允许 | 发布标记2 |
| 33 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 34 | styleparams | String | 允许 | 服务框架参数 |
| 35 | ppssyssfpubname | String | 允许 | 父后台服务体系 |
| 36 | ppssyssfpubid | String | 允许 | 父后台服务体系 |
| 37 | <动态属性> | Object | 允许 | 支持动态属性 |

