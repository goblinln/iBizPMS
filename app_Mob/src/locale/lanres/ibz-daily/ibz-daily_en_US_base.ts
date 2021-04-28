import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    submittime:  commonLogic.appcommonhandle("提交时间",null),
    todaytask:  commonLogic.appcommonhandle("完成任务",null),
    worktoday:  commonLogic.appcommonhandle("今日工作",null),
    tomorrowplanstask:  commonLogic.appcommonhandle("明日计划任务",null),
    reportto:  commonLogic.appcommonhandle("汇报给",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    issubmit:  commonLogic.appcommonhandle("是否提交",null),
    date:  commonLogic.appcommonhandle("日期",null),
    files:  commonLogic.appcommonhandle("附件",null),
    updateManName:  commonLogic.appcommonhandle("更新人名称",null),
    mailtopk:  commonLogic.appcommonhandle("抄送给（选择）",null),
    reportstatus:  commonLogic.appcommonhandle("状态",null),
    comment:  commonLogic.appcommonhandle("其他事项",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    planstomorrow:  commonLogic.appcommonhandle("明日计划",null),
    mailto:  commonLogic.appcommonhandle("抄送给",null),
    ibzDailyId:  commonLogic.appcommonhandle("日报标识",null),
    account:  commonLogic.appcommonhandle("用户",null),
    reporttopk:  commonLogic.appcommonhandle("汇报给（选择）",null),
    createManName:  commonLogic.appcommonhandle("建立人名称",null),
    ibzDailyName:  commonLogic.appcommonhandle("日报名称",null),
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
	dailymobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	dailymobmdviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("New",null),
			tip: 'tbitem1',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_mobedit',
		},
		deuiaction1_submitmob: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction1_submitmob',
		},
	},
	dailyinfomobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_mobedit',
		},
		deuiaction1_submitmob: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction1_submitmob',
		},
	},
	dailycompletetaskmobmdviewrighttoolbar_toolbar: {
	},
	dailyplanstomorrowtaskmobmdviewrighttoolbar_toolbar: {
	},
	dailymobtabexpviewrighttoolbar_toolbar: {
	},
	dailyreportsubmitmobmdviewrighttoolbar_toolbar: {
	},
	dailycreatemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	reportreceivedmobmdviewrighttoolbar_toolbar: {
	},
	mydailymobtabexpviewrighttoolbar_toolbar: {
	},
	mymobmdviewrighttoolbar_toolbar: {
		deuiaction1_mobcreate: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: 'deuiaction1_mobcreate',
		},
	},
};