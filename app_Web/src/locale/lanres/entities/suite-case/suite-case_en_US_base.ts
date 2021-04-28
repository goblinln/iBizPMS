import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'version': commonLogic.appcommonhandle("用例版本",null),
		'suite': commonLogic.appcommonhandle("测试套件",null),
		'ibizcase': commonLogic.appcommonhandle("用例",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'id': commonLogic.appcommonhandle("主键",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;