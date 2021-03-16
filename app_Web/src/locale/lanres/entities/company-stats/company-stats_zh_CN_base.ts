import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		logincnt: commonLogic.appcommonhandle("登录次数",null),
		openedstorycnt: commonLogic.appcommonhandle("新增需求数",null),
		ztdate: commonLogic.appcommonhandle("日期",null),
		date: commonLogic.appcommonhandle("日志日期",null),
		closedstorycnt: commonLogic.appcommonhandle("关闭需求数",null),
		openedbugcnt: commonLogic.appcommonhandle("新增Bug数",null),
		loghours: commonLogic.appcommonhandle("日志工时",null),
		dynamiccnt: commonLogic.appcommonhandle("动态数",null),
		finishedtaskcnt: commonLogic.appcommonhandle("完成任务数",null),
		resolvedbugcnt: commonLogic.appcommonhandle("解决Bug数",null),
		comment: commonLogic.appcommonhandle("备注",null),
		openedtaskcnt: commonLogic.appcommonhandle("新增任务数",null),
		id: commonLogic.appcommonhandle("标识",null),
	},
		views: {
			companydynamicstatsgridview: {
				caption: commonLogic.appcommonhandle("公司动态统计表",null),
				title: commonLogic.appcommonhandle("公司动态汇总表格视图",null),
			},
		},
		companydynamicstats_grid: {
			columns: {
				ztdate: commonLogic.appcommonhandle("日期",null),
				logincnt: commonLogic.appcommonhandle("登录次数",null),
				loghours: commonLogic.appcommonhandle("日志工时",null),
				openedstorycnt: commonLogic.appcommonhandle("新增需求数",null),
				closedstorycnt: commonLogic.appcommonhandle("关闭需求数",null),
				openedtaskcnt: commonLogic.appcommonhandle("新增任务数",null),
				finishedtaskcnt: commonLogic.appcommonhandle("完成任务数",null),
				openedbugcnt: commonLogic.appcommonhandle("新增Bug数",null),
				resolvedbugcnt: commonLogic.appcommonhandle("解决Bug数",null),
				dynamiccnt: commonLogic.appcommonhandle("动态数",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		companydynamicstatic_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				begin: commonLogic.appcommonhandle("起始日期",null), 
				end: commonLogic.appcommonhandle("结束日期",null), 
			},
			uiactions: {
			},
		},
		companydynamicstatsgridviewtoolbar_toolbar: {
			deuiaction7: {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;