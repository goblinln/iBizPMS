import { Vue, Component, Prop } from 'vue-property-decorator';
import { IBizAppMenuModel, Util } from 'ibiz-core';
import { AppFuncService, UIStateService } from '../../../../app-service';
import './app-content-bottom-exp.less';

/**
 * 应用内容区底部导航区
 *
 * @export
 * @class AppContentBottomExp
 * @extends {Vue}
 */
@Component({})
export class AppContentBottomExp extends Vue {
    /**
     * UI状态服务
     *
     * @protected
     * @type {UIStateService}
     * @memberof AppContentBottomExp
     */
    protected uiState: UIStateService = new UIStateService();

    /**
     * 部件名称
     *
     * @type {string}
     * @memberof AppContentBottomExp
     */
    @Prop()
    public ctrlName!: string;

    /**
     * 菜单部件实例
     * 
     * @memberof AppContentBottomExp
     */
    @Prop()
    protected menuInstance!: IBizAppMenuModel;

    /**
     * 传入数据
     *
     * @type {any[]}
     * @memberof AppContentBottomExp
     */
    @Prop({ default: () => [] })
    public items!: any[];

    /**
     * 菜单数据
     * 
     * @memberof AppContentBottomExp
     */
    protected menus: any[] = [];

    /**
     * 当前激活项下标
     *
     * @protected
     * @type {number}
     * @memberof AppContentBottomExp
     */
    protected activeIndex: number = -1;

    /**
     * 当前激活项
     *
     * @protected
     * @type {*}
     * @memberof AppContentBottomExp
     */
    protected activeItem: any;

    /**
     * 组件创建完毕
     *
     * @memberof AppContentBottomExp
     */
    public async created(){
        const i: number = this.uiState.layoutState.bottomExpActiveIndex;
        await this.replenishData(this.items);
        if (this.menus.length >= i+1) {
            this.itemClick(this.menus[i], i);
        }
    }

    /**
     * 填充数据
     * 
     * @memberof AppContentBottomExp
     */
    protected async replenishData(items: any[]){
        this.menus = [];
        let menus = [...items];
        if(menus && menus.length>0){
            for(let i = 0; i < menus.length; i++){
                if (menus[i].getPSAppFunc && menus[i].getPSAppFunc.modelref) {
                    const appFunc = this.menuInstance.getAppFunc(menus[i].getPSAppFunc.id);
                    if (appFunc && Object.is(appFunc.appFuncType,'APPVIEW')) {
                        const appView: any = await AppFuncService.getInstance().getViewModeJsonData(appFunc.getPSAppView);
                        if(appView){
                            Object.assign(menus[i], {viewname: Util.srfFilePath2(appView.codeName)});
                        }
                    }
                }
            }  
        }
        this.menus = menus;
    }

    /**
     * 激活分页
     *
     * @protected
     * @param {string} name
     * @memberof AppContentBottomExp
     */
    protected activeTab(name: string): void {
        try {
            const item: any = this.menus[parseInt(name)];
            this.itemClick(item, parseInt(name));
        } catch (error) {
            console.warn(error);
        }
    }

    /**
     * 菜单项点击
     *
     * @protected
     * @param {*} item
     * @param {number} index
     * @memberof AppContentBottomExp
     */
    protected itemClick(item: any, index: number): void {
        this.uiState.layoutState.bottomExpActiveIndex = index;
        this.activeIndex = index;
        this.activeItem = item;
        this.activeItem.isActivated = true;
    }

    /**
     * 绘制标题
     *
     * @protected
     * @param {*} h
     * @param {*} item
     * @returns {*}
     * @memberof AppContentBottomExp
     */
    protected renderTitle(h: any, item: any): any {
        return (
            <div title={item.tooltip} class="tab-exp-title">
                <menu-icon item={item} />
                {item.caption}
            </div>
        );
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppContentBottomExp
     */
    public render(): any {
        return (
            <div class="app-content-bottom-exp">
                <tabs
                    size="small"
                    animated={false}
                    value={this.activeIndex.toString()}
                    on-on-click={(name: string) => this.activeTab(name)}
                >
                    {this.menus.map((item: any, i: number) => {
                        if (item.hidden) {
                            return;
                        }
                        return (
                            <tabPane label={(h: any) => this.renderTitle(h, item)} name={i.toString()}>
                                {item.isActivated ? (
                                    <div key={i} class="tab-exp-item-content">
                                        {this.$createElement(item.viewname)}
                                    </div>
                                ) : null}
                            </tabPane>
                        );
                    })}
                </tabs>
            </div>
        );
    }
}
