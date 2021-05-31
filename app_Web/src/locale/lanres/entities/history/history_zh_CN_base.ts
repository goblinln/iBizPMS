import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'diff': commonLogic.appcommonhandle("不同",null),
		'field': commonLogic.appcommonhandle("字段",null),
		'ibiznew': commonLogic.appcommonhandle("新值",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'old': commonLogic.appcommonhandle("旧值",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'id': commonLogic.appcommonhandle("id",null),
		'action': commonLogic.appcommonhandle("关联日志",null),
		'historysn': commonLogic.appcommonhandle("操作历史编号",null),
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