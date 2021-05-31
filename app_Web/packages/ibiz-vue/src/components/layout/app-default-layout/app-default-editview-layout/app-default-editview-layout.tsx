import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import './app-default-editview-layout.less';

@Component({})
export class AppDefaultEditViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewHeader(): any {
        if (this.$slots.datapanel) {
            return [
                this.viewIsshowToolbar ? [<div class="toptoolbar">{this.$slots.toolbar}</div>, <divider class="toptoolbar-divider" />] : null,
                <div class='header-info-container'>
                    {
                        this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.viewInstance.caption}</span> : null
                    }
                    <div class='dataInfo-container'>{this.$slots.datapanel}</div>
                </div>,
            ]
        } else {
            return [
                this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.viewInstance.caption}</span> : null,
                this.viewIsshowToolbar ? <div class='toolbar-container'>
                    {this.$slots.toolbar}
                </div> : null,
            ]
        }
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-card2': this.$slots.datapanel ? true : false,
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
                    {this.$slots.bodyMessage}
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }
}