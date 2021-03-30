import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'role': commonLogic.appcommonhandle("角色",null),
		'limited': commonLogic.appcommonhandle("受限用户",null),
		'total': commonLogic.appcommonhandle("总计可用",null),
		'username': commonLogic.appcommonhandle("用户",null),
		'days': commonLogic.appcommonhandle("可用工日",null),
		'exitdate': commonLogic.appcommonhandle("退场时间",null),
		'type': commonLogic.appcommonhandle("团队类型",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'estimate': commonLogic.appcommonhandle("最初预计",null),
		'join': commonLogic.appcommonhandle("加盟日",null),
		'hours': commonLogic.appcommonhandle("可用工时/天",null),
		'taskcnt': commonLogic.appcommonhandle("任务数",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'pm': commonLogic.appcommonhandle("项目经理",null),
		'projectname': commonLogic.appcommonhandle("所属项目",null),
		'root': commonLogic.appcommonhandle("项目编号",null),
	},
		views: {
			'maingridview': {
				caption: commonLogic.appcommonhandle("项目团队",null),
				title: commonLogic.appcommonhandle("项目团队表格视图",null),
			},
			'maingridview_editrow': {
				caption: commonLogic.appcommonhandle("项目团队",null),
				title: commonLogic.appcommonhandle("项目团队表格视图",null),
			},
			'listview': {
				caption: commonLogic.appcommonhandle("项目团队",null),
				title: commonLogic.appcommonhandle("项目团队列表视图",null),
			},
		},
		main_editrow_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'role': commonLogic.appcommonhandle("角色",null),
				'days': commonLogic.appcommonhandle("可用工日",null),
				'hours': commonLogic.appcommonhandle("可用工时/天",null),
				'limited': commonLogic.appcommonhandle("受限用户",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				newrow: commonLogic.appcommonhandle("新建行",null),
				remove: commonLogic.appcommonhandle("删除",null),
			},
		},
		main_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'role': commonLogic.appcommonhandle("角色",null),
				'join': commonLogic.appcommonhandle("加盟日",null),
				'days': commonLogic.appcommonhandle("可用工日",null),
				'hours': commonLogic.appcommonhandle("可用工时/天",null),
				'total': commonLogic.appcommonhandle("总计可用",null),
				'limited': commonLogic.appcommonhandle("受限用户",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'role': commonLogic.appcommonhandle("角色",null),
				'join': commonLogic.appcommonhandle("加盟日",null),
				'days': commonLogic.appcommonhandle("可用工日",null),
				'hours': commonLogic.appcommonhandle("可用工时/天",null),
				'total': commonLogic.appcommonhandle("总计可用",null),
				'limited': commonLogic.appcommonhandle("受限用户",null),
		},
			uiactions: {
				projectteam_removemember: commonLogic.appcommonhandle("移除成员",null),
			},
		},
		projectteampc_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
				'dept': commonLogic.appcommonhandle("选择部门",null), 
				'teams': commonLogic.appcommonhandle("复制团队",null), 
			},
			uiactions: {
			},
		},
		maingridview_editrowtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
		listviewtoolbar_toolbar: {
			'deuiaction3_managermember': {
				caption: commonLogic.appcommonhandle("团队管理",null),
				tip: commonLogic.appcommonhandle("团队管理",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction3_managermember': {
				caption: commonLogic.appcommonhandle("团队管理",null),
				tip: commonLogic.appcommonhandle("团队管理",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;