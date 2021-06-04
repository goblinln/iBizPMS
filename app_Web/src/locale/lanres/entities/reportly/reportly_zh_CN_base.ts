import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'files': commonLogic.appcommonhandle("附件",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
		'ibzreportlyid': commonLogic.appcommonhandle("汇报标识",null),
		'ibzreportlyname': commonLogic.appcommonhandle("汇报名称",null),
		'reportstatus': commonLogic.appcommonhandle("状态",null),
		'submittime': commonLogic.appcommonhandle("提交时间",null),
		'reporttopk': commonLogic.appcommonhandle("汇报给（选择）",null),
		'reportto': commonLogic.appcommonhandle("汇报给",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'issubmit': commonLogic.appcommonhandle("是否提交",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'content': commonLogic.appcommonhandle("工作内容",null),
		'date': commonLogic.appcommonhandle("汇报日期",null),
		'mailtopk': commonLogic.appcommonhandle("抄送给（选择）",null),
	},
		views: {
			'maingridview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("汇报表格视图",null),
			},
			'myreceivedreportlydetaileditview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("汇报编辑视图",null),
			},
			'myreceivedgridview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("汇报表格视图",null),
			},
			'reportlydetaileditview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("汇报编辑视图",null),
			},
			'calendareditview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("汇报编辑视图",null),
			},
			'createeditview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("汇报编辑视图",null),
			},
		},
		reportlydetail_form: {
			details: {
				'druipart1': commonLogic.appcommonhandle("",null), 
				'grouppanel1': commonLogic.appcommonhandle("附件",null), 
				'druipart2': commonLogic.appcommonhandle("",null), 
				'grouppanel2': commonLogic.appcommonhandle("操作",null), 
				'group1': commonLogic.appcommonhandle("汇报基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("汇报标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("汇报名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'ibz_reportlyname': commonLogic.appcommonhandle("汇报名称",null), 
				'date': commonLogic.appcommonhandle("汇报日期",null), 
				'content': commonLogic.appcommonhandle("工作内容",null), 
				'reportto': commonLogic.appcommonhandle("汇报给",null), 
				'mailto': commonLogic.appcommonhandle("抄送给",null), 
				'ibz_reportlyid': commonLogic.appcommonhandle("汇报标识",null), 
				'account': commonLogic.appcommonhandle("用户",null), 
				'issubmit': commonLogic.appcommonhandle("是否提交",null), 
			},
			uiactions: {
			},
		},
		create_form: {
			details: {
				'group1': commonLogic.appcommonhandle("汇报基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("汇报标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("汇报名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'ibz_reportlyname': commonLogic.appcommonhandle("汇报名称",null), 
				'content': commonLogic.appcommonhandle("工作内容",null), 
				'files': commonLogic.appcommonhandle("附件",null), 
				'reportto': commonLogic.appcommonhandle("汇报给",null), 
				'mailto': commonLogic.appcommonhandle("抄送给",null), 
				'ibz_reportlyid': commonLogic.appcommonhandle("汇报标识",null), 
				'date': commonLogic.appcommonhandle("汇报日期",null), 
				'account': commonLogic.appcommonhandle("用户",null), 
				'issubmit': commonLogic.appcommonhandle("是否提交",null), 
			},
			uiactions: {
			},
		},
		calendarshow_form: {
			details: {
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'button1': commonLogic.appcommonhandle("编辑",null), 
				'button2': commonLogic.appcommonhandle("提交",null), 
				'grouppanel3': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel4': commonLogic.appcommonhandle("工作内容",null), 
				'druipart1': commonLogic.appcommonhandle("",null), 
				'grouppanel5': commonLogic.appcommonhandle("附件",null), 
				'druipart2': commonLogic.appcommonhandle("",null), 
				'grouppanel6': commonLogic.appcommonhandle("历史记录",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("汇报标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("汇报名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'ibz_reportlyid': commonLogic.appcommonhandle("",null), 
				'ibz_reportlyname': commonLogic.appcommonhandle("",null), 
				'account': commonLogic.appcommonhandle("",null), 
				'date': commonLogic.appcommonhandle("",null), 
				'content': commonLogic.appcommonhandle("",null), 
				'issubmit': commonLogic.appcommonhandle("是否提交",null), 
			},
			uiactions: {
				reportly_calendarshowedit: commonLogic.appcommonhandle("编辑",null),
				reportly_calendarshowsubmit: commonLogic.appcommonhandle("提交",null),
			},
		},
		myreceived_grid: {
			columns: {
				'ibz_reportlyid': commonLogic.appcommonhandle("编号",null),
				'ibz_reportlyname': commonLogic.appcommonhandle("汇报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
				'reportstatus': commonLogic.appcommonhandle("状态",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				reportly_haveread: commonLogic.appcommonhandle("已读",null),
			},
		},
		main_grid: {
			columns: {
				'ibz_reportlyname': commonLogic.appcommonhandle("汇报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'ibz_reportlyid': commonLogic.appcommonhandle("汇报标识",null),
				'ibz_reportlyname': commonLogic.appcommonhandle("汇报名称",null),
				'account': commonLogic.appcommonhandle("用户",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'reportto': commonLogic.appcommonhandle("汇报给",null),
				'issubmit': commonLogic.appcommonhandle("是否提交",null),
		},
			uiactions: {
				reportly_edit: commonLogic.appcommonhandle("编辑",null),
				reportly_submit: commonLogic.appcommonhandle("提交",null),
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		createeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction1_create': {
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