import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id: "编号",
    account: "所有者",
    closeddate: "关闭时间",
    closedby: "由谁关闭",
    type: "类型",
    end: "结束",
    desc: "描述",
    finishedby: "由谁完成",
    begin: "开始",
    idvalue: "关联编号",
    assignedby: "由谁指派",
    finisheddate: "完成时间",
    cycle: "周期",
    assignedto: "指派给",
    status: "状态",
    name: "待办名称",
    assigneddate: "指派日期",
    pri: "优先级",
    date: "日期",
    ibizprivate: "私人事务",
    config: "config",
    config_day: "间隔天数",
    config_beforedays: "提前",
    config_week: "周期设置周几",
    config_month: "周期设置月",
    config_type: "周期类型",
    config_end: "过期时间",
    bug: "待办名称",
    task: "待办名称",
    story: "待办名称",
    date1: "日期",
    date_disable: "待定",
    assignedtopk: "指派给（选择）",
    noticeusers: "消息通知用户",
  },
	views: {
		newmobeditview: {
			caption: commonLogic.appcommonhandle("快速新建",null),
		},
		moblistview: {
			caption: commonLogic.appcommonhandle("待办",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("我的待办",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("待办",null),
		},
		moboptionview: {
			caption: commonLogic.appcommonhandle("待办",null),
		},
	},
	mobnew_form: {
		details: {
			group1: commonLogic.appcommonhandle("待办事宜表基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("待办名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("待办名称",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			begin: commonLogic.appcommonhandle("开始",null), 
			end: commonLogic.appcommonhandle("结束",null), 
			type: commonLogic.appcommonhandle("类型",null), 
			bug: commonLogic.appcommonhandle("待办名称",null), 
			idvalue: commonLogic.appcommonhandle("关联编号",null), 
			task: commonLogic.appcommonhandle("待办名称",null), 
			config_type: commonLogic.appcommonhandle("周期类型",null), 
			cycle: commonLogic.appcommonhandle("周期",null), 
			story: commonLogic.appcommonhandle("待办名称",null), 
			private: commonLogic.appcommonhandle("私人事务",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			status: commonLogic.appcommonhandle("人员",null), 
			assignedtopk: commonLogic.appcommonhandle("部门",null), 
			account: commonLogic.appcommonhandle("【单选】指定单位内人员",null), 
			assignedby: commonLogic.appcommonhandle("【多选】指定单位内人员",null), 
			assigneddate: commonLogic.appcommonhandle("【单选】指定单位及下级人员",null), 
			assignedto: commonLogic.appcommonhandle("【多选】指定单位及下级人员",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	assmob_form: {
		details: {
			group1: commonLogic.appcommonhandle("指派给",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("待办名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			assignedtopk: commonLogic.appcommonhandle("指派给",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			begin: commonLogic.appcommonhandle("开始时间",null), 
			end: commonLogic.appcommonhandle("结束时间",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("待办事宜表基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("待办名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("待办名称",null), 
			pri: commonLogic.appcommonhandle("优先级",null), 
			date1: commonLogic.appcommonhandle("日期",null), 
			begin: commonLogic.appcommonhandle("开始",null), 
			end: commonLogic.appcommonhandle("结束",null), 
			type: commonLogic.appcommonhandle("类型",null), 
			status: commonLogic.appcommonhandle("状态",null), 
			assignedby: commonLogic.appcommonhandle("由谁指派",null), 
			assigneddate: commonLogic.appcommonhandle("指派日期",null), 
			assignedto: commonLogic.appcommonhandle("指派给",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: '新建',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
	},
	moblistviewrighttoolbar_toolbar: {
	},
};