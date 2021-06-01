import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'id': commonLogic.appcommonhandle("主键",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'productname': commonLogic.appcommonhandle("产品",null),
		'projectname': commonLogic.appcommonhandle("项目名称",null),
		'planname': commonLogic.appcommonhandle("计划名称",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'plan': commonLogic.appcommonhandle("产品计划",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'project': commonLogic.appcommonhandle("项目编号",null),
		'productcode': commonLogic.appcommonhandle("产品编号",null),
		'begin': commonLogic.appcommonhandle("计划开始时间",null),
		'end': commonLogic.appcommonhandle("计划结束时间",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'status': commonLogic.appcommonhandle("项目状态",null),
		'projectcode': commonLogic.appcommonhandle("项目代号",null),
		'projectend': commonLogic.appcommonhandle("结束日期",null),
	},
		views: {
			'planlistview9': {
				caption: commonLogic.appcommonhandle("关联计划",null),
				title: commonLogic.appcommonhandle("项目产品列表视图",null),
			},
			'plangridview9': {
				caption: commonLogic.appcommonhandle("关联计划",null),
				title: commonLogic.appcommonhandle("项目产品表格视图（关联计划）",null),
			},
			'listview9': {
				caption: commonLogic.appcommonhandle("关联产品",null),
				title: commonLogic.appcommonhandle("项目产品列表视图",null),
			},
			'productgridview9': {
				caption: commonLogic.appcommonhandle("关联产品",null),
				title: commonLogic.appcommonhandle("项目产品表格视图（关联计划）",null),
			},
			'projectgridview9': {
				caption: commonLogic.appcommonhandle("关联项目",null),
				title: commonLogic.appcommonhandle("项目产品表格视图（关联项目）",null),
			},
		},
		mainplan_grid: {
			columns: {
				'planname': commonLogic.appcommonhandle("计划名称",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'productname': commonLogic.appcommonhandle("产品名称",null),
				'productcode': commonLogic.appcommonhandle("产品代号",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectmain_grid: {
			columns: {
				'projectname': commonLogic.appcommonhandle("项目名称",null),
				'projectcode': commonLogic.appcommonhandle("项目代号",null),
				'status': commonLogic.appcommonhandle("项目状态",null),
				'projectend': commonLogic.appcommonhandle("结束日期",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		plan_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;