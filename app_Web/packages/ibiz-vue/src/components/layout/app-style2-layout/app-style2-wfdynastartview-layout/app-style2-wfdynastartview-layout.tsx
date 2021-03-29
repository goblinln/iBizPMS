import { Component } from 'vue-property-decorator';
import { AppStyle2DefaultLayout } from '../app-style2-default-layout/app-style2-default-layout';
import "./app-style2-wfdynastartview-layout.less";

@Component({})
export class AppStyle2WFDynaStartViewLayout extends AppStyle2DefaultLayout {

    /**
     * 绘制内容
     * 
     * @memberof AppStyle2WFDynaStartViewLayout
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