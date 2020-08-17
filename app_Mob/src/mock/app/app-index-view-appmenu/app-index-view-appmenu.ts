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
	id: '53549F95-1F2F-44E3-8943-0789AC42088D',
	name: 'menuitem1',
	text: '项目',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '项目',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'home',
	icon: '',
	textcls: '',
	appfunctag: '_2',
	resourcetag: '',
},
            {
	id: 'B578310F-43D0-4142-9323-F619297E717A',
	name: 'menuitem2',
	text: '分类',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '分类',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'alarm',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
            {
	id: '9218AC38-DA73-4891-B93D-EF38795487C6',
	name: 'menuitem3',
	text: '测试',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '测试',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'snow',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
},
        ],
    }];
});

