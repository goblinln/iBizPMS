import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'acl': commonLogic.appcommonhandle("acl",null),
		'desc': commonLogic.appcommonhandle("分组描述",null),
		'id': commonLogic.appcommonhandle("ID",null),
		'name': commonLogic.appcommonhandle("分组名称",null),
		'role': commonLogic.appcommonhandle("role",null),
	},
		views: {
			'maingridview': {
				caption: commonLogic.appcommonhandle("群组",null),
				title: commonLogic.appcommonhandle("群组表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("分组信息",null),
				title: commonLogic.appcommonhandle("群组编辑视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("ID",null), 
				'srfmajortext': commonLogic.appcommonhandle("分组名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("分组名称",null), 
				'desc': commonLogic.appcommonhandle("分组描述",null), 
				'id': commonLogic.appcommonhandle("ID",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("ID",null),
				'name': commonLogic.appcommonhandle("分组名称",null),
				'desc': commonLogic.appcommonhandle("分组描述",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			edit: commonLogic.appcommonhandle("Edit",null),
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
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
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;