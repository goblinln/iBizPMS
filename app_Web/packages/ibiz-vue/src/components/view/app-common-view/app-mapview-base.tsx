import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MapViewBase } from '../../../view/mapview-base';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';

/**
 * 应用地图视图基类
 *
 * @export
 * @class AppMapViewBase
 * @extends {MapViewBase}
 */
export class AppMapViewBase extends MapViewBase {

    /**
     * 视图动态参数
     *
     * @type {any}
     * @memberof AppMapViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {any}
     * @memberof AppMapViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMapViewBase
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
     * 监听当前视图环境参数变化
     * 
     * @memberof AppMapViewBase
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
     * @memberof AppMapViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }
    
    /**
     * 地图视图渲染
     * 
     * @memberof AppMapViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            !(this.viewInstance?.viewStyle == "DEFAULT" && this.viewInstance?.enableQuickSearch) ? [this.renderSearchForm(), this.renderSearchBar()] : null,
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}

