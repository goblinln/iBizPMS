import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'severity': commonLogic.appcommonhandle("严重程度",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'storyversion': commonLogic.appcommonhandle("需求版本",null),
		'buildname': commonLogic.appcommonhandle("版本名称",null),
		'linkbug': commonLogic.appcommonhandle("相关Bug",null),
		'activateddate': commonLogic.appcommonhandle("激活日期",null),
		'overduebugs': commonLogic.appcommonhandle("过期天数",null),
		'createbuild': commonLogic.appcommonhandle("创建版本",null),
		'assignedto': commonLogic.appcommonhandle("指派给",null),
		'resolution': commonLogic.appcommonhandle("解决方案",null),
		'lastediteddate': commonLogic.appcommonhandle("修改日期",null),
		'mobimage': commonLogic.appcommonhandle("移动端图片",null),
		'result': commonLogic.appcommonhandle("result",null),
		'keywords': commonLogic.appcommonhandle("关键词",null),
		'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
		'modulename1': commonLogic.appcommonhandle("模块名称",null),
		'closedby': commonLogic.appcommonhandle("由谁关闭",null),
		'browser': commonLogic.appcommonhandle("浏览器",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'steps': commonLogic.appcommonhandle("重现步骤",null),
		'v2': commonLogic.appcommonhandle("v2",null),
		'confirmed': commonLogic.appcommonhandle("是否确认",null),
		'mailtoconact': commonLogic.appcommonhandle("联系人",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'activatedcount': commonLogic.appcommonhandle("激活次数",null),
		'openeddate': commonLogic.appcommonhandle("创建日期",null),
		'closeddate': commonLogic.appcommonhandle("关闭日期",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
		'assigneddate': commonLogic.appcommonhandle("指派日期",null),
		'deadline': commonLogic.appcommonhandle("截止日期",null),
		'color': commonLogic.appcommonhandle("标题颜色",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'resolveddate': commonLogic.appcommonhandle("解决日期",null),
		'type': commonLogic.appcommonhandle("Bug类型",null),
		'status': commonLogic.appcommonhandle("Bug状态",null),
		'openedbuild': commonLogic.appcommonhandle("影响版本",null),
		'delayresolve': commonLogic.appcommonhandle("延期解决",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'mailtopk': commonLogic.appcommonhandle("抄送给",null),
		'v1': commonLogic.appcommonhandle("v1",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'lines': commonLogic.appcommonhandle("lines",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'bugsn': commonLogic.appcommonhandle("BUG编号",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'buildproject': commonLogic.appcommonhandle("版本项目",null),
		'id': commonLogic.appcommonhandle("Bug编号",null),
		'delay': commonLogic.appcommonhandle("延期",null),
		'found': commonLogic.appcommonhandle("found",null),
		'resolvedby': commonLogic.appcommonhandle("解决者",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'resolvedbuild': commonLogic.appcommonhandle("解决版本",null),
		'caseversion': commonLogic.appcommonhandle("用例版本",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'os': commonLogic.appcommonhandle("操作系统",null),
		'repotype': commonLogic.appcommonhandle("代码类型",null),
		'hardware': commonLogic.appcommonhandle("hardware",null),
		'lasteditedby': commonLogic.appcommonhandle("最后修改者",null),
		'title': commonLogic.appcommonhandle("Bug标题",null),
		'productname': commonLogic.appcommonhandle("产品",null),
		'branchname': commonLogic.appcommonhandle("平台/分支",null),
		'taskname': commonLogic.appcommonhandle("相关任务",null),
		'casename': commonLogic.appcommonhandle("相关用例",null),
		'projectname': commonLogic.appcommonhandle("项目",null),
		'storyname': commonLogic.appcommonhandle("相关需求",null),
		'modulename': commonLogic.appcommonhandle("模块名称",null),
		'tostory': commonLogic.appcommonhandle("转需求",null),
		'entry': commonLogic.appcommonhandle("应用",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'totask': commonLogic.appcommonhandle("转任务",null),
		'plan': commonLogic.appcommonhandle("所属计划",null),
		'module': commonLogic.appcommonhandle("所属模块",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'duplicatebug': commonLogic.appcommonhandle("重复ID",null),
		'repo': commonLogic.appcommonhandle("代码",null),
		'story': commonLogic.appcommonhandle("相关需求",null),
		'ibizcase': commonLogic.appcommonhandle("相关用例",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'task': commonLogic.appcommonhandle("相关任务",null),
		'testtask': commonLogic.appcommonhandle("测试单",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
	},
		views: {
			'gridview9_assignedtome': {
				caption: commonLogic.appcommonhandle("Bug",null),
				title: commonLogic.appcommonhandle("Bug表格视图",null),
			},
			'mainmygridview': {
				caption: commonLogic.appcommonhandle("Bug",null),
				title: commonLogic.appcommonhandle("bug表格视图",null),
			},
		},
		main2_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'title': commonLogic.appcommonhandle("Bug标题",null),
				'status': commonLogic.appcommonhandle("状态",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		mygroupmain_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'severity': commonLogic.appcommonhandle("级别",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'confirmed': commonLogic.appcommonhandle("确认",null),
				'productname': commonLogic.appcommonhandle("产品",null),
				'projectname': commonLogic.appcommonhandle("项目",null),
				'title': commonLogic.appcommonhandle("Bug标题",null),
				'status': commonLogic.appcommonhandle("Bug状态",null),
				'openedby': commonLogic.appcommonhandle("由谁创建",null),
				'openeddate': commonLogic.appcommonhandle("创建日期",null),
				'assignedto': commonLogic.appcommonhandle("指派给",null),
				'resolution': commonLogic.appcommonhandle("方案",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
				'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'bugsn': commonLogic.appcommonhandle("编号",null),
				'id': commonLogic.appcommonhandle("编号",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'confirmed': commonLogic.appcommonhandle("确认",null),
				'title': commonLogic.appcommonhandle("Bug标题",null),
				'status': commonLogic.appcommonhandle("Bug状态",null),
				'openedby': commonLogic.appcommonhandle("由谁创建",null),
				'openeddate': commonLogic.appcommonhandle("创建日期",null),
				'assignedto': commonLogic.appcommonhandle("指派给",null),
				'resolution': commonLogic.appcommonhandle("方案",null),
				'lastediteddate': commonLogic.appcommonhandle("修改日期",null),
				'activateddate': commonLogic.appcommonhandle("激活日期",null),
				'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
				'productname': commonLogic.appcommonhandle("产品",null),
				'projectname': commonLogic.appcommonhandle("项目",null),
		},
			uiactions: {
			accountbug_confirmbug: commonLogic.appcommonhandle("确认",null),
			accountbug_resolvebug: commonLogic.appcommonhandle("解决",null),
			accountbug_closebug: commonLogic.appcommonhandle("关闭",null),
			accountbug_mainedit: commonLogic.appcommonhandle("编辑",null),
			copy: commonLogic.appcommonhandle("Copy",null),
			accountbug_bugfavorites: commonLogic.appcommonhandle("收藏",null),
			accountbug_bugnfavorites: commonLogic.appcommonhandle("取消收藏",null),
			},
		},
		mainmygridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;