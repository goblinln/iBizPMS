<template>
    <div class="app-mobile-select" data-tap-disabled="true">
        <div class="cancel-icon" v-if="curValue || curValue === 0"><app-mob-icon name="close-circle-outline" @onClick="clear"></app-mob-icon></div>
        <div v-if="curValue== null || curValue==''" class="ion-select-icon"></div>
        <ion-select  :value="curValue" :disabled="disabled ? disabled : false" @ionChange="change" interface="action-sheet" @click="load" :cancel-text="$t('app.button.cancel')" @ionCancel="cancel">
              <template v-if="codeListType == 'DYNAMIC'">
                <ion-select-option  v-for="option of options" :key="option.value" :value="option.value" class="mob-select-text">{{($t('userCustom.'+tag+'.'+option.value)!== ('userCustom.'+tag+'.'+option.value))?$t('userCustom.'+tag+'.'+option.value) : option.text}}</ion-select-option>
              </template>
              <template v-else>
                <ion-select-option  v-for="option of options" :key="option.value" :value="option.value" class="mob-select-text">{{($t('codelist.'+tag+'.'+option.value)!== ('codelist.'+tag+'.'+option.value))?$t('codelist.'+tag+'.'+option.value) : option.text}}</ion-select-option>
              </template>
        </ion-select>
    </div>   
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { CodeListService } from "ibiz-service";

@Component({
    components: {},
})
export default class AppSelect extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppSelect
     */

    public codeListService: CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 传入值
     *
     * @type {string}
     * @memberof AppSelect
     */
    @Prop() public value?: any;

    /**
     * 当前选中值
     * @memberof AppSelect
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
     * change事件
     *
     * @memberof AppSelect
     */
    public change(value: any) {
        this.$store.commit('setSelectStatus',true); 
        let devalue:any = value.detail.value;
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
     * 下拉数据数组
     *
     * @type {any[]}
     * @memberof AppSelect
     */
    public options: any[] = [];

    /**
     * 是否禁用
     *
     * @type {string}
     * @memberof AppSelect
     */
    @Prop() public disabled?: string;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppSelect
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
     * @memberof AppSelect
     */
    @Prop() public data?: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop({ default: {} }) protected context?: any;

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
     * 是否缓存
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop({ default: true }) protected isCache?: boolean;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop() public viewparams!: any;

    /**
     * 是否被缓存
     *
     * @type {*}
     * @memberof AppSelect
     */
    public isCached: boolean = false;

    /**
     * 监听表单数据
     *
     * @param {*} newVal
     * @param {*} val
     * @memberof AppSelect
     */
    @Watch('value')
    onDataChange(newVal: any, oldVal: any) {
        if (newVal) {
            this.load();
            this.$store.commit('setSelectStatus',true);
        }
    }

    /**
     * mounted
     */
    public mounted() {
        if (Object.is(this.codeListType, "STATIC")) {
            this.options = this.$store.getters.getCodeListItems(this.tag);
        } else {
            if (this.curValue) {
                this.load();
                this.$store.commit('setSelectStatus',true);
            }
        }
    }

    /**
     * 取消选择
     *
     * @type {*}
     * @memberof AppSelect
     */
    public cancel(){
      this.$store.commit('setSelectStatus',true);
    }

    /**
     * 加载
     *
     * @returns {Promise<any>}
     * @memberof AppSelect
     */
    public async load(): Promise<any> {
        this.$store.commit('setSelectStatus',false);
        if (Object.is(this.codeListType, "STATIC")) {
            return;
        }
    
        // 处理导航参数、上下文参数
        let param: any = {};
        const bcancel: boolean =this.handleOtherParam(param);
        if(!bcancel){
            return
        }
        let response: any = await this.codeListService.getItems(this.tag,  param.context, param.param);
        if (response) {
            this.options = response
            if (this.isCache) {
                this.isCached = true;
            }
        } else {
            this.options = [];
        }
    }
    /**
     * 清空值
     * @memberof AppSelect
     */
    public clear() {
        this.$emit("change", "");
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

}
</script>

<style lang="less">
@import './app-mob-select.less';
</style>