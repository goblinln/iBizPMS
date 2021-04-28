import { Vue, Component, Prop } from 'vue-property-decorator';
import './app-embed-view.less';

/**
 * 嵌入视图布局面板
 *
 * @export
 * @class AppEmbedView
 * @extends {Vue}
 */
@Component({})
export class AppEmbedView extends Vue {

    /**
     * 额外类名
     *
     * @type {*}
     * @memberof AppEmbedView
     */
    @Prop({ default: () => { } })
    public className: any;

    /**
     * 绘制内容
     *
     * @memberof AppEmbedView
     */
    public render(): any {
        return <div class={{ 'app-embed-view': true, ...this.className }}>
            <div class="embed-view-header">
                {this.$slots.header}
            </div>
            <div class="embed-view-toolbar">
                {this.$slots.toolbar}
            </div>
            <div class="embed-view-content">
                {this.$slots.content}
            </div>
            <div class="embed-view-footer">
                {this.$slots.footer}
            </div>
        </div>;
    }

}