import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    ibzplantempletid: commonLogic.appcommonhandle("产品计划模板标识",null),
    ibzplantempletname: commonLogic.appcommonhandle("模板名称",null),
    createman: commonLogic.appcommonhandle("建立人",null),
    createdate: commonLogic.appcommonhandle("建立时间",null),
    updateman: commonLogic.appcommonhandle("更新人",null),
    updatedate: commonLogic.appcommonhandle("更新时间",null),
    plans: commonLogic.appcommonhandle("计划",null),
    product: commonLogic.appcommonhandle("产品",null),
    plantempletdetail: commonLogic.appcommonhandle("计划项",null),
    acl: commonLogic.appcommonhandle("权限",null),
    createmanname: commonLogic.appcommonhandle("创建人姓名",null),
  },
	views: {
		editview: {
			caption: commonLogic.appcommonhandle("计划模板",null),
      		title: commonLogic.appcommonhandle("产品计划模板编辑视图",null),
		},
		gridview: {
			caption: commonLogic.appcommonhandle("计划模板",null),
      		title: commonLogic.appcommonhandle("产品计划模板表格视图",null),
		},
		optionview: {
			caption: commonLogic.appcommonhandle("计划模板",null),
      		title: commonLogic.appcommonhandle("产品计划模板选项操作视图",null),
		},
	},
	main_form: {
		details: {
			group1: commonLogic.appcommonhandle("产品计划模板基本信息",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("产品计划模板标识",null), 
			srfmajortext: commonLogic.appcommonhandle("模板名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			ibz_plantempletname: commonLogic.appcommonhandle("模板名称",null), 
			acl: commonLogic.appcommonhandle("权限",null), 
			plans: commonLogic.appcommonhandle("计划",null), 
			product: commonLogic.appcommonhandle("产品",null), 
			ibz_plantempletid: commonLogic.appcommonhandle("产品计划模板标识",null), 
		},
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			ibz_plantempletname: commonLogic.appcommonhandle("模板名称",null),
			updateman: commonLogic.appcommonhandle("更新人",null),
			updatedate: commonLogic.appcommonhandle("更新时间",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	default_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
		},
		uiactions: {
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("保存并关闭",null),
			tip: commonLogic.appcommonhandle("保存并关闭",null),
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: commonLogic.appcommonhandle("新建",null),
		},
		deuiaction4: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: commonLogic.appcommonhandle("删除",null),
		},
		seperator1: {
			caption: commonLogic.appcommonhandle("",null),
			tip: commonLogic.appcommonhandle("",null),
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("刷新",null),
			tip: commonLogic.appcommonhandle("刷新",null),
		},
		seperator3: {
			caption: commonLogic.appcommonhandle("",null),
			tip: commonLogic.appcommonhandle("",null),
		},
		deuiaction5: {
			caption: commonLogic.appcommonhandle("导出",null),
			tip: commonLogic.appcommonhandle("导出",null),
		},
	},
};