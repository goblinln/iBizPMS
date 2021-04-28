import { MainViewBase } from "./main-view-base";
import { MobOptionViewEngine, ModelTool } from 'ibiz-core';
import { IPSAppDEMobEditView, IPSDEForm } from "@ibiz/dynamic-model-api";

/**
 * 选项操作视图基类
 *
 * @export
 * @class MobOptViewBase
 * @extends {MainViewBase}
 */
export class MobOptViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobOptViewBase
     */
    public viewInstance!: IPSAppDEMobEditView;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizMobFormModel}
     * @memberof MobOptViewBase
     */
    public editFormInstance !: IPSDEForm;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobOptViewBase
     */
    public engine: MobOptionViewEngine = new MobOptionViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobOptViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.editFormInstance.name] as any).ctrl,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });

    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobOptViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEMobEditView;
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls());
        this.initViewToolBar();
    }


    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobOptViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.editFormInstance.name, on: targetCtrlEvent });
    }

    /**
     *  渲染视图底部按钮
     */
    public renderFooter() {
        return <div class="option-view-btnbox" slot="footer">
            <app-mob-button
                class="option-btn medium"
                color="medium"
                text={this.$t('app.button.cancel')}
                on-click={this.back.bind(this)} />
            <app-mob-button
                class="option-btn success"
                text={this.$t('app.button.confirm')}
                on-click={this.save.bind(this)} />
        </div>
    }

    /**
     * 保存按钮事件
     *
     * @protected
     * @memberof MOBTESTMobOptionViewBase
     */
    protected save() {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let xDataShell: any = this.$refs.form;
        xData = xDataShell.ctrl;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        this.viewState.next({ tag: 'form', action: 'saveandexit', data: datas });
    }

    /**
     * 返回按钮事件
     *
     * @protected
     * @memberof MOBTESTMobOptionViewBase
     */
    protected back(args: any[]) {
        if (this.viewDefaultUsage === "routerView") {
            this.$store.commit("deletePage", this.$route.fullPath);
            this.$router.go(-1);
        } else {
            this.$emit('view-event', { viewName: this.viewCodeName, action: 'close', data: args instanceof MouseEvent ? null : args });
        }
    }

    /**
     * 检查表单是否修改
     *
     * @param {any[]} args
     * @memberof PimEducationMobEditViewBase
     */
    public async cheackChange(): Promise<any> {
        const view = this.$store.getters['viewaction/getAppView'](this.viewtag);
        if (view && view.viewdatachange) {
            const title: any = this.$t('app.tabpage.sureclosetip.title');
            const contant: any = this.$t('app.tabpage.sureclosetip.content');
            const result = await this.$Notice.confirm(title, contant);
            if (result) {
                this.$store.commit('viewaction/setViewDataChange', { viewtag: this.viewtag, viewdatachange: false });
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
