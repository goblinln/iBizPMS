import { Prop, Watch, Emit, Component } from 'vue-property-decorator';
import { ModelTool, Util } from 'ibiz-core';
import { MobTreeControlBase } from '../../../widgets';
import { IPSAppDETreeView, IPSDETreeNode, IPSDEContextMenu } from '@ibiz/dynamic-model-api';

/**
 * 树视图部件基类
 *
 * @export
 * @class AppMobTreeBase
 * @extends {MobTreeControlBase}
 */
@Component({})
export class AppMobTreeBase extends MobTreeControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobTreeBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobTreeBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobTreeBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobTreeBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppMobTreeBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobTreeBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制上下文菜单
     *
     * @memberof AppMobTreeBase
     */
    public renderContextMenu(){
        if(!Util.isEmpty(this.activeNode)){
            let treeNodes = this.controlInstance.getPSDETreeNodes() || [];
            let treeNode = treeNodes.find((node: IPSDETreeNode) => this.activeNode == node.nodeType) as IPSDETreeNode;
            let contextMenu = ModelTool.findPSControlByName(treeNode?.getPSDEContextMenu()?.name as string,this.controlInstance.getPSControls());
            if(contextMenu){
                let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(contextMenu);
                targetCtrlParam.dynamicProps.contextMenuActionModel = this.copyActionModel;
                return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: contextMenu.name, on: targetCtrlEvent ,key:contextMenu.codeName+JSON.stringify(this.copyActionModel)});
            }
        }
    }

    /**
     * 绘制普通树
     *
     * @memberof AppMobTreeBase
     */
    public renderDefaultTree(){
        return this.valueNodes.map((item: any)=>{
            return <v-touch on-press={()=>this.node_touch(item)} key={item.srfkey}>
                <ion-item>
                    <ion-label>{item.text}</ion-label>
                </ion-item>
            </v-touch>
        })
    }

    /**
     * 绘制多选树
     *
     * @memberof AppMobTreeBase
     */    
    public renderMultipleTree(){
        return <ion-list>
        {this.valueNodes.map((item:any)=>{
            return <ion-item>
                <ion-checkbox color="secondary"  ref={item.srfkey+'checkbox'}  checked={item.selected} value={item.srfkey} slot="end" on-ionChange={this.onChecked.bind(this)}></ion-checkbox>
                <ion-label class="tree_item_label">
                    {item.strIcon != 'default_text' 
                    ? (item.strIcon ? <img class="tree_item_img" src={item.strIcon} /> : <div class="tree_item_index_text">{item.text.substring(0,1)}</div>)
                    : null
                    }
                    {item.text}
                </ion-label>
            </ion-item>
        })}
        </ion-list>
    }

    /**
     * 绘制单选树
     *
     * @memberof AppMobTreeBase
     */       
    public renderSingleTree(){
        return <ion-radio-group value={this.selectedValue}>
                  {this.valueNodes.map((item:any)=>{
                  return <ion-item on-click={()=>this.onSimpleSelChange(item)}>
                      <ion-label class="tree_item_label">
                          {item.strIcon != 'default_text' 
                          ? (item.strIcon ? <img class="tree_item_img" src={item.strIcon} /> : <div class="tree_item_index_text">{item.text.substring(0,1)}</div>)
                          : null
                          }
                          {item.text}
                      </ion-label>
                      <ion-radio slot="end" checked={item.selected} value={item.srfkey}></ion-radio>
                  </ion-item>
              })}
        </ion-radio-group>
    }

    /**
     * 根据视图类型绘制树
     *
     * @memberof AppMobTreeBase
     */
    public renderByViewType(){
        const { viewType } = this.controlInstance.getParentPSModelObject() as IPSAppDETreeView;
        if(viewType == "DEMOBTREEVIEW"){
            return this.renderDefaultTree();
        }else if(viewType == 'DEMOBPICKUPTREEVIEW' && !this.isSingleSelect){
            return this.renderMultipleTree();
        }else if(viewType == 'DEMOBPICKUPTREEVIEW' && this.isSingleSelect){
            return this.renderSingleTree();
        }
    }

    /**
     * 绘制树内容
     *
     * @returns {*}
     * @memberof AppMobTreeBase
     */
    public renderTreeContent():any{
        return [<div class="treeNav">
                  {this.treeNav.map((item: any,index: number)=>{
                      let className = index+1 < this.treeNav.length? 'treeNav-active':'';
                      return [
                          <span  key={item.id} class={className} on-click={() => this.nav_click(item) }>{item.text}</span>,
                          index+1 < this.treeNav.length && <span class="tree-span" key={item.id+'span'} >{'>'}</span>
                      ]
                  })}
              </div>,
              this.valueNodes.length > 0 && <div class="tree-partition" ></div>,
              <ion-list>
                  {this.rootNodes.map((item: any, index: number)=>{
                      return <v-touch on-press={()=>this.node_touch(item)} key={index}>
                          <ion-item on-click={()=> this.click_node(item)}>
                              <ion-label>{item.text}</ion-label>
                              <app-mob-icon class="tree-icon" position="end" name="chevron-forward-outline"></app-mob-icon>
                          </ion-item>
                      </v-touch>
                  })}
              </ion-list>,
              this.rootNodes.length > 0 && <div class="tree-partition tree-partition-bigger"></div> ,
              this.renderByViewType()]
    }

    /**
     * 绘制树视图
     *
     * @returns {*}
     * @memberof AppMobTreeBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let treeClassName = {
            'app-mob-treeview': true,
            ...this.renderOptions.controlClassNames,
        };
        return (
            <div class={treeClassName} >
                <app-mob-context-menu 
                    ref="contextmenu" 
                    scopedSlots={{
                        content: () => {
                            return this.renderContextMenu()
                        }
                    }}>
                </app-mob-context-menu>
                {this.renderTreeContent()}
            </div>
        )
    }
}