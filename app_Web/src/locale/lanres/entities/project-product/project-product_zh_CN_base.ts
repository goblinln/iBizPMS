import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'id': commonLogic.appcommonhandle("主键",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'productname': commonLogic.appcommonhandle("产品",null),
		'projectname': commonLogic.appcommonhandle("项目",null),
		'planname': commonLogic.appcommonhandle("计划名称",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'plan': commonLogic.appcommonhandle("产品计划",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'project': commonLogic.appcommonhandle("项目",null),
	},
		views: {
			'planlistview9': {
				caption: commonLogic.appcommonhandle("关联计划",null),
				title: commonLogic.appcommonhandle("项目产品列表视图",null),
			},
			'listview9': {
				caption: commonLogic.appcommonhandle("关联产品",null),
				title: commonLogic.appcommonhandle("项目产品列表视图",null),
			},
		},
		plan_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
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