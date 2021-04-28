import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobPanelControlBase } from '../../../widgets';
import { IPSCodeListEditor, IPSSysPanelField } from '@ibiz/dynamic-model-api';

/**
 * 多编辑面板部件基类
 *
 * @export
 * @class AppMobPanelBase
 * @extends {PanelControlBase}
 */
export class AppMobPanelBase extends MobPanelControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppMobPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof PanelControlBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobPanelBase
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
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobPanelBase
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
     * @memberof AppMobPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDefaultTree
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * FLEX布局时类名映射
     *
     * @memberof AppMobPanelBase
     */
    public classObj: any = {
        BUTTON: 'app-layoutpanel-button',
        CONTAINER: 'app-layoutpanel-container',
        FIELD: 'app-layoutpanel-field',
        RAWITEM: 'app-layoutpanel-rowitem',
    };

    /**
     * 获取FLEX布局时类名
     * @param item
     */
    public renderDetailClass(item: any) {
        // 映射类名
        let detailClass: any = this.classObj[item.itemType];
        if (item.getPSSysCss()) {
            detailClass += ` ${item.getPSSysCss()?.cssName}`;
        }
        return detailClass;
    }

    /**
     * 获取栅格布局的props
     *
     * @param {*} parent 父
     * @param {*} child 子
     * @returns {*}
     * @memberof AppMobPanelBase
     */
    public getGridLayoutProps(parent: any, child: any): any {
        let layout = parent?.getPSLayout()?.layout;
        let { colXS, colSM, colMD, colLG, colXSOffset, colSMOffset, colMDOffset, colLGOffset } = child?.getPSLayoutPos();
        // 设置初始值
        colXS = !colXS || colXS == -1 ? 24 : colXS;
        colSM = !colSM || colSM == -1 ? 24 : colSM;
        colMD = !colMD || colMD == -1 ? 24 : colMD;
        colLG = !colLG || colLG == -1 ? 24 : colLG;
        colXSOffset = !colXSOffset || colXSOffset == -1 ? 0 : colXSOffset;
        colSMOffset = !colSMOffset || colSMOffset == -1 ? 0 : colSMOffset;
        colMDOffset = !colMDOffset || colMDOffset == -1 ? 0 : colMDOffset;
        colLGOffset = !colLGOffset || colLGOffset == -1 ? 0 : colLGOffset;
        if (layout == 'TABLE_12COL') {
            // 重新计算12列的栅格数值
            colXS = Math.min(colXS * 2, 24);
            colSM = Math.min(colSM * 2, 24);
            colMD = Math.min(colMD * 2, 24);
            colLG = Math.min(colXS * 2, 24);
            // 重新计算12列的栅格偏移
            let sign = (num: number) => (num == 0 ? 0 : num / Math.abs(num));
            colXSOffset = sign(colXSOffset) * Math.min(colXSOffset * 2, 24);
            colSMOffset = sign(colSMOffset) * Math.min(colSMOffset * 2, 24);
            colMDOffset = sign(colMDOffset) * Math.min(colMDOffset * 2, 24);
            colLGOffset = sign(colLGOffset) * Math.min(colLGOffset * 2, 24);
        }
        return {
            lg: colLG,
            span: colMD,
            sm: colSM,
            offset: colMDOffset,
        };

    }

    /**
     * 绘制顶级面板成员集合
     *
     * @memberof AppMobPanelBase
     */

    public renderRootPSPanelItems(controlInstance: any) {
        return controlInstance.getRootPSPanelItems()?.map((container: any, index: number) => {
            return this.renderByDetailType(container, index);
        });
    }

    /**
     * 绘制面板成员集合
     *
     * @memberof AppMobPanelBase
     */

    public renderPanelItems(container: any) {
        if (!container.getPSPanelItems() || container.getPSPanelItems().length == 0) {
            return null;
        }
        let layoutMode = container.getPSLayout()?.layout;
        // FLEX布局
        if (layoutMode == 'FLEX') {
            let cssStyle: string = 'width: 100%; height: 100%; overflow: auto; display: flex;';
            cssStyle += container.getPSLayout().dir ? `flex-direction: ${container.getPSLayout().dir};` : '';
            cssStyle += container.getPSLayout().align ? `justify-content: ${container.getPSLayout().align};` : '';
            cssStyle += container.getPSLayout().vAlign ? `align-items: ${container.getPSLayout().vAlign};` : '';
            return (
                <div style={cssStyle}>
                    {container.getPSPanelItems()?.map((item: any, index: number) => {
                        // 子样式
                        let detailStyle: any = {
                            'display': this.detailsModel[item.name]?.visible ? false : 'none',
                        };
                        if (item.getPSLayoutPos) {
                            let { grow, height, width } = item.getPSLayoutPos();
                            detailStyle.flexGrow = grow != -1 ? grow : 0;
                            detailStyle.height = height > 0 ? height + 'px' : '';
                            detailStyle.width = width > 0 ? width + 'px' : '';
                        }
                        // 自定义类名
                        const controlClassName = this.renderDetailClass(item);
                        return (
                            <div style={detailStyle} class={controlClassName}>
                                {this.renderByDetailType(item, index)}
                            </div>
                        );
                    })}
                </div>
            );
        } else {
            // 栅格布局
            return (
                <van-row style="height:100%;">
                    {container.getPSPanelItems()?.map((item: any, index: number) => {
                        //子样式
                        let detailStyle: any = {
                            'display': this.detailsModel[item.name]?.visible ? false : 'none',
                        };
                        if (item.getPSLayoutPos()) {
                            let { height, width } = item.getPSLayoutPos();
                            detailStyle.height = height > 0 ? height + 'px' : '';
                            detailStyle.width = width > 0 ? width + 'px' : '';
                        }
                        // 栅格布局
                        let attrs = this.getGridLayoutProps(container, item);
                        // 自定义类名
                        const controlClassName = this.renderDetailClass(item);
                        return (
                            <van-col {...{ props: attrs }} style={detailStyle} class={controlClassName}>
                                {this.renderByDetailType(item, index)}
                            </van-col>
                        );
                    })}
                </van-row>
            );
        }
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @param {number} index
     * @memberof AppMobPanelBase
     */
    public renderByDetailType(modelJson: any, index: number) {
        switch (modelJson.itemType) {
            case 'CONTAINER':
                return this.renderContainer(modelJson, index);
            case 'BUTTON':
                return this.renderButton(modelJson, index);
            case 'FIELD':
                return this.renderField(modelJson, index);
            case 'RAWITEM':
                return this.renderRawitem(modelJson, index);
        }
    }

    /**
     * 绘制面板Container
     *
     * @memberof AppMobPanelBase
     */

    public renderContainer(modelJson: any, index: number) {

        let layoutMode = modelJson?.getPSLayout()?.layout;
        let containerClass = "app-layoutpanel-container"
        if (modelJson.getPSSysCss()) {
            containerClass += ` ${modelJson?.getPSSysCss()?.cssName}`;
        }
        if (layoutMode == 'FLEX') {
            return <div class={containerClass}>
                {this.renderPanelItems(modelJson)}
            </div>
        } else {
            return <van-col class={containerClass}>
                {this.renderPanelItems(modelJson)}
            </van-col>
        }
    }

    /**
     * 绘制面板Button
     *
     * @memberof AppMobPanelBase
     */

    public renderButton(modelJson: any, index: number) {
        let {
            caption,
            showCaption,
            name,
            height,
        } = modelJson;
        const buttonStyle = {
            height: height && height > 0 ? height + 'px' : false,
        };
        const icon = modelJson.getPSSysImage();
        const uiAction = modelJson.getPSUIAction();
        const labelPSSysCss = modelJson?.getLabelPSSysCss?.();
        return (
            <app-mob-button
                style={buttonStyle}
                text={showCaption ? caption : ''}
                iconName={
                    icon?.cssClass
                        ? icon.cssClass
                        : uiAction?.getPSSysImage()?.cssClass
                            ? uiAction.getPSSysImage().cssClass
                            : null
                }
                disabled={this.detailsModel[name]?.disabled}
                class={labelPSSysCss?.cssName}
                on-click={($event: any) => {
                    this.buttonClick(this.controlInstance.name, { tag: name }, $event);
                }}
            ></app-mob-button>
        );
    }

    /**
     * 绘制面板Field
     *
     * @memberof AppMobPanelBase
     */

    public renderField(modelJson: any, index: number) {
        let { caption } = modelJson;
        const editor: any = modelJson.getPSEditor();
        let labelClass = {
            'item-field-label': true,
            [modelJson.getLabelPSSysCss?.cssName]: true
        }

        return (
            <div class="item-field ">
                {caption ? <ion-label class={labelClass} >{caption}</ion-label> : null}
                {editor && (
                    <app-default-editor
                        value={this.data[editor.name]}
                        editorInstance={editor}
                        contextData={this.data}
                        containerCtrl={this.controlInstance}
                        parentItem={modelJson}
                        context={this.context}
                        viewparams={this.viewparams}
                        on-change={(value: any) => {
                            this.onPanelItemValueChange(this.data, value);
                        }}
                    />
                )}
            </div>
        )
    }
    /**
     * 绘制面板Rawitem
     *
     * @memberof AppMobPanelBase
     */

    public renderRawitem(modelJson: any, index: number) {
        let {
            contentType,
            htmlContent,
            getPSSysImage,
            rawContent,
        } = modelJson;
        if (rawContent) {
            const items = rawContent.match(/\{{(.+?)\}}/g);
            if (items) {
                items.forEach((item: string) => {
                    rawContent = this.$t(rawContent.replace(/\{{(.+?)\}}/, item.substring(2, item.length - 2)));
                });
            }
        }
        const tempNode = this.$createElement('div', {
            domProps: {
                innerHTML: rawContent,
            },
        });
        switch (contentType) {
            case 'HTML':
                return htmlContent
            case 'RAW':
                return tempNode
            case 'IMAGE':
                return <img src={getPSSysImage?.imagePath} ></img>
        }

    }

    /**
     * 部件模型数据初始化
     *
     * @memberof AppMobPanelBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (this.controlInstance.getRootPSPanelItems() && (this.controlInstance.getRootPSPanelItems() as any)?.length > 0) {
            for (let i = 0; i < (this.controlInstance.getRootPSPanelItems() as any).length; i++) {
                const item = (this.controlInstance.getRootPSPanelItems() as any)[i];
                const getPSPanelItems = item.getPSPanelItems();
                if (getPSPanelItems) {
                    for (let i = 0; i < getPSPanelItems.length; i++) {
                        const panelItems = getPSPanelItems[i]?.getPSPanelItems?.();
                        if (Array.isArray(panelItems) && panelItems.length > 0) {
                            for (let i = 0; i < panelItems.length; i++) {
                                const item: IPSSysPanelField = panelItems[i];
                                //获取面板项代码表
                                if ((item?.getPSEditor?.() as IPSCodeListEditor)?.getPSAppCodeList?.()) {
                                    const codeList = (item.getPSEditor() as IPSCodeListEditor)?.getPSAppCodeList();
                                    if (codeList) {
                                        if (codeList.isFill) {
                                            await codeList.fill();
                                            // item.codelist = tempCodeList;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 绘制面板
     *
     * @returns {*}
     * @memberof AppMobPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }

        let controlClassNames = {
            'app-layoutpanel': true,
            ...this.renderOptions.controlClassNames,
        };
        return (
            <div class="view-container" style="height:100%;width:100%">
                <van-row class={controlClassNames}>
                    {this.renderRootPSPanelItems(this.controlInstance)}
                </van-row>
            </div>
        );
    }
}
