# 服务接口-PSSYSAPP
## 接口说明
系统应用

## 接口清单
### 新建系统应用
#### 访问路径
/pssysapps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysappdto | [PSSysAppDTO](#PSSysAppDTO) | 系统应用实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSysAppDTO](#PSSysAppDTO)：系统应用实体传输对象 |

### 批量新建系统应用
#### 访问路径
/pssysapps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysappdtos | List<[PSSysAppDTO](#PSSysAppDTO)> | 系统应用实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取系统应用
#### 访问路径
/pssysapps/{pssysapp_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysapp_id | String | 系统应用主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSysAppDTO](#PSSysAppDTO)：系统应用实体传输对象 |

### 删除系统应用
#### 访问路径
/pssysapps/{pssysapp_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysapp_id | String | 系统应用主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除系统应用
#### 访问路径
/pssysapps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 系统应用主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新系统应用
#### 访问路径
/pssysapps/{pssysapp_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysapp_id | String | 系统应用主键ID |
| 2 | pssysappdto | [PSSysAppDTO](#PSSysAppDTO) | 系统应用实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [PSSysAppDTO](#PSSysAppDTO)：系统应用实体传输对象 |

### 批量更新系统应用
#### 访问路径
/pssysapps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysappdtos | List<[PSSysAppDTO](#PSSysAppDTO)> | 系统应用实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查系统应用
#### 访问路径
/pssysapps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysappdto | [PSSysAppDTO](#PSSysAppDTO) | 系统应用实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存系统应用
#### 访问路径
/pssysapps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysappdto | [PSSysAppDTO](#PSSysAppDTO) | 系统应用实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存系统应用
#### 访问路径
/pssysapps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | pssysappdtos | List<[PSSysAppDTO](#PSSysAppDTO)> | 系统应用实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取版本
#### 访问路径
/pssysapps/fetchbuild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysAppSearchContext](#PSSysAppSearchContext) | 系统应用查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PSSysAppDTO](#PSSysAppDTO)>：系统应用实体传输对象列表 |

### 查询版本
#### 访问路径
/pssysapps/searchbuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysAppSearchContext](#PSSysAppSearchContext) | 系统应用查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PSSysAppDTO](#PSSysAppDTO)>：系统应用实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/pssysapps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysAppSearchContext](#PSSysAppSearchContext) | 系统应用查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[PSSysAppDTO](#PSSysAppDTO)>：系统应用实体传输对象列表 |

### 查询数据集
#### 访问路径
/pssysapps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [PSSysAppSearchContext](#PSSysAppSearchContext) | 系统应用查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[PSSysAppDTO](#PSSysAppDTO)>：系统应用实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### PSSysAppDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | uaclogin | Integer | 允许 | 启用统一认证 |
| 2 | pspfid | String | 不可 | 前台技术架构 |
| 3 | usertag | String | 允许 | 用户标记 |
| 4 | autoaddappview | Integer | 允许 | 自动添加应用视图 |
| 5 | enalocalservice | Integer | 允许 | 启用本地服务 |
| 6 | usertag2 | String | 允许 | 用户标记2 |
| 7 | usertag4 | String | 允许 | 用户标记4 |
| 8 | pssystemid | String | 允许 | 系统 |
| 9 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | pubsysrefviewonly | Integer | 允许 | 只发布系统引用视图（废弃） |
| 11 | mainmenuside | String | 允许 | 视图主菜单方向 |
| 12 | pssysappid | String | 不可 | 系统应用标识 |
| 13 | codefolder | String | 允许 | 代码目录 |
| 14 | gridforcefit | Integer | 允许 | 表格适应屏宽 |
| 15 | updateman | String | 不可 | 更新人 |
| 16 | finoprivdm | Integer | 允许 | 表单项无权限显示模式 |
| 17 | userparams | String | 允许 | 自定义参数 |
| 18 | orientationmode | String | 允许 | 移动端方向设置 |
| 19 | memo | String | 允许 | 备注 |
| 20 | removeflag | Integer | 允许 | 删除模式 |
| 21 | enablestoryboard | Integer | 允许 | 启用故事板 |
| 22 | psstudiothemename | String | 允许 | 应用主题 |
| 23 | enabledynasys | Integer | 允许 | 支持动态系统 |
| 24 | validflag | Integer | 允许 | 是否启用 |
| 25 | iconfile | String | 允许 | 图标文件 |
| 26 | apptag | String | 允许 | 应用标记 |
| 27 | defaultpub | Integer | 允许 | 默认应用 |
| 28 | apptag2 | String | 允许 | 应用标记2 |
| 29 | servicecodename | String | 允许 | 服务代码名称 |
| 30 | preventxss | Integer | 允许 | 防止XSS攻击 |
| 31 | psapptypename | String | 不可 | 应用类型 |
| 32 | pspfcdnid | String | 允许 | 应用CDN |
| 33 | gridcolenablelink | Integer | 允许 | 表格列启用链接 |
| 34 | psstudiothemeid | String | 允许 | 应用主题 |
| 35 | enablec12toc24 | Integer | 允许 | 转换12列至24列布局 |
| 36 | appsn | String | 允许 | 应用编号 |
| 37 | apptag4 | String | 允许 | 应用标记4 |
| 38 | createman | String | 不可 | 建立人 |
| 39 | gridrowactivemode | Integer | 允许 | 表格行激活模式 |
| 40 | usertag3 | String | 允许 | 用户标记3 |
| 41 | apppkgname | String | 不可 | 代码名称 |
| 42 | logicname | String | 允许 | 中文名称 |
| 43 | pspfstyleid | String | 不可 | 应用样式 |
| 44 | btnnoprivdm | Integer | 允许 | 按钮无权限显示模式 |
| 45 | pspfcdnname | String | 允许 | 应用CDN |
| 46 | pubrefviewonly | Integer | 允许 | 只发布引用视图 |
| 47 | appmode | String | 允许 | 应用模式 |
| 48 | pssysappname | String | 允许 | 系统应用名称 |
| 49 | psapptypeid | String | 允许 | 应用类型 |
| 50 | appfolder | String | 允许 | 应用目录 |
| 51 | startpagefile | String | 允许 | 起始页图片文件 |
| 52 | usercat | String | 允许 | 用户分类 |
| 53 | apptag3 | String | 允许 | 应用标记3 |
| 54 | pfstyleparam | String | 允许 | 应用样式参数 |
| 55 | pssystemname | String | 允许 | 系统 |
| 56 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 57 | uistyle | String | 允许 | 内建界面式样 |
| 58 | gcnoprivdm | Integer | 允许 | 表格列无权限显示模式 |
| 59 | fiupdateprivtag | Integer | 允许 | 输出表单项更新权限标记 |
| 60 | pssysserviceapiname | String | 允许 | 默认服务接口 |
| 61 | pssysserviceapiid | String | 允许 | 默认服务接口 |
| 62 | <动态属性> | Object | 允许 | 支持动态属性 |

