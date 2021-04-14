import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 直接内容编辑器
 *
 * @export
 * @class RawEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class RawEditor extends EditorBase {

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof RawEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        return <div style={this.customStyle}>{this.value}</div>
    }
}
