import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'ibizshort': commonLogic.appcommonhandle("简称",null),
		'isleaf': commonLogic.appcommonhandle("叶子模块",null),
		'type': commonLogic.appcommonhandle("类型（task）",null),
		'orderpk': commonLogic.appcommonhandle("数据选择排序",null),
		'name': commonLogic.appcommonhandle("模块名称",null),
		'order': commonLogic.appcommonhandle("排序值",null),
		'collector': commonLogic.appcommonhandle("collector",null),
		'grade': commonLogic.appcommonhandle("grade",null),
		'branch': commonLogic.appcommonhandle("branch",null),
		'path': commonLogic.appcommonhandle("path",null),
		'id': commonLogic.appcommonhandle("id",null),
		'owner': commonLogic.appcommonhandle("owner",null),
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'rootname': commonLogic.appcommonhandle("所属项目",null),
		'parentname': commonLogic.appcommonhandle("上级模块",null),
		'root': commonLogic.appcommonhandle("项目",null),
		'parent': commonLogic.appcommonhandle("id",null),
	},
		views: {
			'treeexpview': {
				caption: commonLogic.appcommonhandle("任务模块",null),
				title: commonLogic.appcommonhandle("任务模块树导航视图",null),
			},
			'tasktreeexpview': {
				caption: commonLogic.appcommonhandle("任务模块",null),
				title: commonLogic.appcommonhandle("任务模块树导航视图",null),
			},
			'maingridview': {
				caption: commonLogic.appcommonhandle("任务模块",null),
				title: commonLogic.appcommonhandle("实体表格视图",null),
			},
			'quickcfgview': {
				caption: commonLogic.appcommonhandle("任务模块",null),
				title: commonLogic.appcommonhandle("任务模块",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("模块信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("模块名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'root': commonLogic.appcommonhandle("所属项目",null), 
				'parent': commonLogic.appcommonhandle("上级模块",null), 
				'rootname': commonLogic.appcommonhandle("所属项目",null), 
				'parentname': commonLogic.appcommonhandle("上级模块",null), 
				'name': commonLogic.appcommonhandle("模块名称",null), 
				'short': commonLogic.appcommonhandle("简称",null), 
				'order': commonLogic.appcommonhandle("排序值",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("模块名称",null),
				'short': commonLogic.appcommonhandle("简称",null),
				'order': commonLogic.appcommonhandle("排序值",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		treeexpviewtreeexpbar_toolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("修复",null),
				tip: commonLogic.appcommonhandle("修复",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
		taskmoduleexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'all': commonLogic.appcommonhandle("所有模块",null),
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			refreshparent: commonLogic.appcommonhandle("刷新",null),
			},
		},
		moduleexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'all': commonLogic.appcommonhandle("所有模块",null),
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			refreshall: commonLogic.appcommonhandle("刷新",null),
			refreshparent: commonLogic.appcommonhandle("刷新",null),
			productmodule_openquickcfgview: commonLogic.appcommonhandle("编辑",null),
			projectmodule_openquickcfgview: commonLogic.appcommonhandle("编辑",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;