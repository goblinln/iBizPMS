import { Component } from 'vue-property-decorator';
import { AppStyle2DefaultLayout } from '../app-style2-default-layout/app-style2-default-layout';
import './app-style2-deredirectview-layout.less';

@Component({})
export class AppStyle2DeRedirectViewLayout extends AppStyle2DefaultLayout {


    /**
     * 绘制内容
     * 
     * @memberof AppStyle2DeRedirectViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewInstance.viewIsshowToolbar,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                <div class="context-container">
                    <img src="./assets/img/500.png" />
                    <div class="context">跳转中......</div>
                </div>
            </card>
        );
    }
}