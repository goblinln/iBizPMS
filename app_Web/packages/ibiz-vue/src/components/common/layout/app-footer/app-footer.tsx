import { Vue, Component } from 'vue-property-decorator';
import { VNode, CreateElement } from 'vue';
import { FooterItemsService } from '../../../../app-service';
import './app-footer.less';

/**
 * 应用头部
 *
 * @export
 * @class AppFooter
 * @extends {Vue}
 */
@Component({})
export class AppFooter extends Vue {
    /**
     * 底部项绘制服务
     *
     * @private
     * @memberof AppFooter
     */
    private footerItemsService = new FooterItemsService();

    /**
     * 组件创建完毕
     *
     * @memberof AppFooter
     */
    public created(): void {
        this.footerItemsService.tickTrigger().subscribe(() => {
            this.$nextTick();
        });
    }

    /**
     * 绘制内容
     *
     * @returns {VNode}
     * @memberof AppFooter
     */
    public render(h: CreateElement): VNode {
        return (
            <div class="app-footer">
                <div class="app-footer-left">
                    {this.footerItemsService.leftItemsRenders.map((item) => {
                        return <div class="item">{item(h)}</div>;
                    })}
                </div>
                <div class="app-footer-center">
                    {this.footerItemsService.centerItemsRenders.map((item) => {
                        return <div class="item">{item(h)}</div>;
                    })}
                </div>
                <div class="app-footer-right">
                    {this.footerItemsService.rightItemsRenders.map((item) => {
                        return <div class="item">{item(h)}</div>;
                    })}
                </div>
            </div>
        );
    }
}
