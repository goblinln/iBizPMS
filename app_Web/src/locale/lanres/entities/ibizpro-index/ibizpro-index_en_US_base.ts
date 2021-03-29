import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'indexid': commonLogic.appcommonhandle("主键",null),
		'acl': commonLogic.appcommonhandle("权限",null),
		'docid': commonLogic.appcommonhandle("docid",null),
		'indexname': commonLogic.appcommonhandle("标题[需求、任务等]",null),
		'color': commonLogic.appcommonhandle("颜色",null),
		'deleted': commonLogic.appcommonhandle("逻辑标识",null),
		'acllist': commonLogic.appcommonhandle("权限列表",null),
		'indexdesc': commonLogic.appcommonhandle("内容[需求、任务等]",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'indextype': commonLogic.appcommonhandle("类型",null),
		'mdeptid': commonLogic.appcommonhandle("部门标识",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'orgid': commonLogic.appcommonhandle("组织标识",null),
	},
		views: {
			'listview': {
				caption: commonLogic.appcommonhandle("索引检索",null),
				title: commonLogic.appcommonhandle("索引检索列表视图",null),
			},
			'indexpickupview': {
				caption: commonLogic.appcommonhandle("索引检索",null),
				title: commonLogic.appcommonhandle("索引检索数据选择视图",null),
			},
			'redirectview': {
				caption: commonLogic.appcommonhandle("索引检索",null),
				title: commonLogic.appcommonhandle("索引检索数据重定向视图",null),
			},
			'indexpickupdataview': {
				caption: commonLogic.appcommonhandle("索引检索",null),
				title: commonLogic.appcommonhandle("索引检索索引关系选择数据视图",null),
			},
		},
		indexmob_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		indextype_dataview: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;