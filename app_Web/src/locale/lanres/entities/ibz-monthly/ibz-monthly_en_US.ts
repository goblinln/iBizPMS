export default {
  fields: {
    ibzmonthlyid: "月报标识",
    createman: "建立人",
    updateman: "更新人",
    createdate: "建立时间",
    ibzmonthlyname: "月报名称",
    updatedate: "更新时间",
    date: "日期",
    account: "用户",
    workthismonth: "本月工作",
    plansnextmonth: "下月计划",
    reportto: "汇报",
    mailto: "抄送给",
    comment: "其他事项",
    thismonthtask: "本月完成任务",
    nextmonthplanstask: "下月计划任务",
    files: "附件",
    issubmit: "是否提交",
  },
	views: {
		newmonthlyeditview: {
			caption: "新建月报",
      		title: "月报编辑视图",
		},
		mainmonthlygridview: {
			caption: "月报实体表格视图",
      		title: "月报表格视图",
		},
	},
	new_form: {
		details: {
			grouppanel1: "分组面板", 
			formpage1: "基本信息", 
			srfupdatedate: "更新时间", 
			srforikey: "", 
			srfkey: "月报标识", 
			srfmajortext: "月报名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			thismonthtask: "本月完成任务", 
			workthismonth: "本月工作", 
			nextmonthplanstask: "下月计划任务", 
			plansnextmonth: "下月计划", 
			files: "附件", 
			reportto: "汇报", 
			mailto: "抄送给", 
			ibz_monthlyid: "月报标识", 
		},
		uiactions: {
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			ibz_monthlyname: "月报名称",
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
	mainmonthlygridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "New",
			tip: "New",
		},
	},
	newmonthlyeditviewtoolbar_toolbar: {
	},
};