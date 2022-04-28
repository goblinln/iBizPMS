import { Component } from 'vue-property-decorator';
import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import './app-default-mdview-layout.less';

/**
 * 视图基础布局
 *
 * @export
 * @class AppDefaultViewLayout
 * @extends {Vue}
 */
@Component({})
export class AppDefaultMDViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultGridViewLayout
     */
    public renderViewHeader() {
        return [
            <div class="caption-container">
                {this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.model.srfCaption}</span> : null}
                {this.$slots.quickGroupSearch}
            </div>,
            <div class="quick-search-form">
              {this.$slots.quickSearchForm}
            </div>,
            this.$slots.quickSearch || (this.viewIsshowToolbar && this.$slots.toolbar) ?
            <div class="bar-container">
                {this.$slots.quickSearch}
                {this.viewIsshowToolbar ? this.$slots.toolbar : null}
            </div> : null
        ]
    }

    /**
    * 绘制内容
    * 
    * @memberof AppDefaultGridViewLayout
    */
    public renderContent() {
        const noHeader = !this.showCaption && !this.viewIsshowToolbar && !this.$slots.quickGroupSearch && !this.$slots.quickSearch
        let cardClass = {
            'view-card': true,
            'mdview-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewIsshowToolbar,
            'view-no-header': noHeader
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                {!noHeader ? <div slot='title' class='header-container' key='view-header'>
                    {this.renderViewHeader()}
                </div> : null}
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