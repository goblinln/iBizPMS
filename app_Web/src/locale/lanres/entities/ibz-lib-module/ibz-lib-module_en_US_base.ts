import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'id': commonLogic.appcommonhandle("id",null),
		'branch': commonLogic.appcommonhandle("branch",null),
		'isleaf': commonLogic.appcommonhandle("叶子模块",null),
		'type': commonLogic.appcommonhandle("类型（story）",null),
		'ibizshort': commonLogic.appcommonhandle("简称",null),
		'grade': commonLogic.appcommonhandle("grade",null),
		'collector': commonLogic.appcommonhandle("collector",null),
		'path': commonLogic.appcommonhandle("path",null),
		'name': commonLogic.appcommonhandle("名称",null),
		'owner': commonLogic.appcommonhandle("owner",null),
		'order': commonLogic.appcommonhandle("排序值",null),
		'parentname': commonLogic.appcommonhandle("上级模块",null),
		'root': commonLogic.appcommonhandle("编号",null),
		'parent': commonLogic.appcommonhandle("id",null),
		'orgid': commonLogic.appcommonhandle("组织机构标识",null),
		'mdeptid': commonLogic.appcommonhandle("部门标识",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'mdeptname': commonLogic.appcommonhandle("归属部门名",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("用例库模块",null),
				title: commonLogic.appcommonhandle("用例库模块表格视图",null),
			},
			'casetreeexpview': {
				caption: commonLogic.appcommonhandle("用例",null),
				title: commonLogic.appcommonhandle("用例",null),
			},
			'treeexpview': {
				caption: commonLogic.appcommonhandle("模块维护",null),
				title: commonLogic.appcommonhandle("模块维护",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("用例库模块",null),
				title: commonLogic.appcommonhandle("用例库模块编辑视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("用例库模块基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("名称",null),
				'short': commonLogic.appcommonhandle("简称",null),
				'order': commonLogic.appcommonhandle("排序值",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
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
		treelibexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'all': commonLogic.appcommonhandle("所有模板",null),
			},
			uiactions: {
			},
		},
		treelibmodule_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'all': commonLogic.appcommonhandle("所有模板",null),
			},
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;