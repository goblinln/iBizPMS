import { AuthServiceBase, LogUtil, Util } from 'ibiz-core';
import { Component } from 'vue-property-decorator';
import { AppFuncService, AppLayoutService, FooterItemsService } from '../../../app-service';
import { AppMenuService } from '../../../ctrl-service/app-menu-service';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppIndexViewBase } from '../app-common-view/app-indexview-base';


/**
 * 应用首页视图
 *
 * @export
 * @class AppStyle2IndexView
 * @extends {AppIndexViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppStyle2IndexView extends AppIndexViewBase {

    /**
     * 建构权限服务对象
     *
     * @type {AuthService}
     * @memberof AppStyle2IndexView
     */
    public authService: AuthServiceBase = new AuthServiceBase({ $store: this.$store });

    /**
     * 底部项绘制服务
     *
     * @type {FooterItemsService}
     * @memberof AppStyle2IndexView
     */
    public footerItemsService: FooterItemsService = new FooterItemsService();

    /**
     * 当前激活菜单项
     * 
     * @type {*}
     * @memberof AppStyle2IndexView
     */
    public activeItem: any;

    /**
     * 左侧导航菜单
     *
     * @type {*}
     * @memberof AppStyle2IndexView
     */
    public left_exp: any;

    /**
     * 底部导航菜单
     *
     * @type {*}
     * @memberof AppStyle2IndexView
     */
    public bottom_exp: any;

    /**
     * 标题栏菜单
     *
     * @type {*}
     * @memberof AppStyle2IndexView
     */
    public top_menus: any;

    /**
     * 用户菜单
     *
     * @type {*}
     * @memberof AppStyle2IndexView
     */
    public user_menus: any;

    /**
     * 底部绘制
     *
     * @private
     * @memberof AppStyle2IndexView
     */
    public footerRenders: { remove: () => boolean }[] = [];

    /**
     * 菜单模型数据
     * 
     * @memberof AppStyle2IndexView
     */
    public appMenuModel: any;

    /**
     * 菜单部件服务对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public service: any;

    /**
     * 初始化应用首页视图实例
     * 
     * @memberof AppStyle2IndexView
     */
    public async viewModelInit(){
        await super.viewModelInit();
        this.service = new AppMenuService(this.menuInstance);
        await this.service.initServiceParam(this.context,this.menuInstance);
        this.appMenuModel = this.service.getAllMenuItems();
        this.left_exp = this.handleMenusResource('left_exp');
        this.bottom_exp = this.handleMenusResource('bottom_exp');
        this.top_menus = this.handleMenusResource('top_menus');
        this.user_menus = this.handleMenusResource('user_menus');
        this.registerFooterItems();
    }
    /**
     * 根据名称获取菜单组
     * 
	 * @param {string} name
     * @memberof AppStyle2IndexViewLayout 
     */
    public getMenuGroup(name: string){
        return this.appMenuModel.find((item: any) => Object.is(item.name, name));
    }

    /**
     * 通过统一资源标识计算菜单
     *
     * @param {*} name 菜单标识
     * @memberof AppStyle2IndexViewLayout
     */
    public handleMenusResource(name: any) {
        const inputMenus = this.getMenuGroup(name);
        if (inputMenus && inputMenus.getPSAppMenuItems) {
            this.computedEffectiveMenus(inputMenus.getPSAppMenuItems);
            // this.computeParentMenus(inputMenus.getPSAppMenuItems);
        }
        return inputMenus;
    }

    /**
     * 计算有效菜单项
     *
     * @param {*} inputMenus
     * @memberof AppStyle2IndexViewLayout
     */
    public computedEffectiveMenus(inputMenus: Array<any>) {
        inputMenus.forEach((_item: any) => {
            if (!this.authService?.getMenusPermission(_item)) {
                _item.hidden = true;
            }
            if (_item.getPSAppMenuItems && _item.getPSAppMenuItems.length > 0) {
                this.computedEffectiveMenus(_item.getPSAppMenuItems);
            }
        });
    }

    // /**
    //  * 计算父项菜单项是否隐藏
    //  *
    //  * @param {*} inputMenus
    //  * @memberof AppStyle2IndexViewLayout
    //  */
    // public computeParentMenus(inputMenus: Array<any>) {
    //     if (inputMenus && inputMenus.length > 0) {
    //         inputMenus.forEach((item: any) => {
    //             if (item.hidden && item.getPSAppMenuItems && item.getPSAppMenuItems.length > 0) {
    //                 item.getPSAppMenuItems.map((singleItem: any) => {
    //                     if (!singleItem.hidden) {
    //                         item.hidden = false;
    //                     }
    //                     if (singleItem.getPSAppMenuItems && singleItem.getPSAppMenuItems.length > 0) {
    //                         this.computeParentMenus(singleItem.getPSAppMenuItems);
    //                     }
    //                 });
    //             }
    //         });
    //     }
    // }

    /**
     * 注册底部项
     *
     * @memberof AppStyle2IndexViewLayout
     */
    protected registerFooterItems(): void {
        const leftItems: any = this.getMenuGroup('footer_left');
        const centerItems: any = this.getMenuGroup('footer_center');
        const rightItems: any = this.getMenuGroup('footer_right');
        if (leftItems && leftItems.getPSAppMenuItems) {
            leftItems.getPSAppMenuItems.forEach((item: any) => {
                this.footerRenders.push(
                    this.footerItemsService.registerLeftItem((h: any) => {
                        return (
                            <div class='action-item' title={item.tooltip} on-click={() => this.click(item)}>
                                <menu-icon item={item} />
                                {item.caption}
                                {/* todo 菜单多语言*/}
                                {/* this.$tl(item.captiontag,item.caption) */}
                            </div>
                        );
                    }),
                );
            });
        }
        if (centerItems && centerItems.getPSAppMenuItems) {
            centerItems.getPSAppMenuItems.forEach((item: any) => {
                this.footerRenders.push(
                    this.footerItemsService.registerCenterItem((h: any) => {
                        return (
                            <div class='action-item' title={item.tooltip} on-click={() => this.click(item)}>
                                <menu-icon item={item} />
                                {item.caption}
                                {/* todo 菜单多语言*/}
                                {/* this.$tl(item.captiontag,item.caption) */}
                            </div>
                        );
                    }),
                );
            });
        }
        if (rightItems && rightItems.getPSAppMenuItems) {
            rightItems.getPSAppMenuItems.forEach((item: any) => {
                this.footerRenders.push(
                    this.footerItemsService.registerRightItem((h: any) => {
                        return (
                            <div class='action-item' title={item.tooltip} on-click={() => this.click(item)}>
                                <menu-icon item={item} />
                                {item.caption}
                                {/* todo 菜单多语言*/}
                                {/* this.$tl(item.captiontag,item.caption) */}
                            </div>
                        );
                    }),
                );
            });
        }
    }

    /**
     * 项点击触发界面行为
     *
     * @protected
     * @param {*} item
     * @memberof AppStyle2IndexViewLayout
     */
    protected click(item: any): void {
        let tempContext:any = Util.deepCopy(this.context);
        if(item.getPSNavigateContexts){
            const localContext = Util.formatNavParam(item.getPSNavigateContexts);
            Object.assign(tempContext,localContext);
        }
        if (item.getPSAppFunc) {
            const appFuncs: Array<any> = this.service.getAllFuncs();
            const appFunc = appFuncs.find((element:any) =>{
                return element.appfunctag === item.getPSAppFunc.codeName;
            });
            if (appFunc) {
                AppFuncService.getInstance().executeApplication(appFunc,tempContext);
            }
        } else {
            LogUtil.warn('未指定应用功能');
        }
    }

    /**
     * 绘制左导航
     * 
     * @memberof AppStyle2IndexView
     */
    public renderLeftExp(){
        return (
            this.left_exp?.getPSAppMenuItems ?
            <app-content-left-exp 
                slot="leftExp"
                ref="leftExp"
                service={this.service}
                ctrlName={this.menuInstance?.codeName?.toLowerCase()}
                items={this.left_exp?.getPSAppMenuItems}
                modelService={this.modelService}
                on-active-item-change={(activeItem: any) => { this.activeItem = activeItem; this.$forceUpdate(); }}/>
            : null        
        )
    }

    /**
     * 绘制左导航菜单
     * 
     * @memberof AppStyle2IndexView
     */
    public renderLeftNavMenu(){
        return (
            this.left_exp?.getPSAppMenuItems ?
            <app-content-left-nav-menu
                slot="leftNavMenu"
                ref="leftNavMenu"
                ctrlName={this.menuInstance?.codeName?.toLowerCase()}
                menus={this.left_exp?.getPSAppMenuItems}
                modelService={this.modelService}
                on-menu-click={(item: any) => this.click(item)}/>
            : null        
        )
    }

    /**
     * 绘制头部菜单
     * 
     * @memberof AppStyle2IndexView
     */
    public renderHeaderMenus(){
        return (
            <app-header-right-menus
                slot="headerMenus"
                ref="headerMenus"
                ctrlName={this.menuInstance?.codeName?.toLowerCase()}
                menus={this.top_menus?.getPSAppMenuItems}
                modelService={this.modelService}
                on-menu-click={(item: any) => this.click(item)}/>
        )
    }

    /**
     * 绘制导航分页
     * 
     * @memberof AppStyle2IndexView
     */
    public renderTabPageExp(){
        return (
            <tab-page-exp-style2 slot="tabPageExp" ref="tabExp" modelService={this.modelService} activeItem={this.activeItem}></tab-page-exp-style2>
        )
    }

    /**
     * 绘制底部导航
     * 
     * @memberof AppStyle2IndexView
     */
    public renderBootomExp(){
        return (
            this.bottom_exp?.getPSAppMenuItems ?
            <app-content-bottom-exp
                slot="bootomExp"
                ref="bootomExp"
                service={this.service}
                ctrlName={this.menuInstance?.codeName?.toLowerCase()}
                modelService={this.modelService}
                items={this.bottom_exp?.getPSAppMenuItems} />
            : null           
        )
    }

    /**
     * 渲染视图主题内容(隐藏主体表单)
     * 
     * @memberof IndexViewBase
     */
     public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.menuInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.menuInstance?.name, on: targetCtrlEvent, style: { display: 'none'} });
    }

    /**
     * 应用首页视图渲染
     * 
     * @memberof AppStyle2IndexView
     */
    render(h: any){
        if(!this.viewIsLoaded){
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context },
        }, [
            this.renderLeftExp(),
            this.renderLeftNavMenu(),
            this.renderHeaderMenus(),
            this.renderTabPageExp(),
            this.renderMainContent(),
            this.renderBootomExp(),
        ]);
    }
    
}
