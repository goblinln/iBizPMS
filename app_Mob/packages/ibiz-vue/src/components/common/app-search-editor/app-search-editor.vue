<template>
  <div :style="{ height: setHeight }" class="app-search-editor">
    <div class="app-search-editor-item" :class="{'app-search-editor-item-active':selectValue.value == item.value}" @click="itemClick(item)" v-for="item in options " :key="item.id">{{item.text}}</div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { CodeListServiceBase } from "ibiz-core";
@Component({})
export default class AppSearchEditor extends Vue {
    /**
      * 代码表服务对象
      *
      * @type {CodeListService}
      * @memberof AppSearchEditor
      */
    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
      * 激活项
      *
      * @type {CodeListService}
      * @memberof AppSearchEditor
      */
    public activeItem: any = {};

    /**
      * 获取选中值
      *
      * @type {CodeListService}
      * @memberof AppSearchEditor
      */
    get selectValue() {
        return this.activeItem;
    }

    /**
      * 点击行为
      *
      * @type {CodeListService}
      * @memberof AppSearchEditor
      */
    public itemClick(item: any) {
        if (this.activeItem.value == item.value) {
            this.activeItem = {};
            this.change({value:null});
            return
        }
        this.activeItem = item;
        this.change(item);
    }

    /**
     * 传入值
     *
     * @type {string}
     * @memberof AppSearchEditor
     */
    @Prop() public value?: any;


    /**
     * 当前选中值
     * @memberof AppSearchEditor
     */
    get curValue() {
        if (this.options.length > 0 && this.value !== null && this.value !== "") {
            let isIncluded = this.options.some((option: any) => { return option.value === this.value })
            if (isIncluded) {
                return this.value;
            }
        }
        return "";
    }

    /**
     * 下拉数据数组
     *
     * @type {any[]}
     * @memberof AppSearchEditor
     */
    public options: any[] = [];

    /**
     * 是否禁用
     *
     * @type {string}
     * @memberof AppSearchEditor
     */
    @Prop() public disabled?: string;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppSearchEditor
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
     * @memberof AppSearchEditor
     */
    @Prop() public data?: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    @Prop({ default: {} }) protected context?: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    @Prop({ default: ()=>{} }) protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    @Prop({ default: ()=>{} }) protected navigateContext?: any;

    /**
     * 是否缓存
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    @Prop({ default: true }) protected isCache?: boolean;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    @Prop() public viewparams!: any;

    /**
     * 是否被缓存
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    public isCached: boolean = false;

    /**
     * 监听表单数据
     *
     * @param {*} newVal
     * @param {*} val
     * @memberof AppSearchEditor
     */
    @Watch('value')
    onDataChange(newVal: any, oldVal: any) {
        if (!newVal) {
            this.activeItem = {};
        }
        if (newVal) {
            this.load();
            this.$store.commit('setSelectStatus', true);
        }
    }

    /**
     * change事件
     *
     * @memberof AppSearchEditor
     */
    public change(value: any) {
        this.$store.commit('setSelectStatus', true);
        let devalue: any = value.value;
        if (devalue !== '') {
            for (let key in this.options) {
                if (this.options[key].isValueNumber) {
                    devalue = +devalue;
                }
            }
            if (Object.is(this.codeListType, 'DYNAMIC')) {
                for (let key in this.options) {
                    if (typeof this.options[key].id == 'number') {
                        devalue = +devalue;
                    }
                }
            }
        }
        this.$emit("change", devalue);
    }

    /**
     * 取消选择
     *
     * @type {*}
     * @memberof AppSearchEditor
     */
    public cancel() {
        this.$store.commit('setSelectStatus', true);
    }

    /**
     * 加载
     *
     * @memberof AppSearchEditor
     */
    public async load(): Promise<any> {
        this.$store.commit('setSelectStatus', false);
        if (Object.is(this.codeListType, "STATIC")) {
            return;
        }

        // 处理导航参数、上下文参数
        let param: any = {};
        const bcancel: boolean = this.handleOtherParam(param);
        if (!bcancel) {
            return
        }
        let response: any = await this.codeListService.getItems(this.tag, param.context, param.param);
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
     * 
     * @memberof AppSearchEditor
     */
    public clear() {
        this.$emit("change", "");
    }

    /**
     * 处理额外参数
     * 
     * @memberof AppSearchEditor
     */
    public handleOtherParam(arg: any) {
        if (!this.data) {
            return false;
        }
        // 附加参数处理
        const { context, param } = this.$viewTool.formatNavigateParam(this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data);
        arg.context = context;
        arg.param = param;
        return true;
    }

    /**
     * setHeight
     */
    get setHeight() {
       return this.options.length>3?"105px":"aotu"
    }

    /**
     * 生命周期
     * 
     * @memberof AppSearchEditor
     */
    public mounted() {
        if (Object.is(this.codeListType, "STATIC")) {
            this.options = this.$store.getters.getCodeListItems(this.tag);
        } else {
            if (this.curValue) {
                this.load();
                this.$store.commit('setSelectStatus', true);
            }
        }
    }
}
</script>

<style lang='less'>
@import './app-search-editor.less';
</style>