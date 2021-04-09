import IbzproProductUserTask_zh_CN_Base from './ibzpro-product-user-task_zh_CN_base';

function getLocaleResource(){
    const IbzproProductUserTask_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzproProductUserTask_zh_CN_Base(), IbzproProductUserTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;