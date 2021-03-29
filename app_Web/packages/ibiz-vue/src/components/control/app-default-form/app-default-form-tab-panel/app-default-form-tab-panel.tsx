import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';
import { AppDefaultFormDetail } from '../app-default-form-detail/app-default-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultFormTabPanel
 * @extends {Vue}
 */
@Component({})
export class AppDefaultFormTabPanel extends AppDefaultFormDetail {

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultFormTabPanel
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        let { codeName, caption, layoutmode, titleBarCloseMode } = this.detailsInstance;
        // 设置默认值
        layoutmode = layoutmode || 'TABLE_24COL';
        titleBarCloseMode = Number(titleBarCloseMode) || 0;
        caption = caption || codeName;
        return (
            <app-form-group
                layoutType={layoutmode}
                caption={caption}
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
