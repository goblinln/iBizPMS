import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id:  commonLogic.appcommonhandle("编号",null),
    account:  commonLogic.appcommonhandle("所有者",null),
    closedDate:  commonLogic.appcommonhandle("关闭时间",null),
    closedBy:  commonLogic.appcommonhandle("由谁关闭",null),
    type:  commonLogic.appcommonhandle("类型",null),
    end:  commonLogic.appcommonhandle("结束",null),
    desc:  commonLogic.appcommonhandle("描述",null),
    finishedBy:  commonLogic.appcommonhandle("由谁完成",null),
    begin:  commonLogic.appcommonhandle("开始",null),
    idvalue:  commonLogic.appcommonhandle("关联编号",null),
    assignedBy:  commonLogic.appcommonhandle("由谁指派",null),
    finishedDate:  commonLogic.appcommonhandle("完成时间",null),
    cycle:  commonLogic.appcommonhandle("周期",null),
    assignedTo:  commonLogic.appcommonhandle("指派给",null),
    status:  commonLogic.appcommonhandle("状态",null),
    name:  commonLogic.appcommonhandle("待办名称",null),
    assignedDate:  commonLogic.appcommonhandle("指派日期",null),
    pri:  commonLogic.appcommonhandle("优先级",null),
    date:  commonLogic.appcommonhandle("日期",null),
    iBizPrivate:  commonLogic.appcommonhandle("私人事务",null),
    config:  commonLogic.appcommonhandle("config",null),
    config_day:  commonLogic.appcommonhandle("间隔天数",null),
    config_beforedays:  commonLogic.appcommonhandle("提前",null),
    config_week:  commonLogic.appcommonhandle("周期设置周几",null),
    config_month:  commonLogic.appcommonhandle("周期设置月",null),
    config_type:  commonLogic.appcommonhandle("周期类型",null),
    config_end:  commonLogic.appcommonhandle("过期时间",null),
    bug:  commonLogic.appcommonhandle("待办名称",null),
    task:  commonLogic.appcommonhandle("待办名称",null),
    story:  commonLogic.appcommonhandle("待办名称",null),
    date1:  commonLogic.appcommonhandle("日期",null),
    date_disable:  commonLogic.appcommonhandle("待定",null),
    assignedtopk:  commonLogic.appcommonhandle("指派给（选择）",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
  },
	views: {
		newmobeditview: {
			caption: commonLogic.appcommonhandle("快速新建",null),
		},
		usr2mobeditview: {
			caption: commonLogic.appcommonhandle("待办",null),
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
			formitem7: commonLogic.appcommonhandle("颜色选择",null), 
			formitem: commonLogic.appcommonhandle("【多选】部门（指定单位及其下级单位的所有部门多选)",null), 
			assignedtopk: commonLogic.appcommonhandle("【单选】部门（指定单位及其下级单位的所有部门）",null), 
			formitem2: commonLogic.appcommonhandle("【多选】部门（指定单位内的部门多选）",null), 
			formitem1: commonLogic.appcommonhandle("【单选】部门（指定单位内的部门单选）",null), 
			account: commonLogic.appcommonhandle("【单选】指定单位内人员",null), 
			assignedby: commonLogic.appcommonhandle("【多选】指定单位内人员",null), 
			assigneddate: commonLogic.appcommonhandle("【单选】指定单位及下级人员",null), 
			noticeusers: commonLogic.appcommonhandle("【多选】指定单位及下级人员",null), 
			formitem3: commonLogic.appcommonhandle("【单选】单位（仅含指定单位及其下级单位单选）",null), 
			formitem4: commonLogic.appcommonhandle("【多选】单位（仅含指定单位及其下级单位多选）",null), 
			formitem5: commonLogic.appcommonhandle("【单选】单位（全部单位单选）",null), 
			formitem6: commonLogic.appcommonhandle("【多选】单位（全部单位多选）",null), 
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
			noticeusers: commonLogic.appcommonhandle("测试人员",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	zt_todo_form: {
		details: {
			group1: commonLogic.appcommonhandle("待办基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("待办名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("待办名称",null), 
			id: commonLogic.appcommonhandle("编号",null), 
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
	mobeditviewrighttoolbar_toolbar: {
	},
	usr2mobeditviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'deuiaction1',
		},
	},
	moblistviewrighttoolbar_toolbar: {
	},
};