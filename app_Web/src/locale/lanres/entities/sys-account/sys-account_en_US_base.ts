import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'userid': commonLogic.appcommonhandle("用户标识",null),
		'username': commonLogic.appcommonhandle("用户全局名",null),
		'personname': commonLogic.appcommonhandle("用户姓名",null),
		'usercode': commonLogic.appcommonhandle("用户工号",null),
		'password': commonLogic.appcommonhandle("密码",null),
		'loginname': commonLogic.appcommonhandle("登录名",null),
		'domains': commonLogic.appcommonhandle("区属",null),
		'mdeptid': commonLogic.appcommonhandle("主部门",null),
		'mdeptcode': commonLogic.appcommonhandle("主部门代码",null),
		'mdeptname': commonLogic.appcommonhandle("主部门名称",null),
		'bcode': commonLogic.appcommonhandle("业务编码",null),
		'postid': commonLogic.appcommonhandle("岗位标识",null),
		'postcode': commonLogic.appcommonhandle("岗位代码",null),
		'postname': commonLogic.appcommonhandle("岗位名称",null),
		'orgid': commonLogic.appcommonhandle("单位",null),
		'orgcode': commonLogic.appcommonhandle("单位代码",null),
		'orgname': commonLogic.appcommonhandle("单位名称",null),
		'nickname': commonLogic.appcommonhandle("昵称别名",null),
		'sex': commonLogic.appcommonhandle("性别",null),
		'birthday': commonLogic.appcommonhandle("出生日期",null),
		'certcode': commonLogic.appcommonhandle("证件号码",null),
		'phone': commonLogic.appcommonhandle("联系方式",null),
		'email': commonLogic.appcommonhandle("邮件",null),
		'avatar': commonLogic.appcommonhandle("社交账号",null),
		'addr': commonLogic.appcommonhandle("地址",null),
		'usericon': commonLogic.appcommonhandle("照片",null),
		'theme': commonLogic.appcommonhandle("样式",null),
		'lang': commonLogic.appcommonhandle("语言",null),
		'fontsize': commonLogic.appcommonhandle("字号",null),
		'memo': commonLogic.appcommonhandle("备注",null),
		'reserver': commonLogic.appcommonhandle("保留",null),
		'superuser': commonLogic.appcommonhandle("超级管理员",null),
		'originalpassword': commonLogic.appcommonhandle("原密码",null),
		'newpassword': commonLogic.appcommonhandle("新密码",null),
		'repeatpassword': commonLogic.appcommonhandle("重复密码",null),
		'eprojects': commonLogic.appcommonhandle("过期项目数",null),
		'projectteamcnt': commonLogic.appcommonhandle("项目成员",null),
		'mytodocnt': commonLogic.appcommonhandle("我的待办数",null),
		'myfavoritebugs': commonLogic.appcommonhandle("我收藏的bugs",null),
		'mybugs': commonLogic.appcommonhandle("我的bugs",null),
		'leftlcbcnt': commonLogic.appcommonhandle("剩余里程碑",null),
		'mytasks': commonLogic.appcommonhandle("我的任务",null),
		'myebugs': commonLogic.appcommonhandle("我的过期bug数",null),
		'myterritorycnt': commonLogic.appcommonhandle("我的地盘",null),
		'myfavorites': commonLogic.appcommonhandle("我的收藏",null),
		'mystorys': commonLogic.appcommonhandle("我的需求数",null),
		'mytodocntjz': commonLogic.appcommonhandle("今日截止待办",null),
		'leftlcbjzcnt': commonLogic.appcommonhandle("剩余里程碑（今日到期）",null),
		'myetasks': commonLogic.appcommonhandle("我的过期任务数",null),
		'myfavoritestorys': commonLogic.appcommonhandle("我收藏的需求数",null),
		'myfavoritetasks': commonLogic.appcommonhandle("我收藏的任务",null),
		'products': commonLogic.appcommonhandle("未关闭产品数",null),
		'projects': commonLogic.appcommonhandle("未关闭项目数",null),
	},
		views: {
			'changepasswordeditview': {
				caption: commonLogic.appcommonhandle("修改密码",null),
				title: commonLogic.appcommonhandle("系统用户编辑视图",null),
			},
			'calendarview': {
				caption: commonLogic.appcommonhandle("系统用户",null),
				title: commonLogic.appcommonhandle("我的地盘日历视图",null),
			},
			'listview': {
				caption: commonLogic.appcommonhandle("欢迎",null),
				title: commonLogic.appcommonhandle("欢迎",null),
			},
			'projectreporttabexpview': {
				caption: commonLogic.appcommonhandle("项目",null),
				title: commonLogic.appcommonhandle("我的地盘分页导航视图",null),
			},
			'calendarexpview': {
				caption: commonLogic.appcommonhandle("系统用户",null),
				title: commonLogic.appcommonhandle("我的地盘日历导航视图",null),
			},
			'mywork': {
				caption: commonLogic.appcommonhandle("我的工作",null),
				title: commonLogic.appcommonhandle("我的工作",null),
			},
			'myfavoritestabexpview': {
				caption: commonLogic.appcommonhandle("我的收藏",null),
				title: commonLogic.appcommonhandle("我的收藏",null),
			},
			'listview9': {
				caption: commonLogic.appcommonhandle("我的工作",null),
				title: commonLogic.appcommonhandle("我的工作",null),
			},
			'listview9personinfo': {
				caption: commonLogic.appcommonhandle("我的工作",null),
				title: commonLogic.appcommonhandle("我的工作",null),
			},
			'tabexpview': {
				caption: commonLogic.appcommonhandle("我的地盘",null),
				title: commonLogic.appcommonhandle("我的地盘",null),
			},
		},
		changepassword_form: {
			details: {
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("用户标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("用户姓名",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'username': commonLogic.appcommonhandle("用户名",null), 
				'originalpassword': commonLogic.appcommonhandle("原密码",null), 
				'newpassword': commonLogic.appcommonhandle("新密码",null), 
				'repeatpassword': commonLogic.appcommonhandle("重复密码",null), 
				'userid': commonLogic.appcommonhandle("用户标识",null), 
			},
			uiactions: {
			},
		},
		mywork_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		personinfomywork_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		welcome_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		changepasswordeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		calendarmywork_calendar: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;