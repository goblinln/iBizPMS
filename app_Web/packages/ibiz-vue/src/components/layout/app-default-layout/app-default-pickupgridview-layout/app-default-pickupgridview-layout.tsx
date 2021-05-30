import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop,Component } from 'vue-property-decorator';
import "./app-default-pickupgridview-layout.less";

@Component({})
export class AppDefaultPickupGridViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultPickupGridViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': true,
            'view-no-toolbar': true,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                { (this.$slots.quickGroupSearch || this.$slots.quickSearch) && <div style="margin-bottom: 6px;">
                    {this.$slots.quickGroupSearch}
                    {this.$slots.quickSearch}
                </div> }
                {this.$slots.searchForm}
                {this.$slots.topMessage}
                <div class='content-container pickup-view'>
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }
}