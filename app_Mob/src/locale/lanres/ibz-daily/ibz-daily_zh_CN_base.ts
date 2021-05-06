import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    updatedate: "更新时间",
    submittime: "提交时间",
    todaytask: "完成任务",
    worktoday: "今日工作",
    tomorrowplanstask: "明日计划任务",
    reportto: "汇报给",
    createman: "建立人",
    createdate: "建立时间",
    issubmit: "是否提交",
    date: "日期",
    files: "附件",
    updatemanname: "更新人名称",
    mailtopk: "抄送给（选择）",
    reportstatus: "状态",
    comment: "其他事项",
    updateman: "更新人",
    planstomorrow: "明日计划",
    mailto: "抄送给",
    ibzdailyid: "日报标识",
    account: "用户",
    reporttopk: "汇报给（选择）",
    createmanname: "建立人名称",
    ibzdailyname: "日报名称",
  },
	views: {
		dailymobmdview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		dailyreportsubmitmobmdview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		dailycreatemobeditview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		dailymobeditview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		dailycompletetaskmobmdview: {
			caption: commonLogic.appcommonhandle("完成任务",null),
		},
		reportreceivedmobmdview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		dailymobtabexpview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		mydailymobtabexpview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		mymobmdview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
		myremobeditview: {
			caption: commonLogic.appcommonhandle("我收到的",null),
		},
		dailyplanstomorrowtaskmobmdview: {
			caption: commonLogic.appcommonhandle("完成任务",null),
		},
		dailyinfomobeditview: {
			caption: commonLogic.appcommonhandle("日报",null),
		},
	},
	dailyinfomob_form: {
		details: {
			group1: commonLogic.appcommonhandle("日报基本信息",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("附件",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("操作记录",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("日报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("日报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			account: commonLogic.appcommonhandle("",null), 
			ibzdailyname: commonLogic.appcommonhandle("日报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			worktoday: commonLogic.appcommonhandle("今日工作",null), 
			planstomorrow: commonLogic.appcommonhandle("明日计划",null), 
			comment: commonLogic.appcommonhandle("其他事项",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			ibz_dailyid: commonLogic.appcommonhandle("日报标识",null), 
			todaytask: commonLogic.appcommonhandle("完成任务",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	dailyinfodingding_form: {
		details: {
			druipart3: commonLogic.appcommonhandle("",null), 
			grouppanel3: commonLogic.appcommonhandle("完成任务",null), 
			druipart4: commonLogic.appcommonhandle("",null), 
			grouppanel4: commonLogic.appcommonhandle("计划参与",null), 
			group1: commonLogic.appcommonhandle("日报基本信息",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("附件",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("操作记录",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("日报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("日报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			account: commonLogic.appcommonhandle("",null), 
			ibzdailyname: commonLogic.appcommonhandle("日报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			worktoday: commonLogic.appcommonhandle("今日工作",null), 
			planstomorrow: commonLogic.appcommonhandle("明日计划",null), 
			comment: commonLogic.appcommonhandle("其他事项",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			ibz_dailyid: commonLogic.appcommonhandle("日报标识",null), 
			todaytask: commonLogic.appcommonhandle("完成任务",null), 
			tomorrowplanstask: commonLogic.appcommonhandle("明日计划任务",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	mobdailyedit_form: {
		details: {
			group1: commonLogic.appcommonhandle("日报基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("日报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("日报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			ibz_dailyname: commonLogic.appcommonhandle("日报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			todaytask: commonLogic.appcommonhandle("完成任务",null), 
			worktoday: commonLogic.appcommonhandle("今日工作",null), 
			tomorrowplanstask: commonLogic.appcommonhandle("明日计划任务",null), 
			planstomorrow: commonLogic.appcommonhandle("明日计划",null), 
			comment: commonLogic.appcommonhandle("其他事项",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			reporttopk: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			ibz_dailyid: commonLogic.appcommonhandle("日报标识",null), 
			account: commonLogic.appcommonhandle("用户",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	dailycompletetaskmobmdviewrighttoolbar_toolbar: {
	},
	dailymobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction1_submitmob: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: '提交',
		},
	},
	dailyplanstomorrowtaskmobmdviewrighttoolbar_toolbar: {
	},
	mydailymobtabexpviewrighttoolbar_toolbar: {
	},
	dailyinfomobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction1_submitmob: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: '提交',
		},
	},
	dailymobtabexpviewrighttoolbar_toolbar: {
	},
	reportreceivedmobmdviewrighttoolbar_toolbar: {
	},
	dailycreatemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mymobmdviewrighttoolbar_toolbar: {
		deuiaction1_mobcreate: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: '新建',
		},
	},
	dailyreportsubmitmobmdviewrighttoolbar_toolbar: {
	},
	dailymobmdviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: '新建',
		},
	},
};