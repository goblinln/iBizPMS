# 服务接口-ZT_CASE
## 接口说明
测试用例

## 接口清单
### 新建测试用例
#### 访问路径
/cases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 批量新建测试用例
#### 访问路径
/cases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新测试用例
#### 访问路径
/cases/{case_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 批量更新测试用例
#### 访问路径
/cases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除测试用例
#### 访问路径
/cases/{case_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除测试用例
#### 访问路径
/cases/batch

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
/cases/{case_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 行为
#### 访问路径
/cases/{case_id}/casefavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### CaseNFavorite
#### 访问路径
/cases/{case_id}/casenfavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 检查测试用例
#### 访问路径
/cases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 确认用例变更
#### 访问路径
/cases/{case_id}/confirmchange

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 确认需求变更
#### 访问路径
/cases/{case_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据测试单获取或者状态
#### 访问路径
/cases/{case_id}/getbytesttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 获取测试单执行结果
#### 访问路径
/cases/{case_id}/gettesttaskcntrun

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 测试单关联测试用例
#### 访问路径
/cases/{case_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移动端关联需求
#### 访问路径
/cases/{case_id}/moblinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 执行测试
#### 访问路径
/cases/{case_id}/runcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### runCases
#### 访问路径
/cases/{case_id}/runcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 保存测试用例
#### 访问路径
/cases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存测试用例
#### 访问路径
/cases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 执行测试
#### 访问路径
/cases/{case_id}/testruncase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### testRunCases
#### 访问路径
/cases/{case_id}/testruncases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 套件关联
#### 访问路径
/cases/{case_id}/testsuitelinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/cases/{case_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkCases
#### 访问路径
/cases/{case_id}/unlinkcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/cases/{case_id}/unlinksuitecase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkSuiteCases
#### 访问路径
/cases/{case_id}/unlinksuitecases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 获取批量新建用例
#### 访问路径
/cases/fetchbatchnew

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询批量新建用例
#### 访问路径
/cases/searchbatchnew

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取累计创建的用例
#### 访问路径
/cases/fetchcuropenedcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询累计创建的用例
#### 访问路径
/cases/searchcuropenedcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取套件关联用例
#### 访问路径
/cases/fetchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询套件关联用例
#### 访问路径
/cases/searchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试单关联用例
#### 访问路径
/cases/fetchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试单关联用例
#### 访问路径
/cases/searchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/cases/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/cases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/cases/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/cases/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联用例
#### 访问路径
/cases/fetchmodulereportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联用例
#### 访问路径
/cases/searchmodulereportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联-按模块-条目
#### 访问路径
/cases/fetchmodulereportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联-按模块-条目
#### 访问路径
/cases/searchmodulereportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目报告关联-按模块
#### 访问路径
/cases/fetchmodulereportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询项目报告关联-按模块
#### 访问路径
/cases/searchmodulereportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/cases/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/cases/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取套件关联用例
#### 访问路径
/cases/fetchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询套件关联用例
#### 访问路径
/cases/searchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试单关联用例
#### 访问路径
/cases/fetchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试单关联用例
#### 访问路径
/cases/searchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试单关联用例（项目关联）
#### 访问路径
/cases/fetchnotcurtesttaskproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试单关联用例（项目关联）
#### 访问路径
/cases/searchnotcurtesttaskproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联用例
#### 访问路径
/cases/fetchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联用例
#### 访问路径
/cases/searchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联用例-条目
#### 访问路径
/cases/fetchreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联用例-条目
#### 访问路径
/cases/searchreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目报告关联用例-关联用例
#### 访问路径
/cases/fetchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询项目报告关联用例-关联用例
#### 访问路径
/cases/searchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联-执行人
#### 访问路径
/cases/fetchrunerreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联-执行人
#### 访问路径
/cases/searchrunerreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联-执行人-条目
#### 访问路径
/cases/fetchrunerreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联-执行人-条目
#### 访问路径
/cases/searchrunerreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目报告关联-执行人
#### 访问路径
/cases/fetchrunerreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询项目报告关联-执行人
#### 访问路径
/cases/searchrunerreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联用例
#### 访问路径
/cases/fetchrunreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联用例
#### 访问路径
/cases/searchrunreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联--执行结果条目
#### 访问路径
/cases/fetchrunreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询测试报告关联--执行结果条目
#### 访问路径
/cases/searchrunreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目报告关联-执行结果
#### 访问路径
/cases/fetchrunreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 查询项目报告关联-执行结果
#### 访问路径
/cases/searchrunreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试用例
#### 访问路径
/products/{product_id}/cases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据批量建立测试用例
#### 访问路径
/products/{product_id}/cases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试用例
#### 访问路径
/products/{product_id}/cases/{case_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据批量更新测试用例
#### 访问路径
/products/{product_id}/cases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试用例
#### 访问路径
/products/{product_id}/cases/{case_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试用例
#### 访问路径
/products/{product_id}/cases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 测试用例主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试用例
#### 访问路径
/products/{product_id}/cases/{case_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 行为
#### 访问路径
/products/{product_id}/cases/{case_id}/casefavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### CaseNFavorite
#### 访问路径
/products/{product_id}/cases/{case_id}/casenfavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据检查测试用例
#### 访问路径
/products/{product_id}/cases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 确认用例变更
#### 访问路径
/products/{product_id}/cases/{case_id}/confirmchange

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 确认需求变更
#### 访问路径
/products/{product_id}/cases/{case_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据测试单获取或者状态
#### 访问路径
/products/{product_id}/cases/{case_id}/getbytesttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 获取测试单执行结果
#### 访问路径
/products/{product_id}/cases/{case_id}/gettesttaskcntrun

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 测试单关联测试用例
#### 访问路径
/products/{product_id}/cases/{case_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移动端关联需求
#### 访问路径
/products/{product_id}/cases/{case_id}/moblinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 执行测试
#### 访问路径
/products/{product_id}/cases/{case_id}/runcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### runCases
#### 访问路径
/products/{product_id}/cases/{case_id}/runcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据保存测试用例
#### 访问路径
/products/{product_id}/cases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试用例
#### 访问路径
/products/{product_id}/cases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 执行测试
#### 访问路径
/products/{product_id}/cases/{case_id}/testruncase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### testRunCases
#### 访问路径
/products/{product_id}/cases/{case_id}/testruncases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 套件关联
#### 访问路径
/products/{product_id}/cases/{case_id}/testsuitelinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/products/{product_id}/cases/{case_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkCases
#### 访问路径
/products/{product_id}/cases/{case_id}/unlinkcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/products/{product_id}/cases/{case_id}/unlinksuitecase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkSuiteCases
#### 访问路径
/products/{product_id}/cases/{case_id}/unlinksuitecases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据获取批量新建用例
#### 访问路径
/products/{product_id}/cases/fetchbatchnew

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询批量新建用例
#### 访问路径
/products/{product_id}/cases/searchbatchnew

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的用例
#### 访问路径
/products/{product_id}/cases/fetchcuropenedcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询累计创建的用例
#### 访问路径
/products/{product_id}/cases/searchcuropenedcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取套件关联用例
#### 访问路径
/products/{product_id}/cases/fetchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询套件关联用例
#### 访问路径
/products/{product_id}/cases/searchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例
#### 访问路径
/products/{product_id}/cases/fetchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例
#### 访问路径
/products/{product_id}/cases/searchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/cases/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/cases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/cases/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/cases/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/products/{product_id}/cases/fetchmodulereportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/products/{product_id}/cases/searchmodulereportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-按模块-条目
#### 访问路径
/products/{product_id}/cases/fetchmodulereportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-按模块-条目
#### 访问路径
/products/{product_id}/cases/searchmodulereportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-按模块
#### 访问路径
/products/{product_id}/cases/fetchmodulereportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-按模块
#### 访问路径
/products/{product_id}/cases/searchmodulereportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/cases/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/cases/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取套件关联用例
#### 访问路径
/products/{product_id}/cases/fetchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询套件关联用例
#### 访问路径
/products/{product_id}/cases/searchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例
#### 访问路径
/products/{product_id}/cases/fetchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例
#### 访问路径
/products/{product_id}/cases/searchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例（项目关联）
#### 访问路径
/products/{product_id}/cases/fetchnotcurtesttaskproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例（项目关联）
#### 访问路径
/products/{product_id}/cases/searchnotcurtesttaskproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/products/{product_id}/cases/fetchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/products/{product_id}/cases/searchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例-条目
#### 访问路径
/products/{product_id}/cases/fetchreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例-条目
#### 访问路径
/products/{product_id}/cases/searchreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联用例-关联用例
#### 访问路径
/products/{product_id}/cases/fetchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联用例-关联用例
#### 访问路径
/products/{product_id}/cases/searchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-执行人
#### 访问路径
/products/{product_id}/cases/fetchrunerreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-执行人
#### 访问路径
/products/{product_id}/cases/searchrunerreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-执行人-条目
#### 访问路径
/products/{product_id}/cases/fetchrunerreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-执行人-条目
#### 访问路径
/products/{product_id}/cases/searchrunerreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-执行人
#### 访问路径
/products/{product_id}/cases/fetchrunerreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-执行人
#### 访问路径
/products/{product_id}/cases/searchrunerreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/products/{product_id}/cases/fetchrunreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/products/{product_id}/cases/searchrunreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联--执行结果条目
#### 访问路径
/products/{product_id}/cases/fetchrunreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联--执行结果条目
#### 访问路径
/products/{product_id}/cases/searchrunreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-执行结果
#### 访问路径
/products/{product_id}/cases/fetchrunreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-执行结果
#### 访问路径
/products/{product_id}/cases/searchrunreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试用例
#### 访问路径
/stories/{story_id}/cases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据批量建立测试用例
#### 访问路径
/stories/{story_id}/cases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试用例
#### 访问路径
/stories/{story_id}/cases/{case_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据批量更新测试用例
#### 访问路径
/stories/{story_id}/cases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试用例
#### 访问路径
/stories/{story_id}/cases/{case_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试用例
#### 访问路径
/stories/{story_id}/cases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | ids | List<Long> | 测试用例主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试用例
#### 访问路径
/stories/{story_id}/cases/{case_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 行为
#### 访问路径
/stories/{story_id}/cases/{case_id}/casefavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### CaseNFavorite
#### 访问路径
/stories/{story_id}/cases/{case_id}/casenfavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据检查测试用例
#### 访问路径
/stories/{story_id}/cases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 确认用例变更
#### 访问路径
/stories/{story_id}/cases/{case_id}/confirmchange

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 确认需求变更
#### 访问路径
/stories/{story_id}/cases/{case_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据测试单获取或者状态
#### 访问路径
/stories/{story_id}/cases/{case_id}/getbytesttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 获取测试单执行结果
#### 访问路径
/stories/{story_id}/cases/{case_id}/gettesttaskcntrun

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 测试单关联测试用例
#### 访问路径
/stories/{story_id}/cases/{case_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移动端关联需求
#### 访问路径
/stories/{story_id}/cases/{case_id}/moblinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 执行测试
#### 访问路径
/stories/{story_id}/cases/{case_id}/runcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### runCases
#### 访问路径
/stories/{story_id}/cases/{case_id}/runcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据保存测试用例
#### 访问路径
/stories/{story_id}/cases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试用例
#### 访问路径
/stories/{story_id}/cases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 执行测试
#### 访问路径
/stories/{story_id}/cases/{case_id}/testruncase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### testRunCases
#### 访问路径
/stories/{story_id}/cases/{case_id}/testruncases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 套件关联
#### 访问路径
/stories/{story_id}/cases/{case_id}/testsuitelinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/stories/{story_id}/cases/{case_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkCases
#### 访问路径
/stories/{story_id}/cases/{case_id}/unlinkcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/stories/{story_id}/cases/{case_id}/unlinksuitecase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkSuiteCases
#### 访问路径
/stories/{story_id}/cases/{case_id}/unlinksuitecases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | case_id | Long | 测试用例主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据获取批量新建用例
#### 访问路径
/stories/{story_id}/cases/fetchbatchnew

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询批量新建用例
#### 访问路径
/stories/{story_id}/cases/searchbatchnew

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的用例
#### 访问路径
/stories/{story_id}/cases/fetchcuropenedcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询累计创建的用例
#### 访问路径
/stories/{story_id}/cases/searchcuropenedcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取套件关联用例
#### 访问路径
/stories/{story_id}/cases/fetchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询套件关联用例
#### 访问路径
/stories/{story_id}/cases/searchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例
#### 访问路径
/stories/{story_id}/cases/fetchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例
#### 访问路径
/stories/{story_id}/cases/searchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/cases/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/cases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/stories/{story_id}/cases/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/stories/{story_id}/cases/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/stories/{story_id}/cases/fetchmodulereportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/stories/{story_id}/cases/searchmodulereportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-按模块-条目
#### 访问路径
/stories/{story_id}/cases/fetchmodulereportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-按模块-条目
#### 访问路径
/stories/{story_id}/cases/searchmodulereportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-按模块
#### 访问路径
/stories/{story_id}/cases/fetchmodulereportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-按模块
#### 访问路径
/stories/{story_id}/cases/searchmodulereportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/stories/{story_id}/cases/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/stories/{story_id}/cases/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取套件关联用例
#### 访问路径
/stories/{story_id}/cases/fetchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询套件关联用例
#### 访问路径
/stories/{story_id}/cases/searchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例
#### 访问路径
/stories/{story_id}/cases/fetchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例
#### 访问路径
/stories/{story_id}/cases/searchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例（项目关联）
#### 访问路径
/stories/{story_id}/cases/fetchnotcurtesttaskproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例（项目关联）
#### 访问路径
/stories/{story_id}/cases/searchnotcurtesttaskproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/stories/{story_id}/cases/fetchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/stories/{story_id}/cases/searchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例-条目
#### 访问路径
/stories/{story_id}/cases/fetchreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例-条目
#### 访问路径
/stories/{story_id}/cases/searchreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联用例-关联用例
#### 访问路径
/stories/{story_id}/cases/fetchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联用例-关联用例
#### 访问路径
/stories/{story_id}/cases/searchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-执行人
#### 访问路径
/stories/{story_id}/cases/fetchrunerreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-执行人
#### 访问路径
/stories/{story_id}/cases/searchrunerreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-执行人-条目
#### 访问路径
/stories/{story_id}/cases/fetchrunerreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-执行人-条目
#### 访问路径
/stories/{story_id}/cases/searchrunerreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-执行人
#### 访问路径
/stories/{story_id}/cases/fetchrunerreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-执行人
#### 访问路径
/stories/{story_id}/cases/searchrunerreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/stories/{story_id}/cases/fetchrunreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/stories/{story_id}/cases/searchrunreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联--执行结果条目
#### 访问路径
/stories/{story_id}/cases/fetchrunreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联--执行结果条目
#### 访问路径
/stories/{story_id}/cases/searchrunreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-执行结果
#### 访问路径
/stories/{story_id}/cases/fetchrunreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-执行结果
#### 访问路径
/stories/{story_id}/cases/searchrunreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据批量建立测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据批量更新测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | ids | List<Long> | 测试用例主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 行为
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casefavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### CaseNFavorite
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casenfavorite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据检查测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 确认用例变更
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/confirmchange

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 确认需求变更
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/confirmstorychange

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据测试单获取或者状态
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/getbytesttask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 获取测试单执行结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/gettesttaskcntrun

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 测试单关联测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/linkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移动端关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/moblinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 执行测试
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/runcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### runCases
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/runcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据保存测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存测试用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | casedtos | List<[CaseDTO](#CaseDTO)> | 测试用例实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 执行测试
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testruncase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### testRunCases
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testruncases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 套件关联
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/testsuitelinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/unlinkcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkCases
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/unlinkcases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 移除用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/unlinksuitecase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### unlinkSuiteCases
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/unlinksuitecases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | case_id | Long | 测试用例主键ID |
| 4 | casedto | [CaseDTO](#CaseDTO) | 测试用例实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseDTO](#CaseDTO)：测试用例实体传输对象 |

### 根据获取批量新建用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchbatchnew

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询批量新建用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchbatchnew

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchcuropenedcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询累计创建的用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchcuropenedcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取套件关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询套件关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchcursuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchmodulereportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchmodulereportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-按模块-条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchmodulereportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-按模块-条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchmodulereportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-按模块
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchmodulereportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-按模块
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchmodulereportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取套件关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询套件关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchnotcurtestsuite

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchnotcurtesttask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试单关联用例（项目关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchnotcurtesttaskproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试单关联用例（项目关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchnotcurtesttaskproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例-条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例-条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联用例-关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联用例-关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-执行人
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchrunerreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-执行人
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchrunerreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联-执行人-条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchrunerreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联-执行人-条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchrunerreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-执行人
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchrunerreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-执行人
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchrunerreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchrunreportcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联用例
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchrunreportcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联--执行结果条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchrunreportcaseentry

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询测试报告关联--执行结果条目
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchrunreportcaseentry

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目报告关联-执行结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/fetchrunreportcase_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseDTO](#CaseDTO)>：测试用例实体传输对象列表 |

### 根据查询项目报告关联-执行结果
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/searchrunreportcase_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [CaseSearchContext](#CaseSearchContext) | 测试用例查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseDTO](#CaseDTO)>：测试用例实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### CaseDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | lastediteddate | Timestamp | 允许 | 修改日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 2 | scripteddate | Timestamp | 允许 | scriptedDate<br>时间格式：yyyy-MM-dd |
| 3 | color | String | 允许 | 标题颜色 |
| 4 | path | Integer | 允许 | path |
| 5 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 6 | lastrunresult | String | 允许 | 结果 |
| 7 | modulename1 | String | 允许 | 模块名称 |
| 8 | linkcase | String | 允许 | 相关用例 |
| 9 | task | String | 允许 | 属性 |
| 10 | order | Integer | 允许 | 排序 |
| 11 | howrun | String | 允许 | howRun |
| 12 | resultcnt | Integer | 允许 | 测试结果数 |
| 13 | noticeusers | String | 允许 | 消息通知用户 |
| 14 | version | Integer | 允许 | 用例版本 |
| 15 | scriptedby | String | 允许 | scriptedBy |
| 16 | openedby | String | 允许 | 由谁创建 |
| 17 | type | String | 允许 | 用例类型 |
| 18 | resultfalicnt | Integer | 允许 | 测试失败数 |
| 19 | status | String | 允许 | 用例状态 |
| 20 | comment | String | 允许 | 备注 |
| 21 | auto | String | 允许 | auto |
| 22 | isfavorites | String | 允许 | 是否收藏 |
| 23 | frequency | String | 允许 | frequency |
| 24 | title | String | 不可 | 用例标题 |
| 25 | lasteditedby | String | 允许 | 最后修改者 |
| 26 | reviewedby | String | 允许 | 由谁评审 |
| 27 | files | String | 允许 | 附件 |
| 28 | tobugcnt | Integer | 允许 | 转bug数 |
| 29 | assignedto | String | 允许 | 指派给 |
| 30 | deleted | String | 允许 | 已删除 |
| 31 | revieweddate | Timestamp | 允许 | 评审时间<br>时间格式：yyyy-MM-dd |
| 32 | pri | Integer | 允许 | 优先级 |
| 33 | stage | String | 允许 | 适用阶段 |
| 34 | scriptlocation | String | 允许 | scriptLocation |
| 35 | status1 | String | 允许 | 用例状态 |
| 36 | lastrundate | Timestamp | 允许 | 执行时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 37 | keywords | String | 允许 | 关键词 |
| 38 | scriptstatus | String | 允许 | scriptStatus |
| 39 | frame | String | 允许 | 工具/框架 |
| 40 | lastrunresult1 | String | 允许 | 测试用例结果 |
| 41 | stepcnt | Integer | 允许 | 用例步骤数 |
| 42 | substatus | String | 允许 | 子状态 |
| 43 | id | Long | 不可 | 用例编号 |
| 44 | precondition | String | 允许 | 前置条件 |
| 45 | lastrunner | String | 允许 | 执行人 |
| 46 | libname | String | 允许 | 用例库 |
| 47 | fromcaseversion | Integer | 允许 | 来源用例版本 |
| 48 | storyname | String | 允许 | 需求名称 |
| 49 | modulename | String | 允许 | 模块名称 |
| 50 | storyversion | Integer | 允许 | 需求版本 |
| 51 | productname | String | 允许 | 产品名称 |
| 52 | fromcaseid | Long | 允许 | 来源用例 |
| 53 | branch | Long | 允许 | 平台/分支 |
| 54 | frombug | Long | 允许 | 来源Bug |
| 55 | story | Long | 允许 | 相关需求 |
| 56 | product | Long | 允许 | 所属产品 |
| 57 | lib | Long | 允许 | 所属库 |
| 58 | module | Long | 允许 | 所属模块 |
| 59 | ibzcasesteps | List<[IBZCaseStepDTO}](#IBZCaseStepDTO})> | 允许 | 用例步骤 |
| 60 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IBZCaseStepDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | casestepid | Long | 允许 | 用例步骤编号 |
| 2 | reals | String | 允许 | 实际情况 |
| 3 | steps | String | 允许 | 测试结果 |
| 4 | type | String | 允许 | 用例步骤类型 |
| 5 | id | Long | 不可 | 编号 |
| 6 | desc | String | 允许 | 步骤 |
| 7 | expect | String | 允许 | 预期 |
| 8 | files | String | 允许 | 附件 |
| 9 | runid | Integer | 允许 | 执行编号 |
| 10 | version | Integer | 允许 | 用例版本 |
| 11 | ibizcase | Long | 允许 | 用例 |
| 12 | parent | Long | 允许 | 分组用例步骤的组编号 |
| 13 | <动态属性> | Object | 允许 | 支持动态属性 |

#### CaseSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 2 | n_lastrunresult_eq | String | 允许 | 条件字段：lastrunresult<br>条件组合方式：`=` |
| 3 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 4 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 5 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 6 | n_isfavorites_eq | String | 允许 | 条件字段：isfavorites<br>条件组合方式：`=` |
| 7 | n_frequency_eq | String | 允许 | 条件字段：frequency<br>条件组合方式：`=` |
| 8 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 9 | n_assignedto_eq | String | 允许 | 条件字段：assignedto<br>条件组合方式：`=` |
| 10 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 11 | n_status1_eq | String | 允许 | 条件字段：status1<br>条件组合方式：`=` |
| 12 | n_frame_eq | String | 允许 | 条件字段：frame<br>条件组合方式：`=` |
| 13 | n_lastrunresult1_eq | String | 允许 | 条件字段：lastrunresult1<br>条件组合方式：`=` |
| 14 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 15 | n_lastrunner_eq | String | 允许 | 条件字段：lastrunner<br>条件组合方式：`=` |
| 16 | n_libname_eq | String | 允许 | 条件字段：libname<br>条件组合方式：`=` |
| 17 | n_libname_like | String | 允许 | 条件字段：libname<br>条件组合方式：`%like%` |
| 18 | n_storyname_eq | String | 允许 | 条件字段：storyname<br>条件组合方式：`=` |
| 19 | n_storyname_like | String | 允许 | 条件字段：storyname<br>条件组合方式：`%like%` |
| 20 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 21 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 22 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 23 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 24 | n_fromcaseid_eq | Long | 允许 | 条件字段：fromcaseid<br>条件组合方式：`=` |
| 25 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 26 | n_frombug_eq | Long | 允许 | 条件字段：frombug<br>条件组合方式：`=` |
| 27 | n_story_eq | Long | 允许 | 条件字段：story<br>条件组合方式：`=` |
| 28 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 29 | n_lib_eq | Long | 允许 | 条件字段：lib<br>条件组合方式：`=` |
| 30 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 31 | customcond | String | 允许 | 自定义查询条件 |
| 32 | customparams | String | 允许 | 自定义查询参数 |
| 33 | query | String | 允许 | 快速搜索 |
| 34 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 35 | page | int | 允许 | 当前页数<br>默认值0 |
| 36 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 37 | sort | String | 允许 | 排序 |
