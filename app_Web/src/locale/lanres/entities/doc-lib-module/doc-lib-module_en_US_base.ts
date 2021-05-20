import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'isfavourites': commonLogic.appcommonhandle("是否已收藏",null),
		'grade': commonLogic.appcommonhandle("grade",null),
		'path': commonLogic.appcommonhandle("path",null),
		'order': commonLogic.appcommonhandle("排序值",null),
		'name': commonLogic.appcommonhandle("名称",null),
		'docqtype': commonLogic.appcommonhandle("查询类型",null),
		'owner': commonLogic.appcommonhandle("owner",null),
		'branch': commonLogic.appcommonhandle("branch",null),
		'isleaf': commonLogic.appcommonhandle("叶子模块",null),
		'type': commonLogic.appcommonhandle("类型",null),
		'ibizshort': commonLogic.appcommonhandle("简称",null),
		'doccnt': commonLogic.appcommonhandle("文档数",null),
		'collector': commonLogic.appcommonhandle("collector",null),
		'id': commonLogic.appcommonhandle("id",null),
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'modulename': commonLogic.appcommonhandle("上级模块",null),
		'doclibname': commonLogic.appcommonhandle("所属文档库",null),
		'parent': commonLogic.appcommonhandle("id",null),
		'root': commonLogic.appcommonhandle("编号",null),
		'mdeptid': commonLogic.appcommonhandle("部门标识",null),
		'orgid': commonLogic.appcommonhandle("组织机构标识",null),
		'mdeptname': commonLogic.appcommonhandle("归属部门名",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类表格视图",null),
			},
			'usr2gridview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类表格视图",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类选择表格视图",null),
			},
			'editviewmain': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类编辑视图",null),
			},
			'redirectview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类数据重定向视图",null),
			},
			'editview2': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类编辑视图",null),
			},
			'pickupview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类数据选择视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类编辑视图",null),
			},
			'mpickupview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类数据多项选择视图",null),
			},
			'childweihuitreeexpview': {
				caption: commonLogic.appcommonhandle("文档库分类实体树导航视图",null),
				title: commonLogic.appcommonhandle("文档库分类树导航视图",null),
			},
			'treeexpview': {
				caption: commonLogic.appcommonhandle("文档库分类",null),
				title: commonLogic.appcommonhandle("文档库分类树导航视图",null),
			},
		},
		mainedit_form: {
			details: {
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("id",null), 
				'name': commonLogic.appcommonhandle("名称",null), 
				'doclibname': commonLogic.appcommonhandle("所属文档库",null), 
				'docqtype': commonLogic.appcommonhandle("查询类型",null), 
				'isfavourites': commonLogic.appcommonhandle("是否已收藏",null), 
				'root': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("文档库分类基本信息",null), 
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
		editviewmaintoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		usr2gridviewtoolbar_toolbar: {
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
			'tbitem3': {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("Edit",null),
				tip: commonLogic.appcommonhandle("Edit {0}",null),
			},
			'tbitem6': {
				caption: commonLogic.appcommonhandle("Copy",null),
				tip: commonLogic.appcommonhandle("Copy {0}",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem8': {
				caption: commonLogic.appcommonhandle("Remove",null),
				tip: commonLogic.appcommonhandle("Remove {0}",null),
			},
			'tbitem9': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem13': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'tbitem10': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem16': {
				caption: commonLogic.appcommonhandle("其它",null),
				tip: commonLogic.appcommonhandle("其它",null),
			},
			'tbitem21': {
				caption: commonLogic.appcommonhandle("Export Data Model",null),
				tip: commonLogic.appcommonhandle("导出数据模型",null),
			},
			'tbitem23': {
				caption: commonLogic.appcommonhandle("数据导入",null),
				tip: commonLogic.appcommonhandle("数据导入",null),
			},
			'tbitem17': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem19': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
			'tbitem18': {
				caption: commonLogic.appcommonhandle("Help",null),
				tip: commonLogic.appcommonhandle("Help",null),
			},
		},
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("Save And New",null),
				tip: commonLogic.appcommonhandle("Save And New",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
		editview2toolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("Save And New",null),
				tip: commonLogic.appcommonhandle("Save And New",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
		treedoclib_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'allmodule': commonLogic.appcommonhandle("所有分类",null),
			},
			uiactions: {
			refreshall: commonLogic.appcommonhandle("刷新",null),
			},
		},
		moduleweihu_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'allmodule': commonLogic.appcommonhandle("所有分类",null),
			},
			uiactions: {
			refreshall: commonLogic.appcommonhandle("刷新",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;