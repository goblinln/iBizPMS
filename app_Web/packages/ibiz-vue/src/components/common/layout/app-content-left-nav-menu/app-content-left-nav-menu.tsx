import { Vue, Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { UIStateService } from '../../../../app-service';
import './app-content-left-nav-menu.less';

/**
 * 左侧导航菜单
 *
 * @export
 * @class AppContentLeftNavMenu
 * @extends {Vue}
 */
@Component({})
export class AppContentLeftNavMenu extends Vue {
    /**
     * UI状态服务
     *
     * @protected
     * @type {UIStateService}
     * @memberof AppContentLeftNavMenu
     */
    protected uiState: UIStateService = new UIStateService();

    /**
     * 当前激活项
     *
     * @protected
     * @type {*}
     * @memberof AppContentLeftNavMenu
     */
    protected activeItem: any = {};

    /**
     * 菜单Map表
     *
     * @protected
     * @type {Map<string, any>}
     * @memberof AppContentLeftNavMenu
     */
    protected menuMap: Map<string, any> = new Map();

    /**
     * 部件名称
     *
     * @type {string}
     * @memberof AppContentLeftNavMenu
     */
    @Prop()
    public ctrlName!: string;

    /**
     * 菜单数据
     *
     * @type {any[]}
     * @memberof AppContentLeftNavMenu
     */
    @Prop({ default: () => [] })
    public menus!: any[];

    /**
     * 监控菜单数据变更
     *
     * @memberof AppContentLeftNavMenu
     */
    @Watch('menus', { immediate: true })
    public watchMenus(): void {
        this.fillMenuMap(this.menus);
    }

    /**
     * 菜单项点击
     *
     * @param {*} item
     * @returns {*}
     * @memberof AppContentLeftNavMenu
     */
    @Emit('menu-click')
    public menuClick(item: any): any {}

    /**
     * 底部绘制实例
     *
     * @memberof AppContentLeftNavMenu
     */
    public footerRenderItem!: any;

    /**
     * 组件创建完毕
     *
     * @memberof AppContentLeftNavMenu
     */
    public created(): void {
        if (this.$route && this.$route.matched.length === 1) {
            this.openDefault();
        }
        this.footerRenderItem = this.$footerRenderService.registerLeftItem((h: any) => {
            return (
                <icon
                    title={this.uiState.layoutState.leftNavMenuCollapse ? '展开菜单' : '收起菜单'}
                    type="md-menu"
                    style="font-size: 20px;vertical-align: -3px;"
                    on-click={() => this.uiState.leftNavMenuCollapseChange()}
                />
            );
        }, 0);
    }

    /**
     * 组件销毁
     *
     * @memberof AppContentLeftNavMenu
     */
    public destroyed(): void {
        this.footerRenderItem?.remove();
    }

    /**
     * 打开默认菜单
     *
     * @protected
     * @memberof AppContentLeftNavMenu
     */
    protected openDefault(): void {
        let menu: any;
        for (const [key, item] of this.menuMap) {
            if (item.openDefault === true) {
                menu = item;
                break;
            }
        }
        if (menu) {
            this.itemClick(menu);
        }
    }

    /**
     * 菜单项点击
     *
     * @protected
     * @param {*} item
     * @memberof AppContentLeftExp
     */
    protected itemClick(item: any): void {
        const styleMode: any = this.$uiState.layoutState.styleMode;
        if ((Object.is(styleMode,'DEFAULT') && item.name !== this.activeItem.name) || Object.is(styleMode,'STYLE2')) {
            this.changeActiveItem(item);
            this.menuClick(item);
        } 
    }

    /**
     * 改变激活项
     *
     * @protected
     * @param {*} item
     * @memberof AppContentLeftExp
     */
    protected changeActiveItem(item: any): void {
        this.activeItem = item;
        this.activeItem.isActivated = true;
    }

    /**
     * 菜单项选中
     *
     * @protected
     * @param {string} name
     * @memberof AppContentLeftNavMenu
     */
    protected select(name: string): void {
        const item = this.menuMap.get(name);
        if (item) {
            this.itemClick(item);
        }
    }

    /**
     * 填充菜单Map表
     *
     * @protected
     * @param {any[]} menus
     * @returns {*}
     * @memberof AppContentLeftNavMenu
     */
    protected fillMenuMap(menus: any[]): any {
        menus.forEach((item: any) => {
            this.menuMap.set(item.name, item);
            if (item.getPSAppMenuItems) {
                this.fillMenuMap(item.getPSAppMenuItems);
            }
        });
    }

    /**
     * 展开菜单项
     *
     * @protected
     * @param {string} name
     * @memberof AppContentLeftNavMenu
     */
    protected open(name: string): void {
        const i: number = this.uiState.layoutState.leftNavOpenedMenus.findIndex((str: any) => Object.is(str, name));
        if (i === -1) {
            this.uiState.layoutState.leftNavOpenedMenus.push(name);
        }
    }

    /**
     * 收起菜单项
     *
     * @protected
     * @param {string} name
     * @memberof AppContentLeftNavMenu
     */
    protected close(name: string): void {
        const i: number = this.uiState.layoutState.leftNavOpenedMenus.findIndex((str: any) => Object.is(str, name));
        if (i !== -1) {
            this.uiState.layoutState.leftNavOpenedMenus.splice(i, 1);
            this.$forceUpdate();
        }
    }

    /**
     * 绘制子菜单
     *
     * @protected
     * @param {*} item
     * @returns {*}
     * @memberof AppContentLeftNavMenu
     */
    protected renderGroup(item: any): any {
        return (
            <el-submenu index={item.name}>
                <template slot="title">
                    <menu-icon item={item} />
                    <span slot="title">{item.caption}</span>
                </template>
                {this.renderItems(item.getPSAppMenuItems)}
            </el-submenu>
        );
    }

    /**
     * 绘制菜单项
     *
     * @protected
     * @param {*} item
     * @returns {*}
     * @memberof AppContentLeftNavMenu
     */
    protected renderItem(item: any): any {
        return (
            <el-menu-item index={item.name}>
                <menu-icon item={item} />
                <span slot="title">{item.caption}</span>
            </el-menu-item>
        );
    }

    /**
     * 绘制菜单
     *
     * @protected
     * @param {any[]} items
     * @returns {*}
     * @memberof AppContentLeftNavMenu
     */
    protected renderItems(items: any[]): any {
        return items.map((item: any) => {
            if (item.hidden) {
                return;
            }
            if (item.getPSAppMenuItems) {
                return this.renderGroup(item);
            }
            return this.renderItem(item);
        });
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppContentLeftNavMenu
     */
    public render(): any {
        return (
            <div class="app-content-lef-nav-menu">
                <el-menu
                    default-active={this.activeItem.name}
                    default-openeds={this.uiState.layoutState.leftNavOpenedMenus}
                    collapse={this.uiState.layoutState.leftNavMenuCollapse}
                    on-select={(i: any) => this.select(i)}
                    on-open={(i: any) => this.open(i)}
                    on-close={(i: any) => this.close(i)}
                >
                    {this.renderItems(this.menus)}
                </el-menu>
            </div>
        );
    }
}
