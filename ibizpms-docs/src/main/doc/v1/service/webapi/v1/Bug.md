# 服务接口-ZT_BUG
## 接口说明
Bug

## 接口清单
### 新建Bug
#### 访问路径
/bugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量新建Bug
#### 访问路径
/bugs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取Bug
#### 访问路径
/bugs/{bug_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 删除Bug
#### 访问路径
/bugs/{bug_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除Bug
#### 访问路径
/bugs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | Bug主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新Bug
#### 访问路径
/bugs/{bug_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量更新Bug
#### 访问路径
/bugs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/bugs/{bug_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 指派
#### 访问路径
/bugs/{bug_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/bugs/{bug_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### Bug收藏
#### 访问路径
/bugs/{bug_id}/bugfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 取消收藏
#### 访问路径
/bugs/{bug_id}/bugnfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本批量解除关联Bug
#### 访问路径
/bugs/{bug_id}/buildbatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本关联Bug
#### 访问路径
/bugs/{bug_id}/buildlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本解除关联Bug
#### 访问路径
/bugs/{bug_id}/buildunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 检查Bug
#### 访问路径
/bugs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/bugs/{bug_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 确认
#### 访问路径
/bugs/{bug_id}/confirm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug
#### 访问路径
/bugs/{bug_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/bugs/{bug_id}/releaasebatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/bugs/{bug_id}/releaselinkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/bugs/{bug_id}/releaselinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 移除关联Bug（遗留Bug）
#### 访问路径
/bugs/{bug_id}/releaseunlinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/bugs/{bug_id}/releaseunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解决
#### 访问路径
/bugs/{bug_id}/resolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 保存Bug
#### 访问路径
/bugs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存Bug
#### 访问路径
/bugs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/bugs/{bug_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 发送消息前置处理
#### 访问路径
/bugs/{bug_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 转需求
#### 访问路径
/bugs/{bug_id}/tostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/bugs/{bug_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 更新需求版本
#### 访问路径
/bugs/{bug_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | bug_id | Long | Bug主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 获取指派给我Bug
#### 访问路径
/bugs/fetchassignedtomybug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询指派给我Bug
#### 访问路径
/bugs/searchassignedtomybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我Bug（PC）
#### 访问路径
/bugs/fetchassignedtomybugpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询指派给我Bug（PC）
#### 访问路径
/bugs/searchassignedtomybugpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本关联bug(遗留的)
#### 访问路径
/bugs/fetchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询版本关联bug(遗留的)
#### 访问路径
/bugs/searchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本关联Bug（已解决）
#### 访问路径
/bugs/fetchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询版本关联Bug（已解决）
#### 访问路径
/bugs/searchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本可关联的已解决的Bugs集合
#### 访问路径
/bugs/fetchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询版本可关联的已解决的Bugs集合
#### 访问路径
/bugs/searchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本关联Bug（已解决）
#### 访问路径
/bugs/fetchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询版本关联Bug（已解决）
#### 访问路径
/bugs/searchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug
#### 访问路径
/bugs/fetchbuildproducebug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug
#### 访问路径
/bugs/searchbuildproducebug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug
#### 访问路径
/bugs/fetchbuildproducebugmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug
#### 访问路径
/bugs/searchbuildproducebugmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugmodule_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/bugs/searchbuildproducebugmodule_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-创建分类
#### 访问路径
/bugs/fetchbuildproducebugopenedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-创建分类
#### 访问路径
/bugs/searchbuildproducebugopenedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugopenedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/bugs/searchbuildproducebugopenedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug（已解决）
#### 访问路径
/bugs/fetchbuildproducebugres

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug（已解决）
#### 访问路径
/bugs/searchbuildproducebugres

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-解决者分布
#### 访问路径
/bugs/fetchbuildproducebugresolvedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-解决者分布
#### 访问路径
/bugs/searchbuildproducebugresolvedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-解决者分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugresolvedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-解决者分布(项目)
#### 访问路径
/bugs/searchbuildproducebugresolvedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-解决方案分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugresolution_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-解决方案分布(项目)
#### 访问路径
/bugs/searchbuildproducebugresolution_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-严重程度分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugseverity_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-严重程度分布(项目)
#### 访问路径
/bugs/searchbuildproducebugseverity_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-状态分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugstatus_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-状态分布(项目)
#### 访问路径
/bugs/searchbuildproducebugstatus_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Build产生的Bug-类型分布(项目)
#### 访问路径
/bugs/fetchbuildproducebugtype_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询Build产生的Bug-类型分布(项目)
#### 访问路径
/bugs/searchbuildproducebugtype_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取当前用户解决的Bug
#### 访问路径
/bugs/fetchcuruserresolve

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询当前用户解决的Bug
#### 访问路径
/bugs/searchcuruserresolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/bugs/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/bugs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/bugs/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/bugs/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我代理的Bug
#### 访问路径
/bugs/fetchmyagentbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询我代理的Bug
#### 访问路径
/bugs/searchmyagentbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取累计创建的Bug数
#### 访问路径
/bugs/fetchmycuropenedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询累计创建的Bug数
#### 访问路径
/bugs/searchmycuropenedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/bugs/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/bugs/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取计划关联bug（去除已关联）
#### 访问路径
/bugs/fetchnotcurplanlinkbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询计划关联bug（去除已关联）
#### 访问路径
/bugs/searchnotcurplanlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取遗留得Bug(项目)
#### 访问路径
/bugs/fetchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询遗留得Bug(项目)
#### 访问路径
/bugs/searchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取发布关联Bug（已解决）
#### 访问路径
/bugs/fetchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询发布关联Bug（已解决）
#### 访问路径
/bugs/searchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取发布关联Bug（已解决）
#### 访问路径
/bugs/fetchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询发布关联Bug（已解决）
#### 访问路径
/bugs/searchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取发布可关联的bug（遗留）
#### 访问路径
/bugs/fetchreleaselinkableleftbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询发布可关联的bug（遗留）
#### 访问路径
/bugs/searchreleaselinkableleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取发布可关联的bug（已解决）
#### 访问路径
/bugs/fetchreleaselinkableresolvedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询发布可关联的bug（已解决）
#### 访问路径
/bugs/searchreleaselinkableresolvedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取发布关联Bug（未解决）
#### 访问路径
/bugs/fetchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询发布关联Bug（未解决）
#### 访问路径
/bugs/searchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务相关bug
#### 访问路径
/bugs/fetchtaskrelatedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 查询任务相关bug
#### 访问路径
/bugs/searchtaskrelatedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立Bug
#### 访问路径
/products/{product_id}/bugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量建立Bug
#### 访问路径
/products/{product_id}/bugs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据删除Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除Bug
#### 访问路径
/products/{product_id}/bugs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | Bug主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量更新Bug
#### 访问路径
/products/{product_id}/bugs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/products/{product_id}/bugs/{bug_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 指派
#### 访问路径
/products/{product_id}/bugs/{bug_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### Bug收藏
#### 访问路径
/products/{product_id}/bugs/{bug_id}/bugfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 取消收藏
#### 访问路径
/products/{product_id}/bugs/{bug_id}/bugnfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本批量解除关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/buildbatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/buildlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本解除关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/buildunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据检查Bug
#### 访问路径
/products/{product_id}/bugs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/bugs/{bug_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 确认
#### 访问路径
/products/{product_id}/bugs/{bug_id}/confirm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/releaasebatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/products/{product_id}/bugs/{bug_id}/releaselinkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/products/{product_id}/bugs/{bug_id}/releaselinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 移除关联Bug（遗留Bug）
#### 访问路径
/products/{product_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/releaseunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解决
#### 访问路径
/products/{product_id}/bugs/{bug_id}/resolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据保存Bug
#### 访问路径
/products/{product_id}/bugs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存Bug
#### 访问路径
/products/{product_id}/bugs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/products/{product_id}/bugs/{bug_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/bugs/{bug_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 转需求
#### 访问路径
/products/{product_id}/bugs/{bug_id}/tostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/bugs/{bug_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 更新需求版本
#### 访问路径
/products/{product_id}/bugs/{bug_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据获取指派给我Bug
#### 访问路径
/products/{product_id}/bugs/fetchassignedtomybug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug
#### 访问路径
/products/{product_id}/bugs/searchassignedtomybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我Bug（PC）
#### 访问路径
/products/{product_id}/bugs/fetchassignedtomybugpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug（PC）
#### 访问路径
/products/{product_id}/bugs/searchassignedtomybugpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联bug(遗留的)
#### 访问路径
/products/{product_id}/bugs/fetchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联bug(遗留的)
#### 访问路径
/products/{product_id}/bugs/searchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/fetchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/searchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的已解决的Bugs集合
#### 访问路径
/products/{product_id}/bugs/fetchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本可关联的已解决的Bugs集合
#### 访问路径
/products/{product_id}/bugs/searchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/fetchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/searchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugmodule_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugmodule_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建分类
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugopenedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建分类
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugopenedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugopenedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugopenedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugres

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugres

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugresolvedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugresolvedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugresolvedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugresolvedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决方案分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugresolution_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决方案分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugresolution_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-严重程度分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugseverity_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-严重程度分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugseverity_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-状态分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugstatus_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-状态分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugstatus_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-类型分布(项目)
#### 访问路径
/products/{product_id}/bugs/fetchbuildproducebugtype_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-类型分布(项目)
#### 访问路径
/products/{product_id}/bugs/searchbuildproducebugtype_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取当前用户解决的Bug
#### 访问路径
/products/{product_id}/bugs/fetchcuruserresolve

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询当前用户解决的Bug
#### 访问路径
/products/{product_id}/bugs/searchcuruserresolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/bugs/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/bugs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/bugs/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/bugs/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的Bug
#### 访问路径
/products/{product_id}/bugs/fetchmyagentbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我代理的Bug
#### 访问路径
/products/{product_id}/bugs/searchmyagentbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的Bug数
#### 访问路径
/products/{product_id}/bugs/fetchmycuropenedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询累计创建的Bug数
#### 访问路径
/products/{product_id}/bugs/searchmycuropenedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/bugs/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/bugs/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联bug（去除已关联）
#### 访问路径
/products/{product_id}/bugs/fetchnotcurplanlinkbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询计划关联bug（去除已关联）
#### 访问路径
/products/{product_id}/bugs/searchnotcurplanlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取遗留得Bug(项目)
#### 访问路径
/products/{product_id}/bugs/fetchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询遗留得Bug(项目)
#### 访问路径
/products/{product_id}/bugs/searchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/fetchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/searchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/fetchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/bugs/searchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（遗留）
#### 访问路径
/products/{product_id}/bugs/fetchreleaselinkableleftbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（遗留）
#### 访问路径
/products/{product_id}/bugs/searchreleaselinkableleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（已解决）
#### 访问路径
/products/{product_id}/bugs/fetchreleaselinkableresolvedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（已解决）
#### 访问路径
/products/{product_id}/bugs/searchreleaselinkableresolvedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（未解决）
#### 访问路径
/products/{product_id}/bugs/fetchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（未解决）
#### 访问路径
/products/{product_id}/bugs/searchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关bug
#### 访问路径
/products/{product_id}/bugs/fetchtaskrelatedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询任务相关bug
#### 访问路径
/products/{product_id}/bugs/searchtaskrelatedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立Bug
#### 访问路径
/stories/{story_id}/bugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量建立Bug
#### 访问路径
/stories/{story_id}/bugs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据删除Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除Bug
#### 访问路径
/stories/{story_id}/bugs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | ids | List<Long> | Bug主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量更新Bug
#### 访问路径
/stories/{story_id}/bugs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 指派
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### Bug收藏
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/bugfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 取消收藏
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/bugnfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本批量解除关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/buildbatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/buildlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本解除关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/buildunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据检查Bug
#### 访问路径
/stories/{story_id}/bugs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 确认
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/confirm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/releaasebatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/releaselinkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/releaselinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 移除关联Bug（遗留Bug）
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/releaseunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解决
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/resolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据保存Bug
#### 访问路径
/stories/{story_id}/bugs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存Bug
#### 访问路径
/stories/{story_id}/bugs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 发送消息前置处理
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 转需求
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/tostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 更新需求版本
#### 访问路径
/stories/{story_id}/bugs/{bug_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据获取指派给我Bug
#### 访问路径
/stories/{story_id}/bugs/fetchassignedtomybug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug
#### 访问路径
/stories/{story_id}/bugs/searchassignedtomybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我Bug（PC）
#### 访问路径
/stories/{story_id}/bugs/fetchassignedtomybugpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug（PC）
#### 访问路径
/stories/{story_id}/bugs/searchassignedtomybugpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联bug(遗留的)
#### 访问路径
/stories/{story_id}/bugs/fetchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联bug(遗留的)
#### 访问路径
/stories/{story_id}/bugs/searchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/fetchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/searchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的已解决的Bugs集合
#### 访问路径
/stories/{story_id}/bugs/fetchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本可关联的已解决的Bugs集合
#### 访问路径
/stories/{story_id}/bugs/searchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/fetchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/searchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugmodule_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugmodule_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建分类
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugopenedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建分类
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugopenedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugopenedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugopenedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugres

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugres

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugresolvedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugresolvedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugresolvedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugresolvedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决方案分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugresolution_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决方案分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugresolution_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-严重程度分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugseverity_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-严重程度分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugseverity_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-状态分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugstatus_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-状态分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugstatus_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-类型分布(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchbuildproducebugtype_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-类型分布(项目)
#### 访问路径
/stories/{story_id}/bugs/searchbuildproducebugtype_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取当前用户解决的Bug
#### 访问路径
/stories/{story_id}/bugs/fetchcuruserresolve

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询当前用户解决的Bug
#### 访问路径
/stories/{story_id}/bugs/searchcuruserresolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/bugs/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/bugs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/stories/{story_id}/bugs/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/stories/{story_id}/bugs/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的Bug
#### 访问路径
/stories/{story_id}/bugs/fetchmyagentbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我代理的Bug
#### 访问路径
/stories/{story_id}/bugs/searchmyagentbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的Bug数
#### 访问路径
/stories/{story_id}/bugs/fetchmycuropenedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询累计创建的Bug数
#### 访问路径
/stories/{story_id}/bugs/searchmycuropenedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/stories/{story_id}/bugs/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/stories/{story_id}/bugs/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联bug（去除已关联）
#### 访问路径
/stories/{story_id}/bugs/fetchnotcurplanlinkbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询计划关联bug（去除已关联）
#### 访问路径
/stories/{story_id}/bugs/searchnotcurplanlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取遗留得Bug(项目)
#### 访问路径
/stories/{story_id}/bugs/fetchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询遗留得Bug(项目)
#### 访问路径
/stories/{story_id}/bugs/searchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/fetchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/searchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/fetchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/searchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（遗留）
#### 访问路径
/stories/{story_id}/bugs/fetchreleaselinkableleftbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（遗留）
#### 访问路径
/stories/{story_id}/bugs/searchreleaselinkableleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/fetchreleaselinkableresolvedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（已解决）
#### 访问路径
/stories/{story_id}/bugs/searchreleaselinkableresolvedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（未解决）
#### 访问路径
/stories/{story_id}/bugs/fetchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（未解决）
#### 访问路径
/stories/{story_id}/bugs/searchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关bug
#### 访问路径
/stories/{story_id}/bugs/fetchtaskrelatedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询任务相关bug
#### 访问路径
/stories/{story_id}/bugs/searchtaskrelatedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立Bug
#### 访问路径
/projects/{project_id}/bugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量建立Bug
#### 访问路径
/projects/{project_id}/bugs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据删除Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除Bug
#### 访问路径
/projects/{project_id}/bugs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | ids | List<Long> | Bug主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量更新Bug
#### 访问路径
/projects/{project_id}/bugs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 指派
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### Bug收藏
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/bugfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 取消收藏
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/bugnfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本批量解除关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/buildbatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/buildlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本解除关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/buildunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据检查Bug
#### 访问路径
/projects/{project_id}/bugs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 确认
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/confirm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/releaasebatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/releaselinkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/releaselinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 移除关联Bug（遗留Bug）
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/releaseunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解决
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/resolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据保存Bug
#### 访问路径
/projects/{project_id}/bugs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存Bug
#### 访问路径
/projects/{project_id}/bugs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 发送消息前置处理
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 转需求
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/tostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 更新需求版本
#### 访问路径
/projects/{project_id}/bugs/{bug_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | bug_id | Long | Bug主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据获取指派给我Bug
#### 访问路径
/projects/{project_id}/bugs/fetchassignedtomybug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug
#### 访问路径
/projects/{project_id}/bugs/searchassignedtomybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我Bug（PC）
#### 访问路径
/projects/{project_id}/bugs/fetchassignedtomybugpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug（PC）
#### 访问路径
/projects/{project_id}/bugs/searchassignedtomybugpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联bug(遗留的)
#### 访问路径
/projects/{project_id}/bugs/fetchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联bug(遗留的)
#### 访问路径
/projects/{project_id}/bugs/searchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/fetchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/searchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的已解决的Bugs集合
#### 访问路径
/projects/{project_id}/bugs/fetchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本可关联的已解决的Bugs集合
#### 访问路径
/projects/{project_id}/bugs/searchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/fetchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/searchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugmodule_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugmodule_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建分类
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugopenedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建分类
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugopenedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugopenedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugopenedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugres

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugres

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugresolvedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugresolvedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugresolvedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugresolvedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决方案分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugresolution_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决方案分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugresolution_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-严重程度分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugseverity_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-严重程度分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugseverity_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-状态分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugstatus_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-状态分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugstatus_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-类型分布(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchbuildproducebugtype_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-类型分布(项目)
#### 访问路径
/projects/{project_id}/bugs/searchbuildproducebugtype_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取当前用户解决的Bug
#### 访问路径
/projects/{project_id}/bugs/fetchcuruserresolve

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询当前用户解决的Bug
#### 访问路径
/projects/{project_id}/bugs/searchcuruserresolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/projects/{project_id}/bugs/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/projects/{project_id}/bugs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/projects/{project_id}/bugs/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/projects/{project_id}/bugs/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的Bug
#### 访问路径
/projects/{project_id}/bugs/fetchmyagentbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我代理的Bug
#### 访问路径
/projects/{project_id}/bugs/searchmyagentbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的Bug数
#### 访问路径
/projects/{project_id}/bugs/fetchmycuropenedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询累计创建的Bug数
#### 访问路径
/projects/{project_id}/bugs/searchmycuropenedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/projects/{project_id}/bugs/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/projects/{project_id}/bugs/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联bug（去除已关联）
#### 访问路径
/projects/{project_id}/bugs/fetchnotcurplanlinkbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询计划关联bug（去除已关联）
#### 访问路径
/projects/{project_id}/bugs/searchnotcurplanlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取遗留得Bug(项目)
#### 访问路径
/projects/{project_id}/bugs/fetchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询遗留得Bug(项目)
#### 访问路径
/projects/{project_id}/bugs/searchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/fetchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/searchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/fetchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/searchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（遗留）
#### 访问路径
/projects/{project_id}/bugs/fetchreleaselinkableleftbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（遗留）
#### 访问路径
/projects/{project_id}/bugs/searchreleaselinkableleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/fetchreleaselinkableresolvedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（已解决）
#### 访问路径
/projects/{project_id}/bugs/searchreleaselinkableresolvedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（未解决）
#### 访问路径
/projects/{project_id}/bugs/fetchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（未解决）
#### 访问路径
/projects/{project_id}/bugs/searchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关bug
#### 访问路径
/projects/{project_id}/bugs/fetchtaskrelatedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询任务相关bug
#### 访问路径
/projects/{project_id}/bugs/searchtaskrelatedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | project_id | Long | 项目主键ID |
| 2 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量建立Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据删除Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | ids | List<Long> | Bug主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据批量更新Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 激活
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 指派
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### Bug收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/bugfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 取消收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/bugnfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本批量解除关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/buildbatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/buildlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 版本解除关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/buildunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据检查Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 确认
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/confirm

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaasebatchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaselinkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaselinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 移除关联Bug（遗留Bug）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaseunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解决
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/resolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据保存Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bugdtos | List<[BugDTO](#BugDTO)> | Bug实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 转需求
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/tostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 更新需求版本
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/{bug_id}/updatestoryversion

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | bug_id | Long | Bug主键ID |
| 4 | bugdto | [BugDTO](#BugDTO) | Bug实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [BugDTO](#BugDTO)：Bug实体传输对象 |

### 根据获取指派给我Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchassignedtomybug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchassignedtomybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我Bug（PC）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchassignedtomybugpc

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询指派给我Bug（PC）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchassignedtomybugpc

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联bug(遗留的)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联bug(遗留的)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbugsbybuild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的已解决的Bugs集合
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本可关联的已解决的Bugs集合
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildlinkresolvedbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询版本关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildopenbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugmodule

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugmodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugmodule_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugmodule_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建分类
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugopenedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建分类
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugopenedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugopenedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-创建者分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugopenedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugres

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugres

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugresolvedby

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugresolvedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决者分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugresolvedby_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决者分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugresolvedby_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-解决方案分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugresolution_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-解决方案分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugresolution_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-严重程度分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugseverity_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-严重程度分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugseverity_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-状态分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugstatus_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-状态分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugstatus_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Build产生的Bug-类型分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugtype_project

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询Build产生的Bug-类型分布(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugtype_project

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取当前用户解决的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchcuruserresolve

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询当前用户解决的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchcuruserresolve

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchmyagentbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我代理的Bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchmyagentbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取累计创建的Bug数
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchmycuropenedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询累计创建的Bug数
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchmycuropenedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联bug（去除已关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchnotcurplanlinkbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询计划关联bug（去除已关联）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchnotcurplanlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取遗留得Bug(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询遗留得Bug(项目)
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchprojectbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchreleasebugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchreleaseleftbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（遗留）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchreleaselinkableleftbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（遗留）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchreleaselinkableleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布可关联的bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchreleaselinkableresolvedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布可关联的bug（已解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchreleaselinkableresolvedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取发布关联Bug（未解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询发布关联Bug（未解决）
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchreportbugs

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/fetchtaskrelatedbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[BugDTO](#BugDTO)>：Bug实体传输对象列表 |

### 根据查询任务相关bug
#### 访问路径
/products/{product_id}/stories/{story_id}/bugs/searchtaskrelatedbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [BugSearchContext](#BugSearchContext) | Bug查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[BugDTO](#BugDTO)>：Bug实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### BugDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | severity | Integer | 允许 | 严重程度 |
| 2 | storyversion | Integer | 允许 | 需求版本 |
| 3 | buildname | String | 允许 | 版本名称 |
| 4 | linkbug | String | 允许 | 相关Bug |
| 5 | activateddate | Timestamp | 允许 | 激活日期<br>时间格式：yyyy-MM-dd |
| 6 | overduebugs | Integer | 允许 | 过期天数 |
| 7 | createbuild | Integer | 允许 | 创建版本 |
| 8 | assignedto | String | 允许 | 指派给 |
| 9 | resolution | String | 允许 | 解决方案 |
| 10 | lastediteddate | Timestamp | 允许 | 修改日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 11 | mobimage | String | 允许 | 移动端图片 |
| 12 | result | Integer | 允许 | result |
| 13 | keywords | String | 允许 | 关键词 |
| 14 | isfavorites | String | 允许 | 是否收藏 |
| 15 | modulename1 | String | 允许 | 模块名称 |
| 16 | closedby | String | 允许 | 由谁关闭 |
| 17 | browser | String | 允许 | 浏览器 |
| 18 | noticeusers | String | 允许 | 消息通知用户 |
| 19 | steps | String | 允许 | 重现步骤 |
| 20 | v2 | String | 允许 | v2 |
| 21 | confirmed | Integer | 允许 | 是否确认 |
| 22 | mailtoconact | String | 允许 | 联系人 |
| 23 | openedby | String | 允许 | 由谁创建 |
| 24 | activatedcount | Integer | 允许 | 激活次数 |
| 25 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 26 | closeddate | Timestamp | 允许 | 关闭日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 27 | mailto | String | 允许 | 抄送给 |
| 28 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 29 | deadline | Timestamp | 允许 | 截止日期<br>时间格式：yyyy-MM-dd |
| 30 | color | String | 允许 | 标题颜色 |
| 31 | comment | String | 允许 | 备注 |
| 32 | resolveddate | Timestamp | 允许 | 解决日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 33 | type | String | 允许 | Bug类型 |
| 34 | status | String | 允许 | Bug状态 |
| 35 | openedbuild | String | 允许 | 影响版本 |
| 36 | delayresolve | String | 允许 | 延期解决 |
| 37 | files | String | 允许 | 附件 |
| 38 | mailtopk | String | 允许 | 抄送给 |
| 39 | v1 | String | 允许 | v1 |
| 40 | deleted | String | 允许 | 已删除 |
| 41 | lines | String | 允许 | lines |
| 42 | substatus | String | 允许 | 子状态 |
| 43 | buildproject | String | 允许 | 版本项目 |
| 44 | id | Long | 不可 | Bug编号 |
| 45 | delay | String | 允许 | 延期 |
| 46 | found | String | 允许 | found |
| 47 | resolvedby | String | 允许 | 解决者 |
| 48 | resolvedbuild | String | 允许 | 解决版本 |
| 49 | pri | Integer | 允许 | 优先级 |
| 50 | os | String | 允许 | 操作系统 |
| 51 | hardware | String | 允许 | hardware |
| 52 | lasteditedby | String | 允许 | 最后修改者 |
| 53 | title | String | 不可 | Bug标题 |
| 54 | productname | String | 允许 | 产品 |
| 55 | branchname | String | 允许 | 平台/分支 |
| 56 | taskname | String | 允许 | 相关任务 |
| 57 | casename | String | 允许 | 相关用例 |
| 58 | projectname | String | 允许 | 项目 |
| 59 | storyname | String | 允许 | 相关需求 |
| 60 | caseversion | Integer | 允许 | 用例版本 |
| 61 | repotype | String | 允许 | 代码类型 |
| 62 | modulename | String | 允许 | 模块名称 |
| 63 | tostory | Long | 允许 | 转需求 |
| 64 | entry | Long | 允许 | 应用 |
| 65 | product | Long | 允许 | 所属产品 |
| 66 | totask | Long | 允许 | 转任务 |
| 67 | plan | Long | 允许 | 所属计划 |
| 68 | module | Long | 允许 | 所属模块 |
| 69 | branch | Long | 允许 | 平台/分支 |
| 70 | duplicatebug | Long | 允许 | 重复ID |
| 71 | repo | Long | 允许 | 代码 |
| 72 | story | Long | 允许 | 相关需求 |
| 73 | ibizcase | Long | 允许 | 相关用例 |
| 74 | project | Long | 允许 | 所属项目 |
| 75 | task | Long | 允许 | 相关任务 |
| 76 | testtask | Long | 允许 | 测试单 |
| 77 | <动态属性> | Object | 允许 | 支持动态属性 |

#### BugSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_severity_eq | Integer | 允许 | 条件字段：severity<br>条件组合方式：`=` |
| 2 | n_overduebugs_lt | Integer | 允许 | 条件字段：overduebugs<br>条件组合方式：`<` |
| 3 | n_assignedto_eq | String | 允许 | 条件字段：assignedto<br>条件组合方式：`=` |
| 4 | n_resolution_eq | String | 允许 | 条件字段：resolution<br>条件组合方式：`=` |
| 5 | n_isfavorites_eq | String | 允许 | 条件字段：isfavorites<br>条件组合方式：`=` |
| 6 | n_closedby_eq | String | 允许 | 条件字段：closedby<br>条件组合方式：`=` |
| 7 | n_browser_eq | String | 允许 | 条件字段：browser<br>条件组合方式：`=` |
| 8 | n_confirmed_eq | Integer | 允许 | 条件字段：confirmed<br>条件组合方式：`=` |
| 9 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 10 | n_assigneddate_gtandeq | Timestamp | 允许 | 条件字段：assigneddate<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 11 | n_deadline_gtandeq | Timestamp | 允许 | 条件字段：deadline<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 12 | n_deadline_lt | Timestamp | 允许 | 条件字段：deadline<br>条件组合方式：`<`<br>时间格式：yyyy-MM-dd |
| 13 | n_deadline_ltandeq | Timestamp | 允许 | 条件字段：deadline<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 14 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 15 | n_resolveddate_ltandeq | Timestamp | 允许 | 条件字段：resolveddate<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 16 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 17 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 18 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 19 | n_buildproject_eq | String | 允许 | 条件字段：buildproject<br>条件组合方式：`=` |
| 20 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 21 | n_resolvedby_eq | String | 允许 | 条件字段：resolvedby<br>条件组合方式：`=` |
| 22 | n_resolvedbuild_eq | String | 允许 | 条件字段：resolvedbuild<br>条件组合方式：`=` |
| 23 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 24 | n_os_eq | String | 允许 | 条件字段：os<br>条件组合方式：`=` |
| 25 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 26 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 27 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 28 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 29 | n_branchname_eq | String | 允许 | 条件字段：branchname<br>条件组合方式：`=` |
| 30 | n_branchname_like | String | 允许 | 条件字段：branchname<br>条件组合方式：`%like%` |
| 31 | n_taskname_eq | String | 允许 | 条件字段：taskname<br>条件组合方式：`=` |
| 32 | n_taskname_like | String | 允许 | 条件字段：taskname<br>条件组合方式：`%like%` |
| 33 | n_projectname_eq | String | 允许 | 条件字段：projectname<br>条件组合方式：`=` |
| 34 | n_projectname_like | String | 允许 | 条件字段：projectname<br>条件组合方式：`%like%` |
| 35 | n_storyname_eq | String | 允许 | 条件字段：storyname<br>条件组合方式：`=` |
| 36 | n_storyname_like | String | 允许 | 条件字段：storyname<br>条件组合方式：`%like%` |
| 37 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 38 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 39 | n_tostory_eq | Long | 允许 | 条件字段：tostory<br>条件组合方式：`=` |
| 40 | n_entry_eq | Long | 允许 | 条件字段：entry<br>条件组合方式：`=` |
| 41 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 42 | n_totask_eq | Long | 允许 | 条件字段：totask<br>条件组合方式：`=` |
| 43 | n_plan_eq | Long | 允许 | 条件字段：plan<br>条件组合方式：`=` |
| 44 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 45 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 46 | n_duplicatebug_eq | Long | 允许 | 条件字段：duplicatebug<br>条件组合方式：`=` |
| 47 | n_repo_eq | Long | 允许 | 条件字段：repo<br>条件组合方式：`=` |
| 48 | n_story_eq | Long | 允许 | 条件字段：story<br>条件组合方式：`=` |
| 49 | n_case_eq | Long | 允许 | 条件字段：case<br>条件组合方式：`=` |
| 50 | n_project_eq | Long | 允许 | 条件字段：project<br>条件组合方式：`=` |
| 51 | n_task_eq | Long | 允许 | 条件字段：task<br>条件组合方式：`=` |
| 52 | n_testtask_eq | Long | 允许 | 条件字段：testtask<br>条件组合方式：`=` |
| 53 | customcond | String | 允许 | 自定义查询条件 |
| 54 | customparams | String | 允许 | 自定义查询参数 |
| 55 | query | String | 允许 | 快速搜索 |
| 56 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 57 | page | int | 允许 | 当前页数<br>默认值0 |
| 58 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 59 | sort | String | 允许 | 排序 |
