# 服务接口-IBZ_CASE
## 接口说明
测试用例

## 接口清单
### 新建测试用例
#### 访问路径
/ibzcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzCaseDTO](#IbzCaseDTO)：测试用例实体传输对象 |

### 批量新建测试用例
#### 访问路径
/ibzcases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcasedtos | List<[IbzCaseDTO](#IbzCaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试用例
#### 访问路径
/ibzcases/{ibzcase_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzCaseDTO](#IbzCaseDTO)：测试用例实体传输对象 |

### 批量更新测试用例
#### 访问路径
/ibzcases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcasedtos | List<[IbzCaseDTO](#IbzCaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除测试用例
#### 访问路径
/ibzcases/{ibzcase_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试用例
#### 访问路径
/ibzcases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 测试用例主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取测试用例
#### 访问路径
/ibzcases/{ibzcase_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzCaseDTO](#IbzCaseDTO)：测试用例实体传输对象 |

### 检查测试用例
#### 访问路径
/ibzcases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存测试用例
#### 访问路径
/ibzcases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试用例
#### 访问路径
/ibzcases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcasedtos | List<[IbzCaseDTO](#IbzCaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/ibzcases/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzCaseSearchContext](#IbzCaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzCaseDTO](#IbzCaseDTO)>：测试用例实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/ibzcases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzCaseSearchContext](#IbzCaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzCaseDTO](#IbzCaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzCaseDTO](#IbzCaseDTO)：测试用例实体传输对象 |

### 根据批量建立测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcasedtos | List<[IbzCaseDTO](#IbzCaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzCaseDTO](#IbzCaseDTO)：测试用例实体传输对象 |

### 根据批量更新测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcasedtos | List<[IbzCaseDTO](#IbzCaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcase_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ids | List<Long> | 测试用例主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcase_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzCaseDTO](#IbzCaseDTO)：测试用例实体传输对象 |

### 根据检查测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcasedto | [IbzCaseDTO](#IbzCaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试用例
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | ibzcasedtos | List<[IbzCaseDTO](#IbzCaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | context | [IbzCaseSearchContext](#IbzCaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzCaseDTO](#IbzCaseDTO)>：测试用例实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |
| 2 | context | [IbzCaseSearchContext](#IbzCaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzCaseDTO](#IbzCaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzCaseDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | lasteditedby | String | 允许 | 最后修改者 |
| 2 | path | Integer | 允许 | path |
| 3 | id | Long | 不可 | 用例编号 |
| 4 | scriptedby | String | 允许 | scriptedBy |
| 5 | type | String | 允许 | 用例类型 |
| 6 | scriptstatus | String | 允许 | scriptStatus |
| 7 | stage | String | 允许 | 适用阶段 |
| 8 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 9 | lastediteddate | Timestamp | 允许 | 修改日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 10 | auto | String | 允许 | auto |
| 11 | title | String | 不可 | 用例标题 |
| 12 | howrun | String | 允许 | howRun |
| 13 | pri | String | 允许 | 优先级 |
| 14 | comment | String | 允许 | 备注 |
| 15 | keywords | String | 允许 | 关键词 |
| 16 | scriptlocation | String | 允许 | scriptLocation |
| 17 | version | Integer | 允许 | 用例版本 |
| 18 | status | String | 允许 | 状态 |
| 19 | precondition | String | 允许 | 前置条件 |
| 20 | deleted | String | 允许 | 已删除 |
| 21 | order | Integer | 允许 | 排序 |
| 22 | openedby | String | 允许 | 由谁创建 |
| 23 | scripteddate | Timestamp | 允许 | scriptedDate<br>时间格式：yyyy-MM-dd |
| 24 | libname | String | 允许 | 用例库 |
| 25 | modulename | String | 允许 | 所属模块 |
| 26 | module | Long | 允许 | id |
| 27 | lib | Long | 允许 | 编号 |
| 28 | ibzlibcasesteptmps | List<[IbzLibCaseStepTmpDTO}](#IbzLibCaseStepTmpDTO})> | 允许 | 用例库用例步骤 |
| 29 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzLibCaseStepTmpDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | reals | String | 允许 | 实际情况 |
| 2 | expect | String | 允许 | 预期 |
| 3 | desc | String | 允许 | 步骤 |
| 4 | files | String | 允许 | 附件 |
| 5 | id | Long | 不可 | 编号 |
| 6 | type | String | 允许 | 类型 |
| 7 | version | Integer | 允许 | 版本 |
| 8 | parent | Long | 允许 | 编号 |
| 9 | ibizcase | Long | 允许 | 用例编号 |
| 10 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzCaseSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 4 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 5 | n_pri_eq | String | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 6 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 7 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 8 | n_libname_eq | String | 允许 | 条件字段：libname<br>条件组合方式：`=` |
| 9 | n_libname_like | String | 允许 | 条件字段：libname<br>条件组合方式：`%like%` |
| 10 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 11 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 12 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 13 | n_lib_eq | Long | 允许 | 条件字段：lib<br>条件组合方式：`=` |
| 14 | customcond | String | 允许 | 自定义查询条件 |
| 15 | customparams | String | 允许 | 自定义查询参数 |
| 16 | query | String | 允许 | 快速搜索 |
| 17 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 18 | page | int | 允许 | 当前页数<br>默认值0 |
| 19 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 20 | sort | String | 允许 | 排序 |
