import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		desc: commonLogic.appcommonhandle("描述",null),
		product: commonLogic.appcommonhandle("产品",null),
		addeddate: commonLogic.appcommonhandle("创建时间",null),
		id: commonLogic.appcommonhandle("编号",null),
		lastediteddate: commonLogic.appcommonhandle("最后编辑时间",null),
		addedby: commonLogic.appcommonhandle("由谁创建",null),
		deleted: commonLogic.appcommonhandle("已删除",null),
		name: commonLogic.appcommonhandle("名称",null),
		type: commonLogic.appcommonhandle("类型",null),
		lasteditedby: commonLogic.appcommonhandle("最后编辑人",null),
	},
		views: {
			tabexpview: {
				caption: commonLogic.appcommonhandle("用例库",null),
				title: commonLogic.appcommonhandle("用例库",null),
			},
			editview9: {
				caption: commonLogic.appcommonhandle("用例库",null),
				title: commonLogic.appcommonhandle("用例库编辑视图",null),
			},
			openlibview: {
				caption: commonLogic.appcommonhandle("用例库",null),
				title: commonLogic.appcommonhandle("用例库数据看板视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("用例库",null),
				title: commonLogic.appcommonhandle("用例库",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("用例库",null),
				title: commonLogic.appcommonhandle("用例库编辑视图",null),
			},
		},
		desc_form: {
			details: {
				druipart1: commonLogic.appcommonhandle("",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("最后编辑时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("编号",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				id: commonLogic.appcommonhandle("",null), 
				name: commonLogic.appcommonhandle("",null), 
				desc: commonLogic.appcommonhandle("描述",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("最后编辑时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("编号",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				name: commonLogic.appcommonhandle("名称",null), 
				type: commonLogic.appcommonhandle("类型",null), 
				desc: commonLogic.appcommonhandle("描述",null), 
				id: commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				id: commonLogic.appcommonhandle("编号",null),
				name: commonLogic.appcommonhandle("名称",null),
				uagridcolumn1: commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			ibzlib_editlib: commonLogic.appcommonhandle("编辑",null),
			ibzlib_delete: commonLogic.appcommonhandle("删除",null),
			},
		},
		editviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		gridviewtoolbar_toolbar: {
			deuiaction3_create: {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			deuiaction4: {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;