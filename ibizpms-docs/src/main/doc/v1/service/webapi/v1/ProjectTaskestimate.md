# 服务接口-PROJECTTASKESTIMATE
## 接口说明
项目工时统计

## 接口清单
### 新建项目工时统计
#### 访问路径
/projecttaskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimatedto | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO) | 项目工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO)：项目工时统计实体传输对象 |

### 批量新建项目工时统计
#### 访问路径
/projecttaskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimatedtos | List<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)> | 项目工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新项目工时统计
#### 访问路径
/projecttaskestimates/{projecttaskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimate_id | String | 项目工时统计主键ID |
| 2 | projecttaskestimatedto | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO) | 项目工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO)：项目工时统计实体传输对象 |

### 批量更新项目工时统计
#### 访问路径
/projecttaskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimatedtos | List<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)> | 项目工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除项目工时统计
#### 访问路径
/projecttaskestimates/{projecttaskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimate_id | String | 项目工时统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除项目工时统计
#### 访问路径
/projecttaskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 项目工时统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取项目工时统计
#### 访问路径
/projecttaskestimates/{projecttaskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimate_id | String | 项目工时统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO)：项目工时统计实体传输对象 |

### 检查项目工时统计
#### 访问路径
/projecttaskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimatedto | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO) | 项目工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存项目工时统计
#### 访问路径
/projecttaskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimatedto | [ProjectTaskestimateDTO](#ProjectTaskestimateDTO) | 项目工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存项目工时统计
#### 访问路径
/projecttaskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | projecttaskestimatedtos | List<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)> | 项目工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用户月工时详情
#### 访问路径
/projecttaskestimates/fetchaccountdetail

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTaskestimateSearchContext](#ProjectTaskestimateSearchContext) | 项目工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)>：项目工时统计实体传输对象列表 |

### 查询用户月工时详情
#### 访问路径
/projecttaskestimates/searchaccountdetail

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTaskestimateSearchContext](#ProjectTaskestimateSearchContext) | 项目工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)>：项目工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/projecttaskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTaskestimateSearchContext](#ProjectTaskestimateSearchContext) | 项目工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)>：项目工时统计实体传输对象列表 |

### 查询数据集
#### 访问路径
/projecttaskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ProjectTaskestimateSearchContext](#ProjectTaskestimateSearchContext) | 项目工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ProjectTaskestimateDTO](#ProjectTaskestimateDTO)>：项目工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ProjectTaskestimateDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | twentyfiveevaluationtime | Double | 允许 | 二十五号评估工时 |
| 2 | seventeenconsumed | Double | 允许 | 十七号工时 |
| 3 | project | Long | 允许 | 项目标识 |
| 4 | fifteenevaluationtime | Double | 允许 | 十五号评估工时 |
| 5 | otherconsumed | Double | 允许 | 其他项目工时 |
| 6 | thirteenevaluationcost | Double | 允许 | 十三号评估成本 |
| 7 | thirtyevaluationcost | Double | 允许 | 三十号评估成本 |
| 8 | evaluationcost | Double | 允许 | 评估成本 |
| 9 | twentysixevaluationcost | Double | 允许 | 二十六号评估成本 |
| 10 | twoevaluationtime | Double | 允许 | 二号评估工时 |
| 11 | thirtyevaluationtime | Double | 允许 | 三十号评估工时 |
| 12 | twentysixconsumed | Double | 允许 | 二十六号工时 |
| 13 | nineteenevaluationtime | Double | 允许 | 十九号评估工时 |
| 14 | twentyoneconsumed | Double | 允许 | 二十一号工时 |
| 15 | evaluationtime | Double | 允许 | 评估工时 |
| 16 | twentyfourevaluationcost | Double | 允许 | 二十四号评估成本 |
| 17 | threeevaluationcost | Double | 允许 | 三号评估成本 |
| 18 | thirtyoneevaluationcost | Double | 允许 | 三十一号评估成本 |
| 19 | sixevaluationcost | Double | 允许 | 六号评估成本 |
| 20 | eighteenevaluationtime | Double | 允许 | 十八号评估工时 |
| 21 | thirteenevaluationtime | Double | 允许 | 十三号评估工时 |
| 22 | twentynineevaluationcost | Double | 允许 | 二十九号评估成本 |
| 23 | twentyevaluationtime | Double | 允许 | 二十号评估工时 |
| 24 | twentytwoevaluationtime | Double | 允许 | 二十二号评估工时 |
| 25 | oneevaluationtime | Double | 允许 | 一号评估工时 |
| 26 | elevenconsumed | Double | 允许 | 十一号工时 |
| 27 | fourteenevaluationtime | Double | 允许 | 十四号评估工时 |
| 28 | twentytwoconsumed | Double | 允许 | 二十二号工时 |
| 29 | twentyoneevaluationcost | Double | 允许 | 二十一号评估成本 |
| 30 | eightconsumed | Double | 允许 | 八号工时 |
| 31 | twentyeightevaluationtime | Double | 允许 | 二十八号评估工时 |
| 32 | threeconsumed | Double | 允许 | 三号工时 |
| 33 | twentythreeevaluationtime | Double | 允许 | 二十三号评估工时 |
| 34 | tenevaluationtime | Double | 允许 | 十号评估工时 |
| 35 | sixconsumed | Double | 允许 | 六号工时 |
| 36 | twentyfiveconsumed | Double | 允许 | 二十五号工时 |
| 37 | twentyeightconsumed | Double | 允许 | 二十八号工时 |
| 38 | thirteenconsumed | Double | 允许 | 十三号工时 |
| 39 | fourevaluationcost | Double | 允许 | 四号评估成本 |
| 40 | twentytwoevaluationcost | Double | 允许 | 二十二号评估成本 |
| 41 | month | String | 允许 | 月 |
| 42 | twelveevaluationtime | Double | 允许 | 十二号评估工时 |
| 43 | tenevaluationcost | Double | 允许 | 十号评估成本 |
| 44 | sevenevaluationcost | Double | 允许 | 七号评估成本 |
| 45 | fifteenevaluationcost | Double | 允许 | 十五号评估成本 |
| 46 | nineevaluationcost | Double | 允许 | 九号评估成本 |
| 47 | thirtyconsumed | Double | 允许 | 三十号工时 |
| 48 | elevenevaluationcost | Double | 允许 | 十一号评估成本 |
| 49 | threeevaluationtime | Double | 允许 | 三号评估工时 |
| 50 | twentynineevaluationtime | Double | 允许 | 二十九号评估工时 |
| 51 | nineconsumed | Double | 允许 | 九号工时 |
| 52 | oneevaluationcost | Double | 允许 | 一号评估成本 |
| 53 | twentyconsumed | Double | 允许 | 二十号工时 |
| 54 | fiveevaluationcost | Double | 允许 | 五号评估成本 |
| 55 | twentyoneevaluationtime | Double | 允许 | 二十一号评估工时 |
| 56 | fourconsumed | Double | 允许 | 四号工时 |
| 57 | otherevaluationtime | Double | 允许 | 其他项目评估工时 |
| 58 | twentysevenconsumed | Double | 允许 | 二十七号工时 |
| 59 | twoconsumed | Double | 允许 | 二号工时 |
| 60 | twentyfourevaluationtime | Double | 允许 | 二十四号评估工时 |
| 61 | twentysixevaluationtime | Double | 允许 | 二十六号评估工时 |
| 62 | nineevaluationtime | Double | 允许 | 九号评估工时 |
| 63 | eighteenevaluationcost | Double | 允许 | 十八号评估成本 |
| 64 | twentythreeevaluationcost | Double | 允许 | 二十三号评估成本 |
| 65 | fourevaluationtime | Double | 允许 | 四号评估工时 |
| 66 | projectname | String | 允许 | 项目名称 |
| 67 | eightevaluationcost | Double | 允许 | 八号评估成本 |
| 68 | eightevaluationtime | Double | 允许 | 八号评估工时 |
| 69 | twentynineconsumed | Double | 允许 | 二十九号工时 |
| 70 | consumed | Double | 允许 | 工时 |
| 71 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 72 | sixteenconsumed | Double | 允许 | 十六号工时 |
| 73 | thirtyoneevaluationtime | Double | 允许 | 三十一号评估工时 |
| 74 | sixteenevaluationcost | Double | 允许 | 十六号评估成本 |
| 75 | sixteenevaluationtime | Double | 允许 | 十六号评估工时 |
| 76 | account | String | 允许 | 用户 |
| 77 | sevenconsumed | Double | 允许 | 七号工时 |
| 78 | nineteenconsumed | Double | 允许 | 十九号工时 |
| 79 | year | String | 允许 | 年 |
| 80 | sixevaluationtime | Double | 允许 | 六号评估工时 |
| 81 | twentyfiveevaluationcost | Double | 允许 | 二十五号评估成本 |
| 82 | seventeenevaluationtime | Double | 允许 | 十七号评估工时 |
| 83 | twentysevenevaluationtime | Double | 允许 | 二十七号评估工时 |
| 84 | fourteenconsumed | Double | 允许 | 十四号工时 |
| 85 | eighteenconsumed | Double | 允许 | 十八号工时 |
| 86 | id | String | 允许 | 主键 |
| 87 | twentyfourconsumed | Double | 允许 | 二十四号工时 |
| 88 | thirtyoneconsumed | Double | 允许 | 三十一号工时 |
| 89 | twelveevaluationcost | Double | 允许 | 十二号评估成本 |
| 90 | seventeenevaluationcost | Double | 允许 | 十七号评估成本 |
| 91 | twentyevaluationcost | Double | 允许 | 二十号评估成本 |
| 92 | tenconsumed | Double | 允许 | 十号工时 |
| 93 | elevenevaluationtime | Double | 允许 | 十一号评估工时 |
| 94 | fiveevaluationtime | Double | 允许 | 五号评估工时 |
| 95 | fifteenconsumed | Double | 允许 | 十五号工时 |
| 96 | twentyeightevaluationcost | Double | 允许 | 二十八号评估成本 |
| 97 | twelveconsumed | Double | 允许 | 十二号工时 |
| 98 | fourteenevaluationcost | Double | 允许 | 十四号评估成本 |
| 99 | fiveconsumed | Double | 允许 | 五号工时 |
| 100 | twentythreeconsumed | Double | 允许 | 二十三号工时 |
| 101 | sevenevaluationtime | Double | 允许 | 七号评估工时 |
| 102 | twentysevenevaluationcost | Double | 允许 | 二十七号评估成本 |
| 103 | oneconsumed | Double | 允许 | 一号工时 |
| 104 | twoevaluationcost | Double | 允许 | 二号评估成本 |
| 105 | nineteenevaluationcost | Double | 允许 | 十九号评估成本 |
| 106 | inputcost | Double | 允许 | 投入成本 |
| 107 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectTaskestimateSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_project_in | Long | 允许 | 条件字段：project<br>条件组合方式：`in(...)` |
| 2 | n_month_eq | String | 允许 | 条件字段：month<br>条件组合方式：`=` |
| 3 | n_date_eq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`=`<br>时间格式：yyyy-MM-dd |
| 4 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 5 | n_year_eq | String | 允许 | 条件字段：year<br>条件组合方式：`=` |
| 6 | customcond | String | 允许 | 自定义查询条件 |
| 7 | customparams | String | 允许 | 自定义查询参数 |
| 8 | query | String | 允许 | 快速搜索 |
| 9 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 10 | page | int | 允许 | 当前页数<br>默认值0 |
| 11 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 12 | sort | String | 允许 | 排序 |
