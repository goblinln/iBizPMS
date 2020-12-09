import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			severity: commonLogic.appcommonhandle("严重程度",null),
			storyversion: commonLogic.appcommonhandle("需求版本",null),
			linkbug: commonLogic.appcommonhandle("相关Bug",null),
			activateddate: commonLogic.appcommonhandle("激活日期",null),
			assignedto: commonLogic.appcommonhandle("指派给",null),
			resolution: commonLogic.appcommonhandle("解决方案",null),
			lastediteddate: commonLogic.appcommonhandle("修改日期",null),
			result: commonLogic.appcommonhandle("result",null),
			keywords: commonLogic.appcommonhandle("关键词",null),
			closedby: commonLogic.appcommonhandle("由谁关闭",null),
			browser: commonLogic.appcommonhandle("浏览器",null),
			steps: commonLogic.appcommonhandle("重现步骤",null),
			v2: commonLogic.appcommonhandle("v2",null),
			confirmed: commonLogic.appcommonhandle("是否确认",null),
			openedby: commonLogic.appcommonhandle("由谁创建",null),
			activatedcount: commonLogic.appcommonhandle("激活次数",null),
			openeddate: commonLogic.appcommonhandle("创建日期",null),
			closeddate: commonLogic.appcommonhandle("关闭日期",null),
			mailto: commonLogic.appcommonhandle("抄送给",null),
			assigneddate: commonLogic.appcommonhandle("指派日期",null),
			deadline: commonLogic.appcommonhandle("截止日期",null),
			color: commonLogic.appcommonhandle("标题颜色",null),
			resolveddate: commonLogic.appcommonhandle("解决日期",null),
			type: commonLogic.appcommonhandle("Bug类型",null),
			status: commonLogic.appcommonhandle("Bug状态",null),
			openedbuild: commonLogic.appcommonhandle("影响版本",null),
			v1: commonLogic.appcommonhandle("v1",null),
			deleted: commonLogic.appcommonhandle("已删除",null),
			lines: commonLogic.appcommonhandle("lines",null),
			substatus: commonLogic.appcommonhandle("子状态",null),
			id: commonLogic.appcommonhandle("Bug编号",null),
			found: commonLogic.appcommonhandle("found",null),
			resolvedby: commonLogic.appcommonhandle("解决者",null),
			resolvedbuild: commonLogic.appcommonhandle("解决版本",null),
			pri: commonLogic.appcommonhandle("优先级",null),
			os: commonLogic.appcommonhandle("操作系统",null),
			hardware: commonLogic.appcommonhandle("hardware",null),
			lasteditedby: commonLogic.appcommonhandle("最后修改者",null),
			title: commonLogic.appcommonhandle("Bug标题",null),
			productname: commonLogic.appcommonhandle("产品",null),
			projectname: commonLogic.appcommonhandle("项目",null),
			storyname: commonLogic.appcommonhandle("相关需求",null),
			caseversion: commonLogic.appcommonhandle("用例版本",null),
			repotype: commonLogic.appcommonhandle("代码类型",null),
			tostory: commonLogic.appcommonhandle("转需求",null),
			entry: commonLogic.appcommonhandle("应用",null),
			product: commonLogic.appcommonhandle("所属产品",null),
			totask: commonLogic.appcommonhandle("转任务",null),
			plan: commonLogic.appcommonhandle("所属计划",null),
			module: commonLogic.appcommonhandle("所属模块",null),
			branch: commonLogic.appcommonhandle("平台/分支",null),
			duplicatebug: commonLogic.appcommonhandle("重复ID",null),
			repo: commonLogic.appcommonhandle("代码",null),
			story: commonLogic.appcommonhandle("相关需求",null),
			ibizcase: commonLogic.appcommonhandle("相关用例",null),
			project: commonLogic.appcommonhandle("所属项目",null),
			task: commonLogic.appcommonhandle("相关任务",null),
			testtask: commonLogic.appcommonhandle("测试单",null),
			comment: commonLogic.appcommonhandle("备注",null),
			taskname: commonLogic.appcommonhandle("相关任务",null),
			modulename: commonLogic.appcommonhandle("模块名称",null),
			branchname: commonLogic.appcommonhandle("平台/分支",null),
			modulename1: commonLogic.appcommonhandle("模块名称",null),
			files: commonLogic.appcommonhandle("附件",null),
			mobimage: commonLogic.appcommonhandle("移动端图片",null),
			isfavorites: commonLogic.appcommonhandle("是否收藏",null),
			buildname: commonLogic.appcommonhandle("版本名称",null),
			buildproject: commonLogic.appcommonhandle("版本项目",null),
			createbuild: commonLogic.appcommonhandle("创建版本",null),
			overduebugs: commonLogic.appcommonhandle("过期天数",null),
			casename: commonLogic.appcommonhandle("相关用例",null),
			delay: commonLogic.appcommonhandle("延期",null),
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null),
			mailtopk: commonLogic.appcommonhandle("抄送给",null),
			mailtoconact: commonLogic.appcommonhandle("联系人",null),
			delayresolve: commonLogic.appcommonhandle("延期解决",null),
		},
			views: {
				plansubgridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				buildsubgridview_new: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				mpickupview4: {
					caption: commonLogic.appcommonhandle("关联Bug",null),
					title: commonLogic.appcommonhandle("关联Bug",null),
				},
				maineditview: {
					caption: commonLogic.appcommonhandle("Bug编辑",null),
					title: commonLogic.appcommonhandle("Bug编辑",null),
				},
				myfavoritegridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				maindashboardview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug数据看板视图",null),
				},
				gridview9_storyaffect: {
					caption: commonLogic.appcommonhandle("相关Bug",null),
					title: commonLogic.appcommonhandle("相关Bug",null),
				},
				pickupgridview4: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug选择表格视图",null),
				},
				pickupgridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug选择表格视图",null),
				},
				mpickupview: {
					caption: commonLogic.appcommonhandle("关联Bug",null),
					title: commonLogic.appcommonhandle("关联Bug",null),
				},
				activationview: {
					caption: commonLogic.appcommonhandle("激活Bug",null),
					title: commonLogic.appcommonhandle("激活Bug",null),
				},
				buildsubgridview_new_9212: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				buglifeeditview9: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug的一生",null),
				},
				editview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				mpickupview2: {
					caption: commonLogic.appcommonhandle("关联Bug",null),
					title: commonLogic.appcommonhandle("关联Bug",null),
				},
				editview_1162: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				testreportsubgridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				releasesubgridview_done: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				storytobugeditview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				stepsinfoeditview: {
					caption: commonLogic.appcommonhandle("重现步骤",null),
					title: commonLogic.appcommonhandle("Bug编辑视图",null),
				},
				bugkanbanview: {
					caption: commonLogic.appcommonhandle("Bug实体看板视图",null),
					title: commonLogic.appcommonhandle("Bug看板视图",null),
				},
				gridview9_storyformbug: {
					caption: commonLogic.appcommonhandle("来源Bug",null),
					title: commonLogic.appcommonhandle("来源Bug",null),
				},
				confirmview: {
					caption: commonLogic.appcommonhandle("确认Bug",null),
					title: commonLogic.appcommonhandle("确认Bug",null),
				},
				gridview9_assignedtome: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug表格视图",null),
				},
				usr2gridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug表格视图",null),
				},
				maindashboardview_link: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug数据看板视图",null),
				},
				gridview9_taskrelated: {
					caption: commonLogic.appcommonhandle("相关Bug",null),
					title: commonLogic.appcommonhandle("相关Bug",null),
				},
				editview_4791: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				projectgridview: {
					caption: commonLogic.appcommonhandle("项目",null),
					title: commonLogic.appcommonhandle("bug表格视图（项目）",null),
				},
				pickupgridview_buildlinkresolvedbugs: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug选择表格视图",null),
				},
				releasesubgridview_undone: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				todoeditview: {
					caption: commonLogic.appcommonhandle("待办提交",null),
					title: commonLogic.appcommonhandle("待办提交",null),
				},
				pickupgridview5: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug选择表格视图",null),
				},
				resolveview: {
					caption: commonLogic.appcommonhandle("解决Bug",null),
					title: commonLogic.appcommonhandle("解决Bug",null),
				},
				resolvechartview: {
					caption: commonLogic.appcommonhandle("解决Bug",null),
					title: commonLogic.appcommonhandle("Bug图表视图（解决Bug）",null),
				},
				gridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				gridview9_storyrelated: {
					caption: commonLogic.appcommonhandle("相关Bug",null),
					title: commonLogic.appcommonhandle("相关Bug",null),
				},
				gridview9_myassignedtome: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug表格视图",null),
				},
				tasktobugeditview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				mainmygridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
				casetobugeditview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug",null),
				},
				projectbugsgridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug实体表格视图(项目遗留得Bug)",null),
				},
				totalopenedchartview: {
					caption: commonLogic.appcommonhandle("累计创建Bug",null),
					title: commonLogic.appcommonhandle("Bug图表视图（累计创建Bug）",null),
				},
				testbugsgridview: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("Bug表格视图(遗留的bug)",null),
				},
				calendareditview: {
					caption: commonLogic.appcommonhandle("日历导航",null),
					title: commonLogic.appcommonhandle("Bug编辑视图（日历导航）",null),
				},
				closeview: {
					caption: commonLogic.appcommonhandle("关闭Bug",null),
					title: commonLogic.appcommonhandle("关闭Bug",null),
				},
				assingtoview: {
					caption: commonLogic.appcommonhandle("指派Bug",null),
					title: commonLogic.appcommonhandle("指派Bug",null),
				},
				dashboardmaineditview9: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("主信息",null),
				},
				mpickupview5: {
					caption: commonLogic.appcommonhandle("关联Bug",null),
					title: commonLogic.appcommonhandle("关联Bug",null),
				},
				buildsubgridview_done: {
					caption: commonLogic.appcommonhandle("Bug",null),
					title: commonLogic.appcommonhandle("bug表格视图",null),
				},
			},
			stepsinfo_form: {
				details: {
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel1: commonLogic.appcommonhandle("附件",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					id: commonLogic.appcommonhandle("",null), 
					title: commonLogic.appcommonhandle("",null), 
					steps: commonLogic.appcommonhandle("",null), 
					color: commonLogic.appcommonhandle("标题颜色",null), 
				},
				uiactions: {
				},
			},
			dashboardmainedit_form: {
				details: {
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel3: commonLogic.appcommonhandle("基本信息",null), 
					grouppanel4: commonLogic.appcommonhandle("项目/需求/任务",null), 
					grouppanel5: commonLogic.appcommonhandle("Bug的一生",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					comment: commonLogic.appcommonhandle("备注",null), 
					color: commonLogic.appcommonhandle("标题颜色",null), 
					files: commonLogic.appcommonhandle("附件",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					branchname: commonLogic.appcommonhandle("平台/分支",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					modulename: commonLogic.appcommonhandle("模块名称",null), 
					plan: commonLogic.appcommonhandle("所属计划",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					storyversion: commonLogic.appcommonhandle("需求版本",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					status: commonLogic.appcommonhandle("Bug状态",null), 
					activatedcount: commonLogic.appcommonhandle("激活次数",null), 
					activateddate: commonLogic.appcommonhandle("激活日期",null), 
					confirmed: commonLogic.appcommonhandle("是否确认",null), 
					assignedto: commonLogic.appcommonhandle("当前指派",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					openedby: commonLogic.appcommonhandle("由谁创建",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					resolvedby: commonLogic.appcommonhandle("由谁解决",null), 
					resolution: commonLogic.appcommonhandle("解决方案",null), 
					resolvedbuild: commonLogic.appcommonhandle("解决版本",null), 
					closedby: commonLogic.appcommonhandle("由谁关闭",null), 
					lasteditedby: commonLogic.appcommonhandle("最后修改者",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
				},
				uiactions: {
				},
			},
			close_form: {
				details: {
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					comment: commonLogic.appcommonhandle("备注",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
				},
				uiactions: {
				},
			},
			resolve_form: {
				details: {
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel4: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					resolution: commonLogic.appcommonhandle("解决方案",null), 
					resolvedbuild: commonLogic.appcommonhandle("解决版本",null), 
					buildproject: commonLogic.appcommonhandle("解决版本/所属项目",null), 
					buildname: commonLogic.appcommonhandle("",null), 
					createbuild: commonLogic.appcommonhandle("",null), 
					resolveddate: commonLogic.appcommonhandle("解决日期",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					files: commonLogic.appcommonhandle("附件",null), 
					comment: commonLogic.appcommonhandle("备注",null), 
				},
				uiactions: {
				},
			},
			assignto_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					comment: commonLogic.appcommonhandle("备注",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
				},
				uiactions: {
				},
			},
			confirm_form: {
				details: {
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					comment: commonLogic.appcommonhandle("备注",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
				},
				uiactions: {
				},
			},
			activation_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					resolvedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					files: commonLogic.appcommonhandle("附件",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					comment: commonLogic.appcommonhandle("备注",null), 
				},
				uiactions: {
				},
			},
			storytobug_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					modulename: commonLogic.appcommonhandle("所属模块",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					color: commonLogic.appcommonhandle("标题颜色",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					caseversion: commonLogic.appcommonhandle("用例版本",null), 
					case: commonLogic.appcommonhandle("相关用例",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
				},
				uiactions: {
				},
			},
			main_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					modulename: commonLogic.appcommonhandle("所属模块",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					color: commonLogic.appcommonhandle("",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					files: commonLogic.appcommonhandle("附件",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
				},
				uiactions: {
				},
			},
			casetobug_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					modulename: commonLogic.appcommonhandle("所属模块",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					caseversion: commonLogic.appcommonhandle("用例版本",null), 
					case: commonLogic.appcommonhandle("相关用例",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
				},
				uiactions: {
				},
			},
			tasktobug_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					modulename: commonLogic.appcommonhandle("所属模块",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					color: commonLogic.appcommonhandle("标题颜色",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					caseversion: commonLogic.appcommonhandle("用例版本",null), 
					case: commonLogic.appcommonhandle("相关用例",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
				},
				uiactions: {
				},
			},
			buildbugnew_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					modulename: commonLogic.appcommonhandle("所属模块",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					mailtoconact: commonLogic.appcommonhandle("",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					files: commonLogic.appcommonhandle("附件",null), 
					mailtopk: commonLogic.appcommonhandle("抄送给",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
				},
				uiactions: {
				},
			},
			dashboardmain_form: {
				details: {
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel3: commonLogic.appcommonhandle("分组面板",null), 
					maingroup1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					formpage2: commonLogic.appcommonhandle("项目/需求/任务",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("平台/分支",null), 
					branchname: commonLogic.appcommonhandle("平台/分支",null), 
					modulename1: commonLogic.appcommonhandle("模块名称",null), 
					plan: commonLogic.appcommonhandle("所属计划",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					status: commonLogic.appcommonhandle("Bug状态",null), 
					activatedcount: commonLogic.appcommonhandle("激活次数",null), 
					activateddate: commonLogic.appcommonhandle("激活日期",null), 
					confirmed: commonLogic.appcommonhandle("是否确认",null), 
					assignedto: commonLogic.appcommonhandle("当前指派",null), 
					assigneddate: commonLogic.appcommonhandle("于",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					delay: commonLogic.appcommonhandle("",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
				},
				uiactions: {
				},
			},
			dashboardbuglife_form: {
				details: {
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel3: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel4: commonLogic.appcommonhandle("分组面板",null), 
					buggroup1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("Bug的一生",null), 
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					formpage2: commonLogic.appcommonhandle("其他相关",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					openedby: commonLogic.appcommonhandle("由谁创建",null), 
					openeddate: commonLogic.appcommonhandle("于",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					resolvedby: commonLogic.appcommonhandle("由谁解决",null), 
					resolveddate: commonLogic.appcommonhandle("于",null), 
					delayresolve: commonLogic.appcommonhandle("",null), 
					resolvedbuild: commonLogic.appcommonhandle("解决版本",null), 
					resolution: commonLogic.appcommonhandle("解决方案",null), 
					closedby: commonLogic.appcommonhandle("由谁关闭",null), 
					closeddate: commonLogic.appcommonhandle("于",null), 
					lasteditedby: commonLogic.appcommonhandle("最后修改者",null), 
					lastediteddate: commonLogic.appcommonhandle("于",null), 
					linkbug: commonLogic.appcommonhandle("相关Bug",null), 
					case: commonLogic.appcommonhandle("相关用例",null), 
					casename: commonLogic.appcommonhandle("相关用例",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
				},
				uiactions: {
				},
			},
			pendingsubmission_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					productname: commonLogic.appcommonhandle("产品",null), 
					branch: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("所属产品",null), 
					modulename: commonLogic.appcommonhandle("所属模块",null), 
					module: commonLogic.appcommonhandle("所属模块",null), 
					project: commonLogic.appcommonhandle("所属项目",null), 
					projectname: commonLogic.appcommonhandle("项目",null), 
					openedbuild: commonLogic.appcommonhandle("影响版本",null), 
					assignedto: commonLogic.appcommonhandle("指派给",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					type: commonLogic.appcommonhandle("Bug类型",null), 
					os: commonLogic.appcommonhandle("操作系统",null), 
					browser: commonLogic.appcommonhandle("浏览器",null), 
					title: commonLogic.appcommonhandle("Bug标题",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					steps: commonLogic.appcommonhandle("重现步骤",null), 
					storyname: commonLogic.appcommonhandle("相关需求",null), 
					taskname: commonLogic.appcommonhandle("相关任务",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					keywords: commonLogic.appcommonhandle("关键词",null), 
					id: commonLogic.appcommonhandle("Bug编号",null), 
					story: commonLogic.appcommonhandle("相关需求",null), 
					task: commonLogic.appcommonhandle("相关任务",null), 
				},
				uiactions: {
				},
			},
			calendarmain_form: {
				details: {
					grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
					button1: commonLogic.appcommonhandle("确认",null), 
					button2: commonLogic.appcommonhandle("指派",null), 
					button3: commonLogic.appcommonhandle("解决",null), 
					button4: commonLogic.appcommonhandle("关闭",null), 
					button5: commonLogic.appcommonhandle("激活",null), 
					button6: commonLogic.appcommonhandle("编辑",null), 
					grouppanel3: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel4: commonLogic.appcommonhandle("重现步骤",null), 
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
					group1: commonLogic.appcommonhandle("Bug基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("Bug编号",null), 
					srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					id: commonLogic.appcommonhandle("",null), 
					status: commonLogic.appcommonhandle("",null), 
					title: commonLogic.appcommonhandle("",null), 
					severity: commonLogic.appcommonhandle("严重程度",null), 
					pri: commonLogic.appcommonhandle("优先级",null), 
					deadline: commonLogic.appcommonhandle("截止日期",null), 
					isfavorites: commonLogic.appcommonhandle("是否收藏",null), 
					confirmed: commonLogic.appcommonhandle("是否确认",null), 
					steps: commonLogic.appcommonhandle("",null), 
				},
				uiactions: {
				bug_confirmbugdash: commonLogic.appcommonhandle("确认",null),
				bug_assingtobugcz: commonLogic.appcommonhandle("指派",null),
				bug_resolvebugdash: commonLogic.appcommonhandle("解决",null),
				bug_closebugdash: commonLogic.appcommonhandle("关闭",null),
				bug_activation: commonLogic.appcommonhandle("激活",null),
				bug_maineditdash: commonLogic.appcommonhandle("编辑",null),
				},
			},
			storyaffectbug_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					resolvedby: commonLogic.appcommonhandle("解决者",null),
					resolution: commonLogic.appcommonhandle("解决方案",null),
					lasteditedby: commonLogic.appcommonhandle("最后修改者",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			main_plansub_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_unlinkbug: commonLogic.appcommonhandle("移除关联",null),
				},
			},
			pickupgird_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					openedby: commonLogic.appcommonhandle("创建",null),
					resolvedby: commonLogic.appcommonhandle("解决者",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			main_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_assingtobug: commonLogic.appcommonhandle("指派",null),
				bug_confirmbug: commonLogic.appcommonhandle("确认",null),
				bug_resolvebug: commonLogic.appcommonhandle("解决",null),
				bug_closebug: commonLogic.appcommonhandle("关闭",null),
				bug_mainedit: commonLogic.appcommonhandle("编辑",null),
				copy: commonLogic.appcommonhandle("Copy",null),
				bug_bugfavorites: commonLogic.appcommonhandle("收藏",null),
				bug_bugnfavorites: commonLogic.appcommonhandle("取消收藏",null),
				},
			},
			main_buildsub2_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("状态",null),
					openedby: commonLogic.appcommonhandle("创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					resolvedby: commonLogic.appcommonhandle("解决",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_unlinkbug_build: commonLogic.appcommonhandle("解除关联",null),
				},
			},
			main_buildsub3_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("创建",null),
					assignedto: commonLogic.appcommonhandle("指派",null),
					resolvedby: commonLogic.appcommonhandle("解决者",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					project: commonLogic.appcommonhandle("所属项目",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			main_buildsub_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("是否确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("创建",null),
					assignedto: commonLogic.appcommonhandle("指派",null),
					resolvedby: commonLogic.appcommonhandle("解决者",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作列",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				bug_confirmbug: commonLogic.appcommonhandle("确认",null),
				bug_resolvebug: commonLogic.appcommonhandle("解决",null),
				bug_closebug: commonLogic.appcommonhandle("关闭",null),
				copy: commonLogic.appcommonhandle("Copy",null),
				bug_mainedit: commonLogic.appcommonhandle("编辑",null),
				},
			},
			main_reportsub_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					openedby: commonLogic.appcommonhandle("创建",null),
					resolvedby: commonLogic.appcommonhandle("解决者",null),
					resolveddate: commonLogic.appcommonhandle("解决日期",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			taskrelated_grid: {
				columns: {
					id: commonLogic.appcommonhandle("Bug编号",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			storyrelated_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			main_releasesubr_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_releaseunlinkbug: commonLogic.appcommonhandle("移除bug",null),
				},
			},
			main_releasesub_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_releaseunlinkbugbyleftbug: commonLogic.appcommonhandle("移除bug",null),
				},
			},
			main_copy_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					severity: commonLogic.appcommonhandle("级别",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_assingtobug: commonLogic.appcommonhandle("指派",null),
				bug_confirmbug: commonLogic.appcommonhandle("确认",null),
				bug_resolvebug: commonLogic.appcommonhandle("解决",null),
				bug_closebug: commonLogic.appcommonhandle("关闭",null),
				bug_mainedit: commonLogic.appcommonhandle("编辑",null),
				copy: commonLogic.appcommonhandle("Copy",null),
				bug_bugfavorites: commonLogic.appcommonhandle("收藏",null),
				bug_bugnfavorites: commonLogic.appcommonhandle("取消收藏",null),
				},
			},
			main_dataexport_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			favoritemain_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					productname: commonLogic.appcommonhandle("产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_confirmbug: commonLogic.appcommonhandle("确认",null),
				bug_resolvebug: commonLogic.appcommonhandle("解决",null),
				bug_closebug: commonLogic.appcommonhandle("关闭",null),
				bug_mainedit: commonLogic.appcommonhandle("编辑",null),
				copy: commonLogic.appcommonhandle("Copy",null),
				bug_bugfavorites: commonLogic.appcommonhandle("收藏",null),
				bug_bugnfavorites: commonLogic.appcommonhandle("取消收藏",null),
				},
			},
			main2_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("状态",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			mygroupmain_grid: {
				columns: {
					id: commonLogic.appcommonhandle("ID",null),
					severity: commonLogic.appcommonhandle("级别",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					productname: commonLogic.appcommonhandle("产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					id: commonLogic.appcommonhandle("ID",null),
					pri: commonLogic.appcommonhandle("P",null),
					confirmed: commonLogic.appcommonhandle("确认",null),
					title: commonLogic.appcommonhandle("Bug标题",null),
					status: commonLogic.appcommonhandle("Bug状态",null),
					openedby: commonLogic.appcommonhandle("由谁创建",null),
					openeddate: commonLogic.appcommonhandle("创建日期",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					resolution: commonLogic.appcommonhandle("方案",null),
					lastediteddate: commonLogic.appcommonhandle("修改日期",null),
					activateddate: commonLogic.appcommonhandle("激活日期",null),
					isfavorites: commonLogic.appcommonhandle("是否收藏",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					projectname: commonLogic.appcommonhandle("项目",null),
			},
				uiactions: {
				bug_confirmbug: commonLogic.appcommonhandle("确认",null),
				bug_resolvebug: commonLogic.appcommonhandle("解决",null),
				bug_closebug: commonLogic.appcommonhandle("关闭",null),
				bug_mainedit: commonLogic.appcommonhandle("编辑",null),
				copy: commonLogic.appcommonhandle("Copy",null),
				bug_bugfavorites: commonLogic.appcommonhandle("收藏",null),
				bug_bugnfavorites: commonLogic.appcommonhandle("取消收藏",null),
				},
			},
			bugseverity_project_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugtype_project_chart: {
				nodata:commonLogic.appcommonhandle("",null),
			},
			bugstatus_project_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugresolution_project_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugopenedby_project_chart: {
				nodata:commonLogic.appcommonhandle("",null),
			},
			bugmodule_project_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugresolvedby_project_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			severity_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugtype_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugstatus_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugresolution_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugopenedby_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugmodule_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugresolvedby_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			curuseropenedbug_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			curuserresolvebug_chart: {
				nodata:commonLogic.appcommonhandle("无",null),
			},
			bugkanban_kanban: {
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			default_searchform: {
				details: {
					formpage1: commonLogic.appcommonhandle("常规条件",null), 
				},
				uiactions: {
				},
			},
			maineditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			storytobugeditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			editviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			casetobugeditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			plansubgridviewtoolbar_toolbar: {
				deuiaction3_planrelationbug: {
					caption: commonLogic.appcommonhandle("关联Bug",null),
					tip: commonLogic.appcommonhandle("关联Bug",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			tasktobugeditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			projectgridviewtoolbar_toolbar: {
				deuiaction3_create: {
					caption: commonLogic.appcommonhandle("新建",null),
					tip: commonLogic.appcommonhandle("新建",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			bugkanbanviewtoolbar_toolbar: {
				deuiaction3_create: {
					caption: commonLogic.appcommonhandle("新建",null),
					tip: commonLogic.appcommonhandle("新建",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
			},
			usr2gridviewtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("保存",null),
					tip: commonLogic.appcommonhandle("保存",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			buildsubgridview_donetoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("关联bug",null),
					tip: commonLogic.appcommonhandle("关联bug",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			buildsubgridview_newtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			editview_4791toolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			buildsubgridview_new_9212toolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("提Bug",null),
					tip: commonLogic.appcommonhandle("提Bug",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			testreportsubgridviewtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			projectbugsgridviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			releasesubgridview_donetoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("关联bug",null),
					tip: commonLogic.appcommonhandle("关联bug",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			releasesubgridview_undonetoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("关联bug",null),
					tip: commonLogic.appcommonhandle("关联bug",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			gridviewtoolbar_toolbar: {
				deuiaction3_create: {
					caption: commonLogic.appcommonhandle("新建",null),
					tip: commonLogic.appcommonhandle("新建",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			testbugsgridviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			myfavoritegridviewtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			todoeditviewtoolbar_toolbar: {
			},
			mainmygridviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
			},
			editview_1162toolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			maindashboardviewdashboard_container1_portlet: {
				uiactions: {
				},
			},
			maindashboardviewdashboard_container2_portlet: {
				uiactions: {
				},
			},
			stepsinfo_portlet: {
			  stepsinfo: {
				  title: commonLogic.appcommonhandle("重现步骤", null)
			  }
				uiactions: {
				},
			},
			actionhistorylist_portlet: {
			  actionhistorylist: {
				  title: commonLogic.appcommonhandle("历史记录", null)
			  }
				uiactions: {
				},
			},
			maindashboardviewdashboard_container4_portlet: {
				uiactions: {
				},
			},
			bugdashboardactions_portlet: {
			  bugdashboardactions: {
				  title: commonLogic.appcommonhandle("操作栏", null)
			  }
				uiactions: {
				exit: commonLogic.appcommonhandle("返回",null),
				bug_maineditdash: commonLogic.appcommonhandle("编辑",null),
				bug_closebugdash: commonLogic.appcommonhandle("关闭",null),
				bug_resolvebugdash: commonLogic.appcommonhandle("解决",null),
				bug_assingtobugcz: commonLogic.appcommonhandle("指派",null),
				bug_confirmbugdash: commonLogic.appcommonhandle("确认",null),
				bug_activation: commonLogic.appcommonhandle("激活",null),
				bug_tostory: commonLogic.appcommonhandle("提需求",null),
				bug_buildusecase: commonLogic.appcommonhandle("建用例",null),
				bug_delete: commonLogic.appcommonhandle("删除",null),
				},
			},
			maindashboardviewdashboard_container3_portlet: {
				uiactions: {
				},
			},
			bugseverity_project_portlet: {
			  bugseverity_project: {
				  title: commonLogic.appcommonhandle("Bug严重级别分布", null)
			  }
				uiactions: {
				},
			},
			bugtype_project_portlet: {
			  bugtype_project: {
				  title: commonLogic.appcommonhandle("Bug类型分布", null)
			  }
				uiactions: {
				},
			},
			bugstatus_project_portlet: {
			  bugstatus_project: {
				  title: commonLogic.appcommonhandle("Bug状态分布", null)
			  }
				uiactions: {
				},
			},
			bugresolution_project_portlet: {
			  bugresolution_project: {
				  title: commonLogic.appcommonhandle("Bug解决方案分布", null)
			  }
				uiactions: {
				},
			},
			bugopenedby_project_portlet: {
			  bugopenedby_project: {
				  title: commonLogic.appcommonhandle("Bug创建者分布", null)
			  }
				uiactions: {
				},
			},
			bugmodule_project_portlet: {
			  bugmodule_project: {
				  title: commonLogic.appcommonhandle("Bug模块分布", null)
			  }
				uiactions: {
				},
			},
			bugresolvedby_project_portlet: {
			  bugresolvedby_project: {
				  title: commonLogic.appcommonhandle("Bug解决者分布", null)
			  }
				uiactions: {
				},
			},
			dashboardbugmain_portlet: {
			  dashboardbugmain: {
				  title: commonLogic.appcommonhandle("基本信息", null)
			  }
				uiactions: {
				},
			},
			dashboardbuglife_portlet: {
			  dashboardbuglife: {
				  title: commonLogic.appcommonhandle("Bug的一生", null)
			  }
				uiactions: {
				},
			},
			bugseverity_portlet: {
			  bugseverity: {
				  title: commonLogic.appcommonhandle("Bug严重级别分布", null)
			  }
				uiactions: {
				},
			},
			bugtype_portlet: {
			  bugtype: {
				  title: commonLogic.appcommonhandle("Bug类型分布", null)
			  }
				uiactions: {
				},
			},
			bugstatus_portlet: {
			  bugstatus: {
				  title: commonLogic.appcommonhandle("Bug状态分布", null)
			  }
				uiactions: {
				},
			},
			bugresolution_portlet: {
			  bugresolution: {
				  title: commonLogic.appcommonhandle("Bug解决方案分布", null)
			  }
				uiactions: {
				},
			},
			bugopenedby_portlet: {
			  bugopenedby: {
				  title: commonLogic.appcommonhandle("Bug创建者分布", null)
			  }
				uiactions: {
				},
			},
			bugmodule_portlet: {
			  bugmodule: {
				  title: commonLogic.appcommonhandle("Bug模块分布", null)
			  }
				uiactions: {
				},
			},
			bugresolvedby_portlet: {
			  bugresolvedby: {
				  title: commonLogic.appcommonhandle("Bug解决者分布", null)
			  }
				uiactions: {
				},
			},
			assignedtomebug_portlet: {
			  assignedtomebug: {
				  title: commonLogic.appcommonhandle("指派bug", null)
			  }
				uiactions: {
				bug_more: commonLogic.appcommonhandle("MORE",null),
				},
			},
			maindashboardview_linkdashboard_container1_portlet: {
				uiactions: {
				},
			},
			maindashboardview_linkdashboard_container2_portlet: {
				uiactions: {
				},
			},
			maindashboardview_linkdashboard_container4_portlet: {
				uiactions: {
				},
			},
			maindashboardview_linkdashboard_container3_portlet: {
				uiactions: {
				},
			},
			totalcuropenedbug_portlet: {
			  totalcuropenedbug: {
				  title: commonLogic.appcommonhandle("累计创建的Bug", null)
			  }
				uiactions: {
				},
			},
			curuserresolvebug_portlet: {
			  curuserresolvebug: {
				  title: commonLogic.appcommonhandle("解决的Bug", null)
			  }
				uiactions: {
				},
			},
		};
		return data;
}

export default getLocaleResourceBase;