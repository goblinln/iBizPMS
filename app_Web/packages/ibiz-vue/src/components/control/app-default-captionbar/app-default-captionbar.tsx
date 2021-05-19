import { Component, Prop } from 'vue-property-decorator';
import Vue from 'vue';
import './app-default-captionbar.less';

/**
 * 标题栏组件
 *
 * @export
 * @class AppDefaultCaptionBar
 * @extends {Vue}
 */
@Component({})
export class AppDefaultCaptionBar extends Vue {

    /**
     * 标题栏模型
     *
     * @memberof AppDefaultCaptionBar
     */
    @Prop() public modelData!: any;

    /**
     * 视图模型数据
     *
     * @memberof AppDefaultCaptionBar
     */
    @Prop() public viewModelData!: any;

    /**
     * 应用上下文
     *
     * @memberof AppDefaultCaptionBar
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @memberof AppDefaultCaptionBar
     */
    @Prop() public viewparam!: any;

    /**
     * 绘制标题栏
     *
     * @memberof AppDefaultCaptionBar
     */
    public render() {
        return (<div class="app-default-captionbar">
            {this.viewModelData.caption}
        </div>)
    }
}