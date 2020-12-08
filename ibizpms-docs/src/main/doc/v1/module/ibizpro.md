# iBizPro模块(ibizpro)

  

## 实体关系
{% plantuml %}

package iBizPro模块 {

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

class 计划模板 {
}

class 计划模板详情 {
}

class 系统配置表 {
}


平台产品 *-- 需求模块 


需求模块 *-- 需求模块 


平台产品 *-- 需求 


需求模块 *-- 需求 



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|IBZPRO_PRODUCT|[平台产品](ibizpro/IBZProProduct.md)|主实体|&nbsp;|
|IBZPRO_STORY|[需求](ibizpro/IBZProStory.md)|主实体|&nbsp;|
|IBZPRO_STORYMODULE|[需求模块](ibizpro/IBZProStoryModule.md)|主实体|&nbsp;|
|IBZPRO_SYSTPL|[系统模板](ibizpro/IBZProSysTpl.md)|主实体|&nbsp;|
|IBZPRO_SYSTEM|[运行生产系统](ibizpro/IBZProSystem.md)|主实体|&nbsp;|
|IBZ_PLANTEMPLET|[计划模板](ibizpro/IbzPlanTemplet.md)|主实体|&nbsp;|
|IBZ_PLANTEMPLETDETAIL|[计划模板详情](ibizpro/IbzPlanTempletDetail.md)|主实体|&nbsp;|
|IBZPRO_CONFIG|[系统配置表](ibizpro/IbzproConfig.md)|主实体|&nbsp;|
