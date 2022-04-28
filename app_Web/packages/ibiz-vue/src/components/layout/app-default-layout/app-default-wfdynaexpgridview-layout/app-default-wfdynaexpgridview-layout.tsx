import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop,Component } from 'vue-property-decorator';
import './app-default-wfdynaexpgridview-layout.less';


@Component({})
export class AppDefaultWfDynaExpGridViewLayout extends AppDefaultViewLayout{

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultWfDynaExpGridViewLayout
     */
     public renderViewHeader(): any {
        return [
            this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.model.srfCaption}</span> : null,
            this.viewIsshowToolbar ? <div class='toolbar-container'>
                {this.$slots.quickSearch}
                {this.$slots.toolbar}
            </div> : null,
        ]
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultWfDynaExpGridViewLayout
     */
     public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewIsshowToolbar,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                {(this.showCaption || this.viewIsshowToolbar) && (
                    <div slot='title' class='header-container' key='view-header'>
                        {this.renderViewHeader()}
                    </div>
                )}
                {this.$slots.topMessage}
                {this.$slots.searchForm}
                <div class='content-container'>
                    {(this.$slots.quickGroupSearch || this.$slots.quickSearch) && <div style="margin-bottom: 6px;">
                        {this.$slots.quickGroupSearch}
                    </div>}
                    {this.$slots.bodyMessage}
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }
}