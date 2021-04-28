import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

import Mock from 'mockjs'
const Random = Mock.Random;

// 获取应用数据
mock.onGet('v7/myappmenu').reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    return [status, {
        name: 'db_appmenu1_appmenu',
        items:  [
            {
	id: '74a220bb5c6ec3fe2ac72afb4c602c2d',
	name: 'menuitem6',
	text: '我的待办',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我的待办',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/mydb.svg',
	textcls: '',
	appfunctag: 'AppFunc7',
	resourcetag: '',
},
            {
	id: '8e505026674c1486fd24ecf1b9efc969',
	name: 'menuitem5',
	text: '仪表盘',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '仪表盘',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/dashboard.svg',
	textcls: '',
	appfunctag: 'AppFunc8',
	resourcetag: '',
},
            {
	id: 'fbb4deb1f901ca7638ccc674b4d98790',
	name: 'menuitem15',
	text: '我的工作',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我的工作',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/mywork.svg',
	textcls: '',
	appfunctag: 'AppFunc6',
	resourcetag: '',
},
        ],
    }];
});

