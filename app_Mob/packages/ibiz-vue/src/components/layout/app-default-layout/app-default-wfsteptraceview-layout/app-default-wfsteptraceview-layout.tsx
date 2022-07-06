import { AppDefaultViewLayout } from '../app-default-view-layout/app-default-view-layout';
import { Component } from 'vue-property-decorator';
import './app-default-wfsteptraceview-layout.less';
import { Util } from 'ibiz-core';

@Component({})
export class AppDefaultWfStepTraceViewLayout extends AppDefaultViewLayout {
    /**
     * 绘制布局
     *
     * @memberof AppDefaultViewLayout
     */
    public render(h: any) {
        let viewClass = {
            'view-container': true,
            [this.viewInstance.viewType?.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance.codeName)]: true,
        };
        return <div class={viewClass}>{this.$slots.default}</div>;
    }
}
