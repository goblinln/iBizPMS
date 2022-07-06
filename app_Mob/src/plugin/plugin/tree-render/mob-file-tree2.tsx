
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobTreeBase } from 'ibiz-vue'; 	


/**
 * 移动端树附件插件插件类
 *
 * @export
 * @class MobFileTree2
 * @class MobFileTree2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobFileTree2 extends AppMobTreeBase {

      /**
   * 绘制树视图
   *
   * @returns {*}
   * @memberof MobFileTree
   */
  public renderTreeContent(){
    if (!this.controlIsLoaded) {
      return;
    }
    return <app-tree-word 
          treeNav={this.treeNav} 
          valueNodes={this.valueNodes} 
          rootNodes={this.rootNodes}
          on-nav_click={this.nav_click.bind(this)} 
          on-node_touch={this.node_touch.bind(this)} 
          on-click_node={this.click_node.bind(this)} />
  }

}

