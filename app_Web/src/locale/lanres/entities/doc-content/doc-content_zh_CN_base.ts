import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'files': commonLogic.appcommonhandle("附件",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'content': commonLogic.appcommonhandle("文档正文",null),
		'type': commonLogic.appcommonhandle("文档类型",null),
		'title': commonLogic.appcommonhandle("文档标题",null),
		'version': commonLogic.appcommonhandle("版本号",null),
		'digest': commonLogic.appcommonhandle("文档摘要",null),
		'doc': commonLogic.appcommonhandle("文档",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;