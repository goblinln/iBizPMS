import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'spec': commonLogic.appcommonhandle("需求描述	",null),
		'title': commonLogic.appcommonhandle("需求名称",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'verify': commonLogic.appcommonhandle("验收标准",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'id': commonLogic.appcommonhandle("主键",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'version': commonLogic.appcommonhandle("版本号",null),
		'story': commonLogic.appcommonhandle("需求",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;