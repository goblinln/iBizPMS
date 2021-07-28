<template>
    <div class="app-list-box">
        <template v-if="multiple" class="app-list-box-multiple">
            <checkbox-group v-model="selectArray" @on-change="selectChange">
                <checkbox
                    v-for="(item, index) in items"
                    :key="index"
                    :label="Object.is('LISTBOX', editorType) ? item.value : item[deKeyField]"
                    :disabled="disabled || item.disabled"
                >
                    <span>{{ Object.is('LISTBOX', editorType) ? item.text : item[deMajorField] }}</span>
                </checkbox>
            </checkbox-group>
        </template>
        <template v-else class="app-list-box-radio">
            <el-radio-group v-model="selectArray[0]">
                <el-radio
                    v-for="(item, index) in items"
                    :key="index"
                    :label="Object.is('LISTBOX', editorType) ? item.value : item[deKeyField]"
                    :disabled="disabled || item.disabled"
                    @change="selectChange(Object.is('LISTBOX', editorType) ? item.value : item[deKeyField])"
                >
                    {{ Object.is('LISTBOX', editorType) ? item.text : item[deMajorField] }}
                </el-radio>
            </el-radio-group>
        </template>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { LogUtil, Util } from 'ibiz-core';
import { Subject, Subscription } from 'rxjs';
import { CodeListService } from 'ibiz-service';

@Component({})
export default class AppListBox extends Vue {

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppListBox
     */
    public codeListService: CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppListBox
     */
    @Prop()
    public tag?: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppListBox
     */
    @Prop()
    public codelistType?: string;

    /**
     * 代码表对象
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop()
    public codeList?: any;

    /**
     * 表单项名称
     *
     * @type {string}
     * @memberof AppListBox
     */
    @Prop()
    public name!: string;

    /**
     * 上下文
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop()
    public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop()
    public viewparams!: any;

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop()
    public data!: any;

    /**
     * 是否启用
     *
     * @type {boolean}
     * @memberof AppListBox
     */
    @Prop()
    public disabled?: boolean;

    /**
     * 是否多选
     *
     * @type {boolean}
     * @memberof AppListBox
     */
    @Prop({ default: false })
    public multiple?: boolean;

    /**
     * 值分隔符
     *
     * @type {string}
     * @memberof AppListBox
     */
    @Prop({ default: ',' })
    public valueSeparator!: string;

    /**
     * 属性类型
     *
     * @type {'string' | 'number'}
     * @memberof AppListBox
     */
    @Prop({ default: 'string' })
    public valueType!: 'string' | 'number';

    /**
     * 编辑器类型
     *
     * @type {string}
     * @memberof AppAutocomplete
     */
    @Prop()
    public editorType!: string;

    /**
     * 局部上下文导航参数
     *
     * @type {any}
     * @memberof AppListBox
     */
    @Prop()
    public localContext!: any;

    /**
     * 局部导航参数
     *
     * @type {any}
     * @memberof AppListBox
     */
    @Prop()
    public localParam!: any;

    /**
     * 模式（数字或者字符串）
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop()
    public mode?: any;

    /**
     * AC参数
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop({default: () => {}})
    public acParams?: any;

    /**
     * 表单状态对象
     *
     * @type {Subject<any>}
     * @memberof AppListBox
     */
    @Prop()
    public formState!: Subject<any>;

    /**
     * 表单服务
     *
     * @type {*}
     * @memberof AppListBox
     */
    @Prop()
    public service?: any;

    /**
     * 应用实体主信息属性名称
     *
     * @type {string}
     * @memberof AppListBox
     */
    @Prop({default: 'srfmajortext'})
    public deMajorField!: string;

    /**
     * 应用实体主键属性名称
     *
     * @type {string}
     * @memberof AppListBox
     */
    @Prop({default: 'srfkey'})
    public deKeyField!: string;

    /**
     * 订阅对象
     *
     * @protected
     * @type {(Subscription | undefined)}
     * @memberof AppListBox
     */
    protected formStateEvent: Subscription | undefined;

    /**
     * 数据集合
     *
     * @type {*}
     * @memberof AppListBox
     */
    public items: any[] = [];

    /**
     * 选中数据
     * 
     * @type {*}
     * @memberof AppListBox
     */
    public selectArray: any[] = [];

    /**
     * 值变化
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Watch('data',{
        immediate:true,
        deep:true
    })
    public onValueChange(newVal: any, oldVal: any) {
        if (newVal && (this.data[this.name] || this.data[this.name] == 0 || this.data[this.name] == false)) {
            this.selectArray = [];
            if (this.multiple) {
                let selectsArray: Array<any> = [];
                if (Object.is('LISTBOX', this.editorType)) {
                    if (Object.is(this.mode, 'num') && this.items) {
                        let num: number = parseInt(this.data[this.name], 10);
                        this.items.forEach((item: any) => {
                            if ((num & item.value) == item.value) {
                                selectsArray.push(item.value);
                            }
                        });
                    } else if (Object.is(this.mode, 'str')) {
                        selectsArray = this.data[this.name].split(this.valueSeparator);
                        if(this.codeList.codeItemValueNumber){
                            for(let i = 0, len = selectsArray.length; i < len ;i++ ){
                                selectsArray[i] = Number(selectsArray[i]);
                            }
                        }
                    }
                } else if (Object.is('LISTBOXPICKUP', this.editorType)) {
                    selectsArray = this.data[this.name].split(this.valueSeparator);
                }
                this.selectArray = selectsArray;
            } else{
                this.selectArray = [this.data[this.name]];
            }
        }
    }

    /**
     * 生命周期
     *
     * @memberof AppListBox
     */
    public created() {
        if (this.formState) {
            this.formStateEvent = this.formState.subscribe(({ type, data }) => {
                if (Object.is('load', type)) {
                    if (Object.is('LISTBOX', this.editorType)) {
                        this.loadCodeListData();
                    } else if (Object.is('LISTBOXPICKUP', this.editorType)) {
                        this.loadData();
                    }
                }
            });
        }
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppCheckBox
     */
    public handlePublicParams(arg: any) {
        // 合并参数
        arg.param = this.viewparams ? Util.deepCopy(this.viewparams) : {};
        arg.context = this.context ? Util.deepCopy(this.context) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length >0) {
            let _context = Util.computedNavData(this.data,arg.context,arg.param,this.localContext);
            Object.assign(arg.context,_context);
        }
        if (this.localParam && Object.keys(this.localParam).length >0) {
            let _param = Util.computedNavData(this.data,arg.param,arg.param,this.localParam);
            Object.assign(arg.param,_param);
        }
    }

    /**
     * 加载代码表数据
     * 
     * @memberof AppListBox
     */
    public loadCodeListData() {
        if(this.tag && this.codelistType) {
            let data: any = {};
            this.handlePublicParams(data);
            // 参数处理
            let context = data.context;
            let viewparam = data.param;
            this.codeListService.getDataItems({
                tag: this.tag,
                type: this.codelistType,
                data: this.codeList,
                context:context,
                viewparam:viewparam
            }).then((codelistItems: Array<any>) => {
                this.formatCodeList(codelistItems);
            }).catch((error: any) => {
                LogUtil.log(`----${this.tag}----${this.$t('app.commonwords.codenotexist')}`);
            })
        }
    }

    /**
     * 加载数据
     * 
     * @memberof AppListBox
     */
    public loadData() {
        // 公共参数处理
        let data: any = {};
        this.handlePublicParams(data);
        // 处理搜索参数
        let context = data.context;
        let viewparam = data.param;
        if(!this.service){
            
        } else if(!this.acParams?.serviceName) {
           
        } else if(!this.acParams?.interfaceName) {
           
        } else {
            this.service.getItems(this.acParams.serviceName,this.acParams.interfaceName, context, viewparam).then((response: any) => {
                if (response) {
                    this.items = [...response];
                }
            }).catch((response: any) => {
                this.$throw(response)
            });
        } 
    }
    /**
     * 代码表类型和属性匹配
     * 
     * @param {*} items
     * @memberof AppListBox
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

    /**
     * 选中数据改变
     *
     * @param {*} value 选中项的key
     * @memberof AppListBox
     */
    public selectChange(value: any) {
        let _value: any;
        // 单多选
        if (this.multiple) {
            const values = [...value];
            if (Object.is('LISTBOX', this.editorType)) {
                // 根据代码表模式对值进行计算
                if (Object.is(this.mode, 'num')) {
                    let temp: number = 0;
                    values.forEach((item: any) => {
                        temp = temp | parseInt(item, 10);
                    });
                    _value = temp;
                } else if (Object.is(this.mode, 'str')) {
                    _value = values.join(this.valueSeparator);
                }
            } else if (Object.is('LISTBOXPICKUP', this.editorType)) {
                _value = values.join(this.valueSeparator);
            }
        } else {
            _value = value;
        }
        this.$emit('formitemvaluechange', { name: this.name, value: _value });
    }
}
</script>

<style lang="less">
@import './app-list-box.less';
</style>
