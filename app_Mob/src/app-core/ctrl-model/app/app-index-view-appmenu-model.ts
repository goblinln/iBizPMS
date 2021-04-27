/**
 * AppIndexView 部件模型
 *
 * @export
 * @class AppIndexViewModel
 */
export class AppIndexViewModel {

    /**
     * 菜单项集合
     *
     * @private
     * @type {any[]}
     * @memberof AppIndexViewModel
     */
    private items: any[] = [
        {
	id: 'c1a10404d32addccb3c940e05a11b920',
	name: 'menuitem1',
	text: '产品',
	type: 'MENUITEM',
	counterid: '',
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
	authtag:'Mob-AppIndexView-menuitem1',
},
        {
	id: '62d00b8a490cf4b6ef758c0aa3814494',
	name: 'menuitem2',
	text: '项目',
	type: 'MENUITEM',
	counterid: '',
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
	authtag:'Mob-AppIndexView-menuitem2',
},
        {
	id: '0cfef223143b6805dcf2d802b6e79ea0',
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
	iconcls: 'clipboard',
	icon: '',
	textcls: '',
	appfunctag: 'Auto20',
	resourcetag: '',
	authtag:'Mob-AppIndexView-menuitem3',
},
        {
	id: '1ebe52baa4573adb6c5d44146d0da6a3',
	name: 'menuitem4',
	text: '我的',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '我的',
	expanded: true,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: true,
	iconcls: 'person',
	icon: '',
	textcls: '',
	appfunctag: 'Auto22',
	resourcetag: '',
	authtag:'Mob-AppIndexView-menuitem4',
},
        {
	id: '1c0f7d8bb0f18d1e0596c623d739fbe7',
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
	authtag:'Mob-AppIndexView-menuitem6',
},
        {
	id: '1b6e83d604e5bca3af9f3a905e60f440',
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
	authtag:'Mob-AppIndexView-menuitem7',
},
        {
	id: 'e76dd92a08a5ec0045cde9fe7e659562',
	name: 'menuitem8',
	text: '更新日志',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '更新日志',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc3',
	resourcetag: '',
	authtag:'Mob-AppIndexView-menuitem8',
},
        {
	id: '7aceef16a9ecd690aca5e89d63565d33',
	name: 'menuitem9',
	text: '登录地图test',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '登录地图test',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc4',
	resourcetag: '',
	authtag:'Mob-AppIndexView-menuitem9',
},
        {
	id: '440650b717c9839bb6d06f8589fcc585',
	name: 'menuitem10',
	text: '头像',
	type: 'MENUITEM',
	counterid: '',
	tooltip: '头像',
	expanded: false,
	separator: false,
	hidden: true,
	hidesidebar: false,
	opendefault: false,
	iconcls: '',
	icon: '',
	textcls: '',
	appfunctag: 'AppFunc14',
	resourcetag: '',
	authtag:'Mob-AppIndexView-menuitem10',
},
        {
	id: '58CC5647-6F7F-41D4-9D0B-0305D06E9359',
	name: 'menuitem11',
	text: 'test',
	type: 'MENUITEM',
	counterid: '',
	tooltip: 'test',
	expanded: false,
	separator: false,
	hidden: false,
	hidesidebar: false,
	opendefault: false,
	iconcls: 'male',
	icon: '',
	textcls: '',
	appfunctag: '',
	resourcetag: '',
	authtag:'Mob-AppIndexView-menuitem11',
},
    ];

	/**
	 * 应用功能集合
	 *
	 * @private
	 * @type {any[]}
	 * @memberof AppIndexViewModel
	 */
	private funcs: any[] = [
        {
            appfunctag: 'AppFunc14',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'sys-employeehead-portrait-mob-edit-view', 
            codename: 'sysemployeeheadportraitmobeditview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/sysemployees/:sysemployee?/headportraitmobeditview/:headportraitmobeditview?',
            parameters: [
                { pathName: 'sysemployees', parameterName: 'sysemployee' },
                { pathName: 'headportraitmobeditview', parameterName: 'headportraitmobeditview' },
            ],
        },
        {
            appfunctag: 'Auto20',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'product-test-mob-mdview', 
            codename: 'producttestmobmdview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/products/:product?/testmobmdview/:testmobmdview?',
            parameters: [
                { pathName: 'products', parameterName: 'product' },
                { pathName: 'testmobmdview', parameterName: 'testmobmdview' },
            ],
        },
        {
            appfunctag: 'Auto22',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'app-portal-view2', 
            codename: 'appportalview2',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/appportalview2/:appportalview2?',
            parameters: [
                { pathName: 'appportalview2', parameterName: 'appportalview2' },
            ],
        },
        {
            appfunctag: 'Auto13',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'product-mob-mdview', 
            codename: 'productmobmdview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/products/:product?/mobmdview/:mobmdview?',
            parameters: [
                { pathName: 'products', parameterName: 'product' },
                { pathName: 'mobmdview', parameterName: 'mobmdview' },
            ],
        },
        {
            appfunctag: 'AppFunc3',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'sys-update-log-mob-mdview', 
            codename: 'sysupdatelogmobmdview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/sysupdatelogs/:sysupdatelog?/mobmdview/:mobmdview?',
            parameters: [
                { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                { pathName: 'mobmdview', parameterName: 'mobmdview' },
            ],
        },
        {
            appfunctag: 'AppFunc2',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'user-mob-pickup-view', 
            codename: 'usermobpickupview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/users/:user?/mobpickupview/:mobpickupview?',
            parameters: [
                { pathName: 'users', parameterName: 'user' },
                { pathName: 'mobpickupview', parameterName: 'mobpickupview' },
            ],
        },
        {
            appfunctag: 'AppFunc',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'sys-employee-login-mob-edit-view', 
            codename: 'sysemployeeloginmobeditview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/sysemployees/:sysemployee?/loginmobeditview/:loginmobeditview?',
            parameters: [
                { pathName: 'sysemployees', parameterName: 'sysemployee' },
                { pathName: 'loginmobeditview', parameterName: 'loginmobeditview' },
            ],
        },
        {
            appfunctag: 'AppFunc4',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'action-mob-map-view', 
            codename: 'actionmobmapview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/actions/:action?/mobmapview/:mobmapview?',
            parameters: [
                { pathName: 'actions', parameterName: 'action' },
                { pathName: 'mobmapview', parameterName: 'mobmapview' },
            ],
        },
        {
            appfunctag: 'Auto18',
            appfuncyype: 'APPVIEW',
            openmode: '', 
            componentname: 'project-mob-mdview', 
            codename: 'projectmobmdview',
            deResParameters: [],
            routepath: '/appindexview/:appindexview?/projects/:project?/mobmdview/:mobmdview?',
            parameters: [
                { pathName: 'projects', parameterName: 'project' },
                { pathName: 'mobmdview', parameterName: 'mobmdview' },
            ],
        },
	];

    /**
     * 获取所有菜单项集合
     *
     * @returns {any[]}
     * @memberof AppIndexViewModel
     */
    public getAppMenuItems(): any[] {
        return this.items;
    }

    /**
     * 获取所有应用功能集合
     *
     * @returns {any[]}
     * @memberof AppIndexViewModel
     */
    public getAppFuncs(): any[] {
        return this.funcs;
    }
}
// 默认导出
export default AppIndexViewModel;