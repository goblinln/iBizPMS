import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'work': commonLogic.appcommonhandle("work",null),
		'tasktype': commonLogic.appcommonhandle("任务类型",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'delaydays': commonLogic.appcommonhandle("延期天数",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'progressrate': commonLogic.appcommonhandle("进度",null),
		'eststarted': commonLogic.appcommonhandle("预计开始",null),
		'deadline': commonLogic.appcommonhandle("截止日期",null),
		'task': commonLogic.appcommonhandle("任务",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'taskname': commonLogic.appcommonhandle("任务名称",null),
	},
		views: {
			'monthlygridview': {
				caption: commonLogic.appcommonhandle("项目汇报用户任务",null),
				title: commonLogic.appcommonhandle("项目汇报用户任务表格视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("项目汇报用户任务",null),
				title: commonLogic.appcommonhandle("项目汇报用户任务表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("项目汇报用户任务",null),
				title: commonLogic.appcommonhandle("项目汇报用户任务编辑视图",null),
			},
			'projectweeklygridview': {
				caption: commonLogic.appcommonhandle("项目汇报用户任务",null),
				title: commonLogic.appcommonhandle("项目汇报用户任务表格视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("项目汇报用户任务基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("编号",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		monthlymain_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'taskname': commonLogic.appcommonhandle("任务名称",null),
				'tasktype': commonLogic.appcommonhandle("任务类型",null),
				'consumed': commonLogic.appcommonhandle("当天消耗",null),
				'eststarted': commonLogic.appcommonhandle("预计开始",null),
				'deadline': commonLogic.appcommonhandle("截止日期",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'progressrate': commonLogic.appcommonhandle("进度",null),
				'delaydays': commonLogic.appcommonhandle("延期天数",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				ibzproprojectusertask_taskdetail: commonLogic.appcommonhandle("任务详情",null),
			},
		},
		main_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'taskname': commonLogic.appcommonhandle("任务名称",null),
				'tasktype': commonLogic.appcommonhandle("任务类型",null),
				'consumed': commonLogic.appcommonhandle("本周消耗",null),
				'eststarted': commonLogic.appcommonhandle("预计开始",null),
				'deadline': commonLogic.appcommonhandle("截止日期",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'progressrate': commonLogic.appcommonhandle("进度",null),
				'delaydays': commonLogic.appcommonhandle("延期天数",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				ibzproprojectusertask_taskdetail: commonLogic.appcommonhandle("任务详情",null),
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