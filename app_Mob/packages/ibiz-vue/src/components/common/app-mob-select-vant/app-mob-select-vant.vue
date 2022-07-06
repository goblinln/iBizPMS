<template>
    <div class="app-mobile-select app-mob-select-vant" @click="content_click" data-tap-disabled="true">
        <div class="form-value-content select-value-content">{{visibleText}}</div><van-icon class="app-mob-select-vant-icon" name="arrow" />
            <van-action-sheet
                get-container="#app"
                v-model="visible"
                :actions="this.options"
                cancel-text="取消"
                close-on-click-action
                @select="change"
                @cancel="onCancel"
            />
    </div>   
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { CodeListService } from "ibiz-service";
import { LogUtil } from "ibiz-core";

@Component({
    components: {},
})
export default class AppSelectVant extends Vue {

    /**
     * 传入值
     *
     * @type {string}
     * @memberof AppSelectVant
     */
    @Prop() public value?: any;

    /**
     * 是否禁用
     *
     * @type {string}
     * @memberof AppSelectVant
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppSelectVant
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     * STATIC：静态
     * DYNAMIC：动态
     *
     * @type {('STATIC' | 'DYNAMIC')}
     * @memberof Login
     */
    @Prop() public codeListType!: 'STATIC' | 'DYNAMIC';

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof AppSelectVant
     */
    @Prop() public data?: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppSelectVant
     */
    @Prop({ default: {} }) protected context?: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppSelectVant
     */
    @Prop({ default: ()=>{} }) protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppSelectVant
     */
    @Prop({ default: ()=>{} }) protected navigateContext?: any;

    /**
     * 代码表
     *
     * @type {string}
     * @memberof DropDownList
     */    
    @Prop() public codeList?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppSelectVant
     */
    @Prop() public viewparams!: any;

    /**
     * 下拉数据数组
     *
     * @type {any[]}
     * @memberof AppSelectVant
     */
    public options: any[] = [];

    /**
     * 显示状态
     *
     * @type {CodeListService}
     * @memberof AppSelectVant
     */
    public visible:boolean = false;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppSelectVant
     */
    public codeListService: CodeListService = new CodeListService({ $store: this.$store });
    
    /**
     * 参数缓存字符串
     *
     * @memberof AppSelectVant
     */
    public cachParamStr:string = "";

    /**
     * 显示文本
     *
     * @type {CodeListService}
     * @memberof AppSelectVant
     */
    get visibleText(){
        const item =  this.options.find((item:any)=>{return item.value === this.curValue});
        if(item){
            return item.label;
        }else{
            return '';
        }
    }

    /**
     * 当前选中值
     * @memberof AppSelectVant
     */
    get curValue() {
        if (this.options.length > 0 && this.value !== null && this.value !== "") {
            let isIncluded = this.options.some((option:any)=>{return option.value === this.value})
            if (isIncluded) {
                return this.value;
            }
        }
        return "";
    }

    /**
     * 监听表单数据
     *
     * @param {*} newVal
     * @param {*} val
     * @memberof AppSelectVant
     */
    @Watch('data',{deep:true})
    onDataChange(newVal: any, oldVal: any) {
        let param = {};
        this.handleOtherParam(param);
        if (newVal && !Object.is(JSON.stringify(param),this.cachParamStr)) {
            this.load();
        }
    }
    
    /**
     * change事件
     *
     * @memberof AppSelectVant
     */
    public change(data: any) {
        let devalue:any = data.value;
        if (devalue !== '') {
          for(let key in this.options){
            if (this.options[key].isValueNumber) {
              devalue = +devalue;
            }
          }
          if (Object.is(this.codeListType, 'DYNAMIC')) {
            for(let key in this.options){
              if (typeof this.options[key].id == 'number') {
                  devalue = +devalue;
              }
            }
          }
        }
        this.$emit("change", devalue);
    }

    /**
     * mounted
     */
    public mounted() {
        if (Object.is(this.codeListType, "STATIC")) {
            this.codeListService.getDataItems({ tag: this.tag, type: 'STATIC', data: this.codeList, context:this.context, viewparam:null }).then((codelistItems: Array<any>) => {
                this.options = codelistItems;
                this.initOption();
            }).catch((error: any) => {
                LogUtil.log(`----${this.tag}----${this.$t('app.commonwords.codeNotExist')}`);
            })   
        } else {
            this.load();
        }
    }

    /**
     * 加载
     *
     * @returns {Promise<any>}
     * @memberof AppSelectVant
     */
    public async load(): Promise<any> {
        if (Object.is(this.codeListType, "STATIC")) {
            return;
        }
    
        // 处理导航参数、上下文参数
        let param: any = {};
        const bcancel: boolean =this.handleOtherParam(param);
        if(!bcancel){
            return
        }
        this.cachParamStr = JSON.stringify(param);
        let response: any = await this.codeListService.getItems(this.tag,  param.context, param.param);
        if (response) {
            this.options = response;
            this.initOption();
        } else {
            this.options = [];
        }
    }

    /**
     * 处理额外参数
     */
    public handleOtherParam(arg:any) {
        if (!this.data) {
            return false;
        }
        // 附加参数处理
        const {context, param} = this.$viewTool.formatNavigateParam( this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data );
        arg.context = context;
        arg.param = param;
        return true;
    }

    /**
     * 点击事件
     */
    public content_click(){
        if(this.disabled){
            return;
        }
        this.visible = true;
    }

    /**
     * 初始化option
     */
    public initOption(){
        this.options.forEach((item:any)=>{item.name = item.text})
    }

    /**
     * 取消
     */
    public onCancel(){
        this.visible = false;
    }
}
</script>

<style lang="less">
@import './app-mob-select-vant.less';
</style>