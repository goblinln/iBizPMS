import commonLogic from '@/locale/logic/common/common-logic';
function getLocaleResourceBase(){
	const data:any = {
		appdename: commonLogic.appcommonhandle("子需求", null),
		fields: {
			assignedto: commonLogic.appcommonhandle("指派给",null),
			childstories: commonLogic.appcommonhandle("细分需求",null),
			plan: commonLogic.appcommonhandle("所属计划",null),
			version: commonLogic.appcommonhandle("版本号",null),
			assigneddate: commonLogic.appcommonhandle("指派日期",null),
			pri: commonLogic.appcommonhandle("优先级",null),
			linkstories: commonLogic.appcommonhandle("相关需求",null),
			status: commonLogic.appcommonhandle("当前状态",null),
			estimate: commonLogic.appcommonhandle("预计工时",null),
			revieweddate: commonLogic.appcommonhandle("评审时间",null),
			title: commonLogic.appcommonhandle("需求名称",null),
			sourcenote: commonLogic.appcommonhandle("来源备注",null),
			reviewedby: commonLogic.appcommonhandle("由谁评审",null),
			substatus: commonLogic.appcommonhandle("子状态",null),
			stagedby: commonLogic.appcommonhandle("设置阶段者",null),
			openedby: commonLogic.appcommonhandle("由谁创建",null),
			openeddate: commonLogic.appcommonhandle("创建日期",null),
			id: commonLogic.appcommonhandle("编号",null),
			source: commonLogic.appcommonhandle("需求来源",null),
			closedreason: commonLogic.appcommonhandle("关闭原因",null),
			color: commonLogic.appcommonhandle("标题颜色",null),
			mailto: commonLogic.appcommonhandle("抄送给",null),
			deleted: commonLogic.appcommonhandle("已删除",null),
			keywords: commonLogic.appcommonhandle("关键词",null),
			lasteditedby: commonLogic.appcommonhandle("最后修改",null),
			stage: commonLogic.appcommonhandle("所处阶段",null),
			closeddate: commonLogic.appcommonhandle("关闭日期	",null),
			closedby: commonLogic.appcommonhandle("由谁关闭",null),
			type: commonLogic.appcommonhandle("需求类型",null),
			lastediteddate: commonLogic.appcommonhandle("最后修改日期",null),
			path: commonLogic.appcommonhandle("模块路径",null),
			parentname: commonLogic.appcommonhandle("父需求名称",null),
			modulename: commonLogic.appcommonhandle("所属模块名称",null),
			productname: commonLogic.appcommonhandle("产品名称",null),
			frombug: commonLogic.appcommonhandle("来源Bug",null),
			parent: commonLogic.appcommonhandle("父需求",null),
			module: commonLogic.appcommonhandle("所属模块",null),
			product: commonLogic.appcommonhandle("所属产品",null),
			duplicatestory: commonLogic.appcommonhandle("重复需求ID",null),
			branch: commonLogic.appcommonhandle("平台/分支",null),
			tobug: commonLogic.appcommonhandle("转Bug",null),
			spec: commonLogic.appcommonhandle("需求描述",null),
			verify: commonLogic.appcommonhandle("验收标准",null),
			result: commonLogic.appcommonhandle("评审结果",null),
			comment: commonLogic.appcommonhandle("备注",null),
			isleaf: commonLogic.appcommonhandle("是否子需求",null),
			files: commonLogic.appcommonhandle("附件",null),
			branchname: commonLogic.appcommonhandle("平台/分支",null),
			versionc: commonLogic.appcommonhandle("版本号",null),
			modulename1: commonLogic.appcommonhandle("所属模块名称",null),
			project: commonLogic.appcommonhandle("项目",null),
			preversion: commonLogic.appcommonhandle("之前的版本",null),
			neednotreview: commonLogic.appcommonhandle("不需要评审",null),
			isfavorites: commonLogic.appcommonhandle("是否收藏",null),
			ischild: commonLogic.appcommonhandle("是否可以细分",null),
			mailtoconact: commonLogic.appcommonhandle("联系人",null),
			mailtopk: commonLogic.appcommonhandle("抄送给",null),
			assignedtopk: commonLogic.appcommonhandle("指派给（选择）",null),
			noticeusers: commonLogic.appcommonhandle("消息通知用户",null),
			ibiz_sourceobject: commonLogic.appcommonhandle("来源对象",null),
			sourceobject: commonLogic.appcommonhandle("来源对象",null),
			ibiz_id: commonLogic.appcommonhandle("IBIZ标识",null),
			sourcename: commonLogic.appcommonhandle("来源对象名称",null),
			sourceid: commonLogic.appcommonhandle("来源对象标识",null),
			ibiz_sourceid: commonLogic.appcommonhandle("来源对象标识",null),
			ibiz_sourcename: commonLogic.appcommonhandle("来源对象名称",null),
			storypoints: commonLogic.appcommonhandle("故事点",null),
		},
			views: {
				subgridview: {
					caption: commonLogic.appcommonhandle("需求细分",null),
					title: commonLogic.appcommonhandle("需求细分",null),
				},
			},
			substorynew_grid: {
				columns: {
					modulename: commonLogic.appcommonhandle("所属模块名称",null),
					plan: commonLogic.appcommonhandle("所属计划",null),
					title: commonLogic.appcommonhandle("需求名称",null),
					spec: commonLogic.appcommonhandle("需求描述",null),
					pri: commonLogic.appcommonhandle("优先级",null),
					storypoints: commonLogic.appcommonhandle("故事点",null),
					estimate: commonLogic.appcommonhandle("预计工时",null),
					neednotreview: commonLogic.appcommonhandle("需要评审",null),
					product: commonLogic.appcommonhandle("所属产品",null),
					parent: commonLogic.appcommonhandle("父需求",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			subgridviewtoolbar_toolbar: {
				deuiaction2: {
					caption: commonLogic.appcommonhandle("新建行",null),
					tip: commonLogic.appcommonhandle("新建行",null),
				},
				deuiaction3: {
					caption: commonLogic.appcommonhandle("保存行",null),
					tip: commonLogic.appcommonhandle("保存行",null),
				},
			},
		};
		return data;
}
export default getLocaleResourceBase;