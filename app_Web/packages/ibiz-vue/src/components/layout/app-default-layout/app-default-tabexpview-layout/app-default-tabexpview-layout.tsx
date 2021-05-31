import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop, Component } from 'vue-property-decorator';
import './app-default-tabexpview-layout.less';

@Component({})
export class AppDefaultTabExpViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultTabExpViewLayout
     */
    public renderViewHeader() {
        return (
            <div slot="title" class='header-container' key='view-header'>
                {this.showCaption ? 
                    <div class="caption">
                        <span class='info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.viewInstance.caption}</span>
                    </div> : null }
            </div>
        )
    }
    
    /**
     * 绘制内容
     * 
     * @memberof AppDefaultTabExpViewLayout
     */
    public renderContent() {
        //TODO 分页位置样式等待模型补充
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewIsshowToolbar,
            'view-tabs-mode-default': true
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                {this.renderViewHeader()}
                {this.$slots.topMessage}
                {this.$slots.searchForm}
                <div class='content-container'>
                    { (this.$slots.quickGroupSearch || this.$slots.quickSearch) && <div style="margin-bottom: 6px;">
                        {this.$slots.quickGroupSearch}
                        {this.$slots.quickSearchForm}
                        {this.$slots.quickSearch}
                    </div> }
                    {this.$slots.bodyMessage}
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }

}