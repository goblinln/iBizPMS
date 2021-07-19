import { Vue, Component, Prop } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import './app-form-group-data-panel.less';

/**
 * 表单分组
 *
 * @export
 * @class AppFormGroupDataPanel
 * @extends {Vue}
 */
@Component({})
export default class AppFormGroupDataPanel extends Vue {
    /**
     * 表单分组模型
     *
     * @type {*}
     * @memberof AppFormGroupDataPanel
     */
    @Prop({ default: () => {} })
    public detailModel: any;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppFormGroupDataPanel
     */
    @Prop({ default: () => {} })
    public data!: any;

    /**
     * 上下文
     *
     * @type {*}
     * @memberof AppFormGroupDataPanel
     */
    @Prop()
    public context?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppFormGroupDataPanel
     */
    @Prop()
    public viewparams?: any;

    /**
     * 获取项标题
     *
     * @protected
     * @param {*} item
     * @returns {*}
     * @memberof AppFormGroupDataPanel
     */
    protected getCaption(item: any): any {
        if (Util.isExistAndNotEmpty(item.langbase)) {
            return this.$t(`${item.langbase}.${item.name}`);
        }
        return item.caption;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppFormGroupDataPanel
     */
    public render(): any {
        const data: any[] = this.detailModel?.anchorPoints;
        if (data) { 
            const items: any[] = [];
            data.forEach((item: any, index: number) => {
                const { getPSDEField, codeList, unitName } = item.editor;
                if (index > 0) {
                    items.push(
                        <span class='seperator'>|</span>
                    );
                };
                items.push(
                    <span class="data-item">
                        <app-span
                            name={item.name}
                            value={this.data[item.name]}
                            noValueShowMode={'STYLE1'}
                            data={this.data}
                            context={this.context}
                            viewparams={this.viewparams}
                            dataType={getPSDEField && getPSDEField.dataType ? getPSDEField.dataType : null}
                            unitName={unitName ? unitName : null}
                            codeList={codeList ? codeList : null}
                        ></app-span>
                    </span>
                );
            });
            return <span class="form-group-data-panel">{items}</span>;
        }
    }
}
