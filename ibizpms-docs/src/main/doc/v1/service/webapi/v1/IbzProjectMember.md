# 服务接口-IBZ_PROJECTMEMBER
## 接口说明
项目相关成员

## 接口清单
### 新建项目相关成员
#### 访问路径
/ibzprojectmembers

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmemberdto | [IbzProjectMemberDTO](#IbzProjectMemberDTO) | 项目相关成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzProjectMemberDTO](#IbzProjectMemberDTO)：项目相关成员实体传输对象 |

### 批量新建项目相关成员
#### 访问路径
/ibzprojectmembers/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmemberdtos | List<[IbzProjectMemberDTO](#IbzProjectMemberDTO)> | 项目相关成员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目相关成员
#### 访问路径
/ibzprojectmembers/{ibzprojectmember_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmember_id | Long | 项目相关成员主键ID |
| 2 | ibzprojectmemberdto | [IbzProjectMemberDTO](#IbzProjectMemberDTO) | 项目相关成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzProjectMemberDTO](#IbzProjectMemberDTO)：项目相关成员实体传输对象 |

### 批量更新项目相关成员
#### 访问路径
/ibzprojectmembers/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmemberdtos | List<[IbzProjectMemberDTO](#IbzProjectMemberDTO)> | 项目相关成员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目相关成员
#### 访问路径
/ibzprojectmembers/{ibzprojectmember_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmember_id | Long | 项目相关成员主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目相关成员
#### 访问路径
/ibzprojectmembers/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 项目相关成员主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目相关成员
#### 访问路径
/ibzprojectmembers/{ibzprojectmember_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmember_id | Long | 项目相关成员主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzProjectMemberDTO](#IbzProjectMemberDTO)：项目相关成员实体传输对象 |

### 检查项目相关成员
#### 访问路径
/ibzprojectmembers/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmemberdto | [IbzProjectMemberDTO](#IbzProjectMemberDTO) | 项目相关成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存项目相关成员
#### 访问路径
/ibzprojectmembers/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmemberdto | [IbzProjectMemberDTO](#IbzProjectMemberDTO) | 项目相关成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目相关成员
#### 访问路径
/ibzprojectmembers/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzprojectmemberdtos | List<[IbzProjectMemberDTO](#IbzProjectMemberDTO)> | 项目相关成员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/ibzprojectmembers/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzProjectMemberSearchContext](#IbzProjectMemberSearchContext) | 项目相关成员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[IbzProjectMemberDTO](#IbzProjectMemberDTO)>：项目相关成员实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/ibzprojectmembers/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [IbzProjectMemberSearchContext](#IbzProjectMemberSearchContext) | 项目相关成员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[IbzProjectMemberDTO](#IbzProjectMemberDTO)>：项目相关成员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### IbzProjectMemberDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | secondmember | String | 允许 | 团队成员（二） |
| 2 | rd | String | 允许 | 发布负责人 |
| 3 | qd | String | 允许 | 测试负责人 |
| 4 | teamembers | String | 允许 | 全部成员 |
| 5 | thirdmember | String | 允许 | 团队成员（三） |
| 6 | id | Long | 允许 | 编号 |
| 7 | fristmember | String | 允许 | 团队成员（一） |
| 8 | po | String | 允许 | 产品负责人 |
| 9 | fourthmember | String | 允许 | 团队成员（四） |
| 10 | pm | String | 允许 | 项目负责人 |
| 11 | fifthmember | String | 允许 | 团队成员（五） |
| 12 | sixthmember | String | 允许 | 团队成员（六） |
| 13 | name | String | 允许 | 项目名称 |
| 14 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzProjectMemberSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | customcond | String | 允许 | 自定义查询条件 |
| 2 | customparams | String | 允许 | 自定义查询参数 |
| 3 | query | String | 允许 | 快速搜索 |
| 4 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 5 | page | int | 允许 | 当前页数<br>默认值0 |
| 6 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 7 | sort | String | 允许 | 排序 |
