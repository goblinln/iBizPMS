import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'title': commonLogic.appcommonhandle("模板标题",null),
		'id': commonLogic.appcommonhandle("id",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'content': commonLogic.appcommonhandle("content",null),
		'type': commonLogic.appcommonhandle("type",null),
		'account': commonLogic.appcommonhandle("account",null),
		'ibizpublic': commonLogic.appcommonhandle("公开",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("用户模板",null),
				title: commonLogic.appcommonhandle("usertpl表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("用户模板",null),
				title: commonLogic.appcommonhandle("usertpl编辑视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("usertpl基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("模板标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'title': commonLogic.appcommonhandle("模板标题",null), 
				'type': commonLogic.appcommonhandle("类型",null), 
				'account': commonLogic.appcommonhandle("所属用户",null), 
				'content': commonLogic.appcommonhandle("模板",null), 
				'public': commonLogic.appcommonhandle("公开",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'title': commonLogic.appcommonhandle("模板标题",null),
				'type': commonLogic.appcommonhandle("类型",null),
				'public': commonLogic.appcommonhandle("公开",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			remove: commonLogic.appcommonhandle("Remove",null),
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;