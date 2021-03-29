import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'monthname': commonLogic.appcommonhandle("月（显示）",null),
		'year': commonLogic.appcommonhandle("年",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'evaluationcost': commonLogic.appcommonhandle("评估成本",null),
		'monthorder': commonLogic.appcommonhandle("月（排序）",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'evaluationstatus': commonLogic.appcommonhandle("评估状态",null),
		'yearname': commonLogic.appcommonhandle("年（显示）",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'evaluationtime': commonLogic.appcommonhandle("评估工时",null),
		'inputcost': commonLogic.appcommonhandle("投入成本",null),
		'dates': commonLogic.appcommonhandle("日期",null),
		'month': commonLogic.appcommonhandle("月",null),
		'work': commonLogic.appcommonhandle("work",null),
		'evaluationdesc': commonLogic.appcommonhandle("评估说明",null),
		'taskspecies': commonLogic.appcommonhandle("任务种别",null),
		'taskname': commonLogic.appcommonhandle("任务名称",null),
		'projectname': commonLogic.appcommonhandle("所属项目",null),
		'type': commonLogic.appcommonhandle("任务类型",null),
		'deleted': commonLogic.appcommonhandle("任务删除标识",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'task': commonLogic.appcommonhandle("任务",null),
	},
		views: {
			'lookgridview9': {
				caption: commonLogic.appcommonhandle("工时",null),
				title: commonLogic.appcommonhandle("任务预计表格视图",null),
			},
			'editgridview9': {
				caption: commonLogic.appcommonhandle("工时",null),
				title: commonLogic.appcommonhandle("任务预计表格视图",null),
			},
			'optionview': {
				caption: commonLogic.appcommonhandle("任务预计",null),
				title: commonLogic.appcommonhandle("任务预计选项操作视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("taskestimate基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("编号",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'date': commonLogic.appcommonhandle("日期",null), 
				'consumed': commonLogic.appcommonhandle("工时",null), 
				'left': commonLogic.appcommonhandle("剩余",null), 
				'work': commonLogic.appcommonhandle("备注",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		mainlook_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("ID",null),
				'dates': commonLogic.appcommonhandle("日期",null),
				'consumed': commonLogic.appcommonhandle("总计消耗",null),
				'left': commonLogic.appcommonhandle("预计剩余",null),
				'work': commonLogic.appcommonhandle("备注",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		mainedit_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("ID",null),
				'dates': commonLogic.appcommonhandle("日期",null),
				'consumed': commonLogic.appcommonhandle("总计消耗",null),
				'left': commonLogic.appcommonhandle("预计剩余",null),
				'work': commonLogic.appcommonhandle("备注",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			taskestimate_edit: commonLogic.appcommonhandle("编辑",null),
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;