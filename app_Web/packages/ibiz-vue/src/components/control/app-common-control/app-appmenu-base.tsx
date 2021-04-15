import { Emit, Prop, Watch } from 'vue-property-decorator';
import { IBizAppFuncModel, Util } from 'ibiz-core';
import { AppMenuControlBase } from '../../../widgets';

/**
 * 应用菜单部件基类
 *
 * @export
 * @class AppmenuBase
 * @extends {AppMenuControlBase}
 */
export class AppmenuBase extends AppMenuControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppmenuBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppmenuBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppmenuBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppmenuBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppmenuBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 左侧应用菜单的右侧视图组件
     *
     * @type {*} 
     * @memberof AppmenuBase
     */
    public renderRightView: any;

    /**
     * 左侧应用菜单分割面板比例
     *
     * @type {number}
     * @memberof AppmenuBase
     */
    public split: number = 0.15

    /**
     * 左侧应用菜单树属性
     *
     * @type {*} **对象
     * @memberof AppmenuBase
     */
    public defaultProps: any = {
        children: 'getPSAppMenuItems',
        label: 'caption'
    }

    /**
     * 加载菜单的默认点击
     *
     * @memberof AppmenuBase
     */
    public defaultMenuSelect(): void {
        if (this.menus && this.menus[0].getPSAppMenuItems && this.menuTreeClick) {
            this.menuTreeClick(this.menus[0].getPSAppMenuItems[0]);
        }
    }

    /**
     * 左侧菜单点击
     *
     * @param {*} item ***对象
     * @memberof AppmenuBase
     */
    public menuTreeClick(item: any) {
        let tempContext: any = Util.deepCopy(this.context);
        if (item.getPSNavigateContexts) {
            const localContext = Util.formatNavParam(item.getPSNavigateContexts);
            Object.assign(tempContext, localContext);
        } else {
            if (tempContext.hasOwnProperty("srfdynainstid")) {
                delete tempContext.srfdynainstid;
            }
        }
        if (item.getPSAppFunc && item.getPSAppFunc.modelref) {
            const appFunc = this.controlInstance.getAppFunc(item.getPSAppFunc.id);
            if (appFunc) {
                let targetCtrlParam: any = {
                    staticProps: {
                        viewDefaultUsage: false,
                    },
                    dynamicProps: {
                        viewparam: {},
                        viewdata: JSON.stringify({ viewpath: appFunc.getPSAppView.path }),
                    }
                };
                this.renderRightView = this.$createElement('app-view-shell', {
                    key: Util.createUUID(),
                    class: "viewcontainer2",
                    props: targetCtrlParam,
                });
                this.$forceUpdate();
            }
        } else {
            console.warn('未指定应用功能');
        }
    }

    /**
     * 左侧应用菜单树绘制事件
     *
     * @param {*} h
     * @param {*} { node, data }
     * @return {*} **node
     * @memberof AppmenuBase
     */
    public menuTreeload(h: any, { node, data }: any) {
        return (
            <span title={node.data.caption}>{node.data.caption}</span>
        )

    }
    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppmenuBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制菜单项
     * 
     * @param   menu 同一级菜单
     * @param   isFirst 是否为一级菜单
     * @memberof AppmenuBase
     */
    public renderMenuItem(menu: any, isFirst: boolean) {
        return (
            (Object.is(menu.itemType, 'MENUITEM') && !menu.hidden) ?
                <el-menu-item
                    key={Util.createUUID()}
                    index={menu.name}
                    class={[{ 'isFirst': isFirst }, menu.getPSSysCss?.cssName]}>
                    {menu.getPSSysImage?.imagePath ? <img class='app-menu-icon' src={menu.getPSSysImage.imagePath} /> : null}
                    {menu.getPSSysImage?.cssClass ? <i class={[menu.getPSSysImage.cssClass, 'app-menu-icon']}></i> : null}
                    {(!menu.getPSSysImage && isFirst) ? <i class='fa fa-cogs app-menu-icon'></i> : null}
                    {
                        isFirst && this.collapseChange ?
                            <span ref="circleText" class={['text', { 'app-menu-circle': this.collapseChange && isFirst }]} title={menu.tooltip}>
                                {menu.caption.slice(0, 1)}
                            </span> : null
                    }
                    <template slot="title">
                        <span class={['text', { 'app-menu-circle': this.collapseChange && isFirst }]} title={menu.tooltip}>
                            {menu.caption}
                        </span>
                        {
                            (this.counterdata && this.counterdata[menu.counterid] && this.counterdata[menu.counterid] > 0) ?
                                <span class="pull-right">
                                    <badge count={this.counterdata ? this.counterdata[menu.counterid] : 0} overflow-count={9999}></badge>
                                </span> : null
                        }
                    </template>
                </el-menu-item> : (Object.is(menu.itemType, 'SEPERATOR') && !menu.hidden) ? <divider /> : null
        );
    }

    /**
     * 绘制子菜单
     * 
     * @param   menus 同一级菜单
     * @param   isFirst 是否为一级菜单
     * @memberof AppmenuBase
     */
    public renderSubmenu(menus: any, isFirst: boolean) {
        return (
            !menus.hidden ?
                <el-submenu
                    key={Util.createUUID()}
                    index={menus.name}
                    class={[menus.getPSSysCss?.cssName, { 'isCollpase': this.collapseChange && isFirst }]}
                    popper-class={this.popperClass()}>
                    <template slot='title'>
                        {menus.getPSSysImage?.imagePath ? <img class='app-menu-icon' src={menus.getPSSysImage.imagePath} /> : null}
                        {menus.getPSSysImage?.cssClass ? <i class={[menus.getPSSysImage.cssClass, 'app-menu-icon']}></i> : null}
                        {(!menus.getPSSysImage && isFirst) ? <i class='fa fa-cogs app-menu-icon'></i> : null}
                        <span ref="circleText" class={['text', { 'app-menu-circle': this.collapseChange && isFirst }]} title={menus.tooltip}>
                            {this.collapseChange && isFirst ? menus.caption.slice(0, 1) : menus.caption}
                        </span>
                    </template>
                    {menus.getPSAppMenuItems.map((menu: any) => {
                        return this.renderAppMenuContent(menu, false);
                    })}
                </el-submenu> : null
        );
    }

    /**
     * 绘制菜单内容
     * 
     * @param   menu 菜单项
     * @param   isFirst 是否为一级菜单
     * @memberof AppmenuBase
     */
    public renderAppMenuContent(menu: any, isFirst: boolean) {
        if (menu.getPSAppMenuItems?.length > 0) {
            return this.renderSubmenu(menu, isFirst);
        } else {
            return this.renderMenuItem(menu, isFirst);
        }
    }

    /**
     * 绘制应用菜单
     * 
     * @memberof AppmenuBase
     */
    public renderAppMenu() {
        return (
            <el-menu
                class="app-menu"
                default-openeds={Object.is(this.mode, 'LEFT') ? this.defaultOpeneds : []}
                mode={Object.is(this.mode, 'LEFT') ? 'vertical' : 'horizontal'}
                menu-trigger={this.trigger}
                collapse={this.collapseChange}
                on-select={(menuName: string) => this.select(menuName)}
                default-active={this.defaultActive}>
                <div class="app-menu-item">
                    {this.menus.map((menu: any) => {
                        return this.renderAppMenuContent(menu, true);
                    })}
                </div>
            </el-menu>
        );
    }

    /**
     * 绘制中间应用菜单
     * 
     * @memberof AppmenuBase
     */
    public renderMiddleMenu() {
        return (
            <div class={{ 'app-middle-menu': true }}>
                {this.menus.map((menu: any) => {
                    return <card bordered={false} class={{ 'app-middle-menu-group': true }}>
                        <p slot="title" class={{ 'app-middle-menu-header': true }}>
                            {menu?.caption}
                        </p>
                        <div class={{ 'app-middle-menu-content': true }}>
                            {
                                (menu.getPSAppMenuItems && menu.getPSAppMenuItems.length > 0) ? menu.getPSAppMenuItems.map((item: any) => {
                                    return this.$createElement('card', {
                                        class: 'app-middle-menu-item',
                                        nativeOn: {
                                            click: () => { this.click(item) }
                                        },
                                        scopedSlots: {
                                            default: () => {
                                                return item?.caption;
                                            }
                                        }
                                    })
                                }) : null
                            }
                        </div>
                    </card >
                })}
            </div>
        )
    }


    /**
     * 左侧应用菜单的左侧树绘制
     *
     * @return {*} 
     * @memberof AppmenuBase
     */
    public renderMenuTree() {
        return this.$createElement('el-tree', {
            props: {
                'current-node-key': "menuitem6__srf1",
                data: this.menus,
                props: this.defaultProps,
                ref: 'eltree',
                'default-expand-all': true,
                'render-content': this.menuTreeload,
                'node-key': 'name'
            },
            on: {
                'node-click': ((e: any) => this.menuTreeClick(e))
            }
        })
    }

    /**
     * 左侧应用菜单内容
     *
     * @memberof AppmenuBase
     */
    public renderLeftContent() {
        return [
            <div slot="left" style={{ height: '100%', padding: '6px 0' }}>
                <div style={{ height: '100%' }}>
                    {this.renderMenuTree()}
                </div>
            </div>,
            <div slot="right">
                {this.renderRightView ? this.renderRightView : null}
            </div>
        ];
    }

    /**
     * 绘制左侧应用菜单
     *
     * @return {*} 
     * @memberof AppmenuBase
     */
    public renderTableLeftMenu() {
        return (
            <split
                class={[`app-tree-exp-bar`, this.renderOptions?.controlClassNames]}
                v-model={this.split}
                style={{ height: 'calc(100vh - 115px)' }}>
                {this.renderLeftContent()}

            </split>
        )
    }

    /**
     * 绘制应用菜单
     *
     * @returns {*}
     * @memberof AppmenuBase
     */
    public render() {
        const { controlClassNames } = this.renderOptions;
        if (this.staticProps && this.staticProps.mode && Object.is(this.staticProps.mode, "CENTER")) {
            return (<div>
                {this.renderMiddleMenu()}
            </div>)
        } else if (this.staticProps && this.staticProps.mode && Object.is(this.staticProps.mode, "TABEXP_LEFT")) {
            return (<div>
                {this.renderTableLeftMenu()}
            </div>)
        } else {
            return (
                <div class={{ ...controlClassNames, 'app-app-menu': true }}>
                    {this.renderAppMenu()}
                </div>
            );
        }
    }
}
