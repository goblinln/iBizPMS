import { Component } from 'vue-property-decorator';
import { AppDefaultViewLayout } from '../app-default-view-layout/app-default-view-layout';
import "./app-default-wfdynastartview-layout.less";

@Component({})
export class AppDefaultWFDynaStartViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultWFDynaStartViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': true,
            'view-no-toolbar': true,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                {this.$slots.searchForm}
                <div class='content-container'>
                    {this.$slots.default}
                </div>
                {this.$slots.button}
            </card>
        );
    }
}