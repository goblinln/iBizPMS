import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			canceledby: commonLogic.appcommonhandle("由谁取消",null),
			left: commonLogic.appcommonhandle("预计剩余",null),
			openeddate: commonLogic.appcommonhandle("创建日期",null),
			color: commonLogic.appcommonhandle("标题颜色",null),
			id: commonLogic.appcommonhandle("编号",null),
			finishedby: commonLogic.appcommonhandle("由谁完成",null),
			finishedlist: commonLogic.appcommonhandle("完成者列表",null),
			realstarted: commonLogic.appcommonhandle("实际开始",null),
			closedby: commonLogic.appcommonhandle("由谁关闭",null),
			substatus: commonLogic.appcommonhandle("子状态",null),
			closedreason: commonLogic.appcommonhandle("关闭原因",null),
			lastediteddate: commonLogic.appcommonhandle("最后修改日期",null),
			assigneddate: commonLogic.appcommonhandle("指派日期",null),
			pri: commonLogic.appcommonhandle("优先级",null),
			lasteditedby: commonLogic.appcommonhandle("最后修改",null),
			status: commonLogic.appcommonhandle("任务状态",null),
			name: commonLogic.appcommonhandle("任务名称",null),
			closeddate: commonLogic.appcommonhandle("关闭时间",null),
			type: commonLogic.appcommonhandle("任务类型",null),
			assignedto: commonLogic.appcommonhandle("指派给",null),
			desc: commonLogic.appcommonhandle("任务描述",null),
			eststarted: commonLogic.appcommonhandle("预计开始",null),
			deadline: commonLogic.appcommonhandle("截止日期",null),
			deleted: commonLogic.appcommonhandle("已删除",null),
			mailto: commonLogic.appcommonhandle("抄送给",null),
			consumed: commonLogic.appcommonhandle("总计消耗",null),
			estimate: commonLogic.appcommonhandle("最初预计",null),
			openedby: commonLogic.appcommonhandle("由谁创建",null),
			canceleddate: commonLogic.appcommonhandle("取消时间",null),
			finisheddate: commonLogic.appcommonhandle("实际完成",null),
			modulename: commonLogic.appcommonhandle("所属模块",null),
			storyname: commonLogic.appcommonhandle("相关需求",null),
			projectname: commonLogic.appcommonhandle("所属项目",null),
			product: commonLogic.appcommonhandle("产品",null),
			storyversion: commonLogic.appcommonhandle("需求版本",null),
			productname: commonLogic.appcommonhandle("产品",null),
			parentname: commonLogic.appcommonhandle("父任务",null),
			project: commonLogic.appcommonhandle("所属项目",null),
			story: commonLogic.appcommonhandle("相关需求",null),
			parent: commonLogic.appcommonhandle("父任务",null),
			frombug: commonLogic.appcommonhandle("来源Bug",null),
			duration: commonLogic.appcommonhandle("持续时间",null),
			module: commonLogic.appcommonhandle("模块",null),
			path: commonLogic.appcommonhandle("模块路径",null),
			comment: commonLogic.appcommonhandle("备注",null),
			currentconsumed: commonLogic.appcommonhandle("本次消耗",null),
			totaltime: commonLogic.appcommonhandle("总计耗时",null),
			isleaf: commonLogic.appcommonhandle("是否子任务",null),
			allmodules: commonLogic.appcommonhandle("所有模块",null),
			multiple: commonLogic.appcommonhandle("多人任务",null),
			taskteams: commonLogic.appcommonhandle("项目团队成员",null),
			modulename1: commonLogic.appcommonhandle("所属模块",null),
			ibztaskestimates: commonLogic.appcommonhandle("工时",null),
			isfavorites: commonLogic.appcommonhandle("是否收藏",null),
			status1: commonLogic.appcommonhandle("任务状态",null),
			tasktype: commonLogic.appcommonhandle("任务类型",null),
			files: commonLogic.appcommonhandle("附件",null),
			usernames: commonLogic.appcommonhandle("团队用户",null),
			isfinished: commonLogic.appcommonhandle("是否完成",null),
			replycount: commonLogic.appcommonhandle("回复数量",null),
			hasdetail: commonLogic.appcommonhandle("是否填写描述",null),
			updatedate: commonLogic.appcommonhandle("最后的更新日期",null),
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null),
			progressrate: commonLogic.appcommonhandle("进度",null),
			delay: commonLogic.appcommonhandle("延期",null),
			mailtopk: commonLogic.appcommonhandle("抄送给",null),
			mailtoconact: commonLogic.appcommonhandle("联系人",null),
			statusorder: commonLogic.appcommonhandle("排序",null),
			myconsumed: commonLogic.appcommonhandle("之前消耗",null),
			mytotaltime: commonLogic.appcommonhandle("我的总消耗",null),
			assignedtozj: commonLogic.appcommonhandle("转交给",null),
			plan: commonLogic.appcommonhandle("编号",null),
			taskspecies: commonLogic.appcommonhandle("任务种别",null),
			configweek: commonLogic.appcommonhandle("周期设置周几",null),
			configmonth: commonLogic.appcommonhandle("周期设置月",null),
			configtype: commonLogic.appcommonhandle("周期类型",null),
			configbeforedays: commonLogic.appcommonhandle("提前天数",null),
			configday: commonLogic.appcommonhandle("间隔天数",null),
			configend: commonLogic.appcommonhandle("过期日期",null),
			configbegin: commonLogic.appcommonhandle("开始日期",null),
			planname: commonLogic.appcommonhandle("所属计划",null),
			idvalue: commonLogic.appcommonhandle("关联编号",null),
			cycle: commonLogic.appcommonhandle("周期",null),
		},
			views: {
				subtasknewview: {
					caption: commonLogic.appcommonhandle("子任务",null),
					title: commonLogic.appcommonhandle("子任务",null),
				},
			},
			subtasknew_grid: {
				columns: {
					modulename: commonLogic.appcommonhandle("所属模块",null),
					storyname: commonLogic.appcommonhandle("相关需求",null),
					name: commonLogic.appcommonhandle("任务名称",null),
					type: commonLogic.appcommonhandle("任务类型",null),
					assignedto: commonLogic.appcommonhandle("指派给",null),
					eststarted: commonLogic.appcommonhandle("预计开始",null),
					deadline: commonLogic.appcommonhandle("截止日期",null),
					desc: commonLogic.appcommonhandle("任务描述",null),
					pri: commonLogic.appcommonhandle("优先级",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			subtasknewviewtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("新建行",null),
					tip: commonLogic.appcommonhandle("新建行",null),
				},
				deuiaction3: {
					caption: commonLogic.appcommonhandle("保存行",null),
					tip: commonLogic.appcommonhandle("保存行",null),
				},
			},
		};
		return data;
}

export default getLocaleResourceBase;