import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		usertag: commonLogic.appcommonhandle("用户标记",null),
		pssystemid: commonLogic.appcommonhandle("系统",null),
		pubfolder: commonLogic.appcommonhandle("服务目录",null),
		pssfstyleverid: commonLogic.appcommonhandle("服务框架扩展",null),
		usertag4: commonLogic.appcommonhandle("用户标记4",null),
		pubtag4: commonLogic.appcommonhandle("发布标记4",null),
		pssfstyleid: commonLogic.appcommonhandle("服务框架",null),
		removeflag: commonLogic.appcommonhandle("删除模式",null),
		subsyspkgflag: commonLogic.appcommonhandle("引用系统组件",null),
		defaultpub: commonLogic.appcommonhandle("默认后台服务",null),
		docpssfstylename: commonLogic.appcommonhandle("文档模板样式",null),
		usertag3: commonLogic.appcommonhandle("用户标记3",null),
		pssfstyleparamname: commonLogic.appcommonhandle("服务框架参数",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		codename: commonLogic.appcommonhandle("代码名称",null),
		usertag2: commonLogic.appcommonhandle("用户标记2",null),
		pssyssfpubname: commonLogic.appcommonhandle("后台服务架构名称",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		pubtag: commonLogic.appcommonhandle("发布标记",null),
		baseclspkgcodename: commonLogic.appcommonhandle("基类代码包名",null),
		verstr: commonLogic.appcommonhandle("版本号",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		pubtag3: commonLogic.appcommonhandle("发布标记3",null),
		pssfstyleparamid: commonLogic.appcommonhandle("服务框架参数",null),
		docpssfstyleid: commonLogic.appcommonhandle("文档模板样式",null),
		memo: commonLogic.appcommonhandle("备注",null),
		pkgcodename: commonLogic.appcommonhandle("代码包名",null),
		pssyssfpubid: commonLogic.appcommonhandle("后台服务架构标识",null),
		pssystemname: commonLogic.appcommonhandle("系统",null),
		contenttype: commonLogic.appcommonhandle("发布内容类型",null),
		usercat: commonLogic.appcommonhandle("用户分类",null),
		pubtag2: commonLogic.appcommonhandle("发布标记2",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		styleparams: commonLogic.appcommonhandle("服务框架参数",null),
		ppssyssfpubname: commonLogic.appcommonhandle("父后台服务体系",null),
		ppssyssfpubid: commonLogic.appcommonhandle("父后台服务体系",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;