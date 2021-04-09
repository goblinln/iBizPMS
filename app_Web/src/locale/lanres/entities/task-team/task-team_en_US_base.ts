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
	},
		views: {
			'gridview9_look': {
				caption: commonLogic.appcommonhandle("团队",null),
				title: commonLogic.appcommonhandle("团队",null),
			},
		},
		main_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("团队",null),
				'estimate': commonLogic.appcommonhandle("最初预计",null),
				'consumed': commonLogic.appcommonhandle("总计消耗",null),
				'left': commonLogic.appcommonhandle("预计剩余",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;