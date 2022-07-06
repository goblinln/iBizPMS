<template>
  <div class="app-mob-department-select" @click="openView">
      <div class="form-value-content select-value-content">{{visibleLabel}}</div><van-icon class="app-mob-select-vant-icon" name="arrow" />
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch, Prop, Model } from 'vue-property-decorator';
import { CodeListServiceBase, Http } from "ibiz-core";
import { CodeListService } from 'ibiz-service';
import axios from 'axios';
@Component({
})
export default class AppMobDepartmentSelect extends Vue {

    /**
     * 接口url
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop() public url?: any;

    /**
     * 代码表标识
     * 
     * @memberof AppDepartmentSelect
     */
    @Prop() public tag?:string;

    /**
     * 代码表类型
     * 
     * @memberof AppDepartmentSelect
     */
    @Prop() public codelistType?:string;

    /**
     * 过滤项
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop() public filter?: any;

    /**
     * 过滤项
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop() public fillMap?: any;

    /**
     * 是否多选
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop({default:false}) public multiple?: any;

    /**
     * 是否禁用
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 只读模式
     * 
     * @type {boolean}
     */
    @Prop({default: false}) public readonly?: boolean;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop() public data!: any;

    /**
     * 上下文变量
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    @Prop() public context!: any;

    /**
     * 请求方式
     *
     * @type {string}
     * @memberof AppDepartmentSelect
     */ 
    @Prop({ default: 'get'})
    public requestMode!: 'get' | 'post' | 'delete' | 'put';

    /**
     * 选中数值
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    public selectTreeValue:any = "";

    /**
     * 树节点数据
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    public nodesData: any[] = [];

    /**
     * 当前树节点数据的url
     *
     * @type {*}
     * @memberof AppDepartmentSelect
     */
    public oldurl: any;

    /**
     * 获取节点数据
     *
     * @memberof AppDepartmentSelect
     */
    public handleFilter(){
      if(this.filter){
          if(this.data && this.data[this.filter]){
            return this.data[this.filter];
          }else if(this.context && this.context[this.filter]){
            return this.context[this.filter];
          }else{
            return this.context.srforgid;
          }
      }else{
          return this.context.srforgid;
      }
    }

    /**
     * 获取节点数据
     *
     * @memberof AppDepartmentSelect
     */
    public searchNodesData(){
      // 处理过滤参数，生成url
      let param = this.handleFilter();
      let _url = this.url.replace('${srforgid}',param)
      if(this.oldurl === _url){
          return;
      }
      this.oldurl = _url;
      // 缓存机制
      const result:any = this.$store.getters.getDepData(_url);
      if(result){
        this.nodesData = result;
        return;
      }
      Http.getInstance()[this.requestMode](_url, {}).then((response: any) => {
        const data = this.parseData(response.data)
          this.nodesData = data;
          this.$store.commit('addDepData', { srfkey: _url, depData: response.data });
      }).catch((response: any) => {
          // this.$throw(response,'searchNodesData');
      });
    }

    /**
     * 解析数据
     * 
     * @memberof AppOrgSelect
     */
    public parseData(data:any){
      if(this.fillMap && Object.keys(this.fillMap).length >0){
        Object.keys(this.fillMap).forEach((key:any) =>{
            data.forEach((_item:any)=>{
              _item[key] = _item[this.fillMap[key]];
            })
        })
      }
      return data;
    }

    /**
     * 值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDepartmentSelect
     */
    @Watch('data',{immediate:true,deep:true})
    public onValueChange(newVal: any, oldVal: any) {
        if(newVal){
          this.computedSelectedData();
          this.$nextTick(()=>{
            this.searchNodesData();
          });
        }
    }

    /**
     * 计算选中值
     * 
     * @memberof AppOrgSelect
     */
    public computedSelectedData(){
      // 单选
      if(!this.multiple){
        if(this.fillMap && Object.keys(this.fillMap).length >0){
        let templateValue:any = {};
        Object.keys(this.fillMap).forEach((item:any) =>{
          if(this.data && this.data[this.fillMap[item]]){
            Object.assign(templateValue,{[item]:this.data[this.fillMap[item]]});
          }
        })
          if(!templateValue.label && templateValue.id && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
            this.fillLabel(templateValue,templateValue.id,(templateValue:any) =>{
              this.selectTreeValue = JSON.stringify([templateValue]);
            });
          }else{
            this.selectTreeValue = JSON.stringify([templateValue]);
          }
        }
      }else{
      // 多选
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          let tempArray:Array<any> = [];
          Object.keys(this.fillMap).forEach((item:any) =>{
            if(this.data && this.data[this.fillMap[item]]){
              let tempDataArray:Array<any> = (this.data[this.fillMap[item]]).split(",");
              tempDataArray.forEach((tempData:any,index:number) =>{
                if(tempArray.length < tempDataArray.length){
                  let singleData:any ={[item]:tempData};
                  tempArray.push(singleData);
                }else{
                  Object.assign(tempArray[index],{[item]:tempData});
                }
              })
            }
          })
          let tempflag:boolean = false;
          if(tempArray.length >0 && tempArray.length >0){
            tempArray.forEach((item:any) =>{
              if(!item.label) tempflag = true;
            })
          }
          if(tempflag && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
            let tempStatus:number = 0;
            tempArray.forEach((item:any) =>{
              if(!item.label){
                tempStatus += 1;
                this.fillLabel(item,item.id,(result:any) =>{
                  item = result;
                  tempStatus -= 1;
                  if(tempStatus === 0){
                    this.selectTreeValue = JSON.stringify(tempArray);
                  }
                })
              }
            })
          }else{
            this.selectTreeValue = JSON.stringify(tempArray);
          }
        }
      }
    } 

    /**
     * select事件处理
     * 
     * @param {*} $event
     * @memberof AppDepartmentSelect
     */
    public onSelect($event:any){
        // 组件自身抛值事件
        let selectArr = JSON.parse($event);
        // fillMap抛值事件
        if(this.fillMap && Object.keys(this.fillMap).length > 0){
            Object.keys(this.fillMap).forEach((attribute:string) => {
                let _name = this.fillMap[attribute];
                let values = selectArr.map((item:any) => item[attribute]);
                let _value = $event === "[]" ? null : values.join(",");
                setTimeout(() => {
                  this.$emit('select-change',{name: this.fillMap[attribute], value: _value});
                },0);
            });
        }
    }

  /**
   * 填充label
   * 
   * @memberof AppOrgSelect
   */
  public fillLabel(tempObject:any,valueItem:any,callback:any){
    if(!tempObject.label && tempObject.id && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
      let codeListService:CodeListService = new CodeListService();
      codeListService.getItems(this.tag).then((items:any) =>{
        if(items && items.length >0){
          let result:any = items.find((item:any) =>{
            return item.id === valueItem;
          })
          Object.assign(tempObject,{label:result.label});
        }
        callback(tempObject);
      }).catch((error:any) =>{
        // LogUtil.log(error);
      })
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
            viewname: 'app-tree',
            title: (this.$t('components.AppMobOrgSelect.orgSelect') as string)
        };
        const context: any = JSON.parse(JSON.stringify(this.context));
        const param: any = {};
        Object.assign(param, {
            selectTreeValue: this.selectTreeValue,
            multiple: this.multiple,
            nodesData: this.nodesData
        });
        this.$appmodal.openModal(view, context,param).then((result:any)=>{
          if(result.ret != "OK"){
            return;
          }
          this.onSelect(JSON.stringify(result.datas));
        });
        
    }

      /**
   * 显示文本
   * 
   * @memberof AppOrgSelect
   */
  get visibleLabel(){
    if(this.selectTreeValue){
      const value = JSON.parse(this.selectTreeValue);
      if(value.length > 0){
        let text = '';
        value.forEach((item:any)=>{
          if(item.label){
            text = text?text+','+item.label:text+item.label;
          }
        })
        return text;
      }else{
        return  '';
      }
    }else{
      return '';
    }
  }
}
</script>

<style lang='less'>
@import './app-mob-department-select.less';
</style>