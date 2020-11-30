export default {
  fields: {
    createman: "建立人",
    ibzdailyid: "日报标识",
    ibzdailyname: "日报名称",
    updatedate: "更新时间",
    createdate: "建立时间",
    updateman: "更新人",
    date: "日期",
    account: "用户",
    worktoday: "今日工作",
    mailto: "抄送给",
    files: "附件",
    todaytask: "完成任务",
    issubmit: "是否提交",
    planstomorrow: "明日计划",
    tomorrowplanstask: "明日计划任务",
    reportto: "汇报给",
    comment: "其他事项",
  },
	views: {
		dailyeditview: {
			caption: "日报",
      		title: "日报编辑视图",
		},
		dailygridview: {
			caption: "日报",
      		title: "日报表格视图",
		},
	},
	dailyedit_form: {
		details: {
			group1: "日报基本信息", 
			button1: "完成任务", 
			druipart1: "", 
			formpage1: "基本信息", 
			srfupdatedate: "更新时间", 
			srforikey: "", 
			srfkey: "日报标识", 
			srfmajortext: "日报名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			ibz_dailyname: "日报名称", 
			date: "日期", 
			worktoday: "今日工作", 
			todaytask: "完成任务", 
			planstomorrow: "明日计划", 
			tomorrowplanstask: "明日计划任务", 
			comment: "其他事项", 
			files: "附件", 
			mailto: "抄送给", 
			reportto: "汇报给", 
			ibz_dailyid: "日报标识", 
			account: "用户", 
			issubmit: "是否提交", 
		},
		uiactions: {
        ibzdaily_linkcompletetask: "完成任务",
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			ibz_dailyname: "日报名称",
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
	dailygridviewtoolbar_toolbar: {
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
		seperator3: {
			caption: "",
			tip: "",
		},
		deuiaction4: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
		seperator4: {
			caption: "",
			tip: "",
		},
		deuiaction5: {
			caption: "Filter",
			tip: "Filter",
		},
	},
};