import { Util } from 'ibiz-core';
import { Prop, Watch } from 'vue-property-decorator';
import { AppLayoutService } from '../../../app-service';
import { CalendarViewBase } from '../../../view/CalendarViewBase';

/**
 * 应用日历视图基类
 *
 * @export
 * @class AppCalendarViewBase
 * @extends {CalendarViewBase}
 */
export class AppCalendarViewBase extends CalendarViewBase {  

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppCalendarViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppCalendarViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppCalendarViewBase
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
     * 监听视图静态参数变化
     * 
     * @memberof AppCalendarViewBase
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
     * @memberof AppCalendarViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }
    
    /**
     * 绘制
     * 
     * @param h
     * @memberof AppCalendarViewBase
     */
    public render(h: any) {
        if(!this.viewIsLoaded){
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderSearchForm(),
            this.renderSearchBar(),
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }

}