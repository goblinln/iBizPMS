import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
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
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
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
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppmenuBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppmenuBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制菜单项
     * 
     * @param   menu 同一级菜单
     * @param   isFirst 是否为一级菜单
     * @memberof AppmenuBase
     */
    public renderMenuItem(menu: any, isFirst: boolean){
        return (
            (Object.is(menu.itemType, 'MENUITEM') && !menu.hidden) ?
            <el-menu-item
                key={ Util.createUUID() }
                index={ menu.name }
                class={ [ {'isFirst':isFirst}, menu.getPSSysCss?.cssName ] }>
                    { menu.getPSSysImage?.imagePath ? <img class='app-menu-icon' src={ menu.getPSSysImage.imagePath } /> : null}
                    { menu.getPSSysImage?.cssClass ? <i class={ [ menu.getPSSysImage.cssClass, 'app-menu-icon' ] }></i> : null}
                    { (!menu.getPSSysImage && isFirst) ? <i class='fa fa-cogs app-menu-icon'></i> : null}
                    {
                        isFirst && this.collapseChange ?
                        <span ref="circleText" class={ ['text', { 'app-menu-circle': this.collapseChange && isFirst}] } title={ menu.tooltip }>
                            { menu.caption.slice(0,1) }
                        </span> : null
                    }
                    <template slot="title">
                        <span class={ ['text', { 'app-menu-circle': this.collapseChange && isFirst}] } title={ menu.tooltip }>
                            { menu.caption }
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
    public renderSubmenu(menus: any, isFirst: boolean){
        return (  
            !menus.hidden ?
            <el-submenu
                key={ Util.createUUID() }
                index={ menus.name }
                class={ [ menus.getPSSysCss?.cssName , { 'isCollpase': this.collapseChange && isFirst } ] }
                popper-class={ this.popperClass() }>
                    <template slot='title'>
                        { menus.getPSSysImage?.imagePath ? <img class='app-menu-icon' src={ menus.getPSSysImage.imagePath } /> : null}
                        { menus.getPSSysImage?.cssClass ? <i class={ [ menus.getPSSysImage.cssClass, 'app-menu-icon' ] }></i> : null}
                        { (!menus.getPSSysImage && isFirst) ? <i class='fa fa-cogs app-menu-icon'></i> : null}
                        <span ref="circleText" class={ [ 'text', { 'app-menu-circle': this.collapseChange && isFirst} ] } title={ menus.tooltip }>
                            { this.collapseChange && isFirst ? menus.caption.slice(0,1) : menus.caption }
                        </span>
                    </template>
                    { menus.getPSAppMenuItems.map((menu: any) => {
                            return this.renderAppMenuContent(menu, false);
                    }) }
            </el-submenu>:null
        );
    }

    /**
     * 绘制菜单内容
     * 
     * @param   menu 菜单项
     * @param   isFirst 是否为一级菜单
     * @memberof AppmenuBase
     */
    public renderAppMenuContent(menu: any, isFirst: boolean){
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
    public renderAppMenu(){
        return (
            <el-menu
                class="app-menu"
                default-openeds={ Object.is(this.mode, 'horizontal') ? this.defaultOpeneds : [] }
                mode={ this.mode }
                menu-trigger={this.trigger}
                collapse={ this.collapseChange }
                on-select={ (menuName: string)=>this.select(menuName) }
                default-active={ this.defaultActive }>
                    <div class="app-menu-item">
                        { this.menus.map((menu: any) => {
                            return this.renderAppMenuContent(menu, true);
                        }) }
                    </div> 
            </el-menu>
        );
    }

    /**
     * 绘制应用菜单
     *
     * @returns {*}
     * @memberof AppmenuBase
     */
    public render(){
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{...controlClassNames,'app-app-menu': true}}>
                {this.renderAppMenu()}
            </div>
        );
    }
}
