import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import './app-default-mob-tabexpview-layout.less';
import { ThirdPartyService } from "ibiz-core";

@Component({})
export class AppDefaultMobTabExpViewLayout extends AppDefaultViewLayout{
    

    

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultMobTabExpViewLayout
     */
    public renderViewHeader() {
        return <ion-header>
            {!ThirdPartyService.getInstance().platform ? this.$slots.captionbar && !this.viewInstance.isPartsView ? this.$slots.captionbar : null:null}
            {this.$slots.toolbar}
            {this.$slots.quicksearch}
            {this.$slots.quickGroupSearch}
            {this.$slots.segment}
            {this.$slots.topMessage} 
        </ion-header>
    }
}