import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		grade: commonLogic.appcommonhandle("级别",null),
		collector: commonLogic.appcommonhandle("collector",null),
		owner: commonLogic.appcommonhandle("owner",null),
		ibiz_storytype: commonLogic.appcommonhandle("需求模块类型",null),
		id: commonLogic.appcommonhandle("id",null),
		type: commonLogic.appcommonhandle("类型",null),
		ibizshort: commonLogic.appcommonhandle("简称",null),
		name: commonLogic.appcommonhandle("名称",null),
		deleted: commonLogic.appcommonhandle("已删除",null),
		path: commonLogic.appcommonhandle("路径",null),
		ibizid: commonLogic.appcommonhandle("IBIZ标识",null),
		productname: commonLogic.appcommonhandle("产品",null),
		root: commonLogic.appcommonhandle("编号",null),
		parent: commonLogic.appcommonhandle("id",null),
	},
		views: {
			gridview: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("需求模块表格视图",null),
			},
			optionview: {
				caption: commonLogic.appcommonhandle("同步",null),
				title: commonLogic.appcommonhandle("需求模块选项操作视图同步",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("需求模块编辑视图",null),
			},
		},
		sycfromibz_form: {
			details: {
				group1: commonLogic.appcommonhandle("需求模块基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("id",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				ibiz_id: commonLogic.appcommonhandle("IBIZ标识",null), 
				productname: commonLogic.appcommonhandle("产品",null), 
				root: commonLogic.appcommonhandle("编号",null), 
				type: commonLogic.appcommonhandle("类型",null), 
				id: commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("需求模块基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				group2: commonLogic.appcommonhandle("操作信息",null), 
				formpage2: commonLogic.appcommonhandle("其它",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("id",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				id: commonLogic.appcommonhandle("id",null), 
				root: commonLogic.appcommonhandle("编号",null), 
				ibiz_id: commonLogic.appcommonhandle("IBIZ标识",null), 
				name: commonLogic.appcommonhandle("名称",null), 
				type: commonLogic.appcommonhandle("类型",null), 
				grade: commonLogic.appcommonhandle("级别",null), 
				short: commonLogic.appcommonhandle("简称",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				id: commonLogic.appcommonhandle("id",null),
				name: commonLogic.appcommonhandle("名称",null),
				ibiz_id: commonLogic.appcommonhandle("IBIZ标识",null),
				root: commonLogic.appcommonhandle("编号",null),
			},
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
		gridviewtoolbar_toolbar: {
			deuiaction3_syncfromibiz: {
				caption: commonLogic.appcommonhandle("同步",null),
				tip: commonLogic.appcommonhandle("同步",null),
			},
			seperator2: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction1: {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			seperator1: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			seperator3: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction4: {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
		},
		editviewtoolbar_toolbar: {
		},
	};
	return data;
}
export default getLocaleResourceBase;