import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id: commonLogic.appcommonhandle("主键标识",null),
    name: commonLogic.appcommonhandle("产品名称",null),
    po: commonLogic.appcommonhandle("产品负责人",null),
    plan: commonLogic.appcommonhandle("计划",null),
    begin: commonLogic.appcommonhandle("开始日期",null),
    end: commonLogic.appcommonhandle("结束日期",null),
    waitstorycnt: commonLogic.appcommonhandle("草稿",null),
    activestorycnt: commonLogic.appcommonhandle("激活",null),
    changedstorycnt: commonLogic.appcommonhandle("已变更",null),
    closedstorycnt: commonLogic.appcommonhandle("已关闭",null),
    storycnt: commonLogic.appcommonhandle("总计",null),
    bugcnt: commonLogic.appcommonhandle("Bug数",null),
    waitstagestorycnt: commonLogic.appcommonhandle("未开始阶段需求数量",null),
    planedstagestorycnt: commonLogic.appcommonhandle("已计划阶段需求数量",null),
    projectedstagestorycnt: commonLogic.appcommonhandle("已立项阶段需求数量",null),
    developingstagestorycnt: commonLogic.appcommonhandle("研发中阶段需求数量",null),
    developedstagestorycnt: commonLogic.appcommonhandle("研发完毕阶段需求数量",null),
    testingstagestorycnt: commonLogic.appcommonhandle("测试中阶段需求数量",null),
    testedstagestorycnt: commonLogic.appcommonhandle("测试完毕阶段需求数量",null),
    verifiedstagestorycnt: commonLogic.appcommonhandle("已验收阶段需求数量",null),
    releasedstagestorycnt: commonLogic.appcommonhandle("已发布阶段需求数量",null),
    closedstagestorycnt: commonLogic.appcommonhandle("已关闭阶段需求数量",null),
    waitstagestoryhours: commonLogic.appcommonhandle("未开始阶段需求工时",null),
    closedstagestoryhours: commonLogic.appcommonhandle("已关闭阶段需求工时",null),
    releasedstagestoryhours: commonLogic.appcommonhandle("已发布阶段需求工时",null),
    verifiedstagestoryhours: commonLogic.appcommonhandle("已验收阶段需求工时",null),
    testedstagestoryhours: commonLogic.appcommonhandle("测试完毕阶段需求工时",null),
    testingstagestoryhours: commonLogic.appcommonhandle("测试中阶段需求工时",null),
    developedstagestoryhours: commonLogic.appcommonhandle("研发完毕阶段需求工时",null),
    developingstagestoryhours: commonLogic.appcommonhandle("研发中阶段需求工时",null),
    projectedstagestoryhours: commonLogic.appcommonhandle("已立项阶段需求工时",null),
    planedstagestoryhours: commonLogic.appcommonhandle("已计划阶段需求工时",null),
    totalhours: commonLogic.appcommonhandle("总工时",null),
  },
	views: {
		storysumgridview: {
			caption: commonLogic.appcommonhandle("需求汇总",null),
      		title: commonLogic.appcommonhandle("产品汇总表表格视图",null),
		},
		pochartview9: {
			caption: commonLogic.appcommonhandle("产品汇总表",null),
      		title: commonLogic.appcommonhandle("产品汇总表图表视图",null),
		},
		gridview: {
			caption: commonLogic.appcommonhandle("产品汇总表",null),
      		title: commonLogic.appcommonhandle("产品汇总表表格视图",null),
		},
		usr2gridview: {
			caption: commonLogic.appcommonhandle("产品汇总表",null),
      		title: commonLogic.appcommonhandle("产品计划数和需求数实体表格视图",null),
		},
		storyhourssumgridview: {
			caption: commonLogic.appcommonhandle("需求工时汇总",null),
      		title: commonLogic.appcommonhandle("产品汇总表表格视图",null),
		},
		editview: {
			caption: commonLogic.appcommonhandle("产品汇总表",null),
      		title: commonLogic.appcommonhandle("产品汇总表编辑视图",null),
		},
		qachartview9: {
			caption: commonLogic.appcommonhandle("产品汇总表",null),
      		title: commonLogic.appcommonhandle("产品汇总表图表视图",null),
		},
		usr3gridview: {
			caption: commonLogic.appcommonhandle("产品汇总表",null),
      		title: commonLogic.appcommonhandle("产品汇总表表格视图",null),
		},
	},
	main_form: {
		details: {
			group1: commonLogic.appcommonhandle("产品汇总表基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			group2: commonLogic.appcommonhandle("操作信息",null), 
			formpage2: commonLogic.appcommonhandle("其它",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("主键标识",null), 
			srfmajortext: commonLogic.appcommonhandle("产品名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("主键标识",null), 
		},
		uiactions: {
		},
	},
	productplancntandstorycnt_po_grid: {
		columns: {
			name: commonLogic.appcommonhandle("产品名称",null),
			plan: commonLogic.appcommonhandle("计划数",null),
			storycnt: commonLogic.appcommonhandle("需求数",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	productbugcnt_qa_grid: {
		columns: {
			name: commonLogic.appcommonhandle("产品名称",null),
			bugcnt: commonLogic.appcommonhandle("创建bug数",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	productstoryhourssum_grid: {
		columns: {
			name: commonLogic.appcommonhandle("产品名称",null),
			po: commonLogic.appcommonhandle("产品负责人",null),
			waitstagestoryhours: commonLogic.appcommonhandle("未开始",null),
			planedstagestoryhours: commonLogic.appcommonhandle("已计划",null),
			projectedstagestoryhours: commonLogic.appcommonhandle("已立项",null),
			developingstagestoryhours: commonLogic.appcommonhandle("研发中",null),
			developedstagestoryhours: commonLogic.appcommonhandle("研发完毕",null),
			testingstagestoryhours: commonLogic.appcommonhandle("测试中",null),
			testedstagestoryhours: commonLogic.appcommonhandle("测试完毕",null),
			verifiedstagestoryhours: commonLogic.appcommonhandle("已验收",null),
			releasedstagestoryhours: commonLogic.appcommonhandle("已发布",null),
			closedstagestoryhours: commonLogic.appcommonhandle("已关闭",null),
			totalhours: commonLogic.appcommonhandle("总工时",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			name: commonLogic.appcommonhandle("产品名称",null),
			po: commonLogic.appcommonhandle("产品负责人",null),
			plan: commonLogic.appcommonhandle("计划",null),
			begin: commonLogic.appcommonhandle("开始日期",null),
			end: commonLogic.appcommonhandle("结束日期",null),
			waitstorycnt: commonLogic.appcommonhandle("草稿",null),
			activestorycnt: commonLogic.appcommonhandle("激活",null),
			changedstorycnt: commonLogic.appcommonhandle("已变更",null),
			closedstorycnt: commonLogic.appcommonhandle("已关闭",null),
			storycnt: commonLogic.appcommonhandle("总计",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	productstorysum_grid: {
		columns: {
			name: commonLogic.appcommonhandle("产品名称",null),
			po: commonLogic.appcommonhandle("产品负责人",null),
			waitstagestorycnt: commonLogic.appcommonhandle("未开始",null),
			planedstagestorycnt: commonLogic.appcommonhandle("已计划",null),
			projectedstagestorycnt: commonLogic.appcommonhandle("已立项",null),
			developingstagestorycnt: commonLogic.appcommonhandle("研发中",null),
			developedstagestorycnt: commonLogic.appcommonhandle("研发完毕",null),
			testingstagestorycnt: commonLogic.appcommonhandle("测试中",null),
			testedstagestorycnt: commonLogic.appcommonhandle("测试完毕",null),
			verifiedstagestorycnt: commonLogic.appcommonhandle("已验收",null),
			releasedstagestorycnt: commonLogic.appcommonhandle("已发布",null),
			closedstagestorycnt: commonLogic.appcommonhandle("已关闭",null),
			storycnt: commonLogic.appcommonhandle("总计",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	productcreatestory_po_chart: {
		nodata:commonLogic.appcommonhandle("",null),
	},
	productbugcnt_qa_chart: {
		nodata:commonLogic.appcommonhandle("",null),
	},
	productstorysum_searchform: {
		details: {
			rawitem1: commonLogic.appcommonhandle("",null), 
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			closed: commonLogic.appcommonhandle("关闭产品",null), 
			n_id_eq: commonLogic.appcommonhandle("产品",null), 
		},
		uiactions: {
		},
	},
	default_searchform: {
		details: {
			rawitem1: commonLogic.appcommonhandle("",null), 
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			closed: commonLogic.appcommonhandle("关闭产品",null), 
			expired: commonLogic.appcommonhandle("过期计划",null), 
			n_id_eq: commonLogic.appcommonhandle("产品",null), 
			n_plan_eq: commonLogic.appcommonhandle("计划",null), 
		},
		uiactions: {
		},
	},
	editviewtoolbar_toolbar: {
		tbitem3: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: commonLogic.appcommonhandle("保存",null),
		},
		tbitem4: {
			caption: commonLogic.appcommonhandle("保存并新建",null),
			tip: commonLogic.appcommonhandle("保存并新建",null),
		},
		tbitem5: {
			caption: commonLogic.appcommonhandle("保存并关闭",null),
			tip: commonLogic.appcommonhandle("保存并关闭",null),
		},
		tbitem7: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: commonLogic.appcommonhandle("删除",null),
		},
	},
	storyhourssumgridviewtoolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("导出",null),
			tip: commonLogic.appcommonhandle("导出",null),
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("导出",null),
			tip: commonLogic.appcommonhandle("导出",null),
		},
	},
	storysumgridviewtoolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("导出",null),
			tip: commonLogic.appcommonhandle("导出",null),
		},
	},
};