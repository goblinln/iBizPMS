import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'date': commonLogic.appcommonhandle("日期",null),
		'consumed': commonLogic.appcommonhandle("消耗的工时",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'name': commonLogic.appcommonhandle("项目名称",null),
		'taskcnt': commonLogic.appcommonhandle("任务数",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;