import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'isweekend': commonLogic.appcommonhandle("周末",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'id': commonLogic.appcommonhandle("虚拟主键",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'estimate': commonLogic.appcommonhandle("最初预计",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'task': commonLogic.appcommonhandle("任务",null),
	},
		views: {
			'chartview': {
				caption: commonLogic.appcommonhandle("燃尽图",null),
				title: commonLogic.appcommonhandle("燃尽图",null),
			},
		},
		burndown_chart: {
			nodata:commonLogic.appcommonhandle("",null),
		},
		burndown2_chart: {
			nodata:commonLogic.appcommonhandle("",null),
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
				'isweekend': commonLogic.appcommonhandle("间隔",null), 
			},
			uiactions: {
			},
		},
		chartviewtoolbar_toolbar: {
			'deuiaction1_computeburn': {
				caption: commonLogic.appcommonhandle("更新燃尽图",null),
				tip: commonLogic.appcommonhandle("更新燃尽图",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;