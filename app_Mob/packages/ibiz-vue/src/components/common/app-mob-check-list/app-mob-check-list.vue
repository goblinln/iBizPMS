<template>
  <div class="app-mobile-check-list">
    <div class="cancel-icon" v-if="curValue"><app-mob-icon name="close-circle-outline" @onClick="clear"></app-mob-icon></div>
    <div v-if="curValue== null || curValue==''" class="ion-select-icon"></div>
    <ion-select :disabled="disabled"  ref="checkList" @ionChange="change" multiple="true" @click="load" :ok-text="$t('app.button.confirm')" :cancel-text="$t('app.button.cancel')" @ionCancel="cancel">
      <ion-select-option v-for="option of options" :key="option.value" :value="option.value">{{option.text}}
      </ion-select-option>
    </ion-select>
  </div>

</template>

<script lang="ts">
  import {
    Vue,
    Component,
    Prop,
    Provide,
    Emit,
    Watch
  } from "vue-property-decorator";
import { CodeListService } from "ibiz-service";

  @Component({
    components: {}
  })
  export default class AppCheckList extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppCheckList
     */

    public codeListService: CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 是否禁用
     *
     * @type {string}
     * @memberof AppSelect
     */
    @Prop() public disabled?: string;

    /**
     * 传入值
     *
     * @type {string}
     * @memberof AppCheckList
     */
    @Prop() public value!: string;

    /**
     * 当前选中值
     * @memberof AppCheckList
     */
    public curValue:any = this.value;

    @Watch('value')
    valueChange(newValue: string, oldValue: string) {
      if (newValue) {
        this.load();
        this.$store.commit('setSelectStatus',true);
      }
    }

    /**
     * 下拉数据数组
     *
     * @type {any[]}
     * @memberof AppCheckList
     */
    public options: any[] = [];

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppCheckList
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppCheckList
     */
    @Prop() public codeListType!: string;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppCheckList
     */
    @Prop({ default: ()=>{} }) protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppCheckList
     */
    @Prop({ default: ()=>{} }) protected navigateContext?: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppCheckList
     */
    @Prop({ default: {} }) protected context?: any;

    /**
     * 是否缓存
     *
     * @type {*}
     * @memberof AppCheckList
     */
    @Prop({ default: true }) protected isCache?: boolean;

    /**
     * 是否被缓存
     *
     * @type {*}
     * @memberof AppSelect
     */
    public isCached: boolean = false;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof AppCheckList
     */
    @Prop() public data?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppCheckList
     */
    @Prop() public viewparams!: any;

    /**
     * 生命周期
     *
     * @type {*}
     * @memberof AppCheckList
     */
    public mounted() {
      setTimeout(() => {    
        this.load();
        this.$store.commit('setSelectStatus',true);
      }, 1000);
    }

    /**
     * 加载
     *
     * @returns {Promise<any>}
     * @memberof AppSelect
     */
    public async load(): Promise<any> {
      this.$store.commit('setSelectStatus',false);
      const _this: any= this;
      const back:Function =(response:any)=>{
            _this.options = response
            if (_this.isCache) {
                _this.isCached = true;
            }
            if (_this.value) {
              const select:any = _this.$refs.checkList;
              if(_this.value && this.$util.isFunction(_this.value.split)){
                let arr = (_this.value as string).split(',')
                select.value = arr;
              }else{
                select.value = (_this.value as string);
              }
            }
            _this.$forceUpdate();
      }
      if (this.tag && this.codeListType) {
        if (Object.is(this.codeListType, "STATIC")) {
          let response: any = await this.codeListService.getStaticItems(this.tag);
          if (response) {
            back(response);
          }
          return;
        }
        // 处理导航参数、上下文参数
        let param: any = {};
        const bcancel: boolean =this.handleOtherParam(param);
        if (!bcancel) {
            return
        }
        let response: any = await this.codeListService.getItems(this.tag,  param.context, param.param);
        if (response) {
            back(response);
        } else {
            this.options = [];
        }
      }
    }

    /**
     * 值改变
     * 
     * @memberof AppCheckList
     */
    public change(value: any) {
      this.$store.commit('setSelectStatus',true);
      this.curValue = value.detail.value.toString();
      this.$emit('change', this.curValue);
    }

    /**
     * 取消选择
     *
     * @type {*}
     * @memberof AppCheckList
     */
    public cancel(){
      this.$store.commit('setSelectStatus',true);
    }

    /**
     * 处理额外参数
     */
    public handleOtherParam(arg:any) {
        if (!this.data) {
            return false;
        }
        // 导航参数处理
        const {context, param} = this.$viewTool.formatNavigateParam( this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data );
        arg.context =  context;
        arg.param = param;
        return true;
    }

    /**
     * 清空值
     * @memberof @memberof AppCheckList
     */
    public clear(){
        this.curValue = null;
        const select:any = this.$refs.checkList;
        select.value = '';
        this.$emit('change','')
    }
  }
</script>
<style lang="less">
  @import "./app-mob-check-list.less";
</style>