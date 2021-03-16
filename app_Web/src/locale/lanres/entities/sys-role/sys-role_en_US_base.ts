import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		roleid: commonLogic.appcommonhandle("Normal",null),
		rolename: commonLogic.appcommonhandle("Save",null),
		memo: commonLogic.appcommonhandle("备注",null),
		proleid: commonLogic.appcommonhandle("父角色标识",null),
		prolename: commonLogic.appcommonhandle("父角色名称",null),
		createdate: commonLogic.appcommonhandle(">",null),
		updatedate: commonLogic.appcommonhandle("Upload",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;