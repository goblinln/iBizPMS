import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    title:  commonLogic.appcommonhandle("名称",null),
    id:  commonLogic.appcommonhandle("编号",null),
    begin:  commonLogic.appcommonhandle("开始日期",null),
    desc:  commonLogic.appcommonhandle("描述",null),
    end:  commonLogic.appcommonhandle("结束日期",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    order:  commonLogic.appcommonhandle("排序",null),
    parentName:  commonLogic.appcommonhandle("父计划名称",null),
    branch:  commonLogic.appcommonhandle("平台/分支",null),
    parent:  commonLogic.appcommonhandle("父计划",null),
    product:  commonLogic.appcommonhandle("产品",null),
    statuss:  commonLogic.appcommonhandle("状态",null),
    future:  commonLogic.appcommonhandle("待定",null),
    delta:  commonLogic.appcommonhandle("周期",null),
    oldtitle:  commonLogic.appcommonhandle("上一次计划名称",null),
    storyCnt:  commonLogic.appcommonhandle("需求数",null),
    bugCnt:  commonLogic.appcommonhandle("bug数",null),
    isexpired:  commonLogic.appcommonhandle("是否过期",null),
    estimatecnt:  commonLogic.appcommonhandle("工时数",null),
    beginstr:  commonLogic.appcommonhandle("开始日期",null),
    endstr:  commonLogic.appcommonhandle("结束日期",null),
    plantemplet:  commonLogic.appcommonhandle("计划模板",null),
  },
	views: {
		mobmdview: {
			caption: commonLogic.appcommonhandle("产品计划",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("产品计划",null),
		},
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("产品计划",null),
		},
		usr2mobpickupmdview: {
			caption: commonLogic.appcommonhandle("产品计划",null),
		},
		editmobeditview: {
			caption: commonLogic.appcommonhandle("产品计划",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("产品计划",null),
		},
	},
	mobmaintable_form: {
		details: {
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("productplan基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			product: commonLogic.appcommonhandle("产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			title: commonLogic.appcommonhandle("名称",null), 
			oldtitle: commonLogic.appcommonhandle("",null), 
			future: commonLogic.appcommonhandle("待定",null), 
			begin: commonLogic.appcommonhandle("开始日期",null), 
			delta: commonLogic.appcommonhandle("周期",null), 
			end: commonLogic.appcommonhandle("结束日期",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobnewform_form: {
		details: {
			grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("productplan基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			product: commonLogic.appcommonhandle("产品",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			title: commonLogic.appcommonhandle("名称",null), 
			oldtitle: commonLogic.appcommonhandle("",null), 
			begin: commonLogic.appcommonhandle("开始日期",null), 
			future: commonLogic.appcommonhandle("",null), 
			end: commonLogic.appcommonhandle("结束日期",null), 
			delta: commonLogic.appcommonhandle("",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart3: commonLogic.appcommonhandle("",null), 
			grouppanel3: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("产品计划基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			branch: commonLogic.appcommonhandle("平台/分支",null), 
			title: commonLogic.appcommonhandle("名称",null), 
			begin: commonLogic.appcommonhandle("开始日期",null), 
			end: commonLogic.appcommonhandle("结束日期",null), 
			storycnt: commonLogic.appcommonhandle("需求数",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_title_like: commonLogic.appcommonhandle("名称",null), 
			n_begin_gtandeq: commonLogic.appcommonhandle("开始日期",null), 
			n_end_ltandeq: commonLogic.appcommonhandle("结束日期",null), 
		},
		uiactions: {
		},
	},
	editmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
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
		deuiaction1_mobmainedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: 'deuiaction1_mobmainedit',
		},
		deuiaction1_mobdelete: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: 'deuiaction1_mobdelete',
		},
	},
};