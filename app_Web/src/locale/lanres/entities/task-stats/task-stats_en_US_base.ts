import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'taskdeadline': commonLogic.appcommonhandle("任务截至日期",null),
		'taskid': commonLogic.appcommonhandle("任务编号",null),
		'taskrealstart': commonLogic.appcommonhandle("任务实际开始时间",null),
		'taskefficient': commonLogic.appcommonhandle("效率",null),
		'taskestimate': commonLogic.appcommonhandle("任务预计消耗",null),
		'end': commonLogic.appcommonhandle("结束",null),
		'userconsumed': commonLogic.appcommonhandle("用户总消耗",null),
		'totalleft': commonLogic.appcommonhandle("剩余总工时",null),
		'projectconsumed': commonLogic.appcommonhandle("项目总消耗",null),
		'begin': commonLogic.appcommonhandle("属性",null),
		'totalconsumed': commonLogic.appcommonhandle("消耗总工时",null),
		'totalestimate': commonLogic.appcommonhandle("预计总工时",null),
		'taskeststarted': commonLogic.appcommonhandle("任务预计开始日期",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'dept': commonLogic.appcommonhandle("部门",null),
		'name': commonLogic.appcommonhandle("名称",null),
		'projectname': commonLogic.appcommonhandle("项目名称",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'taskfinisheddate': commonLogic.appcommonhandle("任务实际完成日期",null),
		'taskname': commonLogic.appcommonhandle("任务名称",null),
		'taskcnt': commonLogic.appcommonhandle("总任务数",null),
		'taskpri': commonLogic.appcommonhandle("任务优先级",null),
		'taskdelay': commonLogic.appcommonhandle("任务延期",null),
		'finishedby': commonLogic.appcommonhandle("完成者",null),
	},
		views: {
			'userfinishtaskgridview': {
				caption: commonLogic.appcommonhandle("用户完成任务汇总",null),
				title: commonLogic.appcommonhandle("任务统计表格视图",null),
			},
			'userfinishtasksumgridview': {
				caption: commonLogic.appcommonhandle("用户完成任务汇总",null),
				title: commonLogic.appcommonhandle("任务统计表格视图",null),
			},
		},
		taskfinishgird_grid: {
			columns: {
				'finishedby': commonLogic.appcommonhandle("完成者",null),
				'projectname': commonLogic.appcommonhandle("所属项目",null),
				'taskid': commonLogic.appcommonhandle("编号",null),
				'taskname': commonLogic.appcommonhandle("任务名称",null),
				'taskpri': commonLogic.appcommonhandle("优先级",null),
				'taskeststarted': commonLogic.appcommonhandle("预计开始",null),
				'taskrealstart': commonLogic.appcommonhandle("实际开始",null),
				'taskdeadline': commonLogic.appcommonhandle("截至日期",null),
				'taskfinisheddate': commonLogic.appcommonhandle("实际完成",null),
				'taskdelay': commonLogic.appcommonhandle("延期",null),
				'taskestimate': commonLogic.appcommonhandle("最初预计",null),
				'totalconsumed': commonLogic.appcommonhandle("任务总消耗",null),
				'taskcnt': commonLogic.appcommonhandle("总任务数",null),
				'projectconsumed': commonLogic.appcommonhandle("项目总消耗",null),
				'userconsumed': commonLogic.appcommonhandle("用户总消耗",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		userfinishtasksum_grid: {
			columns: {
				'projectname': commonLogic.appcommonhandle("项目名称",null),
				'finishedby': commonLogic.appcommonhandle("完成者",null),
				'totalestimate': commonLogic.appcommonhandle("预计总工时",null),
				'totalconsumed': commonLogic.appcommonhandle("消耗总工时",null),
				'taskefficient': commonLogic.appcommonhandle("效率",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		taskfinshsearchgird_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("表单分页",null), 
				'n_dept_eq': commonLogic.appcommonhandle("部门",null), 
				'n_begin_gtandeq': commonLogic.appcommonhandle("完成时间",null), 
				'n_end_ltandeq': commonLogic.appcommonhandle("至",null), 
			},
			uiactions: {
			},
		},
		userfinishtask_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("表单分页",null), 
				'n_project_eq': commonLogic.appcommonhandle("项目",null), 
				'n_finishedby_eq': commonLogic.appcommonhandle("完成者",null), 
			},
			uiactions: {
			},
		},
		userfinishtaskgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		userfinishtasksumgridviewtoolbar_toolbar: {
		},
	};
	return data;
}

export default getLocaleResourceBase;