<template>
  <div class="app-mob-org-select">
  <ion-input :disabled="disabled" :value="data[fillMap.label]"  readonly @click="openView">
      <svg t="1608543874255" class="icon" viewBox="0 0 1165 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2970" width="200" height="200"><path d="M1107.30913719 909.45959375l-186.86323313-145.37686875a479.97811781 479.97811781 0 1 0-69.42107062 87.6783975q13.80107531-13.80107531 26.30518125-28.51668l185.08405875 143.96350594a36.58116281 36.58116281 0 1 0 44.89506375-57.73172719z m-594.79308657 9.37807969c-223.66055812 0-405.63521719-181.95803156-405.63521718-405.63521719S288.80560906 107.56723906 512.46616719 107.56723906 918.10138437 289.59178156 918.10138437 513.20245625 736.14335281 918.83767344 512.46616719 918.83767344z" fill="#474747" p-id="2971"></path></svg>  
    </ion-input>
  </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { CodeListServiceBase } from "ibiz-core";
import { observable } from 'rxjs';
@Component({})
export default class AppMobOrgSelect extends Vue {
  /**
   * 表单数据
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public data!:any;

  /**
   * 上下文
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public context!:any;

  /**
   * 填充对象
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public fillMap:any;

  /**
   * 过滤项
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public filter?:string;

  /**
   * 代码表标识
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public tag?:string;

  /**
   * 代码表类型
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public codelistType?:string;

  /**
   * 是否多选
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop({default:false}) public multiple?:boolean;

  /**
   * 是否禁用
   *
   * @type {*}
   * @memberof AppDepartmentSelect
   */
  @Prop({default:false}) public disabled?: boolean;

  /**
   * 查询单位路径
   * 
   * @memberof AppMobOrgSelect
   */
  @Prop() public url!:string;

  /**
   * 监听表单数据变化
   * 
   * @memberof AppMobOrgSelect
   */
  @Watch('data',{immediate:true,deep:true})
  onDataChange(newVal: any, oldVal: any) {
    if(newVal){
      this.computedSelectedData();
      if(this.filter){
        let tempFilterValue:any = this.initBasicData();
        // filter值变化才去请求数据
        if(tempFilterValue && (this.copyFilterValue !== tempFilterValue)){
          this.loadTreeData(this.url.replace('${orgid}',tempFilterValue));
          this.copyFilterValue = tempFilterValue;
        }
      }
    }
  }

  /**
   * 选择值
   * 
   * @memberof AppMobOrgSelect
   */
  public selectTreeValue:any = "";

  /**
   * 树节点数据
   * 
   * @memberof AppMobOrgSelect
   */
  public nodesData:any = [];

  /**
     * 选中项集合
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    protected selects: any[] = [];

  /**
   * 备份过滤值
   * 
   * @memberof AppMobOrgSelect
   */
  public copyFilterValue:any;

  /**
   * vue生命周期
   * 
   * @memberof AppMobOrgSelect
   */
  public created(){
    if(!this.filter){
      this.loadTreeData(this.url);
    }
  }

   /**
     * 单选时选中名称
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    get selectName() {
        if(this.selects.length > 0) {
            return this.selects[0].label;
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
          this.viewClose(result);
        });
        
    }

    /**
     * 选择视图关闭
     *
     * @type {*}
     * @memberof AppMobGroupSelect
     */  
    public viewClose(result: any) {
        let treeValue:any[] = [];
        result.datas.forEach((item:any) => {
          treeValue.push(item.label);
        });
        let stringVal:string = treeValue.join(',');
        this.$emit("select-change",{name: this.fillMap.label, value:stringVal});
    }

  /**
   * 加载树数据
   * 
   * @memberof AppMobOrgSelect
   */
  public initBasicData(){
    // 计算出过滤值
    if(this.filter){
      if(this.data && this.data[this.filter]){
        return this.data[this.filter];
      }else if(this.context && this.context[this.filter]){
        return this.context[this.filter];
      }else{
        return null;
      }
    }
  }

  /**
   * 计算选中值
   * 
   * @memberof AppMobOrgSelect
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
   * 加载树数据
   * 
   * @memberof AppMobOrgSelect
   */
  public loadTreeData(requestUrl:string){
    if(this.filter){
      const result:any = this.$store.getters.getOrgData(this.filter);
      if(result){
        this.nodesData = result;
        return;
      }
    }
    this.$http.get(requestUrl).then((res:any) =>{
      if(!res.status && res.status !== 200){
        console.error((this.$t('components.AppMobOrgSelect.loadFail') as string));
        return;
      }
      this.nodesData = res.data;
      if(this.filter){
        this.$store.commit('addOrgData', { srfkey: this.filter, orgData: res.data });
      }
    })
  }

  /**
   * 树选择触发事件
   * 
   * @memberof AppMobOrgSelect
   */
  public treeSelectChange($event:any){
    // 多选
    if(this.multiple){
      if(!Object.is($event,'[]')){
        const tempValue:any = JSON.parse($event);
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            let tempResult:any ="";
            tempValue.forEach((value:any,index:number) =>{
              tempResult += index>0?`,${value[item]}`:`${value[item]}`;
            })
            setTimeout(() => {
              this.emitValue(this.fillMap[item],tempResult);
            }, 0);
          })
        }
      }else{
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            this.emitValue(this.fillMap[item],null);
          })
        }
      }
    }else{
      // 单选
      if(!Object.is($event,'[]')){
        const tempValue:any = JSON.parse($event)[0];
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            setTimeout(() => {
              this.emitValue(this.fillMap[item],tempValue[item]);
            }, 0);
          })
        }
      }else{
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            this.emitValue(this.fillMap[item],null);
          })
        }
      }
    }
  }

  /**
   * 抛值
   * 
   * @memberof AppMobOrgSelect
   */
  public emitValue(name:string,value:any){
    this.$emit('select-change',{name:name,value:value});
  }

  /**
   * 填充label
   * 
   * @memberof AppMobOrgSelect
   */
  public fillLabel(tempObject:any,valueItem:any,callback:any){
    if(!tempObject.label && tempObject.id && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
      let codeListService:CodeListServiceBase = new CodeListServiceBase();
      codeListService.getItems(this.tag).then((items:any) =>{
        if(items && items.length >0){
          let result:any = items.find((item:any) =>{
            return item.id === valueItem;
          })
          Object.assign(tempObject,{label:result.label});
        }
        callback(tempObject);
      }).catch((error:any) =>{
        console.log(error);
      })
    }
  }

}
</script>

<style lang="less">
@import "./app-mob-org-select.less";
</style>