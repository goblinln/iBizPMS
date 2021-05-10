import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'manager': commonLogic.appcommonhandle("负责人",null),
		'isleaf': commonLogic.appcommonhandle("无子部门",null),
		'grade': commonLogic.appcommonhandle("grade",null),
		'function': commonLogic.appcommonhandle("function",null),
		'order': commonLogic.appcommonhandle("order",null),
		'path': commonLogic.appcommonhandle("path",null),
		'position': commonLogic.appcommonhandle("position",null),
		'id': commonLogic.appcommonhandle("id",null),
		'name': commonLogic.appcommonhandle("部门名称",null),
		'parentname': commonLogic.appcommonhandle("上级部门",null),
		'parent': commonLogic.appcommonhandle("parent",null),
		'deptsn': commonLogic.appcommonhandle("部门编号",null),
	},
		views: {
			'pickupview': {
				caption: commonLogic.appcommonhandle("部门",null),
				title: commonLogic.appcommonhandle("部门数据选择视图",null),
			},
			'infoeditview': {
				caption: commonLogic.appcommonhandle("部门信息",null),
				title: commonLogic.appcommonhandle("部门编辑视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("部门信息",null),
				title: commonLogic.appcommonhandle("部门编辑视图",null),
			},
			'maingridview': {
				caption: commonLogic.appcommonhandle("部门",null),
				title: commonLogic.appcommonhandle("部门",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("部门",null),
				title: commonLogic.appcommonhandle("部门选择表格视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("部门名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("部门名称",null), 
				'parentname': commonLogic.appcommonhandle("上级部门",null), 
				'manager': commonLogic.appcommonhandle("负责人",null), 
				'id': commonLogic.appcommonhandle("id",null), 
				'parent': commonLogic.appcommonhandle("parent",null), 
			},
			uiactions: {
			},
		},
		info_form: {
			details: {
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("部门名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("部门名称",null), 
				'parentname': commonLogic.appcommonhandle("上级部门",null), 
				'manager': commonLogic.appcommonhandle("负责人",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("ID",null),
				'name': commonLogic.appcommonhandle("部门",null),
				'parentname': commonLogic.appcommonhandle("上级部门",null),
				'manager': commonLogic.appcommonhandle("负责人",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				dept_delete: commonLogic.appcommonhandle("删除",null),
				dept_edit: commonLogic.appcommonhandle("编辑",null),
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("保存",null),
				tip: commonLogic.appcommonhandle("保存",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("保存并新建",null),
				tip: commonLogic.appcommonhandle("保存并新建",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction3_create': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
		},
		infoeditviewtoolbar_toolbar: {
		},
	};
	return data;
}
export default getLocaleResourceBase;