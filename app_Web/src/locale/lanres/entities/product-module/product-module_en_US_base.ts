import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		path: commonLogic.appcommonhandle("path",null),
		orderpk: commonLogic.appcommonhandle("数据选择排序",null),
		deleted: commonLogic.appcommonhandle("逻辑删除标志",null),
		name: commonLogic.appcommonhandle("名称",null),
		branch: commonLogic.appcommonhandle("branch",null),
		ibizshort: commonLogic.appcommonhandle("简称",null),
		order: commonLogic.appcommonhandle("排序值",null),
		grade: commonLogic.appcommonhandle("grade",null),
		type: commonLogic.appcommonhandle("类型（story）",null),
		owner: commonLogic.appcommonhandle("owner",null),
		isleaf: commonLogic.appcommonhandle("叶子模块",null),
		id: commonLogic.appcommonhandle("id",null),
		collector: commonLogic.appcommonhandle("collector",null),
		rootname: commonLogic.appcommonhandle("所属产品",null),
		parentname: commonLogic.appcommonhandle("上级模块",null),
		root: commonLogic.appcommonhandle("产品",null),
		parent: commonLogic.appcommonhandle("id",null),
	},
		views: {
			gridviewbranch: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("产品模块表格视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("产品模块表格视图",null),
			},
			quickcfgview: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("需求模块",null),
			},
			treeexpview: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("需求模块树导航视图",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("需求模块",null),
				title: commonLogic.appcommonhandle("产品模块编辑视图",null),
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("产品模块基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("id",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				rootname: commonLogic.appcommonhandle("所属产品",null), 
				branch: commonLogic.appcommonhandle("平台",null), 
				parentname: commonLogic.appcommonhandle("上级模块",null), 
				name: commonLogic.appcommonhandle("名称",null), 
				short: commonLogic.appcommonhandle("简称",null), 
				order: commonLogic.appcommonhandle("排序值",null), 
				root: commonLogic.appcommonhandle("产品",null), 
				id: commonLogic.appcommonhandle("id",null), 
				parent: commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		mainbranch_grid: {
			columns: {
				name: commonLogic.appcommonhandle("名称",null),
				branch: commonLogic.appcommonhandle("平台",null),
				short: commonLogic.appcommonhandle("简称",null),
				order: commonLogic.appcommonhandle("排序值",null),
				uagridcolumn1: commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		main_grid: {
			columns: {
				name: commonLogic.appcommonhandle("名称",null),
				branch: commonLogic.appcommonhandle("平台",null),
				short: commonLogic.appcommonhandle("简称",null),
				order: commonLogic.appcommonhandle("排序值",null),
				uagridcolumn1: commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		gridviewbranchtoolbar_toolbar: {
			deuiaction2: {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			deuiaction3: {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		gridviewtoolbar_toolbar: {
			deuiaction1_syncfromibiz: {
				caption: commonLogic.appcommonhandle("同步",null),
				tip: commonLogic.appcommonhandle("同步",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			deuiaction3: {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		treeexpviewtreeexpbar_toolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("修复",null),
				tip: commonLogic.appcommonhandle("修复",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
		editviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		exp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				all: commonLogic.appcommonhandle("所有模块",null),
				branch: commonLogic.appcommonhandle("平台",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			refreshparent: commonLogic.appcommonhandle("刷新",null),
			},
		},
		moduleexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				branch: commonLogic.appcommonhandle("平台",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
				all: commonLogic.appcommonhandle("所有模块",null),
			},
			uiactions: {
			refreshparent: commonLogic.appcommonhandle("刷新",null),
			refreshall: commonLogic.appcommonhandle("刷新",null),
			productmodule_openquickcfgview: commonLogic.appcommonhandle("编辑",null),
			},
		},
		bugexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
				all: commonLogic.appcommonhandle("全部",null),
			},
			uiactions: {
			},
		},
		caseexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				all: commonLogic.appcommonhandle("全部",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;