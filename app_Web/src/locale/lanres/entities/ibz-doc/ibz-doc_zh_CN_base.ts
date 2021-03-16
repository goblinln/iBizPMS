import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		ibzdocid: commonLogic.appcommonhandle("文档标识",null),
		addedby: commonLogic.appcommonhandle("由谁添加",null),
		editedby: commonLogic.appcommonhandle("由谁更新",null),
		addeddate: commonLogic.appcommonhandle("添加时间",null),
		size: commonLogic.appcommonhandle("大小",null),
		lib: commonLogic.appcommonhandle("所属文档库",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		ibzdocname: commonLogic.appcommonhandle("文档名称",null),
		order: commonLogic.appcommonhandle("排序",null),
		iscollect: commonLogic.appcommonhandle("是否已收藏",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		editeddate: commonLogic.appcommonhandle("更新时间",null),
		objecttype: commonLogic.appcommonhandle("对象类型",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;