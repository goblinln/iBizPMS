import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'tabspace2': commonLogic.appcommonhandle("表空间2名称",null),
		'resstate': commonLogic.appcommonhandle("资源状态",null),
		'nullvalorder': commonLogic.appcommonhandle("空值排序",null),
		'usertag3': commonLogic.appcommonhandle("用户标记3",null),
		'tabspace': commonLogic.appcommonhandle("默认表空间名称",null),
		'defaultflag': commonLogic.appcommonhandle("默认数据源",null),
		'memo': commonLogic.appcommonhandle("备注",null),
		'nodbinstmode': commonLogic.appcommonhandle("无数据库模式",null),
		'usertag4': commonLogic.appcommonhandle("用户标记4",null),
		'pssystemdbcfgname': commonLogic.appcommonhandle("系统数据库名称",null),
		'pssystemname': commonLogic.appcommonhandle("系统",null),
		'resinfo': commonLogic.appcommonhandle("资源信息",null),
		'usertag': commonLogic.appcommonhandle("用户标记",null),
		'userparams': commonLogic.appcommonhandle("自定义参数",null),
		'resreadytime': commonLogic.appcommonhandle("资源就绪时间",null),
		'pubfkeyflag': commonLogic.appcommonhandle("发布外键",null),
		'pssystemdbcfgid': commonLogic.appcommonhandle("系统数据库标识",null),
		'usertag2': commonLogic.appcommonhandle("用户标记2",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'pubcommentflag': commonLogic.appcommonhandle("发布模型注释",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'tabspace3': commonLogic.appcommonhandle("表空间3名称",null),
		'enablewebtool': commonLogic.appcommonhandle("支持Web管理",null),
		'pubdbmodelflag': commonLogic.appcommonhandle("发布数据库模型",null),
		'objnamecase': commonLogic.appcommonhandle("对象名称转换",null),
		'pubindexflag': commonLogic.appcommonhandle("发布索引",null),
		'appendschema': commonLogic.appcommonhandle("附加模式名称",null),
		'pssystemid': commonLogic.appcommonhandle("系统",null),
		'dbschemaname': commonLogic.appcommonhandle("数据库模式名称",null),
		'tabspace4': commonLogic.appcommonhandle("表空间4名称",null),
		'usercat': commonLogic.appcommonhandle("用户分类",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'pubviewflag': commonLogic.appcommonhandle("发布视图",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;