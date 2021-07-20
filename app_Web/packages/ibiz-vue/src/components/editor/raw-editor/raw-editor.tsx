import { Component } from 'vue-property-decorator';
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
     * 直接内容类型
     * 
     * @memberof RawEditor
     */
    public contentType: string = '';

    /**
     * 编辑器初始化
     *
     * @memberof RawEditor
     */
    public async initEditor() {
        await super.initEditor();
        this.contentType = this.editorInstance.editorParams?.['CONTENTTYPE'] || 'RAW';
        this.customProps.contentType = this.contentType;
    }

    /**
     * 绘制直接内容的值
     * 
     * @memberof RawEditor
     */
    public renderRaw(rawContent: any) {
        if (!Object.is('RAW', this.contentType)) {
            return null;
        }
        const data = this.contextData;
        if (rawContent) {
            const items = rawContent.match(/\{{(.+?)\}}/g);
            if (items) {
                items.forEach((item: string) => {
                    rawContent = rawContent.replace(/\{{(.+?)\}}/, eval(item.substring(2, item.length - 2)));
                });
            }
        }
        return this.$createElement('div', {
            domProps: {
                innerHTML: rawContent,
            },
        });
    }

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
        return this.$createElement(this.editorComponentName, {
            props: {
                htmlContent: this.value,
                imgUrl: this.value,
                itemValue: this.value,
                ...this.customProps,
            },
            scopedSlots: {
                default: () => {
                    return this.renderRaw(this.value);
                }
            },
            style: this.customStyle,
        });
    }
}
