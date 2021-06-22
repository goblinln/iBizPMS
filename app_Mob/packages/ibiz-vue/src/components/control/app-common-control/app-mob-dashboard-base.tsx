import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobDashboardControlBase } from '../../../widgets';
import { IPSAppPortlet, IPSDBPortletPart, IPSDEDashboard, IPSLanguageRes } from '@ibiz/dynamic-model-api';

/**
 * 数据看板部件基类
 *
 * @export
 * @class AppMobDashboardBase
 * @extends {MobDashboardControlBase}
 */
export class AppMobDashboardBase extends MobDashboardControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobDashboardBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobDashboardBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobDashboardBase
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
     * @memberof AppMobDashboardBase
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
     * @memberof AppMobDashboardBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobDashboardBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制门户部件内容
     *
     * @param {IBizMobPortletModel} modelJson
     * @memberof AppMobDashboardBase
     */
    public renderPortletContent(modelJson: IPSDBPortletPart, index?: number) {
        if(modelJson.portletType == 'CONTAINER'){
            // 绘制门户部件（容器）
            const childPortlets = modelJson.getPSControls();
            let cardClass = !childPortlets ? 'portlet-card' : "portlet-card custom-card";
            let cardPadding = childPortlets ? 0 : 10;
            let isShowTitle = !!(modelJson.showTitleBar && modelJson.title);
            const controlClassNames: any = {
                [Util.srfFilePath2(modelJson?.codeName)]: true,
            };
            if (modelJson?.getPSSysCss?.()?.cssName) {
                Object.assign(controlClassNames, { [modelJson.getPSSysCss()?.cssName as string]: true });
            }
            let labelCaption: any = this.$tl((modelJson.getTitlePSLanguageRes() as IPSLanguageRes)?.lanResTag, modelJson.title);
            return <div class='portlet-without-title'>
            <card class={cardClass} bordered={false} dis-hover padding={cardPadding}>
                { isShowTitle && [
                    <p slot='title'>{labelCaption}<span class="line"></span></p>,
                    <a slot='extra'></a>
                ]}
                <span>
                    <div class={{'portlet-container':true}}>
                      {this.renderPortletContent(modelJson)}
                    </div>
                </span>
            </card>
            </div>
        }else{
            // 绘制门户部件（非容器）
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(modelJson);
            return this.$createElement(targetCtrlName,{ props: targetCtrlParam, ref: modelJson.name, on: targetCtrlEvent })
        }
       
    }

    /**
     * 绘制静态布局内容
     *
     * @returns
     * @memberof AppMobDashboardBase
     */
    public renderStaticDashboard(modelJson:IPSDEDashboard): any {
        return  (
            modelJson?.getPSControls()?.map((item: any, index: number) => {
                return this.renderPortletContent(item, index);
            })
    );
    }

    /**
     * 绘制自定义门户部件
     *
     * @param {*} item
     * @returns
     * @memberof AppMobDashboardBase
     */
    public renderCustomPortlet(customModel: any){
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(customModel.modelData);
        Object.assign(targetCtrlParam.dynamicProps, { isAdaptiveSize: true });
        return this.$createElement(targetCtrlName, {
            key: Util.createUUID(),
            props: targetCtrlParam,
            ref: customModel.modelData?.name,
            on: targetCtrlEvent,
            class:'dashboard-item userCustomize'
        });
    }

    /**
     * 绘制动态自定义布局内容
     *
     * @returns
     * @memberof AppMobDashboardBase
     */
    public renderCustomizedDashboard(): any {
        return this.renderCustomModelData.map((item:any, index: number)=>{
            return this.renderCustomPortlet(item)
        })
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppMobDashboardBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (
            <ion-grid class={{...controlClassNames, 'app-mob-dashboard': true}}>
                {this.isEnableCustomized && 
                        <div class="dashboard-enableCustomized" on-click={()=>{this.handleClick()}}>定制仪表盘<app-mob-icon name="settings-outline"></app-mob-icon></div>
                }
                {this.isHasCustomized ? this.renderCustomizedDashboard() : !this.isEnableCustomized?this.renderStaticDashboard(this.controlInstance):null}
            </ion-grid>
        );
    }
}
