import { IPSDEUIMsgBoxLogic } from '@ibiz/dynamic-model-api';
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
                const options = {
                    type: logicNode.msgBoxType?.toLowerCase(),
                    title: logicNode.title,
                    content: logicNode.message,
                    buttonType: logicNode.buttonsType?.toLowerCase(),
                    showMode: logicNode.showMode?.toLowerCase(),
                    showClose: true,
                    mask: true,
                    maskClosable: true
                };
                const subject: Subject<any> | null = AppMessageBoxService.getInstance().open(options);
                const subscription = subject?.subscribe((result: any) => {
                    resolve(this.handleResponse(logicNode, actionContext, result));
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
    public handleResponse(logicNode: IPSDEUIMsgBoxLogic, actionContext: UIActionContext, result: string) {
        const { buttonsType } = logicNode;
        switch (buttonsType) {
            case 'YESNO':
                if (result && Object.is(result, 'yes')) {
                    return this.computeNextNodes(logicNode, actionContext);
                }
                break;
            case 'YESNOCANCEL':
                if (result && Object.is(result, 'yes')) {
                    return this.computeNextNodes(logicNode, actionContext);
                }
                break;
            case 'OK':
                if (result && Object.is(result, 'ok')) {
                    return this.computeNextNodes(logicNode, actionContext);
                }
                break;
            case 'OKCANCEL':
                if (result && Object.is(result, 'ok')) {
                    return this.computeNextNodes(logicNode, actionContext);
                }
                break;
            default:
                LogUtil.warn(`${buttonsType}未实现`);
                break;
        }
    }
}