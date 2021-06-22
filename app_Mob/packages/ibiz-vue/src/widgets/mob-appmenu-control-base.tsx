import { AppControlBase } from "./app-control-base";
import { AppFuncService } from '../app-service';
import { IPSAppMenu, IPSAppMenuItem } from '@ibiz/dynamic-model-api';
import { AuthServiceBase, Util } from "ibiz-core";
import { AppMobMenuService } from "../ctrl-service";

/**
 * 菜单部件基类
 *
 * @export
 * @class AppControlBase
 * @extends {MobAppMenuControlBase}
 */
export class MobAppMenuControlBase extends AppControlBase {

    /**
     * 菜单部件实例
     * 
     * @memberof MobAppMenuControlBase
     */
    public controlInstance!: IPSAppMenu;

    /**
     * 部件名称
     * 
     * @memberof MobAppMenuControlBase
     */
    public name!: string;

    /**
     * 部件服务对象
     *
     * @type {*}
     * @memberof AppControlBase
     */
    public service!: AppMobMenuService;

    /**
     * 部件样式
     *
     * @protected
     * @type {(string | 'ICONVIEW' | 'LISTVIEW' | 'SWIPERVIEW' | 'LISTVIEW2' | 'LISTVIEW3' | 'LISTVIEW4')}   默认空字符串 | 图标视图 | 列表视图 | 图片滑动视图 | 列表视图（无刷新） | 列表视图（无滑动） | 列表视图（无背景）
     * @memberof MobAppMenuControlBase
     */
    public controlStyle!: string | 'ICONVIEW' | 'LISTVIEW' | 'SWIPERVIEW' | 'LISTVIEW2' | 'LISTVIEW3' | 'LISTVIEW4';

    /**
     * 菜单数据
     *
     * @private
     * @type {any[]}
     * @memberof MobAppMenuControlBase
     */
    public menus: any[] = [];


    /**
     * 当前模式，菜单在左侧还是在底部，true为左侧
     *
     * @type {*}
     * @memberof MobAppMenuControlBase
     */
    public mode: any;

    /**
     * 当前菜单是否在默认视图上
     *
     * @type {*}
     * @memberof MobAppMenuControlBase
     */
    public isDefaultPage?: boolean;

    /**
     * 默认打开视图
     *
     * @type {*}
     * @memberof MobAppMenuControlBase
     */
    public defPSAppView: any;

    /**
     * 默认激活的index
     *
     * @type {*}
     * @memberof MobAppMenuControlBase
     */
    public defaultActive: any = null;

    /**
     * 当前选中主题
     *
     * @type {*}
     * @memberof MobAppMenuControlBase
     */
    public selectTheme: any;

    protected defaultOpeneds: any[] = [];

    /**
     * 计数器数据
     *
     * @type {*}
     * @memberof MobAppMenuControlBase
     */
    protected counterdata: any = {};

    public authService!: AuthServiceBase;


    /**
     * 菜单项选中处理
     *
     * @param {*} index
     * @param {any[]} indexs
     * @returns
     * @memberof MobAppMenuControlBase
     */
    public select(menuName: any) {
        let item = this.compute(this.menus, menuName);
        if (Object.keys(item).length === 0) {
            return;
        }
        this.click(item);
    }

    /**
     * 获取菜单项数据
     *
     * @private
     * @param {any[]} items
     * @param {string} name
     * @returns
     * @memberof MobAppMenuControlBase
     */
    private compute(items: any[], name: string) {
        return items.find((_item: IPSAppMenuItem) => {
            return _item.name == name;
        })
    }

    /**
     * 菜单点击
     *  
     * @param menuItem
     * @memberof MobAppMenuControlBase
     */
    public click(item: any) {
        let tempContext: any = Util.deepCopy(this.context);
        if (item.getPSNavigateContexts) {
            const localContext = Util.formatNavParam(item.getPSNavigateContexts);
            Object.assign(tempContext, localContext);
        }
        if (item.getPSAppFunc) {
            const appFuncs = this.service.getAllFuncs();
            const appFunc = appFuncs?.find((element: any) => {
                return element.appfunctag === item.getPSAppFunc?.codeName;
            });
            if (appFunc) {
                AppFuncService.getInstance().executeApplication(appFunc, tempContext);
            }
        } else {
            console.warn(this.$t('app.commonWords.noAssign'));
        }
    }

    /**
     * 计算有效菜单项
     *
     * @param {*} data
     * @memberof MobAppMenuControlBase
     */
    public computedEffectiveMenus(inputMenus: Array<any>) {
        inputMenus.forEach((_item: any) => {
            if(!this.authService.getMenusPermission(_item)){
                _item.hidden = true;
                if (_item.items && _item.items.length > 0) {
                    this.computedEffectiveMenus(_item.items);
                }
            }
            if (Object.is(_item.id, 'setting')) {
                _item.hidden = false;
            }
        })
    }

    /**
     * 数据处理
     *
     * @private
     * @param {any[]} items
     * @memberof MobAppMenuControlBase
     */
    private dataProcess(items: any[]): void {
        items.forEach((_item: any) => {
            if (_item.expanded) {
                this.defaultOpeneds.push(_item.id);
            }
            if (_item.items && _item.items.length > 0) {
                this.dataProcess(_item.items)
            }
        });
    }

    /**
     * 提示框主题样式
     *
     * @readonly
     * @type {string}
     * @memberof MobAppMenuControlBase
     */
    get popperClass(): string {
        return 'app-popper-menu ' + this.selectTheme;
    }

    /**
     * 部件挂载
     *
     * @memberof AppControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit();
        this.controlStyle = this.staticProps.controlStyle ? this.staticProps.controlStyle : "";
        this.mode = this.dynamicProps.mode;
        this.isDefaultPage = this.dynamicProps.isDefaultPage;
        this.defPSAppView = this.dynamicProps.defPSAppView;
        this.selectTheme = this.staticProps.selectTheme;
        let _this: any = this;
        this.authService = new AuthServiceBase({ $store: _this.$store });
        this.handleMenusResource(this.service.getAllMenuItems());
        this.calcMenuName();
    }


    /**
     * 计算菜单名称
     *
     * @memberof MobAppMenuControlBase
     */
    public calcMenuName() {
        this.menus.forEach((item:any)=>{
            item.caption = this.$tl(item.captionTag,item.caption);
        });
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof AppDefaultMobForm
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args)
        this.service = new AppMobMenuService(this.controlInstance);
        await this.service.initServiceParam(this.context,this.controlInstance);
    }


    /**
     * 通过统一资源标识计算菜单
     *
     * @param {*} data
     * @memberof AppMenuControlBase
     */
    public handleMenusResource(inputMenus: Array<any> | undefined) {
        if (!inputMenus) {
            return;
        }
        let _this: any = this;
        if (_this.$store.getters['authresource/getEnablePermissionValid']) {
            this.computedEffectiveMenus(inputMenus);
        }
        this.dataProcess(inputMenus);
        this.menus = inputMenus;
    }
}