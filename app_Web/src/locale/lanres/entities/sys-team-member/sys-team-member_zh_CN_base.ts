import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'teammemberid': commonLogic.appcommonhandle("组成员标识",null),
		'teamname': commonLogic.appcommonhandle("组名称",null),
		'postname': commonLogic.appcommonhandle("岗位",null),
		'domains': commonLogic.appcommonhandle("区属",null),
		'usericon': commonLogic.appcommonhandle("头像",null),
		'personname': commonLogic.appcommonhandle("姓名",null),
		'username': commonLogic.appcommonhandle("账号",null),
		'postid': commonLogic.appcommonhandle("岗位标识",null),
		'teamid': commonLogic.appcommonhandle("组标识",null),
		'userid': commonLogic.appcommonhandle("用户标识",null),
	},
		views: {
			'usr2gridview': {
				caption: commonLogic.appcommonhandle("组成员",null),
				title: commonLogic.appcommonhandle("组成员表格视图",null),
			},
			'gridexpview': {
				caption: commonLogic.appcommonhandle("组成员",null),
				title: commonLogic.appcommonhandle("组成员表格导航视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("组成员",null),
				title: commonLogic.appcommonhandle("组成员表格视图",null),
			},
			'usr2gridviewpostmembers': {
				caption: commonLogic.appcommonhandle("组成员",null),
				title: commonLogic.appcommonhandle("组成员表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("组成员",null),
				title: commonLogic.appcommonhandle("组成员编辑视图",null),
			},
			'gridexpviewteam': {
				caption: commonLogic.appcommonhandle("组成员",null),
				title: commonLogic.appcommonhandle("组成员表格导航视图",null),
			},
		},
		expmainedit_form: {
			details: {
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("组成员标识",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'postid': commonLogic.appcommonhandle("岗位标识",null), 
				'teamid': commonLogic.appcommonhandle("组标识",null), 
				'userid': commonLogic.appcommonhandle("用户标识",null), 
				'personname': commonLogic.appcommonhandle("姓名",null), 
				'username': commonLogic.appcommonhandle("账号",null), 
				'postname': commonLogic.appcommonhandle("岗位",null), 
				'teammemberid': commonLogic.appcommonhandle("组成员标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'teammemberid': commonLogic.appcommonhandle("组成员标识",null),
				'personname': commonLogic.appcommonhandle("姓名",null),
				'userid': commonLogic.appcommonhandle("用户标识",null),
				'teamname': commonLogic.appcommonhandle("组名称",null),
				'teamid': commonLogic.appcommonhandle("组标识",null),
				'postid': commonLogic.appcommonhandle("岗位标识",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		mainexp_grid: {
			columns: {
				'userid': commonLogic.appcommonhandle("用户标识",null),
				'personname': commonLogic.appcommonhandle("姓名",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		usr2gridviewtoolbar_toolbar: {
		},
		usr2gridviewpostmemberstoolbar_toolbar: {
		},
		gridviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("编辑",null),
				tip: commonLogic.appcommonhandle("编辑",null),
			},
			'tbitem6': {
				caption: commonLogic.appcommonhandle("拷贝",null),
				tip: commonLogic.appcommonhandle("拷贝",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem8': {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
			'tbitem9': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem13': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'tbitem10': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem16': {
				caption: commonLogic.appcommonhandle("其它",null),
				tip: commonLogic.appcommonhandle("其它",null),
			},
			'tbitem21': {
				caption: commonLogic.appcommonhandle("导出数据模型",null),
				tip: commonLogic.appcommonhandle("导出数据模型",null),
			},
			'tbitem23': {
				caption: commonLogic.appcommonhandle("数据导入",null),
				tip: commonLogic.appcommonhandle("数据导入",null),
			},
			'tbitem17': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem19': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
			'tbitem18': {
				caption: commonLogic.appcommonhandle("帮助",null),
				tip: commonLogic.appcommonhandle("帮助",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;