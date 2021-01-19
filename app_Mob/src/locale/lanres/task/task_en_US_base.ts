import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    canceledBy:  commonLogic.appcommonhandle("由谁取消",null),
    left:  commonLogic.appcommonhandle("预计剩余",null),
    openedDate:  commonLogic.appcommonhandle("创建日期",null),
    color:  commonLogic.appcommonhandle("标题颜色",null),
    id:  commonLogic.appcommonhandle("编号",null),
    finishedBy:  commonLogic.appcommonhandle("由谁完成",null),
    finishedList:  commonLogic.appcommonhandle("完成者列表",null),
    realStarted:  commonLogic.appcommonhandle("实际开始",null),
    closedBy:  commonLogic.appcommonhandle("由谁关闭",null),
    subStatus:  commonLogic.appcommonhandle("子状态",null),
    closedReason:  commonLogic.appcommonhandle("关闭原因",null),
    lastEditedDate:  commonLogic.appcommonhandle("最后修改日期",null),
    assignedDate:  commonLogic.appcommonhandle("指派日期",null),
    pri:  commonLogic.appcommonhandle("优先级",null),
    lastEditedBy:  commonLogic.appcommonhandle("最后修改",null),
    status:  commonLogic.appcommonhandle("任务状态",null),
    name:  commonLogic.appcommonhandle("任务名称",null),
    closedDate:  commonLogic.appcommonhandle("关闭时间",null),
    type:  commonLogic.appcommonhandle("任务类型",null),
    assignedTo:  commonLogic.appcommonhandle("指派给",null),
    desc:  commonLogic.appcommonhandle("任务描述",null),
    estStarted:  commonLogic.appcommonhandle("预计开始",null),
    deadline:  commonLogic.appcommonhandle("截止日期",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    mailto:  commonLogic.appcommonhandle("抄送给",null),
    consumed:  commonLogic.appcommonhandle("总计消耗",null),
    estimate:  commonLogic.appcommonhandle("最初预计",null),
    openedBy:  commonLogic.appcommonhandle("由谁创建",null),
    canceledDate:  commonLogic.appcommonhandle("取消时间",null),
    finishedDate:  commonLogic.appcommonhandle("实际完成",null),
    modulename:  commonLogic.appcommonhandle("所属模块",null),
    storyname:  commonLogic.appcommonhandle("相关需求",null),
    projectName:  commonLogic.appcommonhandle("所属项目",null),
    product:  commonLogic.appcommonhandle("产品",null),
    storyVersion:  commonLogic.appcommonhandle("需求版本",null),
    productName:  commonLogic.appcommonhandle("产品",null),
    parentName:  commonLogic.appcommonhandle("父任务",null),
    project:  commonLogic.appcommonhandle("所属项目",null),
    story:  commonLogic.appcommonhandle("相关需求",null),
    parent:  commonLogic.appcommonhandle("父任务",null),
    fromBug:  commonLogic.appcommonhandle("来源Bug",null),
    duration:  commonLogic.appcommonhandle("持续时间",null),
    module:  commonLogic.appcommonhandle("模块",null),
    path:  commonLogic.appcommonhandle("模块路径",null),
    comment:  commonLogic.appcommonhandle("备注",null),
    currentConsumed:  commonLogic.appcommonhandle("本次消耗",null),
    totalTime:  commonLogic.appcommonhandle("总计耗时",null),
    isLeaf:  commonLogic.appcommonhandle("是否子任务",null),
    allModules:  commonLogic.appcommonhandle("所有模块",null),
    multiple:  commonLogic.appcommonhandle("多人任务",null),
    taskteams:  commonLogic.appcommonhandle("项目团队成员",null),
    modulename1:  commonLogic.appcommonhandle("所属模块",null),
    ibztaskestimates:  commonLogic.appcommonhandle("工时",null),
    isfavorites:  commonLogic.appcommonhandle("是否收藏",null),
    status1:  commonLogic.appcommonhandle("任务状态",null),
    tasktype:  commonLogic.appcommonhandle("任务类型",null),
    files:  commonLogic.appcommonhandle("附件",null),
    usernames:  commonLogic.appcommonhandle("团队用户",null),
    isfinished:  commonLogic.appcommonhandle("是否完成",null),
    replycount:  commonLogic.appcommonhandle("回复数量",null),
    hasdetail:  commonLogic.appcommonhandle("是否填写描述",null),
    updateDate:  commonLogic.appcommonhandle("最后的更新日期",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
    progressrate:  commonLogic.appcommonhandle("进度",null),
    delay:  commonLogic.appcommonhandle("延期",null),
    mailtopk:  commonLogic.appcommonhandle("抄送给",null),
    mailtoconact:  commonLogic.appcommonhandle("联系人",null),
    statusorder:  commonLogic.appcommonhandle("排序",null),
    myconsumed:  commonLogic.appcommonhandle("之前消耗",null),
    mytotaltime:  commonLogic.appcommonhandle("我的总消耗",null),
    assignedToZj:  commonLogic.appcommonhandle("转交给",null),
  },
	views: {
		mobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		assmoremobmdview: {
			caption: commonLogic.appcommonhandle("指派给我的任务",null),
		},
		mycompletetaskmobmdviewweekly: {
			caption: commonLogic.appcommonhandle("计划参与",null),
		},
		weeklyplanstaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		stopmoboptionview: {
			caption: commonLogic.appcommonhandle("暂停",null),
		},
		mycompletetaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		monthlymycompletetaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		dailydonetaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		editmobeditview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		activemobtask: {
			caption: commonLogic.appcommonhandle("激活",null),
		},
		cancelmoboptionview: {
			caption: commonLogic.appcommonhandle("取消",null),
		},
		favoritemobmdview9: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		closemoboptionview: {
			caption: commonLogic.appcommonhandle("关闭",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		startmoboptionview: {
			caption: commonLogic.appcommonhandle("开始",null),
		},
		monthlyplanstaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		dailyplanstaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		favoritemoremobmdview: {
			caption: commonLogic.appcommonhandle("我收藏的任务",null),
		},
		favoritemobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		commoboptionview: {
			caption: commonLogic.appcommonhandle("完成",null),
		},
		monthlydonetaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		assmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		mycompletetaskmobmdview1: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		monthlymyplanstaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		moboptionview: {
			caption: commonLogic.appcommonhandle("指派",null),
		},
		gsmoboptionview: {
			caption: commonLogic.appcommonhandle("工时",null),
		},
		weeklylydonetaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		assmobmdview9: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		mycompletetaskmobmdviewnextplanweekly: {
			caption: commonLogic.appcommonhandle("计划参与",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		myplanstomorrowtaskmobmdview: {
			caption: commonLogic.appcommonhandle("任务",null),
		},
		usr2moboptionview: {
			caption: commonLogic.appcommonhandle("继续",null),
		},
	},
	mobstartform_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			realstarted: commonLogic.appcommonhandle("实际开始",null), 
			consumed: commonLogic.appcommonhandle("总计消耗",null), 
			left: commonLogic.appcommonhandle("预计剩余",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	assignformmob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			multiple: commonLogic.appcommonhandle("多人任务",null), 
			left: commonLogic.appcommonhandle("预计剩余",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			status: commonLogic.appcommonhandle("任务状态",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	completeformmob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			myconsumed: commonLogic.appcommonhandle("之前消耗",null), 
			consumed: commonLogic.appcommonhandle("之前消耗",null), 
			currentconsumed: commonLogic.appcommonhandle("本次消耗",null), 
			totaltime: commonLogic.appcommonhandle("总计耗时",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			finisheddate: commonLogic.appcommonhandle("实际完成",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			multiple: commonLogic.appcommonhandle("多人任务",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
		},
		uiactions: {
		},
	},
	closepausecancelformmob_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobactiviteform_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			left: commonLogic.appcommonhandle("预计剩余",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			multiple: commonLogic.appcommonhandle("多人任务",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	estimatemob_form: {
		details: {
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("工时",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			name: commonLogic.appcommonhandle("任务名称",null), 
		},
		uiactions: {
		},
	},
	mobnewfrom_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			group1: commonLogic.appcommonhandle("task基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			projectname: commonLogic.appcommonhandle("所属项目",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			module: commonLogic.appcommonhandle("模块",null), 
			type: commonLogic.appcommonhandle("任务类型",null), 
			modulename: commonLogic.appcommonhandle("所属模块",null), 
			allmodules: commonLogic.appcommonhandle("所有模块",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			multiple: commonLogic.appcommonhandle("多人任务",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
			story: commonLogic.appcommonhandle("相关需求",null), 
			storyname: commonLogic.appcommonhandle("相关需求",null), 
			name: commonLogic.appcommonhandle("任务名称",null), 
			color: commonLogic.appcommonhandle("任务名称color",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			estimate: commonLogic.appcommonhandle("预计",null), 
			eststarted: commonLogic.appcommonhandle("预计开始",null), 
			deadline: commonLogic.appcommonhandle("截止日期",null), 
			desc: commonLogic.appcommonhandle("任务描述",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("任务团队",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			druipart3: commonLogic.appcommonhandle("",null), 
			grouppanel3: commonLogic.appcommonhandle("附件",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			projectname: commonLogic.appcommonhandle("所属项目",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			modulename: commonLogic.appcommonhandle("所属模块",null), 
			name: commonLogic.appcommonhandle("任务名称",null), 
			type: commonLogic.appcommonhandle("任务类型",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			assigneddate: commonLogic.appcommonhandle("指派日期",null), 
			multiple: commonLogic.appcommonhandle("多人任务",null), 
			status: commonLogic.appcommonhandle("任务状态",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			estimate: commonLogic.appcommonhandle("最初预计",null), 
			consumed: commonLogic.appcommonhandle("总计消耗",null), 
			left: commonLogic.appcommonhandle("预计剩余",null), 
			eststarted: commonLogic.appcommonhandle("预计开始",null), 
			realstarted: commonLogic.appcommonhandle("实际开始",null), 
			deadline: commonLogic.appcommonhandle("截止日期",null), 
			finishedby: commonLogic.appcommonhandle("由谁完成",null), 
			closedby: commonLogic.appcommonhandle("由谁关闭",null), 
			closeddate: commonLogic.appcommonhandle("关闭时间",null), 
			closedreason: commonLogic.appcommonhandle("关闭原因",null), 
			desc: commonLogic.appcommonhandle("任务描述",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobmainedit_form: {
		details: {
			group1: commonLogic.appcommonhandle("任务基本信息",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel8: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel1: commonLogic.appcommonhandle("基本信息",null), 
			grouppanel4: commonLogic.appcommonhandle("工时信息",null), 
			grouppanel6: commonLogic.appcommonhandle("任务描述",null), 
			grouppanel7: commonLogic.appcommonhandle("备注",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel9: commonLogic.appcommonhandle("历史记录",null), 
			grouppanel5: commonLogic.appcommonhandle("任务的一生",null), 
			grouppanel3: commonLogic.appcommonhandle("分组面板",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后修改日期",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("任务名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			name: commonLogic.appcommonhandle("任务名称",null), 
			projectname: commonLogic.appcommonhandle("所属项目",null), 
			project: commonLogic.appcommonhandle("所属项目",null), 
			module: commonLogic.appcommonhandle("模块",null), 
			modulename: commonLogic.appcommonhandle("所属模块",null), 
			allmodules: commonLogic.appcommonhandle("所有模块",null), 
			storyname: commonLogic.appcommonhandle("相关需求",null), 
			story: commonLogic.appcommonhandle("相关需求",null), 
			parentname: commonLogic.appcommonhandle("父任务",null), 
			parent: commonLogic.appcommonhandle("父任务",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			storyversion: commonLogic.appcommonhandle("需求版本",null), 
			multiple: commonLogic.appcommonhandle("多人任务",null), 
			type: commonLogic.appcommonhandle("任务类型",null), 
			status: commonLogic.appcommonhandle("任务状态",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			mailto: commonLogic.appcommonhandle("抄送给",null), 
			mailtopk: commonLogic.appcommonhandle("抄送给",null), 
			eststarted: commonLogic.appcommonhandle("预计开始",null), 
			deadline: commonLogic.appcommonhandle("截止日期",null), 
			estimate: commonLogic.appcommonhandle("最初预计",null), 
			consumed: commonLogic.appcommonhandle("总计消耗",null), 
			left: commonLogic.appcommonhandle("预计剩余",null), 
			openedby: commonLogic.appcommonhandle("由谁创建",null), 
			realstarted: commonLogic.appcommonhandle("实际开始",null), 
			finishedby: commonLogic.appcommonhandle("由谁完成",null), 
			finisheddate: commonLogic.appcommonhandle("实际完成",null), 
			canceledby: commonLogic.appcommonhandle("由谁取消",null), 
			canceleddate: commonLogic.appcommonhandle("取消时间",null), 
			closedby: commonLogic.appcommonhandle("由谁关闭",null), 
			closedreason: commonLogic.appcommonhandle("关闭原因",null), 
			closeddate: commonLogic.appcommonhandle("关闭时间",null), 
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null), 
			desc: commonLogic.appcommonhandle("任务描述",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			files: commonLogic.appcommonhandle("附件",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_name_like: commonLogic.appcommonhandle("任务名称",null), 
			n_type_eq: commonLogic.appcommonhandle("任务类型",null), 
			n_status_eq: commonLogic.appcommonhandle("任务状态",null), 
			n_projectname_eq: commonLogic.appcommonhandle("所属项目",null), 
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
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
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
		deuiaction1_confirmstorychangecz: {
			caption: commonLogic.appcommonhandle("确认",null),
			tip: 'deuiaction1_confirmstorychangecz',
		},
		deuiaction1_startmobteamtask: {
			caption: commonLogic.appcommonhandle("开始",null),
			tip: 'deuiaction1_startmobteamtask',
		},
		deuiaction1_assigntaskmob: {
			caption: commonLogic.appcommonhandle("指派",null),
			tip: 'deuiaction1_assigntaskmob',
		},
		deuiaction1_activemobtask: {
			caption: commonLogic.appcommonhandle("激活",null),
			tip: 'deuiaction1_activemobtask',
		},
		deuiaction1_consumedmobtaskteam: {
			caption: commonLogic.appcommonhandle("工时",null),
			tip: 'deuiaction1_consumedmobtaskteam',
		},
		deuiaction1_finishtask1: {
			caption: commonLogic.appcommonhandle("完成",null),
			tip: 'deuiaction1_finishtask1',
		},
		deuiaction1_pausemobteamtask: {
			caption: commonLogic.appcommonhandle("暂停",null),
			tip: 'deuiaction1_pausemobteamtask',
		},
		deuiaction1_restartmobteamtask: {
			caption: commonLogic.appcommonhandle("继续",null),
			tip: 'deuiaction1_restartmobteamtask',
		},
		deuiaction1_canceltaskmob: {
			caption: commonLogic.appcommonhandle("取消",null),
			tip: 'deuiaction1_canceltaskmob',
		},
		deuiaction1_closetaskmob: {
			caption: commonLogic.appcommonhandle("关闭",null),
			tip: 'deuiaction1_closetaskmob',
		},
		deuiaction1_mobmainedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_mobmainedit',
		},
		deuiaction1_deletemob: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: 'deuiaction1_deletemob',
		},
	},
	assmobmdview9mdctrl_quicktoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("更多",null),
			tip: 'deuiaction1',
		},
	},
	mycompletetaskmobmdviewrighttoolbar_toolbar: {
	},
	myplanstomorrowtaskmobmdviewrighttoolbar_toolbar: {
	},
	mycompletetaskmobmdview1righttoolbar_toolbar: {
	},
	assmobmdviewmdctrl_quicktoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("更多",null),
			tip: 'deuiaction1',
		},
	},
	assmobmdviewrighttoolbar_toolbar: {
		tbitem1_myassmore: {
			caption: commonLogic.appcommonhandle("更多",null),
			tip: 'tbitem1_myassmore',
		},
		tbitem2: {
			caption: commonLogic.appcommonhandle("-",null),
			tip: 'tbitem2',
		},
		deuiaction1: {
			caption: commonLogic.appcommonhandle("Filter",null),
			tip: 'deuiaction1',
		},
	},
};