# 服务接口-用户年度工作内容统计
## 接口说明
用户年度工作内容统计

## 接口清单
### 新建用户年度工作内容统计
#### 访问路径
/useryearworkstats

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 批量新建用户年度工作内容统计
#### 访问路径
/useryearworkstats/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstatsdtos | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)> | 用户年度工作内容统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用户年度工作内容统计
#### 访问路径
/useryearworkstats/{useryearworkstats_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 删除用户年度工作内容统计
#### 访问路径
/useryearworkstats/{useryearworkstats_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用户年度工作内容统计
#### 访问路径
/useryearworkstats/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 用户年度工作内容统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用户年度工作内容统计
#### 访问路径
/useryearworkstats/{useryearworkstats_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |
| 2 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 批量更新用户年度工作内容统计
#### 访问路径
/useryearworkstats/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstatsdtos | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)> | 用户年度工作内容统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查用户年度工作内容统计
#### 访问路径
/useryearworkstats/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取研发人员相关数据
#### 访问路径
/useryearworkstats/{useryearworkstats_id}/getdevinfomation

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |
| 2 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 获取产品经理相关数据
#### 访问路径
/useryearworkstats/{useryearworkstats_id}/getpoinfomation

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |
| 2 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 获取测试人员相关数据
#### 访问路径
/useryearworkstats/{useryearworkstats_id}/getqainfomation

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |
| 2 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 获取用户所选年度的动作
#### 访问路径
/useryearworkstats/{useryearworkstats_id}/getuseryearaction

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |
| 2 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 保存用户年度工作内容统计
#### 访问路径
/useryearworkstats/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用户年度工作内容统计
#### 访问路径
/useryearworkstats/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstatsdtos | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)> | 用户年度工作内容统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新标题
#### 访问路径
/useryearworkstats/{useryearworkstats_id}/updatetitlebyyear

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | useryearworkstats_id | Long | 用户年度工作内容统计主键ID |
| 2 | useryearworkstatsdto | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO) | 用户年度工作内容统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [UserYearWorkStatsDTO](#UserYearWorkStatsDTO)：用户年度工作内容统计实体传输对象 |

### 获取数据集
#### 访问路径
/useryearworkstats/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象列表 |

### 查询数据集
#### 访问路径
/useryearworkstats/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取月完成任务数及累计工时和解决Bug数
#### 访问路径
/useryearworkstats/fetchmonthfinishtaskandbug

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象列表 |

### 查询月完成任务数及累计工时和解决Bug数
#### 访问路径
/useryearworkstats/searchmonthfinishtaskandbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取月创建Bug数和创建用例数
#### 访问路径
/useryearworkstats/fetchmonthopenedbugandcase

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象列表 |

### 查询月创建Bug数和创建用例数
#### 访问路径
/useryearworkstats/searchmonthopenedbugandcase

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取月创建需求数
#### 访问路径
/useryearworkstats/fetchmonthopenedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象列表 |

### 查询月创建需求数
#### 访问路径
/useryearworkstats/searchmonthopenedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [UserYearWorkStatsSearchContext](#UserYearWorkStatsSearchContext) | 用户年度工作内容统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[UserYearWorkStatsDTO](#UserYearWorkStatsDTO)>：用户年度工作内容统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### UserYearWorkStatsDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | password | String | 允许 | 密码 |
| 2 | yearproductcnt | Integer | 允许 | 累计参与产品数 |
| 3 | yearbugcnt | Integer | 允许 | 累计创建Bug数 |
| 4 | weixin | String | 允许 | 微信 |
| 5 | yearcasecnt | Integer | 允许 | 累计创建用例数 |
| 6 | account | String | 允许 | 账号 |
| 7 | qq | String | 允许 | QQ |
| 8 | ranzhi | String | 允许 | ranzhi |
| 9 | nickname | String | 允许 | nickname |
| 10 | avatar | String | 允许 | avatar |
| 11 | monthfinishtask | Integer | 允许 | 月完成任务数 |
| 12 | fails | Integer | 允许 | fails |
| 13 | join | Timestamp | 允许 | 入职日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 14 | yearstorycnt | Integer | 允许 | 累计创建需求数 |
| 15 | yearplancnt | Integer | 允许 | 累计创建计划数 |
| 16 | dingding | String | 允许 | 钉钉 |
| 17 | ip | String | 允许 | ip |
| 18 | yearactioncnt | Integer | 允许 | 累计动态数 |
| 19 | mobile | String | 允许 | 手机 |
| 20 | whatsapp | String | 允许 | whatsapp |
| 21 | id | Long | 允许 | 用户编号 |
| 22 | email | String | 允许 | 邮箱 |
| 23 | yearestimatecnt | Integer | 允许 | 累计工时数 |
| 24 | clientlang | String | 允许 | clientLang |
| 25 | montestimate | Integer | 允许 | 月累计工时 |
| 26 | realname | String | 允许 | 真实用户名 |
| 27 | commiter | String | 允许 | 源代码账户 |
| 28 | slack | String | 允许 | slack |
| 29 | deleted | String | 允许 | 逻辑删除标志 |
| 30 | last | Integer | 允许 | 最后登录 |
| 31 | curyear | String | 允许 | 年度 |
| 32 | judgerole | String | 允许 | 判断角色 |
| 33 | skype | String | 允许 | skype |
| 34 | birthday | Timestamp | 允许 | birthday<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 35 | phone | String | 允许 | 电话 |
| 36 | yearvisits | Integer | 允许 | 累计登录次数 |
| 37 | score | Integer | 允许 | score |
| 38 | role | String | 允许 | 角色 |
| 39 | clientstatus | String | 允许 | clientStatus |
| 40 | dept | String | 允许 | 部门编号 |
| 41 | title | String | 允许 | 标题 |
| 42 | curmonth | String | 允许 | 当前月 |
| 43 | montresolvedbug | Integer | 允许 | 月解决Bug数 |
| 44 | address | String | 允许 | 通讯地址 |
| 45 | scorelevel | Integer | 允许 | scoreLevel |
| 46 | visits | Integer | 允许 | 累计登录次数 |
| 47 | yearlogcnt | Integer | 允许 | 累计日志数 |
| 48 | locked | Timestamp | 允许 | locked<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 49 | gender | String | 允许 | 性别 |
| 50 | zipcode | String | 允许 | zipcode |
| 51 | <动态属性> | Object | 允许 | 支持动态属性 |

#### UserYearWorkStatsSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 2 | n_realname_like | String | 允许 | 条件字段：realname<br>条件组合方式：`%like%` |
| 3 | n_dept_eq | String | 允许 | 条件字段：dept<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
