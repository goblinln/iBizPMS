export default {
  fields: {
    ibzweeklyname: "周报名称",
    ibzweeklyid: "周报标识",
    createman: "建立人",
    createdate: "建立时间",
    updateman: "更新人",
    updatedate: "更新时间",
    account: "用户",
    mailto: "抄送给",
    files: "附件",
    issubmit: "是否提交",
    reportto: "汇报给",
    comment: "其他事项",
    date: "日期",
    workthisweek: "本周工作",
    plannextweek: "下周计划",
    thisweektask: "本周完成任务",
    nextweektask: "下周计划任务",
  },
	views: {
		editview: {
			caption: "周报",
      		title: "实体2编辑视图",
		},
		usr2gridview: {
			caption: "周报",
      		title: "周报表格视图",
		},
		gridview: {
			caption: "周报",
      		title: "实体2表格视图",
		},
	},
	main_form: {
		details: {
			group1: "实体2基本信息", 
			formpage1: "基本信息", 
			group2: "操作信息", 
			formpage2: "其它", 
			srfupdatedate: "更新时间", 
			srforikey: "", 
			srfkey: "周报标识", 
			srfmajortext: "周报名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			ibz_weeklyname: "周报名称", 
			createman: "建立人", 
			createdate: "建立时间", 
			updateman: "更新人", 
			updatedate: "更新时间", 
			ibz_weeklyid: "周报标识", 
		},
		uiactions: {
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			ibz_weeklyname: "周报名称",
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
	usr2gridviewtoolbar_toolbar: {
	},
	editviewtoolbar_toolbar: {
		tbitem3: {
			caption: "保存",
			tip: "保存",
		},
		tbitem4: {
			caption: "保存并新建",
			tip: "保存并新建",
		},
		tbitem5: {
			caption: "保存并关闭",
			tip: "保存并关闭",
		},
		tbitem7: {
			caption: "删除",
			tip: "删除",
		},
	},
	gridviewtoolbar_toolbar: {
		tbitem3: {
			caption: "新建",
			tip: "新建",
		},
		tbitem4: {
			caption: "编辑",
			tip: "编辑",
		},
		tbitem6: {
			caption: "拷贝",
			tip: "拷贝",
		},
		tbitem7: {
			caption: "-",
			tip: "",
		},
		tbitem8: {
			caption: "删除",
			tip: "删除",
		},
		tbitem9: {
			caption: "-",
			tip: "",
		},
		tbitem13: {
			caption: "导出",
			tip: "导出",
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
			caption: "导出数据模型",
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
			caption: "过滤",
			tip: "过滤",
		},
		tbitem18: {
			caption: "帮助",
			tip: "帮助",
		},
	},
};