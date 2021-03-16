import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		reals: commonLogic.appcommonhandle("实际情况",null),
		expect: commonLogic.appcommonhandle("预期",null),
		desc: commonLogic.appcommonhandle("步骤",null),
		files: commonLogic.appcommonhandle("附件",null),
		id: commonLogic.appcommonhandle("编号",null),
		type: commonLogic.appcommonhandle("类型",null),
		version: commonLogic.appcommonhandle("版本",null),
		parent: commonLogic.appcommonhandle("编号",null),
		ibizcase: commonLogic.appcommonhandle("用例编号",null),
	},
		views: {
			infogridview9: {
				caption: commonLogic.appcommonhandle("用例库用例步骤",null),
				title: commonLogic.appcommonhandle("用例库用例步骤表格视图（主数据）",null),
			},
		},
		maininfo_grid: {
			columns: {
				case: commonLogic.appcommonhandle("用例编号",null),
				desc: commonLogic.appcommonhandle("步骤",null),
				expect: commonLogic.appcommonhandle("预期",null),
				type: commonLogic.appcommonhandle("类型",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;