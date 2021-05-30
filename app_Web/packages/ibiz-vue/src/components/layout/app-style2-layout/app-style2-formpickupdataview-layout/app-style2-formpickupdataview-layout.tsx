
import { Prop,Component } from 'vue-property-decorator';
import { AppStyle2DefaultLayout } from '../app-style2-default-layout/app-style2-default-layout';
import "./app-style2-formpickupdataview-layout.less";

@Component({})
export class AppStyle2FormPickupDataViewLayout extends AppStyle2DefaultLayout {

    /**
     * 绘制内容
     * 
     * @memberof AppStyle2FormPickupDataViewLayout
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