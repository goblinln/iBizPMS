import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		passcase: commonLogic.appcommonhandle("通过用例数",null),
		blockedcase: commonLogic.appcommonhandle("阻塞用例数",null),
		totalruncase: commonLogic.appcommonhandle("总执行数",null),
		failcase: commonLogic.appcommonhandle("失败用例数",null),
		title: commonLogic.appcommonhandle("用例标题",null),
		totalcase: commonLogic.appcommonhandle("总用例数",null),
		passrate: commonLogic.appcommonhandle("用例通过率",null),
		id: commonLogic.appcommonhandle("用例编号",null),
		modulename: commonLogic.appcommonhandle("模块名称",null),
		product: commonLogic.appcommonhandle("产品",null),
		module: commonLogic.appcommonhandle("模块",null),
	},
		views: {
			testcasestatsgridview: {
				caption: commonLogic.appcommonhandle("用例统计表",null),
				title: commonLogic.appcommonhandle("测试用例统计表格视图",null),
			},
		},
		testcasestats_grid: {
			columns: {
				modulename: commonLogic.appcommonhandle("模块名称",null),
				totalcase: commonLogic.appcommonhandle("总用例数",null),
				passcase: commonLogic.appcommonhandle("通过",null),
				failcase: commonLogic.appcommonhandle("失败",null),
				blockedcase: commonLogic.appcommonhandle("阻塞",null),
				totalruncase: commonLogic.appcommonhandle("总执行数",null),
				passrate: commonLogic.appcommonhandle("用例通过率",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		testcasestats_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				product: commonLogic.appcommonhandle("产品",null), 
			},
			uiactions: {
			},
		},
		testcasestatsgridviewtoolbar_toolbar: {
			deuiaction7: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;