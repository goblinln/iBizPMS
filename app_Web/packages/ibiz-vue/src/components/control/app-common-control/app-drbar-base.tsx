import { debounce, Util } from 'ibiz-core';
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
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDrbarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制关系界面
     *
     * @return {*} 
     * @memberof AppDrbarBase
     */
    public renderDrView(){
        if (this.selection && this.selection.view) {
            return this.$createElement('app-view-shell', {
                props: { 
                    staticProps: {
                        viewDefaultUsage: false,
                        viewModelData: this.selection.view,
                        appDeCodeName: this.appDeCodeName,
                    },
                    dynamicProps:{
                        viewdata: JSON.stringify(this.selection.data), 
                        viewparam: JSON.stringify(this.selection.param), 
                    }
                },
                class: "viewcontainer2",
                on: {
                }
            })
        } 
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
            <layout class={{...controlClassNames, [this.controlInstance.codeName]: true}}>
                <sider width={this.width}>
                    <el-menu
                        default-openeds={this.defaultOpeneds}
                        default-active={this.items[0]?.id}
                        on-select={(event: any) => debounce(this.onSelect, [event], this)}
                        on-open={(event: any) => debounce(this.onOpen, [event], this)}
                        on-close={(event: any) => debounce(this.onClose, [event], this)}>
                        <app-sider-menus menus={this.items}/>
                    </el-menu>
                </sider>
                <content style={{ width: `calc(100% - ${this.width + 1}px)` }}>
                    {this.selection && Object.is(this.selection.id, this.formInstance?.name?.toLowerCase()) ?
                        <div class="main-data"> {this.$parent.$slots.mainform} </div> : null}
                    {this.renderDrView()}
                </content>
            </layout>
        )
    }
}