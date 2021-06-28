import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop, Component } from 'vue-property-decorator';
import './app-default-deportalview-layout.less';

@Component({})
export class AppDefaultDePortalViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultDePortalViewLayout
     */
    public renderViewHeader(): any {
        return [
            this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.model.srfCaption}</span> : null,
            this.viewIsshowToolbar ? <div class='toolbar-container bar-container'>
                {this.$slots.toolbar}
            </div> : null,
        ]
    }

}