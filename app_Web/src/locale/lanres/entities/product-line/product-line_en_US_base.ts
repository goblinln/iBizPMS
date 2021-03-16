import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		productlinename: commonLogic.appcommonhandle("产品线名称",null),
		productlineid: commonLogic.appcommonhandle("产品线标识",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		createman: commonLogic.appcommonhandle("建立人",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;