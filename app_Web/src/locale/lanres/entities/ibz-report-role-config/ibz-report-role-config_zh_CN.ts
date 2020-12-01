export default {
  fields: {
    ibzreportroleconfigname: "汇报角色配置名称",
    ibzreportroleconfigid: "汇报角色配置标识",
    createdate: "建立时间",
    createman: "建立人",
    updateman: "更新人",
    updatedate: "更新时间",
    report_role: "角色",
  },
	views: {
		gridview: {
			caption: "汇报角色配置",
      		title: "汇报角色配置表格视图",
		},
		editview: {
			caption: "汇报角色配置",
      		title: "汇报角色配置编辑视图",
		},
	},
	main_form: {
		details: {
			group1: "汇报角色配置基本信息", 
			formpage1: "基本信息", 
			srfupdatedate: "更新时间", 
			srforikey: "", 
			srfkey: "汇报角色配置标识", 
			srfmajortext: "汇报角色配置名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			ibz_report_role_configname: "汇报角色配置名称", 
			report_role: "角色", 
			ibz_report_role_configid: "汇报角色配置标识", 
		},
		uiactions: {
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			ibz_report_role_configname: "汇报角色配置名称",
			report_role: "角色",
			updateman: "更新人",
			updatedate: "更新时间",
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
	gridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "新建",
			tip: "新建",
		},
		deuiaction4: {
			caption: "删除",
			tip: "删除",
		},
		seperator1: {
			caption: "",
			tip: "",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
		seperator3: {
			caption: "",
			tip: "",
		},
		deuiaction5: {
			caption: "导出",
			tip: "导出",
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "保存并关闭",
			tip: "保存并关闭",
		},
	},
};