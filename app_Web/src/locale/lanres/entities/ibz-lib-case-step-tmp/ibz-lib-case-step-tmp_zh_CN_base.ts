import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    version: commonLogic.appcommonhandle("版本",null),
    ibizcase: commonLogic.appcommonhandle("用例编号",null),
    expect: commonLogic.appcommonhandle("预期",null),
    files: commonLogic.appcommonhandle("附件",null),
    id: commonLogic.appcommonhandle("编号",null),
    reals: commonLogic.appcommonhandle("实际情况",null),
    desc: commonLogic.appcommonhandle("步骤",null),
    parent: commonLogic.appcommonhandle("编号",null),
    type: commonLogic.appcommonhandle("类型",null),
  },
	views: {
		gridview9: {
			caption: commonLogic.appcommonhandle("用例库用例步骤",null),
      		title: commonLogic.appcommonhandle("用例库用例步骤表格视图",null),
		},
	},
	main_grid: {
		columns: {
			desc: commonLogic.appcommonhandle("步骤",null),
			expect: commonLogic.appcommonhandle("预期",null),
			type: commonLogic.appcommonhandle("类型",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	gridview9toolbar_toolbar: {
		deuiaction2: {
			caption: commonLogic.appcommonhandle("新建行",null),
			tip: commonLogic.appcommonhandle("新建行",null),
		},
	},
};