<template>
    <div class="dropdown-list-mpicker-container">
        <i-select
            v-if="!hasChildren"
            class='dropdown-list-mpicker'
            multiple 
            :transfer="true"
            transfer-class-name="dropdown-list-mpicker-transfer"
            v-model="currentVal"
            :disabled="disabled"
            :clearable="true"
            :filterable="filterable"
            @on-open-change="onClick"
            :placeholder="placeholder?placeholder:$t('components.dropDownListMpicker.placeholder')">
            <i-option v-for="(item, index) in items" :key="index" :class="item.class" :value="item.value ? item.value.toString():''" :label="item.text">
                <Checkbox :value="(currentVal.indexOf(item.value ? item.value.toString() : '')) == -1 ? false : true">
                    {{Object.is(codelistType,'STATIC') ? $t('codelist.'+tag+'.'+item.value) : item.text}}
                </Checkbox>
            </i-option>
        </i-select>
        <ibiz-select-tree v-if="hasChildren" class="tree-dropdown-list-mpicker" :disabled="disabled" :NodesData="items" v-model="currentVal" :multiple="true"></ibiz-select-tree>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model } from 'vue-property-decorator';
import { CodeListService } from "ibiz-service";
import { LogUtil, Util } from 'ibiz-core';

@Component({
})
export default class DropDownListMpicker extends Vue {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof DropDownListMpicker
     */  
    public codeListService:CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 是否有子集
     * @type {boolean}
     * @memberof DropDownListMpicker
     */
    public hasChildren:boolean = false;

    /**
     * 当前选中值
     * @type {any}
     * @memberof DropDownListMpicker
     */
    @Model('change') readonly itemValue!: any;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof DropDownListMpicker
     */
    @Prop() public tag?: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof DropDownListMpicker
     */
    @Prop() public codelistType?: string;

    /**
     * 代码表
     *
     * @type {string}
     * @memberof DropDownListMpicker
     */    
    @Prop() public codeList!: any;

    /**
     * 代码表值分隔符
     *
     * @type {string}
     * @memberof DropDownListMpicker
     */
    @Prop({default:','}) public valueSeparator?: string;

    /**
     * 是否禁用
     * @type {any}
     * @memberof DropDownListMpicker
     * 
     */
    @Prop() public disabled?: any;

    /**
     * 是否支持过滤
     * @type {boolean}
     * @memberof DropDownListMpicker
     */
    public filterable: boolean = true;

    /**
     * 下拉选提示内容
     * @type {string}
     * @memberof DropDownListMpicker
     */
    @Prop() public placeholder?: string;

    /**
     * 属性类型
     *
     * @type {'string' | 'number'}
     * @memberof DropDownListMpicker
     */
    @Prop({ default: 'string' })
    public valueType!: 'string' | 'number';

    /**
     * 局部上下文导航参数
     * 
     * @type {any}
     * @memberof DropDownListMpicker
     */
    @Prop() public localContext!:any;

    /**
     * 局部导航参数
     * 
     * @type {any}
     * @memberof DropDownListMpicker
     */
    @Prop() public localParam!:any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof DropDownListMpicker
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof DropDownListMpicker
     */
    @Prop() public viewparams!: any;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof DropDownListMpicker
     */
    @Prop() public data?: any;

    /**
     * 计算属性(当前值)
     * @type {any}
     * @memberof DropDownListMpicker
     */
    set currentVal(val: any) {
        if(this.hasChildren && val){
            let tempVal:any = JSON.parse(val);
            if(tempVal.length >0){
                val = tempVal.map((item:any) =>{
                    return item.value;
                })
            }
        }
        const type: string = this.$util.typeOf(val);
        val = Object.is(type, 'null') || Object.is(type, 'undefined') ? [] : val;
        let value = val.length > 0 ? val.join(this.valueSeparator) : '';
        this.$emit('change', value);
    }

    /**
     * 获取值对象
     *
     * @memberof DropDownListMpicker
     */
    get currentVal() {
        if(this.hasChildren){
            if(this.itemValue){
                let list:Array<any> = [];
                let selectedvalueArray:Array<any> = [];
                let curSelectedValue:Array<any> = this.itemValue.split(this.valueSeparator);
                this.getItemList(list,this.items);
                if(curSelectedValue.length > 0){
                    curSelectedValue.forEach((selectedVal:any) =>{
                        let tempResult:any = list.find((item:any) =>{
                            return item.value == selectedVal;
                        })
                        selectedvalueArray.push(tempResult);
                    })
                }
                return selectedvalueArray.length >0?JSON.stringify(selectedvalueArray):null;
            }else{
                return null;
            }

        }
        return this.itemValue? this.itemValue.split(this.valueSeparator):[];
    }

    /**
     * 获取代码表列表
     *
     * @memberof DropDownListMpicker
     */
    public getItemList(list:Array<any>,items:Array<any>){
        if(items && items.length >0){
            items.forEach((item:any) =>{
                if(item.children){
                    this.getItemList(list,item.children);
                }
                list.push(item);
            })
        }
    }

    /**
     * 代码表
     *
     * @type {any[]}
     * @memberof DropDownListMpicker
     */
    public items: any[] = [];

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof DropDownList
     */
    public handlePublicParams() {
        // 合并表单参数
        let viewparams = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        let context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length >0) {
            let _context = this.$util.computedNavData(this.data,context,viewparams,this.localContext);
            Object.assign(context,_context);
        }
        if (this.localParam && Object.keys(this.localParam).length >0) {
            let _param = this.$util.computedNavData(this.data,context,viewparams,this.localParam);
            Object.assign(viewparams,_param);
        }
        return {context,viewparams};
    }

    /**
     * vue  生命周期
     *
     * @memberof DropDownListMpicker
     */
    public created() {
        if(this.itemValue){
            this.handleCodeListItems();
        }
    }

    /**
     * 代码表类型和属性匹配
     * 
     * @param {*} items
     * @memberof DropDownListMpicker
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
        this.handleLevelCodeList(Util.deepCopy(this.items));
    }

    /**
     * 处理代码表
     * 
     * @memberof DropDownListMpicker
     */
    public handleCodeListItems() {
        let arg = this.handlePublicParams();
        if(this.tag && this.codelistType) {
            this.codeListService.getDataItems({ tag: this.tag, type: this.codelistType,data: this.codeList,context:arg.context,viewparam:arg.viewparams }).then((codelistItems: Array<any>) => {
                this.formatCodeList(codelistItems);   
            }).catch((error: any) => {
                LogUtil.log(`----${this.tag}----${(this.$t('app.commonwords.codenotexist') as string)}`);
            })
        }
    }
    
    /**
     * 下拉点击事件
     *
     * @param {*} $event
     * @memberof DropDownListMpicker
     */
    public onClick($event:any){
        if($event){
            this.handleCodeListItems();
        }
    }

    /**
     * 处理层级代码表
     * 
     * @param {*} items
     * @memberof DropDownListMpicker
     */
    public handleLevelCodeList(items: Array<any>){
        if(items && items.length >0){
            this.hasChildren = items.some((item:any) =>{
                return item.pvalue;
            })
            if(this.hasChildren){
                let list:Array<any> = [];
                items.forEach((codeItem:any) =>{
                    if(!codeItem.pvalue){
                        let valueField:string = codeItem.value;
                        this.setChildCodeItems(valueField,items,codeItem);
                        list.push(codeItem);
                    }
                })
                this.items = list;
            }
        }
    }

    /**
     * 计算子类代码表
     * 
     * @param {*} items
     * @memberof DropDownListMpicker
     */
    public setChildCodeItems(pValue:string,result:Array<any>,codeItem:any){
        result.forEach((item:any) =>{
            if(item.pvalue == pValue){
                let valueField:string = item.value;
                this.setChildCodeItems(valueField,result,item);
                if(!codeItem.children){
                    codeItem.children = [];
                }
                codeItem.children.push(item);
            }
        })
    }

}
</script>

<style lang='less'>
@import './dropdown-list-mpicker.less';
</style>