import { Component, Prop, Watch } from 'vue-property-decorator';
import { ViewContext, Util } from 'ibiz-core';
import { ViewBase } from '../../../view/ViewBase';

/**
 * 应用未支持视图
 *
 * @export
 * @class AppDefaultNotSupportedView
 * @extends {ViewBase}
 */
@Component({})
export class AppDefaultNotSupportedView extends ViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultNotSupportedView
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultNotSupportedView
     */
    @Prop() public dynamicProps!: any;


    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultNotSupportedView
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
     * @memberof AppDefaultNotSupportedView
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
     * 视图渲染
     * 
     * @memberof AppDefaultNotSupportedView
     */
    render(h: any) {
        let flexStyle: string = 'overflow: auto; display: flex;justify-content:center;align-items:center;';
        return (
            <div class='view-container' style={flexStyle}>
              暂未支持{this.staticProps?.modeldata?.title}
            </div>
        );
    }
}

