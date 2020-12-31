import commonLogic from '@/locale/logic/common/common-logic';
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
    updatemanname: "更新人名称",
    createmanname: "建立人名称",
    reportstatus: "状态",
    submittime: "提交时间",
    reporttopk: "汇报给(选择)",
    mailtopk: "抄送给(选择)",
  },
	views: {
		mobeditviewcreate: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobtabexpview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewmainmytijiao: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobtabexpviewmytijiao: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobtabexpviewmyreceived: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobmdview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobeditview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewmian: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewmainreceived: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
	},
	mobweekedit_form: {
		details: {
			group1: commonLogic.appcommonhandle("周报基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("周报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("周报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			ibz_weeklyname: commonLogic.appcommonhandle("周报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			thisweektask: commonLogic.appcommonhandle("本周完成任务",null), 
			workthisweek: commonLogic.appcommonhandle("本周工作",null), 
			nextweektask: commonLogic.appcommonhandle("下周计划任务",null), 
			plannextweek: commonLogic.appcommonhandle("下周计划",null), 
			comment: commonLogic.appcommonhandle("其他事项",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			reporttopk: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			ibz_weeklyid: commonLogic.appcommonhandle("周报标识",null), 
			account: commonLogic.appcommonhandle("用户",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	mobwaitread_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("完成的任务",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("下周计划参与",null), 
			druipart3: commonLogic.appcommonhandle("",null), 
			grouppanel3: commonLogic.appcommonhandle("附件",null), 
			druipart4: commonLogic.appcommonhandle("",null), 
			grouppanel4: commonLogic.appcommonhandle("操作历史",null), 
			group1: commonLogic.appcommonhandle("周报基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("周报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("周报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			account: commonLogic.appcommonhandle("",null), 
			ibz_weeklyname: commonLogic.appcommonhandle("周报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			workthisweek: commonLogic.appcommonhandle("本周工作",null), 
			plannextweek: commonLogic.appcommonhandle("下周计划",null), 
			comment: commonLogic.appcommonhandle("其他事项",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			thisweektask: commonLogic.appcommonhandle("本周完成任务",null), 
			nextweektask: commonLogic.appcommonhandle("下周计划任务",null), 
			ibz_weeklyid: commonLogic.appcommonhandle("周报标识",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	mobinfomain2_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("附件",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel3: commonLogic.appcommonhandle("操作历史",null), 
			group1: commonLogic.appcommonhandle("周报基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("周报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("周报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			account: commonLogic.appcommonhandle("",null), 
			ibz_weeklyname: commonLogic.appcommonhandle("周报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			workthisweek: commonLogic.appcommonhandle("本周工作",null), 
			plannextweek: commonLogic.appcommonhandle("下周计划",null), 
			comment: commonLogic.appcommonhandle("其他事项",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			thisweektask: commonLogic.appcommonhandle("本周完成任务",null), 
			nextweektask: commonLogic.appcommonhandle("下周计划任务",null), 
			ibz_weeklyid: commonLogic.appcommonhandle("周报标识",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	mobeditviewcreaterighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
	},
	mobeditviewmianrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobedit1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction1_mobsubmit: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: '提交',
		},
	},
	mobeditviewmainmytijiaorighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: '提交',
		},
	},
	usr2mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: '新建',
		},
	},
	usr2mobeditviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: '提交',
		},
	},
	mobeditviewmainreceivedrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: '提交',
		},
	},
};