import { MobMDViewEngine } from './mob-mdview-engine';

/**
 * 实体移动端工作流动态导航多数据视图引擎基础
 *
 * @export
 * @class WFDynaExpMDViewEngine
 * @extends {GridViewEngine}
 */
export class MobWFDynaExpMDViewEngine extends MobMDViewEngine {

    /**
     * Creates an instance of WFDynaExpMDViewEngine.
     * @memberof WFDynaExpMDViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof WFDynaExpMDViewEngine
     */
    public load(opts: any = {}, isnotify: boolean = false): void {
        this.view.getWFStepModel().then((res: any) => {
            if (!this.view.isformDruipart) {
                super.load(opts);
            } else {
                if (isnotify) {
                    super.load(opts);
                }
            }
        })
    }

    /**
     * 编辑数据
     *
     * @param {*} arg
     * @memberof MobWFMDViewEngine
     */
    public onEditData(arg: any): void {
        const loadParam: any = {};
        const { data }: { data: any } = arg;
        if (this.keyPSDEField && data[this.keyPSDEField] && !Object.is(data[this.keyPSDEField], '')) {
            Object.assign(loadParam, { [this.keyPSDEField]: data[this.keyPSDEField] });
        }
        if (this.openData && this.isFunc(this.openData)) {
            if (this.view.viewparams.hasOwnProperty('userTaskId')) {
                const paramsJO: any = { userTaskId: this.view.viewparams['userTaskId'] };
                this.openData([data], {}, paramsJO);
            } else {
                this.openData([data], {}, {});
            }
        }
    }
}