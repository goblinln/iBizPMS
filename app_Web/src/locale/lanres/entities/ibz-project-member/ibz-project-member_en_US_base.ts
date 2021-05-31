import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'secondmember': commonLogic.appcommonhandle("团队成员（二）",null),
		'rd': commonLogic.appcommonhandle("发布负责人",null),
		'qd': commonLogic.appcommonhandle("测试负责人",null),
		'teamembers': commonLogic.appcommonhandle("全部成员",null),
		'thirdmember': commonLogic.appcommonhandle("团队成员（三）",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'fristmember': commonLogic.appcommonhandle("团队成员（一）",null),
		'po': commonLogic.appcommonhandle("产品负责人",null),
		'fourthmember': commonLogic.appcommonhandle("团队成员（四）",null),
		'pm': commonLogic.appcommonhandle("项目负责人",null),
		'fifthmember': commonLogic.appcommonhandle("团队成员（五）",null),
		'sixthmember': commonLogic.appcommonhandle("团队成员（六）",null),
		'name': commonLogic.appcommonhandle("项目名称",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'acl': commonLogic.appcommonhandle("访问控制",null),
		'closedby': commonLogic.appcommonhandle("由谁关闭",null),
		'canceledby': commonLogic.appcommonhandle("由谁取消",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
	},
		views: {
			'editview9': {
				caption: commonLogic.appcommonhandle("相关成员",null),
				title: commonLogic.appcommonhandle("项目相关成员编辑视图（相关成员）",null),
			},
		},
		main_form: {
			details: {
				'button1': commonLogic.appcommonhandle("团队管理",null), 
				'group1': commonLogic.appcommonhandle("项目相关成员基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("项目名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'pm': commonLogic.appcommonhandle("",null), 
				'po': commonLogic.appcommonhandle("",null), 
				'qd': commonLogic.appcommonhandle("",null), 
				'rd': commonLogic.appcommonhandle("",null), 
				'fristmember': commonLogic.appcommonhandle("",null), 
				'secondmember': commonLogic.appcommonhandle("",null), 
				'thirdmember': commonLogic.appcommonhandle("",null), 
				'fourthmember': commonLogic.appcommonhandle("",null), 
				'fifthmember': commonLogic.appcommonhandle("",null), 
				'sixthmember': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("",null), 
			},
			uiactions: {
			ibzprojectmember_teammanagement: commonLogic.appcommonhandle("团队管理",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;