# 服务接口-IbzLogin
## 接口说明
实体

## 接口清单
### ZT登录
#### 访问路径
/ibzlogins/{ibzlogin_id}/ztlogin

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlogin_id | Long | 实体主键ID |
| 2 | ibzlogindto | [IbzLoginDTO](#IbzLoginDTO) | 实体实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLoginDTO](#IbzLoginDTO)：实体实体传输对象 |

### 获取ZT账户登录信息
#### 访问路径
/ibzlogins/{ibzlogin_id}/getuser

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ibzlogin_id | Long | 实体主键ID |
| 2 | ibzlogindto | [IbzLoginDTO](#IbzLoginDTO) | 实体实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [IbzLoginDTO](#IbzLoginDTO)：实体实体传输对象 |

## 附录
### 数据类型说明
#### IbzLoginDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | realname | String | 允许 | 真实姓名 |
| 2 | id | Long | 不可 | ID |
| 3 | account | String | 允许 | 账户 |
| 4 | token | String | 允许 | token |
| 5 | commiter | String | 允许 | 源代码账户 |
| 6 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbiLoginSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_realname_like | String | 允许 | 条件字段：realname<br>条件组合方式：`%like%` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | customcond | String | 允许 | 自定义查询条件 |
| 4 | customparams | String | 允许 | 自定义查询参数 |
| 5 | query | String | 允许 | 快速搜索 |
| 6 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 7 | page | int | 允许 | 当前页数<br>默认值0 |
| 8 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 9 | sort | String | 允许 | 排序 |
