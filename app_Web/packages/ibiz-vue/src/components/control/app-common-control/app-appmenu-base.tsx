import { Util, LogUtil, debounce  } from 'ibiz-core';
import { Emit, Prop, Watch } from 'vue-property-decorator';
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
    public split: number = 0.15;

    /**
     * 配置左侧分页菜单默认选项
     *
     * @return {*}
     * @memberof AppmenuBase
     */
     public defaultMuneOption(menus: any[]): any {
        return menus[0]?.getPSAppMenuItems?.[0];
    }
    /**
     * 顶部菜单项选中
     *
     * @param {string} item
     * @memberof AppmenuBase
     */
     public topMenuSelected(item: string) {
        this.menus.forEach(menu1 => {
            if (menu1?.name == item) {
                return this.menuTreeClick(menu1);
            } else {
                menu1.getPSAppMenuItems.forEach((menu2: any) => {
                    if (menu2?.name == item) {
                        return this.menuTreeClick(menu2);
                    }
                });
            }
        });
    }
    /**
     * 加载菜单的默认点击
     *
     * @memberof AppmenuBase
     */
     public defaultMenuSelect(): void {
         //分页导航菜单的默认点击
        if(Object.is(this.staticProps.mode, ("TABEXP_LEFT"||'TABEXP_TOP'||'TABEXP_RIGHT'||'TABEXP_BOTTOM'))){
            return this.menuTreeClick(this.defaultMuneOption(this.menus))
        }

        if (!this.isDefaultPage || this.isBlankMode) {
            return;
        }
        const appFuncs: Array<any> = this.service.getAllFuncs();
        if (this.$route && this.$route.matched && this.$route.matched.length == 2) { // 存在二级路由
            const [{ }, matched] = this.$route.matched;
            const appfunc: any = appFuncs.find((_appfunc: any) => Object.is(_appfunc.routepath, matched.path) && Object.is(_appfunc.appfunctype, 'APPVIEW'));
            if (appfunc) {
                this.computeMenuSelect(this.menus, appfunc.appfunctag);
            }
            return;
        } else if (this.defPSAppView && Object.keys(this.defPSAppView).length > 0) { // 存在默认视图
            const appfunc: any = appFuncs.find((_appfunc: any) => Object.is(_appfunc.routepath, this.defPSAppView.routepath) && Object.is(_appfunc.appfunctype, 'APPVIEW'));
            if (appfunc) {
                this.computeMenuSelect(this.menus, appfunc.appfunctag);
            }
            const viewparam: any = {};
            const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, this.defPSAppView.deResParameters, this.defPSAppView.parameters, [], viewparam);
            this.$router.push(path);
            return;
        }
        this.computeMenuSelect(this.menus, '');
        let item = this.compute(this.menus, this.defaultActive);
        if (Object.keys(item).length === 0) {
            return;
        }
        if(!item.hidden){
            this.click(item);
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
        if (item.getPSAppFunc) {
            const appFuncs: Array<any> = this.service.getAllFuncs();
            const appFunc = appFuncs.find((element:any) =>{
                return element.appfunctag === item.getPSAppFunc.codeName;
            });
            if (appFunc && appFunc.getPSAppView) {
                Object.assign(tempContext,{ viewpath: appFunc.getPSAppView._modelFilePath });
                let targetCtrlParam: any = {
                    staticProps: {
                        viewDefaultUsage: false,
                    },
                    dynamicProps: {
                        viewparam: {},
                        viewdata: JSON.stringify(tempContext),
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
            LogUtil.warn(this.$t('app.commonwords.noassign'));
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
        if (data.hidden) {
          return null
        }
        if (data.getPSAppMenuItems && data.getPSAppMenuItems.length > 0) {
            return (
                <div class="parent-treeitem">
                    <span class='icon'><i class="el-icon-s-claim" size="14"/></span>
                    <span class='text' title={node.data.caption}>{node.data.caption}</span>
                    <i class={{ 'el-icon-arrow-right': true, 'expanded': node.expanded }} size="14"/>
                </div>
            )
        } else {
            return (
                <div class="leaf-treeitem">
                    <span title={node.data.caption}>{node.data.caption}</span>
                </div>
            )
        }

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
        let hasIcon: boolean = menu.getPSSysImage?.imagePath || menu.getPSSysImage?.cssClass || (!menu.getPSSysImage && isFirst) ? true : false;
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
                            <span ref="circleText" class={['text', { 'app-menu-circle': this.collapseChange && isFirst, 'no-icon': !hasIcon }]} title={this.$tl(menu.tooltipTag,menu.tooltip)}>
                                {this.$tl(menu.captionTag,menu.caption).slice(0, 1)}
                            </span> : null
                    }
                    <template slot="title">
                        <span class={['text', { 'app-menu-circle': this.collapseChange && isFirst, 'no-icon': !hasIcon }]} title={this.$tl(menu.tooltipTag,menu.tooltip)}>
                            {this.$tl(menu.captionTag,menu.caption)}
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
        let hasIcon: boolean = menus.getPSSysImage?.imagePath || menus.getPSSysImage?.cssClass || (!menus.getPSSysImage && isFirst) ? true : false;
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
                        <span ref="circleText" class={['text', { 'app-menu-circle': this.collapseChange && isFirst, 'no-icon': !hasIcon }]} title={this.$tl(menus.tooltipTag,menus.tooltip)}>
                            {this.collapseChange && isFirst ? menus.caption.slice(0, 1) : this.$tl(menus.captionTag,menus.caption)}
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
        if (menu?.getPSAppMenuItems?.length > 0) {
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
                mode={(Object.is(this.mode, 'LEFT') || !this.mode) ? 'vertical' : 'horizontal'}
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
                    return <card bordered={false} v-show={!menu.hidden} class={{ 'app-middle-menu-group': true }}>
                        <p slot="title" class={{ 'app-middle-menu-header': true }}>
                            {this.$tl(menu.captionTag,menu.caption)}
                        </p>
                        <div class={{ 'app-middle-menu-content': true }}>
                            {
                                (menu.getPSAppMenuItems && menu.getPSAppMenuItems.length > 0) ? menu.getPSAppMenuItems.map((item: any) => {
                                    return !item.hidden?this.$createElement('card', {
                                        class: 'app-middle-menu-item',
                                        nativeOn: {
                                            click: () => { debounce(this.click,[item],this) }
                                        },
                                        scopedSlots: {
                                            default: () => {
                                                return this.$tl(item.captionTag,item.caption);
                                            }
                                        }
                                    }):null
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
        if (this.menus && this.menus.length > 0) {
            const defaultMuneOption:any = this.defaultMuneOption(this.menus)?.name;
            return this.$createElement('el-tree', {
                props: {
                    'current-node-key': defaultMuneOption,
                    data: this.menus,
                    props: {
                        children: 'getPSAppMenuItems',
                        label: 'caption'
                    },
                    'default-expand-all': true,
                    'highlight-current': true,
                    'render-content': this.menuTreeload,
                    'node-key': 'name',
                    'filter-node-method': ((filter: any, data: any) => {
                        if (!filter) return true;
                        return this.$tl(data.captionTag,data.caption).indexOf(filter) !== -1;
                    })
                },
                ref: 'eltree',
                on: {
                    'node-click': ((e: any) => debounce(this.menuTreeClick,[e],this))
                },
                scopedSlots:{
                    default : ({node, data}:any)=>{
                        return this.$tl(data.captionTag,data.caption);
                    }
                }
            })
        }
    }

    /**
     * 搜索菜单节点
     *
     * @memberof AppmenuBase
     */
    public onSearch(filter: any) {
        const tree: any = this.$refs.eltree;
        if (tree && tree.filter && tree.filter instanceof Function) {
            tree.filter(filter);
        }
    }

    /**
     * 左侧应用菜单内容
     *
     * @memberof AppmenuBase
     */
    public renderContent() {
        if(this.split == 0.85){
            return [
                <div slot="right" style={{ height: '100%', padding: '6px 0 10px' }}>
                    <i-input
                        search={true}
                        class="index-search"
                        placeholder={this.$t('components.search.holder')}
                        on-on-search={(value: any) => { this.onSearch(value); }}>
                    </i-input>
                    <div style={{ height: '100%' }}>
                        {this.renderMenuTree()}
                    </div>
                </div>,
                <div slot="left">
                    {this.renderRightView ? this.renderRightView : null}
                </div>
            ];
        }else {
            return [
                <div slot="left" style={{ height: '100%', padding: '6px 0' }}>
                    <i-input
                        search={true}
                        class="index-search"
                        placeholder={this.$t('components.search.holder')}
                        on-on-search={(value: any) => { this.onSearch(value); }}>
                    </i-input>
                    <div style={{ height: '100%' }}>
                        {this.renderMenuTree()}
                    </div>
                </div>,
                <div slot="right">
                    {this.renderRightView ? this.renderRightView : null}
                </div>
            ];
        }
        
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
                style={{ height: '100%' }}>
                {this.renderContent()}

            </split>
        )
    }
       /**
     * 绘制右侧分页菜单
     *
     * @return {*}
     * @memberof AppmenuBase
     */
        public renderTableRightMenu() {
            this.split = 0.85;
            return (
                <split
                    class={[`app-tree-exp-bar`, this.renderOptions?.controlClassNames]}
                    v-model={this.split}
                    style={{ height: 'calc(100%)' }}
                >
                    {this.renderContent()}
                </split>
            );
        }
    
        /**
         * 绘制顶部分页菜单
         *
         * @return {*}
         * @memberof AppmenuBase
         */
        public renderTableTopMenu() {
            const defaultMuneOption:any = this.defaultMuneOption(this.menus)?.name;
            return (
                <div style='height:100%'>
                    <el-menu
                        mode='horizontal'
                        default-active={defaultMuneOption}
                        on-select={(e: any) => this.topMenuSelected(e)}
                    >
                        {this.menus.map((item, index) =>
                            item.getPSAppMenuItems && item.getPSAppMenuItems.length > 0 ? (
                                item.getPSAppMenuItems.getPSAppMenuItems &&
                                item.getPSAppMenuItems.getPSAppMenuItems.length > 0 ? (
                                    <el-submenu v-show={!item.hidden} index={item.getPSAppMenuItems?.name}>
                                        <template slot='title'>{this.$tl(item.getPSAppMenuItems.captionTag,item.getPSAppMenuItems.caption)}</template>
                                        {item.getPSAppMenuItems.getPSAppMenuItems.map((item2: any) => (
                                            <el-menu-item v-show={!item2.hidden} index={item2?.name}>{this.$tl(item2.captionTag,item2.caption)}</el-menu-item>
                                        ))}
                                    </el-submenu>
                                ) : (
                                    <el-submenu v-show={!item.hidden} index={item?.name}>
                                        <template slot='title'>{item.caption}</template>
                                        {item.getPSAppMenuItems.map((item1: any) => (
                                            <el-menu-item v-show={!item1.hidden} index={item1?.name}>{this.$tl(item1.captionTag,item1.caption)}</el-menu-item>
                                        ))}
                                    </el-submenu>
                                )
                            ) : (
                                <el-menu-item v-show={!item.hidden} index={item?.name}>{this.$tl(item.captionTag,item.caption)}</el-menu-item>
                            ),
                        )}
                    </el-menu>
                    <div style='height:calc(100% - 61px)'>{this.renderRightView ? this.renderRightView : null}</div>
                </div>
            );
        }
        /**
         * 绘制底部分页菜单
         *
         * @return {*}
         * @memberof AppmenuBase
         */
        public renderTableBottomMenu() {
            const defaultMuneOption:any = this.defaultMuneOption(this.menus)?.name
            return (
                <div style='height:100%'>
                    <div style='height:calc(100% - 61px)'>{this.renderRightView ? this.renderRightView : null}</div>
                    <div class='bottom-view'>
                        <el-menu
                            mode='horizontal'
                            default-active={defaultMuneOption}
                            on-select={(e: any) => this.topMenuSelected(e)}
                        >
                            {this.menus.map((item, index) =>
                                item.getPSAppMenuItems && item.getPSAppMenuItems.length > 0 ? (
                                    item.getPSAppMenuItems.getPSAppMenuItems &&
                                    item.getPSAppMenuItems.getPSAppMenuItems.length > 0 ? (
                                        <el-submenu v-show={!item.hidden} index={item.getPSAppMenuItems?.name}>
                                            <template slot='title'>{this.$tl(item.getPSAppMenuItems.captionTag,item.getPSAppMenuItems.caption)}</template>
                                            {item.getPSAppMenuItems.getPSAppMenuItems.map((item2: any) => (
                                                <el-menu-item v-show={!item2.hidden} index={item2?.name}>{this.$tl(item2.captionTag,item2.caption)}</el-menu-item>
                                            ))}
                                        </el-submenu>
                                    ) : (
                                        <el-submenu v-show={!item.hidden} index={item?.name}>
                                            <template slot='title'>{item.caption}</template>
                                            {item.getPSAppMenuItems.map((item1: any) => (
                                                <el-menu-item v-show={!item1.hidden} index={item1?.name}>{this.$tl(item1.captionTag,item1.caption)}</el-menu-item>
                                            ))}
                                        </el-submenu>
                                    )
                                ) : (
                                    <el-menu-item v-show={!item.hidden} index={item?.name}>{this.$tl(item.captionTag,item.caption)}</el-menu-item>
                                ),
                            )}
                        </el-menu>
                    </div>
                </div>
            );
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
        } else  if (this.staticProps && this.staticProps.mode && Object.is(this.staticProps.mode, 'TABEXP_LEFT')) {
            return <div>{this.renderTableLeftMenu()}</div>;
        } else if (this.staticProps && this.staticProps.mode && Object.is(this.staticProps.mode, 'TABEXP_TOP')) {
            return <div>{this.renderTableTopMenu()}</div>;
        } else if (this.staticProps && this.staticProps.mode && Object.is(this.staticProps.mode, 'TABEXP_RIGHT')) {
            return <div>{this.renderTableRightMenu()}</div>;
        } else if (this.staticProps && this.staticProps.mode && Object.is(this.staticProps.mode, 'TABEXP_BOTTOM')) {
            return <div>{this.renderTableBottomMenu()}</div>;
        } else {
            return <div class={{ ...controlClassNames, 'app-app-menu': true }}>{this.renderAppMenu()}</div>;
        }
    }
}
