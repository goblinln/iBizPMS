import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    files:  commonLogic.appcommonhandle("附件",null),
    account:  commonLogic.appcommonhandle("用户",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    mailto:  commonLogic.appcommonhandle("抄送给",null),
    ibzReportlyId:  commonLogic.appcommonhandle("汇报标识",null),
    ibzReportlyName:  commonLogic.appcommonhandle("汇报名称",null),
    reportstatus:  commonLogic.appcommonhandle("状态",null),
    submittime:  commonLogic.appcommonhandle("提交时间",null),
    reporttopk:  commonLogic.appcommonhandle("汇报给（选择）",null),
    reportto:  commonLogic.appcommonhandle("汇报给",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    issubmit:  commonLogic.appcommonhandle("是否提交",null),
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    content:  commonLogic.appcommonhandle("工作内容",null),
    date:  commonLogic.appcommonhandle("汇报日期",null),
    mailtopk:  commonLogic.appcommonhandle("抄送给（选择）",null),
  },
	views: {
		maininfomobeditview: {
			caption: commonLogic.appcommonhandle("汇报",null),
		},
		createmobeditview: {
			caption: commonLogic.appcommonhandle("汇报",null),
		},
		reportlymobmdview: {
			caption: commonLogic.appcommonhandle("汇报",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("汇报",null),
		},
	},
	mobreportlydetail_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("附件",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("操作历史",null), 
			group1: commonLogic.appcommonhandle("汇报基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("汇报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("汇报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			account: commonLogic.appcommonhandle("",null), 
			ibzreportlyname: commonLogic.appcommonhandle("汇报名称",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			content: commonLogic.appcommonhandle("工作内容",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			ibz_reportlyid: commonLogic.appcommonhandle("汇报标识",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	mobcreate_form: {
		details: {
			group1: commonLogic.appcommonhandle("汇报基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("汇报标识",null), 
			srfmajortext: commonLogic.appcommonhandle("汇报名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			ibz_reportlyname: commonLogic.appcommonhandle("汇报名称",null), 
			content: commonLogic.appcommonhandle("工作内容",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			reportto: commonLogic.appcommonhandle("汇报给",null), 
			reporttopk: commonLogic.appcommonhandle("汇报给",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			ibz_reportlyid: commonLogic.appcommonhandle("汇报标识",null), 
			date: commonLogic.appcommonhandle("汇报日期",null), 
			account: commonLogic.appcommonhandle("用户",null), 
			issubmit: commonLogic.appcommonhandle("是否提交",null), 
		},
		uiactions: {
		},
	},
	createmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	maininfomobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_mobedit',
		},
		deuiaction1_mobsubmit: {
			caption: commonLogic.appcommonhandle("提交",null),
			tip: 'deuiaction1_mobsubmit',
		},
	},
	reportlymobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: 'deuiaction1',
		},
	},
};