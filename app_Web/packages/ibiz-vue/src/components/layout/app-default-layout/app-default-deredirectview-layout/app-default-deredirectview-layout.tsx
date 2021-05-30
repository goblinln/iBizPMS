import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import './app-default-deredirectview-layout.less';

@Component({})
export class AppDefaultDeRedirectViewLayout extends AppDefaultViewLayout {


    /**
     * 绘制内容
     * 
     * @memberof AppDefaultDeRedirectViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewIsshowToolbar,
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