import commonLogic from '@/locale/logic/common/common-logic';

export default {
  fields: {
    role: commonLogic.appcommonhandle("角色",null),
    root: commonLogic.appcommonhandle("编号",null),
    limited: commonLogic.appcommonhandle("受限用户",null),
    total: commonLogic.appcommonhandle("总计可用",null),
    username: commonLogic.appcommonhandle("用户",null),
    order: commonLogic.appcommonhandle("排序",null),
    days: commonLogic.appcommonhandle("可用工日",null),
    type: commonLogic.appcommonhandle("团队类型",null),
    estimate: commonLogic.appcommonhandle("最初预计",null),
    account: commonLogic.appcommonhandle("用户",null),
    consumed: commonLogic.appcommonhandle("总计消耗",null),
    id: commonLogic.appcommonhandle("编号",null),
    join: commonLogic.appcommonhandle("加盟日",null),
    hours: commonLogic.appcommonhandle("可用工时/天",null),
    left: commonLogic.appcommonhandle("预计剩余",null),
  },
	views: {
		gridview9: {
			caption: commonLogic.appcommonhandle("团队",null),
      		title: commonLogic.appcommonhandle("团队",null),
		},
		gridview9_edit: {
			caption: commonLogic.appcommonhandle("团队",null),
      		title: commonLogic.appcommonhandle("团队",null),
		},
	},
	mainedit_grid: {
		columns: {
			account: commonLogic.appcommonhandle("用户",null),
			estimate: commonLogic.appcommonhandle("预计",null),
			consumed: commonLogic.appcommonhandle("总计消耗",null),
			left: commonLogic.appcommonhandle("预计剩余",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	maineditrow_grid: {
		columns: {
			account: commonLogic.appcommonhandle("用户",null),
			estimate: commonLogic.appcommonhandle("预计",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	gridview9_edittoolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("新建行",null),
			tip: commonLogic.appcommonhandle("新建行",null),
		},
	},
	gridview9toolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("新建行",null),
			tip: commonLogic.appcommonhandle("新建行",null),
		},
	},
};