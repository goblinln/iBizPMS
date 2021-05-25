import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'thismonth': commonLogic.appcommonhandle("本月",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'yesterday': commonLogic.appcommonhandle("昨天",null),
		'extra': commonLogic.appcommonhandle("附加值",null),
		'actionsn': commonLogic.appcommonhandle("系统日志编号",null),
		'files': commonLogic.appcommonhandle("文件",null),
		'lastmonth': commonLogic.appcommonhandle("上月",null),
		'isactorss': commonLogic.appcommonhandle("当前用户",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'thisweek': commonLogic.appcommonhandle("本周",null),
		'today': commonLogic.appcommonhandle("今天",null),
		'objecttype': commonLogic.appcommonhandle("对象类型",null),
		'date1': commonLogic.appcommonhandle("显示日期",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'id': commonLogic.appcommonhandle("id",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'read': commonLogic.appcommonhandle("已读",null),
		'action': commonLogic.appcommonhandle("动作",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'lastcomment': commonLogic.appcommonhandle("备注",null),
		'actionmanner': commonLogic.appcommonhandle("操作方式",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'lastweek': commonLogic.appcommonhandle("上周",null),
		'actor': commonLogic.appcommonhandle("操作者",null),
		'objectid': commonLogic.appcommonhandle("日报标识",null),
		'project': commonLogic.appcommonhandle("项目",null),
	},
		views: {
			'optionview': {
				caption: commonLogic.appcommonhandle("添加备注",null),
				title: commonLogic.appcommonhandle("添加备注",null),
			},
			'addlistview': {
				caption: commonLogic.appcommonhandle("添加备注",null),
				title: commonLogic.appcommonhandle("日报日志列表视图（添加备注）",null),
			},
			'listview': {
				caption: commonLogic.appcommonhandle("日报日志",null),
				title: commonLogic.appcommonhandle("日报日志列表视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("日报日志",null),
				title: commonLogic.appcommonhandle("日报日志编辑视图",null),
			},
		},
		addcomment_form: {
			details: {
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("备注",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'objecttype': commonLogic.appcommonhandle("对象类型",null), 
				'objectid': commonLogic.appcommonhandle("日报标识",null), 
				'comment': commonLogic.appcommonhandle("备注",null), 
				'files': commonLogic.appcommonhandle("文件",null), 
				'noticeusers': commonLogic.appcommonhandle("消息通知用户",null), 
				'extra': commonLogic.appcommonhandle("附加值",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("日报日志基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("备注",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		classifybytype_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		addlistviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("添加备注",null),
				tip: commonLogic.appcommonhandle("添加备注",null),
			},
		},
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("Save And New",null),
				tip: commonLogic.appcommonhandle("Save And New",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;