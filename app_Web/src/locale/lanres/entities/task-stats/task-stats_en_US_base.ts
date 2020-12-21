import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
		fields: {
			projectname: commonLogic.appcommonhandle("项目名称",null),
			totalestimate: commonLogic.appcommonhandle("预计总工时",null),
			totalconsumed: commonLogic.appcommonhandle("消耗总工时",null),
			totalleft: commonLogic.appcommonhandle("剩余总工时",null),
			project: commonLogic.appcommonhandle("项目",null),
			id: commonLogic.appcommonhandle("编号",null),
			name: commonLogic.appcommonhandle("名称",null),
			finishedby: commonLogic.appcommonhandle("完成者",null),
			taskefficient: commonLogic.appcommonhandle("效率",null),
			taskid: commonLogic.appcommonhandle("任务编号",null),
			taskname: commonLogic.appcommonhandle("任务名称",null),
			taskpri: commonLogic.appcommonhandle("任务优先级",null),
			taskestimate: commonLogic.appcommonhandle("任务总消耗",null),
			taskrealstart: commonLogic.appcommonhandle("任务实际开始时间",null),
			taskdeadline: commonLogic.appcommonhandle("任务截至日期",null),
			taskfinisheddate: commonLogic.appcommonhandle("任务实际完成日期",null),
			taskdelay: commonLogic.appcommonhandle("任务延期",null),
			taskeststarted: commonLogic.appcommonhandle("任务预计开始日期",null),
			taskcnt: commonLogic.appcommonhandle("总任务数",null),
			projectconsumed: commonLogic.appcommonhandle("项目总消耗",null),
			userconsumed: commonLogic.appcommonhandle("用户总消耗",null),
		},
			views: {
				userfinishtasksumgridview: {
					caption: commonLogic.appcommonhandle("用户完成任务汇总",null),
					title: commonLogic.appcommonhandle("任务统计表格视图",null),
				},
			},
			userfinishtasksum_grid: {
				columns: {
					projectname: commonLogic.appcommonhandle("项目名称",null),
					finishedby: commonLogic.appcommonhandle("完成者",null),
					totalestimate: commonLogic.appcommonhandle("预计总工时",null),
					totalconsumed: commonLogic.appcommonhandle("消耗总工时",null),
					taskefficient: commonLogic.appcommonhandle("效率",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			userfinishtask_searchform: {
				details: {
					formpage1: commonLogic.appcommonhandle("表单分页",null), 
					n_project_eq: commonLogic.appcommonhandle("项目",null), 
					n_finishedby_eq: commonLogic.appcommonhandle("完成者",null), 
				},
				uiactions: {
				},
			},
			userfinishtasksumgridviewtoolbar_toolbar: {
			},
		};
		return data;
}

export default getLocaleResourceBase;