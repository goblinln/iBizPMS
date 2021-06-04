import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'lastediteddate': commonLogic.appcommonhandle("修改日期",null),
		'scripteddate': commonLogic.appcommonhandle("scriptedDate",null),
		'color': commonLogic.appcommonhandle("标题颜色",null),
		'path': commonLogic.appcommonhandle("path",null),
		'openeddate': commonLogic.appcommonhandle("创建日期",null),
		'lastrunresult': commonLogic.appcommonhandle("结果",null),
		'modulename1': commonLogic.appcommonhandle("模块名称",null),
		'linkcase': commonLogic.appcommonhandle("相关用例",null),
		'casesteps': commonLogic.appcommonhandle("用例步骤集合",null),
		'task': commonLogic.appcommonhandle("属性",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'howrun': commonLogic.appcommonhandle("howRun",null),
		'resultcnt': commonLogic.appcommonhandle("测试结果数",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'fromcaseversion': commonLogic.appcommonhandle("来源用例版本",null),
		'version': commonLogic.appcommonhandle("用例版本",null),
		'scriptedby': commonLogic.appcommonhandle("scriptedBy",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'type': commonLogic.appcommonhandle("用例类型",null),
		'resultfalicnt': commonLogic.appcommonhandle("测试失败数",null),
		'status': commonLogic.appcommonhandle("用例状态",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'auto': commonLogic.appcommonhandle("auto",null),
		'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'frequency': commonLogic.appcommonhandle("frequency",null),
		'title': commonLogic.appcommonhandle("用例标题",null),
		'lasteditedby': commonLogic.appcommonhandle("最后修改者",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'reviewedby': commonLogic.appcommonhandle("由谁评审",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'tobugcnt': commonLogic.appcommonhandle("转bug数",null),
		'assignedto': commonLogic.appcommonhandle("指派给",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'storyversion': commonLogic.appcommonhandle("需求版本",null),
		'revieweddate': commonLogic.appcommonhandle("评审时间",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'stage': commonLogic.appcommonhandle("适用阶段",null),
		'scriptlocation': commonLogic.appcommonhandle("scriptLocation",null),
		'status1': commonLogic.appcommonhandle("用例状态",null),
		'lastrundate': commonLogic.appcommonhandle("执行时间",null),
		'keywords': commonLogic.appcommonhandle("关键词",null),
		'scriptstatus': commonLogic.appcommonhandle("scriptStatus",null),
		'frame': commonLogic.appcommonhandle("工具/框架",null),
		'lastrunresult1': commonLogic.appcommonhandle("测试用例结果",null),
		'stepcnt': commonLogic.appcommonhandle("用例步骤数",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'id': commonLogic.appcommonhandle("用例编号",null),
		'precondition': commonLogic.appcommonhandle("前置条件",null),
		'lastrunner': commonLogic.appcommonhandle("执行人",null),
		'libname': commonLogic.appcommonhandle("用例库",null),
		'storyname': commonLogic.appcommonhandle("需求名称",null),
		'modulename': commonLogic.appcommonhandle("模块名称",null),
		'productname': commonLogic.appcommonhandle("产品名称",null),
		'fromcaseid': commonLogic.appcommonhandle("来源用例",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'frombug': commonLogic.appcommonhandle("来源Bug",null),
		'story': commonLogic.appcommonhandle("相关需求",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'lib': commonLogic.appcommonhandle("所属库",null),
		'module': commonLogic.appcommonhandle("所属模块",null),
		'casesn': commonLogic.appcommonhandle("测试用例编号",null),
	},
		views: {
			'mainmygridview': {
				caption: commonLogic.appcommonhandle("功能测试",null),
				title: commonLogic.appcommonhandle("功能测试",null),
			},
			'mainmynewgridview': {
				caption: commonLogic.appcommonhandle("功能测试",null),
				title: commonLogic.appcommonhandle("功能测试",null),
			},
			'casefavorite': {
				caption: commonLogic.appcommonhandle("测试用例",null),
				title: commonLogic.appcommonhandle("case我得收藏",null),
			},
			'gridview9': {
				caption: commonLogic.appcommonhandle("测试用例",null),
				title: commonLogic.appcommonhandle("测试用例表格视图",null),
			},
			'gridview9_mecretae': {
				caption: commonLogic.appcommonhandle("测试用例",null),
				title: commonLogic.appcommonhandle("测试用例表格视图",null),
			},
		},
		mygrid_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'title': commonLogic.appcommonhandle("用例标题",null),
				'type': commonLogic.appcommonhandle("用例类型",null),
				'openedby': commonLogic.appcommonhandle("创建",null),
				'lastrunner': commonLogic.appcommonhandle("执行人",null),
				'lastrundate': commonLogic.appcommonhandle("执行时间",null),
				'lastrunresult': commonLogic.appcommonhandle("结果",null),
				'status1': commonLogic.appcommonhandle("状态",null),
				'tobugcnt': commonLogic.appcommonhandle("B",null),
				'resultcnt': commonLogic.appcommonhandle("R",null),
				'casesteps': commonLogic.appcommonhandle("S",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'casesn': commonLogic.appcommonhandle("编号",null),
				'id': commonLogic.appcommonhandle("编号",null),
				'pri': commonLogic.appcommonhandle("P",null),
				'title': commonLogic.appcommonhandle("用例标题",null),
				'type': commonLogic.appcommonhandle("用例类型",null),
				'openedby': commonLogic.appcommonhandle("创建",null),
				'lastrunner': commonLogic.appcommonhandle("执行人",null),
				'lastrundate': commonLogic.appcommonhandle("执行时间",null),
				'lastrunresult': commonLogic.appcommonhandle("结果",null),
				'status': commonLogic.appcommonhandle("状态",null),
				'status1': commonLogic.appcommonhandle("状态",null),
				'tobugcnt': commonLogic.appcommonhandle("B",null),
				'resultcnt': commonLogic.appcommonhandle("R",null),
				'casesteps': commonLogic.appcommonhandle("S",null),
				'lastrunresult1': commonLogic.appcommonhandle("测试用例结果",null),
				'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
				'color': commonLogic.appcommonhandle("标题颜色",null),
		},
			uiactions: {
			},
		},
		main2_grid: {
			columns: {
				'pri': commonLogic.appcommonhandle("P",null),
				'title': commonLogic.appcommonhandle("用例标题",null),
				'status1': commonLogic.appcommonhandle("状态",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		casefavoritetoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		mainmygridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		mainmynewgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;