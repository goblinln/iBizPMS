# iBizPro模块(ibizpro)

  

## 实体关系
{% plantuml %}

package iBizPro模块 {

class 用户工时统计 {
}

class 平台产品 {
}

class 需求 {
}

class 需求模块 {
}

class 系统模板 {
}

class 运行生产系统 {
}

class 索引检索 {
}

class 产品日报 {
}

class 产品月报 {
}

class 产品周报 {
}

class 项目日报 {
}

class 项目月报 {
}

class 项目周报 {
}

class 计划模板 {
}

class 计划模板详情 {
}

class 系统配置表 {
}

class 产品汇报用户任务 {
}

class 项目汇报用户任务 {
}

class 项目工时统计 {
}

class 项目其他活动 {
}


平台产品 *-- 需求模块 


需求模块 *-- 需求模块 


平台产品 *-- 需求 


需求模块 *-- 需求 


计划模板 *-- 计划模板详情 



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|ACCOUNTTASKESTIMATE|[用户工时统计](ibizpro/AccountTaskestimate.md)|主实体|&nbsp;|
|IBZPRO_PRODUCT|[平台产品](ibizpro/IBZProProduct.md)|主实体|&nbsp;|
|IBZPRO_STORY|[需求](ibizpro/IBZProStory.md)|主实体|&nbsp;|
|IBZPRO_STORYMODULE|[需求模块](ibizpro/IBZProStoryModule.md)|主实体|&nbsp;|
|IBZPRO_SYSTPL|[系统模板](ibizpro/IBZProSysTpl.md)|主实体|&nbsp;|
|IBZPRO_SYSTEM|[运行生产系统](ibizpro/IBZProSystem.md)|主实体|&nbsp;|
|IBIZPRO_INDEX|[索引检索](ibizpro/IbizproIndex.md)|主实体|&nbsp;|
|IBIZPRO_PRODUCTDAILY|[产品日报](ibizpro/IbizproProductDaily.md)|主实体|&nbsp;|
|IBIZPRO_PRODUCTMONTHLY|[产品月报](ibizpro/IbizproProductMonthly.md)|主实体|&nbsp;|
|IBIZPRO_PRODUCTWEEKLY|[产品周报](ibizpro/IbizproProductWeekly.md)|主实体|&nbsp;|
|IBIZPRO_PROJECTDAILY|[项目日报](ibizpro/IbizproProjectDaily.md)|主实体|&nbsp;|
|IBIZPRO_PROJECTMONTHLY|[项目月报](ibizpro/IbizproProjectMonthly.md)|主实体|&nbsp;|
|IBZPRO_PROJECTWEEKLY|[项目周报](ibizpro/IbizproProjectWeekly.md)|主实体|&nbsp;|
|IBZ_PLANTEMPLET|[计划模板](ibizpro/IbzPlanTemplet.md)|主实体|&nbsp;|
|IBZ_PLANTEMPLETDETAIL|[计划模板详情](ibizpro/IbzPlanTempletDetail.md)|主实体|&nbsp;|
|IBZPRO_CONFIG|[系统配置表](ibizpro/IbzproConfig.md)|主实体|&nbsp;|
|IBIZPRO_PRODUCTUSERTASK|[产品汇报用户任务](ibizpro/IbzproProductUserTask.md)|主实体|&nbsp;|
|IBIZPRO_PROJECTUSERTASK|[项目汇报用户任务](ibizpro/IbzproProjectUserTask.md)|主实体|&nbsp;|
|PROJECTTASKESTIMATE|[项目工时统计](ibizpro/ProjectTaskestimate.md)|主实体|&nbsp;|
|PROJECTTODO|[项目其他活动](ibizpro/ProjectTodo.md)|主实体|&nbsp;|
