import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id:  commonLogic.appcommonhandle("编号",null),
    account:  commonLogic.appcommonhandle("所有者",null),
    config_day:  commonLogic.appcommonhandle("间隔天数",null),
    assignedtopk:  commonLogic.appcommonhandle("指派给（选择）",null),
    date1:  commonLogic.appcommonhandle("日期",null),
    config_type:  commonLogic.appcommonhandle("周期类型",null),
    todosn:  commonLogic.appcommonhandle("待办编号",null),
    closedDate:  commonLogic.appcommonhandle("关闭时间",null),
    closedBy:  commonLogic.appcommonhandle("由谁关闭",null),
    type:  commonLogic.appcommonhandle("类型",null),
    end:  commonLogic.appcommonhandle("结束",null),
    desc:  commonLogic.appcommonhandle("描述",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    finishedBy:  commonLogic.appcommonhandle("由谁完成",null),
    begin:  commonLogic.appcommonhandle("开始",null),
    idvalue:  commonLogic.appcommonhandle("关联编号",null),
    assignedBy:  commonLogic.appcommonhandle("由谁指派",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    config_month:  commonLogic.appcommonhandle("周期设置月",null),
    task:  commonLogic.appcommonhandle("待办名称",null),
    bug:  commonLogic.appcommonhandle("待办名称",null),
    finishedDate:  commonLogic.appcommonhandle("完成时间",null),
    cycle:  commonLogic.appcommonhandle("周期",null),
    date_disable:  commonLogic.appcommonhandle("待定",null),
    config_week:  commonLogic.appcommonhandle("周期设置周几",null),
    assignedTo:  commonLogic.appcommonhandle("指派给",null),
    status:  commonLogic.appcommonhandle("状态",null),
    config_beforedays:  commonLogic.appcommonhandle("提前",null),
    name:  commonLogic.appcommonhandle("待办名称",null),
    assignedDate:  commonLogic.appcommonhandle("指派日期",null),
    config_end:  commonLogic.appcommonhandle("过期时间",null),
    cost:  commonLogic.appcommonhandle("费用",null),
    pri:  commonLogic.appcommonhandle("优先级",null),
    date:  commonLogic.appcommonhandle("日期",null),
    story:  commonLogic.appcommonhandle("待办名称",null),
    iBizPrivate:  commonLogic.appcommonhandle("私人事务",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    config:  commonLogic.appcommonhandle("config",null),
  },
	views: {
		newmobeditview: {
			caption: commonLogic.appcommonhandle("快速新建",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("我的待办",null),
		},
		mobredirectview: {
			caption: commonLogic.appcommonhandle("待办",null),
		},
		moboptionview: {
			caption: commonLogic.appcommonhandle("待办",null),
		},
		moblistview: {
			caption: commonLogic.appcommonhandle("待办",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("待办",null),
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
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobeditviewrighttoolbar_toolbar: {
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
	moblistviewrighttoolbar_toolbar: {
	},
};