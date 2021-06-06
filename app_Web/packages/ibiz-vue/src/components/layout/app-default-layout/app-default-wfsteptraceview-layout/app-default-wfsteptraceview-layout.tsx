import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import './app-default-wfsteptraceview-layout.less';
import { Util } from "ibiz-core";

@Component({})
export class AppDefaultWfStepTraceViewLayout extends AppDefaultViewLayout {

    /**
     * 绘制布局
     * 
     * @memberof AppDefaultWfStepTraceViewLayout
     */
    public render(h: any) {
        let viewClass = {
            'view-container': true,
            'view-default': true,
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
                    viewTitle={this.model.srfCaption} />
                 {this.$slots.default}
            </div>
        );
    }

}