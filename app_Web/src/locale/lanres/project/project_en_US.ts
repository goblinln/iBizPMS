
export default {
  fields: {
    openedversion: '当前系统版本',
    begin: '开始时间',
    acl: '访问控制',
    deleted: '已删除',
    desc: '项目描述',
    pm: '项目负责人',
    id: '项目编号',
    name: '项目名称',
    substatus: '子状态',
    order: '项目排序',
    rd: '发布负责人',
    whitelist: '分组白名单',
    pri: '优先级',
    end: '结束日期',
    canceleddate: '取消日期',
    code: '项目代号',
    catid: 'catID',
    statge: 'statge',
    canceledby: '由谁取消',
    iscat: 'isCat',
    openeddate: '创建日期',
    closedby: '由谁关闭',
    type: '项目类型',
    po: '产品负责人',
    status: '项目状态',
    days: '可用工作日',
    team: '团队名称',
    closeddate: '关闭日期',
    openedby: '由谁创建',
    qd: '测试负责人',
    parentname: 'parent',
    parent: '父项目',
  },
	views: {
		tasktreeexpview: {
			caption: "项目",
      title: '项目',
		},
		burndownchartview: {
			caption: "项目",
      title: '项目',
		},
		listexpview: {
			caption: "项目",
      title: '项目',
		},
		curproductgridview: {
			caption: "项目",
      title: '项目',
		},
		leftsidebarlistview: {
			caption: "项目",
      title: '项目',
		},
		gridview: {
			caption: "项目",
      title: '项目',
		},
		maindashboardview: {
			caption: "项目",
      title: '项目',
		},
		gridview9_unclosed: {
			caption: "未关闭的项目",
      title: '未关闭的项目',
		},
		dashboardinfoview: {
			caption: "项目",
      title: '项目',
		},
		editview: {
			caption: "项目",
      title: '项目',
		},
		maintabexpview: {
			caption: "项目",
      title: '项目',
		},
	},
	dashboardinfo_form: {
		details: {
			group1: "project基本信息", 
			druipart1: "", 
			grouppanel6: "关联产品", 
			druipart2: "", 
			grouppanel7: "关联计划", 
			grouppanel2: "分组面板", 
			grouppanel3: "分组面板", 
			grouppanel1: "工时统计", 
			grouppanel4: "基本信息", 
			grouppanel5: "访问控制", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "项目编号", 
			srfmajortext: "项目名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			id: "项目编号", 
			code: "项目代号", 
			name: "项目名称", 
			desc: "项目描述", 
			type: "项目类型", 
			status: "项目状态", 
			begin: "开始时间", 
			end: "结束日期", 
			days: "可用工作日", 
			formitem: "预计", 
			formitem1: "消耗", 
			formitem2: "剩余", 
			formitem3: "需求", 
			formitem4: "任务", 
			formitem5: "Bug", 
			acl: "访问控制", 
		},
		uiactions: {
		},
	},
	main_form: {
		details: {
			grouppanel1: "分组面板", 
			group1: "project基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "项目编号", 
			srfmajortext: "项目名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			name: "项目名称", 
			code: "项目代号", 
			end: "结束日期", 
			begin: "开始时间", 
			formitemex1: "项目周期", 
			days: "可用工作日", 
			team: "团队名称", 
			type: "项目类型", 
			formitem: "关联产品", 
			formitem1: "关联计划", 
			desc: "项目描述", 
			acl: "访问控制", 
			id: "项目编号", 
		},
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			id: "ID",
			name: "项目名称",
			code: "项目代号",
			status: "项目状态",
			end: "结束日期",
		},
		uiactions: {
		},
	},
	main2_grid: {
		columns: {
			id: "ID",
			name: "项目名称",
			code: "项目代号",
			status: "项目状态",
			end: "结束日期",
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
	curproductgridviewtoolbar_toolbar: {
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
	editviewtoolbar_toolbar: {
		deuiaction2: {
			caption: "Save",
			tip: "Save",
		},
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction4: {
			caption: "Remove",
			tip: "Remove {0}",
		},
		seperator1: {
			caption: "",
			tip: "",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
	},
	leftsidebarlistviewtoolbar_toolbar: {
		deuiaction3_manager: {
			caption: "管理",
			tip: "管理",
		},
		seperator2: {
			caption: "",
			tip: "",
		},
		deuiaction1: {
			caption: "New",
			tip: "New",
		},
		seperator1: {
			caption: "",
			tip: "",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
	},
};