import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'userlist': commonLogic.appcommonhandle("userList",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'listname': commonLogic.appcommonhandle("标题",null),
		'id': commonLogic.appcommonhandle("id",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'account': commonLogic.appcommonhandle("account",null),
		'usercontactsn': commonLogic.appcommonhandle("用户联系方式编号",null),
	},
		views: {
			'editview': {
				caption: commonLogic.appcommonhandle("用户联系方式",null),
				title: commonLogic.appcommonhandle("usercontact编辑视图",null),
			},
			'gridexpview': {
				caption: commonLogic.appcommonhandle("用户联系方式",null),
				title: commonLogic.appcommonhandle("用户联系方式表格导航视图",null),
			},
			'usr2gridview': {
				caption: commonLogic.appcommonhandle("用户联系方式",null),
				title: commonLogic.appcommonhandle("用户联系方式表格视图",null),
			},
			'treeexpview': {
				caption: commonLogic.appcommonhandle("用户联系方式",null),
				title: commonLogic.appcommonhandle("用户联系方式树导航视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("用户联系方式",null),
				title: commonLogic.appcommonhandle("usercontact表格视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("usercontact基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'listname': commonLogic.appcommonhandle("列表名称",null), 
				'userlist': commonLogic.appcommonhandle("选择用户",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		mainexp_grid: {
			columns: {
				'usercontactsn': commonLogic.appcommonhandle("编号",null),
				'listname': commonLogic.appcommonhandle("标题",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'usercontactsn': commonLogic.appcommonhandle("编号",null),
				'id': commonLogic.appcommonhandle("编号",null),
				'listname': commonLogic.appcommonhandle("标题",null),
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
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("Remove",null),
				tip: commonLogic.appcommonhandle("Remove {0}",null),
			},
			'seperator1': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'seperator3': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction5': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'seperator4': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
		concatlist_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;