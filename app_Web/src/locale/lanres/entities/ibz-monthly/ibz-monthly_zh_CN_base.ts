import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'createman': commonLogic.appcommonhandle("建立人",null),
		'reportstatus': commonLogic.appcommonhandle("状态",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'plansnextmonth': commonLogic.appcommonhandle("下月计划",null),
		'reporttopk': commonLogic.appcommonhandle("汇报给（选择）",null),
		'issubmit': commonLogic.appcommonhandle("是否提交",null),
		'mailtopk': commonLogic.appcommonhandle("抄送给（选择）",null),
		'updatemanname': commonLogic.appcommonhandle("更新人名称",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'submittime': commonLogic.appcommonhandle("提交时间",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'createmanname': commonLogic.appcommonhandle("建立人名称",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'ibzmonthlyid': commonLogic.appcommonhandle("月报标识",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'nextmonthplanstask': commonLogic.appcommonhandle("下月计划任务",null),
		'thismonthtask': commonLogic.appcommonhandle("本月完成任务",null),
		'workthismonth': commonLogic.appcommonhandle("本月工作",null),
		'reportto': commonLogic.appcommonhandle("汇报给",null),
		'ibzmonthlyname': commonLogic.appcommonhandle("月报名称",null),
		'comment': commonLogic.appcommonhandle("其他事项",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
	},
		views: {
			'editmonthlyeditview': {
				caption: commonLogic.appcommonhandle("月报",null),
				title: commonLogic.appcommonhandle("月报编辑视图",null),
			},
			'monthlymainmsgeditview': {
				caption: commonLogic.appcommonhandle("月报",null),
				title: commonLogic.appcommonhandle("月报编辑视图",null),
			},
			'myreceivedgridview': {
				caption: commonLogic.appcommonhandle("月报实体表格视图",null),
				title: commonLogic.appcommonhandle("月报表格视图",null),
			},
			'newmonthlyeditview': {
				caption: commonLogic.appcommonhandle("月报",null),
				title: commonLogic.appcommonhandle("月报编辑视图",null),
			},
			'mainmonthlygridview': {
				caption: commonLogic.appcommonhandle("月报",null),
				title: commonLogic.appcommonhandle("月报表格视图",null),
			},
			'projectmonthlygridview_': {
				caption: commonLogic.appcommonhandle("月报",null),
				title: commonLogic.appcommonhandle("月报表格视图",null),
			},
			'myreceivededitview': {
				caption: commonLogic.appcommonhandle("月报实体编辑视图",null),
				title: commonLogic.appcommonhandle("月报编辑视图",null),
			},
			'productmonthlygridview': {
				caption: commonLogic.appcommonhandle("月报",null),
				title: commonLogic.appcommonhandle("月报表格视图",null),
			},
		},
		maininfo_form: {
			details: {
				'druipart1': commonLogic.appcommonhandle("",null), 
				'grouppanel1': commonLogic.appcommonhandle("附件",null), 
				'druipart2': commonLogic.appcommonhandle("",null), 
				'grouppanel2': commonLogic.appcommonhandle("操作",null), 
				'group1': commonLogic.appcommonhandle("月报基本信息",null), 
				'druipart3': commonLogic.appcommonhandle("",null), 
				'grouppanel4': commonLogic.appcommonhandle("完成任务",null), 
				'druipart4': commonLogic.appcommonhandle("",null), 
				'grouppanel5': commonLogic.appcommonhandle("计划参与",null), 
				'grouppanel3': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("月报标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("月报名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null), 
				'date': commonLogic.appcommonhandle("日期",null), 
				'workthismonth': commonLogic.appcommonhandle("本月工作",null), 
				'plansnextmonth': commonLogic.appcommonhandle("下月计划",null), 
				'comment': commonLogic.appcommonhandle("其他事项",null), 
				'reportto': commonLogic.appcommonhandle("汇报给",null), 
				'mailto': commonLogic.appcommonhandle("抄送给",null), 
				'thismonthtask': commonLogic.appcommonhandle("本月完成任务",null), 
				'nextmonthplanstask': commonLogic.appcommonhandle("下月计划任务",null), 
				'ibz_monthlyid': commonLogic.appcommonhandle("月报标识",null), 
				'account': commonLogic.appcommonhandle("用户",null), 
				'issubmit': commonLogic.appcommonhandle("是否提交",null), 
			},
			uiactions: {
			},
		},
		new_form: {
			details: {
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("月报标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("月报名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null), 
				'date': commonLogic.appcommonhandle("日期",null), 
				'reportto': commonLogic.appcommonhandle("汇报给",null), 
				'mailto': commonLogic.appcommonhandle("抄送给",null), 
				'thismonthtask': commonLogic.appcommonhandle("本月完成任务",null), 
				'workthismonth': commonLogic.appcommonhandle("本月工作",null), 
				'nextmonthplanstask': commonLogic.appcommonhandle("下月计划任务",null), 
				'plansnextmonth': commonLogic.appcommonhandle("下月计划",null), 
				'comment': commonLogic.appcommonhandle("其他事项",null), 
				'files': commonLogic.appcommonhandle("附件",null), 
				'ibz_monthlyid': commonLogic.appcommonhandle("月报标识",null), 
				'account': commonLogic.appcommonhandle("用户",null), 
				'issubmit': commonLogic.appcommonhandle("是否提交",null), 
			},
			uiactions: {
			},
		},
		monthmain_grid: {
			columns: {
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				ibzmonthly_projectmonthly: commonLogic.appcommonhandle("项目月报查看详情",null),
			},
		},
		productmonthlymain_grid: {
			columns: {
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				ibzmonthly_projectmonthly: commonLogic.appcommonhandle("项目月报查看详情",null),
			},
		},
		myreceived_grid: {
			columns: {
				'ibz_monthlyid': commonLogic.appcommonhandle("编号",null),
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
				'reportstatus': commonLogic.appcommonhandle("状态",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				ibzmonthly_haveread: commonLogic.appcommonhandle("已读",null),
			},
		},
		main_grid: {
			columns: {
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'ibz_monthlyid': commonLogic.appcommonhandle("月报标识",null),
				'ibz_monthlyname': commonLogic.appcommonhandle("月报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
				'issubmit': commonLogic.appcommonhandle("是否提交",null),
		},
			uiactions: {
				ibzmonthly_edit: commonLogic.appcommonhandle("修改",null),
				ibzmonthly_submit: commonLogic.appcommonhandle("提交",null),
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		editmonthlyeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		newmonthlyeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		mainmonthlygridviewtoolbar_toolbar: {
			'deuiaction2_create': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;