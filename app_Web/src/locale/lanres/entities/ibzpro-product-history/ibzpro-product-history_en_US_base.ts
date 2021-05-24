import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'id': commonLogic.appcommonhandle("id",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'diff': commonLogic.appcommonhandle("不同",null),
		'field': commonLogic.appcommonhandle("字段",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'ibiznew': commonLogic.appcommonhandle("新值",null),
		'historysn': commonLogic.appcommonhandle("操作历史编号",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'old': commonLogic.appcommonhandle("旧值",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'action': commonLogic.appcommonhandle("id",null),
	},
		default_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;