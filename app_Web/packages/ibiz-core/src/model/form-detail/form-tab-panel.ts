import { FormDetailModel } from './form-detail';

/**
 * 分页部件模型
 *
 * @export
 * @class FormTabPanelModel
 * @extends {FormDetailModel}
 */
export class FormTabPanelModel extends FormDetailModel {

    /**
     * 被激活分页
     *
     * @type {string}
     * @memberof FormTabPanelModel
     */
    public activatedPage: string = '';

    /**
     * 选中激活状态
     *
     * @type {string}
     * @memberof FormTabPanelModel
     */
    public clickActiviePage: string = '';

    /**
     * 分页子成员
     *
     * @type {any[]}
     * @memberof FormTabPanelModel
     */
    public tabPages: any[] = [];

    /**
     * Creates an instance of FormTabPanelModel.
     * FormTabPanelModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof FormTabPanelModel
     */
    constructor(opts: any = {}) {
        super(opts);
        this.tabPages = [...opts.tabPages];
        if (this.tabPages.length > 0) {
            const activate = this.form.viewparams?.srftabactivate?.toLowerCase() || this.form.viewparams?.srfnavtag?.toLowerCase();
            if (activate) {
              this.activatedPage = activate;
            } else {
              this.activatedPage = this.tabPages[0].name;
            }
        }
    }

    /**
     * 设置激活分页
     *
     * @memberof FormTabPanelModel
     */
    public setActiviePage(): void {
        if (!this.form) {
            return;
        }
        const detailsModel: any = this.form.detailsModel;

        const index = this.tabPages.findIndex((tabpage: any) => Object.is(tabpage.name, this.clickActiviePage) && Object.is(tabpage.name, this.activatedPage) && detailsModel[tabpage.name].visible);
        if (index !== - 1) {
            return;
        }

        this.tabPages.some((tabpage: any) => {
            if (detailsModel[tabpage.name].visible) {
                this.activatedPage = tabpage.name;
                return true;
            }
            return false;
        });
    }

    /**
     * 选中页面
     *
     * @param {*} $event
     * @returns {void}
     * @memberof FormTabPanelModel
     */
    public clickPage($event: any): void {
        if (!$event) {
            return;
        }

        this.clickActiviePage = $event;
        this.activatedPage = $event;
    }
}