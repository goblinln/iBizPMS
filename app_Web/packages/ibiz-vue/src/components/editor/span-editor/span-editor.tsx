import { Util } from 'ibiz-core';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 文本框编辑器
 *
 * @export
 * @class SpanEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class SpanEditor extends EditorBase {

    /**
     * 编辑器初始化
     *
     * @memberof SpanEditor
     */
    public initEditor() {
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch (editorTypeStyle) {
              case 'SPAN':
                  this.initSpan();
                  break;
              case 'SPANEX':
                  this.initSpanEX();
                  break;
              case 'SPAN_HTML':
                  this.initSpanHtml();
                  break;
              case 'SPAN_COLORSPAN':
                  this.initSpanColorSpan();
                  break;
              case 'SPAN_AFTERTIME':
                  this.initSpanAfterTime();
                  break;
              case 'SPAN_ADDRESSPICKUP':
                  this.initAddressPickUp();
                  break;

          }
    }

    /**
     * 解析标签值格式化参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */     
    public initFormatParams(){
        const { unitName, getPSDEField } = this.editorInstance;
        if (getPSDEField?.dataType) {
          this.customProps.dataType = getPSDEField?.dataType;
        }
        if (getPSDEField?.valueFormat) {
        //   this.customProps.valueFormat = getPSDEField?.valueFormat;
        }
        if (unitName) {
          this.customProps.unitName = unitName;
        }
    }

    /**
     * 解析标签数值精度参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */     
    public initNumberParams(){
        const { precision } = this.editorInstance;
        if(!Util.isEmpty(precision)){
            this.customProps.precision = precision;
        }
    }

    /**
     * 解析标签代码表参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */        
    public initCodelistParams(){
        const { codeList } = this.editorInstance;
        if (codeList) {
          this.customProps.tag = codeList?.codeName;
          this.customProps.codelistType = codeList?.codelistType;
          this.customProps.renderMode = codeList?.orMode;
          this.customProps.valueSeparator = codeList?.valueSeparator;
          this.customProps.textSeparator = codeList?.textSeparator;
        }
    }


    /**
     * 解析标签参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */    
    public initSpan(){
        const { codeList, getPSDEField, precision } = this.editorInstance;
        this.customProps.codeList = codeList;
        this.customProps.data = JSON.stringify(this.contextData);
        this.customProps.context = this.context;
        this.customProps.viewparams = this.viewparams;
        this.initFormatParams();
        this.initNumberParams();
    }

    /**
     * 解析旧标签参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */      
    public initSpanEX(){
        this.customProps.data = JSON.stringify(this.contextData);
        this.customProps.readonly = true;
        this.customProps.disabled = true;
        this.customProps.placeholder = this.editorInstance?.placeHolder;
    }

    /**
     * 解析标签（格式化信息）参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */         
    public initSpanHtml(){
        this.customProps.data = JSON.stringify(this.contextData);
    }

    /**
     * 解析标签（颜色）参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */     
    public initSpanColorSpan(){
        this.customProps.data = this.contextData;
        this.initCodelistParams();
        this.customProps.context = this.context;
        this.customProps.viewparams = this.viewparams;
    }

    /**
     * 解析标签（多久之前）参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */      
    public initSpanAfterTime(){
        this.customProps.data = JSON.stringify(this.contextData);
        this.customProps.formState = JSON.stringify(this.contextData);
        this.customProps.context = this.context;
        this.customProps.viewparams = this.viewparams;
    }

    /**
     * 解析标签（地址栏）参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */     
    public initAddressPickUp(){
        const { codeList } = this.editorInstance;
        this.customProps.codeList = codeList;
        this.customProps.data = JSON.stringify(this.contextData);
        this.customProps.editorType = "ADDRESSPICKUP";
        this.customProps.context = this.context;
        this.customProps.viewparams = this.viewparams;
        this.initFormatParams();
        this.initNumberParams();
        this.initCodelistParams();
    }

    /**
     * 绘制标签（格式化信息）
     *
     * @memberof SpanEditor
     */
    public renderSpanHtml(){
        const tempNode = this.$createElement('div',{
            domProps:{
                innerHTML: this.value,
            },
            class:'span-html'
        })
        return tempNode;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof SpanEditor
     */
    public render(): any {
        if(!this.editorIsLoaded){
           return 
        }
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        if (editorTypeStyle == "SPAN_HTML") {
            return this.renderSpanHtml();
        }
        return this.$createElement(this.editorComponentName,{
          props: {
              name: this.editorInstance.name,
              value: this.value,
              disabled: this.disabled,
              data: this.contextData,
              ...this.customProps,
          },
          style: this.customStyle,
          on: { change: this.editorChange }
      })

    }


}
