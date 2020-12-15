import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    assignedTo:  commonLogic.appcommonhandle("指派给",null),
    childStories:  commonLogic.appcommonhandle("细分需求",null),
    plan:  commonLogic.appcommonhandle("所属计划",null),
    version:  commonLogic.appcommonhandle("版本号",null),
    assignedDate:  commonLogic.appcommonhandle("指派日期",null),
    pri:  commonLogic.appcommonhandle("优先级",null),
    linkStories:  commonLogic.appcommonhandle("相关需求",null),
    status:  commonLogic.appcommonhandle("当前状态",null),
    estimate:  commonLogic.appcommonhandle("预计工时",null),
    reviewedDate:  commonLogic.appcommonhandle("评审时间",null),
    title:  commonLogic.appcommonhandle("需求名称",null),
    sourceNote:  commonLogic.appcommonhandle("来源备注",null),
    reviewedBy:  commonLogic.appcommonhandle("由谁评审",null),
    subStatus:  commonLogic.appcommonhandle("子状态",null),
    stagedBy:  commonLogic.appcommonhandle("设置阶段者",null),
    openedBy:  commonLogic.appcommonhandle("由谁创建",null),
    openedDate:  commonLogic.appcommonhandle("创建日期",null),
    id:  commonLogic.appcommonhandle("编号",null),
    source:  commonLogic.appcommonhandle("需求来源",null),
    closedReason:  commonLogic.appcommonhandle("关闭原因",null),
    color:  commonLogic.appcommonhandle("标题颜色",null),
    mailto:  commonLogic.appcommonhandle("抄送给",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    keywords:  commonLogic.appcommonhandle("关键词",null),
    lastEditedBy:  commonLogic.appcommonhandle("最后修改",null),
    stage:  commonLogic.appcommonhandle("所处阶段",null),
    closedDate:  commonLogic.appcommonhandle("关闭日期	",null),
    closedBy:  commonLogic.appcommonhandle("由谁关闭",null),
    type:  commonLogic.appcommonhandle("需求类型",null),
    lastEditedDate:  commonLogic.appcommonhandle("最后修改日期",null),
    path:  commonLogic.appcommonhandle("模块路径",null),
    parentName:  commonLogic.appcommonhandle("父需求名称",null),
    modulename:  commonLogic.appcommonhandle("所属模块名称",null),
    productName:  commonLogic.appcommonhandle("产品名称",null),
    fromBug:  commonLogic.appcommonhandle("来源Bug",null),
    parent:  commonLogic.appcommonhandle("父需求",null),
    module:  commonLogic.appcommonhandle("所属模块",null),
    product:  commonLogic.appcommonhandle("所属产品",null),
    duplicateStory:  commonLogic.appcommonhandle("重复需求ID",null),
    branch:  commonLogic.appcommonhandle("平台/分支",null),
    toBug:  commonLogic.appcommonhandle("转Bug",null),
    spec:  commonLogic.appcommonhandle("需求描述",null),
    verify:  commonLogic.appcommonhandle("验收标准",null),
    result:  commonLogic.appcommonhandle("评审结果",null),
    comment:  commonLogic.appcommonhandle("备注",null),
    isLeaf:  commonLogic.appcommonhandle("是否子需求",null),
    files:  commonLogic.appcommonhandle("附件",null),
    branchname:  commonLogic.appcommonhandle("平台/分支",null),
    versionc:  commonLogic.appcommonhandle("版本号",null),
    modulename1:  commonLogic.appcommonhandle("所属模块名称",null),
    project:  commonLogic.appcommonhandle("项目",null),
    preversion:  commonLogic.appcommonhandle("之前的版本",null),
    neednotreview:  commonLogic.appcommonhandle("不需要评审",null),
    isfavorites:  commonLogic.appcommonhandle("是否收藏",null),
    ischild:  commonLogic.appcommonhandle("是否可以细分",null),
    mailtoconact:  commonLogic.appcommonhandle("联系人",null),
    mailtopk:  commonLogic.appcommonhandle("抄送给",null),
    assignedtopk:  commonLogic.appcommonhandle("指派给（选择）",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
    iBIZ_SOURCEOBJECT:  commonLogic.appcommonhandle("来源对象",null),
    sOURCEOBJECT:  commonLogic.appcommonhandle("来源对象",null),
    iBIZ_ID:  commonLogic.appcommonhandle("IBIZ标识",null),
    sOURCENAME:  commonLogic.appcommonhandle("来源对象名称",null),
    sOURCEID:  commonLogic.appcommonhandle("来源对象标识",null),
    iBIZ_SOURCEID:  commonLogic.appcommonhandle("来源对象标识",null),
    iBIZ_SOURCENAME:  commonLogic.appcommonhandle("来源对象名称",null),
    storypoints:  commonLogic.appcommonhandle("故事点",null),
  },
	views: {
		newmobeditview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		moblistview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		mobmdview9: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		usr2mobmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		editmobeditview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		rmoboptionview: {
			caption: commonLogic.appcommonhandle("评审",null),
		},
		mobmdviewcurproject: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		linkstorymobmpickupview: {
			caption: commonLogic.appcommonhandle("管理需求",null),
		},
		usr2mobmpickupbuildview: {
			caption: commonLogic.appcommonhandle("关联需求",null),
		},
		logmobmdview9: {
			caption: commonLogic.appcommonhandle("更新日志",null),
		},
		usr3mobmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		favoritemoremobmdview: {
			caption: commonLogic.appcommonhandle("我收藏的需求",null),
		},
		asmoboptionview: {
			caption: commonLogic.appcommonhandle("指派",null),
		},
		assmobmdview9: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		usr3mobmpickupview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		usr2mobmpickupview: {
			caption: commonLogic.appcommonhandle("关联需求",null),
		},
		favoritemobmdview9: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		usr3mobpickupmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		usr2mobmdview_5219: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		cmoboptionview: {
			caption: commonLogic.appcommonhandle("关闭",null),
		},
		usr4mobmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		assmobmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		acmoboptionview: {
			caption: commonLogic.appcommonhandle("激活",null),
		},
		usr2mobpickupmdview: {
			caption: commonLogic.appcommonhandle("关联需求",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		assmoremobmdview: {
			caption: commonLogic.appcommonhandle("指派给我的需求",null),
		},
		favoritemobmdview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		changemoboptionview: {
			caption: commonLogic.appcommonhandle("变更",null),
		},
		linkstorymobpickupmdview: {
			caption: commonLogic.appcommonhandle("关联需求",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
		usr2mobpickupmdbuildview: {
			caption: commonLogic.appcommonhandle("关联需求",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("需求",null),
		},
	},
	mobnewform_form: {
		details: {
			group1: commonLogic.appcommonhandle("需求基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			module: commonLogic.appcommonhandle("所属模块",null), 
			prodoctname: commonLogic.appcommonhandle("所属产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			modulename: commonLogic.appcommonhandle("所属模块",null), 
			plan: commonLogic.appcommonhandle("计划",null), 
			source: commonLogic.appcommonhandle("需求来源",null), 
			sourcenote: commonLogic.appcommonhandle("来源备注",null), 
			reviewedby: commonLogic.appcommonhandle("由谁评审",null), 
			assignedtopk: commonLogic.appcommonhandle("由谁评审",null), 
			neednotreview: commonLogic.appcommonhandle("",null), 
			title: commonLogic.appcommonhandle("需求名称",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			storypoints: commonLogic.appcommonhandle("故事点",null), 
			estimate: commonLogic.appcommonhandle("预计",null), 
			spec: commonLogic.appcommonhandle("需求描述",null), 
			verify: commonLogic.appcommonhandle("验收标准",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("附件",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("需求基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			prodoctname: commonLogic.appcommonhandle("产品名称",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			branchname: commonLogic.appcommonhandle("平台/分支",null), 
			modulename1: commonLogic.appcommonhandle("所属模块名称",null), 
			version: commonLogic.appcommonhandle("版本号",null), 
			title: commonLogic.appcommonhandle("需求名称",null), 
			type: commonLogic.appcommonhandle("需求类型",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			storypoints: commonLogic.appcommonhandle("故事点",null), 
			status: commonLogic.appcommonhandle("当前状态",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			assigneddate: commonLogic.appcommonhandle("指派日期",null), 
			source: commonLogic.appcommonhandle("需求来源",null), 
			sourcenote: commonLogic.appcommonhandle("来源备注",null), 
			stage: commonLogic.appcommonhandle("所处阶段",null), 
			closedby: commonLogic.appcommonhandle("由谁关闭",null), 
			closeddate: commonLogic.appcommonhandle("关闭日期	",null), 
			closedreason: commonLogic.appcommonhandle("关闭原因",null), 
			spec: commonLogic.appcommonhandle("需求描述",null), 
			verify: commonLogic.appcommonhandle("验收标准",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobchageform_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("需求描述信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			status: commonLogic.appcommonhandle("当前状态",null), 
			version: commonLogic.appcommonhandle("版本#",null), 
			assignedtopk: commonLogic.appcommonhandle("由谁评审",null), 
			reviewedby: commonLogic.appcommonhandle("由谁评审",null), 
			neednotreview: commonLogic.appcommonhandle("需要评审",null), 
			title: commonLogic.appcommonhandle("需求名称",null), 
			spec: commonLogic.appcommonhandle("需求描述",null), 
			verify: commonLogic.appcommonhandle("验收标准",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
		},
		uiactions: {
		},
	},
	assigntomob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("需求描述信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			assignedtopk: commonLogic.appcommonhandle("指派给",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	reviewmob_form: {
		details: {
			grouppanel2: commonLogic.appcommonhandle("评审通过",null), 
			grouppanel3: commonLogic.appcommonhandle("拒绝",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			group1: commonLogic.appcommonhandle("需求基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			title: commonLogic.appcommonhandle("需求名称",null), 
			revieweddate: commonLogic.appcommonhandle("评审时间",null), 
			result: commonLogic.appcommonhandle("评审结果",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			estimate: commonLogic.appcommonhandle("预计工时",null), 
			preversion: commonLogic.appcommonhandle("之前版本",null), 
			closedreason: commonLogic.appcommonhandle("拒绝原因",null), 
			assignedtopk: commonLogic.appcommonhandle("指派给",null), 
			version: commonLogic.appcommonhandle("版本号",null), 
			reviewedby: commonLogic.appcommonhandle("由谁评审",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
		},
		uiactions: {
		},
	},
	closemob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("需求描述信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			closedreason: commonLogic.appcommonhandle("关闭原因",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobmainedit_form: {
		details: {
			group1: commonLogic.appcommonhandle("需求基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			title: commonLogic.appcommonhandle("需求名称",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			module: commonLogic.appcommonhandle("所属模块",null), 
			prodoctname: commonLogic.appcommonhandle("所属产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			modulename: commonLogic.appcommonhandle("所属模块",null), 
			plan: commonLogic.appcommonhandle("计划",null), 
			source: commonLogic.appcommonhandle("需求来源",null), 
			sourcenote: commonLogic.appcommonhandle("来源备注",null), 
			reviewedby: commonLogic.appcommonhandle("由谁评审",null), 
			assignedtopk: commonLogic.appcommonhandle("由谁评审",null), 
			neednotreview: commonLogic.appcommonhandle("",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			storypoints: commonLogic.appcommonhandle("故事点",null), 
			estimate: commonLogic.appcommonhandle("预计",null), 
			spec: commonLogic.appcommonhandle("需求描述",null), 
			verify: commonLogic.appcommonhandle("验收标准",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
		},
		uiactions: {
		},
	},
	activitemob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("需求描述信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("需求名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			assignedtopk: commonLogic.appcommonhandle("指派给",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_title_like: commonLogic.appcommonhandle("需求名",null), 
			n_status_eq: commonLogic.appcommonhandle("当前状态",null), 
			n_type_eq: commonLogic.appcommonhandle("需求类型",null), 
		},
		uiactions: {
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	usr3mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联需求",null),
			tip: 'deuiaction1',
		},
	},
	editmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_changestorydetailmob: {
			caption: commonLogic.appcommonhandle("变更",null),
			tip: 'deuiaction1_changestorydetailmob',
		},
		deuiaction1_assigntomob: {
			caption: commonLogic.appcommonhandle("指派",null),
			tip: 'deuiaction1_assigntomob',
		},
		deuiaction1_reviewstorymob: {
			caption: commonLogic.appcommonhandle("评审",null),
			tip: 'deuiaction1_reviewstorymob',
		},
		deuiaction1_closestorymob: {
			caption: commonLogic.appcommonhandle("关闭",null),
			tip: 'deuiaction1_closestorymob',
		},
		deuiaction1_openbaseinfoeditviewmob: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_openbaseinfoeditviewmob',
		},
		deuiaction1_deletemob: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: 'deuiaction1_deletemob',
		},
		deuiaction1_projectlinkstoriesmob: {
			caption: commonLogic.appcommonhandle("关联需求",null),
			tip: 'deuiaction1_projectlinkstoriesmob',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建需求",null),
			tip: 'deuiaction1',
		},
	},
	usr2mobmdview_5219righttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联需求",null),
			tip: 'deuiaction1',
		},
	},
	usr4mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联需求",null),
			tip: 'deuiaction1',
		},
	},
	mobmdviewcurprojectrighttoolbar_toolbar: {
	},
	usr2mobmdviewrighttoolbar_toolbar: {
	},
};