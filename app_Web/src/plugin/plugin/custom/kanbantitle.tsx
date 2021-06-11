

import { IPSDEKanban, IPSUIActionGroupDetail } from "@ibiz/dynamic-model-api";
import Vue from 'vue';

/**
 * 看板标题颜色插件（自定义）插件类
 *
 * @export
 * @class Kanbantitle
 * @extends {Vue}
 */
export class Kanbantitle {

 renderCtrlItem(h: any, ctrlItemModel: IPSDEKanban, parentContainer: any, group: any) {
   			if (!this.isRender) {
          return;
        }
        const { groupWidth } = ctrlItemModel;
        return <div class="dataview-group-content" style={groupWidth ? { width: groupWidth + 'px' } : { flexGrow: 1 }}>
            <div class={["dataview-group-header", ctrlItemModel.getGroupPSSysCss()?.cssName]} style={this.getHeaderStyle(group)}>
                {parentContainer.getGroupText(group.name)}
                {
                    ctrlItemModel.getGroupPSUIActionGroup() ?
                        <poptip trigger="hover" content="content" placement="bottom-end" style="float: right;">
                            <icon type="md-more" />
                            <div slot="content" class="group-action">
                                {
                                    ctrlItemModel.getGroupPSUIActionGroup()?.getPSUIActionGroupDetails()?.map((action: IPSUIActionGroupDetail) => {
                                        return (
                                            <div class="group-action-item">
                                                <i-button long on-click={($event: any) => parentContainer.uiActionClick(action, $event, group)}>
                                                    {action.getPSUIAction()?.getPSSysImage()?.imagePath ? <img class="app-kanban-icon" src={action.getPSUIAction()?.getPSSysImage()?.imagePath} /> : null}
                                                    {action.getPSUIAction()?.getPSSysImage()?.cssClass ? <i class={[action.getPSUIAction()?.getPSSysImage()?.cssClass, "app-kanban-icon"]}></i> : null}
                                                    <span class="caption">{action.getPSUIAction()?.caption}</span>
                                                </i-button>
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        </poptip> : null
                }
            </div>
            <draggable list={group.items} group={ctrlItemModel.name} class="dataview-group-items" on-change={($event: any) => this.onDragStart($event,group,parentContainer)} on-end={()=>{this.onDragEnd(group,parentContainer)}}>
                {
                    group.items.map((item: any) => {
                        return (
                            <div class={['dataview-group-item', { 'is-select': item.isselected }]} on-click={() => parentContainer.handleClick(item)} on-dblclick={() => parentContainer.handleDblClick(item)}>
                                { parentContainer.controlInstance.getItemPSLayoutPanel ? parentContainer.renderItemPSLayoutPanel(item) : parentContainer.renderItemContent(item)}
                            </div>
                        )
                    })
                }
            </draggable>
        </div >
    }
	
	public isRender: boolean = true;

     /**
     * 拖拽结束
     * 
     */
    public onDragStart($event: any,group: any,parentContainer: any) {
      parentContainer.$forceUpdate();
      parentContainer.onDragChange($event, group.value);
    }

    /**
     * 拖拽结束
     * 
     */
    public onDragEnd(group: any,parentContainer: any){
        this.isRender = false;
        this.$nextTick(()=> {
          this.isRender = true;
          parentContainer.$forceUpdate();
        })
    }

    public getHeaderStyle(group: any) {
        let style = { 'text-align': 'center' };
        if (group.color) {
            Object.assign(style, {
                'border-color': group.color,
                'border-width': '3px'
            })
        }
        return style;
    }

}
