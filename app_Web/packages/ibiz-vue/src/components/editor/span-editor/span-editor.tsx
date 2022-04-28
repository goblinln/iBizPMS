import { IPSAppCodeList, IPSAppDataEntity, IPSAppDEField, IPSCodeListEditor } from '@ibiz/dynamic-model-api';
import { DataTypes, ModelTool, Util } from 'ibiz-core';
import { IPSAppDEView, IPSAppView } from 'ibz-dynamic-core';
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
     * @description 链接需要的参数
     * @type {*}
     * @memberof SpanEditor
     */
    public spanLinkProps: any = {}

    /**
     * 编辑器初始化
     *
     * @memberof SpanEditor
     */
    public async initEditor() {
        await super.initEditor();
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        this.customProps.noValueShowMode = Object.is('FORM', this.containerCtrl?.controlType) ? 'STYLE1' : 'DEFAULT';
        switch (editorTypeStyle) {
              case 'SPAN':
                  await this.initSpan();
                  break;
              case 'SPANEX':
                  this.initSpanEX();
                  break;
              case 'SPAN_HTML':
                  this.initSpanHtml();
                  break;
              case 'SPAN_COLORSPAN':
                  await this.initSpanColorSpan();
                  break;
              case 'SPAN_AFTERTIME':
                  this.initSpanAfterTime();
                  break;
              case 'SPAN_ADDRESSPICKUP':
                  await this.initAddressPickUp();
                  break;
              case 'SPAN_LINK':
                  await this.initSpanLink();
                  break;

          }
    }

    /**
     * @description 解析标签链接参数
     * @memberof SpanEditor
     */
    public async initSpanLink(){
      await this.initSpan();
      let linkAppView: IPSAppView | null = (this.editorInstance as any)?.getLinkPSAppView?.();
      await (linkAppView as any)?.fill(true);
      if (linkAppView) {
          const view: any = {
              viewname: 'app-view-shell',
              title: this.$tl(linkAppView.getCapPSLanguageRes()?.lanResTag, linkAppView.title),
              width: linkAppView?.width,
              height: linkAppView?.height,
              placement: linkAppView?.openMode,
              deResParameters: Util.formatAppDERSPath(this.context, (linkAppView as IPSAppDEView).getPSAppDERSPaths()),
              isRedirectView: linkAppView.redirectView,
              viewpath: linkAppView?.modelPath
          }
          Object.defineProperty(view, 'viewModel', { enumerable: false, writable: true, value: linkAppView });
          if (linkAppView?.getPSAppDataEntity()) {
              Object.assign(view, {
                  parameters: [
                      { pathName: Util.srfpluralize(linkAppView.getPSAppDataEntity()?.codeName || '').toLowerCase(), parameterName: linkAppView.getPSAppDataEntity()?.codeName.toLowerCase() },
                      { pathName: 'views', parameterName: (linkAppView as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase() },
                  ]
              })
          } else {
              Object.assign(view, {
                  parameters: [
                      { pathName: 'views', parameterName: linkAppView.codeName.toLowerCase() }
                  ]
              })
          }
          this.spanLinkProps.linkview = view;
      }
    }

    /**
     * 解析标签值格式化参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */     
    public initFormatParams(){
        this.customProps.valueFormat = "";
        let unitName = this.parentItem?.unitName;
        let appDeField: IPSAppDEField= this.parentItem?.getPSAppDEField?.();
        if (appDeField?.stdDataType) {
            this.customProps.dataType = DataTypes.toString(appDeField.stdDataType);
        }
        if (appDeField?.valueFormat) {
            this.customProps.valueFormat = appDeField?.valueFormat;
        }
        if (this.valueFormat) {
          this.customProps.valueFormat = this.valueFormat;
        }
        if (this.editorInstance.editorParams?.valueFormat) {
          this.customProps.valueFormat = this.editorInstance.editorParams?.valueFormat;
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
        let appDeField: IPSAppDEField = this.parentItem?.getPSAppDEField?.();
        this.customProps.precision = ModelTool.getPrecision(this.editorInstance, appDeField);
    }

    /**
     * 解析标签代码表参数
     *
     * @return {*} 
     * @memberof SpanEditor
     */        
    public async initCodelistParams(){
        let codeList = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        if (codeList) {
          this.customProps.tag = codeList?.codeName;
          this.customProps.codelistType = codeList?.codeListType;
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
    public async initSpan(){
        let codeList: IPSAppCodeList | null  = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        this.customProps.codeList = codeList;
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
    public async initSpanColorSpan(){
        this.customProps.data = this.contextData;
        await this.initCodelistParams();
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
    public async initAddressPickUp(){
        let codeList: IPSAppCodeList | null  = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        this.customProps.codeList = codeList;
        this.customProps.data = JSON.stringify(this.contextData);
        this.customProps.editorType = "ADDRESSPICKUP";
        this.customProps.context = this.context;
        this.customProps.viewparams = this.viewparams;
        this.initFormatParams();
        this.initNumberParams();
        await this.initCodelistParams();
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
     * 绘制标签（链接）
     *
     * @memberof SpanEditor
     */
    public renderSpanLink(){
      let linkAppView: IPSAppView | null = (this.editorInstance as any)?.getLinkPSAppView?.();
      const linkViewEntity: IPSAppDataEntity | null = linkAppView?.getPSAppDataEntity?.();
      let tempContext: any = Util.deepCopy(this.context);
      if (this.spanLinkProps.linkview.viewpath) {
          Object.assign(tempContext, { viewpath: this.spanLinkProps.linkview.viewpath });
      }
      let tempViewParam: any = Util.deepCopy(this.viewparams);
      let localContext: any = {}
      let localParam: any = {}
      if(this.editorInstance){
        localContext = ModelTool.getNavigateContext(this.editorInstance)
        localParam = ModelTool.getNavigateParams(this.editorInstance)
      }
        return <app-column-link
            class="app-span-link"
            deKeyField={linkViewEntity && linkViewEntity.codeName ? linkViewEntity.codeName?.toLowerCase() : ""}
            context={tempContext}
            viewparams={tempViewParam}
            data={this.contextData}
            linkview={this.spanLinkProps.linkview}
            localContext={localContext}
            localParam={localParam}
            valueitem={this.parentItem?.valueItemName}
        >
          {this.$createElement('app-span',{
              props: {
                  name: this.editorInstance.name,
                  value: this.value,
                  disabled: this.disabled,
                  data: this.contextData,
                  ...this.customProps,
              },
              style: this.customStyle,
              on: { change: this.editorChange }
          })}
    </app-column-link>;
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
        }else if(editorTypeStyle == "SPAN_LINK"){
          debugger
            return this.renderSpanLink()
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
