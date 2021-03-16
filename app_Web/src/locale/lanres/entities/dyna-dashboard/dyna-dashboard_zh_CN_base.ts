import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		userid: commonLogic.appcommonhandle("用户标识",null),
		dynadashboardid: commonLogic.appcommonhandle("动态数据看板标识",null),
		dynadashboardname: commonLogic.appcommonhandle("动态数据看板名称",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		modelid: commonLogic.appcommonhandle("模型标识",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		appid: commonLogic.appcommonhandle("应用标识",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		model: commonLogic.appcommonhandle("模型",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;