import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			createman: commonLogic.appcommonhandle("建立人",null),
			ibzdailyid: commonLogic.appcommonhandle("日报标识",null),
			ibzdailyname: commonLogic.appcommonhandle("日报名称",null),
			updatedate: commonLogic.appcommonhandle("更新时间",null),
			createdate: commonLogic.appcommonhandle("建立时间",null),
			updateman: commonLogic.appcommonhandle("更新人",null),
			date: commonLogic.appcommonhandle("日期",null),
			account: commonLogic.appcommonhandle("用户",null),
			worktoday: commonLogic.appcommonhandle("今日工作",null),
			mailto: commonLogic.appcommonhandle("抄送给",null),
			files: commonLogic.appcommonhandle("附件",null),
			todaytask: commonLogic.appcommonhandle("完成任务",null),
			issubmit: commonLogic.appcommonhandle("是否提交",null),
			planstomorrow: commonLogic.appcommonhandle("明日计划",null),
			tomorrowplanstask: commonLogic.appcommonhandle("明日计划任务",null),
			reportto: commonLogic.appcommonhandle("汇报给",null),
			comment: commonLogic.appcommonhandle("其他事项",null),
			createmanname: commonLogic.appcommonhandle("建立人名称",null),
			updatemanname: commonLogic.appcommonhandle("更新人名称",null),
			reportstatus: commonLogic.appcommonhandle("状态",null),
			submittime: commonLogic.appcommonhandle("提交时间",null),
			reporttopk: commonLogic.appcommonhandle("汇报给（选择）",null),
			mailtopk: commonLogic.appcommonhandle("抄送给（选择）",null),
		},
			views: {
				dailyeditview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报编辑视图",null),
				},
				maineditview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报编辑视图（主数据）",null),
				},
				mymaineditview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报编辑视图（主数据）",null),
				},
				dailygridview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报表格视图",null),
				},
				dailyinfocalendareditview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报编辑视图(日历显示)",null),
				},
				dailyinfoeditview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报编辑视图(日报描述)",null),
				},
				mydailygridview: {
					caption: commonLogic.appcommonhandle("日报",null),
					title: commonLogic.appcommonhandle("日报表格视图",null),
				},
			},
			maininfo_form: {
				details: {
					druipart3: commonLogic.appcommonhandle("",null), 
					grouppanel4: commonLogic.appcommonhandle("附件",null), 
					druipart4: commonLogic.appcommonhandle("",null), 
					grouppanel5: commonLogic.appcommonhandle("操作",null), 
					group1: commonLogic.appcommonhandle("日报基本信息",null), 
					druipart1: commonLogic.appcommonhandle("",null), 
					grouppanel2: commonLogic.appcommonhandle("完成任务",null), 
					druipart2: commonLogic.appcommonhandle("",null), 
					grouppanel3: commonLogic.appcommonhandle("计划参与",null), 
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
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
					worktoday: commonLogic.appcommonhandle("今日工作",null), 
					planstomorrow: commonLogic.appcommonhandle("明日计划",null), 
					comment: commonLogic.appcommonhandle("其他事项",null), 
					reportto: commonLogic.appcommonhandle("汇报给",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					todaytask: commonLogic.appcommonhandle("完成任务",null), 
					tomorrowplanstask: commonLogic.appcommonhandle("明日计划任务",null), 
					ibz_dailyid: commonLogic.appcommonhandle("日报标识",null), 
					account: commonLogic.appcommonhandle("用户",null), 
					issubmit: commonLogic.appcommonhandle("是否提交",null), 
				},
				uiactions: {
				},
			},
			dailyedit_form: {
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
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					ibz_dailyid: commonLogic.appcommonhandle("日报标识",null), 
					account: commonLogic.appcommonhandle("用户",null), 
					issubmit: commonLogic.appcommonhandle("是否提交",null), 
				},
				uiactions: {
				},
			},
			dailyinfocalendar_form: {
				details: {
					group1: commonLogic.appcommonhandle("日报基本信息",null), 
					button1: commonLogic.appcommonhandle("修改",null), 
					button2: commonLogic.appcommonhandle("提交",null), 
					grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
					druipart2: commonLogic.appcommonhandle("",null), 
					druipart1: commonLogic.appcommonhandle("",null), 
					formpage1: commonLogic.appcommonhandle("详情",null), 
					druipart3: commonLogic.appcommonhandle("",null), 
					formpage2: commonLogic.appcommonhandle("完成任务",null), 
					druipart4: commonLogic.appcommonhandle("",null), 
					formpage3: commonLogic.appcommonhandle("计划参与",null), 
					srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("日报标识",null), 
					srfmajortext: commonLogic.appcommonhandle("日报名称",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					ibz_dailyname: commonLogic.appcommonhandle("",null), 
					ibz_dailyid: commonLogic.appcommonhandle("日报标识",null), 
					issubmit: commonLogic.appcommonhandle("是否提交",null), 
					worktoday: commonLogic.appcommonhandle("今日工作",null), 
					planstomorrow: commonLogic.appcommonhandle("明日计划",null), 
					reportto: commonLogic.appcommonhandle("汇报给",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					todaytask: commonLogic.appcommonhandle("完成任务",null), 
					tomorrowplanstask: commonLogic.appcommonhandle("明日计划任务",null), 
				},
				uiactions: {
				ibzdaily_editcz: commonLogic.appcommonhandle("修改",null),
				ibzdaily_submit: commonLogic.appcommonhandle("提交",null),
				},
			},
			mymain_grid: {
				columns: {
					ibz_dailyid: commonLogic.appcommonhandle("编号",null),
					ibz_dailyname: commonLogic.appcommonhandle("日报名称",null),
					account: commonLogic.appcommonhandle("用户",null),
					date: commonLogic.appcommonhandle("日期",null),
					reportto: commonLogic.appcommonhandle("汇报给",null),
					reportstatus: commonLogic.appcommonhandle("状态",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				ibzdaily_haveread: commonLogic.appcommonhandle("已读",null),
				},
			},
			main_grid: {
				columns: {
					ibz_dailyname: commonLogic.appcommonhandle("日报名称",null),
					account: commonLogic.appcommonhandle("用户",null),
					date: commonLogic.appcommonhandle("日期",null),
					reportto: commonLogic.appcommonhandle("汇报给",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				ibzdaily_edit: commonLogic.appcommonhandle("修改",null),
				ibzdaily_submitcz: commonLogic.appcommonhandle("提交",null),
				},
			},
			default_searchform: {
				details: {
					formpage1: commonLogic.appcommonhandle("常规条件",null), 
				},
				uiactions: {
				},
			},
			dailyinfocalendareditviewtoolbar_toolbar: {
			},
			maineditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			dailyinfoeditviewtoolbar_toolbar: {
			},
			dailyeditviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			dailygridviewtoolbar_toolbar: {
				deuiaction1_createuserdaily: {
					caption: commonLogic.appcommonhandle("生成日报",null),
					tip: commonLogic.appcommonhandle("生成日报",null),
				},
				deuiaction2_create: {
					caption: commonLogic.appcommonhandle("新建",null),
					tip: commonLogic.appcommonhandle("新建",null),
				},
				deuiaction7: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
			},
		};
		return data;
}

export default getLocaleResourceBase;