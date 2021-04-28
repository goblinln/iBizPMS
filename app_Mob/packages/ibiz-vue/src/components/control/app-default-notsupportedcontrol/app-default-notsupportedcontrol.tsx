import { Vue, Component, Prop, Watch, Emit } from 'vue-property-decorator';
import { ControlContext, Util } from 'ibiz-core';
import { AppControlBase } from '../../../widgets';


/**
 * 列表
 *
 * @extends ListControlBase
 */
@Component({})
export class AppDefaultNotSupportedControl extends AppControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppDefaultNotSupportedControl
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDefaultNotSupportedControl
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultNotSupportedControl
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
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultNotSupportedControl
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
     * 绘制
     *
     * @returns {*}
     * @memberof AppDefaultNotSupportedControl
     */
    public render(){
      let flexStyle: string = 'width: 100%; height: 100%; overflow: auto; display: flex;justify-content:center;align-items:center;';
      return (
          <div class='control-container' style={flexStyle}>
            暂未支持{this.staticProps.modelData.controlType}部件
          </div>
      );
    }



}