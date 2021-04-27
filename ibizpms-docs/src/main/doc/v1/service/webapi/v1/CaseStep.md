# 服务接口-ZT_CASESTEP
## 接口说明
用例步骤

## 接口清单
### 新建用例步骤
#### 访问路径
/casesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 批量新建用例步骤
#### 访问路径
/casesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用例步骤
#### 访问路径
/casesteps/{casestep_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestep_id | Long | 用例步骤主键ID |
| 2 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 批量更新用例步骤
#### 访问路径
/casesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除用例步骤
#### 访问路径
/casesteps/{casestep_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用例步骤
#### 访问路径
/casesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用例步骤
#### 访问路径
/casesteps/{casestep_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 检查用例步骤
#### 访问路径
/casesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存用例步骤
#### 访问路径
/casesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用例步骤
#### 访问路径
/casesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取当前测试步骤
#### 访问路径
/casesteps/fetchcurtest

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 查询当前测试步骤
#### 访问路径
/casesteps/searchcurtest

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/casesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/casesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT1
#### 访问路径
/casesteps/fetchdefault1

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 查询DEFAULT1
#### 访问路径
/casesteps/searchdefault1

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Mob
#### 访问路径
/casesteps/fetchmob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 查询Mob
#### 访问路径
/casesteps/searchmob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本
#### 访问路径
/casesteps/fetchversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 查询版本
#### 访问路径
/casesteps/searchversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本1
#### 访问路径
/casesteps/fetchversions

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 查询版本1
#### 访问路径
/casesteps/searchversions

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立用例步骤
#### 访问路径
/cases/{case_id}/casesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量建立用例步骤
#### 访问路径
/cases/{case_id}/casesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新用例步骤
#### 访问路径
/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestep_id | Long | 用例步骤主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量更新用例步骤
#### 访问路径
/cases/{case_id}/casesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除用例步骤
#### 访问路径
/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除用例步骤
#### 访问路径
/cases/{case_id}/casesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | ids | List<Long> | 用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取用例步骤
#### 访问路径
/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据检查用例步骤
#### 访问路径
/cases/{case_id}/casesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存用例步骤
#### 访问路径
/cases/{case_id}/casesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存用例步骤
#### 访问路径
/cases/{case_id}/casesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取当前测试步骤
#### 访问路径
/cases/{case_id}/casesteps/fetchcurtest

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询当前测试步骤
#### 访问路径
/cases/{case_id}/casesteps/searchcurtest

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/cases/{case_id}/casesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/cases/{case_id}/casesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/cases/{case_id}/casesteps/fetchdefault1

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/cases/{case_id}/casesteps/searchdefault1

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Mob
#### 访问路径
/cases/{case_id}/casesteps/fetchmob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询Mob
#### 访问路径
/cases/{case_id}/casesteps/searchmob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本
#### 访问路径
/cases/{case_id}/casesteps/fetchversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本
#### 访问路径
/cases/{case_id}/casesteps/searchversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本1
#### 访问路径
/cases/{case_id}/casesteps/fetchversions

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本1
#### 访问路径
/cases/{case_id}/casesteps/searchversions

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | case_id | Long | 测试用例主键ID |
| 2 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量建立用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestep_id | Long | 用例步骤主键ID |
| 4 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量更新用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | ids | List<Long> | 用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据检查用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存用例步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取当前测试步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/fetchcurtest

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询当前测试步骤
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/searchcurtest

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/fetchdefault1

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/searchdefault1

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Mob
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/fetchmob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询Mob
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/searchmob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/fetchversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/searchversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本1
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/fetchversions

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本1
#### 访问路径
/products/{product_id}/cases/{case_id}/casesteps/searchversions

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量建立用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestep_id | Long | 用例步骤主键ID |
| 4 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量更新用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | ids | List<Long> | 用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据检查用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存用例步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取当前测试步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/fetchcurtest

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询当前测试步骤
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/searchcurtest

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/fetchdefault1

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/searchdefault1

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Mob
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/fetchmob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询Mob
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/searchmob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/fetchversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/searchversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本1
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/fetchversions

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本1
#### 访问路径
/stories/{story_id}/cases/{case_id}/casesteps/searchversions

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |/r/n| 2 | case_id | Long | 测试用例主键ID |
| 3 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量建立用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestep_id | Long | 用例步骤主键ID |
| 5 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据批量更新用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | ids | List<Long> | 用例步骤主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/{casestep_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestep_id | Long | 用例步骤主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [CaseStepDTO](#CaseStepDTO)：用例步骤实体传输对象 |

### 根据检查用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据保存用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestepdto | [CaseStepDTO](#CaseStepDTO) | 用例步骤实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存用例步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | casestepdtos | List<[CaseStepDTO](#CaseStepDTO)> | 用例步骤实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取当前测试步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/fetchcurtest

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询当前测试步骤
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/searchcurtest

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT1
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/fetchdefault1

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询DEFAULT1
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/searchdefault1

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Mob
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/fetchmob

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询Mob
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/searchmob

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/fetchversion

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/searchversion

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本1
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/fetchversions

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象列表 |

### 根据查询版本1
#### 访问路径
/products/{product_id}/stories/{story_id}/cases/{case_id}/casesteps/searchversions

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |/r/n| 3 | case_id | Long | 测试用例主键ID |
| 4 | context | [CaseStepSearchContext](#CaseStepSearchContext) | 用例步骤查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[CaseStepDTO](#CaseStepDTO)>：用例步骤实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### CaseStepDTO
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

#### CaseStepSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_steps_eq | String | 允许 | 条件字段：steps<br>条件组合方式：`=` |
| 2 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 3 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | n_expect_like | String | 允许 | 条件字段：expect<br>条件组合方式：`%like%` |
| 5 | n_version_eq | Integer | 允许 | 条件字段：version<br>条件组合方式：`=` |
| 6 | n_case_eq | Long | 允许 | 条件字段：case<br>条件组合方式：`=` |
| 7 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 8 | customcond | String | 允许 | 自定义查询条件 |
| 9 | customparams | String | 允许 | 自定义查询参数 |
| 10 | query | String | 允许 | 快速搜索 |
| 11 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 12 | page | int | 允许 | 当前页数<br>默认值0 |
| 13 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 14 | sort | String | 允许 | 排序 |
