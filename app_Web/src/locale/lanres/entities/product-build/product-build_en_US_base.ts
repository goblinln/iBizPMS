import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'ids': commonLogic.appcommonhandle("Bug版本健值",null),
		'name': commonLogic.appcommonhandle("名称编号",null),
		'backgroundid': commonLogic.appcommonhandle("后台体系",null),
		'builder': commonLogic.appcommonhandle("构建者",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'releasetype': commonLogic.appcommonhandle("运行模式",null),
		'builderpk': commonLogic.appcommonhandle("构建者（选择）",null),
		'rebuild': commonLogic.appcommonhandle("重新构建",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'desc': commonLogic.appcommonhandle("描述",null),
		'id': commonLogic.appcommonhandle("id",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'sqlid': commonLogic.appcommonhandle("运行数据库",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'scmpath': commonLogic.appcommonhandle("源代码地址",null),
		'filepath': commonLogic.appcommonhandle("下载地址",null),
		'createbugcnt': commonLogic.appcommonhandle("产生的bug",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'stories': commonLogic.appcommonhandle("完成的需求",null),
		'bugs': commonLogic.appcommonhandle("解决的Bug",null),
		'frontapplication': commonLogic.appcommonhandle("系统应用",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'date': commonLogic.appcommonhandle("打包日期",null),
		'productname': commonLogic.appcommonhandle("产品名称",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'buildsn': commonLogic.appcommonhandle("版本编号",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;