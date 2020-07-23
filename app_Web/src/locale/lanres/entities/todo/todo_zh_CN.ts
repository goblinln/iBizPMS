export default {
  fields: {
    id: "编号",
    account: "所有者",
    closeddate: "关闭时间",
    closedby: "由谁关闭",
    type: "类型",
    end: "结束",
    desc: "描述",
    finishedby: "由谁完成",
    begin: "开始",
    idvalue: "关联编号",
    assignedby: "由谁指派",
    finisheddate: "完成时间",
    cycle: "周期",
    assignedto: "指派给",
    status: "状态",
    name: "待办名称",
    assigneddate: "指派日期",
    pri: "优先级",
    date: "日期",
    ibizprivate: "私人事务",
    config: "config",
  },
	views: {
		assigntoview: {
			caption: "指派给",
      		title: "指派表单视图",
		},
		gridview: {
			caption: "待办事宜表",
      		title: "todo表格视图",
		},
		editview: {
			caption: "待办事宜表",
      		title: "todo编辑视图",
		},
	},
	assigntoform_form: {
		details: {
			group1: "指派给", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "编号", 
			srfmajortext: "待办名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			assignto: "指派给", 
			date: "日期", 
			future: "", 
			begin: "起止时间", 
			end: "", 
			lbldisabledate: "", 
			id: "编号", 
		},
		uiactions: {
		},
	},
	main_form: {
		details: {
			group1: "添加待办", 
			formpage1: "添加待办", 
			srforikey: "", 
			srfkey: "编号", 
			srfmajortext: "待办名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			date: "日期", 
			date_disable: "", 
			cycle_enable: "", 
			type: "类型", 
			pri: "优先级", 
			name: "待办名称", 
			status: "状态", 
			begin: "起止时间", 
			end: "", 
			formitem10: "", 
			private: "私人事务", 
			id: "编号", 
		},
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			id: "编号",
			date: "日期",
			type: "类型",
			pri: "优先级",
			name: "待办名称",
			begin: "开始",
			end: "结束",
			status: "状态",
			uagridcolumn1: "操作",
		},
		uiactions: {
			todo_assignto: "指派",
			todo_finish: "完成",
			todo_activate: "激活",
			todo_close: "关闭",
			todo_edit: "编辑",
			todo_delete: "删除",
		},
	},
	default_searchform: {
		details: {
			formpage1: "常规条件", 
		},
		uiactions: {
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
};