import { ViewTool } from '@/utils';

/**
 * Zentao 部件模型
 *
 * @export
 * @class ZentaoModel
 */
export default class ZentaoModel {

    /**
     * 菜单项集合
     *
     * @private
     * @type {any[]}
     * @memberof ZentaoModel
     */
    private items: any[] = [
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
        	authtag:'Web-zentao-top_menus',
        	localetag: 'app.menus.zentao.top_menus',
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
                	appfunctag: 'Auto23',
                	appfuncyype: 'APPVIEW',
                	viewname: 'ibz-favorites-tab-exp-view',
                	authtag:'Web-zentao-menuitem12',
                	localetag: 'app.menus.zentao.menuitem12',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'ibz-my-territory-tab-exp-view',
                	authtag:'Web-zentao-menuitem9',
                	localetag: 'app.menus.zentao.menuitem9',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'product-portal-view',
                	authtag:'Web-zentao-menuitem3',
                	localetag: 'app.menus.zentao.menuitem3',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'project-portal-view',
                	authtag:'Web-zentao-menuitem2',
                	localetag: 'app.menus.zentao.menuitem2',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'test-portal-view',
                	authtag:'Web-zentao-menuitem1',
                	localetag: 'app.menus.zentao.menuitem1',
                }
                ,
                		        {
                	id: 'CC6C6D64-B2D5-4E49-AE4F-996AA44C8685',
                	name: 'menuitem16',
                	text: '文档',
                	type: 'MENUITEM',
                	counterid: '',
                	tooltip: '文档',
                	expanded: false,
                	separator: false,
                	hidden: false,
                	hidesidebar: false,
                	opendefault: false,
                	iconcls: '',
                	icon: '',
                	textcls: '',
                	appfunctag: 'AppFunc4',
                	appfuncyype: 'APPVIEW',
                	viewname: 'doc-lib-lib-tab-exp-view',
                	authtag:'Web-zentao-menuitem16',
                	localetag: 'app.menus.zentao.menuitem16',
                }
                ,
                		        {
                	id: '61D98BC4-BCF2-4CD0-AF45-6693EF3CCC72',
                	name: 'menuitem23',
                	text: '计划模板',
                	type: 'MENUITEM',
                	counterid: '',
                	tooltip: '计划模板',
                	expanded: false,
                	separator: false,
                	hidden: false,
                	hidesidebar: false,
                	opendefault: false,
                	iconcls: '',
                	icon: '',
                	textcls: '',
                	appfunctag: 'AppFunc7',
                	appfuncyype: 'APPVIEW',
                	viewname: 'ibz-plan-templet-grid-view',
                	authtag:'Web-zentao-menuitem23',
                	localetag: 'app.menus.zentao.menuitem23',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'ibz-my-territory-stats-tab-exp-view',
                	authtag:'Web-zentao-menuitem22',
                	localetag: 'app.menus.zentao.menuitem22',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'user-year-work-stats-edit-view',
                	authtag:'Web-zentao-menuitem25',
                	localetag: 'app.menus.zentao.menuitem25',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'product-html-view',
                	authtag:'Web-zentao-menuitem7',
                	localetag: 'app.menus.zentao.menuitem7',
                }
                ,
                		        {
                	id: '1CC661E6-9593-4C82-98B5-01496AE8D80C',
                	name: 'menuitem26',
                	text: '人员',
                	type: 'MENUITEM',
                	counterid: '',
                	tooltip: '人员',
                	expanded: false,
                	separator: false,
                	hidden: false,
                	hidesidebar: false,
                	opendefault: false,
                	iconcls: '',
                	icon: '',
                	textcls: '',
                	appfunctag: 'AppFunc8',
                	appfuncyype: 'APPVIEW',
                	viewname: 'task-tree-exp-view',
                	authtag:'Web-zentao-menuitem26',
                	localetag: 'app.menus.zentao.menuitem26',
                }
                ,
                		        {
                	id: '1A933810-32D7-43CE-A664-5ABF5C5735A0',
                	name: 'menuitem27',
                	text: '全文检索',
                	type: 'MENUITEM',
                	counterid: '',
                	tooltip: '全文检索',
                	expanded: false,
                	separator: false,
                	hidden: false,
                	hidesidebar: false,
                	opendefault: false,
                	iconcls: '',
                	icon: '',
                	textcls: '',
                	appfunctag: 'AppFunc9',
                	appfuncyype: 'APPVIEW',
                	viewname: 'ibizpro-indexlist-view',
                	authtag:'Web-zentao-menuitem27',
                	localetag: 'app.menus.zentao.menuitem27',
                }
                ,
                		        {
                	id: '50D22ECD-CF16-4313-856A-645B1E837DE7',
                	name: 'menuitem24',
                	text: '系统管理',
                	type: 'MENUITEM',
                	counterid: '',
                	tooltip: '系统管理',
                	expanded: false,
                	separator: false,
                	hidden: false,
                	hidesidebar: false,
                	opendefault: false,
                	iconcls: '',
                	icon: '',
                	textcls: '',
                	appfunctag: '',
                	authtag:'Web-zentao-menuitem24',
                	localetag: 'app.menus.zentao.menuitem24',
                	items: [
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
                        	appfunctag: 'AppFunc2',
                        	appfuncyype: 'OPENHTMLPAGE',
                        	authtag:'Web-zentao-menuitem11',
                        	localetag: 'app.menus.zentao.menuitem11',
                        }
                        ,
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
                        	appfuncyype: 'APPVIEW',
                        	viewname: 'ibz-lib-grid-view',
                        	authtag:'Web-zentao-menuitem10',
                        	localetag: 'app.menus.zentao.menuitem10',
                        }
                        ,
                        		        {
                        	id: 'DCD81E08-ABA8-4E86-9C77-FB1DC04A51A8',
                        	name: 'menuitem21',
                        	text: '系统配置',
                        	type: 'MENUITEM',
                        	counterid: '',
                        	tooltip: '系统配置',
                        	expanded: false,
                        	separator: false,
                        	hidden: false,
                        	hidesidebar: false,
                        	opendefault: false,
                        	iconcls: '',
                        	icon: '',
                        	textcls: '',
                        	appfunctag: 'AppFunc6',
                        	appfuncyype: 'APPVIEW',
                        	viewname: 'ibzpro-config-grid-view',
                        	authtag:'Web-zentao-menuitem21',
                        	localetag: 'app.menus.zentao.menuitem21',
                        }
                        ,
                        		        {
                        	id: '80D6A552-4F24-4273-B535-1685F0A22CBF',
                        	name: 'menuitem19',
                        	text: '汇报角色角色',
                        	type: 'MENUITEM',
                        	counterid: '',
                        	tooltip: '汇报角色角色',
                        	expanded: false,
                        	separator: false,
                        	hidden: false,
                        	hidesidebar: false,
                        	opendefault: false,
                        	iconcls: '',
                        	icon: '',
                        	textcls: '',
                        	appfunctag: 'AppFunc5',
                        	appfuncyype: 'APPVIEW',
                        	viewname: 'ibz-report-role-config-grid-view',
                        	authtag:'Web-zentao-menuitem19',
                        	localetag: 'app.menus.zentao.menuitem19',
                        }
                        ,
                        		        {
                        	id: 'C2324D75-EADF-4116-8EF3-2C62A3C8AB18',
                        	name: 'menuitem17',
                        	text: '插件管理',
                        	type: 'MENUITEM',
                        	counterid: '',
                        	tooltip: '插件管理',
                        	expanded: false,
                        	separator: false,
                        	hidden: false,
                        	hidesidebar: false,
                        	opendefault: false,
                        	iconcls: '',
                        	icon: '',
                        	textcls: '',
                        	appfunctag: 'PluginManagement',
                        	appfuncyype: 'APPVIEW',
                        	viewname: 'ibz-my-territory-usr3-tab-exp-view',
                        	authtag:'Web-zentao-menuitem17',
                        	localetag: 'app.menus.zentao.menuitem17',
                        }
                        ,
                        		        {
                        	id: '5AC6121D-1379-4944-8C3F-E7A2BAA9C7B3',
                        	name: 'menuitem8',
                        	text: '更新日志',
                        	type: 'MENUITEM',
                        	counterid: '',
                        	tooltip: '更新日志',
                        	expanded: false,
                        	separator: false,
                        	hidden: false,
                        	hidesidebar: false,
                        	opendefault: false,
                        	iconcls: '',
                        	icon: '',
                        	textcls: '',
                        	appfunctag: 'AppFunc3',
                        	appfuncyype: 'APPVIEW',
                        	viewname: 'sys-update-log-grid-view',
                        	authtag:'Web-zentao-menuitem8',
                        	localetag: 'app.menus.zentao.menuitem8',
                        }
                        ,
                	],
                }
                ,
        	],
        }
        ,
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
        	authtag:'Web-zentao-left_exp',
        	localetag: 'app.menus.zentao.left_exp',
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'product-left-sidebar-list-view',
                	resourcetag: 'PRODUCTLEFT',
                	authtag:'Web-zentao-menuitem4',
                	localetag: 'app.menus.zentao.menuitem4',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'project-left-sidebar-list-view',
                	authtag:'Web-zentao-menuitem5',
                	localetag: 'app.menus.zentao.menuitem5',
                }
                ,
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
                	appfuncyype: 'APPVIEW',
                	viewname: 'product-test-left-sidebar-list-view',
                	authtag:'Web-zentao-menuitem6',
                	localetag: 'app.menus.zentao.menuitem6',
                }
                ,
        	],
        }
        ,
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
        	authtag:'Web-zentao-bottom_exp',
        	localetag: 'app.menus.zentao.bottom_exp',
        }
        ,
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
        	authtag:'Web-zentao-footer_center',
        	localetag: 'app.menus.zentao.footer_center',
        }
        ,
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
        	appfunctag: 'Auto26',
        	appfuncyype: 'APPVIEW',
        	viewname: 'bugmaindashboardview-link',
        	authtag:'Web-zentao-menuitem13',
        	localetag: 'app.menus.zentao.menuitem13',
        }
        ,
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
        	appfunctag: 'Auto26',
        	appfuncyype: 'APPVIEW',
        	viewname: 'bugmaindashboardview-link',
        	authtag:'Web-zentao-menuitem14',
        	localetag: 'app.menus.zentao.menuitem14',
        }
        ,
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
        	appfunctag: 'Auto27',
        	appfuncyype: 'APPVIEW',
        	viewname: 'taskmaindashboardview-link',
        	authtag:'Web-zentao-menuitem15',
        	localetag: 'app.menus.zentao.menuitem15',
        }
        ,
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
        	appfunctag: 'Auto29',
        	appfuncyype: 'APPVIEW',
        	viewname: 'todomaindashboardview-link',
        	authtag:'Web-zentao-menuitem18',
        	localetag: 'app.menus.zentao.menuitem18',
        }
        ,
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
        	appfunctag: 'Auto32',
        	appfuncyype: 'APPVIEW',
        	viewname: 'storymaindashboardview-link',
        	authtag:'Web-zentao-menuitem20',
        	localetag: 'app.menus.zentao.menuitem20',
        }
        ,
    ];

	/**
	 * 应用功能集合
	 *
	 * @private
	 * @type {any[]}
	 * @memberof ZentaoModel
	 */
	private funcs: any[] = [
        {
            appfunctag: 'AppFunc',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzmyterritorystatstabexpview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzmyterritories/:ibzmyterritory?/statstabexpview/:statstabexpview?',
            parameters: [
                { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                { pathName: 'statstabexpview', parameterName: 'statstabexpview' },
            ],
        },
        {
            appfunctag: 'Auto8',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'producthtmlview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/products/:product?/htmlview/:htmlview?',
            parameters: [
                { pathName: 'products', parameterName: 'product' },
                { pathName: 'htmlview', parameterName: 'htmlview' },
            ],
        },
        {
            appfunctag: 'Auto2',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'productleftsidebarlistview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/products/:product?/leftsidebarlistview/:leftsidebarlistview?',
            parameters: [
                { pathName: 'products', parameterName: 'product' },
                { pathName: 'leftsidebarlistview', parameterName: 'leftsidebarlistview' },
            ],
        },
        {
            appfunctag: 'AppFunc6',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzproconfiggridview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzproconfigs/:ibzproconfig?/gridview/:gridview?',
            parameters: [
                { pathName: 'ibzproconfigs', parameterName: 'ibzproconfig' },
                { pathName: 'gridview', parameterName: 'gridview' },
            ],
        },
        {
            appfunctag: 'Auto6',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'projectportalview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/projectportalview/:projectportalview?',
            parameters: [
                { pathName: 'projectportalview', parameterName: 'projectportalview' },
            ],
        },
        {
            appfunctag: 'AppFunc3',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'sysupdateloggridview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/sysupdatelogs/:sysupdatelog?/gridview/:gridview?',
            parameters: [
                { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
                { pathName: 'gridview', parameterName: 'gridview' },
            ],
        },
        {
            appfunctag: 'Auto27',
            appfuncyype: 'APPVIEW',
            openmode: 'INDEXVIEWTAB',
            codename: 'taskmaindashboardview_link',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/tasks/:task?/maindashboardview_link/:maindashboardview_link?',
            parameters: [
                { pathName: 'tasks', parameterName: 'task' },
                { pathName: 'maindashboardview_link', parameterName: 'maindashboardview_link' },
            ],
        },
        {
            appfunctag: 'Auto10',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzmyterritorytabexpview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzmyterritories/:ibzmyterritory?/tabexpview/:tabexpview?',
            parameters: [
                { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                { pathName: 'tabexpview', parameterName: 'tabexpview' },
            ],
        },
        {
            appfunctag: 'Auto32',
            appfuncyype: 'APPVIEW',
            openmode: 'INDEXVIEWTAB',
            codename: 'storymaindashboardview_link',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/stories/:story?/maindashboardview_link/:maindashboardview_link?',
            parameters: [
                { pathName: 'stories', parameterName: 'story' },
                { pathName: 'maindashboardview_link', parameterName: 'maindashboardview_link' },
            ],
        },
        {
            appfunctag: 'Auto1',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'productportalview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/productportalview/:productportalview?',
            parameters: [
                { pathName: 'productportalview', parameterName: 'productportalview' },
            ],
        },
        {
            appfunctag: 'Auto26',
            appfuncyype: 'APPVIEW',
            openmode: 'INDEXVIEWTAB',
            codename: 'bugmaindashboardview_link',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/bugs/:bug?/maindashboardview_link/:maindashboardview_link?',
            parameters: [
                { pathName: 'bugs', parameterName: 'bug' },
                { pathName: 'maindashboardview_link', parameterName: 'maindashboardview_link' },
            ],
        },
        {
            appfunctag: 'AppFunc9',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibizpro_indexlistview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibizpro_indices/:ibizpro_index?/listview/:listview?',
            parameters: [
                { pathName: 'ibizpro_indices', parameterName: 'ibizpro_index' },
                { pathName: 'listview', parameterName: 'listview' },
            ],
        },
        {
            appfunctag: 'PluginManagement',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzmyterritoryusr3tabexpview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzmyterritories/:ibzmyterritory?/usr3tabexpview/:usr3tabexpview?',
            parameters: [
                { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
                { pathName: 'usr3tabexpview', parameterName: 'usr3tabexpview' },
            ],
        },
        {
            appfunctag: 'Auto5',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'projectleftsidebarlistview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/projects/:project?/leftsidebarlistview/:leftsidebarlistview?',
            parameters: [
                { pathName: 'projects', parameterName: 'project' },
                { pathName: 'leftsidebarlistview', parameterName: 'leftsidebarlistview' },
            ],
        },
        {
            appfunctag: 'Auto29',
            appfuncyype: 'APPVIEW',
            openmode: 'INDEXVIEWTAB',
            codename: 'todomaindashboardview_link',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/todos/:todo?/maindashboardview_link/:maindashboardview_link?',
            parameters: [
                { pathName: 'todos', parameterName: 'todo' },
                { pathName: 'maindashboardview_link', parameterName: 'maindashboardview_link' },
            ],
        },
        {
            appfunctag: 'Auto9',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'testportalview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/testportalview/:testportalview?',
            parameters: [
                { pathName: 'testportalview', parameterName: 'testportalview' },
            ],
        },
        {
            appfunctag: 'AppFunc2',
            appfuncyype: 'OPENHTMLPAGE',
        },
        {
            appfunctag: 'Auto11',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'producttestleftsidebarlistview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/products/:product?/testleftsidebarlistview/:testleftsidebarlistview?',
            parameters: [
                { pathName: 'products', parameterName: 'product' },
                { pathName: 'testleftsidebarlistview', parameterName: 'testleftsidebarlistview' },
            ],
        },
        {
            appfunctag: 'AppFunc8',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'tasktreeexpview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/tasks/:task?/treeexpview/:treeexpview?',
            parameters: [
                { pathName: 'tasks', parameterName: 'task' },
                { pathName: 'treeexpview', parameterName: 'treeexpview' },
            ],
        },
        {
            appfunctag: 'AppFunc7',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzplantempletgridview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzplantemplets/:ibzplantemplet?/gridview/:gridview?',
            parameters: [
                { pathName: 'ibzplantemplets', parameterName: 'ibzplantemplet' },
                { pathName: 'gridview', parameterName: 'gridview' },
            ],
        },
        {
            appfunctag: 'Auto23',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzfavoritestabexpview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzfavorites/:ibzfavorites?/tabexpview/:tabexpview?',
            parameters: [
                { pathName: 'ibzfavorites', parameterName: 'ibzfavorites' },
                { pathName: 'tabexpview', parameterName: 'tabexpview' },
            ],
        },
        {
            appfunctag: 'Auto15',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzlibgridview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzlibs/:ibzlib?/gridview/:gridview?',
            parameters: [
                { pathName: 'ibzlibs', parameterName: 'ibzlib' },
                { pathName: 'gridview', parameterName: 'gridview' },
            ],
        },
        {
            appfunctag: 'AppFunc5',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'ibzreportroleconfiggridview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/ibzreportroleconfigs/:ibzreportroleconfig?/gridview/:gridview?',
            parameters: [
                { pathName: 'ibzreportroleconfigs', parameterName: 'ibzreportroleconfig' },
                { pathName: 'gridview', parameterName: 'gridview' },
            ],
        },
        {
            appfunctag: 'AppFunc4',
            appfuncyype: 'APPVIEW',
            openmode: '',
            codename: 'docliblibtabexpview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/doclibs/:doclib?/libtabexpview/:libtabexpview?',
            parameters: [
                { pathName: 'doclibs', parameterName: 'doclib' },
                { pathName: 'libtabexpview', parameterName: 'libtabexpview' },
            ],
        },
        {
            appfunctag: 'POAppFunc',
            appfuncyype: 'APPVIEW',
            openmode: 'DRAWER_TOP',
            codename: 'useryearworkstatseditview',
            deResParameters: [],
            routepath: '/ibizpms/:ibizpms?/useryearworkstats/:useryearworkstats?/editview/:editview?',
            parameters: [
                { pathName: 'useryearworkstats', parameterName: 'useryearworkstats' },
                { pathName: 'editview', parameterName: 'editview' },
            ],
        },
	];

	/**
	 * 根据当前路由查找激活菜单
	 *
	 * @param {*} route
	 * @returns {*}
	 * @memberof ZentaoModel
	 */
	public findActiveMenuByRoute(route: any): any {
		if (route) {
			const func = this.funcs.find((item: any) => {
				if (item.openmode === '') {
					const url: string = ViewTool.buildUpRoutePath(route, route.params, [], item.parameters, [], {});
					return url === route.fullPath;
				}
			});
            if (func) {
			    return this.findMenuByFuncTag(func.appfunctag);
            }
		}
	}

	/**
	 * 根据应用功能id查找菜单项
	 *
	 * @param {string} tag
	 * @param {any[]} [menus=this.items]
	 * @returns {*}
	 * @memberof ZentaoModel
	 */
	public findMenuByFuncTag(tag: string, menus: any[] = this.items): any {
		let menu: any;
		menus.every((item: any) => {
			if (item.appfunctag === tag) {
				menu = item;
				return false;
			}
			if (item.items) {
				menu = this.findMenuByFuncTag(tag, item.items);
				if (menu) {
					return false;
				}
			}
			return true;
		});
		return menu;
	}

	/**
	 * 查找默认打开菜单
	 *
	 * @param {any[]} [menus=this.items]
	 * @returns {*}
	 * @memberof ZentaoModel
	 */
	public findDefaultOpenMenu(menus: any[] = this.items): any {
		let menu: any;
		menus.every((item: any) => {
			if (item.opendefault === true) {
				menu = item;
				return false;
			}
			if (item.items) {
				menu = this.findMenuByFuncTag(item.items);
				if (menu) {
					return false;
				}
			}
			return true;
		});
		return menu;
	}

    /**
     * 获取所有菜单项集合
     *
     * @returns {any[]}
     * @memberof ZentaoModel
     */
    public getAppMenuItems(): any[] {
        return this.items;
    }

	/**
	 * 根据名称获取菜单组
	 *
	 * @param {string} name
	 * @returns {*}
	 * @memberof ZentaoModel
	 */
	public getMenuGroup(name: string): any {
		return this.items.find((item: any) => Object.is(item.name, name));
	}

    /**
     * 获取所有应用功能集合
     *
     * @returns {any[]}
     * @memberof ZentaoModel
     */
    public getAppFuncs(): any[] {
        return this.funcs;
    }
}