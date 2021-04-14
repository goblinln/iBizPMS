
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 表格行编辑下拉扩展插件类
 *
 * @export
 * @class RowEditExtend
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class RowEditExtend extends EditorBase {
    
    /**
     * 编辑器初始化
     *
     * @memberof RowEditExtend
     */
    public initEditor() {
        //TODO
        this.customProps.localContext = {};
        this.customProps.localParam = {};
        this.customProps.placeholder = '请选择...';
        let codeList: any = this.editorInstance.codeList;
        if(codeList) {
            Object.assign(this.customProps, {
                tag: codeList.codeName,
                codelistType: codeList.codeListType
            });
        }
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof RowEditExtend
     */
    public handleChange($event: any){
        this.editorChange({name: this.editorInstance.name, value: $event})
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof DropdownListEditor
     */
    public render(): any {
        if(!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('dropdown-list-extend', {
            props: {
                key: `'RowEdit'${this.editorInstance.name}`,
                name: this.editorInstance.name,
                itemValue: this.value,
                disabled: this.disabled,
                data: this.contextData?.row,
                datas: this.contextData?.datas,
                context: this.context,
                viewparams: this.viewparams,
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle,
        });
    }

}