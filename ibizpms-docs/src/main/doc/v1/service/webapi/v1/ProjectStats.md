# 服务接口-IBZ_PROJECTSTATS
## 接口说明
项目统计

## 接口清单
### 新建项目统计
#### 访问路径
/projectstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstatsdto | [ProjectStatsDTO](#ProjectStatsDTO) | 项目统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectStatsDTO](#ProjectStatsDTO)：项目统计实体传输对象 |

### 批量新建项目统计
#### 访问路径
/projectstats/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstatsdtos | List<[ProjectStatsDTO](#ProjectStatsDTO)> | 项目统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目统计
#### 访问路径
/projectstats/{projectstats_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstats_id | Long | 项目统计主键ID |
| 2 | projectstatsdto | [ProjectStatsDTO](#ProjectStatsDTO) | 项目统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectStatsDTO](#ProjectStatsDTO)：项目统计实体传输对象 |

### 批量更新项目统计
#### 访问路径
/projectstats/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstatsdtos | List<[ProjectStatsDTO](#ProjectStatsDTO)> | 项目统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目统计
#### 访问路径
/projectstats/{projectstats_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstats_id | Long | 项目统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目统计
#### 访问路径
/projectstats/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 项目统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目统计
#### 访问路径
/projectstats/{projectstats_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstats_id | Long | 项目统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectStatsDTO](#ProjectStatsDTO)：项目统计实体传输对象 |

### 检查项目统计
#### 访问路径
/projectstats/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstatsdto | [ProjectStatsDTO](#ProjectStatsDTO) | 项目统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 项目质量表聚合逻辑
#### 访问路径
/projectstats/{projectstats_id}/projectqualitysum

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstats_id | Long | 项目统计主键ID |
| 2 | projectstatsdto | [ProjectStatsDTO](#ProjectStatsDTO) | 项目统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectStatsDTO](#ProjectStatsDTO)：项目统计实体传输对象 |

### 保存项目统计
#### 访问路径
/projectstats/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstatsdto | [ProjectStatsDTO](#ProjectStatsDTO) | 项目统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目统计
#### 访问路径
/projectstats/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projectstatsdtos | List<[ProjectStatsDTO](#ProjectStatsDTO)> | 项目统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取DEFAULT
#### 访问路径
/projectstats/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/projectstats/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取未关闭产品
#### 访问路径
/projectstats/fetchnoopenproduct

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询未关闭产品
#### 访问路径
/projectstats/searchnoopenproduct

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目bug类型统计
#### 访问路径
/projectstats/fetchprojectbugtype

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目bug类型统计
#### 访问路径
/projectstats/searchprojectbugtype

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目投入统计
#### 访问路径
/projectstats/fetchprojectinputstats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目投入统计
#### 访问路径
/projectstats/searchprojectinputstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目进度
#### 访问路径
/projectstats/fetchprojectprogress

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目进度
#### 访问路径
/projectstats/searchprojectprogress

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目质量
#### 访问路径
/projectstats/fetchprojectquality

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目质量
#### 访问路径
/projectstats/searchprojectquality

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目需求阶段统计
#### 访问路径
/projectstats/fetchprojectstorystagestats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目需求阶段统计
#### 访问路径
/projectstats/searchprojectstorystagestats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目需求状态统计
#### 访问路径
/projectstats/fetchprojectstorystatusstats

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目需求状态统计
#### 访问路径
/projectstats/searchprojectstorystatusstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务统计(任务状态)
#### 访问路径
/projectstats/fetchprojecttaskcountbytaskstatus

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目任务统计(任务状态)
#### 访问路径
/projectstats/searchprojecttaskcountbytaskstatus

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目任务类型统计
#### 访问路径
/projectstats/fetchprojecttaskcountbytype

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询项目任务类型统计
#### 访问路径
/projectstats/searchprojecttaskcountbytype

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务工时消耗剩余查询
#### 访问路径
/projectstats/fetchtasktime

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象列表 |

### 查询任务工时消耗剩余查询
#### 访问路径
/projectstats/searchtasktime

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectStatsSearchContext](#ProjectStatsSearchContext) | 项目统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectStatsDTO](#ProjectStatsDTO)>：项目统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectStatsDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | standard | Integer | 允许 | 标准规范 |
| 2 | type | String | 允许 | 工时类型 |
| 3 | emptystory | Integer | 允许 | 空需求 |
| 4 | draftstory | Integer | 允许 | 草稿需求 |
| 5 | end | Timestamp | 允许 | 截止日期<br>时间格式：yyyy-MM-dd |
| 6 | releasedstagestorycnt | Integer | 允许 | 已发布阶段需求数 |
| 7 | importantbugcnt | Integer | 允许 | 重要Bug数 |
| 8 | canceltaskcnt | Integer | 允许 | 已取消任务数 |
| 9 | begin | Timestamp | 允许 | 开始时间<br>时间格式：yyyy-MM-dd |
| 10 | status | String | 允许 | 状态 |
| 11 | seriousbugproportion | String | 允许 | 严重Bug比率 |
| 12 | doingtaskcnt | Integer | 允许 | 进行中任务数 |
| 13 | undonetaskcnt | Integer | 允许 | 未完成任务总数 |
| 14 | servetaskcnt | Integer | 允许 | 服务类型任务 |
| 15 | donetaskcnt | Integer | 允许 | 已完成任务数 |
| 16 | totalwh | Integer | 允许 | 总工时 |
| 17 | unclosedstorycnt | Integer | 允许 | 未关闭需求总数 |
| 18 | misctaskcnt | Integer | 允许 | 其他类型任务 |
| 19 | leftstorycnt | Integer | 允许 | 剩余需求数 |
| 20 | performance | Integer | 允许 | 性能问题 |
| 21 | emptystagestorycnt | Integer | 允许 | 空阶段需求数 |
| 22 | waitstagestorycnt | Integer | 允许 | 未开始阶段需求数 |
| 23 | developingstagestorycnt | Integer | 允许 | 研发中阶段需求数 |
| 24 | istop | Integer | 允许 | 是否置顶 |
| 25 | completestorycnt | Integer | 允许 | 完成需求数 |
| 26 | testtaskcnt | Integer | 允许 | 测试类型任务 |
| 27 | deleted | String | 允许 | 已删除 |
| 28 | storycnt | Integer | 允许 | 需求总数 |
| 29 | projectedstagestorycnt | Integer | 允许 | 已立项阶段需求数 |
| 30 | discusstaskcnt | Integer | 允许 | 讨论类型任务 |
| 31 | completetaskcnt | Integer | 允许 | 完成任务数 |
| 32 | plannedstagestorycnt | Integer | 允许 | 已计划阶段需求数 |
| 33 | testedstagestorycnt | Integer | 允许 | 测试完毕阶段需求数 |
| 34 | name | String | 不可 | 项目名称 |
| 35 | taskcnt | Integer | 允许 | 任务总数 |
| 36 | totalleft | Double | 允许 | 任务预计剩余总工时 |
| 37 | timescale | String | 允许 | 工期 |
| 38 | activestory | Integer | 允许 | 激活需求 |
| 39 | order1 | Integer | 允许 | 项目排序 |
| 40 | activebugcnt | Integer | 允许 | 未解决Bug总数 |
| 41 | designdefect | Integer | 允许 | 设计缺陷 |
| 42 | waittaskcnt | Integer | 允许 | 未开始任务数 |
| 43 | pausetaskcnt | Integer | 允许 | 已暂停任务数 |
| 44 | closedstagestorycnt | Integer | 允许 | 已关闭阶段需求数 |
| 45 | totalestimate | Double | 允许 | 任务最初预计总工时 |
| 46 | verifiedstagestorycnt | Integer | 允许 | 已验收阶段需求数 |
| 47 | install | Integer | 允许 | 安装部署 |
| 48 | id | Long | 不可 | 项目编号 |
| 49 | designtaskcnt | Integer | 允许 | 设计类型任务 |
| 50 | security | Integer | 允许 | 安全相关 |
| 51 | others | Integer | 允许 | 其他 |
| 52 | bugcnt | Integer | 允许 | Bug总数 |
| 53 | unclosedbugcnt | Integer | 允许 | 未关闭Bug总数 |
| 54 | yesterdayctaskcnt | Integer | 允许 | 昨日完成任务数 |
| 55 | time | Double | 允许 | 工时 |
| 56 | automation | Integer | 允许 | 测试脚本 |
| 57 | unconfirmedbugcnt | Integer | 允许 | 未确认Bug总数 |
| 58 | developedstagestorycnt | Integer | 允许 | 研发完毕阶段需求数 |
| 59 | testingstagestorycnt | Integer | 允许 | 测试中阶段需求数 |
| 60 | finishtaskcnt | Integer | 允许 | 已结束任务总数 |
| 61 | config | Integer | 允许 | 配置相关 |
| 62 | totalconsumed | Double | 允许 | 任务消耗总工时 |
| 63 | affairtaskcnt | Integer | 允许 | 事务类型任务 |
| 64 | projecttotalconsumed | Double | 允许 | 项目消耗总工时 |
| 65 | uitaskcnt | Integer | 允许 | 界面类型任务 |
| 66 | bugstory | Integer | 允许 | Bug/完成需求 |
| 67 | closedstory | Integer | 允许 | 已关闭需求 |
| 68 | bugtask | Integer | 允许 | Bug/完成任务 |
| 69 | releasedstorycnt | Integer | 允许 | 已发布需求数 |
| 70 | studytaskcnt | Integer | 允许 | 研究类型任务 |
| 71 | closedtaskcnt | Integer | 允许 | 已关闭任务数 |
| 72 | codeerror | Integer | 允许 | 代码错误 |
| 73 | closedstorycnt | Integer | 允许 | 关闭需求总数 |
| 74 | finishbugcnt | Integer | 允许 | 已解决Bug总数 |
| 75 | changedstory | Integer | 允许 | 已变更需求 |
| 76 | develtaskcnt | Integer | 允许 | 开发类型任务 |
| 77 | membercnt | Integer | 允许 | 人数 |
| 78 | progress | String | 允许 | 进度 |
| 79 | yesterdayrbugcnt | Integer | 允许 | 昨天解决Bug数 |
| 80 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectStatsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_end_ltandeq | Timestamp | 允许 | 条件字段：end<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 2 | n_begin_gtandeq | Timestamp | 允许 | 条件字段：begin<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 3 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 4 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 5 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
