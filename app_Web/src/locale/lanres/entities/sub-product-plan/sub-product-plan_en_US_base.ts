import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			taskscnt: commonLogic.appcommonhandle("总任务数",null),
			estimatecnt: commonLogic.appcommonhandle("工时数",null),
			comment: commonLogic.appcommonhandle("备注",null),
			title: commonLogic.appcommonhandle("名称",null),
			delaytaskscnt: commonLogic.appcommonhandle("延迟任务数",null),
			oldtitle: commonLogic.appcommonhandle("上一次计划名称",null),
			id: commonLogic.appcommonhandle("编号",null),
			begin: commonLogic.appcommonhandle("开始日期",null),
			statuss: commonLogic.appcommonhandle("状态",null),
			desc: commonLogic.appcommonhandle("描述",null),
			end: commonLogic.appcommonhandle("结束日期",null),
			delay: commonLogic.appcommonhandle("延期",null),
			duration: commonLogic.appcommonhandle("持续时间",null),
			beginstr: commonLogic.appcommonhandle("开始日期",null),
			leftestimate: commonLogic.appcommonhandle("剩余工时",null),
			plantemplet: commonLogic.appcommonhandle("计划模板",null),
			unfinishedtaskscnt: commonLogic.appcommonhandle("未完成任务数",null),
			endstr: commonLogic.appcommonhandle("结束日期",null),
			status: commonLogic.appcommonhandle("计划状态",null),
			isexpired: commonLogic.appcommonhandle("是否过期",null),
			deleted: commonLogic.appcommonhandle("已删除",null),
			consumedestimate: commonLogic.appcommonhandle("消耗工时",null),
			order: commonLogic.appcommonhandle("排序",null),
			isleaf: commonLogic.appcommonhandle("叶子节点",null),
			future: commonLogic.appcommonhandle("待定",null),
			storycnt: commonLogic.appcommonhandle("需求数",null),
			delta: commonLogic.appcommonhandle("周期",null),
			finishedtaskscnt: commonLogic.appcommonhandle("完成任务数",null),
			bugcnt: commonLogic.appcommonhandle("bug数",null),
			parentname: commonLogic.appcommonhandle("父计划名称",null),
			branch: commonLogic.appcommonhandle("平台/分支",null),
			parent: commonLogic.appcommonhandle("父计划",null),
			product: commonLogic.appcommonhandle("产品",null),
			begintime: commonLogic.appcommonhandle("开始时间",null),
			ordernum: commonLogic.appcommonhandle("排序",null),
		},
			views: {
				subplaneditview: {
					caption: commonLogic.appcommonhandle("子计划",null),
					title: commonLogic.appcommonhandle("子计划",null),
				},
				subplangridview: {
					caption: commonLogic.appcommonhandle("子计划",null),
					title: commonLogic.appcommonhandle("子计划",null),
				},
				subplancreateview: {
					caption: commonLogic.appcommonhandle("产品计划",null),
					title: commonLogic.appcommonhandle("计划",null),
				},
			},
			mainsubplan_form: {
				details: {
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					group1: commonLogic.appcommonhandle("productplan基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("编号",null), 
					srfmajortext: commonLogic.appcommonhandle("名称",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					product: commonLogic.appcommonhandle("产品",null), 
					parent: commonLogic.appcommonhandle("父计划",null), 
					parentname: commonLogic.appcommonhandle("父计划名称",null), 
					title: commonLogic.appcommonhandle("名称",null), 
					begin: commonLogic.appcommonhandle("开始日期",null), 
					future: commonLogic.appcommonhandle("",null), 
					end: commonLogic.appcommonhandle("结束日期",null), 
					delta: commonLogic.appcommonhandle("",null), 
					desc: commonLogic.appcommonhandle("描述",null), 
					id: commonLogic.appcommonhandle("编号",null), 
				},
				uiactions: {
				},
			},
			mainsubplan_grid: {
				columns: {
					id: commonLogic.appcommonhandle("编号",null),
					title: commonLogic.appcommonhandle("名称",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			subplancreateviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			subplaneditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
		};
		return data;
}

export default getLocaleResourceBase;