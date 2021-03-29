import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'myebugs': commonLogic.appcommonhandle("我的过期bug数",null),
		'nickname': commonLogic.appcommonhandle("nickname",null),
		'fails': commonLogic.appcommonhandle("fails",null),
		'visits': commonLogic.appcommonhandle("访问次数",null),
		'phone': commonLogic.appcommonhandle("电话",null),
		'mytodocnt': commonLogic.appcommonhandle("我的待办数",null),
		'myterritorycnt': commonLogic.appcommonhandle("我的地盘",null),
		'mybugs': commonLogic.appcommonhandle("我的bugs",null),
		'role': commonLogic.appcommonhandle("职位",null),
		'realname': commonLogic.appcommonhandle("真实姓名",null),
		'clientstatus': commonLogic.appcommonhandle("clientStatus",null),
		'last': commonLogic.appcommonhandle("最后登录",null),
		'zipcode': commonLogic.appcommonhandle("zipcode",null),
		'skype': commonLogic.appcommonhandle("skype",null),
		'myfavoritebugs': commonLogic.appcommonhandle("我收藏的bugs",null),
		'join': commonLogic.appcommonhandle("入职日期",null),
		'score': commonLogic.appcommonhandle("score",null),
		'dept': commonLogic.appcommonhandle("所属部门",null),
		'account': commonLogic.appcommonhandle("账户",null),
		'myfavorites': commonLogic.appcommonhandle("我的收藏",null),
		'mystorys': commonLogic.appcommonhandle("我的需求数",null),
		'commiter': commonLogic.appcommonhandle("源代码账户",null),
		'mobile': commonLogic.appcommonhandle("手机",null),
		'locked': commonLogic.appcommonhandle("locked",null),
		'address': commonLogic.appcommonhandle("通讯地址",null),
		'mytasks': commonLogic.appcommonhandle("我的任务",null),
		'scorelevel': commonLogic.appcommonhandle("scoreLevel",null),
		'password': commonLogic.appcommonhandle("密码",null),
		'ranzhi': commonLogic.appcommonhandle("ranzhi",null),
		'projects': commonLogic.appcommonhandle("未关闭项目数",null),
		'slack': commonLogic.appcommonhandle("slack",null),
		'products': commonLogic.appcommonhandle("未关闭产品数",null),
		'weixin': commonLogic.appcommonhandle("微信",null),
		'myetasks': commonLogic.appcommonhandle("我的过期任务数",null),
		'eprojects': commonLogic.appcommonhandle("过期项目数",null),
		'whatsapp': commonLogic.appcommonhandle("whatsapp",null),
		'qq': commonLogic.appcommonhandle("QQ",null),
		'gender': commonLogic.appcommonhandle("男女",null),
		'clientlang': commonLogic.appcommonhandle("clientLang",null),
		'deleted': commonLogic.appcommonhandle("逻辑删除标志",null),
		'myfavoritetasks': commonLogic.appcommonhandle("我收藏的任务",null),
		'birthday': commonLogic.appcommonhandle("birthday",null),
		'ip': commonLogic.appcommonhandle("ip",null),
		'myfavoritestorys': commonLogic.appcommonhandle("我收藏的需求数",null),
		'email': commonLogic.appcommonhandle("邮箱",null),
		'dingding': commonLogic.appcommonhandle("钉钉",null),
		'avatar': commonLogic.appcommonhandle("avatar",null),
		'id': commonLogic.appcommonhandle("ID",null),
	},
		views: {
			'listview9personinfo': {
				caption: commonLogic.appcommonhandle("我的工作",null),
				title: commonLogic.appcommonhandle("我的工作",null),
			},
			'productsumtreeexpview': {
				caption: commonLogic.appcommonhandle("产品汇总表",null),
				title: commonLogic.appcommonhandle("我的地盘树导航视图（产品汇总表）",null),
			},
			'listview': {
				caption: commonLogic.appcommonhandle("欢迎",null),
				title: commonLogic.appcommonhandle("欢迎",null),
			},
			'productreporttabexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("我的地盘分页导航视图",null),
			},
			'testtreeexpview': {
				caption: commonLogic.appcommonhandle("测试统计",null),
				title: commonLogic.appcommonhandle("我的地盘树导航视图（测试统计）",null),
			},
			'projectreporttabexpview': {
				caption: commonLogic.appcommonhandle("项目",null),
				title: commonLogic.appcommonhandle("我的地盘分页导航视图",null),
			},
			'usersumtreeexpview': {
				caption: commonLogic.appcommonhandle("用户汇总表",null),
				title: commonLogic.appcommonhandle("我的地盘树导航视图",null),
			},
			'usr3tabexpview': {
				caption: commonLogic.appcommonhandle("我的地盘",null),
				title: commonLogic.appcommonhandle("管理",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("我的地盘",null),
				title: commonLogic.appcommonhandle("我的地盘编辑视图",null),
			},
			'treeexpviewprojectstats': {
				caption: commonLogic.appcommonhandle("项目汇总表",null),
				title: commonLogic.appcommonhandle("我的地盘我的地盘实体树导航视图（项目汇总表）导航视图",null),
			},
			'allreporttabexpview': {
				caption: commonLogic.appcommonhandle("汇报",null),
				title: commonLogic.appcommonhandle("我的地盘分页导航视图",null),
			},
			'dashboardview': {
				caption: commonLogic.appcommonhandle("首页",null),
				title: commonLogic.appcommonhandle("首页",null),
			},
			'statstabexpview': {
				caption: commonLogic.appcommonhandle("统计",null),
				title: commonLogic.appcommonhandle("我的地盘分页导航视图（统计）",null),
			},
			'calendarview': {
				caption: commonLogic.appcommonhandle("我的地盘",null),
				title: commonLogic.appcommonhandle("我的地盘日历视图",null),
			},
			'calendarexpview': {
				caption: commonLogic.appcommonhandle("我的地盘",null),
				title: commonLogic.appcommonhandle("我的地盘日历导航视图",null),
			},
			'reporttabexpview': {
				caption: commonLogic.appcommonhandle("个人",null),
				title: commonLogic.appcommonhandle("我的地盘分页导航视图",null),
			},
			'listview9': {
				caption: commonLogic.appcommonhandle("我的工作",null),
				title: commonLogic.appcommonhandle("我的工作",null),
			},
			'tabexpview': {
				caption: commonLogic.appcommonhandle("我的地盘",null),
				title: commonLogic.appcommonhandle("我的地盘",null),
			},
			'mywork': {
				caption: commonLogic.appcommonhandle("我的工作",null),
				title: commonLogic.appcommonhandle("我的工作",null),
			},
			'employtreeexpview': {
				caption: commonLogic.appcommonhandle("员工负载",null),
				title: commonLogic.appcommonhandle("我的地盘树导航视图（员工负载计）",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("我的地盘基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("ID",null), 
				'srfmajortext': commonLogic.appcommonhandle("真实姓名",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("ID",null), 
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
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("Save And New",null),
				tip: commonLogic.appcommonhandle("Save And New",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
		productsum_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'productinputtable': commonLogic.appcommonhandle("产品投入表",null),
				'productstorysum': commonLogic.appcommonhandle("需求汇总表",null),
				'storyhourssum': commonLogic.appcommonhandle("需求工时汇总表",null),
				'productsum': commonLogic.appcommonhandle("产品汇总表",null),
				'productquantity': commonLogic.appcommonhandle("产品质量表",null),
				'productcompletetable': commonLogic.appcommonhandle("产品完成度统计表",null),
				'productbugtypesum': commonLogic.appcommonhandle("产品Bug类型统计表",null),
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			},
		},
		test_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'testcasestats': commonLogic.appcommonhandle("用例统计表",null),
				'bugopenendby': commonLogic.appcommonhandle("Bug创建表",null),
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'productbugresolutionstats': commonLogic.appcommonhandle("产品Bug解决方案汇总表",null),
				'bugresolvedby': commonLogic.appcommonhandle("Bug完成表",null),
				'productbugstatussum': commonLogic.appcommonhandle("产品Bug状态汇总表",null),
				'bugassignedto': commonLogic.appcommonhandle("Bug指派表",null),
			},
			uiactions: {
			},
		},
		projectstats_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'bugtype': commonLogic.appcommonhandle("bug类型统计",null),
				'tasktypecount': commonLogic.appcommonhandle("项目任务类型统计",null),
				'bugstatuscount': commonLogic.appcommonhandle("bug状态统计",null),
				'taskstatuscount': commonLogic.appcommonhandle("项目任务状态统计",null),
				'projectquality': commonLogic.appcommonhandle("项目质量表",null),
				'projectstorystagestats': commonLogic.appcommonhandle("项目需求阶段分布表",null),
				'projectinputstats': commonLogic.appcommonhandle("项目投入统计表",null),
				'projectprogress': commonLogic.appcommonhandle("项目进展表",null),
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'bugresolutioncount': commonLogic.appcommonhandle("bug解决方案统计",null),
				'projectstorystatusstats': commonLogic.appcommonhandle("项目需求状态分布表",null),
			},
			uiactions: {
			},
		},
		employeeload_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'companydynamicstats': commonLogic.appcommonhandle("公司动态汇总表",null),
				'employeeload': commonLogic.appcommonhandle("员工负载表",null),
				'taskfinish': commonLogic.appcommonhandle("任务完成汇总表",null),
				'bugresolved': commonLogic.appcommonhandle("bug解决汇总表",null),
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			},
		},
		usersum_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
				'userfinashtasksum': commonLogic.appcommonhandle("用户完成任务汇总表",null),
			},
			uiactions: {
			},
		},
		myworkpc_calendar: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;