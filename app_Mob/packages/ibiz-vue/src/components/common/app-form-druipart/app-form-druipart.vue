<template>
  <div class="form-druipart">
    <ion-list v-show="blockUI" id="skeleton">
      <template v-for="item in 3">
        <ion-item :key="item">
          <ion-thumbnail slot="start">
            <ion-skeleton-text></ion-skeleton-text>
          </ion-thumbnail>
          <ion-label>
            <h3>
              <ion-skeleton-text animated style="width: 80%"></ion-skeleton-text>
            </h3>
            <p>
              <ion-skeleton-text animated style="width: 60%"></ion-skeleton-text>
            </p>
            <p>
              <ion-skeleton-text animated style="width: 30%"></ion-skeleton-text>
            </p>
          </ion-label>
        </ion-item>
      </template>
    </ion-list>
    <div v-show="!blockUI" class="form-druipart-text">{{caption}}</div>
    <div v-show="!blockUI">
      <component
        class="viewcontainer2"
        ref="appFormDruipart"
        :is="'app-view-shell'"
        :staticProps="{
          viewDefaultUsage: 'includedView',
          isformDruipart: true,
          isChildView : true,
          formDruipartState: this.formDruipart,
          viewModelData: this.viewModelData
        }"
        :dynamicProps="{
          _context: JSON.stringify(tempContext),
          _viewparams: JSON.stringify(tempViewParams)
        }"
        @mditemsload="mditemsload"
        @drdatasaved="drdatasaved"
        @drdatachange="drdatachange"
        @viewdataschange="viewdataschange"
        @viewload="viewload"
      ></component>
    </div>
  </div>
</template>

<script lang = 'ts'>
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { Subject, Unsubscribable } from "rxjs";
import "./app-form-druipart.less";

@Component({})
export default class AppFormDRUIPart extends Vue {
    /**
     * 表单数据
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop() public data!: string;

    /**
     * 表单名称
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop() public caption!: string;

    /**
     * 关联视图
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop() public viewname?: string;

    /**
     * 刷新关系项
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop({ default: "" }) public refreshitems!: string;


    /**
     * 临时数据模式：从数据模式:"2"、主数据模式:"1"、无临时数据模式:"0"
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop({ default: "0" }) public tempMode?: string;

    /**
     * 父数据
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    @Prop() public parentdata!: any;

   /**
     * 视图模型
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop() public viewModelData!: any;

    /**
     * 传入参数项名称
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop() public paramItem!: string;

    /**
     * 是否忽略表单项值变化
     *
     * @type {boolean}
     * @memberof AppFormDRUIPart
     */
    @Prop() public ignorefieldvaluechange!: boolean;

    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof AppFormDRUIPart
     */
    @Prop() public formState!: Subject<any>;

    /**
     * 视图参数
     *
     * @type {any[]}
     * @memberof AppFormDRUIPart
     */
    @Prop() public parameters!: any[];

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    @Prop() public viewparams!: any;

    /**
     * 应用实体参数名称
     *
     * @type {string}
     * @memberof AppFormDRUIPart
     */
    @Prop() public parameterName!: string;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop({ default: ()=>{} }) protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop({ default: ()=>{} }) protected navigateContext?: any;

    /**
     * 关系界面向视图下发指令对象
     *
     * @private
     * @type {Subject<any>}
     * @memberof AppFormDRUIPart
     */
    private formDruipart: Subject<any> = new Subject<any>();

    /**
     * 表单状态事件
     *
     * @private
     * @type {(Unsubscribable | undefined)}
     * @memberof AppFormDRUIPart
     */
    private formStateEvent: Unsubscribable | undefined;

    /**
     * 监控值
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppFormDRUIPart
     */
    @Watch("data")
    onActivedataChange(newVal: any, oldVal: any) {
        if (this.ignorefieldvaluechange) {
            return;
        }
        if (Object.is(newVal, oldVal)) {
            return;
        }
        const newFormData: any = JSON.parse(newVal);
        const oldDormData: any = JSON.parse(oldVal);
        let refreshRefview = false;
        this.hookItems.some((_hookItem: any) => {
            if (!Object.is(newFormData[_hookItem], oldDormData[_hookItem])) {
                refreshRefview = true;
                return refreshRefview;
            }
            return refreshRefview;
        });
        if (refreshRefview) {
            this.refreshDRUIPart();
        }
    }

    /**
     * 是否启用遮罩
     *
     * @type {boolean}
     * @memberof AppFormDRUIPart
     */
    public blockUI: boolean = false;



    /**
     * 是否刷新关系数据
     *
     * @private
     * @type {boolean}
     * @memberof AppFormDRUIPart
     */
    private isRelationalData: boolean = true;

    /**
     * 刷新节点
     *
     * @private
     * @type {string[]}
     * @memberof AppFormDRUIPart
     */
    private hookItems: string[] = [];

    /**
     * 父视图参数
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    public tempViewParams: any = {};

    /**
     * 父视图参数
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    public tempContext: any = {};

    /**
     * 刷新关系页面
     *
     * @private
     * @returns {void}
     * @memberof AppFormDRUIPart
     */
    private refreshDRUIPart(data?: any): void {
        if (Object.is(this.parentdata.SRFPARENTTYPE, "CUSTOM")) {
            this.isRelationalData = false;
        }
        const formData: any = data ? data : JSON.parse(this.data);
        const _paramitem = formData[this.paramItem];
        let _context = {};
        Object.assign(_context, this.$viewTool.getIndexViewParam());
        const _parameters: any[] = [
            ...this.$viewTool.getIndexParameters(),
            ...this.parameters
        ];
        _parameters.forEach((parameter: any) => {
            const {
                pathName,
                parameterName
            }: { pathName: string; parameterName: string } = parameter;
            if (formData[parameterName] && !Object.is(formData[parameterName], "")) {
                Object.assign(_context, { [parameterName]: formData[parameterName] });
            }
        });
        Object.assign(_context, { [this.paramItem]: _paramitem });
        //设置顶层视图唯一标识
        Object.assign(_context, this.context);
        // 导航参数处理
        const { context, param } = this.$viewTool.formatNavigateParam(this.navigateContext, this.navigateParam, _context, this.viewparams, JSON.parse(this.data));
        Object.assign(this.tempContext, context);
        Object.assign(this.tempViewParams, param);
        if (this.isRelationalData) {
            if (this.tempMode && Object.is(this.tempMode, 2)) {
                this.blockUIStop();
            } else {
                if (!_paramitem || _paramitem == null || Object.is(_paramitem, '')) {
                    this.blockUIStart();
                    return;
                } else {
                    this.blockUIStop();
                }
            }
        }
        this.partViewEvent("load", {} );
    }

    /**
     * vue  生命周期
     *
     * @memberof AppFormDRUIPart
     */
    public created(): void {
        this.hookItems = [...this.refreshitems.split(";")];
        if (!this.formState) {
            return;
        }
        if (!Object.is(this.paramItem, this.parameterName)) {
            this.hookItems.push(this.paramItem);
        }
        this.formStateEvent = this.formState.subscribe(($event: any) => {
          // 表单加载完成
            if (Object.is($event.type, "load")) {
                this.refreshDRUIPart($event.data);
            }
            // 表单保存之前
            if (Object.is($event.type, "beforesave")) {
                if (this.tempMode && this.tempMode == '2') {
                    this.formDruipart.next({ action: "save", data: $event.data });
                } else {
                    if ($event.data && !Object.is($event.data.srfuf, "0")) {
                        this.formDruipart.next({ action: "save", data: $event.data });
                    } else {
                        this.$emit("drdatasaved", $event);
                    }
                }
            }
            // 表单保存完成
            if (Object.is($event.type, "save")) {
                this.refreshDRUIPart($event.data);
            }
            // 表单项更新
            if (Object.is($event.type, "updateformitem")) {
                if (!$event.data) {
                    return;
                }
                let refreshRefview = false;
                Object.keys($event.data).some((name: string) => {
                    const index = this.hookItems.findIndex((_name: string) =>
                        Object.is(_name, name)
                    );
                    refreshRefview = index !== -1 ? true : false;
                    return refreshRefview;
                });
                if (refreshRefview) {
                    this.refreshDRUIPart();
                }
            }
        });
        this.refreshDRUIPart();
    }

    /**
     * 部件销毁
     *
     * @memberof AppFormDRUIPart
     */
    public destroyed(): void {
        if (this.formStateEvent) {
            this.formStateEvent.unsubscribe();
        }
    }

    /**
     * 开启遮罩
     *
     * @private
     * @memberof AppFormDRUIPart
     */
    private blockUIStart(): void {
        this.blockUI = true;
    }

    /**
     * 关闭遮罩
     *
     * @private
     * @memberof AppFormDRUIPart
     */
    private blockUIStop(): void {
        this.blockUI = false;
    }

    /**
     * 多数据视图加载完成
     *
     * @public
     * @memberof AppFormDRUIPart
     */
    public mditemsload() {
        console.log("多数据视图加载完成，触发后续表单项更新");
    }

    /**
     * DEMEDITVIEW9 关系数据保存完成
     *
     * @public
     * @memberof AppFormDRUIPart
     */
    public drdatasaved($event: any) {
        this.$emit("drdatasaved", $event);
        console.log(this.viewname + "关系数据保存完成");
    }

    /**
     * DEMEDITVIEW9 关系数据值变化
     *
     * @public
     * @memberof AppFormDRUIPart
     */
    public drdatachange() {
        console.log("DEMEDITVIEW9 关系数据值变化");
    }

    /**
     * 视图数据变化
     *
     * @public
     * @memberof AppFormDRUIPart
     */
    public viewdataschange() {
        console.log("视图数据变化");
    }

    /**
     * 视图加载完成
     *
     * @public
     * @memberof AppFormDRUIPart
     */
    public viewload() {
        console.log("视图加载完成");
    }

    /**
     * 定时器实例
     *
     * @type {[any]}
     * @memberof AppFormDRUIPart
     */
    protected timer?: any;

    /**
     * 向关系视图发送事件，采用轮询模式。避免异步视图出现加载慢情况
     *
     * @param {*} action 触发行为
     * @param {*} data 数据
     * @param {*} count 轮询计数
     * @memberof AppFormDRUIPart
     */
    protected partViewEvent(action: string, data: any, count: number = 0): void {
        if (count > 100) {
            return;
        }
        const clearResource: Function = () => {
            if (this.timer !== undefined) {
                clearTimeout(this.timer);
                this.timer = undefined;
            }
        }
        if (count === 0) {
            clearResource();
        }
        if (this.$refs.appFormDruipart) {
            this.formDruipart.next({ action: action, data });
            clearResource();
            return;
        }
        this.timer = setTimeout(() => {
            count++;
            this.partViewEvent(action, data, count);
        }, 30);
    }

    /**
     * Vue声明周期
     *
     * @memberof AppFormDRUIPart
     */
    public activated() {
        this.refreshDRUIPart();
    }
}
</script>

<style lang = "less">
@import "./app-form-druipart.less";
</style>