import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id:  commonLogic.appcommonhandle("产品编号",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    storyCnt:  commonLogic.appcommonhandle("需求总数",null),
    productPlanCnt:  commonLogic.appcommonhandle("计划总数",null),
    releaseCnt:  commonLogic.appcommonhandle("发布总数",null),
    waitStoryCnt:  commonLogic.appcommonhandle("未开始需求数",null),
    plannedStoryCnt:  commonLogic.appcommonhandle("已计划需求数",null),
    developingStoryCnt:  commonLogic.appcommonhandle("开发中需求数",null),
    testingStoryCnt:  commonLogic.appcommonhandle("测试中需求数",null),
    releasedStoryCnt:  commonLogic.appcommonhandle("已发布需求数",null),
    unEndProductPlanCnt:  commonLogic.appcommonhandle("未过期计划数",null),
    resProjectCnt:  commonLogic.appcommonhandle("关联项目数",null),
    unDoneResProjectCnt:  commonLogic.appcommonhandle("未完成关联项目数",null),
    normalReleaseCnt:  commonLogic.appcommonhandle("维护中发布数",null),
    activeStoryCnt:  commonLogic.appcommonhandle("激活需求数",null),
    activeBugCnt:  commonLogic.appcommonhandle("未解决Bug数",null),
    name:  commonLogic.appcommonhandle("产品名称",null),
    assignToMeBugCnt:  commonLogic.appcommonhandle("指派给我的Bug数",null),
    notClosedBugCnt:  commonLogic.appcommonhandle("未关闭Bug数",null),
    bugCnt:  commonLogic.appcommonhandle("所有Bug数",null),
    unconfirmBugCnt:  commonLogic.appcommonhandle("未确认Bug数",null),
    yesterdayClosedBugCnt:  commonLogic.appcommonhandle("昨天关闭Bug数",null),
    yesterdayconfirmbugcnt:  commonLogic.appcommonhandle("昨天确认Bug数",null),
    yesterdayresolvedbugcnt:  commonLogic.appcommonhandle("昨天解决Bug数",null),
    postponedprojectcnt:  commonLogic.appcommonhandle("已延期",null),
    currproject:  commonLogic.appcommonhandle("当前项目",null),
    status:  commonLogic.appcommonhandle("状态",null),
    code:  commonLogic.appcommonhandle("产品代号",null),
    type:  commonLogic.appcommonhandle("产品类型",null),
    order1:  commonLogic.appcommonhandle("产品排序",null),
    istop:  commonLogic.appcommonhandle("是否置顶",null),
  },
	views: {
		mobmdview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("产品详情",null),
		},
		testmobmdview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("New",null),
			tip: 'tbitem1',
		},
	},
	testmobmdviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("New",null),
			tip: 'tbitem1',
		},
	},
};