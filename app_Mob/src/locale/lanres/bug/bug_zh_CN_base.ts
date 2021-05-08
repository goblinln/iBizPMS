import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    severity: "严重程度",
    dept: "归属部门",
    storyversion: "需求版本",
    buildname: "版本名称",
    linkbug: "相关Bug",
    activateddate: "激活日期",
    overduebugs: "过期天数",
    createbuild: "创建版本",
    assignedto: "指派给",
    resolution: "解决方案",
    lastediteddate: "修改日期",
    mobimage: "移动端图片",
    result: "result",
    keywords: "关键词",
    isfavorites: "是否收藏",
    modulename1: "模块名称",
    closedby: "由谁关闭",
    browser: "浏览器",
    deptname: "归属部门名",
    noticeusers: "消息通知用户",
    steps: "重现步骤",
    v2: "v2",
    confirmed: "是否确认",
    mailtoconact: "联系人",
    openedby: "由谁创建",
    activatedcount: "激活次数",
    openeddate: "创建日期",
    closeddate: "关闭日期",
    mailto: "抄送给",
    assigneddate: "指派日期",
    deadline: "截止日期",
    color: "标题颜色",
    comment: "备注",
    resolveddate: "解决日期",
    type: "Bug类型",
    status: "Bug状态",
    openedbuild: "影响版本",
    delayresolve: "延期解决",
    files: "附件",
    mailtopk: "抄送给",
    v1: "v1",
    deleted: "已删除",
    lines: "lines",
    substatus: "子状态",
    bugsn: "BUG编号",
    org: "归属组织",
    buildproject: "版本项目",
    id: "Bug编号",
    delay: "延期",
    found: "found",
    resolvedby: "解决者",
    orgname: "归属组织名",
    resolvedbuild: "解决版本",
    caseversion: "用例版本",
    pri: "优先级",
    os: "操作系统",
    repotype: "代码类型",
    hardware: "hardware",
    lasteditedby: "最后修改者",
    title: "Bug标题",
    productname: "产品",
    branchname: "平台/分支",
    taskname: "相关任务",
    casename: "相关用例",
    projectname: "项目",
    storyname: "相关需求",
    modulename: "模块名称",
    tostory: "转需求",
    entry: "应用",
    product: "所属产品",
    totask: "转任务",
    plan: "所属计划",
    module: "所属模块",
    branch: "平台/分支",
    duplicatebug: "重复ID",
    repo: "代码",
    story: "相关需求",
    ibizcase: "相关用例",
    project: "所属项目",
    task: "相关任务",
    testtask: "测试单",
  },
	views: {
		usr3mobmpickupleftview: {
			caption: commonLogic.appcommonhandle("关联bug",null),
		},
		editnewmobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr3mobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		resolvemobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		assigntomobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		assmobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr2mobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		colsemobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr3mobpickupbuildresolvedmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		closemoboptionview: {
			caption: commonLogic.appcommonhandle("关闭Bug",null),
		},
		usr3mobmpickupview: {
			caption: commonLogic.appcommonhandle("关联bug",null),
		},
		usr4mobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr6mobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		assmobmdview9: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr5mobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		confirmmobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		rmoboptionview: {
			caption: commonLogic.appcommonhandle("解决Bug",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr2mobmpickupview: {
			caption: commonLogic.appcommonhandle("关联bug",null),
		},
		usr3mobpickupmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		assmoboptionview: {
			caption: commonLogic.appcommonhandle("指派Bug",null),
		},
		acmoboptionview: {
			caption: commonLogic.appcommonhandle("激活Bug",null),
		},
		cmoboptionview: {
			caption: commonLogic.appcommonhandle("确认Bug",null),
		},
		testmobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		planmobmdview9: {
			caption: commonLogic.appcommonhandle("计划下属",null),
		},
		usr3mobmpickupbuildcreatebugview: {
			caption: commonLogic.appcommonhandle("关联bug",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr3mobpickupmdview1: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		activationmobeditview: {
			caption: commonLogic.appcommonhandle("Bug",null),
		},
		usr2mobpickupmdview: {
			caption: commonLogic.appcommonhandle("关联bug",null),
		},
		assmoremobmdview: {
			caption: commonLogic.appcommonhandle("指派给我的Bug",null),
		},
		logmobmdview9: {
			caption: commonLogic.appcommonhandle("更新日志",null),
		},
	},
	mobmain_form: {
		details: {
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("附件",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("变更历史",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			productname: commonLogic.appcommonhandle("产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			branchname: commonLogic.appcommonhandle("平台/分支",null), 
			modulename1: commonLogic.appcommonhandle("模块名称",null), 
			projectname: commonLogic.appcommonhandle("项目",null), 
			openedbuild: commonLogic.appcommonhandle("影响版本",null), 
			title: commonLogic.appcommonhandle("Bug标题",null), 
			type: commonLogic.appcommonhandle("Bug类型",null), 
			severity: commonLogic.appcommonhandle("严重程度",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			os: commonLogic.appcommonhandle("操作系统",null), 
			browser: commonLogic.appcommonhandle("浏览器",null), 
			deadline: commonLogic.appcommonhandle("截止日期",null), 
			repotype: commonLogic.appcommonhandle("代码类型",null), 
			status: commonLogic.appcommonhandle("Bug状态",null), 
			resolution: commonLogic.appcommonhandle("解决方案",null), 
			resolveddate: commonLogic.appcommonhandle("解决日期",null), 
			resolvedby: commonLogic.appcommonhandle("解决者",null), 
			steps: commonLogic.appcommonhandle("重现步骤",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
		},
		uiactions: {
		},
	},
	assigntomob_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
		},
		uiactions: {
		},
	},
	confirmmob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			type: commonLogic.appcommonhandle("Bug类型",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
		},
		uiactions: {
		},
	},
	activationmob_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
			resolvedbuild: commonLogic.appcommonhandle("影响版本",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
		},
		uiactions: {
		},
	},
	resolvemob_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
			resolution: commonLogic.appcommonhandle("解决方案",null), 
			resolvedbuild: commonLogic.appcommonhandle("解决版本",null), 
			resolveddate: commonLogic.appcommonhandle("解决日期",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
		},
		uiactions: {
		},
	},
	mobmaindataedit_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel3: commonLogic.appcommonhandle("基本信息",null), 
			grouppanel4: commonLogic.appcommonhandle("项目/需求/任务",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
			grouppanel5: commonLogic.appcommonhandle("Bug的一生",null), 
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
			title: commonLogic.appcommonhandle("Bug标题",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			productname: commonLogic.appcommonhandle("产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			branchname: commonLogic.appcommonhandle("平台/分支",null), 
			module: commonLogic.appcommonhandle("所属模块",null), 
			modulename: commonLogic.appcommonhandle("模块名称",null), 
			plan: commonLogic.appcommonhandle("所属计划",null), 
			type: commonLogic.appcommonhandle("Bug类型",null), 
			severity: commonLogic.appcommonhandle("严重程度",null), 
			storyversion: commonLogic.appcommonhandle("需求版本",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			status: commonLogic.appcommonhandle("Bug状态",null), 
			activatedcount: commonLogic.appcommonhandle("激活次数",null), 
			activateddate: commonLogic.appcommonhandle("激活日期",null), 
			confirmed: commonLogic.appcommonhandle("是否确认",null), 
			assignedto: commonLogic.appcommonhandle("当前指派",null), 
			deadline: commonLogic.appcommonhandle("截止日期",null), 
			os: commonLogic.appcommonhandle("操作系统",null), 
			browser: commonLogic.appcommonhandle("浏览器",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			story: commonLogic.appcommonhandle("相关需求",null), 
			projectname: commonLogic.appcommonhandle("项目",null), 
			storyname: commonLogic.appcommonhandle("相关需求",null), 
			taskname: commonLogic.appcommonhandle("相关任务",null), 
			openedby: commonLogic.appcommonhandle("由谁创建",null), 
			openedbuild: commonLogic.appcommonhandle("影响版本",null), 
			resolvedby: commonLogic.appcommonhandle("由谁解决",null), 
			resolution: commonLogic.appcommonhandle("解决方案",null), 
			resolvedbuild: commonLogic.appcommonhandle("解决版本",null), 
			closedby: commonLogic.appcommonhandle("由谁关闭",null), 
			lasteditedby: commonLogic.appcommonhandle("最后修改者",null), 
			steps: commonLogic.appcommonhandle("重现步骤",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			files: commonLogic.appcommonhandle("附件",null), 
		},
		uiactions: {
		},
	},
	closemob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel6: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("Bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
		},
		uiactions: {
		},
	},
	mobnewfrom_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("bug基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("Bug编号",null), 
			srfmajortext: commonLogic.appcommonhandle("Bug标题",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			productname: commonLogic.appcommonhandle("产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			product: commonLogic.appcommonhandle("所属产品",null), 
			modulename: commonLogic.appcommonhandle("所属模块",null), 
			module: commonLogic.appcommonhandle("所属模块",null), 
			projectname: commonLogic.appcommonhandle("项目",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			openedbuild: commonLogic.appcommonhandle("影响版本",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			deadline: commonLogic.appcommonhandle("截止日期",null), 
			type: commonLogic.appcommonhandle("Bug类型",null), 
			os: commonLogic.appcommonhandle("操作系统",null), 
			browser: commonLogic.appcommonhandle("浏览器",null), 
			title: commonLogic.appcommonhandle("Bug标题",null), 
			severity: commonLogic.appcommonhandle("严重程度",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			steps: commonLogic.appcommonhandle("重现步骤",null), 
			task: commonLogic.appcommonhandle("相关任务",null), 
			storyname: commonLogic.appcommonhandle("相关需求",null), 
			story: commonLogic.appcommonhandle("相关需求",null), 
			taskname: commonLogic.appcommonhandle("相关任务",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			keywords: commonLogic.appcommonhandle("关键词",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			id: commonLogic.appcommonhandle("Bug编号",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_title_like: commonLogic.appcommonhandle("Bug标题",null), 
			n_status_eq: commonLogic.appcommonhandle("Bug状态",null), 
			n_type_eq: commonLogic.appcommonhandle("Bug类型",null), 
			n_openedby_eq: commonLogic.appcommonhandle("由谁创建",null), 
			n_severity_eq: commonLogic.appcommonhandle("严重程度",null), 
			n_modulename_like: commonLogic.appcommonhandle("模块名称",null), 
		},
		uiactions: {
		},
	},
	usr4mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联bug",null),
			tip: '关联bug',
		},
	},
	editnewmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_assingtobugmob: {
			caption: commonLogic.appcommonhandle("指派",null),
			tip: '指派',
		},
		deuiaction1_confirmbugmob: {
			caption: commonLogic.appcommonhandle("确认",null),
			tip: '确认',
		},
		deuiaction1_activationmob: {
			caption: commonLogic.appcommonhandle("激活",null),
			tip: '激活',
		},
		deuiaction1_resolvebugmob: {
			caption: commonLogic.appcommonhandle("解决",null),
			tip: '解决',
		},
		deuiaction1_mobmainedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction1_closebugmob: {
			caption: commonLogic.appcommonhandle("关闭",null),
			tip: '关闭',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
	},
	usr6mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联bug",null),
			tip: '关联bug',
		},
	},
	usr2mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联bug",null),
			tip: '关联bug',
		},
	},
	usr3mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关联bug",null),
			tip: '关联bug',
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	testmobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: '新建',
		},
	},
	resolvemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	assigntomobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	colsemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	confirmmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	planmobmdview9righttoolbar_toolbar: {
	},
	activationmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
};