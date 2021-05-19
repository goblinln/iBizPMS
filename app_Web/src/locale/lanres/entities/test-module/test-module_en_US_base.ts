import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'type': commonLogic.appcommonhandle("类型（story）",null),
		'path': commonLogic.appcommonhandle("path",null),
		'owner': commonLogic.appcommonhandle("owner",null),
		'order': commonLogic.appcommonhandle("排序值",null),
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'branch': commonLogic.appcommonhandle("branch",null),
		'collector': commonLogic.appcommonhandle("collector",null),
		'id': commonLogic.appcommonhandle("id",null),
		'isleaf': commonLogic.appcommonhandle("叶子模块",null),
		'name': commonLogic.appcommonhandle("名称",null),
		'ibizshort': commonLogic.appcommonhandle("简称",null),
		'grade': commonLogic.appcommonhandle("grade",null),
		'parentname': commonLogic.appcommonhandle("上级模块",null),
		'rootname': commonLogic.appcommonhandle("测试",null),
		'root': commonLogic.appcommonhandle("编号",null),
		'parent': commonLogic.appcommonhandle("id",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("测试模块表格视图",null),
			},
			'pickupview': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("测试模块数据选择视图",null),
			},
			'treeexpview': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("测试模块树导航视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("测试模块编辑视图",null),
			},
			'gridviewbranch': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("产品模块表格视图",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("测试模块选择表格视图",null),
			},
			'quickcfgview': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("需求模块",null),
			},
			'gridviewmain': {
				caption: commonLogic.appcommonhandle("测试模块",null),
				title: commonLogic.appcommonhandle("产品模块表格视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("测试模块基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'rootname': commonLogic.appcommonhandle("所属产品",null), 
				'branch': commonLogic.appcommonhandle("",null), 
				'path': commonLogic.appcommonhandle("path",null), 
				'root': commonLogic.appcommonhandle("编号",null), 
				'parentname': commonLogic.appcommonhandle("上级模块",null), 
				'name': commonLogic.appcommonhandle("名称",null), 
				'short': commonLogic.appcommonhandle("简称",null), 
				'order': commonLogic.appcommonhandle("排序值",null), 
				'id': commonLogic.appcommonhandle("id",null), 
				'parent': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		mainrow_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("名称",null),
				'branch': commonLogic.appcommonhandle("平台",null),
				'short': commonLogic.appcommonhandle("简称",null),
				'order': commonLogic.appcommonhandle("排序值",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		mainbranch_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("名称",null),
				'branch': commonLogic.appcommonhandle("平台",null),
				'short': commonLogic.appcommonhandle("简称",null),
				'order': commonLogic.appcommonhandle("排序值",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		main_grid: {
			columns: {
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
		gridviewmaintoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		gridviewbranchtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		moduleexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'branch': commonLogic.appcommonhandle("平台",null),
				'all': commonLogic.appcommonhandle("所有模块",null),
			},
			uiactions: {
			testmodule_openquickcfgview: commonLogic.appcommonhandle("编辑",null),
			refreshall: commonLogic.appcommonhandle("刷新",null),
			refreshparent: commonLogic.appcommonhandle("刷新",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;