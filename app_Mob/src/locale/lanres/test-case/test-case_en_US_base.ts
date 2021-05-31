import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    lastEditedDate:  commonLogic.appcommonhandle("修改日期",null),
    scriptedDate:  commonLogic.appcommonhandle("scriptedDate",null),
    color:  commonLogic.appcommonhandle("标题颜色",null),
    path:  commonLogic.appcommonhandle("path",null),
    openedDate:  commonLogic.appcommonhandle("创建日期",null),
    lastRunResult:  commonLogic.appcommonhandle("结果",null),
    modulename1:  commonLogic.appcommonhandle("模块名称",null),
    linkCase:  commonLogic.appcommonhandle("相关用例",null),
    caseSteps:  commonLogic.appcommonhandle("用例步骤集合",null),
    task:  commonLogic.appcommonhandle("属性",null),
    order:  commonLogic.appcommonhandle("排序",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    howRun:  commonLogic.appcommonhandle("howRun",null),
    resultCNT:  commonLogic.appcommonhandle("测试结果数",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
    fromCaseVersion:  commonLogic.appcommonhandle("来源用例版本",null),
    version:  commonLogic.appcommonhandle("用例版本",null),
    scriptedBy:  commonLogic.appcommonhandle("scriptedBy",null),
    openedBy:  commonLogic.appcommonhandle("由谁创建",null),
    type:  commonLogic.appcommonhandle("用例类型",null),
    resultfalicnt:  commonLogic.appcommonhandle("测试失败数",null),
    status:  commonLogic.appcommonhandle("用例状态",null),
    comment:  commonLogic.appcommonhandle("备注",null),
    auto:  commonLogic.appcommonhandle("auto",null),
    isfavorites:  commonLogic.appcommonhandle("是否收藏",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    frequency:  commonLogic.appcommonhandle("frequency",null),
    title:  commonLogic.appcommonhandle("用例标题",null),
    lastEditedBy:  commonLogic.appcommonhandle("最后修改者",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    reviewedBy:  commonLogic.appcommonhandle("由谁评审",null),
    files:  commonLogic.appcommonhandle("附件",null),
    toBugCNT:  commonLogic.appcommonhandle("转bug数",null),
    assignedTo:  commonLogic.appcommonhandle("指派给",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    storyVersion:  commonLogic.appcommonhandle("需求版本",null),
    reviewedDate:  commonLogic.appcommonhandle("评审时间",null),
    pri:  commonLogic.appcommonhandle("优先级",null),
    stage:  commonLogic.appcommonhandle("适用阶段",null),
    scriptLocation:  commonLogic.appcommonhandle("scriptLocation",null),
    status1:  commonLogic.appcommonhandle("用例状态",null),
    lastRunDate:  commonLogic.appcommonhandle("执行时间",null),
    keywords:  commonLogic.appcommonhandle("关键词",null),
    scriptStatus:  commonLogic.appcommonhandle("scriptStatus",null),
    frame:  commonLogic.appcommonhandle("工具/框架",null),
    lastrunresult1:  commonLogic.appcommonhandle("测试用例结果",null),
    stepCNT:  commonLogic.appcommonhandle("用例步骤数",null),
    subStatus:  commonLogic.appcommonhandle("子状态",null),
    id:  commonLogic.appcommonhandle("用例编号",null),
    precondition:  commonLogic.appcommonhandle("前置条件",null),
    lastRunner:  commonLogic.appcommonhandle("执行人",null),
    libname:  commonLogic.appcommonhandle("用例库",null),
    storyname:  commonLogic.appcommonhandle("需求名称",null),
    modulename:  commonLogic.appcommonhandle("模块名称",null),
    productName:  commonLogic.appcommonhandle("产品名称",null),
    fromCaseId:  commonLogic.appcommonhandle("来源用例",null),
    branch:  commonLogic.appcommonhandle("平台/分支",null),
    fromBug:  commonLogic.appcommonhandle("来源Bug",null),
    story:  commonLogic.appcommonhandle("相关需求",null),
    product:  commonLogic.appcommonhandle("所属产品",null),
    lib:  commonLogic.appcommonhandle("所属库",null),
    module:  commonLogic.appcommonhandle("所属模块",null),
    casesn:  commonLogic.appcommonhandle("测试用例编号",null),
  },
	views: {
		mobeditview: {
			caption: commonLogic.appcommonhandle("测试用例",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("测试用例",null),
		},
		usr2mobmpickupview: {
			caption: commonLogic.appcommonhandle("关联用例",null),
		},
		usr2mobpickupmdview: {
			caption: commonLogic.appcommonhandle("关联用例",null),
		},
		createcasemobeditview: {
			caption: commonLogic.appcommonhandle("测试用例",null),
		},
		mobmdview_testtask: {
			caption: commonLogic.appcommonhandle("测试用例",null),
		},
		mobmdview_testsuite: {
			caption: commonLogic.appcommonhandle("测试用例",null),
		},
	},
	createmob_form: {
		details: {
			group1: commonLogic.appcommonhandle("测试用例基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("用例编号",null), 
			srfmajortext: commonLogic.appcommonhandle("用例标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			title: commonLogic.appcommonhandle("用例标题",null), 
			type: commonLogic.appcommonhandle("用例类型",null), 
			stage: commonLogic.appcommonhandle("适用阶段",null), 
			precondition: commonLogic.appcommonhandle("前置条件",null), 
			version: commonLogic.appcommonhandle("用例版本",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
			id: commonLogic.appcommonhandle("用例编号",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("用例步骤",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("测试用例基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("用例编号",null), 
			srfmajortext: commonLogic.appcommonhandle("用例标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			title: commonLogic.appcommonhandle("用例标题",null), 
			type: commonLogic.appcommonhandle("用例类型",null), 
			stage: commonLogic.appcommonhandle("适用阶段",null), 
			precondition: commonLogic.appcommonhandle("前置条件",null), 
			id: commonLogic.appcommonhandle("用例编号",null), 
			version: commonLogic.appcommonhandle("用例版本",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_title_like: commonLogic.appcommonhandle("用例标题",null), 
			n_type_eq: commonLogic.appcommonhandle("用例类型",null), 
			n_status_eq: commonLogic.appcommonhandle("用例状态",null), 
			n_modulename_eq: commonLogic.appcommonhandle("模块名称",null), 
		},
		uiactions: {
		},
	},
	createcasemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("移动端新建",null),
			tip: 'deuiaction1',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
	},
	mobmdview_testtaskrighttoolbar2_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联需求",null),
			tip: 'deuiaction1',
		},
	},
	mobmdview_testsuiterighttoolbar_toolbar: {
	},
};