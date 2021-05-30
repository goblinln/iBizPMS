import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dynafilterid': commonLogic.appcommonhandle("动态搜索栏标识",null),
		'dynafiltername': commonLogic.appcommonhandle("动态搜索栏名称",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'orgid': commonLogic.appcommonhandle("组织机构标识",null),
		'deptid': commonLogic.appcommonhandle("组织部门标识",null),
		'formname': commonLogic.appcommonhandle("表单名称",null),
		'dename': commonLogic.appcommonhandle("实体名称",null),
		'data': commonLogic.appcommonhandle("数据",null),
	},
	};
	return data;
}

export default getLocaleResourceBase;