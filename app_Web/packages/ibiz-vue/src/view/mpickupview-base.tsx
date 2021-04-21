import { IPSAppDEPickupView, IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';
import { MPickupViewEngine, ModelTool } from 'ibiz-core';
import { MainViewBase } from './mainview-base';


/**
 * 数据多项选择视图基类
 *
 * @export
 * @class MPickUpViewBase
 * @extends {MainViewBase}
 */
export class MPickUpViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof MPickUpViewBase
     */
    public viewInstance!: IPSAppDEPickupView;

    /**
     * 选择视图面板实例
     * 
     * @memberof MPickUpViewBase
     */
    public pickUpViewPanelInstance!: IPSDEPickupViewPanel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MPickUpViewBase
     */
    public engine: MPickupViewEngine = new MPickupViewEngine();

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MPickUpViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal,oldVal);
        if(this.viewparams?.selectedData){
            this.selectedData = JSON.stringify(this.viewparams.selectedData);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MPickUpViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isShowButton = newVal?.isShowButton !== false;
        super.onStaticPropsChange(newVal,oldVal);
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MPickUpViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
      let engineOpts = ({
          view: this,
          parentContainer: this.$parent,
          p2k: '0',
          pickupviewPanel: (this.$refs[this.pickUpViewPanelInstance?.name] as any).ctrl,
          keyPSDEField: this.appDeCodeName.toLowerCase(),
          majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
        this.engine.init(engineOpts);
    }  

    /**
     *  视图初始化
     *
     * @memberof MPickUpViewBase
     */
    public async viewMounted() {
      super.viewMounted();      
      if (this.viewparams?.selectedData) {
          this.engine.onCtrlEvent('pickupviewpanel', 'selectionchange', this.viewparams.selectedData);
      }
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof MPickUpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.pickUpViewPanelInstance = ModelTool.findPSControlByType("PICKUPVIEWPANEL",this.viewInstance.getPSControls());      
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MPickUpViewBase
     */
    public renderMainContent() {
      return [this.renderBodyMessage(),
                  <div class="translate-contant">
                    <div class="center" style={{width : !this.isShowButton ? '100%' : ''}}>
                      {this.renderControlContent()}
                    </div>
                    {this.isShowButton && <div class="translate-buttons">
                      {this.renderButtons()}
                    </div>}
                    {this.isShowButton && <div class="right">
                      {this.renderMpickerSelect()}
                    </div>}
                  </div>,
              this.isShowButton && this.renderFooter(),
            ]
    }

    /**
     *  渲染视图底部按钮
     * @memberof MPickUpViewBase
     */
    public renderFooter() {
        const { viewStyle } = this.viewInstance;
        const style2 =
            <template slot="footer">
            {this.isShowButton && <div style={{ 'textAlign': 'right' }}>
                <i-button type="primary"  disabled={this.viewSelections.length > 0 ? false : true}  on-click={this.onClickOk.bind(this)}>{this.containerModel?.view_okbtn?.text}</i-button>
                    &nbsp;&nbsp;
                <i-button on-click={this.onClickCancel.bind(this)}>{this.containerModel?.view_cancelbtn?.text}</i-button>
            </div>}
          </template>
        const defaultStyle =
            <card dis-hover={true} bordered={false} class="footer">
              <row style={{ 'textAlign': 'right' }}>
                  <i-button type="primary"  disabled={this.viewSelections.length > 0 ? false : true}  on-click={this.onClickOk.bind(this)}>{this.containerModel?.view_okbtn?.text}</i-button>
                      &nbsp;&nbsp;
                  <i-button on-click={this.onClickCancel.bind(this)}>{this.containerModel?.view_cancelbtn?.text}</i-button>
              </row>
            </card>
        return viewStyle === 'STYLE2' ? style2 : defaultStyle;
    }

    /**
     * 渲染按钮
     * 
     * @memberof MPickUpViewBase
     */    
    public renderButtons(){
      return <div class="buttons">
                <i-button type="primary" title={this.containerModel?.view_rightbtn.text}
                    disabled={this.containerModel?.view_rightbtn.disabled}
                    on-click={this.onCLickRight.bind(this)}>
                    <i class="el-icon-arrow-right"></i>
                </i-button>
                <i-button type="primary" title={this.containerModel?.view_leftbtn.text}
                    disabled={this.containerModel?.view_leftbtn.disabled}
                    on-click={this.onCLickLeft.bind(this)}>
                    <i class="el-icon-arrow-left"></i>
                </i-button>
                <i-button type="primary" title={this.containerModel?.view_allrightbtn.text}
                    on-click={this.onCLickAllRight.bind(this)}>
                    <i class="el-icon-d-arrow-right"></i>
                </i-button>
                <i-button type="primary" title={this.containerModel?.view_allleftbtn.text}
                    on-click={this.onCLickAllLeft.bind(this)}>
                    <i class="el-icon-d-arrow-left"></i>
                </i-button>
            </div>
    }

    /**
     * 渲染多数据选择
     * 
     * @memberof MPickUpViewBase
     */     
    public renderMpickerSelect(){
      return <div class="mpicker-select">
        {this.viewSelections.map((item:any,index:number)=>{
          return <div key={index} class={item._select ? 'select' : ''} on-click={()=>this.selectionsClick(item)} on-dblclick={()=>this.selectionsDBLClick(item)}>
            <span>{item.srfmajortext}</span>
          </div>
        })}
      </div>
    } 

    /**
     * 渲染选择视图面板
     * 
     * @memberof MPickUpViewBase
     */    
    public renderControlContent(){
      let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.pickUpViewPanelInstance);
      return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.pickUpViewPanelInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof MPickUpViewBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps,{
            selectedData: this.selectedData,
        })
        Object.assign(targetCtrlParam.staticProps,{
            isSingleSelect: false,
            isShowButton: this.isShowButton,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent }
    }

    /**
     * 是否显示按钮
     *
     * @type {boolean}
     * @memberof MPickUpViewBase
     */
    public isShowButton: boolean = true;
    
    /**
     * 选中数据的字符串
     *
     * @type {string}
     * @memberof MPickUpViewBase
     */
    public selectedData: string = "";

    /**
     * 是否初始化已选中项
     *
     * @type {any[]}
     * @memberof MPickUpViewBase
     */
    public isInitSelected:boolean = false;
    
    /**
     * 视图选中数据
     *
     * @type {any[]}
     * @memberof MPickUpViewBase
     */
    public viewSelections:any[] = [];
    
    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof MPickUpViewBase
     */
    public isSingleSelect: boolean = false;

    /**
     * 选中数据单击
     *
     * @param {*} item
     * @memberof MPickUpViewBase
     */
    public selectionsClick(item:any):void {
        item._select = !item._select;
        const removeSelect: boolean = this.viewSelections.some((selection: any) => selection._select);
        this.containerModel.view_leftbtn.disabled = !removeSelect;
    }

    /**
     * 选中树双击
     *
     * @param {*} item
     * @memberof MPickUpViewBase
     */
    public selectionsDBLClick(item:any):void {
        const index: number = this.viewSelections.findIndex((selection: any) => Object.is(selection.srfkey, item.srfkey));
        if (index !== -1) {
            this.viewSelections.splice(index, 1);
        }
        const removeSelect: boolean = this.viewSelections.some((selection: any) => selection._select);
        this.containerModel.view_leftbtn.disabled = !removeSelect;
        this.selectedData = JSON.stringify(this.viewSelections);
    }

    /**
     * 删除右侧全部选中数据
     *
     * @memberof MPickUpViewBase
     */
    public onCLickLeft():void {
        const _selectiions = [...JSON.parse(JSON.stringify(this.viewSelections))];
        _selectiions.forEach((item: any) => {
            if (!item._select) {
                return;
            }
            const index = this.viewSelections.findIndex((selection: any) => Object.is(item.srfkey, selection.srfkey));
            if (index !== -1) {
                this.viewSelections.splice(index, 1);
            }
        });
        const removeSelect: boolean = this.viewSelections.some((selection: any) => selection._select);
        this.containerModel.view_leftbtn.disabled = !removeSelect;
        this.selectedData = JSON.stringify(this.viewSelections);
    }

    /**
     * 添加左侧选中数据
     *
     * @memberof MPickUpViewBase
     */
    public onCLickRight():void {
        Object.values(this.containerModel).forEach((model: any) => {
            if (!Object.is(model.type, 'PICKUPVIEWPANEL')) {
                return;
            }
            let newSelections:any[] = [];
            model.selections.forEach((item: any) => {
                const index: number = this.viewSelections.findIndex((selection: any) => Object.is(item.srfkey, selection.srfkey));
                if (index === -1) {
                    let _item: any = { ...JSON.parse(JSON.stringify(item)) };
                    Object.assign(_item, { _select: false })
                    newSelections.push(_item);
                }else{
                    newSelections.push(this.viewSelections[index]);
                }
            });
            this.viewSelections = this.removeDuplicates([...newSelections,...this.viewSelections]);
        });
    }

    /**
     * 去重
     *
     * @memberof MPickUpViewBase
     */
    public removeDuplicates(data:any):Array<any> {
        const uniqueSet = new Set(data);
        return [...uniqueSet];
    }

    /**
     * 选中数据全部删除
     *
     * @memberof MPickUpViewBase
     */
    public onCLickAllLeft():void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.viewSelections = [];
        this.containerModel.view_leftbtn.disabled = true;
        this.engine.onCtrlEvent('pickupviewpanel', 'selectionchange', []);
        this.selectedData = JSON.stringify(this.viewSelections);
    }

    /**
     * 添加左侧面板所有数据到右侧
     *
     * @memberof MPickUpViewBase
     */
    public onCLickAllRight():void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        Object.values(this.containerModel).forEach((model: any) => {
            if (!Object.is(model.type, 'PICKUPVIEWPANEL')) {
                return;
            }
            if(model.datas.length>0){
                model.datas.forEach((data:any,index:any)=>{
                    if(!data.srfmajortext){
                        let fieldCodeName = this.appDeMajorFieldName.toLowerCase();
                        Object.assign(data,{srfmajortext: data[fieldCodeName]});
                    }
                })
            }
            model.datas.forEach((item: any) => {
                const index: number = this.viewSelections.findIndex((selection: any) => Object.is(item.srfkey, selection.srfkey));
                if (index === -1) {
                    let _item: any = { ...JSON.parse(JSON.stringify(item)) };
                    Object.assign(_item, { _select: false })
                    this.viewSelections.push(_item);
                }
            });
        });
        this.selectedData = JSON.stringify(this.viewSelections);
    }

    /**
     * 确定
     *
     * @memberof MPickUpViewBase
     */
    public onClickOk(): void {
        this.$emit('view-event', { viewName: this.viewInstance?.name, action: 'viewdataschange', data: this.viewSelections });
        this.$emit('view-event', { viewName: this.viewInstance?.name, action: 'close', data: null });
    }

    /**
     * 取消
     *
     * @memberof MPickUpViewBase
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance?.name, action: 'viewdataschange', data: null });
        this.$emit('view-event', { viewName: this.viewInstance?.name, action: 'close', data: null });
    }    

}