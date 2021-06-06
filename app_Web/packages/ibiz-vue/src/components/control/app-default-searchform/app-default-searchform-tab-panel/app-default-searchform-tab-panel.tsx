import { Component } from 'vue-property-decorator';
import { IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { AppDefaultSearchFormDetail } from '../app-default-searchform-detail/app-default-searchform-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultSearchFormTabPanel
 * @extends {Vue}
 */
@Component({})
export class AppDefaultSearchFormTabPanel extends AppDefaultSearchFormDetail {

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultSearchFormTabPanel
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        let { codeName, caption, layoutmode, titleBarCloseMode } = this.detailsInstance;
        // 设置默认值
        layoutmode = layoutmode || 'TABLE_24COL';
        titleBarCloseMode = Number(titleBarCloseMode) || 0;
        let labelCaption: any = this.$tl((this.detailsInstance.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, caption);
        return (
            <app-form-group
                layoutType={layoutmode}
                caption={labelCaption}
                isShowCaption={true}
                uiStyle='DEFAULT'
                titleBarCloseMode={titleBarCloseMode}
                isInfoGroupMode={false}
                key={codeName}
                class={detailClassNames}
            >
                {this.$slots.default}
            </app-form-group>
        );
    }
}
