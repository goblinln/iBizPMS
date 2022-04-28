import { IPSAppDEField, IPSAppDEWFDynaExpGridView, IPSDEDataView, IPSDEGrid, IPSDESearchForm } from '@ibiz/dynamic-model-api';
import { WFDynaExpGridViewEngine, Util, ModelTool, throttle, WFDynaExpGridInterface } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppGlobalService } from '../app-service/logic-service/app-global-action-service';
import { MainViewBase } from './mainview-base';

/**
 * 工作流动态导航表格视图基类
 *
 * @export
 * @class WfDynaExpGridViewBase
 * @extends {MainViewBase}
 * @implements {WFDynaExpGridInterface}
 */
export class WfDynaExpGridViewBase extends MainViewBase implements WFDynaExpGridInterface {

    /**
     * 视图实例
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public viewInstance!: IPSAppDEWFDynaExpGridView;

    /**
     * 表格实例
     *
     * @memberof WfDynaExpGridViewBase
     */
    public gridInstance!: IPSDEGrid;

    /**
     * 表格实例
     *
     * @memberof WfDynaExpGridViewBase
     */
    public searchFormInstance!: IPSDESearchForm;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof WfDynaExpGridViewBase
     */
    public engine: WFDynaExpGridViewEngine = new WFDynaExpGridViewEngine();

    /**
     * 可搜索字段名称
     * 
     * @type {(string)}
     * @memberof WfDynaExpGridViewBase
     */
     public placeholder: string = "";

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
    public wfStepModel: Array<any> = [{ userTaskName:'我的处理',userTaskId: "all", children: [] }];

    /**
     * 快速搜索值
     *
     * @type {string}
     * @memberof WfDynaExpGridViewBase
     */
     public query: string = '';

    /**
     * 是否展开搜索表单（接收参数）
     * 
     * @type {boolean}
     * @memberof WfDynaExpGridViewBase
     */
    public expandSearchForm: boolean = false;

    /**
     * 是否展开搜索表单
     *
     * @type {any}
     * @memberof  WfDynaExpGridViewBase
     */
    public isExpandSearchForm: boolean = false;

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
     * 引擎初始化
     *
     * @public
     * @memberof WfDynaExpGridViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let engineOpts: any = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            grid: (this.$refs[this.gridInstance.name] as any).ctrl,
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        if (this.searchFormInstance?.name) {
            engineOpts.searchform = ((this.$refs[this.searchFormInstance.name] as any).ctrl);
        }
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.gridInstance = ModelTool.findPSControlByName('grid', this.viewInstance.getPSControls()) as IPSDEGrid;
        this.searchFormInstance = ModelTool.findPSControlByName('searchform', this.viewInstance.getPSControls()) as IPSDESearchForm;
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName, this.context);
        this.viewRefData = await ModelTool.loadedAppViewRef(this.viewInstance);
    }

    /**
     * @description 视图初始化
     * @memberof WfDynaExpGridViewBase
     */
    public viewInit(){
      super.viewInit();
      // 初始化属性值
      this.query = '';
      this.expandSearchForm = this.viewInstance?.expandSearchForm ? true : false;
      this.initQuickSearchPlaceholder();
    }

    
    /**
     * 视图挂载
     * 
     * @memberof WfDynaExpGridViewBase
     */
     public viewMounted() {
        super.viewMounted();
        this.handleDefaultExpandSearchForm()
    }

    /**
     * 处理默认展开搜索表单
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public handleDefaultExpandSearchForm() {
        //  默认展开搜索表单
        if (this.expandSearchForm) {
            //  搜索表单以弹框展示
            if (this.viewInstance?.viewStyle == "DEFAULT" && this.viewInstance?.enableQuickSearch) {
                this.$nextTick(() => {
                    const element = document.querySelector('button.filter');
                    debugger
                    if (element) {
                        (element as HTMLElement).click();
                    }
                })
            } else {
                this.isExpandSearchForm = this.expandSearchForm;
            }
        }
    }
    
    /**
     *  初始化快速搜索栏空白填充内容
     *
     * @memberof WfDynaExpGridViewBase
     */
     public initQuickSearchPlaceholder() {
        const quickSearchFields: Array<IPSAppDEField> = (this.viewInstance as any).getPSAppDataEntity()?.getQuickSearchPSAppDEFields() || [];
        if (quickSearchFields.length > 0) {
            quickSearchFields.forEach((field: IPSAppDEField, index: number) => {
                const _field: IPSAppDEField | null | undefined = (this.viewInstance as any).getPSAppDataEntity()?.findPSAppDEField(field.codeName);
                if (_field) {
                    this.placeholder += (this.$tl(_field.getLNPSLanguageRes()?.lanResTag, _field.logicName) + (index === quickSearchFields.length-1 ? '' : ', '));
                }
            })
        }
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
                    return <tooltip transfer={true} max-width={600} key={linkItem.codeName}>
                        <i-button disabled={linkItem.disabled} on-click={($event: any) => { throttle(this.dynamic_toolbar_click, [linkItem, $event], this) }} loading={this.viewLoadingService.isLoading}>
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
                <el-tree ref="tree" data={this.wfStepModel} default-expanded-keys={["all"]} node-key="userTaskId" highlight-current={true} props={this.defaultProps} on-node-click={(...params: any[]) => throttle(this.handleNodeClick, params, this)} scopedSlots={{
                    default: ({ node, data }: any) => {
                        let iconClass = "tree-node-icon";
                        iconClass += data?.children?.length > 0 ? " fa fa-folder-open" : " fa fa-sitemap";
                        return <span class="custom-tree-node">
                            <i class={iconClass} />
                            <span class="tree-node-label">{data.userTaskName}</span>
                            <span class="tree-node-count"><badge count={data.cnt}></badge></span>
                        </span>
                    }
                }}>
                </el-tree>
            </div>
            <div slot="right">
                <div class="content-container">
                    {!(this.viewInstance?.viewStyle == "DEFAULT" && this.viewInstance?.enableQuickSearch) ? this.renderSearchForm() : null}
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
        if (!this.searchFormInstance) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.searchFormInstance);
        if (this.viewInstance?.viewStyle == "DEFAULT" && this.viewInstance?.enableQuickSearch) {
          return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.searchFormInstance?.name, on: targetCtrlEvent });
        } else {
            return this.$createElement(targetCtrlName, { slot: 'searchForm', props: targetCtrlParam, ref: this.searchFormInstance?.name, on: targetCtrlEvent });
        }
    }

    /**
     * 绘制表格
     *
     * @returns
     * @memberof WfDynaExpGridViewBase
     */
    public rednderGrid() {
        if (!this.gridInstance) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.gridInstance);
        if(!targetCtrlParam.dynamicProps.viewparams.hasOwnProperty('srfwf')){
          targetCtrlParam.dynamicProps.viewparams.srfwf = 'todo';
        }
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.gridInstance.name, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof WfDynaExpGridViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        if (controlInstance.controlType == 'SEARCHFORM') {
            Object.assign(targetCtrlParam.dynamicProps, {
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
     * 获取树导航栏数据
     *
     * @memberof  WfDynaExpGridViewBase
     */
    public getWFStepModel(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            this.appEntityService.WFGetWFStep(Util.deepCopy(this.context),Util.deepCopy(this.viewparams)).then((response: any) => {
                if (response && response.status === 200) {
                    this.wfStepModel[0].children = [...response.data];
                    if (!this.curSelectedNode) {
                        this.curSelectedNode = this.wfStepModel[0];
                    } else {
                        let tempCopySelectedNode: any = Util.deepCopy(this.curSelectedNode);
                        this.curSelectedNode = this.wfStepModel[0].children.find((item: any) => {
                            return item.userTaskId === tempCopySelectedNode.userTaskId && item.processDefinitionKey === tempCopySelectedNode.processDefinitionKey;
                        })
                    }
                    if (this.curSelectedNode) {
                        let userTaskId = this.curSelectedNode['userTaskId'] == "all" ? undefined : this.curSelectedNode['userTaskId'];
                        Object.assign(this.viewparams, { 'taskDefinitionKey': userTaskId,'srfwfstep': userTaskId, 'processDefinitionKey': this.curSelectedNode['processDefinitionKey'] });
                        this.setTreeNodeHighLight(this.curSelectedNode);
                        this.getWFLinkModel({ 'taskDefinitionKey': userTaskId, 'processDefinitionKey': this.curSelectedNode['processDefinitionKey'] });
                    }
                    resolve(response.data);
                }
            }).catch((response: any) => {
                this.$throw(response, 'getWFStepModel');
            });
        })
    }

    /**
     * 获取工具栏按钮
     *
     * @param {*} data 请求参数
     * @memberof WfDynaExpGridViewBase
     */
    public getWFLinkModel(data: any) {
        if(!data.taskDefinitionKey){
          this.linkModel = [];
          return;
        }
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
            this.$throw(response, 'getWFLinkModel');
        });
    }

    /**
     * 工具栏点击事件
     * 
     * @param {*} linkItem 点击对象
     * @param {*} $event 事件源
     * @memberof WfDynaExpGridViewBase
     */
    public dynamic_toolbar_click(linkItem: any, $event: any) {
        let datas: any[] = [];
        let xData: any = (this.$refs[this.gridInstance.name] as any).ctrl;
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
            const targetView: any = this.viewRefData.find((item: any) => {
                return item.name === `WFACTION@${linkItem.sequenceflowview}`;
            })
            if (targetView) {
                let tempContext: any = Util.deepCopy(this.context);
                let tempViewParam: any = { actionView: linkItem.sequenceflowview, actionForm: linkItem.sequenceflowform };
                const appmodal = this.$appmodal.openModal({ viewname: Util.srfFilePath2(targetView.codeName), title: this.$tl(targetView.getCapPSLanguageRes()?.lanResTag, targetView.caption), height: targetView.height, width: targetView.width }, tempContext, tempViewParam);
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
     * @param {*} data 选择数据
     * @memberof WfDynaExpGridViewBase
     */
    public handleNodeClick(data: any) {
        this.curSelectedNode = data;
        this.setTreeNodeHighLight(this.curSelectedNode);
        let userTaskId = data['userTaskId'] == "all" ? undefined : data['userTaskId'];
        Object.assign(this.viewparams, { 'taskDefinitionKey': userTaskId,'srfwfstep': userTaskId, 'processDefinitionKey': data['processDefinitionKey'] });
        this.getWFLinkModel({ 'taskDefinitionKey': userTaskId, 'processDefinitionKey': this.curSelectedNode['processDefinitionKey'] });
        (this.$refs[this.searchFormInstance.name] as any).ctrl.onSearch();
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

    /**
     * @description 快速搜索点击事件
     * @param {*} $event
     * @memberof WfDynaExpGridViewBase
     */
    public onQuickSearchClick($event: any){
        const refs: any = this.$refs;
        if (refs[this.gridInstance?.name] && refs[this.gridInstance.name].ctrl) {
            refs[this.gridInstance?.name].ctrl.load(this.context, true);
        }
    }

    /**
     * 渲染快速搜索(DEFAULT)
     *
     * @return {*} 
     * @memberof WfDynaExpGridViewBase
     */
     public renderQuickSearch(){
        const searchformItem: Array<any> = this.searchFormInstance?.getPSDEFormItems() || [];
        let enableFilter = this.viewInstance?.enableFilter === true && searchformItem.length > 0;
        const popoverClass: string = this.searchFormInstance ? 'searchform-popover' : '';
        return  <template slot="quickSearch">
            <i-input class={{'app-quick-search': true, 'width-filter': enableFilter}} style='max-width: 400px;margin-top:4px;padding-left: 24px' search on-on-search={($event: any) => this.onQuickSearchClick($event)} v-model={this.query} placeholder={this.placeholder} />
            {<el-popover placement="bottom" popper-class={popoverClass} trigger="click" visible-arrow={false} on-hide={() => this.isExpandSearchForm = !this.isExpandSearchForm}>
                <i-button slot="reference" class={{'filter': true, 'is-expand': this.isExpandSearchForm, 'hidden-searchbtn': !enableFilter}} icon="ios-funnel" on-click={(e:any)=>{
                    if (!this.isExpandSearchForm) {
                        throttle(() => (AppGlobalService.getInstance() as any).executeGlobalAction('ToggleFilter',undefined, undefined, undefined, e, undefined, this, undefined),[],this);
                    }}} />
                {popoverClass && popoverClass != '' ? this.renderSearchForm() : null}
            </el-popover>}
        </template>
    }
}