import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'orgid': commonLogic.appcommonhandle("组织机构标识",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'id': commonLogic.appcommonhandle("id",null),
		'ibizshort': commonLogic.appcommonhandle("简称",null),
		'mdeptid': commonLogic.appcommonhandle("部门标识",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'type': commonLogic.appcommonhandle("类型",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'mdeptname': commonLogic.appcommonhandle("归属部门名",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'name': commonLogic.appcommonhandle("产品线名称",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("产品线",null),
				title: commonLogic.appcommonhandle("生产线表格视图",null),
			},
		},
		main_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("名称",null),
				'short': commonLogic.appcommonhandle("简称",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存行",null),
				tip: commonLogic.appcommonhandle("保存行",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;