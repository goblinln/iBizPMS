import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		userroleid: commonLogic.appcommonhandle("The major data entity cannot be found",null),
		roleid: commonLogic.appcommonhandle("角色标识",null),
		rolename: commonLogic.appcommonhandle("角色名称",null),
		userid: commonLogic.appcommonhandle("用户标识",null),
		personname: commonLogic.appcommonhandle("用户名称",null),
		loginname: commonLogic.appcommonhandle("登录名",null),
		orgname: commonLogic.appcommonhandle("单位",null),
		mdeptname: commonLogic.appcommonhandle("主部门",null),
		createdate: commonLogic.appcommonhandle(">",null),
		updatedate: commonLogic.appcommonhandle("Upload",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;