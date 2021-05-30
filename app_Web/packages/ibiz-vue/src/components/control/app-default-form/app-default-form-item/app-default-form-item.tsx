import { Subject } from 'rxjs';
import { Component, Prop} from 'vue-property-decorator';
import { AppDefaultFormDetail } from '../app-default-form-detail/app-default-form-detail';
import { IPSDEFormItemEx } from '@ibiz/dynamic-model-api';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultFormItem
 * @extends {Vue}
 */
@Component({})
export class AppDefaultFormItem extends AppDefaultFormDetail {
    /**
     * 表单项实例对象
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() public detailsInstance!: IPSDEFormItemEx;

    /**
     * 表单的模型对象
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() public data: any;

    /**
     * 表单值规则
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() public rules: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() viewparams: any;
    
    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof AppDefaultFormItem
     */
    @Prop() formState!: Subject<any>;    

    /**
     * 表单服务对象
     *
     * @type {*}
     * @memberof AppDefaultFormItem
     */
    @Prop() service: any;    

    /**
     * 忽略表单项值变化
     *
     * @type {boolean}
     * @memberof AppDefaultFormItem
     */
    @Prop() ignorefieldvaluechange?: boolean;


    /**
     * 表单项值变化事件
     *
     * @memberof AppDefaultFormItem
     */
    public onFormItemValueChange(...args: any) {
        this.$emit('formItemValueChange', ...args);
    }

    /**
     * 绘制复合表单项
     *
     * @returns
     * @memberof AppDefaultFormItem
     */
    public renderCompositeItem() {
        const { name, contentHeight, contentWidth } = this.detailsInstance;
        let editor = this.detailsInstance.getPSEditor();
        let formItems = this.detailsInstance.getPSDEFormItems();
        let editorType = editor?.editorType;
        // 设置高宽
        let contentStyle: string = '';
        contentStyle += contentWidth && contentWidth != 0 ? `width:${contentWidth}px;` : '';
        contentStyle += contentHeight && contentHeight != 0 ? `height:${contentHeight}px;` : '';
        contentStyle += this.runtimeModel?.visible ? '' : 'display: none;';
        const refFormItem: any = [];
        if(formItems){
            formItems?.forEach((formItem: any) => {
                refFormItem.push(formItem?.name);
            });   
        }
        if (editor?.editorType !== 'USERCONTROL') {
          return (
              <app-range-editor
                  value={this.data[name]}
                  activeData={this.data}
                  name={name}
                  editorType={editorType}
                  refFormItem={refFormItem}
                  disabled={this.runtimeModel?.disabled}
                  format={editor?.editorParams?.['TIMEFMT']}
                  on-formitemvaluechange={(value: any) => {
                    this.onFormItemValueChange(value);
                  }}
                  style={contentStyle}
              ></app-range-editor>
          );
        } else {
          return (
          <app-default-editor
              editorInstance={editor}
              value={this.data[editor.name]}
              contextData={this.data}
              context={this.context}
              viewparams={this.viewparams}
              contextState={this.formState}
              service={this.service}
              disabled={this.runtimeModel?.disabled}
              ignorefieldvaluechange={this.ignorefieldvaluechange}
              on-change={(value: any) => {
                  this.onFormItemValueChange(value);
              }}
          />)
        }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultFormItem
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        let {
            name,
            caption,
            labelWidth,
            labelPos,
            showCaption,
            emptyCaption,
            detailStyle,
            compositeItem,
            contentWidth,
            contentHeight,
        } = this.detailsInstance;
        let editor = this.detailsInstance.getPSEditor();
        let sysCss = this.detailsInstance.getLabelPSSysCss();
        let editorType = editor?.editorType;
        // 隐藏表单项
        if (editorType == 'HIDDEN') {
            return;
        }
        // 设置高宽
        let contentStyle: string = '';
        contentStyle += contentWidth && contentWidth != 0 ? `width:${contentWidth}px;` : '';
        contentStyle += contentHeight && contentHeight != 0 ? `height:${contentHeight}px;` : '';
        contentStyle += this.runtimeModel?.visible ? '' : 'display: none;';
        return (
            <app-form-item
                name={name}
                caption={caption}
                isEmptyCaption={emptyCaption}
                isShowCaption={showCaption}
                labelWidth={labelWidth}
                labelPos={labelPos}
                uiStyle={detailStyle}
                itemRules={this.rules}
                required={this.runtimeModel?.required}
                error={this.runtimeModel?.error}
                class={detailClassNames}
                labelStyle={sysCss?.cssName}
                style={contentStyle}
                controlInstance={this.controlInstance}
            >
                { compositeItem ? (
                    this.renderCompositeItem()
                ) : this.$slots.default }
            </app-form-item>
        );
    }
}
