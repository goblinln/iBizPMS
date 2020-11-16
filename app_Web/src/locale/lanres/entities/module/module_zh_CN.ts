export default {
  fields: {
    root: "所属根",
    grade: "级别",
    type: "类型",
    name: "模块名称",
    order: "排序",
    owner: "负责人",
    id: "id",
    collector: "收藏者",
    ibizshort: "简称",
    path: "路径",
    deleted: "已删除",
    parentname: "上级模块",
    branch: "平台/分支",
    parent: "上级模块",
    mdeptid: "部门标识",
    orgid: "组织标识",
  },
	views: {
		editview: {
			caption: "模块",
      		title: "模块",
		},
		treeexpview: {
			caption: "所有产品",
      		title: "所有产品",
		},
		gridview: {
			caption: "模块",
      		title: "模块",
		},
		linegridview: {
			caption: "产品线",
      		title: "产品线",
		},
		pickupgridview: {
			caption: "模块",
      		title: "模块选择",
		},
		pickupview: {
			caption: "模块",
      		title: "模块选择",
		},
	},
	main_form: {
		details: {
			group1: "module基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "id", 
			srfmajortext: "模块名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			name: "模块名称", 
			short: "简称", 
			parentname: "上级模块", 
			parent: "上级模块", 
			order: "排序", 
			id: "id", 
		},
		uiactions: {
		},
	},
	line_grid: {
		nodata: "",
		columns: {
			name: "名称",
			short: "简称",
		},
		uiactions: {
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			name: "模块名称",
			parentname: "上级模块",
			grade: "级别",
			order: "排序",
			owner: "负责人",
		},
		uiactions: {
		},
	},
	default_searchform: {
		details: {
			formpage1: "常规条件", 
		},
		uiactions: {
		},
	},
	linegridviewtoolbar_toolbar: {
		deuiaction2: {
			caption: "新建行",
			tip: "新建行",
		},
		deuiaction3: {
			caption: "保存行",
			tip: "保存行",
		},
		deuiaction1: {
			caption: "删除",
			tip: "删除",
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "保存并关闭",
			tip: "保存并关闭",
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "新建",
			tip: "新建",
		},
		seperator1: {
			caption: "",
			tip: "",
		},
		deuiaction2: {
			caption: "编辑",
			tip: "编辑",
		},
	},
	productline_treeview: {
		nodes: {
			root: "默认根节点",
			all: "所有产品",
		},
		uiactions: {
			module_seline: "维护产品线",
			refreshall: "刷新",
		},
	},
};