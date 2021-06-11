# 服务接口-SYS_TEAMMEMBER
## 接口说明
组成员

## 接口清单
### 新建组成员
#### 访问路径
/systeammembers

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammemberdto | [SysTeamMemberDTO](#SysTeamMemberDTO) | 组成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysTeamMemberDTO](#SysTeamMemberDTO)：组成员实体传输对象 |

### 批量新建组成员
#### 访问路径
/systeammembers/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammemberdtos | List<[SysTeamMemberDTO](#SysTeamMemberDTO)> | 组成员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取组成员
#### 访问路径
/systeammembers/{systeammember_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammember_id | String | 组成员主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysTeamMemberDTO](#SysTeamMemberDTO)：组成员实体传输对象 |

### 删除组成员
#### 访问路径
/systeammembers/{systeammember_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammember_id | String | 组成员主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除组成员
#### 访问路径
/systeammembers/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 组成员主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新组成员
#### 访问路径
/systeammembers/{systeammember_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammember_id | String | 组成员主键ID |
| 2 | systeammemberdto | [SysTeamMemberDTO](#SysTeamMemberDTO) | 组成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SysTeamMemberDTO](#SysTeamMemberDTO)：组成员实体传输对象 |

### 批量更新组成员
#### 访问路径
/systeammembers/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammemberdtos | List<[SysTeamMemberDTO](#SysTeamMemberDTO)> | 组成员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查组成员
#### 访问路径
/systeammembers/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammemberdto | [SysTeamMemberDTO](#SysTeamMemberDTO) | 组成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存组成员
#### 访问路径
/systeammembers/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammemberdto | [SysTeamMemberDTO](#SysTeamMemberDTO) | 组成员实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存组成员
#### 访问路径
/systeammembers/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | systeammemberdtos | List<[SysTeamMemberDTO](#SysTeamMemberDTO)> | 组成员实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取数据集
#### 访问路径
/systeammembers/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysTeamMemberSearchContext](#SysTeamMemberSearchContext) | 组成员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SysTeamMemberDTO](#SysTeamMemberDTO)>：组成员实体传输对象列表 |

### 查询数据集
#### 访问路径
/systeammembers/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [SysTeamMemberSearchContext](#SysTeamMemberSearchContext) | 组成员查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SysTeamMemberDTO](#SysTeamMemberDTO)>：组成员实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SysTeamMemberDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | teammemberid | String | 允许 | 组成员标识 |
| 2 | teamname | String | 不可 | 组名称 |
| 3 | postname | String | 允许 | 岗位 |
| 4 | domains | String | 允许 | 区属 |
| 5 | usericon | String | 允许 | 头像 |
| 6 | personname | String | 允许 | 姓名 |
| 7 | username | String | 允许 | 账号 |
| 8 | postid | String | 允许 | 岗位标识 |
| 9 | teamid | String | 允许 | 组标识 |
| 10 | userid | String | 允许 | 用户标识 |
| 11 | <动态属性> | Object | 允许 | 支持动态属性 |

