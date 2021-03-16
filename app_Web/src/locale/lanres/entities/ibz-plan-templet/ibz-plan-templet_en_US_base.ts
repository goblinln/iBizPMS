import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		createdate: commonLogic.appcommonhandle("建立时间",null),
		plans: commonLogic.appcommonhandle("计划",null),
		acl: commonLogic.appcommonhandle("权限",null),
		createmanname: commonLogic.appcommonhandle("创建人姓名",null),
		product: commonLogic.appcommonhandle("产品",null),
		ibzplantempletid: commonLogic.appcommonhandle("产品计划模板标识",null),
		plantempletdetail: commonLogic.appcommonhandle("计划项",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		ibzplantempletname: commonLogic.appcommonhandle("模板名称",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		createman: commonLogic.appcommonhandle("建立人",null),
	},
		views: {
			optionview: {
				caption: commonLogic.appcommonhandle("计划模板",null),
				title: commonLogic.appcommonhandle("产品计划模板选项操作视图",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("计划模板",null),
				title: commonLogic.appcommonhandle("产品计划模板编辑视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("计划模板",null),
				title: commonLogic.appcommonhandle("产品计划模板表格视图",null),
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("产品计划模板基本信息",null), 
				druipart1: commonLogic.appcommonhandle("",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("产品计划模板标识",null), 
				srfmajortext: commonLogic.appcommonhandle("模板名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				ibz_plantempletname: commonLogic.appcommonhandle("模板名称",null), 
				acl: commonLogic.appcommonhandle("权限",null), 
				plans: commonLogic.appcommonhandle("计划",null), 
				product: commonLogic.appcommonhandle("产品",null), 
				ibz_plantempletid: commonLogic.appcommonhandle("产品计划模板标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				ibz_plantempletname: commonLogic.appcommonhandle("模板名称",null),
				updatedate: commonLogic.appcommonhandle("更新时间",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				ibz_plantempletname: commonLogic.appcommonhandle("模板名称",null),
				updatedate: commonLogic.appcommonhandle("更新时间",null),
		},
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
			deuiaction1: {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
			},
			deuiaction4: {
				caption: commonLogic.appcommonhandle("Remove",null),
				tip: commonLogic.appcommonhandle("Remove {0}",null),
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
			deuiaction5: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			seperator4: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction7: {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		editviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;