import { IPSDEFormDetail } from '@ibiz/dynamic-model-api';
import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';

/**
 * 表单成员基类
 *
 * @export
 * @class AppDefaultFormDetail
 * @extends {Vue}
 */
@Component({})
export class AppDefaultFormDetail extends Vue {
    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public detailsInstance!: IPSDEFormDetail;

    /**
     * 表单成员索引
     *
     * @type {number}
     * @memberof AppDefaultFormDetail
     */
    @Prop({ default: 0 }) public index!: number;

    /**
     * 表单模型对象
     *
     * @type {any}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public formModel!: any;

    /**
     * 表单模型对象
     *
     * @type {any}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单成员运行时模型对象
     *
     * @type {any}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public runtimeModel!: any;

    /**
     * 应用上下文
     *
     * @type {any}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public context: any;

    /**
     * 视图参数
     *
     * @type {any}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public viewparams: any;

    /**
     * 监听表单成员实例对象变化
     *
     * @type {*}
     * @memberof AppDefaultFormDetail
     */    
    @Watch('detailsInstance',{immediate: true})
    detailsInstanceChange(){
      this.initDetail();
    }

    /**
     * 绘制参数
     *
     * @type {*}
     * @memberof AppDefaultForm
     */
    public renderOptions: any = {
        detailClassNames: {}
    };

    /**
     * 初始化解析formModel
     *
     * @memberof AppDefaultForm
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
