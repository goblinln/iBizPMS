import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			join: commonLogic.appcommonhandle("加盟日",null),
			hours: commonLogic.appcommonhandle("可用工时/天",null),
			id: commonLogic.appcommonhandle("编号",null),
			role: commonLogic.appcommonhandle("角色",null),
			type: commonLogic.appcommonhandle("团队类型",null),
			limited: commonLogic.appcommonhandle("受限用户",null),
			root: commonLogic.appcommonhandle("编号",null),
			account: commonLogic.appcommonhandle("用户",null),
			days: commonLogic.appcommonhandle("可用工日",null),
			order: commonLogic.appcommonhandle("排序",null),
			consumed: commonLogic.appcommonhandle("总计消耗",null),
			left: commonLogic.appcommonhandle("预计剩余",null),
			estimate: commonLogic.appcommonhandle("最初预计",null),
			total: commonLogic.appcommonhandle("总计可用",null),
			taskcnt: commonLogic.appcommonhandle("任务数",null),
			username: commonLogic.appcommonhandle("用户",null),
		},
			views: {
				usr2gridview: {
					caption: commonLogic.appcommonhandle("产品团队",null),
					title: commonLogic.appcommonhandle("产品团队表格视图",null),
				},
				editview: {
					caption: commonLogic.appcommonhandle("产品团队",null),
					title: commonLogic.appcommonhandle("产品团队编辑视图",null),
				},
				productteamlistview: {
					caption: commonLogic.appcommonhandle("产品团队",null),
					title: commonLogic.appcommonhandle("产品团队列表视图",null),
				},
			},
			main_form: {
				details: {
					group1: commonLogic.appcommonhandle("产品团队基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					group2: commonLogic.appcommonhandle("操作信息",null), 
					formpage2: commonLogic.appcommonhandle("其它",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("编号",null), 
					srfmajortext: commonLogic.appcommonhandle("用户",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					id: commonLogic.appcommonhandle("编号",null), 
				},
				uiactions: {
				},
			},
			productteamedit_grid: {
				columns: {
					account: commonLogic.appcommonhandle("用户",null),
					role: commonLogic.appcommonhandle("角色",null),
					days: commonLogic.appcommonhandle("可用工日",null),
					hours: commonLogic.appcommonhandle("可用工时/天",null),
					limited: commonLogic.appcommonhandle("受限用户",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				newrow: commonLogic.appcommonhandle("新建行",null),
				remove: commonLogic.appcommonhandle("Remove",null),
				},
			},
			productteam_list: {
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
			usr2gridviewtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("新建行",null),
					tip: commonLogic.appcommonhandle("新建行",null),
				},
				deuiaction3: {
					caption: commonLogic.appcommonhandle("保存行",null),
					tip: commonLogic.appcommonhandle("保存行",null),
				},
			},
			editviewtoolbar_toolbar: {
				tbitem3: {
					caption: commonLogic.appcommonhandle("Save",null),
					tip: commonLogic.appcommonhandle("Save",null),
				},
				tbitem4: {
					caption: commonLogic.appcommonhandle("Save And New",null),
					tip: commonLogic.appcommonhandle("Save And New",null),
				},
				tbitem5: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
				tbitem7: {
					caption: commonLogic.appcommonhandle("Remove And Close",null),
					tip: commonLogic.appcommonhandle("Remove And Close Window",null),
				},
			},
			productteamlistviewtoolbar_toolbar: {
				deuiaction3_managermember: {
					caption: commonLogic.appcommonhandle("团队管理",null),
					tip: commonLogic.appcommonhandle("团队管理",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
			},
		};
		return data;
}

export default getLocaleResourceBase;