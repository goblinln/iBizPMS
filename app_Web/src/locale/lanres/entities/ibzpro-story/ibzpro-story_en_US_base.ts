import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'version': commonLogic.appcommonhandle("版本号",null),
		'sourcenote': commonLogic.appcommonhandle("来源备注",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
		'stagedby': commonLogic.appcommonhandle("设置阶段者",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'ibiz_sourcename': commonLogic.appcommonhandle("来源对象名称",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'stage': commonLogic.appcommonhandle("需求阶段",null),
		'closedby': commonLogic.appcommonhandle("由谁关闭",null),
		'spec': commonLogic.appcommonhandle("需求描述",null),
		'closeddate': commonLogic.appcommonhandle("关闭日期	",null),
		'linkstories': commonLogic.appcommonhandle("相关需求",null),
		'type': commonLogic.appcommonhandle("需求类型",null),
		'duplicatestory': commonLogic.appcommonhandle("重复需求",null),
		'branch': commonLogic.appcommonhandle("平台",null),
		'keywords': commonLogic.appcommonhandle("关键词",null),
		'childstories': commonLogic.appcommonhandle("需求细分",null),
		'openeddate': commonLogic.appcommonhandle("创建日期",null),
		'tobug': commonLogic.appcommonhandle("转Bug",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'closedreason': commonLogic.appcommonhandle("关闭原因",null),
		'project': commonLogic.appcommonhandle("项目",null),
		'frombug': commonLogic.appcommonhandle("来源Bug",null),
		'lasteditedby': commonLogic.appcommonhandle("最后修改者",null),
		'assigneddate': commonLogic.appcommonhandle("指派日期",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'ibiz_sourceid': commonLogic.appcommonhandle("来源对象标识",null),
		'title': commonLogic.appcommonhandle("需求名称",null),
		'lastediteddate': commonLogic.appcommonhandle("最后修改日期",null),
		'ibiz_sourceobject': commonLogic.appcommonhandle("来源对象",null),
		'ibizid': commonLogic.appcommonhandle("IBIZ标识",null),
		'verify': commonLogic.appcommonhandle("验收标准",null),
		'reviewedby': commonLogic.appcommonhandle("由谁评审",null),
		'assignedto': commonLogic.appcommonhandle("指派给",null),
		'source': commonLogic.appcommonhandle("需求来源",null),
		'estimate': commonLogic.appcommonhandle("预计工时",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'revieweddate': commonLogic.appcommonhandle("评审时间",null),
		'color': commonLogic.appcommonhandle("颜色",null),
		'status': commonLogic.appcommonhandle("状态",null),
		'product': commonLogic.appcommonhandle("编号",null),
		'module': commonLogic.appcommonhandle("id",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("需求",null),
				title: commonLogic.appcommonhandle("需求表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("需求",null),
				title: commonLogic.appcommonhandle("需求编辑视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("需求基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("需求名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
				'title': commonLogic.appcommonhandle("需求名称",null), 
				'ibiz_id': commonLogic.appcommonhandle("IBIZ标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'title': commonLogic.appcommonhandle("需求名称",null),
				'ibiz_id': commonLogic.appcommonhandle("IBIZ标识",null),
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
		gridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
			},
			'seperator1': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'seperator3': {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;