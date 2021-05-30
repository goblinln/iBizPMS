import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'type': commonLogic.appcommonhandle("类型",null),
		'plancode': commonLogic.appcommonhandle("计划编号",null),
		'desc': commonLogic.appcommonhandle("计划名称",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'ibzplantempletdetailid': commonLogic.appcommonhandle("计划模板详情标识",null),
		'ibzplantempletdetailname': commonLogic.appcommonhandle("计划模板详情名称",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'expect': commonLogic.appcommonhandle("描述",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'plantempletid': commonLogic.appcommonhandle("产品计划模板标识",null),
	},
		views: {
			'gridview9': {
				caption: commonLogic.appcommonhandle("计划模板详情",null),
				title: commonLogic.appcommonhandle("计划模板详情表格视图",null),
			},
		},
		main_edit_grid: {
			columns: {
				'desc': commonLogic.appcommonhandle("计划名称",null),
				'type': commonLogic.appcommonhandle("类型",null),
				'expect1': commonLogic.appcommonhandle("描述",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		gridview9toolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;