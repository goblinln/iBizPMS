import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

import Mock from 'mockjs'
const Random = Mock.Random;

// 获取应用数据
mock.onGet('v7/zentaoappmenu').reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    return [status, {
        name: 'appmenu',
        items:  [
            {
	id: '2be499b03e8aba2785cf63e079c901d4',
	name: 'top_menus',
	text: '顶部菜单',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '顶部菜单',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
	items: [
		{
	id: 'b454720d9937098dbf2a8c63ff255e49',
	name: 'menuitem12',
	text: '我的收藏',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我的收藏',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: true,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_2',
	resourcetag: '',
},
		{
	id: 'abe5c771826140b55b809218260136c6',
	name: 'menuitem9',
	text: '我的地盘',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我的地盘',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto10',
	resourcetag: '',
},
		{
	id: '326619b4fb6af93bdeed04e5dcbf029a',
	name: 'menuitem3',
	text: '产品主页',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '产品主页',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto1',
	resourcetag: '',
},
		{
	id: '743c132e78e3c231d195ecd66fbd4a85',
	name: 'menuitem2',
	text: '项目主页',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '项目主页',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto6',
	resourcetag: '',
},
		{
	id: '8c190ff0dc037afa1c9bd4c6a473313d',
	name: 'menuitem1',
	text: '测试主页',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '测试主页',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto9',
	resourcetag: '',
},
		{
	id: '832B4ECF-B32B-48DD-B1EE-CE25A5A11DD7',
	name: 'menuitem22',
	text: '统计',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '统计',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc',
	resourcetag: '',
},
		{
	id: '2A5D7A95-FD8A-46D2-829A-B297A1CE6207',
	name: 'menuitem25',
	text: '年度统计',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '年度统计',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'POAppFunc',
	resourcetag: '',
},
		{
	id: '28e753fc0b33a6fadf12f5f37150bb5a',
	name: 'menuitem10',
	text: '用例库',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '用例库',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto15',
	resourcetag: 'IBZ_LIB_M',
},
		{
	id: 'dd9b338bb75db00a0974af4a4669e432',
	name: 'menuitem11',
	text: '用户',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '用户',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto19',
	resourcetag: 'IBZ_LIB_M',
},
		{
	id: 'b6196a3350c20e3bba9943cfba16be09',
	name: 'menuitem7',
	text: 'iBiz软件生产管理',
	type: 'MENUITEM',
	counterid: '',
	tooltip: 'iBiz软件生产管理',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'Auto8',
	resourcetag: '',
},
		{
	id: '9CDF7B6E-C426-410E-AD69-3F06CC4BE6AA',
	name: 'menuitem16',
	text: 'plus',
	type: 'MENUITEM',
	counterid: '',
	tooltip: 'plus',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: 'PLUS',
	items: [
		{
	id: 'A3AFF7A2-3281-408C-91F9-FDC4E87954AA',
	name: 'menuitem19',
	text: '产品',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '产品',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_8',
	resourcetag: '',
},
		{
	id: 'BF204127-161E-4825-9833-E1B47DD447A7',
	name: 'menuitem21',
	text: '模块',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '模块',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_4',
	resourcetag: '',
},
		{
	id: '865A7F0C-1E1E-4400-89E5-DBD4EAC19426',
	name: 'menuitem17',
	text: '需求',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '需求',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_3',
	resourcetag: '',
},
	],
},
	],
},
            {
	id: '0c09750969133187f7284fc78d6abb46',
	name: 'left_exp',
	text: '左侧分页导航',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '左侧分页导航',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
	items: [
		{
	id: 'd69dc5da5fec042124ed71785112a2ed',
	name: 'menuitem4',
	text: '产品',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '产品',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'fa fa-cubes',
	icon: '',
	textcls: '',
	appfunctag: 'Auto2',
	resourcetag: 'PRODUCTLEFT',
},
		{
	id: '1bc9bde6c2735da9d300fc5925d5ecf8',
	name: 'menuitem5',
	text: '项目',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '项目',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'fa fa-stack-exchange',
	icon: '',
	textcls: '',
	appfunctag: 'Auto5',
	resourcetag: '',
},
		{
	id: 'da31be11a700a5b2f5f63874113a175d',
	name: 'menuitem6',
	text: '测试',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '测试',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'fa fa-cogs',
	icon: '',
	textcls: '',
	appfunctag: 'Auto11',
	resourcetag: '',
},
	],
},
            {
	id: 'fa4f78c182d429862b14a5a2f7c516e1',
	name: 'bottom_exp',
	text: '底部导航区',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '底部导航区',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
            {
	id: '65951b0f0e0015d9af6f4f0e0ee337ae',
	name: 'footer_center',
	text: '底部中间菜单',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '底部中间菜单',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
            {
	id: '37a7f7d513421b09ab4a40876e30422b',
	name: 'menuitem13',
	text: 'bug菜单项',
	type: 'MENUITEM',
	counterid: '',
	tooltip: 'bug菜单项',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_5',
	resourcetag: '',
},
            {
	id: 'a6eb25e83c2701563f5f920bed8a04d1',
	name: 'menuitem14',
	text: '菜单项',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '菜单项',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_5',
	resourcetag: '',
},
            {
	id: '42b1cfedaf40277bf4241f1c080be056',
	name: 'menuitem15',
	text: '菜单项',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '菜单项',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_6',
	resourcetag: '',
},
            {
	id: '000cbeeaea954c8c3760dae0abf16e48',
	name: 'menuitem18',
	text: '菜单项',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '菜单项',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_7',
	resourcetag: '',
},
            {
	id: 'c74953e0910a0fe5f994dad32125ae76',
	name: 'menuitem20',
	text: '菜单项',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '菜单项',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: '_9',
	resourcetag: '',
},
        ],
    }];
});

