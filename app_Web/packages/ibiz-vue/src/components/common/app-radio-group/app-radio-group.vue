<template>
    <radio-group class="app-radio-group" v-model="value">
        <radio
            v-for="(_item, index) in items"
            :key="index"
            :label="_item.value"
            :disabled="isDisabled || _item.disabled || readonly"
        >
            <span>{{ _item.text }}</span>
        </radio>
    </radio-group>
</template>
<script lang="ts">
import { Component, Vue, Prop, Model, Watch } from 'vue-property-decorator';
import { CodeListService } from 'ibiz-service';
import { LogUtil, Util } from 'ibiz-core';
import { Subject } from 'rxjs';

@Component({})
export default class AppRadioGroup extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppRadioGroup
     */

    public codeListService: CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 双向绑定值
     *
     * @type {*}
     * @memberof AppRadioGroup
     */
    @Model('change') item?: any;

    /**
     * 属性类型
     *
     * @type {'string' | 'number'}
     * @memberof AppRadioGroup
     */
    @Prop({ default: 'string' })
    public valueType!: 'string' | 'number';

    /**
     * 获取值
     *
     * @memberof AppRadioGroup
     */
    get value() {
        if (this.valueType && (this.valueType === 'number')) {
            return this.item;
        } else {
            return `${this.item}`;
        }
    }

    /**
     * 设置值
     *
     * @memberof AppRadioGroup
     */
    set value(val: any) {
        if (typeof val == 'string') {
            if (this.valueType && (this.valueType === 'number')) {
                if (val.indexOf('.') === -1) {
                    this.$emit('change', parseInt(val));
                } else {
                    this.$emit('change', parseFloat(val));
                }
            } else {
                this.$emit('change', val);
            }
        } else if (typeof val == 'number') {
            if (this.valueType && (this.valueType === 'number')) {
                this.$emit('change', val);
            } else {
                this.$emit('change', val.toString());
            }
        }
    }

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppRadioGroup
     */
    @Prop() public tag?: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppRadioGroup
     */
    @Prop() public codelistType?: string;

    /**
     * 代码表
     *
     * @type {*}
     * @memberof AppRadioGroup
     */
    @Prop() public codeList!: any;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof AppRadioGroup
     */
    @Prop() public data?: any;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppRadioGroup
     */
    @Prop() public disabled?: boolean;

	/**
	 * 只读模式
	 * 
	 * @type {boolean}
	 */
	@Prop({default: false}) public readonly?: boolean;

    /**
     * 局部上下文导航参数
     *
     * @type {any}
     * @memberof AppRadioGroup
     */
    @Prop() public localContext!: any;

    /**
     * 局部导航参数
     *
     * @type {any}
     * @memberof AppRadioGroup
     */
    @Prop() public localParam!: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppRadioGroup
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppRadioGroup
     */
    @Prop() public viewparams!: any;

    /**
     * 属性名称
     *
     * @type {string}
     * @memberof AppRadioGroup
     */
    @Prop() name?: string;

    /**
     * 编辑器状态
     *
     * @type {Subject<any>}
     * @memberof AppRadioGroup
     */
    @Prop() public contextState?: Subject<any>;

    /**
     * 编辑器状态事件
     *
     * @type {*}
     * @memberof AppRadioGroup
     */
    public contextStateEvents: any;

    /**
     * 是否禁用
     *
     * @readonly
     * @memberof AppRadioGroup
     */
    get isDisabled() {
        if (this.disabled) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 代码表
     *
     * @type {any[]}
     * @memberof AppRadioGroup
     */
    public items: any[] = [];

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppRadioGroup
     */
    public handlePublicParams(arg: any) {
        // 合并表单参数
        arg.param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length > 0) {
            let _context = this.$util.computedNavData(this.data, arg.context, arg.param, this.localContext);
            Object.assign(arg.context, _context);
        }
        if (this.localParam && Object.keys(this.localParam).length > 0) {
            let _param = this.$util.computedNavData(this.data, arg.param, arg.param, this.localParam);
            Object.assign(arg.param, _param);
        }
    }

    /**
     * vue生命周期 -- created
     *
     * @memberof AppRadioGroup
     */
    public created() {
        this.handleData();
    }

    /**
     * vue生命周期 -- mounted
     *
     * @memberof AppRadioGroup
     */
    public mounted() {
        if (this.contextState) {
            this.contextStateEvents = this.contextState.subscribe(({ type, data }: any) => {
                if (type == 'load') {
                    this.handleData();
                }
            })
        }
    }

    public handleData() {
        if (this.tag && this.codelistType) {
            // 公共参数处理
            let data: any = {};
            this.handlePublicParams(data);
            // 参数处理
            let context = data.context;
            let viewparam = data.param;
            this.codeListService
                .getDataItems({
                    tag: this.tag,
                    type: this.codelistType,
                    data: this.codeList,
                    context: context,
                    viewparam: viewparam,
                })
                .then((codelistItems: Array<any>) => {
                    this.formatCodeList(codelistItems);
                })
                .catch((error: any) => {
                    LogUtil.log(`----${this.tag}----${this.$t('app.commonwords.codenotexist')}`);
                });
        }
    }

    /**
     * 代码表类型和属性匹配
     * 
     * @param {*} items
     * @memberof AppRadioGroup
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
                LogUtil.warn(`${ this.tag }${this.$t('app.commonwords.codelistwarn')}`);
            }
            
        }catch(error){
            LogUtil.warn(this.$t('app.commonwords.codelistwarn'));
        }
    }



}
</script>
<style lang="less">
@import './app-radio-group.less';
</style>
