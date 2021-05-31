import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'assignedto': commonLogic.appcommonhandle("指派给",null),
		'modulename1': commonLogic.appcommonhandle("所属模块名称",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'childstories': commonLogic.appcommonhandle("细分需求",null),
		'ibiz_id': commonLogic.appcommonhandle("IBIZ标识",null),
		'plan': commonLogic.appcommonhandle("所属计划",null),
		'version': commonLogic.appcommonhandle("版本号",null),
		'assigneddate': commonLogic.appcommonhandle("指派日期",null),
		'storypoints': commonLogic.appcommonhandle("故事点",null),
		'sourcename': commonLogic.appcommonhandle("来源对象名称",null),
		'storyprovidedate': commonLogic.appcommonhandle("需求提供时间",null),
		'isleaf': commonLogic.appcommonhandle("是否子需求",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'sourceid': commonLogic.appcommonhandle("来源对象标识",null),
		'linkstories': commonLogic.appcommonhandle("相关需求",null),
		'assessresult': commonLogic.appcommonhandle("评审结果",null),
		'status': commonLogic.appcommonhandle("当前状态",null),
		'mailtopk': commonLogic.appcommonhandle("抄送给",null),
		'estimate': commonLogic.appcommonhandle("预计工时",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'revieweddate': commonLogic.appcommonhandle("评审时间",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'title': commonLogic.appcommonhandle("需求名称",null),
		'mailtoconact': commonLogic.appcommonhandle("联系人",null),
		'sourcenote': commonLogic.appcommonhandle("来源备注",null),
		'versionc': commonLogic.appcommonhandle("版本号",null),
		'reviewedby': commonLogic.appcommonhandle("由谁评审",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'stagedby': commonLogic.appcommonhandle("设置阶段者",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'openeddate': commonLogic.appcommonhandle("创建日期",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'ibiz_sourceobject': commonLogic.appcommonhandle("来源对象",null),
		'source': commonLogic.appcommonhandle("需求来源",null),
		'storylatestfinisheddate': commonLogic.appcommonhandle("需求最晚完成时间",null),
		'neednotreview': commonLogic.appcommonhandle("不需要评审",null),
		'ischild': commonLogic.appcommonhandle("是否可以细分",null),
		'closedreason': commonLogic.appcommonhandle("关闭原因",null),
		'color': commonLogic.appcommonhandle("标题颜色",null),
		'orgid': commonLogic.appcommonhandle("orgid",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
		'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'sourceobject': commonLogic.appcommonhandle("来源对象",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'keywords': commonLogic.appcommonhandle("关键词",null),
		'lasteditedby': commonLogic.appcommonhandle("最后修改",null),
		'stage': commonLogic.appcommonhandle("所处阶段",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'closeddate': commonLogic.appcommonhandle("关闭日期	",null),
		'spec': commonLogic.appcommonhandle("需求描述",null),
		'ibiz_sourcename': commonLogic.appcommonhandle("来源对象名称",null),
		'assignedtopk': commonLogic.appcommonhandle("指派给（选择）",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'acllist': commonLogic.appcommonhandle("acllist",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'verify': commonLogic.appcommonhandle("验收标准",null),
		'closedby': commonLogic.appcommonhandle("由谁关闭",null),
		'acl': commonLogic.appcommonhandle("acl",null),
		'result': commonLogic.appcommonhandle("评审结果",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'type': commonLogic.appcommonhandle("需求类型",null),
		'lastediteddate': commonLogic.appcommonhandle("最后修改日期",null),
		'ibiz_sourceid': commonLogic.appcommonhandle("来源对象标识",null),
		'preversion': commonLogic.appcommonhandle("之前的版本",null),
		'storyprovider': commonLogic.appcommonhandle("需求提供人",null),
		'mdeptid': commonLogic.appcommonhandle("MDEPTID",null),
		'path': commonLogic.appcommonhandle("模块路径",null),
		'parentname': commonLogic.appcommonhandle("父需求名称",null),
		'modulename': commonLogic.appcommonhandle("所属模块名称",null),
		'productname': commonLogic.appcommonhandle("产品名称",null),
		'branchname': commonLogic.appcommonhandle("平台/分支",null),
		'frombug': commonLogic.appcommonhandle("来源Bug",null),
		'parent': commonLogic.appcommonhandle("父需求",null),
		'module': commonLogic.appcommonhandle("所属模块",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'duplicatestory': commonLogic.appcommonhandle("重复需求ID",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'tobug': commonLogic.appcommonhandle("转Bug",null),
		'storysn': commonLogic.appcommonhandle("需求编号",null),
	},
		views: {
			'tabexpview': {
				caption: commonLogic.appcommonhandle("需求",null),
				title: commonLogic.appcommonhandle("需求分页导航视图",null),
			},
			'curprojectwgridview': {
				caption: commonLogic.appcommonhandle("需求",null),
				title: commonLogic.appcommonhandle("story表格视图",null),
			},
			'curprojectkanbanview': {
				caption: commonLogic.appcommonhandle("需求",null),
				title: commonLogic.appcommonhandle("需求看板视图",null),
			},
			'curprojectgridview': {
				caption: commonLogic.appcommonhandle("需求",null),
				title: commonLogic.appcommonhandle("story表格视图",null),
			},
		},
		projectstory_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'title': commonLogic.appcommonhandle("需求名称",null),
				'plan': commonLogic.appcommonhandle("计划",null),
				'openedby': commonLogic.appcommonhandle("创建",null),
				'assignedto': commonLogic.appcommonhandle("指派",null),
				'estimate': commonLogic.appcommonhandle("预计",null),
				'status': commonLogic.appcommonhandle("状态",null),
				'stage': commonLogic.appcommonhandle("阶段",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'storysn': commonLogic.appcommonhandle("编号",null),
				'id': commonLogic.appcommonhandle("编号",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'title': commonLogic.appcommonhandle("需求名称",null),
				'plan': commonLogic.appcommonhandle("计划",null),
				'openedby': commonLogic.appcommonhandle("创建",null),
				'assignedto': commonLogic.appcommonhandle("指派",null),
				'estimate': commonLogic.appcommonhandle("预计",null),
				'status': commonLogic.appcommonhandle("状态",null),
				'stage': commonLogic.appcommonhandle("阶段",null),
				'modulename': commonLogic.appcommonhandle("所属模块名称",null),
				'module': commonLogic.appcommonhandle("所属模块",null),
				'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
				'ischild': commonLogic.appcommonhandle("是否可以细分",null),
				'color': commonLogic.appcommonhandle("标题颜色",null),
		},
			uiactions: {
			projectstory_breakdowntasks: commonLogic.appcommonhandle("分解任务",null),
			projectstory_batchbreakdowntasks: commonLogic.appcommonhandle("批量分解",null),
			projectstory_projectunlinkstory: commonLogic.appcommonhandle("移除",null),
			},
		},
		storykanban_kanban: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		curprojectgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("关联需求",null),
				tip: commonLogic.appcommonhandle("关联需求",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("按照计划关联",null),
				tip: commonLogic.appcommonhandle("按照计划关联",null),
			},
			'deuiaction5': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction6': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		curprojectwgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("关联需求",null),
				tip: commonLogic.appcommonhandle("关联需求",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("按照计划关联",null),
				tip: commonLogic.appcommonhandle("按照计划关联",null),
			},
			'deuiaction5': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction6': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		curprojectkanbanviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("关联需求",null),
				tip: commonLogic.appcommonhandle("关联需求",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("按照计划关联",null),
				tip: commonLogic.appcommonhandle("按照计划关联",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;