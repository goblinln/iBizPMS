import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'id': commonLogic.appcommonhandle("虚拟主键",null),
		'version': commonLogic.appcommonhandle("用例版本",null),
		'suite': commonLogic.appcommonhandle("测试套件",null),
		'ibizcase': commonLogic.appcommonhandle("用例",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;