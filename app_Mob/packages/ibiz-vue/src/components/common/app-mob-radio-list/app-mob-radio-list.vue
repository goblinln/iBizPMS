<template>
    <van-radio-group class="app-mobile-radio-list" :disabled="disabled" v-model="curValue" direction="horizontal">
        <van-radio v-for="(item,index) in options" :key="index" :name="item.value">{{item.text}}</van-radio>
    </van-radio-group>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { CodeListServiceBase, LogUtil } from "ibiz-core";

@Component({
    components: {}
})
export default class AppMobRadio extends Vue {

    /**
     * 禁用
     *
     * @type {string}
     * @memberof AppStepper
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppMobRadio
     */
    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppMobRadio
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppMobRadio
     */
    @Prop() public codeListType!: string;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    @Prop({ default: {} }) protected context?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppMobRadio
     */
    @Prop() public viewparams!: any;    

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppMobRadio
     */
    @Prop() protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppMobRadio
     */
    @Prop() protected navigateContext?: any;    

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof AppMobRadio
     */
    @Prop() public data?: any;    

    /**
     * 代码表列表项
     *
     * @type {Array<any>}
     * @memberof AppMobRadio
     */
    public options?: Array<any> = [];

    /**
     * 输入值
     *
     * @type {any}
     * @memberof AppMobRadio
     */
    @Prop() public value?: any;

    /**
     * 代码表
     *
     * @type {string}
     * @memberof DropDownList
     */    
    @Prop() public codeList?: any;

    /**
     * 输入值变化后的值
     *
     * @type {any}
     * @memberof AppMobRadio
     */
    get curValue() {
        return this.value;
    }

    set curValue(item:any){
      this.$emit("change", item);
    }

    /**
     *  vue 生命周期
     *
     * @returns
     * @memberof AppMobRadio
     */
    public created() {
        if (!this.tag || !this.codeListType) {
            return;
        }
        this.loadItems();
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppMobActionsheet
     */
    public handlePublicParams(arg: any) {
        // 合并表单参数
        arg.param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.navigateContext && Object.keys(this.navigateContext).length >0) {
            let _context = this.$util.computedNavData(this.data,arg.context,arg.param,this.navigateContext);
            Object.assign(arg.context,_context);
        }
        if (this.navigateParam && Object.keys(this.navigateParam).length >0) {
            let _param = this.$util.computedNavData(this.data,arg.param,arg.param,this.navigateParam);
            Object.assign(arg.param,_param);
        }
    }

    /**
     * 加载 数据
     *
     * @private
     * @returns {Promise<any>}
     * @memberof AppMobRadio
     */
    private async loadItems(): Promise<any> {
        if (Object.is(this.codeListType, 'DYNAMIC')) {
            let data: any = {};
            this.handlePublicParams(data);
            // 参数处理
            let context = data.context;
            let viewparam = data.param;          
            const response: any = await this.codeListService.getDataItems({ tag: this.tag, type: this.codeListType,data: this.codeList,context:context,viewparam:viewparam })
            if (response) {
                this.options = response;
            } else {
                this.options = [];
            }
        } else {
            this.codeListService.getDataItems({ tag: this.tag, type: 'STATIC', data: this.codeList, context:this.context, viewparam:this.viewparams }).then((codelistItems: Array<any>) => {
                this.options = codelistItems;
            }).catch((error: any) => {
                LogUtil.log(`----${this.tag}----${this.$t('app.commonwords.codeNotExist')}`);
            }) 
        }
    }
}
</script>

<style lang="less">
    .van-radio-group--horizontal{
        justify-content: flex-end;
    }
    .van-radio{
        margin:5px;
    }
</style>