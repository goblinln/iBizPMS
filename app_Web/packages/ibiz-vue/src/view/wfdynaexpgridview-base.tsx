import { WFDynaExpGridViewEngine, Util, IBizWfDynaExpGridViewModel } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './mainview-base';

/**
 * 工作流动态导航表格视图基类
 *
 * @export
 * @class WfDynaExpGridViewBase
 * @extends {ExpViewBase}
 */
export class WfDynaExpGridViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public viewInstance!: IBizWfDynaExpGridViewModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof WfDynaExpGridViewBase
     */
    public engine: WFDynaExpGridViewEngine = new WFDynaExpGridViewEngine;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof WfDynaExpGridViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let grid = this.viewInstance.getControl('grid');
        let searchform = this.viewInstance.getControl('searchform');
        let engineOpts: any = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            grid: (this.$refs[grid.name] as any).ctrl,
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        if (searchform?.name) {
            engineOpts.searchform = ((this.$refs[searchform.name] as any).ctrl);
        }
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizWfDynaExpGridViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        this.viewRefData = await this.viewInstance.loadedAppViewRef();
        await super.viewModelInit();
        this.appEntityService = await new GlobalService().getService(this.viewInstance.appDataEntity?.codeName);
    }

    /**
     * 渲染视图工作流工具栏
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public renderToolBar(): any {
        return (<span slot='toolbar'>
            {
                this.linkModel.map((linkItem: any, index: number) => {
                    return <tooltip transfer={true} max-width={600} key="index">
                        <i-button disabled={linkItem.disabled} on-click={($event: any) => { this.dynamic_toolbar_click(linkItem, $event) }} loading={this.viewLoadingService.isLoading}>
                            <span class='caption'>{linkItem.sequenceFlowName}</span>
                        </i-button>
                        <div slot='content'>{linkItem.sequenceFlowName}</div>
                    </tooltip>
                })
            }
        </span>)
    }

    /**
     * 渲染视图主题内容
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public renderMainContent() {
        const { codeName } = this.viewInstance;
        return <split id={codeName.toLowerCase()} v-model={this.split} mode="horizontal">
            <div slot='left'>
                <el-tree ref="tree" data={this.wfStepModel} node-key="userTaskId" highlight-current={true} props={this.defaultProps} on-node-click={this.handleNodeClick.bind(this)} scopedSlots={{
                    default: ({ node, data }: any) => {
                        return <span class="custom-tree-node">
                            <span class="tree-node-label">{data.userTaskName}</span>
                            <span class="tree-node-count"><badge count={data.cnt}></badge></span>
                        </span>
                    }
                }}>
                </el-tree>
            </div>
            <div slot="right">
                <div class="content-container">
                    {this.renderSearchForm()}
                    {this.rednderGrid()}
                </div>
            </div>
        </split>
    }

    /**
     * 绘制搜索表单
     *
     * @returns
     * @memberof WfDynaExpGridViewBase
     */
    public renderSearchForm() {
        let searchform = this.viewInstance.getControl('searchform');
        if (!searchform) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(searchform);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: searchform.name, on: targetCtrlEvent });
    }

    /**
     * 绘制表格
     *
     * @returns
     * @memberof WfDynaExpGridViewBase
     */
    public rednderGrid() {
        let grid = this.viewInstance.getControl('grid');
        if (!grid) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(grid);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: grid.name, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof GridViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        if (controlInstance.controlType == 'SEARCHFORM') {
            Object.assign(targetCtrlParam.staticProps, {
                isExpandSearchForm: this.isExpandSearchForm
            });
        } else {
            Object.assign(targetCtrlParam.staticProps, {
                opendata: this.opendata,
                newdata: this.newdata,
            });
        }
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }


    /**
     * 分割宽度
     *
     * @type {number}
     * @memberof WfDynaExpGridViewBase
     */
    public split: number = 0.2;

    /**
     * 视图引用数据
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public viewRefData: any = {}

    /**
     * 树导航栏数据
     *
     * @type {any}
     * @memberof WfDynaExpGridViewBase
     */
    public wfStepModel: Array<any> = [];

    /**
     * 是否展开搜索表单
     *
     * @type {any}
     * @memberof  WfDynaExpGridViewBase
     */
    public isExpandSearchForm: boolean = true;

    /**
     * 是否单选
     *
     * @type {any}
     * @memberof  WfDynaExpGridViewBase
     */
    public isSingleSelect: boolean = false;

    /**
     * 工具栏模型数据
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public linkModel: Array<any> = [];

    /**
     * 左侧树的默认配置
     *
     * @type {any}
     * @memberof  WfDynaExpGridViewBase
     */
    public defaultProps: any = {
        children: 'children',
        label: 'userTaskName'
    };

    /**
     * 左侧树当前选中节点
     *
     * @type {any}
     * @memberof  WfDynaExpGridViewBase
     */
    public curSelectedNode: any;

    /**
     * 获取树导航栏数据
     *
     * @memberof  WfDynaExpGridViewBase
     */
    public getWFStepModel(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            this.appEntityService.WFGetWFStep().then((response: any) => {
                if (response && response.status === 200) {
                    this.wfStepModel = response.data;
                    if (this.wfStepModel && this.wfStepModel.length > 0) {
                        if (!this.curSelectedNode) {
                            this.curSelectedNode = this.wfStepModel[0];
                        } else {
                            let tempCopySelectedNode: any = Util.deepCopy(this.curSelectedNode);
                            this.curSelectedNode = this.wfStepModel.find((item: any) => {
                                return item.userTaskId === tempCopySelectedNode.userTaskId && item.processDefinitionKey === tempCopySelectedNode.processDefinitionKey;
                            })
                        }
                    }
                    if (this.curSelectedNode) {
                        Object.assign(this.viewparams, { 'userTaskId': this.curSelectedNode['userTaskId'], 'processDefinitionKey': this.curSelectedNode['processDefinitionKey'] });
                        this.setTreeNodeHighLight(this.curSelectedNode);
                        this.getWFLinkModel({ 'userTaskId': this.curSelectedNode['userTaskId'], 'processDefinitionKey': this.curSelectedNode['processDefinitionKey'] });
                    }
                    resolve(response.data);
                }
            }).catch((response: any) => {
                if (response && response.status) {
                    this.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                if (!response || !response.status || !response.data) {
                    this.$Notice.error({ title: '错误', desc: '系统异常' });
                    return;
                }
            });
        })
    }


    /**
     * 获取工具栏按钮
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public getWFLinkModel(data: any) {
        this.appEntityService.getWFLinks(JSON.parse(JSON.stringify(this.context)), data, true).then((response: any) => {
            if (response && response.status === 200) {
                this.linkModel = response.data;
                if (this.linkModel.length > 0) {
                    this.linkModel.forEach((item: any) => {
                        item.disabled = true;
                    })
                }
            }
        }).catch((response: any) => {
            if (response && response.status) {
                this.$Notice.error({ title: '错误', desc: response.message });
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: '错误', desc: '系统异常' });
                return;
            }
        });
    }

    /**
     * 工具栏点击事件
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public dynamic_toolbar_click(linkItem: any, $event: any) {
        let grid = this.viewInstance.getControl('grid');
        let datas: any[] = [];
        let xData: any = (this.$refs[grid.name] as any).ctrl;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        const submitBatchData: Function = (submitdata: any, linkItem: any) => {
            xData.submitbatch(submitdata, linkItem).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                this.engine.load();
                if (this.viewdata) {
                    this.$emit('viewdataschange', [{ ..._data }]);
                }
            });
        }
        if (linkItem && linkItem.sequenceflowview) {
            const targetView:any = this.viewRefData.find((item:any) =>{
                return item.name === `WFACTION@${linkItem.sequenceflowview}`;
            })
            if(targetView){
                let tempContext: any = Util.deepCopy(this.context);
                let tempViewParam: any = { actionView: linkItem.sequenceflowview, actionForm: linkItem.sequenceflowform };
                const appmodal = this.$appmodal.openModal({ viewname: Util.srfFilePath2(targetView.codeName), title: targetView.title, height: targetView.height, width: targetView.width }, tempContext, tempViewParam);
                appmodal.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    let tempSubmitData: any = Util.deepCopy(datas);
                    tempSubmitData.forEach((element: any) => {
                        element.viewparams = result.datas && result.datas[0];
                    });
                    submitBatchData(tempSubmitData, linkItem);
                });
            }
        } else {
            submitBatchData(datas, linkItem);
        }
    }

    /**
     * 左侧树选中节点
     *
     * @type {any}
     * @memberof WfDynaExpGridViewBase
     */
    public handleNodeClick(data: any) {
        let searchform = this.viewInstance.getControl('searchform');
        this.curSelectedNode = data;
        this.setTreeNodeHighLight(this.curSelectedNode);
        Object.assign(this.viewparams, { 'userTaskId': data['userTaskId'], 'processDefinitionKey': data['processDefinitionKey'] });
        this.getWFLinkModel({ 'userTaskId': this.curSelectedNode['userTaskId'], 'processDefinitionKey': this.curSelectedNode['processDefinitionKey'] });
        (this.$refs[searchform.name] as any).ctrl.onSearch();
    }

    /**
     * 设置选中高亮
     *
     * @param {*} data
     * @memberof WfDynaExpGridViewBase
     */
    public setTreeNodeHighLight(data: any): void {
        this.$nextTick(() => {
            const tree: any = this.$refs.tree;
            if (tree) {
                tree.setCurrentKey(data.userTaskId);
            }
        })
    }

}