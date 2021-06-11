<template>
    <div class="dropdown-list-extend">
        <i-select
            class='dropdown-list'
            :transfer="true"
            v-model="currentVal"
            :disabled="disabled === true ? true : false"
            :clearable="true"
            :filterable="filterable === true ? true : false"
            @on-open-change="onClick"
            :placeholder="$t('components.dropdownlist.placeholder')">
            <i-option v-for="(item, index) in items" :key="index" :value="item.value">{{($t('codelist.'+tag+'.'+item.value)!== ('codelist.'+tag+'.'+item.value))?$t('codelist.'+tag+'.'+item.value) : item.text}}</i-option>
        </i-select>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Watch, Prop, Model } from 'vue-property-decorator';
import { CodeListService } from "ibiz-service";
import { Util } from 'ibiz-core';

@Component({
})
export default class DropDownListExtend extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof DropDownListExtend
     */  
    public codeListService:CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 额外参数
     *
     * @type {*}
     * @memberof DropDownListExtend
     */
    public otherParam:any;

    /**
     * 查询参数
     * @type {*}
     * @memberof DropDownListExtend
     */
    public queryParam:any;

    /**
     * 当前选中值
     * 
     * @type {*}
     * @memberof DropDownListExtend
     */
    @Model('change') readonly itemValue!: any;

    /**
     * 监控值变化，根据属性类型强制转换
     * 
     * @memberof DropDownListExtend
     */
    @Watch('itemValue')
    public valueWatch() {
        try {
            this.readyValue();
            // 代码表集合中不存在改选项，重新准备集合
            if(this.value && !this.items.find((item: any) => Object.is(this.value, item.value))) {
                this.readyCodelist();
            }
        } catch (error) {
            console.log('下拉列表，值转换失败');
        }
    }

    /**
     * 选择实际值
     * 
     * @type {*}
     * @memberof DropDownListExtend
     */
    public value: any = null;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof DropDownListExtend
     */
    @Prop() public tag?: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof DropDownListExtend
     */
    @Prop() public codelistType?: string;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof DropDownListExtend
     */
    @Prop() public data?: any;

    /**
     * 属性类型
     *
     * @type {'string' | 'number'}
     * @memberof DropDownListExtend
     */
    @Prop({ default: 'string' })
    public valueType!: 'string' | 'number';

    /**
     * 局部上下文导航参数
     *
     * @type {*}
     * @memberof DropDownListExtend
     */
    @Prop() public localContext!:any;
    
    /**
     * 局部导航参数
     *
     * @type {*}
     * @memberof DropDownListExtend
     */
    @Prop() public localParam!:any;

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
     * 是否禁用
     * @type {any}
     * @memberof DropDownListExtend
     * 
     */
    @Prop() public disabled?: any;

    /**
     * 是否支持过滤
     * @type {boolean}
     * @memberof DropDownListExtend
     */
    @Prop() public filterable?: boolean;

    /**
     * 下拉选提示内容
     * @type {string}
     * @memberof DropDownListExtend
     */
    @Prop() public placeholder?: string;

    /**
     * 字段属性
     * 
     * @type {string}
     * @memberof DropDownListExtend
     */
    @Prop() public name?:string;

    /**
     * 当前行数据
     * 
     * @type {any[]}
     * @memberof DropDownListExtend
     */
    @Prop() public datas?:Array<any>;

    /**
     * 计算属性(当前值)
     * @type {any}
     * @memberof DropDownListExtend
     */
    set currentVal(val: any) {
        if (Util.isExistAndNotEmpty(val)) {
            this.$emit('change', val);
        } else {
            this.$emit('change', undefined);
        }
    }

    /**
     * 获取值对象
     *
     * @memberof DropDownListExtend
     */
    get currentVal() {
        return this.value;
    }

    /**
     * 代码表
     *
     * @type {any[]}
     * @memberof DropDownListExtend
     */
    public items: any[] = [];

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof DropDownListExtend
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
            let _param = this.$util.computedNavData(this.data,arg.context,arg.param,this.localParam);
            Object.assign(arg.param,_param);
        }
        if (this.name && this.datas){
            let _name: string = this.name;
            let _datas: any[] = [];
            for (let data of this.datas) {    
                if (data[_name]) {
                    if(!(this.data[_name] && this.data[_name] == data[_name])) {
                        _datas.push(data[_name]);
                    }
                }
            }
            arg.param[_name] = _datas.join(',');
        }
    }

    /**
     * 格式化代码表值类型
     *
     * @param {any[]} items
     * @returns
     * @memberof DropDownListExtend
     */
    protected formatCodeList(items: any[]): void {
        // 判断类型是否和属性一致
        let judge = false;
        this.items = [];
        try {
            items.forEach((item: any) => {
                const type = this.$util.typeOf(item.value);
                if (type !== this.valueType) {
                    judge = true;
                    if (type === 'number') {
                        item.value = item.value.toString();
                    } else {
                        if (item.value.indexOf('.') === -1) {
                            item.value = parseInt(item.value);
                        } else {
                            item.value = parseFloat(item.value);
                        }
                    }
                }
                this.items.push(item);
            });
            if (judge) {
                console.warn(`代码表「${this.tag}」值类型和属性类型不符，目前采用强制转换模式。请修正代码表值类型和属性类型匹配。`);
            }
        } catch (error) {
            console.warn('代码表值类型和属性类型不符，目前采用强制转换模式。转换过程异常，请修正代码表值类型和属性类型匹配。');
        }
    }

    /**
     * vue  生命周期
     *
     * @memberof DropDownListExtend
     */
    public created() {
        this.readyCodelist();
        this.readyValue();
    }
    
    /**
     * 准备值
     *
     * @memberof DropDownList
     */
    public readyValue() {
        if(this.itemValue == null) {
            this.value = null;
            return;
        }
        if (this.$util.typeOf(this.itemValue) === this.valueType) {
            this.value = this.itemValue;
        } else if (this.valueType === 'number') {
            if (this.itemValue.indexOf('.') === -1) {
                this.value = parseInt(this.itemValue);
            } else {
                this.value = parseFloat(this.itemValue);
            }
        } else {
            this.value = this.itemValue.toString();
        }
    }

    /**
     * 准备代码表
     *
     * @memberof DropDownListExtend
     */
    public readyCodelist() {
        if(this.tag && Object.is(this.codelistType,"STATIC")){
          const codelist = this.$store.getters.getCodeList(this.tag);
          if (codelist) {
              this.formatCodeList(JSON.parse(JSON.stringify(codelist.items)));
          } else {
              console.log(`----${this.tag}----${(this.$t('app.commonwords.codenotexist') as string)}`);
          }
      }else if(this.tag && Object.is(this.codelistType,"DYNAMIC")){
          // 公共参数处理
          let data: any = {};
          this.handlePublicParams(data);
          // 参数处理
          let _context = data.context;
          let _param = data.param;
          this.codeListService.getItems(this.tag,_context,_param).then((res:any) => {
              this.formatCodeList(res);
          }).catch((error:any) => {
              console.log(`----${this.tag}----${(this.$t('app.commonwords.codenotexist') as string)}`);
          });
      }
    }

    /**
     * 下拉点击事件
     *
     * @param {*} $event
     * @memberof DropDownListExtend
     */
    public onClick($event:any){
        if($event){
            if(this.tag && Object.is(this.codelistType,"DYNAMIC")){
                // 公共参数处理
                let data: any = {};
                this.handlePublicParams(data);
                // 参数处理
                let _context = data.context;
                let _param = data.param;
                this.codeListService.getItems(this.tag,_context,_param).then((res:any) => {
                    this.formatCodeList(res);
                }).catch((error:any) => {
                    console.log(`----${this.tag}----${(this.$t('app.commonwords.codenotexist') as string)}`);
                });
            }
        }
    }
}
</script>
<style lang='less'>
@import './dropdown-list-extend.less';
</style>