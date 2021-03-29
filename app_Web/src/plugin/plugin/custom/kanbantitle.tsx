



/**
 * 看板标题颜色插件（自定义）插件类
 *
 * @export
 * @class Kanbantitle
 * @extends {Vue}
 */
export class Kanbantitle {

    renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, group: any) {
        const { groupWidth, getGroupPSSysCss, getGroupPSUIActionGroup } = ctrlItemModel.controlModelData;
        return <div class="dataview-group-content" style={groupWidth ? { width: groupWidth + 'px' } : { flexGrow: 1 }}>
            <div class={["dataview-group-header", getGroupPSSysCss?.cssName]} style={this.getHeaderStyle(group.name, parentContainer)}>
                {parentContainer.getGroupText(group.name)}
                {
                    getGroupPSUIActionGroup ?
                        <poptip trigger="hover" content="content" placement="bottom-end" style="float: right;">
                            <icon type="md-more" />
                            <div slot="content" class="group-action">
                                {
                                    getGroupPSUIActionGroup.getPSUIActions.map((action: any) => {
                                        return (
                                            <div class="group-action-item">
                                                <i-button long on-click={($event: any) => parentContainer.uiActionClick(action, $event, group)}>
                                                    {action.getPSUIAction.getPSSysImage?.imagePath ? <img class="app-kanban-icon" src={action.getPSUIAction.getPSSysImage.imagePath} /> : null}
                                                    {action.getPSUIAction.getPSSysImage?.cssClass ? <i class={[action.getPSUIAction.getPSSysImage.cssClass, "app-kanban-icon"]}></i> : null}
                                                    <span class="caption">{action.getPSUIAction.caption}</span>
                                                </i-button>
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        </poptip> : null
                }
            </div>
            <draggable list={group.items} group={ctrlItemModel.controlModelData.name} class="dataview-group-items" on-change={($event: any) => parentContainer.onDragChange($event, group.value)}>
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

    public getHeaderStyle(name: string, _this: any) {
        let style = { 'text-align': 'center' };
        if (Object.is(_this.groupMode, 'CODELIST') && _this.groupCodelist) {
            let codelist: any = _this.$store.getters.getCodeList(_this.groupCodelist.tag);
            if (codelist) {
                let item = codelist.items.find((item: any) => Object.is(item.value, name));
                if (item) {
                    Object.assign(style, {
                        'border-color': item.color,
                        'border-width': '3px'
                    })
                }
            }
        }
        return style;
    }

}
