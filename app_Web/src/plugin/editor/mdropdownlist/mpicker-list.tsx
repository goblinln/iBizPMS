
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';
import { Prop, Watch } from 'vue-property-decorator';
import { IPSAppDEField, IPSDropDownList } from '@ibiz/dynamic-model-api';
import { ModelTool, Util } from 'ibiz-core';

/**
 * 多选列表插件类
 *
 * @export
 * @class MpickerList
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class MpickerList extends EditorBase {
    
    /**
   * 编辑器模型
   *
   * @type {*}
   * @memberof EditorBase
   */
  @Prop() editorInstance!: IPSDropDownList;

  /**
   * 监听编辑器值
   * 
   * 
   * @param newVlue 
   * @param oldVal 
   * @memberof EditorBase
   */
  @Watch('value', {
    deep: true,
    immediate: true
  })
  public valueChange(newVlue: any, oldVal: any) {
    if (!Object.is(newVlue, oldVal)) {
      this.cacheUUID = Util.createUUID();
    }
  }

  /**
   * 缓存UUID
   * 
   * @memberof EditorBase
   */
  public cacheUUID: any;

  /**
   * 编辑器初始化
   *
   * @memberof DropdownListEditor
   */
  public async initEditor() {
    await super.initEditor();
    const { placeHolder } = this.editorInstance;
    let appDEField: IPSAppDEField = this.parentItem?.getPSAppDEField?.();
    this.customProps.valueType = ModelTool.isNumberField(appDEField) ? 'number' : 'string';
    this.customProps.placeholder = placeHolder || this.$t('components.dropdownlist.placeholder');
    let codeList: any = this.editorInstance?.getPSAppCodeList();
    if (codeList.isFill) {
      Object.assign(this.customProps, {
        valueSeparator: codeList.valueSeparator,
        tag: codeList.codeName,
        codeList: codeList,
        codelistType: codeList.codeListType
      });
    }
  }

  /**
   * 编辑器change事件
   *
   * @param {*} value
   * @memberof DropdownListEditor
   */
  public handleChange($event: any) {
    this.editorChange({ name: this.editorInstance.name, value: $event })
  }

  /**
   * 绘制内容
   *
   * @returns {*}
   * @memberof DropdownListEditor
   */
  public render(): any {
    if (!this.editorIsLoaded) {
      return null;
    }
    return this.$createElement('app-mpicker-list', {
      key: this.cacheUUID,
      props: {
        name: this.editorInstance.name,
        itemValue: this.value,
        disabled: this.disabled,
        data: this.contextData,
        context: this.context,
        viewparams: this.viewparams,
        formState: this.contextState,
        ...this.customProps,
      },
      on: { change: this.handleChange },
      style: this.customStyle,
    });
  }
}
