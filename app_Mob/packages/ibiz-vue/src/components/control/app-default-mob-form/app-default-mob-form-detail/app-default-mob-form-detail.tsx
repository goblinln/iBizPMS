import { IPSDEFormDetail } from '@ibiz/dynamic-model-api';
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';

/**
 * 表单成员基类
 *
 * @export
 * @class AppDefaultMobFormDetail
 * @extends {Vue}
 */
@Component({})
export class AppDefaultMobFormDetail extends Vue {
    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormDetail
     */
    @Prop() public detailsInstance!: IPSDEFormDetail;

    /**
     * 表单成员索引
     *
     * @type {number}
     * @memberof AppDefaultMobFormDetail
     */
    @Prop({ default: 0 }) public index!: number;

    /**
     * 表单模型对象
     *
     * @type {any}
     * @memberof AppDefaultMobFormDetail
     */
    @Prop() public formModel!: any;

    /**
     * 表单模型对象
     *
     * @type {any}
     * @memberof AppDefaultMobFormDetail
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单成员运行时模型对象
     *
     * @type {any}
     * @memberof AppDefaultMobFormDetail
     */
    @Prop() public runtimeModel!: any;

    /**
     * 模型服务对象
     * 
     * @memberof AppDefaultFormDetail
     */
    @Prop() public modelService?: any;

    /**
     * 监听表单成员实例对象变化
     *
     * @type {*}
     * @memberof AppDefaultMobFormDetail
     */
    @Watch('detailsInstance', { immediate: true })
    detailsInstanceChange() {
        this.initDetail();
    }

    /**
     * 绘制参数
     *
     * @type {*}
     * @memberof AppDefaultMobForm
     */
    public renderOptions: any = {
        detailClassNames: {}
    };

    /**
     * 初始化解析formModel
     *
     * @memberof AppDefaultMobForm
     */
    public initDetail() {
        const sysCss = this.detailsInstance.getPSSysCss();
        this.renderOptions = {};
        // 表单成员类名
        const detailClassNames: any = {};
        if (sysCss?.cssName) {
            Object.assign(detailClassNames, { [sysCss?.cssName]: true });
        }
        this.$set(this.renderOptions, 'detailClassNames', detailClassNames);
    }
}
