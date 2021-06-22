<template>
  <div class="app-mobile-actionsheet">
      <div class="cancel-icon" v-if="curValue || curValue === 0"><app-mob-icon name="close-circle-outline" @onClick="clear"></app-mob-icon></div>
      <div v-if="curValue== null || curValue==''" class="select-icon" @click="showSheet"></div>
      <!-- <ion-input :value="action" readonly @ionFocus="showSheet"></ion-input> -->
      <div @click="showSheet" class="text">{{action}}</div>
      <van-action-sheet get-container="#app" v-model="show" :actions="actions" @open="load" @select="change" :cancel-text="$t('app.button.cancel')" @cancel="cancel" close-on-click-action/>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { CodeListServiceBase, LogUtil } from "ibiz-core";

@Component({
    components: {},
})
export default class AppMobActionsheet extends Vue {

    /**
     * 动作面板选中数据
     *
     * @type {CodeListService}
     * @memberof AppMobActionsheet
     */
    public action:any = '';
  
    /**
     * 动作面板所需数据
     *
     * @type {CodeListService}
     * @memberof AppMobActionsheet
     */
    public actions:Array<any> = [];

    /**
     * 动作面板状态
     *
     * @type {CodeListService}
     * @memberof AppMobActionsheet
     */
    public show:boolean = false;

    /**
     * 显示动作面板
     *
     * @type {CodeListService}
     * @memberof AppMobActionsheet
     */
    public showSheet(){
      this.show = true;
    }

    /**
     * options->actions处理数据
     *
     * @type {CodeListService}
     * @memberof AppMobActionsheet
     */
    public handleActions(){
      this.options.forEach((option:any,index:number)=>{
        this.actions[index] = {};
        this.actions[index].name = option.text;
        this.actions[index].value = option.value;
      })
    }

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppMobActionsheet
     */
    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
     * 传入值
     *
     * @type {string}
     * @memberof AppMobActionsheet
     */
    @Prop() public value?: any;

    /**
     * 当前选中值
     * @memberof AppMobActionsheet
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
     * @memberof AppMobActionsheet
     */
    public change(action: any) {
        this.action = action.name;
        this.$store.commit('setSelectStatus',true); 
        let devalue:any = action.value;
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
     * @memberof AppMobActionsheet
     */
    public options: any[] = [];

    /**
     * 是否禁用
     *
     * @type {string}
     * @memberof AppMobActionsheet
     */
    @Prop() public disabled?: string;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppMobActionsheet
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
     * @memberof AppMobActionsheet
     */
    @Prop() public data?: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    @Prop({ default: {} }) protected context?: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    @Prop({ default: {} }) protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    @Prop({ default: {} }) protected navigateContext?: any;

    /**
     * 是否缓存
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    @Prop({ default: true }) protected isCache?: boolean;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    @Prop() public viewparams!: any;

    /**
     * 是否被缓存
     *
     * @type {*}
     * @memberof AppMobActionsheet
     */
    public isCached: boolean = false;

    /**
     * 监听表单数据
     *
     * @param {*} newVal
     * @param {*} val
     * @memberof AppMobActionsheet
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
            this.codeListService.getDataItems({ tag: this.tag, type: 'STATIC', data: null, context:this.context, viewparam:null }).then((codelistItems: Array<any>) => {
                this.options = codelistItems;
            }).catch((error: any) => {
                LogUtil.log(`----${this.tag}----${this.$t('app.commonwords.codeNotExist')}`);
            })
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
     * @memberof AppMobActionsheet
     */
    public cancel(){
      this.$store.commit('setSelectStatus',true);
    }

    /**
     * 加载
     *
     * @returns {Promise<any>}
     * @memberof AppMobActionsheet
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
        this.handleActions();
    }

    /**
     * 清空值
     * @memberof AppMobActionsheet
     */
    public clear() {
        this.$emit("change", "");
        this.action = '';
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
@import './app-mob-actionsheet.less';
</style>