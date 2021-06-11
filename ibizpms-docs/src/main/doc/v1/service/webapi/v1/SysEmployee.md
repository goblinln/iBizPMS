# 服务接口-IBZEMP
## 接口说明
人员

## 接口清单
### 新建人员
#### 访问路径
/sysemployees

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployeedto | [SysEmployeeDTO](#SysEmployeeDTO) | 人员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysEmployeeDTO](#SysEmployeeDTO)：人员实体传输对象 |

### 批量新建人员
#### 访问路径
/sysemployees/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployeedtos | List<[SysEmployeeDTO](#SysEmployeeDTO)> | 人员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取人员
#### 访问路径
/sysemployees/{sysemployee_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployee_id | String | 人员主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysEmployeeDTO](#SysEmployeeDTO)：人员实体传输对象 |

### 删除人员
#### 访问路径
/sysemployees/{sysemployee_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployee_id | String | 人员主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除人员
#### 访问路径
/sysemployees/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 人员主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新人员
#### 访问路径
/sysemployees/{sysemployee_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployee_id | String | 人员主键ID |
| 2 | sysemployeedto | [SysEmployeeDTO](#SysEmployeeDTO) | 人员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysEmployeeDTO](#SysEmployeeDTO)：人员实体传输对象 |

### 批量更新人员
#### 访问路径
/sysemployees/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployeedtos | List<[SysEmployeeDTO](#SysEmployeeDTO)> | 人员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查人员
#### 访问路径
/sysemployees/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployeedto | [SysEmployeeDTO](#SysEmployeeDTO) | 人员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存人员
#### 访问路径
/sysemployees/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployeedto | [SysEmployeeDTO](#SysEmployeeDTO) | 人员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存人员
#### 访问路径
/sysemployees/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | sysemployeedtos | List<[SysEmployeeDTO](#SysEmployeeDTO)> | 人员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取Bug用户
#### 访问路径
/sysemployees/fetchbuguser

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询Bug用户
#### 访问路径
/sysemployees/searchbuguser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取联系人用户
#### 访问路径
/sysemployees/fetchcontactlist

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询联系人用户
#### 访问路径
/sysemployees/searchcontactlist

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/sysemployees/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询数据集
#### 访问路径
/sysemployees/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队管理
#### 访问路径
/sysemployees/fetchproductteamm

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队管理
#### 访问路径
/sysemployees/searchproductteamm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队管理
#### 访问路径
/sysemployees/fetchprojectteamm

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队管理
#### 访问路径
/sysemployees/searchprojectteamm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队管理
#### 访问路径
/sysemployees/fetchprojectteammproduct

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队管理
#### 访问路径
/sysemployees/searchprojectteammproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队成员(临时)
#### 访问路径
/sysemployees/fetchprojectteamtaskusertemp

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队成员(临时)
#### 访问路径
/sysemployees/searchprojectteamtaskusertemp

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队成员
#### 访问路径
/sysemployees/fetchprojectteamuser

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队成员
#### 访问路径
/sysemployees/searchprojectteamuser

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队成员
#### 访问路径
/sysemployees/fetchprojectteamusertask

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队成员
#### 访问路径
/sysemployees/searchprojectteamusertask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目团队成员选择
#### 访问路径
/sysemployees/fetchprojectteampk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询项目团队成员选择
#### 访问路径
/sysemployees/searchprojectteampk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取产品团队成员选择
#### 访问路径
/sysemployees/fetchstoryproductteampk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询产品团队成员选择
#### 访问路径
/sysemployees/searchstoryproductteampk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务多人团队
#### 访问路径
/sysemployees/fetchtaskmteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询任务多人团队
#### 访问路径
/sysemployees/searchtaskmteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询2
#### 访问路径
/sysemployees/fetchtaskteam

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象列表 |

### 查询数据查询2
#### 访问路径
/sysemployees/searchtaskteam

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysEmployeeSearchContext](#SysEmployeeSearchContext) | 人员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysEmployeeDTO](#SysEmployeeDTO)>：人员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysEmployeeDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | userid | String | 允许 | 用户标识 |
| 2 | username | String | 允许 | 用户全局名 |
| 3 | personname | String | 不可 | 姓名 |
| 4 | usercode | String | 不可 | 用户工号 |
| 5 | loginname | String | 不可 | 登录名 |
| 6 | password | String | 不可 | 密码 |
| 7 | domains | String | 允许 | 区属 |
| 8 | mdeptid | String | 允许 | 主部门 |
| 9 | mdeptcode | String | 允许 | 主部门代码 |
| 10 | mdeptname | String | 允许 | 主部门名称 |
| 11 | bcode | String | 允许 | 业务编码 |
| 12 | orgid | String | 允许 | 单位 |
| 13 | orgcode | String | 允许 | 单位代码 |
| 14 | orgname | String | 允许 | 单位名称 |
| 15 | nickname | String | 允许 | 昵称别名 |
| 16 | sex | String | 允许 | 性别 |
| 17 | phone | String | 允许 | 联系方式 |
| 18 | email | String | 允许 | 邮件 |
| 19 | avatar | String | 允许 | 社交账号 |
| 20 | addr | String | 允许 | 地址 |
| 21 | usericon | String | 允许 | 照片 |
| 22 | ipaddr | String | 允许 | ip地址 |
| 23 | lang | String | 允许 | 语言 |
| 24 | memo | String | 允许 | 备注 |
| 25 | reserver | String | 允许 | 保留 |
| 26 | showorder | Integer | 允许 | 排序 |
| 27 | enable | Integer | 允许 | 逻辑有效 |
| 28 | createdate | Timestamp | 允许 | 创建时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 29 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 30 | updateman | String | 不可 | 更新人 |
| 31 | createman | String | 不可 | 建立人 |
| 32 | <动态属性> | Object | 允许 | 支持动态属性 |

