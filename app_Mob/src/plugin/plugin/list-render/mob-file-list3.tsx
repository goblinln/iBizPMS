
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * 移动端列表文件插件插件类
 *
 * @export
 * @class MobFileList3
 * @class MobFileList3
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobFileList3 extends AppMobMDCtrlBase {

    /**
     * 绘制
     * 
     * @memberof MobFileList
     */
       public render(): any{
        if(!this.controlIsLoaded){
          return null;
        }
        return <app-mob-file-list items={this.items} on-delete={this.remove.bind(this)}></app-mob-file-list>
      }

}

