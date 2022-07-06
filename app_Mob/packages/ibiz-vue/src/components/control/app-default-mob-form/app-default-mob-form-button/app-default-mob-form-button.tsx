import { IPSDEFormButton } from "@ibiz/dynamic-model-api";
import { Component, Prop } from "vue-property-decorator";
import { AppDefaultMobFormDetail } from "../app-default-mob-form-detail/app-default-mob-form-detail";

@Component({})
export class AppDefaultMobFormButton extends AppDefaultMobFormDetail {
    /**
     * 表单项实例对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormButton
     */
    @Prop() public detailsInstance!: IPSDEFormButton;

    /**
     * 表单的模型对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormButton
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppDefaultMobFormButton
     */
    @Prop() public data: any;

    /**
     * 表单值规则
     *
     * @type {*}
     * @memberof AppDefaultMobFormButton
     */
    @Prop() public rules: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppDefaultMobFormButton
     */
    @Prop() context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDefaultMobFormButton
     */
    @Prop() viewparams: any;

    /**
     * 处理按钮点击
     * @param {*} event
     * @memberof AppDefaultMobFormButton
     */
    public handleActionClick(event: any) {
        this.$emit('uIAction', { formDetail: this.detailsInstance, event: event });
    }

    render() {
        const { detailClassNames } = this.renderOptions;
        const { caption } = this.detailsInstance;
        const customStyle = this.runtimeModel.visible ? '' : 'display: none;';
        return (
            <div
                class={detailClassNames}
                style={customStyle}
                >
                    <app-mob-button
                        class="app-form-button"
                        text={caption}
                        disabled={this.runtimeModel.disabled}
                        flexType={'horizontal'}
                        showDefaultIcon={false}
                        on-click={(event: any) => {this.handleActionClick(event)}}>
                    </app-mob-button>
            </div>
        )
    }
}