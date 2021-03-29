import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'spec': commonLogic.appcommonhandle("需求描述	",null),
		'verify': commonLogic.appcommonhandle("验收标准",null),
		'id': commonLogic.appcommonhandle("虚拟主键",null),
		'title': commonLogic.appcommonhandle("需求名称",null),
		'version': commonLogic.appcommonhandle("版本号",null),
		'story': commonLogic.appcommonhandle("需求",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;