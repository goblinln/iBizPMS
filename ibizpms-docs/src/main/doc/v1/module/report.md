# 汇报模块(report)

  

## 实体关系
{% plantuml %}

package 汇报模块 {

class 日报 {
}

class 月报 {
}

class 汇报汇总 {
}

class 汇报角色配置 {
}

class 汇报 {
}

class 周报 {
}



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|IBZ_DAILY|[日报](report/IbzDaily.md)|主实体|&nbsp;|
|IBZ_MONTHLY|[月报](report/IbzMonthly.md)|主实体|&nbsp;|
|IBZ_REPORT|[汇报汇总](report/IbzReport.md)|主实体|&nbsp;|
|IBZ_REPORT_ROLE_CONFIG|[汇报角色配置](report/IbzReportRoleConfig.md)|主实体|&nbsp;|
|IBZ_REPORTLY|[汇报](report/IbzReportly.md)|主实体|&nbsp;|
|IBZ_WEEKLY|[周报](report/IbzWeekly.md)|主实体|&nbsp;|
