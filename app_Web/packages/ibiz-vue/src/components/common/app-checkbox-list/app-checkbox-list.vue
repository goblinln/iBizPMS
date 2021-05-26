<template>
    <checkbox-group class="app-checkbox-list" v-model="selectArray">
        <checkbox v-for="(item,index) in items" :key="index" :label="item.value" :disabled="isDisabled || item.disabled">
            <span>{{item.text}}</span>
        </checkbox>
    </checkbox-group >
</template>

<script lang="ts">
import { Component, Vue, Prop, Model, Watch } from 'vue-property-decorator';
import { CodeListService } from "ibiz-service";
import { LogUtil, Util } from 'ibiz-core';

@Component({
})
export default class AppCheckBox extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppCheckBox
     */  
    public codeListService:CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppCheckBox
     */
    @Prop() public tag?: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppCheckBox
     */
    @Prop() public codelistType?: string;

    /**
     * 代码表
     *
     * @type {string}
     * @memberof AppCheckBox
     */    
    @Prop() public codeList!: any;

    /**
     * 代码表值分隔符
     *
     * @type {string}
     * @memberof AppCheckBox
     */
    @Prop({default:','}) public valueSeparator?: string;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppCheckBox
     */
    @Prop() disabled?: boolean;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof AppCheckBox
     */
    @Prop() public data?: any;

    /**
     * 局部上下文导航参数
     * 
     * @type {any}
     * @memberof AppCheckBox
     */
    @Prop() public localContext!:any;

    /**
     * 局部导航参数
     * 
     * @type {any}
     * @memberof AppCheckBox
     */
    @Prop() public localParam!:any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppCheckBox
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppCheckBox
     */
    @Prop() public viewparams!: any;

    /**
     * 属性类型
     *
     * @type {'string' | 'number'}
     * @memberof AppCheckBox
     */
    @Prop({ default: 'string' })
    public valueType!: 'string' | 'number';

    /**
     * 获取启用禁用状态
     *
     * @readonly
     * @memberof AppCheckBox
     */
    get isDisabled() {
        if (this.disabled) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 属性名称
     *
     * @type {*}
     * @memberof AppCheckBox
     */
    @Prop() name?: any;

    /**
     * 模式（数字或者字符串）
     *
     * @type {*}
     * @memberof AppCheckBox
     */
    @Prop() mode: any;

    /**
     * 当前模式
     *
     * @readonly
     * @memberof AppCheckBox
     */
    get currentmode() {
        if (this.mode) {
            return this.mode;
        } else {
            return 'str';
        }
    }

    /** 
     * 选中值
     *
     * @type {*}
     * @memberof AppCheckBox
     */
    @Model('change') value?: any;

    /**
     * 选中数组
     *
     * @memberof AppCheckBox
     */
    get selectArray() {
        if (this.value) {
            if (Object.is(this.currentmode, 'num') && this.items) {
                let selectsArray: Array<any> = [];
                let num: number = parseInt(this.value, 10);
                this.items.forEach((item: any) => {
                    if ((num & item.value) == item.value) {
                        selectsArray.push(item.value);
                    }
                });
                return selectsArray;
            } else if (Object.is(this.currentmode, 'str')) {
                if (this.value !== '') {
                    let selects = this.value.split(this.valueSeparator);
                    if(this.codeList.codeItemValueNumber){
                        for(let i = 0, len =selects.length; i < len ;i++ ){
                            selects[i] = Number(selects[i]);
                        }
                    }
                    return selects;
                }
            }
        } else {
            return [];
        }
    }

    /**
     * 设置选中
     *
     * @memberof AppCheckBox
     */
    set selectArray(val: any) {
        let value: null | string | number = null;
        if (Object.is(this.currentmode, 'num')) {
            let temp: number = 0;
            val.forEach((item: any) => {
                temp = temp | parseInt(item, 10);
            });
            value = temp;
        } else if (Object.is(this.currentmode, 'str')) {
            let _datas: string[] = [];
            this.items.forEach((item: any) => {
                const index = val.findIndex((_key: any) => Object.is(item.value, _key));
                if (index === -1) {
                    return;
                }
                _datas.push(item.value);
            });
            value = _datas.join(this.valueSeparator);
        }
        this.$emit('change', value);
    }

    /**
     * 代码表数组
     *
     * @type {any[]}
     * @memberof AppCheckBox
     */
    public items: any[] = [];

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppCheckBox
     */
    public handlePublicParams(arg: any) {
        // 合并表单参数
        arg.param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length >0) {
            let _context = this.$util.computedNavData(this.data,arg.context,arg.param,this.localContext);
            Object.assign(arg.context,_context);
        }
        if (this.localParam && Object.keys(this.localParam).length >0) {
            let _param = this.$util.computedNavData(this.data,arg.param,arg.param,this.localParam);
            Object.assign(arg.param,_param);
        }
    }

    /**
     * 加载数据
     *
     * @memberof AppCheckBox
     */
    public loadData(){
        if(this.tag && this.codelistType) {
            let data: any = {};
            this.handlePublicParams(data);
            // 参数处理
            let context = data.context;
            let viewparam = data.param;
            this.codeListService.getDataItems({ tag: this.tag, type: this.codelistType,data: this.codeList,context:context,viewparam:viewparam }).then((codelistItems: Array<any>) => {
                this.formatCodeList(codelistItems);
            }).catch((error: any) => {
                LogUtil.log(`----${this.tag}----${this.$t('components.appCheckBox.notExist')}`);
            })
        }
    }

    /**
     * 代码表类型和属性匹配
     * 
     * @param {*} items
     * @memberof AppCheckBox
     */
    public formatCodeList(items: Array<any>){
        let matching: boolean = false;
        this.items = [];
        try{
            items.forEach((item: any)=>{
                const type = this.$util.typeOf(item.value);
                if(type != this.valueType){
                    matching = true;
                    if(type === 'number'){
                        item.value = item.value.toString();
                    }else{
                        if(type == "null") {
                            this.valueType == "number" ? item.value = 0 : item.value = '';
                        }else if(item.value.indexOf('.') == -1){
                            item.value = parseInt(item.value);
                        }else{
                            item.value = parseFloat(item.value);
                        }
                    }
                }
                this.items.push(item);
            });
            if(matching){
                LogUtil.warn(`${ this.tag }${this.$t('components.appCheckBox.warn')}`);
            }
            
        }catch(error){
            LogUtil.warn(this.$t('components.appCheckBox.warn'));
        }
    }

    /**
     * vue  生命周期
     *
     * @memberof AppCheckBox
     */
    public created() {
        this.loadData();
    }

    /**
     * 监听表单数据变化
     * 
     * @memberof AppCheckBox
     */
    @Watch('data',{immediate:true,deep:true})
    onDataChange(newVal: any, oldVal: any) {
      if(newVal){
          this.loadData();
      }
    }
}
</script>

<style lang='less'>
@import './app-checkbox-list.less';
</style>