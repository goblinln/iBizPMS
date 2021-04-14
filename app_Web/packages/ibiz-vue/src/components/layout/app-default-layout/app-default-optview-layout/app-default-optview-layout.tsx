import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import './app-default-optview-layout.less';

@Component({})
export class AppDefaultOptViewLayout extends AppDefaultViewLayout {
    /**
     * 绘制内容
     * 
     * @memberof AppDefaultWFDynaActionViewLayout
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
                {this.$slots.footer}
            </card>
        );
    }

}