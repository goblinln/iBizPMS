export default {
  fields: {
    join: "加盟日",
    account: "用户",
    hours: "可用工时/天",
    left: "预计剩余",
    days: "可用工日",
    id: "编号",
    consumed: "总计消耗",
    order: "排序",
    estimate: "最初预计",
    limited: "受限用户",
    role: "角色",
    type: "团队类型",
    total: "总计可用",
    root: "项目编号",
    username: "用户",
  },
	views: {
		maingridview: {
			caption: "项目团队",
      		title: "项目团队表格视图",
		},
		maingridview_editrow: {
			caption: "项目团队",
      		title: "项目团队表格视图",
		},
	},
	main_editrow_grid: {
		nodata: "",
		columns: {
			account: "用户",
			role: "角色",
			days: "可用工日",
			hours: "可用工时/天",
			limited: "受限用户",
			uagridcolumn1: "操作",
		},
		uiactions: {
        newrow: "新建行",
        remove: "Remove",
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			account: "用户",
			role: "角色",
			join: "加盟日",
			days: "可用工日",
			hours: "可用工时/天",
			total: "总计可用",
			limited: "受限用户",
			uagridcolumn1: "操作",
		},
    exportColumns: {
			account: "用户",
			role: "角色",
			join: "加盟日",
			days: "可用工日",
			hours: "可用工时/天",
			total: "总计可用",
			limited: "受限用户",
    },
		uiactions: {
        projectteam_removemember: "移除成员",
		},
	},
	default_searchform: {
		details: {
			formpage1: "常规条件", 
			dept: "选择部门", 
			teams: "复制团队", 
		},
		uiactions: {
		},
	},
	main2_searchform: {
		details: {
			formpage1: "常规条件", 
			n_account_eq: "用户", 
		},
		uiactions: {
		},
	},
	maingridview_editrowtoolbar_toolbar: {
		deuiaction2: {
			caption: "新建行",
			tip: "新建行",
		},
		deuiaction3: {
			caption: "保存行",
			tip: "保存行",
		},
	},
	maingridviewtoolbar_toolbar: {
		deuiaction3_managermember: {
			caption: "团队管理",
			tip: "团队管理",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
		deuiaction1: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
		deuiaction4: {
			caption: "Filter",
			tip: "Filter",
		},
	},
};