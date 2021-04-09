import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'admins': commonLogic.appcommonhandle("admins",null),
		'fax': commonLogic.appcommonhandle("传真",null),
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'website': commonLogic.appcommonhandle("官网",null),
		'id': commonLogic.appcommonhandle("id",null),
		'zipcode': commonLogic.appcommonhandle("邮政编码",null),
		'address': commonLogic.appcommonhandle("通讯地址",null),
		'backyard': commonLogic.appcommonhandle("内网",null),
		'name': commonLogic.appcommonhandle("公司名称",null),
		'guest': commonLogic.appcommonhandle("匿名登陆",null),
		'phone': commonLogic.appcommonhandle("联系电话",null),
	},
		views: {
			'mainview9': {
				caption: commonLogic.appcommonhandle("公司",null),
				title: commonLogic.appcommonhandle("公司编辑视图",null),
			},
			'deptusertreeexpview': {
				caption: commonLogic.appcommonhandle("公司",null),
				title: commonLogic.appcommonhandle("组织部门人员树导航视图",null),
			},
			'depttreeexpview': {
				caption: commonLogic.appcommonhandle("公司",null),
				title: commonLogic.appcommonhandle("组织部门树导航视图",null),
			},
			'mainview': {
				caption: commonLogic.appcommonhandle("公司信息",null),
				title: commonLogic.appcommonhandle("公司数据看板视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("公司信息",null),
				title: commonLogic.appcommonhandle("公司编辑视图",null),
			},
			'maintabexpview': {
				caption: commonLogic.appcommonhandle("组织权限",null),
				title: commonLogic.appcommonhandle("组织权限",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("company基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("公司名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("公司名称",null), 
				'phone': commonLogic.appcommonhandle("联系电话",null), 
				'fax': commonLogic.appcommonhandle("传真",null), 
				'address': commonLogic.appcommonhandle("通讯地址",null), 
				'zipcode': commonLogic.appcommonhandle("邮政编码",null), 
				'website': commonLogic.appcommonhandle("官网",null), 
				'backyard': commonLogic.appcommonhandle("内网",null), 
				'guest': commonLogic.appcommonhandle("匿名登陆",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_edit_form: {
			details: {
				'group1': commonLogic.appcommonhandle("company基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("公司名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("公司名称",null), 
				'phone': commonLogic.appcommonhandle("联系电话",null), 
				'fax': commonLogic.appcommonhandle("传真",null), 
				'address': commonLogic.appcommonhandle("通讯地址",null), 
				'zipcode': commonLogic.appcommonhandle("邮政编码",null), 
				'website': commonLogic.appcommonhandle("官网",null), 
				'backyard': commonLogic.appcommonhandle("内网",null), 
				'guest': commonLogic.appcommonhandle("匿名登陆",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		deptuserexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'alldept': commonLogic.appcommonhandle("所有部门",null),
			},
			uiactions: {
			},
		},
		deptexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'alldept': commonLogic.appcommonhandle("所有部门",null),
			},
			uiactions: {
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;