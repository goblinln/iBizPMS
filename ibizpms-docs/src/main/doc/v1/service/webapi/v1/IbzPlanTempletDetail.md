# 服务接口-IBZ_PLANTEMPLETDETAIL
## 接口说明
计划模板详情

## 接口清单
## 附录
### 数据类型说明
#### IbzPlanTempletDetailDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | type | String | 允许 | 类型 |
| 2 | plancode | String | 允许 | 计划编号 |
| 3 | desc | String | 允许 | 计划名称 |
| 4 | order | Integer | 允许 | 排序 |
| 5 | ibzplantempletdetailid | String | 不可 | 计划模板详情标识 |
| 6 | ibzplantempletdetailname | String | 允许 | 计划模板详情名称 |
| 7 | createman | String | 不可 | 建立人 |
| 8 | createdate | Timestamp | 不可 | 建立时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 9 | updateman | String | 不可 | 更新人 |
| 10 | expect | String | 允许 | 描述 |
| 11 | updatedate | Timestamp | 不可 | 更新时间<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 12 | plantempletid | String | 允许 | 产品计划模板标识 |
| 13 | <动态属性> | Object | 允许 | 支持动态属性 |

#### IbzPlanTempletDetailSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 2 | n_ibz_plantempletdetailname_like | String | 允许 | 条件字段：ibz_plantempletdetailname<br>条件组合方式：`%like%` |
| 3 | n_plantempletid_eq | String | 允许 | 条件字段：plantempletid<br>条件组合方式：`=` |
| 4 | customcond | String | 允许 | 自定义查询条件 |
| 5 | customparams | String | 允许 | 自定义查询参数 |
| 6 | query | String | 允许 | 快速搜索 |
| 7 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 8 | page | int | 允许 | 当前页数<br>默认值0 |
| 9 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 10 | sort | String | 允许 | 排序 |
