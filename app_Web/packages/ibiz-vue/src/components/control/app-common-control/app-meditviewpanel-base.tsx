import { Emit, Prop, Watch } from 'vue-property-decorator';
import { MEditViewPanelControlBase } from '../../../widgets';
import { Util } from 'ibiz-core';

/**
 * 多编辑面板部件基类
 *
 * @export
 * @class AppListExpBarBase
 * @extends {ListExpBarControlBase}
 */
export class AppMEditViewPanelBase extends MEditViewPanelControlBase{

    /**
     * 部件动态参数
     *
     * @memberof AppMEditViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMEditViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMEditViewPanelBase
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
     * @memberof AppMEditViewPanelBase
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
     * @memberof AppMEditViewPanelBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMEditViewPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制内容
     * 
     * @param item 
     * @memberof AppMEditViewPanelBase
     */
    public renderContent(){
        if(!this.embedViewName){
            return;
        }
        return this.items.map((item: any) =>{
            return [
                this.$createElement(this.embedViewName, {
                    props: { 
                        staticProps: {
                            viewDefaultUsage: false, 
                            panelState: this.panelState,
                        },
                        dynamicProps:{
                            viewdata: JSON.stringify(item.viewdata), 
                            viewparam: JSON.stringify(item.viewparam), 
                        }
                    },
                    class: "viewcontainer2",
                    on: {
                        viewdataschange: this.viewDataChange.bind(this),
                        viewload: this.viewload.bind(this),
                    }
                }),
                <divider />
            ]
        })
    }

    /**
     * 绘制部件
     * 
     * @param h 
     * @memberof AppMEditViewPanelBase
     */
    public render(h: any){
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{...controlClassNames,'multieditviewpanel': true}}>
                {this.items.length > 0 ? this.renderContent() : null}
                <i-button type="primary" on-click={()=>this.handleAdd()} style="float: right;">
                    增加
                </i-button>
            </div>
        )
    }
}