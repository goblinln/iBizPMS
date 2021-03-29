import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'sysupdatefeaturesname': commonLogic.appcommonhandle("系统更新功能名称",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'type': commonLogic.appcommonhandle("更新类型",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'upfeatures': commonLogic.appcommonhandle("更新功能",null),
		'sysupdatefeaturesid': commonLogic.appcommonhandle("系统更新功能标识",null),
		'displayorder': commonLogic.appcommonhandle("展示顺序",null),
		'featuresdesc': commonLogic.appcommonhandle("功能描述",null),
		'sysupdatelogname': commonLogic.appcommonhandle("所属更新",null),
		'sysupdatelogid': commonLogic.appcommonhandle("系统更新日志标识",null),
	},
		views: {
			'editview': {
				caption: commonLogic.appcommonhandle("系统更新功能",null),
				title: commonLogic.appcommonhandle("系统更新功能编辑视图",null),
			},
			'lastgridview': {
				caption: commonLogic.appcommonhandle("系统更新功能",null),
				title: commonLogic.appcommonhandle("系统更新功能表格视图",null),
			},
			'infoeditview': {
				caption: commonLogic.appcommonhandle("系统更新功能",null),
				title: commonLogic.appcommonhandle("系统更新功能编辑视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("系统更新功能",null),
				title: commonLogic.appcommonhandle("系统更新功能表格视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("系统更新功能基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("系统更新功能标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("系统更新功能名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'sys_update_logname': commonLogic.appcommonhandle("所属更新",null), 
				'type': commonLogic.appcommonhandle("更新类型",null), 
				'upfeatures': commonLogic.appcommonhandle("更新功能",null), 
				'displayorder': commonLogic.appcommonhandle("展示顺序",null), 
				'featuresdesc': commonLogic.appcommonhandle("功能描述",null), 
				'createman': commonLogic.appcommonhandle("建立人",null), 
				'createdate': commonLogic.appcommonhandle("建立时间",null), 
				'updateman': commonLogic.appcommonhandle("更新人",null), 
				'updatedate': commonLogic.appcommonhandle("更新时间",null), 
				'sys_update_featuresid': commonLogic.appcommonhandle("系统更新功能标识",null), 
				'sys_update_logid': commonLogic.appcommonhandle("系统更新日志标识",null), 
			},
			uiactions: {
			},
		},
		infomain_form: {
			details: {
				'group1': commonLogic.appcommonhandle("系统更新功能基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("系统更新功能标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("系统更新功能名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'sys_update_logname': commonLogic.appcommonhandle("所属更新",null), 
				'type': commonLogic.appcommonhandle("更新类型",null), 
				'upfeatures': commonLogic.appcommonhandle("更新功能",null), 
				'displayorder': commonLogic.appcommonhandle("展示顺序",null), 
				'featuresdesc': commonLogic.appcommonhandle("功能描述",null), 
				'createman': commonLogic.appcommonhandle("建立人",null), 
				'createdate': commonLogic.appcommonhandle("建立时间",null), 
				'updateman': commonLogic.appcommonhandle("更新人",null), 
				'updatedate': commonLogic.appcommonhandle("更新时间",null), 
				'sys_update_featuresid': commonLogic.appcommonhandle("系统更新功能标识",null), 
				'sys_update_logid': commonLogic.appcommonhandle("系统更新日志标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'sys_update_logname': commonLogic.appcommonhandle("所属更新",null),
				'upfeatures': commonLogic.appcommonhandle("更新功能",null),
				'type': commonLogic.appcommonhandle("更新类型",null),
				'displayorder': commonLogic.appcommonhandle("展示顺序",null),
				'updateman': commonLogic.appcommonhandle("更新人",null),
				'updatedate': commonLogic.appcommonhandle("更新时间",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
			'seperator1': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'seperator3': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction5': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'seperator4': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;