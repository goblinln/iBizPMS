import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    issubmit:  commonLogic.appcommonhandle("是否提交",null),
    plannextweek:  commonLogic.appcommonhandle("下周计划",null),
    ibzWeeklyName:  commonLogic.appcommonhandle("周报名称",null),
    mailto:  commonLogic.appcommonhandle("抄送给",null),
    reporttopk:  commonLogic.appcommonhandle("汇报给(选择)",null),
    nextweektask:  commonLogic.appcommonhandle("下周计划任务",null),
    ibzWeeklyId:  commonLogic.appcommonhandle("周报标识",null),
    submittime:  commonLogic.appcommonhandle("提交时间",null),
    mailtopk:  commonLogic.appcommonhandle("抄送给(选择)",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    reportstatus:  commonLogic.appcommonhandle("状态",null),
    files:  commonLogic.appcommonhandle("附件",null),
    workthisweek:  commonLogic.appcommonhandle("本周工作",null),
    reportto:  commonLogic.appcommonhandle("汇报给",null),
    updateManName:  commonLogic.appcommonhandle("更新人名称",null),
    account:  commonLogic.appcommonhandle("用户",null),
    thisweektask:  commonLogic.appcommonhandle("本周完成任务",null),
    comment:  commonLogic.appcommonhandle("其他事项",null),
    date:  commonLogic.appcommonhandle("日期",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    createManName:  commonLogic.appcommonhandle("建立人名称",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
  },
	views: {
		mobeditview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobtabexpview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewmian: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewmainmytijiao: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewmainreceived: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobtabexpviewmytijiao: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobtabexpviewmyreceived: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		mobeditviewcreate: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobeditview: {
			caption: commonLogic.appcommonhandle("周报",null),
		},
		usr2mobmdview: {
			caption: commonLogic.appcommonhandle("周报",null),
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
	mobeditviewmianrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobsubmit: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction1_mobsubmit',
		},
		deuiaction1_mobedit1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_mobedit1',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
	},
	usr2mobeditviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction2',
		},
	},
	mobeditviewmainmytijiaorighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction2',
		},
	},
	mobeditviewmainreceivedrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction2',
		},
	},
	mobeditviewcreaterighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	usr2mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: 'deuiaction1',
		},
	},
};