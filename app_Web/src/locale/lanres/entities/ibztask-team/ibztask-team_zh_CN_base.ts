import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'estimate': commonLogic.appcommonhandle("最初预计",null),
		'username': commonLogic.appcommonhandle("用户",null),
		'join': commonLogic.appcommonhandle("加盟日",null),
		'hours': commonLogic.appcommonhandle("可用工时/天",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'limited': commonLogic.appcommonhandle("受限用户",null),
		'role': commonLogic.appcommonhandle("角色",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'days': commonLogic.appcommonhandle("可用工日",null),
		'total': commonLogic.appcommonhandle("总计可用",null),
		'type': commonLogic.appcommonhandle("团队类型",null),
		'root': commonLogic.appcommonhandle("编号",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
	},
		views: {
			'gridview9_edit': {
				caption: commonLogic.appcommonhandle("团队",null),
				title: commonLogic.appcommonhandle("团队",null),
			},
			'gridview9': {
				caption: commonLogic.appcommonhandle("团队",null),
				title: commonLogic.appcommonhandle("团队",null),
			},
		},
		mainedit_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'estimate': commonLogic.appcommonhandle("预计",null),
				'consumed': commonLogic.appcommonhandle("总计消耗",null),
				'left': commonLogic.appcommonhandle("预计剩余",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		maineditrow_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'estimate': commonLogic.appcommonhandle("预计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		gridview9_edittoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
		},
		gridview9toolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;