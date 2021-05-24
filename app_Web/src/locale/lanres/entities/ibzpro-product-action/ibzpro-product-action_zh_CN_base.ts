import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'thismonth': commonLogic.appcommonhandle("本月",null),
		'yesterday': commonLogic.appcommonhandle("昨天",null),
		'files': commonLogic.appcommonhandle("文件",null),
		'extra': commonLogic.appcommonhandle("附加值",null),
		'lastmonth': commonLogic.appcommonhandle("上月",null),
		'actionsn': commonLogic.appcommonhandle("系统日志编号",null),
		'isactorss': commonLogic.appcommonhandle("当前用户",null),
		'thisweek': commonLogic.appcommonhandle("本周",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'today': commonLogic.appcommonhandle("今天",null),
		'date1': commonLogic.appcommonhandle("显示日期",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'objecttype': commonLogic.appcommonhandle("对象类型",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'id': commonLogic.appcommonhandle("id",null),
		'read': commonLogic.appcommonhandle("已读",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'action': commonLogic.appcommonhandle("动作",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'srfkey': commonLogic.appcommonhandle("前端键值",null),
		'lastcomment': commonLogic.appcommonhandle("备注",null),
		'actionmanner': commonLogic.appcommonhandle("操作方式",null),
		'lastweek': commonLogic.appcommonhandle("上周",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'actor': commonLogic.appcommonhandle("操作者",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'objectid': commonLogic.appcommonhandle("编号",null),
	},
		views: {
			'listview': {
				caption: commonLogic.appcommonhandle("产品日志",null),
				title: commonLogic.appcommonhandle("产品日志列表视图",null),
			},
			'addcommenthistorylistview': {
				caption: commonLogic.appcommonhandle("添加备注",null),
				title: commonLogic.appcommonhandle("产品日志列表视图（添加备注）",null),
			},
		},
		classifybytype_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		list_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		addcommenthistorylistviewtoolbar_toolbar: {
		},
	};
	return data;
}
export default getLocaleResourceBase;