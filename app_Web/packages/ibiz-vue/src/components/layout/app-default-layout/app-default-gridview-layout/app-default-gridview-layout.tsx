import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop,Component } from 'vue-property-decorator';
import './app-default-gridview-layout.less';

@Component({})
export class AppDefaultGridViewLayout extends AppDefaultViewLayout{

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultGridViewLayout
     */
    public renderViewHeader() {
        return [
            <div class="caption-container">
                {this.viewInstance.showCaptionBar ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.viewInstance.caption}</span> : null}
                {this.$slots.quickGroupSearch}
            </div>,
            <div class="bar-container">
                {this.$slots.quickSearch}
                {this.viewInstance.viewIsshowToolbar ? this.$slots.toolbar : null}
            </div>
        ]
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultGridViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.viewInstance.showCaptionBar,
            'view-no-toolbar': !this.viewInstance.viewIsshowToolbar,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                <div slot='title' class='header-container' key='view-header'>
                    {this.renderViewHeader()}
                </div>
                {this.$slots.topMessage}
                {this.$slots.searchForm}
                <div class='content-container'>
                    {this.$slots.bodyMessage}
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }
}