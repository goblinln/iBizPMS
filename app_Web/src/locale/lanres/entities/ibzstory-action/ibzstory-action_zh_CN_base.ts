import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'lastcomment': commonLogic.appcommonhandle("备注",null),
		'extra': commonLogic.appcommonhandle("附加值",null),
		'files': commonLogic.appcommonhandle("文件",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'date1': commonLogic.appcommonhandle("显示日期",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'thisweek': commonLogic.appcommonhandle("本周",null),
		'thismonth': commonLogic.appcommonhandle("本月",null),
		'lastmonth': commonLogic.appcommonhandle("上月",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'today': commonLogic.appcommonhandle("今天",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'lastweek': commonLogic.appcommonhandle("上周",null),
		'yesterday': commonLogic.appcommonhandle("昨天",null),
		'isactorss': commonLogic.appcommonhandle("当前用户",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'actionsn': commonLogic.appcommonhandle("系统日志编号",null),
		'srfkey': commonLogic.appcommonhandle("前端键值",null),
		'id': commonLogic.appcommonhandle("id",null),
		'actor': commonLogic.appcommonhandle("操作者",null),
		'objecttype': commonLogic.appcommonhandle("对象类型",null),
		'action': commonLogic.appcommonhandle("动作",null),
		'actionmanner': commonLogic.appcommonhandle("操作方式",null),
		'read': commonLogic.appcommonhandle("已读",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'objectid': commonLogic.appcommonhandle("编号",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("需求日志",null),
				title: commonLogic.appcommonhandle("需求日志表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("需求日志",null),
				title: commonLogic.appcommonhandle("需求日志编辑视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("需求日志基本信息",null), 
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
		main_grid: {
			columns: {
			},
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
		gridviewtoolbar_toolbar: {
			'tbitem13': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("保存",null),
				tip: commonLogic.appcommonhandle("保存",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("保存并新建",null),
				tip: commonLogic.appcommonhandle("保存并新建",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;