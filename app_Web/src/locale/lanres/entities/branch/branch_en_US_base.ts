import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'name': commonLogic.appcommonhandle("名称",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'branchsn': commonLogic.appcommonhandle("产品的分支和平台信息编号",null),
	},
		views: {
			'pmgridview': {
				caption: commonLogic.appcommonhandle("平台管理",null),
				title: commonLogic.appcommonhandle("平台管理",null),
			},
			'pminfoeditview': {
				caption: commonLogic.appcommonhandle("产品的分支和平台信息",null),
				title: commonLogic.appcommonhandle("平台管理",null),
			},
			'pickupview': {
				caption: commonLogic.appcommonhandle("产品的分支和平台信息",null),
				title: commonLogic.appcommonhandle("平台数据选择视图",null),
			},
			'pmeditview': {
				caption: commonLogic.appcommonhandle("产品的分支和平台信息",null),
				title: commonLogic.appcommonhandle("平台管理",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("产品的分支和平台信息",null),
				title: commonLogic.appcommonhandle("平台选择表格视图",null),
			},
		},
		platformmanagement_form: {
			details: {
				'group1': commonLogic.appcommonhandle("产品的分支和平台信息基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("名称",null), 
				'order': commonLogic.appcommonhandle("排序",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		platformmanagement_grid: {
			columns: {
				'branchsn': commonLogic.appcommonhandle("编号",null),
				'name': commonLogic.appcommonhandle("名称",null),
				'order': commonLogic.appcommonhandle("排序",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'branchsn': commonLogic.appcommonhandle("编号",null),
				'name': commonLogic.appcommonhandle("名称",null),
				'order': commonLogic.appcommonhandle("排序",null),
				'id': commonLogic.appcommonhandle("编号",null),
		},
			uiactions: {
			branch_edit: commonLogic.appcommonhandle("编辑",null),
			branch_delete: commonLogic.appcommonhandle("删除",null),
			},
		},
		main_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("名称",null),
			},
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
		pmeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		pmgridviewtoolbar_toolbar: {
			'deuiaction3_create': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		pminfoeditviewtoolbar_toolbar: {
		},
	};
	return data;
}

export default getLocaleResourceBase;