
export default {
  fields: {
    type: '用例步骤类型',
    id: '编号',
    desc: '步骤',
    expect: '预期',
    version: '用例版本',
    ibizcase: '用例',
    parent: '分组用例步骤的组编号',
  },
	views: {
		gridview: {
			caption: "用例步骤",
      title: '用例步骤',
		},
		maingridview9: {
			caption: "用例步骤",
      title: '用例步骤',
		},
	},
	main_grid: {
		columns: {
			id: "编号",
			desc: "步骤",
			type: "用例步骤类型",
			expect: "预期",
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
	maingridview9toolbar_toolbar: {
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
	},
	gridviewtoolbar_toolbar: {
		tbitem3: {
			caption: "New",
			tip: "New",
		},
		tbitem4: {
			caption: "Edit",
			tip: "Edit {0}",
		},
		tbitem6: {
			caption: "Copy",
			tip: "Copy {0}",
		},
		tbitem7: {
			caption: "-",
			tip: "",
		},
		tbitem8: {
			caption: "Remove",
			tip: "Remove {0}",
		},
		tbitem9: {
			caption: "-",
			tip: "",
		},
		tbitem13: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
		tbitem10: {
			caption: "-",
			tip: "",
		},
		tbitem16: {
			caption: "其它",
			tip: "其它",
		},
		tbitem21: {
			caption: "Export Data Model",
			tip: "导出数据模型",
		},
		tbitem23: {
			caption: "数据导入",
			tip: "数据导入",
		},
		tbitem17: {
			caption: "-",
			tip: "",
		},
		tbitem19: {
			caption: "Filter",
			tip: "Filter",
		},
		tbitem18: {
			caption: "Help",
			tip: "Help",
		},
	},
};