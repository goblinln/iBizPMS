import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		roleid: commonLogic.appcommonhandle("角色标识",null),
		rolename: commonLogic.appcommonhandle("角色名称",null),
		memo: commonLogic.appcommonhandle("备注",null),
		proleid: commonLogic.appcommonhandle("父角色标识",null),
		prolename: commonLogic.appcommonhandle("父角色名称",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;