import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'diff': commonLogic.appcommonhandle("不同",null),
		'field': commonLogic.appcommonhandle("字段",null),
		'ibiznew': commonLogic.appcommonhandle("新值",null),
		'old': commonLogic.appcommonhandle("旧值",null),
		'id': commonLogic.appcommonhandle("id",null),
		'action': commonLogic.appcommonhandle("关联日志",null),
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