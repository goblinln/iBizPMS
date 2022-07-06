import { IPSDEUILogicParam, IPSDEUIMsgBoxLogic } from '@ibiz/dynamic-model-api';
import { Subject } from 'rxjs';
import { LogUtil } from 'ibiz-core';
import { AppMessageBoxService } from '../../../app-service';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 消息弹窗节点
 *
 * @export
 * @class AppUILogicMsgboxNode
 */
export class AppUILogicMsgboxNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSDEUIMsgBoxLogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicMsgboxNode
     */
    public async executeNode(logicNode: IPSDEUIMsgBoxLogic, actionContext: UIActionContext) {
        return new Promise<void>((resolve) => {
            if (logicNode) {
                let msgBoxParam: any = actionContext.getParam((logicNode.getMsgBoxParam() as IPSDEUILogicParam)?.codeName);
                const options = {
                    type: logicNode.msgBoxType?.toLowerCase(),
                    title: msgBoxParam?.title ? msgBoxParam.title : logicNode.title,
                    content: msgBoxParam?.message ? msgBoxParam.message : logicNode.message,
                    buttonType: logicNode.buttonsType?.toLowerCase(),
                    showMode: logicNode.showMode?.toLowerCase(),
                    showClose: false,
                    mask: true,
                    maskClosable: true
                };
                const subject: Subject<any> | null = AppMessageBoxService.getInstance().open(options);
                const subscription = subject?.subscribe((result: any) => {
                    resolve(this.handleResponse(logicNode, actionContext, options, result));
                    subscription!.unsubscribe();
                    subject.complete();
                })
            } else {
                LogUtil.warn('消息弹窗逻辑节点参数不足');
            }
        });

    }

    /**
     * 处理响应
     *
     * @param {IPSDEUIMsgBoxLogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @param {string} result 响应结果
     * @memberof AppUILogicMsgboxNode
     */
    public handleResponse(logicNode: IPSDEUIMsgBoxLogic, actionContext: UIActionContext, options: any, result: string) {
        const { buttonsType } = logicNode;
        if (!Object.is(buttonsType, 'YESNO') && !Object.is(buttonsType, 'YESNOCANCEL') && !Object.is(buttonsType, 'OK') && !Object.is(buttonsType, 'OKCANCEL')) {
            LogUtil.warn(`${buttonsType}未实现`);
            return;
        }
        let msgBoxParam: any = actionContext.getParam((logicNode.getMsgBoxParam() as IPSDEUILogicParam)?.codeName);
        if (msgBoxParam)
            msgBoxParam.result = result;
        return this.computeNextNodes(logicNode, actionContext);
    }
}