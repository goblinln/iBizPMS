import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';

/**
 * 表单成员基类
 *
 * @export
 * @class AppDefaultSearchFormDetail
 * @extends {Vue}
 */
@Component({})
export class AppDefaultSearchFormDetail extends Vue {
    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultSearchFormDetail
     */
    @Prop() public detailsInstance!: any;

    /**
     * 表单成员索引
     *
     * @type {number}
     * @memberof AppDefaultSearchFormDetail
     */
    @Prop({ default: 0 }) public index!: number;

    /**
     * 表单模型对象
     *
     * @type {any}
     * @memberof AppDefaultSearchFormDetail
     */
    @Prop() public formModel!: any;

    /**
     * 表单模型对象
     *
     * @type {any}
     * @memberof AppDefaultSearchFormDetail
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单成员运行时模型对象
     *
     * @type {any}
     * @memberof AppDefaultSearchFormDetail
     */
    @Prop() public runtimeModel!: any;

    /**
     * 监听表单成员实例对象变化
     *
     * @type {*}
     * @memberof AppDefaultSearchFormDetail
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
        const { getPSSysCss } = this.detailsInstance;
        this.renderOptions = {};
        // 表单成员类名
        const detailClassNames: any = {};
        if (getPSSysCss?.cssName) {
            Object.assign(detailClassNames, { [getPSSysCss?.cssName]: true });
        }
        this.$set(this.renderOptions, 'detailClassNames', detailClassNames);
    }
}
