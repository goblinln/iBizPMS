<template>
    <div class="app-mob-group-select" @click="openView">
       <div class="form-value-content select-value-content">{{visibleLabel}}</div><van-icon class="app-mob-select-vant-icon" name="arrow" />
    </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { CodeListServiceBase, Util } from "ibiz-core";
import axios from 'axios';
import { CodeListService } from 'ibiz-service';

@Component({})
export default class AppMobGroupSelect extends Vue {

     /**
     * 名称标识
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() name!: string;

    /**
     * 树加载地址
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() treeurl?:boolean;

    /**
     * 数据接口地址
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() url!: string;

    /**
     * 多选
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop({default: false}) multiple?: boolean;

    /**
     * 数据对象
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() data: any;

    /**
     * 代码表标识
     * 
     * @memberof AppGroupSelect
     */
    @Prop() public tag?:string;

    /**
     * 代码表类型
     * 
     * @memberof AppGroupSelect
     */
    @Prop() public codelistType?:string;

    /**
     * 过滤属性标识
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() filter?: string;

    /**
     * 是否启用
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() disabled?: boolean;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() value: any;

    /**
     * 上下文参数
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() context: any;

    /**
     * 关联属性
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() valueitem: any;

    /**
     * 填充属性
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Prop() fillMap: any;

    /**
     * 请求方式
     *
     * @type {stinr}
     * @memberof AppGroupSelect
     */ 
    @Prop({ default: 'get'})
    public requestMode!: 'get' | 'post' | 'delete' | 'put';

    /**
     * 树双向绑定值
     *
     * @memberof AppGroupSelect
     */ 
    get curValue(){
        return JSON.stringify(this.selects);
    }
    
    set curValue(newVal:any ){
        this.onSelect(newVal);
    }

    /**
     * 分组编辑器模式
     *
     * @memberof AppGroupSelect
     */ 
    @Prop({ default: ''})
    public editorMode ?:string ;

    /**
     * 选中项集合
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    protected selects: any[] = [];

    /**
     * 树模式数据
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public nodesData:any[] = [];

    /**
     * 树模式下模拟树父节点标识（用于过滤父节点）
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public groupIDArray:string[] = [];

    /**
     * 值变化
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Watch('data',{immediate:true,deep:true})
    onValueChange(newVal: any, oldVal: any) {
        this.selects = [];
        if (newVal) {
            let item: any = {};
            item.label = this.data[this.name]?this.data[this.name].split(','):[];
            item.id = this.data[this.valueitem] ? this.data[this.valueitem].split(',') : [];
            if(this.fillMap) {
                for(let key in this.fillMap) {
                    item[this.fillMap[key]] = this.data[key] ? this.data[key].split(',') : [];
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
            console.log(this.selects);
            
        }
    }

    
    /**
     * 打开选择视图
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public openView() {
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
            requestMode: this.requestMode,
        });
        this.$appmodal.openModal(view, context,param).then((result:any)=>{
          if(result.ret != "OK"){
            return;
          }
          this.openViewClose(result);
        });
    }

    /**
     * 选择视图关闭
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public openViewClose(result: any) {
        this.selects = [];
        if (result.datas && result.datas.length > 0) {
            this.selects = result.datas
        }
        this.setValue()
    }

    /**
     * 数据删除
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public remove(item: any) {
        this.selects.splice(this.selects.indexOf(item), 1);
        this.setValue()
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
        if(this.fillMap) {
            for(let key in this.fillMap) {
                item[key] = null;
            }
        }
        if(this.multiple) {
            this.selects.forEach((select: any) => {
                item[this.name] = item[this.name] ? `${item[this.name]},${select.label}` : select.label;
                if(this.valueitem) {
                    item[this.valueitem] = item[this.valueitem] ? `${item[this.valueitem]},${select.id}` : select.id;
                }
                if(this.fillMap) {
                    for(let key in this.fillMap) {
                        item[key] = item[key] ? `${item[key]},${select[this.fillMap[key]]}` : select[this.fillMap[key]];
                    }
                }
            });
        } else {
            item[this.name] = this.selects.length > 0 ? this.selects[0].label : null;
            if(this.valueitem) {
                item[this.valueitem] = this.selects.length > 0 ? this.selects[0].id : null;
            }
            if(this.fillMap) {
                for(let key in this.fillMap) {
                    item[key] = this.selects.length > 0 ? this.selects[0][this.fillMap[key]] : null;
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
     * @memberof AppGroupSelect
     */
    public fillLabel(tempObject:any,valueItem:Array<any>,callback:any){
        if(tempObject.label.length === 0 && tempObject.id.length >0 && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
        let codeListService:CodeListService = new CodeListService();
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
     * 声明周期
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    mounted(){
        if(this.editorMode == 'tree'){
            this.loadTree();
        }
    }

    /**
     * 加载树数据
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public loadTree() {
        const context: any = JSON.parse(JSON.stringify(this.context));
        let orgid:string = "";
        if(this.filter){
            if(this.data[this.filter]){
                orgid = this.data[this.filter];
            }else if(context[this.filter]){
                orgid = context[this.filter];
            }else{
                orgid = context.srforgid;
            }
        }else{
            orgid = context.srforgid;
        }
        let tempTreeUrl = this.url?.replace('${selected-orgid}',orgid);
        if(!tempTreeUrl){
            return;
        }
        axios({method: this.requestMode, url: tempTreeUrl, data: {}}).then((response: any) => {
            if(response.status === 200) {
                this.parseTreeData(response.data);
            }
        }).catch((error: any) => {
            console.log(error);
            
        })
    }

    /**
     * 树选择事件
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    public onSelect(event: any) {
        if (!event || JSON.parse(event).length == 0) {
            return;
        }
        const items: any[] = JSON.parse(event);
        this.selects = [];
        const _this: any = this;
        // 过滤根节点
        const curValue = items.filter((item: any) => {
            return !(
                _this.groupIDArray.findIndex((_item: any) => {
                    return item.id === _item;
                }) != -1
            );
        });
        this.selects = curValue;
        this.setValue();
    }

    /**
     * 构造树数据
     *
     * @type {*}
     * @memberof AppGroupSelect
     */
    public parseTreeData(data:any) {
        let treeData:any = [];
        data.forEach((item:any) => {
            const index = treeData.findIndex((_item:any)=>{return _item.label == item.group});
            if(index != -1){
                treeData[index].children.push(item);
            }else{
                const uuid = Util.createUUID();
                this.groupIDArray.push(uuid);
                treeData.push({id:uuid,label:item.group,children:[item]});
            }
        });
        this.nodesData =treeData;
    }
    /**
   * 显示文本
   * 
   * @memberof AppOrgSelect
   */
  get visibleLabel(){
      if(this.selects.length > 0){
        let text = '';
        this.selects.forEach((item:any)=>{
          if(item.label){
            text = text?text+','+item.label:text+item.label;
          }
        })
        return text;
      }else{
        return  '';
      }
  }
}
</script>

<style lang="less">
@import './app-mob-group-select.less';
</style>