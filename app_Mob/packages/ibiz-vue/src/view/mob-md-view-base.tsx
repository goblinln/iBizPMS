import { MobMDViewEngine, ModelTool } from 'ibiz-core';
import { IPSAppDEMobMDView, IPSDEMobMDCtrl} from '@ibiz/dynamic-model-api';
import { MDViewBase } from "./md-view-base";
import { Util } from 'ibiz-core';

/**
 * 多数据视图基类
 *
 * @export
 * @class MDViewBase
 * @extends {MainViewBase}
 */
export class MobMDViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobMDViewBase
     */
    public viewInstance!: IPSAppDEMobMDView;

    /**
     * 列表实例
     * 
     * @memberof MobMDViewBase
     */
    public mdCtrlInstance!: IPSDEMobMDCtrl;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobMDViewBase
     */
    public engine: MobMDViewEngine = new MobMDViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MobMDViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine && this.mdCtrlInstance) {
            let engineOpts = Object.assign({
                mdctrl: (this.$refs[this.mdCtrlInstance.name] as any).ctrl,
            }, opts)
            super.engineInit(engineOpts);
        }
    }

    /**
     * 初始化多数据视图实例
     * 
     * @memberof MobMDViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEMobMDView;
        await super.viewModelInit();
        this.mdCtrlInstance = ModelTool.findPSControlByName('mdctrl', this.viewInstance.getPSControls()) as IPSDEMobMDCtrl;
        this.initViewToolBar();
    }

    /**
     * 多数据部件事件处理
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof MobMDViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'totalRecordChange') {
            this.pageTotalChange(data);
        }
        super.onCtrlEvent(controlname,action,data);
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobMDViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.mdCtrlInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.mdCtrlInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 多数据特殊处理content内容
     * 
     * @memberof MobMDViewBase
     */
    public renderViewContent() {
        const id = this.viewInstance.codeName;
        return <ion-content ref="ionScroll" id={id} scroll-y={this.enableControlUIAuth as string} scroll-events={true} slot="ioncontent"  on-ionScroll={this.onScroll.bind(this)} on-ionScrollEnd={this.onScrollEnd.bind(this)}>
            {this.renderBodyMessage()}
            {this.renderPullDownRefresh()}
            {this.renderMainContent()}
        </ion-content>
    }


    /**
     * 拦截滚动
     *
     * @memberof MobMDViewBase
     */
    public captorScroll = false;

    /**
     * onScroll滚动事件
     *
     * @memberof ViewBase
     */
    public async onScroll(e: any) {
        if(this.captorScroll){
            return;
        }
        this.isScrollStop = false;
        if (e.detail.scrollTop > 600) {
            this.isShouleBackTop = true;
        } else {
            this.isShouleBackTop = false;
        }
        // 多数据
        let ionScroll: any = this.$refs.ionScroll;
        if (ionScroll) {
            let ele = await ionScroll.getScrollElement();
            if (ele) {
                let scrollTop = ele.scrollTop;
                let clientHeight = ele.clientHeight;
                let scrollHeight = ele.scrollHeight;
                if (scrollHeight > clientHeight && scrollHeight - scrollTop - clientHeight < 1) {
                    this.captorScroll = true;
                    let mdctrl: any = this.$refs.mdctrl;
                    if (mdctrl?.ctrl?.loadBottom && Util.isFunction(mdctrl.ctrl.loadBottom)) {
                        mdctrl.ctrl.loadStatus = true;
                        await mdctrl.ctrl.loadBottom();
                        mdctrl.ctrl.loadStatus = false;
                        this.captorScroll  = false;
                    }
                }
            }
        }
    }

}
