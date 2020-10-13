import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

import Mock from 'mockjs'
const Random = Mock.Random;

// 获取应用数据
mock.onGet('v7/app-index-viewappmenu').reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    return [status, {
        name: 'appmenu',
        items:  [
            {
	id: 'c1a10404d32addccb3c940e05a11b920',
	name: 'menuitem1',
	text: '产品',
	type: 'MENUITEM',
	counterid: 'PRODUCTS',
	tooltip: '产品',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'home',
	icon: '',
	textcls: '',
	appfunctag: 'Auto13',
	resourcetag: '',
},
            {
	id: '62d00b8a490cf4b6ef758c0aa3814494',
	name: 'menuitem2',
	text: '项目',
	type: 'MENUITEM',
	counterid: 'PROJECTS',
	tooltip: '项目',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'cube',
	icon: '',
	textcls: '',
	appfunctag: 'Auto18',
	resourcetag: '',
},
            {
	id: '0cfef223143b6805dcf2d802b6e79ea0',
	name: 'menuitem3',
	text: '测试',
	type: 'MENUITEM',
	counterid: 'PRODUCTS',
	tooltip: '测试',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'clipboard',
	icon: '',
	textcls: '',
	appfunctag: 'Auto20',
	resourcetag: '',
},
            {
	id: '1ebe52baa4573adb6c5d44146d0da6a3',
	name: 'menuitem4',
	text: '我的',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我的',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'person',
	icon: '',
	textcls: '',
	appfunctag: 'Auto22',
	resourcetag: '',
},
            {
	id: 'B46864D2-69CB-4432-A520-12632D097F30',
	name: 'menuitem6',
	text: '个人中心',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '个人中心',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc',
	resourcetag: '',
},
            {
	id: 'FA0E885A-486D-47C0-8459-4AC00BF64922',
	name: 'menuitem7',
	text: '用户选择',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '用户选择',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc2',
	resourcetag: '',
},
        ],
    }];
});

