import { Component, Prop } from 'vue-property-decorator';
import Vue from 'vue';
import './app-default-datainfobar.less';

/**
 * 信息栏组件
 *
 * @export
 * @class AppDefaultDataInfoBar
 * @extends {Vue}
 */
@Component({})
export class AppDefaultDataInfoBar extends Vue {

    /**
     * 信息栏模型
     *
     * @memberof AppDefaultDataInfoBar
     */
    @Prop() public modelData!: any;

    /**
     * 数据信息
     *
     * @memberof AppDefaultDataInfoBar
     */
    @Prop() public viewInfo!: any;

    /**
     * 应用上下文
     *
     * @memberof AppDefaultDataInfoBar
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @memberof AppDefaultDataInfoBar
     */
    @Prop() public viewparam!: any;

    /**
     * 绘制信息栏
     *
     * @memberof AppDefaultDataInfoBar
     */
    public render() {
        return (<div class="app-default-datainfobar">
            {this.viewInfo.dataInfo ? <span>{this.viewInfo.dataInfo}</span> : null}
        </div>)
    }
}