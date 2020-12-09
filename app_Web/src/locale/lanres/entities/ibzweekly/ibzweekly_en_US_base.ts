import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			ibzweeklyname: commonLogic.appcommonhandle("周报名称",null),
			ibzweeklyid: commonLogic.appcommonhandle("周报标识",null),
			createman: commonLogic.appcommonhandle("建立人",null),
			createdate: commonLogic.appcommonhandle("建立时间",null),
			updateman: commonLogic.appcommonhandle("更新人",null),
			updatedate: commonLogic.appcommonhandle("更新时间",null),
			account: commonLogic.appcommonhandle("用户",null),
			mailto: commonLogic.appcommonhandle("抄送给",null),
			files: commonLogic.appcommonhandle("附件",null),
			issubmit: commonLogic.appcommonhandle("是否提交",null),
			reportto: commonLogic.appcommonhandle("汇报给",null),
			comment: commonLogic.appcommonhandle("其他事项",null),
			date: commonLogic.appcommonhandle("日期",null),
			workthisweek: commonLogic.appcommonhandle("本周工作",null),
			plannextweek: commonLogic.appcommonhandle("下周计划",null),
			thisweektask: commonLogic.appcommonhandle("本周完成任务",null),
			nextweektask: commonLogic.appcommonhandle("下周计划任务",null),
			updatemanname: commonLogic.appcommonhandle("更新人名称",null),
			createmanname: commonLogic.appcommonhandle("建立人名称",null),
			reportstatus: commonLogic.appcommonhandle("状态",null),
			submittime: commonLogic.appcommonhandle("提交时间",null),
			reporttopk: commonLogic.appcommonhandle("汇报给(选择)",null),
			mailtopk: commonLogic.appcommonhandle("抄送给(选择)",null),
		},
			views: {
				usr2editview: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("周报编辑视图",null),
				},
				editview: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("实体2编辑视图",null),
				},
				usr3gridview: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("周报表格视图",null),
				},
				usr2gridview: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("周报表格视图",null),
				},
				gridview: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("实体2表格视图",null),
				},
				editviewedit: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("实体2编辑视图",null),
				},
				editviewmainmyweekly: {
					caption: commonLogic.appcommonhandle("周报",null),
					title: commonLogic.appcommonhandle("实体2编辑视图",null),
				},
			},
			weeklymsg_form: {
				details: {
					druipart2: commonLogic.appcommonhandle("附件",null), 
					grouppanel1: commonLogic.appcommonhandle("附件",null), 
					druipart1: commonLogic.appcommonhandle("操作历史",null), 
					grouppanel2: commonLogic.appcommonhandle("操作历史",null), 
					group1: commonLogic.appcommonhandle("周报基本信息",null), 
					druipart3: commonLogic.appcommonhandle("",null), 
					grouppanel3: commonLogic.appcommonhandle("完成的任务",null), 
					druipart4: commonLogic.appcommonhandle("",null), 
					grouppanel4: commonLogic.appcommonhandle("下周计划任务",null), 
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
					workthisweek: commonLogic.appcommonhandle("本周工作",null), 
					plannextweek: commonLogic.appcommonhandle("下周计划",null), 
					comment: commonLogic.appcommonhandle("其他事项",null), 
					reportto: commonLogic.appcommonhandle("汇报给",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					thisweektask: commonLogic.appcommonhandle("本周完成任务",null), 
					nextweektask: commonLogic.appcommonhandle("下周计划任务",null), 
					ibz_weeklyid: commonLogic.appcommonhandle("周报标识",null), 
				},
				uiactions: {
				},
			},
			main_form: {
				details: {
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
					thisweektask1: commonLogic.appcommonhandle("本周完成任务",null), 
					workthisweek: commonLogic.appcommonhandle("本周工作",null), 
					nextweektask: commonLogic.appcommonhandle("下周计划任务",null), 
					plannextweek: commonLogic.appcommonhandle("下周计划",null), 
					comment: commonLogic.appcommonhandle("其他事项",null), 
					files: commonLogic.appcommonhandle("附件",null), 
					reportto: commonLogic.appcommonhandle("汇报给",null), 
					mailto: commonLogic.appcommonhandle("抄送给",null), 
					ibz_weeklyid: commonLogic.appcommonhandle("周报标识",null), 
					account: commonLogic.appcommonhandle("用户",null), 
					issubmit: commonLogic.appcommonhandle("是否提交",null), 
				},
				uiactions: {
				},
			},
			myreceviedweekly_grid: {
				columns: {
					ibz_weeklyid: commonLogic.appcommonhandle("编号",null),
					ibz_weeklyname: commonLogic.appcommonhandle("周报名称",null),
					account: commonLogic.appcommonhandle("用户",null),
					date: commonLogic.appcommonhandle("日期",null),
					reportto: commonLogic.appcommonhandle("汇报给",null),
					reportstatus: commonLogic.appcommonhandle("状态",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				ibzweekly_haveread: commonLogic.appcommonhandle("已读",null),
				},
			},
			main_grid: {
				columns: {
					ibz_weeklyname: commonLogic.appcommonhandle("周报名称",null),
					account: commonLogic.appcommonhandle("用户",null),
					date: commonLogic.appcommonhandle("日期",null),
					reportto: commonLogic.appcommonhandle("汇报给",null),
					uagridcolumn1: commonLogic.appcommonhandle("操作",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
			exportColumns: {
					ibz_weeklyid: commonLogic.appcommonhandle("周报标识",null),
					ibz_weeklyname: commonLogic.appcommonhandle("周报名称",null),
					account: commonLogic.appcommonhandle("用户",null),
					date: commonLogic.appcommonhandle("日期",null),
					reportto: commonLogic.appcommonhandle("汇报给",null),
					issubmit: commonLogic.appcommonhandle("是否提交",null),
			},
				uiactions: {
				ibzweekly_submitzzz: commonLogic.appcommonhandle("提交",null),
				ibzweekly_edit: commonLogic.appcommonhandle("修改",null),
				},
			},
			default_searchform: {
				details: {
					formpage1: commonLogic.appcommonhandle("常规条件",null), 
				},
				uiactions: {
				},
			},
			usr2editviewtoolbar_toolbar: {
			},
			editviewtoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			editviewedittoolbar_toolbar: {
				deuiaction1: {
					caption: commonLogic.appcommonhandle("Save And Close",null),
					tip: commonLogic.appcommonhandle("Save And Close Window",null),
				},
			},
			usr2gridviewtoolbar_toolbar: {
				deuiaction1_createeveryweekreport: {
					caption: commonLogic.appcommonhandle("生成周报",null),
					tip: commonLogic.appcommonhandle("生成周报",null),
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
			gridviewtoolbar_toolbar: {
				tbitem3: {
					caption: commonLogic.appcommonhandle("New",null),
					tip: commonLogic.appcommonhandle("New",null),
				},
				tbitem4: {
					caption: commonLogic.appcommonhandle("Edit",null),
					tip: commonLogic.appcommonhandle("Edit {0}",null),
				},
				tbitem6: {
					caption: commonLogic.appcommonhandle("Copy",null),
					tip: commonLogic.appcommonhandle("Copy {0}",null),
				},
				tbitem7: {
					caption: commonLogic.appcommonhandle("-",null),
					tip: commonLogic.appcommonhandle("",null),
				},
				tbitem8: {
					caption: commonLogic.appcommonhandle("Remove",null),
					tip: commonLogic.appcommonhandle("Remove {0}",null),
				},
				tbitem9: {
					caption: commonLogic.appcommonhandle("-",null),
					tip: commonLogic.appcommonhandle("",null),
				},
				tbitem13: {
					caption: commonLogic.appcommonhandle("Export",null),
					tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
				},
				tbitem10: {
					caption: commonLogic.appcommonhandle("-",null),
					tip: commonLogic.appcommonhandle("",null),
				},
				tbitem16: {
					caption: commonLogic.appcommonhandle("其它",null),
					tip: commonLogic.appcommonhandle("其它",null),
				},
				tbitem21: {
					caption: commonLogic.appcommonhandle("Export Data Model",null),
					tip: commonLogic.appcommonhandle("导出数据模型",null),
				},
				tbitem23: {
					caption: commonLogic.appcommonhandle("数据导入",null),
					tip: commonLogic.appcommonhandle("数据导入",null),
				},
				tbitem17: {
					caption: commonLogic.appcommonhandle("-",null),
					tip: commonLogic.appcommonhandle("",null),
				},
				tbitem19: {
					caption: commonLogic.appcommonhandle("Filter",null),
					tip: commonLogic.appcommonhandle("Filter",null),
				},
				tbitem18: {
					caption: commonLogic.appcommonhandle("Help",null),
					tip: commonLogic.appcommonhandle("Help",null),
				},
			},
		};
		return data;
}

export default getLocaleResourceBase;