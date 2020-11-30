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
    reportto: "汇报给",
    mailto: "抄送给",
    comment: "其他事项",
    thismonthtask: "本月完成任务",
    nextmonthplanstask: "下月计划任务",
    files: "附件",
    issubmit: "是否提交",
    updatemanname: "更新人名称",
    reportstatus: "状态",
    createmanname: "建立人名称",
  },
	views: {
		mainmsgeditview: {
			caption: "主信息",
      		title: "月报编辑视图",
		},
		monthlymainmsgeditview: {
			caption: "月报实体编辑视图",
      		title: "月报编辑视图",
		},
		newmonthlyeditview: {
			caption: "月报",
      		title: "月报编辑视图",
		},
		mainmonthlygridview: {
			caption: "月报实体表格视图",
      		title: "月报表格视图",
		},
		monthlymainmsgdashboardview: {
			caption: "月报实体数据看板视图",
      		title: "月报数据看板视图",
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
			comment: "其他事项", 
			files: "附件", 
			reportto: "汇报给", 
			mailto: "抄送给", 
			ibz_monthlyid: "月报标识", 
			account: "用户", 
			issubmit: "是否提交", 
		},
		uiactions: {
		},
	},
	mainmsg_form: {
		details: {
			group1: "月报基本信息", 
			formpage1: "基本信息", 
			srfupdatedate: "更新时间", 
			srforikey: "", 
			srfkey: "月报标识", 
			srfmajortext: "月报名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			ibz_monthlyname: "月报名称", 
			ibz_monthlyid: "月报标识", 
		},
		uiactions: {
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			ibz_monthlyname: "名称",
			account: "用户",
			date: "日期",
			reportto: "汇报给",
			uagridcolumn1: "操作",
		},
		uiactions: {
			ibzmonthly_submit: "提交",
		},
	},
	default_searchform: {
		details: {
			formpage1: "常规条件", 
		},
		uiactions: {
		},
	},
	newmonthlyeditviewtoolbar_toolbar: {
		deuiaction3_submit: {
			caption: "提交",
			tip: "提交",
		},
		deuiaction1: {
			caption: "保存并关闭",
			tip: "保存并关闭",
		},
	},
	mainmonthlygridviewtoolbar_toolbar: {
		deuiaction1_create: {
			caption: "新建",
			tip: "新建",
		},
	},
	monthlymainmsgeditviewtoolbar_toolbar: {
	},
};