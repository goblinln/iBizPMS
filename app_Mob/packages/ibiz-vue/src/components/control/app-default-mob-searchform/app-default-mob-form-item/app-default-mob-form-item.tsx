import { Subject } from 'rxjs';
import { Component, Prop } from 'vue-property-decorator';
import { AppDefaultMobFormDetail } from '../app-default-mob-form-detail/app-default-mob-form-detail';
import { IPSDEFormItemEx, IPSLanguageRes } from '@ibiz/dynamic-model-api';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultMobFormItem
 * @extends {Vue}
 */
@Component({})
export class AppDefaultMobFormItem extends AppDefaultMobFormDetail {
    /**
     * 表单项实例对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() public detailsInstance!: IPSDEFormItemEx;

    /**
     * 表单的模型对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() public data: any;

    /**
     * 表单值规则
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() public rules: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() viewparams: any;

    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() formState!: Subject<any>;

    /**
     * 表单服务对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() service: any;

    /**
     * 忽略表单项值变化
     *
     * @type {boolean}
     * @memberof AppDefaultMobFormItem
     */
    @Prop() ignorefieldvaluechange?: boolean;

    /**
     * 模型服务
     *
     * @type {AppModelService}
     * @memberof AppDefaultMobFormItem
     */
     @Prop() modelService ?: any;    

    /**
     * 表单项值变化事件
     *
     * @memberof AppDefaultMobFormItem
     */
    public onFormItemValueChange(...args: any) {
        this.$emit('formItemValueChange', ...args);
    }

    /**
     * 绘制复合表单项
     *
     * @returns
     * @memberof AppDefaultMobFormItem
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
        if (editor?.editorType !== 'USERCONTROL') {
            return (
                <app-range-editor
                    v-model={this.data[name]}
                    activeData={this.data}
                    name={name}
                    disabled={this.runtimeModel?.disabled}
                    editorType={editorType}
                    on-formitemvaluechange={this.onFormItemValueChange}
                    style={contentStyle}
                ></app-range-editor>
            );
        } else {
            return (
                <app-default-editor
                    editorInstance={editor}
                    value={this.data[editor?.name]}
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
     * @memberof AppDefaultMobFormItem
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
        contentStyle += this.runtimeModel?.visible ? '' : 'display: none;';
        const { formStyle } = this.controlInstance;
        let labelCaption: any = this.$tl((this.detailsInstance.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, caption);
        return (
            formStyle === 'MOBSEARCHBAR2' ?
                <app-form-item2
                    name={name}
                    itemType={editorType}
                    caption={labelCaption}
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
                    itemValue={this.data[name]}
                    controlInstance={this.controlInstance}
                >
                    {compositeItem ? (
                        this.renderCompositeItem()
                    ) : this.$slots.default}
                </app-form-item2> :
                <app-form-item
                    name={name}
                    caption={labelCaption}
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
                    itemValue={this.data[name]}
                    controlInstance={this.controlInstance}
                >
                    {compositeItem ? (
                        this.renderCompositeItem()
                    ) : this.$slots.default}
                </app-form-item>
        );
    }
}
