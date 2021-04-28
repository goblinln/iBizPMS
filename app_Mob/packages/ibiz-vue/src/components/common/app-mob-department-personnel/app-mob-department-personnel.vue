<template>
    <div class="app-mob-department-personnel">
        <ion-select :disabled="disabled" @ionChange="change" :multiple="multiple" @click="onClick" :ok-text="$t('app.button.confirm')" :cancel-text="$t('app.button.cancel')">
          <ion-select-option v-for="(item, index) in items" :key="index" :value="item.id">{{item.label}}
          </ion-select-option>
        </ion-select>
        <!-- <template>
          <ion-icon v-if="!disabled" @click="openView" name="search-outline"></ion-icon>
        </template> -->
    </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { Http } from "ibiz-core";
import { CodeListServiceBase } from "ibiz-core";

@Component({})
export default class AppMobDepartmentPersonnel extends Vue {

    /**
     * 名称标识
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public name!: string;

    /**
     * 树加载地址
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public treeurl?: string;

    /**
     * 数据接口地址
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public url!: string;

    /**
     * 多选
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop({default: false}) public multiple?: boolean;

    /**
     * 数据对象
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public data: any;

    /**
     * 代码表标识
     * 
     * @memberof AppMobDepartmentPersonnel
     */
    @Prop() public tag?: string;

    /**
     * 代码表类型
     * 
     * @memberof AppMobDepartmentPersonnel
     */
    @Prop() public codelistType?: string;

    /**
     * 过滤属性标识
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public filter?: string;

    /**
     * 是否启用
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public disabled?: boolean;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public value: any;

    /**
     * 上下文参数
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public context: any;

    /**
     * 关联属性
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public valueitem: any;

    /**
     * 填充属性
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Prop() public fillmap: any;

    /**
     * 选中项对象集合
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    public selects: any[] = [];

    /**
     * 选中项label集合
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    public selectsLabel: any[] = [] ;

    /**
     * 下拉数组
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    public items: any[] = [];
    /**
     * 过滤值
     *
     * @type {string}
     * @memberof AppMobDepartmentPersonnel
     */
    public filtervalue: string = '';

    /**
     * 获取需要过滤的部门id
     * 
     * @memberof AppMobDepartmentPersonnel
     */
    public getDepertmentId(){
        const context: any = JSON.parse(JSON.stringify(this.context));
        if(this.filter){
            if(this.data[this.filter]){
                this.filtervalue = this.data[this.filter];
            }else if(context[this.filter]){
                this.filtervalue = context[this.filter];
            }else{
                this.filtervalue = context.srfsdept;
            }
        }else{
            this.filtervalue = context.srfsdept;
        } 
    }

    /**
     * 下拉加载数据
     *
     * @param {*} $event
     * @memberof AppMobDepartmentPersonnel
     */
    public onClick($event: any){
            const items: Array<any> = this.$store.getters.getDepartmentPersonnel();
            if(items.length > 0){
                this.items = items;
            }else{
                this.getDepertmentId();
                if(this.treeurl){
                    let tempUrl = this.treeurl.replace('{deptId}',this.filtervalue);
                    let get = Http.getInstance().get(tempUrl, true);
                    get.then((response: any)=>{
                        if(response.status === 200) {
                            this.getTreeItems(response.data);
                        }
                    });
                }else{
                    this.getPersonnelItems(this.filtervalue);
                }
            }
    }

    /**
     * 加载当前部门和其下级部门数据
     *
     * @param {*} $event
     * @memberof AppMobDepartmentPersonnel
     */
    public getTreeItems(treeItems: Array<any>){
        if(treeItems.length > 0){
            treeItems.forEach((treeItem: any)=>{
                this.getPersonnelItems(treeItem.id);
            });
        }
    }

    /**
     * 加载部门人员数据
     *
     * @param {*} $event
     * @memberof AppMobDepartmentPersonnel
     */
    public getPersonnelItems($event: string){
        let tempUrl = this.url.replace('{deptId}',$event);
        let get = Http.getInstance().get(tempUrl, true);
        get.then((response: any) => {
            if(response.status === 200 && response.data.length > 0) {
                response.data.forEach((item: any)=>{
                    this.items.push(item);
                });
            }
            this.$store.commit("addDepartmentPersonnel",this.items);
        }).catch((error: any) => {
                console.log(error)
        })
    }

    /**
     * 值变化
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    @Watch('data',{immediate:true,deep:true})
    onValueChange(newVal: any, oldVal: any) {   
        this.selects = [];
        this.selectsLabel = [];
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
                    this.selects.push(_item);
                    let i = this.items.findIndex((select: any) => Object.is(select.id,_item.id));
                    if(i < 0){
                        this.items.push(_item);
                    }
                    this.selectsLabel.push(_item.id);
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
     * 填充label
     * 
     * @memberof AppMobDepartmentPersonnel
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
     * 打开选择视图
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    public openView() {
        const view: any = {
            viewname: 'app-group-picker',
            title: (this.$t('components.appGroupSelect.groupSelect') as string)
        };
        const context: any = JSON.parse(JSON.stringify(this.context));
        this.getDepertmentId();
        const param: any = {};
        Object.assign(param, {
            showtree: this.treeurl?true:false,
            url:this.url,
            treeurl:this.treeurl,
            filtervalue: this.filtervalue,
            multiple: this.multiple,
            selects: this.selects,
            selectType: 'dept',
        });
        // let container: Subject<any> = this.$appmodal.openModal(view, context, param);
        // container.subscribe((result: any) => {
        //     if (!result || !Object.is(result.ret, 'OK')) {
        //         return;
        //     }
        //     this.openViewClose(result);
        // });
    }

    /**
     * 选择视图关闭
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    public openViewClose(result: any) {
        if (result.datas && result.datas.length > 0) {
            this.selects = [];
            this.selectsLabel = [];
            this.selects = [...result.datas];
            this.selects.forEach((select: any)=>{
                let index = this.items.findIndex((item) => Object.is(item.id, select.id));
                if(index < 0){
                    this.items.push(select);
                }
                this.selectsLabel.push(select.id);
            });
        }
        this.setValue();
    }

    /**
     * 选中项发生变化时
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
     */  
    public change($event: any) {
        this.selects = [];
        if(this.multiple){
            if($event.length > 0){
                $event.forEach((select: any)=>{
                    let index = this.items.findIndex((item) => Object.is(item.id, select));
                    if (index >= 0) {
                        this.selects.push(this.items[index]);
                    }
                });
            }
        }else{
            let index = this.items.findIndex((item) => Object.is(item.id, $event));
            if (index >= 0) {
                this.selects.push(this.items[index]);
            }
        }
        this.setValue();
    }

    /**
     * 设置值
     *
     * @type {*}
     * @memberof AppMobDepartmentPersonnel
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
}

</script>
<style lang="less">
@import './app-mob-department-personnel.less';
</style>