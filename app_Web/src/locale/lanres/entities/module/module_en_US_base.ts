import commonLogic from '@/locale/logic/common/common-logic';

export default {
  fields: {
    root: commonLogic.appcommonhandle("所属根",null),
    grade: commonLogic.appcommonhandle("级别",null),
    type: commonLogic.appcommonhandle("类型",null),
    name: commonLogic.appcommonhandle("模块名称",null),
    order: commonLogic.appcommonhandle("排序",null),
    owner: commonLogic.appcommonhandle("负责人",null),
    id: commonLogic.appcommonhandle("id",null),
    collector: commonLogic.appcommonhandle("收藏者",null),
    ibizshort: commonLogic.appcommonhandle("简称",null),
    path: commonLogic.appcommonhandle("路径",null),
    deleted: commonLogic.appcommonhandle("已删除",null),
    parentname: commonLogic.appcommonhandle("上级模块",null),
    branch: commonLogic.appcommonhandle("平台/分支",null),
    parent: commonLogic.appcommonhandle("上级模块",null),
    mdeptid: commonLogic.appcommonhandle("部门标识",null),
    orgid: commonLogic.appcommonhandle("组织标识",null),
    orderpk: commonLogic.appcommonhandle("数据选择排序",null),
  },
	views: {
		editview: {
			caption: commonLogic.appcommonhandle("模块",null),
      		title: commonLogic.appcommonhandle("模块",null),
		},
		treeexpview: {
			caption: commonLogic.appcommonhandle("所有产品",null),
      		title: commonLogic.appcommonhandle("所有产品",null),
		},
		gridview: {
			caption: commonLogic.appcommonhandle("模块",null),
      		title: commonLogic.appcommonhandle("模块",null),
		},
		linegridview: {
			caption: commonLogic.appcommonhandle("产品线",null),
      		title: commonLogic.appcommonhandle("产品线",null),
		},
		pickupgridview: {
			caption: commonLogic.appcommonhandle("模块",null),
      		title: commonLogic.appcommonhandle("模块选择",null),
		},
		pickupview: {
			caption: commonLogic.appcommonhandle("模块",null),
      		title: commonLogic.appcommonhandle("模块选择",null),
		},
	},
	main_form: {
		details: {
			group1: commonLogic.appcommonhandle("module基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("id",null), 
			srfmajortext: commonLogic.appcommonhandle("模块名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("模块名称",null), 
			short: commonLogic.appcommonhandle("简称",null), 
			parentname: commonLogic.appcommonhandle("上级模块",null), 
			parent: commonLogic.appcommonhandle("上级模块",null), 
			order: commonLogic.appcommonhandle("排序",null), 
			id: commonLogic.appcommonhandle("id",null), 
		},
		uiactions: {
		},
	},
	line_grid: {
		columns: {
			name: commonLogic.appcommonhandle("名称",null),
			short: commonLogic.appcommonhandle("简称",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			name: commonLogic.appcommonhandle("模块名称",null),
			parentname: commonLogic.appcommonhandle("上级模块",null),
			grade: commonLogic.appcommonhandle("级别",null),
			order: commonLogic.appcommonhandle("排序",null),
			owner: commonLogic.appcommonhandle("负责人",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	default_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
		},
		uiactions: {
		},
	},
	linegridviewtoolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("新建行",null),
			tip: commonLogic.appcommonhandle("新建行",null),
		},
		deuiaction3: {
			caption: commonLogic.appcommonhandle("保存行",null),
			tip: commonLogic.appcommonhandle("保存行",null),
		},
		deuiaction1: {
			caption: commonLogic.appcommonhandle("Remove",null),
			tip: commonLogic.appcommonhandle("Remove {0}",null),
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: commonLogic.appcommonhandle("Save And Close Window",null),
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("New",null),
			tip: commonLogic.appcommonhandle("New",null),
		},
		seperator1: {
			caption: commonLogic.appcommonhandle("",null),
			tip: commonLogic.appcommonhandle("",null),
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("Edit",null),
			tip: commonLogic.appcommonhandle("Edit {0}",null),
		},
	},
	productline_treeview: {
		nodata:commonLogic.appcommonhandle("",null),
		nodes: {
			root: commonLogic.appcommonhandle("默认根节点",null),
			all: commonLogic.appcommonhandle("所有产品",null),
		},
		uiactions: {
        module_seline: commonLogic.appcommonhandle("维护产品线",null),
        refreshall: commonLogic.appcommonhandle("刷新",null),
		},
	},
};