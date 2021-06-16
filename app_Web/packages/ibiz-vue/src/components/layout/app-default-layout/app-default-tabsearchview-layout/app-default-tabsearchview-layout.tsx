import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop, Component } from 'vue-property-decorator';
import './app-default-tabsearchview-layout.less';

@Component({})
export class AppDefaultTabSearchViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultTabSearchViewLayout
     */
    public renderViewHeader() {
        return (
            <div slot="title" class='header-container' key='view-header'>
                <div class="tabs-container">
                    {this.showCaption ? 
                        <div class="caption">
                            <span class='info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.viewInstance.caption}</span>
                        </div> : null }
                    {this.$slots.tabsHeader}
                    {this.$slots.quickGroupSearch}
                </div>
                <div class='toolbar-container'>
                    {this.$slots.quickSearch}
                    {this.$slots.quickSearchForm}
                    {this.viewIsshowToolbar ? this.$slots.toolbar : null}
                </div>
            </div>
        )
    }
    
    /**
     * 绘制内容
     * 
     * @memberof AppDefaultTabSearchViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewIsshowToolbar,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                {this.renderViewHeader()}
                {this.$slots.topMessage}
                {this.$slots.searchForm}
                {this.$slots.searchBar}
                <div class='content-container'>
                    {this.$slots.bodyMessage}
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }

}