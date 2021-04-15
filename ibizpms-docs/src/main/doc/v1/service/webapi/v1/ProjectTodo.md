# 服务接口-PROJECTTODO
## 接口说明
项目其他活动

## 接口清单
## 附录
### 数据类型说明
#### ProjectTodoDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | id | Long | 不可 | 编号 |
| 2 | account | String | 允许 | 所有者 |
| 3 | ibizprivate | String | 允许 | 私人事务 |
| 4 | status | String | 允许 | 状态 |
| 5 | type | String | 允许 | 类型 |
| 6 | finisheddate | Timestamp | 允许 | 完成时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 7 | pri | Integer | 允许 | 优先级 |
| 8 | config | String | 允许 | config |
| 9 | assignedby | String | 允许 | 由谁指派 |
| 10 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd |
| 11 | closeddate | Timestamp | 允许 | 关闭时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 12 | assignedto | String | 允许 | 指派给 |
| 13 | date | Timestamp | 允许 | 日期<br>时间格式：yyyy-MM-dd |
| 14 | finishedby | String | 允许 | 由谁完成 |
| 15 | name | String | 允许 | 待办名称 |
| 16 | cost | Integer | 允许 | 费用 |
| 17 | closedby | String | 允许 | 由谁关闭 |
| 18 | desc | String | 允许 | 描述 |
| 19 | idvalue | Long | 允许 | 项目编号 |
| 20 | consumed | Double | 允许 | 工时 |
| 21 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProjectTodoSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 2 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 3 | n_idvalue_eq | Long | 允许 | 条件字段：idvalue<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
