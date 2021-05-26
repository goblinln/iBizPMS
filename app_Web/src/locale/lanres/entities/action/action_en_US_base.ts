import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'thismonth': commonLogic.appcommonhandle("本月",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'yesterday': commonLogic.appcommonhandle("昨天",null),
		'extra': commonLogic.appcommonhandle("附加值",null),
		'files': commonLogic.appcommonhandle("文件",null),
		'lastmonth': commonLogic.appcommonhandle("上月",null),
		'isactorss': commonLogic.appcommonhandle("当前用户",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'thisweek': commonLogic.appcommonhandle("本周",null),
		'today': commonLogic.appcommonhandle("今天",null),
		'date1': commonLogic.appcommonhandle("显示日期",null),
		'objecttype': commonLogic.appcommonhandle("对象类型",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'id': commonLogic.appcommonhandle("id",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'read': commonLogic.appcommonhandle("已读",null),
		'action': commonLogic.appcommonhandle("动作",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'lastcomment': commonLogic.appcommonhandle("备注",null),
		'srfkey': commonLogic.appcommonhandle("前端键值",null),
		'actionmanner': commonLogic.appcommonhandle("操作方式",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'lastweek': commonLogic.appcommonhandle("上周",null),
		'objectid': commonLogic.appcommonhandle("对象ID",null),
		'actor': commonLogic.appcommonhandle("操作者",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'actionsn': commonLogic.appcommonhandle("系统日志编号",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
	},
		views: {
			'historylistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("历史记录",null),
			},
			'addcommenthistorylistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("历史记录",null),
			},
			'alltrendslistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("系统日志列表视图",null),
			},
			'projecttrendslistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("系统日志列表视图",null),
			},
			'projecttrendslistview9': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("产品动态",null),
			},
			'producttrendslistview9': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("产品动态",null),
			},
			'myalltrendslistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("系统日志列表视图",null),
			},
			'mytrendslistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("系统日志列表视图",null),
			},
			'optionview': {
				caption: commonLogic.appcommonhandle("添加备注",null),
				title: commonLogic.appcommonhandle("添加备注",null),
			},
			'producttrendslistview': {
				caption: commonLogic.appcommonhandle("系统日志",null),
				title: commonLogic.appcommonhandle("系统日志列表视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("备注",null),
				title: commonLogic.appcommonhandle("备注",null),
			},
		},
		addcomment_form: {
			details: {
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("备注",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'objecttype': commonLogic.appcommonhandle("对象类型",null), 
				'objectid': commonLogic.appcommonhandle("对象ID",null), 
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
				'button1': commonLogic.appcommonhandle("关闭",null), 
				'button2': commonLogic.appcommonhandle("Save And Close",null), 
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("备注",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'lastcomment': commonLogic.appcommonhandle("",null), 
				'objectid': commonLogic.appcommonhandle("对象ID",null), 
				'noticeusers': commonLogic.appcommonhandle("消息通知用户",null), 
				'objecttype': commonLogic.appcommonhandle("对象类型",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			exit: commonLogic.appcommonhandle("返回",null),
			saveandexit: commonLogic.appcommonhandle("Save And Close",null),
			},
		},
		classifybytype_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		trends_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		grouptrends_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		addcommenthistorylistviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("添加备注",null),
				tip: commonLogic.appcommonhandle("添加备注",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;