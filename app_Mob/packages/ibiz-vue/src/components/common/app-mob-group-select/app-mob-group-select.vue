<template>
    <div class="ibiz-mob-group-select">
        <ion-input class="ibz-input" :value="selectName"  readonly></ion-input>
        <app-mob-icon  v-show="selects.length>0" class="delete-value right-common-icon" name="close-circle-outline" @onClick="clearSelects"></app-mob-icon>
        <app-mob-icon  v-show="selects.length<1" class="open-picker" name="search-outline" @onClick="openView"></app-mob-icon>
    </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { CodeListServiceBase } from "ibiz-core";

@Component({})
export default class AppMobGroupSelect extends Vue {
    /**
     * 名称标识
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() name!: string;

    /**
     * 树加载地址
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() treeurl?:string;

    /**
     * 数据接口地址
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() url!: string;

    /**
     * 多选
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop({default: false}) multiple?: boolean;

    /**
     * 数据对象
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() data: any;

    /**
     * 代码表标识
     * 
     * @memberof AppMobGroupSelect
     */
    @Prop() public tag?:string;

    /**
     * 代码表类型
     * 
     * @memberof AppMobGroupSelect
     */
    @Prop() public codelistType?:string;

    /**
     * 过滤属性标识
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() filter?: string;

    /**
     * 是否启用
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() disabled?: boolean;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() value: any;

    /**
     * 上下文参数
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() context: any;

    /**
     * 关联属性
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() valueitem: any;

    /**
     * 填充属性
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Prop() fillmap: any;

    /**
     * 选中项集合
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    protected selects: any[] = [];

    /**
     * 值变化
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    @Watch('data',{immediate:true,deep:true})
    onValueChange(newVal: any, oldVal: any) {
        this.selects = [];
        if (newVal) {
            let item: any = {};
            item.label = this.data[this.name]?this.data[this.name].split(','):[];
            item.id = this.data[this.valueitem] ? this.data[this.valueitem].split(',') : [];
            if(this.fillmap) {
                for(let key in this.fillmap) {
                    item[this.fillmap[key]] = this.data[key] ? this.data[key].split(',') : [];
                }
            }
            const callback:any = (item:any) =>{
                item.label.forEach((val: string, index: number) => {
                    let _item: any = {};
                    for(let key in item) {
                        _item[key] = item[key][index] ? item[key][index] : null;
                    }
                    this.selects.push(_item)
                })
            }
            if(item.label.length == 0 && item.id.length > 0){
                this.fillLabel(item,item.id,(result:any) =>{
                    item.label = result.label;
                    callback(item);
                });
            }else{
                callback(item);
            }
            
        }
    }

    /**
     * 单选时选中名称
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    get selectName() {
        if(this.selects.length == 1) {
            return this.selects[0].label;
        } else {
            let chosenName = '';
            for (let i = 0; i < this.selects.length; i++) {
              const element:any = this.selects[i];
              chosenName += element.label + ',';
            }
            return chosenName.slice(0,chosenName.length-1)
        }
    }

    /**
     * 打开选择视图
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    public async openView() {
        const view: any = {
            viewname: 'app-mob-group-picker',
            title: (this.$t('components.AppMobGroupSelect.groupSelect') as string)
        };
        const context: any = JSON.parse(JSON.stringify(this.context));
        let filtervalue:string = "";
        if(this.filter){
            if(this.data[this.filter]){
                filtervalue = this.data[this.filter];
            }else if(context[this.filter]){
                filtervalue = context[this.filter];
            }else{
                filtervalue = context.srforgid;
            }
        }else{
            filtervalue = context.srforgid;
        }
        const param: any = {};
        Object.assign(param, {
            showtree: this.treeurl?true:false,
            url:this.url,
            treeurl:this.treeurl,
            filtervalue: filtervalue,
            multiple: this.multiple,
            selects: this.selects,
        });
        const result: any = await this.$appmodal.openModal(view, context, param);
        if (result || Object.is(result.ret, 'OK')) {
            this.openViewClose(result);
        }
    }

    /**
     * 选择视图关闭
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    public openViewClose(result: any) {
        this.selects = [];
        if (result.datas && result.datas.length > 0) {
            this.selects = result.datas;
        }
        this.setValue();
    }

    /**
     * 数据删除
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    public remove(item: any) {
        this.selects.splice(this.selects.indexOf(item), 1);
        this.setValue();
    }

    /**
     * 设置值
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public setValue() {
        let item: any = {};
        item[this.name] = null;
        if(this.valueitem) {
            item[this.valueitem] = null;
        }
        if(this.fillmap) {
            for(let key in this.fillmap) {
                item[key] = null;
            }
        }
        if(this.multiple) {
            this.selects.forEach((select: any) => {
                item[this.name] = item[this.name] ? `${item[this.name]},${select.label}` : select.label;
                if(this.valueitem) {
                    item[this.valueitem] = item[this.valueitem] ? `${item[this.valueitem]},${select.id}` : select.id;
                }
                if(this.fillmap) {
                    for(let key in this.fillmap) {
                        item[key] = item[key] ? `${item[key]},${select[this.fillmap[key]]}` : select[this.fillmap[key]];
                    }
                }
            });
        } else {
            item[this.name] = this.selects.length > 0 ? this.selects[0].label : null;
            if(this.valueitem) {
                item[this.valueitem] = this.selects.length > 0 ? this.selects[0].id : null;
            }
            if(this.fillmap) {
                for(let key in this.fillmap) {
                    item[key] = this.selects.length > 0 ? this.selects[0][this.fillmap[key]] : null;
                }
            }
        }
        for(let key in item) {
            this.$emit('formitemvaluechange', { name: key, value: item[key] });
        }
    }

    /**
     * 填充label
     * 
     * @memberof AppMobGroupSelect
     */
    public fillLabel(tempObject:any,valueItem:Array<any>,callback:any){
        if(tempObject.label.length === 0 && tempObject.id.length >0 && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
        let codeListService:CodeListServiceBase = new CodeListServiceBase();
        codeListService.getItems(this.tag).then((items:any) =>{
            if(items && items.length >0 && valueItem.length >0){
            let tempLabel:Array<any> = [];
            valueItem.forEach((value:any) =>{
                let result:any = items.find((item:any) =>{
                    return item.id === value;
                })
                tempLabel.push(result.label);
            })
            Object.assign(tempObject,{label:tempLabel});
            }
            callback(tempObject);
        }).catch((error:any) =>{
            console.log(error);
        })
        }
    }

    /**
     * 全部清除
     * 
     * @memberof AppMobGroupSelect
     */
    public clearSelects(){
      this.selects = [];
      this.setValue();
    }

}
</script>

<style lang="less">
@import './app-mob-group-select.less';
</style>