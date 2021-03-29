<template>
    <div class="icon-codelist">
        <span v-if="ifEmpty">{{ $t('codelist.' + tag + '.empty') }}</span>
        <template v-if="!ifEmpty">
            <template v-for="(item, index) in items">
                <div class="codelist-item" :key="index">
                    <span v-if="index != 0">{{ textSeparator }}</span>
                    <i v-if="item.iconcls" :class="item.iconcls"></i>
                    <img  v-if="item.icon" :src="getIcon(item.icon)" />
                    <div class="codelist-item-icon"  v-if="!item.icon">{{ isUseLangres ? $t(item.text).substring(0,1) : item.text.substring(0,1) }}</div>
                    <span :class="item.class" :style="{ color: item.color }">
                        {{ isUseLangres ? $t(item.text) : item.text }}
                    </span>
               </div>
            </template>
        </template>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model, Watch } from 'vue-property-decorator';
import {CodeListService} from "ibiz-service";
import { Environment } from '@/environments/environment';

@Component({})
export default class IconCodeList extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof IconCodeList
     */
    public codeListService: CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof IconCodeList
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof IconCodeList
     */
    @Prop() public codelistType?: string;

    /**
     * 当前值
     * @type {any}
     * @memberof IconCodeList
     *
     */
    @Prop() public value?: string;

    /**
     * 获取或模式
     * @type {boolean}
     * @memberof IconCodeList
     */
    @Prop({ default: 'STR' }) public renderMode?: string;

    /**
     * 文本分隔符
     * @type {boolean}
     * @memberof IconCodeList
     */
    @Prop({ default: '、' }) public textSeparator?: string;

    /**
     * 值分隔符
     * @type {boolean}
     * @memberof IconCodeList
     */
    @Prop({ default: ',' }) public valueSeparator?: string;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof IconCodeList
     */
    @Prop() public data?: any;

    /**
     * 局部上下文导航参数
     *
     * @type {any}
     * @memberof IconCodeList
     */
    @Prop() public localContext!: any;

    /**
     * 局部导航参数
     *
     * @type {any}
     * @memberof IconCodeList
     */
    @Prop() public localParam!: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof IconCodeList
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof IconCodeList
     */
    @Prop() public viewparams!: any;

    /**
     * 是否为空
     *
     * @memberof IconCodeList
     */
    public ifEmpty: boolean = false;

    /**
     * 显示数据集合
     *
     * @type {any[]}
     * @memberof IconCodeList
     */
    public items: any[] = [];

    /**
     * vue  生命周期
     *
     * @memberof IconCodeList
     */
    public created() {
        this.dataHandle();
    }

    /**
     * 是否使用多语言资源
     * @type {boolean}
     * @memberof IconCodeList
     */
    public isUseLangres: boolean = false;

    /**
     * 数据值变化
     *
     * @param {*} newval
     * @param {*} val
     * @returns
     * @memberof IconCodeList
     */
    @Watch('value')
    public onValueChange(newVal: any, oldVal: any) {
        this.dataHandle();
    }

    /**
     * 监听表单数据变化
     *
     * @memberof IconCodeList
     */
    @Watch('data', { immediate: true, deep: true })
    onDataChange(newVal: any, oldVal: any) {
        if (newVal) {
            this.dataHandle();
        }
    }

    /**
     * 数据处理
     *
     * @memberof IconCodeList
     */
    private dataHandle() {
        this.isUseLangres = false;
        let _this = this;
        // 空值判断
        if (
            Object.is(this.$util.typeOf(this.value), 'undefined') ||
            Object.is(this.$util.typeOf(this.value), 'null') ||
            Object.is(this.value, '')
        ) {
            this.ifEmpty = true;
            return;
        }
        this.ifEmpty = false;
        // 动态代码表处理
        if (Object.is(this.codelistType, 'DYNAMIC')) {
            // 公共参数处理
            let data: any = {};
            this.handlePublicParams(data);
            // 参数处理
            let _context = data.context;
            let _param = data.param;
            this.codeListService
                .getItems(this.tag, _context, _param)
                .then((res: any) => {
                    let items = res;
                    _this.setItems(items, _this);
                })
                .catch((error: any) => {
                    console.log(`----${_this.tag}----${this.$t('app.commonWords.codeNotExist') as string}`);
                });
            // 静态处理
        } else if (Object.is(this.codelistType, 'STATIC')) {
            this.isUseLangres = true;
            let items = this.$store.getters.getCodeListItems(this.tag);
            _this.setItems(items, _this);
        }
    }

    /**
     * 解析代码表和vlaue，设置items
     *
     * @private
     * @param {any[]} items 代码表数据
     * @param {*} value
     * @returns {*}
     * @memberof IconCodeList
     */
    private setItems(items: any[], _this: any) {
        if (items) {
            let result: any = [];
            if (Object.is(_this.renderMode, 'NUM')) {
                _this.isUseLangres = false;
                items.map((_item: any, index: number) => {
                    const nValue = parseInt(_this.value as any, 10);
                    const codevalue = _item.value;
                    if ((parseInt(codevalue, 10) & nValue) > 0) {
                        result.push(_item);
                    }
                });
            } else {
                let values: any[] = Object.is(_this.$util.typeOf(_this.value), 'number')
                    ? [_this.value]
                    : [...(_this.value as any).split(_this.valueSeparator)];
                values.map((value: any, index: number) => {
                    const item = _this.getItem(items, value);
                    if (item) {
                        result.push(item);
                    }
                });
            }
            // 设置items
            if (result.length != 0) {
                _this.items = result;
            } else {
                _this.items = [{ text: _this.value }];
            }
        }
    }

    /**
     * 获取代码项
     *
     * @private
     * @param {any[]} items
     * @param {*} value
     * @returns {*}
     * @memberof IconCodeList
     */
    private getItem(items: any[], value: any): any {
        let result: any = {};
        const arr: Array<any> = items.filter((item) => {
            return item.value == value;
        });
        if (arr.length !== 1) {
            return undefined;
        }
        result = { ...arr[0] };
        if (Object.is(this.codelistType, 'STATIC')) {
            let value = JSON.parse(JSON.stringify(result));
            value.text = 'codelist.' + this.tag + '.' + value.value;
            return value;
        } else {
            return result;
        }
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof IconCodeList
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
     * 获取图片路径
     *
     * @param {*} arg
     * @returns
     * @memberof IconCodeList
     */
    public getIcon(arg:any){
       if(!arg){
           return;
        }
       if(Object.prototype.toString.call(arg)=="[object String]"){
           try {
               let targetData:any = JSON.parse(arg);
               if(Object.prototype.toString.call(targetData) == "[object Array]"){
                    if(targetData && targetData.length >0){
                        let fileId:string = targetData[0] && targetData[0].id;
                        return Environment.BaseUrl + "ibizutil/download/" + fileId;
                    }
                }else if(Object.prototype.toString.call(targetData) === '[object Object]'){
                        let fileId:string = targetData && targetData.id;
                        return Environment.BaseUrl + "ibizutil/download/" + fileId;
                }
           } catch (error) {
               return arg;
           }
       }else if(Object.prototype.toString.call(arg) == "[object Array]"){
           if(arg && arg.length >0){
               let fileId:string = arg[0] && arg[0].id;
               return Environment.BaseUrl + "ibizutil/download/" + fileId;
           }
       }else if(Object.prototype.toString.call(arg) === '[object Object]'){
            let fileId:string = arg && arg.id;
            return Environment.BaseUrl + "ibizutil/download/" + fileId;
       }else{
            return arg;
       }
    }
}
</script>

<style lang="less">
.icon-codelist {
    white-space: nowrap;
    text-overflow: ellipsis;
    word-break: break-all;
    overflow: hidden;
    .codelist-item{
        display: flex;
        align-items: center;
        max-height: 32px;
        > .codelist-item-icon{
            height: 32px;
            width: 32px;
            line-height: 32px;
            margin-right: 6px;
            border-radius: 50%;
            text-align: center;
            background-color: var(--view-background-color-active);
            color: var(--view-font-color-active);
        }
        > img{
            max-height: 32px;
            width: 32px;
            margin-right: 6px;
            border-radius: 50%;
        }
    }
}
</style>
