# 服务接口-IBZ_LIBCASESTEPS
## 接口说明
用例库用例步骤

## 接口清单
### 根据建立用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)：用例库用例步骤实体传输对象 |

### 根据批量建立用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasestepsdtos | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)> | 用例库用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasesteps_id | Long | 用例库用例步骤主键ID |
| 3 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)：用例库用例步骤实体传输对象 |

### 根据批量更新用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasestepsdtos | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)> | 用例库用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasesteps_id | Long | 用例库用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ids | List<Long> | 用例库用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasesteps_id | Long | 用例库用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)：用例库用例步骤实体传输对象 |

### 根据检查用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存用例库用例步骤
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | ibzlibcasestepsdtos | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)> | 用例库用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | context | [IbzLibCaseStepsSearchContext](#IbzLibCaseStepsSearchContext) | 用例库用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)>：用例库用例步骤实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/ibzcases/{ibzcase_id}/ibzlibcasesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzcase_id | Long | 测试用例主键ID |
| 2 | context | [IbzLibCaseStepsSearchContext](#IbzLibCaseStepsSearchContext) | 用例库用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)>：用例库用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)：用例库用例步骤实体传输对象 |

### 根据批量建立用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasestepsdtos | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)> | 用例库用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasesteps_id | Long | 用例库用例步骤主键ID |
| 4 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)：用例库用例步骤实体传输对象 |

### 根据批量更新用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasestepsdtos | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)> | 用例库用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasesteps_id | Long | 用例库用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ids | List<Long> | 用例库用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasesteps_id | Long | 用例库用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)：用例库用例步骤实体传输对象 |

### 根据检查用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasestepsdto | [IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO) | 用例库用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存用例库用例步骤
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | ibzlibcasestepsdtos | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)> | 用例库用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取DEFAULT
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | context | [IbzLibCaseStepsSearchContext](#IbzLibCaseStepsSearchContext) | 用例库用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)>：用例库用例步骤实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlib_id | Long | 用例库主键ID |/r/n| 2 | ibzcase_id | Long | 测试用例主键ID |
| 3 | context | [IbzLibCaseStepsSearchContext](#IbzLibCaseStepsSearchContext) | 用例库用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzLibCaseStepsDTO](#IbzLibCaseStepsDTO)>：用例库用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzLibCaseStepsDTO
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

#### IbzLibCaseStepsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_expect_like | String | 允许 | 条件字段：expect<br>条件组合方式：`%like%` |
| 2 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 3 | n_version_eq | Integer | 允许 | 条件字段：version<br>条件组合方式：`=` |
| 4 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 5 | n_case_eq | Long | 允许 | 条件字段：case<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
