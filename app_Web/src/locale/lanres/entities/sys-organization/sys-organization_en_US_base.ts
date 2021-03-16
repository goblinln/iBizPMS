import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		orgid: commonLogic.appcommonhandle("The workflow start node was not found",null),
		orgcode: commonLogic.appcommonhandle("Updated By",null),
		orgname: commonLogic.appcommonhandle("名称",null),
		parentorgid: commonLogic.appcommonhandle("上级单位",null),
		shortname: commonLogic.appcommonhandle("Cancel",null),
		orglevel: commonLogic.appcommonhandle("单位级别",null),
		showorder: commonLogic.appcommonhandle("Audit",null),
		parentorgname: commonLogic.appcommonhandle("上级单位",null),
		domains: commonLogic.appcommonhandle("区属",null),
		enable: commonLogic.appcommonhandle("the workflow instance current processing step is invalid",null),
		createdate: commonLogic.appcommonhandle("Print",null),
		updatedate: commonLogic.appcommonhandle("Actor",null),
	},
		views: {
			gridexpview: {
				caption: commonLogic.appcommonhandle("单位",null),
				title: commonLogic.appcommonhandle("单位表格导航视图",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("单位",null),
				title: commonLogic.appcommonhandle("单位编辑视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("单位",null),
				title: commonLogic.appcommonhandle("单位表格视图",null),
			},
			usr2gridview: {
				caption: commonLogic.appcommonhandle("单位",null),
				title: commonLogic.appcommonhandle("单位单位实体表格视图(通讯录所有组织)",null),
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("单位基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("Actor",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("The workflow start node was not found",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				orgid: commonLogic.appcommonhandle("The workflow start node was not found",null), 
			},
			uiactions: {
			},
		},
		mainexp_grid: {
			columns: {
				orgname: commonLogic.appcommonhandle("名称",null),
				shortname: commonLogic.appcommonhandle("Cancel",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				orgid: commonLogic.appcommonhandle("The workflow start node was not found",null),
				shortname: commonLogic.appcommonhandle("Cancel",null),
				domains: commonLogic.appcommonhandle("区属",null),
				orgname: commonLogic.appcommonhandle("名称",null),
				orglevel: commonLogic.appcommonhandle("单位级别",null),
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
		gridviewtoolbar_toolbar: {
			tbitem3: {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
			},
			tbitem4: {
				caption: commonLogic.appcommonhandle("Edit",null),
				tip: commonLogic.appcommonhandle("Edit {0}",null),
			},
			tbitem6: {
				caption: commonLogic.appcommonhandle("Copy",null),
				tip: commonLogic.appcommonhandle("Copy {0}",null),
			},
			tbitem7: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem8: {
				caption: commonLogic.appcommonhandle("Remove",null),
				tip: commonLogic.appcommonhandle("Remove {0}",null),
			},
			tbitem9: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem13: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			tbitem10: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem16: {
				caption: commonLogic.appcommonhandle("其它",null),
				tip: commonLogic.appcommonhandle("其它",null),
			},
			tbitem21: {
				caption: commonLogic.appcommonhandle("Export Data Model",null),
				tip: commonLogic.appcommonhandle("导出数据模型",null),
			},
			tbitem23: {
				caption: commonLogic.appcommonhandle("数据导入",null),
				tip: commonLogic.appcommonhandle("数据导入",null),
			},
			tbitem17: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem19: {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
			tbitem18: {
				caption: commonLogic.appcommonhandle("Help",null),
				tip: commonLogic.appcommonhandle("Help",null),
			},
		},
		usr2gridviewtoolbar_toolbar: {
		},
	};
	return data;
}

export default getLocaleResourceBase;