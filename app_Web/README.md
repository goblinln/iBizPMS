## 前言

### 1. 简介

R7动态模板是iBiz平台提供的一套全新的前端解决方案（以下简称前端动态模板），基于消费iBiz模型进行开发扩展。从某种意义上来讲，我们可以把它理解成解释器，根据所获取的iBiz模型数据（以下简称模型数据）和前端技术融合进行解释，绘制界面。需要注意的是，在这一过程中，它不关注模型数据的来源，只专注于自己解释模型数据，这样就提供了获取模型数据的灵活性，为实现业务提供了更多的可能，目前支持远端和本地2种方式来获取模型数据。本文档主要介绍该前端动态模板的整体架构、主要体系及二次开发做一阐述，旨在能够为开发人员提供一定开发指导支持。而今框架开源，希望能有更多志同道合的伙伴参与迭代 ^_^

### 2.开发语言：

`TypeScript`、`less`、`html`

### 3.技术选型：

- 前端MVVM框架：vue.js
- 路由：vue-router
- 状态管理：vuex
- 国际化：vue-i18n
- 数据交互：axios
- UI框架：element-ui , view-design
- 工具库：qs, path-to-regexp, rxjs，moment
- 图标库：font-awesome
- 图表集成：echarts
- 日历集成：fullcalendar
- 引入组件： tinymce，ibiz-vue-lib，ibiz-vue-pivottable，ibiz-gantt-elastic，interactjs
- 模型接口：@ibiz/dynamic-model-api 
- 代码风格检测：eslint

### 4.特点

前端动态模板除了继承R7标准版的标准Restful设计、业务逻辑与界面表现分离、快速介入的代码能力的3大特点，另外还具备分层设计、引擎化2大特点。

1.分层设计

分层设计这一特点体现在代码管理方式和应用内部2个方面。前端动态模板整个项目结构采用monorepo的方式来管理代码，packages目录下的ibiz-core、ibiz-plugin、ibiz-service、ibiz-vue各包专注解释自己的职责。在应用内部，又可划分UI体系和数据交互体系2个方面，在UI方面，从视图、部件、部件项逐层传递；在数据交互体系方面，从UI、UI服务、数据服务逐层传递。使用分层设计，层次清楚，低耦合，高内聚。

2.引擎化

引擎化这一特点是动态的必然结果。如需要实现某一个场景，我们会提前预置该场景的逻辑，该逻辑是通过接收模型数据进行不同的处理。相比于R7标准模板，前端动态模板始终只有一份代码，而R7标准模板，如果模型数据不一致，在成果物中所获得的代码会有所不同，在前端动态模板UI部分体现尤为明显。

## 快速上手

<blockquote style="border-color: blue;"><p>克隆项目</p></blockquote>

```javascript
git clone https://xxxxxx/vue-example.git
```

<blockquote style="border-color: blue;"><p>打开前端项目，进入工作空间下，执行安装依赖命令</p></blockquote>

```javascript
cd vue-example // 进入项目根目录
yarn install // 安装依赖
```

<blockquote style="border-color: blue;"><p>本地启动前，需执行 yarn link-model命令、修改远程代理地址</p></blockquote>

```javascript
yarn link-model // 把模型数据合入到前端assets目录下，走本地模式需用到
proxy: "http://172.16.190.141:30228/" // 修改远程代理文件 vue.config.js 代理地址
```

<blockquote style="border-color: blue;"><p>在工作空间下，执行启动命令</p></blockquote>

```
yarn serve
```

<blockquote style="border-color: blue;"><p>在工作空间下，执行打包命令，生成最终交付产物</p></blockquote>

```javascript
yarn build
```

## 目录结构

前端动态模板已经为你生成了一个完整的开发框架，提供了涵盖前端开发的各类功能和坑位，下面是整个项目的目录结构。

```javascript
|─ ─ app_Web
	|─ ─ packages												本地依赖包
		|─ ─ ibiz-core											ibiz-core核心依赖包
			|─ ─ src											工程文件夹
				|─ ─ engine										视图引擎
				|─ ─ entities									实体基类
				|─ ─ interface									接口
				|─ ─ logic										逻辑模型
				|─ ─ model										运行模型
					|─ ─ chart-detail							图表类模型
					|─ ─ error-detail							错误类模型
					|─ ─ form-detail							表单成员模型
					|─ ─ panel-detail							面板成员模型
				|─ ─ modules									模块
				|─ ─ service									服务基类
				|─ ─ types										声明文件
				|─ ─ utils										工具类
		|─ ─ ibiz-plugin										ibiz-plugin插件依赖包
		|─ ─ ibiz-service										ibiz-service服务依赖包
			|─ ─ src											工程文件夹
				|─ ─ authservice								权限服务
					|─ ─ XXX									应用实体名称
						|─ ─ XXX-auth-service-base.ts			应用实体权限服务文件
						|─ ─ XXX-auth-service.ts				自定义应用实体权限服务文件
					|─ ─ auth-service.ts						权限服务基类
				|─ ─ codelist									代码表服务
				|─ ─ counter									计数器服务
				|─ ─ entities									实体基类
					|─ ─ XXX									应用实体名称
						|─ ─ XXX-base.ts						应用实体基类文件
						|─ ─ XXX-keys.ts						应用实体属性文件
						|─ ─ XXX.ts								自定义应用实体文件	
				|─ ─ message									视图消息服务
				|─ ─ register									服务注册中心
				|─ ─ service									应用实体服务
					|─ ─ XXX									应用实体名称
						|─ ─ XXX-base.service.ts				应用实体服务文件
						|─ ─ XXX-.service.ts					自定义应用实体服务文件
				|─ ─ types										声明文件
				|─ ─ uiservice									UI服务
					|─ ─ XXX									应用实体名称
						|─ ─ XXX-ui-service-base.ts				应用实体UI服务文件
						|─ ─ XXX-ui-service.ts					自定义应用实体UI服务文件
		|─ ─ ibiz-vue											ibiz-vue Vue组件依赖包
			|─ ─ src											工程文件夹
				|─ ─ app-logic									应用逻辑
					|─ ─ app-backend-action.ts					后台界面行为
					|─ ─ app-front-ation.ts						前台界面行为
					|─ ─ app-lofic-factory.ts					界面行为工厂
				|─ ─ app-service								应用服务
					|─ ─ common-service							通用服务
						|─ ─ app-center-service.ts				应用中心服务
						|─ ─ app-component-sercice.ts			应用组件服务
						|─ ─ app-contet-store.ts				全局上下文仓库基类
						|─ ─ app-layout-service.ts				视图布局服务
						|─ ─ app-nav-history.ts					导航历史记录项
						|─ ─ app-navdata-service.ts				导航数据服务
						|─ ─ app-thrid-service.ts				第三方登录服务
						|─ ─ codelist-translator.ts				代码表翻译服务
						|─ ─ footer-items-service.ts			底部项绘制服务
						|─ ─ top-items-servicet.ts				顶部项绘制服务
						|─ ─ plugin-service.ts					插件服务
						|─ ─ ui-state-service.ts				应用UI状态管理服务
					|─ ─ loading-service						加载服务
						|─ ─ app-loading-service.ts				应用加载服务
						|─ ─ ctrl-loading-service.ts			部件加载服务
						|─ ─ view-loading-service.ts			视图加载服务
					|─ ─ logic-service							逻辑服务
						|─ ─ app-func-service.ts				应用功能服务
						|─ ─ app-global-action-service.ts		全局界面行为服务
						|─ ─ app-viewlogic-service.ts			视图逻辑服务
				|─ ─ components									组件
					|─ ─ common									通用Vue组件
					|─ ─ control								部件组件
					|─ ─ editor									编辑器组件
					|─ ─ layout									视图布局组件
					|─ ─ view									视图组件
				|─ ─ ctrl-model									部件模型类
				|─ ─ ctrl-service								部件服务
				|─ ─ decorators									装饰器
				|─ ─ directives									指令
				|─ ─ events										事件集
				|─ ─ types										声明文件
				|─ ─ utils										工具类
				|─ ─ view										视图组件基类
				|─ ─ view-container								视图容器
					|─ ─ app-indexview-shell.tsx				首页视图容器
					|─ ─ app-view-shell.tsx						视图容器
					|─ ─ view-container-base.tsx				视图容器基类
				|─ ─ widgets									部件组件基类
	|─ ─ public													public文件夹
		|─ ─ assets												静态文件夹
		|─ ─ environments										环境文件
	|─ ─ src													项目工程文件夹
		|─ ─ assets												静态文件夹
		|─ ─ components											项目组件
		|─ ─ config												应用配置
		|─ ─ environments										环境文件
		|─ ─ locale												多语言文件
		|─ ─ pages												视图文件
			|─ ─ main.ts										应用主函数入口
			|─ ─ router.ts										应用路由管理
		|─ ─ plugin												插件文件夹
		|─ ─ router												自定义路由配置
		|─ ─ store												全局状态管理
		|─ ─ styles												应用样式文件夹
			|─ ─ default.less									默认样式
			|─ ─ style2.less									style2样式
			|─ ─ app-style.less									样式表样式
			|─ ─ user.less										用户自定义样式
			|─ ─ var.css										css变量定义文件
		|─ ─ theme												主题文件夹
		|─ ─ utils												工具类文件
        |─ ─ app-register.ts									公共组件全局注册
        |─ ─ App.vue											入口文件
        |─ ─ user-register.ts									自定义组件全局注册
	|─ ─ package.json											依赖管理文件
	|─ ─ vue.config.js											vue cli配置
```

## 其他

更多信息参见该模板映射的静态文件README.md。