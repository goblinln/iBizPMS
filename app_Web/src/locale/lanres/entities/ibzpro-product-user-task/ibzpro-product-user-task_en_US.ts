import IbzproProductUserTask_en_US_Base from './ibzpro-product-user-task_en_US_base';

function getLocaleResource(){
    const IbzproProductUserTask_en_US_OwnData = {};
    const targetData = Object.assign(IbzproProductUserTask_en_US_Base(), IbzproProductUserTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;