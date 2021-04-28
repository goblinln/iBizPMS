import { Util, ThirdPartyService } from 'ibiz-core';
import Vue from 'vue';
import { Prop, Component } from 'vue-property-decorator';
import './app-default-view-layout.less';

/**
 * 视图基础布局
 *
 * @export
 * @class AppDefaultViewLayout
 * @extends {Vue}
 */
@Component({})
export class AppDefaultViewLayout extends Vue {

    /**
     * 视图模型数据
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public viewInstance!: any;

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewHeader() {
        return <ion-header>
            {!ThirdPartyService.getInstance().platform ? this.$slots.captionbar && !this.viewInstance.isPartsView ? this.$slots.captionbar : null:null}
            {this.$slots.toolbar}
            {this.$slots.quicksearch}
            {this.$slots.quickGroupSearch}
        </ion-header>
    }

    get isEmbedView(){
        return this.viewInstance.viewType.indexOf('VIEW9') != -1
    }

    /**
     * renderViewContent
     */
    public renderViewContent() {
        return <ion-content ref="ionScroll">
            {this.$slots.fixed}
            {this.$slots.default}
        </ion-content>
    }

    public renderViewFooter() {
        return <ion-footer class="view-footer">
            {this.$slots.mobbottommenu}
            {this.$slots.footer}
            {this.$slots.scrollTool}
        </ion-footer>
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderContent() {
        return [
            this.renderViewHeader(),
            this.renderViewContent(),
            this.renderViewFooter()
        ];
    }

    /**
     * 绘制布局
     * 
     * @memberof AppDefaultViewLayout
     */
    public render(h: any) {
        let viewClass = {
            'view-container': true,
            [this.viewInstance.viewType?.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance.codeName)]: true
        };
        return (
            this.isEmbedView ? <app-embed-view>
                <template slot="header">{this.renderViewHeader()}</template>
                <template slot="content">{this.$slots.default}</template>
                <template slot="footer">{this.renderViewFooter()}</template>
            </app-embed-view> :
                <ion-page className={viewClass}>
                    {this.renderContent()}
                </ion-page>
        );
    }

}