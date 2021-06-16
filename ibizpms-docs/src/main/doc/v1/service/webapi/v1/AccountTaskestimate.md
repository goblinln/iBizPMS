# 服务接口-ACCOUNTTASKESTIMATE
## 接口说明
用户工时统计

## 接口清单
### 新建用户工时统计
#### 访问路径
/accounttaskestimates

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimatedto | [AccountTaskestimateDTO](#AccountTaskestimateDTO) | 用户工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [AccountTaskestimateDTO](#AccountTaskestimateDTO)：用户工时统计实体传输对象 |

### 批量新建用户工时统计
#### 访问路径
/accounttaskestimates/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimatedtos | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)> | 用户工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取用户工时统计
#### 访问路径
/accounttaskestimates/{accounttaskestimate_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimate_id | String | 用户工时统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [AccountTaskestimateDTO](#AccountTaskestimateDTO)：用户工时统计实体传输对象 |

### 删除用户工时统计
#### 访问路径
/accounttaskestimates/{accounttaskestimate_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimate_id | String | 用户工时统计主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除用户工时统计
#### 访问路径
/accounttaskestimates/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<String> | 用户工时统计主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新用户工时统计
#### 访问路径
/accounttaskestimates/{accounttaskestimate_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimate_id | String | 用户工时统计主键ID |
| 2 | accounttaskestimatedto | [AccountTaskestimateDTO](#AccountTaskestimateDTO) | 用户工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [AccountTaskestimateDTO](#AccountTaskestimateDTO)：用户工时统计实体传输对象 |

### 批量更新用户工时统计
#### 访问路径
/accounttaskestimates/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimatedtos | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)> | 用户工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 检查用户工时统计
#### 访问路径
/accounttaskestimates/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimatedto | [AccountTaskestimateDTO](#AccountTaskestimateDTO) | 用户工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 保存用户工时统计
#### 访问路径
/accounttaskestimates/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimatedto | [AccountTaskestimateDTO](#AccountTaskestimateDTO) | 用户工时统计实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存用户工时统计
#### 访问路径
/accounttaskestimates/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | accounttaskestimatedtos | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)> | 用户工时统计实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取所有用户工时
#### 访问路径
/accounttaskestimates/fetchallaccountestimate

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象列表 |

### 查询所有用户工时
#### 访问路径
/accounttaskestimates/searchallaccountestimate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户日工时统计
#### 访问路径
/accounttaskestimates/fetchcurdaysum

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象列表 |

### 查询用户日工时统计
#### 访问路径
/accounttaskestimates/searchcurdaysum

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取用户当日工时（项目）
#### 访问路径
/accounttaskestimates/fetchcurdaysunbyproject

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象列表 |

### 查询用户当日工时（项目）
#### 访问路径
/accounttaskestimates/searchcurdaysunbyproject

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据集
#### 访问路径
/accounttaskestimates/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象列表 |

### 查询数据集
#### 访问路径
/accounttaskestimates/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的工时统计
#### 访问路径
/accounttaskestimates/fetchmydaysum

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象列表 |

### 查询我的工时统计
#### 访问路径
/accounttaskestimates/searchmydaysum

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [AccountTaskestimateSearchContext](#AccountTaskestimateSearchContext) | 用户工时统计查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[AccountTaskestimateDTO](#AccountTaskestimateDTO)>：用户工时统计实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### AccountTaskestimateDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | projectname | String | 允许 | 项目名称 |
| 2 | sevenconsumed | Double | 允许 | 七号工时 |
| 3 | sevenevaluationtime | Double | 允许 | 七号评估工时 |
| 4 | fourteenevaluationtime | Double | 允许 | 十四号评估工时 |
| 5 | tenconsumed | Double | 允许 | 十号工时 |
| 6 | nineconsumed | Double | 允许 | 九号工时 |
| 7 | twentyconsumed | Double | 允许 | 二十号工时 |
| 8 | sixconsumed | Double | 允许 | 六号工时 |
| 9 | thirtyevaluationtime | Double | 允许 | 三十号评估工时 |
| 10 | thirteenconsumed | Double | 允许 | 十三号工时 |
| 11 | eightevaluationcost | Double | 允许 | 八号评估成本 |
| 12 | nineteenevaluationtime | Double | 允许 | 十九号评估工时 |
| 13 | twentynineevaluationtime | Double | 允许 | 二十九号评估工时 |
| 14 | seventeenevaluationtime | Double | 允许 | 十七号评估工时 |
| 15 | twelveevaluationcost | Double | 允许 | 十二号评估成本 |
| 16 | twentynineevaluationcost | Double | 允许 | 二十九号评估成本 |
| 17 | threeevaluationcost | Double | 允许 | 三号评估成本 |
| 18 | twentysixevaluationtime | Double | 允许 | 二十六号评估工时 |
| 19 | fourteenevaluationcost | Double | 允许 | 十四号评估成本 |
| 20 | eighteenevaluationcost | Double | 允许 | 十八号评估成本 |
| 21 | oneevaluationtime | Double | 允许 | 一号评估工时 |
| 22 | month | String | 允许 | 月 |
| 23 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 24 | twentynineconsumed | Double | 允许 | 二十九号工时 |
| 25 | evaluationtime | Double | 允许 | 评估工时 |
| 26 | evaluationcost | Double | 允许 | 评估成本 |
| 27 | elevenevaluationtime | Double | 允许 | 十一号评估工时 |
| 28 | tenevaluationtime | Double | 允许 | 十号评估工时 |
| 29 | thirtyoneevaluationcost | Double | 允许 | 三十一号评估成本 |
| 30 | fourevaluationtime | Double | 允许 | 四号评估工时 |
| 31 | twentytwoconsumed | Double | 允许 | 二十二号工时 |
| 32 | twentyoneconsumed | Double | 允许 | 二十一号工时 |
| 33 | thirteenevaluationtime | Double | 允许 | 十三号评估工时 |
| 34 | seventeenconsumed | Double | 允许 | 十七号工时 |
| 35 | nineevaluationtime | Double | 允许 | 九号评估工时 |
| 36 | twoconsumed | Double | 允许 | 二号工时 |
| 37 | twentysevenconsumed | Double | 允许 | 二十七号工时 |
| 38 | inputcost | Double | 允许 | 投入成本 |
| 39 | fourteenconsumed | Double | 允许 | 十四号工时 |
| 40 | twentyeightconsumed | Double | 允许 | 二十八号工时 |
| 41 | consumed | Double | 允许 | 工时 |
| 42 | thirteenevaluationcost | Double | 允许 | 十三号评估成本 |
| 43 | elevenconsumed | Double | 允许 | 十一号工时 |
| 44 | twentyoneevaluationtime | Double | 允许 | 二十一号评估工时 |
| 45 | twelveconsumed | Double | 允许 | 十二号工时 |
| 46 | thirtyoneconsumed | Double | 允许 | 三十一号工时 |
| 47 | fiveconsumed | Double | 允许 | 五号工时 |
| 48 | twentyfourconsumed | Double | 允许 | 二十四号工时 |
| 49 | twentyeightevaluationcost | Double | 允许 | 二十八号评估成本 |
| 50 | twentythreeevaluationtime | Double | 允许 | 二十三号评估工时 |
| 51 | nineteenevaluationcost | Double | 允许 | 十九号评估成本 |
| 52 | thirtyconsumed | Double | 允许 | 三十号工时 |
| 53 | twentyoneevaluationcost | Double | 允许 | 二十一号评估成本 |
| 54 | twentyevaluationtime | Double | 允许 | 二十号评估工时 |
| 55 | sixevaluationcost | Double | 允许 | 六号评估成本 |
| 56 | nineteenconsumed | Double | 允许 | 十九号工时 |
| 57 | eightconsumed | Double | 允许 | 八号工时 |
| 58 | seventeenevaluationcost | Double | 允许 | 十七号评估成本 |
| 59 | twelveevaluationtime | Double | 允许 | 十二号评估工时 |
| 60 | twentytwoevaluationcost | Double | 允许 | 二十二号评估成本 |
| 61 | twentyeightevaluationtime | Double | 允许 | 二十八号评估工时 |
| 62 | fifteenconsumed | Double | 允许 | 十五号工时 |
| 63 | threeevaluationtime | Double | 允许 | 三号评估工时 |
| 64 | eighteenevaluationtime | Double | 允许 | 十八号评估工时 |
| 65 | thirtyevaluationcost | Double | 允许 | 三十号评估成本 |
| 66 | sixevaluationtime | Double | 允许 | 六号评估工时 |
| 67 | id | String | 允许 | 主键 |
| 68 | twentythreeevaluationcost | Double | 允许 | 二十三号评估成本 |
| 69 | twentysixevaluationcost | Double | 允许 | 二十六号评估成本 |
| 70 | twentysevenevaluationtime | Double | 允许 | 二十七号评估工时 |
| 71 | oneconsumed | Double | 允许 | 一号工时 |
| 72 | twentytwoevaluationtime | Double | 允许 | 二十二号评估工时 |
| 73 | eightevaluationtime | Double | 允许 | 八号评估工时 |
| 74 | twentyfiveevaluationcost | Double | 允许 | 二十五号评估成本 |
| 75 | sevenevaluationcost | Double | 允许 | 七号评估成本 |
| 76 | sixteenevaluationtime | Double | 允许 | 十六号评估工时 |
| 77 | eighteenconsumed | Double | 允许 | 十八号工时 |
| 78 | fiveevaluationcost | Double | 允许 | 五号评估成本 |
| 79 | twoevaluationcost | Double | 允许 | 二号评估成本 |
| 80 | elevenevaluationcost | Double | 允许 | 十一号评估成本 |
| 81 | twoevaluationtime | Double | 允许 | 二号评估工时 |
| 82 | fifteenevaluationcost | Double | 允许 | 十五号评估成本 |
| 83 | twentyevaluationcost | Double | 允许 | 二十号评估成本 |
| 84 | twentythreeconsumed | Double | 允许 | 二十三号工时 |
| 85 | thirtyoneevaluationtime | Double | 允许 | 三十一号评估工时 |
| 86 | nineevaluationcost | Double | 允许 | 九号评估成本 |
| 87 | twentysevenevaluationcost | Double | 允许 | 二十七号评估成本 |
| 88 | twentyfiveevaluationtime | Double | 允许 | 二十五号评估工时 |
| 89 | twentyfiveconsumed | Double | 允许 | 二十五号工时 |
| 90 | year | String | 允许 | 年 |
| 91 | twentyfourevaluationtime | Double | 允许 | 二十四号评估工时 |
| 92 | twentyfourevaluationcost | Double | 允许 | 二十四号评估成本 |
| 93 | project | Long | 允许 | 项目标识 |
| 94 | fifteenevaluationtime | Double | 允许 | 十五号评估工时 |
| 95 | tenevaluationcost | Double | 允许 | 十号评估成本 |
| 96 | threeconsumed | Double | 允许 | 三号工时 |
| 97 | account | String | 允许 | 用户 |
| 98 | twentysixconsumed | Double | 允许 | 二十六号工时 |
| 99 | sixteenevaluationcost | Double | 允许 | 十六号评估成本 |
| 100 | oneevaluationcost | Double | 允许 | 一号评估成本 |
| 101 | fourevaluationcost | Double | 允许 | 四号评估成本 |
| 102 | fiveevaluationtime | Double | 允许 | 五号评估工时 |
| 103 | fourconsumed | Double | 允许 | 四号工时 |
| 104 | sixteenconsumed | Double | 允许 | 十六号工时 |
| 105 | dayname | String | 允许 | 天（显示） |
| 106 | <动态属性> | Object | 允许 | 支持动态属性 |

#### AccountTaskestimateSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_month_eq | String | 允许 | 条件字段：month<br>条件组合方式：`=` |
| 2 | n_date_eq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`=`<br>时间格式：yyyy-MM-dd |
| 3 | n_id_eq | String | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 4 | n_year_eq | String | 允许 | 条件字段：year<br>条件组合方式：`=` |
| 5 | n_project_in | Long | 允许 | 条件字段：project<br>条件组合方式：`in(...)` |
| 6 | n_account_eq | String | 允许 | 条件字段：account<br>条件组合方式：`=` |
| 7 | customcond | String | 允许 | 自定义查询条件 |
| 8 | customparams | String | 允许 | 自定义查询参数 |
| 9 | query | String | 允许 | 快速搜索 |
| 10 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 11 | page | int | 允许 | 当前页数<br>默认值0 |
| 12 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 13 | sort | String | 允许 | 排序 |
