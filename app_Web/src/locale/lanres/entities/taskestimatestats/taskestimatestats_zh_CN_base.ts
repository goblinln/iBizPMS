import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		date: commonLogic.appcommonhandle("日期",null),
		year: commonLogic.appcommonhandle("年",null),
		consumed: commonLogic.appcommonhandle("消耗的工时",null),
		evaluationstatus: commonLogic.appcommonhandle("评估状态",null),
		account: commonLogic.appcommonhandle("用户",null),
		id: commonLogic.appcommonhandle("编号",null),
		monthname: commonLogic.appcommonhandle("月（显示）",null),
		name: commonLogic.appcommonhandle("项目名称",null),
		yearname: commonLogic.appcommonhandle("年（显示）",null),
		evaluationtime: commonLogic.appcommonhandle("评估工时",null),
		evaluationcost: commonLogic.appcommonhandle("评估成本",null),
		inputcost: commonLogic.appcommonhandle("投入成本",null),
		month: commonLogic.appcommonhandle("月",null),
		taskcnt: commonLogic.appcommonhandle("任务数",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;