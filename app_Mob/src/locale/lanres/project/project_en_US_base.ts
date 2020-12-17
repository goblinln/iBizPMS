import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    openedVersion:  commonLogic.appcommonhandle("当前系统版本",null),
    begin:  commonLogic.appcommonhandle("开始时间",null),
    acl:  commonLogic.appcommonhandle("访问控制",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    desc:  commonLogic.appcommonhandle("项目描述",null),
    pM:  commonLogic.appcommonhandle("项目负责人",null),
    id:  commonLogic.appcommonhandle("项目编号",null),
    name:  commonLogic.appcommonhandle("项目名称",null),
    subStatus:  commonLogic.appcommonhandle("子状态",null),
    order:  commonLogic.appcommonhandle("项目排序",null),
    rD:  commonLogic.appcommonhandle("发布负责人",null),
    whitelist:  commonLogic.appcommonhandle("分组白名单",null),
    pri:  commonLogic.appcommonhandle("优先级",null),
    end:  commonLogic.appcommonhandle("结束日期",null),
    canceledDate:  commonLogic.appcommonhandle("取消日期",null),
    code:  commonLogic.appcommonhandle("项目代号",null),
    catID:  commonLogic.appcommonhandle("catID",null),
    statge:  commonLogic.appcommonhandle("statge",null),
    canceledBy:  commonLogic.appcommonhandle("由谁取消",null),
    isCat:  commonLogic.appcommonhandle("isCat",null),
    openedDate:  commonLogic.appcommonhandle("创建日期",null),
    closedBy:  commonLogic.appcommonhandle("由谁关闭",null),
    type:  commonLogic.appcommonhandle("项目类型",null),
    pO:  commonLogic.appcommonhandle("产品负责人",null),
    status:  commonLogic.appcommonhandle("项目状态",null),
    days:  commonLogic.appcommonhandle("可用工作日",null),
    team:  commonLogic.appcommonhandle("团队名称",null),
    closedDate:  commonLogic.appcommonhandle("关闭日期",null),
    openedBy:  commonLogic.appcommonhandle("由谁创建",null),
    qD:  commonLogic.appcommonhandle("测试负责人",null),
    parentName:  commonLogic.appcommonhandle("parent",null),
    parent:  commonLogic.appcommonhandle("父项目",null),
    taskCnt:  commonLogic.appcommonhandle("任务总数",null),
    bugCnt:  commonLogic.appcommonhandle("Bug总数",null),
    storyCnt:  commonLogic.appcommonhandle("需求总数",null),
    products:  commonLogic.appcommonhandle("关联产品",null),
    branchs:  commonLogic.appcommonhandle("关联产品平台集合",null),
    plans:  commonLogic.appcommonhandle("关联计划",null),
    srfArray:  commonLogic.appcommonhandle("关联数据数组",null),
    comment:  commonLogic.appcommonhandle("备注",null),
    period:  commonLogic.appcommonhandle("时间段",null),
    account:  commonLogic.appcommonhandle("项目团队成员",null),
    join:  commonLogic.appcommonhandle("加盟日",null),
    hours:  commonLogic.appcommonhandle("可用工时/天",null),
    role:  commonLogic.appcommonhandle("角色",null),
    totalConsumed:  commonLogic.appcommonhandle("任务消耗总工时",null),
    totalwh:  commonLogic.appcommonhandle("总工时",null),
    totalLeft:  commonLogic.appcommonhandle("任务预计剩余总工时",null),
    totalEstimate:  commonLogic.appcommonhandle("任务最初预计总工时",null),
    totalhours:  commonLogic.appcommonhandle("可用工时",null),
    mobimage:  commonLogic.appcommonhandle("移动端图片",null),
    accounts:  commonLogic.appcommonhandle("项目团队相关成员",null),
    order1:  commonLogic.appcommonhandle("项目排序",null),
    istop:  commonLogic.appcommonhandle("是否置顶",null),
    dept:  commonLogic.appcommonhandle("选择部门",null),
    managemembers:  commonLogic.appcommonhandle("复制团队",null),
    buildcnt:  commonLogic.appcommonhandle("版本总数",null),
    teamcnt:  commonLogic.appcommonhandle("团队成员总数",null),
    alltaskcnt:  commonLogic.appcommonhandle("所有任务数",null),
    unclosetaskcnt:  commonLogic.appcommonhandle("未关闭任务数",null),
    asstomytaskcnt:  commonLogic.appcommonhandle("指派给我任务数",null),
    unstarttaskcnt:  commonLogic.appcommonhandle("未开始任务数",null),
    moretaskcnt:  commonLogic.appcommonhandle("更多任务数",null),
    yStarttaskcnt:  commonLogic.appcommonhandle("进行中任务数",null),
    uncompletetaskcnt:  commonLogic.appcommonhandle("未完成任务数",null),
    ycompletetaskcnt:  commonLogic.appcommonhandle("已完成任务数",null),
    mycompletetaskcnt:  commonLogic.appcommonhandle("我完成任务数",null),
    closetaskcnt:  commonLogic.appcommonhandle("关闭任务数",null),
    canceltaskcnt:  commonLogic.appcommonhandle("取消任务数",null),
    storychangecnt:  commonLogic.appcommonhandle("需求变更数",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
    doclibcnt:  commonLogic.appcommonhandle("文档数量",null),
    orgId:  commonLogic.appcommonhandle("组织标识",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    projectteams:  commonLogic.appcommonhandle("项目团队成员",null),
  },
	views: {
		mobchartview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		supmobeditview: {
			caption: commonLogic.appcommonhandle("挂起",null),
		},
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		activitemobeditview: {
			caption: commonLogic.appcommonhandle("激活",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		closemobeditview: {
			caption: commonLogic.appcommonhandle("关闭",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		projectteammanagemobeditview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("项目基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("项目编号",null), 
			srfmajortext: commonLogic.appcommonhandle("项目名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("项目名称",null), 
			code: commonLogic.appcommonhandle("项目代号",null), 
			pm: commonLogic.appcommonhandle("项目负责人",null), 
			po: commonLogic.appcommonhandle("产品负责人",null), 
			qd: commonLogic.appcommonhandle("测试负责人",null), 
			rd: commonLogic.appcommonhandle("发布负责人",null), 
			begin: commonLogic.appcommonhandle("开始时间",null), 
			end: commonLogic.appcommonhandle("结束日期",null), 
			days: commonLogic.appcommonhandle("可用工作日",null), 
			team: commonLogic.appcommonhandle("团队名称",null), 
			status: commonLogic.appcommonhandle("项目状态",null), 
			storycnt: commonLogic.appcommonhandle("需求总数",null), 
			bugcnt: commonLogic.appcommonhandle("Bug总数",null), 
			taskcnt: commonLogic.appcommonhandle("任务总数",null), 
			totalestimate: commonLogic.appcommonhandle("任务最初预计总工时",null), 
			totalconsumed: commonLogic.appcommonhandle("任务消耗总工时",null), 
			desc: commonLogic.appcommonhandle("项目描述",null), 
			id: commonLogic.appcommonhandle("项目编号",null), 
		},
		uiactions: {
		},
	},
	activitemob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("project基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("项目编号",null), 
			srfmajortext: commonLogic.appcommonhandle("项目名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			begin: commonLogic.appcommonhandle("开始时间",null), 
			end: commonLogic.appcommonhandle("结束日期",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			mobimage: commonLogic.appcommonhandle("移动端图片",null), 
			id: commonLogic.appcommonhandle("项目编号",null), 
		},
		uiactions: {
		},
	},
	suspendnclosemob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("project基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("项目编号",null), 
			srfmajortext: commonLogic.appcommonhandle("项目名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			mobimage: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("项目编号",null), 
		},
		uiactions: {
		},
	},
	mobprojectteammanage_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("项目编号",null), 
			srfmajortext: commonLogic.appcommonhandle("项目名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("项目编号",null), 
		},
		uiactions: {
		},
	},
	mobnewform_form: {
		details: {
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("project基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("项目编号",null), 
			srfmajortext: commonLogic.appcommonhandle("项目名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("项目名称",null), 
			code: commonLogic.appcommonhandle("项目代号",null), 
			begin: commonLogic.appcommonhandle("开始时间",null), 
			period: commonLogic.appcommonhandle("时间段",null), 
			end: commonLogic.appcommonhandle("结束日期",null), 
			days: commonLogic.appcommonhandle("可用工作日",null), 
			team: commonLogic.appcommonhandle("团队名称",null), 
			type: commonLogic.appcommonhandle("项目类型",null), 
			products: commonLogic.appcommonhandle("关联产品",null), 
			srfarray: commonLogic.appcommonhandle("关联数据数组",null), 
			branchs: commonLogic.appcommonhandle("关联产品平台集合",null), 
			plans: commonLogic.appcommonhandle("关联计划",null), 
			formitemex1: commonLogic.appcommonhandle("",null), 
			desc: commonLogic.appcommonhandle("项目描述",null), 
			acl: commonLogic.appcommonhandle("访问控制",null), 
			id: commonLogic.appcommonhandle("项目编号",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_name_like: commonLogic.appcommonhandle("项目名称",null), 
			n_status_eq: commonLogic.appcommonhandle("项目状态",null), 
			n_type_eq: commonLogic.appcommonhandle("项目类型",null), 
		},
		uiactions: {
		},
	},
	mobpickupmdviewmdctrl_quicktoolbar_toolbar: {
	},
	mobpickupmdviewmdctrl_batchtoolbar_toolbar: {
	},
	mobmdviewmdctrl_quicktoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("New",null),
			tip: 'deuiaction1',
		},
	},
	mobmdviewmdctrl_batchtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("Remove",null),
			tip: 'deuiaction1',
		},
	},
	activitemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	supmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	closemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	projectteammanagemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		items1: {
			caption: commonLogic.appcommonhandle("更多",null),
			tip: 'items1',
		},
		deuiaction1: {
			caption: commonLogic.appcommonhandle("激活",null),
			tip: 'deuiaction1',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("挂起",null),
			tip: 'deuiaction2',
		},
		deuiaction3: {
			caption: commonLogic.appcommonhandle("关闭",null),
			tip: 'deuiaction3',
		},
		deuiaction4: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: 'deuiaction4',
		},
		deuiaction5: {
			caption: commonLogic.appcommonhandle("团队成员管理",null),
			tip: 'deuiaction5',
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: 'deuiaction1',
		},
	},
};