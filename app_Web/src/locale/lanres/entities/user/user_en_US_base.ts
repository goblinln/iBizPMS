import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'password': commonLogic.appcommonhandle("密码",null),
		'address': commonLogic.appcommonhandle("通讯地址",null),
		'weixin': commonLogic.appcommonhandle("微信",null),
		'dingding': commonLogic.appcommonhandle("钉钉",null),
		'fails': commonLogic.appcommonhandle("fails",null),
		'slack': commonLogic.appcommonhandle("slack",null),
		'ranzhi': commonLogic.appcommonhandle("ranzhi",null),
		'account': commonLogic.appcommonhandle("账户",null),
		'locked': commonLogic.appcommonhandle("locked",null),
		'avatar': commonLogic.appcommonhandle("avatar",null),
		'scorelevel': commonLogic.appcommonhandle("scoreLevel",null),
		'realname': commonLogic.appcommonhandle("真实姓名",null),
		'zipcode': commonLogic.appcommonhandle("zipcode",null),
		'dept': commonLogic.appcommonhandle("所属部门",null),
		'commiter': commonLogic.appcommonhandle("源代码账户",null),
		'role': commonLogic.appcommonhandle("职位",null),
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'last': commonLogic.appcommonhandle("最后登录",null),
		'clientstatus': commonLogic.appcommonhandle("clientStatus",null),
		'skype': commonLogic.appcommonhandle("skype",null),
		'whatsapp': commonLogic.appcommonhandle("whatsapp",null),
		'score': commonLogic.appcommonhandle("score",null),
		'gender': commonLogic.appcommonhandle("性别",null),
		'mobile': commonLogic.appcommonhandle("手机",null),
		'clientlang': commonLogic.appcommonhandle("clientLang",null),
		'visits': commonLogic.appcommonhandle("访问次数",null),
		'join': commonLogic.appcommonhandle("入职日期",null),
		'email': commonLogic.appcommonhandle("邮箱",null),
		'ip': commonLogic.appcommonhandle("ip",null),
		'birthday': commonLogic.appcommonhandle("birthday",null),
		'nickname': commonLogic.appcommonhandle("nickname",null),
		'phone': commonLogic.appcommonhandle("电话",null),
		'id': commonLogic.appcommonhandle("ID",null),
		'qq': commonLogic.appcommonhandle("QQ",null),
		'usersn': commonLogic.appcommonhandle("用户编号",null),
	},
		views: {
			'infoeditview': {
				caption: commonLogic.appcommonhandle("用户信息",null),
				title: commonLogic.appcommonhandle("用户编辑视图",null),
			},
			'pickupview': {
				caption: commonLogic.appcommonhandle("用户",null),
				title: commonLogic.appcommonhandle("用户数据选择视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("用户信息",null),
				title: commonLogic.appcommonhandle("用户编辑视图",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("用户",null),
				title: commonLogic.appcommonhandle("用户选择表格视图",null),
			},
			'treeexpview': {
				caption: commonLogic.appcommonhandle("用户",null),
				title: commonLogic.appcommonhandle("用户",null),
			},
			'maingridview': {
				caption: commonLogic.appcommonhandle("用户",null),
				title: commonLogic.appcommonhandle("用户表格视图",null),
			},
		},
		info_form: {
			details: {
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'grouppanel1': commonLogic.appcommonhandle("账号信息",null), 
				'grouppanel2': commonLogic.appcommonhandle("联系信息",null), 
				'grouppanel3': commonLogic.appcommonhandle("用户信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("ID",null), 
				'srfmajortext': commonLogic.appcommonhandle("真实姓名",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'realname': commonLogic.appcommonhandle("真实姓名",null), 
				'join': commonLogic.appcommonhandle("入职日期",null), 
				'dept': commonLogic.appcommonhandle("所属部门",null), 
				'role': commonLogic.appcommonhandle("职位",null), 
				'gender': commonLogic.appcommonhandle("性别",null), 
				'account': commonLogic.appcommonhandle("账户",null), 
				'email': commonLogic.appcommonhandle("邮箱",null), 
				'mobile': commonLogic.appcommonhandle("手机",null), 
				'phone': commonLogic.appcommonhandle("电话",null), 
				'qq': commonLogic.appcommonhandle("QQ",null), 
				'dingding': commonLogic.appcommonhandle("钉钉",null), 
				'weixin': commonLogic.appcommonhandle("微信",null), 
				'address': commonLogic.appcommonhandle("通讯地址",null), 
				'id': commonLogic.appcommonhandle("ID",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'grouppanel1': commonLogic.appcommonhandle("账号信息",null), 
				'grouppanel2': commonLogic.appcommonhandle("联系信息",null), 
				'grouppanel3': commonLogic.appcommonhandle("用户信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("ID",null), 
				'srfmajortext': commonLogic.appcommonhandle("真实姓名",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'realname': commonLogic.appcommonhandle("真实姓名",null), 
				'join': commonLogic.appcommonhandle("入职日期",null), 
				'dept': commonLogic.appcommonhandle("所属部门",null), 
				'role': commonLogic.appcommonhandle("职位",null), 
				'gender': commonLogic.appcommonhandle("性别",null), 
				'account': commonLogic.appcommonhandle("账户",null), 
				'email': commonLogic.appcommonhandle("邮箱",null), 
				'password': commonLogic.appcommonhandle("密码",null), 
				'mobile': commonLogic.appcommonhandle("手机",null), 
				'phone': commonLogic.appcommonhandle("电话",null), 
				'qq': commonLogic.appcommonhandle("QQ",null), 
				'dingding': commonLogic.appcommonhandle("钉钉",null), 
				'weixin': commonLogic.appcommonhandle("微信",null), 
				'address': commonLogic.appcommonhandle("通讯地址",null), 
				'id': commonLogic.appcommonhandle("ID",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'usersn': commonLogic.appcommonhandle("用户编号",null),
				'realname': commonLogic.appcommonhandle("真实姓名",null),
				'account': commonLogic.appcommonhandle("账户",null),
				'role': commonLogic.appcommonhandle("职位",null),
				'email': commonLogic.appcommonhandle("邮箱",null),
				'gender': commonLogic.appcommonhandle("性别",null),
				'phone': commonLogic.appcommonhandle("电话",null),
				'qq': commonLogic.appcommonhandle("QQ",null),
				'address': commonLogic.appcommonhandle("通讯地址",null),
				'last': commonLogic.appcommonhandle("最后登录",null),
				'visits': commonLogic.appcommonhandle("访问次数",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			user_edit: commonLogic.appcommonhandle("编辑",null),
			user_delete: commonLogic.appcommonhandle("删除",null),
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		infoeditviewtoolbar_toolbar: {
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction3_create': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction3_syncaccount': {
				caption: commonLogic.appcommonhandle("同步账号",null),
				tip: commonLogic.appcommonhandle("同步账号",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		userexp_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			refreshall: commonLogic.appcommonhandle("刷新",null),
			company_editdept: commonLogic.appcommonhandle("维护部门",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;