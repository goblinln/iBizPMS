export default {
  fields: {
    path: "path",
    deleted: "逻辑删除标志",
    name: "名称",
    branch: "branch",
    ibizshort: "简称",
    order: "排序值",
    grade: "grade",
    type: "类型（story）",
    owner: "owner",
    isleaf: "叶子模块",
    id: "id",
    collector: "collector",
    root: "产品",
    parent: "id",
    rootname: "所属产品",
    parentname: "上级模块",
  },
	views: {
		gridviewbranch: {
			caption: "需求模块",
      		title: "产品模块表格视图",
		},
		gridview: {
			caption: "需求模块",
      		title: "产品模块表格视图",
		},
		treeexpview: {
			caption: "需求模块",
      		title: "需求模块树导航视图",
		},
		editview: {
			caption: "需求模块",
      		title: "产品模块编辑视图",
		},
		quickcfgview: {
			caption: "需求模块",
      		title: "需求模块",
		},
	},
	main_form: {
		details: {
			group1: "产品模块基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "id", 
			srfmajortext: "名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			rootname: "所属产品", 
			branch: "平台", 
			parentname: "上级模块", 
			name: "名称", 
			short: "简称", 
			order: "排序值", 
			root: "产品", 
			id: "id", 
			parent: "id", 
		},
		uiactions: {
		},
	},
	mainbranch_grid: {
		nodata: "",
		columns: {
			name: "名称",
			branch: "平台",
			short: "简称",
			order: "排序值",
			uagridcolumn1: "操作",
		},
		uiactions: {
			remove: "删除",
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			name: "名称",
			branch: "平台",
			short: "简称",
			order: "排序值",
			uagridcolumn1: "操作",
		},
		uiactions: {
			remove: "删除",
		},
	},
	gridviewbranchtoolbar_toolbar: {
		deuiaction2: {
			caption: "新建行",
			tip: "新建行",
		},
		deuiaction3: {
			caption: "保存行",
			tip: "保存行",
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction1_syncfromibiz: {
			caption: "同步",
			tip: "同步",
		},
		deuiaction2: {
			caption: "新建行",
			tip: "新建行",
		},
		deuiaction3: {
			caption: "保存行",
			tip: "保存行",
		},
	},
	treeexpviewtreeexpbar_toolbar_toolbar: {
		deuiaction1: {
			caption: "修复",
			tip: "修复",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "保存并关闭",
			tip: "保存并关闭",
		},
	},
	exp_treeview: {
		nodes: {
			all: "所有模块",
			branch: "平台",
			root: "默认根节点",
		},
		uiactions: {
			refreshparent: "刷新",
		},
	},
	moduleexp_treeview: {
		nodes: {
			root: "默认根节点",
			branch: "平台",
			all: "所有模块",
		},
		uiactions: {
			refreshparent: "刷新",
			productmodule_openquickcfgview: "编辑",
			refreshall: "刷新",
		},
	},
	bugexp_treeview: {
		nodes: {
			root: "默认根节点",
			all: "全部",
		},
		uiactions: {
		},
	},
	caseexp_treeview: {
		nodes: {
			all: "全部",
			root: "默认根节点",
		},
		uiactions: {
		},
	},
};