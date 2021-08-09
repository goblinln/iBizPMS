<template>
    <el-autocomplete
        class="text-value"
        :disabled="disabled || readonly"
        v-model="curvalue"
        size="small"
        :readonly="Object.is('AC_FS', editorType) || Object.is('AC_FS_NOBUTTON', editorType) ? true : false"
        :trigger-on-focus="Object.is('AC_NOBUTTON', editorType) ? false : true"
        :fetch-suggestions="onSearch"
        :sort="sort"
        @select="onACSelect"
        @input="onInput"
        @blur="onBlur"
        style="width: 100%"
    >
        <template v-slot:suffix>
            <i v-if="curvalue && !disabled && !(Object.is('AC_NOBUTTON', editorType) || Object.is('AC_FS_NOBUTTON', editorType))" class="el-icon-circle-close" @click="onClear"></i>
            <i v-if="!(Object.is('AC_NOBUTTON', editorType) || Object.is('AC_FS_NOBUTTON', editorType))" class="el-icon-arrow-down"></i>
        </template>
        <template slot-scope="{ item }">
            <span v-if="!dataItems">{{ item[deMajorField] }}</span>
            <span v-if="dataItems">{{ item.text }}</span>
        </template>
    </el-autocomplete>
</template>

<script lang='ts'>
import { Component, Vue, Prop, Model, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';

@Component({})
export default class AppAutocomplete extends Vue {
    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppAutocomplete
     */
    @Prop() public data: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppAutocomplete
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
     * AC参数
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    @Prop({ default: () => {} }) public acParams?: any;

    /**
     * 表单服务
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    @Prop() public service?: any;

    /**
     * 应用实体主信息属性名称
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop({ default: 'srfmajortext' }) public deMajorField!: string;

    /**
     * 应用实体主键属性名称
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop({ default: 'srfkey' }) public deKeyField!: string;

    /**
     * 是否启用
     *
     * @type {boolean}
     * @memberof AppAutocomplete
     */
    @Prop() public disabled?: boolean;

    /**
     * 属性项名称
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop() public name!: string;

    /**
     * 局部上下文导航参数
     *
     * @type {any}
     * @memberof AppAutocomplete
     */
    @Prop() public localContext!: any;

    /**
     * 局部导航参数
     *
     * @type {any}
     * @memberof AppAutocomplete
     */
    @Prop() public localParam!: any;

    /**
     * 排序
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop() public sort?: string;

    /**
     * 数据项
     *
     * @type {Array<any>}
     * @memberof AppAutocomplete
     */
    @Prop() public dataItems?: Array<any>;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppAutocomplete
     */
    @Model('change') public value?: any;

    /**
     * 当前值
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    public curvalue: string = '';

    /**
     * 编辑器类型
     * 
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop() public editorType!: string; 

    /**
     * 远程请求url 地址
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop() public url?: string;

    /**
     * 只读模式
     * 
     * @type {boolean}
     */
    @Prop({default: false}) public readonly?: boolean;

    /**
     * 数组
     *
     * @type {any[]}
     * @memberof AppAutocomplete
     */
    public items: any[] = [];

    /**
     * 输入状态
     *
     * @type {boolean}
     * @memberof AppAutocomplete
     */
    public inputState: boolean = false;

    /**
     * 值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppAutocomplete
     */
    @Watch('value', { immediate: true })
    public onValueChange(newVal: any, oldVal: any) {
        this.curvalue = newVal;
    }

    /**
     * 执行搜索数据
     * @param query
     * @param callback
     * @memberof AppAutocomplete
     */
    public onSearch(query: any, callback: any): void {
        // 公共参数处理
        let data: any = {};
        const bcancel: boolean = this.handlePublicParams(data);
        if (!bcancel) {
            return;
        }
        // 参数处理
        let _context = data.context;
        let _param = data.param;
        // 处理搜索参数
        query = !query ? '' : query;
        if (!this.inputState && Object.is(query, this.value)) {
            query = '';
        }
        this.inputState = false;
        if (this.sort && !Object.is(this.sort, '')) {
            Object.assign(_param, { sort: this.sort });
        }
        Object.assign(_param, { query: query });
        // 错误信息国际化
        let error: string = this.$t('components.appautocomplete.error') as any;
        let miss: string = this.$t('components.appautocomplete.miss') as any;
        let requestException: string = this.$t('components.appautocomplete.requestexception') as any;

        if (!this.service) {
            this.$throw(miss + 'service', 'onSearch');
        } else if (!this.acParams.serviceName) {
            this.$throw(miss + 'serviceName', 'onSearch');
        } else if (!this.acParams.interfaceName) {
            this.$throw(miss + 'interfaceName', 'onSearch');
        } else {
            this.service
                .getItems(this.acParams.serviceName, this.acParams.interfaceName, _context, _param)
                .then((response: any) => {
                    if (!response) {
                        this.$throw(requestException, 'onSearch');
                    } else {
                        this.items = this.handleDataItems([...response]);
                    }
                    if (callback) {
                        callback(this.items);
                    }
                })
                .catch((error: any) => {
                    if (callback) {
                        callback([]);
                    }
                });
        }
    }

    /**
     * 处理数据项转化
     *
     * @memberof AppAutocomplete
     */
    public handleDataItems(opts: any) {
        if (this.dataItems && this.dataItems.length > 0) {
            if (opts && opts.length > 0) {
                for(let element  of opts){
                    for (let item of this.dataItems as Array<any>) {
                        if (!item.customCode) {
                            if (item.getPSAppDEField()?.codeName) {
                                element[item.name] = element[item.getPSAppDEField().codeName.toLowerCase()];
                            }
                        }
                    }
                    // 先计算非脚本的数据项，防止数据有误
                    for (let item of this.dataItems as Array<any>) {
                        if (item.customCode) {
                            let data = element;
                            element[item.name] = eval(`${item.scriptCode}`);
                        }
                    }
                }
            }
        }
        return opts;
    }

    /**
     * 选中数据回调
     * @memberof AppAutocomplete
     */
    public onACSelect(item: any): void {
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: item[this.deMajorField] });
        }
    }

    /**
     * 输入过程中
     *
     * @memberof AppAutocomplete
     */
    public onInput($event: any) {
        if (Object.is($event, this.value)) {
            this.inputState = true;
        }
    }

    /**
     * 失去焦点事件
     * @param e
     */
    public onBlur(e: any): void {
        let val: string = e.target.value;
        if (!Object.is(val, this.value)) {
            this.onACSelect({ [this.deMajorField]: val, [this.deKeyField]: '' });
        }
        this.$forceUpdate();
    }

    /**
     * 清除
     */
    public onClear($event: any): void {
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: '' });
        }
        this.$forceUpdate();
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppAutocomplete
     */
    public handlePublicParams(arg: any): boolean {
        if (!this.data) {
            this.$throw(this.$t('components.AppAutocomplete.formdataException') as any, 'handlePublicParams');
            return false;
        }
        // 合并表单参数
        arg.param = this.viewparams ? Util.deepCopy(this.viewparams) : {};
        arg.context = this.context ? Util.deepCopy(this.context) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length > 0) {
            let _context = Util.computedNavData(this.data, arg.context, arg.param, this.localContext);
            Object.assign(arg.context, _context);
        }
        if (this.localParam && Object.keys(this.localParam).length > 0) {
            let _param = Util.computedNavData(this.data, arg.param, arg.param, this.localParam);
            Object.assign(arg.param, _param);
        }
        return true;
    }
}
</script>

<style lang='less'>
@import './app-autocomplete.less';
</style>