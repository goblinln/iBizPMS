import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		userroleid: commonLogic.appcommonhandle("用户角色关系标识",null),
		roleid: commonLogic.appcommonhandle("角色标识",null),
		rolename: commonLogic.appcommonhandle("角色名称",null),
		userid: commonLogic.appcommonhandle("用户标识",null),
		personname: commonLogic.appcommonhandle("用户名称",null),
		loginname: commonLogic.appcommonhandle("登录名",null),
		orgname: commonLogic.appcommonhandle("单位",null),
		mdeptname: commonLogic.appcommonhandle("主部门",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;