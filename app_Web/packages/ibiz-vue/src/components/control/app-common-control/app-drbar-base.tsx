import { IPSDEDRBarGroup } from '@ibiz/dynamic-model-api';
import { throttle, Util } from 'ibiz-core';
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { DrbarControlBase } from '../../../widgets/drbar-control-base';

/**
 * 数据关系栏部件基类
 *
 * @export
 * @class AppDrbarBase
 * @extends {TabExpPanelBase}
 */
export class AppDrbarBase extends DrbarControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppDrbarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDrbarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDrbarBase
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDrbarBase
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
     * @memberof AppDrbarBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDrbarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制关系界面
     *
     * @return {*} 
     * @memberof AppDrbarBase
     */
    public renderDrView() {
        if (this.selection && this.selection.view && !this.selection.disabled) {
            let viewData: any = Util.deepCopy(this.context);
            let viewParam: any = Util.deepCopy(this.viewparams);
            if (this.selection.localContext) {
                Object.assign(viewData, this.selection.localContext);
            }
            if (this.selection.localViewParam) {
                Object.assign(viewParam, this.selection.localViewParam);
            }
            if (this.selection.view.getPSAppView?.()) {
                Object.assign(viewData, { viewpath: this.selection.view.getPSAppView()?.modelPath });
            }
            //  填充主视图标识参数
            if (this.appDeCodeName && viewData.hasOwnProperty(this.appDeCodeName.toLowerCase())) {
                Object.assign(viewData, { srfparentdename: this.appDeCodeName, srfparentkey: viewData[this.appDeCodeName.toLowerCase()] });
                Object.assign(viewParam, { srfparentdename: this.appDeCodeName, srfparentkey: viewData[this.appDeCodeName.toLowerCase()] });
            }
            return this.$createElement('app-view-shell', {
                props: {
                    staticProps: {
                        viewDefaultUsage: false,
                        appDeCodeName: this.appDeCodeName,
                    },
                    dynamicProps: {
                        viewdata: JSON.stringify(viewData),
                        viewparam: JSON.stringify(viewParam),
                    }
                },
                key: Util.createUUID(),
                class: "viewcontainer2",
                on: {
                }
            })
        }
    }

    /**
     * @description 渲染菜单项
     * @param {any[]} items
     * @return {*} 
     * @memberof AppDrbarBase
     */
    public renderMenuItems(items: any[]) {
        const getGroupTitle = (item: any): string => {
            if (this.showMode == 'DEFAULT') {
                return item.text;
            }
            if (this.selection && this.selection.groupCodeName == item.id) {
                return item.text + '-' + this.selection.text;
            } else {
                return item.text;
            }
        }
        return items.map((item: any, index: number) => {
            if (this.showMode == 'INDEXMODE' && item.id == this.formName) {
                return null;
            }
            if (item.items && item.items.length > 0) {
                return (
                    <el-submenu class="drbar-menu-item drbar-menu-item--subitem" key={index} index={item.id} disabled={item.disabled}>
                        <span class="drbar-menu-item--title" slot="title">
                            {item.icon ? <img src={item.icon} class="drbar-menu-item--icon"></img> :
                                item.iconcls ? <i class={[item.iconcls, 'drbar-menu-item--icon']}></i> : null}
                            {getGroupTitle(item)}
                        </span>
                        {this.renderMenuItems(item.items)}
                    </el-submenu>
                )
            } else {
                return (
                    <el-menu-item class="drbar-menu-item" key={index} index={item.id} disabled={item.disabled}>
                        <span class="drbar-menu-item--title" slot="title">
                            {item.icon ? <img src={item.icon} class="drbar-menu-item--icon"></img> :
                                item.iconcls ? <i class={[item.iconcls, 'drbar-menu-item--icon']}></i> : null}
                            {item.text}
                        </span>
                        {item.counter && (item.counter.count || item.counter.count == 0) ?
                            <span v-badge={item.counter} class="right-badge"></span> : null}
                    </el-menu-item>
                )
            }
        })
    }

    /**
     * @description 渲染侧边栏
     * @return {*} 
     * @memberof AppDrbarBase
     */
    public renderSider() {
        return (
            <sider width={this.width}>
                <el-menu
                    mode={this.menuDir}
                    default-openeds={this.defaultOpeneds}
                    default-active={this.selection?.id}
                    on-select={(event: any) => throttle(this.onSelect, [event], this)}>
                    {this.renderMenuItems(this.menuItems)}
                </el-menu>
            </sider>
        )
    }

    /**
     * @description 渲染头部
     * @return {*} 
     * @memberof AppDrbarBase
     */
    public renderHeader() {
        return (
            <header>
                <el-menu
                    mode={this.menuDir}
                    default-openeds={this.defaultOpeneds}
                    default-active={this.selection?.id}
                    on-select={(event: any) => throttle(this.onSelect, [event], this)}>
                    {this.renderMenuItems(this.menuItems)}
                </el-menu>
            </header>
        )
    }

    /**
     * 绘制关系栏部件
     *
     * @returns {*}
     * @memberof AppDrbarBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (
            <layout class={{ ...controlClassNames, 'app-dr-bar': true, [`drbar-${this.showMode.toLowerCase()}`]: true }}>
                {this.menuDir == 'horizontal' ? this.renderHeader() : this.renderSider()}
                <content style={[this.showMode == 'DEFAULT' && this.menuDir == 'vertical' ? `width: calc(100% - ${this.width + 1}px)` : '']}>
                    {this.showMode == 'DEFAULT' ?
                        <div class="main-data" style={[this.selection && Object.is(this.selection.id, this.formName) ? '' : { 'display': 'none', 'visibility': 'visible' }]}>
                            {this.$parent.$slots.mainform}
                        </div> : null}
                    {this.renderDrView()}
                </content>
            </layout>
        )
    }
}