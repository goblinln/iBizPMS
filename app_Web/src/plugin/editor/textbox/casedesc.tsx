
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 动态placeholder插件类
 *
 * @export
 * @class Casedesc
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class Casedesc extends EditorBase {
    
        /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof TextboxEditor
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    public render() {
        const placeholder = this.contextData?.row?.type
        switch (placeholder) {
            case 'group':
                this.customProps.placeholder = '分组名称'
                break;
            case 'item':
                this.customProps.placeholder = '分组步骤名称'
                break;
            case 'step':
                this.customProps.placeholder = '步骤名称'
                break;

            default:
                this.customProps.placeholder = ''
                break;
        }
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('input-box', {
            props: {
                name: this.editorInstance.name,
                itemValue: this.value,
                data: this.contextData,
                ...this.customProps,
            },
            on: { change: this.handleChange},
            style: this.customStyle
        })
    }
}
