import { Component } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppDefaultViewLayout } from "../../app-default-layout/app-default-view-layout/app-default-view-layout";
import './app-style4-default-layout.less';

@Component({})
export class AppStyle4DefaultLayout extends AppDefaultViewLayout{

    /**
     * 绘制布局
     * 
     * @memberof AppStyle4DefaultLayout
     */
    public render(h: any) {
        let viewClass = {
            'view-container': true,
            'view-style4': true,
            [this.viewInstance.viewType.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance.codeName)]: true,
            [this.viewInstance.getPSSysCss()?.cssName || '']: true,
        };
        return (
            <div class={viewClass}>
                <app-studioaction
                    viewInstance={this.viewInstance}
                    context={this.context}
                    viewparams={this.viewparams}
                    viewName={this.viewInstance.codeName.toLowerCase()}
                    viewTitle={this.model?.srfCaption} />
                { this.renderContent()}
            </div>
        );
    }
}