import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'org': commonLogic.appcommonhandle("归属组织",null),
		'monthname': commonLogic.appcommonhandle("月（显示）",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'year': commonLogic.appcommonhandle("年",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'evaluationcost': commonLogic.appcommonhandle("评估成本",null),
		'monthorder': commonLogic.appcommonhandle("月（排序）",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'evaluationstatus': commonLogic.appcommonhandle("评估状态",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'yearname': commonLogic.appcommonhandle("年（显示）",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'evaluationtime': commonLogic.appcommonhandle("评估工时",null),
		'inputcost': commonLogic.appcommonhandle("投入成本",null),
		'dates': commonLogic.appcommonhandle("日期",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
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
			'gridview9': {
				caption: commonLogic.appcommonhandle("工时",null),
				title: commonLogic.appcommonhandle("任务预计表格视图",null),
			},
		},
		main_grid: {
			columns: {
				'dates': commonLogic.appcommonhandle("日期",null),
				'consumed': commonLogic.appcommonhandle("总计消耗",null),
				'left': commonLogic.appcommonhandle("预计剩余",null),
				'work': commonLogic.appcommonhandle("备注",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;