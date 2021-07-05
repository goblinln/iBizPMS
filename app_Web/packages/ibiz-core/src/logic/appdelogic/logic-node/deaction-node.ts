import { IPSDEDEActionLogic,IPSDELogicParam } from '@ibiz/dynamic-model-api';
import { AppDeLogicNodeBase } from './logic-node-base';
import { ActionContext } from '../action-context';

/**
 * 实体行为处理节点
 *
 * @export
 * @class AppDeLogicDeActionNode
 */
export class AppDeLogicDeActionNode extends AppDeLogicNodeBase{

    constructor() { 
        super();
    }
    
    /**
     * 执行节点
     *
     * @static
     * @param {IPSDEDEActionLogic} logicNode
     * @param {DeLogicContext} delogicContext
     * @memberof AppDeLogicDeActionNode
     */
    public async executeNode(logicNode: IPSDEDEActionLogic, actionContext: ActionContext){
        this.setParam(logicNode, actionContext);
        const dstEntity = logicNode.getDstPSAppDataEntity();
        const deAction = logicNode.getDstPSAppDEAction();
        const dstParam = actionContext.getParam((logicNode.getDstPSDELogicParam() as IPSDELogicParam)?.codeName);
        if(dstEntity && deAction && dstParam){
            const service = await  ___ibz___.gs.getService(dstEntity.codeName);
            const res = await service[deAction.codeName](actionContext.context, dstParam);
            if(res && res.ok && res.data){
                Object.assign(dstParam, res.data);
                return this.computeNextNodes(logicNode, actionContext);
            }else{
                return this.computeNextNodes(logicNode, actionContext);
            }
        }else{
            return this.computeNextNodes(logicNode, actionContext);
        }
    }
}