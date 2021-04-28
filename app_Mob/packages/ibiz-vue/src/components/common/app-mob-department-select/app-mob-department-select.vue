<template>
  <div class="app-mob-department-select">
    <div class="main">
      <div class="checkField">
        <ion-input :disabled="disabled" :value="curValue" readonly @click="openModal"></ion-input>
      </div>
      <div class="icon">
          <app-mob-icon  name="search-outline" v-show="!curValue" @onClick="openModal"></app-mob-icon>
          <app-mob-icon  v-show="curValue" class="delete-value " name="close-circle-outline" @onClick="onClear"></app-mob-icon>
      </div>
    </div>
    
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch, Prop, Model } from 'vue-property-decorator';
import { CodeListServiceBase } from "ibiz-core";
@Component({
})
export default class AppMobDepartmentSelect extends Vue {

    /**
     * 接口url
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public url?: any;

    /**
     * 代码表标识
     * 
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public tag?:string;

    /**
     * 代码表类型
     * 
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public codelistType?:string;

    /**
     * 过滤项
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public filter?: any;

    /**
     * 过滤项
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public fillMap?: any;

    /**
     * 是否多选
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop({default:false}) public multiple?: any;

    /**
     * 是否禁用
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public data!: any;

    /**
     * 上下文变量
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    @Prop() public context!: any;

    /**
     * 选中数值
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    public selectTreeValue:any = "";

    /**
     * 选中回填
     * 
     * @type {*}
     */
    public curValue: any = '';

    /**
     * 树节点数据
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    public Nodesdata: any[] = [];

    /**
     * 当前树节点数据的url
     *
     * @type {*}
     * @memberof AppMobDepartmentSelect
     */
    public oldurl: any;

    /**
     * 获取节点数据
     *
     * @memberof AppMobDepartmentSelect
     */
    public handleFilter(){
      if(this.filter){
          if(this.data && this.data[this.filter]){
            return this.data[this.filter];
          }else if(this.context && this.context[this.filter]){
            return this.context[this.filter];
          }
      }else{
          return this.context.srforgid;
      }
    }

    /**
     * 获取节点数据
     *
     * @memberof AppMobDepartmentSelect
     */
    public searchNodesData(){
      // 处理过滤参数，生成url
      let param = this.handleFilter();
      let _url = this.url.replace('${orgid}',param)
      if(this.oldurl === _url){
          return;
      }
      this.oldurl = _url;
      // 缓存机制
      const result:any = this.$store.getters.getDepData(_url);
      if(result){
        this.Nodesdata = result;
        return;
      }
      this.$http.get(_url).then((response: any) => {
          this.Nodesdata = response.data;
          this.$store.commit('addDepData', { srfkey: _url, depData: response.data });
      }).catch((response: any) => {
          if (!response || !response.status || !response.data) {
            //   this.$Notice.error({ title: (this.$t('app.commonWords.error') as string), desc: (this.$t('app.commonWords.sysException') as string) });
              return;
          }
      });
    }

    /**
     * 值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobDepartmentSelect
     */
    @Watch('data',{immediate:true,deep:true})
    public onValueChange(newVal: any, oldVal: any) {
        if(newVal){
          this.$nextTick(()=>{
            this.searchNodesData();
          });
        }
    }
  
  /**
   * 打开模态
   * 
   * @memberof AppMobDepartmentSelect
   */
  public async openModal(){
    const view: any = {
        viewname: 'app-tree',
        title: (this.$t('components.AppMobGroupSelect.groupSelect') as string)
    };
    const context: any = JSON.parse(JSON.stringify(this.context));
    const param: any = {};
    Object.assign(param, {
      selectTreeValue: this.selectTreeValue,
      multiple: this.multiple,
      nodesData: this.Nodesdata
    });
    let result: any = await this.$appmodal.openModal(view, context, param);
    this.closeModal(result.datas);
  }

  /**
     * 模态关闭
     *
     * @memberof AppMobDepartmentSelect
     */  
    public closeModal(datas: any) {
       let checkValue: any[] = [];
       datas.forEach((item: any)=>{
         checkValue.push(item.label);
       })
       this.selectTreeValue = JSON.stringify(datas);
       this.curValue = checkValue == []? '' : checkValue.join(',');
       if(this.fillMap && Object.keys(this.fillMap).length > 0){
            Object.keys(this.fillMap).forEach((attribute:string) => {
                let _name = this.fillMap[attribute];
                let values = datas.map((item:any) => item[attribute]);
                let _value = datas === "[]" ? null : values.join(",");
                setTimeout(() => {
                  this.$emit('select-change',{name: this.fillMap[attribute], value: _value});
                },0);
            });
        }
    }

    /**
     * 清除
     * 
     * @memberof AppMobDepartmentSelect
     */
    public onClear(){
      this.curValue = '';
      this.selectTreeValue = '';
      if(this.fillMap && Object.keys(this.fillMap).length > 0){
            Object.keys(this.fillMap).forEach((attribute:string) => {
                let _name = this.fillMap[attribute];
                setTimeout(() => {
                  this.$emit('select-change',{name: this.fillMap[attribute], value:null});
                },0);
            });
        }
    }
}
</script>

<style lang='less'>
@import './app-mob-department-select.less';
</style>