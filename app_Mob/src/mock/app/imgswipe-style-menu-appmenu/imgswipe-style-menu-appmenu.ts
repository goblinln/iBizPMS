import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

import Mock from 'mockjs'
const Random = Mock.Random;

// 获取应用数据
mock.onGet('v7/imgswipe-style-menuappmenu').reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    return [status, {
        name: 'db_appmenu1_appmenu',
        items:  [
            {
	id: '000d9220401358534fd0d34ccc145d9d',
	name: 'menuitem1',
	text: '京东',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '京东',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'http://img.itmop.com/upload/2016-1/2016116172054.jpg',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
            {
	id: 'bc0a0736c36a3723eede9e8424c931f9',
	name: 'menuitem2',
	text: '天猫',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '天猫',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'https://consumer.gucheng.com/UploadFiles_6578/201711/2017112810575805.jpg',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
            {
	id: 'ea1afc1ded5298692e0151a04ec18ac3',
	name: 'menuitem3',
	text: '亚马逊',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '亚马逊',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3696020296,2643788514&fm=26&gp=0.jpg',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
        ],
    }];
});

