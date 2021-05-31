import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop, Component } from 'vue-property-decorator';
import './app-default-mob-mdview-layout.less';
import { ThirdPartyService } from "ibiz-core";

@Component({})
export class AppDefaultMobMdViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewHeader() {
        return !this.isEmbedView ? <ion-header>
            {!ThirdPartyService.getInstance().platform ?
                this.$slots.captionbar : null}
            {this.$slots.toolbar}
            { this.$slots.quicksearch }
            {this.$slots.quickGroupSearch}
            {this.$slots.mdviewtools}
            {this.$slots.expmdviewtoolbar}
        </ion-header>: <div></div>
    }

    /**
     * renderSearchForm
     */
    public renderSearchForm() {
        return <div>{this.$slots.searchForm}</div>;
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderContent() {
        return [
            this.renderViewHeader(),
            this.renderSearchForm(),
            this.renderViewContent(),
            this.renderViewFooter()
        ];
    }
    
    /**
     * 绘制内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewContent(){
        return (this.$slots.ioncontent) as any;
    }
}