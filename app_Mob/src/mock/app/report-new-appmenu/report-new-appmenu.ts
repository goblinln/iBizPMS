import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

import Mock from 'mockjs'
const Random = Mock.Random;

// 获取应用数据
mock.onGet('v7/report-newappmenu').reply((config: any) => {
    let status = MockAdapter.mockStatus(config);
    return [status, {
        name: 'db_appmenu2_appmenu',
        items:  [
            {
	id: '833da28842c93ba4072811fb42ee75ed',
	name: 'menuitem1',
	text: '日报',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '日报',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/daily.svg',
	textcls: '',
	appfunctag: 'AppFunc5',
	resourcetag: '',
},
            {
	id: '9f68bb18d313df8409ff7de50e813514',
	name: 'menuitem2',
	text: '周报',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '周报',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/weekly.svg',
	textcls: '',
	appfunctag: 'AppFunc9',
	resourcetag: '',
},
            {
	id: 'bdb40ef1d23d8b53a573fabd01ded34a',
	name: 'menuitem3',
	text: '月报',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '月报',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/monthly.svg',
	textcls: '',
	appfunctag: 'Monthly',
	resourcetag: '',
},
            {
	id: '6e607bbc18a631d63ad35116f561d2ac',
	name: 'menuitem6',
	text: '汇报',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '汇报',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/report.svg',
	textcls: '',
	appfunctag: 'Reportly',
	resourcetag: '',
},
            {
	id: 'd0339eb7529c998d2cb0671a7d914352',
	name: 'menuitem4',
	text: '我收到的',
	type: 'MENUITEM',
	counterid: 'myunreadreportcnt',
	tooltip: '我收到的',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/myreview.svg',
	textcls: '',
	appfunctag: 'AppFunc12',
	resourcetag: '',
},
            {
	id: 'c35b4b4a24cf08c3cb6fa8a5bc2e45df',
	name: 'menuitem5',
	text: '我提交的',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我提交的',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: 'assets/images/submit.svg',
	textcls: '',
	appfunctag: 'AppFunc11',
	resourcetag: '',
},
            {
	id: 'b87833770d09a63869655a25b19368b8',
	name: 'menuitem7',
	text: '月报（待阅）',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '月报（待阅）',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'NeedLookMonthly',
	resourcetag: '',
},
            {
	id: '9118e752cea79b2a40a8c0416629d78a',
	name: 'menuitem8',
	text: '周报(待阅)',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '周报(待阅)',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc13',
	resourcetag: '',
},
        ],
    }];
});

