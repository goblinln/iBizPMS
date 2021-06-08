import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MPickUpView2Base } from '../../../view/mpickupview2-base';
import { AppLayoutService } from '../../..';

export class AppMPickUpView2Base extends MPickUpView2Base {
        /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppMPickUpView2Base
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppMPickUpView2Base
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMPickUpView2Base
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
     * 监听静态参数变化
     * 
     * @memberof AppMPickUpView2Base
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
     * 初始化containerModel
     * 
     * @memberof AppMPickUpView2Base
     */
    public initContainerModel(opts: any) {
        super.initContainerModel(opts);
        const { modeldata } = opts;
        modeldata?.getPSControls().forEach((ctrl:any) => {
            this.containerModel[`view_${ctrl.name}`] = { name: `${ctrl.name}`, type: `${ctrl.controlType}` }
        });
    }

    /**
     * 渲染
     * 
     * @memberof AppMPickUpView2Base
     */
    public render(h: any) {
        if (!this.viewIsLoaded) {
            return;
        }
        console.log(this.viewInstance);
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderMainContent(),
            this.renderBottomMessage(),
        ]);
    }
}